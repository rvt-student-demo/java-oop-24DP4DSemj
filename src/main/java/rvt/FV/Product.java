package rvt.FV;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {

    public static void addProduct(String name, double price, int categoryId) {
        String sql = "INSERT INTO products (name, price, category_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, categoryId);
            ps.executeUpdate();
            System.out.println("Produkts veiksmīgi pievienots!");
            
        } catch (SQLException e) {
            System.out.println("Kļūda pievienojot produktu: " + e.getMessage());
        }
    }

    public static void printAllProducts() {
        String sql = "SELECT products.id, products.name, products.price, categories.name AS cat_name " +
                     "FROM products " +
                     "LEFT JOIN categories ON products.category_id = categories.id";
                     
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            System.out.println("\n--- VISI PRODUKTI ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + 
                                   " | " + rs.getString("name") + 
                                   " | Cena: " + rs.getDouble("price") + " EUR" +
                                   " | Kategorija: " + rs.getString("cat_name"));
            }
            
        } catch (SQLException e) {
            System.out.println("Kļūda ielasot produktus: " + e.getMessage());
        }
    }

    public static void searchByCategory(String criteria) {
        String sql = "SELECT products.id, products.name, products.price, categories.name AS cat_name " +
                     "FROM products " +
                     "JOIN categories ON products.category_id = categories.id " +
                     "WHERE categories.id = ? OR categories.name LIKE ?";
                     
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            int idSearch;
            try {
                idSearch = Integer.parseInt(criteria);
            } catch (NumberFormatException e) {
                idSearch = -1;
            }

            ps.setInt(1, idSearch);
            ps.setString(2, "%" + criteria + "%");

            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("\n--- MEKLĒŠANAS REZULTĀTI ---");
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id") + 
                                       " | " + rs.getString("name") + 
                                       " | Cena: " + rs.getDouble("price") + " EUR" +
                                       " | Kategorija: " + rs.getString("cat_name"));
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Kļūda meklējot produktus: " + e.getMessage());
        }
    }
}