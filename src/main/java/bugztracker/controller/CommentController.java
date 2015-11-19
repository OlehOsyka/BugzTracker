package bugztracker.controller;

import bugztracker.entity.IssueComment;
import bugztracker.entity.User;
import bugztracker.exception.service.IssueCommentServiceException;
import bugztracker.service.IIssueCommentService;
import bugztracker.validator.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_SESSION;

/**
 * Created by Y. Vovk on 04.11.15.
 */
@Controller
@RequestMapping("/comment")
@SessionAttributes("user")
public class CommentController {

    @Autowired
    private IIssueCommentService issueCommentService;

    @Autowired
    private IValidator<IssueComment> issueCommentValidator;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestParam String comment,
                    @RequestParam int issueId,
                    WebRequest request) {
        //issueCommentValidator.validate();

        User user = (User) request.getAttribute("user", SCOPE_SESSION);
        issueCommentService.addComment(comment, issueId, user);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestParam String comment,
                       @RequestParam int issueId,
                       @RequestParam int commentId) {
        //issueCommentValidator.validate();

        issueCommentService.updateComment(comment, issueId, commentId);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/list/{issueId}", method = RequestMethod.GET)
    public List<IssueComment> listComments(@PathVariable int issueId) {
        return issueCommentService.getAll(issueId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/delete/{issueId}/{commentId}", method = RequestMethod.GET)
    public void delete(@PathVariable int issueId,
                       @PathVariable int commentId) {
        issueCommentService.delete(issueId, commentId);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    private String uploadErrorHandler(IssueCommentServiceException e) {
        return e.getMessage();
    }

}
