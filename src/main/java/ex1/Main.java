package ex1;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        ReportGenerator fileReportGenerator = new ReportGenerator(new FileReadPersons(), new BuildRanges(List.of(1, 5, 10, 20, 30)));
        ReportGenerator manualReportGenerator = new ReportGenerator(new ManuallyReadPersons(), new BuildRanges(List.of(1, 5, 10)));

        fileReportGenerator.writeReport();
        manualReportGenerator.writeReport();
    }
}
