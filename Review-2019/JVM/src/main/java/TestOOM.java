import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.*;

public class TestOOM {


    // -Xmx50m -Xms5m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=e:/oom.dump
    @Test
    public void testOOM() {

        long t1 = System.currentTimeMillis();

        try {
            List<byte[]> list = new LinkedList<byte[]>();
            for (int i = 0; i < 38; i++) {
                list.add(new byte[1024 * 1024]);
            }
        } finally {
            long t2 = System.currentTimeMillis();
            float t = t2 - t1;
            System.out.println("oom: " + t);
        }

    }

    // VM Args -XX:PermSize=10M -XX:MaxPermSize=10M(JDK6.0)
    // VM Args -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M(JDK8.0)
    // java.lang.OutOfMemoryError: Metaspace
    @Test
    public void testCgLib() {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(ProxyObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
                System.out.println("I am proxy");
                return methodProxy.invokeSuper(o, objects);
            });
            ProxyObject proxy = (ProxyObject) enhancer.create();
            proxy.greet();
        }
    }

    @Test
    public void testJmap() {
        while (true) {
            byte[] bytes = new byte[1024 * 1024];
        }
    }

    private static class T implements Runnable {

        private List<Integer> a;
        private List<Integer> b;
        private Integer target;

        public T(List a, List b,Integer target) {
            this.a = a;
            this.b = b;
            this.target = target;
        }

        @Override
        public void run() {
            movelistItem(a, b, target);
        }

        private void movelistItem(List<Integer> a, List<Integer> b, Integer target) {
            System.out.println("attempting lock for list a");
            synchronized (a) {
                System.out.println("lock acquired for list a");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("attempting lock for list b");
                synchronized (b) {
                    System.out.println("lock acquired for list b");
                    if (b.remove(target)) {
                        a.add(target);
                    }
                    System.out.println("moved item to list b");
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10));
        List<Integer> b = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9));
        Thread t1 = new Thread(new T(a, b, 2));
        Thread t2 = new Thread(new T(b, a, 9));
        t1.start();
        t2.start();
    }
}