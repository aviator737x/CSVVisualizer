public class CSVFileInformationReader extends PythonScriptRunner {

    public CSVFileInformationReader(String pathToTheCSVFile, String pythonInterpreterCommand) {
        super(createPythonScriptForCSVFileInformationReading(pathToTheCSVFile), pythonInterpreterCommand);
    }

    /**
     * Reading of CSV file information using pandas library
     * @param pathToTheCSVFile path to the CSV file given by user
     * @return string of information in CSV file in the format: [number of rows]
     * \n[number of columns]\n[list of columns]\n[json of data in table]
     */
    private static String createPythonScriptForCSVFileInformationReading(String pathToTheCSVFile) {
        String pythonScript = "import pandas as pd;" +
                "df = pd.read_csv(\"" + pathToTheCSVFile + "\");" +
                "pd.options.display.max_rows = df.shape[0];" +
                "pd.options.display.max_columns = df.shape[1];" +
                "print(df.shape[0]);" +
                "print(df.shape[1]);" +
                "print(list(df));" +
                "js = df.to_json();" +
                "print(js);";
        return pythonScript;
    }

}
