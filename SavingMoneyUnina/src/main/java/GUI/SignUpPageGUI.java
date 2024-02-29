package GUI;

import CONTROLLER.Controller;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;
import java.sql.SQLException;

public class SignUpPageGUI extends JFrame{

    //Dichiarazione del controller
    private Controller controller;
    //Dichiarazioni Variabili per i Font
    private Font fontRegular;
    private Font fontBold;
    private Font fontExtraBold;
    private Font fontRegularSmall;
    private Font fontRegularBold;

    public SignUpPageGUI(Controller controller){
        this.controller = controller;
        setTitle("SignUp Page");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        fontBold();
        fontRegular();
        fontExtraBold();
        fontRegularSmall();
        fontRegularBold();

        // Creazione dei pannelli
        // Creazione del pannello di sfondo e setta il GridBagLayout
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(246, 248, 255));
        contentPane.setLayout(new GridBagLayout());


        //Creazione constraints per il GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();


        //Creazione di un JPanel con BoxLayout per contenere i componenti utili per il login
        JPanel panelLoginWhite = new JPanel(new GridBagLayout());
        panelLoginWhite.setBackground(new Color(246, 248, 255)); // Scegli il colore che preferisci
        panelLoginWhite.setOpaque(true); // Imposta come trasparente per mostrare il gradiente
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, -200, 0, 0);
        gbc.weightx = 0.2;
        gbc.weighty = 1;
        contentPane.add(panelLoginWhite, gbc);//Aggiunge il panelLoginWhite al contentPane


        //Creazione di un JPanel 'PanelLoginRed' con BoxLayout
        JPanel panelLoginGreen = new JPanel(new BorderLayout());
        panelLoginGreen.setBackground(new Color(0, 50, 73));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.45;
        gbc.weighty = 1;
        contentPane.add(panelLoginGreen, gbc);




        // Creazione e aggiunta dei componenti sul pannello 'PanelLoginWhite'
        //Creazione della label 'Login'
        JLabel loginLabel = new JLabel("Registrazione");
        if (fontExtraBold != null)
            loginLabel.setFont(fontExtraBold);
        gbc.gridwidth = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20, 5, 20, 5);
        panelLoginWhite.add(loginLabel, gbc); //aggiunge la loginLabel al panelLoginWhite

        //Creazione della label 'Email' e della textfield per il campo email.
        JLabel nameLabel = new JLabel("Nome:");
        JTextField nameField = new JTextField(20);
        nameField.setBackground(new Color(246, 248, 255));
        if (fontRegularBold != null)
            nameLabel.setFont(fontRegularBold);
        if (fontRegular != null){
            nameField.setFont(fontRegular);
        }
        nameField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
        nameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                nameField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(37, 89, 87)));
            }
            @Override
            public void focusLost(FocusEvent e) {
                nameField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
            }
        });
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelLoginWhite.add(nameLabel, gbc); //Aggiunge la emailLabel al panelLoginWhite
        gbc.gridy = 2;
        panelLoginWhite.add(nameField, gbc); //Aggiunge la emailfield al panelLoginWhite


        //Creazione della label 'Email' e della textfield per il campo email.
        JLabel surnameLabel = new JLabel("Cognome:");
        JTextField surnameField = new JTextField(20);
        surnameField.setBackground(new Color(246, 248, 255));
        if (fontRegularBold != null)
            surnameLabel.setFont(fontRegularBold);
        if (fontRegular != null){
            surnameField.setFont(fontRegular);
        }
        surnameField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
        surnameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                surnameField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(37, 89, 87)));
            }
            @Override
            public void focusLost(FocusEvent e) {
                surnameField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
            }
        });
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelLoginWhite.add(surnameLabel, gbc); //Aggiunge la emailLabel al panelLoginWhite
        gbc.gridy = 4;
        panelLoginWhite.add(surnameField, gbc); //Aggiunge la emailfield al panelLoginWhite


        //Creazione della label 'Email' e della textfield per il campo email.
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);
        emailField.setBackground(new Color(246, 248, 255));
        if (fontRegularBold != null)
            emailLabel.setFont(fontRegularBold);
        if (fontRegular != null){
            emailField.setFont(fontRegular);
        }

        emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, emailField.getPreferredSize().height));
        emailField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
        emailField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                emailField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(37, 89, 87)));
            }
            @Override
            public void focusLost(FocusEvent e) {
                emailField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
            }
        });
        gbc.gridy = 5;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelLoginWhite.add(emailLabel, gbc); //Aggiunge la emailLabel al panelLoginWhite
        gbc.gridy = 6;
        panelLoginWhite.add(emailField, gbc); //Aggiunge la emailfield al panelLoginWhite


        //Creaione della label 'Password' e della textfield per il campo password.
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBackground(new Color(246, 248, 255));
        passwordField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
        if (fontRegularBold != null)
            passwordLabel.setFont(fontRegularBold);
        if (fontRegular != null){
            passwordField.setFont(fontRegular);
        }
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(37, 89, 87)));
            }
            @Override
            public void focusLost(FocusEvent e) {
                passwordField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
            }
        });
        passwordField.setEchoChar('*');
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1; // Occupa una colonna
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelLoginWhite.add(passwordLabel, gbc);
        gbc.gridy = 8;
        panelLoginWhite.add(passwordField, gbc);

        //Creaione della label 'Password' e della textfield per il campo password.
        JLabel confirmPasswordLabel = new JLabel("Conferma Password:");
        JPasswordField confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setBackground(new Color(246, 248, 255));
        confirmPasswordField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
        if (fontRegularBold != null)
            confirmPasswordLabel.setFont(fontRegularBold);
        if (fontRegular != null){
            confirmPasswordField.setFont(fontRegular);
        }
        confirmPasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                confirmPasswordField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(37, 89, 87)));
            }
            @Override
            public void focusLost(FocusEvent e) {
                confirmPasswordField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
            }
        });
        confirmPasswordField.setEchoChar('*');
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 1; // Occupa una colonna
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelLoginWhite.add(confirmPasswordLabel, gbc);
        gbc.gridy = 10;
        panelLoginWhite.add(confirmPasswordField, gbc);



        // Creazione del button 'loginButton'
        JButton backButton = new JButton("Indietro");
        if (fontBold != null)
            backButton.setFont(fontBold);
        backButton.setOpaque(true);
        backButton.setBackground(new Color(0, 0, 0, 255));
        backButton.setForeground(new Color(246, 248, 255));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia il cursore per indicare che è cliccabile
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Chiamiamo la funzione checkCredentials dal controller passandogli i dati inseriti
                controller.backLoginPage();
            }
        });
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = 0;
        panelLoginWhite.add(backButton, gbc); //Aggiunge il loginButton al panelLoginWhit

        // Creazione del button 'loginButton'
        JButton signButton = new JButton("Registrati");
        if (fontBold != null)
            signButton.setFont(fontBold);
        signButton.setOpaque(true);
        signButton.setBackground(new Color(0, 0, 0, 255));
        signButton.setForeground(new Color(246, 248, 255));
        signButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia il cursore per indicare che è cliccabile
        signButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        signButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Chiamiamo la funzione checkCredentials dal controller passandogli i dati inseriti
                if(controller.confirmedPassword(passwordField.getText(), confirmPasswordField.getText())){
                    controller.insertAccount(emailField.getText(), passwordField.getText(), nameField.getText(), surnameField.getText());
                    emailField.setText("");
                    passwordField.setText("");
                    confirmPasswordField.setText("");
                    nameField.setText("");
                    surnameField.setText("");
                    controller.backLoginPage();
                }
                else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Le password non corrispondono",
                            "Errore",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = 0;
        panelLoginWhite.add(signButton, gbc); //Aggiunge il loginButton al panelLoginWhit

        setContentPane(contentPane);
    }

    //Creazione del fontExtraBold
    private void fontExtraBold() {
        try {
            InputStream is = LoginViewGUI.class.getResourceAsStream("/FONT/Rubik-ExtraBold.ttf"); // Sostituisci con il tuo percorso
            fontExtraBold = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(52f); // Modifica la dimensione a piacimento
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fontExtraBold);
        } catch (Exception e) {
            e.printStackTrace();
            fontExtraBold = null;
        }
    }

    //Creazione del fontBold
    private void fontBold() {
        try {
            InputStream is = LoginViewGUI.class.getResourceAsStream("/FONT/Rubik-Bold.ttf"); // Sostituisci con il tuo percorso
            fontBold = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(16f); // Modifica la dimensione a piacimento
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fontBold);
        } catch (Exception e) {
            e.printStackTrace();
            fontBold = null;
        }
    }

    //Creazione del fontRegular
    private void fontRegular() {
        try {
            InputStream is = LoginViewGUI.class.getResourceAsStream("/FONT/Rubik-Regular.ttf"); // Sostituisci con il tuo percorso
            fontRegular = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(20f); // Modifica la dimensione a piacimento
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fontRegular);
        } catch (Exception e) {
            e.printStackTrace();
            fontRegular = null;
        }
    }

    //Creazioned del fontRegularBold
    private void fontRegularBold() {
        try {
            InputStream is = LoginViewGUI.class.getResourceAsStream("/FONT/Rubik-Bold.ttf"); // Sostituisci con il tuo percorso
            fontRegularBold = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(22f); // Modifica la dimensione a piacimento
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fontRegularBold);
        } catch (Exception e) {
            e.printStackTrace();
            fontRegularBold = null;
        }
    }

    //Creazione del fontRegularSmall
    private void fontRegularSmall() {
        try {
            InputStream is = LoginViewGUI.class.getResourceAsStream("/FONT/Rubik-Regular.ttf"); // Sostituisci con il tuo percorso
            fontRegularSmall = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(12f); // Modifica la dimensione a piacimento
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fontRegularSmall);
        } catch (Exception e) {
            e.printStackTrace();
            fontRegularSmall = null;
        }
    }
}
