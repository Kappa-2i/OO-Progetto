package GUI;

import CONTROLLER.Controller;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;

public class HomeViewGUI extends JFrame {
    private Controller controller;

    //Dichiarazioni Variabili per i Font
    private Font fontRegular;
    private Font fontBold;
    private Font fontExtraBold;
    private Font fontRegularSmall;
    private Font fontRegularBold;
    private Font fontRegularXXL;

    //Dichiarazione delle immagini
    ImageIcon iconExit = new ImageIcon(HomeViewGUI.class.getResource("/IMG/door_exit.png"));
    ImageIcon iconLogOut = new ImageIcon(HomeViewGUI.class.getResource("/IMG/logout.png"));
    ImageIcon iconUnina = new ImageIcon(HomeViewGUI.class.getResource("/IMG/unina.png")); // Sostituisci con il percorso del tuo file icona
    ImageIcon iconUpgrade = new ImageIcon(HomeViewGUI.class.getResource("/IMG/upgrade.png"));
    ImageIcon iconDowngrade = new ImageIcon(HomeViewGUI.class.getResource("/IMG/downgrade.png"));
    ImageIcon iconCollections = new ImageIcon(HomeViewGUI.class.getResource("/IMG/raccolte.png"));
    ImageIcon iconSendMoney = new ImageIcon(HomeViewGUI.class.getResource("/IMG/sendMoney.png"));
    ImageIcon iconPiggyBank = new ImageIcon(HomeViewGUI.class.getResource("/IMG/saving_resized.png"));
    ImageIcon iconTransactions = new ImageIcon(HomeViewGUI.class.getResource("/IMG/time-count_resized_flipped.png"));
    ImageIcon iconCard = new ImageIcon(HomeViewGUI.class.getResource("/IMG/credit_resized.png"));
    ImageIcon iconUser = new ImageIcon(HomeViewGUI.class.getResource("/IMG/user.png")); // Sostituisci con il percorso del tuo file icona
    ImageIcon iconDelete = new ImageIcon(HomeViewGUI.class.getResource("/IMG/delete.png"));
    ImageIcon iconCancel = new ImageIcon(HomeViewGUI.class.getResource("/IMG/cancel.png"));
    ImageIcon iconInformation = new ImageIcon(HomeViewGUI.class.getResource("/IMG/information.png"));

    public HomeViewGUI(Controller controller){
        this.controller = controller;
        setTitle("HomePage - S.M.U.");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        fontBold();
        fontRegular();
        fontExtraBold();
        fontRegularSmall();
        fontRegularBold();
        fontRegularXXL();
        Object[] options = {"Sì", "No"};


        // Creazione panello principale con GridBagLayout
        JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.setBackground(new Color(246, 248, 255));

        // Dichiarazione dei constraints per posizionare i pannelli
        GridBagConstraints gbc = new GridBagConstraints();

        // Dichiarazione del pannello superiore con aggiunta dei constraints per posizionarlo
        JPanel panelTop = new JPanel(new GridBagLayout());
        panelTop.setBackground(new Color(0, 50, 73, 255));
        gbc.gridwidth = 4;
        gbc.weighty = 0.1;
        gbc.weightx = 1;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;
        contentPane.add(panelTop, gbc);

        // Dichiarazione del pannello laterale sinistro con aggiunta dei constraints per posizionarlo
        RoundedPanel panelLeft = new RoundedPanel(50, new Color(0, 50, 73, 255));
        panelLeft.setLayout(new GridBagLayout());
        gbc.gridwidth = 1;
        gbc.weighty = 0.95;
        gbc.weightx = 0.04;
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.insets = new Insets(20, 20, 20, 20);
        contentPane.add(panelLeft, gbc);

        // Dichiarazione del pannello laterale destro con aggiunta dei constraints per posizionarlo
        JPanel panelGhost = new JPanel(new GridBagLayout());
        panelGhost.setBackground(new Color(246, 248, 255));

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0;
        gbc.weightx = 0.35;
        gbc.gridy = 1;
        gbc.gridx = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        contentPane.add(panelGhost, gbc);

        // Dichiarazione del pannello a comparsa laterale destro con aggiunta dei constraints per posizionarlo
        JPanel userPanel = new JPanel(new GridBagLayout());
        userPanel.setVisible(false);
        userPanel.setBackground(new Color(217, 217, 217));
        userPanel.setBorder(new MatteBorder(0, 3, 0, 0, new Color(0, 50, 73, 255)));
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0;
        gbc.weightx = 0.08;
        gbc.gridy = 1;
        gbc.gridx = 3;
        contentPane.add(userPanel, gbc);



        // Dichiarazione dei componenti per il pannello superiore
        JLabel homePageLabel = new JLabel("Home Page");
        homePageLabel.setForeground(new Color(246, 248, 255));
        JLabel titleSmu = new JLabel("S.M.U.");
        titleSmu.setForeground(Color.WHITE);
        if (fontExtraBold != null) {
            homePageLabel.setFont(fontExtraBold);
            titleSmu.setFont(fontRegular);
        }

        JButton buttonLogo = new JButton();
        buttonLogo.setBackground(null);
        buttonLogo.setIcon(iconUnina);
        buttonLogo.setContentAreaFilled(false);
        buttonLogo.setOpaque(false);
        buttonLogo.setBorderPainted(false);
        buttonLogo.setBorder(null);
        buttonLogo.setFocusPainted(false);

        JButton buttonLogout = new JButton();
        buttonLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonLogout.setBackground(null);
        buttonLogout.setIcon(iconLogOut);
        buttonLogout.setContentAreaFilled(false);
        buttonLogout.setOpaque(false);
        buttonLogout.setBorderPainted(false);
        buttonLogout.setBorder(null);
        buttonLogout.setFocusPainted(false);
        buttonLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int scelta = JOptionPane.showOptionDialog(
                        null,
                        "Sei sicuro di voler uscire?", // Messaggio
                        "Conferma Logout", // Titolo
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, // Tipo di messaggio
                        iconExit, // Icona personalizzata
                        options, // Array contenente le etichette dei pulsanti
                        options[1] // Opzione di default
                );
                if (scelta == JOptionPane.YES_OPTION)
                    controller.backLoginPage();
            }
        });



        JLabel accountLabel = new JLabel("Informazioni Profilo");
        accountLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        accountLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                accountLabel.setText("<html><u>Informazioni Profilo</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                accountLabel.setText("Informazioni Profilo");
            }

            @Override
            public void mouseClicked(MouseEvent e){
                JOptionPane.showMessageDialog(
                        null,
                        "<html><b>Nome: </b> " +controller.getAccount().getName() +"</html>"+
                                "\n<html><b>Cognome: </b> " +controller.getAccount().getSurname() +"</html>"+
                                "\n<html><b>E-mail: </b> " +controller.getAccount().getEmail() + "</html>" +
                                "\n<html><b>Iban: </b> " +controller.getSelectedBankAccount().getIban() + "</html>",
                        "Informazioni profilo",
                        JOptionPane.PLAIN_MESSAGE,
                        iconInformation
                );
            }
        });

        JLabel bankAccountLabel = new JLabel("Seleziona Conto");
        bankAccountLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bankAccountLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                bankAccountLabel.setText("<html><u>Seleziona Conto</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bankAccountLabel.setText("Seleziona Conto");
            }

            @Override
            public void mouseClicked(MouseEvent e){
                setVisible(false);
                controller.showPickFrame();
            }
        });

        JLabel notificheLabel = new JLabel("Elimina Account");
        notificheLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        notificheLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                notificheLabel.setText("<html><u>Elimina Account</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                notificheLabel.setText("Elimina Account");
            }
        });



        JLabel deleteBankAccount = new JLabel("Elimina Conto Corrente");
        deleteBankAccount.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteBankAccount.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                deleteBankAccount.setText("<html><u>Elimina Conto Corrente</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                deleteBankAccount.setText("Elimina Conto Corrente");
            }

            @Override
            public void mouseClicked(MouseEvent e){
                int scelta = JOptionPane.showOptionDialog(
                        null, // Componente padre
                        "Vuoi eliminare questo conto corrente?", // Messaggio
                        "Eliminazione conto corrente", // Titolo
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, // Tipo di messaggio
                        iconDelete, // Icona personalizzata, usa null per l'icona di default
                        options, // Array contenente le etichette dei pulsanti
                        options[1] // Opzione di default
                );
                if (scelta == JOptionPane.YES_OPTION)
                    controller.deleteBankAccount(controller.getSelectedBankAccount().getIban());
            }
        });


        if (fontRegular != null){
            accountLabel.setFont(fontRegular);
            bankAccountLabel.setFont(fontRegular);
            deleteBankAccount.setFont(fontRegular);
        }

        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 10);
        userPanel.add(accountLabel, gbc);
        gbc.gridy = 1;
        userPanel.add(bankAccountLabel, gbc);
        gbc.gridy = 3;
        userPanel.add(deleteBankAccount, gbc);




        JButton buttonUser = new JButton();
        buttonUser.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonUser.setBackground(null);
        buttonUser.setIcon(iconUser);
        buttonUser.setContentAreaFilled(false);
        buttonUser.setOpaque(false);
        buttonUser.setBorderPainted(false);
        buttonUser.setBorder(null);
        buttonUser.setFocusPainted(false);
        buttonUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //Metodi per mostrare e nascondere l'user Panel
                if (!userPanel.isVisible())
                    userPanel.setVisible(true);
                else
                    userPanel.setVisible(false);
            }
        });

        gbc = new GridBagConstraints();


        // Configurazione per buttonLogo a sinistra di titoloSmu
        gbc.gridx = 1; // Posizione immediatamente a sinistra di titoloSmu
        gbc.weightx = 0; // Non assegna spazio extra, mantiene la posizione
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 15, 0, 0); // Aggiusta gli insetti se necessario
        panelTop.add(buttonLogo, gbc);

        // Configurazione per il titoloSmu a sinistra di homePageLabel
        gbc.gridx = 2; // Posiziona titoloSmu accanto a buttonLogo
        panelTop.add(titleSmu, gbc);

        gbc = new GridBagConstraints();
        // Infine, aggiungi spazio di espansione a destra per mantenere homePageLabel centrata
        gbc.gridx = 3;
        gbc.weightx = 1.0; // Bilancia lo spazio extra a destra
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelTop.add(Box.createHorizontalGlue(), gbc);

        gbc = new GridBagConstraints();
        // Configurazione per la homePageLabel al centro
        gbc.gridx = 4;
        panelTop.add(homePageLabel, gbc);

        // Infine, aggiungi spazio di espansione a destra per mantenere homePageLabel centrata
        gbc.gridx = 5;
        gbc.weightx = 1.0; // Bilancia lo spazio extra a destra
        gbc.fill = GridBagConstraints.EAST;
        panelTop.add(Box.createHorizontalGlue(), gbc);

        gbc = new GridBagConstraints();
        // Configurazione per buttonUser e buttonLogout a destra della homePageLabel
        gbc.gridx = 6; // Posiziona buttonUser a destra della homePageLabel
        panelTop.add(buttonUser, gbc);

        gbc.gridx = 7; // Posiziona buttonLogout a destra di buttonUser
        gbc.insets = new Insets(0, 20, 0, 15); // Aggiusta gli insetti se necessario
        panelTop.add(buttonLogout, gbc);



        /**
         * Aggiungiamo ora i componenti all'interno del panello di sinistra
         * */
        RoundedPanel cardPanel = new RoundedPanel(50, new Color(69, 184, 196) );
        cardPanel.setLayout(new GridBagLayout());
        cardPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.showCardPage();
            }
        });

        RoundedPanel transactionsPanel = new RoundedPanel(50, new Color(128, 206, 215));
        transactionsPanel.setLayout(new GridBagLayout());
        transactionsPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        transactionsPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //controller.showTransazioniPage();
            }
        });

        RoundedPanel piggyBanksPanel = new RoundedPanel(50, new Color(174, 227, 230));
        piggyBanksPanel.setLayout(new GridBagLayout());
        piggyBanksPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        piggyBanksPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.showPiggyBankView();
            }
        });

        gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0.33;
        gbc.insets = new Insets(20, 20, 20, 20);
        panelLeft.add(cardPanel, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.33;
        panelLeft.add(transactionsPanel, gbc);

        gbc.gridy = 2;
        gbc.weighty = 0.33;
        panelLeft.add(piggyBanksPanel, gbc);

        /**
         * Aggiungiamo i componenti ad ognuno dei 3 rounded panel all'interno del panel di sx
         * */


        JLabel cardLabel = new JLabel();
        if (controller.getCard().getTypeCard().equals("CartaDiCredito")) {
             cardLabel.setText("<html><b>CARTA DI<br>CREDITO</b></html>");
        }
        else {
            cardLabel.setText("<html><b>CARTA DI<br>DEBITO</b></html>");
        }
        cardLabel.setForeground(new Color(8, 76, 97));
        JLabel amountLabel = new JLabel(String.valueOf(controller.getSelectedBankAccount().getBalance())+"€");
        amountLabel.setForeground(new Color(246, 248, 255));
        JButton buttonCard = new JButton();
        buttonCard.setBackground(null);
        buttonCard.setIcon(iconCard);
        buttonCard.setContentAreaFilled(false);
        buttonCard.setOpaque(false);
        buttonCard.setBorderPainted(false);
        buttonCard.setBorder(null);
        buttonCard.setFocusPainted(false);
        buttonCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.showCardPage();
            }
        });



        JLabel speseLabel = new JLabel("<html><b>LE TUE<br>SPESE</b></html>");
        speseLabel.setForeground(new Color(8, 76, 97));
        JButton buttonSpese = new JButton();
        buttonSpese.setBackground(null);
        buttonSpese.setIcon(iconTransactions);
        buttonSpese.setContentAreaFilled(false);
        buttonSpese.setOpaque(false);
        buttonSpese.setBorderPainted(false);
        buttonSpese.setBorder(null);
        buttonSpese.setFocusPainted(false);
        buttonSpese.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //controller.showTransazioniPage();
            }
        });


        JLabel biggyBankLabel = new JLabel("<html><b>PIGGY<br>BANK</b></html>");
        biggyBankLabel.setForeground(new Color(8, 76, 97));
        JButton buttonPiggyBank = new JButton();
        buttonPiggyBank.setBackground(null);
        buttonPiggyBank.setIcon(iconPiggyBank);
        buttonPiggyBank.setContentAreaFilled(false);
        buttonPiggyBank.setOpaque(false);
        buttonPiggyBank.setBorderPainted(false);
        buttonPiggyBank.setBorder(null);
        buttonPiggyBank.setFocusPainted(false);
        buttonPiggyBank.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.showPiggyBankView();
            }
        });


        if (fontRegular != null){
            amountLabel.setFont(fontRegularXXL);
            cardLabel.setFont(fontRegularXXL);
            speseLabel.setFont(fontRegularXXL);
            biggyBankLabel.setFont(fontRegularXXL);
        }


        gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 0.5;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(-40, 0, 0, 0);
        cardPanel.add(cardLabel, gbc);


        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = 2;
        gbc.gridy = 1;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(-40, 0, 0, 0);
        cardPanel.add(amountLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 0);
        cardPanel.add(buttonCard, gbc);

        gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 0.5;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(0, 0, 0, 0);
        transactionsPanel.add(speseLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 0);
        transactionsPanel.add(buttonSpese, gbc);

        gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 0;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(0, 8, 0, 0);
        piggyBanksPanel.add(biggyBankLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 50, 0, 0);
        piggyBanksPanel.add(buttonPiggyBank, gbc);


        /**
         * Aggiungiamo ora i componenti all'interno del panello trasparente centrale
         * */
        RoundedPanel inviaSoldiPanel = new RoundedPanel(200, new Color(246, 248, 255) );
        inviaSoldiPanel.setLayout(new GridBagLayout());

        RoundedPanel raccoltePanel = new RoundedPanel(200, new Color(246, 248, 255));
        raccoltePanel.setLayout(new GridBagLayout());

        RoundedPanel riceviSoldiPanel = new RoundedPanel(200, new Color(246, 248, 255));
        riceviSoldiPanel.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0.33;
        gbc.insets = new Insets(20, 20, 20, 20);
        panelGhost.add(inviaSoldiPanel, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.33;
        panelGhost.add(raccoltePanel, gbc);

        gbc.gridy = 2;
        gbc.weighty = 0.33;
        panelGhost.add(riceviSoldiPanel, gbc);

        /**
         * Aggiungiamo i componenti ad ognuno dei 3 rounded panel all'interno del panel trasparente
         * */


        JButton buttonSendMoney = new JButton();
        buttonSendMoney.setBackground(null);
        buttonSendMoney.setIcon(iconSendMoney);
        buttonSendMoney.setContentAreaFilled(false);
        buttonSendMoney.setOpaque(false);
        buttonSendMoney.setBorderPainted(false);
        buttonSendMoney.setBorder(null);
        buttonSendMoney.setFocusPainted(false);
        buttonSendMoney.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel sendMoneyLabel = new JLabel("   INVIA BONIFICO");
        if (fontRegularXXL != null)
            sendMoneyLabel.setFont(fontRegularXXL);
        sendMoneyLabel.setForeground(new Color(8, 76, 97));
        sendMoneyLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));


        gbc = new GridBagConstraints();

        // Infine, aggiungi spazio di espansione a destra per mantenere homePageLabel centrata
        gbc.gridx = 0;
        gbc.weightx = 1.0; // Bilancia lo spazio extra a destra
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inviaSoldiPanel.add(Box.createHorizontalGlue(), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.WEST;
        inviaSoldiPanel.add(buttonSendMoney, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0.7;
        gbc.anchor = GridBagConstraints.CENTER;
        inviaSoldiPanel.add(sendMoneyLabel, gbc);


        JButton buttonRaccolte = new JButton();
        buttonRaccolte.setBackground(null);
        buttonRaccolte.setIcon(iconCollections);
        buttonRaccolte.setContentAreaFilled(false);
        buttonRaccolte.setOpaque(false);
        buttonRaccolte.setBorderPainted(false);
        buttonRaccolte.setBorder(null);
        buttonRaccolte.setFocusPainted(false);
        buttonRaccolte.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel raccolteLabel = new JLabel("          RACCOLTE");
        if (fontRegularXXL != null)
            raccolteLabel.setFont(fontRegularXXL);
        raccolteLabel.setForeground(new Color(8, 76, 97));
        raccolteLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));



        gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.weightx = 0.7;
        gbc.anchor = GridBagConstraints.CENTER;
        raccoltePanel.add(raccolteLabel, gbc);


        gbc.gridx = 1;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.WEST;
        raccoltePanel.add(buttonRaccolte, gbc);

        // Infine, aggiungi spazio di espansione a destra per mantenere homePageLabel centrata
        gbc.gridx = 2;
        gbc.weightx = 1.0; // Bilancia lo spazio extra a destra
        gbc.fill = GridBagConstraints.HORIZONTAL;
        raccoltePanel.add(Box.createHorizontalGlue(), gbc);




        JButton upgradeOrDowngradeButton = new JButton();
        upgradeOrDowngradeButton.setBackground(null);
        upgradeOrDowngradeButton.setContentAreaFilled(false);
        upgradeOrDowngradeButton.setOpaque(false);
        upgradeOrDowngradeButton.setBorderPainted(false);
        upgradeOrDowngradeButton.setBorder(null);
        upgradeOrDowngradeButton.setFocusPainted(false);
        upgradeOrDowngradeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel upgradeOrDowngradeLabel = new JLabel("");
        if (fontRegularXXL != null)
            upgradeOrDowngradeLabel.setFont(fontRegularXXL);
        upgradeOrDowngradeLabel.setForeground(new Color(8, 76, 97));
        upgradeOrDowngradeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // In base al tipo della carta scelgo quale funzione far apparire
        if(controller.getCard().getTypeCard().equals("CartaDiCredito")) {
            upgradeOrDowngradeLabel.setText("<html><b>DOWNGRADE<br>CARTA</b></html>");
            upgradeOrDowngradeButton.setIcon(iconDowngrade);
        }
        else {
            upgradeOrDowngradeLabel.setText("<html><b>UPGRADE<br>CARTA</b></html>");
            upgradeOrDowngradeButton.setIcon(iconUpgrade);
        }
        upgradeOrDowngradeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(upgradeOrDowngradeLabel.getText().equals("<html><b>UPGRADE<br>CARTA</b></html>")){
                    controller.upgradeCard(controller.getCard().getPan());
                }
                else
                    controller.downgradeCard(controller.getCard().getPan());
            }
        });

        gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.weightx = 1.0; // Bilancia lo spazio extra a destra
        gbc.fill = GridBagConstraints.HORIZONTAL;
        riceviSoldiPanel.add(Box.createHorizontalGlue(), gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.WEST;
        riceviSoldiPanel.add(upgradeOrDowngradeButton, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0.7;
        gbc.anchor = GridBagConstraints.CENTER;
        riceviSoldiPanel.add(upgradeOrDowngradeLabel, gbc);


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

    //Creazione del fontRegular
    private void fontRegularXXL() {
        try {
            InputStream is = LoginViewGUI.class.getResourceAsStream("/FONT/Rubik-Bold.ttf"); // Sostituisci con il tuo percorso
            fontRegularXXL = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(40f); // Modifica la dimensione a piacimento
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fontRegularXXL);
        } catch (Exception e) {
            e.printStackTrace();
            fontRegularXXL = null;
        }
    }
}
