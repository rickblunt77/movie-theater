package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {

    TestConfig config;
    DailySchedule daySchedule;

    public ReservationTests() {
        this.config = new TestConfig();
        LocalDate scheduleDate = LocalDate.of(2022, 11, 1);
        config.configure(scheduleDate);
        this.daySchedule = config.getDaySchedule();
    }

    @Test
    void totalFee() {
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90)),
                daySchedule,
                LocalDateTime.now(),
                12.5,
                MovieSpecialType.SPECIAL
        );
        assertEquals(37.5, new Reservation(showing, 3).totalFee());
    }
}
