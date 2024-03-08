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

    //Icone
    private ImageIcon iconExit = new ImageIcon(HomeViewGUI.class.getResource("/IMG/door_exit.png"));
    private ImageIcon iconLogOut = new ImageIcon(HomeViewGUI.class.getResource("/IMG/logout.png"));
    private ImageIcon iconUnina = new ImageIcon(HomeViewGUI.class.getResource("/IMG/unina.png")); // Sostituisci con il percorso del tuo file icona
    private ImageIcon iconUpgrade = new ImageIcon(HomeViewGUI.class.getResource("/IMG/upgrade.png"));
    private ImageIcon iconDowngrade = new ImageIcon(HomeViewGUI.class.getResource("/IMG/downgrade.png"));
    private ImageIcon iconCollections = new ImageIcon(HomeViewGUI.class.getResource("/IMG/raccolte.png"));
    private ImageIcon iconSendMoney = new ImageIcon(HomeViewGUI.class.getResource("/IMG/sendMoney.png"));
    private ImageIcon iconPiggyBank = new ImageIcon(HomeViewGUI.class.getResource("/IMG/saving_resized.png"));
    private ImageIcon iconTransactions = new ImageIcon(HomeViewGUI.class.getResource("/IMG/time-count_resized_flipped.png"));
    private ImageIcon iconCard = new ImageIcon(HomeViewGUI.class.getResource("/IMG/credit_resized.png"));
    private ImageIcon iconUser = new ImageIcon(HomeViewGUI.class.getResource("/IMG/user.png")); // Sostituisci con il percorso del tuo file icona
    private ImageIcon iconDelete = new ImageIcon(HomeViewGUI.class.getResource("/IMG/delete.png"));
    private ImageIcon iconCancel = new ImageIcon(HomeViewGUI.class.getResource("/IMG/cancel.png"));
    private ImageIcon iconInformation = new ImageIcon(HomeViewGUI.class.getResource("/IMG/information.png"));


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


        // Creazione panello principale che contiene il tutto
        JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.setBackground(new Color(246, 248, 255));

        // Dichiarazione dei constraints per posizionare i pannelli
        GridBagConstraints gbc = new GridBagConstraints();

        // Dichiarazione del pannello superiore con aggiunta dei constraints per posizionarlo
        JPanel panelHeader = new JPanel(new GridBagLayout());
        panelHeader.setBackground(new Color(0, 50, 73));
        gbc.gridwidth = 4;
        gbc.weighty = 0.1;
        gbc.weightx = 1;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;
        contentPane.add(panelHeader, gbc);

        // Dichiarazione del pannello laterale sinistro con aggiunta dei constraints per posizionarlo
        RoundedPanel panelLeft = new RoundedPanel(50, new Color(0, 50, 73));
        panelLeft.setLayout(new GridBagLayout());
        gbc.gridwidth = 1;
        gbc.weighty = 0.95;
        gbc.weightx = 0.04;
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.insets = new Insets(20, 20, 20, 20);
        contentPane.add(panelLeft, gbc);

        // Dichiarazione del pannello laterale destro con aggiunta dei constraints per posizionarlo
        JPanel panelCenter = new JPanel(new GridBagLayout());
        panelCenter.setBackground(new Color(246, 248, 255));

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0;
        gbc.weightx = 0.35;
        gbc.gridy = 1;
        gbc.gridx = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        contentPane.add(panelCenter, gbc);

        JPanel userPanel = new JPanel(new GridBagLayout());
        userPanel.setVisible(false);
        userPanel.setBackground(new Color(217, 217, 217));
        userPanel.setBorder(new MatteBorder(0, 3, 0, 0, new Color(0, 50, 73)));
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

        JLabel titleSmuLabel = new JLabel("S.M.U.");
        titleSmuLabel.setForeground(Color.WHITE);
        if (fontExtraBold != null) {
            homePageLabel.setFont(fontExtraBold);
            titleSmuLabel.setFont(fontRegular);
        }

        JButton logoButton = new JButton();
        logoButton.setBackground(null);
        logoButton.setIcon(iconUnina);
        logoButton.setContentAreaFilled(false);
        logoButton.setOpaque(false);
        logoButton.setBorderPainted(false);
        logoButton.setBorder(null);
        logoButton.setFocusPainted(false);

        JButton logOutButton = new JButton();
        logOutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logOutButton.setBackground(null);
        logOutButton.setIcon(iconLogOut);
        logOutButton.setContentAreaFilled(false);
        logOutButton.setOpaque(false);
        logOutButton.setBorderPainted(false);
        logOutButton.setBorder(null);
        logOutButton.setFocusPainted(false);
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
                    controller.backLoginView();
            }
        });



        JLabel infoLabel = new JLabel("Informazioni Profilo");
        infoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        infoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                infoLabel.setText("<html><u>Informazioni Profilo</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                infoLabel.setText("Informazioni Profilo");
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

        JLabel selectBankAccountLabel = new JLabel("Seleziona Conto");
        selectBankAccountLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectBankAccountLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                selectBankAccountLabel.setText("<html><u>Seleziona Conto</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                selectBankAccountLabel.setText("Seleziona Conto");
            }

            @Override
            public void mouseClicked(MouseEvent e){
                setVisible(false);
                controller.showPickBankAccountView();
            }
        });



        JLabel deleteBankAccountLabel = new JLabel("Elimina Conto Corrente");
        deleteBankAccountLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteBankAccountLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                deleteBankAccountLabel.setText("<html><u>Elimina Conto Corrente</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                deleteBankAccountLabel.setText("Elimina Conto Corrente");
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
            infoLabel.setFont(fontRegular);
            selectBankAccountLabel.setFont(fontRegular);
            deleteBankAccountLabel.setFont(fontRegular);

        }

        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 10);
        userPanel.add(infoLabel, gbc);
        gbc.gridy = 1;
        userPanel.add(selectBankAccountLabel, gbc);
        gbc.gridy = 3;
        userPanel.add(deleteBankAccountLabel, gbc);


        JButton userButton = new JButton();
        userButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        userButton.setBackground(null);
        userButton.setIcon(iconUser);
        userButton.setContentAreaFilled(false);
        userButton.setOpaque(false);
        userButton.setBorderPainted(false);
        userButton.setBorder(null);
        userButton.setFocusPainted(false);
        userButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
        panelHeader.add(logoButton, gbc);

        // Configurazione per il titoloSmu a sinistra di homePageLabel
        gbc.gridx = 2; // Posiziona titoloSmu accanto a buttonLogo
        panelHeader.add(titleSmuLabel, gbc);

        gbc = new GridBagConstraints();
        // Infine, aggiungi spazio di espansione a destra per mantenere homePageLabel centrata
        gbc.gridx = 3;
        gbc.weightx = 1.0; // Bilancia lo spazio extra a destra
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelHeader.add(Box.createHorizontalGlue(), gbc);

        gbc = new GridBagConstraints();
        // Configurazione per la homePageLabel al centro
        gbc.gridx = 4;
        panelHeader.add(homePageLabel, gbc);

        // Infine, aggiungi spazio di espansione a destra per mantenere homePageLabel centrata
        gbc.gridx = 5;
        gbc.weightx = 1.0; // Bilancia lo spazio extra a destra
        gbc.fill = GridBagConstraints.EAST;
        panelHeader.add(Box.createHorizontalGlue(), gbc);

        gbc = new GridBagConstraints();
        // Configurazione per buttonUser e buttonLogout a destra della homePageLabel
        gbc.gridx = 6; // Posiziona buttonUser a destra della homePageLabel
        panelHeader.add(userButton, gbc);

        gbc.gridx = 7; // Posiziona buttonLogout a destra di buttonUser
        gbc.insets = new Insets(0, 20, 0, 15); // Aggiusta gli insetti se necessario
        panelHeader.add(logOutButton, gbc);



        /**
         * Aggiungiamo ora i componenti all'interno del panello di sinistra
         * */
        RoundedPanel cardPanel = new RoundedPanel(50, new Color(69, 184, 196, 255) );
        cardPanel.setLayout(new GridBagLayout());
        cardPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.showCardView();
            }
        });

        RoundedPanel transactionsPanel = new RoundedPanel(50, new Color(128, 206, 215));
        transactionsPanel.setLayout(new GridBagLayout());
        transactionsPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        transactionsPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.showTransactionView();
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

        String carta;
        if (controller.getCard().getTypeCard().equals("CartaDiCredito")) {
            carta = "<html><b>CARTA<br>DI CREDITO</b></html>";
        }
        else {
            carta = "<html><b>CARTA<br>DI DEBITO</b></html>";
        }

        JLabel cardLabel = new JLabel(carta);
        cardLabel.setForeground(new Color(8, 76, 97));

        JLabel balanceLabel = new JLabel(String.valueOf(controller.getSelectedBankAccount().getBalance())+"€");
        balanceLabel.setForeground(new Color(246, 248, 255));
        JButton cardButtonIcon = new JButton();
        cardButtonIcon.setBackground(null);
        cardButtonIcon.setIcon(iconCard);
        cardButtonIcon.setContentAreaFilled(false);
        cardButtonIcon.setOpaque(false);
        cardButtonIcon.setBorderPainted(false);
        cardButtonIcon.setBorder(null);
        cardButtonIcon.setFocusPainted(false);
        cardButtonIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.showCardView();
            }
        });



        JLabel transactionsLabel = new JLabel("<html><b>LE TUE<br>SPESE</b></html>");
        transactionsLabel.setForeground(new Color(8, 76, 97));

        JButton transactionsButtonIcon = new JButton();
        transactionsButtonIcon.setBackground(null);
        transactionsButtonIcon.setIcon(iconTransactions);
        transactionsButtonIcon.setContentAreaFilled(false);
        transactionsButtonIcon.setOpaque(false);
        transactionsButtonIcon.setBorderPainted(false);
        transactionsButtonIcon.setBorder(null);
        transactionsButtonIcon.setFocusPainted(false);
        transactionsButtonIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.showTransactionView();
            }
        });


        JLabel piggyBankLabel = new JLabel("<html><b>PIGGY<br>BANKS</b></html>");
        piggyBankLabel.setForeground(new Color(8, 76, 97));

        JButton piggyBanksButtonIcon = new JButton();
        piggyBanksButtonIcon.setBackground(null);
        piggyBanksButtonIcon.setIcon(iconPiggyBank);
        piggyBanksButtonIcon.setContentAreaFilled(false);
        piggyBanksButtonIcon.setOpaque(false);
        piggyBanksButtonIcon.setBorderPainted(false);
        piggyBanksButtonIcon.setBorder(null);
        piggyBanksButtonIcon.setFocusPainted(false);


        if (fontRegular != null){
            balanceLabel.setFont(fontRegularXXL);
            cardLabel.setFont(fontRegularXXL);
            transactionsLabel.setFont(fontRegularXXL);
            piggyBankLabel.setFont(fontRegularXXL);
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
        cardPanel.add(balanceLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 0);
        cardPanel.add(cardButtonIcon, gbc);

        gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 0.5;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(0, 0, 0, 0);
        transactionsPanel.add(transactionsLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 0);
        transactionsPanel.add(transactionsButtonIcon, gbc);

        gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 0;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(0, 8, 0, 0);
        piggyBanksPanel.add(piggyBankLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 50, 0, 0);
        piggyBanksPanel.add(piggyBanksButtonIcon, gbc);


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
        panelCenter.add(inviaSoldiPanel, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.33;
        panelCenter.add(raccoltePanel, gbc);

        gbc.gridy = 2;
        gbc.weighty = 0.33;
        panelCenter.add(riceviSoldiPanel, gbc);

        /**
         * Aggiungiamo i componenti ad ognuno dei 3 rounded panel all'interno del panel centrale
         * */

        JButton sendMoneyButtonIcon = new JButton();
        sendMoneyButtonIcon.setBackground(null);
        sendMoneyButtonIcon.setIcon(iconSendMoney);
        sendMoneyButtonIcon.setContentAreaFilled(false);
        sendMoneyButtonIcon.setOpaque(false);
        sendMoneyButtonIcon.setBorderPainted(false);
        sendMoneyButtonIcon.setBorder(null);
        sendMoneyButtonIcon.setFocusPainted(false);
        sendMoneyButtonIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sendMoneyButtonIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.showBankTransferView();
            }
        });

        JLabel sendMoneyLabel = new JLabel("   INVIA BONIFICO");
        if (fontRegularXXL != null)
            sendMoneyLabel.setFont(fontRegularXXL);
        sendMoneyLabel.setForeground(new Color(8, 76, 97));
        sendMoneyLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sendMoneyLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.showBankTransferView();
            }
        });


        gbc = new GridBagConstraints();

        // Infine, aggiungi spazio di espansione a destra per mantenere homePageLabel centrata
        gbc.gridx = 0;
        gbc.weightx = 1.0; // Bilancia lo spazio extra a destra
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inviaSoldiPanel.add(Box.createHorizontalGlue(), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.WEST;
        inviaSoldiPanel.add(sendMoneyButtonIcon, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0.7;
        gbc.anchor = GridBagConstraints.CENTER;
        inviaSoldiPanel.add(sendMoneyLabel, gbc);


        JButton collectionsButtonIcon = new JButton();
        collectionsButtonIcon.setBackground(null);
        collectionsButtonIcon.setIcon(iconCollections);
        collectionsButtonIcon.setContentAreaFilled(false);
        collectionsButtonIcon.setOpaque(false);
        collectionsButtonIcon.setBorderPainted(false);
        collectionsButtonIcon.setBorder(null);
        collectionsButtonIcon.setFocusPainted(false);
        collectionsButtonIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        collectionsButtonIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.showCollectionPickView();
            }
        });

        JLabel collectionsLabel = new JLabel("          RACCOLTE");
        if (fontRegularXXL != null)
            collectionsLabel.setFont(fontRegularXXL);
        collectionsLabel.setForeground(new Color(8, 76, 97));
        collectionsLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        collectionsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.showCollectionPickView();
            }
        });



        gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.weightx = 0.7;
        gbc.anchor = GridBagConstraints.CENTER;
        raccoltePanel.add(collectionsLabel, gbc);


        gbc.gridx = 1;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.WEST;
        raccoltePanel.add(collectionsButtonIcon, gbc);

        // Infine, aggiungi spazio di espansione a destra per mantenere homePageLabel centrata
        gbc.gridx = 2;
        gbc.weightx = 1.0; // Bilancia lo spazio extra a destra
        gbc.fill = GridBagConstraints.HORIZONTAL;
        raccoltePanel.add(Box.createHorizontalGlue(), gbc);




        JButton upOrDownButtonIcon = new JButton();
        upOrDownButtonIcon.setBackground(null);
        upOrDownButtonIcon.setContentAreaFilled(false);
        upOrDownButtonIcon.setOpaque(false);
        upOrDownButtonIcon.setBorderPainted(false);
        upOrDownButtonIcon.setBorder(null);
        upOrDownButtonIcon.setFocusPainted(false);
        upOrDownButtonIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel upOrDowngradeLabel = new JLabel("");
        if(controller.getCard().getTypeCard().equals("CartaDiCredito")) {
            upOrDowngradeLabel.setText("<html><b>DOWNGRADE<br>CARTA</b></html>");
            upOrDownButtonIcon.setIcon(iconDowngrade);
        }
        else {
            upOrDowngradeLabel.setText("<html><b>UPGRADE CARTA</b></html>");
            upOrDownButtonIcon.setIcon(iconUpgrade);
        }

        upOrDowngradeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(upOrDowngradeLabel.getText().equals("<html><b>UPGRADE CARTA</b></html>")){
                    controller.upgradeCard(controller.getCard().getPan());
                }
                else
                    controller.downgradeCard(controller.getCard().getPan());
            }
        });

        if (fontRegularXXL != null)
            upOrDowngradeLabel.setFont(fontRegularXXL);
        upOrDowngradeLabel.setForeground(new Color(8, 76, 97));
        upOrDowngradeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.weightx = 1.0; // Bilancia lo spazio extra a destra
        gbc.fill = GridBagConstraints.HORIZONTAL;
        riceviSoldiPanel.add(Box.createHorizontalGlue(), gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.WEST;
        riceviSoldiPanel.add(upOrDownButtonIcon, gbc);


        gbc.gridx = 2;
        gbc.weightx = 0.7;
        gbc.anchor = GridBagConstraints.CENTER;
        riceviSoldiPanel.add(upOrDowngradeLabel, gbc);



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