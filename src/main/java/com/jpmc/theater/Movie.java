package com.jpmc.theater;

import java.io.Serializable;
import java.time.Duration;
import java.util.Objects;

public class Movie {

    private final String title;
    private String description;
    private final Duration runningTime;

    public Movie(String title, Duration runningTime) {
        this.title = title;
        this.runningTime = runningTime;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return  Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime);
    }
}