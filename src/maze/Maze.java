package maze;

import java.io.Serializable;
import java.util.*;

public class Maze implements Serializable {
    public static final int[][] DIRECTIONS = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    private Cell[][] board;
    private int rows;
    private int columns;
    private final transient Map<Cell, Cell> frontiers = new HashMap<>();
    private Cell cellIn;
    private Cell cellOut;

    private Maze() {
    }

    public static Maze createMaze() {
        Maze maze = new Maze();

        System.out.println("Please, enter the size of a maze");
        try {
            maze.rows = InputHandler.getNum(5, 50);
            maze.columns = InputHandler.getNum(5, 50);
        } catch (InputMismatchException ex) {
            System.out.println("Incorrect bounds. Please enter a number from 5 to 50");
            return null;
        }
        maze.createBoard();
        maze.createPaths();
        maze.createExits();
        System.out.println("New maze is successfully generated.");
        maze.printMaze();
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

    public void createBoard() {
        board = new Cell[rows][columns];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell(i, j);
            }
        }
    }

    public void createPaths() {
        Random random = new Random();
        int r = random.nextInt(1, rows - 2) / 2 * 2 + 1;
        int c = random.nextInt(1, columns - 2) / 2 * 2 + 1;
        Cell seed = board[r][c];
        seed.setState(CellState.EMPTY);
        addFrontier(seed);
        while (!frontiers.isEmpty()) {
            Map.Entry<Cell, Cell> newFrontier = chooseFrontier();
            newFrontier.getKey().setState(CellState.EMPTY);
            newFrontier.getValue().setState(CellState.EMPTY);
            addFrontier(newFrontier.getKey());
        }
    }

    public void createExits() {
        cellIn = createExit();
        cellOut = createExit();
    }

    public Cell createExit() {
        Random random = new Random();
        int seed = random.nextInt(4);
        Cell cell = switch (seed) {
            case 0 -> {
                int r = random.nextInt(2, rows - 2) / 2 * 2 + 1;
                yield board[r][0];
            }
            case 1 -> {
                int c = random.nextInt(2, columns - 2) / 2 * 2 + 1;
                yield board[0][c];
            }
            case 2 -> {
                int r = random.nextInt(2, rows - 2) / 2 * 2 + 1;
                if (rows % 2 == 0) board[r][rows - 2].setState(CellState.EMPTY);
                yield board[r][rows - 1];
            }
            case 3 -> {
                int c = random.nextInt(2, columns - 2) / 2 * 2 + 1;
                if (columns % 2 == 0) board[columns - 2][c].setState(CellState.EMPTY);
                yield board[columns - 1][c];
            }
            default -> throw new IllegalStateException("Unexpected value: " + seed);
        };
        cell.setState(CellState.EMPTY);
        return cell;
    }

    public void printMaze() {
        for (Cell[] cells : board) {
            for (Cell cell : cells) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }

    public void findEscape() {
        dfsRecursiveUtil(cellIn);
        for (Cell cell : visited) {
            cell.setState(CellState.PATH);
        }
        printMaze();
        for (Cell cell : visited) {
            cell.setState(CellState.EMPTY);
        }
    }

    List<Cell> visited = new ArrayList<>();

    private boolean dfsRecursiveUtil(Cell start) {
        visited.add(start);
//        start.setState(CellState.PATH);
        List<Cell> neighbors = findNeighbor(start);
        if (neighbors.isEmpty()) {
            return start == cellOut;
        } else {
            for (Cell neighbor : neighbors) {
                if (dfsRecursiveUtil(neighbor)) return true;
                else visited.remove(neighbor);
            }
        }
        return false;
    }

    public List<Cell> findNeighbor(Cell cell) {
        List<Cell> list = new ArrayList<>();
        int row = cell.getRow();
        int column = cell.getColumn();
        for (int[] dir : DIRECTIONS) {
            int newRow = row + dir[0];
            int newColumn = column + dir[1];
            if (neighborIsValid(newRow, newColumn) && !visited.contains(board[newRow][newColumn])) {
                list.add(board[newRow][newColumn]);
            }
        }
        return list;
    }

    public boolean neighborIsValid(int row, int column) {
        if (row < 0 || row > rows - 1) return false;
        if (column < 0 || column > columns - 1) return false;
        return board[row][column].getState() == CellState.EMPTY;
    }
}