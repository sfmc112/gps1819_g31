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
    public void displayNotification(Movie movie) {
        notificationManager.displayPopup(movie, user.getPreferences());
    }

    public User getLoggedUser() {
        return user;
    }

    public NotificationManager getNotificationManager() {
        return notificationManager;
    }

    //Authentication
    public void login(String user)
            throws UserDoesNotExistException, OpeningFileException,
            ReadWriteObjectException {
        try {
            this.user = AuthenticationManager.authenticateUser(user);
            service = new NotificationService(this);
            service.start();

        } catch (UserDoesNotExistException | ReadWriteObjectException
                | OpeningFileException e) {
            throw e;
        }

        setChanged();
        notifyObservers();
    }

    public void logout()
            throws OpeningFileException, ReadWriteObjectException {
        try {
            AuthenticationManager.logoutUser(user);

            user = null;
            service.exit();
            user = new User("","","");
        } catch (OpeningFileException | ReadWriteObjectException e) {
            throw e;
        }
        
        setChanged();
        notifyObservers();
    }

    public void createUser(String username, String firstName, String lastName)
            throws UserAlreadyExistsException, ValidationException,
            ReadWriteObjectException, OpeningFileException {
        try {
            AuthenticationManager.createUser(username, firstName, lastName);

        } catch (UserAlreadyExistsException e) {
            throw e;
        } catch (ValidationException e) {
            throw e;
        } catch (ReadWriteObjectException e) {
            throw e;
        } catch (OpeningFileException e) {
            throw e;
        }
    }

    //User preferences
    public void addPreferredMovie(int movie) {
        user.addFavoriteMovie(movie);

        setChanged();
        notifyObservers();
    }

    public void removeFavouriteMovie(int id) {
        user.removeFavoriteMovie(id);

        setChanged();
        notifyObservers();
    }

    public boolean checkPreferredGenre(int genreID) {
        return user.checkPreferredGenre(genreID);
    }

    public void addSetPreferredGenres(HashSet<Integer> genreSet) {
        user.addSetPreferredGenres(genreSet);
        
        setChanged();
        notifyObservers();
    }

    public void removeSetPreferredGenres(HashSet<Integer> genreSet) {
        user.removeSetPreferredGenres(genreSet);
        
        setChanged();
        notifyObservers();
    }

    public boolean isMovieBeingFollowed(int id) {
        return user.checkMovieId(id);
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public String getLastName() {
        return user.getLastName();
    }

    public String getUsername() {
        return user.getUsername();
    }
    
    public int getDaysToAlert() {
        return user.getPreferences().getDaysToAlert();
    }

    public void setDaysToAlert(int days) {
        user.getPreferences().setDaysToAlert(days);
    }

    public void setIncludeDirector(boolean choice) {
        user.getPreferences().setIncludeDirector(choice);
    }

    public void setIncludeMainActors(boolean choice) {
        user.getPreferences().setIncludeMainActors(choice);
    }

    public void setIncludeGenre(boolean choice) {
        user.getPreferences().setIncludeGenre(choice);
    }

    public boolean isDirectorIncluded() {
        return user.getPreferences().isIncludeDirector();
    }

    public boolean isCastIncluded() {
        return user.getPreferences().isIncludeMainActors();
    }

    public boolean isGenreIncluded() {
        return user.getPreferences().isIncludeGenre();
    }

    public Set<Integer> getFavouriteMoviesId() {
        return user.getFavoriteMovieIDs();
    }
    
    public ArrayList<String> getStringGenres(){
        ArrayList<String> stringGenres = new ArrayList<>();
        stringGenres.add(Movie.getSingleGenreAsString(Movie.ACTION));
        stringGenres.add(Movie.getSingleGenreAsString(Movie.ADVENTURE));
        stringGenres.add(Movie.getSingleGenreAsString(Movie.ANIMATION));
        stringGenres.add(Movie.getSingleGenreAsString(Movie.COMEDY));
        stringGenres.add(Movie.getSingleGenreAsString(Movie.CRIME));
        stringGenres.add(Movie.getSingleGenreAsString(Movie.DOCUMENTARY));
        stringGenres.add(Movie.getSingleGenreAsString(Movie.DRAMA));
        stringGenres.add(Movie.getSingleGenreAsString(Movie.FAMILY));
        stringGenres.add(Movie.getSingleGenreAsString(Movie.FANTASY));
        stringGenres.add(Movie.getSingleGenreAsString(Movie.HISTORY));
        stringGenres.add(Movie.getSingleGenreAsString(Movie.HORROR));
        stringGenres.add(Movie.getSingleGenreAsString(Movie.MUSIC));
        stringGenres.add(Movie.getSingleGenreAsString(Movie.MYSTERY));
        stringGenres.add(Movie.getSingleGenreAsString(Movie.ROMANCE));
        stringGenres.add(Movie.getSingleGenreAsString(Movie.SCIENCE_FICTION));
        stringGenres.add(Movie.getSingleGenreAsString(Movie.THRILLER));
        stringGenres.add(Movie.getSingleGenreAsString(Movie.TV_MOVIE));
        stringGenres.add(Movie.getSingleGenreAsString(Movie.WAR));
        stringGenres.add(Movie.getSingleGenreAsString(Movie.WESTERN));
        return stringGenres;
    }

    //Database Queries
    public ArrayList<ArrayList<Movie>> getUpcomingMovies() {
        return DatabaseManager.getUpcomingMovies();
    }

    public ArrayList<ArrayList<Movie>> getUpcomingMoviesByGenre(
            HashSet<Integer> genres) {
        return DatabaseManager.getUpcomingMoviesByGenre(genres);
    }

    public ArrayList<ArrayList<Movie>> getUpcomingMoviesByKeyword(
            String keyword) {
        return DatabaseManager.getUpcomingMoviesByKeyword(keyword);
    }

    public ArrayList<ArrayList<Movie>> getUpcomingMoviesByKeywordAndGenre(
            String keyword, int genre) {
        return DatabaseManager.getUpcomingMoviesByKeywordAndGenre(keyword, genre);
    }

    public Movie getMovieById(int id) {
        return DatabaseManager.getMovieByID(id);
    }

    public ArrayList<Movie> getFollowedMovies() {
        return DatabaseManager.getFollowedMovies(
                (HashSet) user.getFavoriteMovieIDs());
    }

    public ArrayList<ArrayList<Movie>> getPreferredMovies() {
        return DatabaseManager.getUpcomingMoviesByGenre(user.getPreferredGenres());
    }
}
