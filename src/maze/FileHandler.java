package maze;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FileHandler {

    public static void saveMaze(Maze maze) {
        System.out.println("Please, enter a file path.");
        String fileName = InputHandler.getFilePath();

        try {
            SerializationUtils.serialize(maze, fileName);
            System.out.println("Maze is successfully saved.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static Maze loadMaze() {
        System.out.println("Please, enter a file path.");
        String fileName = InputHandler.getFilePath();
        try {
            Maze maze =  (Maze) SerializationUtils.deserialize(fileName);
            System.out.println("Maze is successfully loaded.");
            return maze;
        } catch (FileNotFoundException e) {
            System.out.println("The file " + fileName + " does not exist");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Cannot load the maze. It has an invalid format");
        }
        return null;
    }
}
