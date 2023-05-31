package model.entities;

import model.exceptions.DomainException;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reservation {

    private Integer roomNumber;
    private LocalDate checkIn;
    private LocalDate checkOut;

    private static DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Reservation(Integer roomNumber, LocalDate checkIn, LocalDate checkOut) {
        if(!checkOut.isAfter(checkIn)) {
            throw new DomainException("Check-out date must be after check-in date");
        }
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public long duration() {

         return (Duration.between(checkIn.atTime(0, 0), checkOut.atTime(0, 0))).toDays();

    }

    public void updateDates(LocalDate checkIn, LocalDate checkOut) {

        LocalDate now = LocalDate.now();
        if(checkIn.isBefore(now) || checkOut.isBefore(now)) {
            throw new DomainException("Reservation dates for update must be in future dates");
        }
        if (checkOut.isBefore(checkIn)) {
            throw new DomainException("Check-out date must be after check-in date");
        }

        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "Room{" +
                " Number= " + roomNumber +
                ", checkIn= " + checkIn.format(fmt1) +
                ", checkOut= " + checkOut.format(fmt1) +
                ", " + duration() +
                " nights}";
    }
}
