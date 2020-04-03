import java.io.BufferedReader;
import java.io.IOException;

public class CSVTableInformationContainer {
    private String[] columnNames;
    private String[][] table;
    private int rows;
    private int cols;

    public CSVTableInformationContainer(BufferedReader reader) throws IOException {
        rows = CSVInformationParser.parseRows(reader);
        cols = CSVInformationParser.parseCols(reader);
        columnNames = CSVInformationParser.parseColumnNames(reader);
        table = CSVInformationParser.parseTable(reader, cols, rows, columnNames);
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public String[][] getTable() {
        return table;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
