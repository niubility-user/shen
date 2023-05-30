package com.tencent.mapsdk.internal;

import android.text.TextUtils;
import com.facebook.cache.disk.DefaultDiskStorage;
import com.tencent.map.sdk.comps.offlinemap.OfflineItem;
import com.tencent.map.sdk.comps.offlinemap.OfflineItemController;
import com.tencent.map.sdk.comps.offlinemap.OfflineStatus;
import com.tencent.map.sdk.comps.offlinemap.OfflineStatusChangedListener;
import com.tencent.mapsdk.internal.ba;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/* loaded from: classes9.dex */
public class z1 implements OfflineItemController {
    private final hc a;
    private final a2 b;

    /* renamed from: c  reason: collision with root package name */
    private final OfflineItem f17533c;
    private final String d;

    /* renamed from: e  reason: collision with root package name */
    private final File f17534e;

    /* renamed from: f  reason: collision with root package name */
    private final File f17535f;

    /* renamed from: g  reason: collision with root package name */
    private final File f17536g;

    /* renamed from: h  reason: collision with root package name */
    private String f17537h;

    /* renamed from: i  reason: collision with root package name */
    private final jb f17538i;

    /* renamed from: j  reason: collision with root package name */
    private final e f17539j;

    /* renamed from: k  reason: collision with root package name */
    private OfflineStatusChangedListener f17540k;

    /* renamed from: l  reason: collision with root package name */
    private d f17541l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f17542m;

    /* renamed from: n  reason: collision with root package name */
    private final o1 f17543n;

    /* loaded from: classes9.dex */
    public class a extends ba.c<Boolean> {
        public a() {
        }

        @Override // com.tencent.mapsdk.internal.ba.c, com.tencent.map.tools.Callback
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callback(Boolean bool) {
            if (!bool.booleanValue()) {
                z1.this.startDownload();
            } else if (z1.this.f17540k != null) {
                z1.this.f17540k.onStatusChanged(z1.this.f17533c, OfflineStatus.COMPLETED);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class b extends ba.i<Boolean> {
        public b() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Boolean call() {
            z1 z1Var = z1.this;
            return Boolean.valueOf(z1Var.b(z1Var.f17543n));
        }
    }

    /* loaded from: classes9.dex */
    public class c implements FilenameFilter {
        public c() {
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return z1.this.f17535f.getName().equals(str);
        }
    }

    /* loaded from: classes9.dex */
    public interface d {
        void a(OfflineItem offlineItem, int i2);

        void a(OfflineItem offlineItem, boolean z);

        void b(OfflineItem offlineItem, boolean z);
    }

    /* loaded from: classes9.dex */
    public class e extends lb implements ib {
        private kb d;

        /* renamed from: e  reason: collision with root package name */
        private File f17544e;

        private e() {
        }

        public /* synthetic */ e(z1 z1Var, a aVar) {
            this();
        }

        @Override // com.tencent.mapsdk.internal.ib
        public void a(String str) {
            File file = this.f17544e;
            if (file == null || !file.exists()) {
                return;
            }
            ma.c(la.u, "\u5b8c\u6210\u4e0b\u8f7d:[" + this.f17544e + "]");
            ma.c(la.u, "\u521b\u5efa\u57ce\u5e02\u7f13\u5b58\u6587\u4ef6:[" + z1.this.f17535f + "]");
            fa.d(z1.this.f17535f);
            fa.b(this.f17544e, z1.this.f17534e);
            z1.this.b();
            if (!z1.this.f17535f.exists()) {
                ma.g(la.u, "\u7f13\u5b58\u6587\u4ef6\u521b\u5efa\u5931\u8d25\uff01");
                return;
            }
            ma.c(la.u, "\u89e3\u538b\u6210\u529f:[" + z1.this.f17535f + "]");
            a2 a2Var = z1.this.b;
            a2Var.b(z1.this.a);
            ma.c(la.u, "\u4fdd\u6301\u57ce\u5e02\u7f13\u5b58\u4fe1\u606f:[" + a2Var + "]");
            z1.this.f17538i.b(z1.this.f17539j);
            if (z1.this.f17541l != null) {
                z1.this.f17541l.a(z1.this.f17533c, 100);
                z1.this.f17541l.b(z1.this.f17533c, false);
            }
            if (z1.this.f17540k != null) {
                z1.this.f17540k.onStatusChanged(z1.this.f17533c, OfflineStatus.COMPLETED);
            }
        }

        @Override // com.tencent.mapsdk.internal.ib
        public void a(String str, kb kbVar) {
            ma.c(la.u, "\u4e0b\u8f7d\u72b6\u6001\uff1a" + kbVar);
            this.d = kbVar;
        }

        @Override // com.tencent.mapsdk.internal.ib
        public void a(String str, byte[] bArr) {
            File file;
            if (TextUtils.isEmpty(str) || !str.equals(z1.this.f17537h) || bArr == null) {
                return;
            }
            if (this.d == kb.RUNNING && (file = this.f17544e) != null) {
                int length = (int) ((file.length() * 100) / z1.this.b.d);
                if (z1.this.f17541l != null) {
                    z1.this.f17541l.a(z1.this.f17533c, length);
                }
                ma.c(la.u, "\u7f13\u5b58\u6587\u4ef6\u4e0b\u8f7d\u4e2d:\uff1alength: " + this.f17544e.length() + "\uff1a" + length + "%");
                fa.a(this.f17544e, bArr);
            }
            if (z1.this.f17540k != null) {
                z1.this.f17540k.onStatusChanged(z1.this.f17533c, OfflineStatus.DOWNLOADING);
            }
        }

        @Override // com.tencent.mapsdk.internal.ib
        public void b(String str) {
            if (this.f17544e != null) {
                ma.g(la.u, "\u7f13\u5b58\u6587\u4ef6\u4e0b\u8f7d\u5931\u8d25\uff01");
                fa.d(this.f17544e);
            }
            if (z1.this.f17540k != null) {
                z1.this.f17540k.onStatusChanged(z1.this.f17533c, OfflineStatus.ERROR);
            }
        }

        @Override // com.tencent.mapsdk.internal.ib
        public void c(String str) {
            if (this.f17544e != null) {
                ma.g(la.u, "\u53d6\u6d88\u4e0b\u8f7d:[" + this.f17544e + "]");
                fa.d(this.f17544e);
            }
            if (z1.this.f17540k != null) {
                z1.this.f17540k.onStatusChanged(z1.this.f17533c, OfflineStatus.CANCEL);
            }
        }

        @Override // com.tencent.mapsdk.internal.ib
        public void d(String str) {
            this.f17544e = new File(z1.this.d, c() + DefaultDiskStorage.FileType.TEMP);
            ma.c(la.u, "\u5f00\u59cb\u4e0b\u8f7d:[" + this.f17544e + "]");
            if (z1.this.f17540k != null) {
                z1.this.f17540k.onStatusChanged(z1.this.f17533c, OfflineStatus.START);
            }
        }
    }

    public z1(o1 o1Var, String str, OfflineItem offlineItem, a2 a2Var, hc hcVar, OfflineStatusChangedListener offlineStatusChangedListener) {
        this.f17543n = o1Var;
        this.a = hcVar;
        this.f17533c = offlineItem;
        this.b = a2Var;
        this.f17540k = offlineStatusChangedListener;
        jb jbVar = new jb();
        this.f17538i = jbVar;
        this.f17539j = new e(this, null);
        jbVar.a(g7.f());
        this.d = str;
        this.f17534e = new File(str, a2Var.c());
        this.f17535f = new File(str, a2Var.a());
        this.f17536g = new File(lc.b(o1Var.getContext()).d(), a2Var.a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.f17534e.exists()) {
            try {
                ma.c(la.u, "\u5f00\u59cb\u7f13\u5b58\u6587\u4ef6\u6821\u9a8c...");
                String a2 = va.a(this.f17534e);
                ma.c(la.u, "\u7ed3\u675f\u7f13\u5b58\u6587\u4ef6\u6821\u9a8c...");
                if (this.b.b.equals(a2)) {
                    fa.d(this.f17535f);
                    ia.a(this.f17534e, this.f17535f.getParent(), new c());
                    return;
                }
                ma.g(la.u, "\u7f13\u5b58\u6587\u4ef6MD5\u4e0d\u4e00\u81f4\uff01");
                fa.d(this.f17534e);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a() {
        this.f17541l = null;
        this.f17540k = null;
    }

    public void a(OfflineStatusChangedListener offlineStatusChangedListener) {
        this.f17540k = offlineStatusChangedListener;
    }

    public void a(d dVar) {
        this.f17541l = dVar;
    }

    public synchronized boolean a(o1 o1Var) {
        if (this.f17536g.exists() && o1Var != null) {
            t1 o = o1Var.o();
            o.a();
            fa.b(this.f17536g, this.f17535f);
            o.e();
            o.c();
            d dVar = this.f17541l;
            if (dVar != null) {
                dVar.a(this.f17533c, false);
            }
            OfflineStatusChangedListener offlineStatusChangedListener = this.f17540k;
            if (offlineStatusChangedListener != null) {
                offlineStatusChangedListener.onStatusChanged(this.f17533c, OfflineStatus.CLOSE);
            }
            this.f17542m = false;
            ma.c(la.u, "\u5173\u95ed[" + this.b.f16220c + "]\u79bb\u7ebf");
            return true;
        }
        return false;
    }

    public synchronized boolean b(o1 o1Var) {
        if (o1Var == null) {
            return false;
        }
        b();
        if (this.f17535f.exists()) {
            t1 o = o1Var.o();
            o.a();
            fa.b(this.f17535f, this.f17536g);
            o.e();
            o.c();
            this.f17542m = true;
            d dVar = this.f17541l;
            if (dVar != null) {
                dVar.a(this.f17533c, true);
            }
            OfflineStatusChangedListener offlineStatusChangedListener = this.f17540k;
            if (offlineStatusChangedListener != null) {
                offlineStatusChangedListener.onStatusChanged(this.f17533c, OfflineStatus.OPEN);
            }
            ma.c(la.u, "\u5f00\u542f[" + this.b.f16220c + "]\u79bb\u7ebf");
            return true;
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x004c, code lost:
        if (r4.f17535f.exists() == false) goto L15;
     */
    @Override // com.tencent.map.sdk.comps.offlinemap.OfflineItemController
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean checkInvalidate() {
        /*
            r4 = this;
            monitor-enter(r4)
            com.tencent.mapsdk.internal.a2 r0 = r4.b     // Catch: java.lang.Throwable -> L5c
            r1 = 0
            if (r0 == 0) goto L51
            java.lang.String r2 = r0.b()     // Catch: java.lang.Throwable -> L5c
            r4.f17537h = r2     // Catch: java.lang.Throwable -> L5c
            com.tencent.mapsdk.internal.hc r2 = r4.a     // Catch: java.lang.Throwable -> L5c
            boolean r0 = r0.a(r2)     // Catch: java.lang.Throwable -> L5c
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5c
            r2.<init>()     // Catch: java.lang.Throwable -> L5c
            java.lang.String r3 = "\u68c0\u67e5\u662f\u5426\u9700\u8981\u66f4\u65b0:["
            r2.append(r3)     // Catch: java.lang.Throwable -> L5c
            r2.append(r0)     // Catch: java.lang.Throwable -> L5c
            java.lang.String r3 = "]"
            r2.append(r3)     // Catch: java.lang.Throwable -> L5c
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L5c
            java.lang.String r3 = "OM"
            com.tencent.mapsdk.internal.ma.c(r3, r2)     // Catch: java.lang.Throwable -> L5c
            if (r0 != 0) goto L50
            boolean r2 = r4.f17542m     // Catch: java.lang.Throwable -> L5c
            if (r2 != 0) goto L50
            java.io.File r2 = r4.f17535f     // Catch: java.lang.Throwable -> L5c
            boolean r2 = r2.exists()     // Catch: java.lang.Throwable -> L5c
            if (r2 != 0) goto L50
            java.io.File r0 = r4.f17534e     // Catch: java.lang.Throwable -> L5c
            boolean r0 = r0.exists()     // Catch: java.lang.Throwable -> L5c
            if (r0 == 0) goto L4e
            r4.b()     // Catch: java.lang.Throwable -> L5c
            java.io.File r0 = r4.f17535f     // Catch: java.lang.Throwable -> L5c
            boolean r0 = r0.exists()     // Catch: java.lang.Throwable -> L5c
            if (r0 != 0) goto L51
        L4e:
            r1 = 1
            goto L51
        L50:
            r1 = r0
        L51:
            com.tencent.mapsdk.internal.z1$d r0 = r4.f17541l     // Catch: java.lang.Throwable -> L5c
            if (r0 == 0) goto L5a
            com.tencent.map.sdk.comps.offlinemap.OfflineItem r2 = r4.f17533c     // Catch: java.lang.Throwable -> L5c
            r0.b(r2, r1)     // Catch: java.lang.Throwable -> L5c
        L5a:
            monitor-exit(r4)
            return r1
        L5c:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.internal.z1.checkInvalidate():boolean");
    }

    @Override // com.tencent.map.sdk.comps.offlinemap.OfflineItemController
    public boolean close() {
        return a(this.f17543n);
    }

    @Override // com.tencent.map.sdk.comps.offlinemap.OfflineItemController
    public boolean open() {
        return b(this.f17543n);
    }

    @Override // com.tencent.map.sdk.comps.offlinemap.OfflineItemController
    public boolean removeCache() {
        boolean d2 = fa.d(this.f17534e);
        ma.c(la.u, "\u5220\u9664[" + this.b.f16220c + "]\u79bb\u7ebf\u7f13\u5b58");
        return d2;
    }

    @Override // com.tencent.map.sdk.comps.offlinemap.OfflineItemController
    public void startDownload() {
        if (this.f17543n == null) {
            OfflineStatusChangedListener offlineStatusChangedListener = this.f17540k;
            if (offlineStatusChangedListener != null) {
                offlineStatusChangedListener.onStatusChanged(this.f17533c, OfflineStatus.ERROR);
            }
        } else if (this.f17534e.exists()) {
            ba.a((ba.i) new b()).b(null, new a());
        } else if (!checkInvalidate() || TextUtils.isEmpty(this.f17537h)) {
            OfflineStatusChangedListener offlineStatusChangedListener2 = this.f17540k;
            if (offlineStatusChangedListener2 != null) {
                offlineStatusChangedListener2.onStatusChanged(this.f17533c, OfflineStatus.ERROR);
            }
        } else {
            if (this.f17540k != null) {
                this.f17538i.a(this.f17539j);
            }
            ma.c(la.u, "\u8bf7\u6c42\u4e0b\u8f7d:[" + this.f17537h + "]");
            this.f17538i.a(this.f17537h, this.f17539j);
            OfflineStatusChangedListener offlineStatusChangedListener3 = this.f17540k;
            if (offlineStatusChangedListener3 != null) {
                offlineStatusChangedListener3.onStatusChanged(this.f17533c, OfflineStatus.READY);
            }
        }
    }

    @Override // com.tencent.map.sdk.comps.offlinemap.OfflineItemController
    public void stopDownload() {
        if (TextUtils.isEmpty(this.f17537h)) {
            return;
        }
        ma.c(la.u, "\u505c\u6b62\u4e0b\u8f7d:[" + this.f17537h + "]");
        this.f17538i.b(this.f17537h);
    }
}
