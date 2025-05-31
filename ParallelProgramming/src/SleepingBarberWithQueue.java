import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SleepingBarberWithQueue {
    private final int capacity;
    private final Queue<Long> queue;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public SleepingBarberWithQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    public void enqueue(long customer) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() >= capacity) {
                notFull.await(); // wait if waiting room is full
            }
            boolean wasEmpty = queue.isEmpty();
            queue.add(customer);
            if (wasEmpty) {
                notEmpty.signal(); // wake up barber if sleeping
            }
        } finally {
            lock.unlock();
        }
    }

    public long dequeue() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await(); // wait if no customers
            }
            long customer = queue.poll(); // remove customer
            if (queue.size() == capacity - 1) {
                notFull.signal(); // signal waiting customers
            }
            return customer;
        } finally {
            lock.unlock();
        }
    }
}
