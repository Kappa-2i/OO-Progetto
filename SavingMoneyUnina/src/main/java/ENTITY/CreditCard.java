package ENTITY;

// Classe del modello che rappresenta la carta di credito

public class CreditCard extends Card{

    //Attributi
    private double priceUpgrade;

    //Costruttori
    public CreditCard(String pan, String pin, String cvv, String typeCard,  BankAccount bankAccount, double priceUpgrade){
        super(pan, pin, cvv, typeCard, bankAccount);
        setPriceUpgrade(priceUpgrade);
    }

    //Getter e Setter
    public double getPriceUpgrade() {
        return priceUpgrade;
    }

    public void setPriceUpgrade(double priceUpgrade) {
        this.priceUpgrade = priceUpgrade;
    }

}
