package Sql;
import java.sql.*;

import Weather.Weather;

public class DatabaseRepository {
    public DatabaseRepository()  {
        DatabaseRepository.createNewTable();
    }

    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/tests.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS forecast (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	city text NOT NULL,\n"
                + "	forecastDate text NOT NULL,\n"
                + "	description text NOT NULL,\n"
                + "	temperature real\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/tests.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insert(String city, String forecastDate, String description, double temperature) {
        String sql = "INSERT INTO forecast(city,forecastDate, description, temperature) VALUES(?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, city);
            pstmt.setString(2, forecastDate);
            pstmt.setString(3, description);
            pstmt.setDouble(4, temperature);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        selectAll();
    }

    /**
     * select all rows in the warehouses table
     */
    public void selectAll(){
        String sql = "SELECT forecastDate, temperature FROM forecast";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(
                        rs.getString("forecastDate") + "\t" +
                        rs.getDouble("temperature")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
