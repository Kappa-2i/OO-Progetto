package DAO;

import ENTITY.Account;
import ENTITY.BankAccount;

import java.util.ArrayList;

public interface BankAccountDAO {
    /**
     * Metodo per selzionare i conti corrente dal db.
     * @param account riferimento per recuperare i conti.
     * @return ArrayList di conti correnti*/
    public ArrayList<BankAccount> selectBankAccountByAccount(Account account);

    /**
     * Metodo per recuperare il saldo aggiornato del conto corrente inserito dal db.
     * @param selectedBankAccount riferimento per il conto a cui aggiornare il saldo.
     * @return saldo aggiornato*/
    public Double updateBankAccount(BankAccount selectedBankAccount);

    /**
     * Metodo per inserire un nuovo Conto Corrente all'interno del db.
     * @param email riferimento per l'account a cui aggiungere il conto.
     * @return true se l'aggiunta del metodo è andata a buon fine, false altrimenti.*/
    public Boolean insertBankAccount(String email);

    /**
     * Metodo per l'eliminazione di un Conto Corrente all'interno del db.
     * @param iban riferimento per eliminare il conto.*/
    public void deleteBankAccount(String iban);


}
