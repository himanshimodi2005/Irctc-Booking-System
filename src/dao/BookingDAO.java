package dao;

import db.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    public boolean createBooking(int passengerId, int trainId, int seats) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO bookings (passenger_id, train_id, seats_booked) VALUES (?,?,?)")) {
            ps.setInt(1, passengerId);
            ps.setInt(2, trainId);
            ps.setInt(3, seats);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean cancelBooking(int bookingId) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE bookings SET status='CANCELLED' WHERE booking_id=? AND status='CONFIRMED'")) {
            ps.setInt(1, bookingId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public int[] getBookingDetails(int bookingId) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT train_id, seats_booked FROM bookings WHERE booking_id=?")) {
            ps.setInt(1, bookingId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new int[]{rs.getInt("train_id"), rs.getInt("seats_booked")};
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public List<String> getAllBookings() {
        List<String> bookings = new ArrayList<>();
        String query = "SELECT b.booking_id, b.status, b.seats_booked, b.booking_date, " +
                "t.train_name, t.source, t.destination, t.travel_date, t.fare, p.name " +
                "FROM bookings b " +
                "JOIN trains t ON b.train_id = t.train_id " +
                "JOIN passengers p ON b.passenger_id = p.passenger_id";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                bookings.add("BookingID: " + rs.getInt("booking_id") +
                        " | Passenger: " + rs.getString("name") +
                        " | Train: " + rs.getString("train_name") +
                        " | From: " + rs.getString("source") +
                        " | To: " + rs.getString("destination") +
                        " | Date: " + rs.getDate("travel_date") +
                        " | Seats: " + rs.getInt("seats_booked") +
                        " | Fare: " + rs.getDouble("fare") +
                        " | Status: " + rs.getString("status"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return bookings;
    }
}
