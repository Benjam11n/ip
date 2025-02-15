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

    /**
     * Converts a String symbol to its corresponding TaskType enum value.
     *
     * @param symbol The single-character symbol representing the TaskType (e.g., "T", "D", "E").
     * @return The TaskType enum value, or null if the symbol is not recognized.
     */
    public static TaskType fromSymbol(String symbol) {
        for (TaskType type : TaskType.values()) {
            if (type.symbol.equals(symbol)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
