package GUI;

import CONTROLLER.Controller;
import ENTITY.Collection;
import EXCEPTIONS.MyExc;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;

public class CollectionPickViewGUI extends JFrame {

    private Controller controller;

    //Dichiarazioni Variabili per i Font
    private Font fontRegular;
    private Font fontRegularBold;
    private Font fontBold;
    private Font fontExtraBold;
    private Font fontRegularSmall;
    private JButton homeButton;

    //Dichiarazione del Panel usato sia nel costruttore che nella funzione
    private JPanel panelCenter;

    //Dichiarazione Icone
    ImageIcon iconAddCollection = new ImageIcon(CollectionPickViewGUI.class.getResource("/IMG/add-folder.png"));
    ImageIcon iconHome = new ImageIcon(CollectionPickViewGUI.class.getResource("/IMG/home.png"));
    ImageIcon iconUnina = new ImageIcon(CollectionPickViewGUI.class.getResource("/IMG/unina.png"));
    ImageIcon iconTrash = new ImageIcon(CollectionPickViewGUI.class.getResource("/IMG/trash.png"));
    ImageIcon iconDelete = new ImageIcon(CollectionPickViewGUI.class.getResource("/IMG/delete.png"));

    //Dichiarazione dei pulsanti da usare nei JOptionPane
    private Object[] optionsAdd = {"Crea", "Annulla"};
    private Object[] optionsDelete = {"Elimina", "Annulla"};


    public CollectionPickViewGUI(Controller controller){
        this.controller = controller;
        setTitle("Seleziona raccolta");
        setSize(1400, 800);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        fontBold();
        fontRegular();
        fontExtraBold();
        fontRegularSmall();
        fontRegularBold();


        // Aggiungo il content Panel
        JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.setBackground(new Color(246, 248, 255));

        // Constraints
        GridBagConstraints gbc = new GridBagConstraints();


        //Creazione del Panel Header
        JPanel panelTop = new JPanel(new GridBagLayout());
        panelTop.setBackground(new Color(0, 50, 73));
        panelTop.setOpaque(true);

        JLabel titleFrame = new JLabel("Seleziona raccolta");
        if (fontExtraBold != null)
            titleFrame.setFont(fontExtraBold);
        titleFrame.setForeground(new Color(246, 248, 255));
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panelTop.add(titleFrame, gbc);

        //Creazione button che contiene il logo Unina
        JButton logoButton = new JButton();
        logoButton.setBackground(null);
        logoButton.setIcon(iconUnina);
        logoButton.setContentAreaFilled(false);
        logoButton.setOpaque(false);
        logoButton.setBorderPainted(false);
        logoButton.setBorder(null);
        logoButton.setFocusPainted(false);

        //Creazione della label SavingMoneyUnina
        JLabel titleSmuLabel = new JLabel("S.M.U.");
        titleSmuLabel.setForeground(Color.WHITE);
        if (fontExtraBold != null) {
            titleSmuLabel.setFont(fontRegular);
        }

        //Creazione button Home
        homeButton = new JButton();
        homeButton.setIcon(iconHome);
        homeButton.setBackground(null);
        homeButton.setOpaque(true);
        homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia il cursore per indicare che è cliccabile
        homeButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        homeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.showHomeView(controller.getSelectedBankAccount());
            }
        });
        gbc = new GridBagConstraints();


        // Configurazione per logoButton a sinistra di titleSmuLabel
        gbc.gridx = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 15, 0, 0);
        panelTop.add(logoButton, gbc);

        // Configurazione per il titleSmuLabel a sinistra di titleFrame
        gbc.gridx = 2;
        panelTop.add(titleSmuLabel, gbc);

        gbc = new GridBagConstraints();
        // Infine, aggiungi spazio di espansione a destra per mantenere titleFrame centrata
        gbc.gridx = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelTop.add(Box.createHorizontalGlue(), gbc);

        // Configurazione per la titleFrame al centro
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        panelTop.add(titleFrame, gbc);

        //Aggiunge spazio di espansione a destra per mantenere titleFrame centrata
        gbc.gridx = 5;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.EAST;
        panelTop.add(Box.createHorizontalGlue(), gbc);

        // Configurazione per homeButton a destra della titleFrame
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        panelTop.add(homeButton, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.05;
        contentPane.add(panelTop, gbc);

        //Creazione di un JPanel da inserire al centro della pagina
        panelCenter = new JPanel(new GridBagLayout());
        panelCenter.setBackground(new Color(246, 248, 255));
        panelCenter.setOpaque(true);


        //Funzione che mostra tutte le collections in delle Card
        showCollections();

        // Creazione dello JScrollPane che conterrà panelCenter
        JScrollPane scrollPane = new JScrollPane(panelCenter);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Impostazioni per gbc in modo che scrollPane si espanda correttamente
        gbc.gridx = 0;
        gbc.gridy = 1; // Assicurati che questo valore di gridy non confligga con altri componenti
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.95; // Assegna più spazio a panelCenter
        gbc.insets = new Insets(0, 0, 0, 0);
        // Aggiungi scrollPane a contentPane invece di panelCenter
        contentPane.add(scrollPane, gbc);
        setContentPane(contentPane);
    }

    public void showCollections(){

        //Controllo se ci sono collezioni all'interno dell'ArrayList, se non ci sono mostra la card per crearne una
        if (!controller.getCollections().isEmpty()){
            int y = 2;
            int x = 0;
            for (Collection collection : controller.getCollections()) {
                //If per controllare se la riga è finita, deve ricominciare accapo
                if (x == 3)
                    x = 0;

                //Card che contiene le informazioni di una collection
                RoundedPanel cardBank = new RoundedPanel(15, new Color(222, 226, 230));

                //Creazione label 'Nome: '
                JLabel nameLabel = new JLabel("Nome: ");
                if (fontRegularBold != null)
                    nameLabel.setFont(fontRegularBold);

                //Creazione label che contiene il nome della collection
                JLabel nameCollectionLabel = new JLabel(collection.getNameCollection());
                if (fontRegular != null)
                    nameCollectionLabel.setFont(fontRegular);

                //Creazione label 'Totale: '
                JLabel totalLabel = new JLabel("Totale: ");
                if(fontRegularBold!=null)
                    totalLabel.setFont(fontRegularBold);

                //Variabile che rappresenta il totale speso per una raccolta
                double totalCollection = controller.selectSumOfCollections(collection.getNameCollection());

                //Creazione label che contiene il valore totale
                JLabel totalTransactions = new JLabel(String.valueOf(totalCollection)+"€");
                if(fontRegular!=null)
                    totalTransactions.setFont(fontRegular);

                //Creazione label 'Descrizione' da poter cliccare per visualizzare la descrizione della raccolta
                JLabel descriptionLabel = new JLabel("Descrizione");

                if(fontRegular!=null)
                    descriptionLabel.setFont(fontRegular);
                descriptionLabel.setForeground(Color.GRAY);
                //Metodo per quando clicco sulla label Descrizione
                descriptionLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JOptionPane.showMessageDialog(
                                null,
                                collection.getDescription(),
                                "Descrizione",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                });

                //Creazione button per eliminare una raccolta
                JButton deleteButton = new JButton();
                deleteButton.setBackground(null);
                deleteButton.setIcon(iconTrash);
                deleteButton.setContentAreaFilled(false);
                deleteButton.setOpaque(false);
                deleteButton.setBorderPainted(false);
                deleteButton.setBorder(null);
                deleteButton.setFocusPainted(false);
                deleteButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int result = JOptionPane.showOptionDialog(
                                null,
                                "Sei sicuro di voler eliminare la raccolta "+collection.getNameCollection() , // Messaggio
                                "Elimina raccolta", // Titolo
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE, // Tipo di messaggio
                                iconDelete, // Icona personalizzata
                                optionsDelete, // Array contenente le etichette dei pulsanti
                                optionsDelete[0] // Opzione di default
                        );
                        if (result == JOptionPane.YES_OPTION) {
                            controller.deleteCollection(collection.getNameCollection());
                        }

                    }
                });

                //Panel Card con layout GroupLayout
                GroupLayout glCollection = new GroupLayout(cardBank);
                cardBank.setLayout(glCollection);

                //Indica al GroupLayout di creare automaticamente spazi tra i componenti che gestisce
                glCollection.setAutoCreateGaps(true);
                //indica al GroupLayout di inserire automaticamente degli spazi tra i componenti e i bordi del contenitore che li ospita
                glCollection.setAutoCreateContainerGaps(true);

                //Creazione del gruppo orizzontale di una singola card
                GroupLayout.SequentialGroup hGroup = glCollection.createSequentialGroup();

                // Aggiungi nameLabel e nameCollectionLabel allo stesso gruppo parallelo per averli sulla stessa riga
                hGroup.addGroup(glCollection.createParallelGroup()
                        .addComponent(nameLabel).addComponent(totalLabel).addComponent(descriptionLabel));
                hGroup.addGroup(glCollection.createParallelGroup()
                        .addComponent(nameCollectionLabel).addComponent(totalTransactions).addComponent(deleteButton));
                glCollection.setHorizontalGroup(hGroup);

                //Creazione del gruppo verticale di una singola card
                GroupLayout.SequentialGroup vGroup = glCollection.createSequentialGroup();

                // Crea un gruppo parallelo per nameLabel e nameCollectionLabel affinché siano allineati verticalmente
                vGroup.addGroup(glCollection.createParallelGroup().
                        addComponent(nameLabel).addComponent(nameCollectionLabel));
                // Crea un gruppo parallelo per nameLabel e nameCollectionLabel affinché siano allineati verticalmente
                vGroup.addGroup(glCollection.createParallelGroup().
                        addComponent(totalLabel).addComponent(totalTransactions));
                // Aggiungi il deleteButton in un nuovo gruppo parallelo per posizionarlo sotto
                vGroup.addGroup(glCollection.createParallelGroup()
                        .addComponent(descriptionLabel).addComponent(deleteButton));
                glCollection.setVerticalGroup(vGroup);

                //Metodo per quando viene cliccata una card di una collection per mostrarne la page
                cardBank.setCursor(new Cursor(Cursor.HAND_CURSOR));
                cardBank.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        controller.showCollectionView(collection);
                    }
                });

                //Constraints
                GridBagConstraints gbc = new GridBagConstraints();

                gbc.insets = new Insets(10, 20, 10, 20);
                gbc.gridy = y;
                gbc.gridx = x;
                gbc.weightx = 0.3;
                gbc.fill = GridBagConstraints.BOTH;

                panelCenter.add(cardBank, gbc);
                //Aggiunta una card passo alla colonna successiva
                x++;
                //Se sono arrivato all'ultima colonna, passo alla riga succesiva
                if(x == 3)
                    y++;

                // Controlla se l'elemento corrente è l'ultimo dell'ArrayList
                if (collection.equals(controller.getCollections().get(controller.getCollections().size() - 1))) {
                    if (x == 3){
                        x = 0;
                    }

                    //Creazione Panel che contiene la funzione per creare una raccolta
                    JPanel addCollection = new JPanel();
                    addCollection.setBackground(new Color(246, 248, 255));
                    addCollection.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 84, 122)));
                    addCollection.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    addCollection.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            addCollection.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 84, 122)));
                        }

                        public void mouseExited(MouseEvent e) {
                            addCollection.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 84, 122)));
                        }
                    });

                    //Creazione label 'Crea Raccolta'
                    JLabel createCollection = new JLabel("Crea Raccolta +");
                    if (fontRegularBold != null)
                        createCollection.setFont(fontRegularBold);

                    createCollection.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            addCollection.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 84, 122)));
                        }

                        public void mouseExited(MouseEvent e) {
                            addCollection.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 84, 122)));
                        }
                    });

                    //Metodo per creare una raccolta quando premo sulla label Crea Raccolta
                    createCollection.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e){

                            //Panel che contiene le Field per la Raccolta da creare
                            JPanel addCollectionPanel = new JPanel(new GridBagLayout());
                            addCollectionPanel.setBackground(new Color(246, 248, 255));

                            //Creazione label 'Nome'
                            JLabel nameCollectionLabel = new JLabel("Nome");
                            nameCollectionLabel.setForeground(new Color(0, 84, 122));

                            //Creazione Field per inserire il nome della Raccolta
                            JTextField nameCollectionField = new JTextField();
                            nameCollectionField.setBorder(new MatteBorder(0,0,2,0, new Color(0, 84, 122)));
                            nameCollectionField.setBackground(new Color(246, 248, 255));

                            //Metodo per inserire un massimo di caratteri al nome
                            PlainDocument doc = (PlainDocument) nameCollectionField.getDocument();
                            doc.setDocumentFilter(new DocumentFilter() {
                                private int maxChars = 20;

                                @Override
                                public void replace(DocumentFilter.FilterBypass fb, int offs, int length, String str, AttributeSet a)
                                        throws BadLocationException {
                                    String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                                    int totalLength = text.length() - length + str.length();
                                    if (totalLength <= maxChars) {
                                        super.replace(fb, offs, length, str, a);
                                    } else {
                                        Toolkit.getDefaultToolkit().beep();
                                    }
                                }

                                @Override
                                public void insertString(DocumentFilter.FilterBypass fb, int offs, String str, AttributeSet a)
                                        throws BadLocationException {
                                    String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                                    int totalLength = text.length() + str.length();
                                    if (totalLength <= maxChars) {
                                        super.insertString(fb, offs, str, a);
                                    } else {
                                        Toolkit.getDefaultToolkit().beep();
                                    }
                                }
                            });

                            //Creazione label 'Descrizione'
                            JLabel descriptionCollectionLabel = new JLabel("Descrizione");
                            descriptionCollectionLabel.setForeground(new Color(0, 84, 122));

                            //Creazione TextArea per inserire la descrizione della raccolta
                            JTextArea descriptionCollectionArea = new JTextArea();
                            descriptionCollectionArea.setBorder(new MatteBorder(2,2,2,2, new Color(0, 84, 122)));
                            descriptionCollectionArea.setBackground(new Color(246, 248, 255));

                            //Metodo per inserire un massimo di caratteri alla descrizione
                            doc = (PlainDocument) descriptionCollectionArea.getDocument();
                            doc.setDocumentFilter(new DocumentFilter() {
                                private int maxChars = 140;

                                @Override
                                public void replace(DocumentFilter.FilterBypass fb, int offs, int length, String str, AttributeSet a)
                                        throws BadLocationException {
                                    String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                                    int totalLength = text.length() - length + str.length();
                                    if (totalLength <= maxChars) {
                                        super.replace(fb, offs, length, str, a);
                                    } else {
                                        Toolkit.getDefaultToolkit().beep();
                                    }
                                }

                                @Override
                                public void insertString(DocumentFilter.FilterBypass fb, int offs, String str, AttributeSet a)
                                        throws BadLocationException {
                                    String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                                    int totalLength = text.length() + str.length();
                                    if (totalLength <= maxChars) {
                                        super.insertString(fb, offs, str, a);
                                    } else {
                                        Toolkit.getDefaultToolkit().beep();
                                    }
                                }
                            });
                            descriptionCollectionArea.setRows(5); // Imposta il numero di righe
                            descriptionCollectionArea.setColumns(20); // Imposta il numero di colonne
                            descriptionCollectionArea.setLineWrap(true); // Abilita l'andare a capo automatico
                            descriptionCollectionArea.setWrapStyleWord(true); // Il testo va a capo per intere parole
                            if(fontRegularBold!=null){
                                nameCollectionLabel.setFont(fontRegularBold);
                                descriptionCollectionLabel.setFont(fontRegularBold);
                            }
                            if(fontRegular!=null){
                                nameCollectionField.setFont(fontRegular);
                                descriptionCollectionArea.setFont(fontRegular);
                            }

                            //Constraints per inserire le label e field all'interno del panel
                            GridBagConstraints gbc = new GridBagConstraints();
                            gbc.fill = GridBagConstraints.BOTH;
                            gbc.gridx = 0;
                            gbc.gridy = 0;
                            addCollectionPanel.add(nameCollectionLabel, gbc);

                            gbc.gridx = 0;
                            gbc.gridy = 1;
                            gbc.gridwidth = 2;
                            addCollectionPanel.add(nameCollectionField, gbc);

                            descriptionCollectionArea.setRows(5);
                            gbc.gridx = 0;
                            gbc.gridy = 2;
                            gbc.gridwidth = 1;
                            addCollectionPanel.add(descriptionCollectionLabel, gbc);

                            gbc.gridx = 0;
                            gbc.gridy = 3;
                            gbc.gridwidth = 2;
                            gbc.insets = new Insets(5, 10, 5, 10);
                            addCollectionPanel.add(descriptionCollectionArea, gbc);


                            UIManager.put("OptionPane.background", new Color(246,248,255)); // Colore di sfondo
                            UIManager.put("Panel.background", new Color(246,248,255)); // Colore di sfondo per il pannello interno


                            // Mostra il JOptionPane con i JTextField inseriti
                            int result = JOptionPane.showOptionDialog(
                                    null, // Componente padre
                                    addCollectionPanel, // Messaggio
                                    "Crea Raccolta", // Titolo
                                    JOptionPane.YES_NO_CANCEL_OPTION,
                                    JOptionPane.QUESTION_MESSAGE, // Tipo di messaggio
                                    iconAddCollection, // Icona personalizzata
                                    optionsAdd, // Array contenente le etichette dei pulsanti
                                    optionsAdd[0] // Opzione di default
                            );
                            //Se il pulsante che premo conferma la creazione della Raccolta
                            if (result == JOptionPane.YES_OPTION) {
                                try {
                                    controller.addCollection(nameCollectionField.getText(), descriptionCollectionArea.getText());
                                } catch (MyExc ex) {
                                    throw new RuntimeException(ex);
                                }
                                //Aggiorno la pagina
                                controller.showCollectionPickView();
                            }
                        }
                    });

                    //Card con layout GroupLayout che contiene la funzione per creare una collection
                    GroupLayout glAddCollection = new GroupLayout(addCollection);
                    addCollection.setLayout(glAddCollection);

                    //Indica al GroupLayout di creare automaticamente spazi tra i componenti che gestisce
                    glAddCollection.setAutoCreateGaps(true);
                    //indica al GroupLayout di inserire automaticamente degli spazi tra i componenti e i bordi del contenitore che li ospita
                    glAddCollection.setAutoCreateContainerGaps(true);

                    //Creazione del gruppo orizzontale di una singola card
                    GroupLayout.SequentialGroup hGroup2 = glAddCollection.createSequentialGroup();

                    //Aggiunge orizzontalmente alla card la label 'Crea Raccolta'
                    hGroup2.addGroup(glAddCollection.createParallelGroup().
                            addComponent(createCollection));
                    glAddCollection.setHorizontalGroup(hGroup2);

                    //Creazione del gruppo verticale di una singola card
                    GroupLayout.SequentialGroup vGroup2 = glAddCollection.createSequentialGroup();

                    //Aggiunge verticalmente alla card la label 'Crea Raccolta'
                    vGroup2.addGroup(glAddCollection.createParallelGroup(GroupLayout.Alignment.BASELINE).
                            addComponent(createCollection));
                    glAddCollection.setVerticalGroup(vGroup2);

                    //Aggiunta della Card per aggiungere una raccolta nello scrollPane
                    gbc = new GridBagConstraints();

                    gbc.insets = new Insets(40, 20, 40, 20);
                    gbc.weightx = 0.3;
                    gbc.gridy = y;
                    gbc.gridx = x;
                    panelCenter.add(addCollection, gbc);
                }
            }
        }
        else {
            JPanel addCollection = new JPanel();
            addCollection.setBackground(new Color(246, 248, 255));
            addCollection.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 50, 73)));
            addCollection.setCursor(new Cursor(Cursor.HAND_CURSOR));
            addCollection.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    addCollection.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 84, 122)));
                }

                public void mouseExited(MouseEvent e) {
                    addCollection.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 84, 122)));
                }
            });

            JLabel createCollection = new JLabel("Crea Raccolta +");
            createCollection.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    addCollection.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 84, 122)));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    addCollection.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 84, 122)));
                }

                @Override
                public void mouseClicked(MouseEvent e){

                    JPanel addCollectionPanel = new JPanel(new GridBagLayout());
                    addCollectionPanel.setBackground(new Color(246, 248, 255));

                    JLabel nameCollectionLabel = new JLabel("Nome");
                    nameCollectionLabel.setForeground(new Color(0, 84, 122));

                    JTextField nameCollectionField = new JTextField();
                    nameCollectionField.setBorder(new MatteBorder(0,0,2,0, new Color(0, 84, 122)));
                    nameCollectionField.setBackground(new Color(246, 248, 255));
                    //Metodo per inserire un massimo di caratteri al nome
                    PlainDocument doc = (PlainDocument) nameCollectionField.getDocument();
                    doc.setDocumentFilter(new DocumentFilter() {
                        private int maxChars = 20;

                        @Override
                        public void replace(DocumentFilter.FilterBypass fb, int offs, int length, String str, AttributeSet a)
                                throws BadLocationException {
                            String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                            int totalLength = text.length() - length + str.length();
                            if (totalLength <= maxChars) {
                                super.replace(fb, offs, length, str, a);
                            } else {
                                Toolkit.getDefaultToolkit().beep();
                            }
                        }

                        @Override
                        public void insertString(DocumentFilter.FilterBypass fb, int offs, String str, AttributeSet a)
                                throws BadLocationException {
                            String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                            int totalLength = text.length() + str.length();
                            if (totalLength <= maxChars) {
                                super.insertString(fb, offs, str, a);
                            } else {
                                Toolkit.getDefaultToolkit().beep();
                            }
                        }
                    });

                    JLabel descriptionCollectionLabel = new JLabel("Descrizione");
                    descriptionCollectionLabel.setForeground(new Color(0, 84, 122));

                    JTextArea descriptionCollectionArea = new JTextArea();
                    descriptionCollectionArea.setBorder(new MatteBorder(2,2,2,2, new Color(0, 84, 122)));
                    descriptionCollectionArea.setBackground(new Color(246, 248, 255));
                    doc = (PlainDocument) descriptionCollectionArea.getDocument();
                    doc.setDocumentFilter(new DocumentFilter() {
                        private int maxChars = 140;

                        @Override
                        public void replace(DocumentFilter.FilterBypass fb, int offs, int length, String str, AttributeSet a)
                                throws BadLocationException {
                            String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                            int totalLength = text.length() - length + str.length();
                            if (totalLength <= maxChars) {
                                super.replace(fb, offs, length, str, a);
                            } else {
                                Toolkit.getDefaultToolkit().beep();
                            }
                        }

                        @Override
                        public void insertString(DocumentFilter.FilterBypass fb, int offs, String str, AttributeSet a)
                                throws BadLocationException {
                            String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                            int totalLength = text.length() + str.length();
                            if (totalLength <= maxChars) {
                                super.insertString(fb, offs, str, a);
                            } else {
                                Toolkit.getDefaultToolkit().beep();
                            }
                        }
                    });
                    descriptionCollectionArea.setRows(5); // Imposta il numero di righe
                    descriptionCollectionArea.setColumns(20); // Imposta il numero di colonne
                    descriptionCollectionArea.setLineWrap(true); // Abilita l'andare a capo automatico
                    descriptionCollectionArea.setWrapStyleWord(true); // Il testo va a capo per intere parole
                    if(fontRegularBold!=null){
                        nameCollectionLabel.setFont(fontRegularBold);
                        descriptionCollectionLabel.setFont(fontRegularBold);
                    }
                    if(fontRegular!=null){
                        nameCollectionField.setFont(fontRegular);
                        descriptionCollectionArea.setFont(fontRegular);
                    }

                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.fill = GridBagConstraints.BOTH;
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    addCollectionPanel.add(nameCollectionLabel, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    gbc.gridwidth = 2;
                    addCollectionPanel.add(nameCollectionField, gbc);

                    descriptionCollectionArea.setRows(5);
                    gbc.gridx = 0;
                    gbc.gridy = 2;
                    gbc.gridwidth = 1;
                    addCollectionPanel.add(descriptionCollectionLabel, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 3;
                    gbc.gridwidth = 2;
                    gbc.insets = new Insets(5, 10, 5, 10);
                    addCollectionPanel.add(descriptionCollectionArea, gbc);



                    UIManager.put("OptionPane.background", new Color(246,248,255)); // Colore di sfondo
                    UIManager.put("Panel.background", new Color(246,248,255)); // Colore di sfondo per il pannello interno


                    // Mostra il JOptionPane con i JTextField inseriti
                    int result = JOptionPane.showOptionDialog(
                            null, // Componente padre
                            addCollectionPanel, // Messaggio
                            "Crea Raccolta", // Titolo
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE, // Tipo di messaggio
                            iconAddCollection, // Icona personalizzata, usa null per l'icona di default
                            optionsAdd, // Array contenente le etichette dei pulsanti
                            optionsAdd[0] // Opzione di default
                    );
                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            controller.addCollection(nameCollectionField.getText(), descriptionCollectionArea.getText());
                        } catch (MyExc ex) {
                            throw new RuntimeException(ex);
                        }
                        controller.showCollectionPickView();
                    }
                }
            });
            if (fontRegularBold != null)
                createCollection.setFont(fontRegularBold);



            //Card con layout GroupLayout che contiene la funzione per creare una collection
            GroupLayout glAddCollection = new GroupLayout(addCollection);
            addCollection.setLayout(glAddCollection);

            //Indica al GroupLayout di creare automaticamente spazi tra i componenti che gestisce
            glAddCollection.setAutoCreateGaps(true);
            //indica al GroupLayout di inserire automaticamente degli spazi tra i componenti e i bordi del contenitore che li ospita
            glAddCollection.setAutoCreateContainerGaps(true);

            //Creazione del gruppo orizzontale di una singola card
            GroupLayout.SequentialGroup hGroup2 = glAddCollection.createSequentialGroup();

            //Aggiunge orizzontalmente alla card la label 'Crea Raccolta'
            hGroup2.addGroup(glAddCollection.createParallelGroup().
                    addComponent(createCollection));
            glAddCollection.setHorizontalGroup(hGroup2);

            //Creazione del gruppo verticale di una singola card
            GroupLayout.SequentialGroup vGroup2 = glAddCollection.createSequentialGroup();

            //Aggiunge verticalmente alla card la label 'Crea Raccolta'
            vGroup2.addGroup(glAddCollection.createParallelGroup(GroupLayout.Alignment.BASELINE).
                    addComponent(createCollection));
            glAddCollection.setVerticalGroup(vGroup2);

            //Constraints per aggiungere al Panel Scrollabile la card per aggiungere una raccolta
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.insets = new Insets(40, 40, 40, 40);
            gbc.gridy = 2;
            gbc.gridx = 0;
            panelCenter.add(addCollection, gbc);
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
