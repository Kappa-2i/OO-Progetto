package ENTITY;

public class Card {
    //Attributi
    private String pan;
    private String pin;
    private String cvv;
    private String typeCard;
    private BankAccount bankAccount;

    //Costruttori
    public Card(String pan, String pin, String cvv, String typeCard, BankAccount bankAccount) {
        setCvv(cvv);
        setPan(pan);
        setPin(pin);
        setTypeCard(typeCard);
        setBankAccount(bankAccount);
    }

    //Getter e Setter
    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getTypeCard() {
        return typeCard;
    }

    public void setTypeCard(String typeCard) {
        this.typeCard = typeCard;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }


}
