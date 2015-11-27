package bugztracker.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * Created by oleg
 * Date: 18.10.15
 * Time: 2:57
 */
public class HibernateLazyObjectMapper extends ObjectMapper {

    public HibernateLazyObjectMapper() {
        registerModule(new Hibernate4Module());
    }
}
