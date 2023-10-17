package cz.cvut.fel.omo.cv3.exceptions;

public class NotEnoughPermissionException extends Exception {

    public NotEnoughPermissionException(String message) {
        super(message);
    }

    public NotEnoughPermissionException(String message, Throwable cause) {
        super(message, cause);
    }
}
