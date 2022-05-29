package ex2;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record Member(String id, String name, LocalDate birthDay) {
}
