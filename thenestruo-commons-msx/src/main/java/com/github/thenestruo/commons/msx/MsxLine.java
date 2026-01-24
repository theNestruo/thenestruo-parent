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
		return new MsxLine(PATTERN_FG, toClrtblByte(singleColor));
	}

	/**
	 * Builder
	 *
	 * @param fg the (unused) foreground color
	 * @param bg the background color
	 * @return MsxCharsetLine instance
	 */
	public static MsxLine backgroundOf(final byte fg, final byte bg) {
		return new MsxLine(PATTERN_FG, toClrtblByte(fg, bg));
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
		return (byte) ((fg << 4) | (bg & 0x0f));
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
	private MsxLine(final byte chrtblByte, final byte clrtblByte) {
		this.chrtblByte = chrtblByte;
		this.clrtblByte = clrtblByte;
	}

	@Override
	public int hashCode() {

		return Objects.hash(this.chrtblByte, this.clrtblByte);
	}

	@Override
	public boolean equals(final Object obj) {

		if (obj == this) {
			return true;
		}
		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}
		final MsxLine that = (MsxLine) obj;
		return Objects.equals(this.chrtblByte, that.chrtblByte)
				&& Objects.equals(this.clrtblByte, that.clrtblByte);
	}

	@Override
	public String toString() {

		return String.format("%s %01X %01X",
				Strings.leftPad(Integer.toBinaryString(this.chrtblByte), 8, '0'), this.fg(), this.bg());
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
