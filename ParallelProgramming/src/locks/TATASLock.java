package locks;
//Test-and-Test-and-Set (TATAS) Lock

import java.util.concurrent.atomic.AtomicBoolean;

public class TATASLock {
	// Write before reading
	AtomicBoolean state = new AtomicBoolean(false);
	int MIN_DELAY; int MAX_DELAY;
	public TATASLock(int MIN_DELAY, int MAX_DELAY) {
		this.MIN_DELAY = MIN_DELAY;
		this.MAX_DELAY = MAX_DELAY;
	}
	
	public void lock() {
		do
			while(state.get()) {}
		while (!state.compareAndSet(false, true));
	}
	public void unlock() {
		state.set(false);
	}
	public void lockWithBackOff() { // exponential backoff
		Backoff backoff = null;
		while (true) {
			while (state.get()) {};
			if (!state.getAndSet(true)) return;
			else {
				try {
					if (backoff == null) backoff = new Backoff(MIN_DELAY, MAX_DELAY);
					backoff.backoff();
				} catch (InterruptedException ex) {};
			}
		}
	}
}
