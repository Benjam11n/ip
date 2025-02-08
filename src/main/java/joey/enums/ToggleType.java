package joey.enums;

/**
 * Represents the possible types of toggle commands currently supported by Joey.
 */
public enum ToggleType {
    MARK("Marked"),
    UNMARK("Unmarked");

    private final String message;

    ToggleType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
