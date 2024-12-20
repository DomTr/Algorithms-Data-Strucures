import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class QuickSelectTest {
	@Test
	public void test1() {
		int[] init = new int[] {1, 2, 3, 4, 5};
		int n = init.length;
		int firstSmallest = Quickselect.kthSmallest(n, 0, init);
		assertEquals(1, firstSmallest);
		int secondSmallest = Quickselect.kthSmallest(n, 1, init);
		assertEquals(2, secondSmallest);
		int thirdSmallest = Quickselect.kthSmallest(n, 2, init);
		assertEquals(3, thirdSmallest);
		int fourthSmallest = Quickselect.kthSmallest(n, 3, init);
		assertEquals(4, fourthSmallest);
		int fifthSmallest = Quickselect.kthSmallest(n, 4, init);
		assertEquals(5, fifthSmallest);
	}
	@Test
	public void test2() {
		int[] init = new int[] {1, 1, 1, 2, 2};
		int n = init.length;
		int firstSmallest = Quickselect.kthSmallest(n, 0, init);
		assertEquals(1, firstSmallest);
		int secondSmallest = Quickselect.kthSmallest(n, 1, init);
		assertEquals(1, secondSmallest);
		int thirdSmallest = Quickselect.kthSmallest(n, 2, init);
		assertEquals(1, thirdSmallest);
		int fourthSmallest = Quickselect.kthSmallest(n, 3, init);
		assertEquals(2, fourthSmallest);
		int fifthSmallest = Quickselect.kthSmallest(n, 4, init);
		assertEquals(2, fifthSmallest);
	}
	
	// Test 3: Test with Array of Even Numbers
	@Test
	public void test3() {
	    int[] init = new int[] {2, 4, 6, 8, 10};
	    int n = init.length;
	    int firstSmallest = Quickselect.kthSmallest(n, 0, init);
	    assertEquals(2, firstSmallest);
	    int secondSmallest = Quickselect.kthSmallest(n, 1, init);
	    assertEquals(4, secondSmallest);
	    int thirdSmallest = Quickselect.kthSmallest(n, 2, init);
	    assertEquals(6, thirdSmallest);
	    int fourthSmallest = Quickselect.kthSmallest(n, 3, init);
	    assertEquals(8, fourthSmallest);
	    int fifthSmallest = Quickselect.kthSmallest(n, 4, init);
	    assertEquals(10, fifthSmallest);
	}
	
	// Test 4: Test with Array of Negative Numbers
	@Test
	public void test4() {
	    int[] init = new int[] {-1, -3, -5, -7, -9};
	    int n = init.length;
	    int firstSmallest = Quickselect.kthSmallest(n, 0, init);
	    assertEquals(-9, firstSmallest);
	    int secondSmallest = Quickselect.kthSmallest(n, 1, init);
	    assertEquals(-7, secondSmallest);
	    int thirdSmallest = Quickselect.kthSmallest(n, 2, init);
	    assertEquals(-5, thirdSmallest);
	    int fourthSmallest = Quickselect.kthSmallest(n, 3, init);
	    assertEquals(-3, fourthSmallest);
	    int fifthSmallest = Quickselect.kthSmallest(n, 4, init);
	    assertEquals(-1, fifthSmallest);
	}
	
	// Test 5: Test containing duplicates
	@Test
	public void test5() {
	    int[] init = new int[] {3, 1, 4, 4, 2};
	    int n = init.length;
	    int firstSmallest = Quickselect.kthSmallest(n, 0, init);
	    assertEquals(1, firstSmallest);
	    int secondSmallest = Quickselect.kthSmallest(n, 1, init);
	    assertEquals(2, secondSmallest);
	    int thirdSmallest = Quickselect.kthSmallest(n, 2, init);
	    assertEquals(3, thirdSmallest);
	    int fourthSmallest = Quickselect.kthSmallest(n, 3, init);
	    assertEquals(4, fourthSmallest);
	    int fifthSmallest = Quickselect.kthSmallest(n, 4, init);
	    assertEquals(4, fifthSmallest);
	}
	// Test 6: Test containing only one element
	@Test
	public void test6() {
	    int[] init = new int[] {42};
	    int n = init.length;
	    int firstSmallest = Quickselect.kthSmallest(n, 0, init);
	    assertEquals(42, firstSmallest);
	}
	// Test 7: Test containing all identical elements
	@Test
	public void test7() {
	    int[] init = new int[] {7, 7, 7, 7, 7};
	    int n = init.length;
	    int firstSmallest = Quickselect.kthSmallest(n, 0, init);
	    assertEquals(7, firstSmallest);
	    int secondSmallest = Quickselect.kthSmallest(n, 1, init);
	    assertEquals(7, secondSmallest);
	    int thirdSmallest = Quickselect.kthSmallest(n, 2, init);
	    assertEquals(7, thirdSmallest);
	    int fourthSmallest = Quickselect.kthSmallest(n, 3, init);
	    assertEquals(7, fourthSmallest);
	    int fifthSmallest = Quickselect.kthSmallest(n, 4, init);
	    assertEquals(7, fifthSmallest);
	}
	// Test 8: Test with large array
	@Test
	public void test8() {
	    int[] init = new int[] {100, 45, 23, 65, 89, 11, 12, 50, 38, 77};
	    int n = init.length;
	    int firstSmallest = Quickselect.kthSmallest(n, 0, init);
	    assertEquals(11, firstSmallest);
	    int secondSmallest = Quickselect.kthSmallest(n, 1, init);
	    assertEquals(12, secondSmallest);
	    int thirdSmallest = Quickselect.kthSmallest(n, 2, init);
	    assertEquals(23, thirdSmallest);
	    int fourthSmallest = Quickselect.kthSmallest(n, 3, init);
	    assertEquals(38, fourthSmallest);
	    int fifthSmallest = Quickselect.kthSmallest(n, 4, init);
	    assertEquals(45, fifthSmallest);
	    int sixthSmallest = Quickselect.kthSmallest(n, 5, init);
	    assertEquals(50, sixthSmallest);
	    int seventhSmallest = Quickselect.kthSmallest(n, 6, init);
	    assertEquals(65, seventhSmallest);
	    int eighthSmallest = Quickselect.kthSmallest(n, 7, init);
	    assertEquals(77, eighthSmallest);
	    int ninthSmallest = Quickselect.kthSmallest(n, 8, init);
	    assertEquals(89, ninthSmallest);
	    int tenthSmallest = Quickselect.kthSmallest(n, 9, init);
	    assertEquals(100, tenthSmallest);
	}
	// Test 9: Tests with empty array
	@Test(expected = IllegalArgumentException.class)
	public void test9() {
	    int[] init = new int[] {};
	    int n = init.length;
	    Quickselect.kthSmallest(n, 0, init);
	}


}
