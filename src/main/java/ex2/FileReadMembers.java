package ex2;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static java.util.UUID.randomUUID;

public class FileReadMembers {
    private static final String FILENAME = "fileProvidedCoGym.txt";

    public List<Member> readMembers() {
        List<Member> members = new ArrayList<>();
        File file = new File("C:\\projects\\fasttrackIT\\jf02\\curs3-homework\\members.txt");
        try {
            Files.lines(file.toPath())
                    .forEach(line -> {
                        String[] splitedLine = line.split(",");
                        members.add(Member.builder()
                                .id(valueOf(randomUUID()))
                                .name(splitedLine[0])
                                .birthDay(LocalDate.of(parseInt(splitedLine[1]), parseInt(splitedLine[2]), parseInt(splitedLine[3])))
                                .build());
                    });
        } catch (Exception ignored) {
        }

        return members;
    }

    public String fileName() {
        return FILENAME;
    }
}
