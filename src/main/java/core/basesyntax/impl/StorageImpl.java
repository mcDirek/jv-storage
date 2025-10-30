package core.basesyntax.impl;

import core.basesyntax.Storage;
import java.util.Objects;


public class StorageImpl<K, V> implements Storage<K, V> {
    private static final int MAX_SIZE = 10;
    private final K[] keys;
    private final V[] values;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public StorageImpl() {
        keys = (K[]) new Object[MAX_SIZE];
        values = (V[]) new Object[MAX_SIZE];
        size = 0;
    }

    @Override
    public void put(K key, V value) {
        int idx = indexOfKey(key); // avoid duplication (CHECKLIST #5)
        if (idx >= 0) {
            // key exists -> replace value
            values[idx] = value;
            return;
        }
        // new key
        if (size >= MAX_SIZE) {
            throw new IllegalStateException("Storage is full!");
        }
        keys[size] = key;
        values[size] = value;
        size++;
    }

    @Override
    public V get(K key) {
        int idx = indexOfKey(key); // reuse helper
        return idx >= 0 ? values[idx] : null;
    }

    private int indexOfKey(K key) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(keys[i], key)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }
}
