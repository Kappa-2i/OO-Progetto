package DAO;

import ENTITY.*;
import EXCEPTIONS.MyExc;

import java.util.ArrayList;

public interface PiggyBankDAO {

    /**
     * Metodo per recuperare i salvadanai dal db.
     * @param bankAccount riferimento per recuperare i conti.
     * @return ArrayList di salvadanai.*/
    public ArrayList<PiggyBank> selectPiggyBank(BankAccount bankAccount);

    /**
     * Metodo per inserire il salvadanaio creato nel db.
     * @param selectedBanAccount riferimento del conto selezionato;
     * @param name riferimento nome salvadanaio inserito;
     * @param target riferimento obiettivo del salvadanaio inserito;
     * @param description riferimento descrizione del salvadanaio inserito.
     */
    public void addPiggyBank(BankAccount selectedBanAccount, String name, double target, String description) throws MyExc;

    /**
     * Metodo per eliminare il salvadanaio dal db.
     * @param selectedBanAccount riferimento del conto sui cui si opera;
     * @param name riferimento nome salvadanaio da eliminare.
     */
    public void deletePiggyBank(BankAccount selectedBanAccount, String name);

    /**
     * Metodo per inviare soldi al salvadanaio nel db.
     * @param selectedBanAccount riferimento del conto selezionato;
     * @param name riferimento nome del salvadanaio da riempire;
     * @param money riferimento soldi da inviare al salvadanaio;
     */
    public void fillPiggyBank(BankAccount selectedBanAccount, String name, double money);

    /**
     * Metodo per prendere soldi al salvadanaio nel db.
     * @param selectedBanAccount riferimento del conto selezionato;
     * @param name riferimento nome del salvadanaio da svuotare;
     * @param money riferimento soldi da prendere dal salvadanaio;
     */
    public void getMoneyByPiggyBank(BankAccount selectedBanAccount, String name, double money);
}
