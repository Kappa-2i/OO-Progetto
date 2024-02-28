package ENTITY;

public class CreditCard extends Card{

    //Attributi
    private double maximumMoneyTransfer;

    //Costruttori
    public CreditCard(String pan, String pin, String cvv, String typeCard,  BankAccount bankAccount, double maximumMoneyTransfer){
        super(pan, pin, cvv, typeCard, bankAccount);
        setMaximumMoneyTransfer(maximumMoneyTransfer);
    }

    //Getter e Setter
    public double getMaximumMoneyTransfer() {
        return maximumMoneyTransfer;
    }

    public void setMaximumMoneyTransfer(double maximumMoneyTransfer) {
        this.maximumMoneyTransfer = maximumMoneyTransfer;
    }

    @Override
    public String toString() {
        return super.toString() +
                "CartaDiCredito{" +
                "maxInvio=" + maximumMoneyTransfer +
                '}';
    }
}
