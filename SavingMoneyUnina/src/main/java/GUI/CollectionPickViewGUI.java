package GUI;

import CONTROLLER.Controller;
import ENTITY.Collection;
import EXCEPTIONS.MyExc;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private JPanel panelSignIn;

    //Icone
    ImageIcon iconAddCollection = new ImageIcon(CollectionPickViewGUI.class.getResource("/IMG/add-folder.png"));
    ImageIcon iconHome = new ImageIcon(CollectionPickViewGUI.class.getResource("/IMG/home.png"));
    ImageIcon iconUnina = new ImageIcon(CollectionPickViewGUI.class.getResource("/IMG/unina.png"));
    ImageIcon iconTrash = new ImageIcon(CollectionPickViewGUI.class.getResource("/IMG/trash.png"));
    ImageIcon iconDelete = new ImageIcon(CollectionPickViewGUI.class.getResource("/IMG/delete.png"));


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


        // Panel 3 in alto a tutto
        JPanel panelSignIn3 = new JPanel(new GridBagLayout());
        panelSignIn3.setBackground(new Color(0, 50, 73)); // Scegli il colore che preferisci
        panelSignIn3.setOpaque(true);
        JLabel titoloFrame = new JLabel("Seleziona raccolta");
        if (fontExtraBold != null)
            titoloFrame.setFont(fontExtraBold);
        titoloFrame.setForeground(new Color(246, 248, 255));
        //gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0; // Inizia dalla prima colonna
        gbc.gridy = 0; // Prima riga
        gbc.fill = GridBagConstraints.BOTH;
        panelSignIn3.add(titoloFrame, gbc);

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
        homeButton = new JButton();
        homeButton.setIcon(iconHome);
        homeButton.setBackground(null);
        homeButton.setOpaque(true);
        homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia il cursore per indicare che è cliccabile
        homeButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        homeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.showHomePage(controller.getContoScelto());
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
        panelSignIn3.add(homeButton, gbc);

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


        showCollections();
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

    public void showCollections(){

        Object[] optionsAdd = {"Crea", "Annulla"};
        Object[] optionsDelete = {"Elimina", "Annulla"};
        if (!controller.getCollections().isEmpty()){
            int y = 2;
            int x = 0;
            for (Collection collection : controller.getCollections()) {
                if (x == 3)
                    x = 0;
                RoundedPanel cardBank = new RoundedPanel(15, new Color(222, 226, 230));


                JLabel nameLabel = new JLabel("Nome: ");
                if (fontRegularBold != null)
                    nameLabel.setFont(fontRegularBold);

                JLabel nameCollectionLabel = new JLabel(collection.getNameCollection());
                if (fontRegular != null)
                    nameCollectionLabel.setFont(fontRegular);

                JLabel totalLabel = new JLabel("Totale: ");
                if(fontRegularBold!=null)
                    totalLabel.setFont(fontRegularBold);

                double sum = controller.selectSumOfCollections(collection.getNameCollection());
                JLabel totalTransactions = new JLabel(String.valueOf(sum)+"€");
                if(fontRegular!=null)
                    totalTransactions.setFont(fontRegular);

                JLabel descriptionLabel = new JLabel("Descrizione");

                if(fontRegular!=null)
                    descriptionLabel.setFont(fontRegular);
                descriptionLabel.setForeground(Color.GRAY);
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
                                null, // Componente padre
                                "Sei sicuro di voler eliminare la raccolta "+collection.getNameCollection() , // Messaggio
                                "Elimina raccolta", // Titolo
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE, // Tipo di messaggio
                                iconDelete, // Icona personalizzata, usa null per l'icona di default
                                optionsDelete, // Array contenente le etichette dei pulsanti
                                optionsDelete[0] // Opzione di default
                        );
                        if (result == JOptionPane.YES_OPTION) {
                            controller.deleteCollection(collection.getNameCollection());
                        }

                    }
                });


                GroupLayout glBankAccount = new GroupLayout(cardBank);
                cardBank.setLayout(glBankAccount);

                glBankAccount.setAutoCreateGaps(true);
                glBankAccount.setAutoCreateContainerGaps(true);

                GroupLayout.SequentialGroup hGroup = glBankAccount.createSequentialGroup();
                // Aggiungi nameLabel e nameCollectionLabel allo stesso gruppo parallelo per averli sulla stessa riga
                hGroup.addGroup(glBankAccount.createParallelGroup()
                        .addComponent(nameLabel).addComponent(totalLabel).addComponent(descriptionLabel));
                hGroup.addGroup(glBankAccount.createParallelGroup()
                        .addComponent(nameCollectionLabel).addComponent(totalTransactions).addComponent(deleteButton));
                glBankAccount.setHorizontalGroup(hGroup);

                GroupLayout.SequentialGroup vGroup = glBankAccount.createSequentialGroup();
                // Crea un gruppo parallelo per nameLabel e nameCollectionLabel affinché siano allineati verticalmente
                vGroup.addGroup(glBankAccount.createParallelGroup().
                        addComponent(nameLabel).addComponent(nameCollectionLabel));
                // Crea un gruppo parallelo per nameLabel e nameCollectionLabel affinché siano allineati verticalmente
                vGroup.addGroup(glBankAccount.createParallelGroup().
                        addComponent(totalLabel).addComponent(totalTransactions));
                // Aggiungi il deleteButton in un nuovo gruppo parallelo per posizionarlo sotto
                vGroup.addGroup(glBankAccount.createParallelGroup()
                        .addComponent(descriptionLabel).addComponent(deleteButton));
                glBankAccount.setVerticalGroup(vGroup);

                cardBank.setCursor(new Cursor(Cursor.HAND_CURSOR));
                cardBank.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println(collection.getNameCollection());
                        controller.showCollectionPage(collection);

                    }

                });


                GridBagConstraints gbc = new GridBagConstraints();

                gbc.insets = new Insets(10, 20, 10, 20);
                gbc.gridy = y;
                gbc.gridx = x;
                gbc.weightx = 0.3;
                gbc.fill = GridBagConstraints.BOTH;

                panelSignIn.add(cardBank, gbc);
                x++;
                if(x == 3)
                    y++;

                // Controlla se l'elemento corrente è l'ultimo dell'ArrayList
                if (collection.equals(controller.getCollections().get(controller.getCollections().size() - 1))) {
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

                        public void mouseExited(MouseEvent e) {
                            addBank.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 84, 122)));
                        }
                    });

                    JLabel createCollection = new JLabel("Crea Raccolta +");
                    if (fontRegularBold != null)
                        createCollection.setFont(fontRegularBold);

                    createCollection.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            addBank.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 84, 122)));
                        }

                        public void mouseExited(MouseEvent e) {
                            addBank.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 84, 122)));
                        }
                    });

                    createCollection.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e){

                            JPanel addCollectionPanel = new JPanel(new GridBagLayout());
                            addCollectionPanel.setBackground(new Color(246, 248, 255));

                            JLabel nameCollectionLabel = new JLabel("Nome");
                            JTextField nameCollectionField = new JTextField();
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
                            JTextArea descriptionCollectionArea = new JTextArea();
                            doc = (PlainDocument) descriptionCollectionArea.getDocument();
                            doc.setDocumentFilter(new DocumentFilter() {
                                private int maxChars = 170;

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
                                    controller.addCollection(controller.getContoScelto(), nameCollectionField.getText(), descriptionCollectionArea.getText());
                                } catch (MyExc ex) {
                                    throw new RuntimeException(ex);
                                }
                                controller.showCollectionPickView();
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
                            addComponent(createCollection));
                    glAddBank.setHorizontalGroup(hGroup2);


                    vGroup2.addGroup(glAddBank.createParallelGroup(GroupLayout.Alignment.BASELINE).
                            addComponent(createCollection));
                    glAddBank.setVerticalGroup(vGroup2);

                    gbc = new GridBagConstraints();

                    gbc.insets = new Insets(40, 20, 40, 20);
                    gbc.weightx = 0.3;
                    gbc.gridy = y;
                    gbc.gridx = x;
                    panelSignIn.add(addBank, gbc);
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

            JLabel createCollection = new JLabel("Crea Collezione +");
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
                    JTextField nameCollectionField = new JTextField();

                    JLabel descriptionCollectionLabel = new JLabel("Descrizione");
                    JTextArea descriptionCollectionArea = new JTextArea();
                    PlainDocument doc = (PlainDocument) descriptionCollectionArea.getDocument();
                    doc.setDocumentFilter(new DocumentFilter() {
                        private int maxChars = 170;

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
                            controller.addCollection(controller.getContoScelto(), nameCollectionField.getText(), descriptionCollectionArea.getText());
                        } catch (MyExc ex) {
                            throw new RuntimeException(ex);
                        }
                        controller.showCollectionPickView();
                    }
                }
            });
            if (fontRegularBold != null)
                createCollection.setFont(fontRegularBold);



            GroupLayout glAddBank = new GroupLayout(addCollection);
            addCollection.setLayout(glAddBank);

            glAddBank.setAutoCreateGaps(true);
            glAddBank.setAutoCreateContainerGaps(true);

            GroupLayout.SequentialGroup hGroup2 = glAddBank.createSequentialGroup();
            GroupLayout.SequentialGroup vGroup2 = glAddBank.createSequentialGroup();



            hGroup2.addGroup(glAddBank.createParallelGroup().
                    addComponent(createCollection));
            glAddBank.setHorizontalGroup(hGroup2);


            vGroup2.addGroup(glAddBank.createParallelGroup(GroupLayout.Alignment.BASELINE).
                    addComponent(createCollection));
            glAddBank.setVerticalGroup(vGroup2);

            GridBagConstraints gbc = new GridBagConstraints();

            gbc.insets = new Insets(40, 40, 40, 40);
            gbc.gridy = 2;
            gbc.gridx = 0;
            panelSignIn.add(addCollection, gbc);
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
