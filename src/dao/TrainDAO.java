package dao;

import db.DBConnection;
import model.Train;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainDAO {

    public List<Train> getAllTrains() {
        List<Train> trains = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM trains")) {
            while (rs.next()) {
                trains.add(new Train(
                        rs.getInt("train_id"), rs.getString("train_name"),
                        rs.getString("source"), rs.getString("destination"),
                        rs.getInt("total_seats"), rs.getInt("available_seats"),
                        rs.getDouble("fare"), rs.getString("travel_date")));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return trains;
    }

    public Train getTrainById(int id) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM trains WHERE train_id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Train(rs.getInt("train_id"), rs.getString("train_name"),
                        rs.getString("source"), rs.getString("destination"),
                        rs.getInt("total_seats"), rs.getInt("available_seats"),
                        rs.getDouble("fare"), rs.getString("travel_date"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public boolean updateSeats(int trainId, int seats) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE trains SET available_seats=? WHERE train_id=?")) {
            ps.setInt(1, seats);
            ps.setInt(2, trainId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public List<Train> searchTrains(String src, String dest, String date) {
        List<Train> trains = new ArrayList<>();
        String query = "SELECT * FROM trains WHERE source=? AND destination=? AND travel_date=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, src);
            ps.setString(2, dest);
            ps.setString(3, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                trains.add(new Train(
                        rs.getInt("train_id"), rs.getString("train_name"),
                        rs.getString("source"), rs.getString("destination"),
                        rs.getInt("total_seats"), rs.getInt("available_seats"),
                        rs.getDouble("fare"), rs.getString("travel_date")));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return trains;
    }
}
