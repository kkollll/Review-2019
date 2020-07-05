import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestClassLoader {

    //-XX:+TraceClassLoading
    @Test
    public void TestClassLoader01() throws ClassNotFoundException {

//        ClassLoader loader = TestClassLoader.class.getClassLoader();
//        loader.loadClass("ClassA");
//        Class.forName("ClassA");
//        Class.forName("ClassA", true, loader);
        //先执行静态代码块 隐式加载
        System.out.println(ClassA.a);
    }

    private void doMethod01() {

        ClassLoader loader = ClassLoader.getSystemClassLoader();
        // sun.misc.Launcher$AppClassLoade
        System.out.println(loader);
        // sun.misc.Launcher$ExtClassLoader
        System.out.println(loader.getParent());
        // 返回null代表bootstrap 底层用C语言
        System.out.println(loader.getParent().getParent());
    }

    private void doMethod02() {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        // sun.misc.Launcher$AppClassLoade
        System.out.println(loader);
        // sun.misc.Launcher$ExtClassLoader
        System.out.println(loader.getParent());
        // 返回null代表bootstrap 底层用C语言
        System.out.println(loader.getParent().getParent());
    }

    private void doMethod03() {

        // sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(TestClassLoader.class.getClassLoader());
        // 返回null代表bootstrap 底层用C语言
        System.out.println(ArrayList.class.getClassLoader());
    }

    @Test
    public void doMethod() {
//        doMethod01();
//        doMethod02();
        doMethod03();
    }

}

class ClassA {
    static int a = 100;

    static {
        System.out.println("ClassA");
    }

}

