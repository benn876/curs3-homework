package ex1;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class FileReadPersons implements PersonProvider {
    private static final String FILENAME = "fileProvidedCo.txt";

    @Override
    public List<Person> readPersons() {
        List<Person> persons = new ArrayList<>();
        File file = new File("C:\\projects\\fasttrackIT\\jf02\\curs3-homework\\people.txt");
        try {
            Files.lines(file.toPath())
                    .forEach(line -> {
                        String[] splitedLine = line.split(",");
                        persons.add(Person.builder()
                                .firstName(splitedLine[0])
                                .lastName(splitedLine[1])
                                .age(parseInt(splitedLine[2]))
                                .build());
                    });
        } catch (Exception ignored) {
        }

        return persons;
    }

    @Override
    public String fileName() {
        return FILENAME;
    }
}
