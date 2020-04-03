import javax.swing.*;
import java.awt.*;

/**
 * Class for visualization of arrays with use of JTable
 */
public class CSVTable extends JFrame {
    private static CSVTable instance = null;
    private JTable tableToVisualizeCSVFile;
    private Object[][] tableData = new String[1000][1000];
    private Object[] columnsHeader = new String[1000];


    private CSVTable(String[][] array, String[] columnsHeader) {
        super("The received CSV file visualization");
        this.tableData = array;
        this.columnsHeader = columnsHeader;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        tableToVisualizeCSVFile = new JTable(array, columnsHeader);
        tableToVisualizeCSVFile.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableToVisualizeCSVFile.setShowGrid(true);
        tableToVisualizeCSVFile.setGridColor(Color.BLACK);
        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(tableToVisualizeCSVFile, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
        setContentPane(contents);
        setSize(500, 400);
        setVisible(true);
        instance = this;
    }

    /**
     * @param array table to visualize
     * @param columnsHeader array of column names
     */
    public static void visualizeTable(String[][] array, String[] columnsHeader) {
        if (instance == null) {
            new CSVTable(array, columnsHeader);
        }
    }
}
