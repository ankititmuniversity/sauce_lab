package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.annotations.Test;
import utils.DBUtils;

public class DBOperation {

    private static final String READ_QUERY = "SELECT * FROM Country WHERE id=?";
    private static final String WRITE_QUERY = "INSERT INTO Country (id, Country_Name) VALUES (?, ?)";

    @Test
    public void readData() {
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(READ_QUERY)) {

            ps.setInt(1, 2);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Country: " + rs.getString("Country_Name"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void writeData() {
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(WRITE_QUERY)) {

            ps.setInt(1, 4);
            ps.setString(2, "Germany");
            int rows = ps.executeUpdate();

            System.out.println("Rows inserted: " + rows);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
