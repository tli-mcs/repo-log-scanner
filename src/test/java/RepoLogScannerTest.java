import org.junit.Assert;
import org.junit.Test;

public class RepoLogScannerTest {


    @Test
    public void testAddRepoToScan() throws Exception {
        RepoLogScanner repoLogScanner = new RepoLogScanner();
        repoLogScanner.addRepoToScan("orch-colocation", "C:\\Ting\\orch-repos\\orch-colocation-master\\orch-colocation-master");
        repoLogScanner.generateRepoLogsReport();
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
