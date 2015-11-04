package bugztracker.controller;

import bugztracker.entity.IssueComment;
import bugztracker.entity.User;
import bugztracker.service.IIssueCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

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

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void add(@RequestBody IssueComment comment, WebRequest request) {
       //validator

        User user = (User) request.getAttribute("user", SCOPE_SESSION);
        issueCommentService.addComment(comment, user);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody IssueComment comment) {
        //validator

        issueCommentService.updateComment(comment);
    }

}
