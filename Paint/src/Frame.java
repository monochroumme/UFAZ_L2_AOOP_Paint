import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener {

    public Frame() {
        super("Paint");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}