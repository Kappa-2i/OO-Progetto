package GUI;

import CONTROLLER.Controller;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;
import java.sql.SQLException;

public class LoginViewGUI extends JFrame{

    //Dichiarazione del controller
    private Controller controller;

    //Dichiarazioni Variabili per i Font
    private Font fontRegular;
    private Font fontBold;
    private Font fontExtraBold;
    private Font fontRegularSmall;
    private Font fontRegularBold;

    //Dichiarazioni immagini usate
    private ImageIcon iconHiddenPassword = new ImageIcon(LoginViewGUI.class.getResource("/IMG/hiddenpassword.png"));
    private ImageIcon iconShowedPassword = new ImageIcon(LoginViewGUI.class.getResource("/IMG/showedpassword.png"));
    private ImageIcon iconApp = new ImageIcon(LoginViewGUI.class.getResource("/IMG/digital-money.png"));


    public LoginViewGUI(Controller controller){
        this.controller = controller;
        setTitle("Login Page");
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
        // Creazione del pannello di sfondo e set del GridBagLayout
        JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.setBackground(new Color(246, 248, 255));


        //Creazione constraints per il GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();


        //Creazione di un JPanel con GridBagLayout per contenere i componenti utili per il login
        JPanel panelLoginWhite = new JPanel(new GridBagLayout());
        panelLoginWhite.setBackground(new Color(246, 248, 255));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weightx = 0.5;
        gbc.weighty = 1;
        contentPane.add(panelLoginWhite, gbc);//Aggiunge il panelLoginWhite al contentPane


        //Creazione di un JPanel 'PanelLoginBlue' con GridBagLayout
        JPanel panelLoginBlue = new JPanel(new GridBagLayout());
        panelLoginBlue.setBackground(new Color(0, 50, 73, 255));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        gbc.weighty = 1;
        contentPane.add(panelLoginBlue, gbc);//Aggiunge il panelLoginBlue al contentPane


        gbc = new GridBagConstraints();
        JButton buttonApp = new JButton();
        buttonApp.setIcon(iconApp);
        buttonApp.setBackground(null);
        buttonApp.setOpaque(true);
        buttonApp.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia il cursore per indicare che è cliccabile
        buttonApp.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelLoginBlue.add(buttonApp, gbc); //Aggiunge l'immagine al centro del panelSignUpBlue




        // Creazione e aggiunta dei componenti sul pannello 'PanelLoginWhite'
        //Creazione della label 'Login'
        JLabel loginLabel = new JLabel("Login");
        if (fontExtraBold != null)
            loginLabel.setFont(fontExtraBold);
        gbc.gridwidth = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(50, 5, 100, 5);
        panelLoginWhite.add(loginLabel, gbc); //aggiunge la loginLabel al panelLoginWhite


        //Creazione della label e della textfield per il campo email.
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);
        emailField.setBackground(new Color(246, 248, 255));
        if (fontRegularBold != null)
            emailLabel.setFont(fontRegularBold);
        if (fontRegular != null){
            emailField.setFont(fontRegular);
        }

        //funzione per modificare i bordi del TextField
        emailField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
        emailField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                emailField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 84, 122)));
            }
            @Override
            public void focusLost(FocusEvent e) {
                emailField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
            }
        });
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelLoginWhite.add(emailLabel, gbc); //Aggiunge la emailLabel al panelLoginWhite
        gbc.gridy = 3;
        panelLoginWhite.add(emailField, gbc); //Aggiunge la emailfield al panelLoginWhite


        //Creaione della label 'Password' e della textfield per il campo password.
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBackground(new Color(246, 248, 255));
        passwordField.setEchoChar('*');
        if (fontRegularBold != null)
            passwordLabel.setFont(fontRegularBold);
        if (fontRegular != null){
            passwordField.setFont(fontRegular);
        }
        //funzione per modificare i bordi del TextField
        passwordField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 84, 122)));
            }
            @Override
            public void focusLost(FocusEvent e) {
                passwordField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
            }
        });


        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelLoginWhite.add(passwordLabel, gbc); //Aggiunge la passwordLabel al panelLoginWhite

        gbc.gridy = 5;
        panelLoginWhite.add(passwordField, gbc); //Aggiunge la passwordField al panelLoginWhite


        // Inizializzazione del JCheckBox per mostrare/nascondere la password
        JCheckBox showPasswordCheckBox = new JCheckBox("");
        if (fontRegularSmall != null)
            showPasswordCheckBox.setFont(fontRegularSmall);
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBox.isSelected()) {
                    passwordField.setEchoChar((char) 0); // Mostra la password
                } else {
                    passwordField.setEchoChar('*'); // Nasconde la password
                }
            }
        });

        showPasswordCheckBox.setIcon(iconHiddenPassword);
        showPasswordCheckBox.setSelectedIcon(iconShowedPassword);
        showPasswordCheckBox.setPressedIcon(iconShowedPassword);
        showPasswordCheckBox.setFocusPainted(false); //Nasconde il rettangolo che si crea quando il componente ha il focus
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        panelLoginWhite.add(showPasswordCheckBox, gbc); //Aggiunge la showPasswordCheckBox al panelLoginWhite


        // Crazione della label 'CreaUtenteLabel'
        JLabel newUserLabel = new JLabel("Crea Utente");
        if (fontRegularSmall != null)
            newUserLabel.setFont(fontRegularSmall);
        newUserLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia il cursore per indicare che è cliccabile

        //Aggiunge un MouseListener alla label 'creaUtenteLabel'
        newUserLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Quando il mouse entra nella JLabel, applica la sottolineatura
                newUserLabel.setText("<html><u>Crea Utente</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Quando il mouse esce dalla JLabel, rimuovi la sottolineatura
                newUserLabel.setText("Crea Utente");
            }

            @Override
            public void mouseClicked(MouseEvent e){
                controller.showFrameSignIn();
            }
        });

        gbc.gridwidth = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 5, 5, 5);
        panelLoginWhite.add(newUserLabel, gbc); //Aggiunge la creaUtenteLabel al panelLoginWhite


        // Crazione della label 'passwordDimenticataLabel'
        JLabel forgottenPasswordLabel = new JLabel("Password dimenticata?");
        if (fontRegularSmall != null)
            forgottenPasswordLabel.setFont(fontRegularSmall);
        forgottenPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia il cursore per indicare che è cliccabile

        // Aggiunge un MouseListener alla JLabel
        forgottenPasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Quando il mouse entra nella JLabel, applica la sottolineatura
                forgottenPasswordLabel.setText("<html><u>Password dimenticata?</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Quando il mouse esce dalla JLabel, rimuovi la sottolineatura
                forgottenPasswordLabel.setText("Password dimenticata?");
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 8;
        panelLoginWhite.add(forgottenPasswordLabel, gbc); //Aggiunge la passwordDimenticatalabel al panelLoginWhite


        // Creazione del button 'loginButton'
        JButton loginButton = new JButton("Accedi");
        if (fontBold != null)
            loginButton.setFont(fontBold);
        loginButton.setOpaque(true);
        loginButton.setBackground(new Color(0, 0, 0, 255));
        loginButton.setForeground(new Color(246, 248, 255));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia il cursore per indicare che è cliccabile
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.checkCredentials(emailField.getText(), passwordField.getText());
                emailField.setText("");
                passwordField.setText("");
            }
        });
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = 0;
        panelLoginWhite.add(loginButton, gbc); //Aggiunge il loginButton al panelLoginWhite

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
