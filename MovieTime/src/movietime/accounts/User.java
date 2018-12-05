package movietime.accounts;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User implements Serializable{
    static final long serialVersionUID = 1L;
    
    private final String username;
    private final String firstName;
    private final String lastName;
    private HashSet<Integer> favoriteMovieIDs;
    private HashSet<Integer> preferredGenres;
    private NotificationPreferences preferences;
    
    //TODO: Add functions to edit preferences

    /**
     * Constructor without any followed movies (when it's a new user)
     * @param username user's username
     * @param firstName user's first name
     * @param lastName user's last name
     */
    public User(String username, String firstName, String lastName) {
        this(username, firstName, lastName,
                new HashSet<Integer>(), new HashSet<Integer>());
    }

    /**
     * Constructor with followed movies (e.g. recovering from file)
     * @param username user's username
     * @param firstName user's first name
     * @param lastName user's last name
     * @param favoriteMovieIDs list of movie ID's
     * @param preferedGenres list of prefered genres
     */
    public User(String username, String firstName, String lastName,
            HashSet<Integer> favoriteMovieIDs, HashSet<Integer> preferedGenres){
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.favoriteMovieIDs = favoriteMovieIDs;
        this.preferredGenres = preferedGenres;
        preferences = new NotificationPreferences();
    }
    
    /**
     * Verifies if a specific movie id is being followed by the user
     * @param id
     * @return <tt>true</tt> if the movie is being followed
     */
    public boolean checkMovieId(int id){
        return favoriteMovieIDs.contains(id);
    }
    
    /**
     * Adds a movie to favorite/followed movies list
     * @param id
     * @return <tt>true</tt> if the movie was added to the list
     */
    public void addFavoriteMovie(int id){
        
        synchronized(favoriteMovieIDs){
            favoriteMovieIDs.add(id);
        }
    }
    
    /**
     * Removes a movie from the favorite/followed movies list
     * @param id
     * @return <tt>true</tt> if the movie was in the list
     */
    public void removeFavoriteMovie(int id){
        favoriteMovieIDs.remove(id);
    }

    /**
     * Obtains username from logged user
     * @return username from logged user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Obtains first name from logged user
     * @return first name from logged user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Obtains last name from logged user
     * @return last name from logged user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get list of favorite movies ids from logged user
     * @return Set of favorite movies IDs
     */
    public final Set<Integer> getFavoriteMovieIDs() {
        synchronized(favoriteMovieIDs){
            return Collections.unmodifiableSet((Set<Integer>) favoriteMovieIDs);
        }
    }
    
    public NotificationPreferences getPreferences() {
        return preferences;
    }

    public HashSet<Integer> getPreferredGenres() {
        return preferredGenres;
    }
    
    public void addPreferredGenre(Integer genreID){
        preferredGenres.add(genreID);
    }
    
    public void removePreferredGenre(Integer genreID){
        preferredGenres.remove(genreID);
    }
    
    public boolean checkPreferredGenre(int genreID){
        return preferredGenres.contains(genreID);
    }
    
    public void updatePreferredGenres(HashSet<Integer> genreSet){
        preferredGenres = genreSet != null? genreSet : new HashSet<>();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        
        if(!(obj instanceof User))
            return false;
        
        User a = (User) obj;
        
        return this.username.equalsIgnoreCase(a.username);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.username);
        return hash;
    }
    
    @Override
    public String toString() {
        return "User{" + "username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", favoriteMovieIDs=" + favoriteMovieIDs + '}';
    }
}
