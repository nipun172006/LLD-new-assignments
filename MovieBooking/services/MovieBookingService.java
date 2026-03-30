package services;

import models.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MovieBookingService {
    private static MovieBookingService instance;

    // In-memory data stores
    private Map<String, City> cityMap; // cityId -> City
    private Map<String, Theatre> theatreMap; // theatreId -> Theatre
    private Map<String, Movie> movieMap; // movieId -> Movie
    private Map<String, Show> showMap; // showId -> Show

    private MovieBookingService() {
        cityMap = new ConcurrentHashMap<>();
        theatreMap = new ConcurrentHashMap<>();
        movieMap = new ConcurrentHashMap<>();
        showMap = new ConcurrentHashMap<>();
    }

    public static synchronized MovieBookingService getInstance() {
        if (instance == null) {
            instance = new MovieBookingService();
        }
        return instance;
    }

    // Add methods for initialization
    public void addCity(City city) {
        cityMap.put(city.getCityId(), city);
    }

    public void addTheatre(Theatre theatre) {
        theatreMap.put(theatre.getTheatreId(), theatre);
        theatre.getCity().addTheatre(theatre);
    }

    public void addMovie(Movie movie) {
        movieMap.put(movie.getMovieId(), movie);
    }

    public void addShow(Show show) {
        showMap.put(show.getShowId(), show);
    }

    // API 1: Show Theatres by City
    public List<Theatre> showTheatres(String cityId) {
        City city = cityMap.get(cityId);
        if (city == null) {
            throw new IllegalArgumentException("City not found");
        }
        return city.getTheatres();
    }

    // API 2: Show Movies by City
    public List<Movie> showMovies(String cityId) {
        City city = cityMap.get(cityId);
        if (city == null) {
            throw new IllegalArgumentException("City not found");
        }

        List<Theatre> theatres = city.getTheatres();
        Set<Movie> moviesInCity = new HashSet<>();

        // Fetch all shows across all theatres in the city, extract movies
        for (Show show : showMap.values()) {
            if (theatres.contains(show.getTheatre())) {
                moviesInCity.add(show.getMovie());
            }
        }

        return new ArrayList<>(moviesInCity);
    }
    
    // Additional Helper: Shows for a movie in a city
    public List<Show> getShowsForMovieInCity(String movieId, String cityId) {
        City city = cityMap.get(cityId);
        Movie movie = movieMap.get(movieId);
        if (city == null || movie == null) return Collections.emptyList();
        
        List<Theatre> theatres = city.getTheatres();
        return showMap.values().stream()
                .filter(show -> show.getMovie().equals(movie) && theatres.contains(show.getTheatre()))
                .collect(Collectors.toList());
    }

    // API 3: Book Tickets
    public Ticket bookTickets(String showId, List<Seat> seatsList) {
        Show show = showMap.get(showId);
        if (show == null) {
            throw new IllegalArgumentException("Show not found");
        }

        // Lock seats to prevent concurrency issues
        boolean lockAcquired = show.lockSeats(seatsList);
        if (!lockAcquired) {
            throw new IllegalStateException("One or more seats are already booked/locked by another user.");
        }

        try {
            // Price Calculation (Could use a Strategy pattern here)
            double totalPrice = calculatePrice(seatsList);

            // Mark seats as booked
            show.bookSeats(seatsList);

            // Generate Ticket
            String ticketId = UUID.randomUUID().toString();
            Ticket ticket = new Ticket(ticketId, show, seatsList, totalPrice, LocalDateTime.now());
            
            return ticket;
        } catch (Exception e) {
            // Rollback locking if something fails
            show.unlockSeats(seatsList);
            throw new RuntimeException("Booking failed: " + e.getMessage());
        }
    }

    // Basic pricing strategy based on seat type
    private double calculatePrice(List<Seat> seatsList) {
        double totalPrice = 0;
        for (Seat seat : seatsList) {
            switch (seat.getType()) {
                case REGULAR:
                    totalPrice += 150.0;
                    break;
                case PREMIUM:
                    totalPrice += 250.0;
                    break;
                case RECLINER:
                    totalPrice += 400.0;
                    break;
            }
        }
        return totalPrice;
    }
}
