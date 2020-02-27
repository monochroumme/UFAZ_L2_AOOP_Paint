package Graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Sidebar extends JPanel {
    public Sidebar(GridLayout layout) {
        super(layout);

        try {
            ArrayList<JButton> buttons = new ArrayList<>();

            // button icons
            Image cursorImg = ImageIO.read(getClass().getResource("resources/cursor.png"));
            Image eraserImg = ImageIO.read(getClass().getResource("resources/eraser.png"));
            Image fillImg = ImageIO.read(getClass().getResource("resources/fill.png"));
            Image rectImg = ImageIO.read(getClass().getResource("resources/rect.png"));
            Image ellipseImg = ImageIO.read(getClass().getResource("resources/ellipse.png"));
            Image lineImg = ImageIO.read(getClass().getResource("resources/line.png"));

            // buttons
            for (int i = 0; i < 6; i++) { // cursor, eraser, fill, rect, ellipse, line
                buttons.add(new JButton());
                buttons.get(i).setBorder(null);
                buttons.get(i).setMargin(null);
                buttons.get(i).setBackground(Color.WHITE);
                switch (i) {
                    case 0:
                        buttons.get(i).setIcon(new ImageIcon(cursorImg));
                        break;
                    case 1:
                        buttons.get(i).setIcon(new ImageIcon(eraserImg));
                        break;
                    case 2:
                        buttons.get(i).setIcon(new ImageIcon(fillImg));
                        break;
                    case 3:
                        buttons.get(i).setIcon(new ImageIcon(rectImg));
                        break;
                    case 4:
                        buttons.get(i).setIcon(new ImageIcon(ellipseImg));
                        break;
                    case 5:
                        buttons.get(i).setIcon(new ImageIcon(lineImg));
                        break;
                }
                add(buttons.get(i));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
