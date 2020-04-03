import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CSVTableInformationContainerTest {

    static {
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
    }

    private CSVTableInformationContainer container = new CSVTableInformationContainer(
            new CSVFileInformationReader(
                    "testData/simple.csv",
                    CSVVisualizer.getPythonInterpreterCommand()).runPythonScript()
    );

    public CSVTableInformationContainerTest() throws IOException, IllegalCommandException {
    }

    @Test
    public void getColumnNames() {
        assertArrayEquals(container.getColumnNames(), new String[]{"a", "b"});
    }

    @Test
    public void getTable() {
        assertArrayEquals(container.getTable(), new String[][]{{"1", "2"}, {"aaa", "xxx"}});
    }

    @Test
    public void getRows() {
        assertEquals(container.getRows(), 2);
    }

    @Test
    public void getCols() {
        assertEquals(container.getCols(), 2);
    }
}