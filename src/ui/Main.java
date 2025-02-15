package ui;

import ui.ContactManagementUI;
import dao.DatabaseConnection;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Initialiser la base de données
        DatabaseConnection.initDatabase();

        // Lancer l'interface graphique
        SwingUtilities.invokeLater(() -> {
            ContactManagementUI app = new ContactManagementUI();
            app.setVisible(true);
        });
    }
}