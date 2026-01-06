package com.github.thenestruo.commons;

public class ByteArrays {

	public static boolean contains(final byte[] array, final byte value) {

		return indexOf(array, value) != -1;
	}

	public static int indexOf(final byte[] array, final byte value) {

		// (sanity check)
		if ((array == null) || (array.length == 0)) {
			return -1;
		}

		for (int i = 0, n = array.length; i < n; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Measures the entropy of a byte array, as entropy count.
	 * The entropy is defined as the number of bytes of the array that are different from the previous byte
	 *
	 * @param array the byte array
	 * @return the entropy of the byte array
	 */
	public static int entropy(final byte[] array) {

		// (sanity check)
		if ((array == null) || (array.length <= 1)) {
			return 0;
		}

		int count = 0;
		for (int i = 0, j = 1, n = array.length; j < n; i++, j++) {
			if (array[i] != array[j]) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Measures the relative entropy of a byte array
	 * as the ratio between the @{link #entropy(byte[]) entropy} count and the array length
	 *
	 * @param array the byte array
	 * @return the entropy of the byte array
	 */
	public static double entropyRatio(final byte[] array) {

		// (sanity check)
		if ((array == null) || (array.length <= 1)) {
			return 0;
		}

		return ((double) entropy(array)) / ((double) (array.length - 1));
	}

	private ByteArrays() {
	}
}
