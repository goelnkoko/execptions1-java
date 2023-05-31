package application;

import model.entities.Reservation;
import model.exceptions.DomainException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try{
            System.out.print("Room number: ");
            int number = sc.nextInt();
            System.out.print("Check-in date (dd/MM/yyyy): ");
            sc.nextLine();
            LocalDate checkIn = LocalDate.parse(sc.nextLine(), fmt);
            System.out.print("Check-Out date (dd/MM/yyyy): ");
            LocalDate checkOut = LocalDate.parse(sc.nextLine(), fmt);

            Reservation reservation = new Reservation(number, checkIn, checkOut);
            System.out.println("Reservation: " + reservation);

            System.out.println();
            System.out.println("Enter data to update the reservation");
            System.out.print("Check-in: ");
            checkIn = LocalDate.parse(sc.nextLine(), fmt);
            System.out.print("Check-out: ");
            checkOut = LocalDate.parse(sc.nextLine(), fmt);

            reservation.updateDates(checkIn, checkOut);
            System.out.println("Reservation: " + reservation);
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input!");
        }
        catch (DateTimeParseException e) {
            System.out.println("Invalid Date!");
        }
        catch (DomainException e) {
            System.out.println("Error in reservation: " + e.getMessage());
        }
        catch (RuntimeException e) {
            System.out.println("Unexpected error!");
        }

    }
}