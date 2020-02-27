import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import java.awt.*;

public class PaintGraphics {
    static final String FRM_TITLE = "ppPaint";
    static final int FRM_WIDTH = 700;
    static final int FRM_HEIGHT = 500;
    static final int FRM_MIN_WIDTH = 500;
    static final int FRM_MIN_HEIGHT = 300;
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


            tools.setLayout(new GridLayout(1, 6));
            JButton moveTool = new JButton(),
                    eraserTool = new JButton(),
                    fillTool = new JButton(),
                    rectangleTool = new JButton(),
                    ellipseTool = new JButton(),
                    lineTool = new JButton();

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

            tools.add(moveTool);
            tools.add(eraserTool);
            tools.add(fillTool);
            tools.add(rectangleTool);
            tools.add(ellipseTool);
            tools.add(lineTool);

            tools.setBackground(Color.WHITE);
            moveTool.setBackground(Color.WHITE);
            eraserTool.setBackground(Color.WHITE);
            fillTool.setBackground(Color.WHITE);
            rectangleTool.setBackground(Color.WHITE);
            ellipseTool.setBackground(Color.WHITE);
            lineTool.setBackground(Color.WHITE);

        } catch (Exception e) { e.printStackTrace(); }

        JColorChooser colorChooser = new JColorChooser();
        colorChooser.getSelectionModel().addChangeListener((ChangeEvent e) -> {
            board.setCurColor(colorChooser.getColor());
        });
        colorChooser.setPreviewPanel(new JPanel());

        // removing everything but swatches from colorChooser
        AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();
        for(AbstractColorChooserPanel p : panels) {
            String displayName = p.getDisplayName();
            if (displayName.compareTo("HSV") == 0 || displayName.compareTo("HSL") == 0 || displayName.compareTo("CMYK") == 0 || displayName.compareTo("RGB") == 0)
                colorChooser.removeChooserPanel(p);
        }

        // creating the panel with the current and the alternative color
        JPanel bottomLeft = new JPanel();
        JPanel bottomRight = new JPanel();
        JPanel curColorPanel = new JPanel();
        JPanel altColorPanel = new JPanel();

        JPanel top = new JPanel();
        JPanel bottom = new JPanel();

        top.add(tools);
        bottom.add(colorChooser);

        frame.setJMenuBar(menu);
        frame.getContentPane().add(top, BorderLayout.NORTH);
        frame.getContentPane().add(board, BorderLayout.CENTER);
        frame.getContentPane().add(bottom, BorderLayout.PAGE_END);

        frame.setVisible(true);
    }
}
