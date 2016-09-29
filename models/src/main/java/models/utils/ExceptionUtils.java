package models.utils;

/**
 * Created by abhishek on 29/9/16.
 */
public class ExceptionUtils {

    public static Exception getException(String exceptionName) {
        return new Exception(exceptionName);
    }
}
