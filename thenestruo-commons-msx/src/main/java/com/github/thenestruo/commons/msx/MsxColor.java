package com.github.thenestruo.commons.msx;

import java.util.Objects;

import com.github.thenestruo.commons.color.Color;

public class MsxColor extends Color {

	/**
	 * @param index the color index (0-15)
	 * @param hex   the color hex value (0x000000-0xFFFFFF)
	 * @return Color instance
	 */
	public static MsxColor hex(final byte index, final int hex) {
		return new MsxColor(hex, index);
	}

	/**
	 * @param index the color index (0-15)
	 * @param r     the red byte (0x00-0xFF)
	 * @param g     the green byte (0x00-0xFF)
	 * @param b     the blue byte (0x00-0xFF)
	 * @return Color instance
	 */
	public static MsxColor rgb(final byte index, final int r, final int g, final int b) {
		return new MsxColor(Color.rgb(r, g, b).getHex(), index);
	}

	//

	/** The color index (0-15) */
	private final byte index;

	/**
	 * Constructor
	 *
	 * @param hex   the color hex value (0x000000-0xFFFFFF)
	 * @param index the color index (0-15)
	 */
	protected MsxColor(final int hex, final byte index) {
		super(hex);
		this.index = index;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.index, super.hashCode());
	}

	@Override
	public boolean equals(final Object obj) {

		if (obj == this) {
			return true;
		}
		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}
		final MsxColor that = (MsxColor) obj;
		return (this.index == that.index)
				&& (this.getHex() == that.getHex());
	}

	@Override
	public String toString() {
		return String.format("#%01X (%s)", this.index, super.toString());
	}

	//

	/**
	 * @return the color index (0-15)
	 */
	public byte getIndex() {
		return this.index;
	}
}
