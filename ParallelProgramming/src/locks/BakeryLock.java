package locks;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class BakeryLock {
	AtomicIntegerArray flag;
	AtomicIntegerArray label;
	final int n;
	BakeryLock(int n) {
		this.n = n;
		flag = new AtomicIntegerArray(n);
		label = new AtomicIntegerArray(n);
	}
	/*
	 * O(n)
	 */
	int MaxLabel() {
		int max = label.get(0);
		for (int i = 1; i < n; i++) {
			max = Math.max(max, label.get(i));
		}
		return max;
	}
	/*
	 * Checks whether there is a thread with smaller id. If yes, there is a conflict, and thread stays in Acquire while-loop.
	 * If diff == 0, do a tie-breaker by i < me.
	 */
	boolean Conflict(int me) {
		for (int i = 0; i < n; i++) {
			if (i != me && flag.get(i) != 0) {
				int diff = label.get(i) - label.get(me);
				if (diff < 0 || diff == 0 && i < me) return true;
			}
		}
		return false;
	}
	public void Acquire(int me) {
		flag.set(me,  1);
		label.set(me, MaxLabel() + 1);
		while(Conflict(me));
	}
	public void Release(int me) {
		flag.set(me,  0);
	}
}
