package models;

import enums.SeatStatus;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Show {
    private String showId;
    private Movie movie;
    private Screen screen;
    private Theatre theatre;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Map<String, SeatStatus> seatStatusMap; // Map of seatId to SeatStatus

    public Show(String showId, Movie movie, Screen screen, Theatre theatre, LocalDateTime startTime, LocalDateTime endTime) {
        this.showId = showId;
        this.movie = movie;
        this.screen = screen;
        this.theatre = theatre;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seatStatusMap = new ConcurrentHashMap<>();
        
        // Initialize all seats as AVAILABLE
        for (Seat seat : screen.getSeats()) {
            seatStatusMap.put(seat.getSeatId(), SeatStatus.AVAILABLE);
        }
    }

    public String getShowId() { return showId; }
    public Movie getMovie() { return movie; }
    public Screen getScreen() { return screen; }
    public Theatre getTheatre() { return theatre; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }

    public synchronized boolean lockSeats(java.util.List<Seat> seatsToLock) {
        for (Seat seat : seatsToLock) {
            if (seatStatusMap.get(seat.getSeatId()) != SeatStatus.AVAILABLE) {
                return false;
            }
        }
        for (Seat seat : seatsToLock) {
            seatStatusMap.put(seat.getSeatId(), SeatStatus.LOCKED);
        }
        return true;
    }
    
    public synchronized void bookSeats(java.util.List<Seat> seatsToBook) {
        for (Seat seat : seatsToBook) {
            seatStatusMap.put(seat.getSeatId(), SeatStatus.BOOKED);
        }
    }
    
    public synchronized void unlockSeats(java.util.List<Seat> seatsToUnlock) {
        for (Seat seat : seatsToUnlock) {
            seatStatusMap.put(seat.getSeatId(), SeatStatus.AVAILABLE);
        }
    }
    
    public SeatStatus getSeatStatus(String seatId) {
        return seatStatusMap.get(seatId);
    }
}
