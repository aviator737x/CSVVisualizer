import java.io.BufferedReader;
import java.io.IOException;

public class CSVInformationParser {

    public static int parseRows(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    public static int parseCols(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    public static String[] parseColumnNames(BufferedReader reader) throws IOException {
        String[] listOfColumns = reader.readLine().split(", ");
        listOfColumns[0] = listOfColumns[0].replaceFirst("\\[", "");
        listOfColumns[listOfColumns.length - 1] = listOfColumns[listOfColumns.length - 1]
                .replaceFirst("]", "");
        return listOfColumns;
    }

    public static String[][] parseTable(BufferedReader reader,
                                        int cols,
                                        int rows,
                                        String[] columnNames) throws IOException {
        String[][] table = new String[rows][cols];
        String[] columns = reader.readLine().split("},.*?\\{");
        columns[0] = columns[0].replaceFirst("\\{.*?\\{", "");
        columns[columns.length - 1] = columns[columns.length - 1]
                .substring(0, columns[columns.length - 1].length() - 2);
        for (int i = 0; i < cols; i++) {
            columnNames[i] = columnNames[i].substring(1, columnNames[i].length() - 1);
            String[] rowsOfColumns = columns[i].split(",\"[0-9]+?\":");
            rowsOfColumns[0] = rowsOfColumns[0].replaceFirst("\"[0-9]+?\":", "");
            for (int j = 0; j < rows; j++) {
                if (rowsOfColumns[j].startsWith("\"") && rowsOfColumns[j].endsWith("\"")) {
                    rowsOfColumns[j] = rowsOfColumns[j].substring(1, rowsOfColumns[j].length() - 1);
                }
                table[j][i] = rowsOfColumns[j];
            }
        }
        return table;
    }
}
