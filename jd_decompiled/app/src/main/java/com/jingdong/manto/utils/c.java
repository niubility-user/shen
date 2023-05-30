package com.jingdong.manto.utils;

import android.text.TextUtils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes16.dex */
public class c {
    private static volatile c b;
    private Map<String, b> a = new ConcurrentHashMap();

    /* loaded from: classes16.dex */
    public static class a {
        public Map a = new ConcurrentHashMap();

        public final a a(String str, Object obj) {
            if (!TextUtils.isEmpty(str)) {
                this.a.put(str, obj);
            }
            return this;
        }

        public final Object a(String str) {
            return this.a.get(str);
        }

        public final String a(String str, String str2) {
            if (TextUtils.isEmpty(str)) {
                return str2;
            }
            Object obj = this.a.get(str);
            return obj instanceof String ? (String) obj : str2;
        }

        public final void a() {
            this.a.clear();
        }

        public final boolean a(String str, boolean z) {
            if (TextUtils.isEmpty(str)) {
                return z;
            }
            Object obj = this.a.get(str);
            return obj instanceof Boolean ? ((Boolean) obj).booleanValue() : z;
        }

        public final a b(String str, String str2) {
            if (!TextUtils.isEmpty(str)) {
                this.a.put(str, str2);
            }
            return this;
        }

        public final a b(String str, boolean z) {
            if (!TextUtils.isEmpty(str)) {
                this.a.put(str, Boolean.valueOf(z));
            }
            return this;
        }

        public final boolean b(String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            Object obj = this.a.get(str);
            if (obj instanceof Boolean) {
                return ((Boolean) obj).booleanValue();
            }
            return false;
        }
    }

    /* loaded from: classes16.dex */
    final class b {
        a a = new a();

        b(c cVar) {
        }
    }

    private c() {
    }

    public static c a() {
        if (b == null) {
            synchronized (c.class) {
                if (b == null) {
                    b = new c();
                }
            }
        }
        return b;
    }

    public final a a(String str) {
        b bVar = this.a.get(str);
        if (bVar != null) {
            return bVar.a;
        }
        return null;
    }

    public final a a(String str, boolean z) {
        b bVar = this.a.get(str);
        if (bVar == null) {
            if (!z) {
                return null;
            }
            bVar = new b(this);
            this.a.put(str, bVar);
        }
        return bVar.a;
    }

    public final a b(String str) {
        b remove = this.a.remove(str);
        if (remove != null) {
            return remove.a;
        }
        return null;
    }
}
