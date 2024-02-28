package ENTITY;

import EXCEPTIONS.MyExc;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

// Classe del modello che rappresenta la persona

public class Person {

    //Attributi
    String fiscalCode;
    String name;
    String surname;
    String dateOfBirth;
    String phoneNumber;
    String city;
    String street;
    String houseNumber;
    String cap;

    //Costruttori
    public Person(String name, String surname, String phoneNumber, String dateOfBirth, String city, String street, String houseNumber, String cap, String codiceFiscale) throws MyExc {
        setName(name);
        setSurname(surname);
        setPhoneNumber(phoneNumber);
        setDateOfBirth(dateOfBirth);
        setCity(city);
        setStreet(street);
        setHouseNumber(houseNumber);
        setCap(cap);
        setFiscalCode(codiceFiscale);
    }

    //Getter e Setter
    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) throws MyExc {
        if(fiscalCode.length() == 16)
            this.fiscalCode = fiscalCode;
        else
            throw new MyExc("Il codice fiscale deve essere di 16 caratteri.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) throws MyExc {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Imposta il formato della data
            java.util.Date utilDate = dateFormat.parse(dateOfBirth); // Converte la stringa in java.util.Date
            java.sql.Date sqlDateOfBirth = new java.sql.Date(utilDate.getTime()); // Converte in java.sql.Date

            // Calcola la data di maggiore età (18 anni fa)
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, -18);
            java.util.Date dataOfAge = cal.getTime();

            // Verifica se la data di nascita è maggiorenne
            if (sqlDateOfBirth.after(dataOfAge)) {
                throw new MyExc("Devi essere maggiorenne per poterti registrare.");
            } else {
                this.dateOfBirth = String.valueOf(sqlDateOfBirth);
            }
        } catch (ParseException e) {
            throw new MyExc("Formato data non valido. Utilizza il formato 'yyyy-MM-dd'.");
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws MyExc {
        if (phoneNumber.length() == 10)
            this.phoneNumber = phoneNumber;
        else
            throw new MyExc("Il numero di telefono deve esserre di 10 cifre");
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
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
                "codiceFiscale='" + fiscalCode + '\'' +
                ", nome='" + name + '\'' +
                ", cognome='" + surname + '\'' +
                ", dataNascita='" + dateOfBirth + '\'' +
                ", numeroTelefono='" + phoneNumber + '\'' +
                ", citta='" + city + '\'' +
                ", via='" + street + '\'' +
                ", nCivico='" + houseNumber + '\'' +
                ", cap='" + cap + '\'' +
                '}';
    }
}
