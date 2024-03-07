package CONTROLLER;

import DAO.*;
import DAOIMPL.*;
import ENTITY.*;
import EXCEPTIONS.MyExc;
import GUI.*;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

// Classe del controller
public class Controller {

    //Dichiarazioni delle Gui
    private LoginViewGUI frameLogin;
    private SignUpViewGUI frameSignIn;
    private BankAccountPickViewGUI framePick;
    private HomeViewGUI frameHome;
    private CardViewGUI frameCard;
    private PiggyBanksViewGUI frameSalvadanaio;
    private TransactionViewGUI frameTransazioni;
    private BankTransferViewGUI frameBankTransfer;
    private CollectionPickViewGUI framePickCollection;
    private CollectionViewGUI frameCollection;

    //Icone
    ImageIcon iconAlert = new ImageIcon(Controller.class.getResource("/IMG/alert.png"));
    ImageIcon iconCancel = new ImageIcon(Controller.class.getResource("/IMG/cancel.png"));
    ImageIcon iconChecked = new ImageIcon(Controller.class.getResource("/IMG/checked.png"));
    ImageIcon iconDelete = new ImageIcon(Controller.class.getResource("/IMG/delete.png"));

    //Dichiarazioni delle Dao
    private AccountDAO accountDao;
    private BankAccountDAO contoCorrenteDAO;
    private CardDAO cartaDAO;
    private PiggyBankDAO salvadanaioDAO;
    private TransactionDAO transazioneDAO;
    private CollectionDAO collectionDAO;

    //Dichiarazione delle variabili
    private Account account = null;
    private ArrayList<BankAccount> conti = null;
    private BankAccount contoScelto = null;
    private Card carta = null;
    private ArrayList<PiggyBank> salvadanai = null;
    private ArrayList<Transaction> transazioni = null;
    private Double[] report = null;
    private String credenzialiIbanMittDest = null;
    private ArrayList<Collection> collections = null;
    private Collection selectedCollection = null;
    private ArrayList<Transaction> transactionsCollection = null;

    public Controller() {
        frameLogin = new LoginViewGUI(this); //LoginView accetta ControllerLogin come parametro
        frameLogin(true);

        //DAO
        this.accountDao = new AccountDAOImpl();
        this.contoCorrenteDAO = new BankAccountDAOImpl();
        this.cartaDAO = new CardDAOImpl();
        this.salvadanaioDAO = new PiggyBankDAOImpl();
        this.transazioneDAO = new TransactionDAOImpl();
        this.collectionDAO = new CollectionDAOImpl();
    }

    /**
     * Metodo che controlla la validità dei campi email e password inseriti al momento del login.
     * <br> In caso di successo viene visulalizzata la pagina Home, bisogna riporovare altrimenti.
     * @param email per controllare che l'email sia corretta.
     * @param password per controllare che la password sia corretta.
     * */
    public void checkCredentials(String email, String password){
        if((!email.isEmpty()) && (!password.isEmpty())){
            account = accountDao.checkCredentials(email.toLowerCase(), password);
            if (account != null){
                frameLogin(false);
                showPickFrame();
            }
            else{
                //Se uno dei due campi è sbagliato viene visualizzato un messaggio di errore.
                JOptionPane.showMessageDialog(
                        frameLogin,
                        "Email o Password Errati",
                        "Errore di Login",
                        JOptionPane.PLAIN_MESSAGE,
                        iconCancel
                );
            }
        }
        else {
            //Se i campi non vengono compilati viene visualizzato un messaggio di errore.
            JOptionPane.showMessageDialog(
                    frameLogin,
                    "Inserisci delle credenziali valide!",
                    "Errore di Login",
                    JOptionPane.PLAIN_MESSAGE,
                    iconAlert);
        }
    }

    /**
     *Metodo che permette di gestire la viusalizzazione della pagina di SignIn.*/
    public void showFrameSignIn(){
        frameLogin(false);
        frameSignIn = new SignUpViewGUI(this);
        frameSignIn(true);
    }

    public void showPickFrame(){
        framePick = new BankAccountPickViewGUI(this);
        framePick(true);
    }



    public void insertAccount(String email, String password, String name, String surname){
        try{
            account = new Account(email, password, name, surname);
            if (!email.isEmpty() && !password.isEmpty() && !name.isEmpty() && !surname.isEmpty()) {
                accountDao.insertAccount(email, password, name, surname);
                JOptionPane.showMessageDialog(
                        frameSignIn,
                        "Dati dell'account inseriti!",
                        "Benvenuta/o",
                        JOptionPane.PLAIN_MESSAGE,
                        iconChecked);
            }
            else{
                JOptionPane.showMessageDialog(
                        frameSignIn,
                        "Inserisci delle credenziali valide!",
                        "Errore",
                        JOptionPane.PLAIN_MESSAGE,
                        iconAlert);
            }
        }
        catch (MyExc exc){
            JOptionPane.showMessageDialog(
                    frameSignIn,
                    "L'email deve contenere una '@'!",
                    "Errore",
                    JOptionPane.PLAIN_MESSAGE,
                    iconAlert
            );
        }


    }

    public boolean confirmedPassword(String password, String confirmedPassword){
        if (password.equals(confirmedPassword))
            return true;
        return false;
    }

    /**
     * Metodo che seleziona tutti i conti relativi all'account che gli viene passato
     * @param account riferimento per i conti da selzionare
     * */
    public ArrayList<BankAccount> selectBankAccountByAccount(Account account){
        conti = new ArrayList<BankAccount>();
        conti = contoCorrenteDAO.selectBankAccountByAccount(account);
        account.setBankAccounts(conti);
        return conti;
    }

    public void updateBankAccount(BankAccount conto){
        contoScelto.setBalance(contoCorrenteDAO.updateBankAccount(conto));
    }

    /**
     * Metodo che aggiunge un nuovo Conto Corrente. <br>Ritorna true in caso venga completato correttanente, false altrimenti.
     * @param email riferimeto per il conto da aggiungere.*/
    public Boolean insertBankAccount(String email){
        if (contoCorrenteDAO.insertBankAccount(email)){
            return true;
        }
        else
            return false;
    }

    /**
     * Metodo che elimina un determinato Conto Corrente.
     *@param iban riferimento per l'eliminazione del conto.*/
    public void deleteBankAccount(String iban){
        contoCorrenteDAO.deleteBankAccount(iban);
        frameHome(false);

        //Viene aggiornata la pagina con i conti corretti.
            checkCredentials(account.getEmail(), account.getPassword());

        JOptionPane.showMessageDialog(
                framePick,
                "Conto Corrente con Iban: " +iban+ ", eliminato con successo!",
                "Conto Corrente eliminato",
                JOptionPane.PLAIN_MESSAGE,
                iconDelete
        );
    }

    /**
     * Metodo per gesitre la visualizzaione della pagina di Home page.
     * @param conto riferimento per le informazioni da visualizzare in Home Page.*/
    public void showHomePage(BankAccount conto){

        //Viene selezionato il conto dopo averlo scelto dalla pagina di selezione.
        contoScelto = conto;
        //Viene recuperata la carta associata al conto scelto.
        if(carta != null)
            carta = null;
        carta = cartaDAO.selectCard(contoScelto);

        framePick(false);
        if(frameSalvadanaio != null)
            frameSalvadanaio(false);
        if(framePickCollection != null)
            framePickCollection(false);
        if(frameHome != null)
            frameHome(false);
        frameHome = new HomeViewGUI(this);
        frameHome(true);
    }

    /**
     * Metodo che permette di tornare alla pagina di Login.
     */
    public void backLoginPage(){
        //Quando si torna alla pagina di Login l'account viene settato a null.
        account = null;
        //Quando si torna alla pagina di Login il conto scelto viene settato a null.
        if (contoScelto!= null)
            contoScelto = null;

        if(frameBankTransfer!=null)
            frameBankTransfer(false);
        if(frameCard!=null)
            frameCard(false);
        if(framePick != null)
            framePick(false);
        if(frameHome != null)
            frameHome(false);
        if(frameSignIn != null)
            frameSignIn(false);

        frameLogin(true);
    }


    /**
     * Metodo che permette gestire la visualizzazione della pagina della carta.*/
    public void showCardPage(){
        if (frameCard != null) {
            frameCard(false);
            frameCard = new CardViewGUI(this);
            frameCard(true);
        }
        else {
            frameCard = new CardViewGUI(this);
            frameCard(true);
        }

    }




    /**
     * Metodo che permette di effettuare l'upgrade della carta da Debito (default) a Credito.
     * @param pan riferimento per la carta da aggiornare.*/
    public void upgradeCarta(String pan){
        if(contoScelto.getBalance() >= 5) {
            cartaDAO.upgradeCard(pan);
            JOptionPane.showMessageDialog(
                    frameHome,
                    "La tua carta è stata aggiornata a carta di credito!",
                    "Aggiornamento effettuato",
                    JOptionPane.PLAIN_MESSAGE,
                    iconChecked
            );
            frameHome(false);
            contoScelto.setBalance(contoCorrenteDAO.updateBankAccount(contoScelto));
            showHomePage(contoScelto);
        }
        else {
            JOptionPane.showMessageDialog(
                    frameHome,
                    "Saldo insufficiente per effettuare l'upgrade!",
                    "Errore",
                    JOptionPane.PLAIN_MESSAGE,
                    iconAlert
            );
        }
    }

    /**
     * Metodo che permette di effetuare il downgrade della carta Da credito a Debito.
     * @param pan riferimento per la carta da aggiornare.*/
    public void downgradeCarta(String pan){
        cartaDAO.downgradeCard(pan);
        JOptionPane.showMessageDialog(
                null,
                "La tua carta è stata aggiornata a carta di debito!",
                "Aggiornamento effettuato",
                JOptionPane.PLAIN_MESSAGE,
                iconChecked
        );
        frameHome(false);
        showHomePage(contoScelto);
    }

    /**
     * Metodo che permette di gestire la visualizzazione della pagina dei salvadanai. */
    public void showSalvadanaioPage(){
        if (frameSalvadanaio != null)
            frameSalvadanaio(false);
        //Vengono recuperati i salvadanai associati al conto scelto.
        salvadanai = salvadanaioDAO.selectPiggyBank(contoScelto);
        contoScelto.setPiggyBanks(salvadanai);
        if(frameBankTransfer!=null){
            frameBankTransfer(false);
        }
        if(frameCard!=null){
            frameCard(false);
        }
        frameSalvadanaio = new PiggyBanksViewGUI(this);
        frameHome(false);
        frameSalvadanaio(true);
    }

    public void addPiggyBank(String nome, double obiettivo, String descrizione) throws MyExc{
        try {
            if(!nome.isEmpty() && !descrizione.isEmpty())
                salvadanaioDAO.addPiggyBank(contoScelto, nome, obiettivo, descrizione);
            else{
                JOptionPane.showMessageDialog(
                        null,
                        "Riempi tutti i campi!",
                        "Errore inserimento",
                        JOptionPane.ERROR_MESSAGE,
                        iconAlert
                );
            }
        } catch (MyExc e) {
            JOptionPane.showMessageDialog(
                    frameSalvadanaio,
                    e.getMessage(),
                    "Errore",
                    JOptionPane.PLAIN_MESSAGE,
                    iconCancel
            );
        }

    }

    public void deletePiggyBank(String nome){
        salvadanaioDAO.deletePiggyBank(contoScelto, nome);
    }

    public void fillPiggyBank(String nome, String soldiDaInviare){
        try{
            if(!soldiDaInviare.isEmpty()) {
                if(Math.round((Double.parseDouble(soldiDaInviare)*100.00)/100.00) > 0) {
                    if (contoScelto.getBalance() >= Math.round((Double.parseDouble(soldiDaInviare) * 100.00) / 100.00)) {
                        salvadanaioDAO.fillPiggyBank(contoScelto, nome, Math.round((Double.parseDouble(soldiDaInviare) * 100.00) / 100.00));
                    } else {
                        JOptionPane.showMessageDialog(
                                frameSalvadanaio,
                                "Saldo conto corrente insufficiente!",
                                "Errore",
                                JOptionPane.PLAIN_MESSAGE,
                                iconCancel

                        );
                    }
                }
                else {
                    JOptionPane.showMessageDialog(
                            frameSalvadanaio,
                            "Inserisci una cifra valida!",
                            "Errore inserimento",
                            JOptionPane.PLAIN_MESSAGE,
                            iconAlert
                    );
                }
            }
            else {
                JOptionPane.showMessageDialog(
                        frameSalvadanaio,
                        "Riempi tutti i campi!",
                        "Errore inserimento",
                        JOptionPane.PLAIN_MESSAGE,
                        iconAlert
                );
            }
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(
                    frameSalvadanaio,
                    "Inserisci una cifra valida!",
                    "Errore inserimento",
                    JOptionPane.ERROR_MESSAGE,
                    iconAlert
            );
        }
    }

    public void getMoneyByPiggyBank(String saldoSalvadanaio, String nome, String soldiDaPrelevare){
        try{
            if(!soldiDaPrelevare.isEmpty()) {
                if (Double.parseDouble(saldoSalvadanaio) >= Math.round((Double.parseDouble(soldiDaPrelevare)*100.00)/100.00)) {
                    salvadanaioDAO.getMoneyByPiggyBank(contoScelto, nome, Math.round((Double.parseDouble(soldiDaPrelevare)*100.00)/100.00));
                } else {
                    JOptionPane.showMessageDialog(
                            frameSalvadanaio,
                            "Saldo salvadanaio insufficiente!",
                            "Errore",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
            else {
                JOptionPane.showMessageDialog(
                        frameSalvadanaio,
                        "Inserisci una cifra valida!",
                        "Errore inserimento",
                        JOptionPane.PLAIN_MESSAGE,
                        iconAlert
                );
            }
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(
                    frameSalvadanaio,
                    "Inserisci una cifra valida",
                    "Errore inserimento",
                    JOptionPane.PLAIN_MESSAGE,
                    iconAlert
            );
        }
    }


    public void showTransazioniPage(){
        if (frameTransazioni != null)
            frameTransazioni(false);
        //Vengono recuperati le transazioni associati al conto scelto.
        transazioni = transazioneDAO.selectTransazioniByIban(contoScelto);
        contoScelto.setTransactions(transazioni);
        if(frameBankTransfer!=null){
            frameBankTransfer(false);
        }
        if(frameCard!=null){
            frameCard(false);
        }
        frameTransazioni = new TransactionViewGUI(this);
        frameHome(false);
        frameTransazioni(true);
    }

    public void showBankTransferPage(){
        if(frameBankTransfer != null){
            frameBankTransfer(false);
        }
        frameBankTransfer = new BankTransferViewGUI(this);
        frameBankTransfer(true);
    }

    public void sendBankTransfer(String ibanReceiver, String amount, String name, String surname, String reason, String cat, String typeBankTransfer, String nameCollection){
        try{
            if(typeBankTransfer.equals("Bonifico")){
                if(!amount.isEmpty()) {
                    if((getCarta().getTypeCard().equals("CartaDiDebito") && Double.parseDouble(amount)<=3000) || (getCarta().getTypeCard().equals("CartaDiCredito"))) {
                        if (!contoScelto.getIban().equals(ibanReceiver)) {
                            if (contoScelto.getBalance() >= Math.round((Double.parseDouble(amount) * 100.00) / 100.00)) {
                                if (!ibanReceiver.isEmpty() && !name.isEmpty() && !surname.isEmpty() && !reason.isEmpty()) {
                                    if (transazioneDAO.checkIban(ibanReceiver, name, surname)) {
                                        if (nameCollection == null)
                                            nameCollection = "ALTRO";
                                        transazioneDAO.sendBankTransfer(contoScelto, ibanReceiver, amount, reason, cat, nameCollection);
                                        JOptionPane.showMessageDialog(
                                                frameBankTransfer,
                                                "Bonifico inviato con successo!",
                                                "",
                                                JOptionPane.INFORMATION_MESSAGE
                                        );
                                        frameBankTransfer(false);
                                        contoScelto.setBalance(contoCorrenteDAO.updateBankAccount(contoScelto));
                                        showHomePage(contoScelto);

                                    }
                                } else {
                                    JOptionPane.showMessageDialog(
                                            frameBankTransfer,
                                            "Riempi tutti i campi.",
                                            "Errore",
                                            JOptionPane.PLAIN_MESSAGE,
                                            iconAlert
                                    );
                                }
                            } else {
                                JOptionPane.showMessageDialog(
                                        frameBankTransfer,
                                        "Saldo conto corrente insufficiente",
                                        "Errore",
                                        JOptionPane.PLAIN_MESSAGE,
                                        iconAlert
                                );
                            }
                        } else {
                            JOptionPane.showMessageDialog(
                                    frameBankTransfer,
                                    "Non puoi inserire il tuo stesso Iban.",
                                    "Errore",
                                    JOptionPane.PLAIN_MESSAGE,
                                    iconAlert
                            );
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(
                                frameBankTransfer,
                                "<html>L'importo supera il limite di soldi che è possibile inviare con una sola transazione.<br>Se desideri puoi effettuare l'upgrade della carta!</html>",
                                "Attenzione",
                                JOptionPane.PLAIN_MESSAGE,
                                iconAlert
                        );
                    }
                }
                else {
                    JOptionPane.showMessageDialog(
                            frameBankTransfer,
                            "Riempi tutti i campi.",
                            "Errore",
                            JOptionPane.PLAIN_MESSAGE,
                            iconAlert
                    );
                }

            }
            else {
                if(!amount.isEmpty()) {
                    if((getCarta().getTypeCard().equals("CartaDiDebito") && Double.parseDouble(amount)<=3000) || (getCarta().getTypeCard().equals("CartaDiCredito"))) {
                        if (!contoScelto.getIban().equals(ibanReceiver)) {
                            if (contoScelto.getBalance() >= Math.round((Double.parseDouble(amount) * 100.00) / 100.00)) {
                                if (!ibanReceiver.isEmpty() && !name.isEmpty() && !surname.isEmpty() && !reason.isEmpty()) {
                                    if (transazioneDAO.checkIban(ibanReceiver, name, surname)) {
                                        if (nameCollection == null)
                                            nameCollection = "ALTRO";
                                        transazioneDAO.sendIstantBankTransfer(contoScelto, ibanReceiver, amount, reason, cat, nameCollection);
                                        JOptionPane.showMessageDialog(
                                                frameBankTransfer,
                                                "Bonifico inviato con successo!",
                                                "",
                                                JOptionPane.INFORMATION_MESSAGE
                                        );
                                        frameBankTransfer(false);
                                        contoScelto.setBalance(contoCorrenteDAO.updateBankAccount(contoScelto));
                                        showHomePage(contoScelto);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(
                                            frameBankTransfer,
                                            "Riempi tutti i campi.",
                                            "Errore",
                                            JOptionPane.PLAIN_MESSAGE,
                                            iconAlert
                                    );
                                }
                            } else {
                                JOptionPane.showMessageDialog(
                                        frameBankTransfer,
                                        "Saldo conto corrente insufficiente",
                                        "Errore",
                                        JOptionPane.PLAIN_MESSAGE,
                                        iconAlert
                                );
                            }
                        } else {
                            JOptionPane.showMessageDialog(
                                    frameBankTransfer,
                                    "Non puoi inserire il tuo stesso Iban.",
                                    "Errore",
                                    JOptionPane.PLAIN_MESSAGE,
                                    iconAlert
                            );
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(
                                frameBankTransfer,
                                "<html>L'importo supera il limite di soldi che è possibile inviare con una sola transazione.<br>Se desideri puoi effettuare l'upgrade della carta!</html>",
                                "Attenzione",
                                JOptionPane.PLAIN_MESSAGE,
                                iconAlert
                        );
                    }
                }
                else {
                    JOptionPane.showMessageDialog(
                            frameBankTransfer,
                            "Riempi tutti i campi.",
                            "Errore",
                            JOptionPane.PLAIN_MESSAGE,
                            iconAlert
                    );
                }
            }
        }
        catch (MyExc e){
            JOptionPane.showMessageDialog(
                    frameBankTransfer,
                    e.getMessage(),
                    "Errore",
                    JOptionPane.PLAIN_MESSAGE,
                    iconAlert
            );
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(
                    frameBankTransfer,
                    "Inserisci una cifra valida",
                    "Errore",
                    JOptionPane.PLAIN_MESSAGE,
                    iconAlert
            );
        }
    }

    public void selectNameAndSurnameByIban(String iban){
        credenzialiIbanMittDest = transazioneDAO.selectNameAndSurnameByIban(iban);
    }

    public void showCollectionPickView(){
        collections = collectionDAO.selectCollectionByIban(contoScelto);

        if(framePickCollection!=null){
            framePickCollection(false);
        }

        framePickCollection = new CollectionPickViewGUI(this);

        frameHome(false);
        if(frameBankTransfer!=null){
            frameBankTransfer(false);
        }
        if(frameCard!=null){
            frameCard(false);
        }

        framePickCollection(true);
    }

    public void pickCollectionByIban(){
        collections = collectionDAO.selectCollectionByIban(contoScelto);
    }

    public double selectSumOfCollections(String name){
        return transazioneDAO.selectSumOfCollections(contoScelto, name);
    }

    public void showCollectionPage(Collection collection){
        selectedCollection = collection;
        transactionsCollection = transazioneDAO.selectTransactionsByCollection(selectedCollection, contoScelto);


        selectedCollection.setTransactions(transactionsCollection);


        framePickCollection(false);
        frameCollection = new CollectionViewGUI(this);
        frameCollection(true);

    }

    public void addCollection(BankAccount conto, String name, String description) throws MyExc{
        try {
            if(!name.isEmpty() && !description.isEmpty())
                collectionDAO.addCollection(contoScelto, name, description);
            else{
                JOptionPane.showMessageDialog(
                        null,
                        "Riempi tutti i campi!",
                        "Errore inserimento",
                        JOptionPane.ERROR_MESSAGE,
                        iconAlert
                );
            }
        } catch (MyExc e) {
            JOptionPane.showMessageDialog(
                    frameSalvadanaio,
                    e.getMessage(),
                    "Errore",
                    JOptionPane.PLAIN_MESSAGE,
                    iconCancel
            );
        }
    }

    public void deleteCollection(String name){
        collectionDAO.deleteCollection(contoScelto, name);
        if(framePickCollection!=null)
            framePickCollection(false);
        showCollectionPickView();
    }

    public void viewReport(BankAccount conto, String mese){
        report = transazioneDAO.viewReport(contoScelto, mese);

    }

    public double totaleInviatoMensile(BankAccount conto, String mese){
        return transazioneDAO.totalSentMonthly(conto, mese);
    }

    public double totaleRicevutoMensile(BankAccount conto, String mese){
        return transazioneDAO.totalReceivedMonthly(conto, mese);
    }

    /**
     * Metodo che gestisce la visibilità della pagina di Login.
     * @param isVisibile setta la visibilità della pagina
     * */
    public void frameLogin(Boolean isVisibile){
        frameLogin.setVisible(isVisibile);
    }

    /**
     * Metodo che gestisce la visibilità della pagina di SignIn.
     * @param isVisibile setta la visibilità della pagina
     * */
    public void frameSignIn(Boolean isVisibile){
        frameSignIn.setVisible(isVisibile);
    }

    /**
     * Metodo che gestisce la visibilità della pagina di scelata del conto.
     * @param isVisibile setta la visibilità della pagina
     * */
    public void framePick(Boolean isVisibile){
        framePick.setVisible(isVisibile);
    }

    /**
     * Metodo che gestisce la visibilità della pagina Home.
     * @param isVisibile setta la visibilità della pagina
     * */
    public void frameHome(Boolean isVisible){
        frameHome.setVisible(isVisible);
    }

    /**
     * Metodo che gestisce la visibilità della pagina per visualizzare la carta.
     * @param isVisibile setta la visibilità della pagina
     * */
    public void frameCard(Boolean isVisible){
        frameCard.setVisible(isVisible);
    }

    /**
     * Metodo che gestisce la visibilità della pagina per visualizzare la carta.
     * @param isVisibile setta la visibilità della pagina
     * */
    public void frameBankTransfer(Boolean isVisible){
        frameBankTransfer.setVisible(isVisible);
    }

    /**
     * Metodo che gestisce la visibilità della pagina per visualizzare i salvadanai.
     * @param isVisibile setta la visibilità della pagina
     * */
    public void frameSalvadanaio(Boolean isVisible){
        frameSalvadanaio.setVisible(isVisible);
    }

    public void frameTransazioni(Boolean isVisibile){
        frameTransazioni.setVisible(isVisibile);
    }

    /**
     * Metodo che gestisce la visibilità della pagina per visualizzare le collezioni.
     * @param isVisibile setta la visibilità della pagina
     * */
    public void framePickCollection(Boolean isVisible){
        framePickCollection.setVisible(isVisible);
    }

    /**
     * Metodo che gestisce la visibilità della pagina per visualizzare le collezioni.
     * @param isVisibile setta la visibilità della pagina
     * */
    public void frameCollection(Boolean isVisible){
        frameCollection.setVisible(isVisible);
    }











    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ArrayList<BankAccount> getConti() {
        return conti;
    }

    public void setConti(ArrayList<BankAccount> conti) {
        this.conti = conti;
    }

    public BankAccount getContoScelto() {
        return contoScelto;
    }

    public void setContoScelto(BankAccount contoScelto) {
        this.contoScelto = contoScelto;
    }

    public Card getCarta() {
        return carta;
    }

    public void setCarta(Card carta) {
        this.carta = carta;
    }

    public ArrayList<PiggyBank> getSalvadanai() {
        return salvadanai;
    }

    public void setSalvadanai(ArrayList<PiggyBank> salvadanai) {
        this.salvadanai = salvadanai;
    }

    public ArrayList<Transaction> getTransazioni() {
        return transazioni;
    }

    public void setTransazioni(ArrayList<Transaction> transazioni) {
        this.transazioni = transazioni;
    }

    public Double[] getReport() {
        return report;
    }

    public void setReport(Double[] report) {
        this.report = report;
    }

    public String getCredenzialiIbanMittDest() {
        return credenzialiIbanMittDest;
    }

    public void setCredenzialiIbanMittDest(String credenzialiIbanMittDest) {
        this.credenzialiIbanMittDest = credenzialiIbanMittDest;
    }

    public ArrayList<Collection> getCollections() {
        return collections;
    }

    public void setCollections(ArrayList<Collection> collections) {
        this.collections = collections;
    }

    public ArrayList<Transaction> getTransactionsCollection() {
        return transactionsCollection;
    }

    public void setTransactionsCollection(ArrayList<Transaction> transactionsCollection) {
        this.transactionsCollection = transactionsCollection;
    }

    public Collection getSelectedCollection() {
        return selectedCollection;
    }

    public void setSelectedCollection(Collection selectedCollection) {
        this.selectedCollection = selectedCollection;
    }
}