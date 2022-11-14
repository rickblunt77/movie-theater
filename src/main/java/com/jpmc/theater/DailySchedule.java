package com.jpmc.theater;
import com.sun.jdi.request.DuplicateRequestException;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class DailySchedule {
    private List<Showing> showings;
    private LocalDate date;

    public DailySchedule(LocalDate date) {
        this.date = date;
    }

    public List<Showing> getShowings() {
        return showings;
    }

    public Showing addShowing(Movie movie, LocalDateTime dateTime, double ticketPrice, MovieSpecialType specialType) {
        if (showings == null) showings = new ArrayList<>();
        Showing showing = new Showing(movie, this, dateTime, ticketPrice, specialType);
        if (showings.contains(showing)) throw new DuplicateRequestException("The Showing for " + showing.getMovie().getTitle()
                + "at: " + showing.getStartTime() + " has already been added to the schedule");
        validateDate(showing.getStartTime());
        validateTime(showing.getStartTime());
        showings.add(showing);
        //We sort the showings so that we can calculate the sequence of the day from the start time.
        //As this is a O(n log(n)) operation we do it here when a new showing is added rather than each time
        //we call the getShowingSequenceOfTheDay() method as that may be called more times than this method which is only
        //called when we configure the schedule for the day.
        Collections.sort(showings);
        return showing;
    }

    public Showing getShowingBySequence(int sequence) {
        return showings.get(sequence - 1);
    }

    public int getShowingSequenceOfTheDay(Showing showing) {
        List<Showing> showings = getShowings();
        for (int i = 0; i < showings.size(); i++) {
            if (showings.get(i) == showing) return i + 1;
        }
        throw new NoSuchElementException("The showing for: " + showing.getMovie().getTitle() + " at: " + showing.getStartTime() + " is not in the schedule");
    }

    private void validateTime(LocalDateTime showingDateTime) {
        for (Showing showing : getShowings()) {
            if (showing.getStartTime() == showingDateTime) {
                throw new InvalidParameterException("A showing has already been scheduled for: " + showingDateTime);
            }
        }
    }

    private void validateDate(LocalDateTime showingDateTime) throws InvalidParameterException{
        if (date.compareTo(showingDateTime.toLocalDate()) != 0) throw new InvalidParameterException("The date of the showing is not for the correct date");
    }
}
