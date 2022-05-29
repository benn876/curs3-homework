package ex2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    private String id;
    private Member member;
    private SubscriptionType subscriptionType;
    private Duration remainingTime;

}
