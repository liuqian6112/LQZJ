package com.lqzj.web.interview;

import com.google.common.collect.Lists;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author liuqian1
 */
public class ReflectTest {
    public static void main(String[] args) throws Exception {
        // 一个类只有一个Class对象产生
        ReflectTest test = new ReflectTest();
        Class<? extends ReflectTest> testClass1 = test.getClass();
        Class<ReflectTest> testClass = ReflectTest.class;

        System.out.println(testClass == testClass1);

        Class<?> aClass = Class.forName("com.lqzj.web.interview.ReflectTest");

        System.out.println(aClass == testClass);
        // 通过反射越过泛型检查
        List<String> list = Lists.newArrayList();
        list.add("100");
        list.add("200");

        Class<? extends List> listClass = list.getClass();
        Method addMethod = listClass.getMethod("add", Object.class);
//        addMethod.invoke(list, 500);

        for (String s : list) {
            System.out.println(s);
        }

        ReflectTest reflectTest = new ReflectTest();
        Class<? extends ReflectTest> reflectTestClass = reflectTest.getClass();
        Method func = reflectTestClass.getMethod("func", String[].class);
        func.invoke(reflectTest, new String[1]);// null
        func.invoke(reflectTest, new Object[]{new String[]{"1", "2"}});// 2
        func.invoke(reflectTest, (Object) new String[]{"1", "2"});// 2
        func.invoke(reflectTest, new String[]{"1", "2"});// 报错
        func.invoke(reflectTest, new String[]{"1"});// 报错
        func.invoke(reflectTest, new Object[2]);// null

    }

    public void func(String[] arg) {
        System.out.println(arg == null ? "null" : arg.length);
    }
}
