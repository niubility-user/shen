package com.facebook.react.common;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class MapBuilder {

    /* loaded from: classes.dex */
    public static final class Builder<K, V> {
        private Map mMap;
        private boolean mUnderConstruction;

        public Map<K, V> build() {
            if (this.mUnderConstruction) {
                this.mUnderConstruction = false;
                return this.mMap;
            }
            throw new IllegalStateException("Underlying map has already been built");
        }

        public Builder<K, V> put(K k2, V v) {
            if (this.mUnderConstruction) {
                this.mMap.put(k2, v);
                return this;
            }
            throw new IllegalStateException("Underlying map has already been built");
        }

        private Builder() {
            this.mMap = MapBuilder.newHashMap();
            this.mUnderConstruction = true;
        }
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<>();
    }

    public static <K, V> Map<K, V> of() {
        return newHashMap();
    }

    public static <K, V> Map<K, V> of(K k2, V v) {
        Map<K, V> of = of();
        of.put(k2, v);
        return of;
    }

    public static <K, V> Map<K, V> of(K k2, V v, K k3, V v2) {
        Map<K, V> of = of();
        of.put(k2, v);
        of.put(k3, v2);
        return of;
    }

    public static <K, V> Map<K, V> of(K k2, V v, K k3, V v2, K k4, V v3) {
        Map<K, V> of = of();
        of.put(k2, v);
        of.put(k3, v2);
        of.put(k4, v3);
        return of;
    }

    public static <K, V> Map<K, V> of(K k2, V v, K k3, V v2, K k4, V v3, K k5, V v4) {
        Map<K, V> of = of();
        of.put(k2, v);
        of.put(k3, v2);
        of.put(k4, v3);
        of.put(k5, v4);
        return of;
    }

    public static <K, V> Map<K, V> of(K k2, V v, K k3, V v2, K k4, V v3, K k5, V v4, K k6, V v5) {
        Map<K, V> of = of();
        of.put(k2, v);
        of.put(k3, v2);
        of.put(k4, v3);
        of.put(k5, v4);
        of.put(k6, v5);
        return of;
    }

    public static <K, V> Map<K, V> of(K k2, V v, K k3, V v2, K k4, V v3, K k5, V v4, K k6, V v5, K k7, V v6) {
        Map<K, V> of = of();
        of.put(k2, v);
        of.put(k3, v2);
        of.put(k4, v3);
        of.put(k5, v4);
        of.put(k6, v5);
        of.put(k7, v6);
        return of;
    }

    public static <K, V> Map<K, V> of(K k2, V v, K k3, V v2, K k4, V v3, K k5, V v4, K k6, V v5, K k7, V v6, K k8, V v7) {
        Map<K, V> of = of();
        of.put(k2, v);
        of.put(k3, v2);
        of.put(k4, v3);
        of.put(k5, v4);
        of.put(k6, v5);
        of.put(k7, v6);
        of.put(k8, v7);
        return of;
    }
}
