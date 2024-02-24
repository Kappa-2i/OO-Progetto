package ENTITY;


import EXCEPTIONS.MyExc;

import java.util.ArrayList;

// Classe del modello che rappresenta l'account
public class Account {

    //Attributi
    private String email;
    private String password;
    private String username;
    private Person person;
    private ArrayList<BankAccount> bankAccounts;

    //Costruttori
    public Account(String email, String password, String username) throws MyExc {
        setEmail(email);
        setPassword(password);
        setUsername(username);
    }

    public Account(String email, String password, String username, Person person) throws MyExc {
        setEmail(email);
        setPassword(password);
        setUsername(username);
        setPerson(person);
    }

    //Getter e Setter
    public Account(ArrayList<BankAccount> bankAccounts) {
        setBankAccounts(bankAccounts);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws MyExc {
        // Verifica che la stringa "email" contenga esattamente una "@" e nessuna altra occorrenza
        if (countOccurrences(email, '@') != 1)
            throw new MyExc("L'email deve contenere esattamente una carattere '@'.");
        else
            this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person= person;
    }

    public ArrayList<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(ArrayList<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    // Metodo per contare le occorrenze di un carattere in una stringa
    private int countOccurrences(String str, char character) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == character) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return "Account{" +
                "persona=" + person +
                '}';
    }
}
