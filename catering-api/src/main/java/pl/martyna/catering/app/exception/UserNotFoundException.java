package pl.martyna.catering.app.exception;


public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(){
        super("User not found.");
    }
}
