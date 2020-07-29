package idv.cnfang.exception;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -6138655266107998318L;
    
    private static String prefix = "Resource Not Found - ";

    public NotFoundException(String message, Throwable cause) {
        super(prefix + message, cause);
    }

    public NotFoundException(String message) {
        super(prefix + message);
    }
    
}
