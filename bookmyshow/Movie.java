public class Movie {
    private final String movieId;
    private final String title;
    private final String genre;
    private final int durationMinutes;
    private final String language;

    public Movie(String movieId, String title, String genre, int durationMinutes, String language) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
        this.language = language;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %d mins", title, language, durationMinutes);
    }
}
