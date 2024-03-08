package DAO;

import ENTITY.*;
import EXCEPTIONS.MyExc;

import java.util.ArrayList;

public interface CollectionDAO {

    /**
     * Metodo per selezionare le collezioni del conto corrente inserito dal db.
     * @param bankAccount riferimento per la ricerca delle collezioni.
     * @return ArrayList di collezioni.*/
    public ArrayList<Collection> selectCollectionByIban(BankAccount bankAccount);

    /**
     * Metodo per aggiungere una collezione al conto corrente personale.
     * @param bankAccount riferimento del conto su cui aggiungere la collezione;
     * @param name nome da dare alla collezione;
     * @param description descrizione della collezione.
     * */
    public void addCollection(BankAccount bankAccount, String name, String description) throws MyExc;

    /**
     * Metodo per cancellare una collezione dal conto corrente personale.
     * @param bankAccount riferimento del conto da cui eliminare la collezione;
     * @param name nome della collezione da eliminare.
     * */
    public void deleteCollection(BankAccount bankAccount, String name);
}
