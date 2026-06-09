package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Signup {

    public static boolean registerUser(String username, String email, String password, String phone, String address) {

        try {
            Connection con = DBConnection.getConnection();

            // STEP 1: Check if user already exists
            String checkQuery = "SELECT * FROM users WHERE email = ?";

            PreparedStatement checkPs = con.prepareStatement(checkQuery);
            checkPs.setString(1, email);

            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {
                System.out.println("❌ User already exists");
                return false;
            }

            // STEP 2: Insert user
            String query = "INSERT INTO users (username, email, password, phone, address) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, phone);
            ps.setString(5, address);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("✅ User Registered Successfully");
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}