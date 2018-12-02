/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movietime;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import movietime.accounts.User;
import movietime.authentication.AuthenticationManager;
import movietime.authentication.UserAlreadyExistsException;
import movietime.authentication.UserDoesNotExistException;
import movietime.authentication.ValidationException;
import movietime.database.DatabaseManager;
import movietime.database.Movie;
import movietime.storage.StorageManager;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Olympus
 */
public class ObservableAppTest {
    
    private static final int USERNAME_MIN_SIZE = 4;
    private static final int NAME_MIN_SIZE = 3;
    private static final int ANY_NAME_MAX_SIZE = 15;
    
    public ObservableAppTest() {
    }

    /**
     * Test of login method, of class ObservableApp.
     */
    @Test
    public void testLoginWithNoMatchingUser() throws Exception {
        File f = new File(".\\data\\MovieTimeUsers.bin");
        f.delete();
        
        User u1 = new User("username1","user1","user1");
        User u2 = new User("username2","user2","user2");
        
        StorageManager.addNewUser(u1);
        StorageManager.addNewUser(u2);
        
        String username = "Ahoha";
        
        try{
            AuthenticationManager.authenticateUser(username);
            fail("Found Inexistent user, wrong match atributed");
        }catch(UserDoesNotExistException e){
            assertEquals("User does not exist",e.getMessage(), "User does not exist");
        }
        
        f = new File(".\\data\\MovieTimeUsers.bin");
        f.delete();
    }
    
    /**
     * Test of authenticateUser method, of class AuthenticationManager, with matching username, but diferents Uppercases.
     */
    @Test
    public void testLoginWithSameUsernameButDiferentUppercase() throws Exception {

        File f = new File(".\\data\\MovieTimeUsers.bin");
        f.delete();
        
        User u1 = new User("username1","user1","user1");
        User u2 = new User("username2","user2","user2");
        
        StorageManager.addNewUser(u1);
        StorageManager.addNewUser(u2);
        
        String username = "userName1";
        
        try{
            AuthenticationManager.authenticateUser(username);
            
        }catch(UserDoesNotExistException e){
            fail("User not found, with same name but diferent uppercase, ignore case not impleemented");
        }
        
        f = new File(".\\data\\MovieTimeUsers.bin");
        f.delete();
    }
    
    /**
     * Test of authenticateUser method, of class AuthenticationManager, with matching username, but diferents Uppercases.
     */
    @Test
    public void testLoginWithSameUsernameAndUppercase() throws Exception {

        File f = new File(".\\data\\MovieTimeUsers.bin");
        f.delete();
        
        User u1 = new User("username1","user1","user1");
        User u2 = new User("username2","user2","user2");
        
        StorageManager.addNewUser(u1);
        StorageManager.addNewUser(u2);
        
        String username = "username1";
        
        try{
            AuthenticationManager.authenticateUser(username);
            
        }catch(UserDoesNotExistException e){
            fail("User not found, with username, authentication poorly implemented, cannot find user by username");
        }
        
        f = new File(".\\data\\MovieTimeUsers.bin");
        f.delete();
    }

    /**
     * Test of createUser method, of class ObservableApp.
     */
    @Test
    public void testCreateUser() throws Exception {
    }

    @Test
    public void testCreateUserEmptyUsername() throws Exception {
        try{
            AuthenticationManager.createUser("", "ddd", "dddd");
            fail("Username cannot be an empty field.");
        }catch(ValidationException e){
            assertEquals("The username does not contain between "
                    + USERNAME_MIN_SIZE + " and " + ANY_NAME_MAX_SIZE 
                    + " characters. Please, try again!",e.getMessage()
                    , "The username does not contain between " 
                            + USERNAME_MIN_SIZE + " and " + ANY_NAME_MAX_SIZE 
                            + " characters. Please, try again!");
        }catch(Exception e){
            fail("Exception for empty username field not validated.");
        }
    }

            /**
     * Test of createUser method, of class AuthenticationManager, with null entry for username
     */
    @Test
    public void testCreateUserShortUsername() throws Exception {
        try{
            AuthenticationManager.createUser("a", "dddd", "dddd");
            fail("Username cannot have less than 4 characters.");
        }catch(ValidationException e){
            assertEquals("The username does not contain between "
                    + USERNAME_MIN_SIZE + " and " + ANY_NAME_MAX_SIZE 
                    + " characters. Please, try again!",e.getMessage()
                    , "The username does not contain between " 
                            + USERNAME_MIN_SIZE + " and " + ANY_NAME_MAX_SIZE 
                            + " characters. Please, try again!");
        }catch(Exception e){
            fail("Exception for short username field not validated.");
        }
    }

            /**
     * Test of createUser method, of class AuthenticationManager, with null entry for username
     */
    @Test
    public void testCreateUserLongUsername() throws Exception {
        try{
            AuthenticationManager.createUser("aaaaaaaaaaaaaaaaa", "dddd", "dddd");
            fail("Username cannot have more than 15 characters.");
        }catch(ValidationException e){
            assertEquals("The username does not contain between "
                    + USERNAME_MIN_SIZE + " and " + ANY_NAME_MAX_SIZE 
                    + " characters. Please, try again!",e.getMessage()
                    , "The username does not contain between " 
                            + USERNAME_MIN_SIZE + " and " + ANY_NAME_MAX_SIZE 
                            + " characters. Please, try again!");
        }catch(Exception e){
            fail("Exception for long username field not validated.");
        }
    }
    
    /**
     * Test of createUser method, of class AuthenticationManager, with null entry for username
     */
    @Test
    public void testCreateUserUsernameWithInvalidCharacterInFirstLetter() throws Exception {
        try{
            AuthenticationManager.createUser("&ser", "dddd", "dddd");
            fail("Username cannot have special characters.");
        }catch(ValidationException e){
            assertEquals("The username is not valid. Starts with a number or contains a "
                    + "special character. Please, try again!",e.getMessage()
                    , "The username is not valid. Starts with a number or contains a "
                    + "special character. Please, try again!");
        }catch(Exception e){
            fail("Exception for not valid username field not validated.");
        }
    
    }
    
    /**
     * Test of createUser method, of class AuthenticationManager, with null entry for username
     */
    @Test
    public void testCreateUserUsernameWithInvalidCharacterInMiddleLetter() throws Exception {
        try{
            AuthenticationManager.createUser("u&er", "dddd", "dddd");
            fail("Username cannot have special characters.");
        }catch(ValidationException e){
            assertEquals("The username is not valid. Starts with a number or contains a "
                    + "special character. Please, try again!",e.getMessage()
                    , "The username is not valid. Starts with a number or contains a "
                    + "special character. Please, try again!");
        }catch(Exception e){
            fail("Exception for not valid username field not validated.");
        }
    }
    
    /**
     * Test of createUser method, of class AuthenticationManager, with null entry for username
     */
    @Test
    public void testCreateUserUsernameWithNumberInFirstLetter() throws Exception {
        try{
            AuthenticationManager.createUser("7ser", "dddd", "dddd");
            fail("Username cannot have special characters.");
        }catch(ValidationException e){
            assertEquals("The username is not valid. Starts with a number or contains a "
                    + "special character. Please, try again!",e.getMessage()
                    , "The username is not valid. Starts with a number or contains a "
                    + "special character. Please, try again!");
        }catch(Exception e){
            fail("Exception for not valid username field not validated.");
        }
    }
    
    /**
     * Test of createUser method, of class AuthenticationManager, with null entry for username
     */
    @Test
    public void testCreateUserEmptyFirstName() throws Exception {
        try{
            AuthenticationManager.createUser("dddd", "", "dddd");
            fail("Name cannot be an empty field.");
        }catch(ValidationException e){
            assertEquals("The first name does not contain between "
                    + NAME_MIN_SIZE + " and " + ANY_NAME_MAX_SIZE 
                    + " characters. Please, try again!",e.getMessage()
                    , "The first name does not contain between " 
                            + NAME_MIN_SIZE + " and " + ANY_NAME_MAX_SIZE 
                            + " characters. Please, try again!");
        }catch(Exception e){
            fail("Exception for empty fisrt name field not validated.");
        }
    }
    
                /**
     * Test of createUser method, of class AuthenticationManager, with null entry for username
     */
    @Test
    public void testCreateUserShortFirstName() throws Exception {
        try{
            AuthenticationManager.createUser("dddd", "a", "dddd");
            fail("Name cannot have less than 4 characters.");
        }catch(ValidationException e){
            assertEquals("The first name does not contain between "
                    + NAME_MIN_SIZE + " and " + ANY_NAME_MAX_SIZE 
                    + " characters. Please, try again!"
                    , "The first name does not contain between " 
                            + NAME_MIN_SIZE + " and " + ANY_NAME_MAX_SIZE 
                            + " characters. Please, try again!");
        }catch(Exception e){
            fail("Exception for short name field not validated.");
        }
    }

            /**
     * Test of createUser method, of class AuthenticationManager, with null entry for username
     */
    @Test
    public void testCreateUserLongFirstName() throws Exception {
        try{
            AuthenticationManager.createUser("dddd", "aaaaaaaaaaaaaaaaa", "dddd");
            fail("Name cannot have more than 15 characters.");
        }catch(ValidationException e){
            assertEquals("The first name does not contain between "
                    + NAME_MIN_SIZE + " and " + ANY_NAME_MAX_SIZE 
                    + " characters. Please, try again!",e.getMessage()
                    , "The first name does not contain between " 
                            + NAME_MIN_SIZE + " and " + ANY_NAME_MAX_SIZE 
                            + " characters. Please, try again!");
        }catch(Exception e){
            fail("Exception for long name field not validated.");
        }
    }
    
    /**
     * Test of createUser method, of class AuthenticationManager, with null entry for username
     */
    @Test
    public void testCreateUserFirstNameWithInvalidCharacterInFirstLetter() 
            throws Exception {
        try{
            AuthenticationManager.createUser("dddd", "&ddd", "dddd");
            fail("Name cannot have special characters.");
        }catch(ValidationException e){
            assertEquals("The first name is not valid. Starts with a number or contains a "
                    + "special character. Please, try again!",e.getMessage()
                    , "The first name is not valid. Starts with a number or contains a "
                    + "special character. Please, try again!");
        }catch(Exception e){
            fail("Exception for not valid name field not validated.");
        }
    
    }
    
    /**
     * Test of createUser method, of class AuthenticationManager, with null entry for username
     */
    @Test
    public void testCreateUserFisrtNameWithInvalidCharacterInMiddleLetter() 
            throws Exception {
        try{
            AuthenticationManager.createUser("dddd", "d&dd", "dddd");
            fail("First name cannot have special characters.");
        }catch(ValidationException e){
            assertEquals("The first name is not valid. Starts with a number or contains a "
                    + "special character. Please, try again!",e.getMessage()
                    , "The first name is not valid. Starts with a number or contains a "
                    + "special character. Please, try again!");
        }catch(Exception e){
            fail("Exception for not valid name field not validated.");
        }
    }
    
    /**
     * Test of createUser method, of class AuthenticationManager, with null entry for username
     */
    @Test
    public void testCreateUserFirstNameWithNumberInFirstLetter() throws Exception {
        try{
            AuthenticationManager.createUser("dddd", "7ser", "dddd");
            fail("Name cannot have special characters.");
        }catch(ValidationException e){
            assertEquals("The first name is not valid. Starts with a number or contains a "
                    + "special character. Please, try again!",e.getMessage()
                    , "The first name is not valid. Starts with a number or contains a "
                    + "special character. Please, try again!");
        }catch(Exception e){
            fail("Exception for not valid name field not validated.");
        }
    }
    
    /**
     * Test of createUser method, of class AuthenticationManager, with null entry for username
     */
    @Test
    public void testCreateUserEmptyLastName() throws Exception {
        try{
            AuthenticationManager.createUser("dddd", "dddd", "");
            fail("Last name cannot be an empty field.");
        }catch(ValidationException e){
            assertEquals("The last name does not contain between "
                    + NAME_MIN_SIZE + " and " + ANY_NAME_MAX_SIZE 
                    + " characters. Please, try again!",e.getMessage()
                    , "The last name does not contain between " 
                            + NAME_MIN_SIZE + " and " + ANY_NAME_MAX_SIZE 
                            + " characters. Please, try again!");
        }catch(Exception e){
            fail("Exception for empty last name field not validated.");
        }
    }
    
                /**
     * Test of createUser method, of class AuthenticationManager, with null entry for username
     */
    @Test
    public void testCreateUserShortLastName() throws Exception {
        try{
            AuthenticationManager.createUser("dddd", "dddd", "d");
            fail("Last name cannot have less than 4 characters.");
        }catch(ValidationException e){
            assertEquals("The last name does not contain between "
                    + NAME_MIN_SIZE + " and " + ANY_NAME_MAX_SIZE 
                    + " characters. Please, try again!"
                    , "The last name does not contain between " 
                            + NAME_MIN_SIZE + " and " + ANY_NAME_MAX_SIZE 
                            + " characters. Please, try again!");
        }catch(Exception e){
            fail("Exception for short last name field not validated.");
        }
    }

            /**
     * Test of createUser method, of class AuthenticationManager, with null entry for username
     */
    @Test
    public void testCreateUserLonglastName() throws Exception {
        try{
            AuthenticationManager.createUser("dddd", "dddd", "aaaaaaaaaaaaaaaaa");
            fail("Last name cannot have more than 15 characters.");
        }catch(ValidationException e){
            assertEquals("The last name does not contain between "
                    + NAME_MIN_SIZE + " and " + ANY_NAME_MAX_SIZE 
                    + " characters. Please, try again!",e.getMessage()
                    , "The last name does not contain between " 
                            + NAME_MIN_SIZE + " and " + ANY_NAME_MAX_SIZE 
                            + " characters. Please, try again!");
        }catch(Exception e){
            fail("Exception for long last name field not validated.");
        }
    }
    
    /**
     * Test of createUser method, of class AuthenticationManager, with null entry for username
     */
    @Test
    public void testCreateUserLastNameWithInvalidCharacterInFirstLetter() 
            throws Exception {
        try{
            AuthenticationManager.createUser("dddd", "dddd", "&ddd");
            fail("Last ame cannot have special characters.");
        }catch(ValidationException e){
            assertEquals("The last name is not valid. Starts with a number or contains a "
                    + "special character. Please, try again!",e.getMessage()
                    , "The last name is not valid. Starts with a number or contains a "
                    + "special character. Please, try again!");
        }catch(Exception e){
            fail("Exception for not valid last name field not validated.");
        }
    
    }
    
    /**
     * Test of createUser method, of class AuthenticationManager, with null entry for username
     */
    @Test
    public void testCreateUserLastNameWithInvalidCharacterInMiddleLetter() 
            throws Exception {
        try{
            AuthenticationManager.createUser("dddd", "dddd", "d&dd");
            fail("Last name cannot have special characters.");
        }catch(ValidationException e){
            assertEquals("The last name is not valid. Starts with a number or contains a "
                    + "special character. Please, try again!",e.getMessage()
                    , "The last name is not valid. Starts with a number or contains a "
                    + "special character. Please, try again!");
        }catch(Exception e){
            fail("Exception for not valid last name field not validated.");
        }
    }
    
    /**
     * Test of createUser method, of class AuthenticationManager, with null entry for username
     */
    @Test
    public void testCreateUserLastNameWithNumberInFirstLetter() throws Exception {
        try{
            AuthenticationManager.createUser("dddd", "dddd", "7ser");
            fail("Last name cannot have special characters.");
        }catch(ValidationException e){
            assertEquals("The last name is not valid. Starts with a number or contains a "
                    + "special character. Please, try again!",e.getMessage()
                    , "The last name is not valid. Starts with a number or contains a "
                    + "special character. Please, try again!");
        }catch(Exception e){
            fail("Exception for not valid last name field not validated.");
        }
    }     

    /**
     * Test of createUser method, of class AuthenticationManager, with null entry for username
     */
    @Test
    public void testCreateUserExistingUser() throws Exception {
        File f = new File(".\\data\\MovieTimeUsers.bin");
        f.delete();
        
        User u1 = new User("username1","user1","user1");
        User u2 = new User("username2","user2","user2");
        
        StorageManager.addNewUser(u1);
        StorageManager.addNewUser(u2);
        
        try{
            AuthenticationManager.createUser("username1", "dddd", "dddd");
            fail("Cannot create previously existing user(with same username)");
            
        }catch(UserAlreadyExistsException e){
            assertEquals("User already exists!",e.getMessage()
                    , "User already exists!");
        }catch(Exception e){
            fail("Exception for already existing user not validated.");
        }
        
        f = new File(".\\data\\MovieTimeUsers.bin");
        f.delete();
    }
    
    /**
     * Test of createUser method, of class AuthenticationManager, with null entry for username
     */
    @Test
    public void testCreateUserCorrectUser() throws Exception {
        File f = new File(".\\data\\MovieTimeUsers.bin");
        f.delete();
        
        User u1 = new User("username1","user1","user1");
        User u2 = new User("username2","user2","user2");
        
        StorageManager.addNewUser(u1);
        StorageManager.addNewUser(u2);
        
        try{
            AuthenticationManager.createUser("dddd", "dddd", "dddd");
        }catch(Exception e){
            fail("User should be created.");
        }
        
        f = new File(".\\data\\MovieTimeUsers.bin");
        f.delete();
    }
    
    //>>>>>>User tests yet to be done
    /**
     * Test of addPreferedMovie method, of class ObservableApp.
     */
    @Test
    public void testAddPreferedMovie() {
    }

    /**
     * Test of removeFavouriteMovie method, of class ObservableApp.
     */
    @Test
    public void testRemoveFavouriteMovie() {
    }

    /**
     * Test of isMovieBeingFollowed method, of class ObservableApp.
     */
    @Test
    public void testIsMovieBeingFollowed() {
    }

    
    //>>>>>>User tests yet to be done//
    //>>>>>>Notification preferences tests yet to be done
    /**
     * Test of setDaysToAlert method, of class ObservableApp.
     */
    @Test
    public void testSetDaysToAlert() {
    }

    /**
     * Test of setIncludeDirector method, of class ObservableApp.
     */
    @Test
    public void testSetIncludeDirector() {
    }

    /**
     * Test of setIncludeMainActors method, of class ObservableApp.
     */
    @Test
    public void testSetIncludeMainActors() {
    }

    /**
     * Test of setIncludeGenre method, of class ObservableApp.
     */
    @Test
    public void testSetIncludeGenre() {
    }

    
    //>>>>>>Notification preferences tests yet to be done//
    
    
    /**
     * Test of getFollowedMovies method, of class DatabaseManager.
     * The method should return null when the HashSet passed as argument is null
     */
    @Test
    public void testGetFollowedMoviesWithNullHashSet() {
        System.out.println("getFollowedMoviesWithNullHashSet");
        HashSet<Integer> followedIds = null;
        ArrayList<Movie> expResult = null;
        ArrayList<Movie> result = DatabaseManager.getFollowedMovies(followedIds);
        assertEquals("The method should return null when the HashSet passed as argument is null", expResult, result);
    }

    /**
     * Test of getUpcomingMoviesByGenre method, of class DatabaseManager.
     * With a null HashSet as past parameter.
     */
    @Test
    public void testGetUpcomingMoviesByGenre() {
        System.out.println("getUpcomingMoviesByGenre");
        HashSet<Integer> genres = null;
        ArrayList<ArrayList<Movie>> expResult = null;
        ArrayList<ArrayList<Movie>> result = DatabaseManager.getUpcomingMoviesByGenre(genres);
        assertEquals("A null HashSet does not return the expected result", expResult, result);
    }
    
    /**
     * Test of getUpcomingMoviesByGenre method, of class DatabaseManager.
     * With an empty HashSet as past parameter.
     */
    @Test
    public void testGetUpcomingMoviesByGenreWithEmptyHashSet() {
        System.out.println("getUpcomingMoviesByGenre");
        HashSet<Integer> genres = new HashSet<>();
        ArrayList<ArrayList<Movie>> expResult = null;
        ArrayList<ArrayList<Movie>> result = DatabaseManager.getUpcomingMoviesByGenre(genres);
        assertEquals("An empty HashSet does not return the expected result", expResult, result);
    }
    /**
     * Test of getUpcomingMoviesByKeywordAndGenre method, of class DatabaseManager.
     * Calling the method with an HashSet containing Integers that do not represent
     * any genre should return null do distinct it from the case in which there are
     * no actual movies for a specified genre
     */
    
    @Test
    public void testGetUpcomingMoviesByGenreWithIncorrectGenres(){
        System.out.println("getUpcomingMoviesByGenreWithIncorrectGenres");
        HashSet<Integer> genres = new HashSet<>();
        genres.add(2);
        ArrayList<ArrayList<Movie>> result = DatabaseManager.getUpcomingMoviesByGenre(genres);
        assertNull("When the argument is an HashSet containing Integers that do not "
                + "represent any genre the method should return null", result);
    }
    
    /**
     * Test of getUpcomingMoviesByKeywordAndGenre method, of class DatabaseManager.
     * Searching upcoming movies with an "" keyword and a null genre the result should be
     * the same as the getUpComingMovies result
     */
    @Test
    public void testGetUpcomingMoviesByEmptyKeywordAndNotSelectedGenre() {
        System.out.println("getUpcomingMoviesByKeywordAndNotSelectedGenre");
        String keyword = "";
        Integer genre = null;
        ArrayList<ArrayList<Movie>> expResult = DatabaseManager.getUpcomingMovies();
        ArrayList<ArrayList<Movie>> result = DatabaseManager.getUpcomingMoviesByKeywordAndGenre(keyword, genre);
        if(!arrayListOfArrayListEquals(expResult, result))
            fail("When the arguments are an empty keyword and a null genre (when it is not selected) the method"
                    + "should return the same as the getUpcomingMovies method");
    }
    
    /**
     * Test of getUpcomingMoviesByKeywordAndGenre method, of class DatabaseManager.
     * Searching upcoming movies with an empty keyword the method should return all the movies
     * of that genre
     */
    @Test
    public void testGetUpcomingMoviesByEmptyKeywordAndGenre() {
        System.out.println("getUpcomingMoviesByKeywordAndGenre");
        String keyword = "";
        Integer genre = 18;
        HashSet<Integer> genres = new HashSet<>();
        genres.add(genre);
        
        ArrayList<ArrayList<Movie>> expResult = DatabaseManager.getUpcomingMoviesByGenre(genres);
        ArrayList<ArrayList<Movie>> result = DatabaseManager.getUpcomingMoviesByKeywordAndGenre(keyword, genre);
        if(!arrayListOfArrayListEquals(expResult, result))
            fail("When the arguments are an empty keyword and a selected genre the method should"
                    + "return all the upcoming movies of that genre");
    }

    /**
     * Test of getUpcomingMoviesByKeyword method, of class DatabaseManager.
     * Searching upcoming movies with an empty keyword the result should be
     * the same as the getUpcomingMovies method.
     */
    @Test
    public void testGetUpcomingMoviesByEmptyKeyword() {
        System.out.println("getUpcomingMoviesByKeyword");
        String keyword = "";
        ArrayList<ArrayList<Movie>> expResult = DatabaseManager.getUpcomingMovies();
        ArrayList<ArrayList<Movie>> result = DatabaseManager.getUpcomingMoviesByKeyword(keyword);
        if(!arrayListOfArrayListEquals(expResult, result))
            fail("When the arguments are an empty keyword and a selected genre the method should"
                    + "return the same as the getUpcomingMovies method");
    }

    /**
     * Test of getMovieByID method, of class DatabaseManager.
     */
    @Test
    public void testGetMovieByID() {
        System.out.println("getMovieByID");
        int id = 0;
        Movie expResult = null;
        Movie result = DatabaseManager.getMovieByID(id);
        assertNull("When the argument of the method is an unexistent id, the returned movie"
                + " should be null", result);
    }
    
    
    /*
    * This method compares the ArrayList<ArrayList<Movie>> objects.
    * Returns true if they are equal, false if they are not equal.
    */
    private boolean arrayListOfArrayListEquals(ArrayList<ArrayList<Movie>> aListAl1, ArrayList<ArrayList<Movie>> aListAl2){
        if(aListAl1.size() != aListAl2.size())
            return false;
        ArrayList<Movie> aL1 = aListAl1.get(0);
        ArrayList<Movie> aL2 = aListAl2.get(0);
        if(aL1.size() != aL2.size())
            return false;
        for(int i = 0; i < aL1.size(); i++){
            if(!(aL1.get(i).getId() == aL2.get(i).getId()))
                return false;
        }
        return true;
    }
}
