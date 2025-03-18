package divideAndConquer;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;


// Divide and conquer but using ForkJoin framework
public class ExecForkJoin extends RecursiveTask<Integer> {
	private static final long serialVersionUID = 1L; // needs to be declared
	int l, h;
	int[] arr;
	public ExecForkJoin(int l, int h, int[] arr) {
		this.l = l;
		this.h = h;
		this.arr = arr;
	}
	protected Integer compute() { // must be protected
		// base
		System.out.println(l + " " + h + " " + Thread.currentThread().getName());
		if (h - l <= 2) {
			return h - l == 1 ? arr[l] : Math.min(arr[l], arr[l+1]);
		}
		int mid = l + (h-l)/2;
		ExecForkJoin m1 = new ExecForkJoin(l, mid, arr);
		ExecForkJoin m2 = new ExecForkJoin(mid, h, arr);
		
		// run subtasks
		m1.fork(); // This call forks (schedules) the task m1 for execution in parallel with m2.compute()
		
		int min2 = m2.compute(); // This directly computes the result of the second subtask. This is the exact work that is assigned to current thread. Task m1 was assigned to another thread in the pool
		int min1 = m1.join(); // After m1 is forked, and m2 is computed, we use join() to wait for the result of m1 before proceeding.
		return Math.min(min2, min1);
	}
	public static void main(String[] args) {
		int[] arr = new int[] {15, 12, 99, 89, 1, -10, 29, -100};
		ExecForkJoin tp = new ExecForkJoin(0, arr.length, arr);
		try (ForkJoinPool efj = new ForkJoinPool()) {
			int res = efj.invoke(tp);
			System.out.println(res);
		}

	}

}
