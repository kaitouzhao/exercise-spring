package kaitou.exercise.spring.hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: 赵立伟
 * Date: 2014/5/28
 * Time: 11:11
 */
@Configuration
@ComponentScan
public class HelloApplication {

    //    @Bean
    MessageService mockMessageService() {
        return new MessageService() {
            public String getMessage() {
                return "Mock:Hello World";
            }
        };
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HelloApplication.class);
        MessagePrinter printer = context.getBean(MessagePrinter.class);
        printer.printMessage();
    }
}
