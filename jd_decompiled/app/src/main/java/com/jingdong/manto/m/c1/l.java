package com.jingdong.manto.m.c1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.WebSocket;

/* loaded from: classes15.dex */
public class l {
    private static AtomicInteger b = new AtomicInteger(0);

    /* renamed from: c  reason: collision with root package name */
    public static volatile l f13313c;
    public final HashMap<String, m> a = new HashMap<>();

    private l() {
    }

    public static int a() {
        return b.incrementAndGet();
    }

    public static l c() {
        if (f13313c == null) {
            synchronized (l.class) {
                if (f13313c == null) {
                    f13313c = new l();
                }
            }
        }
        return f13313c;
    }

    public void a(String str) {
        HashMap<WebSocket, Boolean> hashMap;
        m b2 = b(str);
        if (b2 == null || (hashMap = b2.a) == null) {
            return;
        }
        if (hashMap != null && hashMap.size() > 0) {
            Iterator<Map.Entry<WebSocket, Boolean>> it = b2.a.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getKey().close(1000, "app close");
            }
        }
        this.a.remove(str);
    }

    public final m b(String str) {
        if (this.a.containsKey(str)) {
            return this.a.get(str);
        }
        return null;
    }

    public void b() {
        HashMap<WebSocket, Boolean> hashMap;
        HashMap<String, m> hashMap2 = this.a;
        if (hashMap2 != null) {
            try {
                Iterator<Map.Entry<String, m>> it = hashMap2.entrySet().iterator();
                while (it.hasNext()) {
                    m value = it.next().getValue();
                    if (value != null && (hashMap = value.a) != null && hashMap.size() > 0) {
                        Iterator<Map.Entry<WebSocket, Boolean>> it2 = value.a.entrySet().iterator();
                        while (it2.hasNext()) {
                            it2.next().getKey().close(1000, "app close");
                        }
                    }
                }
            } catch (Exception unused) {
            } catch (Throwable th) {
                this.a.clear();
                throw th;
            }
            this.a.clear();
        }
    }
}
