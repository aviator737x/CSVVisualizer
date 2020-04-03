import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * The main class of program containing main method.
 * Tries to find python interpreter automatically, if not found asks user to enter the path to it.
 */
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
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please, enter the path to the CSV file you want to visualize");
            pathToTheCSVFile = new File(scanner.nextLine());
            while (!pathToTheCSVFile.exists()) {
                System.out.printf(
                        "File \"%s\" not found... Please, enter again the path to the CSV file you want to visualize"
                        , pathToTheCSVFile.toString()
                );
                pathToTheCSVFile = new File(scanner.nextLine());
            }
            while (pythonInterpreterCommand == null) {
                System.out.println("Python interpreter is not found... Please enter the python interpreter path");
                String interpreter = scanner.nextLine();
                if (new File(interpreter).exists()) {
                    pythonInterpreterCommand = scanner.nextLine();
                }
            }
        }

        try {
            CSVTableInformationContainer container = new CSVTableInformationContainer(
                    new CSVFileInformationReader(pathToTheCSVFile.toString(), pythonInterpreterCommand)
                            .runPythonScript()
            );
            CSVTable.visualizeTable(container.getTable(), container.getColumnNames());
        } catch (IOException | IllegalCommandException e) {
            e.printStackTrace();
        }
    }

    public static String getPythonInterpreterCommand() {
        return pythonInterpreterCommand;
    }

    public static void setPythonInterpreterCommand(String pythonInterpreterCommand) {
        CSVVisualizer.pythonInterpreterCommand = pythonInterpreterCommand;
    }
}
