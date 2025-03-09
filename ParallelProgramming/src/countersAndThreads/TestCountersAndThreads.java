package countersAndThreads;

import counters.Counter;
import counters.SynchronizedCounter;
import threads.ThreadCounterFactory;

import java.util.ArrayList;
import java.util.List;

public class TestCountersAndThreads {
    public static void count(final Counter counter, int numThreads, ThreadCounterFactory.ThreadCounterType type, int numInterations) {
        List<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < numThreads; i++) {
            threads.add(new Thread(ThreadCounterFactory.make(type, counter, i, numThreads, numInterations)));
        }

        for (int i = 0; i < numThreads; i++) {
            threads.get(i).start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        testFairCounter();
    }
    public static void testFairCounter() {
        Counter counter = new SynchronizedCounter(); // which type of counter can we use here - any works, even
        // SequentialCounter
        count(counter, 12, ThreadCounterFactory.ThreadCounterType.FAIR, 10);
        System.out.println("Counter: " + counter.value());
    }
}
