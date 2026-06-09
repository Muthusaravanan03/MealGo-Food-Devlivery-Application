package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {

    public static boolean loginUser(String email, String password) {

        System.out.println("👉 loginUser() started");

        try {
            Connection con = DBConnection.getConnection();

            if (con == null) {
                System.out.println("❌ DB Connection failed");
                return false;
            }

            String query = "SELECT * FROM users WHERE email = ? AND password = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email.trim());
            ps.setString(2, password.trim());

            System.out.println("👉 Executing login query...");

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("✅ Login Successful");

                String updateQuery =
                        "UPDATE users SET last_login = CURRENT_TIMESTAMP WHERE email = ?";

                PreparedStatement ps2 = con.prepareStatement(updateQuery);
                ps2.setString(1, email.trim());

                ps2.executeUpdate();
                ps2.close();

                System.out.println("🕒 Last login updated");

                rs.close();
                ps.close();
                con.close();

                return true;

            } else {

                System.out.println("❌ Invalid email or password");

                rs.close();
                ps.close();
                con.close();

                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}