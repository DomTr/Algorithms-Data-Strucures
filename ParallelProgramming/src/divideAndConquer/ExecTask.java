package divideAndConquer;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// Finds minimal
public class ExecTask implements Callable<Integer>{
	int l, h;
	int[] arr;
	ExecutorService ex;
	
	public ExecTask(ExecutorService ex, int l, int h, int[] arr) {
		this.ex = ex;
		this.l = l;
		this.h = h;
		this.arr = arr;
	}
	public Integer call() throws Exception {
        //System.out.println("In call " + Thread.currentThread().getName() + " for range [" + l + ", " + h + ")");
        
        // Base case
        if (h - l <= 1) { // if BC is h - l == 1 and number of threads is 4, then will not terminate
            return h - l == 1 ? arr[l] : Math.min(arr[l],arr[l+1]);
        }

        int mid = l + (h - l) / 2; 
        //sSystem.out.println(mid);
        ExecTask t1 = new ExecTask(ex, l, mid, arr);
        ExecTask t2 = new ExecTask(ex, mid, h, arr);

        // Submit tasks for parallel execution
        Future<Integer> f1 = ex.submit(t1);
        Future<Integer> f2 = ex.submit(t2);

        try {
            // Combine the results
            return Math.min(f1.get(), f2.get());
        } catch (Exception e) {
            e.printStackTrace();  // Print stack trace to debug
            return Integer.MAX_VALUE; // Return a large value to indicate an error
        }
    }
	public static void main(String[] args) {
		int[] arr = new int[] {15, 12, 99, 89, 1, -10, 29, -100};
		int numThreads = Runtime.getRuntime().availableProcessors();
		
		ExecutorService ex = Executors.newFixedThreadPool(numThreads); // If not enough threads this will not terminate
		//ExecutorService ex = Executors.newCachedThreadPool();

		ExecTask task = new ExecTask(ex, 0, arr.length, arr);
		Future<Integer> min = ex.submit(task);
		try {
			System.out.println(min.get());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ex.shutdown();
			try {
				if (!ex.awaitTermination(60, TimeUnit.SECONDS)) {
					ex.shutdownNow();
				}
			} catch (InterruptedException e) {
				ex.shutdownNow();
			}
		}

	}
}
