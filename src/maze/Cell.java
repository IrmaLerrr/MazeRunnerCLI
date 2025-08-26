package maze;

import java.io.Serializable;

public class Cell implements Serializable {
    private final int row;
    private final int column;
    CellState state = CellState.BLOCKED;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return state.getValue();
    }
}