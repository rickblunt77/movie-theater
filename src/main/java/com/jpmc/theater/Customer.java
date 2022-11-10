package com.jpmc.theater;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {

    private final String name;
    private final String id;
    private List<Reservation> reservations;

    /**
     * @param name customer name
     * @param id customer id
     */
    public Customer(String name, String id) {
        this.id = id; // NOTE - id is not used anywhere at the moment
        this.name = name;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    /**
     *
     * @param showing instance of Showing class
     * @param howManyTickets number of tickets for reservation
     */
    public void makeReservation(Showing showing, int howManyTickets) {
        if (reservations == null) reservations = new ArrayList<>();
        reservations.add(new Reservation(showing, howManyTickets));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "name: " + name;
    }
}