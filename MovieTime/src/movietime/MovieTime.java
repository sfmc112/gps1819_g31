package movietime;

import java.util.ArrayList;
import java.util.HashSet;
import movietime.database.DatabaseManager;
import movietime.database.Movie;

public class MovieTime {

    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();
        Movie a = db.getMovieByID(100);
        
        HashSet<Integer> genres = new HashSet<>();
        genres.add(Movie.ACTION);
        genres.add(Movie.ADVENTURE);
        genres.add(Movie.ANIMATION);
        
        db.getMoviesByGenre(genres);
        
        //System.out.println("This is A" + a);
    }
}