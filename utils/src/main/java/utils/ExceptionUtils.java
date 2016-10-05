package utils;

/**
 * Created by abhishek on 29/9/16.
 */
public class ExceptionUtils {

    /**
     * Gets exception.
     *
     * @param exceptionName the exception name
     * @return the exception
     */
    public static Exception getException(String exceptionName) {
        return new Exception(exceptionName);
    }
}
