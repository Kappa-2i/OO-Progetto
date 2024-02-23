package ENTITY;

// Classe del modello che rappresenta il salvadanaio

public class PiggyBank {

    //Attributi
    private String nomeSalvadanaio;
    private String descrizione;
    private double obiettivo;
    private double saldoRisparmio;
    private double saldoRimanente;
    private String dataCreazione;

    //Costruttori
    public PiggyBank(String nomeSalvadanaio, String descrizione, double obiettivo, double saldoRisparmio, double saldoRimanente, String dataApertura) {
        setNomeSalvadanaio(nomeSalvadanaio);
        setDescrizione(descrizione);
        setObiettivo(obiettivo);
        setSaldoRisparmio(saldoRisparmio);
        setSaldoRimanente(saldoRimanente);
        setDataCreazione(dataApertura);
    }

    //Getter e Setter
    public String getNomeSalvadanaio() {
        return nomeSalvadanaio;
    }

    public void setNomeSalvadanaio(String nomeSalvadanaio) {
        this.nomeSalvadanaio = nomeSalvadanaio;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getObiettivo() {
        return obiettivo;
    }

    public void setObiettivo(double obiettivo) {
        this.obiettivo = obiettivo;
    }

    public double getSaldoRisparmio() {
        return saldoRisparmio;
    }

    public void setSaldoRisparmio(double saldoRisparmio) {
        this.saldoRisparmio = saldoRisparmio;
    }

    public double getSaldoRimanente() {
        return saldoRimanente;
    }

    public void setSaldoRimanente(double saldoRimanente) {
        this.saldoRimanente = saldoRimanente;
    }

    public String getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(String dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    @Override
    public String toString() {
        return "Salvadanaio{" +
                "nomeSalvadanaio='" + nomeSalvadanaio + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", obiettivo=" + obiettivo +
                ", saldoRisparmio=" + saldoRisparmio +
                ", saldoRimanente=" + saldoRimanente +
                ", dataCreazione='" + dataCreazione + '\'' +
                '}';
    }
}

