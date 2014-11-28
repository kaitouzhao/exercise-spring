package kaitou.exercise.spring.mock.aop.proxy;

import kaitou.exercise.spring.mock.aop.AopAspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK实现aop动态代理.
 * <p>
 * JDK实现动态代理需要实现类通过接口定义业务方法
 * </p>
 * User: 赵立伟
 * Date: 2014/5/22
 * Time: 16:38
 */
public class JdkDynamicAopProxy implements InvocationHandler, IAopProxy {

    private AopAspect aspectBean;

    private Object target;

    public void setAspectBean(AopAspect aspectBean) {
        this.aspectBean = aspectBean;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        aspectBean.mockDoBefore(method);
        Object result = method.invoke(target, args);
        aspectBean.mockDoAfter(method);
        return result;
    }
}
