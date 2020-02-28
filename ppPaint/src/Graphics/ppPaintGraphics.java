package Graphics;

import javax.swing.*;
import java.awt.*;

public class ppPaintGraphics extends JFrame {
    public static Board board;

    public ppPaintGraphics(String title, int width, int height, int minWidth, int minHeight) {
        board = new Board();

        setSize(width, height);
        setMinimumSize(new Dimension(minWidth, minHeight));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // open it in the center of the screen

        setJMenuBar(new TopMenu());
        add(new Sidebar(new GridLayout(6, 1)), BorderLayout.WEST);
        add(new Bottombar(new BorderLayout(0, 0)), BorderLayout.SOUTH);

        setVisible(true);
    }
}
