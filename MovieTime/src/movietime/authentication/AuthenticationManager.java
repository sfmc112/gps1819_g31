package movietime.authentication;

import movietime.accounts.User;
import movietime.storage.StorageManager;

public class AuthenticationManager {

    private static final int USERNAME_MIN_SIZE = 4;
    private static final int NAME_MIN_SIZE = 3;
    private static final int ANY_NAME_MAX_SIZE = 15;

    /**
     * Authenticate the user in the application (making the active user in session)
     * @param user
     * @return
     */
    public static User authenticateUser(String user) {
        //TODO
        //Verify username
        //retrieve the user on file
        //return the user if it exists
        return null;
    }

    /**
     * Creates a new User
     * @see User
     * @param username
     * @param firstName
     * @param lastName
     * @return a new User if all the parameters are coherent
     */
    public static User createUser(String username, String firstName, String lastName) {
        //TODO
        //Verify username
        //check on file
        //validate 
        validateParameters(username, firstName, lastName);

        //If the code reaches here, the parameters were valid 
        //return the user
        
        return new User(username, firstName, lastName);
    }
    
    /**
     * Logout the user in session
     * @see StorageManager
     * @param user 
     */
    public static void logoutUser(User user){
        //TODO
        //Save the user's information on file
    }

    /**
     * Validates length and first letter of the username, first name and last name
     * @param username
     * @param firstName
     * @param lastName 
     */
    private static void validateParameters(String username, String firstName, String lastName) {
        if (!sizeIsValid(username, USERNAME_MIN_SIZE, ANY_NAME_MAX_SIZE)) {
            throw new RuntimeException("The username does not contain between "
                    + USERNAME_MIN_SIZE + " and " + ANY_NAME_MAX_SIZE + " characters. Please, try again!");
        } else if (!spellingIsValid(username)) {
            throw new RuntimeException("The username is not valid. Starts with a number or a special character. Please, try again!");
        }
        if (!sizeIsValid(firstName, NAME_MIN_SIZE, ANY_NAME_MAX_SIZE)) {
            throw new RuntimeException("The first name does not contain between "
                    + NAME_MIN_SIZE + " and " + ANY_NAME_MAX_SIZE + " characters. Please, try again!");
        } else if (!spellingIsValid(firstName)) {
            throw new RuntimeException("The first name is not valid. Starts with a number or a special character. Please, try again!");
        }
        if (!sizeIsValid(lastName, NAME_MIN_SIZE, ANY_NAME_MAX_SIZE)) {
            throw new RuntimeException("The last name does not contain between "
                    + NAME_MIN_SIZE + " and " + ANY_NAME_MAX_SIZE + " characters. Please, try again!");
        } else if (!spellingIsValid(lastName)) {
            throw new RuntimeException("The last name is not valid. Starts with a number or a special character. Please, try again!");
        }
    }
    
    /**
     * Checks if the specified name's length is within the bounds
     * @param name
     * @param minSize lower bound
     * @param maxSize upper bound
     * @return <tt>true</tt> if within bounds, false otherwise
     */
    private static boolean sizeIsValid(String name, int minSize, int maxSize){
        return name.length() >= minSize && name.length() <= maxSize;
    }

    /**
     * Checks if the first character of the string is a letter 
     * @param s name in question
     * @return <tt>true</tt> if the first character is a letter, false otherwise
     */
    private static boolean spellingIsValid(String s) {
        return Character.isLetter(s.codePointAt(0));
    }
}
