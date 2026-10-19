package eventapp;

public class EventAppMain {
	public static void main(String[] args) {
		System.out.println("Event Management System Started!");
	}
    static String APP_NAME = "Event Management System";
    static int EVENT_ID = 101;
    static int SECOND_EVENT_ID = 102;
    static String VENUE = "College Auditorium";

    public static void main(String[] args) {
        EventService service = new EventService();

        printHeader();

        Event event1 = service.createEvent(
                EVENT_ID, "Tech Fest", "25-10-2026",
                VENUE, 100);

        service.createEvent(
                SECOND_EVENT_ID, "Cultural Fest",
                "30-10-2026", "Open Ground", 200);

        service.displayEvents();

        System.out.println("\n--- RSVP AND TICKET SALES ---");

        Attendee a1 = service.rsvp(
                "Arun", "arun@gmail.com", EVENT_ID);

        Attendee a2 = service.rsvp(
                "Priya", "priya@gmail.com", EVENT_ID);

        Attendee a3 = service.rsvp(
                "Kumar", "kumar@gmail.com", EVENT_ID);

        if (a1 != null)
            service.sellTicket(EVENT_ID,
                    a1.getAttendeeId(), "Regular");

        if (a2 != null)
            service.sellTicket(EVENT_ID,
                    a2.getAttendeeId(), "VIP");

        if (a3 != null)
            service.sellTicket(EVENT_ID,
                    a3.getAttendeeId(), "Regular");

        System.out.println("\n--- CHECK-IN ---");
        if (a1 != null)
            service.checkIn(a1.getAttendeeId());

        if (a2 != null)
            service.checkIn(a2.getAttendeeId());

        System.out.println("\n--- TICKET BADGES ---");
        service.displayTicketBadges();

        service.attendeeList(EVENT_ID);
        service.reportRevenue(EVENT_ID);

        System.out.println("\n--- SECOND RSVP METHOD ---");
        if (a3 != null)
            service.rsvp(a3.getAttendeeId());

        System.out.println("\nTotal Attendees: "
                + service.getTotalAttendees());
        System.out.println("Total Tickets Sold: "
                + service.getTotalTickets());

        System.out.println("\n" + APP_NAME + " completed.");
    }

    public static void printHeader() {
        System.out.println("==================================");
        System.out.println("  " + APP_NAME);
        System.out.println("==================================");
    }

    public static void showVenue() {
        System.out.println("Venue: " + VENUE);
    }

    public static void showEventId() {
        System.out.println("Event ID: " + EVENT_ID);
    }

    public static void showSecondEventId() {
        System.out.println("Event ID: " + SECOND_EVENT_ID);
    }
}
