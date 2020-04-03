import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class CSVTableInformationContainerTest {

    CSVTableInformationContainer container = new CSVTableInformationContainer(
            new CSVFileInformationReader(
                    "/Users/user/Desktop/CSVVisualizer/testData/simpleCSV",
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