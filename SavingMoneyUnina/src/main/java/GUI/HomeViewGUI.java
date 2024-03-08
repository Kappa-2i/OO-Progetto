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
    ImageIcon iconExit = new ImageIcon(HomeViewGUI.class.getResource("/IMG/door_exit.png"));
    ImageIcon iconLogOut = new ImageIcon(HomeViewGUI.class.getResource("/IMG/logout.png"));
    ImageIcon iconUnina = new ImageIcon(HomeViewGUI.class.getResource("/IMG/unina.png")); // Sostituisci con il percorso del tuo file icona
    ImageIcon iconUpgrade = new ImageIcon(HomeViewGUI.class.getResource("/IMG/upgrade.png"));
    ImageIcon iconDowngrade = new ImageIcon(HomeViewGUI.class.getResource("/IMG/downgrade.png"));
    ImageIcon iconRaccolte = new ImageIcon(HomeViewGUI.class.getResource("/IMG/raccolte.png"));
    ImageIcon iconInviaSoldi = new ImageIcon(HomeViewGUI.class.getResource("/IMG/sendMoney.png"));
    ImageIcon iconSalvadanaio = new ImageIcon(HomeViewGUI.class.getResource("/IMG/saving_resized.png"));
    ImageIcon iconSpese = new ImageIcon(HomeViewGUI.class.getResource("/IMG/time-count_resized_flipped.png"));
    ImageIcon iconSaldo = new ImageIcon(HomeViewGUI.class.getResource("/IMG/credit_resized.png"));
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


        // Creazione panello principale che contiene il tutto
        JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.setBackground(new Color(246, 248, 255));

        // Dichiarazione dei constraints per posizionare i pannelli
        GridBagConstraints gbc = new GridBagConstraints();

        // Dichiarazione del pannello superiore con aggiunta dei constraints per posizionarlo
        JPanel panelTop = new JPanel(new GridBagLayout());
        panelTop.setBackground(new Color(0, 50, 73));
        gbc.gridwidth = 4;
        gbc.weighty = 0.1;
        gbc.weightx = 1;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;
        contentPane.add(panelTop, gbc);

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
        JLabel titoloSmu = new JLabel("S.M.U.");
        titoloSmu.setForeground(Color.WHITE);
        if (fontExtraBold != null) {
            homePageLabel.setFont(fontExtraBold);
            titoloSmu.setFont(fontRegular);
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
                controller.showPickBankAccountView();
            }
        });



        JLabel settingsLabel = new JLabel("Elimina Conto Corrente");
        settingsLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        settingsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                settingsLabel.setText("<html><u>Elimina Conto Corrente</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                settingsLabel.setText("Elimina Conto Corrente");
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

        JLabel addMoney = new JLabel("Aggiungi Fondi");
        addMoney.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMoney.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                addMoney.setText("<html><u>Aggiungi Fondi</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addMoney.setText("Aggiungi Fondi");
            }

//            @Override
//            public void mouseClicked(MouseEvent e){
//                controller.backFramePick();
//            }
        });

        if (fontRegular != null){
            accountLabel.setFont(fontRegular);
            bankAccountLabel.setFont(fontRegular);
            settingsLabel.setFont(fontRegular);

        }

        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 10);
        userPanel.add(accountLabel, gbc);
        gbc.gridy = 1;
        userPanel.add(bankAccountLabel, gbc);
        gbc.gridy = 3;
        userPanel.add(settingsLabel, gbc);




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
        panelTop.add(titoloSmu, gbc);

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
        RoundedPanel saldoPanel = new RoundedPanel(50, new Color(69, 184, 196, 255) );
        saldoPanel.setLayout(new GridBagLayout());
        saldoPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saldoPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.showCardView();
            }
        });

        RoundedPanel spesePanel = new RoundedPanel(50, new Color(128, 206, 215));
        spesePanel.setLayout(new GridBagLayout());
        spesePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        spesePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.showTransactionView();
            }
        });

        RoundedPanel salvadanaioPanel = new RoundedPanel(50, new Color(174, 227, 230));
        salvadanaioPanel.setLayout(new GridBagLayout());
        salvadanaioPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        salvadanaioPanel.addMouseListener(new MouseAdapter() {
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
        panelLeft.add(saldoPanel, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.33;
        panelLeft.add(spesePanel, gbc);

        gbc.gridy = 2;
        gbc.weighty = 0.33;
        panelLeft.add(salvadanaioPanel, gbc);

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

        JLabel cartaLabel = new JLabel(carta);
        cartaLabel.setForeground(new Color(8, 76, 97));
        JLabel saldoLabel = new JLabel(String.valueOf(controller.getSelectedBankAccount().getBalance())+"€");
        saldoLabel.setForeground(new Color(246, 248, 255));
        JButton buttonSaldo = new JButton();
        buttonSaldo.setBackground(null);
        buttonSaldo.setIcon(iconSaldo);
        buttonSaldo.setContentAreaFilled(false);
        buttonSaldo.setOpaque(false);
        buttonSaldo.setBorderPainted(false);
        buttonSaldo.setBorder(null);
        buttonSaldo.setFocusPainted(false);
        buttonSaldo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.showCardView();
            }
        });



        JLabel speseLabel = new JLabel("<html><b>LE TUE<br>SPESE</b></html>");
        speseLabel.setForeground(new Color(8, 76, 97));
        JButton buttonSpese = new JButton();
        buttonSpese.setBackground(null);
        buttonSpese.setIcon(iconSpese);
        buttonSpese.setContentAreaFilled(false);
        buttonSpese.setOpaque(false);
        buttonSpese.setBorderPainted(false);
        buttonSpese.setBorder(null);
        buttonSpese.setFocusPainted(false);
        buttonSpese.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.showTransactionView();
            }
        });


        JLabel salvadanaioLabel = new JLabel("<html><b>PIGGY<br>BANK</b></html>");
        salvadanaioLabel.setForeground(new Color(8, 76, 97));
        JButton buttonSalvadanaio = new JButton();
        buttonSalvadanaio.setBackground(null);
        buttonSalvadanaio.setIcon(iconSalvadanaio);
        buttonSalvadanaio.setContentAreaFilled(false);
        buttonSalvadanaio.setOpaque(false);
        buttonSalvadanaio.setBorderPainted(false);
        buttonSalvadanaio.setBorder(null);
        buttonSalvadanaio.setFocusPainted(false);


        if (fontRegular != null){
            saldoLabel.setFont(fontRegularXXL);
            cartaLabel.setFont(fontRegularXXL);
            speseLabel.setFont(fontRegularXXL);
            salvadanaioLabel.setFont(fontRegularXXL);
        }


        gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 0.5;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(-40, 0, 0, 0);
        saldoPanel.add(cartaLabel, gbc);


        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = 2;
        gbc.gridy = 1;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(-40, 0, 0, 0);
        saldoPanel.add(saldoLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 0);
        saldoPanel.add(buttonSaldo, gbc);

        gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 0.5;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(0, 0, 0, 0);
        spesePanel.add(speseLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 0);
        spesePanel.add(buttonSpese, gbc);

        gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 0;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(0, 8, 0, 0);
        salvadanaioPanel.add(salvadanaioLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 50, 0, 0);
        salvadanaioPanel.add(buttonSalvadanaio, gbc);


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


        JButton buttonInviaSoldi = new JButton();
        buttonInviaSoldi.setBackground(null);
        buttonInviaSoldi.setIcon(iconInviaSoldi);
        buttonInviaSoldi.setContentAreaFilled(false);
        buttonInviaSoldi.setOpaque(false);
        buttonInviaSoldi.setBorderPainted(false);
        buttonInviaSoldi.setBorder(null);
        buttonInviaSoldi.setFocusPainted(false);
        buttonInviaSoldi.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonInviaSoldi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.showBankTransferView();
            }
        });

        JLabel inviaSoldiLabel = new JLabel("   INVIA BONIFICO");
        if (fontRegularXXL != null)
            inviaSoldiLabel.setFont(fontRegularXXL);
        inviaSoldiLabel.setForeground(new Color(8, 76, 97));
        inviaSoldiLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        inviaSoldiLabel.addMouseListener(new MouseAdapter() {
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
        inviaSoldiPanel.add(buttonInviaSoldi, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0.7;
        gbc.anchor = GridBagConstraints.CENTER;
        inviaSoldiPanel.add(inviaSoldiLabel, gbc);


        JButton buttonRaccolte = new JButton();
        buttonRaccolte.setBackground(null);
        buttonRaccolte.setIcon(iconRaccolte);
        buttonRaccolte.setContentAreaFilled(false);
        buttonRaccolte.setOpaque(false);
        buttonRaccolte.setBorderPainted(false);
        buttonRaccolte.setBorder(null);
        buttonRaccolte.setFocusPainted(false);
        buttonRaccolte.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonRaccolte.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.showCollectionPickView();
            }
        });

        JLabel raccolteLabel = new JLabel("          RACCOLTE");
        if (fontRegularXXL != null)
            raccolteLabel.setFont(fontRegularXXL);
        raccolteLabel.setForeground(new Color(8, 76, 97));
        raccolteLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        raccolteLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.showCollectionPickView();
            }
        });



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




        JButton buttonNotifiche = new JButton();
        buttonNotifiche.setBackground(null);
        buttonNotifiche.setContentAreaFilled(false);
        buttonNotifiche.setOpaque(false);
        buttonNotifiche.setBorderPainted(false);
        buttonNotifiche.setBorder(null);
        buttonNotifiche.setFocusPainted(false);
        buttonNotifiche.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel notificheLabel2 = new JLabel("");
        if(controller.getCard().getTypeCard().equals("CartaDiCredito")) {
            notificheLabel2.setText("<html><b>DOWNGRADE<br>CARTA</b></html>");
            buttonNotifiche.setIcon(iconDowngrade);
        }
        else {
            notificheLabel2.setText("<html><b>UPGRADE CARTA</b></html>");
            buttonNotifiche.setIcon(iconUpgrade);
        }

        notificheLabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(notificheLabel2.getText().equals("<html><b>UPGRADE CARTA</b></html>")){
                    controller.upgradeCard(controller.getCard().getPan());
                }
                else
                    controller.downgradeCard(controller.getCard().getPan());
            }
        });

        if (fontRegularXXL != null)
            notificheLabel2.setFont(fontRegularXXL);
        notificheLabel2.setForeground(new Color(8, 76, 97));
        notificheLabel2.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.weightx = 1.0; // Bilancia lo spazio extra a destra
        gbc.fill = GridBagConstraints.HORIZONTAL;
        riceviSoldiPanel.add(Box.createHorizontalGlue(), gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.WEST;
        riceviSoldiPanel.add(buttonNotifiche, gbc);


        gbc.gridx = 2;
        gbc.weightx = 0.7;
        gbc.anchor = GridBagConstraints.CENTER;
        riceviSoldiPanel.add(notificheLabel2, gbc);



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