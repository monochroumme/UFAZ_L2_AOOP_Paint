package shapes;

import java.awt.*;

public class Rectangle extends Shape {
    public Rectangle(int x, int y, int width, int height, Color c, int id, boolean isFill) {
        super(x, y, width, height, c, id);
        this.isFill = isFill;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        if(isFill) g.fillRect(x, y, width, height);
        else g.drawRect(x, y, width, height);
        g.setColor(color);
        if (isMoving) g.drawRect(x, y, width, height);
    }

    @Override
    public boolean containsPoint(int x, int y) {
        return (this.x <= x && x <= this.x + this.width && this.y <= y && y <= this.y + this.height);
    }
}
