package com.jpmc.theater;

public class ScheduleElement {
    int sequenceNumber;
    String startTime;
    String title;
    String duration;
    double price;

    public ScheduleElement(int sequenceNumber, String startTime, String title, String duration, double price) {
        this.sequenceNumber = sequenceNumber;
        this.startTime = startTime;
        this.title = title;
        this.duration = duration;
        this.price = price;
    }
}
