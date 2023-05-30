package com.xiaomi.push.service;

import android.os.SystemClock;
import android.text.TextUtils;
import com.xiaomi.push.o4;
import com.xiaomi.push.p4;
import com.xiaomi.push.service.XMPushService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes11.dex */
public class t0 {
    private final ConcurrentHashMap<String, c> a = new ConcurrentHashMap<>();

    /* loaded from: classes11.dex */
    public static class a extends XMPushService.j {
        public a() {
            super(17);
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public void a() {
            t0.a().b();
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public String b() {
            return "RecordTimeManager clear";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class b {
        private static final t0 a = new t0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class c {
        long a;
        long b;

        /* renamed from: c  reason: collision with root package name */
        long f19191c;
        long d;

        private c() {
        }

        public long a() {
            long j2 = this.f19191c;
            long j3 = this.b;
            if (j2 > j3) {
                return j2 - j3;
            }
            return 0L;
        }

        public long b() {
            long j2 = this.d;
            long j3 = this.f19191c;
            if (j2 > j3) {
                return j2 - j3;
            }
            return 0L;
        }
    }

    public static t0 a() {
        return b.a;
    }

    private void e(String str, c cVar) {
        if (TextUtils.isEmpty(str) || cVar == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("xmsfVC", Long.valueOf(cVar.a));
        hashMap.put("packetId", str);
        hashMap.put("pTime", Long.valueOf(cVar.a()));
        hashMap.put("bTime", Long.valueOf(cVar.b()));
        p4.b().a(new o4("msg_process_time", hashMap));
    }

    public void b() {
        if (this.a.isEmpty()) {
            return;
        }
        Iterator<Map.Entry<String, c>> it = this.a.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, c> next = it.next();
            if (next != null && next.getValue() != null) {
                c value = next.getValue();
                if (Math.abs(SystemClock.elapsedRealtime() - value.b) > 10000) {
                    e(next.getKey(), value);
                }
            }
            it.remove();
        }
    }

    public void c(String str, long j2) {
        c cVar = this.a.get(str);
        if (cVar != null) {
            cVar.f19191c = j2;
        }
    }

    public void d(String str, long j2, long j3) {
        c cVar = new c();
        cVar.a = j3;
        cVar.b = j2;
        this.a.put(str, cVar);
    }

    public void f(String str, long j2) {
        c remove = this.a.remove(str);
        if (remove != null) {
            remove.d = j2;
            e(str, remove);
        }
    }
}
