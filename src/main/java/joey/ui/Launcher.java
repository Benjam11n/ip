package joey.ui;
import joey.Main;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Main.launch(Main.class, args);
    }
}
