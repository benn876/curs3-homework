package ex1;

import java.util.List;

public interface PersonProvider {
    List<Person> readPersons();

    String fileName();
}
