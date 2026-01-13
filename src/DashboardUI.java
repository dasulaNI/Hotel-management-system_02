import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DashboardUI extends JFrame {

    public DashboardUI() {

        // ===== Frame Settings =====
        setTitle("Hotel Check-In Dashboard");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== LEFT SIDEBAR =====
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(20, 20, 20));      // Dark background
        sidebar.setPreferredSize(new Dimension(220, 700));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        // Hotel logo placeholder
        JLabel logo = new JLabel("Innova", SwingConstants.CENTER);
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Serif", Font.BOLD, 28));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidebar.add(Box.createVerticalStrut(40));
        sidebar.add(logo);
        sidebar.add(Box.createVerticalStrut(40));

        // Sidebar buttons
        sidebar.add(createSidebarButton("Dashboard"));
        sidebar.add(createSidebarButton("Settings and Profile"));
        sidebar.add(createSidebarButton("Exams"));
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(createSidebarButton("Features  NEW"));
        sidebar.add(Box.createVerticalStrut(40));

        add(sidebar, BorderLayout.WEST);

        // ===== MAIN PANEL =====
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        // Banner section
        JLabel title = new JLabel("Today's Check-Ins", SwingConstants.CENTER);
        title.setFont(new Font("Georgia", Font.BOLD, 40));
        title.setForeground(new Color(212, 175, 55)); // Gold color
        title.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));

        mainPanel.add(title, BorderLayout.NORTH);

        // ===== TABLE AREA =====
        String[] columns = {
                "Guest Name", "ID / Passport No.", "Contact Number",
                "Room Number / Type", "Check-In Date & Time",
                "No. of Guests", "Status"
        };

        Object[][] data = {
                {"Maya Perera", "N1234567", "077 458 3254", "302 – Deluxe", "17/11/2025 2:30 PM", "2 Guests", "Checked-In"},
                {"John Silva", "901254789V", "071 225 8941", "118 – Standard", "17/11/2025 11:00 AM", "1 Guest", "Checked-In"},
                {"Hiruni Jayasinghe", "N4423568", "075 663 2104", "501 – Suite", "17/11/2025 6:45 PM", "3 Guests", "Upcoming"},
                {"Kevin Rodrigo", "200534789V", "076 889 3441", "207 – Superior", "18/11/2025 1:00 PM", "2 Guests", "Checked-In"},
                {"Amaya Fernando", "N8845261", "078 556 9032", "412 – Premium", "18/11/2025 3:15 PM", "1 Guest", "Upcoming"},
                {"Shun Tanaka", "P91234567", "‪+81 90 5522 3344‬", "620 – Executive", "17/11/2025 10:10 PM", "2 Guests", "Checked-In"},
        };

        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model);
        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // ===== Helper: Sidebar Button Style =====
    private JButton createSidebarButton(String text) {
        JButton btn = new JButton(text);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(180, 45));
        btn.setFocusPainted(false);
        btn.setForeground(Color.BLACK);
        btn.setBackground(new Color(212, 175, 55)); // Gold
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return btn;
    }

    // ===== Main Entry =====
    public static void main(String[] args) {
        SwingUtilities.invokeLater(DashboardUI::new);
}