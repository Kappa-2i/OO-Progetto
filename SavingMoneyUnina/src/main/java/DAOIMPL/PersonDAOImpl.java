package DAOIMPL;

import DAO.PersonDAO;
import DATABASE.DBConnection;
import ENTITY.*;
import EXCEPTIONS.MyExc;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PersonDAOImpl implements PersonDAO {

    @Override
    public Boolean insertUser(String nome, String cognome, String telefono, String dataNascita, String citta, String via, String nCivico, String cap, String codiceFiscale){
        String insert = "INSERT INTO test.persona(codiceFiscale, nome, cognome, dataNascita, numerotelefono, città, via, n_civico, cap) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getDBConnection().getConnection();  // Ottenimento della connessione al database
             PreparedStatement statement = conn.prepareStatement(insert)) {  // Creazione di un PreparedStatement

            statement.setString(1, codiceFiscale);
            statement.setString(2, nome);
            statement.setString(3, cognome);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(dataNascita);
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
            statement.setDate(4, sqlDate);

            statement.setString(5, telefono);
            statement.setString(6, citta);
            statement.setString(7, via);
            statement.setString(8, nCivico);
            statement.setString(9, cap);

            // Esecuzione dell'insert
            statement.execute();
            statement.close();
            return true;
        } catch (SQLException e) {
            // Gestione delle eccezioni SQL
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public Person selectPersonaFromEmail(String email){
        String query = "SELECT nome, cognome, numerotelefono, datanascita, città, via, n_civico, cap, codicefiscale " +
                "FROM test.persona p " +
                "JOIN test.account a " +
                "ON a.persona_codicefiscale = p.codicefiscale " +
                "WHERE a.email = '" + email + "'";

        try(Connection conn = DBConnection.getDBConnection().getConnection();
            Statement statement = conn.createStatement()){

            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet != null){
                while(resultSet.next()){
                    Person persona = new Person(resultSet.getString("nome"), resultSet.getString("cognome"),
                            resultSet.getString("numerotelefono"), resultSet.getString("datanascita"),
                            resultSet.getString("città"), resultSet.getString("via"),
                            resultSet.getString("n_civico"), resultSet.getString("cap"),
                            resultSet.getString("codicefiscale"));
                    return persona;
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (MyExc e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
