import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class CSVFileInformationReaderTest {

    @Test
    public void testCSVReading() {
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
            assertNotNull(new CSVFileInformationReader(
                    "/Users/user/Desktop/CSVVisualizer/testData/data.csv",
                    CSVVisualizer.getPythonInterpreterCommand()).runPythonScript().readLine()
            );
        } catch (IllegalCommandException | IOException e) {
            e.printStackTrace();
        }
    }

}