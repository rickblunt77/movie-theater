package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheaterTests {
    Theater theater = new Theater(LocalDateProvider.singleton());
    @Test
    void printMovieSchedule() {
        theater.printSchedule();
    }

    @Test
    void printJSONMovieSchedule() {
        theater.printJSONSchedule();
    }
}
