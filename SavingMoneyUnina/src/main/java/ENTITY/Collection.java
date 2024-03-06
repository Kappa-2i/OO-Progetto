package ENTITY;

import java.util.ArrayList;

public class Collection {
    private String nameCollection;
    private String description;
    private ArrayList<Transaction> transactions;
    private BankAccount bankAccount;

    public Collection(String nameCollection, String description) {
        setNameCollection(nameCollection);
        setDescription(description);
    }

    public String getNameCollection() {
        return nameCollection;
    }

    public void setNameCollection(String nameCollection) {
        this.nameCollection = nameCollection;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "nameCollection='" + nameCollection + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
