package com.jingdong.manto.widget.input;

import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.manto.utils.MantoUtils;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes16.dex */
public class o {
    public static Map<String, Integer> a;
    public static Set<String> b;

    /* renamed from: c */
    private static Map<Integer, WeakReference<com.jingdong.manto.widget.input.z.b>> f14481c;
    private static Map<com.jingdong.manto.q.n, com.jingdong.manto.widget.input.z.d> d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements Runnable {
        final /* synthetic */ com.jingdong.manto.q.n a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ Integer f14482c;

        a(com.jingdong.manto.q.n nVar, String str, Integer num) {
            this.a = nVar;
            this.b = str;
            this.f14482c = num;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.manto.widget.input.z.b a;
            com.jingdong.manto.widget.input.z.d dVar = (com.jingdong.manto.widget.input.z.d) o.d.get(this.a);
            if (dVar == null || (a = o.a(dVar.getInputId())) == null) {
                return;
            }
            a.a(this.b, this.f14482c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b implements Runnable {
        final /* synthetic */ com.jingdong.manto.q.n a;
        final /* synthetic */ com.jingdong.manto.widget.input.z.d b;

        b(com.jingdong.manto.q.n nVar, com.jingdong.manto.widget.input.z.d dVar) {
            this.a = nVar;
            this.b = dVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            o.d.put(this.a, this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class c implements Runnable {
        final /* synthetic */ com.jingdong.manto.q.n a;

        c(com.jingdong.manto.q.n nVar) {
            this.a = nVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            o.d.remove(this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class d implements Runnable {
        final /* synthetic */ com.jingdong.manto.q.n a;

        d(com.jingdong.manto.q.n nVar) {
            this.a = nVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            o.e(this.a);
        }
    }

    static {
        HashSet hashSet = new HashSet();
        hashSet.add("text");
        hashSet.add("number");
        hashSet.add("digit");
        hashSet.add("idcard");
        b = Collections.unmodifiableSet(hashSet);
        HashMap hashMap = new HashMap(3);
        hashMap.put("digit", 2);
        hashMap.put("number", 0);
        hashMap.put("idcard", 1);
        a = Collections.unmodifiableMap(hashMap);
        f14481c = new HashMap();
        d = new HashMap();
    }

    static com.jingdong.manto.widget.input.z.b a(int i2) {
        WeakReference<com.jingdong.manto.widget.input.z.b> weakReference = f14481c.get(Integer.valueOf(i2));
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public static com.jingdong.manto.widget.input.z.b a(com.jingdong.manto.q.n nVar) {
        com.jingdong.manto.widget.input.z.d dVar;
        if (nVar == null || (dVar = d.get(nVar)) == null) {
            return null;
        }
        return a(dVar.getInputId());
    }

    public static void a(int i2, com.jingdong.manto.widget.input.z.b bVar) {
        if (bVar != null) {
            f14481c.put(Integer.valueOf(i2), new WeakReference<>(bVar));
        }
    }

    public static void a(com.jingdong.manto.q.n nVar, com.jingdong.manto.widget.input.z.d dVar) {
        if (nVar != null) {
            MantoUtils.runOnUiThread(new b(nVar, dVar));
        }
    }

    public static void a(com.jingdong.manto.q.n nVar, String str, Integer num) {
        if (nVar != null) {
            MantoUtils.runOnUiThread(new a(nVar, str, num));
        }
    }

    public static boolean a(com.jingdong.manto.q.n nVar, int i2, int i3, int i4) {
        com.jingdong.manto.widget.input.z.b a2 = a(i2);
        return a2 != null && a2.a(nVar) && a2.a(i3, i4);
    }

    public static boolean a(com.jingdong.manto.q.n nVar, Integer num) {
        if (num == null) {
            com.jingdong.manto.widget.input.z.d dVar = d.get(nVar);
            if (dVar == null) {
                return false;
            }
            num = Integer.valueOf(dVar.getInputId());
        }
        com.jingdong.manto.widget.input.z.b a2 = a(num.intValue());
        return a2 != null && a2.d();
    }

    @Deprecated
    public static boolean a(com.jingdong.manto.widget.input.a0.g gVar, int i2) {
        WeakReference<com.jingdong.manto.widget.input.z.b> weakReference = f14481c.get(Integer.valueOf(i2));
        Object obj = weakReference == null ? null : (com.jingdong.manto.widget.input.z.b) weakReference.get();
        j jVar = obj instanceof j ? (j) obj : null;
        return jVar != null && jVar.a(gVar);
    }

    public static void b(com.jingdong.manto.q.n nVar) {
        MantoThreadUtils.runOnUIThread(new d(nVar));
    }

    public static boolean b(int i2) {
        com.jingdong.manto.widget.input.z.b a2 = a(i2);
        return a2 != null && a2.f();
    }

    public static void c(com.jingdong.manto.q.n nVar) {
        if (nVar != null) {
            new n(nVar).a();
        }
    }

    public static void d(com.jingdong.manto.q.n nVar) {
        if (nVar != null) {
            MantoUtils.runOnUiThread(new c(nVar));
        }
    }

    public static boolean e(com.jingdong.manto.q.n nVar) {
        return a(nVar, (Integer) null);
    }
}
