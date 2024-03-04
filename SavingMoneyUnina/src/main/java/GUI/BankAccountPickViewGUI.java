package GUI;

import CONTROLLER.Controller;
import ENTITY.BankAccount;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;
import java.sql.SQLException;
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

    private JPanel panelSignIn;


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


        // Panel 3 in alto a tutto
        JPanel panelSignIn3 = new JPanel(new GridBagLayout());
        panelSignIn3.setBackground(new Color(0, 84, 122)); // Scegli il colore che preferisci
        panelSignIn3.setOpaque(true);
        JLabel titoloFrame = new JLabel("Benvenuto " +controller.getAccount().getName());
        if (fontExtraBold != null)
            titoloFrame.setFont(fontExtraBold);
        titoloFrame.setForeground(new Color(246, 248, 255));
        //gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0; // Inizia dalla prima colonna
        gbc.gridy = 0; // Prima riga
        gbc.fill = GridBagConstraints.BOTH;
        panelSignIn3.add(titoloFrame, gbc);

        ImageIcon iconUnina = new ImageIcon(BankAccountPickViewGUI.class.getResource("/IMG/unina.png")); // Sostituisci con il percorso del tuo file icona
        JButton buttonLogo = new JButton();
        buttonLogo.setBackground(null);
        buttonLogo.setIcon(iconUnina);
        buttonLogo.setContentAreaFilled(false);
        buttonLogo.setOpaque(false);
        buttonLogo.setBorderPainted(false);
        buttonLogo.setBorder(null);
        buttonLogo.setFocusPainted(false);

        JLabel titoloSmu = new JLabel("S.M.U.");
        titoloSmu.setForeground(Color.WHITE);
        if (fontExtraBold != null) {
            titoloSmu.setFont(fontRegular);
        }

        //Crea componenti
        ImageIcon icon = new ImageIcon(BankAccountPickViewGUI.class.getResource("/IMG/logout.png")); // Sostituisci con il percorso del tuo file icona
        JButton logOutButton = new JButton();
        logOutButton.setIcon(icon);
        logOutButton.setBackground(null);
        logOutButton.setOpaque(true);
        logOutButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia il cursore per indicare che è cliccabile
        logOutButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        logOutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ImageIcon iconExit = new ImageIcon(BankAccountPickViewGUI.class.getResource("/IMG/door_exit.png"));
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
        gbc.gridx = 1; // Posizione immediatamente a sinistra di titoloSmu
        gbc.weightx = 0; // Non assegna spazio extra, mantiene la posizione
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 15, 0, 0); // Aggiusta gli insetti se necessario
        panelSignIn3.add(buttonLogo, gbc);

        // Configurazione per il titoloSmu a sinistra di homePageLabel
        gbc.gridx = 2; // Posiziona titoloSmu accanto a buttonLogo
        panelSignIn3.add(titoloSmu, gbc);

        gbc = new GridBagConstraints();
        // Infine, aggiungi spazio di espansione a destra per mantenere homePageLabel centrata
        gbc.gridx = 3;
        gbc.weightx = 1.0; // Bilancia lo spazio extra a destra
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelSignIn3.add(Box.createHorizontalGlue(), gbc);

        gbc = new GridBagConstraints();
        // Configurazione per la homePageLabel al centro
        gbc.gridx = 4;
        panelSignIn3.add(titoloFrame, gbc);

        // Infine, aggiungi spazio di espansione a destra per mantenere homePageLabel centrata
        gbc.gridx = 5;
        gbc.weightx = 1.0; // Bilancia lo spazio extra a destra
        gbc.fill = GridBagConstraints.EAST;
        panelSignIn3.add(Box.createHorizontalGlue(), gbc);

        gbc = new GridBagConstraints();
        // Configurazione per buttonUser e buttonLogout a destra della homePageLabel
        gbc.gridx = 6; // Posiziona buttonUser a destra della homePageLabel
        panelSignIn3.add(logOutButton, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0; // Inizia dalla prima colonna
        gbc.gridy = 0; // Prima riga
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Si estende su tutte le colonne
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.05;
        contentPane.add(panelSignIn3, gbc);

        // Crea un JPanel a sx
        panelSignIn = new JPanel(new GridBagLayout());
        panelSignIn.setBackground(new Color(246, 248, 255)); // Scegli il colore che preferisci
        panelSignIn.setOpaque(true);

        controller.selectBankAccountByAccount(controller.getAccount());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(40, 5, 20, 5);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        showBankAccount();
        // Creazione dello JScrollPane che conterrà panelSignIn
        JScrollPane scrollPane = new JScrollPane(panelSignIn);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Impostazioni per gbc in modo che scrollPane si espanda correttamente
        gbc.gridx = 0;
        gbc.gridy = 1; // Assicurati che questo valore di gridy non confligga con altri componenti
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.95; // Assegna più spazio a panelSignIn
        gbc.insets = new Insets(0, 0, 0, 0);

        // Aggiungi scrollPane a contentPane invece di panelSignIn
        contentPane.add(scrollPane, gbc);
        setContentPane(contentPane);
    }

    public void showBankAccount(){

        ArrayList<BankAccount> conti = controller.selectBankAccountByAccount(controller.getAccount());
        if (!conti.isEmpty()){
            int y = 2;
            int x = 0;
            for (BankAccount conto : conti) {
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


                JLabel ibanLabel = new JLabel("Iban: ");
                if (fontRegularBold != null)
                    ibanLabel.setFont(fontRegularBold);

                JLabel numberIbanLabel = new JLabel(conto.getIban());
                if (fontRegular != null)
                    numberIbanLabel.setFont(fontRegular);


                JLabel saldoLabel = new JLabel("Saldo: ");
                JLabel numberSaldoLabel = new JLabel(String.valueOf(conto.getBalance())+"€");
                if (fontBold != null){
                    saldoLabel.setFont(fontBold);
                    numberSaldoLabel.setFont(fontBold);
                }



                GroupLayout glBankAccount = new GroupLayout(cardBank);
                cardBank.setLayout(glBankAccount);

                glBankAccount.setAutoCreateGaps(true);
                glBankAccount.setAutoCreateContainerGaps(true);

                GroupLayout.SequentialGroup hGroup = glBankAccount.createSequentialGroup();

                hGroup.addGroup(glBankAccount.createParallelGroup().
                        addComponent(ibanLabel).addComponent(saldoLabel));
                hGroup.addGroup(glBankAccount.createParallelGroup().
                        addComponent(numberIbanLabel).addComponent(numberSaldoLabel));
                glBankAccount.setHorizontalGroup(hGroup);

                GroupLayout.SequentialGroup vGroup = glBankAccount.createSequentialGroup();

                vGroup.addGroup(glBankAccount.createParallelGroup(GroupLayout.Alignment.BASELINE).
                        addComponent(ibanLabel).addComponent(numberIbanLabel));
                vGroup.addGroup(glBankAccount.createParallelGroup(GroupLayout.Alignment.BASELINE).
                        addComponent(saldoLabel).addComponent(numberSaldoLabel));
                glBankAccount.setVerticalGroup(vGroup);



                GridBagConstraints gbc = new GridBagConstraints();

                gbc.insets = new Insets(40, 40, 40, 40);
                gbc.gridy = y;
                gbc.gridx = x;
                panelSignIn.add(cardBank, gbc);
                x++;
                if(x == 3)
                    y++;

                // Controlla se l'elemento corrente è l'ultimo dell'ArrayList
                if (conto.equals(conti.get(conti.size() - 1))) {
                    if (x == 3){
                        x = 0;
                    }
                    JPanel addBank = new JPanel();
                    addBank.setBackground(new Color(246, 248, 255));
                    addBank.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 84, 122)));
                    addBank.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    addBank.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            addBank.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 84, 122)));
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
                            addBank.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 84, 122)));
                        }
                    });

                    JLabel creaContoLabel = new JLabel("Crea Conto Corrente +");
                    if (fontRegularBold != null)
                        creaContoLabel.setFont(fontRegularBold);

                    creaContoLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            addBank.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 84, 122)));
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
                            addBank.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 84, 122)));
                        }

                        @Override
                        public void mouseClicked(MouseEvent e){
                            if(controller.insertBankAccount(controller.getAccount().getEmail())) {

                                    setVisible(false);
                                    controller.checkCredentials(controller.getAccount().getEmail(), controller.getAccount().getPassword());

                            }
                        }
                    });

                    GroupLayout glAddBank = new GroupLayout(addBank);
                    addBank.setLayout(glAddBank);

                    glAddBank.setAutoCreateGaps(true);
                    glAddBank.setAutoCreateContainerGaps(true);

                    GroupLayout.SequentialGroup hGroup2 = glAddBank.createSequentialGroup();
                    GroupLayout.SequentialGroup vGroup2 = glAddBank.createSequentialGroup();



                    hGroup2.addGroup(glAddBank.createParallelGroup().
                            addComponent(creaContoLabel));
                    glAddBank.setHorizontalGroup(hGroup2);


                    vGroup2.addGroup(glAddBank.createParallelGroup(GroupLayout.Alignment.BASELINE).
                            addComponent(creaContoLabel));
                    glAddBank.setVerticalGroup(vGroup2);

                    gbc = new GridBagConstraints();

                    gbc.insets = new Insets(40, 40, 40, 40);
                    gbc.gridy = y;
                    gbc.gridx = x;
                    panelSignIn.add(addBank, gbc);
                }
            }
        }
        else {
            JPanel addBank = new JPanel();
            addBank.setBackground(new Color(246, 248, 255));
            addBank.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 84, 122)));
            addBank.setCursor(new Cursor(Cursor.HAND_CURSOR));
            addBank.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    addBank.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 84, 122)));
                }

                public void mouseExited(MouseEvent e) {
                    addBank.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 84, 122)));
                }
            });

            JLabel creaContoLabel = new JLabel("Crea Conto Corrente +");
            if (fontRegularBold != null)
                creaContoLabel.setFont(fontRegularBold);
            creaContoLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                    if(controller.insertBankAccount(controller.getAccount().getEmail())) {

                            setVisible(false);
                            controller.checkCredentials(controller.getAccount().getEmail(), controller.getAccount().getPassword());

                    }
                }
            });

            GroupLayout glAddBank = new GroupLayout(addBank);
            addBank.setLayout(glAddBank);

            glAddBank.setAutoCreateGaps(true);
            glAddBank.setAutoCreateContainerGaps(true);

            GroupLayout.SequentialGroup hGroup2 = glAddBank.createSequentialGroup();
            GroupLayout.SequentialGroup vGroup2 = glAddBank.createSequentialGroup();



            hGroup2.addGroup(glAddBank.createParallelGroup().
                    addComponent(creaContoLabel));
            glAddBank.setHorizontalGroup(hGroup2);


            vGroup2.addGroup(glAddBank.createParallelGroup(GroupLayout.Alignment.BASELINE).
                    addComponent(creaContoLabel));
            glAddBank.setVerticalGroup(vGroup2);

            GridBagConstraints gbc = new GridBagConstraints();

            gbc.insets = new Insets(40, 40, 40, 40);
            gbc.gridy = 2;
            gbc.gridx = 0;
            panelSignIn.add(addBank, gbc);
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