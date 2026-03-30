package models;

public class Movie {
    private String movieId;
    private String title;
    private int durationInMinutes;
    private String genre;
    private String language;

    public Movie(String movieId, String title, int durationInMinutes, String genre, String language) {
        this.movieId = movieId;
        this.title = title;
        this.durationInMinutes = durationInMinutes;
        this.genre = genre;
        this.language = language;
    }

    public String getMovieId() { return movieId; }
    public String getTitle() { return title; }
    public int getDurationInMinutes() { return durationInMinutes; }
    public String getGenre() { return genre; }
    public String getLanguage() { return language; }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movie movie = (Movie) obj;
        return movieId.equals(movie.movieId);
    }
    
    @Override
    public int hashCode() {
        return movieId.hashCode();
    }
}
