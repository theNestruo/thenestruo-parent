package com.github.thenestruo.commons.msx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.thenestruo.commons.IntArrays;
import com.github.thenestruo.commons.maps.Pair;

public class MsxPalettesTest {

	@ParameterizedTest
	@MethodSource("sortByLuminanceTestArguments")
	void sortByLuminanceTest(final String label, final List<MsxColor> palette,
			final ToDoubleFunction<MsxColor> function) {

		final List<MsxColor> list = new ArrayList<>(palette);
		list.sort(Comparator.comparingDouble(function).thenComparingInt(MsxColor::getIndex));

		final String debug = list.stream()
				.map(msxColor -> String.format("%01X (%.4f)", msxColor.getIndex(), function.applyAsDouble(msxColor)))
				.collect(Collectors.joining(", "));
		System.out.printf("%s: %s%n", label, debug);

		final int[] array = list.stream().mapToInt(MsxColor::getIndex).toArray();

		Assertions.assertAll(
			() -> Assertions.assertTrue(IntArrays.indexOf(array, 0) <= 1),
			() -> Assertions.assertTrue(IntArrays.indexOf(array, 1) <= 1),
			() -> Assertions.assertTrue(IntArrays.indexOf(array, 4) <= IntArrays.indexOf(array, 5)),
			() -> Assertions.assertTrue(IntArrays.indexOf(array, 5) <= IntArrays.indexOf(array, 7)),
			() -> Assertions.assertTrue(IntArrays.indexOf(array, 6) <= IntArrays.indexOf(array, 8)),
			() -> Assertions.assertTrue(IntArrays.indexOf(array, 8) <= IntArrays.indexOf(array, 9)),
			() -> Assertions.assertTrue(IntArrays.indexOf(array, 12) <= IntArrays.indexOf(array, 2)),
			() -> Assertions.assertTrue(IntArrays.indexOf(array, 2) <= IntArrays.indexOf(array, 3)),
			() -> Assertions.assertTrue(IntArrays.indexOf(array, 14) <= IntArrays.indexOf(array, 15)),
			() -> Assertions.assertTrue(IntArrays.indexOf(array, 15) == 15)
		);
	}

	private static Stream<Arguments> sortByLuminanceTestArguments() {

		final List<Arguments> list = new ArrayList<>();

		for (final Pair<String, List<MsxColor>> palettePair : Arrays.asList(
				Pair.of("TMS9219", MsxPalettes.TMS9219_PALETTE),
				Pair.of("TMS9918", MsxPalettes.TMS9918_PALETTE),
				Pair.of("YAZIOH ", MsxPalettes.YAZIOH_PALETTE),
				Pair.of("TOSHIBA", MsxPalettes.TOSHIBA_PALETTE),
				Pair.of("V9938  ", MsxPalettes.V9938_PALETTE))) {
			final List<MsxColor> palette = palettePair.getValue();
			for (final Pair<String, ToDoubleFunction<MsxColor>> functionPair : Arrays.asList(
					Pair.of("         brightness", (ToDoubleFunction<MsxColor>) MsxColor::brightness),
					Pair.of("perceivedBrightness", (ToDoubleFunction<MsxColor>) MsxColor::perceivedBrightness),
					Pair.of("  relativeLuminance", (ToDoubleFunction<MsxColor>) MsxColor::relativeLuminance))) {
				final ToDoubleFunction<MsxColor> function = functionPair.getValue();
				final String label = String.format("%s(%s)", functionPair.getKey(), palettePair.getKey());

				list.add(Arguments.of(label, palette, function));
			}
		}

		return list.stream();
	}
}
