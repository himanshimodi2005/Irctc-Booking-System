package dao;

import db.DBConnection;
import model.Passenger;
import java.sql.*;

public class PassengerDAO {
    public int addPassenger(Passenger p) {
        int id = -1;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO passengers (name, age, gender) VALUES (?,?,?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getName());
            ps.setInt(2, p.getAge());
            ps.setString(3, p.getGender());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) id = rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return id;
    }
}
