package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {

    @Test
    void totalFee() {
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90)),
                1,
                LocalDateTime.now(),
                12.5,
                MovieSpecialType.SPECIAL
        );
        assertTrue(new Reservation(showing, 3).totalFee() == 37.5);
    }
}
