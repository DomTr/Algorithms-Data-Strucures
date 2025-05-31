import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SleepingBarber {
	int in=0, out=0, size;
	long[] buf;
	final Lock lock = new ReentrantLock();
	int n=0; final Condition notFull = lock.newCondition();
	int m; final Condition notEmpty = lock.newCondition();
	Queue<Long> q;
	SleepingBarber(int s) {
		size = s;m=size-1;
		buf = new long[size];
		q = new LinkedList<>();
	}
	void enqueue(long x) {
		lock.lock();
		m--;
		if (m < 0) {
			while(isFull()) {
				try {notFull.await();} catch(InterruptedException e) {};
			}
		}
		doEnqueue(x);
		n++;
		if (n <= 0)notEmpty.signal();
		lock.unlock();
	}
	
	long dequeue() {
		long x;
		lock.lock();
		n--; if (n < 0) {
			while (isEmpty()) {
				try { notEmpty.await();} catch(InterruptedException e) {};
			}
		}
		x = doDequeue();
		m++;
		if (m <= 0) notFull.signal();
		lock.unlock();
		return x;
	}
	private boolean isFull() {
		return m == size; // buffer for barber full
	}
	private void doEnqueue(long x) {
		q.add(x); // 
	}
	private boolean isEmpty() {
		return n == 0;
	}
	private long doDequeue() {
		return q.element();
	}
}
