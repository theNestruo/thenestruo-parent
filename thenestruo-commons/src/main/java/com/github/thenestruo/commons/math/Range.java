package com.github.thenestruo.commons.math;

import java.util.Objects;

public class Range<N extends Number> {

	private final N minimum;

	private final N maximum;

	public Range(final N minimum, final N maximum) {
		this.minimum = Objects.requireNonNull(minimum);
		this.maximum = Objects.requireNonNull(maximum);
	}

	/**
	 * @param value to check
	 * @return {@code true} if the value is contained in this range, {@code false} otherwise
	 */
	@SuppressWarnings("unchecked")
	public boolean contains(final N value) {

		if (value == null) {
			return false;
		}

		return (((Comparable<N>) this.minimum).compareTo(value) <= 0)
				&& (((Comparable<N>) this.maximum).compareTo(value) >= 0);
	}

	/**
	 * @param other range to check
	 * @return {@code true} if the other range is contained in this range, {@code false} otherwise
	 */
	public boolean contains(final Range<N> other) {

		if (other == null) {
			return false;
		}

		return this.contains(other.minimum) && this.contains(other.maximum);
	}

	/**
	 * @param other range to check
	 * @return {@code true} if the other range ends before this range, {@code false} otherwise
	 */
	@SuppressWarnings("unchecked")
	public boolean isBefore(final Range<N> other) {

		if (other == null) {
			return false;
		}

		return ((Comparable<N>) this.maximum).compareTo(other.minimum) < 0;

	}

	/**
	 * @param other range to check
	 * @return {@code true} if both ranges overlap, {@code false} otherwise
	 */
	public boolean isOverlappedBy(final Range<N> other) {

		if (other == null) {
			return false;
		}

		return other.contains(this.minimum) || other.contains(this.maximum) || this.contains(other.minimum);
	}

	public N getMinimum() {
		return this.minimum;
	}

	public N getMaximum() {
		return this.maximum;
	}
}
