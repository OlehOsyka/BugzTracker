package bugztracker.exception;

/**
 * Created by Y. Vovk on 02.10.15.
 */
public class EncodingException extends RuntimeException {

    public EncodingException() {
        super();
    }

    public EncodingException(String message) {
        super(message);
    }

    public EncodingException(String message, Throwable cause) {
        super(message, cause);
    }

}
