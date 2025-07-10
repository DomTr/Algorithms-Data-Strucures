package divideAndConquer;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MaxSubSum extends RecursiveTask<Integer> {
	private static final long serialVersionUID = 1L;
	int l, h;
	int[] arr;

	public MaxSubSum(int l, int h, int[] arr) {
		this.l = l;
		this.h = h;
		this.arr = arr;
	}

	public static void main(String[] args) {
		int[] arr = new int[] {15, 12, 99, 89, 1, -10, 29, -100};
		MaxSubSum tp = new MaxSubSum(0, arr.length, arr);

	}

	@Override
	protected Integer compute() {
		if (h - l <= 2) {
			if (h - l == 1) {
				return arr[l] >= 0 ? arr[l] : 0;
			} else if (arr[l] >= 0 && arr[l+1] >= 0) {
				return arr[l] + arr[l+1];
			} else if (arr[l] >= 0 && arr[l+1] < 0) {
				return arr[l];
			} else if (arr[l] < 0 && arr[l+1] >= 0) {
				return arr[l+1];
			} else
				return 0;
		}
		int mid = l + (h - l) / 2;
		MaxSubSum m1 = new MaxSubSum(l, mid, arr);
		MaxSubSum m2 = new MaxSubSum(mid, h, arr);
		m1.fork();
		int sum2 = m2.compute();
		int sum1 = m1.join();
		if (sum2 >= 0 && sum1 >= 0) {
			return sum2 + sum1;
		} else if (sum2 >= 0 && sum1 < 0) {
			return sum2;
		} else if (sum2 < 0 && sum1 >= 0) {
			return sum1;
		} else
			return 0;
	}

}
