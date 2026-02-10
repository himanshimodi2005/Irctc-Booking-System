package service;

import dao.BookingDAO;
import dao.PassengerDAO;
import dao.TrainDAO;
import model.Passenger;
import model.Train;
import java.util.List;
import java.util.Scanner;

public class IRCTCService {
    private TrainDAO trainDAO = new TrainDAO();
    private PassengerDAO passengerDAO = new PassengerDAO();
    private BookingDAO bookingDAO = new BookingDAO();
    private Scanner sc = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("\n=== IRCTC BOOKING SYSTEM ===");
            System.out.println("1. View Trains");
            System.out.println("2. Search Trains");
            System.out.println("3. Book Ticket");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. View All Bookings");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> viewTrains();
                case 2 -> searchTrains();
                case 3 -> bookTicket();
                case 4 -> cancelTicket();
                case 5 -> viewBookings();
                case 6 -> { System.out.println("Thank you!"); return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void viewTrains() {
        List<Train> trains = trainDAO.getAllTrains();
        trains.forEach(System.out::println);
    }

    private void searchTrains() {
        sc.nextLine();
        System.out.print("Enter Source: ");
        String src = sc.nextLine();
        System.out.print("Enter Destination: ");
        String dest = sc.nextLine();
        System.out.print("Enter Travel Date (YYYY-MM-DD): ");
        String date = sc.nextLine();
        List<Train> trains = trainDAO.searchTrains(src, dest, date);
        if (trains.isEmpty()) System.out.println("No trains found.");
        else trains.forEach(System.out::println);
    }

    private void bookTicket() {
        System.out.print("Enter Train ID: ");
        int trainId = sc.nextInt();
        Train t = trainDAO.getTrainById(trainId);
        if (t == null) { System.out.println("Train not found!"); return; }
        System.out.print("Enter seats to book: ");
        int seats = sc.nextInt();
        if (seats > t.getAvailableSeats()) { System.out.println("Not enough seats!"); return; }
        sc.nextLine();
        System.out.print("Enter passenger name: ");
        String name = sc.nextLine();
        System.out.print("Enter age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter gender: ");
        String gender = sc.nextLine();
        Passenger p = new Passenger(name, age, gender);
        int pid = passengerDAO.addPassenger(p);
        if (pid != -1 && bookingDAO.createBooking(pid, trainId, seats)) {
            trainDAO.updateSeats(trainId, t.getAvailableSeats() - seats);
            System.out.println("Booking successful!");
        } else System.out.println("Booking failed!");
    }

    private void cancelTicket() {
        System.out.print("Enter Booking ID to cancel: ");
        int id = sc.nextInt();
        int[] details = bookingDAO.getBookingDetails(id);
        if (details == null) { System.out.println("Booking not found."); return; }
        if (bookingDAO.cancelBooking(id)) {
            Train t = trainDAO.getTrainById(details[0]);
            trainDAO.updateSeats(details[0], t.getAvailableSeats() + details[1]);
            System.out.println("Booking cancelled successfully.");
        } else System.out.println("Cancellation failed or already cancelled.");
    }

    private void viewBookings() {
        List<String> bookings = bookingDAO.getAllBookings();
        if (bookings.isEmpty()) System.out.println("No bookings found.");
        else bookings.forEach(System.out::println);
    }
}
