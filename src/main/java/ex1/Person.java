package ex1;

import lombok.Builder;

@Builder
public record Person(String firstName, String lastName, int age) {
}
