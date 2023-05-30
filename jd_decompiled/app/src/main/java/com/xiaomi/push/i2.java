package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.xiaomi.push.l;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class i2 {

    /* renamed from: c  reason: collision with root package name */
    private static volatile i2 f18731c;
    private final ConcurrentLinkedQueue<b> a;
    private Context b;

    /* loaded from: classes11.dex */
    class a extends b {
        a() {
            super(i2.this);
        }

        @Override // com.xiaomi.push.i2.b, com.xiaomi.push.l.b
        public void b() {
            i2.this.i();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public class b extends l.b {
        long a = System.currentTimeMillis();

        /* JADX INFO: Access modifiers changed from: package-private */
        public b(i2 i2Var) {
        }

        @Override // com.xiaomi.push.l.b
        public void b() {
        }

        public boolean d() {
            return true;
        }

        final boolean e() {
            return System.currentTimeMillis() - this.a > 172800000;
        }
    }

    /* loaded from: classes11.dex */
    class c extends b {
        String b;

        /* renamed from: c  reason: collision with root package name */
        String f18732c;
        File d;

        /* renamed from: e  reason: collision with root package name */
        int f18733e;

        /* renamed from: f  reason: collision with root package name */
        boolean f18734f;

        /* renamed from: g  reason: collision with root package name */
        boolean f18735g;

        /* JADX INFO: Access modifiers changed from: package-private */
        public c(String str, String str2, File file, boolean z) {
            super(i2.this);
            this.b = str;
            this.f18732c = str2;
            this.d = file;
            this.f18735g = z;
        }

        private boolean f() {
            int i2;
            int i3 = 0;
            SharedPreferences sharedPreferences = i2.this.b.getSharedPreferences("log.timestamp", 0);
            String string = sharedPreferences.getString("log.requst", "");
            long currentTimeMillis = System.currentTimeMillis();
            try {
                JSONObject jSONObject = new JSONObject(string);
                currentTimeMillis = jSONObject.getLong("time");
                i2 = jSONObject.getInt(VerifyTracker.KEY_TIMES);
            } catch (JSONException unused) {
                i2 = 0;
            }
            if (System.currentTimeMillis() - currentTimeMillis >= 86400000) {
                currentTimeMillis = System.currentTimeMillis();
            } else if (i2 > 10) {
                return false;
            } else {
                i3 = i2;
            }
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("time", currentTimeMillis);
                jSONObject2.put(VerifyTracker.KEY_TIMES, i3 + 1);
                sharedPreferences.edit().putString("log.requst", jSONObject2.toString()).commit();
            } catch (JSONException e2) {
                g.j.a.a.a.c.B("JSONException on put " + e2.getMessage());
            }
            return true;
        }

        @Override // com.xiaomi.push.i2.b, com.xiaomi.push.l.b
        public void b() {
            try {
                if (f()) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("uid", com.xiaomi.push.service.z0.g());
                    hashMap.put("token", this.f18732c);
                    hashMap.put("net", j0.g(i2.this.b));
                    j0.k(this.b, hashMap, this.d, "file");
                }
                this.f18734f = true;
            } catch (IOException unused) {
            }
        }

        @Override // com.xiaomi.push.l.b
        public void c() {
            if (!this.f18734f) {
                int i2 = this.f18733e + 1;
                this.f18733e = i2;
                if (i2 < 3) {
                    i2.this.a.add(this);
                }
            }
            if (this.f18734f || this.f18733e >= 3) {
                this.d.delete();
            }
            i2.this.e((1 << this.f18733e) * 1000);
        }

        @Override // com.xiaomi.push.i2.b
        public boolean d() {
            return j0.s(i2.this.b) || (this.f18735g && j0.p(i2.this.b));
        }
    }

    private i2(Context context) {
        ConcurrentLinkedQueue<b> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
        this.a = concurrentLinkedQueue;
        this.b = context;
        concurrentLinkedQueue.add(new a());
        j(0L);
    }

    public static i2 b(Context context) {
        if (f18731c == null) {
            synchronized (i2.class) {
                if (f18731c == null) {
                    f18731c = new i2(context);
                }
            }
        }
        f18731c.b = context;
        return f18731c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(long j2) {
        b peek = this.a.peek();
        if (peek == null || !peek.d()) {
            return;
        }
        j(j2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (w9.c() || w9.b()) {
            return;
        }
        try {
            File file = new File(this.b.getExternalFilesDir(null) + "/.logcache");
            if (file.exists() && file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    file2.delete();
                }
            }
        } catch (NullPointerException unused) {
        }
    }

    private void j(long j2) {
        if (this.a.isEmpty()) {
            return;
        }
        s6.b(new k2(this), j2);
    }

    private void k() {
        while (!this.a.isEmpty()) {
            b peek = this.a.peek();
            if (peek != null) {
                if (!peek.e() && this.a.size() <= 6) {
                    return;
                }
                g.j.a.a.a.c.B("remove Expired task");
                this.a.remove(peek);
            }
        }
    }

    public void d() {
        k();
        e(0L);
    }

    public void h(String str, String str2, Date date, Date date2, int i2, boolean z) {
        this.a.add(new j2(this, i2, date, date2, str, str2, z));
        j(0L);
    }
}
