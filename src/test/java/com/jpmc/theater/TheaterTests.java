package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TheaterTests {
    Theater theater;

    public TheaterTests() {
        TestConfig config = new TestConfig();
        LocalDate scheduleDate = LocalDate.of(2022, 11, 1);
        config.configure(scheduleDate);
        this.theater = config.getTheater();
    }

    @Test
    void printMovieSchedule() {
        theater.printSchedule(LocalDate.of(2022, 11, 9));
    }

    @Test
    void printJSONMovieSchedule() {
        theater.printJSONSchedule(LocalDate.of(2022, 11, 9));
    }
}
