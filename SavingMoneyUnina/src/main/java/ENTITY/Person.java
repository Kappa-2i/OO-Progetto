package ENTITY;

import EXCEPTIONS.MyExc;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

// Classe del modello che rappresenta la persona

public class Person {

    //Attributi
    String codiceFiscale;
    String nome;
    String cognome;
    String dataNascita;
    String numeroTelefono;
    String citta;
    String via;
    String nCivico;
    String cap;

    //Costruttori
    public Person(String nome, String cognome, String numeroTelefono, String dataNascita, String citta, String via, String nCivico, String cap, String codiceFiscale) throws MyExc {
        setNome(nome);
        setCognome(cognome);
        setNumeroTelefono(numeroTelefono);
        setDataNascita(dataNascita);
        setCitta(citta);
        setVia(via);
        setnCivico(nCivico);
        setCap(cap);
        setCodiceFiscale(codiceFiscale);
    }

    //Getter e Setter
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) throws MyExc {
        if(codiceFiscale.length() == 16)
            this.codiceFiscale = codiceFiscale;
        else
            throw new MyExc("Il codice fiscale deve essere di 16 caratteri.");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(String dataNascita) throws MyExc {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Imposta il formato della data
            java.util.Date utilDate = dateFormat.parse(dataNascita); // Converte la stringa in java.util.Date
            java.sql.Date sqlDataNascita = new java.sql.Date(utilDate.getTime()); // Converte in java.sql.Date

            // Calcola la data di maggiore età (18 anni fa)
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, -18);
            java.util.Date dataMaggiorenne = cal.getTime();

            // Verifica se la data di nascita è maggiorenne
            if (sqlDataNascita.after(dataMaggiorenne)) {
                throw new MyExc("Devi essere maggiorenne per poterti registrare.");
            } else {
                this.dataNascita = String.valueOf(sqlDataNascita);
            }
        } catch (ParseException e) {
            throw new MyExc("Formato data non valido. Utilizza il formato 'yyyy-MM-dd'.");
        }
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) throws MyExc {
        if (numeroTelefono.length() == 10)
            this.numeroTelefono = numeroTelefono;
        else
            throw new MyExc("Il numero di telefono deve esserre di 10 cifre");
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getnCivico() {
        return nCivico;
    }

    public void setnCivico(String nCivico) {
        this.nCivico = nCivico;
    }

    public String getCap() throws MyExc {
        if (cap.length() == 5)
            return cap;
        else
            throw new MyExc("Il cap deve essere di 5 cifre.");
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "codiceFiscale='" + codiceFiscale + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita='" + dataNascita + '\'' +
                ", numeroTelefono='" + numeroTelefono + '\'' +
                ", citta='" + citta + '\'' +
                ", via='" + via + '\'' +
                ", nCivico='" + nCivico + '\'' +
                ", cap='" + cap + '\'' +
                '}';
    }
}
