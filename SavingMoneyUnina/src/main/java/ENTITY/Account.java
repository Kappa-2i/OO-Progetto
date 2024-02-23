package ENTITY;


import EXCEPTIONS.MyExc;

import java.util.ArrayList;

// Classe del modello che rappresenta l'account
public class Account {

    //Attributi
    private String email;
    private String password;
    private String nomeutente;
    private Person person;
    private ArrayList<ContoCorrente> conti;

    //Costruttori
    public Account(String email, String password, String nomeutente) throws MyExc {
        setEmail(email);
        setPassword(password);
        setNomeutente(nomeutente);
    }

    public Account(String email, String password, String nomeutente, Person person) throws MyExc {
        setEmail(email);
        setPassword(password);
        setNomeutente(nomeutente);
        setPerson(person);
    }

    //Getter e Setter
    public Account(ArrayList<ContoCorrente> conti) {
        setConti(conti);
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

    public String getNomeutente() {
        return nomeutente;
    }

    public void setNomeutente(String nomeutente) {
        this.nomeutente = nomeutente;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person= person;
    }

    public ArrayList<ContoCorrente> getConti() {
        return conti;
    }

    public void setConti(ArrayList<ContoCorrente> conti) {
        this.conti = conti;
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
