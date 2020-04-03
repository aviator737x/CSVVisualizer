import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

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


    }
}
