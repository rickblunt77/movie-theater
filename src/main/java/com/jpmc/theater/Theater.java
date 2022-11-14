package com.jpmc.theater;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Theater {


    private LocalDateProvider provider;
    private List<DailySchedule> schedule;

    public Theater(LocalDateProvider provider) {
        this.provider = provider;
    }

    public void addToSchedule(DailySchedule dailySchedule) {
        if (schedule == null) schedule = new ArrayList<>();
        schedule.add(dailySchedule);
    }

    public void printSchedule(LocalDate scheduleDate) {
        System.out.println(scheduleDate);
        System.out.println("===================================================");
        schedule.forEach(ds -> ds.getShowings().forEach(s -> {
                    if (scheduleDate.equals(s.getStartTime().toLocalDate())) {
                        System.out.println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " (" + humanReadableFormat(s.getMovie().getRunningTime()) + ") $" + s.getTicketPrice());
                    }
                }
        ));
        System.out.println("===================================================");
    }

    public void printJSONSchedule(LocalDate scheduleDate) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<ScheduleElement> scheduleElements = new ArrayList<>();
        schedule.forEach(ds -> ds.getShowings().forEach(s -> {
            if (scheduleDate.equals(s.getStartTime().toLocalDate())) {
                scheduleElements.add(new ScheduleElement(s.getSequenceOfTheDay(), s.getStartTime().toString(), s.getMovie().getTitle(), humanReadableFormat(s.getMovie().getRunningTime()), s.getTicketPrice()));
            }
        }));
        System.out.println(gson.toJson(scheduleElements));
    }

    private String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("%s hour%s %s minute%s", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    // (s) postfix should be added to handle plural correctly
    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        }
        else {
            return "s";
        }
    }

    public static void main(String[] args) {
        DailySchedule daySchedule = new DailySchedule(LocalDate.of(2022, 11, 9));
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90));
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85));
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95));
        LocalDate scheduleDate = LocalDate.of(2022, 11, 9);
        daySchedule.addShowing(turningRed, LocalDateTime.of(scheduleDate, LocalTime.of(9, 0)), 11, MovieSpecialType.NONE);
        daySchedule.addShowing(spiderMan, LocalDateTime.of(scheduleDate, LocalTime.of(11, 0)), 12.5, MovieSpecialType.SPECIAL);
        daySchedule.addShowing(theBatMan, LocalDateTime.of(scheduleDate, LocalTime.of(12, 50)), 11, MovieSpecialType.NONE);

        daySchedule.addShowing(turningRed, LocalDateTime.of(scheduleDate, LocalTime.of(14, 30)), 11, MovieSpecialType.NONE);
        daySchedule.addShowing(spiderMan, LocalDateTime.of(scheduleDate, LocalTime.of(16, 10)), 12.5, MovieSpecialType.SPECIAL);
        daySchedule.addShowing(theBatMan, LocalDateTime.of(scheduleDate, LocalTime.of(17, 50)), 11, MovieSpecialType.NONE);

        daySchedule.addShowing(turningRed, LocalDateTime.of(scheduleDate, LocalTime.of(19, 30)), 11, MovieSpecialType.NONE);
        daySchedule.addShowing(spiderMan, LocalDateTime.of(scheduleDate, LocalTime.of(21, 10)), 12.5, MovieSpecialType.SPECIAL);
        daySchedule.addShowing(theBatMan, LocalDateTime.of(scheduleDate, LocalTime.of(23, 0)), 11, MovieSpecialType.NONE);

        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.addToSchedule(daySchedule);
        theater.printSchedule(scheduleDate);
        theater.printJSONSchedule(scheduleDate);
    }
}
