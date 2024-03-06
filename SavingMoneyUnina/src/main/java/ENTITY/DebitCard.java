package ENTITY;

public class DebitCard extends Card{

    //Attributi
    private double maxSend;


    //Costruttori
    public DebitCard(String pan, String pin, String cvv, String typeCard, BankAccount bankAccount, double maxSend){
        super(pan, pin, cvv, typeCard, bankAccount);
        setMaxSend(maxSend);
    }

    //Getter e Setter
    public double getMaxSend() {
        return maxSend;
    }

    public void setMaxSend(double maxSend) {
        this.maxSend = maxSend;
    }


}
