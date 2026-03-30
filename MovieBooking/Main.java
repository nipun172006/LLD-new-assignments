import enums.SeatType;
import models.*;
import services.MovieBookingService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("🎬 Initializing Movie Booking System...");

        MovieBookingService service = MovieBookingService.getInstance();

        // 1. Setup City
        City bangalore = new City("C1", "Bangalore");
        service.addCity(bangalore);

        // 2. Setup Movies
        Movie avengers = new Movie("M1", "Avengers: Endgame", 180, "Action", "English");
        Movie inception = new Movie("M2", "Inception", 148, "Sci-Fi", "English");
        service.addMovie(avengers);
        service.addMovie(inception);

        // 3. Setup Seats and Screens
        List<Seat> screen1Seats = createSeats(100);
        Screen screen1 = new Screen("S1", "Screen 1", screen1Seats);
        
        List<Seat> screen2Seats = createSeats(80);
        Screen screen2 = new Screen("S2", "Screen 2", screen2Seats);

        // 4. Setup Theatres
        Theatre pvr = new Theatre("T1", "PVR Forum Mall", bangalore, Arrays.asList(screen1));
        Theatre inox = new Theatre("T2", "INOX Garuda Mall", bangalore, Arrays.asList(screen2));
        service.addTheatre(pvr);
        service.addTheatre(inox);

        // 5. Setup Shows
        LocalDateTime now = LocalDateTime.now();
        Show show1 = new Show("SH1", avengers, screen1, pvr, now.plusHours(2), now.plusHours(5));
        Show show2 = new Show("SH2", inception, screen2, inox, now.plusHours(3), now.plusHours(6));
        Show show3 = new Show("SH3", avengers, screen2, inox, now.plusHours(7), now.plusHours(10));
        
        service.addShow(show1);
        service.addShow(show2);
        service.addShow(show3);

        System.out.println("\n--- User Flow 1: City Based Browsing ---");
        System.out.println("1. Showing Theatres in Bangalore:");
        List<Theatre> blrTheatres = service.showTheatres("C1");
        for (Theatre t : blrTheatres) {
            System.out.println("   - " + t.getName());
        }

        System.out.println("\n2. Showing Movies in Bangalore:");
        List<Movie> blrMovies = service.showMovies("C1");
        for (Movie m : blrMovies) {
            System.out.println("   - " + m.getTitle() + " (" + m.getGenre() + ")");
        }

        System.out.println("\n--- User Flow 2: Movie Based Search ---");
        System.out.println("Searching shows for 'Avengers: Endgame' in Bangalore:");
        List<Show> avengersShows = service.getShowsForMovieInCity("M1", "C1");
        for (Show s : avengersShows) {
            System.out.println("   - " + s.getTheatre().getName() + " - " + s.getScreen().getName() + " - " + s.getStartTime().toLocalTime());
        }

        System.out.println("\n--- User Flow 3: Booking Tickets ---");
        System.out.println("Booking seats 1, 2 for Show SH1...");
        List<Seat> seatsToBook = new ArrayList<>();
        seatsToBook.add(screen1Seats.get(0)); // Seat 1
        seatsToBook.add(screen1Seats.get(1)); // Seat 2
        
        try {
            Ticket ticket1 = service.bookTickets("SH1", seatsToBook);
            System.out.println("✅ Booking Successful! Ticket details:");
            System.out.println(ticket1);
        } catch (Exception e) {
            System.out.println("❌ Booking Failed: " + e.getMessage());
        }
        
        System.out.println("\nAttempting Double Booking for Seat 1 (should fail)...");
        try {
            List<Seat> doubleBookSeats = new ArrayList<>();
            doubleBookSeats.add(screen1Seats.get(0)); // Seat 1 again
            Ticket ticket2 = service.bookTickets("SH1", doubleBookSeats);
            System.out.println("✅ Booking Successful!");
        } catch (Exception e) {
            System.out.println("❌ Booking Failed: " + e.getMessage());
        }
    }

    private static List<Seat> createSeats(int count) {
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            SeatType type = (i > 80) ? SeatType.RECLINER : (i > 50 ? SeatType.PREMIUM : SeatType.REGULAR);
            seats.add(new Seat("Seat" + i, (i / 10) + 1, i, type));
        }
        return seats;
    }
}
