package ENTITY;

public class DebitCard extends Card{

    //Attributi
    private double limitFunds;


    //Costruttori
    public DebitCard(String pan, String pin, String cvv, String typeCard, BankAccount bankAccount, double limitFunds){
        super(pan, pin, cvv, typeCard, bankAccount);
        setLimitFunds(limitFunds);
    }

    //Getter e Setter
    public double getLimitFunds() {
        return limitFunds;
    }

    public void setLimitFunds(double limitFunds) {
        this.limitFunds = limitFunds;
    }

    @Override
    public String toString() {
        return super.toString() +
                "CartaDiDebito{" +
                "limiteFondi=" + limitFunds +
                '}';
    }
}
