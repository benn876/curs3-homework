package ex1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record ReportGenerator(PersonProvider personProvider, BuildRanges ranges) {

    public void writeReport() throws IOException {
        List<Person> persons = personProvider.readPersons();
        Map<Range, List<Person>> result = persons.stream()
                .collect(Collectors.groupingBy(this::getRange));

        writeToFile(result);
    }

    private void writeToFile(Map<Range, List<Person>> result) throws IOException {
        FileWriter fileWriter = new FileWriter(personProvider.fileName());
        PrintWriter printWriter = new PrintWriter(fileWriter);

        result.forEach((key, value) -> {
            System.out.println(key + " " + value);
            printWriter.println(key + ":" + value);
        });
        printWriter.close();
    }

    private <K, T> Range getRange(Person person) {
        return ranges.getRanges().stream()
                .filter(range -> range.from() < person.age() && person.age() < range.to())
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

}
