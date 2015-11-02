package bugztracker.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.io.File;

/**
 * Created by oleg
 * Date: 01.11.15
 * Time: 17:46
 */
@Component
public final class UriBuilder {

    private static final String SLASH = File.separator;
    private static final String FILES = "files";

    @Autowired
    private ServletContext servletContext;

    @Value("${repository.path}")
    private String rootPath;

    private UriBuilder() {
    }

    public String buildPathForIssueFolder(int issueId) {
        return build().
                append(SLASH).
                append(issueId).
                append(SLASH).
                toString();
    }

    public String buildPathForAttachment(int issueId, String filename) {
        return build().
                append(SLASH).
                append(issueId).
                append(SLASH).
                append(filename).
                toString();
    }

    private StringBuilder build() {
        return new StringBuilder(servletContext.getRealPath(rootPath)).
                append(SLASH).
                append(FILES);
    }

}
