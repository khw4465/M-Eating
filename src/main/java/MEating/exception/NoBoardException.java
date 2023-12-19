package MEating.exception;

public class NoBoardException extends RuntimeException{
    public NoBoardException() {
        super();
    }

    public NoBoardException(String message) {
        super(message);
    }

    public NoBoardException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoBoardException(Throwable cause) {
        super(cause);
    }
}
