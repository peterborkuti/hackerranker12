package hr.bp.hu;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class MainTest {
	@Test
	void pickingNumbers() {
		assertEquals(5, Main.pickingNumbers(Arrays.asList(0,0,0,0,0)));
		assertEquals(5, Main.pickingNumbers(Arrays.asList(10,10,10,10,10)));
		assertEquals(5, Main.pickingNumbers(Arrays.asList(0,1,0,1,0)));
		assertEquals(5, Main.pickingNumbers(Arrays.asList(0,-1,-1,0,-1)));

		assertEquals(3, Main.pickingNumbers(Arrays.asList(10,11,9,9,8)));
		assertEquals(3, Main.pickingNumbers(Arrays.asList(4, 6, 5, 3, 3, 1)));
	}

	@Test
	void climbingLeaderBoard() {
		int[] board = new int[]{100,100,50,40,40,20,10};
		int[] alice = new int[]{5, 25, 50, 120};
		// 1    2   3   4   5
		// 100, 50, 40, 20, 10
		assertArrayEquals(new int[]{2}, Main.climbingLeaderboard(board, new int[]{50}));
		assertArrayEquals(new int[]{6,4,2,1}, Main.climbingLeaderboard(board, alice));
	}

	@Test
	void circularArrayRotation() {
		assertArrayEquals(new int[]{2,3}, Main.circularArrayRotation(new int[]{1,2,3,4,5}, 0, new int[]{1,2}));
		assertArrayEquals(new int[]{1,2}, Main.circularArrayRotation(new int[]{1,2,3,4,5}, 1, new int[]{1,2}));
		assertArrayEquals(new int[]{4,5}, Main.circularArrayRotation(new int[]{1,2,3,4,5}, 3, new int[]{1,2}));
		assertArrayEquals(new int[]{2,3}, Main.circularArrayRotation(new int[]{1,2,3,4,5}, 5, new int[]{1,2}));
		assertArrayEquals(new int[]{1,2}, Main.circularArrayRotation(new int[]{1,2,3,4,5}, 6, new int[]{1,2}));

	}

	@Test
	void compareTriplets() {
		assertIterableEquals(Arrays.asList(1,1), Main.compareTriplets(Arrays.asList(5,6,7), Arrays.asList(3,6,10)));
	}

	@Test
	void repeatedString_simpleCases() {
		assertEquals(0, Main.repeatedString("b", 1));
		assertEquals(1, Main.repeatedString("a", 1));
		assertEquals(1, Main.repeatedString("aa", 1));
		assertEquals(2, Main.repeatedString("aa", 2));
		assertEquals(3, Main.repeatedString("aa", 3));
		assertEquals(2, Main.repeatedString("ab", 3));
		assertEquals(2, Main.repeatedString("abac", 3));
		assertEquals(4, Main.repeatedString("abac", 8));
		assertEquals(5, Main.repeatedString("abac", 9));
		assertEquals(8, Main.repeatedString("aab", 11));
		assertEquals(5, Main.repeatedString("abac", 10));
		assertEquals(10000000, Main.repeatedString("a", 10000000));
	}

	@Test
	void hackerRank1() {
		assertEquals(4, Main.repeatedString("abcac", 10));
	}

	@Test
	void hackerRank2() {
		assertEquals(7, Main.repeatedString("aba", 10));
	}
}