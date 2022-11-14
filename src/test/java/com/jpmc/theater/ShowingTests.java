package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowingTests {
    TestConfig config;
    DailySchedule daySchedule;

    public ShowingTests() {
        this.config = new TestConfig();
        LocalDate scheduleDate = LocalDate.of(2022, 11, 1);
        config.configure(scheduleDate);
        this.daySchedule = config.getDaySchedule();
    }

    //These tests validate that the correct discount is applied when calculating the ticket price
    //We use a specified date and time otherwise tests may fail on specific days or times
    @Test
    void specialMovieWith20PercentDiscount() {
        //Movie with sequence 5 has Special Discount Applied
        Showing showing = daySchedule.getShowingBySequence(5);
        assertEquals(10, showing.calculateTicketPrice());
    }

    @Test
    void firstShowMovieWith$3Discount() {
        //Movie is first of the day and so $3 discount should be applied
        Showing showing = daySchedule.getShowingBySequence(1);
        assertEquals(8, showing.calculateTicketPrice());
    }

    @Test
    void secondShowMovieWith$2Discount() {
        //Movie is second of the day and so $2 discount should be applied
        Showing showing = daySchedule.getShowingBySequence(2);
        assertEquals(10.5, showing.calculateTicketPrice());
    }

    @Test
    void matineeMovieWith25PercentDiscount() {
        //Movie start time falls within matinee period and so 25% discount should be applied
        Showing showing = daySchedule.getShowingBySequence(3);
        assertEquals(8.25, showing.calculateTicketPrice());
    }

    @Test
    void dayOfMonthMovieWith$1Discount() {
        //Here we create a new Schedule for the 7th of any month, the resulting movies will have the day of month discount applied
        LocalDate scheduleDate = LocalDate.of(2022, 11, 7);
        config.configure(scheduleDate);
        this.daySchedule = config.getDaySchedule();
        Showing showing = daySchedule.getShowingBySequence(6);
        assertEquals(10, showing.calculateTicketPrice());
    }

    @Test
    //This test validates that the highest discount is applied if the showing is eligible for multiple discounts
    void maxDiscountApplied() {
        //This movie with sequence 4 will have the special, matinee and day of month discounts applied.
        LocalDate scheduleDate = LocalDate.of(2022, 11, 7);
        config.configure(scheduleDate);
        this.daySchedule = config.getDaySchedule();
        Showing showing = daySchedule.getShowingBySequence(4);
        assertEquals(8.25, showing.calculateTicketPrice());
    }
}
