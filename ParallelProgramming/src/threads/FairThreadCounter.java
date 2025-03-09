package threads;
import counters.Counter;
import counters.MyCounter;
// Works in Robin-Round fashion. First thread-0 increments, then thread-1, thread-2, ..., thread-(n-1), then again thread-0, thread-1, ...
// The main thing why this works is because of static MyCounter nextThreadId object which is shared among all instances of FairThreadCounter class.
// On nextThreadId threads always synchronize.
public class FairThreadCounter extends ThreadCounter {
    public static MyCounter nextThreadId = new MyCounter(10); // random value initially, has to be static because it is
    // global for all FairThreadCounters
    public FairThreadCounter(Counter counter, int id, int numThreads, int numIterations) {
        super(counter, id, numThreads, numIterations);
        nextThreadId.n = numThreads;
    }

    @Override
    public void run() {
        for (int i = 0; i < numIterations; i++) {
            this.increment();
        }
    }

    public boolean cond(int id) {
        return nextThreadId.val() != id;
    }

    public void increment() {
        synchronized (nextThreadId) { // synchronize on nextThreadId counter
            while (cond(id)) { // check whether it is thread's turn to add
                try {
                    nextThreadId.wait(); // release the lock on nextThreadId
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // System.out.println(id + " got access");
            counter.increment(); // doesn't need to be synchronized here because only if it is thread's turn,
            // then it got out of the while loop
            // System.out.println("Thread " + id + " added"); // for SequentialCounter
            nextThreadId.inc();
            nextThreadId.notifyAll(); // notify waiting threads
        }
    }
}
