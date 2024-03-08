package GUI;

import CONTROLLER.Controller;
import ENTITY.*;
import org.jfree.chart.ChartPanel;

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

    private String monthNumber;
    private JPanel panelCenterSx;
    private String yearMonth;
    private String currentYear;
    private ChartPanel chartPanel;
    private JLabel entraMaxValue;
    private JLabel entraMinValue;
    private JLabel entraMedValue;
    private JLabel uscitaMaxValue;
    private JLabel uscitaMinValue;
    private JLabel uscitaMedValue;
    private JLabel totaleRicevutoValue;
    private JLabel totaleInviatoValue;

    //Icone
    ImageIcon iconUnina = new ImageIcon(PiggyBank.class.getResource("/IMG/unina.png"));
    ImageIcon iconHome = new ImageIcon(PiggyBank.class.getResource("/IMG/home.png"));
    ImageIcon iconStats = new ImageIcon(PiggyBank.class.getResource("/IMG/statistics.png"));

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

        // Pannello superiore
        JPanel panelTop = new JPanel(new GridBagLayout());
        panelTop.setBackground(new Color(0, 50, 73));
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4; // Occupa due colonne
        gbc.weightx = 1;
        gbc.weighty = 0.1;
        contentPane.add(panelTop, gbc);

        // Pannello sinistro scrollabile
        panelCenterSx = new JPanel(new GridBagLayout());
        panelCenterSx.setBackground(new Color(246, 248, 255));
        showTable();
        JScrollPane scrollPane = new JScrollPane(panelCenterSx);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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

        scrollPane.setBorder(new EmptyBorder(0,0,0,0));

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0; // Prima colonna
        gbc.gridy = 1; // Seconda riga
        gbc.gridwidth = 1; // Resetta a una colonna
        gbc.weightx = 0.4; // Imposta il peso orizzontale
        gbc.weighty = 0.9; // Imposta il peso verticale
        gbc.insets = new Insets(20, 20, 20, 10); // Imposta gli insetti
        contentPane.add(scrollPane, gbc);




        // Dichiarazione dei componenti per il pannello superiore
        JLabel speseLabel = new JLabel(controller.getSelectedCollection().getNameCollection());
        speseLabel.setForeground(new Color(246, 248, 255));
        JLabel titoloSmu = new JLabel("S.M.U.");
        titoloSmu.setForeground(Color.WHITE);
        if (fontExtraBold != null) {
            speseLabel.setFont(fontExtraBold);
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
                controller.showHomeView(controller.getContoScelto());
            }
        });

        JButton statsButton = new JButton();
        statsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        statsButton.setBackground(null);
        statsButton.setIcon(iconStats);
        statsButton.setContentAreaFilled(false);
        statsButton.setOpaque(false);
        statsButton.setBorderPainted(false);
        statsButton.setBorder(null);
        statsButton.setFocusPainted(false);


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
        panelTop.add(speseLabel, gbc);

        // Infine, aggiungi spazio di espansione a destra per mantenere homePageLabel centrata
        gbc.gridx = 5;
        gbc.weightx = 1.0; // Bilancia lo spazio extra a destra
        gbc.fill = GridBagConstraints.EAST;
        panelTop.add(Box.createHorizontalGlue(), gbc);

        gbc = new GridBagConstraints();
        // Configurazione per buttonUser e buttonLogout a destra della homePageLabel
        gbc.gridx = 6; // Posiziona buttonUser a destra della homePageLabel
        //panelTop.add(statsButton, gbc);

        gbc.gridx = 6; // Posiziona buttonLogout a destra di buttonUser
        gbc.insets = new Insets(0, 20, 0, 15); // Aggiusta gli insetti se necessario
        panelTop.add(buttonHome, gbc);

        setContentPane(contentPane);
    }

    public void showTable(){

        if(!controller.getTransactionsCollection().isEmpty()){
            int y = 0;
            for (Transaction transaction : controller.getTransactionsCollection()) {
                RoundedPanel cardBank = new RoundedPanel(15, new Color(222, 226, 230));
                cardBank.setLayout(new GridBagLayout());


                JLabel haiInviatoLabel = new JLabel(String.format("Hai inviato %.2f€ a", transaction.getAmount()));
                controller.selectNameAndSurnameByIban(transaction.getIban());
                JLabel ibanLabel = new JLabel(controller.getCredenzialiIbanMittDest());
                JLabel catLabel = new JLabel("Categoria: "+ transaction.getExitCategory());


                JLabel dataLabel = new JLabel(transaction.getDateTransaction() + ", " + transaction.getTimeTransaction());
                JLabel dettagliLabel = new JLabel("<html><u><i>Causale</i></u></html>");
                dettagliLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                dettagliLabel.addMouseListener(new MouseAdapter() {
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
                    haiInviatoLabel.setFont(fontRegularBold);
                    ibanLabel.setFont(fontRegularBoldSmall);
                }
                if(fontRegularSmall != null){
                    dataLabel.setFont(fontRegularSmall);
                    dettagliLabel.setFont(fontRegularSmall);
                    catLabel.setFont(fontRegularSmall);
                }


                // Aggiungi le etichette al cardBank
                GridBagConstraints gbc = new GridBagConstraints();

                haiInviatoLabel.setForeground(new Color(145, 57, 57));
                gbc.insets = new Insets(5, 5, 5, 5);
                gbc.weightx = 1.0;
                gbc.anchor = GridBagConstraints.NORTHWEST;
                cardBank.add(haiInviatoLabel, gbc);
                gbc.insets = new Insets(5, 5, 5, 5);
                gbc.anchor = GridBagConstraints.NORTHWEST;
                gbc.gridy = 1;
                cardBank.add(ibanLabel,gbc);


                gbc = new GridBagConstraints();
                gbc.insets = new Insets(20, 5, 5, 5);
                gbc.anchor = GridBagConstraints.SOUTHWEST;
                gbc.gridy = 2;
                cardBank.add(catLabel, gbc);
                gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 10);
                gbc.anchor = GridBagConstraints.SOUTHWEST;
                gbc.gridy = 3;
                cardBank.add(dataLabel, gbc);
                gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 10);
                gbc.anchor = GridBagConstraints.SOUTHEAST;
                gbc.gridx = 1;
                gbc.gridy = 3;
                cardBank.add(dettagliLabel, gbc);



                // Aggiungi il cardBank al panelCenter
                gbc = new GridBagConstraints();
                gbc.insets = new Insets(20, 5, 0, 5);
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.gridy = y++;
                gbc.gridx = 0;
                gbc.weightx = 1.0;
                gbc.weighty = 1.0;
                panelCenterSx.add(cardBank, gbc);

            }
        }
        else {
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

