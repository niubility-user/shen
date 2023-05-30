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
    /* JADX WARN: Code restructure failed: missing block: B:137:0x0060, code lost:
        if (r0.equals(com.tencent.mapsdk.internal.j4.f16733m) == false) goto L111;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(FileUpdateRsp fileUpdateRsp) {
        hc hcVar;
        String str;
        String str2;
        char c2 = 1;
        if (fileUpdateRsp.iFileUpdated != 1) {
            return;
        }
        String str3 = fileUpdateRsp.sName;
        str3.hashCode();
        str3.hashCode();
        switch (str3.hashCode()) {
            case -1319508241:
                if (str3.equals(j4.f16734n)) {
                    c2 = 0;
                    break;
                }
                c2 = '\uffff';
                break;
            case -1091367180:
                break;
            case -503063473:
                if (str3.equals(j4.p)) {
                    c2 = 2;
                    break;
                }
                c2 = '\uffff';
                break;
            case 178735484:
                if (str3.equals(j4.f16730j)) {
                    c2 = 3;
                    break;
                }
                c2 = '\uffff';
                break;
            case 204802075:
                if (str3.equals(j4.f16732l)) {
                    c2 = 4;
                    break;
                }
                c2 = '\uffff';
                break;
            case 451944782:
                if (str3.equals("poi_icon")) {
                    c2 = 5;
                    break;
                }
                c2 = '\uffff';
                break;
            case 1366209438:
                if (str3.equals(j4.f16729i)) {
                    c2 = 6;
                    break;
                }
                c2 = '\uffff';
                break;
            case 1864531656:
                if (str3.equals(j4.o)) {
                    c2 = 7;
                    break;
                }
                c2 = '\uffff';
                break;
            default:
                c2 = '\uffff';
                break;
        }
        switch (c2) {
            case 0:
                this.f17067c.b(l4.s, fileUpdateRsp.iVersion);
                hcVar = this.f17067c;
                str = fileUpdateRsp.sMd5;
                str2 = l4.y;
                break;
            case 1:
                this.f17067c.b("indoormap_style_night_version", fileUpdateRsp.iVersion);
                hcVar = this.f17067c;
                str = fileUpdateRsp.sMd5;
                str2 = "indoormap_style_night_md5";
                break;
            case 2:
                this.f17067c.b("escalator_night_version", fileUpdateRsp.iVersion);
                hcVar = this.f17067c;
                str = fileUpdateRsp.sMd5;
                str2 = "escalator_night_md5";
                break;
            case 3:
                this.f17067c.b(l4.d, fileUpdateRsp.iVersion);
                hcVar = this.f17067c;
                str = fileUpdateRsp.sMd5;
                str2 = l4.v;
                break;
            case 4:
                this.f17067c.b("indoormap_style_version", fileUpdateRsp.iVersion);
                hcVar = this.f17067c;
                str = fileUpdateRsp.sMd5;
                str2 = "indoormap_style_md5";
                break;
            case 5:
                this.f17067c.b(l4.f16790c, fileUpdateRsp.iVersion);
                hcVar = this.f17067c;
                str = fileUpdateRsp.sMd5;
                str2 = l4.u;
                break;
            case 6:
                this.f17067c.b(l4.a, fileUpdateRsp.iVersion);
                hcVar = this.f17067c;
                str = fileUpdateRsp.sMd5;
                str2 = l4.t;
                break;
            case 7:
                this.f17067c.b("indoorpoi_icon_3d_night_version", fileUpdateRsp.iVersion);
                hcVar = this.f17067c;
                str = fileUpdateRsp.sMd5;
                str2 = "indoorpoi_icon_3d_night_md5";
                break;
            default:
                return;
        }
        hcVar.b(str2, str);
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

    /* JADX WARN: Code restructure failed: missing block: B:108:0x0095, code lost:
        if (r9 != null) goto L109;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(boolean z) {
        h1 h1Var;
        xi l2;
        this.f17067c.a(l4.b, System.currentTimeMillis());
        ga.a(this.f17072i);
        ga.a(this.f17073j);
        long currentTimeMillis = System.currentTimeMillis();
        if (!z) {
            currentTimeMillis = 0;
        }
        List<WeakReference<h1>> list = f17066k.get(b());
        if (list == null) {
            return;
        }
        int size = list.size();
        WeakReference[] weakReferenceArr = (WeakReference[]) list.toArray(new WeakReference[size]);
        for (int i2 = 0; i2 < size; i2++) {
            if (weakReferenceArr[i2] != null && (h1Var = (h1) weakReferenceArr[i2].get()) != null && (l2 = h1Var.l()) != null && l2.getMap() != null) {
                VectorMap map = l2.getMap();
                v6 w = l2.A().w();
                if (this.a) {
                    map.t0();
                    if (h1Var.m() != null) {
                        h1Var.m().a(l2.getMapContext());
                    }
                    map.v0();
                    l2.b(1);
                    l2.l(true);
                    if (w != null) {
                        w.l().b(false, currentTimeMillis);
                        w.l().a(z, currentTimeMillis);
                    }
                    map.c(true);
                } else {
                    if (!z) {
                    }
                    map.c(true);
                }
            }
        }
        f17066k.clear();
        jc.c();
        qa.i(pa.V);
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
