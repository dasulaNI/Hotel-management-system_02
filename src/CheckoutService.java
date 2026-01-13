import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class CheckoutService {

    private final CheckinService checkinService;

    // === ROOM TYPE BASE RATES (can be moved to DB later) ===
    private final HashMap<String, Double> roomRates = new HashMap<>();

    public CheckoutService(CheckinService checkinService) {
        this.checkinService = checkinService;

        // Base price per night for each room type
        roomRates.put("Standard", 10000.0);
        roomRates.put("Deluxe", 15000.0);
        roomRates.put("Premium", 20000.0);
        roomRates.put("Executive", 25000.0);
        roomRates.put("Suite", 35000.0);
    }

    // ==========================================================
    // 🔥 CORE BILLING ENGINE
    // ==========================================================
    public double calculateTotalBill(
            String roomType,
            String checkInDate,
            String actualCheckoutDate,
            double extras,
            double discountPercent,
            boolean lateCheckout,
            boolean includeServiceCharge
    ) {

        // 1. Parse dates
        LocalDate in = LocalDate.parse(checkInDate);
        LocalDate out = LocalDate.parse(actualCheckoutDate);

        // 2. Nights stayed
        long nights = ChronoUnit.DAYS.between(in, out);
        if (nights <= 0) nights = 1;

        // 3. Base room rate
        double rate = roomRates.getOrDefault(roomType, 0.0);
        double baseTotal = rate * nights;

        // 4. Service charge (optional)
        double serviceCharge = includeServiceCharge ? baseTotal * 0.10 : 0.0;

        // 5. Late checkout fee
        double lateFee = lateCheckout ? 3500.0 : 0.0;

        // 6. Discount
        double discount = (discountPercent / 100.0) * baseTotal;

        // 7. Final amount
        double finalTotal = baseTotal + serviceCharge + extras + lateFee - discount;

        return finalTotal;
    }

    // ==========================================================
    // 📄 CREATE PRINTABLE INVOICE TEXT
    // ==========================================================
    public String generateInvoice(
            String guestName,
            String roomType,
            String checkInDate,
            String checkoutDate,
            double extras,
            double discountPercent,
            boolean lateCheckout,
            boolean includeServiceCharge
    ) {

        double total = calculateTotalBill(
                roomType,
                checkInDate,
                checkoutDate,
                extras,
                discountPercent,
                lateCheckout,
                includeServiceCharge
        );

        long nights = ChronoUnit.DAYS.between(LocalDate.parse(checkInDate), LocalDate.parse(checkoutDate));
        if (nights <= 0) nights = 1;

        double rate = roomRates.getOrDefault(roomType, 0.0);

        return
                "================ HOTEL INVOICE ================\n" +
                "Guest Name:      " + guestName + "\n" +
                "Room Type:       " + roomType + "\n" +
                "Check-In:        " + checkInDate + "\n" +
                "Check-Out:       " + checkoutDate + "\n" +
                "Nights Stayed:   " + nights + "\n" +
                "------------------------------------------------\n" +
                "Room Rate:       Rs. " + rate + " x " + nights + "\n" +
                "Extras:          Rs. " + extras + "\n" +
                "Discount:        " + discountPercent + "%\n" +
                "Service Charge:  " + (includeServiceCharge ? "10%" : "0%") + "\n" +
                "Late Checkout:   " + (lateCheckout ? "Rs. 3500" : "No") + "\n" +
                "------------------------------------------------\n" +
                "TOTAL BILL:      Rs. " + String.format("%.2f", total) + "\n" +
                "================================================\n";
    }
    
    public double getRoomRate(String roomType) {
        return roomRates.getOrDefault(roomType, 0.0);
    }
}
