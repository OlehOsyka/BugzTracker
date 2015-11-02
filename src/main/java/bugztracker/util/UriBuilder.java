package bugztracker.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

import static java.lang.String.format;

/**
 * Created by oleg
 * Date: 01.11.15
 * Time: 17:46
 */
@Component
public final class UriBuilder {

    private static final Logger logger = LoggerFactory.getLogger(UriBuilder.class);
    private static final String SLASH = File.separator;

    @Autowired
    private ResourcePatternResolver resourceResolver;
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
        try {
            String path = resourceResolver.getResource(rootPath).getURI().getPath();
            return new StringBuilder(path);
        } catch (IOException e) {
            logger.warn(format("Can't open URI for rootPath - %s", rootPath), e);
        }
        return new StringBuilder(System.getProperty("user.dir")).
                append(rootPath);
    }

}
