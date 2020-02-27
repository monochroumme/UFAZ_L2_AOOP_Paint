import shapes.Ellipse;
import shapes.Line;
import shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class DrawingBoard extends JPanel implements MouseListener, MouseMotionListener {
    public Color curColor;
    public Color altColor;
    private Actions currentAction;
    private shapes.Shape currentShape;
    int curX, curY, oldX, oldY;
    boolean isFill;

    public ArrayList<shapes.Shape> shapesOnBoard;
    private boolean isDrawing, isResizing;

    public DrawingBoard() {
        setBackground(Color.WHITE);
        curColor = Color.BLACK;
        altColor = Color.WHITE;
        currentAction = Actions.MOVE;
        shapesOnBoard = new ArrayList<>();
        isDrawing = false;
        currentShape = null;

        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        requestFocus();
    }

    public void paintComponent(Graphics g) {
        // add anti-aliasing to make the edges smooth
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
            case ERASE:
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

    public void setCurColor(Color c) {
        this.curColor = c;
    }

    public void setAltColor(Color c) {
        this.altColor = c;
    }

    public void setAction(Actions a) {
        this.currentAction = a;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int id = -1;

        for (int i = shapesOnBoard.size()-1; i >= 0; i--) {
            if (shapesOnBoard.get(i).containsPoint(e.getX(), e.getY())) {
                id = i;
                shapesOnBoard.get(i).dx = shapesOnBoard.get(i).x - e.getX();
                shapesOnBoard.get(i).dy = shapesOnBoard.get(i).y - e.getY();
                break;
            }
        }

        oldX = e.getX();
        oldY = e.getY();

        switch (currentAction) {
            case MOVE:
                if (id != -1) shapesOnBoard.get(id).isMoving = true;
                break;
            case RECT:
                currentShape = new shapes.Rectangle(e.getX(), e.getY(), 1, 1, curColor, shapesOnBoard.size());
                isResizing = true;
                isDrawing = true;
                shapesOnBoard.add(currentShape);
                break;
            case ELLIPSE:
                currentShape = new Ellipse(e.getX(), e.getY(), 1, 1, curColor, shapesOnBoard.size());
                isResizing = true;
                isDrawing = true;
                shapesOnBoard.add(currentShape);
                break;
            case LINE:
                currentShape = new Line(e.getX(), e.getY(), 1, 1, curColor, shapesOnBoard.size(),5);
                isResizing = true;
                isDrawing = true;
                shapesOnBoard.add(currentShape);
                break;
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        curX = e.getX();
        curY = e.getY();

        switch (currentAction) {
            case MOVE:
                for (int i = shapesOnBoard.size()-1; i >= 0; i--) {
                    if(shapesOnBoard.get(i).isMoving) {
                        shapesOnBoard.get(i).isMoving = false;
                        break;
                    }
                }
                break;
            case ELLIPSE:
            case LINE:
            case RECT:
                if (isDrawing) {
                    isDrawing = false;
                    currentShape = null;
                }
                break;
        }
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int id = -1;
        for (int i = shapesOnBoard.size() - 1; i >= 0; i--) {
            if (shapesOnBoard.get(i) instanceof Line) {
                if (currentAction == Actions.MOVE && shapesOnBoard.get(i).containsPoint(e.getX(), e.getY()) || currentAction == Actions.ERASE && ((Line) shapesOnBoard.get(i)).containsPointRect(e.getX(), e.getY())) {
                    id = i;
                    break;
                }
            } else if (shapesOnBoard.get(i).containsPoint(e.getX(), e.getY())) {
                id = i;
                break;
            }
        }

        switch (currentAction) {
            case MOVE:
                if (id >= 0 && shapesOnBoard.get(id).isMoving) shapesOnBoard.get(id).translateTo(e.getX() + shapesOnBoard.get(id).dx, e.getY() + shapesOnBoard.get(id).dy);
                break;
            case ERASE:
                if (id >= 0)
                    shapesOnBoard.remove(id);
                    repaint();
                break;
            case FILL: break;
            default:
                if (isResizing) {
                    if (e.getX() - oldX < 0) { // x < 0
                        if (currentAction != Actions.LINE)
                            currentShape.x = e.getX();
                    }

                    if (e.getY() - oldY < 0) { // y < 0
                        if (currentAction != Actions.LINE)
                            currentShape.y = e.getY();
                    }

                    if (currentAction != Actions.LINE) {
                        currentShape.width = Math.abs(e.getX() - oldX);
                        currentShape.height = Math.abs(e.getY() - oldY);
                    } else {
                        currentShape.width = e.getX() - oldX;
                        currentShape.height = e.getY() - oldY;
                    }
                }
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int id = -1;
        for (int i = shapesOnBoard.size() - 1; i >= 0; i--) {
            if (shapesOnBoard.get(i).containsPoint(e.getX(), e.getY())) {
                id = i;
                break;
            }
        }
        if (id == -1) return;

        switch (currentAction) {
            case FILL:
                shapesOnBoard.get(id).setColor(curColor);
                break;
        }
        repaint();
    }

    public void mouseMoved(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
