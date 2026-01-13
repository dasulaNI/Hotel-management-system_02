import javax.swing.table.DefaultTableModel;
import java.util.*;
public class CheckinService {
    private final DatabaseHelper db;

    public CheckinService(DatabaseHelper db) {
        this.db = db;
    }

    public DefaultTableModel loadTableModel() {
        String[] columns = {"ID","Guest Name","Room Number","Check-In Date","Check-Out Date & Time","Total Nights","Total Bill","Status"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        List<String[]> rows = db.readAll();
        for (String[] r : rows) {
            // ensure length 8
            String[] row = Arrays.copyOf(r, 8);
            for (int i = 0; i < 8; i++) if (row[i]==null) row[i] = "";
            model.addRow(row);
        }
        return model;
    }

    public void addSampleDataIfEmpty() {
        List<String[]> rows = db.readAll();
        if (!rows.isEmpty()) return;
        String[][] sample = {
            {"1","John Silva","118 – Standard","2025-11-16","2025-11-18 11:00 AM","2","22500","Completed"},
            {"2","Maya Perera","302 – Deluxe","2025-11-15","2025-11-17 10:30 AM","2","35800","Completed"},
            {"3","Hiruni Jayasinghe","501 – Suite","2025-11-14","2025-11-17 09:00 AM","3","72000","Completed"},
            {"4","Kevin Rodrigo","207 – Superior","2025-11-14","2025-11-18 14:00 PM","4","28400","Checked-In"}
        };
        for (String[] s : sample) db.appendRow(s);
    }

    public void checkoutGuestById(String id, String checkoutDateTime, String totalBill) {
        List<String[]> rows = db.readAll();
        boolean changed = false;
        for (String[] r : rows) {
            if (r.length>0 && r[0].equals(id)) {
                r[4] = checkoutDateTime;
                r[6] = totalBill;
                r[7] = "Completed";
                changed = true;
            }
        }
        if (changed) db.overwriteAll(rows);
    }

    public String generateNewId() {
        List<String[]> rows = db.readAll();
        int max = 0;
        for (String[] r : rows) {
            try { int v = Integer.parseInt(r[0]); if (v>max) max=v; } catch(Exception e){}
        }
        return String.valueOf(max+1);
    }

    public void addCheckin(String guestName, String roomNumber, String checkInDate, String checkOutDateTime, String totalNights, String totalBill, String status) {
        String id = generateNewId();
        String[] row = {id, guestName, roomNumber, checkInDate, checkOutDateTime, totalNights, totalBill, status};
        db.appendRow(row);
    }
}