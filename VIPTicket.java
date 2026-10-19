package eventapp;

public class VIPTicket extends Ticket {
    private static final String CATEGORY = "VIP";
    private static final String ACCESS = "Premium";
    private static final double MULTIPLIER = 1.5;
    private static int issuedCount = 0;

    public VIPTicket(int ticketId, int eventId,
                     double price, int attendeeId) {
        super(ticketId, eventId, CATEGORY, price, attendeeId);
        issuedCount++;
    }

    @Override
    public double price() {
        return getBasePrice() * MULTIPLIER;
    }

    @Override
    public void printBadge() {
        System.out.println("VIP PASS #" + getTicketId()
                + " | Access: " + ACCESS);
    }

    public String getCategory() { return CATEGORY; }
    public String getAccess() { return ACCESS; }
    public static int getIssuedCount() { return issuedCount; }

    public void displayTicket() {
        System.out.println(toString());
    }

    public boolean isVIP() {
        return true;
    }
}
