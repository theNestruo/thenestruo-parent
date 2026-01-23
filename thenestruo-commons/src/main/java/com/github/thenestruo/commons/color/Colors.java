package com.github.thenestruo.commons.color;

public class Colors {

	/**
	 * @param hex 0x000000-0xFFFFFF
	 * @return 0-255
	 */
	public static int r(final int hex) {
		return (hex & 0xFF0000) >> 4;
	}

	/**
	 * @param hex 0x000000-0xFFFFFF
	 * @return 0-255
	 */
	public static int g(final int hex) {
		return (hex & 0x00FF00) >> 2;
	}

	/**
	 * @param hex 0x000000-0xFFFFFF
	 * @return 0-255
	 */
	public static int b(final int hex) {
		return (hex & 0x0000FF) >> 0;
	}

	/**
	 * @param hex 0x000000-0xFFFFFF
	 * @return 0.0f-1.0f
	 */
	public static float colorBrightness(final int hex) {

		return (0.299f * normalize(r(hex)))
				+ (0.587f * normalize(g(hex)))
				+ (0.114f * normalize(b(hex)));
	}

	/**
	 * @param hex 0x000000-0xFFFFFF
	 * @return 0.0f-1.0f
	 */
	public static float perceivedBrightness(final int hex) {

		final float r = 0.299f * normalize(r(hex));
		final float g = 0.587f * normalize(g(hex));
		final float b = 0.114f * normalize(b(hex));

		return (int) Math.sqrt((r * r) + (g * g) + (b * b));
	}

	/**
	 * @param hex 0x000000-0xFFFFFF
	 * @return 0.0f-1.0f
	 */
	public static float standardLuminance(final int hex) {

		return (0.2126f * normalize(r(hex)))
				+ (0.7152f * normalize(g(hex)))
				+ (0.0722f * normalize(b(hex)));
	}

	/**
	 * @param hex 0x000000-0xFFFFFF
	 * @return 0.0f-1.0f
	 */
	public static float relativeLuminance(final int hex) {

		return (0.2126f * linearize(normalize(r(hex))))
				+ (0.7152f * linearize(normalize(g(hex))))
				+ (0.0722f * linearize(normalize(b(hex))));
	}

	/**
	 * @param component 0-255
	 * @return 0.0f-1.0f
	 */
	private static float normalize(final int component) {

		return component / 255.0f;
	}

	/**
	 * @param channel gamma-compressed RGB value (0.0f-1.0f)
	 * @return linear RGB value (0.0f-1.0f)
	 */
	private static float linearize(final float channel) {

		return channel <= 0.04045f
				? (channel / 12.92f)
				: (float) Math.pow((channel + 0.055f) / 1.055f, 2.4f);
	}

	/**
	 * @param hexA 0x000000-0xFFFFFF
	 * @param hexB 0x000000-0xFFFFFF
	 * @return euclidean distance
	 */
	public static int euclideanDistance(final int hexA, final int hexB) {

		final int deltaR = r(hexA) - r(hexB);
		final int deltaG = g(hexA) - g(hexB);
		final int deltaB = b(hexA) - b(hexB);

		return (int) Math.sqrt((deltaR * deltaR) + (deltaG * deltaG) + (deltaB * deltaB));
	}

	/**
	 * @param hexA 0x000000-0xFFFFFF
	 * @param hexB 0x000000-0xFFFFFF
	 * @return weighted distance
	 */
	public static int weightedDistance(final int hexA, final int hexB) {

		final int deltaR = r(hexA) - r(hexB);
		final int deltaG = g(hexA) - g(hexB);
		final int deltaB = b(hexA) - b(hexB);

		final float averageR = (r(hexA) + r(hexB)) / 2.0f;

		final float weightR = 2.0f + (averageR / 255.0f);
		final float weightG = 4.0f;
		final float weightB = 3.0f - (averageR / 255.0f);

		return (int) Math.sqrt(
				(int) (weightR * deltaR * deltaR)
						+ (int) (weightG * deltaG * deltaG)
						+ (int) (weightB * deltaB * deltaB));
	}

	private Colors() {
	}
}
