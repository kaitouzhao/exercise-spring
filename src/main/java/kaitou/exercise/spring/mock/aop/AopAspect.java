package kaitou.exercise.spring.mock.aop;

import java.lang.reflect.Method;

/**
 * aop切面.
 * User: 赵立伟
 * Date: 2014/5/22
 * Time: 16:46
 */
public abstract class AopAspect {

    public abstract void mockDoBefore(Method method);

    public abstract void mockDoAfter(Method method);
}
