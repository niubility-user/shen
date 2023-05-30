package com.tencent.mapsdk.internal;

import android.content.Context;
import com.tencent.mapsdk.core.components.protocol.jce.conf.CSFileUpdateReq;
import com.tencent.mapsdk.core.components.protocol.jce.conf.FileUpdateReq;
import com.tencent.mapsdk.core.components.protocol.jce.conf.FileUpdateRsp;
import com.tencent.mapsdk.vector.VectorMap;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class qf {

    /* renamed from: k */
    private static volatile Map<String, List<WeakReference<h1>>> f17066k = new HashMap();
    public volatile boolean a = false;
    private String b;

    /* renamed from: c */
    private hc f17067c;
    private lc d;

    /* renamed from: e */
    private WeakReference<h1> f17068e;

    /* renamed from: f */
    private String f17069f;

    /* renamed from: g */
    private String f17070g;

    /* renamed from: h */
    private String f17071h;

    /* renamed from: i */
    private String f17072i;

    /* renamed from: j */
    private String f17073j;

    /* loaded from: classes9.dex */
    public static class a implements Runnable {
        private final WeakReference<qf> a;
        private final String b;

        /* renamed from: c */
        private final r5 f17074c;

        public a(qf qfVar, String str, r5 r5Var) {
            this.a = new WeakReference<>(qfVar);
            this.b = str;
            this.f17074c = r5Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            WeakReference<qf> weakReference = this.a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            qf qfVar = this.a.get();
            List<FileUpdateRsp> b = qfVar.b(this.b, this.f17074c);
            if (b == null) {
                qfVar.a = false;
                qfVar.a(false);
                return;
            }
            if (qfVar.a) {
                if (!qfVar.a(qfVar.f17072i, qfVar.f17070g) || !qfVar.a(qfVar.f17073j, qfVar.f17071h)) {
                    qfVar.a = false;
                    qfVar.a(false);
                    return;
                }
                for (int i2 = 0; i2 < b.size(); i2++) {
                    qfVar.a(b.get(i2));
                }
            }
            qfVar.a(true);
        }
    }

    public qf(Context context, h1 h1Var, String str) {
        this.d = lc.b(context);
        this.b = "";
        if (h1Var != null && h1Var.l() != null && h1Var.l().getMap() != null) {
            this.b = h1Var.l().getMap().y();
        }
        this.f17068e = new WeakReference<>(h1Var);
        this.f17069f = str;
        this.f17067c = jc.a(context, str);
        a();
    }

    private void a() {
        hc hcVar = this.f17067c;
        if (hcVar == null) {
            return;
        }
        hcVar.a(new String[]{"mapPoiIconIndoorVersion", "poiIconIndoorMd5"});
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0060, code lost:
        if (r0.equals(com.tencent.mapsdk.internal.j4.f16733m) == false) goto L59;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(com.tencent.mapsdk.core.components.protocol.jce.conf.FileUpdateRsp r5) {
        /*
            Method dump skipped, instructions count: 300
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.internal.qf.a(com.tencent.mapsdk.core.components.protocol.jce.conf.FileUpdateRsp):void");
    }

    private synchronized void a(String str, WeakReference<h1> weakReference) {
        if (f17066k.containsKey(str)) {
            List<WeakReference<h1>> list = f17066k.get(str);
            if (list != null) {
                list.add(weakReference);
            }
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.add(weakReference);
            f17066k.put(str, arrayList);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x0095, code lost:
        if (r9 != null) goto L69;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(boolean r13) {
        /*
            r12 = this;
            com.tencent.mapsdk.internal.hc r0 = r12.f17067c
            long r1 = java.lang.System.currentTimeMillis()
            java.lang.String r3 = "mapConfigLastCheckTime"
            r0.a(r3, r1)
            java.lang.String r0 = r12.f17072i
            com.tencent.mapsdk.internal.ga.a(r0)
            java.lang.String r0 = r12.f17073j
            com.tencent.mapsdk.internal.ga.a(r0)
            long r0 = java.lang.System.currentTimeMillis()
            if (r13 != 0) goto L1d
            r0 = 0
        L1d:
            java.util.Map<java.lang.String, java.util.List<java.lang.ref.WeakReference<com.tencent.mapsdk.internal.h1>>> r2 = com.tencent.mapsdk.internal.qf.f17066k
            java.lang.String r3 = r12.b()
            java.lang.Object r2 = r2.get(r3)
            java.util.List r2 = (java.util.List) r2
            if (r2 != 0) goto L2c
            return
        L2c:
            int r3 = r2.size()
            java.lang.ref.WeakReference[] r4 = new java.lang.ref.WeakReference[r3]
            java.lang.Object[] r2 = r2.toArray(r4)
            java.lang.ref.WeakReference[] r2 = (java.lang.ref.WeakReference[]) r2
            r4 = 0
            r5 = 0
        L3a:
            if (r5 >= r3) goto La4
            r6 = r2[r5]
            if (r6 != 0) goto L41
            goto La1
        L41:
            r6 = r2[r5]
            java.lang.Object r6 = r6.get()
            com.tencent.mapsdk.internal.h1 r6 = (com.tencent.mapsdk.internal.h1) r6
            if (r6 == 0) goto La1
            com.tencent.mapsdk.internal.xi r7 = r6.l()
            if (r7 == 0) goto La1
            com.tencent.mapsdk.internal.m1 r8 = r7.getMap()
            if (r8 == 0) goto La1
            com.tencent.mapsdk.internal.m1 r8 = r7.getMap()
            com.tencent.mapsdk.vector.VectorMap r8 = (com.tencent.mapsdk.vector.VectorMap) r8
            com.tencent.mapsdk.internal.qc r9 = r7.A()
            com.tencent.mapsdk.internal.v6 r9 = r9.w()
            boolean r10 = r12.a
            r11 = 1
            if (r10 == 0) goto L93
            r8.t0()
            com.tencent.mapsdk.internal.xg r10 = r6.m()
            if (r10 == 0) goto L80
            com.tencent.mapsdk.internal.xg r6 = r6.m()
            com.tencent.mapsdk.internal.o1 r10 = r7.getMapContext()
            com.tencent.mapsdk.internal.qc r10 = (com.tencent.mapsdk.internal.qc) r10
            r6.a(r10)
        L80:
            r8.v0()
            r7.b(r11)
            r7.l(r11)
            if (r9 == 0) goto L9e
            com.tencent.mapsdk.internal.r6 r6 = r9.l()
            r6.b(r4, r0)
            goto L97
        L93:
            if (r13 != 0) goto L9e
            if (r9 == 0) goto L9e
        L97:
            com.tencent.mapsdk.internal.r6 r6 = r9.l()
            r6.a(r13, r0)
        L9e:
            r8.c(r11)
        La1:
            int r5 = r5 + 1
            goto L3a
        La4:
            java.util.Map<java.lang.String, java.util.List<java.lang.ref.WeakReference<com.tencent.mapsdk.internal.h1>>> r13 = com.tencent.mapsdk.internal.qf.f17066k
            r13.clear()
            com.tencent.mapsdk.internal.jc.c()
            java.lang.String r13 = "REQ_CONFIG_UPDATE"
            com.tencent.mapsdk.internal.qa.i(r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.internal.qf.a(boolean):void");
    }

    public boolean a(String str, String str2) {
        h1 h1Var;
        FileInputStream fileInputStream;
        byte[] bArr;
        boolean b;
        WeakReference<h1> weakReference = this.f17068e;
        if (weakReference != null && (h1Var = weakReference.get()) != null && h1Var.l() != null && h1Var.l().getMap() != null) {
            VectorMap map = h1Var.l().getMap();
            File file = new File(str);
            if (file.exists() && file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles == null) {
                    return false;
                }
                FileInputStream fileInputStream2 = null;
                boolean z = true;
                for (File file2 : listFiles) {
                    try {
                        fileInputStream = new FileInputStream(file2);
                        try {
                            int length = (int) file2.length();
                            bArr = new byte[length];
                            fileInputStream.read(bArr, 0, length);
                        } catch (FileNotFoundException | IOException unused) {
                        } catch (Throwable th) {
                            th = th;
                            fileInputStream2 = fileInputStream;
                            ga.a((Closeable) fileInputStream2);
                            throw th;
                        }
                    } catch (FileNotFoundException | IOException unused2) {
                    } catch (Throwable th2) {
                        th = th2;
                    }
                    if (str2.equals(this.f17070g)) {
                        b = map.a(file2.getName(), bArr);
                    } else {
                        if (str2.equals(this.f17071h)) {
                            b = map.b(file2.getName(), bArr);
                        }
                        fileInputStream2 = fileInputStream;
                        ga.a((Closeable) fileInputStream2);
                    }
                    z = b & z;
                    fileInputStream2 = fileInputStream;
                    ga.a((Closeable) fileInputStream2);
                }
                return z;
            }
            ma.b("Config temp dir not exists:" + str);
        }
        return false;
    }

    private String b() {
        String str = this.f17069f;
        return e7.b(str) ? b7.t() : str;
    }

    public void a(String str, r5 r5Var) {
        String b = b();
        if (f17066k.containsKey(b)) {
            a(b, this.f17068e);
            return;
        }
        a(b, this.f17068e);
        qa.h(pa.V);
        ba.a(new a(this, str, r5Var));
    }

    public List<FileUpdateRsp> b(String str, r5 r5Var) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new FileUpdateReq(j4.f16729i, this.f17067c.b(l4.a), this.f17067c.d(l4.t)));
        arrayList.add(new FileUpdateReq("poi_icon", this.f17067c.b(l4.f16790c), this.f17067c.d(l4.u)));
        arrayList.add(new FileUpdateReq(j4.f16730j, this.f17067c.b(l4.d), this.f17067c.d(l4.v)));
        arrayList.add(new FileUpdateReq(j4.p, this.f17067c.b("escalator_night_version"), this.f17067c.d("escalator_night_md5")));
        if (r5Var != null && r5Var.e()) {
            arrayList.add(new FileUpdateReq(j4.f16732l, this.f17067c.b("indoormap_style_version"), this.f17067c.d("indoormap_style_md5")));
            arrayList.add(new FileUpdateReq(j4.f16733m, this.f17067c.b("indoormap_style_night_version"), this.f17067c.d("indoormap_style_night_md5")));
            arrayList.add(new FileUpdateReq(j4.f16734n, this.f17067c.b(l4.s), this.f17067c.d(l4.y)));
            arrayList.add(new FileUpdateReq(j4.o, this.f17067c.b("indoorpoi_icon_3d_night_version"), this.f17067c.d("indoorpoi_icon_3d_night_md5")));
        }
        String b = b();
        CSFileUpdateReq cSFileUpdateReq = new CSFileUpdateReq(arrayList, b, b7.E(), null, this.b, str);
        this.f17070g = this.d.c(this.f17069f);
        this.f17071h = this.d.a(this.f17069f);
        this.f17072i = this.d.d(this.f17069f) + "config/";
        this.f17073j = this.d.d(this.f17069f) + "assets/";
        ga.b(this.f17072i);
        ga.b(this.f17073j);
        return new of().a(this.d.d(this.f17069f) + "config/", this.d.d(this.f17069f) + "assets/", b, cSFileUpdateReq, this);
    }

    public WeakReference<h1>[] c() {
        List<WeakReference<h1>> list = f17066k.get(b());
        if (list == null) {
            return null;
        }
        return (WeakReference[]) list.toArray(new WeakReference[list.size()]);
    }
}
