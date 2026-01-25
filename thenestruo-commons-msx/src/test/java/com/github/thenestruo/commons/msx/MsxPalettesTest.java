package com.github.thenestruo.commons.msx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.thenestruo.commons.maps.Pair;

public class MsxPalettesTest {

	@ParameterizedTest
	@MethodSource("sortByLuminanceTestArguments")
	void sortByLuminanceTest(final String label, final List<MsxColor> palette,
			final ToDoubleFunction<MsxColor> function) {

		final ArrayList<MsxColor> list = new ArrayList<>(palette);
		list.sort(Comparator.comparingDouble(function).thenComparingInt(MsxColor::getIndex));

		final String info = list.stream()
				.map(msxColor -> String.format("%01X (%.4f)", msxColor.getIndex(), function.applyAsDouble(msxColor)))
				.collect(Collectors.joining(" - "));
		System.out.println(info + " :: " + label);
	}

	private static Stream<Arguments> sortByLuminanceTestArguments() {

		final List<Arguments> list = new ArrayList<>();

		for (final Pair<String, List<MsxColor>> palettePair : Arrays.asList(
				Pair.of("TMS9219", MsxPalettes.TMS9219_PALETTE),
				Pair.of("TMS9918", MsxPalettes.TMS9918_PALETTE),
				Pair.of("YAZIOH_", MsxPalettes.YAZIOH_PALETTE),
				Pair.of("TOSHIBA", MsxPalettes.TOSHIBA_PALETTE),
				Pair.of("V9938__", MsxPalettes.V9938_PALETTE))) {
			final List<MsxColor> palette = palettePair.getValue();
			for (final Pair<String, ToDoubleFunction<MsxColor>> functionPair : Arrays.asList(
					Pair.of("colorBr", (ToDoubleFunction<MsxColor>) MsxColor::brightness),
					Pair.of("percBri", (ToDoubleFunction<MsxColor>) MsxColor::perceivedBrightness),
					Pair.of("relLumi", (ToDoubleFunction<MsxColor>) MsxColor::relativeLuminance))) {
				final ToDoubleFunction<MsxColor> function = functionPair.getValue();
				final String label = palettePair.getKey() + "-" + functionPair.getKey();

				list.add(Arguments.of(label, palette, function));
			}
		}

		return list.stream();
	}
}
