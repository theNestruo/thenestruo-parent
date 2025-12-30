package com.github.thenestruo.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * byte[]-related utilities
 */
public class ByteArrayUtils {

	/**
	 * Measures the entropy of a byte array, as entropy count.
	 * The entropy is defined as the number of bytes of the array
	 * that are different from the previous byte
	 * @param array the byte array
	 * @return the entropy of the byte array
	 */
	public static int entropy(final byte[] array) {

		// (sanity check)
		final int n = array == null ? 0 : array.length;
		if (n <= 1) {
			return 0;
		}

		int count = 0;
		for (int i = 0, j = 1; j < n; i++, j++) {
			if (array[i] != array[j]) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Measures the entropy of a byte array, as the ratio between
	 * the @{link #entropy(byte[]) entropy} count and the array length
	 * @param array the byte array
	 * @return the entropy of the byte array
	 */
	public static double entropyRatio(final byte[] array) {

		// (sanity check)
		if (ArrayUtils.getLength(array) <= 1) {
			return 0;
		}

		return ((double) entropy(array)) / ((double) (array.length - 1));
	}

	/**
	 * Private default constructor
	 */
	private ByteArrayUtils() {
		super();
	}
}
