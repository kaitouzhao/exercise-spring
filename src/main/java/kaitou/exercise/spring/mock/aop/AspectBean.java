package kaitou.exercise.spring.mock.aop;

import java.util.List;

/**
 * 配置中的aop切面.
 * User: 赵立伟
 * Date: 2014/5/22
 * Time: 17:12
 */
public class AspectBean {

    private String id;
    private String ref;

    public AspectBean(String id, String ref, List<Pointcut> pointcuts) {
        this.id = id;
        this.ref = ref;
        this.pointcuts = pointcuts;
    }

    private List<Pointcut> pointcuts;

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

    public List<Pointcut> getPointcuts() {
        return pointcuts;
    }

    public void setPointcuts(List<Pointcut> pointcuts) {
        this.pointcuts = pointcuts;
    }
}
