package models;

import java.util.List;

public class Screen {
    private String screenId;
    private String name;
    private List<Seat> seats;

    public Screen(String screenId, String name, List<Seat> seats) {
        this.screenId = screenId;
        this.name = name;
        this.seats = seats;
    }

    public String getScreenId() { return screenId; }
    public String getName() { return name; }
    public List<Seat> getSeats() { return seats; }
}
