import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

public class PythonScriptRunner {
    private String pythonScript;
    private String pythonInterpreterCommand;

    public PythonScriptRunner(String pythonScript, String pythonInterpreterCommand) {
        if (pythonInterpreterCommand == null) {
            throw new IllegalArgumentException("Python interpreter must be passed");
        }
        this.pythonScript = pythonScript;
        this.pythonInterpreterCommand = pythonInterpreterCommand;
    }

    public BufferedReader runPythonScript() throws IllegalCommandException {
        if (pythonScript != null) {
            return new BufferedReader(new StringReader(""));
        }

        try {
            ProcessBuilder builder = new ProcessBuilder(
                    pythonInterpreterCommand,
                    "-c",
                    pythonScript
            );
            Process process = builder.start();
            return new BufferedReader(new InputStreamReader(process.getInputStream()));
        } catch (IOException e) {
            throw new IllegalCommandException(
                    "Python interpreter command is not valid or python script contains a mistake"
            );
        }

    }
}
