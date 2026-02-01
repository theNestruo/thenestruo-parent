package com.github.thenestruo.commons.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Statistics of integer values
 */
public class IntStats {

	/**
	 * @param aStats Statistics of integer values
	 * @param bStats Statistics of integer values
	 * @return Statistics of integer values where each value is the difference
	 *         between the value of aStats and the value of bStats
	 */
	public static IntStats ofDifferences(final IntStats aStats, final IntStats bStats) {

		final List<Integer> aValues = Objects.requireNonNull(aStats).values();
		final List<Integer> bValues = Objects.requireNonNull(bStats).values();

		if (aValues.size() != bValues.size()) {
			throw new IllegalArgumentException();
		}

		final IntStats differenceStats = new IntStats();
		for (int i = 0, n = aValues.size(); i < n; i++) {
			final int a = aValues.get(i);
			final int b = bValues.get(i);
			differenceStats.add(a - b);
		}
		return differenceStats;
	}

	/** The integer values of this statistics */
	private final List<Integer> values = new ArrayList<>();

	/**
	 * @param value the integer value to add to the statistics
	 */
	public IntStats add(int value) {

		this.values.add(value);
		return this;
	}

	//

	/**
	 * @return the integer values of this statistics
	 */
	public List<Integer> values() {

		return Collections.unmodifiableList(this.values);
	}

	/**
	 * @return the number of the integer values of this statistics
	 */
	public int count() {

		return this.values.size();
	}

	/**
	 * @return {@code true} if the statistics have no value
	 */
	public boolean isEmpty() {

		return this.values.isEmpty();
	}

	//

	/**
	 * @return the sum of the integer values of this statistics
	 */
	public int sum() {

		int sum = 0;
		for (int value : this.values) {
			sum += value;
		}
		return sum;
	}

	/**
	 * @return the minimum integer value of this statistics
	 */
	public Integer min() {

		return this.values
				.stream()
				.min(Comparator.naturalOrder())
				.orElse(null);
	}

	/**
	 * @return the maximum integer value of this statistics
	 */
	public Integer max() {

		return this.values
				.stream()
				.max(Comparator.naturalOrder())
				.orElse(null);
	}

	/**
	 * @return the average value of this statistics,
	 *         or {@code null} if there are novalues
	 */
	public Float average() {

		return this.isEmpty()
				? null
				: ((float) this.sum()) / ((float) this.count());
	}

	/**
	 * @return the median value of this statistics,
	 *         or {@code null} if there are no values
	 */
	public Integer median() {

		return this.values
				.stream()
				.sorted()
				.skip(this.count() / 2)
				.findFirst()
				.orElse(null);
	}
}
