package DAO;

import ENTITY.Person;


public interface PersonDAO {

    /***/
    public Boolean insertUser(String nome, String cognome, String telefono, String dataNascita, String citta, String via, String nCivico, String cap, String codiceFiscale);
    public Person selectPersonaFromEmail(String email);
}
