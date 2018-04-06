package com.liangsl.java.classLoad;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * 两个不同的ClassLoader加载的同一个class生成的对象不是同一个
 */
public class ClassLoadTest {

    @Test
    public void test(){
        //将com.liangsl.java.classLoad.Sample.class文件放在classData文件夹下
        String classDataRootPath = "E:\\ideaProjects\\classData";
        FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath);
        FileSystemClassLoader fscl2 = new FileSystemClassLoader(classDataRootPath);
        String className = "com.liangsl.java.classLoad.Sample";
        try {
            Class<?> class1 = fscl1.loadClass(className);
            Object obj1 = class1.newInstance();
            Class<?> class2 = fscl2.loadClass(className);
            Object obj2 = class2.newInstance();
            Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class);
            setSampleMethod.invoke(obj1, obj2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
