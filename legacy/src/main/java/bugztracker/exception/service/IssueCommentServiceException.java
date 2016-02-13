package bugztracker.exception.service;

/**
 * For handling in controller
 * <p/>
 * Created by oleg
 * Date: 09.11.15
 * Time: 4:02
 */
public class IssueCommentServiceException extends RuntimeException {
    public IssueCommentServiceException() {
    }

    public IssueCommentServiceException(String message) {
        super(message);
    }

    public IssueCommentServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public IssueCommentServiceException(Throwable cause) {
        super(cause);
    }
}
