package kaitou.exercise.spring.mock.aop;

import java.util.ArrayList;
import java.util.List;

/**
 * aop配置.
 * User: 赵立伟
 * Date: 2014/5/22
 * Time: 17:11
 */
public class AopConfig {

    private List<AspectBean> aspectBeans;

    public List<AspectBean> getAspectBeans() {
        return aspectBeans;
    }

    public void setAspectBeans(List<AspectBean> aspectBeans) {
        this.aspectBeans = aspectBeans;
    }

    /**
     * 通过切点获取切面信息
     *
     * @param pointcut 切点
     * @return 切面信息：切面id、aop代理类型
     */
    public List<String> getAspectInfo(String pointcut) {
        List<String> result = new ArrayList<String>();
        for (AspectBean aspectBean : aspectBeans) {
            for (Pointcut p : aspectBean.getPointcuts()) {
                if (pointcut.equalsIgnoreCase(p.getRef())) {
                    result.add(aspectBean.getRef());
                    result.add(p.getType());
                    break;
                }
            }
        }
        return result;
    }
}
