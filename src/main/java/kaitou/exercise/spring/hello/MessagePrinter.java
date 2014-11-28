package kaitou.exercise.spring.hello;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: 赵立伟
 * Date: 2014/5/21
 * Time: 15:54
 */
@Component
public class MessagePrinter {

    private final Log log = LogFactory.getLog(getClass());

    @Autowired
    private MessageService messageService;

    public MessagePrinter() {
    }

    public MessagePrinter(MessageService messageService) {
        this.messageService = messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public void printMessage() {
        if (log.isDebugEnabled()) {
            log.debug("MessagePrinter execute.");
        }
        System.out.println(messageService.getMessage());
    }
}
