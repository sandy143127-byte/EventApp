package eventapp;

public class Event {
	private int eventId;
	private String name;
	private String date;
	private String venue;
	private int capacity;
	private int bookedTickets;
		
	public Event(int eventId,String name,String date,String venue,int capacity) {
		this.eventId=eventId;
		this.name=name;
		this.date=date;
		this.venue=venue;
		this.capacity=capacity;
		this.bookedTickets=0;
		}
		
	public int getEventId() {
		return eventId;
		}
	public String getName() {
		return name;
		}
	public String getDate() {
		return date;
		}
	public String getVenue() {
		return venue;
		}
	public int getCapacity() {
		return capacity;
		}
	public int getBookedTickets() {
		return bookedTickets;
		}
		
	public void setName(String name) {
		this.name=name;
		}
	public void setDate(String date) {
		this.date=date;
		}
	public void setVenue(String venue) {
		this.venue=venue;
		}
	public void setCapacity(int capacity) {
		if(capacity>=bookedTickets&&capacity>0) {
		this.capacity=capacity;
			}
		}
		
	public boolean bookTicket() {
		if(bookedTickets<capacity) {
		bookedTickets++;
		return true;
			}
			return false;
		}
		
		public void cancelTicket() {
			if(bookedTickets>0) {
				bookedTickets--;
			}
		}
		
		public int getAvailableSeats() {
			return capacity-bookedTickets;
		}
		
		public void createEvent() {
			System.out.println("Event created successfully!");
		}
		
		public void displayEvent() {
			
			System.out.println("Event ID:"+eventId);
			System.out.println("Name:"+name);
			System.out.println("Date:"+date);
			System.out.println("Venue:"+venue);
			System.out.println("Capacity:"+capacity);
			System.out.println("Available Seats:"+getAvailableSeats());
		}
		
		public String toString() {
			return name+"-"+date+"-"+venue;
		}
	}
