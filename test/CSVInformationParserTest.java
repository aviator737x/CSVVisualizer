import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class CSVInformationParserTest {

    @Test
    public void parseRows() {
        BufferedReader reader = new BufferedReader(new StringReader("125"));
        try {
            assertEquals(CSVInformationParser.parseRows(reader), 125);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parseCols() {
        BufferedReader reader = new BufferedReader(new StringReader("125"));
        try {
            assertEquals(CSVInformationParser.parseCols(reader), 125);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parseColumnNames() {
        BufferedReader reader = new BufferedReader(new StringReader("[a, b]"));
        try {
            assertArrayEquals(CSVInformationParser.parseColumnNames(reader), new String[]{"a", "b"});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parseTable() {
        BufferedReader reader = new BufferedReader(new StringReader(
                "{\"a\":{\"0\":\"aaa\",\"1\":\"2\"},\"b\":{\"0\":\"x\",\"1\":\"5\"}}"));
        try {
            assertArrayEquals(
                    CSVInformationParser.parseTable(reader, 2, 2, new String[]{"\'a\'", "\'b\'"}),
                    new String[][]{{"aaa", "x"}, {"2", "5"}});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}