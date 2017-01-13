package linear.algebra.util;

public class ExceptionUtils {
    public static Exception getException(String exceptionName) {
        return new Exception(exceptionName);
    }
}
