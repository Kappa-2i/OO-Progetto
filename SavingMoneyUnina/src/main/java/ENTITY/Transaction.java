package ENTITY;

public class Transaction {
    private double importo;
    private String causale;
    private String dataTransazione;
    private String orarioTransazione;
    private String tipoTransazione;
    private String iban; //iban di chi ci manda soldi, o a chi inviamo soldi
    private ContoCorrente contoCorrente;

    public Transaction(double importo, String causale, String dataTransazione, String orarioTransazione, String tipoTransazione, String iban, ContoCorrente contoCorrente) {
        setImporto(importo);
        setCausale(causale);
        setDataTransazione(dataTransazione);
        setOrarioTransazione(orarioTransazione);
        setTipoTransazione(tipoTransazione);
        setIban(iban);
        setContoCorrente(contoCorrente);
    }

    public double getImporto() {
        return importo;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }

    public String getCausale() {
        return causale;
    }

    public void setCausale(String causale) {
        this.causale = causale;
    }

    public String getDataTransazione() {
        return dataTransazione;
    }

    public void setDataTransazione(String dataTransazione) {
        this.dataTransazione = dataTransazione;
    }

    public String getOrarioTransazione() {
        return orarioTransazione;
    }

    public void setOrarioTransazione(String orarioTransazione) {
        this.orarioTransazione = orarioTransazione;
    }

    public String getTipoTransazione() {
        return tipoTransazione;
    }

    public void setTipoTransazione(String tipoTransazione) {
        this.tipoTransazione = tipoTransazione;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public ContoCorrente getContoCorrente() {
        return contoCorrente;
    }

    public void setContoCorrente(ContoCorrente contoCorrente) {
        this.contoCorrente = contoCorrente;
    }

    @Override
    public String toString() {
        return "Transazione{" +
                "importo=" + importo +
                ", causale='" + causale + '\'' +
                ", dataTransazione='" + dataTransazione + '\'' +
                ", orarioTransazione='" + orarioTransazione + '\'' +
                ", tipoTransazione='" + tipoTransazione + '\'' +
                ", iban='" + iban + '\'' +
                '}';
    }
}
