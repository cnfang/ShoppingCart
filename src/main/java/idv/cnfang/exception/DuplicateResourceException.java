package idv.cnfang.exception;


public class DuplicateResourceException extends RuntimeException {

    private static final long serialVersionUID = 5108936492106585273L;
    
    private static String prefix = "Create same resource - ";
    
    public DuplicateResourceException(String message, Throwable cause) {
        super(prefix + message, cause);
    }

    public DuplicateResourceException(String message) {
        super(prefix + message);
    }
    
}
