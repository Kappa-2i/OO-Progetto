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
    public Account account = null;
    public Person person = null;
    public ArrayList<BankAccount> bankAccounts = null;
    public BankAccount selectedBankAccount = null;
    public ArrayList<PiggyBank> piggyBanks = null;
    public ArrayList<Transaction> transactions = null;

    //Dichiarazioni delle Gui
    private LoginViewGUI frameLogin;
    private BankAccountPickViewGUI framePick;

    //Dichiarazioni delle Dao
    private AccountDAO accountDao;
    private PersonDAO personDAO;
    private BankAccountDAO bankAccountDAO;

    public Controller(){
        frameLogin = new LoginViewGUI(this); //LoginView accetta ControllerLogin come parametro
        frameLogin(true);

        //DAO
        this.accountDao = new AccountDAOImpl();
        this.personDAO = new PersonDAOImpl();
        this.bankAccountDAO = new BankAccountDAOImpl();

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
}