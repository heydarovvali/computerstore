package az.vali.computer_store.exception;

public class SellerNotFoundException extends RuntimeException {
    public SellerNotFoundException(String message) {
        new RuntimeException(message);
    }
}
