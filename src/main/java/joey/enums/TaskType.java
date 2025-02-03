package joey.enums;

/**
 * Represents the possible types of tasks currently supported by Joey.
 * Each task type has an associated single-character symbol used in storage format.
 */
public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String symbol;

    TaskType(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
