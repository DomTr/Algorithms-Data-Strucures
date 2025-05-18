package locks;
class RWCombined{
	  int writers;
	  int writersWaiting, writersWait;
	  int readersWaiting;
	  int readers;
	  public enum PriorityType{
		  Readers, Writers, Fair
	  }
	  final PriorityType priority = PriorityType.Writers; 
	  
	  public RWCombined()
	  {
	    writers = 0;
	    readers = 0;
	    writersWaiting = 0;
	    readersWaiting = 0;
	    writersWait=0;
	  }

	  synchronized void AcquireRead()
	  {
	    if (priority == PriorityType.Fair) readersWaiting++;
	    while (writers > 0 || writersWaiting > 0 && writersWait <= 0)
	      try {
	        wait();
	      } catch (InterruptedException e) { e.printStackTrace(); }
	    if (priority == PriorityType.Fair) {
	      readersWaiting--;
	      writersWait--;
	    }
	    readers++;
	  }
	  
	  synchronized void ReleaseRead()
	  {
	    readers--;
	    notifyAll();
	  }
	  
	  synchronized void AcquireWrite()
	  {
	    if (priority == PriorityType.Fair || priority == PriorityType.Writers) writersWaiting++;
	    while (writers > 0 || readers > 0 || writersWait > 0)
	      try {
	        wait();
	      } catch (InterruptedException e) { e.printStackTrace(); }
	    if (priority == PriorityType.Fair || priority == PriorityType.Writers) writersWaiting--;
	    writers++;
	  }
	  
	  synchronized void ReleaseWrite()
	  {
	    writers--;
	    writersWait = readersWaiting;
	    notifyAll();
	  }
	  
	}