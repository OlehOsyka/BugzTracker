package bugztracker.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * Created by oleg on 18.10.15.
 */
public class HibernateLazyObjectMapper extends ObjectMapper {

    public HibernateLazyObjectMapper() {
        registerModule(new Hibernate4Module());
    }
}
