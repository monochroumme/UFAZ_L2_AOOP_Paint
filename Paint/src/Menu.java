import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class Menu extends JMenuBar{

    public Menu() {
        JMenu file_menu = new JMenu("File");

        JMenuItem newFile = new JMenuItem("New", 'n');
        newFile.addActionListener(e -> {
            PaintGraphics.board.shapesOnBoard.clear();
            PaintGraphics.board.repaint();
        });

        JMenuItem fileToOpen = new JMenuItem("Open", 'o');
        fileToOpen.addActionListener(e -> {
            ArrayList<Shape> shapes;
            JFileChooser fc = new JFileChooser();
            int msg = fc.showOpenDialog(fileToOpen);

            if(msg == JFileChooser.APPROVE_OPTION){

                File file = fc.getSelectedFile();
                if(file == null) return;

                shapes = FileManager.loadShapesFromFile(file);

                if(shapes != null) PaintGraphics.board.shapesOnBoard = shapes;

                PaintGraphics.board.repaint();
            }
        });

        JMenuItem fileToSave = new JMenuItem("Save", 's');
        fileToSave.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int msg = fileChooser.showSaveDialog(fileToSave);

            if (msg == JFileChooser.APPROVE_OPTION) {

                File file = fileChooser.getSelectedFile();

                if (file == null) return;

                if(!file.getName().toLowerCase().endsWith(".drg")){
                    file = new File(file.getParentFile(), file.getName()+".drg");
                }

                FileManager.saveShapesToFile(PaintGraphics.board.shapesOnBoard, file);
                PaintGraphics.board.repaint();
            }
        });

        JMenuItem quit = new JMenuItem("Quit", 'q');
        quit.addActionListener(e -> {
            System.exit(0);
        });

        file_menu.add(newFile);
        file_menu.add(fileToOpen);
        file_menu.add(fileToSave);
        file_menu.add(quit);

        this.add(file_menu);
    }

}