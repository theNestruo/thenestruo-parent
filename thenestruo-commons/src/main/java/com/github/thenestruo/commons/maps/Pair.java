package com.github.thenestruo.commons.maps;

import java.util.Map;

public class Pair<K, V> implements Map.Entry<K, V> {

	public static <KK, VV> Pair<KK, VV> of(final KK key, final VV value) {
		return new Pair<>(key, value);
	}

	private final K key;

	private final V value;

	private Pair(final K key, final V value) {
		this.key = key;
		this.value = value;
	}

	public K getLeft() {
		return this.key;
	}

	@Override
	public K getKey() {
		return this.key;
	}

	public V getRight() {
		return this.value;
	}

	@Override
	public V getValue() {
		return this.value;
	}

	@Override
	public V setValue(final V value) {
		throw new UnsupportedOperationException();
	}

}
