package c.t.m.g;

import android.location.Location;
import android.net.wifi.ScanResult;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import c.t.m.g.h5;
import c.t.m.g.q5;
import c.t.m.g.z4;
import com.jd.dynamic.DYConstants;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.jdmbridge.base.proxy.share.IShareAdapter;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.map.geolocation.util.SoUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class s3 {
    public static SparseArray<String> c0;
    public static boolean d0;
    public static boolean e0;
    public static final TencentLocationListener f0;
    public g5 A;
    public List<TencentLocationListener> B;
    public List<TencentLocationListener> C;
    public List<e> D;
    public long I;
    public q5 M;
    public double N;
    public double O;
    public q5 P;
    public long R;
    public String T;

    /* renamed from: e */
    public c f664e;

    /* renamed from: f */
    public f f665f;

    /* renamed from: g */
    public Handler f666g;

    /* renamed from: h */
    public boolean f667h;

    /* renamed from: i */
    public g2 f668i;

    /* renamed from: j */
    public h4 f669j;

    /* renamed from: k */
    public boolean f670k;

    /* renamed from: l */
    public k3 f671l;

    /* renamed from: m */
    public z4 f672m;

    /* renamed from: n */
    public x4 f673n;
    public u2 o;
    public p1 p;
    public q4 q;
    public h5 r;
    public volatile int s;
    public boolean t;
    public r5 u;
    public c.t.m.g.f v;
    public b1 w;
    public b1 x;
    public n y;
    public final y4 z;
    public String a = "ReGeoCodingnKey";
    public String b = "default";

    /* renamed from: c */
    public int f663c = -1;
    public int d = 1;
    public long E = 0;
    public volatile long F = 0;
    public int G = 0;
    public volatile int H = 0;
    public final Object J = new Object();
    public final TencentLocationRequest K = TencentLocationRequest.create();
    public long L = 0;
    public int Q = 404;
    public volatile d S = d.Stop;
    public p U = null;
    public o4 V = null;
    public boolean W = false;
    public boolean X = false;
    public int Y = 0;
    public final byte[] Z = new byte[0];
    public String a0 = "";
    public int b0 = -1;

    /* loaded from: classes.dex */
    public static class a implements TencentLocationListener {
        @Override // com.tencent.map.geolocation.TencentLocationListener
        public final void onLocationChanged(TencentLocation tencentLocation, int i2, String str) {
        }

        @Override // com.tencent.map.geolocation.TencentLocationListener
        public final void onStatusUpdate(String str, int i2, String str2) {
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public b() {
            s3.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                s3.this.S = d.Daemon;
                s3 s3Var = s3.this;
                s3Var.M(s3Var.z.o().getLooper());
            } catch (Throwable unused) {
            }
        }
    }

    /* loaded from: classes.dex */
    public class c extends Handler {
        public long a;
        public boolean b;

        /* renamed from: c */
        public boolean f675c;
        public int d;

        /* renamed from: e */
        public z4.c f676e;

        /* loaded from: classes.dex */
        public class a implements z4.c {
            public a() {
                c.this = r1;
            }

            @Override // c.t.m.g.z4.c
            public void a(q5 q5Var, int i2) {
                if (o.e(s3.this.f672m)) {
                    s3.this.f672m.k();
                }
                StringBuilder sb = new StringBuilder("onTxNlpLocationChanged: error= ");
                sb.append(i2);
                sb.append(DYConstants.DY_REGEX_COMMA);
                sb.append(q5Var);
                if (q5Var == null || q5Var == q5.w) {
                    s3.this.q(i2, q5.w);
                    c.this.e(i2);
                    return;
                }
                s3.this.q(0, q5Var);
                c.this.e(0);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(Looper looper) {
            super(looper);
            s3.this = r3;
            this.d = 0;
            this.f676e = new a();
            this.a = 0L;
            this.b = false;
            this.f675c = false;
        }

        public final void a() {
            if (s3.this.A.y() == 0) {
                s3.this.A.i(System.currentTimeMillis() - s3.this.A.J());
            }
        }

        public final void b(int i2) {
            if (!j1.a) {
                if (s3.this.Q == 0 && s3.this.M != null && System.currentTimeMillis() - s3.this.M.getTime() < 60000) {
                    e(0);
                    return;
                }
                s3.this.q(i2, q5.w);
                e(i2);
                return;
            }
            long currentTimeMillis = System.currentTimeMillis() - s3.this.L;
            long currentTimeMillis2 = s3.this.M != null ? System.currentTimeMillis() - s3.this.M.getTime() : 0L;
            StringBuilder sb = new StringBuilder("handleLocationFailed intervalstart: ");
            sb.append(currentTimeMillis);
            sb.append(",locationInterval: ");
            sb.append(currentTimeMillis2);
            sb.append(", mLastError: ");
            sb.append(s3.this.Q);
            if (!o.e(s3.this.f672m) || currentTimeMillis <= Final.HALF_MINUTE) {
                return;
            }
            if (s3.this.Q == 404 || (s3.this.M != null && currentTimeMillis2 > 20000)) {
                s3.this.L = System.currentTimeMillis();
                s3.this.f672m.j(s3.this.d == 1);
                s3.this.f672m.f(this.f676e);
                s3.this.f672m.h(s3.this.T);
                s3.this.f672m.d(s3.this.d);
                s3.this.f672m.n(i2);
                s3.this.f672m.t(s3.this.K.getRequestLevel());
                s3.this.f672m.p();
            }
        }

        public void d() {
            this.d = 0;
            removeCallbacksAndMessages(null);
        }

        public final void e(int i2) {
            if (s3.this.C == null || s3.this.C.isEmpty()) {
                return;
            }
            new StringBuilder("mSingleListenerList size = ").append(s3.this.C.size());
            if (s3.this.Q != 0) {
                s3.this.z(q5.w, i2, R2.color.bubble_chat_to_bg_color);
                return;
            }
            s3 s3Var = s3.this;
            s3Var.z(s3Var.M, s3.this.Q, R2.color.bubble_chat_to_bg_color);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            synchronized (s3.this.J) {
                if (o.e(s3.this.B) || s3.this.B.isEmpty() || s3.this.S != d.Normal) {
                    TencentLocationRequest tencentLocationRequest = s3.this.K;
                    int requestLevel = tencentLocationRequest.getRequestLevel();
                    long j2 = s3.this.F;
                    try {
                        int i2 = message.what;
                        if (i2 == 554) {
                            Bundle data = message.getData();
                            if (data != null) {
                                String string = data.getString("WIFIS");
                                if (TextUtils.isEmpty(string)) {
                                    return;
                                }
                                s3.this.r.f(string);
                                return;
                            }
                            return;
                        }
                        String str = null;
                        if (i2 == 555) {
                            s3.this.w = null;
                            o4.o("WIFI", "wifi clear req");
                            sendEmptyMessage(R2.color.jdreact_common_textview_bg_color);
                        } else if (i2 == 3991) {
                            if (s3.this.X) {
                                return;
                            }
                            sendEmptyMessage(R2.color.jdreact_common_textview_bg_color);
                            o4.o(IShareAdapter.SHARE_ACTION_PANE, "time out");
                        } else {
                            boolean z = false;
                            if (i2 == 3997) {
                                v h0 = s3.this.h0();
                                if (h0 != null) {
                                    str = h0.d(requestLevel, s3.this.T, s3.this.z, false, false, false);
                                    z = !u0.m(str);
                                }
                                if (h0 == null || z) {
                                    b(2);
                                    return;
                                } else {
                                    s3.this.r.g(str, h0, s3.this.d);
                                    return;
                                }
                            }
                            if (i2 != 3999) {
                                if (i2 == 5997) {
                                    b(message.arg1);
                                    return;
                                } else if (i2 == 5999) {
                                    try {
                                        int i3 = message.arg1;
                                        int i4 = message.arg2;
                                        q5 q5Var = (q5) message.obj;
                                        StringBuilder sb = new StringBuilder("nationcode, requestLevel=");
                                        sb.append(s3.this.K.getRequestLevel());
                                        sb.append(", current NationCode: ");
                                        sb.append(q5Var.getNationCode());
                                        new StringBuilder("nationcode, request bundle: ").append(s3.this.K.getExtras());
                                        if (s3.this.K.getRequestLevel() <= 0 || q5Var.getNationCode() != 0) {
                                            return;
                                        }
                                        String i5 = s3.this.z.i("https://apis.map.qq.com/ws/geocoder/v1/?location=" + q5Var.getLatitude() + DYConstants.DY_REGEX_COMMA + q5Var.getLongitude() + "&key=" + s3.this.b);
                                        if (i5 == null) {
                                            s3.this.q(1, q5.w);
                                            return;
                                        }
                                        JSONObject jSONObject = new JSONObject(i5);
                                        s3.this.f663c = jSONObject.optInt("status");
                                        s3.this.Y = jSONObject.getJSONObject("result").getJSONObject("ad_info").getInt("nation_code");
                                        StringBuilder sb2 = new StringBuilder("nation code is ");
                                        sb2.append(s3.this.Y);
                                        sb2.append(", processLocationChanged");
                                        s3.this.z(q5Var, i3, i4);
                                        return;
                                    } catch (Throwable unused) {
                                        return;
                                    }
                                } else if (i2 != 7999) {
                                    if (i2 == 4998) {
                                        if (s3.this.A.y() == 0) {
                                            s3.this.A.i(-1L);
                                        }
                                        if (!s3.this.B(30000, 0)) {
                                            b(1);
                                        }
                                        s3.this.I = -1L;
                                        return;
                                    } else if (i2 != 4999) {
                                        switch (i2) {
                                            case R2.drawable.wxa_recents_item_type_tag /* 11997 */:
                                            case R2.drawable.wxa_setting_icon_back /* 11998 */:
                                                if (s3.this.M != null) {
                                                    s3 s3Var = s3.this;
                                                    s3Var.y(s3Var.M);
                                                    s3 s3Var2 = s3.this;
                                                    s3Var2.z(s3Var2.M, s3.this.Q, R2.color.btn_white_text_color);
                                                    return;
                                                }
                                                return;
                                            case R2.drawable.wxa_setting_icon_close /* 11999 */:
                                                if (s3.this.M != null && tencentLocationRequest.getInterval() > 0) {
                                                    s3 s3Var3 = s3.this;
                                                    s3Var3.y(s3Var3.M);
                                                    s3 s3Var4 = s3.this;
                                                    s3Var4.z(s3Var4.M, s3.this.Q, R2.color.btn_white_text_color);
                                                    new StringBuilder("MSG_ID_TIMED_CALLBACK mLastLocation: ").append(s3.this.M.toString());
                                                }
                                                if (j2 > 0) {
                                                    sendEmptyMessageDelayed(R2.drawable.wxa_setting_icon_close, j2);
                                                    return;
                                                }
                                                return;
                                            default:
                                                return;
                                        }
                                    } else {
                                        a();
                                        removeMessages(R2.color.wmpf_ef_colorAccent);
                                        Pair pair = (Pair) message.obj;
                                        String obj = pair.first.toString();
                                        h5.b bVar = (h5.b) pair.second;
                                        s3.this.x = ((v) bVar.d).a();
                                        String str2 = bVar.f484f;
                                        try {
                                            q5.b bVar2 = new q5.b();
                                            bVar2.e(obj);
                                            bVar2.a(requestLevel);
                                            bVar2.g("network");
                                            q5 f2 = bVar2.f();
                                            if (f2.isMockGps() == 1) {
                                                this.f675c = true;
                                            }
                                            q5.z(f2);
                                            if (s3.this.U != null) {
                                                s3.this.U.e(f2.w(), s3.this.d, f2.getLatitude(), f2.getLongitude(), f2.getAccuracy());
                                            }
                                            s3.this.I = System.currentTimeMillis();
                                            boolean z2 = !TextUtils.isEmpty(f2.getIndoorBuildingId());
                                            if (z2 && s3.this.H == 1) {
                                                s3.this.H = 2;
                                                this.d = 0;
                                            }
                                            if (s3.this.H == 2) {
                                                if (z2) {
                                                    this.d = 0;
                                                } else {
                                                    this.d++;
                                                }
                                                if (this.d >= 5) {
                                                    new StringBuilder("indoor stop,").append(s3.this.K.getInterval());
                                                    s3.this.H = 1;
                                                    this.d = 0;
                                                }
                                            }
                                            f2.y(k0.g().c(f2));
                                            StringBuilder sb3 = new StringBuilder("isGpsValid(): ");
                                            sb3.append(s3.this.m0());
                                            sb3.append(", isIndoorLocation: ");
                                            sb3.append(z2);
                                            sb3.append(", mIndoorLocationStatus: ");
                                            sb3.append(s3.this.H);
                                            if (!s3.this.m0() || (z2 && s3.this.H > 0)) {
                                                q5.k(f2, z2);
                                                s3.this.q(0, f2);
                                                new StringBuilder("MSG_ID_HTTP_LOCATION_RESPONSE updateLastLocation: ").append(f2.toString());
                                            }
                                            if (s3.this.M != null) {
                                                q5.j(s3.this.M, f2);
                                            }
                                            e(0);
                                            s3.this.P = f2;
                                            if (s3.this.S == d.Normal) {
                                                try {
                                                    q2.q().e();
                                                } catch (Exception unused2) {
                                                }
                                            }
                                            i4.b(s3.this.z.a);
                                            return;
                                        } catch (JSONException unused3) {
                                            o4.o("LOC", "server location error.");
                                            o4.o("LOC", "request:".concat(String.valueOf(str2)));
                                            o4.o("LOC", "response:".concat(String.valueOf(obj)));
                                            b(404);
                                            return;
                                        }
                                    }
                                } else if (s3.this.N()) {
                                    return;
                                } else {
                                    long max = Math.max(s3.this.K.getInterval(), 4000L);
                                    if (s3.this.I == 0 || System.currentTimeMillis() - s3.this.I < max) {
                                        return;
                                    }
                                }
                            }
                            s3.this.X = true;
                            removeMessages(R2.color.jdreact_common_textview_bg_color);
                            d dVar = s3.this.S;
                            d dVar2 = d.Daemon;
                            if (dVar == dVar2 || s3.this.W) {
                                long l2 = y2.f().l("up_daemon_delay");
                                if (l2 < 120000) {
                                    l2 = 120000;
                                }
                                StringBuilder sb4 = new StringBuilder("the daemonLocation, so we delay long time upload:");
                                sb4.append(l2);
                                sb4.append(DYConstants.DY_REGEX_COMMA);
                                sb4.append(System.currentTimeMillis());
                                sb4.append(DYConstants.DY_REGEX_COMMA);
                                sb4.append(s3.this.R);
                                if (System.currentTimeMillis() - s3.this.R < l2) {
                                    return;
                                }
                                s3.this.R = System.currentTimeMillis();
                            }
                            int i6 = s3.this.d;
                            v h02 = s3.this.h0();
                            if (h02 == null) {
                                o4.o("LOC", "info is null.");
                                b(2);
                                return;
                            }
                            if (!h02.f() || System.currentTimeMillis() - this.a <= 60000) {
                                this.b = false;
                            } else {
                                this.b = true;
                                this.f675c = false;
                                this.a = System.currentTimeMillis();
                            }
                            String d = h02.d(requestLevel, s3.this.T, s3.this.z, this.b, this.f675c, s3.this.S == dVar2);
                            if (d == null || !u0.m(d)) {
                                o4.o("LOC", "bad json ".concat(String.valueOf(d)));
                                b(2);
                            } else if (s3.this.S != d.Normal || !j1.e(tencentLocationRequest)) {
                                if (k0.f()) {
                                    return;
                                }
                                s3.this.r.g(d, h02, i6);
                            } else {
                                q5.b bVar3 = new q5.b();
                                bVar3.d(null);
                                bVar3.a(requestLevel);
                                q5 f3 = bVar3.f();
                                j1.a(f3, d.getBytes("UTF-8"));
                                s3.this.y(f3);
                                s3.this.z(f3, 0, R2.color.btn_white_text_color);
                            }
                        }
                    } catch (Throwable unused4) {
                    }
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public enum d {
        Normal,
        Daemon,
        Single,
        Stop
    }

    /* loaded from: classes.dex */
    public class e {
        public TencentLocationListener a;
        public long b;

        public e(s3 s3Var, TencentLocationListener tencentLocationListener, long j2) {
            this.a = tencentLocationListener;
            this.b = j2;
        }

        public long a() {
            return this.b;
        }
    }

    /* loaded from: classes.dex */
    public class f extends Handler {
        public double a;
        public double b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(Looper looper) {
            super(looper);
            s3.this = r1;
            this.a = 0.0d;
            this.b = 0.0d;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Bundle data = message.getData();
            if (data == null) {
                return;
            }
            switch (message.what) {
                case R2.color.btn_white_text_color /* 3101 */:
                case R2.color.bubble_chat_to_bg_color /* 3103 */:
                    int i2 = message.arg1;
                    q5 q5Var = q5.w;
                    if (i2 == 0) {
                        q5Var = (q5) message.obj;
                    } else {
                        o4.o("LOC", "callback:".concat(String.valueOf(i2)));
                    }
                    if (q5Var == null) {
                        o4.o("LOC", "cbCode:".concat(String.valueOf(i2)));
                        return;
                    }
                    if (i1.f(q5Var)) {
                        q5Var.l(TencentLocation.BEIDOU_PROVIDER);
                        q5Var.y(0);
                        StringBuilder sb = new StringBuilder("provider: ");
                        sb.append(q5Var.getProvider());
                        sb.append(", sourceProvider: ");
                        sb.append(q5Var.getSourceProvider());
                    }
                    StringBuilder sb2 = new StringBuilder("user location:");
                    sb2.append(q5Var.getLatitude());
                    sb2.append(DYConstants.DY_REGEX_COMMA);
                    sb2.append(q5Var.getLongitude());
                    int i3 = message.what;
                    if (i3 == 3101) {
                        if (!s3.this.P(q5Var) && System.currentTimeMillis() - s3.this.A.J() < s3.this.K.getGpsFirstTimeOut()) {
                            new StringBuilder("is gps first but location provider is ").append(q5Var.getProvider());
                            return;
                        }
                        new StringBuilder("startLocTime: cal time since last loc ").append(s3.this.A.J());
                        long currentTimeMillis = System.currentTimeMillis() - (s3.this.A.J() >= s3.this.A.E() ? s3.this.A.J() : s3.this.A.E());
                        if (s3.this.K.getLocMode() == 12 && !q5Var.getProvider().equals("gps") && currentTimeMillis < 8000 && !s3.this.A.C().equals("network")) {
                            new StringBuilder("location mode is ONLY_GPS_MODE but location provider is ").append(q5Var.getProvider());
                            return;
                        }
                        if (this.a != q5Var.getLatitude() || this.b != q5Var.getLongitude()) {
                            try {
                                o4.o("LOC", String.format(Locale.ENGLISH, "callback:%d,%s,%.6f,%.6f,%.1f,%.2f,%.1f,%.1f,%s", Integer.valueOf(i2), q5Var.getProvider().substring(0, 1), Double.valueOf(q5Var.getLatitude()), Double.valueOf(q5Var.getLongitude()), Double.valueOf(q5Var.getAltitude()), Float.valueOf(q5Var.getAccuracy()), Float.valueOf(q5Var.getBearing()), Float.valueOf(q5Var.getSpeed()), q5Var.getIndoorBuildingFloor()));
                            } catch (Exception unused) {
                            }
                            this.a = q5Var.getLatitude();
                            this.b = q5Var.getLongitude();
                        }
                        if (s3.this.B != null) {
                            String.format(Locale.ENGLISH, "continue callback:%d,%s,%.6f,%.6f,%.1f,%.2f,%.1f,%.1f,%s,%s,%d", Integer.valueOf(i2), Character.valueOf(q5Var.getProvider().charAt(0)), Double.valueOf(q5Var.getLatitude()), Double.valueOf(q5Var.getLongitude()), Double.valueOf(q5Var.getAltitude()), Float.valueOf(q5Var.getAccuracy()), Float.valueOf(q5Var.getBearing()), Float.valueOf(q5Var.getSpeed()), q5Var.getIndoorBuildingFloor(), Character.valueOf(q5Var.getSourceProvider().charAt(0)), Integer.valueOf(q5Var.getFakeReason()));
                            for (TencentLocationListener tencentLocationListener : s3.this.B) {
                                if (tencentLocationListener != null) {
                                    s3.this.A.t(q5Var.getProvider());
                                    s3.this.A.m(System.currentTimeMillis());
                                    tencentLocationListener.onLocationChanged(q5Var, i2, (String) s3.c0.get(i2));
                                    c cVar = s3.this.f664e;
                                    if (cVar != null) {
                                        cVar.removeMessages(R2.drawable.wxa_recents_item_type_tag);
                                    }
                                }
                            }
                            return;
                        }
                        return;
                    } else if (i3 == 3103) {
                        String.format(Locale.ENGLISH, "continue callback:%d,%s,%.6f,%.6f,%.1f,%.2f,%.1f,%.1f,%s,%s,%d", Integer.valueOf(i2), Character.valueOf(q5Var.getProvider().charAt(0)), Double.valueOf(q5Var.getLatitude()), Double.valueOf(q5Var.getLongitude()), Double.valueOf(q5Var.getAltitude()), Float.valueOf(q5Var.getAccuracy()), Float.valueOf(q5Var.getBearing()), Float.valueOf(q5Var.getSpeed()), q5Var.getIndoorBuildingFloor(), Character.valueOf(q5Var.getSourceProvider().charAt(0)), Integer.valueOf(q5Var.getFakeReason()));
                        if (s3.this.P(q5Var)) {
                            if (s3.this.C == null || s3.this.C.isEmpty()) {
                                return;
                            }
                            new StringBuilder("single Loc call back, provider is ").append(q5Var.getProvider());
                            for (TencentLocationListener tencentLocationListener2 : s3.this.C) {
                                if (tencentLocationListener2 != null) {
                                    tencentLocationListener2.onLocationChanged(q5Var, i2, (String) s3.c0.get(i2));
                                }
                            }
                            s3.this.u0();
                            new StringBuilder("clear mSingleListenerList after size: ").append(s3.this.C.size());
                            new StringBuilder("clear mSingleListenerStartTimeList after size: ").append(s3.this.D.size());
                            return;
                        }
                        new StringBuilder("is gps first but location provider is ").append(q5Var.getProvider());
                        if (s3.this.D == null || s3.this.D.isEmpty()) {
                            return;
                        }
                        for (e eVar : s3.this.D) {
                            TencentLocationListener tencentLocationListener3 = eVar.a;
                            if (tencentLocationListener3 != null && System.currentTimeMillis() - eVar.b > s3.this.K.getGpsFirstTimeOut()) {
                                new StringBuilder("before mSingleListenerList size: ").append(s3.this.C.size());
                                new StringBuilder("before mSingleListenerStartTimeList size: ").append(s3.this.D.size());
                                tencentLocationListener3.onLocationChanged(q5Var, i2, (String) s3.c0.get(i2));
                                s3.this.A(tencentLocationListener3);
                                new StringBuilder("after mSingleListenerList after size: ").append(s3.this.C.size());
                                new StringBuilder("after mSingleListenerStartTimeList after size: ").append(s3.this.D.size());
                            }
                        }
                        if (s3.this.D.isEmpty()) {
                            s3.this.u0();
                            new StringBuilder("clear mSingleListenerList after size: ").append(s3.this.C.size());
                            new StringBuilder("clear mSingleListenerStartTimeList after size: ").append(s3.this.D.size());
                            return;
                        }
                        return;
                    } else {
                        return;
                    }
                case R2.color.bubble_chat_from_bg_color /* 3102 */:
                    String string = data.getString("name");
                    int i4 = data.getInt("status");
                    String string2 = data.getString("desc");
                    if (s3.this.B != null) {
                        for (TencentLocationListener tencentLocationListener4 : s3.this.B) {
                            if (tencentLocationListener4 != null) {
                                tencentLocationListener4.onStatusUpdate(string, i4, string2);
                            }
                        }
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    static {
        SparseArray<String> sparseArray = new SparseArray<>();
        c0 = sparseArray;
        d0 = false;
        e0 = false;
        sparseArray.put(0, "OK");
        c0.put(1, "ERROR_NETWORK");
        c0.put(2, "ERROR_NOCELL&WIFI_LOCATIONSWITCHOFF");
        c0.put(4, "DEFLECT_FAILED");
        c0.put(404, "ERROR_SERVER_NOT_LOCATION");
        q2.k(m0.a());
        f0 = new a();
    }

    /* JADX WARN: Removed duplicated region for block: B:79:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00c0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public s3(c.t.m.g.y4 r8) {
        /*
            Method dump skipped, instructions count: 387
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.t.m.g.s3.<init>(c.t.m.g.y4):void");
    }

    public void A(TencentLocationListener tencentLocationListener) {
        List<TencentLocationListener> list;
        synchronized (this.J) {
            List<TencentLocationListener> list2 = this.B;
            if (list2 != null) {
                if (tencentLocationListener != null) {
                    list2.remove(tencentLocationListener);
                } else {
                    list2.clear();
                }
            }
            List<TencentLocationListener> list3 = this.C;
            if (list3 != null && !list3.isEmpty()) {
                if (tencentLocationListener != null) {
                    this.C.remove(tencentLocationListener);
                } else {
                    this.C.clear();
                }
            }
            List<e> list4 = this.D;
            if (list4 != null) {
                if (tencentLocationListener != null) {
                    Iterator<e> it = list4.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        e next = it.next();
                        if (next.a.equals(tencentLocationListener)) {
                            this.D.remove(next);
                            break;
                        }
                    }
                } else {
                    list4.clear();
                }
            }
        }
        List<TencentLocationListener> list5 = this.C;
        if (list5 == null || !list5.isEmpty() || (list = this.B) == null || !list.isEmpty()) {
            return;
        }
        synchronized (this.Z) {
            r0();
            try {
                c cVar = this.f664e;
                if (cVar != null) {
                    cVar.d();
                    this.f664e = null;
                }
            } catch (Throwable unused) {
            }
        }
        o0();
        w0();
    }

    public final void A0() {
        if (this.K.getInterval() > 0) {
            p(R2.drawable.wxa_setting_icon_close, this.K.getInterval());
            new StringBuilder("startTimedCallback, interval is ").append(this.K.getInterval());
        }
    }

    public final boolean B(int i2, int i3) {
        List<TencentLocationListener> list = this.C;
        if (list == null || list.isEmpty() || this.Q != 0 || this.M == null || this.A.J() == 0) {
            return false;
        }
        if (!"gps".equals(this.M.getProvider()) || System.currentTimeMillis() - this.M.getTime() > i2) {
            return "network".equals(this.M.getProvider()) && System.currentTimeMillis() - this.M.getTime() <= ((long) i3);
        }
        return true;
    }

    public final void C0() {
        c cVar = this.f664e;
        if (cVar == null) {
            this.f664e = new c(this.z.o().getLooper());
        } else {
            cVar.d();
        }
        if (y2.f().i("deny_secret_info")) {
            z3.d(true);
        }
        boolean G0 = G0();
        boolean z = d.Daemon == this.S;
        c cVar2 = this.f664e;
        new StringBuilder("startupProviders start mHandler is null? ").append(this.f664e == null);
        o4 v = o4.v();
        this.V = v;
        if (v != null && !z) {
            boolean booleanValue = ((Boolean) n4.a("CONF_USER_DEBUGGABLE", Boolean.FALSE)).booleanValue();
            this.V.q(booleanValue);
            if (booleanValue) {
                this.V.e();
                o4.o("LOC", "request {interval: " + this.K.getInterval() + ", level: " + this.K.getRequestLevel() + ", gps: " + this.K.isAllowGPS() + ", direct: " + this.K.isAllowDirection() + "}");
            }
        }
        this.r.d(cVar2, z);
        if (this.f670k) {
            if (G0 && o.e(this.f669j) && o.e(cVar2)) {
                this.f669j.f(cVar2, z);
            }
        } else if (G0 && o.e(this.f668i) && o.e(cVar2)) {
            this.f668i.c(cVar2);
        }
        if (G0 && o.e(this.f673n) && o.e(cVar2)) {
            this.f673n.m(this.A.L());
            this.f673n.g(cVar2, this.f665f, this.f666g, z);
        }
        if (o.e(this.f671l) && this.K.isAllowGPS() && o.e(cVar2)) {
            this.f671l.G(this.d == 1);
            if (!this.t) {
                this.G = 20;
            }
            this.f671l.A(this.G);
            this.f671l.B(this.t);
            this.f671l.n(cVar2, this.f665f, this.f666g, z);
        }
        p pVar = null;
        if (!z) {
            if (!y2.f().i("collect_bles")) {
                this.p = null;
            }
            if (o.e(this.p) && o.e(cVar2)) {
                this.p.f(cVar2);
            }
            if (o.e(cVar2)) {
                this.q.c(cVar2);
            }
            if (o.e(this.o) && this.K.isAllowDirection() && o.e(cVar2)) {
                this.o.c(cVar2);
            }
        }
        if (cVar2 != null) {
            cVar2.sendEmptyMessageDelayed(R2.color.jdreact_button_d_01_solid, 10000L);
        }
        if (o.e(cVar2)) {
            int j2 = y2.f().j("f_coll_item");
            if ((j2 == 1 || j2 == 2) && this.U == null) {
                pVar = new p(this.z.a);
            }
            this.U = pVar;
            if (this.U != null && cVar2 != null) {
                StringBuilder sb = new StringBuilder("fc,set:");
                sb.append(j2);
                sb.append(",daemon:");
                sb.append(z);
                sb.append(",version:");
                sb.append(p.m());
                if (j2 == 2 || (j2 == 1 && !z)) {
                    this.U.h(e0());
                    this.U.j("D_UP_NET", y2.f().n("f_coll_up_net"));
                    this.U.j("D_UP_U_TRACK_INFO", Boolean.toString(true));
                    this.U.g(cVar2.getLooper());
                }
            }
        }
        o4.o("m", z3.k());
        new StringBuilder("startupProviders end mHandler is null? ").append(this.f664e == null);
    }

    public final boolean E(TencentLocationRequest tencentLocationRequest) {
        if ((tencentLocationRequest.isGpsFirst() && tencentLocationRequest.getLocMode() == 10) || tencentLocationRequest.getLocMode() == 12) {
            return "gps".equals(this.M.getProvider()) && System.currentTimeMillis() - this.M.getTime() <= Final.FIVE_SECOND;
        } else if (tencentLocationRequest.getLocMode() == 11) {
            return "network".equals(this.M.getProvider()) && System.currentTimeMillis() - this.M.getTime() <= Final.FIVE_SECOND;
        } else {
            return true;
        }
    }

    public boolean E0() {
        if (this.H > 0) {
            if (o.e(this.f673n)) {
                this.f673n.m(this.A.L());
            }
            if (o.e(Long.valueOf(this.F))) {
                this.F = this.K.getInterval();
            }
            this.H = 0;
        }
        o4.o("LOC", "stop indoor");
        return true;
    }

    public final boolean G0() {
        Bundle extras = this.K.getExtras();
        if (extras != null) {
            return extras.getBoolean("use_network", true);
        }
        return true;
    }

    public int H(TencentLocationRequest tencentLocationRequest, TencentLocationListener tencentLocationListener, Looper looper) {
        List<e> list;
        List<TencentLocationListener> list2;
        if (this.s != 0) {
            return this.s;
        }
        if (tencentLocationListener != null && (list2 = this.C) != null) {
            list2.add(tencentLocationListener);
        }
        if (tencentLocationListener != null && (list = this.D) != null) {
            list.add(new e(this, tencentLocationListener, System.currentTimeMillis()));
        }
        if (System.currentTimeMillis() - this.E < 2000) {
            return 0;
        }
        this.E = System.currentTimeMillis();
        if (B(90000, 30000) && E(tencentLocationRequest)) {
            z(this.M, this.Q, R2.color.bubble_chat_to_bg_color);
            return 0;
        } else if (this.S == d.Normal) {
            n(R2.color.jdreact_c_F0F2F5);
            new StringBuilder("mHandler is null? ").append(this.f664e == null);
            return 0;
        } else {
            if (tencentLocationRequest != null) {
                tencentLocationRequest.setSmallAppKey(tencentLocationRequest.getSmallAppKey()).setInterval(0L);
            } else {
                tencentLocationRequest = TencentLocationRequest.create().setInterval(0L);
                tencentLocationRequest.setAllowGPS(false);
            }
            return f(tencentLocationRequest, f0, looper);
        }
    }

    public final boolean I0() {
        new StringBuilder("nationcode, lastRegeostatus=").append(this.f663c);
        int i2 = this.f663c;
        return (i2 < 110 || i2 > 199) && i2 != 311;
    }

    public void K(int i2) {
        if (this.d == i2) {
            return;
        }
        synchronized (this.J) {
            if (o.e(this.B) && !this.B.isEmpty()) {
                throw new IllegalStateException("removeUpdates MUST called before set coordinate type!");
            }
        }
        this.d = i2;
    }

    public void L(int i2, TencentLocationListener tencentLocationListener) {
        a1.c(this.z).e(i2, tencentLocationListener);
    }

    public final void M(Looper looper) {
        List<TencentLocationListener> list;
        if (this.S != d.Single || (list = this.C) == null || list.size() < 2) {
            synchronized (this.Z) {
                r(looper);
                r0();
                C0();
            }
        }
    }

    public final boolean N() {
        return this.Q == 404;
    }

    public final boolean P(q5 q5Var) {
        return (this.K.getLocMode() == 10 && this.K.isGpsFirst() && !q5Var.getProvider().equals("gps")) ? false : true;
    }

    @Nullable
    public final g2 S() {
        if (this.z.q()) {
            return new g2(this.z);
        }
        return null;
    }

    @Nullable
    public final k3 W() {
        if (this.z.p()) {
            return new k3(this.z, this.t);
        }
        return null;
    }

    @Nullable
    public final h4 X() {
        if (this.z.q()) {
            return new h4(this.z);
        }
        return null;
    }

    @Nullable
    public final z4 Z() {
        if (this.z.p() && j1.a) {
            return new z4(this.z);
        }
        return null;
    }

    @Nullable
    public final x4 c0() {
        if (this.z.r()) {
            return new x4(this.z);
        }
        return null;
    }

    public int d(int i2, TencentLocationListener tencentLocationListener) {
        return a1.c(this.z).a(i2, tencentLocationListener);
    }

    public final x e0() {
        try {
            g5 c2 = this.z.c();
            return new x(c2.l(), "7.4.9.official_1", c2.a());
        } catch (Throwable unused) {
            return new x("unknown", "unknown", "unknown");
        }
    }

    public int f(TencentLocationRequest tencentLocationRequest, TencentLocationListener tencentLocationListener, Looper looper) {
        if (this.s != 0) {
            return this.s;
        }
        o0();
        this.Q = 404;
        this.M = null;
        synchronized (this.J) {
            if (tencentLocationListener != null) {
                List<TencentLocationListener> list = this.B;
                if (list != null) {
                    list.add(tencentLocationListener);
                }
            }
        }
        if (tencentLocationListener == f0) {
            List<e> list2 = this.D;
            if (list2 != null && !list2.isEmpty()) {
                this.A.q(this.D.get(0).a());
            }
            if (this.S == d.Stop || this.S == d.Single) {
                this.S = d.Single;
                k1.d = true;
            }
        } else {
            this.S = d.Normal;
            k1.d = false;
        }
        Bundle extras = tencentLocationRequest.getExtras();
        if (extras == null || extras.getString("LOCATION_URL_IOT") == null || !extras.getString("LOCATION_URL_IOT").equals(DYConstants.DY_TRUE)) {
            d0 = false;
        } else {
            d0 = true;
        }
        TencentLocationRequest.copy(this.K, tencentLocationRequest);
        if (this.K.isIndoorLocationMode()) {
            this.H = 1;
        }
        if (this.K.getGnssSource() == 20 || this.K.getGnssSource() == 21) {
            this.G = this.K.getGnssSource();
        }
        g5 g5Var = this.A;
        if (g5Var != null) {
            g5Var.q(System.currentTimeMillis());
            new StringBuilder("startLocTime begin set: ").append(this.A.J());
            if (this.A.p().equals(this.A.s())) {
                this.z.t();
            }
            this.A.x(tencentLocationRequest.getQQ());
            this.A.z(tencentLocationRequest.getSmallAppKey());
            this.A.d(tencentLocationRequest.getInterval(), this.K.isIndoorLocationMode());
        }
        this.F = this.K.getInterval();
        M(looper);
        new StringBuilder("requestLocationUpdates request:").append(this.K.toString());
        if (this.K.getLocMode() == 10 && this.K.isGpsFirst()) {
            this.f664e.sendEmptyMessageDelayed(R2.drawable.wxa_recents_item_type_tag, this.K.getGpsFirstTimeOut());
            this.f664e.sendEmptyMessageDelayed(R2.color.jdreact_c_F0F2F5, this.K.getGpsFirstTimeOut());
        } else if (this.K.getLocMode() == 12) {
            this.f664e.sendEmptyMessageDelayed(R2.drawable.wxa_recents_item_type_tag, 8000L);
        }
        return 0;
    }

    public int f0() {
        return this.d;
    }

    public final v h0() {
        b1 b1Var = this.w;
        c.t.m.g.f fVar = this.v;
        n nVar = this.y;
        if (nVar != null && !m0()) {
            nVar = null;
        }
        if (fVar == null) {
            fVar = c.t.m.g.f.d(this.z);
        }
        if (b1Var != null && !b1Var.b(System.currentTimeMillis(), 60000L)) {
            b1Var = null;
        }
        if (fVar != null && nVar != null && Build.VERSION.SDK_INT >= 12) {
            int i2 = fVar.d;
            long j2 = fVar.f406f;
            Location location = nVar.a;
            Bundle bundle = new Bundle();
            StringBuilder sb = new StringBuilder();
            sb.append(i2);
            sb.append(j2);
            bundle.putString("cellkey", sb.toString());
            bundle.putParcelable(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID, location);
            p5 d2 = this.z.d("cell");
            if (d2 != null && !d2.b(bundle)) {
                StringBuilder sb2 = new StringBuilder("getFromLastKnownInfo: discard bad cell(");
                sb2.append(i2);
                sb2.append(DYConstants.DY_REGEX_COMMA);
                sb2.append(j2);
                sb2.append(")");
                fVar = null;
            }
        }
        p1 p1Var = this.p;
        return new v(b1Var, fVar, nVar, p1Var != null ? p1Var.d() : null);
    }

    public TencentLocation k0() {
        if (this.Q == 0) {
            y(this.M);
            return this.M;
        }
        return null;
    }

    public final String m(String str) {
        try {
            if (!str.contains(DYConstants.DY_REGEX_COMMA)) {
                int fun_v = SoUtils.fun_v(str);
                this.t = false;
                return fun_v >= 0 ? Integer.toString(fun_v) : "";
            }
            String[] split = str.split(DYConstants.DY_REGEX_COMMA);
            boolean z = (split == null || split.length <= 1 || split[0] == null || split[1] == null || SoUtils.fun_w(split[0], split[1]) <= 0) ? false : true;
            if (z) {
                e0 = true;
            } else {
                this.t = false;
                e0 = false;
            }
            new StringBuilder("extraKey is ").append(e0);
            return z ? split[0] : "";
        } catch (UnsatisfiedLinkError unused) {
            return null;
        }
    }

    public final boolean m0() {
        k3 k3Var = this.f671l;
        return k3Var != null && k3Var.S() && this.f671l.R();
    }

    public final void n(int i2) {
        if (this.f664e == null) {
            this.f664e = new c(this.z.o().getLooper());
        }
        this.f664e.sendEmptyMessage(i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:72:0x0027, code lost:
        if (c.t.m.g.s5.a != false) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x004e, code lost:
        if (c.t.m.g.k1.a != false) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0051, code lost:
        r6 = 2;
        r0 = "location permission denied";
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void o(int r4, int r5, int r6) {
        /*
            r3 = this;
            java.lang.String r4 = "gps"
            r0 = 0
            java.lang.String r1 = "unknown"
            r2 = 1
            switch(r5) {
                case 12001: goto L35;
                case 12002: goto L2a;
                case 12003: goto L18;
                case 12004: goto Lc;
                default: goto La;
            }
        La:
            r4 = r0
            goto L56
        Lc:
            r0 = 3
            if (r6 == r0) goto L15
            r0 = 4
            if (r6 != r0) goto L55
            java.lang.String r0 = "gps unavailable"
            goto L56
        L15:
            java.lang.String r0 = "gps available"
            goto L56
        L18:
            java.lang.String r4 = "cell"
            if (r6 != r2) goto L1f
            java.lang.String r0 = "cell enabled"
            goto L25
        L1f:
            if (r6 != 0) goto L24
            java.lang.String r0 = "cell disabled"
            goto L25
        L24:
            r0 = r1
        L25:
            boolean r1 = c.t.m.g.s5.a
            if (r1 == 0) goto L56
            goto L51
        L2a:
            if (r6 == 0) goto L32
            if (r6 == r2) goto L2f
            goto L55
        L2f:
            java.lang.String r0 = "gps enabled"
            goto L56
        L32:
            java.lang.String r0 = "gps disabled"
            goto L56
        L35:
            java.lang.String r4 = "wifi"
            r0 = 5
            if (r6 == 0) goto L47
            if (r6 == r2) goto L43
            if (r6 == r0) goto L40
            goto L4a
        L40:
            java.lang.String r1 = "location service switch is off"
            goto L4a
        L43:
            java.lang.String r1 = "wifi enabled"
            goto L4a
        L47:
            java.lang.String r1 = "wifi disabled"
        L4a:
            if (r6 == r0) goto L55
            boolean r0 = c.t.m.g.k1.a
            if (r0 != 0) goto L51
            goto L55
        L51:
            r6 = 2
            java.lang.String r0 = "location permission denied"
            goto L56
        L55:
            r0 = r1
        L56:
            r1 = 12004(0x2ee4, float:1.6821E-41)
            java.lang.String r2 = "s:"
            if (r5 != r1) goto L6f
            int r5 = r3.b0
            if (r5 == r6) goto L6c
            java.lang.String r5 = java.lang.String.valueOf(r6)
            java.lang.String r5 = r2.concat(r5)
            c.t.m.g.o4.o(r4, r5)
        L6c:
            r3.b0 = r6
            goto L7a
        L6f:
            java.lang.String r5 = java.lang.String.valueOf(r6)
            java.lang.String r5 = r2.concat(r5)
            c.t.m.g.o4.o(r4, r5)
        L7a:
            c.t.m.g.s3$f r5 = r3.f665f
            if (r5 == 0) goto La8
            r1 = 3102(0xc1e, float:4.347E-42)
            android.os.Message r5 = r5.obtainMessage(r1)
            android.os.Bundle r1 = r5.getData()
            if (r1 != 0) goto L8f
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
        L8f:
            r1.clear()
            java.lang.String r2 = "name"
            r1.putString(r2, r4)
            java.lang.String r4 = "status"
            r1.putInt(r4, r6)
            java.lang.String r4 = "desc"
            r1.putString(r4, r0)
            r5.setData(r1)
            r5.sendToTarget()
        La8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.t.m.g.s3.o(int, int, int):void");
    }

    public final void o0() {
        p5 d2;
        this.H = 0;
        this.w = null;
        this.v = null;
        this.y = null;
        this.P = null;
        this.I = 0L;
        v.f710e = 0L;
        this.W = false;
        this.X = false;
        this.f663c = -1;
        this.S = d.Stop;
        this.E = 0L;
        this.L = 0L;
        this.Y = 0;
        if (Build.VERSION.SDK_INT >= 12 && (d2 = this.z.d("cell")) != null) {
            d2.a();
        }
        g5 g5Var = this.A;
        if (g5Var != null) {
            g5Var.n("");
            this.A.c(0L);
            this.A.i(0L);
            this.A.q(0L);
            this.A.m(0L);
            this.A.t("");
        }
        k0.g().h();
    }

    public void onCellInfoEvent(c.t.m.g.f fVar) {
        s(fVar);
        if (this.U != null) {
            y yVar = new y(fVar.b, fVar.f404c, fVar.d, fVar.f406f, fVar.f405e, fVar.a.ordinal());
            ArrayList arrayList = new ArrayList();
            arrayList.add(yVar);
            this.U.i(yVar, arrayList);
        }
    }

    public void onGpsInfoEvent(n nVar) {
        t(nVar);
        p pVar = this.U;
        if (pVar != null) {
            pVar.f(nVar.a);
        }
    }

    public void onNetworkEvent(Integer num) {
        String b2 = g.b(this.z.a);
        int intValue = num.intValue();
        if (intValue == 0) {
            StringBuilder sb = new StringBuilder("onNetworkEvent: ");
            sb.append(b2);
            sb.append(" disconnected");
        } else if (intValue == 1) {
            StringBuilder sb2 = new StringBuilder("onNetworkEvent: ");
            sb2.append(b2);
            sb2.append(" connected");
            p(R2.drawable.button_b_02_dark, 1000L);
        }
    }

    public void onStatusEvent(Message message) {
        o(message.what, message.arg1, message.arg2);
    }

    public void onWifiInfoEvent(b1 b1Var) {
        new StringBuilder("onWifiInfoEvent mHandler is null? ").append(this.f664e == null);
        u(b1Var);
        p pVar = this.U;
        if (pVar != null) {
            pVar.k(b1Var.a());
        }
    }

    public final void p(int i2, long j2) {
        if (this.f664e == null) {
            this.f664e = new c(this.z.o().getLooper());
        }
        this.f664e.removeMessages(i2);
        this.f664e.sendEmptyMessageDelayed(i2, j2);
    }

    public final int p0() {
        x4 x4Var = this.f673n;
        int o = x4Var != null ? x4Var.o() : 1;
        if (o != 0) {
            this.w = null;
        }
        return o;
    }

    public final void q(int i2, q5 q5Var) {
        if (q5Var == null) {
            return;
        }
        if (i2 == 0 && q5Var.getLatitude() != 0.0d && q5Var.getLongitude() != 0.0d) {
            q5.s(q5Var, (this.d == 1 && a6.b(q5Var.getLatitude(), q5Var.getLongitude())) ? 1 : 0);
        }
        if (N()) {
            if (q5Var.getAccuracy() < 5000.0f && q5Var.getAccuracy() > 0.0f) {
                this.u.b(q5Var);
            }
            this.N = q5Var.getLatitude();
            this.O = q5Var.getLongitude();
            if (o.e(this.B) && this.B.size() > 0) {
                A0();
            }
        } else if (i2 == 0 && q5Var.getLatitude() != 0.0d && q5Var.getLongitude() != 0.0d && Math.abs(q5Var.getLatitude() - this.N) >= 1.0E-8d && Math.abs(q5Var.getLongitude() - this.O) >= 1.0E-8d) {
            if (!this.u.e(q5Var, this.z, this.f671l.S())) {
                new StringBuilder("discard ").append(q5Var);
                return;
            }
            this.N = q5Var.getLatitude();
            this.O = q5Var.getLongitude();
            if (q5Var.getAccuracy() < 5000.0f && q5Var.getAccuracy() > 0.0f) {
                this.u.a(q5Var);
                this.u.b(q5Var);
            }
        }
        boolean z = this.Q != 0 && i2 == 0;
        this.Q = i2;
        this.M = q5Var;
        new StringBuilder("mLastLocation location:").append(this.M.toString());
        if (this.K.getInterval() == 0 && o.e(this.B) && !this.B.isEmpty()) {
            new StringBuilder("mHandler is null? ").append(this.f664e == null);
            n(R2.drawable.wxa_setting_icon_back);
        } else if (z && o.e(this.B) && !this.B.isEmpty()) {
            new StringBuilder("mHandler is null? ").append(this.f664e == null);
            n(R2.drawable.wxa_setting_icon_back);
        }
    }

    public final void r(Looper looper) {
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        if (o.b(this.f665f) || this.f665f.getLooper() != looper) {
            this.f665f = new f(looper);
        }
        this.f665f.removeCallbacksAndMessages(null);
        Handler handler = this.f666g;
        if (handler == null || handler.getLooper() != Looper.getMainLooper()) {
            this.f666g = new Handler(Looper.getMainLooper());
        }
    }

    public final void r0() {
        s5.a = false;
        if (o.e(this.q)) {
            this.q.a();
        }
        if (o.e(this.r)) {
            this.r.m();
        }
        if (o.e(this.u)) {
            this.u.f();
        }
        if (o.e(this.f673n)) {
            this.f673n.r();
        }
        if (this.f670k) {
            if (o.e(this.f669j)) {
                this.f669j.k();
            }
        } else if (o.e(this.f668i)) {
            this.f668i.o();
        }
        if (o.e(this.f671l)) {
            this.f671l.c0();
        }
        if (o.e(this.f672m)) {
            this.f672m.k();
        }
        if (o.e(this.o) && this.K.isAllowDirection()) {
            this.o.d();
        }
        if (o.e(this.p)) {
            this.p.l();
        }
        p pVar = this.U;
        if (pVar != null) {
            pVar.q();
            this.U = null;
        }
        o4 o4Var = this.V;
        if (o4Var != null) {
            o4Var.c();
            this.V = null;
        }
        try {
            q2.q().c();
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x0056, code lost:
        if ((java.lang.System.currentTimeMillis() - r11.I) <= r0) goto L70;
     */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:82:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void s(c.t.m.g.f r12) {
        /*
            Method dump skipped, instructions count: 315
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.t.m.g.s3.s(c.t.m.g.f):void");
    }

    public final void t(n nVar) {
        double d2;
        double d3;
        if (nVar.a == m2.a) {
            return;
        }
        this.y = nVar;
        int requestLevel = this.K.getRequestLevel();
        q5 q5Var = this.P;
        Location location = new Location(nVar.a);
        Bundle extras = location.getExtras();
        if (extras != null) {
            d2 = extras.getDouble("lat");
            d3 = extras.getDouble(HybridSDK.LNG);
        } else {
            d2 = 0.0d;
            d3 = 0.0d;
        }
        StringBuilder sb = new StringBuilder("ongpschanged location extras:");
        sb.append(d2);
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append(d3);
        if (d2 == 0.0d && d3 == 0.0d) {
            o4.o("g", "defl error");
            return;
        }
        if (N()) {
            new StringBuilder("mHandler is null? ").append(this.f664e == null);
            n(R2.color.jdreact_common_textview_bg_color);
        }
        int b2 = k0.g().b(nVar);
        q5.b bVar = new q5.b();
        bVar.d(q5Var);
        bVar.g("gps");
        bVar.a(requestLevel);
        bVar.c(location.getExtras());
        bVar.b(new Location(nVar.a));
        q5 f2 = bVar.f();
        location.setLatitude(d2);
        location.setLongitude(d3);
        f2.v(location);
        f2.y(b2);
        StringBuilder sb2 = new StringBuilder("ongpschanged location:");
        sb2.append(f2.getLatitude());
        sb2.append(DYConstants.DY_REGEX_COMMA);
        sb2.append(f2.getLongitude());
        StringBuilder sb3 = new StringBuilder("ongpschanged updateLastLocation: mIndoorLocationStatus: ");
        sb3.append(this.H);
        sb3.append(", beforeFirstFix: ");
        sb3.append(N());
        if (this.H != 2) {
            q(0, f2);
            z(f2, 0, R2.color.bubble_chat_to_bg_color);
            StringBuilder sb4 = new StringBuilder("ongpschanged updateLastLocation:");
            sb4.append(f2.getLatitude());
            sb4.append(DYConstants.DY_REGEX_COMMA);
            sb4.append(f2.getLongitude());
        }
        o(R2.id.decode_failed, R2.drawable.x_dialog_bottom, 3);
    }

    public final void u(b1 b1Var) {
        new StringBuilder("onWifiChanged mHandler is null? ").append(this.f664e == null);
        b1 b1Var2 = this.x;
        boolean c2 = b1Var2 != null ? b1Var2.c(b1Var) : false;
        if (c2) {
            this.I = System.currentTimeMillis();
        }
        List<ScanResult> emptyList = b1Var == null ? Collections.emptyList() : b1Var.a();
        StringBuilder sb = new StringBuilder("len:");
        sb.append(emptyList.size());
        sb.append(",sim:");
        sb.append(c2 ? "1" : "0");
        StringBuilder sb2 = new StringBuilder(sb.toString());
        for (int i2 = 0; i2 < Math.min(3, emptyList.size()); i2++) {
            ScanResult scanResult = emptyList.get(i2);
            sb2.append(',');
            sb2.append(scanResult.BSSID.replaceAll(":", ""));
            sb2.append('_');
            sb2.append(scanResult.level);
        }
        o4.o("WIFI", sb2.toString());
        new StringBuilder("mIndoorLocationStatus").append(this.H);
        if (this.w == null || this.H == 2 || b1Var == b1.d || this.I == -1 || b1Var.a().size() < 3 || !c2) {
            o4.o("WIFI", "wifi req");
            new StringBuilder("mHandler is null? ").append(this.f664e == null);
            n(R2.color.jdreact_common_textview_bg_color);
        }
        this.w = b1Var;
    }

    public final void u0() {
        this.E = 0L;
        if (this.S == d.Single) {
            A(null);
        }
    }

    public final void w0() {
        if (y2.f().i("start_daemon")) {
            if (this.S == d.Normal && k1.b(this.z).equalsIgnoreCase("{}")) {
                try {
                    new Handler(this.z.o().getLooper()).postDelayed(new b(), Final.FIVE_SECOND);
                    this.R = System.currentTimeMillis();
                } catch (Throwable unused) {
                }
            } else {
                new StringBuilder("daemon not start! is wifi or running status=").append(this.S);
            }
        }
        this.S = d.Stop;
    }

    public final void y(q5 q5Var) {
        if (q5Var != null) {
            if (this.o != null && this.K.isAllowDirection()) {
                q5Var.getExtra().putDouble("direction", this.o.a());
            }
            try {
                q5Var.getExtra().putAll(this.K.getExtras());
            } catch (Exception unused) {
            }
        }
    }

    public boolean y0() {
        this.H = 1;
        o4.o("LOC", "start indoor");
        return true;
    }

    public final synchronized void z(q5 q5Var, int i2, int i3) {
        if (q5Var != null) {
            if (this.f665f != null) {
                StringBuilder sb = new StringBuilder("processLocationChanged location:");
                sb.append(q5Var.getLatitude());
                sb.append(DYConstants.DY_REGEX_COMMA);
                sb.append(q5Var.getLongitude());
                Bundle extras = this.K.getExtras();
                int i4 = this.Y;
                if (i4 != 0 && i2 == 0) {
                    q5Var.o(i4);
                    new StringBuilder("set nation code: ").append(q5Var.getNationCode());
                } else if (i2 == 0 && extras != null && extras.containsKey(this.a) && I0()) {
                    String string = extras.getString(this.a, "default");
                    if (!"default".equals(string)) {
                        this.b = string;
                        t.l(this.f664e, R2.dimen.dp_451, i2, i3, q5Var);
                        return;
                    }
                }
                Message obtainMessage = this.f665f.obtainMessage(i3);
                obtainMessage.arg1 = i2;
                obtainMessage.obj = q5Var;
                obtainMessage.sendToTarget();
                if (this.f665f.getLooper() == null || this.f665f.getLooper().getThread() == null || !this.f665f.getLooper().getThread().isAlive()) {
                    o4.o("U", i2 + ",user thread is invalid");
                    return;
                }
                return;
            }
        }
        o4.o("G", "user handler is null or loc is null");
    }
}
