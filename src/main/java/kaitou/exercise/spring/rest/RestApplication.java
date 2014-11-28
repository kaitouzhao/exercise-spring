package kaitou.exercise.spring.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created with IntelliJ IDEA.
 * User: 赵立伟
 * Date: 2014/5/28
 * Time: 11:14
 */
@ComponentScan
@EnableAutoConfiguration
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }
}
