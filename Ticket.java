package eventapp;

public class Ticket {
    private int ticketId;
    private int eventId;
    private String type;
    private double price;
    private String state;
    private int attendeeId;

    public Ticket(int ticketId, int eventId, String type,
                  double price, int attendeeId) {
        this.ticketId = ticketId;
        this.eventId = eventId;
        this.type = type;
        this.price = price;
        this.attendeeId = attendeeId;
        this.state = "AVAILABLE";
    }

    public int getTicketId() { return ticketId; }
    public int getEventId() { return eventId; }
    public String getType() { return type; }
    public double getBasePrice() { return price; }
    public String getState() { return state; }
    public int getAttendeeId() { return attendeeId; }

    public void setPrice(double price) {
        if (price >= 0 && state.equals("AVAILABLE"))
            this.price = price;
    }

    public double price() {
        return price;
    }

    public boolean markSold() {
        if (!state.equals("AVAILABLE"))
            return false;

        state = "SOLD";
        return true;
    }

    public void printBadge() {
        System.out.println(type + " Ticket #" + ticketId);
    }

    public boolean isSold() {
        return state.equals("SOLD");
    }

    public String toString() {
        return type + " Ticket #" + ticketId
                + " | Price: " + price() + " | " + state;
    }
}
