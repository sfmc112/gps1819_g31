package movietime.database;

public interface DatabaseConstants {
    static final String TOTAL_PAGES = "total_pages";
    static final String TOTAL_RESULTS = "total_results";
    static final String RESULTS = "results";
    static final String MOVIE_GENRE_IDS = "genre_ids";
    static final String MOVIE_POSTER_PATH = "poster_path";
    static final String MOVIE_BACKDROP_PATH = "backdrop_path";
    static final String MOVIE_ID = "id";
    static final String MOVIE_TITLE = "title";
    static final String MOVIE_RELEASE_DATE = "release_date";
    static final String MOVIE_OVERVIEW ="overview";
    static final String MOVIE_CAST = "cast";
    static final String MOVIE_CAST_NAME = "name";
    static final String MOVIE_CREW = "crew";
    static final String MOVIE_CREW_JOB = "job";
    static final String MOVIE_CREW_DIRECTOR = "Director";
    static final String MOVIE_CREW_NAME = "name";
    static final String NOT_SPECIFIED = "not specified";
    static final String MOVIE_GENRES = "genres";
    static final String GENRE_ID = "id";
    static final String MOVIE_CREDITS = "credits";
    
    
    static final int MAX_CAST = 3;
    static final int MAX_PAGES = 10;
    static final int MAX_NUMBER_OF_GENRES = 19;
}
