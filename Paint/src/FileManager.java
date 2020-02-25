import java.io.*;
import java.util.ArrayList;

public class FileManager {
    public static void saveShapesToFile(ArrayList<Shape> shapesOnBoard, File f) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
            out.writeObject(shapesOnBoard);
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println("Couldn't save");
            e.printStackTrace();
        }
    }

    public static ArrayList<Shape> loadShapesFromFile(File f){
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(f));
            ArrayList<Shape> shapesOnBoard = (ArrayList<Shape>) input.readObject();
            input.close();
            return shapesOnBoard;
        } catch (Exception e) {
            System.out.println("Couldn't load");
            e.printStackTrace();
            return null;
        }
    }
}
