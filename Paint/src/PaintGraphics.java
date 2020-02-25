import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;

public class PaintGraphics {
    static final String FRM_TITLE = "ppPaint";
    static final int FRM_WIDTH = 700;
    static final int FRM_HEIGHT = 500;
    static DrawingBoard board;

    public PaintGraphics() {
        JFrame frame = new JFrame(FRM_TITLE);
        frame.setSize(FRM_WIDTH, FRM_HEIGHT);
        frame.setMinimumSize(new Dimension(400, 300));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // open it in the center of the screen

        Menu menu = new Menu();
        board = new DrawingBoard();

        JToolBar tools = new JToolBar("Toolbar");
        tools.setLayout(new GridLayout(7, 1));
        tools.setFloatable(false);
        JButton moveTool = new JButton("move"),
                eraserTool = new JButton("delete"),
                fillTool = new JButton("fill"),
                rectangleTool = new JButton("rect"),
                ellipseTool = new JButton("ellipse"),
                lineTool = new JButton("line");

        moveTool.addActionListener(a -> { board.setAction(Actions.MOVE); board.repaint();});
        eraserTool.addActionListener(a -> { board.setAction(Actions.DELETE); board.repaint();});
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

        JColorChooser cc = new JColorChooser();
        cc.getSelectionModel().addChangeListener( (ChangeEvent e) -> {
            board.setColor(cc.getColor());
        });
        cc.setPreviewPanel(new JPanel());

        JPanel side = new JPanel();
        JPanel bottom = new JPanel();

        side.add(tools);
        bottom.add(cc);

        frame.setJMenuBar(menu);
        frame.getContentPane().add(side, BorderLayout.LINE_START);
        frame.getContentPane().add(board, BorderLayout.CENTER);
        frame.getContentPane().add(bottom, BorderLayout.PAGE_END);

        frame.setVisible(true);
    }
}
