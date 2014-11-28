package kaitou.exercise.spring.mock.aop.proxy;

import kaitou.exercise.spring.mock.aop.AopAspect;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib实现aop动态代理.
 * <p>
 * CGLib采用了非常底层的字节码技术，<br/>
 * 其原理是通过字节码技术为一个类创建子类，<br/>
 * 并在子类中采用方法拦截的技术拦截所有父类方法的调用，<br/>
 * 顺势织入横切逻辑
 * </p>
 * User: 赵立伟
 * Date: 2014/5/23
 * Time: 9:45
 */
public class CglibAopProxy implements MethodInterceptor, IAopProxy {

    private Object target;
    private AopAspect aspectBean;
    private Enhancer enhancer = new Enhancer();

    public void setAspectBean(AopAspect aspectBean) {
        this.aspectBean = aspectBean;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object getProxy() {
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        aspectBean.mockDoBefore(method);
        Object result = methodProxy.invokeSuper(o, objects);
        aspectBean.mockDoAfter(method);
        return null;
    }
}
