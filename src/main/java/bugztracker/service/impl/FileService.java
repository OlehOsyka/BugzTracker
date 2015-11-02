package bugztracker.service.impl;

import bugztracker.entity.Issue;
import bugztracker.entity.IssueAttachment;
import bugztracker.service.IFileService;
import bugztracker.service.IIssueAttachmentService;
import bugztracker.util.UriBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Created by oleg
 * Date: 01.11.15
 * Time: 17:44
 */
@Service
public class FileService implements IFileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Autowired
    private UriBuilder uriBuilder;
    @Autowired
    private IIssueAttachmentService attachmentService;

    @Override
    public void save(List<MultipartFile> files, int issueId) {
        for (MultipartFile multipart : files) {
            // save
            try {
                String newName = uriBuilder.buildPathForAttachment(issueId, multipart.getOriginalFilename());
                File file = new File(newName);

                if (!file.exists() && !file.mkdirs() && !file.createNewFile()) {
                    logger.warn(format("Can't create file %s.", newName));
                    continue;
                }
                multipart.transferTo(file);

                // update DB
                Issue issue = new Issue();
                issue.setId(issueId);
                IssueAttachment attachment = new IssueAttachment();
                attachment.setId((int) UUID.randomUUID().getMostSignificantBits());
                attachment.setAttachmentPath(newName);
                attachment.setIssueByIssueId(issue);

                attachmentService.addAttachment(attachment);
            } catch (IOException e) {
                logger.info(format("Can't save file %s for issue %s!", multipart.getOriginalFilename(), issueId), e);
                // TODO Should throw?
            }
        }
    }

    @Override
    public List<String> listAttachments(int issueId) {
        return extractNames(attachmentService.getAttachments(issueId));
    }

    @Override
    public List<File> getAttachments(int issueId) {
        List<File> files = new ArrayList<>();
        List<String> paths = extractNames(attachmentService.getAttachments(issueId));
        for (String path : paths) {
            files.add(new File(path));
        }
        return files;
    }

    @Override
    public File get(int issueId, String fileName) {
        IssueAttachment attachment = attachmentService.getAttachment(issueId, fileName);
        return new File(attachment.getAttachmentPath());
    }

    private List<String> extractNames(List<IssueAttachment> attachments) {
        List<String> paths = new ArrayList<>();
        for (IssueAttachment attachment : attachments) {
            String path = attachment.getAttachmentPath();

            if (isBlank(path)) {
                logger.warn(format("Empty path for attachment %s", attachment.getId()));
                continue;
            }

            paths.add(path);
        }
        return paths;
    }
}
