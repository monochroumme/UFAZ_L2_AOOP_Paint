 import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import javax.swing.JFrame;

    public class Shapes extends JFrame{
        int width=500;
        int height=500;

        public Shapes(){
            setSize(width,height);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
            setLocationRelativeTo(null);
            setVisible(true);
        }

        public static void main(String a[]){
            new Shapes();
        }

        public void paint(Graphics g){
            // Circular Surface
            drawCircleByCenter(g, width/2, height/2, width/2);
            Random r = new Random();
            Point center = new Point();
            center.x=r.nextInt(width/2);
            center.y=r.nextInt(width/2);
            drawCircleByCenter(g, center.x, center.y, width/15);
        }

        void drawCircleByCenter(Graphics g, int x, int y, int radius){
            //g.setColor(Color.LIGHT_GRAY);
            g.drawOval(x-radius, y-radius, 2*radius, 2*radius);
        }
    }
