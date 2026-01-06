package com.github.thenestruo.commons.io;

import java.io.IOException;
import java.io.InputStream;

import com.github.thenestruo.commons.Strings;

/**
 * A readable classpath resource
 */
public class ClassPathResource extends AbstractReadableResource {

	/**
	 * Factory method
	 *
	 * @param path the path of the classpath resource
	 * @throws IOException if the classpath resource does not exists
	 */
	public static ClassPathResource from(final String path) {

		final ClassPathResource instance = new ClassPathResource(path);

		// Checks existence
		try (final InputStream is = instance.getInputStream()) {
			return instance;

		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	private final String path;

	/**
	 * Constructor
	 *
	 * @param path the path of the classpath resource
	 * @throws IOException if the classpath resource does not exists
	 */
	public ClassPathResource(final String path) {
		this.path = Strings.requireNotEmpty(path);
	}

	@Override
	public InputStream getInputStream() {

		final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		return classLoader.getResourceAsStream(this.path);
	}

	@Override
	public long sizeOf() {

		try (InputStream is = this.getInputStream()) {
			final byte[] buffer = new byte[8192];
			long count = 0;
			for (int n = -1; (n = is.read(buffer)) != -1;) {
				count += n;
			}
			return count;

		} catch (final IOException e) {
			return -1L;
		}
	}
}
