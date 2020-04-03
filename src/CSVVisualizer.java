import java.io.File;
import java.io.IOException;

public class CSVVisualizer {
    private static File pathToTheCSVFile;
    private static String pythonInterpreterCommand;

    static {
        try {
            Process process = Runtime.getRuntime().exec("python");
            pythonInterpreterCommand = "python";
        } catch (IOException e) {
            try {
                Process process = Runtime.getRuntime().exec("python2");
                pythonInterpreterCommand = "python2";
            } catch (IOException e1) {
                try {
                    Process process = Runtime.getRuntime().exec("python3");
                    pythonInterpreterCommand = "python3";
                } catch (IOException ignored) {

                }
            }
        }
    }

    public static void main(String[] args) {

    }
}
