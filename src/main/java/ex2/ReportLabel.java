package ex2;

import java.time.Duration;

import static java.time.Duration.ofHours;

public enum ReportLabel {
    RED(Duration.ZERO, ofHours(10)),
    YELLOW(ofHours(10), ofHours(30)),
    GREEN(ofHours(30), ofHours(999999));

    private final Duration fromRemainingTime;
    private final Duration toRemainingTime;


    ReportLabel(Duration fromRemainingTime, Duration toRemainingTime) {
        this.fromRemainingTime = fromRemainingTime;
        this.toRemainingTime = toRemainingTime;
    }

    public Duration getFromRemainingTime() {
        return fromRemainingTime;
    }

    public Duration getToRemainingTime() {
        return toRemainingTime;
    }
}
