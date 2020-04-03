import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class PythonScriptRunnerTest {

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void runPythonScriptIllegalArgumentException() {
        new PythonScriptRunner("print(1)", null);
    }

    @org.junit.Test()
    public void runPythonScript() throws IllegalCommandException {
        if (CSVVisualizer.getPythonInterpreterCommand() == null) {
            try (Scanner scanner = new Scanner(System.in)) {
                while (CSVVisualizer.getPythonInterpreterCommand() == null) {
                    System.out.println("Python interpreter is not found... Please enter the python interpreter path");
                    String interpreter = scanner.nextLine();
                    if (new File(interpreter).exists()) {
                        CSVVisualizer.setPythonInterpreterCommand(scanner.nextLine());
                    }
                }
            }
        }

        try {
            String pythonScriptResult = new PythonScriptRunner("print(\"Hello\");",
                    CSVVisualizer.getPythonInterpreterCommand()).runPythonScript().readLine();
            assertEquals(pythonScriptResult, "Hello");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}