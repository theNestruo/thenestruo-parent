package com.github.thenestruo.commons;

public class Strings {

	public static final String EMPTY = "";

	public static int length(final String string) {

		return string == null ? 0 : string.length();
	}

	public static boolean isEmpty(final String string) {

		return (string == null) || string.isEmpty();
	}

	public static String requireNotEmpty(final String string) {

		if (isEmpty(string)) {
			throw new NullPointerException();
		}
		return string;
	}

	public static String requireNotEmpty(final String string, final String message) {

		if (isEmpty(string)) {
			throw new NullPointerException(message);
		}
		return string;
	}

	public static boolean isBlank(final String string) {

		// (sanity check)
		if (string == null) {
			return true;
		}

		for (int i = 0, n = string.length(); i < n; i++) {
			if (!Character.isWhitespace(string.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static String requireNotBlank(final String string) {

		if (isBlank(string)) {
			throw new NullPointerException();
		}
		return string;
	}

	public static String requireNotBlank(final String string, final String message) {

		if (isBlank(string)) {
			throw new NullPointerException(message);
		}
		return string;
	}

	public static boolean startsWith(final String string, final String prefix) {

		if (string == null) {
			return false;
		}
		if (prefix == null) {
			return true;
		}
		return string.startsWith(prefix);
	}

	public static boolean endsWith(final String string, final String suffix) {

		if (string == null) {
			return false;
		}
		if (suffix == null) {
			return true;
		}
		return string.endsWith(suffix);
	}

	public static String removeEnd(final String string, final String suffix) {

		if ((string == null) || (suffix == null)) {
			return string;
		}
		final int beginIndex = string.length() - suffix.length();
		return string.substring(beginIndex).equalsIgnoreCase(suffix)
				? string.substring(0, beginIndex)
				: string;
	}

	public static String prependIfMissing(final String string, final String prefix) {

		if (string == null) {
			return null;
		}
		if (prefix == null) {
			return string;
		}
		return startsWith(string, prefix) ? string : (string + prefix);
	}

	public static String appendIfMissing(final String string, final String sufix) {

		if (string == null) {
			return null;
		}
		if (sufix == null) {
			return string;
		}
		return endsWith(string, sufix) ? string : (string + sufix);
	}

	public static String substringAfter(final String string, final char separator) {

		if (string == null) {
			return null;
		}
		final int index = string.indexOf(separator);
		if (index == -1) {
			return EMPTY;
		}
		return string.substring(index + 1);
	}

	public static String substringAfter(final String string, final String separator) {

		if (string == null) {
			return null;
		}
		if (separator == null) {
			return EMPTY;
		}
		final int index = string.indexOf(separator);
		if (index == -1) {
			return EMPTY;
		}
		return string.substring(index + 1);
	}

	public static String substringAfterLast(final String string, final char separator) {

		if (string == null) {
			return null;
		}
		final int index = string.lastIndexOf(separator);
		if (index == -1) {
			return EMPTY;
		}
		return string.substring(index + 1);
	}

	public static String substringAfterLast(final String string, final String separator) {

		if (string == null) {
			return null;
		}
		if (separator == null) {
			return EMPTY;
		}
		final int index = string.lastIndexOf(separator);
		if (index == -1) {
			return EMPTY;
		}
		return string.substring(index + 1);
	}

	private Strings() {
	}
}
