package locks;

import java.util.concurrent.atomic.AtomicBoolean;

public class TASLock { // slow lock. Atomic operations locks CPU cores
	/*
	  	1. run n threads
	 	2. each thread acquires and releases
		the TASLock a million times
		The problem is that all threads fight for the memory bus. Since operations are atomic, only one thread can read the bus.
	 */
	AtomicBoolean state = new AtomicBoolean(false); 
	
	public void lock() {
		while(state.getAndSet(true)) {};
	}
	public void unlock() {
		state.set(false);
	}
}
