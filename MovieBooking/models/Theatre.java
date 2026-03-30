package models;

import java.util.List;

public class Theatre {
    private String theatreId;
    private String name;
    private City city;
    private List<Screen> screens;

    public Theatre(String theatreId, String name, City city, List<Screen> screens) {
        this.theatreId = theatreId;
        this.name = name;
        this.city = city;
        this.screens = screens;
    }

    public String getTheatreId() { return theatreId; }
    public String getName() { return name; }
    public City getCity() { return city; }
    public List<Screen> getScreens() { return screens; }
}
