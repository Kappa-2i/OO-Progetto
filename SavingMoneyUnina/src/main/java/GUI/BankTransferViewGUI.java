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

        JLabel sendBankTransferLabel = new JLabel("Invia Bonifico");
        sendBankTransferLabel.setForeground(new Color(8, 76, 97));

        JLabel nameReceiverLabel= new JLabel("Nome destinatario");
        nameReceiverLabel.setForeground(new Color(8, 76, 97));
        JTextField nameReceiverField = new JTextField();
        nameReceiverField.setBackground(new Color(246, 248, 255));
        nameReceiverField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
        nameReceiverField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                nameReceiverField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 84, 122, 255)));
            }
            @Override
            public void focusLost(FocusEvent e) {
                nameReceiverField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
            }
        });


        JLabel surnameReceiverLabel = new JLabel("Cognome destinatario");
        surnameReceiverLabel.setForeground(new Color(8, 76, 97));
        JTextField surnameReceiverField = new JTextField();
        surnameReceiverField.setBackground(new Color(246, 248, 255));
        surnameReceiverField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
        surnameReceiverField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                surnameReceiverField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 84, 122, 255)));
            }
            @Override
            public void focusLost(FocusEvent e) {
                surnameReceiverField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
            }
        });

        JLabel ibanReceiverLabel = new JLabel("Iban destinatario");
        ibanReceiverLabel.setForeground(new Color(8, 76, 97));
        JTextField ibanReceiverField = new JTextField();
        ibanReceiverField.setBackground(new Color(246, 248, 255));
        ibanReceiverField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
        ibanReceiverField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                ibanReceiverField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 84, 122, 255)));
            }
            @Override
            public void focusLost(FocusEvent e) {
                ibanReceiverField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
            }
        });

        JLabel amountLabel = new JLabel("Importo");
        amountLabel.setForeground(new Color(8, 76, 97));
        JTextField amountField = new JTextField();
        amountField.setBackground(new Color(246, 248, 255));
        amountField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
        amountField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                amountField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 84, 122, 255)));
            }
            @Override
            public void focusLost(FocusEvent e) {
                amountField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(185, 185, 185)));
            }
        });

        JLabel causalLabel = new JLabel("Causale");
        causalLabel.setForeground(new Color(8, 76, 97));
        JTextArea causalTextArea = new JTextArea();
        causalTextArea.setBackground(new Color(246, 248, 255));
        causalTextArea.setBorder(new MatteBorder(2, 2, 2, 2, new Color(185, 185, 185)));
        causalTextArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                causalTextArea.setBorder(new MatteBorder(2, 2, 2, 2, new Color(0, 84, 122, 255)));
            }
            @Override
            public void focusLost(FocusEvent e) {
                causalTextArea.setBorder(new MatteBorder(2, 2, 2, 2, new Color(185, 185, 185)));
            }
        });



        PlainDocument doc = (PlainDocument) causalTextArea.getDocument();
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
        causalTextArea.setLineWrap(true);
        causalTextArea.setWrapStyleWord(true);

        String[] categories = {"Altro", "Bollette", "Cinema", "Salute", "Spesa", "Spesa medica", "Sport", "Svago"};

        JLabel categorieLabel = new JLabel("Categoria");
        categorieLabel.setForeground(new Color(8, 76, 97));
        JComboBox<String> categoriesComboBox = new JComboBox<>(categories);

        String[] typeBankTransfer = {"Bonifico", "Bonifico Istantaneo"};

        JLabel typeBankTransferLabel = new JLabel("Tipo");
        typeBankTransferLabel.setForeground(new Color(8, 76, 97));
        JComboBox<String> typeBankTransferComboBox = new JComboBox<>(typeBankTransfer);
        typeBankTransferComboBox.setSelectedItem(0);



        JLabel collectionLabel = new JLabel("Inserisci in raccolta:");
        collectionLabel.setForeground(new Color(8, 76, 97));

        ArrayList<String> list = new ArrayList<>();
        //Funzione per prendere tutte le raccolte del conto e mostrarle nella JList
        controller.pickCollectionByIban();
        for (Collection collection : controller.getCollections()){
            list.add(collection.getNameCollection());
        }
        //Il metodo toArray(new String[0]) è usato per convertire l'ArrayList in un array di String, che è il formato richiesto dal costruttore di JList.
        JList<String> listCollection= new JList(list.toArray(new String[0]));
        listCollection.setBackground(new Color(246, 248, 255));

        // Aggiunge la JList a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(listCollection);


        JButton sendBankTransferButton = new JButton("Invia");
        sendBankTransferButton.setOpaque(true);
        sendBankTransferButton.setBackground(new Color(0, 0, 0, 255));
        sendBankTransferButton.setForeground(new Color(246, 248, 255));
        sendBankTransferButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        sendBankTransferButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia il cursore per indicare che è cliccabile
        sendBankTransferButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.sendBankTransfer(ibanReceiverField.getText(),
                        amountField.getText(),
                        nameReceiverField.getText(),
                        surnameReceiverField.getText(),
                        causalTextArea.getText(),
                        (String) categoriesComboBox.getSelectedItem(),
                        (String) typeBankTransferComboBox.getSelectedItem(),
                        listCollection.getSelectedValue());
                ibanReceiverField.setText("");
                amountField.setText("");
                nameReceiverField.setText("");
                surnameReceiverField.setText("");
                causalTextArea.setText("");
            }
        });


        if(fontBold!=null){
            sendBankTransferButton.setFont(fontBold);
        }
        if(fontExtraBold!=null){
            sendBankTransferLabel.setFont(fontExtraBold);
        }
        if(fontRegularBold!=null){
            nameReceiverLabel.setFont(fontRegularBold);
            surnameReceiverLabel.setFont(fontRegularBold);
            amountLabel.setFont(fontRegularBold);
            ibanReceiverLabel.setFont(fontRegularBold);
            causalLabel.setFont(fontRegularBold);
            categorieLabel.setFont(fontRegularBold);
            typeBankTransferLabel.setFont(fontRegularBold);
            collectionLabel.setFont(fontRegularBold);
        }
        if(fontRegular!=null){
            nameReceiverField.setFont(fontRegular);
            surnameReceiverField.setFont(fontRegular);
            amountField.setFont(fontRegular);
            ibanReceiverField.setFont(fontRegular);
            causalTextArea.setFont(fontRegular);
            categoriesComboBox.setFont(fontRegular);
            typeBankTransferComboBox.setFont(fontRegular);
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
        contentPane.add(sendBankTransferLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 40, 5, 20);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 1;
        gbc.gridx = 0;
        contentPane.add(nameReceiverLabel,gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 40, 5, 20);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 2;
        gbc.gridx = 0;
        contentPane.add(nameReceiverField,gbc);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 20, 5, 40);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 1;
        gbc.gridx = 1;
        contentPane.add(surnameReceiverLabel,gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 20, 5, 40);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 2;
        gbc.gridx = 1;
        contentPane.add(surnameReceiverField,gbc);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 40, 5, 20);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 3;
        gbc.gridx = 0;
        contentPane.add(ibanReceiverLabel,gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 40, 5, 20);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 4;
        gbc.gridx = 0;
        contentPane.add(ibanReceiverField,gbc);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 20, 5, 40);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 3;
        gbc.gridx = 1;
        contentPane.add(amountLabel,gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 20, 5, 40);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 4;
        gbc.gridx = 1;
        contentPane.add(amountField,gbc);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 40, 5, 20);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 5;
        gbc.gridx = 0;
        contentPane.add(categorieLabel,gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 40, 5, 20);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 6;
        gbc.gridx = 0;
        contentPane.add(categoriesComboBox,gbc);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 20, 5, 40);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 5;
        gbc.gridx = 1;
        contentPane.add(typeBankTransferLabel,gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 20, 5, 40);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 6;
        gbc.gridx = 1;
        contentPane.add(typeBankTransferComboBox,gbc);


        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 40, 5, 20);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 7;
        gbc.gridx = 0;
        contentPane.add(causalLabel,gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 40, 5, 20);
        gbc.weighty = 0.1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 8;
        gbc.gridx = 0;
        contentPane.add(causalTextArea,gbc);

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
        contentPane.add(sendBankTransferButton,gbc);

        // Utilizza SwingUtilities.invokeLater per richiedere il focus
        SwingUtilities.invokeLater(() -> sendBankTransferButton.requestFocusInWindow());

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
