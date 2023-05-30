package c.t.m.g;

import android.content.Context;
import android.location.Location;
import android.net.wifi.ScanResult;
import android.os.Build;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import android.util.LruCache;
import android.util.Pair;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class p {
    public static volatile p o;
    public Context b;

    /* renamed from: c  reason: collision with root package name */
    public e f584c;
    public HandlerThread d;

    /* renamed from: e  reason: collision with root package name */
    public volatile Location f585e;

    /* renamed from: f  reason: collision with root package name */
    public volatile Location f586f;

    /* renamed from: g  reason: collision with root package name */
    public volatile List<y> f587g;

    /* renamed from: h  reason: collision with root package name */
    public volatile y f588h;

    /* renamed from: i  reason: collision with root package name */
    public volatile List<ScanResult> f589i;

    /* renamed from: j  reason: collision with root package name */
    public LruCache<String, Pair<Double, Double>> f590j;
    public byte[] a = new byte[0];

    /* renamed from: k  reason: collision with root package name */
    public long f591k = 180000;

    /* renamed from: l  reason: collision with root package name */
    public long f592l = 0;

    /* renamed from: m  reason: collision with root package name */
    public long f593m = 0;

    /* renamed from: n  reason: collision with root package name */
    public long f594n = 0;

    public p(Context context) {
        Context applicationContext = context == null ? null : context.getApplicationContext();
        this.b = applicationContext;
        if (applicationContext == null) {
            throw new IllegalArgumentException("context cannot be null!");
        }
        y3.b(context);
        this.f590j = new LruCache<>(100);
        String str = "";
        try {
            if (!a2.a && !a2.b) {
                str = u6.a(this.b, "data").getAbsolutePath();
            }
            if (TextUtils.isEmpty(str)) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.b.getFilesDir().getAbsolutePath());
                sb.append("/data/");
                str = sb.toString();
            }
            if (!TextUtils.isEmpty(str)) {
                this.f584c = new e(this.b, str);
                p();
            }
            o = this;
        } catch (Throwable unused) {
            this.f584c = null;
        }
    }

    public static p b() {
        return o;
    }

    public static String m() {
        return "1.7.6_220414";
    }

    public final Location a(Location location, Location location2) {
        if (location == null) {
            location = new Location("gps");
        }
        if (location2 != null) {
            location.set(location2);
        }
        return location;
    }

    public void c(int i2, long j2, Object obj) {
        synchronized (this.a) {
            this.f584c.k(i2, j2, obj);
        }
    }

    public void d(int i2, Location location) {
        synchronized (this.a) {
            if (n()) {
                if (location == null || !"gps".equals(location.getProvider())) {
                    return;
                }
                if (a2.f294h || Build.VERSION.SDK_INT < 18 || !location.isFromMockProvider()) {
                    e eVar = this.f584c;
                    if (eVar != null) {
                        eVar.l(i2, location);
                    }
                    if (!f2.c(location.getAltitude(), 0.0d) || !f2.c(location.getSpeed(), 0.0d)) {
                        this.f585e = a(this.f585e, location);
                        if (this.f586f == null || (this.f585e != null && this.f585e.distanceTo(this.f586f) >= 50.0f && System.currentTimeMillis() - this.f594n >= Final.FIVE_SECOND)) {
                            r();
                        }
                    }
                }
            }
        }
    }

    public void e(long j2, int i2, double d, double d2, double d3) {
        synchronized (this.a) {
            if (n()) {
                z0.e();
                e eVar = this.f584c;
                if (eVar != null) {
                    eVar.m(j2, i2, d, d2, d3);
                }
            }
        }
    }

    @Deprecated
    public void f(Location location) {
        d(0, location);
    }

    public void g(Looper looper) {
        synchronized (this.a) {
            o();
            if (this.f584c != null) {
                if (looper == null) {
                    HandlerThread e2 = d.e("th_loc_extra");
                    this.d = e2;
                    looper = e2.getLooper();
                }
                this.f584c.g(looper);
            }
        }
    }

    public void h(x xVar) {
        synchronized (this.a) {
            a2.f295i = xVar;
            if (z0.e()) {
                StringBuilder sb = new StringBuilder("appInfo:");
                sb.append(xVar.g());
                sb.append(DYConstants.DY_REGEX_COMMA);
                sb.append(xVar.c());
                sb.append(CartConstant.KEY_YB_INFO_LINK);
                sb.append(xVar.d());
                sb.append(DYConstants.DY_REGEX_COMMA);
                sb.append(xVar.e());
                sb.append(DYConstants.DY_REGEX_COMMA);
                sb.append(xVar.f());
            }
        }
    }

    public void i(y yVar, List<y> list) {
        synchronized (this.a) {
            if (n()) {
                ArrayList arrayList = new ArrayList();
                for (y yVar2 : list) {
                    if (s1.b(yVar2.f761f, yVar2.a, yVar2.b, yVar2.f759c, yVar2.f760e)) {
                        if (l(yVar2.f759c + CartConstant.KEY_YB_INFO_LINK + yVar2.f760e, this.f585e)) {
                            arrayList.add(yVar2);
                        }
                    }
                }
                this.f587g = arrayList;
                this.f592l = System.currentTimeMillis();
                if (yVar != null && !yVar.equals(this.f588h)) {
                    this.f588h = yVar;
                    r();
                }
            }
        }
    }

    public void j(String str, String str2) {
        synchronized (this.a) {
            if (this.f584c == null || t2.c(str2)) {
                return;
            }
            if (z0.e()) {
                StringBuilder sb = new StringBuilder("setSetting(");
                sb.append(str);
                sb.append(DYConstants.DY_REGEX_COMMA);
                sb.append(str2);
                sb.append(")");
            }
            if ("D_CH_ID".equals(str)) {
                b2.b(str2);
            } else if ("D_FC_SRC".equals(str)) {
                b2.c(str2);
            } else if ("D_POS_COLL".equals(str)) {
                a2.f290c = Boolean.parseBoolean(str2.toLowerCase());
            } else if ("D_WRITE_MAC".equals(str)) {
                a2.d = Boolean.parseBoolean(str2.toLowerCase());
            } else if ("D_UP_NET".equals(str)) {
                if ("m".equals(str2.toLowerCase())) {
                    a2.f292f = true;
                } else if (JshopConst.JSHOP_PROMOTIO_W.equals(str2.toLowerCase())) {
                    a2.f292f = false;
                    a2.f293g = false;
                } else if ("w_m1".equals(str2.toLowerCase())) {
                    a2.f292f = false;
                    a2.f293g = true;
                }
            } else if ("D_EXTRA_SET_SN".equals(str)) {
                x2.a = t2.c(str2) ? "" : str2;
            } else {
                this.f584c.s(str, str2);
            }
        }
    }

    public void k(List<ScanResult> list) {
        long currentTimeMillis;
        boolean e2;
        synchronized (this.a) {
            if (n()) {
                try {
                    currentTimeMillis = System.currentTimeMillis();
                    e2 = f3.e(this.f589i, list);
                    z0.e();
                } catch (Throwable unused) {
                }
                if (!e2 || currentTimeMillis - this.f593m <= Final.HALF_MINUTE) {
                    e eVar = this.f584c;
                    if (eVar != null) {
                        eVar.t(list);
                    }
                    if (this.f584c != null && this.f585e != null && !t2.d(list)) {
                        if (list.size() == 1) {
                            if ("123456789abc".equals(list.get(0).BSSID.toLowerCase())) {
                                return;
                            }
                        } else if (list.size() > 1 && f3.d(list)) {
                            return;
                        }
                        if (a2.f292f && currentTimeMillis - this.f593m < Final.FIVE_SECOND) {
                            return;
                        }
                        if (!e2) {
                            this.f593m = currentTimeMillis;
                            this.f589i = list;
                        }
                        this.f584c.n(this.f585e, list, currentTimeMillis - this.f592l < this.f591k ? this.f587g : null);
                    }
                }
            }
        }
    }

    public final boolean l(String str, Location location) {
        if (!t2.c(str) && location != null && location.getLatitude() != 0.0d && location.getLongitude() != 0.0d) {
            Pair<Double, Double> pair = this.f590j.get(str);
            if (pair == null) {
                this.f590j.put(str, Pair.create(Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude())));
                return true;
            } else if (r0.b(location.getLatitude(), location.getLongitude(), ((Double) pair.first).doubleValue(), ((Double) pair.second).doubleValue()) < 6000.0d) {
                return true;
            }
        }
        return false;
    }

    public boolean n() {
        e eVar = this.f584c;
        if (eVar == null) {
            return false;
        }
        return eVar.b();
    }

    public final void o() {
        this.f585e = null;
        this.f586f = null;
        this.f587g = null;
        this.f588h = null;
        this.f589i = null;
        this.f592l = 0L;
        this.f593m = 0L;
        this.f594n = 0L;
        this.f590j.evictAll();
    }

    public final void p() {
        for (Map.Entry<String, String> entry : a2.c().entrySet()) {
            j(entry.getKey(), entry.getValue());
        }
    }

    public void q() {
        synchronized (this.a) {
            e eVar = this.f584c;
            if (eVar != null && eVar.b()) {
                this.f584c.c();
            }
            if (this.d != null) {
                d.c("th_loc_extra", 300L);
                this.d = null;
            }
            o();
        }
    }

    public final void r() {
        if (!t2.h(this.f584c, this.f585e) || t2.d(this.f587g)) {
            return;
        }
        if (a2.f292f && f3.a(this.b) == 3) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f592l < this.f591k) {
            this.f594n = currentTimeMillis;
            this.f586f = a(this.f586f, this.f585e);
            this.f584c.n(this.f585e, null, this.f587g);
        }
    }
}
