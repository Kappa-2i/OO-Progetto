package ENTITY;

import java.util.ArrayList;

// Classe del modello che rappresenta il conto corrente

public class BankAccount {

    //Attributi
    private String iban;
    private double balance;
    private Account account;
    private ArrayList<PiggyBank> piggyBanks;
    private ArrayList<Transaction> transactions;

    //Costruttori
    public BankAccount(String iban, double saldo, Account account, ArrayList<PiggyBank> piggyBanks, ArrayList<Transaction> transactions) {
        setIban(iban);
        setBalance(saldo);
        setAccount(account);
        setPiggyBanks(piggyBanks);
        setTransactions(transactions);
    }

    public BankAccount(double saldo) {
        setBalance(saldo);
    }


    //Getter e Setter
    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ArrayList<PiggyBank> getPiggyBanks() {
        return piggyBanks;
    }

    public void setPiggyBanks(ArrayList<PiggyBank> piggyBanks) {
        this.piggyBanks = piggyBanks;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

}
