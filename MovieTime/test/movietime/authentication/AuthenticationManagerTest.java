/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movietime.authentication;

import java.io.File;
import movietime.accounts.User;
import movietime.storage.StorageManager;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Olympus
 */
public class AuthenticationManagerTest {
    private static final int USERNAME_MIN_SIZE = 4;
    private static final int NAME_MIN_SIZE = 3;
    private static final int ANY_NAME_MAX_SIZE = 15;
    
    public AuthenticationManagerTest() {
    }
    
//***testAuthenticateUser***//
    /**
     * Test of authenticateUser method, of class AuthenticationManager, in case the username doesn't match.
     */
    @Test
    public void testAuthenticateUserNoUserRequested() throws Exception {

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
    public void testAuthenticateUserWithSameUsernameButDiferentUppercase() throws Exception {

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
    public void testAuthenticateUserWithSameUsernameAndUppercase() throws Exception {

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
    
    
    //***testCreateUser***//
 
    /**
     * Test of createUser method, of class AuthenticationManager, with null entry for username
     */
//    @Test
//    public void testCreateUserNullUsername() throws Exception {
//        try{
//            AuthenticationManager.createUser(null, "dddd", "dddd");
//            fail("Username cannot be null.");
//        }catch(ValidationException e){
//            //this exception is not created.
//            assertEquals("The username is not valid. Is null.",e.getMessage(), "The username is not valid. Is null.");
//        }catch(Exception e){
//            fail("Exception for null username field not validated.");
//        }
//    }
    
        /**
     * Test of createUser method, of class AuthenticationManager, with null entry for username
     */
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
            assertEquals("The username is not valid. Starts with a number or a "
                    + "special character. Please, try again!",e.getMessage()
                    , "The username is not valid. Starts with a number or a "
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
            assertEquals("The username is not valid. Starts with a number or a "
                    + "special character. Please, try again!",e.getMessage()
                    , "The username is not valid. Starts with a number or a "
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
            assertEquals("The username is not valid. Starts with a number or a "
                    + "special character. Please, try again!",e.getMessage()
                    , "The username is not valid. Starts with a number or a "
                    + "special character. Please, try again!");
        }catch(Exception e){
            fail("Exception for not valid username field not validated.");
        }
    }
    
    /**
     * Test of createUser method, of class AuthenticationManager, with null entry for username
     */
//    @Test
//    public void testCreateUserNullFirstName() throws Exception {
//        try{
//            AuthenticationManager.createUser("dddd", null, "dddd");
//            fail("Name cannot be null.");
//        }catch(ValidationException e){
//            //this exception is not created.
//            assertEquals("The name is not valid. Is null.",e.getMessage(), "The name is not valid. Is null.");
//        }catch(Exception e){
//            fail("Exception for null name field not validated.");
//        }
//    }
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
            assertEquals("The first name is not valid. Starts with a number or a "
                    + "special character. Please, try again!",e.getMessage()
                    , "The first name is not valid. Starts with a number or a "
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
            assertEquals("The first name is not valid. Starts with a number or a "
                    + "special character. Please, try again!",e.getMessage()
                    , "The first name is not valid. Starts with a number or a "
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
            assertEquals("The first name is not valid. Starts with a number or a "
                    + "special character. Please, try again!",e.getMessage()
                    , "The first name is not valid. Starts with a number or a "
                    + "special character. Please, try again!");
        }catch(Exception e){
            fail("Exception for not valid name field not validated.");
        }
    }
    
    /**
     * Test of createUser method, of class AuthenticationManager, with null entry for username
     */
//    @Test
//    public void testCreateUserNullLasttName() throws Exception {
//        try{
//            AuthenticationManager.createUser("dddd", "dddd", null);
//            fail("Last name cannot be null.");
//        }catch(ValidationException e){
//            //this exception is not created.
//            assertEquals("The last name is not valid. Is null.",e.getMessage(), "The last name is not valid. Is null.");
//        }catch(Exception e){
//            fail("Exception for null last name field not validated.");
//        }
//    }
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
            assertEquals("The last name is not valid. Starts with a number or a "
                    + "special character. Please, try again!",e.getMessage()
                    , "The last name is not valid. Starts with a number or a "
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
            assertEquals("The last name is not valid. Starts with a number or a "
                    + "special character. Please, try again!",e.getMessage()
                    , "The last name is not valid. Starts with a number or a "
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
            assertEquals("The last name is not valid. Starts with a number or a "
                    + "special character. Please, try again!",e.getMessage()
                    , "The last name is not valid. Starts with a number or a "
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
}
