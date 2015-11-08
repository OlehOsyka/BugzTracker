package bugztracker.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * Created by oleg
 * Date: 01.11.15
 * Time: 17:41
 */
public interface IFileService {

    void save(List<MultipartFile> file, int issueId);

    /**
     * Retrieve file names
     */
    List<String> listAttachments(int issueId);

    List<File> getAttachments(int issueId);

    File get(int issueId, String fileName);

    void remove(int issueId, String fileName);
}
