package az.vali.computer_store.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        new RuntimeException(message);
    }
}
