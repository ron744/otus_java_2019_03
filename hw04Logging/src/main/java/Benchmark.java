import java.util.ArrayList;
import java.util.List;

public class Benchmark implements BenchmarkMBean{
    private final int loopCounter;
    private volatile int size = 0;

    public Benchmark(int loopCounter) {
        this.loopCounter = loopCounter;
    }

    void run() throws InterruptedException {
        for (int idx = 0; idx < loopCounter; idx++) {
            int local = size;

            List<Integer> list = new ArrayList<>();
            while (true){
                for (int i = 0; i < local; i++){
                    if (i % 1000 == 0){
                        Thread.sleep(1);
                    }
                    list.add(new Integer(0));
                }
            }
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        System.out.println("new size:" + size);
        this.size = size;
    }
}
