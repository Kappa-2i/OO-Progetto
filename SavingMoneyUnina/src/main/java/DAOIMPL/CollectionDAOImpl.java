package DAOIMPL;

import DAO.CollectionDAO;
import ENTITY.BankAccount;
import ENTITY.Collection;
import EXCEPTIONS.MyExc;

import java.sql.*;
import java.util.ArrayList;

public class CollectionDAOImpl implements CollectionDAO {

    @Override
    public ArrayList<Collection> selectCollectionByIban(BankAccount bankAccount){

        ArrayList<Collection> collections = new ArrayList<Collection>();

        // Query SQL per ottenere i dettagli dell'utente
        String query = "SELECT r.nomeraccolta, r.descrizione " +
                "FROM test.raccolta r " +
                "WHERE r.contocorrente_iban = '" +bankAccount.getIban()+ "'";

        try (Connection conn = DATABASE.DBConnection.getDBConnection().getConnection();  // Ottenimento della connessione al database
             Statement statement = conn.createStatement()) {  // Creazione di un PreparedStatement

            // Esecuzione della query e gestione del ResultSet
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet != null){
                while (resultSet.next()){
                    //Creazione degli oggetti Salvadanaio.
                    Collection collection = new Collection(resultSet.getString("nomeraccolta"), resultSet.getString("descrizione"), bankAccount);
                    //Aggiunta della collezione all'ArrayList di collezioni
                    collections.add(collection);
                }
                return collections;
            }
        } catch (SQLException e) {
            // Gestione delle eccezioni SQL
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void addCollection(BankAccount bankAccount, String name, String description) throws MyExc {
        CallableStatement statement = null;
        try (Connection conn = DATABASE.DBConnection.getDBConnection().getConnection()) {

            //Chiamata della funzione del db.
            String callFunction = "{call test.crea_raccolta(?,?,?)}";

            statement = conn.prepareCall(callFunction);

            statement.setString(1, name);
            statement.setString(2, description);
            statement.setString(3, bankAccount.getIban());


            statement.executeQuery();

        } catch (SQLException e) {
            // "23505" è il codice di stato usato da PostgreSQL per indicare un errore di unique-violation
            if("23505".equals(e.getSQLState())) {
                throw new MyExc("Nome Raccolta già esistente!");
            }
            else {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void deleteCollection(BankAccount conto, String name){
        CallableStatement statement = null;
        try (Connection conn = DATABASE.DBConnection.getDBConnection().getConnection()) {

            //Chiamata della funzione del db.
            String callFunction = "{call test.rimuovi_raccolta(?,?)}";

            statement = conn.prepareCall(callFunction);

            statement.setString(1, conto.getIban());
            statement.setString(2, name);

            statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
