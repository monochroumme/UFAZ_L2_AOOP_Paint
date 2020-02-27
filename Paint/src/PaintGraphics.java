import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import java.awt.*;

public class PaintGraphics {
    static final String FRM_TITLE = "Paint";
    static final int FRM_WIDTH = 800;
    static final int FRM_HEIGHT = 600;
    static final int FRM_MIN_WIDTH = 600;
    static final int FRM_MIN_HEIGHT = 400;
    static DrawingBoard board;

    public PaintGraphics() {
        JFrame frame = new JFrame(FRM_TITLE);
        frame.setSize(FRM_WIDTH, FRM_HEIGHT);
        frame.setMinimumSize(new Dimension(FRM_MIN_WIDTH, FRM_MIN_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // open it in the center of the screen

        Menu menu = new Menu();
        board = new DrawingBoard();

        JPanel tools = new JPanel();

        try {
            Image cursorImg = ImageIO.read(getClass().getResource("resources/cursor.png"));
            Image eraserImg = ImageIO.read(getClass().getResource("resources/eraser.png"));
            Image fillImg = ImageIO.read(getClass().getResource("resources/fill.png"));
            Image rectImg = ImageIO.read(getClass().getResource("resources/rect.png"));
            Image ellipseImg = ImageIO.read(getClass().getResource("resources/ellipse.png"));
            Image lineImg = ImageIO.read(getClass().getResource("resources/line.png"));


            tools.setLayout(new GridLayout(1, 10));
            JButton moveTool = new JButton();
            JButton eraserTool = new JButton();
            JButton fillTool = new JButton();
            JButton rectangleTool = new JButton();
            JButton ellipseTool = new JButton();
            JButton lineTool = new JButton();
            JButton curColor = new JButton();
            curColor.setBackground(board.curColor);
            JPanel fillPanel = new JPanel();
            JPanel noFillPanel = new JPanel();

            //----------------------------------
            JRadioButton fill = new JRadioButton("Fill");
            fill.setBackground(Color.WHITE);
            fillPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            JRadioButton noFill = new JRadioButton("No Fill");
            noFill.setBackground(Color.WHITE);
            noFillPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            ButtonGroup bg = new ButtonGroup();
            bg.add(fill);
            bg.add(noFill);

            fill.addActionListener(a -> {board.isFill = true;});
            noFill.addActionListener(a -> {board.isFill = false;});

            fillPanel.add(fill);
            noFillPanel.add(noFill);

            //----------------------------------


            moveTool.setIcon(new ImageIcon(cursorImg));
            eraserTool.setIcon(new ImageIcon(eraserImg));
            fillTool.setIcon(new ImageIcon(fillImg));
            rectangleTool.setIcon(new ImageIcon(rectImg));
            ellipseTool.setIcon(new ImageIcon(ellipseImg));
            lineTool.setIcon(new ImageIcon(lineImg));

            moveTool.addActionListener(a -> { board.setAction(Actions.MOVE); board.repaint();});
            eraserTool.addActionListener(a -> { board.setAction(Actions.ERASE); board.repaint();});
            fillTool.addActionListener(a -> { board.setAction(Actions.FILL); board.repaint();});
            rectangleTool.addActionListener(a -> { board.setAction(Actions.RECT); board.repaint();});
            ellipseTool.addActionListener(a -> { board.setAction(Actions.ELLIPSE); board.repaint();});
            lineTool.addActionListener(a -> { board.setAction(Actions.LINE); board.repaint();});
            curColor.addActionListener(a -> { Color newColor = JColorChooser.showDialog(
                    curColor, "Choose color", curColor.getBackground());
                if (newColor != null) {
                    curColor.setBackground(newColor);
                }
                board.curColor = newColor;
            });

            tools.add(moveTool);
            tools.add(eraserTool);
            tools.add(fillTool);
            tools.add(rectangleTool);
            tools.add(ellipseTool);
            tools.add(lineTool);
            tools.add(curColor);
            tools.add(fillPanel);
            tools.add(noFillPanel);

            tools.setBackground(Color.WHITE);
            moveTool.setBackground(Color.WHITE);
            eraserTool.setBackground(Color.WHITE);
            fillTool.setBackground(Color.WHITE);
            rectangleTool.setBackground(Color.WHITE);
            ellipseTool.setBackground(Color.WHITE);
            lineTool.setBackground(Color.WHITE);
            fillPanel.setBackground(Color.WHITE);
            noFillPanel.setBackground(Color.WHITE);

        } catch (Exception e) { e.printStackTrace(); }

        JPanel top = new JPanel();
        top.setBackground(Color.WHITE);
        top.add(tools);

        frame.setJMenuBar(menu);
        frame.getContentPane().add(top, BorderLayout.NORTH);
        frame.getContentPane().add(board, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
