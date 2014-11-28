package kaitou.exercise.spring.aop;

import kaitou.exercise.spring.hello.MessagePrinter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: 赵立伟
 * Date: 2014/5/28
 * Time: 11:11
 */
public class AopApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        MessagePrinter printer = context.getBean(MessagePrinter.class);
        printer.printMessage();
    }
}
