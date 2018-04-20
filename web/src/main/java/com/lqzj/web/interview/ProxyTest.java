package com.lqzj.web.interview;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author liuqian1
 */
public class ProxyTest {
    public static void main(String[] args) {
        // jdk动态代理(接口)
        ProxyTestServiceImpl testService = new ProxyTestServiceImpl();
        Object o = Proxy.newProxyInstance(ProxyTest.class.getClassLoader(), testService.getClass().getInterfaces(),
                (proxy, method, args1) -> {
                    System.out.println("invoke before");
                    method.invoke(testService, args1);
                    System.out.println("invoke after");
                    return null;
                });
        ProxyTestService service = (ProxyTestService) o;
        service.get();
        service.set();

        // cglib动态代理(类)
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(LRUTest.class);
        enhancer.setCallback(new CglibTest());
        Object o1 = enhancer.create();
        LRUTest proxyTest = (LRUTest) o1;
        proxyTest.get();

    }

    static class CglibTest implements MethodInterceptor {
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("method:" + method);
            methodProxy.invokeSuper(o, objects);
            return null;
        }
    }

    public void get() {
        System.out.println("get success");
    }
}
