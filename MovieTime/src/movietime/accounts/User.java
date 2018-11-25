/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movietime.accounts;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author sarah
 */
public class User implements Serializable{
    static final long serialVersionUID = 1L;
    
    private final String username;
    private final String firstName;
    private final String lastName;
    private HashSet<Integer> favoriteMovieIDs;
    
    //TODO add methods

    /**
     * Constructor without any followed movies (when it's a new user)
     * @param username user's username
     * @param firstName user's first name
     * @param lastName user's last name
     */
    public User(String username, String firstName, String lastName) {
        this(username, firstName, lastName, new HashSet<Integer>());
        //his.sessionKey = sessionKey;
    }

    /**
     * Constructor with followed movies (e.g. recovering from file)
     * @param username user's username
     * @param firstName user's first name
     * @param lastName user's last name
     * @param favoriteMovieIDs list of movie ID's
     */
    public User(String username, String firstName, String lastName, HashSet<Integer> favoriteMovieIDs) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.favoriteMovieIDs = favoriteMovieIDs;
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
    public boolean addFavoriteMovie(int id){
        return favoriteMovieIDs.add(id);
    }
    
    /**
     * Removes a movie from the favorite/followed movies list
     * @param id
     * @return <tt>true</tt> if the movie was in the list
     */
    public boolean removeFavoriteMovie(int id){
        return favoriteMovieIDs.remove(id);
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
        return Collections.unmodifiableSet((Set<Integer>) favoriteMovieIDs);
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", favoriteMovieIDs=" + favoriteMovieIDs + '}';
    }
}
