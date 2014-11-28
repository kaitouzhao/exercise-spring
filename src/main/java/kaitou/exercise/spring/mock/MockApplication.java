package kaitou.exercise.spring.mock;

import kaitou.exercise.spring.hello.MessagePrinter;
import kaitou.exercise.spring.mock.context.ClassPathXmlApplicationContext;
import org.jdom.JDOMException;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: 赵立伟
 * Date: 2014/5/28
 * Time: 11:13
 */
public class MockApplication {

    public static void main(String[] args) throws JDOMException, IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        MessagePrinter printer = (MessagePrinter) context.getBean("messagePrinter");
        printer.printMessage();
    }
}
