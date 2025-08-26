package maze;

import java.io.Serializable;
import java.util.*;

public class Maze implements Serializable {
    public static final int[][] DIRECTIONS = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    private Cell[][] board;
    private int rows;
    private int columns;
    private final transient Map<Cell, Cell> frontiers = new HashMap<>();

    private Maze(){}

    public static Maze createMaze() {
        Maze maze = new Maze();

        System.out.println("Please, enter the size of a maze");

        maze.rows = InputHandler.getNum(5, 50, "Incorrect bounds. Please enter a number from 5 to 50");
        maze.columns = InputHandler.getNum(5, 50, "Incorrect bounds. Please enter a number from 5 to 50");

        maze.createBoard();
        maze.createPaths();
        maze.createExits();
        System.out.println("New maze is successfully generated.");
        return maze;
    }

    public void addFrontier(Cell cell) {
        int row = cell.getRow();
        int column = cell.getColumn();
        for (int[] dir : DIRECTIONS) {
            int newRow = row + dir[0] + dir[0];
            int newColumn = column + dir[1] + dir[1];
            int interimRow = row + dir[0];
            int interimColumn = column + dir[1];
            if (frontierIsValid(newRow, newColumn)) {
                frontiers.put(board[newRow][newColumn], board[interimRow][interimColumn]);
            }
        }
    }
    public Map.Entry<Cell, Cell> chooseFrontier() {
        List<Map.Entry<Cell, Cell>> entries = new ArrayList<>(frontiers.entrySet());
        Random random = new Random();
        Map.Entry<Cell, Cell> randomEntry = entries.get(random.nextInt(entries.size()));

        frontiers.remove(randomEntry.getKey());
        return randomEntry;
    }

    public boolean frontierIsValid(int row, int column) {
        if (row < 1 || row > rows - 2) return false;
        if (column < 1 || column > columns - 2) return false;
        return board[row][column].getState() != CellState.EMPTY;
    }

    public void createBoard(){
        board = new Cell[rows][columns];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell(i, j);
            }
        }
    }

    public void createPaths(){
        Random random = new Random();
        int r = random.nextInt(1, rows - 2) / 2 * 2 + 1;
        int c = random.nextInt(1, columns - 2) / 2 * 2 + 1;
        Cell seed = board[r][c];
        seed.setState(CellState.EMPTY);
        addFrontier(seed);
        while (!frontiers.isEmpty()){
            Map.Entry<Cell, Cell> newfrontier = chooseFrontier();
            newfrontier.getKey().setState(CellState.EMPTY);
            newfrontier.getValue().setState(CellState.EMPTY);
            addFrontier(newfrontier.getKey());
        }
    }
    public void createExits(){
        board[1][0].setState(CellState.EMPTY);
        if (rows % 2 == 0) {
            board[rows - 3][columns - 2].setState(CellState.EMPTY);
            board[rows - 3][columns - 1].setState(CellState.EMPTY);
        } else {
            board[rows - 2][columns - 2].setState(CellState.EMPTY);
            board[rows - 2][columns - 1].setState(CellState.EMPTY);
        }
    }

    public void printMaze(){
        for (Cell[] cells : board) {
            for (Cell cell : cells) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }
}