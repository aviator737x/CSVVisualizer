import java.io.BufferedReader;
import java.io.IOException;

public class CSVInformationParser {

    /**
     * @param reader stream received from python script containing number of rows at first line
     * @return number of rows in table
     * @throws IOException
     */
    public static int parseRows(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    /**
     * @param reader stream received from python script containing number of cols at first line
     * @return number of cols in table
     * @throws IOException
     */
    public static int parseCols(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    /**
     * @param reader stream received from python script containing array of column names at first line
     * @return array of column names in table
     * @throws IOException
     */
    public static String[] parseColumnNames(BufferedReader reader) throws IOException {
        String[] listOfColumns = reader.readLine().split(", ");
        listOfColumns[0] = listOfColumns[0].replaceFirst("\\[", "");
        listOfColumns[listOfColumns.length - 1] = listOfColumns[listOfColumns.length - 1]
                .replaceFirst("]", "");
        return listOfColumns;
    }


    /**
     * @param reader stream received from python script containing table with data at first line in json format
     * @param cols number of columns
     * @param rows number of rows
     * @param columnNames array with names of columns
     * @return received table in array format
     * @throws IOException
     */
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
