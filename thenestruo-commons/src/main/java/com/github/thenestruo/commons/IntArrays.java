package com.github.thenestruo.commons;

import java.util.Collection;

public class IntArrays {

	public static int[] asIntArray(final Collection<Integer> col) {

		// (santiy check)
		if ((col == null) || (col.size() == 0)) {
			return new int[] {};
		}
		return col.stream().mapToInt(Integer::intValue).toArray();
	}

	public static boolean contains(final int[] array, final int value) {

		return indexOf(array, value) != -1;
	}

	public static int indexOf(final int[] array, final int value) {

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

	private IntArrays() {
	}
}
