package com.github.thenestruo.commons.msx;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MsxPalettes {

	/**
	 * TI TMS9918 palette, according Wikipedia
	 * (https://en.wikipedia.org/wiki/Texas_Instruments_TMS9918#Colors)
	 */
	public static final List<MsxColor> TMS9918_PALETTE = Collections.unmodifiableList(Arrays.asList(
			MsxColor.hex((byte) 0, 0x000000), // 0 transparent
			MsxColor.hex((byte) 1, 0x000000), // 1 black
			MsxColor.hex((byte) 2, 0x0AAD1E), // 2 medium green
			MsxColor.hex((byte) 3, 0x34C84C), // 3 light green
			MsxColor.hex((byte) 4, 0x2B2DE3), // 4 dark blue
			MsxColor.hex((byte) 5, 0x514BFB), // 5 light blue
			MsxColor.hex((byte) 6, 0xBD2925), // 6 dark red
			MsxColor.hex((byte) 7, 0x1EE2EF), // 7 cyan
			MsxColor.hex((byte) 8, 0xFB2C2B), // 8 medium red
			MsxColor.hex((byte) 9, 0xFF5F4C), // 9 light red
			MsxColor.hex((byte) 10, 0xBDA22B), // 10 dark yellow
			MsxColor.hex((byte) 11, 0xD7B454), // 11 light yellow
			MsxColor.hex((byte) 12, 0x0A8C18), // 12 dark green
			MsxColor.hex((byte) 13, 0xAF329A), // 13 magenta
			MsxColor.hex((byte) 14, 0xB2B2B2), // 14 gray
			MsxColor.hex((byte) 15, 0xFFFFFF) // 15 white
	));

	/**
	 * TI TMS9219 palette, from hap's meisei emulator
	 */
	public static final List<MsxColor> TMS9219_PALETTE = Collections.unmodifiableList(Arrays.asList(
			MsxColor.hex((byte) 0, 0x000000), // 0 transparent
			MsxColor.hex((byte) 1, 0x000000), // 1 black
			MsxColor.hex((byte) 2, 0x23CB32), // 2 medium green
			MsxColor.hex((byte) 3, 0x60DD6C), // 3 light green
			MsxColor.hex((byte) 4, 0x544EFF), // 4 dark blue
			MsxColor.hex((byte) 5, 0x7D70FF), // 5 light blue
			MsxColor.hex((byte) 6, 0xD25442), // 6 dark red
			MsxColor.hex((byte) 7, 0x45E8FF), // 7 cyan
			MsxColor.hex((byte) 8, 0xFA5948), // 8 medium red
			MsxColor.hex((byte) 9, 0xFF7C6C), // 9 light red
			MsxColor.hex((byte) 10, 0xD3C63C), // 10 dark yellow
			MsxColor.hex((byte) 11, 0xE5D26D), // 11 light yellow
			MsxColor.hex((byte) 12, 0x23B22C), // 12 dark green
			MsxColor.hex((byte) 13, 0xC85AC6), // 13 magenta
			MsxColor.hex((byte) 14, 0xCCCCCC), // 14 gray
			MsxColor.hex((byte) 15, 0xFFFFFF) // 15 white
	));

	/**
	 * Palette used by pixel artist Yazioh (https://twitter.com/Yazioh)
	 */
	public static final List<MsxColor> YAZIOH_PALETTE = Collections.unmodifiableList(Arrays.asList(
			MsxColor.hex((byte) 0, 0x000000), // 0 transparent
			MsxColor.hex((byte) 1, 0x000000), // 1 black
			MsxColor.hex((byte) 2, 0x3EB849), // 2 medium green
			MsxColor.hex((byte) 3, 0x74D07D), // 3 light green
			MsxColor.hex((byte) 4, 0x5955E0), // 4 dark blue
			MsxColor.hex((byte) 5, 0x8076F1), // 5 light blue
			MsxColor.hex((byte) 6, 0xB95E51), // 6 dark red
			MsxColor.hex((byte) 7, 0x65DBEF), // 7 cyan
			MsxColor.hex((byte) 8, 0xDB6559), // 8 medium red
			MsxColor.hex((byte) 9, 0xFF897D), // 9 light red
			MsxColor.hex((byte) 10, 0xCCC35E), // 10 dark yellow
			MsxColor.hex((byte) 11, 0xDED087), // 11 light yellow
			MsxColor.hex((byte) 12, 0x3AA241), // 12 dark green
			MsxColor.hex((byte) 13, 0xB766B5), // 13 magenta
			MsxColor.hex((byte) 14, 0xCCCCCC), // 14 gray
			MsxColor.hex((byte) 15, 0xFFFFFF) // 15 white
	));

	/**
	 * TOSHIBA palette, from reidrac's MSX Pixel Tools
	 * (https://github.com/reidrac/msx-pixel-tools)
	 */
	public static final List<MsxColor> TOSHIBA_PALETTE = Collections.unmodifiableList(Arrays.asList(
			MsxColor.rgb((byte) 0, 0, 0, 0), // 0 transparent
			MsxColor.rgb((byte) 1, 0, 0, 0), // 1 black
			MsxColor.rgb((byte) 2, 102, 204, 102), // 2 medium green
			MsxColor.rgb((byte) 3, 136, 238, 136), // 3 light green
			MsxColor.rgb((byte) 4, 68, 68, 221), // 4 dark blue
			MsxColor.rgb((byte) 5, 119, 119, 255), // 5 light blue
			MsxColor.rgb((byte) 6, 187, 85, 85), // 6 dark red
			MsxColor.rgb((byte) 7, 119, 221, 221), // 7 cyan
			MsxColor.rgb((byte) 8, 221, 102, 102), // 8 medium red
			MsxColor.rgb((byte) 9, 255, 119, 119), // 9 light red
			MsxColor.rgb((byte) 10, 204, 204, 85), // 10 dark yellow
			MsxColor.rgb((byte) 11, 238, 238, 136), // 11 light yellow
			MsxColor.rgb((byte) 12, 85, 170, 85), // 12 dark green
			MsxColor.rgb((byte) 13, 187, 85, 187), // 13 magenta
			MsxColor.rgb((byte) 14, 204, 204, 204), // 14 gray
			MsxColor.rgb((byte) 15, 238, 238, 238) // 15 white
	));

	/**
	 * V9938 palette, from hap's meisei emulator
	 */
	public static final List<MsxColor> V9938_PALETTE = Collections.unmodifiableList(Arrays.asList(
			MsxColor.hex((byte) 0, 0x000000), // 0 transparent
			MsxColor.hex((byte) 1, 0x000000), // 1 black
			MsxColor.hex((byte) 2, 0x24DA24), // 2 medium green
			MsxColor.hex((byte) 3, 0x6DFF6D), // 3 light green
			MsxColor.hex((byte) 4, 0x2424FF), // 4 dark blue
			MsxColor.hex((byte) 5, 0x486DFF), // 5 light blue
			MsxColor.hex((byte) 6, 0xB62424), // 6 dark red
			MsxColor.hex((byte) 7, 0x48DAFF), // 7 cyan
			MsxColor.hex((byte) 8, 0xFF2424), // 8 medium red
			MsxColor.hex((byte) 9, 0xFF6D6D), // 9 light red
			MsxColor.hex((byte) 10, 0xDADA24), // 10 dark yellow
			MsxColor.hex((byte) 11, 0xDADA91), // 11 light yellow
			MsxColor.hex((byte) 12, 0x249124), // 12 dark green
			MsxColor.hex((byte) 13, 0xDA48B6), // 13 magenta
			MsxColor.hex((byte) 14, 0xB6B6B6), // 14 gray
			MsxColor.hex((byte) 15, 0xFFFFFF) // 15 white
	));
}
