package eventapp;

public class RegularTicket extends Ticket {
    private static final String CATEGORY = "Regular";
    private static final String ACCESS = "Standard";
    private static final double DISCOUNT = 0.0;
    private static int issuedCount = 0;

    public RegularTicket(int ticketId, int eventId,
                         double price, int attendeeId) {
        super(ticketId, eventId, CATEGORY, price, attendeeId);
        issuedCount++;
    }

    @Override
    public double price() {
        return getBasePrice() * (1 - DISCOUNT);
    }

    @Override
    public void printBadge() {
        System.out.println("REGULAR PASS #" + getTicketId()
                + " | Access: " + ACCESS);
    }

    public String getCategory() { return CATEGORY; }
    public String getAccess() { return ACCESS; }
    public static int getIssuedCount() { return issuedCount; }

    public void displayTicket() {
        System.out.println(toString());
    }

    public boolean isRegular() {
        return true;
    }
}
