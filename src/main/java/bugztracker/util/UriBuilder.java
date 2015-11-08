package bugztracker.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
                append(filename.replaceAll(" ", "_")).
                toString();
    }

    private StringBuilder build() {
        return new StringBuilder(System.getProperty("user.home")).
                append(rootPath).
                append(SLASH).
                append(FILES);
    }

}
