package grid.utils;

import java.util.stream.IntStream;

public record ColumnsUtils() {

	/**
	 * 
	 * 
	 * @param of ->
	 * @return
	 * 
	 * @see #getLeastDivisor(int, int)
	 */
	public static int getLeastDivisor(int of) {
		return getLeastDivisor(of % 2 + 1, of);
	}

	/**
	 * Calculates the smallest possible divisor using a recursive calculation within
	 * an IntStream.
	 * 
	 * @param min
	 * @param of
	 * @return
	 */
	public static int getLeastDivisor(int min, int of) {
		return IntStream.iterate(min, i -> of % ((of % i) + 1) != 0, i -> i + 1).max().orElse(1) + 1;
	}
}
