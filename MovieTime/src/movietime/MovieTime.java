package movietime;

import UI.GUI.AppFrame;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import movietime.accounts.User;
import movietime.authentication.AuthenticationManager;
import movietime.authentication.UserAlreadyExistsException;
import movietime.authentication.UserDoesNotExistException;
import movietime.authentication.ValidationException;
import movietime.database.DatabaseManager;
import movietime.storage.OpeningFileException;
import movietime.storage.ReadWriteObjectException;
import movietime.storage.StorageManager;

public class MovieTime {

    public static void main(String[] args) {

        //        DatabaseManager db = new DatabaseManager();
//        //Movie a = db.getMovieByID(100);
//        /*
//        HashSet<Integer> genres = new HashSet<>();
//        genres.add(Movie.ACTION);
//        genres.add(Movie.ADVENTURE);
//        genres.add(Movie.ANIMATION);
//        
//        List<ArrayList<Movie>> list = db.getUpcomingMoviesByGenre(genres);
//        
//        for(ArrayList<Movie> movies :list){
//            System.out.println("\n");
//            for(Movie movie : movies)
//                System.out.println(movie);
//        }
//        
//        list = db.getUpcomingMoviesByKeyword("aqua");
//        
//        for(ArrayList<Movie> movies :list){
//            System.out.println("\n");
//            for(Movie movie : movies)
//                System.out.println(movie);
//        }
//        
//        HashSet<Integer> movieIds = new HashSet<>();
//        movieIds.add(329996);
//        movieIds.add(299534);
//        
//        ArrayList<Movie> list2 = db.getFollowedMovies(movieIds);
//        System.out.println("\n");
//        for(Movie movieId : list2)
//            System.out.println(movieId);
//        */
//    
//        HashSet<Integer> movieIds = new HashSet<>();
//        movieIds.add(329996);
//        movieIds.add(299534);
//        
//        User userino1 = new User("userino1", "user1", "none", movieIds);
//        User userino2 = new User("userino2", "user2", "none", movieIds);
//        
//        try{
//            //StorageManager.addNewUser(userino1);
//            ArrayList<User> x = StorageManager.getUsersFromFile();
//            
////            x.get(0).removeFavoriteMovie(329996);
////            StorageManager.updateUserInfo(x.get(0));
//            System.out.println(x);
//            
//            AuthenticationManager auth = new AuthenticationManager();
//        
//            try{
//                auth.createUser("maria", "maria", "maria");
//            }catch(OpenningFileException | ReadWriteObjectException |
//                    UserAlreadyExistsException e){
//                System.out.println(e);
//            }
//            
//        }catch(Exception e){
//            System.err.println(e);
//        }
        
//        HashSet<Integer> movieIds = new HashSet<>();
//        movieIds.add(329996);
//        movieIds.add(299534);
//
//        try{
//            System.out.println(StorageManager.getUsersFromFile() + "\n");
//            ArrayList<User> x = StorageManager.getUsersFromFile();
//            x.get(0).addFavoriteMovie(660066);
//            StorageManager.updateUserInfo(x.get(0));
//            System.out.println(StorageManager.getUsersFromFile());
//            
//        }catch(Exception e){
//            System.err.println(e);
//        }
//        
       new AppFrame(new ObservableApp());
    }
}
