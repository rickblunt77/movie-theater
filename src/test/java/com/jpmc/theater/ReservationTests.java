package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        assertEquals(37.5, new Reservation(showing, 3).totalFee());
    }
}
