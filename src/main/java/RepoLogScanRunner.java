import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.FileVisitOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Set;

public class RepoLogScanRunner {

    public static void main(String[] args) throws JsonProcessingException {
        // Specify the root folder to start the search
        // Path repoRoot = Path.of("/path/to/your/folder");

//        Path repoRoot = Paths.get("C:\\Ting\\orch-repos\\orch-colocation-master\\orch-colocation-master");
//        RepoLog repoLog = new RepoLog("orch-colocation");
//
//        try {
//            Files.walkFileTree(repoRoot, Set.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE, new FileVisitor(repoLog));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//        String jsonString = objectMapper.writeValueAsString(repoLog);
//        System.out.println(jsonString);

    }

//
//    static class FileVisitor extends SimpleFileVisitor<Path> {
//        RepoLog repoLog;
//        public FileVisitor(RepoLog repoLog){
//            this.repoLog = repoLog;
//        }
//        @Override
//        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//            List<String> lines = Files.readAllLines(file);
//            int totalLines = lines.size();
//            FileLog fileLog = new FileLog(file.toString());
//
//            for (int i = 0; i < totalLines; i++) {
//                String line = lines.get(i).trim();
//                if (line.startsWith("logger.")) {
//                    StringBuilder sb = new StringBuilder();
//                    sb.append(line);
//
//                    if(line.endsWith(")")){
//                        fileLog.addLogItem(i,sb.toString());
//                        continue;
//                    }
//
//                    int j = i + 1;
//                    while (j < totalLines && !lines.get(j).trim().endsWith(")")) {
//                        sb.append(lines.get(j)).append("\n");
//                        j++;
//                    }
//                    sb.append(")");
//                    fileLog.addLogItem(i,sb.toString());
//                }
//            }
//            if(!fileLog.fileLogs.isEmpty()){
//                repoLog.addFileLogItem(fileLog);
//            }
//
//            return FileVisitResult.CONTINUE;
//        }
//
//        @Override
//        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
//            // Handle the case where file visit failed
//            System.err.println("Error accessing file: " + file);
//            exc.printStackTrace();
//            return FileVisitResult.CONTINUE;
//        }
//    }
}
