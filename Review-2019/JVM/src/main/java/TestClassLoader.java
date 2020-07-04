import org.junit.jupiter.api.Test;

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

}
class ClassA {
    static int a = 100;
    static {
        System.out.println("ClassA");
    }

}

