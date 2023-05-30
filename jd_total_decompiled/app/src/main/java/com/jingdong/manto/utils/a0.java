package com.jingdong.manto.utils;

import android.text.TextUtils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes16.dex */
public class a0 {
    public Map<String, Object> a = new b();

    /* loaded from: classes16.dex */
    private static class b<K, V> extends ConcurrentHashMap<K, V> {
        private b() {
        }

        @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return obj != null && super.containsKey(obj);
        }

        @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
        public V get(Object obj) {
            if (obj == null) {
                return null;
            }
            return (V) super.get(obj);
        }

        @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
        public V put(K k2, V v) {
            if (k2 == null || v == null) {
                return null;
            }
            return (V) super.put(k2, v);
        }

        @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
        public V remove(Object obj) {
            if (obj == null) {
                return null;
            }
            return (V) super.remove(obj);
        }
    }

    public final boolean a(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return z;
        }
        Object obj = this.a.get(str);
        return obj instanceof Boolean ? ((Boolean) obj).booleanValue() : z;
    }

    public final a0 b(String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            this.a.put(str, Boolean.valueOf(z));
        }
        return this;
    }
}
