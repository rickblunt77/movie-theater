package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowingTests {
    Movie movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90));

    @Test
    void specialMovieWith20PercentDiscount() {
        Showing showing = new Showing(movie, 5, LocalDateTime.of(LocalDate.of(2022, 9, 1), LocalTime.of(1,0)), 12.5, MovieSpecialType.SPECIAL);
        assertEquals(10, showing.calculateTicketPrice());
    }

    @Test
    void firstShowMovieWithDiscount() {
        Showing showing = new Showing(movie, 1, LocalDateTime.of(LocalDate.of(2022, 9, 1), LocalTime.of(1,0)), 12.5, MovieSpecialType.NONE);
        assertEquals(9.5, showing.calculateTicketPrice());
    }

    @Test
    void secondShowMovieWithDiscount() {
        Showing showing = new Showing(movie, 2, LocalDateTime.of(LocalDate.of(2022, 9, 1), LocalTime.of(1,0)), 12.5, MovieSpecialType.NONE);
        assertEquals(10.5, showing.calculateTicketPrice());
    }

    @Test
    void matineeMovieWith25PercentDiscount() {
        Showing showing = new Showing(movie, 5, LocalDateTime.of(LocalDate.of(2022, 9, 1), LocalTime.of(11,0)), 12.5, MovieSpecialType.NONE);
        assertEquals(9.375, showing.calculateTicketPrice());
    }

    @Test
    void dayOfMonthMovieWithDiscount() {
        Showing showing = new Showing(movie, 5, LocalDateTime.of(LocalDate.of(2022, 9, 7), LocalTime.of(1,0)), 12.5, MovieSpecialType.NONE);
        assertEquals(11.5, showing.calculateTicketPrice());
    }

    @Test
    void maxDiscountApplied() {
        Showing showing = new Showing(movie, 1, LocalDateTime.of(LocalDate.of(2022, 9, 7), LocalTime.of(11,0)), 12.5, MovieSpecialType.SPECIAL);
        assertEquals(9.375, showing.calculateTicketPrice());
    }
}
