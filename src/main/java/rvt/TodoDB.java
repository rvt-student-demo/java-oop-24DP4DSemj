package rvt;

import java.sql.*;
import java.util.ArrayList;

public class TodoDB {

    private static final String DB_URL = "jdbc:sqlite:todo.db";

    public TodoDB() {
        initSchema();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    private void initSchema() {
        String sql = "CREATE TABLE IF NOT EXISTS todo ("
                + "id INTEGER PRIMARY KEY,"
                + "task TEXT NOT NULL) STRICT;";
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void add(String task) {
        String sql = "INSERT INTO todo (task) VALUES (?);";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, task);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> findAll() {
        String sql = "SELECT id, task FROM todo;";
        ArrayList<String> tasks = new ArrayList<>();
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String task = rs.getString("task");
                tasks.add(id + ". " + task);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    public void removeById(int id) {
        String sql = "DELETE FROM todo WHERE id = ?;";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}