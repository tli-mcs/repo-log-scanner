import java.io.IOException;
import java.nio.file.*;
import java.nio.file.FileVisitOption.*;
import java.nio.file.FileVisitOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Set;

public class RepoLogScanner {
    public static void main(String[] args) {
        // Specify the root folder to start the search
        // Path repoRoot = Path.of("/path/to/your/folder");

        Path repoRoot = Paths.get("C:\\Ting\\orch-repos\\orch-colocation-master\\orch-colocation-master");

        try {
            Files.walkFileTree(repoRoot, Set.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE, new FileVisitor());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class FileVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            List<String> lines = Files.readAllLines(file);
            int totalLines = lines.size();

            for (int i = 0; i < totalLines; i++) {
                String line = lines.get(i).trim();
                if (line.startsWith("logger.")) {
                    // Process the lines that start with "logger."
                    System.out.println(file + ": " + line);
                    if(line.endsWith(")")){
                        continue;
                    }
                    // Check following lines until a line ending with ")"
                    int j = i + 1;
                    while (j < totalLines && !lines.get(j).trim().endsWith(")")) {
                        System.out.println(file + ": " + lines.get(j));
                        j++;
                    }
                    System.out.println(")");
                }
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
}
