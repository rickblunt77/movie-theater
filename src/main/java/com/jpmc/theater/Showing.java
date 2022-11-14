package com.jpmc.theater;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Showing implements Comparable<Showing> {
    private Movie movie;
    private DailySchedule schedule;
    private LocalDateTime showStartTime;
    private double ticketPrice;
    private MovieSpecialType movieSpecialType;

    public Showing(Movie movie, DailySchedule schedule, LocalDateTime showStartTime, double ticketPrice,
                   MovieSpecialType movieSpecialType) {
        this.movie = movie;
        this.schedule = schedule;
        this.showStartTime = showStartTime;
        this.ticketPrice = ticketPrice;
        this.movieSpecialType = movieSpecialType;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public double getMovieFee() {
        return getTicketPrice();
    }

    public int getSequenceOfTheDay() {
        return schedule.getShowingSequenceOfTheDay(this);
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public double calculateTicketPrice() {
        return ticketPrice - getDiscount();
    }

    private double getDiscount() {
        double specialDiscount = getSpecialDiscount();
        double sequenceDiscount = getSequenceDiscount();
        double matineeDiscount = getMatineeDiscount();
        double dayOfMonthDiscount = getDayOfMonthDiscount();
        // biggest discount wins
        return Math.max(specialDiscount, Math.max(sequenceDiscount, Math.max(matineeDiscount, dayOfMonthDiscount)));
    }

    private double getSpecialDiscount() {
        double specialDiscount = 0;
        if (movieSpecialType == MovieSpecialType.SPECIAL) {
            specialDiscount = ticketPrice * 0.2;  // 20% discount for special movie
        }
        return specialDiscount;
    }

    private double getSequenceDiscount() {
        int sequenceOfTheDay = getSequenceOfTheDay();
        double sequenceDiscount = 0;
        if (sequenceOfTheDay == 1) {
            sequenceDiscount = 3; // $3 discount for 1st show
        } else if (sequenceOfTheDay == 2) {
            sequenceDiscount = 2; // $2 discount for 2nd show
        }
        return sequenceDiscount;
    }

    private double getMatineeDiscount() {
        double matineeDiscount = 0;
        LocalDateTime matineeStartTime = LocalDateTime.of(showStartTime.toLocalDate(), LocalTime.of(10, 59));
        LocalDateTime matineeEndTime = LocalDateTime.of(showStartTime.toLocalDate(), LocalTime.of(16, 1));
        if (showStartTime.isAfter(matineeStartTime) && showStartTime.isBefore(matineeEndTime)) {
            matineeDiscount = ticketPrice * 0.25; //25% discount for Matinée showing
        }
        return matineeDiscount;
    }

    private double getDayOfMonthDiscount() {
        return showStartTime.getDayOfMonth() == 7? 1 :0;
    }

    @Override
    public int compareTo(Showing o) {
        return getStartTime().compareTo(o.getStartTime());
    }
}
