package com.github.thenestruo.commons;

public class Bools {

	public static boolean requireTrue(final Boolean expression) {

		if ((expression == null) || !expression) {
			throw new IllegalArgumentException();
		}
		return expression;
	}

	public static boolean requireTrue(final Boolean expression, final String message, final Object... args) {

		// (sanity check)
		if (message == null) {
			return requireTrue(expression);
		}

		if ((expression == null) || !expression) {
			throw new IllegalArgumentException(String.format(message, args));
		}
		return expression;
	}

	public static boolean requireFalse(final Boolean expression) {

		if ((expression == null) || expression) {
			throw new IllegalArgumentException();
		}
		return expression;
	}

	public static boolean requireFalse(final Boolean expression, final String message, final Object... args) {

		// (sanity check)
		if (message == null) {
			return requireFalse(expression);
		}

		if ((expression == null) || expression) {
			throw new IllegalArgumentException(String.format(message, args));
		}
		return expression;
	}

	private Bools() {
	}
}
