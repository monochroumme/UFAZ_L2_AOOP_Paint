import java.awt.*;
import java.io.Serializable;

public abstract class Shape implements Serializable{
    protected int x, y, dx, dy, width, height, id;
    protected Color color;
    protected boolean isMoving;

    public Shape(int x, int y, int width, int height, Color c, int id) {
        this.x = y;
        this.y = y;
        this.width = width;
        this.height = height;
        this.dx = 0;
        this.dy = 0;
        this.color = c;
        this.id = id;
    }

    public abstract void draw(Graphics g);

    public abstract boolean containsPoint(int x, int y);

    public void translateTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setColor(Color c) {
        this.color = c;
    }
}