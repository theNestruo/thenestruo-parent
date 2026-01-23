package com.github.thenestruo.commons;

import java.util.Collection;
import java.util.Objects;

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

	public static int min(final int[] array) {

		if (Objects.requireNonNull(array).length == 0) {
			throw new IllegalArgumentException();
		}

		int min = array[0];
		for (int i = 1, n = array.length; i < n; i++) {
			if (array[i] < min) {
				min = array[i];
			}
		}
		return min;
	}

	public static int indexOfMin(final int[] array) {

		if ((array == null) || (array.length == 0)) {
			return -1;
		}

		int index = 0;
		int min = array[0];
		for (int i = 1, n = array.length; i < n; i++) {
			if (array[i] < min) {
				index = i;
				min = array[i];
			}
		}
		return index;
	}

	public static int max(final int[] array) {

		if (Objects.requireNonNull(array).length == 0) {
			throw new IllegalArgumentException();
		}

		int max = array[0];
		for (int i = 1, n = array.length; i < n; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}

	public static int indexOfMax(final int[] array) {

		if ((array == null) || (array.length == 0)) {
			return -1;
		}

		int index = 0;
		int max = array[0];
		for (int i = 1, n = array.length; i < n; i++) {
			if (array[i] > max) {
				index = i;
				max = array[i];
			}
		}
		return index;
	}

	private IntArrays() {
	}
}
