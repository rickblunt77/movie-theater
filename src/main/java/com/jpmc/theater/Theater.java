package com.jpmc.theater;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Theater {

    LocalDateProvider provider;
    private final List<Showing> showings;

    public Theater(LocalDateProvider provider) {
        this.provider = provider;

        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90));
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85));
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95));
        showings = List.of(
                new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0)), 11, MovieSpecialType.NONE),
                new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0)), 12.5, MovieSpecialType.SPECIAL),
                new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50)), 9, MovieSpecialType.NONE),

                new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30)), 11, MovieSpecialType.NONE),
                new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10)), 12.5, MovieSpecialType.SPECIAL),
                new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50)), 9, MovieSpecialType.NONE),

                new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30)), 11, MovieSpecialType.NONE),
                new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10)), 12.5, MovieSpecialType.SPECIAL),
                new Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0)), 9, MovieSpecialType.NONE)
        );
    }

    public void printSchedule() {
        System.out.println(provider.currentDate());
        System.out.println("===================================================");
        showings.forEach(s ->
                System.out.println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " (" + humanReadableFormat(s.getMovie().getRunningTime()) + ") $" + s.getTicketPrice())
        );
        System.out.println("===================================================");
    }

    public void printJSONSchedule() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<ScheduleElement> scheduleElements = new ArrayList<>();
        showings.forEach(s ->
                scheduleElements.add(new ScheduleElement(s.getSequenceOfTheDay(), s.getStartTime().toString(), s.getMovie().getTitle(), humanReadableFormat(s.getMovie().getRunningTime()), s.getTicketPrice()))
        );
        System.out.println(gson.toJson(scheduleElements));
    }

    public String humanReadableFormat(Duration duration) {
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
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule();
        theater.printJSONSchedule();
    }
}
