import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class RepoLogScanner {
    Map<String, String> repos = new HashMap<>();
    public void addRepoToScan(String repoName, String repoPath){
        repos.put(repoName,repoPath);
    }

    public RepoLog repoLog(String repoName, String repoPath){
        RepoLog repoLog = new RepoLog(repoName);
        Path repoRoot = Paths.get(repoPath);

        try {
            Files.walkFileTree(repoRoot, Set.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE, new FileVisitor(repoLog));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repoLog;
    }
    public List<RepoLog> repoLogs(){
        List<RepoLog> repoLogs = new ArrayList<>();
        for(Map.Entry<String, String> entry: repos.entrySet()){
            repoLogs.add(repoLog(entry.getKey(),entry.getValue()));
        }
        return repoLogs;
    }

    public void generateRepoLogsReport() throws JsonProcessingException {
        AllRepoLogs allRepoLogs = new AllRepoLogs();
        allRepoLogs.setRepoLogs(repoLogs());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String jsonString = objectMapper.writeValueAsString(allRepoLogs);
        System.out.println(jsonString);
    }
}

class AllRepoLogs{
    public List<RepoLog> getRepoLogs() {
        return repoLogs;
    }

    public void setRepoLogs(List<RepoLog> repoLogs) {
        this.repoLogs = repoLogs;
    }

    List<RepoLog> repoLogs;
}

class FileVisitor extends SimpleFileVisitor<Path> {
    RepoLog repoLog;
    public FileVisitor(RepoLog repoLog){
        this.repoLog = repoLog;
    }
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        List<String> lines = Files.readAllLines(file);
        int totalLines = lines.size();
        FileLog fileLog = new FileLog(file.toString());

        for (int i = 0; i < totalLines; i++) {
            String line = lines.get(i).trim();
            if (line.startsWith("logger.")||line.endsWith("JobManager.add_job_log")) {
                StringBuilder sb = new StringBuilder();
                sb.append(line);

                if(line.endsWith(")")){
                    fileLog.addLogItem(i,sb.toString());
                    continue;
                }

                int j = i + 1;
                while (j < totalLines && !lines.get(j).trim().endsWith(")")) {
                    sb.append(lines.get(j)).append("\n");
                    j++;
                }
                sb.append(")");
                fileLog.addLogItem(i,sb.toString());
            }
        }
        if(!fileLog.fileLogs.isEmpty()){
            repoLog.addFileLogItem(fileLog);
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        // Handle the case where file visit failed
        System.err.println("Error accessing file: " + file);
        exc.printStackTrace();
        return FileVisitResult.CONTINUE;
    }
}
