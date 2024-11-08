import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NormalBinSearchTest {

	@Test
	void test() {
		int[] init = new int[] {1, 2, 3, 4};
		int index1 = NormalBinSearch.binSearch(1, init);
		assertEquals(index1, 0);
		int index2 = NormalBinSearch.binSearch(2, init);
		assertEquals(index2, 1);
		int index3 = NormalBinSearch.binSearch(3, init);
		assertEquals(index3, 2);
		int index4 = NormalBinSearch.binSearch(4, init);
		assertEquals(index4, 3);
	}
	@Test
	void testNotFound() {
		int[] init = new int[] {1, 2, 3, 4};
		int index1 = NormalBinSearch.binSearch(-1, init);
		assertEquals(index1, -1);
		int index2 = NormalBinSearch.binSearch(10, init);
		assertEquals(index2, -1);
		int index3 = NormalBinSearch.binSearch(5, init);
		assertEquals(index3, -1);
		int index4 = NormalBinSearch.binSearch(6, init);
		assertEquals(index4, -1);
	}
	
	@Test
	void testEmptyArray() {
		int[] init = new int[] {};
		int index1 = NormalBinSearch.binSearch(-1, init);
		assertEquals(index1, -1);
		int index2 = NormalBinSearch.binSearch(10, init);
		assertEquals(index2, -1);
		int index3 = NormalBinSearch.binSearch(5, init);
		assertEquals(index3, -1);
		int index4 = NormalBinSearch.binSearch(6, init);
		assertEquals(index4, -1);
	}
}
