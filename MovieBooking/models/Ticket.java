package models;

import java.time.LocalDateTime;
import java.util.List;

public class Ticket {
    private String ticketId;
    private Show show;
    private List<Seat> seats;
    private double totalPrice;
    private LocalDateTime bookingTime;

    public Ticket(String ticketId, Show show, List<Seat> seats, double totalPrice, LocalDateTime bookingTime) {
        this.ticketId = ticketId;
        this.show = show;
        this.seats = seats;
        this.totalPrice = totalPrice;
        this.bookingTime = bookingTime;
    }

    public String getTicketId() { return ticketId; }
    public Show getShow() { return show; }
    public List<Seat> getSeats() { return seats; }
    public double getTotalPrice() { return totalPrice; }
    public LocalDateTime getBookingTime() { return bookingTime; }
    
    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", movie='" + show.getMovie().getTitle() + '\'' +
                ", theatre='" + show.getTheatre().getName() + '\'' +
                ", screen='" + show.getScreen().getName() + '\'' +
                ", seats=" + seats.size() +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
