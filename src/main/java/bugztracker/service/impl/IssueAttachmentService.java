package bugztracker.service.impl;

import bugztracker.entity.Issue;
import bugztracker.entity.IssueAttachment;
import bugztracker.repository.IIssueAttachmentRepository;
import bugztracker.service.IIssueAttachmentService;
import bugztracker.service.IIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by oleg
 * Date: 02.11.15
 * Time: 10:44
 */
@Transactional
@Service
public class IssueAttachmentService implements IIssueAttachmentService {

    @Autowired
    private IIssueService issueService;
    @Autowired
    private IIssueAttachmentRepository attachmentRepository;

    @Override
    public List<IssueAttachment> getAttachments(int issueId) {
        return attachmentRepository.getAttachments(issueId);
    }

    @Override
    public IssueAttachment getAttachment(int issueId, String fileName) {
        return attachmentRepository.getAttachment(issueId, fileName);
    }

    @Override
    public IssueAttachment get(int id) {
        return attachmentRepository.get(id);
    }

    @Override
    public List<IssueAttachment> getAll() {
        return attachmentRepository.getAll();
    }

    @Override
    public void add(IssueAttachment entity) {
        attachmentRepository.add(entity);
    }

    @Override
    public void delete(IssueAttachment entity) {
        attachmentRepository.delete(entity);
    }

    @Override
    public void update(IssueAttachment entity) {
        attachmentRepository.update(entity);
    }

    @Override
    public void addAttachment(IssueAttachment issueAttachment){
        Issue issue = issueService.get(issueAttachment.getIssueByIssueId().getId());
        IssueAttachment attachment = new IssueAttachment();
        attachment.setId(issueAttachment.getId());
        attachment.setAttachmentPath(issueAttachment.getAttachmentPath());
        attachment.setIssueByIssueId(issue);

        add(attachment);

    }
}
