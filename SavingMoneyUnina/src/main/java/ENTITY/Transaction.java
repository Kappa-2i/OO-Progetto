package ENTITY;

public class Transaction {
    private double amount;
    private String causal;
    private String dateTransaction;
    private String timeTransaction;
    private String typeTransaction;
    private String iban; //iban di chi ci manda soldi, o a chi inviamo soldi
    private String entryCategory;
    private String exitCategory;
    private Collection collection;
    private BankAccount bankAccount;

    public Transaction(double amount, String causal, String dateTransaction, String timeTransaction, String typeTransaction, String iban, String entryCategory, String exitCategory, Collection collection, BankAccount bankAccount) {
        setAmount(amount);
        setCausal(causal);
        setDateTransaction(dateTransaction);
        setTimeTransaction(timeTransaction);
        setTypeTransaction(typeTransaction);
        setIban(iban);
        setEntryCategory(entryCategory);
        setExitCategory(exitCategory);
        setCollection(collection);
        setBankAccount(bankAccount);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCausal() {
        return causal;
    }

    public void setCausal(String causal) {
        this.causal = causal;
    }

    public String getTimeTransaction() {
        return timeTransaction;
    }

    public void setTimeTransaction(String timeTransaction) {
        this.timeTransaction = timeTransaction;
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(String dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public String getEntryCategory() {
        return entryCategory;
    }

    public void setEntryCategory(String entryCategory) {
        this.entryCategory = entryCategory;
    }

    public String getExitCategory() {
        return exitCategory;
    }

    public void setExitCategory(String exitCategory) {
        this.exitCategory = exitCategory;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    @Override
    public String toString() {
        return "Transazione{" +
                "importo=" + amount +
                ", causale='" + causal + '\'' +
                ", dataTransazione='" + dateTransaction + '\'' +
                ", orarioTransazione='" + timeTransaction + '\'' +
                ", tipoTransazione='" + typeTransaction + '\'' +
                ", iban='" + iban + '\'' +
                '}';
    }
}
