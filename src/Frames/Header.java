package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Frames.my.MyFont;
import Frames.my.MyLabel;
import Frames.my.MyPanel;
import Models.Admin;
import application.Database;

public class Header extends MyPanel {

    private Frame parentFrame; // Reference to the parent frame for handling logout
    private String adminID;
    private Database dtb;

    public Header(String title, Frame parentFrame, String adminID) {
        super(1080, 50, Color.WHITE);
        this.parentFrame = parentFrame;
        this.adminID = adminID;
        this.setLayout(new BorderLayout()); // Use BorderLayout to divide the header into regions
        dtb = new Database();
        addLogo();
        addTitle(title);
        addUserMenu();
    }

    private void addLogo() {
        try {
            // Load logo from URL and adjust size
            URL logoUrl = new URL("https://dhs.ptit.edu.vn/web/image/385-67ae5bc7/Logo_PTIT_University.png");
            ImageIcon logoIcon = new ImageIcon(
                new ImageIcon(logoUrl).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)
            );
            JLabel logoLabel = new JLabel(logoIcon);

            JPanel logoPanel = new JPanel(new BorderLayout());
            logoPanel.setBackground(Color.WHITE);
            logoPanel.setBorder(new EmptyBorder(0, 20, 0, 0)); 
            logoPanel.add(logoLabel, BorderLayout.CENTER);

            this.add(logoPanel, BorderLayout.WEST);
        } catch (Exception e) {
            e.printStackTrace();

            JLabel errorLabel = new JLabel("Logo not available");
            errorLabel.setForeground(Color.RED);

            JPanel logoPanel = new JPanel(new BorderLayout());
            logoPanel.setBackground(Color.WHITE);
            logoPanel.setBorder(new EmptyBorder(0, 20, 0, 0)); // Padding on the left of 20px
            logoPanel.add(errorLabel, BorderLayout.CENTER);

            this.add(logoPanel, BorderLayout.WEST);
        }
    }

    private void addTitle(String title) {
        MyLabel titleLabel = new MyLabel(title, new MyFont(Font.BOLD, 24), Color.BLACK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLabel, BorderLayout.CENTER);
    }

    private void addUserMenu() {
        // Create user button
        JButton userButton = new JButton("👨");
        userButton.setBackground(Color.WHITE);
        userButton.setBorder(BorderFactory.createEmptyBorder()); // Remove button border
        userButton.setFocusPainted(true);
    
        // Create popup menu
        JPopupMenu userMenu = new JPopupMenu();
        JMenuItem editInfo = new JMenuItem("Edit Profile");
        JMenuItem logout = new JMenuItem("Log out");
    
        // Handle events for menu items
        editInfo.addActionListener(e -> {
            try {
                updatePersonalInfo();
            } catch (ParseException ex) {
            }
        });
        logout.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Log out successfully!");
            if (parentFrame != null) {
                parentFrame.dispose(); // Close parent frame
                // Initialize login form
                new LoginForm();
            }
        });
    
        // Add items to the menu
        userMenu.add(editInfo);
        userMenu.add(logout);
    
        // Show the menu when the button is clicked
        userButton.addActionListener(e -> {
            // Show the menu at the left of the button (x = button's x - menu's width, y = button's y + button's height)
            userMenu.show(userButton, -userMenu.getPreferredSize().width, userButton.getHeight());
        });
    
        // Place button in the right corner
        JPanel userPanel = new JPanel(new BorderLayout());
        userPanel.setBackground(Color.WHITE);
        userPanel.setBorder(new EmptyBorder(0, 0, 0, 20)); // Padding on the right of 20px
        userPanel.add(userButton, BorderLayout.CENTER);
    
        this.add(userPanel, BorderLayout.EAST);
    }
    

    private void updatePersonalInfo() throws ParseException {
        String sql = "SELECT name, email, phone, birthDate FROM admins WHERE id = ?";
        Admin admin = null;
        
        // Fetch admin data from the database
        try (PreparedStatement preparedStatement = dtb.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, this.adminID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                admin = new Admin(
                    resultSet.getString("name"), 
                    resultSet.getString("phone"), 
                    resultSet.getString("email"), 
                    this.adminID, 
                    resultSet.getDate("birthDate")
                );
            } else {
                JOptionPane.showMessageDialog(this, "Admin not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching admin data from the database!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }
        
        // Create fields for user input
        JTextField nameField = new JTextField(admin.getName());
        JTextField emailField = new JTextField(admin.getEmail());
        JTextField phoneField = new JTextField(admin.getPhone());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(admin.getBirthDate());
        JTextField dobField = new JTextField(formattedDate);
        
        Object[] message = {
            "Name:", nameField,
            "Email:", emailField,
            "Phone number:", phoneField,
            "Date of birth (dd/MM/yyyy):", dobField,
        };
        
        int option = JOptionPane.showConfirmDialog(
            this,
            message,
            "Update Personal Information",
            JOptionPane.OK_CANCEL_OPTION
        );
        
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String dobText = dobField.getText();
        
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || dobText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all the information!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    dateFormat.setLenient(false); // Ensure strict date parsing
                    Date dob = dateFormat.parse(dobText);
                    java.sql.Date sqlDob = new java.sql.Date(dob.getTime());
        
                    // Update the database
                    String updateSql = "UPDATE admins SET name = ?, email = ?, phone = ?, birthDate = ? WHERE id = ?";
                    try (PreparedStatement preparedStatement = dtb.getConnection().prepareStatement(updateSql)) {
                        preparedStatement.setString(1, name);
                        preparedStatement.setString(2, email);
                        preparedStatement.setString(3, phone);
                        preparedStatement.setDate(4, sqlDob);
                        preparedStatement.setString(5, this.adminID); // Ensure admin ID is included
        
                        int rowsAffected = preparedStatement.executeUpdate();
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(this, "Personal information updated successfully!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Admin not found for update!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Invalid birthdate format. Please use the dd/MM/yyyy format.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error updating admin information!", "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        }
        
    }
}
