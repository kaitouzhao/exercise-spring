package kaitou.exercise.spring.mock.aop;

/**
 * 配置中的aop切点.
 * User: 赵立伟
 * Date: 2014/5/22
 * Time: 17:12
 */
public class Pointcut {

    private String id;
    private String ref;
    private String type = "jdk";

    public Pointcut(String id, String ref) {
        this.id = id;
        this.ref = ref;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
