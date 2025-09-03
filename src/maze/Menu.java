package maze;

import java.util.InputMismatchException;

public class Menu {
    private Maze maze;

    public Menu() {
    }

    public void startMenu() {
        boolean running = true;
        while (running) {
            printMenu();
            int num = -1;
            while (num == -1) {
                try {
                    num = InputHandler.getOption(0, maze != null ? 5 : 2);
                } catch (InputMismatchException ex) {
                    System.out.println("Incorrect option. Please try again");
                }
            }
            switch (num) {
                case 0 -> running = false;
                case 1 -> maze = Maze.createMaze();
                case 2 -> {
                    Maze loadedMaze = FileHandler.loadMaze();
                    maze = loadedMaze == null ? maze : loadedMaze;
                }
                case 3 -> FileHandler.saveMaze(maze);
                case 4 -> maze.printMaze();
                case 5 -> maze.findEscape();
                default -> System.out.println("Error!");
            }
        }
    }

    public void printMenu() {
        if (maze != null) {
            System.out.println("""
                    === Menu ===
                    1. Generate a new maze
                    2. Load a maze
                    3. Save the maze
                    4. Display the maze
                    5. Find the escape
                    0. Exit""");
        } else {
            System.out.println("""
                    === Menu ===
                    1. Generate a new maze
                    2. Load a maze
                    0. Exit""");
        }
    }
}
