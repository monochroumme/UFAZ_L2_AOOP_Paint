package Graphics;

import javax.swing.*;

public class TopMenu extends JMenuBar {
    public TopMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem newFile = new JMenuItem("New");
        JMenuItem openFile = new JMenuItem("Open");
        JMenuItem saveFile = new JMenuItem("Save");

        // configuring exit
        JMenuItem exit = new JMenuItem("Exit");
        exit.setToolTipText("Exit application");
        exit.addActionListener((e) -> System.exit(0));

        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(exit);

        add(fileMenu);
    }
}
