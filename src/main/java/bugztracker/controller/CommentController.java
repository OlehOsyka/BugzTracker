package bugztracker.controller;

import bugztracker.entity.IssueComment;
import bugztracker.entity.User;
import bugztracker.service.IIssueCommentService;
import bugztracker.validator.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void add(@RequestBody IssueComment comment,
                    WebRequest request) {
        issueCommentValidator.validate(comment);

        User user = (User) request.getAttribute("user", SCOPE_SESSION);
        issueCommentService.addComment(comment, user);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody IssueComment comment) {
        issueCommentValidator.validate(comment);

        issueCommentService.updateComment(comment);
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
    private ResponseEntity<Object> commentErrorHandler(Throwable e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", e.getMessage());

        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }

}
