package com.github.thenestruo.commons.msx;

import java.util.Objects;

import com.github.thenestruo.commons.Strings;

/**
 * A line of 8 pixels within an MSX charset, immutable
 */
public class MsxLine {

	/** The pattern for all foreground bits */
	public static final byte PATTERN_FG = (byte) 0xff;

	/** The pattern for all background bits */
	public static final byte PATTERN_BG = (byte) 0x00;

	/**
	 * Builder
	 *
	 * @param pattern the line pattern (CHRTBL byte)
	 * @param fg      the foreground color
	 * @param bg      the background color
	 * @return MsxCharsetLine instance
	 */
	public static MsxLine of(final byte pattern, final byte fg, final byte bg) {
		return ofBytes(pattern, toClrtblByte(fg, bg));
	}

	/**
	 * Builder
	 *
	 * @param pattern the line pattern (CHRTBL byte)
	 * @param colors  the line colors (CLRTBL byte)
	 * @return MsxCharsetLine instance
	 */
	public static MsxLine ofBytes(final byte pattern, final byte colors) {
		return new MsxLine(pattern, colors);
	}

	/**
	 * Builder
	 *
	 * @param singleColor the single line color
	 * @return MsxCharsetLine instance
	 */
	public static MsxLine foregroundOfColor(final byte singleColor) {
		return new MsxLine(PATTERN_FG, toClrtblByte(singleColor));
	}

	/**
	 * Builder
	 *
	 * @param fg the foreground color
	 * @param bg the (unused) background color
	 * @return MsxCharsetLine instance
	 */
	public static MsxLine foregroundOf(final byte fg, final byte bg) {
		return new MsxLine(PATTERN_FG, toClrtblByte(fg, bg));
	}

	/**
	 * Builder
	 *
	 * @param reference the reference MsxLine, whose colors will be copied
	 * @return MsxCharsetLine instance
	 */
	public static MsxLine foregroundUsing(final MsxLine reference) {
		return new MsxLine(PATTERN_FG, reference.clrtblByte);
	}

	/**
	 * Builder
	 *
	 * @param singleColor the single line color
	 * @return MsxCharsetLine instance
	 */
	public static MsxLine backgroundOfColor(final byte singleColor) {
		return new MsxLine(PATTERN_BG, toClrtblByte(singleColor));
	}

	/**
	 * Builder
	 *
	 * @param fg the (unused) foreground color
	 * @param bg the background color
	 * @return MsxCharsetLine instance
	 */
	public static MsxLine backgroundOf(final byte fg, final byte bg) {
		return new MsxLine(PATTERN_BG, toClrtblByte(fg, bg));
	}

	/**
	 * Builder
	 *
	 * @param reference the reference MsxLine, whose colors will be copied
	 * @return MsxCharsetLine instance
	 */
	public static MsxLine backgroundUsing(final MsxLine reference) {
		return new MsxLine(PATTERN_BG, reference.clrtblByte);
	}

	//

	/**
	 * @param singleColor a single line color
	 * @return line colors (CLRTBL byte)
	 */
	private static byte toClrtblByte(final byte singleColor) {
		return toClrtblByte(singleColor, singleColor);
	}

	/**
	 * @param fg the foreground color
	 * @param bg the background color
	 * @return line colors (CLRTBL byte)
	 */
	private static byte toClrtblByte(final byte fg, final byte bg) {
		return (byte) (((fg & 0x0f) << 4) | (bg & 0x0f));
	}

	//

	/** The line pattern (CHRTBL byte) */
	private final byte chrtblByte;

	/** The line colors (CLRTBL byte) */
	private final byte clrtblByte;

	/**
	 * Constructor
	 *
	 * @param chrtblByte the line pattern (CHRTBL byte)
	 * @param clrtblByte the line colors (CLRTBL byte)
	 */
	protected MsxLine(final byte chrtblByte, final byte clrtblByte) {
		this.chrtblByte = chrtblByte;
		this.clrtblByte = clrtblByte;
	}

	@Override
	public String toString() {

		final String basePattern = Integer.toBinaryString(this.chrtblByte);
		final String pattern = basePattern.length() < 8 ? Strings.leftPad(basePattern, 8, '0')
				: basePattern.length() == 8  ? basePattern
				: basePattern.substring(basePattern.length() - 8);

		return String.format("%s %01X %01X", pattern, this.fg(), this.bg());
	}

	/**
	 * @param that the other MSXLine to compare
	 * @return {@code true} if both MsxLines have the same pattern and color (CHRTBL and CLRTBL bytes)
	 */
	public boolean isSameAs(final MsxLine that) {

		return Objects.equals(this.chrtblByte, that.chrtblByte)
				&& Objects.equals(this.clrtblByte, that.clrtblByte);
	}

	/**
	 * @param that the other MsxLine to compare
	 * @return {@code true} if both MsxLines are equivalent and will look the same, {@code false} otherwise
	 */
	public boolean isEquivalentTo(final MsxLine that) {

		if ((this.chrtblByte == that.chrtblByte)
				&& (this.clrtblByte == that.clrtblByte)) {
			return true;
		}
		if ((this.invertedChrtblByte() == that.chrtblByte)
				&& (this.invertedClrtblByte() == that.clrtblByte)) {
			return true;
		}
		if ((this.singleColor() != null)
				&& Objects.equals(this.singleColor(), that.singleColor())) {
			return true;
		}
		return false;
	}

	/**
	 * @return the color count, counting the color of each pixel
	 */
	public int[] colorCountByPixel() {

		final int count[] = new int[16];

		final int fg = this.fg();
		final int bg = this.bg();
		for (int bit = 0; bit < 8; bit++) {
			final int color = ((this.chrtblByte & (1 << bit)) != 0) ? fg : bg;
			count[color]++;
		}

		return count;
	}

	/**
	 * @return the color count, counting the colors of the line
	 * (single colored line color is counted twice)
	 */
	public int[] colorCountWeighted() {

		final int count[] = new int[16];

		if (this.isSingleColor()) {
			count[this.singleColor()] += 2;
		} else {
			count[this.fg()]++;
			count[this.bg()]++;
		}

		return count;
	}

	/**
	 * @return the color count, counting the colors of the line
	 * (single colored line color is counted only once)
	 */
	public int[] colorCountByByte() {

		final int count[] = new int[16];

		if (this.isSingleColor()) {
			count[this.singleColor()]++;
		} else {
			count[this.fg()]++;
			count[this.bg()]++;
		}

		return count;
	}

	//

	public byte chrtblByte() {
		return this.chrtblByte;
	}

	public byte clrtblByte() {
		return this.clrtblByte;
	}

	//

	/**
	 * @return a line, inverted from this line
	 */
	public MsxLine inverted() {
		return new MsxLine(this.invertedChrtblByte(), this.invertedClrtblByte());
	}

	/**
	 * @return the pattern for the line inverted from this line
	 */
	public byte invertedChrtblByte() {
		return (byte) (this.chrtblByte ^ 0xff);
	}

	/**
	 * @return the color for the line inverted from this line
	 */
	public byte invertedClrtblByte() {
		return toClrtblByte(this.bg(), this.fg());
	}

	//

	/**
	 * @return the foreground color
	 */
	public byte fg() {
		return (byte) ((this.clrtblByte >> 4) & 0x0f);
	}

	/**
	 * @return the background color
	 */
	public byte bg() {
		return (byte) (this.clrtblByte & 0x0f);
	}

	/**
	 * @return {@code true} if the line has a single color, {@code false} otherwise
	 */
	public boolean isSingleColor() {
		return this.singleColor() != null;
	}

	/**
	 * @return {@code true} if the pattern is all foreground bits, {@code false} otherwise
	 */
	public boolean isFg() {
		return this.chrtblByte() == PATTERN_FG;
	}

	/**
	 * @return {@code true} if the pattern is all background bits, {@code false} otherwise
	 */
	public boolean isBg() {
		return this.chrtblByte() == PATTERN_BG;
	}

	/**
	 * @return if the line has a single color, the single color.
	 *         {@code null} otherwise
	 */
	public Byte singleColor() {
		if (this.chrtblByte == PATTERN_FG) {
			return this.fg();
		}
		if (this.chrtblByte == PATTERN_BG) {
			return this.bg();
		}
		if (this.fg() == this.bg()) {
			return this.fg();
		}
		return null;
	}
}
