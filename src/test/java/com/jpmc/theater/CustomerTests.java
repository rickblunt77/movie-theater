package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTests {
    @Test
    //This test validates that the correct fee is charged when a customer makes a reservation for multiple guests
    void totalFeeForCustomer() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90));
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.of(2022, 9, 1), LocalTime.of(1,0)), 12.5, MovieSpecialType.SPECIAL);
        Customer customer = new Customer("John Doe", "id-12345");
        customer.makeReservation(showing, 4);
        Reservation reservation = customer.getReservations().get(0);
        assertEquals(reservation.totalFee(), 50);
    }
}
