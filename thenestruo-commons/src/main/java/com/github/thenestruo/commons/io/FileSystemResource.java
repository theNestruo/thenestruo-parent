package com.github.thenestruo.commons.io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import com.github.thenestruo.commons.Strings;

/**
 * A readable file system resource
 */
public class FileSystemResource extends AbstractReadableResource {

	private final Path path;

	/**
	 * Constructor
	 *
	 * @param path the path of the file system resource
	 */
	public FileSystemResource(final String path) {
		this(Path.of(Strings.requireNotEmpty(path)));
	}

	/**
	 * Constructor
	 *
	 * @param path the path
	 */
	public FileSystemResource(final Path path) {
		this.path = Objects.requireNonNull(path);
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
	public byte[] readAllBytes() throws IOException {

		return Files.readAllBytes(this.path);
	}

	@Override
	public long sizeOf() {

		try {
			return Files.size(this.path);

		} catch (final IOException e) {
			return -1L;
		}
	}
}
