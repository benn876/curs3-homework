package ex2;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static ex2.SubscriptionType.GYM;
import static java.lang.String.valueOf;
import static java.util.UUID.randomUUID;

public class Main {
    public static void main(String[] args) throws IOException {
        FileReadMembers fileReadMembers = new FileReadMembers();
        List<Member> members = fileReadMembers.readMembers();
        Gym gym = new Gym(List.of());

        populateGym(members, gym);
        Member firstMember = members.get(0);
        gym.registerTimeSpent(firstMember.id(), Duration.ofHours(49));
        Subscription subscriptionForMember = gym.getSubscriptionForMember(firstMember.id());
        System.out.println(subscriptionForMember);

        gym.addTimeToMember(firstMember.id(), Duration.ofMinutes(90));
        System.out.println(subscriptionForMember);
        System.out.println(gym.getTotalRemainingTime());

        gym.writeReport();
    }

    private static void populateGym(List<Member> members, Gym gym) {
        List<Subscription> subscriptions = members.stream()
                .map(member -> Subscription.builder()
                        .id(valueOf(randomUUID()))
                        .member(member)
                        .subscriptionType(GYM)
                        .remainingTime(Duration.ofHours(50))
                        .build())
                .toList();
        gym.addSubscriptions(subscriptions);
    }
}
