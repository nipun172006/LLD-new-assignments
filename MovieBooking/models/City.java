package models;

import java.util.ArrayList;
import java.util.List;

public class City {
    private String cityId;
    private String name;
    private List<Theatre> theatres;

    public City(String cityId, String name) {
        this.cityId = cityId;
        this.name = name;
        this.theatres = new ArrayList<>();
    }

    public String getCityId() { return cityId; }
    public String getName() { return name; }
    public List<Theatre> getTheatres() { return theatres; }
    
    public void addTheatre(Theatre theatre) {
        this.theatres.add(theatre);
    }
}
