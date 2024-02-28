package GUI;

import javax.swing.*;
import java.awt.*;


public class RoundedPanel extends JPanel {
    private Color backgroundColor;
    private int cornerRadius;

    public RoundedPanel(int radius, Color bgColor) {
        super();
        cornerRadius = radius;
        backgroundColor = bgColor;
        setOpaque(false); // Rendi il pannello trasparente per disegnare un background personalizzato
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Chiama la implementazione della superclasse per assicurare che tutti i componenti figli siano disegnati correttamente
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Imposta il colore dello sfondo e disegna il rettangolo arrotondato
        if (backgroundColor != null) {
            graphics.setColor(backgroundColor);
        } else {
            graphics.setColor(getBackground());
        }
        graphics.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
    }
}
