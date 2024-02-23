package ENTITY;

import java.util.ArrayList;

// Classe del modello che rappresenta il conto corrente

public class ContoCorrente {

    //Attributi
    private String iban;
    private double saldo;
    private Account account;
    private ArrayList<PiggyBank> salvadanai;
    private ArrayList<Transazione> transazioni;

    //Costruttori
    public ContoCorrente(String iban, double saldo, Account account, ArrayList<PiggyBank> salvadanai, ArrayList<Transazione> transazioni) {
        setIban(iban);
        setSaldo(saldo);
        setAccount(account);
        setSalvadanai(salvadanai);
        setTransazioni(transazioni);
    }

    public ContoCorrente(String iban, double saldo) {
        setIban(iban);
        setSaldo(saldo);
    }


    //Getter e Setter
    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ArrayList<PiggyBank> getSalvadanai() {
        return salvadanai;
    }

    public void setSalvadanai(ArrayList<PiggyBank> salvadanai) {
        this.salvadanai = salvadanai;
    }

    public ArrayList<Transazione> getTransazioni() {
        return transazioni;
    }

    public void setTransazioni(ArrayList<Transazione> transazioni) {
        this.transazioni = transazioni;
    }

}
