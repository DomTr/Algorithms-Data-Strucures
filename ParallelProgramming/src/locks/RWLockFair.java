package locks;

public class RWLockFair {
	  int writers = 0;
	  int readers = 0;
	  int writersWaiting = 0;
	  int readersWaiting = 0;
	  int writersWait = 0;
	  
	  synchronized void acquire_read() {
	    readersWaiting++;
	    while(writers > 0 || (writersWaiting > 0 && writersWait <= 0)) {
	      try {wait();}
	      catch(InterruptedException e) {};
	    }
	    readersWaiting--;
	    writersWait--;
	    readers++;
	  }
	  synchronized void release_read() {
	    readers--;
	    notifyAll();
	  }
	  synchronized void acquire_write() {
	    writersWaiting++;
	    while(writers > 0 || readers > 0 || writersWait > 0) {
	      try {wait();}
	      catch (InterruptedException e) {};
	    }
	    writersWaiting--;
	    writers++;
	  }
	  synchronized void release_write() {
	    writers--;
	    writersWait = readersWaiting;
	    notifyAll();
	  }
	}
