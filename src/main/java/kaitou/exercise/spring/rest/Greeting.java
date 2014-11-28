package kaitou.exercise.spring.rest;

/**
 * Created with IntelliJ IDEA.
 * User: 赵立伟
 * Date: 2014/5/23
 * Time: 13:57
 */
public class Greeting {

    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
