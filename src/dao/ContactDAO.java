package dao;

import model.Contact;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {
    // Ajouter un contact
    public void addContact(Contact contact) {
        String sql = "INSERT INTO contacts(nom, prenom, telephone, email) VALUES(?,?,?,?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, contact.getNom());
            pstmt.setString(2, contact.getPrenom());
            pstmt.setString(3, contact.getTelephone());
            pstmt.setString(4, contact.getEmail());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Récupérer tous les contacts
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contacts";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Contact contact = new Contact(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("telephone"),
                    rs.getString("email")
                );
                contacts.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return contacts;
    }

    // Mettre à jour un contact
    public void updateContact(Contact contact) {
        String sql = "UPDATE contacts SET nom=?, prenom=?, telephone=?, email=? WHERE id=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, contact.getNom());
            pstmt.setString(2, contact.getPrenom());
            pstmt.setString(3, contact.getTelephone());
            pstmt.setString(4, contact.getEmail());
            pstmt.setInt(5, contact.getId());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprimer un contact
    public void deleteContact(int id) {
        String sql = "DELETE FROM contacts WHERE id=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}