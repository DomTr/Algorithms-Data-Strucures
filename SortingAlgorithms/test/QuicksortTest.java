import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Random;


import org.junit.jupiter.api.Test;

class QuicksortTest {

	@Test
	void test1() {
		int[] init = new int[] {1, 2, 3, 4, 5};
		int[] array = Arrays.copyOf(init, init.length);
		Quicksort.quicksort(array, array.length);
		assertArrayEquals(init, array);
	}
	
	@Test
	void test2() {
		int[] init = new int[] {5, 2, 2, 2, 5};
		int[] array = Arrays.copyOf(init, init.length);
		Quicksort.quicksort(array, array.length);
		Arrays.sort(init);
		assertArrayEquals(array, init);
	}
	
	@Test
	void test3() {
		int[] init = new int[] {5, 2, 5, 3, 3, 10, 10};
		int[] array = Arrays.copyOf(init, init.length);
		Quicksort.quicksort(array, array.length);
		Arrays.sort(init);
		assertArrayEquals(array, init);
	}
	@Test
	void test4() {
		int[] init = new int[] {10, 10, 5, 3, 3, 10, 10, -1, -1};
		int[] array = Arrays.copyOf(init, init.length);
		Quicksort.quicksort(array, array.length);
		Arrays.sort(init);
		assertArrayEquals(array, init);
	}
	@Test
	void test5() {
		int[] init = new int[] {10, 10,10,10,10,-1};
		int[] array = Arrays.copyOf(init, init.length);
		Quicksort.quicksort(array, array.length);
		Arrays.sort(init);
		assertArrayEquals(array, init);
	}
	@Test
	void test6() {
		int[] init = new int[] {10, 8,9,7,5,6, 3, 4, 1, 2};
		int[] array = Arrays.copyOf(init, init.length);
		Quicksort.quicksort(array, array.length);
		Arrays.sort(init);
		assertArrayEquals(array, init);
	}
	@Test
	void test7() {
		int[] init = new int[] {102, -1, -1, -1, -1, -211, -200};
		int[] array = Arrays.copyOf(init, init.length);
		Quicksort.quicksort(array, array.length);
		Arrays.sort(init);
		assertArrayEquals(array, init);
	}
	@Test
	void test8() {
		int[] init = new int[1000];
		for (int i = 0; i < 1000; i++) {
			if (i % 2 == 0) {
				init[i] = -i;
			}
			else {
				init[i] = i;
			}
		}
 		int[] array = Arrays.copyOf(init, init.length);
		Quicksort.quicksort(array, array.length);
		Arrays.sort(init);
		assertArrayEquals(array, init);
	}
	// Maybe not the best idea to write tests with Random because if it doesn't work, you may forget how to replicate it. If there will be some test case discovered, I will save it.
	@Test
	void test9() {
		Random rand = new Random();
		int[] init = new int[10000];
		for (int i = 0; i < 10000; i++) {
			init[i] = rand.nextInt();
		}
 		int[] array = Arrays.copyOf(init, init.length);
		Quicksort.quicksort(array, array.length);
		Arrays.sort(init);
		assertArrayEquals(array, init);
	}
	@Test
	void test10() {
		int[] init = new int[10000];
		for (int i = 0; i < 10000; i++) {
			init[i] = i % 5;
		}
 		int[] array = Arrays.copyOf(init, init.length);
		Quicksort.quicksort(array, array.length);
		Arrays.sort(init);
		assertArrayEquals(array, init);
	}
}
