package com.github.thenestruo.commons.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Base class for readable resource implementations
 */
public abstract class AbstractReadableResource implements ReadableResource {

	@Override
	public BufferedInputStream getBufferedInputStream() {

		final InputStream is = this.getInputStream();
		return is instanceof final BufferedInputStream bis
				? bis
				: new BufferedInputStream(is);
	}

	@Override
	public BufferedReader getBufferedReader(final Charset charset) {

		final InputStream is = this.getInputStream();
		return is != null
				? new BufferedReader(new InputStreamReader(is, charset))
				: null;
	}

	@Override
	public byte[] readAllBytes() throws IOException {

		try (final BufferedInputStream bis = this.getBufferedInputStream()) {
			return bis.readAllBytes();
		}
	}
}
