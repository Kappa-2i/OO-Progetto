package GUI;

import CONTROLLER.Controller;
import ENTITY.BankAccount;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;
import java.util.ArrayList;


public class BankAccountPickViewGUI extends JFrame {
    //Dichiarazione del controller
    private Controller controller;

    //Dichiarazione dei font
    private Font fontRegular;
    private Font fontRegularBold;
    private Font fontBold;
    private Font fontExtraBold;
    private Font fontRegularSmall;

    //Dichiarazione del panel usato sia nel costruttore sia nella funzione
    private JPanel panelCenter;

    //Dichiarazione delle immagini da usare
    private ImageIcon iconUnina = new ImageIcon(BankAccountPickViewGUI.class.getResource("/IMG/unina.png"));
    private ImageIcon iconLogout = new ImageIcon(BankAccountPickViewGUI.class.getResource("/IMG/logout.png"));
    private ImageIcon iconExit = new ImageIcon(BankAccountPickViewGUI.class.getResource("/IMG/door_exit.png"));



    public BankAccountPickViewGUI(Controller controller){
        this.controller = controller;
        setTitle("Seleziona conto");
        setSize(1400, 800);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        fontBold();
        fontRegular();
        fontExtraBold();
        fontRegularSmall();
        fontRegularBold();
        Object[] options = {"Sì", "No"};

        // Aggiungo il content Panel
        JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.setBackground(new Color(246, 248, 255));

        // Constraints
        GridBagConstraints gbc = new GridBagConstraints();


        // Creazione del TopPanel
        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setBackground(new Color(0, 50, 73, 255));
        topPanel.setOpaque(true);
        JLabel titleLabel = new JLabel("Benvenuto " +controller.getAccount().getName());
        if (fontExtraBold != null)
            titleLabel.setFont(fontExtraBold);
        titleLabel.setForeground(new Color(246, 248, 255));
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        topPanel.add(titleLabel, gbc);

        // Creazione del logo Unina
        JButton buttonLogo = new JButton();
        buttonLogo.setBackground(null);
        buttonLogo.setIcon(iconUnina);
        buttonLogo.setContentAreaFilled(false);
        buttonLogo.setOpaque(false);
        buttonLogo.setBorderPainted(false);
        buttonLogo.setBorder(null);
        buttonLogo.setFocusPainted(false);

        // Creazione del titoloSavingMoneyUnina
        JLabel smuLabel = new JLabel("S.M.U.");
        smuLabel.setForeground(Color.WHITE);
        if (fontExtraBold != null) {
            smuLabel.setFont(fontRegular);
        }

        //Creazione dell'icona di logOut
        JButton logOutButton = new JButton();
        logOutButton.setIcon(iconLogout);
        logOutButton.setBackground(null);
        logOutButton.setOpaque(true);
        logOutButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia il cursore per indicare che è cliccabile
        logOutButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        logOutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int scelta = JOptionPane.showOptionDialog(
                        null, // Componente padre
                        "Sei sicuro di voler uscire?", // Messaggio
                        "Conferma Logout", // Titolo
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, // Tipo di messaggio
                        iconExit, // Icona personalizzata, usa null per l'icona di default
                        options, // Array contenente le etichette dei pulsanti
                        options[1] // Opzione di default
                );
                if (scelta == JOptionPane.YES_OPTION)
                    controller.backLoginPage();
            }
        });
        gbc = new GridBagConstraints();


        // Configurazione per buttonLogo a sinistra di titoloSmu
        gbc.gridx = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 15, 0, 0);
        topPanel.add(buttonLogo, gbc);

        // Configurazione per il titoloSmu a sinistra di homePageLabel
        gbc.gridx = 2; // Posiziona titoloSmu accanto a buttonLogo
        topPanel.add(smuLabel, gbc);

        gbc = new GridBagConstraints();
        // Aggiunge spazio di espansione a destra per mantenere la titleLabel centrata
        gbc.gridx = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        topPanel.add(Box.createHorizontalGlue(), gbc);

        gbc = new GridBagConstraints();
        // Configurazione per la homePageLabel al centro
        gbc.gridx = 4;
        topPanel.add(titleLabel, gbc);

        // Aggiunge spazio di espansione a destra per mantenere titleLabel centrata
        gbc.gridx = 5;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.EAST;
        topPanel.add(Box.createHorizontalGlue(), gbc);

        gbc = new GridBagConstraints();
        // Configurazione per buttonLogout a destra della homePageLabel
        gbc.gridx = 6;
        topPanel.add(logOutButton, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.05;
        contentPane.add(topPanel, gbc); //Aggiunge il topPanel con gli elementi al suo interno

        // Creazione del panelCenter con i conti correnti al suo interno
        panelCenter = new JPanel(new GridBagLayout());
        panelCenter.setBackground(new Color(246, 248, 255));
        panelCenter.setOpaque(true);

        controller.selectBankAccountByAccount(controller.getAccount());
        showBankAccount();
        // Creazione dello JScrollPane che conterrà panelSignIn
        JScrollPane scrollPane = new JScrollPane(panelCenter);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Impostazioni per gbc in modo che scrollPane si espanda correttamente
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.95;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 0);

        // Aggiungi scrollPane a contentPane invece di panelSignIn
        contentPane.add(scrollPane, gbc);
        setContentPane(contentPane);
    }

    public void showBankAccount(){

        ArrayList<BankAccount> bankAccounts = controller.selectBankAccountByAccount(controller.getAccount());

        if (!bankAccounts.isEmpty()){
            int y = 2;
            int x = 0;
            for (BankAccount conto : bankAccounts) {
                //If per controllare se la riga è finita, deve ricominciare accapo
                if (x == 3)
                    x = 0;

                JPanel cardBank = new JPanel();
                cardBank.setBackground(new Color(246, 248, 255));
                cardBank.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 84, 122)));
                cardBank.setCursor(new Cursor(Cursor.HAND_CURSOR));
                cardBank.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        cardBank.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 84, 122)));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        cardBank.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 84, 122)));
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        controller.showHomePage(conto);
                    }
                });

                //Creazione della label Iban:
                JLabel ibanLabel = new JLabel("Iban: ");
                if (fontRegularBold != null)
                    ibanLabel.setFont(fontRegularBold);

                //Creazione della label per il numero dell'iban
                JLabel numberIbanLabel = new JLabel(conto.getIban());
                if (fontRegular != null)
                    numberIbanLabel.setFont(fontRegular);

                //Creazione della label Saldo:
                JLabel saldoLabel = new JLabel("Saldo: ");
                //Creazione della label per il valore del saldo
                JLabel numberSaldoLabel = new JLabel(String.valueOf(conto.getBalance())+"€");
                if (fontBold != null){
                    saldoLabel.setFont(fontBold);
                    numberSaldoLabel.setFont(fontBold);
                }

                //Panel Card con layout GroupLayout
                GroupLayout glBankAccount = new GroupLayout(cardBank);
                //Assegnazione del Layout del panel Principale con layout del GroupLayout
                cardBank.setLayout(glBankAccount);

                glBankAccount.setAutoCreateGaps(true);
                glBankAccount.setAutoCreateContainerGaps(true);

                //Creazione del gruppo orizzontale di una singola card
                GroupLayout.SequentialGroup hGroup = glBankAccount.createSequentialGroup();

                //Aggiunta orizzontale dei componenti  creati all'interno di una singola card
                hGroup.addGroup(glBankAccount.createParallelGroup().
                        addComponent(ibanLabel).addComponent(saldoLabel));
                hGroup.addGroup(glBankAccount.createParallelGroup().
                        addComponent(numberIbanLabel).addComponent(numberSaldoLabel));
                glBankAccount.setHorizontalGroup(hGroup);

                //Creazione del gruppo verticale di una singola card
                GroupLayout.SequentialGroup vGroup = glBankAccount.createSequentialGroup();

                //Aggiunta verticola dei componenti  creati all'interno di una singola card
                vGroup.addGroup(glBankAccount.createParallelGroup(GroupLayout.Alignment.BASELINE).
                        addComponent(ibanLabel).addComponent(numberIbanLabel));
                vGroup.addGroup(glBankAccount.createParallelGroup(GroupLayout.Alignment.BASELINE).
                        addComponent(saldoLabel).addComponent(numberSaldoLabel));
                glBankAccount.setVerticalGroup(vGroup);

                //Constraints per assegnare il panel creato con layout GroupLayout al panelCenter con lo scrollPane
                GridBagConstraints gbc = new GridBagConstraints();

                gbc.insets = new Insets(40, 40, 40, 40);
                gbc.gridy = y;
                gbc.gridx = x;
                panelCenter.add(cardBank, gbc);
                //Dopo aver aggiunto una card, passo alla colonna successiva
                x++;
                //Nel caso sono arrivato a fine riga, vado alla riga successiva
                if(x == 3)
                    y++;

                // Controlla se l'elemento corrente è l'ultimo dell'ArrayList
                if (conto.equals(bankAccounts.get(bankAccounts.size() - 1))) {
                    //If per controllare se la riga è finita, deve ricominciare accapo
                    if (x == 3){
                        x = 0;
                    }
                    JPanel addBankAccountPanel = new JPanel();
                    addBankAccountPanel.setBackground(new Color(246, 248, 255));
                    addBankAccountPanel.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 84, 122)));
                    addBankAccountPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    addBankAccountPanel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            addBankAccountPanel.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 84, 122)));
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
                            addBankAccountPanel.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 84, 122)));
                        }
                    });

                    JLabel createBankAccountLabel = new JLabel("Crea Conto Corrente +");
                    if (fontRegularBold != null)
                        createBankAccountLabel.setFont(fontRegularBold);

                    createBankAccountLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            addBankAccountPanel.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 84, 122)));
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
                            addBankAccountPanel.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 84, 122)));
                        }

                        @Override
                        public void mouseClicked(MouseEvent e){
                            if(controller.insertBankAccount(controller.getAccount().getEmail())) {

                                    setVisible(false);
                                    controller.checkCredentials(controller.getAccount().getEmail(), controller.getAccount().getPassword());

                            }
                        }
                    });

                    //Panel Card con layout GroupLayout
                    GroupLayout glAddBank = new GroupLayout(addBankAccountPanel);
                    //Assegnazione del Layout del panel Principale con layout del GroupLayout
                    addBankAccountPanel.setLayout(glAddBank);

                    glAddBank.setAutoCreateGaps(true);
                    glAddBank.setAutoCreateContainerGaps(true);

                    //Creazione del gruppo orizzontale di una singola card
                    GroupLayout.SequentialGroup hGroup2 = glAddBank.createSequentialGroup();

                    //Aggiunta orizzontale dei componenti  creati all'interno di una singola card
                    hGroup2.addGroup(glAddBank.createParallelGroup().
                            addComponent(createBankAccountLabel));
                    glAddBank.setHorizontalGroup(hGroup2);

                    //Creazione del gruppo verticale di una singola card
                    GroupLayout.SequentialGroup vGroup2 = glAddBank.createSequentialGroup();

                    //Aggiunta verticale dei componenti  creati all'interno di una singola card
                    vGroup2.addGroup(glAddBank.createParallelGroup(GroupLayout.Alignment.BASELINE).
                            addComponent(createBankAccountLabel));
                    glAddBank.setVerticalGroup(vGroup2);

                    //Constraints per assegnare il panel creato con layout GroupLayout al panelCenter con lo scrollPane
                    gbc = new GridBagConstraints();

                    gbc.insets = new Insets(40, 40, 40, 40);
                    gbc.gridy = y;
                    gbc.gridx = x;
                    panelCenter.add(addBankAccountPanel, gbc);
                }
            }
        }
        else {
            JPanel addBankAccountPanel = new JPanel();
            addBankAccountPanel.setBackground(new Color(246, 248, 255));
            addBankAccountPanel.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 84, 122)));
            addBankAccountPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            addBankAccountPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    addBankAccountPanel.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 84, 122)));
                }

                public void mouseExited(MouseEvent e) {
                    addBankAccountPanel.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 84, 122)));
                }
            });

            JLabel createBankAccountLabel = new JLabel("Crea Conto Corrente +");
            if (fontRegularBold != null)
                createBankAccountLabel.setFont(fontRegularBold);
            createBankAccountLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                    if(controller.insertBankAccount(controller.getAccount().getEmail())) {

                            setVisible(false);
                            controller.checkCredentials(controller.getAccount().getEmail(), controller.getAccount().getPassword());

                    }
                }
            });

            //Panel Card con layout GroupLayout
            GroupLayout glAddBank = new GroupLayout(addBankAccountPanel);
            //Assegnazione del Layout del panel Principale con layout del GroupLayout
            addBankAccountPanel.setLayout(glAddBank);

            glAddBank.setAutoCreateGaps(true);
            glAddBank.setAutoCreateContainerGaps(true);

            //Creazione del gruppo orizzontale di una singola card
            GroupLayout.SequentialGroup hGroup2 = glAddBank.createSequentialGroup();

            //Aggiunta orizzontale dei componenti  creati all'interno di una singola card
            hGroup2.addGroup(glAddBank.createParallelGroup().
                    addComponent(createBankAccountLabel));
            glAddBank.setHorizontalGroup(hGroup2);

            //Creazione del gruppo verticale di una singola card
            GroupLayout.SequentialGroup vGroup2 = glAddBank.createSequentialGroup();

            //Aggiunta verticale dei componenti  creati all'interno di una singola card
            vGroup2.addGroup(glAddBank.createParallelGroup(GroupLayout.Alignment.BASELINE).
                    addComponent(createBankAccountLabel));
            glAddBank.setVerticalGroup(vGroup2);

            //Constraints per assegnare il panel creato con layout GroupLayout al panelCenter con lo scrollPane
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.insets = new Insets(40, 40, 40, 40);
            gbc.gridy = 2;
            gbc.gridx = 0;
            panelCenter.add(addBankAccountPanel, gbc);
        }
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