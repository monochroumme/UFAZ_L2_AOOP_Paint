import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class DrawingBoard extends JPanel implements MouseListener, MouseMotionListener {
    private Color currentColor;
    private Actions currentAction;
    private Shape currentShape;

    public ArrayList<Shape> shapesOnBoard;
    private boolean isDrawing;

    public DrawingBoard() {
        setBackground(Color.WHITE);
        currentColor = Color.BLACK;
        currentAction = Actions.MOVE;
        shapesOnBoard = new ArrayList<>();
        isDrawing = false;
        currentShape = null;

        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        requestFocus();
    }

    public void draw(Graphics g) {
        // anti-aliasing
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        super.paintComponent(g);

        // draw all shapesOnBoard
        for(Shape s : shapesOnBoard) s.draw(g);

        if(isDrawing) currentShape.draw(g);

        String actionName;
        switch (currentAction) {
            case MOVE:
                actionName = "move";
                break;
            case DELETE:
                actionName = "delete";
                break;
            case FILL:
                actionName = "fill";
                break;
            case RECT:
                actionName = "rect";
                break;
            case ELLIPSE:
                actionName = "ellipse";
                break;
            case LINE:
                actionName = "line";
                break;
            default:
                actionName = "xz";
        }
    }

    public void setColor(Color c) {
        this.currentColor = c;
    }

    public void setAction(Actions a) {
        this.currentAction = a;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int target_id = -1;

        for (int i = shapesOnBoard.size()-1; i >= 0; i--) {
            if (shapesOnBoard.get(i).containsPoint(e.getX(), e.getY())) {
                target_id = i;
                shapesOnBoard.get(i).dx = shapesOnBoard.get(i).x - e.getX();
                shapesOnBoard.get(i).dy = shapesOnBoard.get(i).y - e.getY();
                break;
            }
        }

        switch (currentAction) {
            case MOVE:
                if (target_id != -1) shapesOnBoard.get(target_id).isMoving = true;
                break;
            case FILL: break;
            case DELETE: break;
            case RECT:
                currentShape = new Rectangle(e.getX(), e.getY(), 1, 1, currentColor, shapesOnBoard.size());
                isDrawing = true;
                shapesOnBoard.add(currentShape);
                System.out.println(currentShape.x + " " + currentShape.y + " | " + currentShape.dx + " " + currentShape.dy + " " + currentShape.color);
                break;
            case ELLIPSE:
                currentShape = new Ellipse(e.getX(), e.getY(), 1, 1, currentColor, shapesOnBoard.size());
                isDrawing = true;
                shapesOnBoard.add(currentShape);
                break;
            case LINE:
                currentShape = new Line(e.getX(), e.getY(), 1, 1, currentColor, shapesOnBoard.size(), 10);
                isDrawing = true;
                shapesOnBoard.add(currentShape);
                break;
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (currentAction) {
            case MOVE:
                for (int i = shapesOnBoard.size()-1; i >= 0; i--) {
                    if(shapesOnBoard.get(i).isMoving) {
                        shapesOnBoard.get(i).isMoving = false;
                        break;
                    }
                }
                break;
            case FILL: break;
            case DELETE: break;
            default:
                if(isDrawing) {
                    // we don't need (non-line) shapesOnBoard with negative width or height, dealing with this  is
                    // more difficult, so we just remove the shape from the array, effectively ignoring it
                    if(currentShape.width < 0 || currentShape.height < 0) {
                        if(currentAction != Actions.LINE)
                            shapesOnBoard.remove(shapesOnBoard.size()-1);
                    }
                    isDrawing = false;
                    currentShape = null;
                    repaint();
                }
            break;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int target_id = -1;
        for (int i = shapesOnBoard.size()-1; i >= 0; i--) {
            if(shapesOnBoard.get(i).isMoving) {
                target_id = i;
                break;
            }
        }

        switch (currentAction) {
            case MOVE:
                if (target_id > 0) shapesOnBoard.get(target_id).translateTo(e.getX()+shapesOnBoard.get(target_id).x, e.getY()+shapesOnBoard.get(target_id).y);
                break;
            case FILL: break;
            case DELETE: break;
            default:
                currentShape.width = e.getX()-currentShape.x;
                currentShape.height = e.getY()-currentShape.y;
                break;
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int target_id = -1;
        for (int i = shapesOnBoard.size()-1; i >= 0; i--) {
            if (shapesOnBoard.get(i).containsPoint(e.getX(), e.getY())) {
                target_id = i;
                break;
            }
        }
        if (target_id == -1) return;

        switch (currentAction) {
        case DELETE:
            shapesOnBoard.remove(target_id);
            break;
        case FILL:
            shapesOnBoard.get(target_id).setColor(currentColor);
            break;
        default:
            break;
        }

        repaint();
    }

    public void mouseMoved(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
