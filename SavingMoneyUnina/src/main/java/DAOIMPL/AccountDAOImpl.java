package DAOIMPL;

import DAO.AccountDAO;
import DATABASE.DBConnection;
import ENTITY.*;
import EXCEPTIONS.MyExc;

import java.sql.*;

public class AccountDAOImpl implements AccountDAO{

    @Override
    public void insertAccount(String email, String password, String name, String surname){
        // insert SQL che inserisce i dati dell'account creato
        String insert = "INSERT INTO test.account(email, password, nome, cognome) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getDBConnection().getConnection();  // Ottenimento della connessione al database
             PreparedStatement statement = conn.prepareStatement(insert)) {  // Creazione di un PreparedStatement

            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, name);
            statement.setString(4, surname);

            // Esecuzione dell'insert
            statement.execute();
            statement.close();

        } catch (SQLException e) {
            // Gestione delle eccezioni SQL
            e.printStackTrace();
        }
    }


    @Override
    public Account checkCredentials(String email, String password){
        // Query SQL per ottenere i dettagli dell'utente
        String query = "SELECT a.email, a.password, a.nome, a.cognome " +
                "FROM test.account a " +
                " WHERE a.email = '" + email + "' AND a.password = '" + password + "'";

        try (Connection conn = DBConnection.getDBConnection().getConnection();  // Ottenimento della connessione al database
             Statement statement = conn.createStatement()) {  // Creazione di un PreparedStatement

            // Esecuzione della query e gestione del ResultSet
            ResultSet resultSet = statement.executeQuery(query);

            // Verifico se ho una tupla da analizzare
            if (resultSet != null){
                while (resultSet.next()) {
                    // Ritorno dei dati dell'utente sotto forma di stringhe
                    Account account = new Account(resultSet.getString("Email"), resultSet.getString("Password"), resultSet.getString("Nome"), resultSet.getString("Cognome"));
                    return account;
                }
            }
        } catch (SQLException e) {
            // Gestione delle eccezioni SQL
            e.printStackTrace();
        } catch (MyExc e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
