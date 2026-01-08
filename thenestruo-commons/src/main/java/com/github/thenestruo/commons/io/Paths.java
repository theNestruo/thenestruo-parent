package com.github.thenestruo.commons.io;

import java.nio.file.Path;

import com.github.thenestruo.commons.Strings;

public class Paths {

	/**
	 * Appends a suffix to a path (such as: an extension)
	 *
	 * @param path
	 * @param suffix to be appended (e.g.: {@code ".bak"})
	 */
	public static Path append(final Path path, final String suffix) {

		// (sanity check)
		if ((path == null) || ((suffix == null) || suffix.isEmpty())) {
			return path;
		}

		return path.resolveSibling(Strings.toStringOrEmpty(path.getFileName()) + suffix);
	}

	/**
	 * Checks if a path ends with a suffix (such as: an extension), case insensitive
	 *
	 * @param path
	 * @param suffix to be removed (e.g.: {@code ".bak"})
	 */
	public static boolean endsWith(final Path path, final String suffix) {

		// (sanity check)
		if (path == null) {
			return false;
		}
		if ((suffix == null) || suffix.isEmpty()) {
			return true;
		}

		return Strings.endsWith(Strings.toString(path.getFileName()), suffix);
	}

	/**
	 * Removes the end of a path (such as: an extension), case insensitive
	 *
	 * @param path
	 * @param suffix to be removed (e.g.: {@code ".bak"})
	 */
	public static Path removeEnd(final Path path, final String suffix) {

		// (sanity check)
		if ((path == null) || ((suffix == null) || suffix.isEmpty())) {
			return path;
		}

		return path.resolveSibling(Strings.removeEnd(Strings.toStringOrEmpty(path.getFileName()), suffix));
	}

	private Paths() {
	}
}
