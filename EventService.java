package eventapp;

import java.util.ArrayList;

public class EventService {
    private ArrayList<Event> events;
    private ArrayList<Attendee> attendees;
    private ArrayList<Ticket> tickets;
    private int nextAttendeeId;
    private int nextTicketId;
    private String serviceName;

    public EventService() {
        events = new ArrayList<>();
        attendees = new ArrayList<>();
        tickets = new ArrayList<>();
        nextAttendeeId = 1;
        nextTicketId = 1;
        serviceName = "Event Management Service";
    }

    public Event createEvent(int id, String name, String date,
                             String venue, int capacity) {
        Event event = new Event(id, name, date, venue, capacity);
        events.add(event);
        event.createEvent();
        return event;
    }

    public Attendee rsvp(String name, String email) {
        if (events.isEmpty()) {
            System.out.println("Create an event first!");
            return null;
        }

        return rsvp(name, email, events.get(0).getEventId());
    }

    public Attendee rsvp(String name, String email, int eventId) {
        if (findEvent(eventId) == null) {
            System.out.println("Event not found!");
            return null;
        }

        Attendee attendee = new Attendee(
                nextAttendeeId++, name, email,
                "Pending", false, true);

        attendees.add(attendee);
        System.out.println("RSVP confirmed for " + name);
        return attendee;
    }

    public boolean rsvp(int attendeeId) {
        Attendee attendee = findAttendee(attendeeId);

        if (attendee == null) {
            System.out.println("Attendee not found!");
            return false;
        }

        attendee.setRsvpStatus(true);
        System.out.println("RSVP confirmed for "
                + attendee.getName());
        return true;
    }

    public Ticket sellTicket(int eventId, int attendeeId,
                             String type) {
        Event event = findEvent(eventId);
        Attendee attendee = findAttendee(attendeeId);

        if (event == null || attendee == null) {
            System.out.println("Event or attendee not found!");
            return null;
        }

        if (!attendee.getRsvpStatus()) {
            System.out.println("RSVP required before ticket sale!");
            return null;
        }

        if (!event.hasCapacity(countSoldTickets(eventId))) {
            System.out.println("Event capacity is full!");
            return null;
        }

        Ticket ticket;

        if (type.equalsIgnoreCase("VIP")) {
            ticket = new VIPTicket(
                    nextTicketId++, eventId, 100.0, attendeeId);
        } else if (type.equalsIgnoreCase("Regular")) {
            ticket = new RegularTicket(
                    nextTicketId++, eventId, 50.0, attendeeId);
        } else {
            System.out.println("Invalid ticket type!");
            return null;
        }

        if (ticket.markSold()) {
            tickets.add(ticket);
            attendee.setTicketType(ticket.getType());
            System.out.println(ticket.getType()
                    + " ticket sold to " + attendee.getName()
                    + " for Rs." + ticket.price());
            return ticket;
        }

        return null;
    }

    public boolean checkIn(int attendeeId) {
        Attendee attendee = findAttendee(attendeeId);

        if (attendee == null) {
            System.out.println("Attendee not found!");
            return false;
        }

        if (attendee.getCheckInStatus()) {
            System.out.println("Already checked in!");
            return false;
        }

        for (Ticket ticket : tickets) {
            if (ticket.getAttendeeId() == attendeeId
                    && ticket.isSold()) {
                attendee.checkIn();
                System.out.println(attendee.getName()
                        + " checked in successfully.");
                return true;
            }
        }

        System.out.println("No valid ticket for check-in!");
        return false;
    }

    public void attendeeList(int eventId) {
        Event event = findEvent(eventId);

        if (event == null) {
            System.out.println("Event not found!");
            return;
        }

        System.out.println("\nAttendee List: " + event.getName());
        System.out.println("--------------------------------------");

        for (Attendee attendee : attendees) {
            for (Ticket ticket : tickets) {
                if (ticket.getEventId() == eventId
                        && ticket.getAttendeeId()
                        == attendee.getAttendeeId()) {
                    System.out.println(attendee.getName()
                            + " | " + attendee.getEmail()
                            + " | " + ticket.getType()
                            + " | Checked In: "
                            + attendee.getCheckInStatus());
                    break;
                }
            }
        }
    }

    public double calculateRevenue(int eventId, String type) {
        double revenue = 0;

        for (Ticket ticket : tickets) {
            if (ticket.getEventId() == eventId
                    && ticket.getType().equalsIgnoreCase(type)
                    && ticket.isSold()) {
                revenue += ticket.price();
            }
        }

        return revenue;
    }

    public void reportRevenue(int eventId) {
        Event event = findEvent(eventId);

        if (event == null) {
            System.out.println("Event not found!");
            return;
        }

        double regular = calculateRevenue(eventId, "Regular");
        double vip = calculateRevenue(eventId, "VIP");

        System.out.println("\nRevenue Report: " + event.getName());
        System.out.println("Regular Tickets: Rs." + regular);
        System.out.println("VIP Tickets: Rs." + vip);
        System.out.println("Total Revenue: Rs." + (regular + vip));
    }

    private Event findEvent(int eventId) {
        for (Event event : events) {
            if (event.getEventId() == eventId)
                return event;
        }
        return null;
    }

    private Attendee findAttendee(int attendeeId) {
        for (Attendee attendee : attendees) {
            if (attendee.getAttendeeId() == attendeeId)
                return attendee;
        }
        return null;
    }

    private int countSoldTickets(int eventId) {
        int count = 0;

        for (Ticket ticket : tickets) {
            if (ticket.getEventId() == eventId && ticket.isSold())
                count++;
        }

        return count;
    }

    public void displayEvents() {
        System.out.println("\nEvents:");
        for (Event event : events) {
            event.displayEvent();
        }
    }

    public String getServiceName() {
        return serviceName;
    }

    public int getTotalTickets() {
        return tickets.size();
    }

    public int getTotalAttendees() {
        return attendees.size();
    }

    public void displayTicketBadges() {
        for (Ticket ticket : tickets) {
            ticket.printBadge();
        }
    }
}
