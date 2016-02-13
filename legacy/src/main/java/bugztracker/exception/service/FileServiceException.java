package bugztracker.exception.service;

/**
 * Yuliia Vovk
 * 19.11.15
 */
public class FileServiceException extends RuntimeException {

    public FileServiceException() {
    }

    public FileServiceException(String message) {
        super(message);
    }

    public FileServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileServiceException(Throwable cause) {
        super(cause);
    }
}
