package ex1;

import java.util.List;

public class ManuallyReadPersons implements PersonProvider {
    private static final String FILENAME = "manuallyProvidedCo.txt";

    @Override
    public List<Person> readPersons() {
        return List.of(Person.builder()
                .lastName("Beni")
                .firstName("Caba")
                .age(26)
                .build());
    }

    @Override
    public String fileName() {
        return FILENAME;
    }
}
