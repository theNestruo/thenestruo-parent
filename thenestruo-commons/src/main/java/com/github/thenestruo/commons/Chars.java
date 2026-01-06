package com.github.thenestruo.commons;

public class Chars {

	public static boolean isAsciiPrintable(final char c) {
		return (c >= 32) && (c < 127);
	}

	private Chars() {
	}
}
