package maze;

public enum CellState {
    EMPTY("  "),
    BLOCKED("██");

    private final String value;

    CellState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}