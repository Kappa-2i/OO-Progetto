package GUI;

import CONTROLLER.Controller;
import ENTITY.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;
import java.time.LocalDate;

public class TransactionViewGUI extends JFrame {

    private Controller controller;

    //Dichiarazioni Variabili per i Font
    private Font fontRegular;
    private Font fontBold;
    private Font fontExtraBold;
    private Font fontRegularSmall;
    private Font fontRegularBold;
    private Font fontRegularXXL;
    private Font fontRegularBoldSmall;

    //Dichiarazione elementi da aggiungere dopo aver modificato la pagina, usati sia nel costruttore che nella funzione
    private String monthNumber;
    private JPanel panelLeft;
    private JPanel panelRight;
    private String yearMonth;
    private String currentYear;
    private ChartPanel chartPanel;
    private JLabel maxEntryValue;
    private JLabel minEntryValue;
    private JLabel avgEntryValue;
    private JLabel maxOutValue;
    private JLabel minOutValue;
    private JLabel avgOutValue;
    private JLabel totaleReceveidValue;
    private JLabel totalSentValue;

    //Dichiarazione icone
    private ImageIcon iconUnina = new ImageIcon(TransactionViewGUI.class.getResource("/IMG/unina.png"));
    private ImageIcon iconHome = new ImageIcon(TransactionViewGUI.class.getResource("/IMG/home.png"));

    //Dichiarazione opzioni da cliccare per il JOptionPane
    private Object[] optionsView = {"Visualizza", "Annulla"};


    public TransactionViewGUI(Controller controller) {
        this.controller = controller;
        setTitle("Le tue spese - S.M.U.");
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
        fontRegularBoldSmall();

        
        // Creazione del panello principale
        JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.setBackground(new Color(246, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();

        // Pannello superiore
        JPanel panelTop = new JPanel(new GridBagLayout());
        panelTop.setBackground(new Color(0, 50, 73));
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.weightx = 1;
        gbc.weighty = 0.1;
        contentPane.add(panelTop, gbc);

        // Pannello sinistro scrollabile
        panelLeft = new JPanel(new GridBagLayout());
        panelLeft.setBackground(new Color(246, 248, 255));
        //Funzione che permette di creare le card che visualizzano le transazioni
        showTransactions();
        JScrollPane scrollPane = new JScrollPane(panelLeft);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        //Metodo per modificare la barra per scrollare la pagina
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

        // Pannello destro fisso
        panelRight = new JPanel(new GridBagLayout());
        panelRight.setBackground(new Color(246, 248, 255));
        gbc.gridx = 1; 
        gbc.gridy = 1; 
        gbc.weightx = 0.5; 
        gbc.insets = new Insets(20, 10, 20, 20); 
        contentPane.add(panelRight, gbc);

        // Dichiarazione dei componenti per il pannello superiore
        //Creazione label 'Le tue spese'
        JLabel shoppingLabel = new JLabel("Le tue spese");
        shoppingLabel.setForeground(new Color(246, 248, 255));
        //Crazione label titolo Saving Money Unina
        JLabel titleSmu = new JLabel("S.M.U.");
        titleSmu.setForeground(Color.WHITE);
        if (fontExtraBold != null) {
            shoppingLabel.setFont(fontExtraBold);
            titleSmu.setFont(fontRegular);
        }

        //Creazione button che contiene il logo Unina
        JButton logoButton = new JButton();
        logoButton.setBackground(null);
        logoButton.setIcon(iconUnina);
        logoButton.setContentAreaFilled(false);
        logoButton.setOpaque(false);
        logoButton.setBorderPainted(false);
        logoButton.setBorder(null);
        logoButton.setFocusPainted(false);

        //Creazione button che contiene il logo Home
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

        // Configurazione per logoButton a sinistra di titoloSmu
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 15, 0, 0);
        panelTop.add(logoButton, gbc);

        // Configurazione per il titleSmu a sinistra di shoppingLabel
        gbc.gridx = 2;
        panelTop.add(titleSmu, gbc);

        // Aggiunge spazio di espansione a destra per mantenere shoppingLabel centrata
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelTop.add(Box.createHorizontalGlue(), gbc);

        // Configurazione per la shoppingLabel al centro
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        panelTop.add(shoppingLabel, gbc);

        // Aggiunge spazio di espansione a destra per mantenere shoppingLabel centrata
        gbc.gridx = 5;
        gbc.weightx = 1.0; // Bilancia lo spazio extra a destra
        gbc.fill = GridBagConstraints.EAST;
        panelTop.add(Box.createHorizontalGlue(), gbc);

        // Configurazione per buttonHome
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.insets = new Insets(0, 20, 0, 15);
        panelTop.add(buttonHome, gbc);


        /**GRAFICO*/
        // Array contenente i nomi dei mesi
        String[] mesi = {"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno",
                "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"};


        // Creazione della JComboBox utilizzando l'array dei mesi
        JComboBox<String> monthsComboBox = new JComboBox<>(mesi);

        // Imposta un'opzione di default
        int selectedIndex = 0;
        // Calcola il numero del mese come stringa, aggiungendo uno zero davanti se necessario
        monthNumber = String.format("%02d", selectedIndex + 1);
        // Ottiene l'anno corrente
        currentYear = String.valueOf(LocalDate.now().getYear());
        // Combina l'anno e il mese nel formato YYYY-MM
        String yearMonth = currentYear + "-" + monthNumber;
        controller.viewReport(yearMonth);


        //Constraints
        gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        panelRight.add(monthsComboBox, gbc);

        //Creazione delle label e valori per le informazioni delle transazioni mensili
        JLabel maxEntryLabel = new JLabel("Entrata massima: ");
        maxEntryValue = new JLabel(String.valueOf(controller.getReport()[0]) + "€");
        JLabel minEntryLabel = new JLabel("Entrata minima: ");
        minEntryValue = new JLabel(String.valueOf(controller.getReport()[1]) + "€");
        JLabel avgEntryLabel = new JLabel("Entrata media: ");
        avgEntryValue = new JLabel(String.format("%.2f", controller.getReport()[2]) + "€");
        JLabel maxOutLabel = new JLabel("Uscita massima: ");
        maxOutValue = new JLabel(String.valueOf(controller.getReport()[3]) + "€");
        JLabel minOutLabel = new JLabel("Uscita minima: ");
        minOutValue = new JLabel(String.valueOf(controller.getReport()[4]) + "€");
        JLabel avgOutLabel = new JLabel("Uscita media: ");
        avgOutValue = new JLabel(String.format("%.2f", controller.getReport()[5]) + "€");
        double totalMonthlySentValue = controller.totalMonthlySent(controller.getSelectedBankAccount(), yearMonth);
        double totalMonthlyReceivedValue = controller.totalMonthlyReceived(controller.getSelectedBankAccount(), yearMonth);
        JLabel totalSentLabel = new JLabel("Totale inviato: ");
        JLabel totalReceivedLabel = new JLabel("Totale ricevuto: ");
        totalSentValue = new JLabel(String.format("%.2f", totalMonthlySentValue) + "€");
        totaleReceveidValue = new JLabel(String.format("%.2f", totalMonthlyReceivedValue) + "€");

        if (fontRegularBoldSmall != null) {
            maxEntryLabel.setFont(fontRegularBoldSmall);
            minEntryLabel.setFont(fontRegularBoldSmall);
            avgEntryLabel.setFont(fontRegularBoldSmall);
            maxOutLabel.setFont(fontRegularBoldSmall);
            minOutLabel.setFont(fontRegularBoldSmall);
            avgOutLabel.setFont(fontRegularBoldSmall);
            totalSentLabel.setFont(fontRegularBoldSmall);
            totalReceivedLabel.setFont(fontRegularBoldSmall);
        }
        if (fontRegularSmall != null) {
            maxEntryValue.setFont(fontRegularSmall);
            minEntryValue.setFont(fontRegularSmall);
            avgEntryValue.setFont(fontRegularSmall);
            maxOutValue.setFont(fontRegularSmall);
            minOutValue.setFont(fontRegularSmall);
            avgOutValue.setFont(fontRegularSmall);
            totalSentValue.setFont(fontRegularSmall);
            totaleReceveidValue.setFont(fontRegularSmall);
        }


        //Creazione dati per il grafico
        DefaultPieDataset dataset = new DefaultPieDataset();

        dataset.setValue("Entrate", totalMonthlyReceivedValue);
        dataset.setValue("Uscite", totalMonthlySentValue);


        JFreeChart chart = ChartFactory.createPieChart(
                "Rapporto Entrate/Uscite", // chart title
                dataset, // data
                true, // include legend
                true,
                false);
        chart.setBackgroundPaint(new Color(246, 248, 255)); // Cambia il colore di sfondo dell'intero grafico


        //Creazione PiePlot
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("Entrate", new Color(0, 50, 73)); // Colore entrate
        plot.setSectionPaint("Uscite", new Color(145, 57, 57)); // Colore uscite
        plot.setExplodePercent("Entrate", 0.1); // Evidenzia le entrate
        plot.setBackgroundPaint(new Color(246, 248, 255));
        plot.setOutlinePaint(new Color(246, 248, 255));
        plot.setLabelGenerator(null);//nasconde le etichette sul grafico


        //Creazione del PanelChart
        chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(new Color(246, 248, 255));
        chartPanel.setPreferredSize(new Dimension(350, 350));


        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 1;
        gbc.gridx = 0;
        panelRight.add(maxEntryLabel, gbc);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridy = 1;
        gbc.gridx = 1;
        panelRight.add(maxEntryValue, gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 2;
        gbc.gridx = 0;
        panelRight.add(minEntryLabel, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 2;
        gbc.gridx = 1;
        panelRight.add(minEntryValue, gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 3;
        gbc.gridx = 0;
        panelRight.add(avgEntryLabel, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 3;
        gbc.gridx = 1;
        panelRight.add(avgEntryValue, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.gridy = 4;
        gbc.gridx = 0;
        panelRight.add(maxOutLabel, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 4;
        gbc.gridx = 1;
        panelRight.add(maxOutValue, gbc);

        gbc = new GridBagConstraints();
        gbc.weightx = 0.6;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 5;
        gbc.gridx = 0;
        panelRight.add(minOutLabel, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 5;
        gbc.gridx = 1;
        panelRight.add(minOutValue, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 6;
        gbc.gridx = 0;
        panelRight.add(avgOutLabel, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 6;
        gbc.gridx = 1;
        panelRight.add(avgOutValue, gbc);

        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 7;
        gbc.gridx = 0;
        panelRight.add(totalSentLabel, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 7;
        gbc.gridx = 1;
        panelRight.add(totalSentValue, gbc);

        gbc = new GridBagConstraints();
        gbc.weightx = 0.6;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 8;
        gbc.gridx = 0;
        panelRight.add(totalReceivedLabel, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 8;
        gbc.gridx = 1;
        panelRight.add(totaleReceveidValue, gbc);
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 9;
        gbc.gridx = 0;
        panelRight.add(chartPanel, gbc);


        // Listener per gestire la selezione dell'utente
        monthsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ottieni l'indice del mese selezionato (0 per Gennaio, 1 per Febbraio, ecc.)
                int selectedIndex = monthsComboBox.getSelectedIndex();

                // Calcola il numero del mese come stringa, aggiungendo uno zero davanti se necessario
                // Gli indici partono da 0 quindi aggiungi 1 per ottenere il numero corretto del mese
                monthNumber = String.format("%02d", selectedIndex + 1);

                // Ottieni l'anno corrente
                currentYear = String.valueOf(LocalDate.now().getYear());

                // Combina l'anno e il mese nel formato YYYY-MM
                String yearMonth = currentYear + "-" + monthNumber;


                // Ora chiama la funzione viewReport con l'anno e il mese selezionati
                controller.viewReport(yearMonth);




                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridy = 0;
                gbc.gridx = 0;
                panelRight.add(monthsComboBox, gbc);


                //Rimuove i valori del mese precedente per aggiungere i valori aggiornati del nuovo mese
                if(maxEntryValue !=null)
                    panelRight.remove(maxEntryValue);
                if(avgEntryValue !=null)
                    panelRight.remove(avgEntryValue);
                if(minEntryValue !=null)
                    panelRight.remove(minEntryValue);
                if(maxOutValue !=null)
                    panelRight.remove(maxOutValue);
                if(minOutValue !=null)
                    panelRight.remove(minOutValue);
                if(avgOutValue !=null)
                    panelRight.remove(avgOutValue);
                if(totalSentValue !=null)
                    panelRight.remove(totalSentValue);
                if(totaleReceveidValue !=null)
                    panelRight.remove(totaleReceveidValue);

                //Aggiorno i valori
                maxEntryValue = new JLabel(String.valueOf(controller.getReport()[0]) + "€");

                minEntryValue = new JLabel(String.valueOf(controller.getReport()[1]) + "€");

                avgEntryValue = new JLabel(String.format("%.2f", controller.getReport()[2]) + "€");

                maxOutValue = new JLabel(String.valueOf(controller.getReport()[3]) + "€");

                minOutValue = new JLabel(String.valueOf(controller.getReport()[4]) + "€");

                avgOutValue = new JLabel(String.format("%.2f", controller.getReport()[5]) + "€");

                double totaleInviatoMensile = controller.totalMonthlySent(controller.getSelectedBankAccount(), yearMonth);
                double totaleRicevutoMensile = controller.totalMonthlyReceived(controller.getSelectedBankAccount(), yearMonth);

                totalSentValue = new JLabel(String.format("%.2f", totaleInviatoMensile) + "€");
                totaleReceveidValue = new JLabel(String.format("%.2f", totaleRicevutoMensile) + "€");

                if (fontRegularSmall != null) {
                    maxEntryValue.setFont(fontRegularSmall);
                    minEntryValue.setFont(fontRegularSmall);
                    avgEntryValue.setFont(fontRegularSmall);
                    maxOutValue.setFont(fontRegularSmall);
                    minOutValue.setFont(fontRegularSmall);
                    avgOutValue.setFont(fontRegularSmall);
                    totalSentValue.setFont(fontRegularSmall);
                    totaleReceveidValue.setFont(fontRegularSmall);
                }

                //Aggiornamento del Chart per riposizionarlo
                DefaultPieDataset dataset = new DefaultPieDataset();


                dataset.setValue("Entrate", totaleRicevutoMensile);
                dataset.setValue("Uscite", totaleInviatoMensile);

                JFreeChart chart = ChartFactory.createPieChart(
                        "Rapporto Entrate/Uscite", // chart title
                        dataset, // data
                        true, // include legend
                        true,
                        false);
                chart.setBackgroundPaint(new Color(246, 248, 255)); // Cambia il colore di sfondo dell'intero grafico


                PiePlot plot = (PiePlot) chart.getPlot();
                plot.setSectionPaint("Entrate", new Color(0, 50, 73)); // Colore verde
                plot.setSectionPaint("Uscite", new Color(145, 57, 57)); // Colore rosso
                plot.setExplodePercent("Entrate", 0.1); // Evidenzia le entrate
                plot.setBackgroundPaint(new Color(246, 248, 255));
                plot.setOutlinePaint(new Color(246, 248, 255));
                plot.setLabelGenerator(null);//nasconde le etichette sul grafico

                // Se 'chartPanel' esiste già, rimuovilo
                if (chartPanel != null) {
                    panelRight.remove(chartPanel);
                }

                chartPanel = new ChartPanel(chart);
                chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
                chartPanel.setBackground(new Color(246, 248, 255));
                chartPanel.setPreferredSize(new Dimension(350, 350));

                gbc = new GridBagConstraints();


                gbc.anchor = GridBagConstraints.WEST;
                gbc.gridy = 1;
                gbc.gridx = 1;
                panelRight.add(maxEntryValue, gbc);
                gbc.anchor = GridBagConstraints.WEST;
                gbc.gridy = 2;
                gbc.gridx = 1;
                panelRight.add(minEntryValue, gbc);
                gbc.anchor = GridBagConstraints.WEST;
                gbc.gridy = 3;
                gbc.gridx = 1;
                panelRight.add(avgEntryValue, gbc);


                gbc.insets = new Insets(10, 0, 0, 0);
                gbc.anchor = GridBagConstraints.WEST;
                gbc.gridy = 4;
                gbc.gridx = 1;
                panelRight.add(maxOutValue, gbc);

                gbc = new GridBagConstraints();
                gbc.weightx = 0.6;
                gbc.anchor = GridBagConstraints.WEST;
                gbc.gridy = 5;
                gbc.gridx = 1;
                panelRight.add(minOutValue, gbc);

                gbc.anchor = GridBagConstraints.WEST;
                gbc.gridy = 6;
                gbc.gridx = 1;
                panelRight.add(avgOutValue, gbc);

                gbc.insets = new Insets(10, 0, 0, 0);
                gbc.anchor = GridBagConstraints.WEST;
                gbc.gridy = 7;
                gbc.gridx = 1;
                panelRight.add(totalSentValue, gbc);

                gbc = new GridBagConstraints();
                gbc.weightx = 0.6;
                gbc.anchor = GridBagConstraints.WEST;
                gbc.gridy = 8;
                gbc.gridx = 1;
                panelRight.add(totaleReceveidValue, gbc);
                gbc.insets = new Insets(10, 0, 10, 0);
                gbc.anchor = GridBagConstraints.CENTER;
                gbc.gridy = 9;
                gbc.gridx = 0;
                panelRight.add(chartPanel, gbc);

                // Aggiorna il frame per mostrare il nuovo chart
                panelRight.validate();
                panelRight.repaint();

            }});

        setContentPane(contentPane);
    }

    public void showTransactions(){

        if(!controller.getTransactions().isEmpty()){
            int y = 0;
            for (Transaction transaction : controller.getTransactions()) {
                //PanelCard che contiene le informazioni della transazione
                RoundedPanel cardTransaction = new RoundedPanel(15, new Color(222, 226, 230));
                cardTransaction.setLayout(new GridBagLayout());

                //Creazione label in base al tipo di transazione uscita/entrata
                JLabel sentLabelValue = new JLabel(String.format("Hai inviato %.2f€ a", transaction.getAmount()));
                JLabel receivedLabelValue = new JLabel(String.format("Hai ricevuto %.2f€ da", transaction.getAmount()));


                controller.selectNameAndSurnameByIban(transaction.getIban());
                //Creazione label che contiene le credenziali dell'iban che ha ricevuto/inviato
                JLabel credentialsLabel = new JLabel(controller.getCredentialsIban());

                //Creazione label che contiene il tipo di categoria entrata/uscita
                JLabel catLabel = new JLabel("Categoria: ");
                //Applica la categoria in base al tipo di transazione
                if(transaction.getEntryCategory()!=null)
                    catLabel.setText(catLabel.getText()+transaction.getEntryCategory());
                else
                    catLabel.setText(catLabel.getText()+transaction.getExitCategory());

                //Creazione label che contiene la data e l'ora della transazione
                JLabel dateTimeLabel = new JLabel(transaction.getDateTransaction() + ", " + transaction.getTimeTransaction());

                //Creazione label che contiene la causale da cliccare della transazione
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
                    sentLabelValue.setFont(fontRegularBold);
                    receivedLabelValue.setFont(fontRegularBold);
                    credentialsLabel.setFont(fontRegularBoldSmall);
                }
                if(fontRegularSmall != null){
                    dateTimeLabel.setFont(fontRegularSmall);
                    causalLabel.setFont(fontRegularSmall);
                    catLabel.setFont(fontRegularSmall);
                }


                // Aggiungi le etichette al cardBank
                GridBagConstraints gbc = new GridBagConstraints();

                //Applica le informazioni in base al tipo di transazione entrata/uscita
                if(transaction.getTypeTransaction().equals("Invia a")) {
                    sentLabelValue.setForeground(new Color(145, 57, 57));
                    gbc.insets = new Insets(5, 5, 5, 5);
                    gbc.weightx = 1.0;
                    gbc.anchor = GridBagConstraints.NORTHWEST;
                    cardTransaction.add(sentLabelValue, gbc);

                    gbc.insets = new Insets(5, 5, 5, 5);
                    gbc.anchor = GridBagConstraints.NORTHWEST;
                    gbc.gridy = 1;
                    cardTransaction.add(credentialsLabel,gbc);
                }
                else {
                    receivedLabelValue.setForeground(new Color(37, 89, 87));
                    gbc.insets = new Insets(5, 5, 5, 5);
                    gbc.weightx = 1.0;
                    gbc.anchor = GridBagConstraints.NORTHWEST;
                    cardTransaction.add(receivedLabelValue, gbc);

                    gbc.insets = new Insets(5, 5, 5, 5);
                    gbc.anchor = GridBagConstraints.NORTHWEST;
                    gbc.gridy = 1;
                    cardTransaction.add(credentialsLabel,gbc);
                }

                gbc = new GridBagConstraints();
                gbc.insets = new Insets(20, 5, 5, 5);
                gbc.anchor = GridBagConstraints.SOUTHWEST;
                gbc.gridy = 2;
                cardTransaction.add(catLabel, gbc);
                gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 10);
                gbc.anchor = GridBagConstraints.SOUTHWEST;
                gbc.gridy = 3;
                cardTransaction.add(dateTimeLabel, gbc);
                gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 10);
                gbc.anchor = GridBagConstraints.SOUTHEAST;
                gbc.gridx = 1;
                gbc.gridy = 3;
                cardTransaction.add(causalLabel, gbc);



                // Aggiungi il cardBank allo scrollPane
                gbc = new GridBagConstraints();
                gbc.insets = new Insets(20, 5, 0, 5);
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.gridy = y++;
                gbc.gridx = 0;
                gbc.weightx = 1.0;
                gbc.weighty = 1.0;
                panelLeft.add(cardTransaction, gbc);

            }
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


