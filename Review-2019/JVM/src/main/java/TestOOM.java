import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

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


}
