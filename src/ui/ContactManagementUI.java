package ui;

import dao.ContactDAO;
import model.Contact;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ContactManagementUI extends JFrame {
    private ContactDAO contactDAO;
    private JTable contactTable;
    private DefaultTableModel tableModel;

    public ContactManagementUI() {
        contactDAO = new ContactDAO();
        
        setTitle("Gestion des Contacts");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initComponents();
    }

    private void initComponents() {
        // Modèle de tableau
        String[] columnNames = {"ID", "Nom", "Prénom", "Téléphone", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0);
        contactTable = new JTable(tableModel);

        // Panneau des boutons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Ajouter");
        JButton editButton = new JButton("Modifier");
        JButton deleteButton = new JButton("Supprimer");

        // Écouteurs d'événements
        addButton.addActionListener(e -> ajouterContact());
        editButton.addActionListener(e -> modifierContact());
        deleteButton.addActionListener(e -> supprimerContact());

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Disposition
        setLayout(new BorderLayout());
        add(new JScrollPane(contactTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Charger les contacts initiaux
        chargerContacts();
    }

    private void chargerContacts() {
        // Vider le modèle existant
        tableModel.setRowCount(0);

        // Récupérer et afficher les contacts
        List<Contact> contacts = contactDAO.getAllContacts();
        for (Contact contact : contacts) {
            tableModel.addRow(new Object[]{
                contact.getId(), 
                contact.getNom(), 
                contact.getPrenom(), 
                contact.getTelephone(), 
                contact.getEmail()
            });
        }
    }

    private void ajouterContact() {
        Contact contact = new Contact();
        if (afficherDialogueContact(contact, "Ajouter Contact")) {
            contactDAO.addContact(contact);
            chargerContacts();
        }
    }

    private void modifierContact() {
        int selectedRow = contactTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez un contact à modifier");
            return;
        }

        Contact contact = new Contact(
            (int) tableModel.getValueAt(selectedRow, 0),
            (String) tableModel.getValueAt(selectedRow, 1),
            (String) tableModel.getValueAt(selectedRow, 2),
            (String) tableModel.getValueAt(selectedRow, 3),
            (String) tableModel.getValueAt(selectedRow, 4)
        );

        if (afficherDialogueContact(contact, "Modifier Contact")) {
            contactDAO.updateContact(contact);
            chargerContacts();
        }
    }

    private void supprimerContact() {
        int selectedRow = contactTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez un contact à supprimer");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        int confirmation = JOptionPane.showConfirmDialog(
            this, 
            "Voulez-vous vraiment supprimer ce contact ?", 
            "Confirmation", 
            JOptionPane.YES_NO_OPTION
        );

        if (confirmation == JOptionPane.YES_OPTION) {
            contactDAO.deleteContact(id);
            chargerContacts();
        }
    }

    private boolean afficherDialogueContact(Contact contact, String titre) {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        
        JTextField nomField = new JTextField(contact.getNom());
        JTextField prenomField = new JTextField(contact.getPrenom());
        JTextField telephoneField = new JTextField(contact.getTelephone());
        JTextField emailField = new JTextField(contact.getEmail());

        panel.add(new JLabel("Nom:"));
        panel.add(nomField);
        panel.add(new JLabel("Prénom:"));
        panel.add(prenomField);
        panel.add(new JLabel("Téléphone:"));
        panel.add(telephoneField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        int result = JOptionPane.showConfirmDialog(
            this, panel, titre, 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            contact.setNom(nomField.getText());
            contact.setPrenom(prenomField.getText());
            contact.setTelephone(telephoneField.getText());
            contact.setEmail(emailField.getText());
            return true;
        }
        return false;
    }
}