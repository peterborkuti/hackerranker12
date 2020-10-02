package hr.bp.hu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class Main {
	public static void main(String[] args) {
		}

	/*
	 * Complete the 'pickingNumbers' function below.
	 *
	 * The function is expected to return an INTEGER.
	 * The function accepts INTEGER_ARRAY a as parameter.
	 */

	public static int pickingNumbers(List<Integer> a) {
		final Integer firstElement = a.get(0);
		Map<Integer, Long> diffsCountsBasedOnFirstElement =
				a.stream().collect(groupingBy(e -> firstElement - e, Collectors.counting()));

		List<Integer> diffs = Arrays.asList(diffsCountsBasedOnFirstElement.keySet().toArray(new Integer[0]));
		diffs.sort((x,y) -> Integer.compare(x,y));

		return diffs.stream().map( diff -> {
			long length = diffsCountsBasedOnFirstElement.get(diff);
			if (diffs.contains(diff+1)) {
				length += diffsCountsBasedOnFirstElement.get(diff+1);
			}
			return length;
		}).max(Long::compareTo).get().intValue();
	}

	/*
	 * Complete the 'pickingNumbers' function below.
	 *
	 * The function is expected to return an INTEGER.
	 * The function accepts INTEGER_ARRAY a as parameter.
	 */

	public static int pickingNumbersUgly(List<Integer> a) {
		Map<Integer, List<Integer>> diffsBasedOnFirstElement = new HashMap<>();

		diffsBasedOnFirstElement.put(0, new ArrayList());
		diffsBasedOnFirstElement.get(0).add(a.get(0));

		for (int i = 1; i < a.size(); i++) {
			int diff = a.get(0) - a.get(i);
			if (diffsBasedOnFirstElement.containsKey(diff)) {
				diffsBasedOnFirstElement.get(diff).add(a.get(i));
			}
			else {
				diffsBasedOnFirstElement.put(diff, new ArrayList<>());
				diffsBasedOnFirstElement.get(diff).add(a.get(i));
			}
		}

		int maxLength = 0;
		List<Integer> diffs = Arrays.asList(diffsBasedOnFirstElement.keySet().toArray(new Integer[0]));
		diffs.sort((x,y) -> Integer.compare(x,y));

		for (int i: diffs) {
			int length = diffsBasedOnFirstElement.get(i).size();
			if (diffs.contains(i + 1)) {
				length += diffsBasedOnFirstElement.get(i + 1).size();
			}
			if (length > maxLength) maxLength = length;
		}

		return maxLength;
	}

	/*
	 * Complete the 'pickingNumbers' function below.
	 *
	 * The function is expected to return an INTEGER.
	 * The function accepts INTEGER_ARRAY a as parameter.
	 */

	/* I assumed, that the subarray consists of consecutive
	 * elements of the original array
	 *
	 * HINT: always test your idea on the examples!
	 */

	public static int pickingNumbersWrong(List<Integer> a) {
		int diff = 0;
		int maxDiff = 0;
		int minDiff = 0;
		int length = 1;
		int maxLength = 1;
		for (int i = 1; i < a.size(); i++) {
			diff += a.get(i-1) - a.get(i);
			if (diff > maxDiff) maxDiff = diff;
			if (diff < minDiff) minDiff = diff;

			if (maxDiff - minDiff <= 1) {
				length++;
				if (length > maxLength) maxLength = length;
			}
			else {
				length = 1;
				diff = 0;
				maxDiff = 0;
				minDiff = 0;
			}
		}

		return maxLength;
	}


	// Complete the circularArrayRotation function below.
	static int[] circularArrayRotation(int[] a, int k, int[] queries) {
		final int n = a.length;
		final int kMod = k % n;
		return Arrays.stream(queries).map(i -> ((kMod <= i) ? (i - kMod) : (i - kMod + n))).map(i -> a[i]).toArray();
	}


	// Complete the climbingLeaderboard function below.
	static int[] climbingLeaderboard(int[] scores, int[] alice) {
		List<Integer> distinctScores = Arrays.stream(scores).distinct().boxed().collect(Collectors.toList());

		return
			Arrays.stream(alice)
				.map(a -> Collections.binarySearch(distinctScores, a, (o1, o2) -> Integer.compare(o2, o1)))
				.map(place -> place >= 0 ? ++place : place)
				.map(Math::abs).toArray();
	}

	// Complete the miniMaxSum function below.
	static void miniMaxSum(int[] arr) {
		List<Integer> sorted = Arrays.stream(arr).sorted().boxed().collect(Collectors.toList());
		long min = IntStream.range(0, 4).mapToLong(i -> sorted.get(i)).sum();
		long max = IntStream.range(1, 5).mapToLong(i -> sorted.get(i)).sum();

		System.out.println(min +" " + max);
	}

	// Complete the staircase function below.
	static void staircase(int n) {
		String s = IntStream.range(0,100).mapToObj(i -> " ").collect(Collectors.joining("")) +
				IntStream.range(0,100).mapToObj(i -> "#").collect(Collectors.joining(""));

		IntStream.range(0, n)
				.mapToObj(i -> s.substring(101 -n + i, 101 - n + i + n))
				.forEach(System.out::println);
	}

	static void plusMinus(int[] arr) {
		Map<Integer, Long> counts = Arrays.stream(arr)
				.map(i -> (i > 0) ? 1 : (i < 0) ? -1 : 0)
				.boxed()
				.collect(
					groupingBy(
						Function.identity(), Collectors.counting()
					)
				);

		double n = arr.length;
		Stream.of(1, -1, 0)
				.map(i -> counts.containsKey(i) ? counts.get(i) : 0L)
				.map(i -> String.format("%.6f", i / n))
				.forEach(System.out::println);
	}


	public static int diagonalDifference(List<List<Integer>> arr) {
		int n = arr.size();

		return Math.abs(IntStream.range(0, n).map(i -> arr.get(i).get(i)).sum() -
				IntStream.range(0, n).map(i -> arr.get(i).get(n-i-1)).sum());
	}

	public static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
		return Stream.of(0,1,2)
				.map(i -> score(a.get(i), b.get(i)))
				.reduce(Arrays.asList(0, 0), (s, e) -> Arrays.asList(s.get(0) + e.get(0), s.get(1) + e.get(1)));
	}

	public static List<Integer> score(int a, int b) {
		if (a > b) return Arrays.asList(1, 0);
		if (a < b) return Arrays.asList(0, 1);
		return Arrays.asList(0, 0);
	}

	public static long repeatedString(String s, long n) {
		long numOfAInS = countOfA(s);
		long numOfSInN = 0;
		long remainder = 0;

		if ( n >= s.length()) {
			numOfSInN = n / s.length();
			remainder = n - numOfSInN*s.length();
		}
		else {
			remainder = n;
		}

		long numOfAInRemainder = remainder > 0 ? countOfA(s.substring(0, (int)remainder)) : 0;

		return numOfSInN * numOfAInS + numOfAInRemainder;

	}

	private static long countOfA(String s) {
		return Arrays.stream(s.split("")).filter(c -> "a".equals(c)).count();
	}
}
