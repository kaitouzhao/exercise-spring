package kaitou.exercise.spring.hello;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 赵立伟
 * Date: 2014/5/21
 * Time: 16:37
 */
@Service
public class MessageServiceImpl implements MessageService {

    private final Log log = LogFactory.getLog(getClass());

    @Override
    public String getMessage() {
        if (log.isDebugEnabled()) {
            log.debug("MessageServiceImpl execute.");
        }
        return "MessageServiceImpl:Hello world";
    }
}
