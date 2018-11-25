package movietime.authentication;

public class UserDoesNotExistException extends Exception {
    public UserDoesNotExistException(String msg){
        super(msg);
    }
}
