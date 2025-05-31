import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SleepingBarber2 {
    private final int size;
    private final long[] buf;
    private int in = 0;
    private int out = 0;
    private int count = 0;

    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public SleepingBarber2(int capacity) {
        this.size = capacity;
        this.buf = new long[capacity];
    }

    public void enqueue(long customer) throws InterruptedException {
        lock.lock();
        try {
            while (count == size) {
                notFull.await(); // wait if buffer is full
            }
            buf[in] = customer;
            in = (in + 1) % size;
            count++;
            notEmpty.signal(); // wake up barber
        } finally {
            lock.unlock();
        }
    }

    public long dequeue() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await(); // wait if buffer is empty
            }
            long customer = buf[out];
            out = (out + 1) % size;
            count--;
            notFull.signal(); // let customer in
            return customer;
        } finally {
            lock.unlock();
        }
    }
}
