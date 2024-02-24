package DAO;

import ENTITY.Account;

// Classe DAO per la gestione dell'account nel database
public interface AccountDAO {

    /**
     * Metodo che recupera email e password dal db per controllare la validità delle credenziali inserite.
     * @param email riferimento per l'account da controllare.
     * @param password riferimento per l'account da controllare.*/
    Account checkCredentials(String email, String password);

    /**
     * Metodo per l'inserimeto di un nuovo accont all'interno del db
     * @param email riferimento per l'account da inserire.
     * @param nomeUtente riferimento per l'account da inserire.
     * @param password riferimento per l'account da inserire.
     * @param codiceFiscale riferimento per l'account da inserire.*/
    public void insertAccount(String email, String nomeUtente, String password, String codiceFiscale);

}