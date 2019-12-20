
import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.*;
import javax.management.openmbean.CompositeData;
import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;


public class GCDemo {

    public static void main(String[] args) throws InterruptedException, MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {

        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());
        installGCMonitoring();
        long beginTime = System.currentTimeMillis();

        int size = 5 * 1000 * 1000;
        int loopCounter = 1000;
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        ObjectName name = new ObjectName("ru.otus:type=Benchmark");

        Benchmark mbean = new Benchmark(loopCounter);
        mbs.registerMBean(mbean, name);
        mbean.setSize(size);
        mbean.run();
    }

    private static void installGCMonitoring(){
        AtomicLong sumDuration = new AtomicLong();
        AtomicInteger count = new AtomicInteger(1);

        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();

        AtomicReference<String> infoString = new AtomicReference<>("");
        AtomicReference<String> gcName = new AtomicReference<>("");

        for (GarbageCollectorMXBean gcBean : gcbeans){
            System.out.println("GC name: " + gcbeans);
            NotificationEmitter emitter = (NotificationEmitter) gcBean;
            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)){
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                    String gcAction = info.getGcAction();
                    String gcCause = info.getGcCause();

                    long startTime = info.getGcInfo().getStartTime();
                    long duration = info.getGcInfo().getDuration();
                    sumDuration.addAndGet(duration);

                    infoString.set(info.getGcName() + " " + "sumDuration: " + sumDuration + " count: " + count);
                    gcName.set(info.getGcName());

                    System.out.println("start:" + startTime + " Name:" + gcName + ", action:" + gcAction + ", gcCause:" + gcCause + "(" + duration + " ms)"
                            + " sumDuration: " + sumDuration + " count: " + count);
                    count.getAndIncrement();

                    try {
                        Files.write(Paths.get("C:\\Serial.txt"), infoString.get().getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            emitter.addNotificationListener(listener, null, null);
        }
    }
}
