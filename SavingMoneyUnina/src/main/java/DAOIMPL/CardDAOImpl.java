package DAOIMPL;

import CONTROLLER.Controller;
import DAO.CardDAO;
import DATABASE.DBConnection;
import ENTITY.*;

import java.sql.*;

public class CardDAOImpl implements CardDAO {

    @Override
    public Card selectCard(BankAccount contoCorrente){
        String query = "SELECT c.pan, c.pin, c.cvv, c.tipocarta, c.maxinvio, c.price_upgrade, c.contocorrente_iban " +
                "FROM test.carta c " +
                "WHERE c.contocorrente_iban = '" + contoCorrente.getIban() + "'";
        try(Connection conn = DBConnection.getDBConnection().getConnection();
            Statement statement = conn.createStatement()){

            //Esecuzione della query e gestione del ResultSet
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet != null){
                while(resultSet.next()){

                    /*Se la carta è di tipo Carta di Debito viene creata una carta di debito,
                     viene creata una carta di credito altrimenti.*/

                    if (resultSet.getString("tipocarta").equals("CartaDiDebito")) {
                        Card debitCard = new DebitCard(resultSet.getString("pan"), resultSet.getString("pin"), resultSet.getString("cvv"), resultSet.getString("tipocarta"),
                                contoCorrente, resultSet.getDouble("maxinvio"));
                        return debitCard;
                    }
                    else {
                        Card creditCard = new CreditCard(resultSet.getString("pan"), resultSet.getString("pin"), resultSet.getString("cvv"), resultSet.getString("tipocarta"),
                                contoCorrente, resultSet.getDouble("price_upgrade"));
                        return creditCard;
                    }

                }
            }
        }
        catch (SQLException e){
            // Gestione delle eccezioni SQL
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void upgradeCard(String pan){
        CallableStatement statement = null;
        try (Connection conn = DBConnection.getDBConnection().getConnection()){

            //Chiamata della funzione del db.
            String callFunction = "{call test.upgrade_carta(?)}";

            statement = conn.prepareCall(callFunction);

            statement.setString(1, pan);

            statement.executeQuery();
        }
        catch (SQLException e){
            // Gestione delle eccezioni SQL
            e.printStackTrace();
        }
    }

    @Override
    public void downgradeCard(String pan){
        CallableStatement statement = null;
        try (Connection conn = DBConnection.getDBConnection().getConnection()){

            //Chiamata della funzione del db.
            String callFunction = "{call test.downgrade_carta(?)}";

            statement = conn.prepareCall(callFunction);

            statement.setString(1, pan);

            statement.executeQuery();
        }
        catch (SQLException e){
            // Gestione delle eccezioni SQL
            e.printStackTrace();
        }
    }
}
