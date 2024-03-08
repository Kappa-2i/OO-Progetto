package DAO;

import ENTITY.*;
import EXCEPTIONS.MyExc;

import java.util.ArrayList;

public interface TransactionDAO {

    /**
     * Metodo che sleziona le transazioni dell'account selezionato.
     * @param bankAccount riferimento del conto per selezinare le transazioni.
     * @return ArrayList di Trasazioni.*/
    public ArrayList<Transaction> selectTransazioniByIban(BankAccount bankAccount);

    /**
     * Metodo che permette di visualizzare il report mensile per un determinato conto corrente.
     * @param bankAccount riferimento per il conto del report;
     * @param month riferimento per il mese del report.
     * @return array delle cifre del report.*/
    public Double[] viewReport(BankAccount bankAccount, String month);

    /**
     * Metodo per recuperare il totale inviato mensile per un determinato conto corrente.
     * @param bankAccount riferimeto per il conto del totale;
     * @param month riferimento per il mese del totale.
     * @return cifra del totale inviato mensile*/
    public double totalSentMonthly(BankAccount bankAccount, String month);

    /**
     * Metodo per recuperare il totale ricevuto mensile per un determinato conto corrente.
     * @param bankAccount riferimeto per il conto del totale;
     * @param month riferimento per il mese del totale.
     * @return cifra del totale ricevuto mensile*/
    public double totalReceivedMonthly(BankAccount bankAccount, String month);

    /**
     * Metodo per recuperare il nome e il cognome dell'intestatrio dell'iban.
     * @param iban riferimento per recuperare nome e cognome.
     * @return nome e cognome dell'intestatario dell'iban.*/
    public String selectNameAndSurnameByIban(String iban);

    /**
     *Metodo per inviare un bonifico.
     * @param bankAccount riferimento del conto del mittente;
     * @param receiver riferimento del destinatario;
     * @param amount riferimento della cifra da inviare;
     * @param reason riferimento della causale;
     * @param category riferimento della categoria;
     * @param nameCollection riferimento per il nome della collezione.*/
    public void sendBankTransfer(BankAccount bankAccount, String receiver, String amount, String reason, String category, String nameCollection);

    /**
     *Metodo per inviare un bonifico istantaneo.
     * @param bankAccount riferimento per il conto del mittente;
     * @param receiver riferimento iban del destinatario;
     * @param amount riferimento della cifra da inviare;
     * @param reason riferimento della causale;
     * @param category riferimento della categoria;
     * @param nameCollection riferimento per il nome della collezione.*/
    public void sendIstantBankTransfer(BankAccount bankAccount, String receiver, String amount, String reason, String category, String nameCollection);

    /**
     * Metodo per controllare se il conto a cui viene inviato il bonifico è corretto.
     * @param receiver riferimento dell'iban del destinatario;
     * @param name riferimento del nome del destinatrario;
     * @param surname riferimento del cognome del destinatrario;
     * @return true se esiste il destinatario, false altrimenti.*/
    public boolean checkIban(String receiver, String name, String surname) throws MyExc;

    /**
     * Metodo per selezionare le transazioni in base alla collezione desiderata.
     * @param collection riferimento della collezione per recuperare le transazioni;
     * @param bankAccount riferimento del conto per cui recuperare le transazioni.
     * @return ArrayList di Transazioni*/
    public ArrayList<Transaction> selectTransactionsByCollection(Collection collection, BankAccount bankAccount);

    /**
     * Metodo per recuperare il totale della collezione desiderata.
     * @param bankAccount riferimento del contocorrente;
     * @param name riferimento del nome della collezione.
     * @return totale della collezione.*/
    public double selectSumOfCollections(BankAccount bankAccount, String name);

}
