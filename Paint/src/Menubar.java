import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class Menubar extends JMenuBar{

    public Menubar() {

        JMenu file_menu = new JMenu("File");

        // new menu - reconstructs canvas
        JMenuItem i_new = new JMenuItem("New", 'n');
        i_new.addActionListener(e -> {
            MainWindow.canvas.shapes.clear();
            MainWindow.canvas.repaint();
        });

        // open menu - tries to read a shapes array from file
        JMenuItem i_open = new JMenuItem("Open", 'o');
        i_open.addActionListener(e -> {
            ArrayList<Shape> al;
            JFileChooser fc = new JFileChooser();
            int retval = fc.showOpenDialog(i_open);

            if(retval == JFileChooser.APPROVE_OPTION){

                File file = fc.getSelectedFile();
                if(file == null) return;

                al = MainWindow.loadShapesFromFile(file);

                // if reading canvas failed, do nothing
                if(al != null) MainWindow.canvas.shapes = al;

                MainWindow.canvas.repaint();
            }
        });

        // save menu - saves current shapes array to file
        JMenuItem i_save = new JMenuItem("Save", 's');
        i_save.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int retval = fileChooser.showSaveDialog(i_save);

            if (retval == JFileChooser.APPROVE_OPTION) {

                File file = fileChooser.getSelectedFile();

                if (file == null) return;

                // check the extension
                if(!file.getName().toLowerCase().endsWith(".drg")){
                    file = new File(file.getParentFile(), file.getName()+".drg");
                }

                // if file is okay, save to it
                MainWindow.saveShapesToFile(MainWindow.canvas.shapes, file);
                MainWindow.canvas.repaint();
            }
        });

        JMenuItem i_quit = new JMenuItem("Quit", 'q');
        i_quit.addActionListener(e -> {
            System.exit(0);

        });

        file_menu.add(i_new);
        file_menu.add(i_open);
        file_menu.add(i_save);
        file_menu.add(i_quit);

        this.add(file_menu);
    }

}