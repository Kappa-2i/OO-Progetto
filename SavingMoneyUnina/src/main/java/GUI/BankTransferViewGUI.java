package GUI;

import CONTROLLER.Controller;
import ENTITY.*;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;
import java.util.ArrayList;

public class BankTransferViewGUI extends JFrame {


    private Controller controller;

    //Dichiarazioni Variabili per i Font
    private Font fontRegular;
    private Font fontBold;
    private Font fontExtraBold;
    private Font fontRegularSmall;
    private Font fontRegularBold;
    private Font fontRegularXXL;

    public BankTransferViewGUI(Controller controller){
        this.controller = controller;
        setTitle("Invia Bonifico - S.M.U.");
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setResizable(false);
        setSize(700, 750);
        setLocationRelativeTo(null);
        fontBold();
        fontRegular();
        fontExtraBold();
        fontRegularSmall();
        fontRegularBold();
        fontRegularXXL();



        JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.setBackground(new Color(246, 248, 255));

        JLabel inviaBonificoLabel = new JLabel("Invia Bonifico");
        inviaBonificoLabel.setForeground(new Color(8, 76, 97));

        JLabel nomeDestinatarioLabel= new JLabel("Nome destinatario");
        nomeDestinatarioLabel.setForeground(new Color(8, 76, 97));
        JTextField nomeDestinatarioField = new JTextField();
        nomeDestinatarioField.setBackground(new Color(246, 248, 255));
        nomeDestinatarioField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
        nomeDestinatarioField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                nomeDestinatarioField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 84, 122, 255)));
            }
            @Override
            public void focusLost(FocusEvent e) {
                nomeDestinatarioField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
            }
        });


        JLabel cognomeDestinatarioLabel = new JLabel("Cognome destinatario");
        cognomeDestinatarioLabel.setForeground(new Color(8, 76, 97));
        JTextField cognomeDestinatarioField = new JTextField();
        cognomeDestinatarioField.setBackground(new Color(246, 248, 255));
        cognomeDestinatarioField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
        cognomeDestinatarioField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                cognomeDestinatarioField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 84, 122, 255)));
            }
            @Override
            public void focusLost(FocusEvent e) {
                cognomeDestinatarioField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
            }
        });

        JLabel ibanDestinatarioLabel = new JLabel("Iban destinatario");
        ibanDestinatarioLabel.setForeground(new Color(8, 76, 97));
        JTextField ibanDestinatarioField = new JTextField();
        ibanDestinatarioField.setBackground(new Color(246, 248, 255));
        ibanDestinatarioField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
        ibanDestinatarioField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                ibanDestinatarioField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 84, 122, 255)));
            }
            @Override
            public void focusLost(FocusEvent e) {
                ibanDestinatarioField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
            }
        });

        JLabel importoLabel = new JLabel("Importo");
        importoLabel.setForeground(new Color(8, 76, 97));
        JTextField importoField = new JTextField();
        importoField.setBackground(new Color(246, 248, 255));
        importoField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
        importoField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                importoField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 84, 122, 255)));
            }
            @Override
            public void focusLost(FocusEvent e) {
                importoField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
            }
        });

        JLabel causaleLabel = new JLabel("Causale");
        causaleLabel.setForeground(new Color(8, 76, 97));
        JTextArea causaleArea = new JTextArea();
        causaleArea.setBackground(new Color(246, 248, 255));
        causaleArea.setBorder(new MatteBorder(2, 2, 2, 2, new Color(185, 185, 185)));
        causaleArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                causaleArea.setBorder(new MatteBorder(2, 2, 2, 2, new Color(0, 84, 122, 255)));
            }
            @Override
            public void focusLost(FocusEvent e) {
                causaleArea.setBorder(new MatteBorder(2, 2, 2, 2, new Color(185, 185, 185)));
            }
        });



        PlainDocument doc = (PlainDocument) causaleArea.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            private int maxChars = 200;

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

        //Funzioni che fanno andare accapo quando scrivi nella TextArea
        causaleArea.setLineWrap(true);
        causaleArea.setWrapStyleWord(true);

        String[] categorie = {"Altro", "Bollette", "Cinema", "Salute", "Spesa", "Spesa medica", "Sport", "Svago"};

        JLabel catLabel = new JLabel("Categoria");
        catLabel.setForeground(new Color(8, 76, 97));
        JComboBox<String> catComboBox = new JComboBox<>(categorie);

        String[] tipoBonifico = {"Bonifico", "Bonifico Istantaneo"};

        JLabel bonLabel = new JLabel("Tipo");
        bonLabel.setForeground(new Color(8, 76, 97));
        JComboBox<String> bonComboBox = new JComboBox<>(tipoBonifico);



        JLabel collectionLabel = new JLabel("Inserisci in raccolta:");
        collectionLabel.setForeground(new Color(8, 76, 97));

        ArrayList<String> list = new ArrayList<>();
        //Funzione per prendere tutte le raccolte del conto e mostrarle nella JList
        controller.pickCollectionByIban();
        for (Collection collection : controller.getCollections()){
            list.add(collection.getNameCollection());
        }
        JList<String> listCollection= new JList(list.toArray(new String[0]));
        listCollection.setBackground(new Color(246, 248, 255));

        // Aggiunge la JList a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(listCollection);


        JButton inviaButton = new JButton("Invia");
        inviaButton.setOpaque(true);
        inviaButton.setBackground(new Color(0, 0, 0, 255));
        inviaButton.setForeground(new Color(246, 248, 255));
        inviaButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        inviaButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia il cursore per indicare che è cliccabile
        inviaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.sendBankTransfer(ibanDestinatarioField.getText(),
                        importoField.getText(),
                        nomeDestinatarioField.getText(),
                        cognomeDestinatarioField.getText(),
                        causaleArea.getText(),
                        (String) catComboBox.getSelectedItem(),
                        (String) bonComboBox.getSelectedItem(),
                        listCollection.getSelectedValue());
                ibanDestinatarioField.setText("");
                importoField.setText("");
                nomeDestinatarioField.setText("");
                cognomeDestinatarioField.setText("");
                causaleArea.setText("");
            }
        });


        if(fontBold!=null){
            inviaButton.setFont(fontBold);
        }
        if(fontExtraBold!=null){
            inviaBonificoLabel.setFont(fontExtraBold);
        }
        if(fontRegularBold!=null){
            nomeDestinatarioLabel.setFont(fontRegularBold);
            cognomeDestinatarioLabel.setFont(fontRegularBold);
            importoLabel.setFont(fontRegularBold);
            ibanDestinatarioLabel.setFont(fontRegularBold);
            causaleLabel.setFont(fontRegularBold);
            catLabel.setFont(fontRegularBold);
            bonLabel.setFont(fontRegularBold);
            collectionLabel.setFont(fontRegularBold);
        }
        if(fontRegular!=null){
            nomeDestinatarioField.setFont(fontRegular);
            cognomeDestinatarioField.setFont(fontRegular);
            importoField.setFont(fontRegular);
            ibanDestinatarioField.setFont(fontRegular);
            causaleArea.setFont(fontRegular);
            catComboBox.setFont(fontRegular);
            bonComboBox.setFont(fontRegular);
            listCollection.setFont(fontRegular);
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 40, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 0.9;
        gbc.weightx = 1.0;
        gbc.gridwidth = 2;
        gbc.gridy = 0;
        gbc.gridx = 0;
        contentPane.add(inviaBonificoLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 40, 5, 20);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 1;
        gbc.gridx = 0;
        contentPane.add(nomeDestinatarioLabel,gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 40, 5, 20);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 2;
        gbc.gridx = 0;
        contentPane.add(nomeDestinatarioField,gbc);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 20, 5, 40);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 1;
        gbc.gridx = 1;
        contentPane.add(cognomeDestinatarioLabel,gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 20, 5, 40);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 2;
        gbc.gridx = 1;
        contentPane.add(cognomeDestinatarioField,gbc);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 40, 5, 20);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 3;
        gbc.gridx = 0;
        contentPane.add(ibanDestinatarioLabel,gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 40, 5, 20);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 4;
        gbc.gridx = 0;
        contentPane.add(ibanDestinatarioField,gbc);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 20, 5, 40);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 3;
        gbc.gridx = 1;
        contentPane.add(importoLabel,gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 20, 5, 40);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 4;
        gbc.gridx = 1;
        contentPane.add(importoField,gbc);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 40, 5, 20);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 5;
        gbc.gridx = 0;
        contentPane.add(catLabel,gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 40, 5, 20);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 6;
        gbc.gridx = 0;
        contentPane.add(catComboBox,gbc);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 20, 5, 40);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 5;
        gbc.gridx = 1;
        contentPane.add(bonLabel,gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 20, 5, 40);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 6;
        gbc.gridx = 1;
        contentPane.add(bonComboBox,gbc);


        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 40, 5, 20);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 7;
        gbc.gridx = 0;
        contentPane.add(causaleLabel,gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 40, 5, 20);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 8;
        gbc.gridx = 0;
        contentPane.add(causaleArea,gbc);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 20, 5, 40);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 7;
        gbc.gridx = 1;
        contentPane.add(collectionLabel,gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 20, 5, 40);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 8;
        gbc.gridx = 1;
        contentPane.add(scrollPane,gbc);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 40);
        gbc.weighty = 0.05;
        gbc.gridy = 9;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = 0;
        contentPane.add(inviaButton,gbc);

        // Utilizza SwingUtilities.invokeLater per richiedere il focus
        SwingUtilities.invokeLater(() -> inviaButton.requestFocusInWindow());
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
