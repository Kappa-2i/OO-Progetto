package GUI;

import CONTROLLER.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

public class CardViewGUI extends JFrame {
    private Controller controller;

    //Dichiarazioni Variabili per i Font
    private Font fontRegular;
    private Font fontBold;
    private Font fontExtraBold;
    private Font fontRegularSmall;
    private Font fontRegularBold;
    private Font fontRegularXXL;

    //Dichiarazioni immagini
    private ImageIcon iconChip = new ImageIcon(CardViewGUI.class.getResource("/IMG/chip.png"));
    private ImageIcon iconHiddenPassword = new ImageIcon(CardViewGUI.class.getResource("/IMG/hiddenpassword.png"));
    private ImageIcon iconShowedPassword = new ImageIcon(CardViewGUI.class.getResource("/IMG/showedpassword.png"));


    public CardViewGUI(Controller controller){
        this.controller = controller;
        setTitle("Carta - S.M.U.");
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setResizable(false);
        setSize(450, 250);
        setLocationRelativeTo(null);
        fontBold();
        fontRegular();
        fontExtraBold();
        fontRegularSmall();
        fontRegularBold();
        fontRegularXXL();

        JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.setBackground(new Color(194, 74, 74));

        //Creazione titolo SavingMoneyUnina
        JLabel titleSmuLabel = new JLabel("S.M.U.");
        GridBagConstraints gbc = new GridBagConstraints();
        contentPane.add(titleSmuLabel, gbc);

        //Creazione bottone Image Chip
        JButton chip = new JButton();
        chip.setBackground(null);
        chip.setIcon(iconChip);
        chip.setContentAreaFilled(false);
        chip.setOpaque(false);
        chip.setBorderPainted(false);
        chip.setBorder(null);
        chip.setFocusPainted(false);

        //Creazione label 'Titolare'
        JLabel holderLabel = new JLabel("Titolare");
        holderLabel.setForeground(new Color(246, 248, 255));

        //Creazione label che contiene il nome e cognome del titolare della carta
        JLabel nameSurnameHolderLabel = new JLabel(controller.getAccount().getName() +" "+controller.getAccount().getSurname());
        nameSurnameHolderLabel.setForeground(new Color(246, 248, 255));


        //Creazione label che contiene il numero della carta
        JLabel panCardLabel = new JLabel(controller.getCarta().getPan().substring(0,4) + " " +controller.getCarta().getPan().substring(4,8) + " " +controller.getCarta().getPan().substring(8,12) + " "+ controller.getCarta().getPan().substring(12,16));
        panCardLabel.setForeground(new Color(246, 248, 255));


        JLabel pinCardLabel = new JLabel("PIN");
        pinCardLabel.setForeground(new Color(246, 248, 255));

        //Creazione label che contiene il numero del pin della carta
        JPasswordField pinNumberLabel = new JPasswordField(controller.getCarta().getPin());
        pinNumberLabel.setBackground(null);
        pinNumberLabel.setBorder(null);
        pinNumberLabel.setEditable(false);
        pinNumberLabel.setEchoChar('*');
        pinNumberLabel.setForeground(new Color(246, 248, 255));

        //Pulsante per mostrare e nascondere il pin
        JCheckBox showPinCheckBox = new JCheckBox("");
        if (fontRegularSmall != null)
            showPinCheckBox.setFont(fontRegularSmall);
        showPinCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPinCheckBox.isSelected()) {
                    pinNumberLabel.setEchoChar((char) 0); // Mostra il pin
                } else {
                    pinNumberLabel.setEchoChar('*'); // Nasconde il pin
                }
            }
        });

        JLabel cvvCardLabel = new JLabel("CVV");
        cvvCardLabel.setForeground(new Color(246, 248, 255));

        //Creazione label che contiene il numero del cvv della carta
        JPasswordField cvvNumberLabel = new JPasswordField(controller.getCarta().getCvv());
        cvvNumberLabel.setEchoChar('*');
        cvvNumberLabel.setForeground(new Color(246, 248, 255));
        cvvNumberLabel.setBackground(null);
        cvvNumberLabel.setBorder(null);
        cvvNumberLabel.setEditable(false);

        //Pulsante per mostrare e nascondere il cvv
        JCheckBox showCvvCheckBox = new JCheckBox("");
        if (fontRegularSmall != null)
            showCvvCheckBox.setFont(fontRegularSmall);
        showCvvCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showCvvCheckBox.isSelected()) {
                    cvvNumberLabel.setEchoChar((char) 0); // Mostra il cvv
                } else {
                    cvvNumberLabel.setEchoChar('*'); // Nasconde il cvv
                }
            }
        });

        //Setting delle icone ai pulsanti per nascondere/mostrare le informazioni
        showPinCheckBox.setIcon(iconHiddenPassword);
        showPinCheckBox.setSelectedIcon(iconShowedPassword);
        showPinCheckBox.setPressedIcon(iconShowedPassword);
        showPinCheckBox.setFocusPainted(false);

        showCvvCheckBox.setIcon(iconHiddenPassword);
        showCvvCheckBox.setSelectedIcon(iconShowedPassword);
        showCvvCheckBox.setPressedIcon(iconShowedPassword);
        showCvvCheckBox.setFocusPainted(false);


        if (fontRegular != null && fontRegularBold != null && fontBold != null){
            titleSmuLabel.setFont(fontBold);
            holderLabel.setFont(fontRegularBold);
            nameSurnameHolderLabel.setFont(fontRegular);
            panCardLabel.setFont(fontRegularBold);
            pinCardLabel.setFont(fontRegularBold);
            pinNumberLabel.setFont(fontRegular);
            cvvCardLabel.setFont(fontRegularBold);
            cvvNumberLabel.setFont(fontRegular);
        }

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 0, 0);
        contentPane.add(titleSmuLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets = new Insets(10, 10, 0, 0);
        contentPane.add(chip, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.8;
        gbc.weighty = 0.3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 10, 0, 0);
        contentPane.add(panCardLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.2;
        gbc.weighty = 0.3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 10, 0, 0);
        contentPane.add(pinCardLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.9;
        gbc.weighty = 0.3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 10);
        contentPane.add(pinNumberLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weightx = 0.2;
        gbc.weighty = 0.3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(0, 0, 0, 0);
        contentPane.add(showPinCheckBox, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.2;
        gbc.weighty = 0.3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(0, 10, 0, 0);
        contentPane.add(cvvCardLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0.7;
        gbc.weighty = 0.3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(0, 0, 0, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(cvvNumberLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.weightx = 0.2;
        gbc.weighty = 0.3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(0, 0, 0, 0);
        contentPane.add(showCvvCheckBox, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.8;
        gbc.weighty = 0.3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(0, 10, 0, 0);
        contentPane.add(holderLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 0.8;
        gbc.weighty = 0.3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(0, 0, 0, 0);
        contentPane.add(nameSurnameHolderLabel, gbc);

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
            fontBold = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(28f); // Modifica la dimensione a piacimento
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
