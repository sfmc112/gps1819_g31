package movietime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;
import movietime.accounts.User;
import movietime.authentication.AuthenticationManager;
import movietime.authentication.UserAlreadyExistsException;
import movietime.authentication.UserDoesNotExistException;
import movietime.authentication.ValidationException;
import movietime.database.DatabaseManager;
import movietime.database.Movie;
import movietime.notification.NotificationManager;
import movietime.notification.NotificationService;
import movietime.storage.OpeningFileException;
import movietime.storage.ReadWriteObjectException;

public class ObservableApp extends Observable {
    private User user;
    private final NotificationManager notificationManager;
    private NotificationService service;

    public ObservableApp() {
        user = new User("username", "first", "last");
        notificationManager = new NotificationManager();
        service = new NotificationService(this);
    }
    
    //Display notification
    public void displayNotification(Movie movie){
        notificationManager.displayPopup(movie, user.getPreferences());
    }
    
    public User getLoggedUser(){
        return user;
    }
    
    public NotificationManager getNotificationManager(){
        return notificationManager;
    }
    
    //Authentication
    public void login(String user) 
            throws UserDoesNotExistException,OpeningFileException,
            ReadWriteObjectException
    {
        try{
            this.user = AuthenticationManager.authenticateUser(user);
            service.start();
            
        }catch(UserDoesNotExistException | ReadWriteObjectException |
                OpeningFileException e)
        {
            throw e;
        }
        
        setChanged();
        notifyObservers();
    }
    
    public void logout()
            throws OpeningFileException, ReadWriteObjectException
    {
        try{
            AuthenticationManager.logoutUser(user);
            
            user = null;
            service.exit();
        }catch(OpeningFileException | ReadWriteObjectException e){
            throw e;
        }
    }
    
    public void createUser(String username, String firstName, String lastName) 
            throws UserAlreadyExistsException, ValidationException,
            ReadWriteObjectException, OpeningFileException
            
    {
        try{
            AuthenticationManager.createUser(username, firstName, lastName);
            
        }catch(UserAlreadyExistsException e){
            throw e;
        }catch(ValidationException e){
            throw e;
        }catch(ReadWriteObjectException e){
            throw e;
        }catch(OpeningFileException e){
            throw e;
        }
    }
    
    
    //User preferences
    public void addPreferedMovie(int movie){
        user.addFavoriteMovie(movie);
        
        setChanged();
        notifyObservers();
    }
    
    public void removeFavouriteMovie(int id){
        user.removeFavoriteMovie(id);
        
        setChanged();
        notifyObservers();
    }
    
    public boolean isMovieBeingFollowed(int id){
        return user.checkMovieId(id);
    }
    
    public String getFirstName(){
        return user.getFirstName();
    }
    
    public String getLastName(){
        return user.getLastName();
    }
    
    public String getUsername(){
        return user.getUsername();
    }
    
    public void setDaysToAlert(int days){
        user.getPreferences().setDaysToAlert(days);
    }
    
    public void setIncludeDirector(boolean choice){
        user.getPreferences().setIncludeDirector(choice);
    }
    
    public void setIncludeMainActors(boolean choice){
        user.getPreferences().setIncludeMainActors(choice);
    }
    
    public void setIncludeGenre(boolean choice){
        user.getPreferences().setIncludeGenre(choice);
    }
    
    public boolean isDirectorIncluded(){
        return user.getPreferences().isIncludeDirector();
    }
    
    public boolean isCastIncluded(){
        return user.getPreferences().isIncludeMainActors();
    }
    
    public boolean isGenreIncluded(){
        return user.getPreferences().isIncludeGenre();
    }
    
    public Set<Integer> getFavouriteMoviesId(){
        return user.getFavoriteMovieIDs();
    }
    
    //Database Queries
    public ArrayList<ArrayList<Movie>> getUpcomingMovies(){
        return DatabaseManager.getUpcomingMovies();
    }
    
    public ArrayList<ArrayList<Movie>> getUpcomingMoviesByGenre(
                                                    HashSet<Integer> genres)
    {
        return DatabaseManager.getUpcomingMoviesByGenre(genres);
    }
    
    public ArrayList<ArrayList<Movie>> getUpcomingMoviesByKeyword(
                                                               String keyword)
    {
        return DatabaseManager.getUpcomingMoviesByKeyword(keyword);
    }
    
    public ArrayList<ArrayList<Movie>> getUpcomingMoviesByKeywordAndGenre(
            String keyword, int genre)
    {
        return DatabaseManager.getUpcomingMoviesByKeywordAndGenre(keyword,genre);
    }
    
    public Movie getMovieById(int id){
        return DatabaseManager.getMovieByID(id);
    }
    
    public ArrayList<Movie> getFollowedMovies(){
        return DatabaseManager.getFollowedMovies(
                (HashSet)user.getFavoriteMovieIDs());
    }
    
    public ArrayList<ArrayList<Movie>> getPreferedMovies(){
        return DatabaseManager.getUpcomingMoviesByGenre(user.getPreferedGenres());
    }
}
