package CONTROLLER;

import ENTITY.*;
import GUI.BankAccountPickViewGUI;
import GUI.LoginViewGUI;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {

    //Dichiarazione delle variabili
    public Account account = null;
    public Person persona = null;
    public ArrayList<ContoCorrente> conti = null;
    public ContoCorrente contoScelto = null;
    public ArrayList<PiggyBank> salvadanai = null;
    public ArrayList<Transaction> transazioni = null;

    //Dichiarazioni delle Gui
    private LoginViewGUI frameLogin;
    private BankAccountPickViewGUI framePick;

    public Controller(){
        frameLogin = new LoginViewGUI(this); //LoginView accetta ControllerLogin come parametro
        frameLogin(true);

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
                persona = personaDao.selectPersonaFromEmail(email.toLowerCase());
                account.setPerson(persona);
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

    /**
     *Metodo che permette di gestire la viusalizzazione della pagina di framePick.*/
    public void showPickFrame(){
        framePick = new BankAccountPickViewGUI(this);
        framePick(true);
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
     * Metodo che gestisce la visibilità della pagina di Login.
     * @param isVisible setta la visibilità della pagina
     * */
    public void frameLogin(Boolean isVisible){
        frameLogin.setVisible(isVisible);
    }


    /**
     * Metodo che gestisce la visibilità della pagina di scelata del conto.
     * @param isVisibile setta la visibilità della pagina
     * */
    public void framePick(Boolean isVisibile){
        framePick.setVisible(isVisibile);
    }
}