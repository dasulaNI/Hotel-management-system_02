import javax.swing.*;
import java.awt.*;

public class ReceptionistLogin extends JFrame {

    // --- Color Constants ---
    private final Color GOLD_COLOR = new Color(255, 180, 60);
    private final Color LABEL_COLOR = GOLD_COLOR;
    private final Color FIELD_BORDER_COLOR = GOLD_COLOR;
    private final Color PLACEHOLDER_COLOR = new Color(150, 150, 150);
    private final Color TEXT_COLOR = Color.WHITE;
    private final Color FORM_BACKGROUND = new Color(0, 0, 0, 150);
    private final Color CALENDAR_BG = new Color(40, 40, 40);

    public ReceptionistLogin() {

        // === Frame settings ===
        setTitle("Receptionist Login");
        setSize(500, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // === Background image ===
        ImageIcon bgImage = new ImageIcon("src/Rescources/a170b7e6b576d72403c665e6337322e1.jpg");
        JLabel background = new JLabel(bgImage);
        background.setBounds(0, 0, 500, 650);
        add(background);

        // === Transparent panel ===
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(new Color(255, 255, 255, 120));
        card.setBounds(50, 80, 400, 500);
        card.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 180), 1));
        background.add(card);

        // === Icon ===
        JLabel icon = new JLabel("\uD83D\uDC64");
        icon.setFont(new Font("Segoe UI", Font.PLAIN, 70));
        icon.setBounds(160, 20, 100, 80);
        card.add(icon);

        // === Title ===
        JLabel title = new JLabel("Receptionist LogIn", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(50, 100, 300, 40);
        card.add(title);

        // === Subtitle ===
        JLabel welcome = new JLabel("Welcome onboard with us!", SwingConstants.CENTER);
        welcome.setFont(new Font("Arial", Font.PLAIN, 14));
        welcome.setBounds(50, 140, 300, 30);
        card.add(welcome);

        // === Email Label ===
        JLabel emailLbl = new JLabel("Receptionist Email ID");
        emailLbl.setFont(new Font("Arial", Font.PLAIN, 15));
        emailLbl.setBounds(50, 180, 300, 20);
        card.add(emailLbl);

        // === Email Field ===
        JTextField emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        emailField.setBounds(50, 205, 300, 40);
        emailField.setBackground(new Color(230, 240, 255));
        emailField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        card.add(emailField);

        // === Password Label ===
        JLabel passLbl = new JLabel("Password");
        passLbl.setFont(new Font("Arial", Font.PLAIN, 15));
        passLbl.setBounds(50, 260, 300, 20);
        card.add(passLbl);

        // === Password Field ===
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBounds(50, 285, 300, 40);
        passwordField.setBackground(new Color(230, 240, 255));
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        card.add(passwordField);

        // === Forgot ===
        JLabel forgot = new JLabel("Forgot Password?");
        forgot.setFont(new Font("Arial", Font.PLAIN, 13));
        forgot.setBounds(230, 330, 150, 20);
        forgot.setForeground(Color.DARK_GRAY);
        card.add(forgot);

        // === Login Button ===
        JButton loginBtn = new JButton("LogIn as Receptionist");
        loginBtn.setFont(new Font("Arial", Font.BOLD, 16));
        loginBtn.setBounds(50, 365, 300, 45);
        loginBtn.setFocusPainted(false);
        loginBtn.setBackground(new Color(255, 200, 0));
        loginBtn.setBorder(BorderFactory.createEmptyBorder());
        card.add(loginBtn);

        // === Footer ===
        JLabel student = new JLabel("Are you a student? LogIn as a Student");
        student.setFont(new Font("Arial", Font.PLAIN, 14));
        student.setBounds(60, 420, 300, 40);
        student.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(student);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ReceptionistLogin();
    }
}
