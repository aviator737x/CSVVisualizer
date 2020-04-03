public class CSVFileInformationReader extends PythonScriptRunner {

    private static String createPythonScriptForCSVFileInformationReading(String pathToTheCSVFile) {
        String pythonScript = "import pandas as pd;" +
                "df = pd.read_csv(\"" + pathToTheCSVFile + "\");" +
                "pd.options.display.max_rows = df.shape[0];" +
                "pd.options.display.max_columns = df.shape[1];" +
                "print(df.shape[0]);" +
                "print(df.shape[1]);" +
                "print(list(df));" +
                "js = df.to_json();"+
                "print(js);";
        return pythonScript;
    }

    public CSVFileInformationReader(String pathToTheCSVFile, String pythonInterpreterCommand) {
        super(createPythonScriptForCSVFileInformationReading(pathToTheCSVFile), pythonInterpreterCommand);
    }

}
