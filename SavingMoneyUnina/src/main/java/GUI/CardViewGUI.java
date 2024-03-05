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

        JLabel smuLabel = new JLabel("S.M.U.");
        GridBagConstraints gbc = new GridBagConstraints();
        contentPane.add(smuLabel, gbc);

        ImageIcon iconChip = new ImageIcon(CardViewGUI.class.getResource("/IMG/chip.png"));
        JButton chip = new JButton();
        chip.setBackground(null);
        chip.setIcon(iconChip);
        chip.setContentAreaFilled(false);
        chip.setOpaque(false);
        chip.setBorderPainted(false);
        chip.setBorder(null);
        chip.setFocusPainted(false);

        JLabel titolareLabel = new JLabel("Titolare");
        titolareLabel.setForeground(new Color(246, 248, 255));

        JLabel nomeTitolareLabel = new JLabel(controller.getAccount().getName() +" "+controller.getAccount().getSurname());
        nomeTitolareLabel.setForeground(new Color(246, 248, 255));


        JLabel numeroCartaLabel = new JLabel(controller.getCard().getPan().substring(0,4) + " " +controller.getCard().getPan().substring(4,8) + " " +controller.getCard().getPan().substring(8,12) + " "+ controller.getCard().getPan().substring(12,16));
        numeroCartaLabel.setForeground(new Color(246, 248, 255));


        JLabel pinLabel = new JLabel("PIN");
        pinLabel.setForeground(new Color(246, 248, 255));

        JPasswordField pinNumberLabel = new JPasswordField(controller.getCard().getPin());
        pinNumberLabel.setBackground(null);
        pinNumberLabel.setBorder(null);
        pinNumberLabel.setEditable(false);
        pinNumberLabel.setEchoChar('*');
        pinNumberLabel.setForeground(new Color(246, 248, 255));

        JCheckBox showPinCheckBox = new JCheckBox("");
        if (fontRegularSmall != null)
            showPinCheckBox.setFont(fontRegularSmall);
        showPinCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPinCheckBox.isSelected()) {
                    pinNumberLabel.setEchoChar((char) 0); // Mostra la password
                } else {
                    pinNumberLabel.setEchoChar('*'); // Nasconde la password
                }
            }
        });

        JLabel cvvLabel = new JLabel("CVV");
        cvvLabel.setForeground(new Color(246, 248, 255));

        JPasswordField cvvNumberLabel = new JPasswordField(controller.getCard().getCvv());
        cvvNumberLabel.setEchoChar('*');
        cvvNumberLabel.setForeground(new Color(246, 248, 255));
        cvvNumberLabel.setBackground(null);
        cvvNumberLabel.setBorder(null);
        cvvNumberLabel.setEditable(false);

        JCheckBox showCvvCheckBox = new JCheckBox("");
        if (fontRegularSmall != null)
            showCvvCheckBox.setFont(fontRegularSmall);
        showCvvCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showCvvCheckBox.isSelected()) {
                    cvvNumberLabel.setEchoChar((char) 0); // Mostra la password
                } else {
                    cvvNumberLabel.setEchoChar('*'); // Nasconde la password
                }
            }
        });

        ImageIcon iconHiddenPassword = new ImageIcon(CardViewGUI.class.getResource("/IMG/hiddenpassword.png"));
        ImageIcon iconShowedPassword = new ImageIcon(CardViewGUI.class.getResource("/IMG/showedpassword.png"));

        showPinCheckBox.setIcon(iconHiddenPassword);
        showPinCheckBox.setSelectedIcon(iconShowedPassword);
        showPinCheckBox.setPressedIcon(iconShowedPassword);
        showPinCheckBox.setFocusPainted(false);

        showCvvCheckBox.setIcon(iconHiddenPassword);
        showCvvCheckBox.setSelectedIcon(iconShowedPassword);
        showCvvCheckBox.setPressedIcon(iconShowedPassword);
        showCvvCheckBox.setFocusPainted(false);


        if (fontRegular != null && fontRegularBold != null && fontBold != null){
            smuLabel.setFont(fontBold);
            titolareLabel.setFont(fontRegularBold);
            nomeTitolareLabel.setFont(fontRegular);
            numeroCartaLabel.setFont(fontRegularBold);
            pinLabel.setFont(fontRegularBold);
            pinNumberLabel.setFont(fontRegular);
            cvvLabel.setFont(fontRegularBold);
            cvvNumberLabel.setFont(fontRegular);
        }

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 0, 0);
        contentPane.add(smuLabel, gbc);

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
        contentPane.add(numeroCartaLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.2;
        gbc.weighty = 0.3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 10, 0, 0);
        contentPane.add(pinLabel, gbc);

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
        contentPane.add(cvvLabel, gbc);

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
        contentPane.add(titolareLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 0.8;
        gbc.weighty = 0.3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(0, 0, 0, 0);
        contentPane.add(nomeTitolareLabel, gbc);

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
