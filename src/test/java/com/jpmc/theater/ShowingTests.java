package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowingTests {
    Movie movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90));

    //These tests validate that the correct discount is applied when calculating the ticket price
    //We use a specified date and time otherwise tests may fail on specific days or times
    @Test
    void specialMovieWith20PercentDiscount() {
        Showing showing = new Showing(movie, 5, LocalDateTime.of(LocalDate.of(2022, 9, 1), LocalTime.of(1,0)), 12.5, MovieSpecialType.SPECIAL);
        assertEquals(10, showing.calculateTicketPrice());
    }

    @Test
    void firstShowMovieWith$3Discount() {
        Showing showing = new Showing(movie, 1, LocalDateTime.of(LocalDate.of(2022, 9, 1), LocalTime.of(1,0)), 12.5, MovieSpecialType.NONE);
        assertEquals(9.5, showing.calculateTicketPrice());
    }

    @Test
    void secondShowMovieWith$2Discount() {
        Showing showing = new Showing(movie, 2, LocalDateTime.of(LocalDate.of(2022, 9, 1), LocalTime.of(1,0)), 12.5, MovieSpecialType.NONE);
        assertEquals(10.5, showing.calculateTicketPrice());
    }

    @Test
    void matineeMovieWith25PercentDiscount() {
        Showing showing = new Showing(movie, 5, LocalDateTime.of(LocalDate.of(2022, 9, 1), LocalTime.of(11,0)), 12.5, MovieSpecialType.NONE);
        assertEquals(9.375, showing.calculateTicketPrice());
    }

    @Test
    void dayOfMonthMovieWith$1Discount() {
        Showing showing = new Showing(movie, 5, LocalDateTime.of(LocalDate.of(2022, 9, 7), LocalTime.of(1,0)), 12.5, MovieSpecialType.NONE);
        assertEquals(11.5, showing.calculateTicketPrice());
    }

    @Test
//    This test validates that the highest discount is applied if the showing is eligible for multiple discounts
    void maxDiscountApplied() {
        Showing showing = new Showing(movie, 1, LocalDateTime.of(LocalDate.of(2022, 9, 7), LocalTime.of(11,0)), 12.5, MovieSpecialType.SPECIAL);
        assertEquals(9.375, showing.calculateTicketPrice());
    }
}
