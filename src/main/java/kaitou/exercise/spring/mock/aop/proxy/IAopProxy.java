package kaitou.exercise.spring.mock.aop.proxy;

/**
 * aop动态代理.
 * User: 赵立伟
 * Date: 2014/5/23
 * Time: 10:56
 */
public interface IAopProxy {
    /**
     * 创建动态代理
     *
     * @return 动态代理
     */
    public Object getProxy();
}
