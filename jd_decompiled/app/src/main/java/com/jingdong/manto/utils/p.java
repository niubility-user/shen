package com.jingdong.manto.utils;

import android.text.TextUtils;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes16.dex */
public class p {
    private static volatile p b;
    private Map<String, c> a = new a((byte) 0);

    /* loaded from: classes16.dex */
    static class a<K, V> extends ConcurrentHashMap<K, V> {
        private a() {
        }

        a(byte b) {
            this();
        }

        @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
        public final boolean containsValue(Object obj) {
            if (obj == null) {
                return false;
            }
            return super.containsValue(obj);
        }

        @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
        public final V get(Object obj) {
            if (obj == null) {
                return null;
            }
            return (V) super.get(obj);
        }

        @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
        public final V put(K k2, V v) {
            if (k2 == null) {
                return null;
            }
            return v == null ? (V) super.remove(k2) : (V) super.put(k2, v);
        }

        @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
        public final V remove(Object obj) {
            if (obj == null) {
                return null;
            }
            return (V) super.remove(obj);
        }
    }

    /* loaded from: classes16.dex */
    public static class b {
        private Map<String, Object> a = new a((byte) 0);

        public final b a(String str, String str2) {
            if (!TextUtils.isEmpty(str)) {
                this.a.put(str, str2);
            }
            return this;
        }

        public final Object a(String str) {
            return this.a.get(str);
        }

        public final Set<String> a() {
            return this.a.keySet();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public static final class c {
        b a = new b();
        long b = System.currentTimeMillis();

        c() {
        }
    }

    private p() {
    }

    public static p a() {
        if (b == null) {
            synchronized (p.class) {
                if (b == null) {
                    b = new p();
                }
            }
        }
        return b;
    }

    private c b(String str) {
        c cVar = new c();
        this.a.put(str, cVar);
        return cVar;
    }

    public final b a(String str) {
        c cVar = this.a.get(str);
        if (cVar != null) {
            return cVar.a;
        }
        return null;
    }

    public final b a(String str, boolean z) {
        c cVar = this.a.get(str);
        if (cVar == null) {
            if (!z) {
                return null;
            }
            cVar = b(str);
        }
        return cVar.a;
    }

    public String toString() {
        c cVar;
        long currentTimeMillis = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append("DataCenter \nDataStore size : ");
        sb.append(this.a.size());
        LinkedHashSet linkedHashSet = new LinkedHashSet(this.a.entrySet());
        Iterator it = linkedHashSet.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (entry != null && (cVar = (c) entry.getValue()) != null) {
                sb.append("\nDataStore id : ");
                sb.append((String) entry.getKey());
                sb.append(", CT : ");
                sb.append(cVar.b);
                sb.append("ms");
                sb.append(", TTL : ");
                sb.append((currentTimeMillis - cVar.b) / 1000);
                sb.append("s");
            }
        }
        linkedHashSet.clear();
        return sb.toString();
    }
}
