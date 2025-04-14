package locks;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class PetersonLock {
	AtomicIntegerArray flag = new AtomicIntegerArray(2);
	volatile int victim;
	public void Acquire(int id) {
		flag.set(id, 1);
		victim = id;
		while(flag.get(1-id) == 1 && victim == id);
	}
	public void Release(int id) {
		flag.set(id, 0);
	}
}
