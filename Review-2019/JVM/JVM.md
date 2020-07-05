//JVM是一种规范
JROcket、HOTSPOT（热锅） VM
JVM调优主要是提高程序性能，比如降低系统延迟，增加吞吐量，锁优化，垃圾回收的优化
底层主要是通过解释执行（解释成0101）和编译执行（缓存热点代码）混合模式进行程序执行
//JVM体系结构
1、类加载系统
2、数据区
3、执行引擎
//OOM分析
堆内存溢出：通过产生字节对象OOM
方法区（元空间）内存溢出，通过代理
//JPS&&JMAP
jps -l 完整包名类名
jps -v JVM参数信息
jmap -heap pid 打印进程堆信息
jmap -histo:live pid检测内存泄漏
jhat .dump 分析dump文件
jstack -l pid 检测死锁
jconsole
//jmapbootstrap
通过修改catalina.bat文件，控制tomcat的堆内存等分配
set catalina_opts= -Xms1024m -Xmx1024m -Xmn300m
//类加载
将类信息加载到metaspace（方法区jdk1.7)
包括连接（准备、验证、分析），初始化，使用，卸载等阶段
个人定义的类加载器主要在初始化阶段 其他阶段由JVM主导
显示加载：通过反射直接加载类 隐式加载：通过调用静态属性、继承等加载
被动使用：调用父类静态属性，不会加载子类静态域
//双亲委派
bootstrap-><-ext-><-app-><-custom