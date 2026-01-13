import java.io.*;
import java.util.*;
public class DatabaseHelper {
    private final File storageDir;
    private final File checkinFile;

    public DatabaseHelper(String dirPath) {
        storageDir = new File(dirPath);
        if (!storageDir.exists()) storageDir.mkdirs();
        checkinFile = new File(storageDir, "checkins.csv");
        try {
            if (!checkinFile.exists()) {
                checkinFile.createNewFile();
                // write header
                try (PrintWriter pw = new PrintWriter(new FileWriter(checkinFile, true))) {
                    pw.println("id,guestName,roomNumber,checkInDate,checkOutDateTime,totalNights,totalBill,status");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized List<String[]> readAll() {
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(checkinFile))) {
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (first) { first = false; continue; } // skip header
                if (line.trim().isEmpty()) continue;
                String[] cols = splitCsv(line);
                rows.add(cols);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public synchronized void appendRow(String[] cols) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(checkinFile, true))) {
            pw.println(escapeCsv(cols));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void overwriteAll(List<String[]> rows) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(checkinFile, false))) {
            pw.println("id,guestName,roomNumber,checkInDate,checkOutDateTime,totalNights,totalBill,status");
            for (String[] r : rows) {
                pw.println(escapeCsv(r));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Basic CSV utilities (no external deps)
    private String escapeCsv(String[] cols) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cols.length; i++) {
            String s = cols[i] == null ? "" : cols[i];
            if (s.contains(",") || s.contains("\"") || s.contains("\n")) {
                s = s.replace("\"", "\"\"");
                sb.append('"').append(s).append('"');
            } else {
                sb.append(s);
            }
            if (i < cols.length - 1) sb.append(',');
        }
        return sb.toString();
    }

    private String[] splitCsv(String line) {
        List<String> cols = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        boolean inQuotes = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (inQuotes) {
                if (c == '"') {
                    if (i+1 < line.length() && line.charAt(i+1) == '"') {
                        cur.append('"'); i++;
                    } else {
                        inQuotes = false;
                    }
                } else {
                    cur.append(c);
                }
            } else {
                if (c == '"') {
                    inQuotes = true;
                } else if (c == ',') {
                    cols.add(cur.toString());
                    cur.setLength(0);
                } else {
                    cur.append(c);
                }
            }
        }
        cols.add(cur.toString());
        return cols.toArray(new String[0]);
    }
}