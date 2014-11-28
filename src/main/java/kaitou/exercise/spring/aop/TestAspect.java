package kaitou.exercise.spring.aop;

import kaitou.exercise.spring.mock.aop.AopAspect;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: 赵立伟
 * Date: 2014/5/22
 * Time: 10:27
 */
@Aspect
public class TestAspect extends AopAspect {

    private final Log log = LogFactory.getLog(getClass());

    @Pointcut("execution(* kaitou.exercise.spring.hello.*.*(..))")
    private void anyMethod() {
    }

    @Before("anyMethod()")
    public void doBefore(JoinPoint joinPoint) {
        if (log.isInfoEnabled()) {
            log.info("log beginning method:" + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
        }
    }

    @Override
    public void mockDoBefore(Method method) {
        if (log.isDebugEnabled()) {
            log.debug("Before method: " + method.getDeclaringClass().getName() + "." + method.getName());
        }
    }

    @Override
    public void mockDoAfter(Method method) {
        if (log.isDebugEnabled()) {
            log.debug("After method: " + method.getDeclaringClass().getName() + "." + method.getName());
        }
    }

}
