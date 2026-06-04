package rvt.FV;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Category {

    public static void addCategory(String name) {
        String sql = "INSERT INTO categories (name) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, name);
            ps.executeUpdate();
            System.out.println("Kategorija veiksmīgi pievienota!");
            
        } catch (SQLException e) {
            System.out.println("Kļūda pievienojot kategoriju: " + e.getMessage());
        }
    }

    public static void printAllCategories() {
        String sql = "SELECT * FROM categories";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            System.out.println("\n--- VISAS KATEGORIJAS ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " | Nosaukums: " + rs.getString("name"));
            }
            
        } catch (SQLException e) {
            System.out.println("Kļūda ielasot kategorijas: " + e.getMessage());
        }
    }
}