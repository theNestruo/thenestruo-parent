package com.github.thenestruo.commons.msx;

import java.util.Objects;
import java.util.function.Function;

import com.github.thenestruo.commons.IntArrays;

/**
 * MSX charset data, mutable
 */
public class MsxCharset {

	/**
	 * Builder
	 *
	 * @param chrtbl
	 * @param clrtbl
	 */
	public static MsxCharset of(final byte[] chrtbl, final byte[] clrtbl) {
		if (Objects.requireNonNull(chrtbl).length != Objects.requireNonNull(clrtbl).length) {
			throw new IllegalArgumentException();
		}
		return new MsxCharset(chrtbl, clrtbl);
	}

	/**
	 * Copy builder
	 *
	 * @param that
	 */
	public static MsxCharset copyOf(final MsxCharset that) {
		Objects.requireNonNull(that);
		return of(that.chrtbl, that.clrtbl);
	}

	//

	private final byte[] chrtbl;
	private final byte[] clrtbl;

	/**
	 * Constructor
	 *
	 * @param chrtbl
	 * @param clrtbl
	 */
	private MsxCharset(final byte[] chrtbl, final byte[] clrtbl) {
		this.chrtbl = chrtbl.clone();
		this.clrtbl = clrtbl.clone();
	}

	public byte[] chrtbl() {
		return this.chrtbl.clone();
	}

	public byte[] clrtbl() {
		return this.clrtbl.clone();
	}

	public int size() {
		return this.chrtbl.length;
	}

	public MsxLine get(final int address) {
		return MsxLine.ofBytes(this.chrtbl[address], this.clrtbl[address]);
	}

	public void set(final int address, final MsxLine line) {
		this.chrtbl[address] = line.chrtblByte();
		this.clrtbl[address] = line.clrtblByte();
	}

	/**
	 * @param colorCountFunction the function to get the color count of each line
	 * @return the number of pixels of each color
	 */
	public int[] colorCount(Function<MsxLine, int[]> colorCountFunction) {

		return this.pixelCountByColor(0, 1, colorCountFunction);
	}

	/**
	 * @param colorCountFunction the function to get the color count of each line
	 * @return the number of pixels of each color of even lines
	 */
	public int[] evenLinesColorCount(Function<MsxLine, int[]> colorCountFunction) {

		return this.pixelCountByColor(0, 2, colorCountFunction);
	}

	/**
	 * @param colorCountFunction the function to get the color count of each line
	 * @return the number of pixels of each color of odd lines
	 */
	public int[] oddLinesColorCount(Function<MsxLine, int[]> colorCountFunction) {

		return this.pixelCountByColor(1, 2, colorCountFunction);
	}

	/**
	 * @param startAddress the start address
	 * @param step the address step
	 * @param colorCountFunction the function to get the color count of each line
	 * @return the number of pixels of each color of every step lines, starting at startAddress
	 */
	private int[] pixelCountByColor(int startAddress, int step, Function<MsxLine, int[]> colorCountFunction) {

		final int count[] = new int[16];

		for (int address = startAddress, size = this.size(); address < size; address += step) {
			IntArrays.addTo(count, colorCountFunction.apply(this.get(address)));
		}

		return count;
	}
}
