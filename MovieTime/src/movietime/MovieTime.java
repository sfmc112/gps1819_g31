package movietime;

import java.util.ArrayList;
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
        
        List<ArrayList<Movie>> list = db.getUpcomingMoviesByGenre(genres);
        
        for(ArrayList<Movie> movies :list){
            System.out.println("\n");
            for(Movie movie : movies)
                System.out.println(movie);
        }
        
        List<ArrayList<Movie>> list2 = db.getUpcomingMoviesByKeyword("aqua");
        
        for(ArrayList<Movie> movies :list2){
            System.out.println("\n");
            for(Movie movie : movies)
                System.out.println(movie);
        }
    }
}