package com.jingdong.aura.a.b;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Locale;

/* loaded from: classes4.dex */
public final class i {
    private static final HashSet<String> d;

    /* renamed from: e  reason: collision with root package name */
    private static long f12109e;
    private l.b.a.d a;
    private final Dictionary<String, Object> b;

    /* renamed from: c  reason: collision with root package name */
    private Object f12110c;

    static {
        HashSet<String> hashSet = new HashSet<>();
        d = hashSet;
        Locale locale = Locale.US;
        hashSet.add("service.id".toLowerCase(locale));
        hashSet.add("objectClass".toLowerCase(locale));
    }

    public i(l.b.a.d dVar, Object obj, Dictionary<String, ?> dictionary, String[] strArr) {
        new HashMap(0);
        a(obj, strArr);
        this.a = dVar;
        this.f12110c = obj;
        this.b = dictionary == null ? new Hashtable() : new Hashtable(dictionary.size());
        if (dictionary != null) {
            Enumeration<String> keys = dictionary.keys();
            while (keys.hasMoreElements()) {
                String nextElement = keys.nextElement();
                this.b.put(nextElement, dictionary.get(nextElement));
            }
        }
        this.b.put("objectClass", strArr);
        Dictionary<String, Object> dictionary2 = this.b;
        long j2 = f12109e + 1;
        f12109e = j2;
        dictionary2.put("service.id", Long.valueOf(j2));
        Integer num = dictionary == null ? null : (Integer) dictionary.get("service.ranking");
        this.b.put("service.ranking", Integer.valueOf(num != null ? num.intValue() : 0));
    }

    private void a(Object obj, String[] strArr) {
        for (int i2 = 0; i2 < strArr.length; i2++) {
            try {
                if (!Class.forName(strArr[i2], false, obj.getClass().getClassLoader()).isInstance(obj)) {
                    throw new IllegalArgumentException("Service " + obj.getClass().getName() + " does not implement the interface " + strArr[i2]);
                }
            } catch (ClassNotFoundException e2) {
                throw new IllegalArgumentException("Interface " + strArr[i2] + " implemented by service " + obj.getClass().getName() + " cannot be located: " + e2.getMessage());
            }
        }
    }

    public String toString() {
        return "ServiceReference{" + this.f12110c + "}";
    }
}
