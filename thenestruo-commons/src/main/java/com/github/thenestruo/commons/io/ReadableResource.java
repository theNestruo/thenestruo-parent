package com.github.thenestruo.commons.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * A readable resource
 */
public interface ReadableResource {

	InputStream getInputStream();

	BufferedInputStream getBufferedInputStream();

	BufferedReader getBufferedReader(Charset charset);

	byte[] readAllBytes() throws IOException;

	long sizeOf();
}
