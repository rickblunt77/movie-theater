package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TestConfig {
    private DailySchedule daySchedule;
    private Theater theater;

    public void configure(LocalDate scheduleDate) {
        this.daySchedule = new DailySchedule(scheduleDate);
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90));
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85));
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95));

        daySchedule.addShowing(turningRed, LocalDateTime.of(scheduleDate, LocalTime.of(9, 0)), 11, MovieSpecialType.NONE);
        daySchedule.addShowing(spiderMan, LocalDateTime.of(scheduleDate, LocalTime.of(10, 40)), 12.5, MovieSpecialType.NONE);
        daySchedule.addShowing(theBatMan, LocalDateTime.of(scheduleDate, LocalTime.of(12, 50)), 11, MovieSpecialType.NONE);

        daySchedule.addShowing(turningRed, LocalDateTime.of(scheduleDate, LocalTime.of(14, 30)), 11, MovieSpecialType.SPECIAL);
        daySchedule.addShowing(spiderMan, LocalDateTime.of(scheduleDate, LocalTime.of(16, 10)), 12.5, MovieSpecialType.SPECIAL);
        daySchedule.addShowing(theBatMan, LocalDateTime.of(scheduleDate, LocalTime.of(17, 50)), 11, MovieSpecialType.NONE);

        daySchedule.addShowing(turningRed, LocalDateTime.of(scheduleDate, LocalTime.of(19, 30)), 11, MovieSpecialType.NONE);
        daySchedule.addShowing(spiderMan, LocalDateTime.of(scheduleDate, LocalTime.of(21, 10)), 12.5, MovieSpecialType.SPECIAL);
        daySchedule.addShowing(theBatMan, LocalDateTime.of(scheduleDate, LocalTime.of(23, 0)), 11, MovieSpecialType.NONE);

        this.theater = new Theater(LocalDateProvider.singleton());
        theater.addToSchedule(daySchedule);
    }

    public DailySchedule getDaySchedule() {
        return daySchedule;
    }

    public Theater getTheater() {
        return theater;
    }

}
