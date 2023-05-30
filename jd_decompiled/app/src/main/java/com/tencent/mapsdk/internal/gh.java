package com.tencent.mapsdk.internal;

import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.SystemClock;
import android.util.SparseArray;
import com.jd.dynamic.DYConstants;
import com.tencent.map.tools.EncryptAesUtils;
import com.tencent.map.tools.net.NetManager;
import com.tencent.map.tools.net.NetResponse;
import com.tencent.mapsdk.core.components.protocol.jce.trafficevent.Detail;
import com.tencent.mapsdk.core.components.protocol.jce.trafficevent.Response;
import com.tencent.mapsdk.internal.fh;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds;
import com.tencent.tencentmap.mapsdk.maps.model.TrafficEvent;
import java.io.File;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.crypto.spec.IvParameterSpec;

/* loaded from: classes9.dex */
public class gh implements me {
    public static final String p = "d8ab2f7b7a7536a71894084e1c812fd0";
    public static final IvParameterSpec q = new IvParameterSpec("c0ab1f54he78k36d".getBytes());
    public static int r = 0;
    public static int s = 1;
    public static int t = 2;

    /* renamed from: g */
    private volatile boolean f16607g;

    /* renamed from: h */
    private volatile boolean f16608h;

    /* renamed from: i */
    private volatile boolean f16609i;

    /* renamed from: j */
    private qc f16610j;

    /* renamed from: l */
    private fh f16612l;

    /* renamed from: n */
    private String f16614n;
    private s3 o;

    /* renamed from: k */
    private final SparseArray<dh> f16611k = new SparseArray<>(32);

    /* renamed from: m */
    private final a f16613m = new a();

    /* loaded from: classes9.dex */
    public class a extends Thread {
        private static final int b = 60000;

        public a() {
            gh.this = r2;
            setName("tms-traffic");
            r2.f16608h = false;
            r2.f16609i = false;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (!gh.this.f16609i && gh.this.f16607g) {
                if (gh.this.f16608h) {
                    ma.c(la.f16826m, "traffic event tobe paused!");
                    synchronized (this) {
                        try {
                            wait();
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                    }
                } else if (gh.this.f16610j == null) {
                    gh.this.f16609i = true;
                } else {
                    int x = (int) gh.this.f16610j.b().x();
                    LatLng[] a = gh.this.f16610j.getProjection().a();
                    dh dhVar = (dh) gh.this.f16611k.get(x);
                    dh b2 = (dhVar != null && dhVar.f16430c != null && SystemClock.elapsedRealtime() - dhVar.a <= 60000 && dhVar.b.contains(a[0]) && dhVar.b.contains(a[1]) && dhVar.b.contains(a[2]) && dhVar.b.contains(a[3])) ? null : gh.this.b(x);
                    if (b2 == null || b2.f16430c == null) {
                        b2 = dhVar;
                    }
                    gh.this.f16611k.put(x, b2);
                    gh.this.a(dhVar, b2);
                    gh.this.a(b2);
                    synchronized (this) {
                        try {
                            wait(60000L);
                        } catch (InterruptedException e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            }
            ma.c(la.f16826m, "traffic event tobe destroyed!");
            if (gh.this.f16612l != null) {
                gh.this.f16612l.b();
                gh.this.f16611k.clear();
            }
        }
    }

    public gh(qc qcVar) {
        if (qcVar == null) {
            return;
        }
        this.f16610j = qcVar;
        qcVar.a(this);
        this.f16612l = new fh(this.f16610j.g());
        this.f16614n = lc.b(qcVar.getContext()).i();
        this.o = (s3) l2.a(s3.class);
    }

    private dh a(int i2, LatLng latLng, LatLng latLng2, LatLng latLng3) {
        byte[] bArr;
        NetResponse mapTrafficEvent = ((e3) this.o.d()).mapTrafficEvent(URLEncoder.encode(EncryptAesUtils.encryptAes256Base64("lblat=" + latLng.latitude + "&lblon=" + latLng.longitude + "&rtlat=" + latLng2.latitude + "&rtlon=" + latLng2.longitude + "&zoom=" + i2 + "&suid=" + b7.A() + "&version=" + b7.E() + "&nt=" + b7.G() + "&location=" + latLng3.latitude + DYConstants.DY_REGEX_COMMA + latLng3.longitude, p, q)));
        if (mapTrafficEvent == null || (bArr = mapTrafficEvent.data) == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("rsp = ");
            sb.append(mapTrafficEvent != null ? Integer.valueOf(mapTrafficEvent.statusCode) : DYConstants.DY_NULL_STR);
            ma.c("net", sb.toString());
            return null;
        }
        m mVar = new m(bArr);
        mVar.a("UTF-8");
        Response response = new Response();
        response.readFrom(mVar);
        if (response.error != 0 || response.detail == null) {
            return null;
        }
        return new dh(new LatLngBounds.Builder().include(latLng).include(latLng2).build(), response.detail);
    }

    private void a(List<Detail> list) {
        int i2;
        byte[] h2;
        byte[] bArr;
        Iterator<Detail> it = list.iterator();
        while (it.hasNext()) {
            String str = it.next().basic.icon_normal;
            int lastIndexOf = str.lastIndexOf("/");
            if (lastIndexOf != -1 && (i2 = lastIndexOf + 1) <= str.length()) {
                String substring = str.substring(i2);
                if (a7.f16233e.a(substring) == null) {
                    File file = new File(this.f16614n, substring);
                    if (file.exists()) {
                        h2 = fa.h(file);
                    } else {
                        NetResponse doGet = NetManager.getInstance().builder().url(str).doGet();
                        if (doGet != null && (bArr = doGet.data) != null) {
                            fa.b(file, bArr);
                            h2 = doGet.data;
                        }
                    }
                    if (h2 != null && h2.length > 0) {
                        a7.f16233e.a(substring, BitmapFactory.decodeByteArray(h2, 0, h2.length));
                    }
                }
            }
        }
    }

    public dh b(int i2) {
        qc qcVar = this.f16610j;
        if (qcVar != null) {
            if (qcVar.V() == null || !this.f16610j.V().A()) {
                ma.c(la.f16826m, "traffic event tobe fetch data from net!");
                Rect e2 = this.f16610j.e();
                return a(i2, this.f16610j.getProjection().a(new PointF(e2.width() * (-2), e2.height() * 3)), this.f16610j.getProjection().a(new PointF(e2.width() * 3, e2.height() * (-2))), ea.d(this.f16610j.b().a()));
            }
            return null;
        }
        return null;
    }

    public TrafficEvent a(int i2) {
        fh fhVar = this.f16612l;
        if (fhVar == null) {
            return null;
        }
        Iterator<String> it = fhVar.c().keySet().iterator();
        while (it.hasNext()) {
            fh.a aVar = this.f16612l.c().get(it.next());
            if (aVar != null && aVar.a.d() == i2) {
                return new eh(aVar.b);
            }
        }
        return null;
    }

    public void a() {
        this.f16609i = true;
        this.f16607g = false;
        synchronized (this.f16613m) {
            this.f16613m.notifyAll();
        }
    }

    public void a(dh dhVar) {
        List<Detail> list;
        v6 w;
        if (dhVar == null || (list = dhVar.f16430c) == null || list.isEmpty()) {
            return;
        }
        qc qcVar = this.f16610j;
        if (qcVar != null && (w = qcVar.w()) != null) {
            w.o().b();
        }
        a(dhVar.f16430c);
        this.f16612l.b(dhVar.f16430c);
    }

    public void a(dh dhVar, dh dhVar2) {
        List<Detail> list;
        List<Detail> list2;
        if (dhVar2 == null || (list = dhVar2.f16430c) == null) {
            return;
        }
        if (list.isEmpty()) {
            this.f16612l.a();
            return;
        }
        LinkedList linkedList = new LinkedList();
        if (dhVar == null || (list2 = dhVar.f16430c) == null || list2.isEmpty()) {
            return;
        }
        List<Detail> list3 = dhVar.f16430c;
        List<Detail> list4 = dhVar2.f16430c;
        for (Detail detail : list3) {
            boolean z = false;
            Iterator<Detail> it = list4.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (it.next().basic.eventid.equals(detail.basic.eventid)) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                linkedList.add(detail);
            }
        }
        this.f16612l.a(linkedList);
    }

    public void a(boolean z) {
        this.f16607g = z;
        if (z) {
            try {
                synchronized (this.f16613m) {
                    this.f16613m.start();
                }
                return;
            } catch (Exception unused) {
                return;
            }
        }
        this.f16607g = false;
        synchronized (this.f16613m) {
            this.f16613m.notifyAll();
        }
    }

    public void b() {
        this.f16608h = true;
    }

    public void c() {
        this.f16608h = false;
        synchronized (this.f16613m) {
            this.f16613m.notifyAll();
        }
    }

    @Override // com.tencent.mapsdk.internal.me
    public void onMapCameraChangeStopped() {
        synchronized (this.f16613m) {
            this.f16613m.notifyAll();
        }
    }

    @Override // com.tencent.mapsdk.internal.me
    public void onMapCameraChanged() {
    }
}
