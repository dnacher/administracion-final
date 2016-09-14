package exceptions;

public class UnidadException extends Exception{ 

    public UnidadException() {
    }

    public UnidadException(String message) {
        super(message);
    }

    public UnidadException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnidadException(Throwable cause) {
        super(cause);
    }

    public UnidadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
   
}
