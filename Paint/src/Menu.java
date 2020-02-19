import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JMenuBar {
    public Menu() {

        ActionListener afficherMenuListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
//                System.out.println("aadsda" + event.getActionCommand() + "ada");
            }
        };

        JMenu menu = new JMenu("File");
        JMenuItem menuItem = new JMenuItem("New", 'n');
        menuItem.addActionListener(afficherMenuListener);
        menu.add(menuItem);
        menuItem = new JMenuItem("Open", 'o');
        menuItem.addActionListener(afficherMenuListener);
        menu.add(menuItem);
        menuItem = new JMenuItem("Save", 's');
        menuItem.addActionListener(afficherMenuListener);
        menu.add(menuItem);
        this.add(menu);
    }
}
