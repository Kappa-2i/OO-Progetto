package DATABASE;

import java.sql.*;

public class DBConnection {
    // Singleton pattern per assicurare una sola istanza di questa classe.
    private static DBConnection dbcon = null;
    private Connection conn = null;

    // Costruttore privato per prevenire l'istanziazione diretta.
    private DBConnection(){
    }

    // Metodo statico per ottenere l'istanza singleton.
    public static DBConnection getDBConnection() {
        if (dbcon == null) {
            dbcon = new DBConnection();
        }
        return dbcon;
    }

    // Metodo per ottenere una connessione al database.
    public Connection getConnection() throws SQLException {
        // Creare una nuova connessione se non esiste o è stata chiusa.
        if (conn == null || conn.isClosed()) {
            // Credenziali del database.
            String url = "jdbc:postgresql://localhost:5432/db_smu_v3";
            String user = "postgres";
            String password = "admin";

//            String url = "jdbc:postgresql://localhost:5432/DB PROGETTO";
//            String user = "postgres";
//            String password = "3690";
            // Stabilire una connessione al database.
            conn = DriverManager.getConnection(url, user, password);
        }
        return conn;
    }

    // Metodo per disconnettere dal database.
    public void disconnect() throws SQLException{
        // Chiudere la connessione se è attiva.
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
