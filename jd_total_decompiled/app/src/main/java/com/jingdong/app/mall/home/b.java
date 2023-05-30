package com.jingdong.app.mall.home;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.android.volley.Cache;
import com.facebook.react.animated.InterpolationAnimatedNode;
import com.jd.framework.json.JDJSONObject;
import com.jd.framework.network.JDNetworkHelper;
import com.jingdong.app.mall.home.activity.MainRightWebActivity;
import com.jingdong.app.mall.home.deploy.view.layout.banner2x4.DBanner2x4;
import com.jingdong.app.mall.home.floor.common.i.n;
import com.jingdong.app.mall.home.floor.common.i.s;
import com.jingdong.app.mall.home.floor.view.view.MallFloorBanner;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.unification.customtheme.UnCustomThemeHelper;
import com.jingdong.common.utils.UseCacheHttpGroupUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpGroupSetting;
import com.jingdong.jdsdk.network.toolbox.HttpGroupWithNPS;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class b {
    private static com.jingdong.app.mall.home.r.c.b o;
    private static long p;
    private d a;
    private volatile HttpResponse b;

    /* renamed from: c */
    private volatile HttpResponse f8604c;
    private volatile boolean d;

    /* renamed from: e */
    private final ExceptionReporter f8605e;

    /* renamed from: f */
    private final HttpGroupWithNPS f8606f;

    /* renamed from: g */
    private final AtomicBoolean f8607g;

    /* renamed from: h */
    private final UseCacheHttpGroupUtil f8608h;

    /* renamed from: i */
    private final UseCacheHttpGroupUtil.UseCacheListener f8609i;

    /* renamed from: j */
    private long f8610j;

    /* renamed from: k */
    private final AtomicBoolean f8611k;

    /* renamed from: l */
    private final AtomicBoolean f8612l;

    /* renamed from: m */
    public static final long f8602m = System.currentTimeMillis();

    /* renamed from: n */
    private static final String f8603n = Md5Encrypt.md5("welcomeHome" + PackageInfoUtil.getVersionName());
    private static long q = com.jingdong.app.mall.home.o.a.f.N("JD_APP_FIRST_TIME", -1);
    private static long r = com.jingdong.app.mall.home.o.a.f.N("JD_APP_FIRST_UP_TIME", -1);

    /* loaded from: classes4.dex */
    public class a implements UseCacheHttpGroupUtil.UseCacheListener {
        a() {
            b.this = r1;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (httpResponse != null && httpResponse.isCache()) {
                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                if (b.this.f8612l.get()) {
                    return;
                }
                if (fastJsonObject != null && !com.jingdong.app.mall.home.a.k().equals(fastJsonObject.optString("displayVersion"))) {
                    return;
                }
            }
            b.this.v(httpResponse);
            if (httpResponse == null || !httpResponse.isCache()) {
                b.e(SystemClock.elapsedRealtime());
                b.this.d = false;
            }
        }

        @Override // com.jingdong.common.utils.UseCacheHttpGroupUtil.UseCacheListener
        public void onEndWithoutUpdate() {
            if (b.this.a != null) {
                b.this.a.c();
            }
            b.this.d = false;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            b.this.s(httpError);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            if (b.this.a != null) {
                b.this.a.a(httpSettingParams);
            }
        }
    }

    /* renamed from: com.jingdong.app.mall.home.b$b */
    /* loaded from: classes4.dex */
    public class C0273b extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ HttpError f8614g;

        C0273b(HttpError httpError) {
            b.this = r1;
            this.f8614g = httpError;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            b.this.u();
            b.this.r(this.f8614g);
            b.this.f8607g.set(true);
        }
    }

    /* loaded from: classes4.dex */
    public class c extends com.jingdong.app.mall.home.o.a.b {
        c() {
            b.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            Cache.Entry entry = JDNetworkHelper.getGlobalJDRequestQueue().getCache().get(b.f8603n);
            if (b.this.b == null && !b.this.f8607g.get() && (b.this.f8612l.get() || entry == null || entry.isExpired())) {
                b.this.u();
            }
        }
    }

    /* loaded from: classes4.dex */
    public interface d {
        void a(HttpGroup.HttpSettingParams httpSettingParams);

        void b(HttpError httpError);

        void c();

        void d(HttpResponse httpResponse, boolean z);
    }

    /* loaded from: classes4.dex */
    public static class e {
        private static final b a = new b(null);
    }

    /* synthetic */ b(a aVar) {
        this();
    }

    static /* synthetic */ long e(long j2) {
        return j2;
    }

    public static b m() {
        return e.a;
    }

    public static String n(String str, boolean z) {
        int currentBannerFrameIndex;
        if (o == null) {
            o = com.jingdong.app.mall.home.r.c.b.c("");
        }
        o.a("type", str);
        if (DBanner2x4.A() > MallFloorBanner.getCurrentBannerSelectedTime()) {
            currentBannerFrameIndex = DBanner2x4.z();
        } else {
            currentBannerFrameIndex = MallFloorBanner.getCurrentBannerFrameIndex();
        }
        o.a("frame", currentBannerFrameIndex + "");
        if (z) {
            com.jingdong.app.mall.home.o.a.f.d(o);
            o.remove("position");
        } else {
            o.a("position", "2".equals(str) ? JDHomeFragment.o0 : "");
            o.remove("homestatus");
        }
        return o.toString();
    }

    private void o() {
        if (r >= 0) {
            return;
        }
        long N = com.jingdong.app.mall.home.o.a.f.N("JD_APP_FIRST_UP_TIME", 0);
        r = N;
        if (N > 0) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        com.jingdong.app.mall.home.o.a.f.z0("JD_APP_FIRST_UP_TIME", currentTimeMillis);
        r = currentTimeMillis;
    }

    private void q() {
        com.jingdong.app.mall.home.w.a.b(new c(), false);
    }

    public void r(HttpError httpError) {
        if (!this.f8607g.get()) {
            v(this.b);
        } else {
            d dVar = this.a;
            if (dVar != null) {
                dVar.b(httpError);
            }
        }
        this.d = false;
    }

    public void s(HttpError httpError) {
        if (!this.f8607g.get()) {
            com.jingdong.app.mall.home.o.a.f.F0(new C0273b(httpError), 200L);
        } else {
            r(httpError);
        }
    }

    public void u() {
        JDJSONObject r2 = com.jingdong.app.mall.home.o.a.k.r();
        if (this.b != null || r2 == null) {
            return;
        }
        this.b = new HttpResponse(null);
        this.b.setFastJsonObject(r2);
        this.b.setCache(true);
    }

    public void v(HttpResponse httpResponse) {
        if (httpResponse == null || this.f8604c == httpResponse) {
            return;
        }
        if (this.a != null) {
            this.f8604c = httpResponse;
            this.a.d(httpResponse, false);
            this.f8607g.set(true);
            this.b = null;
        } else if (this.f8607g.get()) {
        } else {
            this.b = httpResponse;
        }
    }

    private void x(com.jingdong.app.mall.ad.d dVar) {
        JSONObject jSONObject = new JSONObject();
        long currentTimeMillis = System.currentTimeMillis();
        if (dVar != null) {
            try {
                if (TextUtils.equals("0", dVar.q)) {
                    jSONObject.put("focusId", dVar.r);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        jSONObject.put("globalUIStyle", "10.0.0");
        jSONObject.put("displayVersion", com.jingdong.app.mall.home.a.k());
        jSONObject.put("xviewGuideFloor", UnCustomThemeHelper.getInstance().getNavigationIds());
        jSONObject.put("showCate", "1");
        jSONObject.put(InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY);
        jSONObject.put("allLastTime", com.jingdong.app.mall.home.o.a.f.u("APP_CENTER_UPDATETIME", "0"));
        jSONObject.put("tSTimes", String.valueOf(com.jingdong.app.mall.home.tips.c.j()));
        jSONObject.put("privacyType", com.jingdong.app.mall.home.o.a.f.k0() ? "1" : "0");
        jSONObject.put("fringe", "1");
        jSONObject.put("barHeight", String.valueOf(n.c()));
        jSONObject.put("cycFirstTimeStamp", com.jingdong.app.mall.home.o.a.f.u("HOME_CYCFIRSTTIMESTAMP", ""));
        jSONObject.put("cycNum", com.jingdong.app.mall.home.o.a.f.t("HOME_CYCNUM", 0));
        jSONObject.put("fQueryStamp", f8602m + "");
        jSONObject.put("homeAreaCode", String.valueOf(com.jingdong.app.mall.home.u.a.w().v()));
        com.jingdong.app.mall.home.o.a.f.i0(jSONObject);
        com.jingdong.app.mall.home.o.a.f.y(jSONObject);
        com.jingdong.app.mall.home.o.a.f.A(jSONObject);
        String u = com.jingdong.app.mall.home.o.a.f.u("HOMEPOZ", "");
        if (!TextUtils.isEmpty(u)) {
            jSONObject.put("poz", new JSONObject(u));
        }
        if (com.jingdong.app.mall.home.o.a.k.B()) {
            jSONObject.put("preLoadHours", com.jingdong.app.mall.home.o.a.k.u());
        }
        jSONObject.put("controlMaterials", com.jingdong.app.mall.home.o.a.f.u("HOME_CONTROL_MATERIALS", ""));
        jSONObject.put("appType", com.jingdong.app.mall.home.state.old.a.d());
        com.jingdong.app.mall.home.v.d.a.b(jSONObject);
        JDHomeFragment z0 = JDHomeFragment.z0();
        long H0 = z0 == null ? 0L : z0.H0();
        if (H0 > 0) {
            com.jingdong.app.mall.home.s.a.b().a();
            jSONObject.put("wakeup", String.valueOf(H0));
        }
        JDJSONObject f2 = s.f();
        if (f2 != null) {
            jSONObject.put("callback", f2);
        }
        ChannelInfo channelInfo = ChannelInfo.getChannelInfo();
        if (channelInfo != null && p != channelInfo.getSaveTime()) {
            com.jingdong.app.mall.home.s.a.b().a();
            p = channelInfo.getSaveTime();
            new com.jingdong.app.mall.home.floor.ctrl.t.a(channelInfo.getInfo()).b(jSONObject);
        }
        if (com.jingdong.app.mall.home.c.f().j()) {
            jSONObject.put("privacyVersion", com.jingdong.app.mall.home.c.f().e());
        }
        long j2 = q;
        if (j2 > 0) {
            jSONObject.put("firstOpenTime", String.valueOf(currentTimeMillis - j2));
        }
        long j3 = r;
        if (j3 > 0) {
            jSONObject.put("firstUpTime", String.valueOf(currentTimeMillis - j3));
        } else {
            o();
        }
        com.jingdong.app.mall.home.o.a.f.h0(jSONObject);
        com.jingdong.app.mall.home.o.a.f.a0(jSONObject);
        this.d = true;
        com.jingdong.app.mall.home.o.a.k.e("request welcomeHome once");
        this.f8610j = currentTimeMillis;
        HttpSetting addUseCache = this.f8608h.addUseCache(this.f8606f.getHttpGroup(), "welcomeHome", jSONObject.toString(), true, true, this.f8609i);
        addUseCache.setNeedRetryOnBusinessLayer(false);
        this.f8605e.attachHttpSetting(addUseCache);
        com.jingdong.app.mall.home.floor.common.i.k.o();
    }

    private void y(com.jingdong.app.mall.ad.d dVar, String str) {
        com.jingdong.app.mall.home.r.c.a.s("Home_Refresh", "", n(str, false));
        x(dVar);
    }

    public boolean A(String str) {
        return B(false, str);
    }

    public boolean B(boolean z, String str) {
        return z(null, z, str);
    }

    public void C(d dVar) {
        this.a = dVar;
    }

    public HttpGroupWithNPS l() {
        return this.f8606f;
    }

    public boolean p() {
        return this.d;
    }

    public void t() {
        if (q >= 0) {
            return;
        }
        long N = com.jingdong.app.mall.home.o.a.f.N("JD_APP_FIRST_TIME", 0);
        q = N;
        if (N > 0) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        com.jingdong.app.mall.home.o.a.f.z0("JD_APP_FIRST_TIME", currentTimeMillis);
        q = currentTimeMillis;
    }

    public void w() {
        ExceptionReporter exceptionReporter = this.f8605e;
        if (exceptionReporter != null) {
            exceptionReporter.reportHttpBusinessException(this.b);
        }
    }

    public boolean z(com.jingdong.app.mall.ad.d dVar, boolean z, String str) {
        if (z) {
            MainRightWebActivity.X();
        }
        if (this.f8611k.getAndSet(false)) {
            q();
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.f8610j;
        boolean z2 = true;
        if (this.b != null && this.a != null && !this.f8607g.getAndSet(true)) {
            if (this.d || (elapsedRealtime <= 10000 && !this.b.isCache())) {
                z2 = false;
            }
            v(this.b);
            if (z2) {
                y(dVar, str);
            }
        } else {
            if (this.f8607g.get()) {
                this.b = null;
            }
            if (this.d && elapsedRealtime > Final.HALF_MINUTE) {
                this.d = false;
            }
            if (!this.d) {
                y(dVar, str);
                return true;
            }
        }
        return false;
    }

    private b() {
        this.f8607g = new AtomicBoolean(false);
        UseCacheHttpGroupUtil useCacheHttpGroupUtil = new UseCacheHttpGroupUtil();
        this.f8608h = useCacheHttpGroupUtil;
        this.f8610j = 0L;
        this.f8611k = new AtomicBoolean(true);
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.f8612l = atomicBoolean;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        atomicBoolean.set(elapsedRealtime - com.jingdong.app.mall.home.o.a.f.N("INIT_TIME_KEY", 0) < 8000);
        com.jingdong.app.mall.home.o.a.f.z0("INIT_TIME_KEY", elapsedRealtime);
        this.f8605e = new ExceptionReporter();
        useCacheHttpGroupUtil.setInterval(0);
        useCacheHttpGroupUtil.setUseLocalCookie(true);
        useCacheHttpGroupUtil.setMd5Cachekey(f8603n);
        useCacheHttpGroupUtil.setHost(Configuration.getPortalHost());
        Context applicationContext = JdSdk.getInstance().getApplicationContext();
        HttpGroupSetting createNewSettings = HttpGroupUtils.createNewSettings();
        createNewSettings.setType(1000);
        this.f8606f = new HttpGroupWithNPS(applicationContext, HttpGroup.getHttpGroup(createNewSettings), "JDHomeFragment", "", false);
        this.f8609i = new a();
    }
}
