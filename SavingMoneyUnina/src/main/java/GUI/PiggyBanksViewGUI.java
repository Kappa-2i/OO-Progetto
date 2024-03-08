package GUI;

import CONTROLLER.Controller;
import ENTITY.*;
import EXCEPTIONS.MyExc;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;
import javax.swing.table.*;



public class PiggyBanksViewGUI extends JFrame {
    private Controller controller;
    //Dichiarazioni Variabili per i Font
    private Font fontRegular;
    private Font fontBold;
    private Font fontExtraBold;
    private Font fontRegularSmall;
    private Font fontRegularBold;
    private Font fontRegularXXL;

    //Icone
    private ImageIcon iconUnina = new ImageIcon(PiggyBanksViewGUI.class.getResource("/IMG/unina.png"));
    private ImageIcon iconHome = new ImageIcon(PiggyBanksViewGUI.class.getResource("/IMG/home.png"));
    private ImageIcon iconAddPiggyBank = new ImageIcon(PiggyBanksViewGUI.class.getResource("/IMG/addPiggyBank.png"));
    private ImageIcon iconExit = new ImageIcon(PiggyBanksViewGUI.class.getResource("/IMG/door_exit.png"));
    private ImageIcon iconInformation = new ImageIcon(PiggyBanksViewGUI.class.getResource("/IMG/information.png"));


    public PiggyBanksViewGUI(Controller controller){
        this.controller = controller;
        setTitle("Salvadanaio - S.M.U.");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1400, 800);
        setLocationRelativeTo(null);
        fontBold();
        fontRegular();
        fontExtraBold();
        fontRegularSmall();
        fontRegularBold();
        fontRegularXXL();
        Object[] optionsAdd = {"Crea", "Annulla"};
        Object[] optionsFill = {"Invia", "Annulla"};
        Object[] optionsGet = {"Prendi", "Annulla"};
        Object[] options = {"Annulla", "Invia soldi", "Prendi soldi", "Elimina"};


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
        JPanel panelCenter = new JPanel(new GridBagLayout());
        panelCenter.setBackground(new Color(0, 50, 73));
        gbc.gridwidth = 1;
        gbc.weighty = 0.95;
        gbc.weightx = 0.7;
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.insets = new Insets(20, 20, 20, 20);
        contentPane.add(panelCenter, gbc);

        // Dichiarazione dei componenti per il pannello superiore
        JLabel piggyBankLabel = new JLabel("Piggy Bank");
        piggyBankLabel.setForeground(new Color(246, 248, 255));
        JLabel titoloSmu = new JLabel("S.M.U.");
        titoloSmu.setForeground(Color.WHITE);
        if (fontExtraBold != null) {
            piggyBankLabel.setFont(fontExtraBold);
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

                controller.showHomeView(controller.getSelectedBankAccount());
            }
        });

        JButton addPiggyBankButton = new JButton();
        addPiggyBankButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addPiggyBankButton.setBackground(null);
        addPiggyBankButton.setIcon(iconAddPiggyBank);
        addPiggyBankButton.setContentAreaFilled(false);
        addPiggyBankButton.setOpaque(false);
        addPiggyBankButton.setBorderPainted(false);
        addPiggyBankButton.setBorder(null);
        addPiggyBankButton.setFocusPainted(false);
        addPiggyBankButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Creazione del JPanel che conterrà i JTextField
                JPanel addPiggyPanel = new JPanel(new GridBagLayout());
                JLabel namePiggyLabel = new JLabel("Nome salvadanaio: ");
                JTextField nomeField = new JTextField();
                JLabel obiettivoLabel = new JLabel("Obiettivo: ");
                JTextField obiettivoField = new JTextField();
                JLabel descrizioneLabel = new JLabel("Descrizione: ");
                JTextField descrizionField = new JTextField();



                GridBagConstraints gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.BOTH;
                gbc.weightx = 0.3;
                gbc.gridy = 0;
                gbc.gridx = 0;
                addPiggyPanel.add(namePiggyLabel, gbc);
                gbc.gridwidth = 2;
                gbc.weightx = 0.6;
                gbc.gridy = 1;
                gbc.gridx = 0;
                addPiggyPanel.add(nomeField, gbc);
                gbc.gridwidth = 1;
                gbc.weightx = 0.3;
                gbc.gridy = 2;
                gbc.gridx = 0;
                addPiggyPanel.add(obiettivoLabel, gbc);
                gbc.gridwidth = 2;
                gbc.weightx = 0.6;
                gbc.gridy = 3;
                gbc.gridx = 0;
                addPiggyPanel.add(obiettivoField, gbc);
                gbc.gridwidth = 1;
                gbc.weightx = 0.3;
                gbc.gridy = 4;
                gbc.gridx = 0;
                addPiggyPanel.add(descrizioneLabel, gbc);
                gbc.gridwidth = 2;
                gbc.weightx = 0.6;
                gbc.gridy = 5;
                gbc.gridx = 0;
                addPiggyPanel.add(descrizionField, gbc);

                // Mostra il JOptionPane con i JTextField inseriti
                int result = JOptionPane.showOptionDialog(
                        null,
                        addPiggyPanel,
                        "Crea Salvadanaio",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        iconExit, // Icona personalizzata
                        optionsAdd, // Array contenente le etichette dei pulsanti
                        optionsAdd[0] // Opzione di default
                );
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        controller.addPiggyBank(nomeField.getText(), Math.round(Double.parseDouble(obiettivoField.getText())*100.00)/100.00, descrizionField.getText());
                    } catch (MyExc ex) {
                        throw new RuntimeException(ex);
                    }
                    catch (NumberFormatException exc){
                        JOptionPane.showMessageDialog(
                                null,
                                "Inserisci una cifra valida!",
                                "Errore inserimento",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                    controller.showPiggyBankView();
                }

            }
        });

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 15, 0, 0);
        panelHeader.add(buttonLogo, gbc);

        // Configurazione per il titoloSmu a sinistra di homePageLabel
        gbc.gridx = 2;
        panelHeader.add(titoloSmu, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelHeader.add(Box.createHorizontalGlue(), gbc);

        gbc = new GridBagConstraints();
        // Configurazione per la piggyBankLabel al centro
        gbc.gridx = 4;
        panelHeader.add(piggyBankLabel, gbc);

        gbc.gridx = 5;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.EAST;
        panelHeader.add(Box.createHorizontalGlue(), gbc);

        gbc = new GridBagConstraints();
        // Configurazione per addPiggyBank button e buttonHome a destra
        gbc.gridx = 6;
        panelHeader.add(addPiggyBankButton, gbc);

        gbc.gridx = 7;
        gbc.insets = new Insets(0, 20, 0, 15);
        panelHeader.add(buttonHome, gbc);


        /**
         * Definizione della JTable con le informazioni di tutti i salvadanai del conto
         * */


        // Colonne della tabella
        String[] colonne = {"Nome", "Descrizione", "Obiettivo", "Saldo Risparmio", "Saldo Rimanente", "Data Creazione"};

        // Modello di tabella predefinito con celle non editabili
        DefaultTableModel model = new DefaultTableModel(colonne, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Rende tutte le celle non editabili
            }
        };
        // Aggiungere i dati del salvadanaio al modello
        for(PiggyBank piggyBanks : controller.getPiggyBanks()){
            Object[] riga = {
                    piggyBanks.getNamePiggyBank(),
                    piggyBanks.getDescription(),
                    piggyBanks.getTarget() + "€",
                    piggyBanks.getSavingBalance() + "€",
                    piggyBanks.getRemainingBalance() + "€",
                    piggyBanks.getCreationDate()
            };
            model.addRow(riga);
        }

        // Creare la tabella con il modello
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.getTableHeader().setFont(fontRegularBold);
        table.getTableHeader().setBackground(new Color(246, 248, 255));
        scrollPane.getViewport().setBackground(new Color(246, 248, 255));
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));


        // Renderer centrato per le celle
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setFont(fontRegular);
        table.setRowHeight(70);
        table.setForeground(new Color(8, 76, 97));
        table.setBackground(new Color(174, 227, 230));

        // Applicare il renderer a tutte le celle per centrare il testo
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Impostare la larghezza preferita per le colonne 0,1
        int smallWidth = 280;
        for (int i = 0; i <= 1; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(smallWidth);
            column.setMaxWidth(smallWidth);
            column.setMinWidth(smallWidth);
        }

        // Impostare la larghezza preferita per le colonne 2, 3, 4, 5
        smallWidth = 200;
        for (int i = 2; i <= 5; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(smallWidth);
            column.setMaxWidth(smallWidth);
            column.setMinWidth(smallWidth);
        }



        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point point = e.getPoint();
                int currentRow = table.rowAtPoint(point);
                if (currentRow >= 0) { // Verifica che il clic sia su una riga valida
                    // Costruisci il messaggio con i dati della riga cliccata
                    StringBuilder infoPiggyBank = new StringBuilder();
                    infoPiggyBank.append("Dettagli Salvadanaio:\n");
                    for (int i = 0; i < table.getColumnCount(); i++) {
                        infoPiggyBank.append("<html><b>" +table.getColumnName(i) + ": </b>" + table.getValueAt(currentRow, i) + "\n</html>");
                    }

                    // Mostra il dialog con le informazioni
                    // Mostra il JOptionPane con i JTextField inseriti
                    int result = JOptionPane.showOptionDialog(
                            null,
                            infoPiggyBank.toString(),
                            "Informazioni Salvadanaio",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            iconInformation, // Icona personalizzata
                            options, // Array contenente le etichette dei pulsanti
                            options[0] // Opzione di default
                    );

                    switch (result){
                        case 0: // caso annulla
                            break;
                        case 1: // caso invia soldi
                            JPanel fillPiggyBankPanel = new JPanel(new GridBagLayout());
                            JLabel soldiLabel = new JLabel("Inserisci una cifra da inviare al salvadanaio: ");
                            JTextField moneyField = new JTextField();
                            GridBagConstraints gbc = new GridBagConstraints();
                            gbc.fill = GridBagConstraints.BOTH;
                            gbc.gridy = 0;
                            gbc.gridx = 0;
                            fillPiggyBankPanel.add(soldiLabel, gbc);
                            gbc.gridy = 1;
                            gbc.gridx = 0;
                            fillPiggyBankPanel.add(moneyField, gbc);
                            int resultFill = JOptionPane.showOptionDialog(
                                    null,
                                    fillPiggyBankPanel,
                                    "Invia soldi al salvadanaio",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    iconInformation, // Icona personalizzata
                                    optionsFill, // Array contenente le etichette dei pulsanti
                                    optionsFill[0] // Opzione di default
                            );
                            if (resultFill == JOptionPane.YES_OPTION) {
                                controller.fillPiggyBank((String) table.getValueAt(currentRow, 0), moneyField.getText());
                                controller.updateBankAccount(controller.getSelectedBankAccount());
                                controller.showPiggyBankView();
                            }
                            break;
                        case 2: // caso prendi soldi
                            JPanel getPiggyBankPanel = new JPanel(new GridBagLayout());
                            JLabel getMoneyLabel = new JLabel("Inserisci una cifra da prendere dal salvadanaio: ");
                            JTextField getMoneyField = new JTextField();
                            gbc = new GridBagConstraints();
                            gbc.fill = GridBagConstraints.BOTH;
                            gbc.gridy = 0;
                            gbc.gridx = 0;
                            getPiggyBankPanel.add(getMoneyLabel, gbc);
                            gbc.gridy = 1;
                            gbc.gridx = 0;
                            getPiggyBankPanel.add(getMoneyField, gbc);
                            int resultGet = JOptionPane.showOptionDialog(
                                    null,
                                    getPiggyBankPanel,
                                    "Prendi soldi dal salvadanaio",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    iconInformation, // Icona personalizzata
                                    optionsGet, // Array contenente le etichette dei pulsanti
                                    optionsGet[0] // Opzione di default
                            );
                            if (resultGet == JOptionPane.YES_OPTION){
                                // Ottiene il valore dalla tabella e convertilo in Stringa
                                String valueWithCurrency = (String) table.getValueAt(currentRow, 3);
                                //  Rimuove il simbolo della valuta '€' e qualsiasi altro carattere non numerico, mantenendo solo numeri e punto decimale
                                String numericValue = valueWithCurrency.replaceAll("[^\\d.]", "");
                                controller.getMoneyByPiggyBank(numericValue, (String) table.getValueAt(currentRow, 0), getMoneyField.getText());
                                controller.updateBankAccount(controller.getSelectedBankAccount());
                                controller.showPiggyBankView();

                            }
                            break;
                        case 3: // caso elimina salvadanaio
                            if (table.getValueAt(currentRow, 3).equals("0.0€")) {
                                controller.deletePiggyBank((String) table.getValueAt(currentRow, 0));
                                controller.showPiggyBankView();
                            }
                            else{
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Rimuovi prima i tuoi risparmi!",
                                        "Errore",
                                        JOptionPane.ERROR_MESSAGE
                                );
                            }
                            break;
                    }
                }
            }
        });

        //posiziona lo scroll pane con la tabella al suo interno nel panel Center
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        gbc.weightx = 1;
        panelCenter.add(scrollPane, gbc);

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
