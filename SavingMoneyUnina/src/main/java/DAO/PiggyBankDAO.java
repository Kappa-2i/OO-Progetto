package DAO;

import ENTITY.*;
import EXCEPTIONS.MyExc;

import java.util.ArrayList;

public interface PiggyBankDAO {

    /**
     * Metodo per recuperare i salvadanai dal bd.
     * @param bankAccount riferimento per recuperare i conti.
     * @return ArrayList di salvadanai.*/
    public ArrayList<PiggyBank> selectPiggyBank(BankAccount bankAccount);
    public void addPiggyBank(BankAccount selectedBanAccount, String name, double target, String description) throws MyExc;
    public void deletePiggyBank(BankAccount selectedBanAccount, String name);
    public void fillPiggyBank(BankAccount selectedBanAccount, String name, double money);
    public void getMoneyByPiggyBank(BankAccount selectedBanAccount, String name, double money);
}
