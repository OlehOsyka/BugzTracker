package bugztracker.controller;

import bugztracker.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by oleg
 * Date: 31.10.15
 * Time: 13:05
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    private IFileService fileService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/save/{issueId}", method = POST)
    public void save(@RequestParam(value = "files[]") List<MultipartFile> files,
                     @PathVariable int issueId) {

        fileService.save(files, issueId);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/get/{issueId}", method = GET)
    public List<String> list(@PathVariable int issueId) {
        return fileService.listAttachments(issueId);
    }


    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    private String uploadErrorHandler(MultipartException e) {
        return e.getMessage();
    }
}
