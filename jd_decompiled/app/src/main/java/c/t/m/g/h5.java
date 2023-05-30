package c.t.m.g;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import com.google.common.net.HttpHeaders;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.map.geolocation.util.SoUtils;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes.dex */
public class h5 {
    public final LinkedBlockingQueue<b> a = new LinkedBlockingQueue<>(3);
    public final y4 b;

    /* renamed from: c  reason: collision with root package name */
    public long f474c;
    public long d;

    /* renamed from: e  reason: collision with root package name */
    public long f475e;

    /* renamed from: f  reason: collision with root package name */
    public long f476f;

    /* renamed from: g  reason: collision with root package name */
    public volatile boolean f477g;

    /* renamed from: h  reason: collision with root package name */
    public boolean f478h;

    /* renamed from: i  reason: collision with root package name */
    public volatile Handler f479i;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h5.this.a.clear();
            h5.this.j();
        }
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: h  reason: collision with root package name */
        public static final b f481h = new b();
        public final int a;
        public final byte[] b;

        /* renamed from: c  reason: collision with root package name */
        public final String f482c;
        public final Object d;

        /* renamed from: e  reason: collision with root package name */
        public int f483e;

        /* renamed from: f  reason: collision with root package name */
        public String f484f;

        /* renamed from: g  reason: collision with root package name */
        public long f485g;

        public b() {
            this.f483e = 1;
            this.a = 0;
            this.b = null;
            this.f482c = null;
            this.d = null;
        }

        public b(int i2, byte[] bArr, String str, Object obj) {
            this.f483e = 1;
            this.a = i2;
            this.b = bArr;
            this.f482c = str;
            this.d = obj;
        }

        public static /* synthetic */ byte[] a(b bVar) {
            return bVar.b;
        }

        public static /* synthetic */ int b(b bVar) {
            return bVar.a;
        }

        public static /* synthetic */ String c(b bVar) {
            return bVar.f482c;
        }

        public static /* synthetic */ int e(b bVar) {
            int i2 = bVar.f483e;
            bVar.f483e = i2 - 1;
            return i2;
        }
    }

    public h5(y4 y4Var) {
        this.b = y4Var;
    }

    public final String a(byte[] bArr, int i2) {
        if (!y1.a() && bArr != null) {
            try {
                if (SoUtils.fun_o(bArr, 1) >= 0) {
                    return m2.a(1, i2, 1);
                }
            } catch (UnsatisfiedLinkError unused) {
                return null;
            }
        }
        return m2.a(1, i2, 0);
    }

    public final void c() {
        this.f474c = 0L;
        this.d = 0L;
        this.f475e = 0L;
        this.f476f = 0L;
    }

    public void d(Handler handler, boolean z) {
        if (this.f477g) {
            return;
        }
        this.f477g = true;
        this.f479i = handler;
        this.f478h = z;
        this.b.m().execute(new a());
        this.f476f = SystemClock.elapsedRealtime();
    }

    public final void e(b bVar, String str) {
        this.f474c++;
        this.d += bVar.b.length;
        this.f475e += c1.c(str.getBytes("UTF-8")) != null ? r5.length : 0;
    }

    public void f(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            byte[] n2 = u0.n(str.getBytes("UTF-8"));
            SoUtils.fun_o(n2, 2);
            b bVar = new b(3, n2, "https://ue.indoorloc.map.qq.com/?wl", null);
            bVar.f484f = str;
            i(bVar);
        } catch (Throwable unused) {
        }
    }

    public void g(String str, v vVar, int i2) {
        try {
            byte[] c2 = c1.c(str.getBytes("GBK"));
            b bVar = new b(1, c2, a(c2, i2), vVar);
            bVar.f484f = str;
            i(bVar);
        } catch (Throwable unused) {
        }
    }

    public final void h(String str, b bVar, Message message) {
        if (1 == bVar.a) {
            message.obj = Pair.create(str, bVar);
            message.what = R2.color.wmpf_ef_colorPrimary;
            message.sendToTarget();
        }
    }

    public final boolean i(b bVar) {
        boolean offer = bVar.b != null ? this.a.offer(bVar) : false;
        if (!offer) {
            this.a.clear();
            this.a.offer(bVar);
            String str = bVar.a == 2 ? "List" : bVar.a == 3 ? "Wifis" : HttpHeaders.LOCATION;
            StringBuilder sb = new StringBuilder(IMantoServerRequester.POST);
            sb.append(str);
            sb.append("Request: failed to add request,because the queue has full,so we delete the first");
        }
        return offer;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public final void j() {
        /*
            r15 = this;
            java.lang.String r0 = "cost:"
            java.util.concurrent.LinkedBlockingQueue<c.t.m.g.h5$b> r1 = r15.a
            r2 = 0
            r3 = r2
        L6:
            boolean r4 = r15.f477g
            if (r4 == 0) goto Ldc
            r4 = 0
            java.lang.Object r6 = r1.take()     // Catch: java.lang.Throwable -> La6
            c.t.m.g.h5$b r6 = (c.t.m.g.h5.b) r6     // Catch: java.lang.Throwable -> La6
            c.t.m.g.h5$b r3 = c.t.m.g.h5.b.f481h     // Catch: java.lang.Throwable -> La0
            if (r3 != r6) goto L17
            return
        L17:
            long r7 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> La0
            c.t.m.g.y4 r3 = r15.b     // Catch: java.io.IOException -> La4
            java.lang.String r9 = c.t.m.g.h5.b.c(r6)     // Catch: java.io.IOException -> La4
            byte[] r10 = c.t.m.g.h5.b.a(r6)     // Catch: java.io.IOException -> La4
            r11 = 1
            android.os.Bundle r3 = r3.a(r9, r10, r11)     // Catch: java.io.IOException -> La4
            java.lang.String r9 = "result"
            java.lang.String r9 = r3.getString(r9)     // Catch: java.io.IOException -> La4
            long r12 = java.lang.System.currentTimeMillis()     // Catch: java.io.IOException -> La4
            long r12 = r12 - r7
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.io.IOException -> La4
            r10.<init>(r0)     // Catch: java.io.IOException -> La4
            r10.append(r12)     // Catch: java.io.IOException -> La4
            java.lang.String r14 = ",result:"
            r10.append(r14)     // Catch: java.io.IOException -> La4
            r10.append(r9)     // Catch: java.io.IOException -> La4
            if (r9 != 0) goto L4a
            java.lang.String r9 = ""
        L4a:
            r15.e(r6, r9)     // Catch: java.io.IOException -> La4
            android.os.Handler r10 = r15.f479i     // Catch: java.io.IOException -> La4
            r6.f485g = r7     // Catch: java.io.IOException -> La4
            boolean r14 = r15.f478h     // Catch: java.io.IOException -> La4
            if (r14 != 0) goto L73
            boolean r14 = r15.f477g     // Catch: java.io.IOException -> La4
            if (r14 == 0) goto L73
            if (r10 == 0) goto L73
            android.os.Looper r14 = r10.getLooper()     // Catch: java.io.IOException -> La4
            java.lang.Thread r14 = r14.getThread()     // Catch: java.io.IOException -> La4
            boolean r14 = r14.isAlive()     // Catch: java.io.IOException -> La4
            if (r14 == 0) goto L73
            android.os.Message r10 = r10.obtainMessage()     // Catch: java.io.IOException -> La4
            int r14 = (int) r12     // Catch: java.io.IOException -> La4
            r10.arg1 = r14     // Catch: java.io.IOException -> La4
            r15.h(r9, r6, r10)     // Catch: java.io.IOException -> La4
        L73:
            int r9 = c.t.m.g.h5.b.b(r6)     // Catch: java.io.IOException -> La4
            if (r9 != r11) goto La0
            c.t.m.g.y4 r9 = r15.b     // Catch: java.io.IOException -> La4
            c.t.m.g.g5 r9 = r9.c()     // Catch: java.io.IOException -> La4
            long r9 = r9.w()     // Catch: java.io.IOException -> La4
            int r11 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r11 != 0) goto La0
            c.t.m.g.y4 r9 = r15.b     // Catch: java.io.IOException -> La4
            c.t.m.g.g5 r9 = r9.c()     // Catch: java.io.IOException -> La4
            r9.c(r12)     // Catch: java.io.IOException -> La4
            c.t.m.g.y4 r9 = r15.b     // Catch: java.io.IOException -> La4
            c.t.m.g.g5 r9 = r9.c()     // Catch: java.io.IOException -> La4
            java.lang.String r10 = "req_key"
            java.lang.String r3 = r3.getString(r10)     // Catch: java.io.IOException -> La4
            r9.n(r3)     // Catch: java.io.IOException -> La4
        La0:
            r3 = r6
            goto L6
        La3:
            r7 = r4
        La4:
            r3 = r6
            goto Laa
        La6:
            goto L6
        La9:
            r7 = r4
        Laa:
            long r9 = java.lang.System.currentTimeMillis()
            long r9 = r9 - r7
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r0)
            r6.append(r9)
            java.lang.String r7 = ",run: io error"
            r6.append(r7)
            r15.k(r3)
            android.os.Handler r6 = r15.f479i
            if (r6 != 0) goto Lc5
            r7 = r2
            goto Lc9
        Lc5:
            android.os.Looper r7 = r6.getLooper()
        Lc9:
            if (r7 == 0) goto L6
            java.lang.Thread r7 = r7.getThread()
            boolean r7 = r7.isAlive()
            if (r7 == 0) goto L6
            r7 = 4998(0x1386, float:7.004E-42)
            r6.sendEmptyMessageDelayed(r7, r4)
            goto L6
        Ldc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.t.m.g.h5.j():void");
    }

    public final void k(b bVar) {
        boolean z;
        b.e(bVar);
        Iterator<b> it = this.a.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            } else if (it.next().a == bVar.a) {
                z = true;
                break;
            }
        }
        if (bVar.f483e <= 0 || z) {
            return;
        }
        new StringBuilder("retryIfNeed: times=").append(bVar.f483e);
        this.a.offer(bVar);
    }

    public void m() {
        if (this.f477g) {
            this.f477g = false;
            this.a.clear();
            this.a.offer(b.f481h);
            this.f479i = null;
            if (this.f476f != 0) {
                String.format(Locale.ENGLISH, "shutdown: duration=%ds, sent=%dB, recv=%dB, reqCount=%d", Long.valueOf((SystemClock.elapsedRealtime() - this.f476f) / 1000), Long.valueOf(this.d), Long.valueOf(this.f475e), Long.valueOf(this.f474c));
            }
            c();
        }
    }
}
