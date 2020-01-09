package util.constants.exception;

public class LearningException extends RuntimeException {

    public LearningException(String message) {
        super(message);
    }

    public LearningException(Throwable cause) {
        super(cause.getMessage(), cause);
    }
}
