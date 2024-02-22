package CONTROLLER;

import GUI.LoginViewGUI;

public class Controller {

    //Dichiarazioni delle Gui
    private LoginViewGUI frameLogin;

    public Controller(){
        frameLogin = new LoginViewGUI(this); //LoginView accetta ControllerLogin come parametro
        frameLogin(true);

    }

    public void frameLogin(Boolean isVisible){
        frameLogin.setVisible(isVisible);
    }
}
