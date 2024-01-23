import java.util.ArrayList;
import java.util.List;

public class RepoLog {
    String projectName;
    List<FileLog> fileLogs = new ArrayList<>();

    public RepoLog(String projectName) {
        this.projectName = projectName;
    }

    public void addFileLogItem(FileLog fLog) {
        fileLogs.add(fLog);
    }
}

class FileLog {
    String fileName;
    List<LogItem> fileLogs = new ArrayList<>();

    public FileLog(String fileName) {
        this.fileName = fileName;
    }

    public void addLogItem(int lineNumber, String logContent) {
        fileLogs.add(new LogItem(lineNumber, logContent));
    }
}

class LogItem {
    int lineNumber;
    String logContent;

    public LogItem(int lineNumber, String logContent) {
        this.lineNumber = lineNumber;
        this.logContent = logContent;
    }
}
