package com.github.thenestruo.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.file.PathUtils;
import org.apache.commons.lang3.Validate;

/**
 * A readable file system resource
 */
public class FileSystemResource implements ReadableResource {

	private final Path path;

	/**
	 * Constructor
	 * @param path the path of the file system resource
	 */
	public FileSystemResource(final String path) {
		this(Path.of(Validate.notBlank(path, "The path must not be null nor blank")));
	}

	/**
	 * Constructor
	 * @param path the path
	 */
	public FileSystemResource(final Path path) {
		super();

		this.path = Validate.notNull(path, "The path must not be null");
	}

	@Override
	public InputStream getInputStream() {

		try {
			return Files.newInputStream(this.path);

		} catch (final IOException e) {
			return null;
		}
	}

	@Override
	public long sizeOf() {

		if (this.path == null) {
			return -1;
		}

		try {
			return PathUtils.sizeOf(this.path);

		} catch (final IOException e) {
			return -1;
		}
	}
}
