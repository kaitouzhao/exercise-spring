package kaitou.exercise.spring.mock.context;

import kaitou.exercise.spring.mock.aop.AopAspect;
import kaitou.exercise.spring.mock.aop.AopConfig;
import kaitou.exercise.spring.mock.aop.AspectBean;
import kaitou.exercise.spring.mock.aop.Pointcut;
import kaitou.exercise.spring.mock.aop.proxy.CglibAopProxy;
import kaitou.exercise.spring.mock.aop.proxy.JdkDynamicAopProxy;
import kaitou.exercise.spring.mock.bean.IBeanFactory;
import kaitou.exercise.spring.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * application配置加载.
 * User: 赵立伟
 * Date: 2014/5/22
 * Time: 15:52
 */
public class ClassPathXmlApplicationContext implements IBeanFactory {

    private final Log log = LogFactory.getLog(getClass());

    private Document doc;
    private AopConfig aopConfig = new AopConfig();
    private Map<String, Object> beans = new HashMap<String, Object>();

    public ClassPathXmlApplicationContext(String path) throws JDOMException, IOException {
        loadXml(path);
        initializeAop();
        initializeBeans();
    }

    /**
     * 初始化bean
     */
    private void initializeBeans() {
        Element root = doc.getRootElement();
        List<Element> beanList = root.getChildren("bean");
        if (log.isDebugEnabled()) {
            log.debug("Create beans start");
        }
        for (int i = 0; i < beanList.size(); i++) {
            Element element = beanList.get(i);
            String id = element.getAttributeValue("id");
            String clazz = element.getAttributeValue("class");
            Object o = null;
            try {
                if (log.isDebugEnabled()) {
                    log.debug("Create bean:" + id + " start.");
                }
                o = Class.forName(clazz).newInstance();
                beans.put(id, o);
                if (log.isDebugEnabled()) {
                    log.debug("Create bean:" + id + " end.");
                }
            } catch (ClassNotFoundException e) {
                log.error("Create bean: " + id + " failed", e);
            } catch (InstantiationException e) {
                log.error("Create bean: " + id + " failed", e);
            } catch (IllegalAccessException e) {
                log.error("Create bean: " + id + " failed", e);
            }
            List<String> aspectInfo = aopConfig.getAspectInfo(id);
            Object targetBean = o;
            if (!aspectInfo.isEmpty()) {
                String aspectBeanId = aspectInfo.get(0);
                Object aspectBean = getBean(aspectBeanId);
                String type = aspectInfo.get(1);
                if ("cglib".equalsIgnoreCase(type)) {
                    log.debug("Creating CGLIB proxy: target source is " + id);
                    CglibAopProxy aopProxy = new CglibAopProxy();
                    aopProxy.setAspectBean((AopAspect) aspectBean);
                    aopProxy.setTarget(targetBean);
                    targetBean = aopProxy.getProxy();
                } else {
                    log.debug("Creating JDK dynamic proxy: target source is " + id);
                    JdkDynamicAopProxy aopProxy = new JdkDynamicAopProxy();
                    aopProxy.setAspectBean((AopAspect) aspectBean);
                    aopProxy.setTarget(targetBean);
                    targetBean = aopProxy.getProxy();
                }
                beans.put(id, targetBean);
            }
            List<Element> propertyList = element.getChildren("property");
            for (int j = 0; j < propertyList.size(); j++) {
                Element property = propertyList.get(j);
                String name = property.getAttributeValue("name");
                String ref = property.getAttributeValue("ref");
                Object refBean = beans.get(ref);
                String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
                try {
                    if (log.isDebugEnabled()) {
                        log.debug("DI bean:" + name + " start.");
                    }
                    Method m = targetBean.getClass().getMethod(methodName, refBean.getClass().getInterfaces()[0]);
                    m.invoke(targetBean, refBean);
                    if (log.isDebugEnabled()) {
                        log.debug("DI bean:" + name + " end.");
                    }
                } catch (NoSuchMethodException e) {
                    log.error("DI bean: " + name + " failed", e);
                } catch (InvocationTargetException e) {
                    log.error("DI bean: " + name + " failed", e);
                } catch (IllegalAccessException e) {
                    log.error("DI bean: " + name + " failed", e);
                }
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("Create beans end.");
        }
    }

    /**
     * 加载xml配置文件
     *
     * @param path 文件名
     */
    private void loadXml(String path) throws JDOMException, IOException {
        SAXBuilder sb = new SAXBuilder();
        if (log.isDebugEnabled()) {
            log.debug("load xml:" + path);
        }
        doc = sb.build(getClass().getClassLoader().getResourceAsStream(path));
    }

    /**
     * 初始化aop
     */
    private void initializeAop() {
        Element element = doc.getRootElement().getChild("aopConfig");
        if (element == null) {
            return;
        }
        List<Element> elements = element.getChild("aspectBeans").getChildren("aspectBean");
        List<AspectBean> aspectBeans = new ArrayList<AspectBean>();
        for (int i = 0; i < elements.size(); i++) {
            List<Element> eles = elements.get(i).getChild("pointcuts").getChildren("pointcut");
            if (eles.isEmpty()) {
                continue;
            }
            List<Pointcut> pointcuts = new ArrayList<Pointcut>();
            for (int j = 0; j < eles.size(); j++) {
                Element ele = eles.get(j);
                Pointcut pointcut = new Pointcut(ele.getAttributeValue("id"), ele.getAttributeValue("ref"));
                String type = ele.getAttributeValue("type");
                if (!StringUtils.isEmpty(type)) {
                    pointcut.setType(type);
                }
                pointcuts.add(pointcut);
            }
            AspectBean aspectBean = new AspectBean(elements.get(i).getAttributeValue("id"), elements.get(i).getAttributeValue("ref"), pointcuts);
            aspectBeans.add(aspectBean);
        }
        aopConfig.setAspectBeans(aspectBeans);
    }

    @Override
    public Object getBean(String id) {
        return beans.get(id);
    }
}
