package pl.martyna.catering.app.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(){
        super("Resource not found.");
    }
}
