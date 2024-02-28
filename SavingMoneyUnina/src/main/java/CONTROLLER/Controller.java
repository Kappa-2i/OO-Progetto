package CONTROLLER;

import DAO.*;
import DAOIMPL.*;
import ENTITY.*;
import GUI.*;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {

    //Dichiarazione delle variabili
    private Account account = null;
    private Person person = null;
    private ArrayList<BankAccount> bankAccounts = null;
    private BankAccount selectedBankAccount = null;
    private ArrayList<PiggyBank> piggyBanks = null;
    private ArrayList<Transaction> transactions = null;
    private Card card = null;

    //Dichiarazioni delle Gui
    private LoginViewGUI frameLogin;
    private BankAccountPickViewGUI framePick;
    private HomePageGUI frameHome;

    //Dichiarazioni delle Dao
    private AccountDAO accountDao;
    private PersonDAO personDAO;
    private BankAccountDAO bankAccountDAO;
    private CardDAO cardDAO;

    public Controller(){
        frameLogin = new LoginViewGUI(this); //LoginView accetta ControllerLogin come parametro
        frameLogin(true);

        //DAO
        this.accountDao = new AccountDAOImpl();
        this.personDAO = new PersonDAOImpl();
        this.bankAccountDAO = new BankAccountDAOImpl();
        this.cardDAO = new CardDAOImpl();

    }


    /**
     * Metodo che controlla la validità dei campi email e password inseriti al momento del login.
     * <br> In caso di successo viene visulalizzata la pagina Home, bisogna riporovare altrimenti.
     * @param email per controllare che l'email sia corretta.
     * @param password per controllare che la password sia corretta.
     * */
    public void checkCredentials(String email, String password) throws SQLException {
        if((!email.isEmpty()) && (!password.isEmpty())){
            account = accountDao.checkCredentials(email.toLowerCase(), password);
            if (account != null){
                person = personDAO.selectPersonaFromEmail(email.toLowerCase());
                account.setPerson(person);
                frameLogin(false);
                showPickFrame();
            }
            else{
                //Se uno dei due campi è sbagliato viene visualizzato un messaggio di errore.
                JOptionPane.showMessageDialog(
                        null,
                        "Email o Password Errati",
                        "Errore di Login",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
        else {
            //Se i campi non vengono compilati viene visualizzato un messaggio di errore.
            JOptionPane.showMessageDialog(
                    null,
                    "Inserisci delle credenziali valide!",
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

   /* *//**
     *Metodo che permette di gestire la viusalizzazione della pagina di SignIn.*//*
    public void showFrameSignIn(){
        frameLogin(false);
        frameSignIn = new SignInViewGUI(this);
        frameSignIn(true);
    }*/


    public boolean confirmedPassword(String password, String confirmedPassword){
        if (password.equals(confirmedPassword))
            return true;
        else
            return false;
    }

    /**
     *Metodo che permette di gestire la viusalizzazione della pagina di framePick.*/
    public void showPickFrame(){
        framePick = new BankAccountPickViewGUI(this);
        framePick(true);
    }

    /**
     * Metodo che seleziona tutti i conti relativi all'account che gli viene passato
     * @param account riferimento per i conti da selzionare
     * */
    public ArrayList<BankAccount> selectBankAccountByAccount(Account account){
        bankAccounts = new ArrayList<BankAccount>();
        bankAccounts = bankAccountDAO.selectBankAccountByAccount(account);
        account.setBankAccounts(bankAccounts);
        return bankAccounts;
    }

    /**
     * Metodo che aggiunge un nuovo Conto Corrente. <br>Ritorna true in caso venga completato correttanente, false altrimenti.
     * @param email riferimeto per il conto da aggiungere.*/
    public Boolean insertBankAccount(String email){
        if (bankAccountDAO.insertBankAccount(email)){
            return true;
        }
        else
            return false;
    }


    /**
     * Metodo che permette di tornare alla pagina di Login.
     */
    public void backLoginPage(){
        //Quando si torna alla pagina di Login l'account viene settato a null.
        account = null;
        //Quando si torna alla pagina di Login il conto scelto viene settato a null.
        if (bankAccounts!= null)
            bankAccounts = null;

        framePick(false);
        //frameHome(false);
        frameLogin(true);
    }

    /**
     * Metodo per gesitre la visualizzaione della pagina di Home page.
     * @param conto riferimento per le informazioni da visualizzare in Home Page.*/
    public void showHomePage(BankAccount bankAccount){

        //Viene selezionato il conto dopo averlo scelto dalla pagina di selzione.
        selectedBankAccount = bankAccount;
        //Viene recuperata la carta associata al conto scelto.
        card = cardDAO.selectCard(selectedBankAccount);

        framePick(false);
//        if(frameSalvadanaio != null)
//            frameSalvadanaio(false);
        frameHome = new HomePageGUI(this);
        frameHome(true);
    }


    /**
     * Metodo che permette di effettuare l'upgrade della carta da Debito (default) a Credito.
     * @param pan riferimento per la carta da aggiornare.*/
    public void upgradeCard(String pan){
        cardDAO.upgradeCard(pan);
        ImageIcon iconChecked = new ImageIcon(Controller.class.getResource("/IMG/checked.png")); //Inserisce l'immagine sul JOptionPane.
        JOptionPane.showMessageDialog(
                null,
                "La tua carta è stata aggiornata a carta di credito!",
                "Aggiornamento effettuato",
                JOptionPane.PLAIN_MESSAGE,
                iconChecked
        );
        frameHome(false);
        showHomePage(selectedBankAccount);
    }

    /**
     * Metodo che permette di effetuare il downgrade della carta Da credito a Debito.
     * @param pan riferimento per la carta da aggiornare.*/
    public void downgradeCard(String pan){
        cardDAO.downgradeCard(pan);
        ImageIcon iconChecked = new ImageIcon(Controller.class.getResource("/IMG/checked.png")); //Inserisce l'immagine sul JOptionPane.
        JOptionPane.showMessageDialog(
                null,
                "La tua carta è stata aggiornata a carta di debito!",
                "Aggiornamento effettuato",
                JOptionPane.PLAIN_MESSAGE,
                iconChecked
        );
        frameHome(false);
        showHomePage(selectedBankAccount);
    }


    /**
     * Metodo che gestisce la visibilità della pagina di Login.
     * @param isVisible setta la visibilità della pagina
     * */
    public void frameLogin(Boolean isVisible){
        frameLogin.setVisible(isVisible);
    }


    /**
     * Metodo che gestisce la visibilità della pagina di scelta del conto.
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

    //Getter e Setter

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}

