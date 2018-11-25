package movietime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import movietime.accounts.User;
import movietime.database.DatabaseManager;
import movietime.database.Movie;
import movietime.storage.StorageManager;

public class MovieTime {

    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();
        //Movie a = db.getMovieByID(100);
        /*
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
        
        list = db.getUpcomingMoviesByKeyword("aqua");
        
        for(ArrayList<Movie> movies :list){
            System.out.println("\n");
            for(Movie movie : movies)
                System.out.println(movie);
        }
        
        HashSet<Integer> movieIds = new HashSet<>();
        movieIds.add(329996);
        movieIds.add(299534);
        
        ArrayList<Movie> list2 = db.getFollowedMovies(movieIds);
        System.out.println("\n");
        for(Movie movieId : list2)
            System.out.println(movieId);
        */
    
        HashSet<Integer> movieIds = new HashSet<>();
        movieIds.add(329996);
        movieIds.add(299534);
        
        User userino1 = new User("userino1", "user1", "none", movieIds);
        User userino2 = new User("userino2", "user2", "none", movieIds);
        
        try{
            StorageManager.addNewUser(userino1);

//            ArrayList<User> users = StorageManager.getUsersFromFile();
//            
//            System.out.println("users size: " + users.size());
//            for(ListIterator<User>lt=users.listIterator();lt.hasNext();lt.next())
//                System.out.println(" elements: " +lt.next().getUsername());
        
        }catch(Exception e){
            System.err.println(e);
        }
    }
}