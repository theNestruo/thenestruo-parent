package com.github.thenestruo.commons.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Statistics of double values
 */
public class DoubleStats {

	/**
	 * @param aStats Statistics of integer values
	 * @param bStats Statistics of integer values
	 * @return Statistics of double values where each value is the ratio
	 *         between the value of aStats and the value of bStats
	 */
	public static DoubleStats ofRatios(final IntStats aStats, final IntStats bStats) {

		final List<Integer> aValues = Objects.requireNonNull(aStats).values();
		final List<Integer> bValues = Objects.requireNonNull(bStats).values();

		if (aValues.size() != bValues.size()) {
			throw new IllegalArgumentException();
		}

		final DoubleStats ratioStats = new DoubleStats();
		for (int i = 0, n = aValues.size(); i < n; i++) {
			final int a = aValues.get(i);
			final int b = bValues.get(i);
			if (b == 0) {
				throw new IllegalArgumentException();
			}
			ratioStats.add(((double) a) / ((double) b));
		}
		return ratioStats;
	}

	/** The double values of this statistics */
	private final List<Double> values = new ArrayList<>();

	/**
	 * @param value the double value to add to the statistics
	 */
	public DoubleStats add(double value) {

		this.values.add(value);
		return this;
	}

	//

	/**
	 * @return the double values of this statistics
	 */
	public List<Double> values() {

		return Collections.unmodifiableList(this.values);
	}

	/**
	 * @return the number of the double values of this statistics
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
	public double sum() {

		double sum = 0;
		for (double value : this.values) {
			sum += value;
		}
		return sum;
	}

	/**
	 * @return the minimum integer value of this statistics
	 */
	public Double min() {

		return this.values
				.stream()
				.min(Comparator.naturalOrder())
				.orElse(null);
	}

	/**
	 * @return the maximum integer value of this statistics
	 */
	public Double max() {

		return this.values
				.stream()
				.max(Comparator.naturalOrder())
				.orElse(null);
	}

	/**
	 * @return the average value of this statistics,
	 *         or {@code null} if there are novalues
	 */
	public Double average() {

		return this.isEmpty()
				? null
				: this.sum() / ((double) this.count());
	}

	/**
	 * @return the median value of this statistics,
	 *         or {@code null} if there are no values
	 */
	public Double median() {

		return this.values
				.stream()
				.sorted()
				.skip(this.count() / 2)
				.findFirst()
				.orElse(null);
	}
}
