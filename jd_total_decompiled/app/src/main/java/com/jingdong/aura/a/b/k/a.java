package com.jingdong.aura.a.b.k;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class a {
    static Map<String, l.b.a.d> a = new ConcurrentHashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<l.b.a.d> a() {
        ArrayList arrayList = new ArrayList(a.size());
        synchronized (a) {
            arrayList.addAll(a.values());
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b() {
        return a.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(String str) {
        if (str == null) {
            com.jingdong.aura.a.b.e.a("", "BundleManager", "", "BundleManager remove key null: ", "BundleManager_remove", null);
        } else {
            a.remove(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static l.b.a.d a(String str) {
        if (str == null) {
            com.jingdong.aura.a.b.e.a("", "BundleManager", "", "BundleManager getBundle location null:", "BundleManager_getBundle", null);
            return null;
        }
        return a.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(String str, l.b.a.d dVar) {
        if (str == null) {
            com.jingdong.aura.a.b.e.a("", "BundleManager", "", "BundleManager put key null: " + dVar, "BundleManager_put", null);
            return;
        }
        a.put(str, dVar);
    }
}
