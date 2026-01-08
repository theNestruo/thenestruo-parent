package com.github.thenestruo.commons;

import java.util.Locale;

public class Strings {

	public static final String EMPTY = "";

	public static int length(final String string) {

		return string == null ? 0 : string.length();
	}

	public static boolean isEmpty(final String string) {

		return (string == null) || string.isEmpty();
	}

	public static boolean isNotEmpty(final String string) {

		return !isEmpty(string);
	}

	public static String emptyIfNull(final String string) {

		return (string == null) ? EMPTY : string;
	}

	public static String nullIfEmpty(final String string) {

		return isEmpty(string) ? null : string;
	}

	public static String requireNotEmpty(final String string) {

		if (isEmpty(string)) {
			throw new IllegalArgumentException();
		}
		return string;
	}

	public static String requireNotEmpty(final String string, final String message) {

		if (isEmpty(string)) {
			throw new IllegalArgumentException(message);
		}
		return string;
	}

	public static boolean isBlank(final String string) {

		return (string == null) || string.isBlank();
	}

	public static boolean isNotBlank(final String string) {

		return !isBlank(string);
	}

	public static String nullIfBlank(final String string) {

		return isBlank(string) ? null : string;
	}

	public static String requireNotBlank(final String string) {

		if (isBlank(string)) {
			throw new IllegalArgumentException();
		}
		return string;
	}

	public static String requireNotBlank(final String string, final String message) {

		if (isBlank(string)) {
			throw new IllegalArgumentException(message);
		}
		return string;
	}

	public static String trim(final String string) {

		return string == null ? null : string.trim();
	}

	public static String trimToNull(final String string) {

		return nullIfEmpty(trim(string));
	}

	public static String trimToEmpty(final String string) {

		return emptyIfNull(trim(string));
	}

	public static String toLowerCase(final String string) {

		return string == null ? null : string.toLowerCase();
	}

	public static String toLowerCase(final String string, final Locale locale) {

		return string == null ? null : string.toLowerCase(locale);
	}

	public static String toUpperCase(final String string) {

		return string == null ? null : string.toUpperCase();
	}

	public static String toUpperCase(final String string, final Locale locale) {

		return string == null ? null : string.toUpperCase(locale);
	}

	public static boolean startsWith(final String string, final char prefix) {

		return (string != null) && (string.length() >= 1) && (string.charAt(0) == prefix);
	}

	public static boolean startsWith(final String string, final String prefix) {

		return (string != null) && (prefix != null) && string.startsWith(prefix);
	}

	public static boolean startsWithAny(final String string, final String... prefixes) {

		for (final String prefix : prefixes) {
			if (startsWith(string, prefix)) {
				return true;
			}
		}
		return false;
	}

	public static boolean startsWithIgnoreCase(final String string, final String prefix) {

		return (string != null) && (prefix != null)
				&& (string.length() >= prefix.length())
				&& string.substring(0, prefix.length()).equalsIgnoreCase(prefix);
	}

	public static boolean startsWithAnyIgnoreCase(final String string, final String... prefixes) {

		for (final String prefix : prefixes) {
			if (startsWithIgnoreCase(string, prefix)) {
				return true;
			}
		}
		return false;
	}

	public static boolean endsWith(final String string, final char suffix) {

		return (string != null) && (string.length() >= 1) && (string.charAt(string.length() - 1) == suffix);
	}

	public static boolean endsWith(final String string, final String suffix) {

		return (string != null) && (suffix != null) && string.endsWith(suffix);
	}

	public static boolean endsWithAny(final String string, final String... suffixes) {

		for (final String suffix : suffixes) {
			if (endsWith(string, suffix)) {
				return true;
			}
		}
		return false;
	}

	public static boolean endsWithIgnoreCase(final String string, final String suffix) {

		return (string != null) && (suffix != null)
				&& (string.length() >= suffix.length())
				&& string.substring(string.length() - suffix.length()).equalsIgnoreCase(suffix);
	}

	public static boolean endsWithAnyIgnoreCase(final String string, final String... suffixes) {

		for (final String suffix : suffixes) {
			if (endsWithIgnoreCase(string, suffix)) {
				return true;
			}
		}
		return false;
	}

	public static String removeStart(final String string, final char prefix) {

		return startsWith(string, prefix) ? string.substring(1) : string;
	}

	public static String removeStart(final String string, final String prefix) {

		return startsWith(string, prefix) ? string.substring(prefix.length()) : string;
	}

	public static String removeStartIgnoreCase(final String string, final String prefix) {

		return startsWithIgnoreCase(string, prefix) ? string.substring(prefix.length()) : string;
	}

	public static String removeEnd(final String string, final char suffix) {

		return endsWith(string, suffix) ? string.substring(0, string.length() - 1) : string;
	}

	public static String removeEnd(final String string, final String suffix) {

		return endsWith(string, suffix) ? string.substring(0, string.length() - suffix.length()) : string;
	}

	public static String removeEndIgnoreCase(final String string, final String suffix) {

		return endsWithIgnoreCase(string, suffix) ? string.substring(0, string.length() - suffix.length()) : string;
	}

	public static String prependIfMissing(final String string, final char prefix) {

		return (string != null)
				&& !startsWith(string, prefix) ? prefix + string : string;
	}

	public static String prependIfMissing(final String string, final String prefix) {

		return (string != null) && (prefix != null)
				&& !startsWith(string, prefix) ? prefix + string : string;
	}

	public static String prependIfMissingIgnoreCase(final String string, final String prefix) {

		return (string != null) && (prefix != null)
				&& !startsWithIgnoreCase(string, prefix) ? prefix + string : string;
	}

	public static String appendIfMissing(final String string, final char suffix) {

		return (string != null)
				&& !endsWith(string, suffix) ? (string + suffix) : string;
	}

	public static String appendIfMissing(final String string, final String suffix) {

		return (string != null) && (suffix != null)
				&& !endsWith(string, suffix) ? (string + suffix) : string;
	}

	public static String appendIfMissingIgnoreCase(final String string, final String suffix) {

		return (string != null) && (suffix != null)
				&& !endsWithIgnoreCase(string, suffix) ? (string + suffix) : string;
	}

	public static String substringAfter(final String string, final char separator) {

		if (string == null) {
			return null;
		}
		final int index = string.indexOf(separator);
		return index == -1 ? EMPTY : string.substring(index + 1);
	}

	public static String substringAfter(final String string, final String separator) {

		if ((string == null) || isEmpty(separator)) {
			return string;
		}
		final int index = string.indexOf(separator);
		return index == -1 ? EMPTY : string.substring(index + 1);
	}

	public static String substringAfterLast(final String string, final char separator) {

		if (string == null) {
			return null;
		}
		final int index = string.lastIndexOf(separator);
		return index == -1 ? EMPTY : string.substring(index + 1);
	}

	public static String substringAfterLast(final String string, final String separator) {

		if (string == null) {
			return null;
		}
		if (isEmpty(separator)) {
			return EMPTY;
		}
		final int index = string.lastIndexOf(separator);
		return index == -1 ? EMPTY : string.substring(index + 1);
	}

	private Strings() {
	}
}
