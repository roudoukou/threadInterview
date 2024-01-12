package com.atguigu.jvm;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * JVM参数
 * XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 * <p>
 * Java 8及之后的版本使用Metaspace来替代永久代。
 * Metaspace是方法区在HotSpot中的实现，它与持久代最大的区别在于: Metaspace 并不在虚拟机内存中而是使用本地内存
 * 即java8中,classe metadata(the virtual machines internal presentation of Java class),被存储在做
 * Metaspace 的 native memory
 * 永久代 (java8后被原空问Metaspace取代了)存放了以下信息:
 * 虚拟机加载的类信息
 * 常量池
 * 静态变量
 * 即时编译后的代码
 * <p>
 * 模拟 Metaspace 空问溢出，我们不断生成类往元空问灌，类占据的空问总是会超过 Metaspace 指定的空问大小的
 */
public class MetaspaceOOMTest {

    static class OOMTest {
    }

    public static void main(String[] args) {
        int i = 0; // 模拟计数多少次以后发生异常

        try {
            while (true) {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, args);
                    }
                });
                enhancer.create();
            }
        } catch (Throwable e) {
            System.out.println("多少次后发生了异常: " + i);
            // throw new RuntimeException(e);
            e.printStackTrace();
        }
    }
}

// 运行结果
/*
多少次后发生了异常: 308
java.lang.OutOfMemoryError: Metaspace
	at java.lang.Class.forName0(Native Method)
	at java.lang.Class.forName(Class.java:348)
	at net.sf.cglib.core.ReflectUtils.defineClass(ReflectUtils.java:467)
	at net.sf.cglib.core.AbstractClassGenerator.generate(AbstractClassGenerator.java:339)
	at net.sf.cglib.proxy.Enhancer.generate(Enhancer.java:492)
	at net.sf.cglib.core.AbstractClassGenerator$ClassLoaderData.get(AbstractClassGenerator.java:117)
	at net.sf.cglib.core.AbstractClassGenerator.create(AbstractClassGenerator.java:294)
	at net.sf.cglib.proxy.Enhancer.createHelper(Enhancer.java:480)
	at net.sf.cglib.proxy.Enhancer.create(Enhancer.java:305)
	at com.atguigu.jvm.MetaspaceOOMTest.main(MetaspaceOOMTest.java:45)
 */
