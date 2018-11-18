package movietime;

import java.util.HashSet;
import java.util.List;
import movietime.database.DatabaseManager;
import movietime.database.Movie;

public class MovieTime {

    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();
        //Movie a = db.getMovieByID(100);
        
        HashSet<Integer> genres = new HashSet<>();
        genres.add(Movie.ACTION);
        genres.add(Movie.ADVENTURE);
        genres.add(Movie.ANIMATION);
        
        List<Movie> list = db.getMoviesByGenre(genres);
        
        for(Movie movie : list)
        System.out.println(movie);
    }
}