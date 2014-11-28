package kaitou.exercise.spring.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created with IntelliJ IDEA.
 * User: 赵立伟
 * Date: 2014/5/23
 * Time: 13:58
 */
@RestController
public class GreetingController {

    private static final String TEMPLATE = "Hello,%s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public
    @ResponseBody
    Greeting greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
    }
}
