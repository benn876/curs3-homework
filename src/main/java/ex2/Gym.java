package ex2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public record Gym(List<Subscription> subscriptions) {
    public Gym(List<Subscription> subscriptions) {
        this.subscriptions = new ArrayList<>(subscriptions);
    }

    public List<Member> getMembers() {
        return this.subscriptions.stream()
                .map(Subscription::getMember)
                .toList();
    }

    public void addSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions.addAll(subscriptions);
    }

    public void registerTimeSpent(String memberId, Duration timeSpent) {
        Subscription currentSubscription = getSubscriptionForMember(memberId);
        currentSubscription.setRemainingTime(currentSubscription.getRemainingTime().minus(timeSpent));
    }

    public void addTimeToMember(String memberId, Duration timeAdded) {
        Subscription currentSubscription = getSubscriptionForMember(memberId);
        currentSubscription.setRemainingTime(currentSubscription.getRemainingTime().plus(timeAdded));
    }

    public Subscription getSubscriptionForMember(String memberId) {
        return this.subscriptions.stream()
                .filter(subscription -> subscription.getMember().id().equals(memberId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("The member with id:" + memberId + " was not found"));
    }

    public Duration getTotalRemainingTime() {
        int totalMillies = this.subscriptions.stream()
                .map(Subscription::getRemainingTime)
                .map(Duration::toMillis)
                .mapToInt(Math::toIntExact)
                .sum();
        return Duration.ofMillis(totalMillies);
    }

    public double getAverageAge() {
        OptionalDouble average = getMembers().stream()
                .map(member -> Period.between(member.birthDay(), LocalDate.now()).getYears())
                .mapToDouble(age -> age)
                .average();

        return average.isPresent() ? average.getAsDouble() : 0;
    }

    public Integer getMaxAge() {
        OptionalInt max = getMembers().stream()
                .map(member -> Period.between(member.birthDay(), LocalDate.now()).getYears())
                .mapToInt(age -> age)
                .max();
        return max.isPresent() ? max.getAsInt() : 0;
    }

    public Integer getMinAge() {
        OptionalInt min = getMembers().stream()
                .map(member -> Period.between(member.birthDay(), LocalDate.now()).getYears())
                .mapToInt(age -> age)
                .min();
        return min.isPresent() ? min.getAsInt() : 0;
    }

    public void writeReport() throws IOException {
        Map<ReportLabel, List<Subscription>> result = this.subscriptions.stream()
                .collect(Collectors.groupingBy(this::getLabel));
        writeToFile(result);
    }

    private void writeToFile(Map<ReportLabel, List<Subscription>> result) throws IOException {
        FileWriter fileWriter = new FileWriter("remaining-time-report-" + LocalDate.now() + ".txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        result.forEach((key, value) -> {
            printWriter.println(key + ":" + value.stream()
                    .map(subscription -> subscription.getMember().name())
                    .toList());
        });
        printWriter.close();
    }

    private <K, T> ReportLabel getLabel(Subscription subscription) {
        return Arrays.stream(ReportLabel.values())
                .filter(label -> label.getFromRemainingTime().toMillis() < subscription.getRemainingTime().toMillis() &&
                        subscription.getRemainingTime().toMillis() < label.getToRemainingTime().toMillis())
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

}
