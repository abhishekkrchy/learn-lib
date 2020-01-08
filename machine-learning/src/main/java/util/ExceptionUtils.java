package util;

public class ExceptionUtils {

    /**
     * Gets exception.
     *
     * @param exceptionName the exception name
     * @return the exception
     * TODO :: can be implemented in a better way
     */
    public static RuntimeException getException(String exceptionName) {
        return new RuntimeException(exceptionName);
    }
}