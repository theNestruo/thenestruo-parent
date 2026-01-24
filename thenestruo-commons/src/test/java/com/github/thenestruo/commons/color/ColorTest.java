package com.github.thenestruo.commons.color;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ColorTest {

	private static final Color BLACK_HEX = Color.hex(0x000000);

	private static final Color BLACK_RGB = Color.rgb(0, 0, 0);

	@Test
	void testBlackRgbIsBlackHex() {
		Assertions.assertEquals(BLACK_RGB, BLACK_HEX);
	}

	@Test
	void testBlackBrightness() {
		Assertions.assertEquals(0.0f, BLACK_HEX.brightness());
	}

	@Test
	void testBlackPerceivedBrightness() {
		Assertions.assertEquals(0.0f, BLACK_HEX.perceivedBrightness());
	}

	@Test
	void testBlackPerceivedLuminance() {
		Assertions.assertEquals(0.0f, BLACK_HEX.relativeLuminance());
	}

	private static final Color WHITE_HEX = Color.hex(0xFFFFFF);

	private static final Color WHITE_RGB = Color.rgb(255, 255, 255);

	@Test
	void testWhiteRgbIsWhiteHex() {
		Assertions.assertEquals(WHITE_RGB, WHITE_HEX);
	}

	@Test
	void testWhiteBrightness() {
		Assertions.assertEquals(1.0f, WHITE_HEX.brightness());
	}

	@Test
	void testWhitePerceivedBrightness() {
		Assertions.assertEquals(1.0f, WHITE_HEX.perceivedBrightness());
	}

	@Test
	void testWhietPerceivedLuminance() {
		Assertions.assertEquals(1.0f, WHITE_HEX.relativeLuminance());
	}
}
