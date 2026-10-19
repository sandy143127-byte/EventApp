package eventapp;

public class Attendee {
    private int attendeeId;
    private String name;
    private String email;
    private String ticketType;
    private boolean checkInStatus;
    private boolean rsvpStatus;

    public Attendee(int attendeeId, String name, String email,
                    String ticketType, boolean checkInStatus,
                    boolean rsvpStatus) {
        this.attendeeId = attendeeId;
        this.name = name;
        this.email = email;
        this.ticketType = ticketType;
        this.checkInStatus = checkInStatus;
        this.rsvpStatus = rsvpStatus;
    }

    public int getAttendeeId() { return attendeeId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getTicketType() { return ticketType; }
    public boolean getCheckInStatus() { return checkInStatus; }
    public boolean getRsvpStatus() { return rsvpStatus; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setTicketType(String type) { ticketType = type; }

    public void setRsvpStatus(boolean status) {
        rsvpStatus = status;
    }

    public void checkIn() {
        checkInStatus = true;
    }

    public void printBadge() {
        System.out.println("ATTENDEE: " + name
                + " | Ticket: " + ticketType);
    }

    public String toString() {
        return attendeeId + " | " + name + " | " + email;
    }
}
