package GUI;

import CONTROLLER.Controller;
import ENTITY.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;

public class CollectionViewGUI extends JFrame {
    private Controller controller;

    //Dichiarazioni Variabili per i Font
    private Font fontRegular;
    private Font fontBold;
    private Font fontExtraBold;
    private Font fontRegularSmall;
    private Font fontRegularBold;
    private Font fontRegularXXL;
    private Font fontRegularBoldSmall;

    //Dichiarazione del panel usato sia nel costruttore che nella funzione per mostrare le transazioni
    private JPanel panelCenterScrollable;

    //Dichiarazione icone
    private ImageIcon iconUnina = new ImageIcon(PiggyBank.class.getResource("/IMG/unina.png"));
    private ImageIcon iconHome = new ImageIcon(PiggyBank.class.getResource("/IMG/home.png"));


    public CollectionViewGUI(Controller controller) {
        this.controller = controller;
        setTitle("La tua raccolta - S.M.U.");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        fontBold();
        fontRegular();
        fontExtraBold();
        fontRegularSmall();
        fontRegularBold();
        fontRegularXXL();
        fontRegularBoldSmall();

        Object[] optionsView = {"Visualizza", "Annulla"};


        // Creazione del panello principale che contiene il tutto
        JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.setBackground(new Color(246, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();

        // Panel header
        JPanel panelHeader = new JPanel(new GridBagLayout());
        panelHeader.setBackground(new Color(0, 50, 73));
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.weightx = 1;
        gbc.weighty = 0.1;
        contentPane.add(panelHeader, gbc);

        // Pannello centrale scrollabile
        panelCenterScrollable = new JPanel(new GridBagLayout());
        panelCenterScrollable.setBackground(new Color(246, 248, 255));
        //Funzione per mostrare le transazioni della raccolta selezionata
        showTransactionsCollection();
        JScrollPane scrollPane = new JScrollPane(panelCenterScrollable);
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //Metodo per modificare la barra dello scrollPane
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(158, 158, 161); // Colore della barra di scorrimento
                this.trackColor = new Color(246, 248, 255); // Colore dello sfondo della barra di scorrimento
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
        });

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.4;
        gbc.weighty = 0.9;
        gbc.insets = new Insets(20, 20, 20, 10);
        contentPane.add(scrollPane, gbc);


        // Dichiarazione dei componenti per il panel superiore
        JLabel nameCollectionLabel = new JLabel(controller.getSelectedCollection().getNameCollection());
        nameCollectionLabel.setForeground(new Color(246, 248, 255));
        //Dichiarazione titolo SavingMoneyUnina
        JLabel titleSmu = new JLabel("S.M.U.");
        titleSmu.setForeground(Color.WHITE);
        if (fontExtraBold != null) {
            nameCollectionLabel.setFont(fontExtraBold);
            titleSmu.setFont(fontRegular);
        }

        //Dichiarazione button con icon Unina
        JButton buttonLogo = new JButton();
        buttonLogo.setBackground(null);
        buttonLogo.setIcon(iconUnina);
        buttonLogo.setContentAreaFilled(false);
        buttonLogo.setOpaque(false);
        buttonLogo.setBorderPainted(false);
        buttonLogo.setBorder(null);
        buttonLogo.setFocusPainted(false);

        //Dichiarazione button con icon Home
        JButton buttonHome = new JButton();
        buttonHome.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonHome.setBackground(null);
        buttonHome.setIcon(iconHome);
        buttonHome.setContentAreaFilled(false);
        buttonHome.setOpaque(false);
        buttonHome.setBorderPainted(false);
        buttonHome.setBorder(null);
        buttonHome.setFocusPainted(false);
        buttonHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                controller.showHomeView(controller.getSelectedBankAccount());
            }
        });

        // Configurazione per buttonLogo a sinistra di titoloSmu
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 15, 0, 0);
        panelHeader.add(buttonLogo, gbc);

        // Configurazione per il titoloSmu a sinistra di homePageLabel
        gbc.gridx = 2;
        panelHeader.add(titleSmu, gbc);

        //Aggiunge spazio di espansione a destra per mantenere homePageLabel centrata
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelHeader.add(Box.createHorizontalGlue(), gbc);

        // Configurazione per il nome della collection al centro
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        panelHeader.add(nameCollectionLabel, gbc);

        //Aggiunge spazio di espansione a destra per mantenere il nome della collection centrata
        gbc.gridx = 5;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.EAST;
        panelHeader.add(Box.createHorizontalGlue(), gbc);

        // Configurazione per il button Home
        gbc.gridx = 6;
        gbc.insets = new Insets(0, 20, 0, 15);
        panelHeader.add(buttonHome, gbc);

        setContentPane(contentPane);
    }

    public void showTransactionsCollection(){

        //Controlla se ci sono transazioni nella raccolta selezionata
        if(!controller.getTransactionsCollection().isEmpty()){
            //ordinata per posizionare una card
            int y = 0;
            for (Transaction transaction : controller.getTransactionsCollection()) {
                //Card che contiene una transazione
                RoundedPanel cardBank = new RoundedPanel(15, new Color(222, 226, 230));
                cardBank.setLayout(new GridBagLayout());

                //Creazione label 'Hai inviato: $$$'
                JLabel moneySentLabel = new JLabel(String.format("Hai inviato %.2f€ a", transaction.getAmount()));

                //Creazione label che contiene le credenziali dell'Iban a cui è stata effettuata la transazione
                controller.selectNameAndSurnameByIban(transaction.getIban());
                JLabel credentialByIbanLabel = new JLabel(controller.getCredentialsIban());

                //Creazione label che contiene la categoria della transazione
                JLabel categoryLabel = new JLabel("Categoria: "+ transaction.getExitCategory());

                //Creazione label che contiene data e orario della transazione effettuata
                JLabel dateLabel = new JLabel(transaction.getDateTransaction() + ", " + transaction.getTimeTransaction());

                //Creazione label che se cliccata mostra la causale
                JLabel causalLabel = new JLabel("<html><u><i>Causale</i></u></html>");
                causalLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                causalLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JOptionPane.showMessageDialog(
                                null,
                                transaction.getCausal(),
                                "Causale",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                });

                if(fontRegularBold != null){
                    moneySentLabel.setFont(fontRegularBold);
                    credentialByIbanLabel.setFont(fontRegularBoldSmall);
                }
                if(fontRegularSmall != null){
                    dateLabel.setFont(fontRegularSmall);
                    causalLabel.setFont(fontRegularSmall);
                    categoryLabel.setFont(fontRegularSmall);
                }


                //Constraints per inserire le informazioni all'interno di una card
                GridBagConstraints gbc = new GridBagConstraints();

                moneySentLabel.setForeground(new Color(145, 57, 57));
                gbc.insets = new Insets(5, 5, 5, 5);
                gbc.weightx = 1.0;
                gbc.anchor = GridBagConstraints.NORTHWEST;
                cardBank.add(moneySentLabel, gbc);
                gbc.insets = new Insets(5, 5, 5, 5);
                gbc.anchor = GridBagConstraints.NORTHWEST;
                gbc.gridy = 1;
                cardBank.add(credentialByIbanLabel,gbc);


                gbc = new GridBagConstraints();
                gbc.insets = new Insets(20, 5, 5, 5);
                gbc.anchor = GridBagConstraints.SOUTHWEST;
                gbc.gridy = 2;
                cardBank.add(categoryLabel, gbc);
                gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 10);
                gbc.anchor = GridBagConstraints.SOUTHWEST;
                gbc.gridy = 3;
                cardBank.add(dateLabel, gbc);
                gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 10);
                gbc.anchor = GridBagConstraints.SOUTHEAST;
                gbc.gridx = 1;
                gbc.gridy = 3;
                cardBank.add(causalLabel, gbc);



                // Aggiungi il cardBank allo scrollPane
                gbc = new GridBagConstraints();
                gbc.insets = new Insets(20, 5, 0, 5);
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.gridy = y++;
                gbc.gridx = 0;
                gbc.weightx = 1.0;
                gbc.weighty = 1.0;
                panelCenterScrollable.add(cardBank, gbc);

            }
        }
        else {
            //Messaggio nel caso non sono state effettuate transazioni per la raccolta selezionata
            JOptionPane.showMessageDialog(
                    null,
                    "Non ci sono transazioni in questa raccolta",
                    "Attenzione!",
                    JOptionPane.ERROR_MESSAGE
            );
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

    //Creazioned del fontRegularBold
    private void fontRegularBoldSmall() {
        try {
            InputStream is = LoginViewGUI.class.getResourceAsStream("/FONT/Rubik-Bold.ttf"); // Sostituisci con il tuo percorso
            fontRegularBoldSmall = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(16f); // Modifica la dimensione a piacimento
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fontRegularBoldSmall);
        } catch (Exception e) {
            e.printStackTrace();
            fontRegularBoldSmall = null;
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

