package com.github.thenestruo.commons.color;

import java.util.Comparator;
import java.util.Objects;

public class Color {

	/** Comparator using {@link #brightness() color brightness} */
	public static final Comparator<? extends Color> brightnessComparator = Comparator
			.comparingDouble(Color::brightness);

	/** Comparator using {@link #perceivedBrightness() perceived brightness} */
	public static final Comparator<? extends Color> perceivedBrightnessComparator = Comparator
			.comparingDouble(Color::perceivedBrightness);

	/** Comparator using {@link #relativeLuminance() relative Luminance (WCAG 2.0 Formula)} */
	public static final Comparator<? extends Color> relativeLuminanceComparator = Comparator
			.comparingDouble(Color::relativeLuminance);

	public static Color copyOf(final Color that) {

		if (that == null) {
			return null;
		}
		if (that.getClass() == Color.class) {
			return that;
		}
		return new Color(that.hex);
	}

	/**
	 * @param hex the color hex value (0x000000-0xFFFFFF)
	 * @return Color instance
	 */
	public static Color hex(final int hex) {
		return new Color(hex);
	}

	/**
	 * @param r the red byte (0x00-0xFF)
	 * @param g the green byte (0x00-0xFF)
	 * @param b the blue byte (0x00-0xFF)
	 * @return Color instance
	 */
	public static Color rgb(final int r, final int g, final int b) {
		return new Color((r << 16) + (g << 8) + b);
	}

	//

	/** The color hex value (0x000000-0xFFFFFF) */
	private final int hex;

	/**
	 * Constructor
	 *
	 * @param hex the color hex value (0x000000-0xFFFFFF)
	 */
	protected Color(final int hex) {
		this.hex = hex;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.hex);
	}

	@Override
	public boolean equals(final Object obj) {

		if (obj == this) {
			return true;
		}
		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}
		final Color that = (Color) obj;
		return this.hex == that.hex;
	}

	@Override
	public String toString() {
		return String.format("%06X", this.hex);
	}

	//

	public boolean isSameColor(final Color that) {
		if (that == null) {
			return false;
		}
		return Objects.equals(this.hex, that.hex);
	}

	//

	/**
	 * @return the color hex value (0x000000-0xFFFFFF)
	 */
	public int getHex() {
		return this.hex;
	}

	/**
	 * @return the red byte (0x00-0xFF)
	 */
	public int r() {
		return (this.hex & 0xFF0000) >> 16;
	}

	/**
	 * @return the green byte (0x00-0xFF)
	 */
	public int g() {
		return (this.hex & 0x00FF00) >> 8;
	}

	/**
	 * @return the blue byte (0x00-0xFF)
	 */
	public int b() {
		return this.hex & 0x0000FF;
	}

	//

	/**
	 * @param that color instance
	 * @return euclidean distance, or -1 if that color is null
	 */
	public int euclideanDistance(final Color that) {

		if (that == null) {
			return -1;
		}

		final int deltaR = this.r() - that.r();
		final int deltaG = this.g() - that.g();
		final int deltaB = this.b() - that.b();

		return (int) Math.sqrt((deltaR * deltaR) + (deltaG * deltaG) + (deltaB * deltaB));
	}

	/**
	 * @param that color instance
	 * @return weighted distance, or -1 if that color is null
	 */
	public int weightedDistance(final Color that) {

		if (that == null) {
			return -1;
		}

		final int deltaR = this.r() - that.r();
		final int deltaG = this.g() - that.g();
		final int deltaB = this.b() - that.b();

		final float averageR = (this.r() + that.r()) / 2.0f;

		final float weightR = 2.0f + (averageR / 255.0f);
		final float weightG = 4.0f;
		final float weightB = 3.0f - (averageR / 255.0f);

		return (int) Math.sqrt(
				(int) (weightR * deltaR * deltaR)
						+ (int) (weightG * deltaG * deltaG)
						+ (int) (weightB * deltaB * deltaB));
	}

	//

	/**
	 * Color brightness.
	 * Photoshop’s RGB-to-Greyscale mode conversion
	 *
	 * @return 0.0f-1.0f
	 */
	public float brightness() {

		return (0.299f * normalize(this.r()))
				+ (0.587f * normalize(this.g()))
				+ (0.114f * normalize(this.b()));
	}

	/**
	 * Perceived brightness.
	 * Simple model that simulates human visual perception
	 *
	 * @return perceived brightness (0.0f-1.0f)
	 */
	public float perceivedBrightness() {

		final float r = normalize(this.r());
		final float g = normalize(this.g());
		final float b = normalize(this.b());

		return (float) Math.sqrt((0.299f * r * r) + (0.587f * g * g) + (0.114f * b * b));
	}

	/**
	 * Relative Luminance (WCAG 2.0 Formula).
	 * Industry-standard formula
	 *
	 * @return relative luminance (0.0f-1.0f)
	 */
	public float relativeLuminance() {

		return (0.2126f * linearize(normalize(this.r())))
				+ (0.7152f * linearize(normalize(this.g())))
				+ (0.0722f * linearize(normalize(this.b())));
	}

	/**
	 * @param component 0-255
	 * @return 0.0f-1.0f
	 */
	private static float normalize(final int component) {

		return component / 255.0f;
	}

	/**
	 * Linearizes a R, G or B component from sRGB.
	 *
	 * @param channel gamma-compressed RGB value (0.0f-1.0f)
	 * @return linear RGB value (0.0f-1.0f)
	 * @see #relativeLuminance()
	 */
	private static float linearize(final float channel) {

		return channel <= 0.04045f
				? (channel / 12.92f)
				: (float) Math.pow((channel + 0.055f) / 1.055f, 2.4f);
	}
}
