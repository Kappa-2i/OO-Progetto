package CONTROLLER;

import DAO.*;
import DAOIMPL.*;
import ENTITY.*;
import EXCEPTIONS.MyExc;
import GUI.*;

import javax.swing.*;
import java.util.ArrayList;

// Classe del controller
public class Controller {

    //Dichiarazioni delle Gui
    private LoginViewGUI frameLogin;
    private SignUpViewGUI frameSignUp;
    private BankAccountPickViewGUI framePickBankAccount;
    private HomeViewGUI frameHome;
    private CardViewGUI frameCard;
    private PiggyBanksViewGUI framePiggyBank;
    private TransactionViewGUI frameTransaction;
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
    private ArrayList<BankAccount> bankAccounts = null;
    private BankAccount selectedBankAccount = null;
    private Card card = null;
    private ArrayList<PiggyBank> piggyBanks = null;
    private ArrayList<Transaction> transactions = null;
    private Double[] report = null;
    private String credentialsIban = null;
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
                showPickBankAccountView();
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
     *Metodo che permette di gestire la viusalizzazione della pagina di SignIn.
     * */
    public void showSingUpView(){
        frameLogin(false);
        frameSignUp = new SignUpViewGUI(this);
        frameSignUp(true);
    }

    /**
     *Metodo che permette di gestire la viusalizzazione della pagina di scelta del conto corrente.
     * */
    public void showPickBankAccountView(){
        framePickBankAccount = new BankAccountPickViewGUI(this);
        framePickBankAccount(true);
    }


    /**
     * Metodo per creare un nuovo account.
     * @param email email da registrare.
     * @param password password da registrare.
     * @param name nome da registrare.
     * @param surname cognome da registrare.
     * */
    public void insertAccount(String email, String password, String name, String surname){
        try{
            account = new Account(email, password, name, surname);
            if (!email.isEmpty() && !password.isEmpty() && !name.isEmpty() && !surname.isEmpty()) {
                accountDao.insertAccount(email, password, name, surname);
                JOptionPane.showMessageDialog(
                        frameSignUp,
                        "Dati dell'account inseriti!",
                        "Benvenuta/o",
                        JOptionPane.PLAIN_MESSAGE,
                        iconChecked);
            }
            else{
                JOptionPane.showMessageDialog(
                        frameSignUp,
                        "Inserisci delle credenziali valide!",
                        "Errore",
                        JOptionPane.PLAIN_MESSAGE,
                        iconAlert);
            }
        }
        catch (MyExc exc){
            JOptionPane.showMessageDialog(
                    frameSignUp,
                    "L'email deve contenere una '@'!",
                    "Errore",
                    JOptionPane.PLAIN_MESSAGE,
                    iconAlert
            );
        }


    }

    
    /**
     * Metodo per controllare che password e conferma password inseriti al momento della registrazione sono uguali.
     * @param password password inserita.
     * @param confirmedPassword conferma password inserita.
     * @return true se le password inserite sono uguali, false altrimenti.*/
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
        bankAccounts = new ArrayList<BankAccount>();
        bankAccounts = contoCorrenteDAO.selectBankAccountByAccount(account);
        account.setBankAccounts(bankAccounts);
        return bankAccounts;
    }

    /**
     * Metodo per recuperare il saldo aggiornato del conto corrente inserito.
     * @param conto riferimento per il conto a cui aggiornare il saldo.
     **/
    public void updateBankAccount(BankAccount conto){
        selectedBankAccount.setBalance(contoCorrenteDAO.updateBankAccount(conto));
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
                framePickBankAccount,
                "Conto Corrente con Iban: " +iban+ ", eliminato con successo!",
                "Conto Corrente eliminato",
                JOptionPane.PLAIN_MESSAGE,
                iconDelete
        );
    }

    /**
     * Metodo per gesitre la visualizzaione della pagina di Home page.
     * @param conto riferimento per le informazioni da visualizzare in Home Page.*/
    public void showHomeView(BankAccount conto){

        //Viene selezionato il conto dopo averlo scelto dalla pagina di selezione.
        selectedBankAccount = conto;
        //Viene recuperata la carta associata al conto scelto.
        if(card != null)
            card = null;
        card = cartaDAO.selectCard(selectedBankAccount);

        framePickBankAccount(false);
        if(framePiggyBank != null)
            framePiggyBank(false);
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
    public void backLoginView(){
        //Quando si torna alla pagina di Login l'account viene settato a null.
        account = null;
        //Quando si torna alla pagina di Login il conto scelto viene settato a null.
        if (selectedBankAccount != null)
            selectedBankAccount = null;

        if(frameBankTransfer!=null)
            frameBankTransfer(false);
        if(frameCard!=null)
            frameCard(false);
        if(framePickBankAccount != null)
            framePickBankAccount(false);
        if(frameHome != null)
            frameHome(false);
        if(frameSignUp != null)
            frameSignUp(false);

        frameLogin(true);
    }


    /**
     * Metodo che permette gestire la visualizzazione della pagina della carta.*/
    public void showCardView(){
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
    public void upgradeCard(String pan){
        if(selectedBankAccount.getBalance() >= 5) {
            cartaDAO.upgradeCard(pan);
            JOptionPane.showMessageDialog(
                    frameHome,
                    "La tua carta è stata aggiornata a carta di credito!",
                    "Aggiornamento effettuato",
                    JOptionPane.PLAIN_MESSAGE,
                    iconChecked
            );
            frameHome(false);
            selectedBankAccount.setBalance(contoCorrenteDAO.updateBankAccount(selectedBankAccount));
            showHomeView(selectedBankAccount);
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
    public void downgradeCard(String pan){
        cartaDAO.downgradeCard(pan);
        JOptionPane.showMessageDialog(
                null,
                "La tua carta è stata aggiornata a carta di debito!",
                "Aggiornamento effettuato",
                JOptionPane.PLAIN_MESSAGE,
                iconChecked
        );
        frameHome(false);
        showHomeView(selectedBankAccount);
    }

    /**
     * Metodo che permette di gestire la visualizzazione della pagina dei salvadanai. */
    public void showPiggyBankView(){
        if (framePiggyBank != null)
            framePiggyBank(false);
        //Vengono recuperati i salvadanai associati al conto scelto.
        piggyBanks = salvadanaioDAO.selectPiggyBank(selectedBankAccount);
        selectedBankAccount.setPiggyBanks(piggyBanks);
        if(frameBankTransfer!=null){
            frameBankTransfer(false);
        }
        if(frameCard!=null){
            frameCard(false);
        }
        framePiggyBank = new PiggyBanksViewGUI(this);
        frameHome(false);
        framePiggyBank(true);
    }

    /**
     * Metodo per aggiungere un nuovo salvadanaio al conto corrente personale.
     * @param name nome del salvadanaio da creare.
     * @param target obbiettivo in denaro da raggiungere.
     * @param description descrizione del salvadanaio.
     * */
    public void addPiggyBank(String name, double target, String description) throws MyExc{
        try {
            if(!name.isEmpty() && !description.isEmpty())
                salvadanaioDAO.addPiggyBank(selectedBankAccount, name, target, description);
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
                    framePiggyBank,
                    e.getMessage(),
                    "Errore",
                    JOptionPane.PLAIN_MESSAGE,
                    iconCancel
            );
        }

    }

    /**
     * Metodo per eliminare un salvadanaio dal conto corrente.
     * @param name nome del salvadanaio da eliminare.
     * */
    public void deletePiggyBank(String name){
        salvadanaioDAO.deletePiggyBank(selectedBankAccount, name);
    }

    /**
     * Metodo per aggiungere soldi al salvadanaio.
     * @param name nome del salvadanaio a cui aggiungere i soldi.
     * @param moneyToSend somma da inserire nel salvadanaio.*/
    public void fillPiggyBank(String name, String moneyToSend){
        try{
            if(!moneyToSend.isEmpty()) {
                if(Math.round((Double.parseDouble(moneyToSend)*100.00)/100.00) > 0) {
                    if (selectedBankAccount.getBalance() >= Math.round((Double.parseDouble(moneyToSend) * 100.00) / 100.00)) {
                        salvadanaioDAO.fillPiggyBank(selectedBankAccount, name, Math.round((Double.parseDouble(moneyToSend) * 100.00) / 100.00));
                    } else {
                        JOptionPane.showMessageDialog(
                                framePiggyBank,
                                "Saldo conto corrente insufficiente!",
                                "Errore",
                                JOptionPane.PLAIN_MESSAGE,
                                iconCancel

                        );
                    }
                }
                else {
                    JOptionPane.showMessageDialog(
                            framePiggyBank,
                            "Inserisci una cifra valida!",
                            "Errore inserimento",
                            JOptionPane.PLAIN_MESSAGE,
                            iconAlert
                    );
                }
            }
            else {
                JOptionPane.showMessageDialog(
                        framePiggyBank,
                        "Riempi tutti i campi!",
                        "Errore inserimento",
                        JOptionPane.PLAIN_MESSAGE,
                        iconAlert
                );
            }
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(
                    framePiggyBank,
                    "Inserisci una cifra valida!",
                    "Errore inserimento",
                    JOptionPane.ERROR_MESSAGE,
                    iconAlert
            );
        }
    }

    /**
     * Metodo per prendere soldi dal salvadanaio selezionato.
     * @param piggyBankBalance saldo del salvadanaio.
     * @param name nome del salvadanaio da cui prelevare i soldi.
     * @param moneyToGet somma da prelevare dal salvadanaio.
     * */
    public void getMoneyByPiggyBank(String piggyBankBalance, String name, String moneyToGet){
        try{
            if(!moneyToGet.isEmpty()) {
                if (Double.parseDouble(piggyBankBalance) >= Math.round((Double.parseDouble(moneyToGet)*100.00)/100.00)) {
                    salvadanaioDAO.getMoneyByPiggyBank(selectedBankAccount, name, Math.round((Double.parseDouble(moneyToGet)*100.00)/100.00));
                } else {
                    JOptionPane.showMessageDialog(
                            framePiggyBank,
                            "Saldo salvadanaio insufficiente!",
                            "Errore",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
            else {
                JOptionPane.showMessageDialog(
                        framePiggyBank,
                        "Inserisci una cifra valida!",
                        "Errore inserimento",
                        JOptionPane.PLAIN_MESSAGE,
                        iconAlert
                );
            }
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(
                    framePiggyBank,
                    "Inserisci una cifra valida",
                    "Errore inserimento",
                    JOptionPane.PLAIN_MESSAGE,
                    iconAlert
            );
        }
    }


    /**
     * Metodo per gesitre la visualizzaione della pagina de 'Le mie spese'.
     * */
    public void showTransactionView(){
        if (frameTransaction != null)
            frameTransaction(false);
        //Vengono recuperati le transazioni associati al conto scelto.
        transactions = transazioneDAO.selectTransazioniByIban(selectedBankAccount);
        selectedBankAccount.setTransactions(transactions);
        if(frameBankTransfer!=null){
            frameBankTransfer(false);
        }
        if(frameCard!=null){
            frameCard(false);
        }
        frameTransaction = new TransactionViewGUI(this);
        frameHome(false);
        frameTransaction(true);
    }

    /** Metodo per gestire la visulizzazione della pagina Invio Bonifico.
     * */
    public void showBankTransferView(){
        if(frameBankTransfer != null){
            frameBankTransfer(false);
        }
        frameBankTransfer = new BankTransferViewGUI(this);
        frameBankTransfer(true);
    }

    /**
     * Metodo per l'invio di un bonifico bancario.
     * @param ibanReceiver iban a cui inviare il denaro.
     * @param amount somma da inviare.
     * @param name nome del titolare del conto a cui inviare il bonifico.
     * @param surname cognome del titolare del conto a cui inviare il bonifico.
     * @param reason causale del bonifico.
     * @param category categoria del bonifico.
     * @param typeBankTransfer tipo di bonifico.
     * @param nameCollection nome della collezione a cui aggiungere il bonifico.
     * */
    public void sendBankTransfer(String ibanReceiver, String amount, String name, String surname, String reason, String category, String typeBankTransfer, String nameCollection){
        try{
            if(typeBankTransfer.equals("Bonifico")){
                if(!amount.isEmpty()) {
                    if((getCard().getTypeCard().equals("CartaDiDebito") && Double.parseDouble(amount)<=3000) || (getCard().getTypeCard().equals("CartaDiCredito"))) {
                        if (!selectedBankAccount.getIban().equals(ibanReceiver)) {
                            if (selectedBankAccount.getBalance() >= Math.round((Double.parseDouble(amount) * 100.00) / 100.00)) {
                                if (!ibanReceiver.isEmpty() && !name.isEmpty() && !surname.isEmpty() && !reason.isEmpty()) {
                                    if (transazioneDAO.checkIban(ibanReceiver, name, surname)) {
                                        if (nameCollection == null)
                                            nameCollection = "ALTRO";
                                        transazioneDAO.sendBankTransfer(selectedBankAccount, ibanReceiver, amount, reason, category, nameCollection);
                                        JOptionPane.showMessageDialog(
                                                frameBankTransfer,
                                                "Bonifico inviato con successo!",
                                                "",
                                                JOptionPane.INFORMATION_MESSAGE
                                        );
                                        frameBankTransfer(false);
                                        selectedBankAccount.setBalance(contoCorrenteDAO.updateBankAccount(selectedBankAccount));
                                        showHomeView(selectedBankAccount);

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
                    if((getCard().getTypeCard().equals("CartaDiDebito") && Double.parseDouble(amount)<=3000) || (getCard().getTypeCard().equals("CartaDiCredito"))) {
                        if (!selectedBankAccount.getIban().equals(ibanReceiver)) {
                            if (selectedBankAccount.getBalance() >= Math.round((Double.parseDouble(amount) * 100.00) / 100.00)) {
                                if (!ibanReceiver.isEmpty() && !name.isEmpty() && !surname.isEmpty() && !reason.isEmpty()) {
                                    if (transazioneDAO.checkIban(ibanReceiver, name, surname)) {
                                        if (nameCollection == null)
                                            nameCollection = "ALTRO";
                                        transazioneDAO.sendIstantBankTransfer(selectedBankAccount, ibanReceiver, amount, reason, category, nameCollection);
                                        JOptionPane.showMessageDialog(
                                                frameBankTransfer,
                                                "Bonifico inviato con successo!",
                                                "",
                                                JOptionPane.INFORMATION_MESSAGE
                                        );
                                        frameBankTransfer(false);
                                        selectedBankAccount.setBalance(contoCorrenteDAO.updateBankAccount(selectedBankAccount));
                                        showHomeView(selectedBankAccount);
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


    /**
     * Metodo per recuperare il nome e il cognome dell'intestatrio dell'iban.
     * @param iban riferimento dell'iban da cui prendere nome e cognome.
     * */
    public void selectNameAndSurnameByIban(String iban){
        credentialsIban = transazioneDAO.selectNameAndSurnameByIban(iban);
    }

    /**
     * Metodo per gestire la visulizzazione della pagina di selzione delle collezioni. */
    public void showCollectionPickView(){
        collections = collectionDAO.selectCollectionByIban(selectedBankAccount);

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


    /**
     * Metodo per prendere le collezioni del conto personale.*/
    public void pickCollectionByIban(){
        collections = collectionDAO.selectCollectionByIban(selectedBankAccount);
    }


    /**
     * Metodo per recuperare il totale della collezione desiderata.
     * @param name riferimento del nome della collezione per cui ottenere la somma.*/
    public double selectSumOfCollections(String name){
        return transazioneDAO.selectSumOfCollections(selectedBankAccount, name);
    }


    /**
     * Metodo per gestire la visualizzazione della pagina delle collezioni.
     * @param collection collezione da visualizzare.
     * */
    public void showCollectionView(Collection collection){
        selectedCollection = collection;
        transactionsCollection = transazioneDAO.selectTransactionsByCollection(selectedCollection, selectedBankAccount);


        selectedCollection.setTransactions(transactionsCollection);


        framePickCollection(false);
        frameCollection = new CollectionViewGUI(this);
        frameCollection(true);

    }


    /**
     * Metodo per aggiungere una nuova collezione.
     * @param name nome della collezione da aggiungere.
     * @param description descrizione della collezione da aggiungere.
     * */
    public void addCollection(String name, String description) throws MyExc{
        try {
            if(!name.isEmpty() && !description.isEmpty())
                collectionDAO.addCollection(selectedBankAccount, name, description);
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
                    framePiggyBank,
                    e.getMessage(),
                    "Errore",
                    JOptionPane.PLAIN_MESSAGE,
                    iconCancel
            );
        }
    }

    /**
     * Metodo per eliminare una collezione dal conto personale.
     * @param name nome della collezione da eliminare.
     * */
    public void deleteCollection(String name){
        collectionDAO.deleteCollection(selectedBankAccount, name);
        if(framePickCollection!=null)
            framePickCollection(false);
        showCollectionPickView();
    }


    /**
     * Metodo per visualizzare il report mensile.
     * @param month mese per cui effettuare il report.
     * */
    public void viewReport(String month){
        report = transazioneDAO.viewReport(selectedBankAccount, month);

    }

    /**
     * Metodo per ottenere il totale mensile in uscita.
     * @param bankAccount conto per cui visualizzare il totale in uscita.
     * @param month mese per cui visualizzare il totale in uscita.
     * @retrun double totale in uscita calcolato.
     * */
    public double totalMonthlySent(BankAccount bankAccount, String month){
        return transazioneDAO.totalSentMonthly(bankAccount, month);
    }

    /**
     * Metodo per ottenere il totale mensile in entrata.
     * @param bankAccount per cui visualizzare il totale in entrata.
     * @param month per cui visualizzare il totale in entrata.
     * @retrun double totale in entrata calcolato.
     * */
    public double totalMonthlyReceived(BankAccount bankAccount, String month){
        return transazioneDAO.totalReceivedMonthly(bankAccount, month);
    }

    /**
     * Metodo che gestisce la visibilità della pagina di Login.
     * @param isVisibile setta la visibilità della pagina
     * */
    public void frameLogin(Boolean isVisibile){
        frameLogin.setVisible(isVisibile);
    }

    /**
     * Metodo che gestisce la visibilità della pagina di SignUp.
     * @param isVisibile setta la visibilità della pagina
     * */
    public void frameSignUp(Boolean isVisibile){
        frameSignUp.setVisible(isVisibile);
    }

    /**
     * Metodo che gestisce la visibilità della pagina di scelata del conto.
     * @param isVisibile setta la visibilità della pagina
     * */
    public void framePickBankAccount(Boolean isVisibile){
        framePickBankAccount.setVisible(isVisibile);
    }

    /**
     * Metodo che gestisce la visibilità della pagina Home.
     * @param isVisible setta la visibilità della pagina
     * */
    public void frameHome(Boolean isVisible){
        frameHome.setVisible(isVisible);
    }

    /**
     * Metodo che gestisce la visibilità della pagina per visualizzare la carta.
     * @param isVisible setta la visibilità della pagina
     * */
    public void frameCard(Boolean isVisible){
        frameCard.setVisible(isVisible);
    }

    /**
     * Metodo che gestisce la visibilità della pagina per visualizzare la pagina di Invia Bonifico.
     * @param isVisible setta la visibilità della pagina
     * */
    public void frameBankTransfer(Boolean isVisible){
        frameBankTransfer.setVisible(isVisible);
    }

    /**
     * Metodo che gestisce la visibilità della pagina per visualizzare i salvadanai.
     * @param isVisible setta la visibilità della pagina
     * */
    public void framePiggyBank(Boolean isVisible){
        framePiggyBank.setVisible(isVisible);
    }

    /**
     * Metodo che gestisce la visibilità della pagina delle spese.
     * @param isVisibile setta la visibilità della pagina.*/
    public void frameTransaction(Boolean isVisibile){
        frameTransaction.setVisible(isVisibile);
    }

    /**
     * Metodo che gestisce la visibilità della pagina per la selezione delle collezioni.
     * @param isVisible setta la visibilità della pagina
     * */
    public void framePickCollection(Boolean isVisible){
        framePickCollection.setVisible(isVisible);
    }

    /**
     * Metodo che gestisce la visibilità della pagina per visualizzare le collezioni.
     * @param isVisible setta la visibilità della pagina
     * */
    public void frameCollection(Boolean isVisible){
        frameCollection.setVisible(isVisible);
    }





    //Getter & Setter

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ArrayList<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(ArrayList<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public BankAccount getSelectedBankAccount() {
        return selectedBankAccount;
    }

    public void setSelectedBankAccount(BankAccount selectedBankAccount) {
        this.selectedBankAccount = selectedBankAccount;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public ArrayList<PiggyBank> getPiggyBanks() {
        return piggyBanks;
    }

    public void setPiggyBanks(ArrayList<PiggyBank> piggyBanks) {
        this.piggyBanks = piggyBanks;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Double[] getReport() {
        return report;
    }

    public void setReport(Double[] report) {
        this.report = report;
    }

    public String getCredentialsIban() {
        return credentialsIban;
    }

    public void setCredentialsIban(String credentialsIban) {
        this.credentialsIban = credentialsIban;
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