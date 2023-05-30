package com.jingdong.manto;

import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.ValueCallback;
import com.huawei.hms.actions.SearchIntents;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.jdma.minterface.BaseEvent;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.OpenJsApiManager;
import com.jingdong.manto.jsengine.IMantoWebViewJS;
import com.jingdong.manto.k.a;
import com.jingdong.manto.m.e0;
import com.jingdong.manto.m.k0;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.t;
import com.jingdong.manto.utils.v;
import com.jingdong.sdk.jdhttpdns.config.HttpDnsConfig;
import com.tencent.connect.common.Constants;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class h extends e0 implements a.b {
    private static final String r = "h";

    /* renamed from: e */
    public int f13034e;

    /* renamed from: f */
    com.jingdong.manto.f f13035f;

    /* renamed from: g */
    IMantoWebViewJS f13036g;

    /* renamed from: h */
    public k0 f13037h;

    /* renamed from: i */
    public com.jingdong.manto.m.v1.d f13038i;

    /* renamed from: n */
    private boolean f13043n;
    private com.jingdong.manto.m.u1.d o;
    public volatile boolean d = true;

    /* renamed from: j */
    private CopyOnWriteArrayList<i> f13039j = new CopyOnWriteArrayList<>();

    /* renamed from: k */
    private boolean f13040k = false;

    /* renamed from: l */
    private boolean f13041l = false;

    /* renamed from: m */
    private boolean f13042m = false;
    private boolean p = false;
    private CopyOnWriteArrayList<String> q = new CopyOnWriteArrayList<>();

    /* loaded from: classes15.dex */
    public class a implements Runnable {
        a() {
            h.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            h hVar = h.this;
            hVar.f13036g = hVar.k();
            h hVar2 = h.this;
            hVar2.f13037h = hVar2.l();
            h hVar3 = h.this;
            hVar3.f13038i = hVar3.n();
            h.this.y();
            com.jingdong.manto.k.a.b().a(h.this);
        }
    }

    /* loaded from: classes15.dex */
    public class b implements v.b {
        b(h hVar) {
        }

        @Override // com.jingdong.manto.utils.v.b
        public void a(String str) {
        }

        @Override // com.jingdong.manto.utils.v.b
        public void onSuccess(String str) {
        }
    }

    /* loaded from: classes15.dex */
    public class c implements v.b {
        c(h hVar) {
        }

        @Override // com.jingdong.manto.utils.v.b
        public void a(String str) {
        }

        @Override // com.jingdong.manto.utils.v.b
        public void onSuccess(String str) {
        }
    }

    /* loaded from: classes15.dex */
    public class d implements ValueCallback<String> {
        d(h hVar) {
        }

        @Override // android.webkit.ValueCallback
        /* renamed from: a */
        public void onReceiveValue(String str) {
        }
    }

    /* loaded from: classes15.dex */
    public class e implements v.b {
        e(h hVar) {
        }

        @Override // com.jingdong.manto.utils.v.b
        public void a(String str) {
        }

        @Override // com.jingdong.manto.utils.v.b
        public void onSuccess(String str) {
        }
    }

    /* loaded from: classes15.dex */
    public class f implements ValueCallback<String> {
        f(h hVar) {
        }

        @Override // android.webkit.ValueCallback
        /* renamed from: a */
        public void onReceiveValue(String str) {
        }
    }

    /* loaded from: classes15.dex */
    public class g implements ValueCallback<String> {
        g(h hVar) {
        }

        @Override // android.webkit.ValueCallback
        /* renamed from: a */
        public void onReceiveValue(String str) {
        }
    }

    /* renamed from: com.jingdong.manto.h$h */
    /* loaded from: classes15.dex */
    public class C0512h implements ValueCallback<String> {
        C0512h(h hVar) {
        }

        @Override // android.webkit.ValueCallback
        /* renamed from: a */
        public void onReceiveValue(String str) {
        }
    }

    /* loaded from: classes15.dex */
    public static class i {
        String a;
        String b;

        /* renamed from: c */
        int f13044c;

        i(String str, String str2, int i2) {
            this.a = str;
            this.b = str2;
            this.f13044c = i2;
        }
    }

    public h() {
        new Handler(Looper.getMainLooper()).post(new a());
    }

    private static void a(JSONObject jSONObject, String str, Object obj) {
        try {
            jSONObject.put(str, obj);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void c(String str) {
        IMantoWebViewJS iMantoWebViewJS;
        com.jingdong.manto.jsengine.b bVar;
        if (MantoStringUtils.isEmpty(str) || (iMantoWebViewJS = this.f13036g) == null || (bVar = (com.jingdong.manto.jsengine.b) iMantoWebViewJS.getInterface(com.jingdong.manto.jsengine.b.class)) == null) {
            return;
        }
        bVar.setTitle(str);
    }

    public IMantoWebViewJS k() {
        return com.jingdong.manto.jsengine.d.a(com.jingdong.manto.c.a());
    }

    public k0 l() {
        k0 k0Var = new k0(this, this.f13036g);
        IMantoWebViewJS iMantoWebViewJS = this.f13036g;
        if (iMantoWebViewJS != null) {
            iMantoWebViewJS.addJavascriptInterface(k0Var, "JDJSCore");
            com.jingdong.manto.m.u1.d dVar = new com.jingdong.manto.m.u1.d();
            this.o = dVar;
            this.f13036g.addJavascriptInterface(dVar, "JDJSCanvas");
        }
        return k0Var;
    }

    private JSONObject m() {
        JSONObject jSONObject = new JSONObject();
        a(jSONObject, Constants.PARAM_PLATFORM, "android");
        a(jSONObject, HybridSDK.APP_VERSION, (Object) 1);
        IMantoWebViewJS iMantoWebViewJS = this.f13036g;
        if (iMantoWebViewJS != null) {
            com.jingdong.manto.jsengine.a aVar = (com.jingdong.manto.jsengine.a) iMantoWebViewJS.getInterface(com.jingdong.manto.jsengine.a.class);
            a(jSONObject, "nativeBufferEnabled", Boolean.valueOf(aVar != null && aVar.canUseNativeBuffer()));
        }
        a(jSONObject, HttpDnsConfig.PREDOWNLOAD_PARAMS, Boolean.TRUE);
        a(jSONObject, "system", "Android " + Build.VERSION.RELEASE);
        return jSONObject;
    }

    public com.jingdong.manto.m.v1.d n() {
        com.jingdong.manto.m.v1.d dVar = new com.jingdong.manto.m.v1.d(this);
        IMantoWebViewJS iMantoWebViewJS = this.f13036g;
        if (iMantoWebViewJS != null) {
            iMantoWebViewJS.addJavascriptInterface(dVar, "JDWorker");
        }
        return dVar;
    }

    private void o() {
        CopyOnWriteArrayList<i> copyOnWriteArrayList = this.f13039j;
        if (copyOnWriteArrayList == null) {
            return;
        }
        Iterator<i> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            i next = it.next();
            super.a(next.a, next.b, next.f13044c);
        }
        this.f13039j = null;
    }

    private void q() {
        String b2 = com.jingdong.manto.pkg.b.g.b(this.f13035f, "app-service.js");
        IMantoWebViewJS iMantoWebViewJS = this.f13036g;
        if (iMantoWebViewJS == null) {
            return;
        }
        v.a(iMantoWebViewJS, b2, new e(this));
    }

    private void r() {
        if (this.f13041l) {
            return;
        }
        this.f13041l = true;
        String c2 = com.jingdong.manto.pkg.b.f.c("jdcanvas.js");
        IMantoWebViewJS iMantoWebViewJS = this.f13036g;
        if (iMantoWebViewJS == null) {
            return;
        }
        iMantoWebViewJS.evaluateJavascript(c2, new f(this));
    }

    private void s() {
        com.jingdong.manto.f fVar;
        com.jingdong.manto.m.u1.d dVar = this.o;
        if (dVar != null && (fVar = this.f13035f) != null) {
            dVar.a(fVar.f13019k);
        }
        String format = String.format("var __jdConfig = %s;\nvar __jdIndexPage = \"%s\"", j().toString(), this.f13035f.t.a);
        IMantoWebViewJS iMantoWebViewJS = this.f13036g;
        if (iMantoWebViewJS != null) {
            iMantoWebViewJS.evaluateJavascript(format, null);
        }
        super.a("onJdConfigReady", "", 0);
    }

    private void t() {
        IMantoWebViewJS iMantoWebViewJS;
        if (this.f13040k) {
            return;
        }
        this.f13040k = true;
        String str = "javascript:" + com.jingdong.manto.pkg.b.f.c("NABridge.js") + com.jingdong.manto.pkg.b.f.c("NAService.js");
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        if (OpenJsApiManager.getSApiMap() != null && OpenJsApiManager.getSApiMap().size() > 0) {
            String c2 = com.jingdong.manto.pkg.b.f.c("NAServiceExt.js");
            if (!TextUtils.isEmpty(c2)) {
                sb.append(c2);
            }
        }
        String sb2 = sb.toString();
        if (MantoStringUtils.isEmpty(sb2) || (iMantoWebViewJS = this.f13036g) == null) {
            return;
        }
        v.a(iMantoWebViewJS, sb2, new c(this));
    }

    private void u() {
        com.jingdong.manto.f fVar;
        if (this.f13042m || (fVar = this.f13035f) == null || !fVar.s.r) {
            return;
        }
        this.f13042m = true;
        String c2 = com.jingdong.manto.pkg.b.f.c("NAPerf.js");
        IMantoWebViewJS iMantoWebViewJS = this.f13036g;
        if (iMantoWebViewJS == null) {
            return;
        }
        iMantoWebViewJS.evaluateJavascript(c2, new d(this));
    }

    private void v() {
        String format = String.format("var __jdConfig = %s;", m().toString());
        IMantoWebViewJS iMantoWebViewJS = this.f13036g;
        if (iMantoWebViewJS != null) {
            iMantoWebViewJS.evaluateJavascript(format, null);
        }
    }

    private void w() {
        boolean e2 = com.jingdong.manto.s.a.f().e();
        boolean d2 = com.jingdong.manto.s.b.e().d();
        if (e2 || d2) {
            String c2 = com.jingdong.manto.pkg.b.f.c("NAServiceRemoteDebug.js");
            IMantoWebViewJS iMantoWebViewJS = this.f13036g;
            if (iMantoWebViewJS == null) {
                return;
            }
            iMantoWebViewJS.evaluateJavascript(c2, new C0512h(this));
        }
    }

    private void x() {
        if (this.p) {
            return;
        }
        this.p = true;
        String c2 = com.jingdong.manto.pkg.b.f.c("NATimerBridge.js");
        IMantoWebViewJS iMantoWebViewJS = this.f13036g;
        if (iMantoWebViewJS != null && (iMantoWebViewJS instanceof com.jingdong.manto.jsengine.e)) {
            iMantoWebViewJS.evaluateJavascript(c2, new g(this));
        }
    }

    public boolean A() {
        com.jingdong.manto.f fVar = this.f13035f;
        if (fVar == null) {
            return false;
        }
        return fVar.P();
    }

    public void a(int i2) {
        com.jingdong.manto.f fVar = this.f13035f;
        if (fVar != null && fVar.k() != null && this.f13040k && A()) {
            String str = i2 == 0 ? FontsUtil.KEY_MULTI_LIGHT : CustomThemeConstance.NAVI_IMAGE_DARK_TAG;
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(CustomThemeConstance.TABLE_NAME, str);
            } catch (Throwable unused) {
            }
            a("onThemeChange", jSONObject.toString(), 0);
        }
    }

    public void a(String str) {
        if (!this.f13043n || this.q.contains(str)) {
            return;
        }
        String a2 = com.jingdong.manto.pkg.b.g.a(this.f13035f, str, "app-service.js");
        IMantoWebViewJS iMantoWebViewJS = this.f13036g;
        if (iMantoWebViewJS == null) {
            return;
        }
        v.a(iMantoWebViewJS, a2, new b(this));
        this.q.add(str);
    }

    @Override // com.jingdong.manto.m.e0
    public void a(String str, String str2, int i2) {
        CopyOnWriteArrayList<i> copyOnWriteArrayList = this.f13039j;
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.add(new i(str, str2, i2));
        } else {
            super.a(str, str2, i2);
        }
    }

    @Override // com.jingdong.manto.m.e0
    public void a(String str, String str2, int[] iArr) {
        h().f13014f.a(str, str2, iArr);
    }

    public boolean b(String str) {
        return this.q.contains(str);
    }

    @Override // com.jingdong.manto.m.e0
    public k0 d() {
        return this.f13037h;
    }

    @Override // com.jingdong.manto.m.e0
    public String e() {
        return "Service";
    }

    @Override // com.jingdong.manto.m.e0
    public boolean f() {
        return this.d;
    }

    @Override // com.jingdong.manto.m.e0
    public IMantoWebViewJS g() {
        return this.f13036g;
    }

    @Override // com.jingdong.manto.m.e0
    public com.jingdong.manto.f h() {
        return this.f13035f;
    }

    public void i() {
        this.d = false;
        IMantoWebViewJS iMantoWebViewJS = this.f13036g;
        if (iMantoWebViewJS != null) {
            iMantoWebViewJS.destroy();
        }
        k0 k0Var = this.f13037h;
        if (k0Var != null) {
            k0Var.a();
        }
        com.jingdong.manto.m.v1.d dVar = this.f13038i;
        if (dVar != null) {
            dVar.a();
        }
        this.q.clear();
        com.jingdong.manto.k.a.b().b(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:128:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x014c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public JSONObject j() {
        JSONObject jSONObject;
        IMantoWebViewJS iMantoWebViewJS;
        boolean z;
        boolean A;
        String str;
        com.jingdong.manto.f fVar;
        PkgDetailEntity pkgDetailEntity;
        JSONObject jSONObject2 = new JSONObject();
        com.jingdong.manto.f fVar2 = this.f13035f;
        com.jingdong.manto.i.e eVar = fVar2.s;
        JSONObject jSONObject3 = fVar2.t.f13047e;
        Iterator<String> keys = jSONObject3.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                jSONObject2.putOpt(next, jSONObject3.opt(next));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        a(jSONObject2, IMantoBaseModule.CARD_MODE, Boolean.valueOf(this.f13035f.u()));
        a(jSONObject2, "appType", Integer.valueOf(this.f13035f.r.d));
        a(jSONObject2, "debug", Boolean.valueOf(eVar.f13094c));
        a(jSONObject2, "downloadDomain", this.f13035f.s.f13104n);
        a(jSONObject2, Constants.PARAM_PLATFORM, "android");
        a(jSONObject2, "system", "Android " + Build.VERSION.RELEASE);
        a(jSONObject2, HybridSDK.APP_VERSION, "1");
        JSONObject jSONObject4 = new JSONObject();
        try {
        } catch (Exception e3) {
            MantoLog.e(r, e3);
        }
        if (!TextUtils.isEmpty(this.f13035f.r.f13090m)) {
            jSONObject = new JSONObject(this.f13035f.r.f13090m);
            a(jSONObject4, BaseEvent.SCENE, this.f13035f.r.f13091n);
            a(jSONObject4, "path", t.b(this.f13035f.m()));
            a(jSONObject4, SearchIntents.EXTRA_QUERY, jSONObject);
            Boolean bool = Boolean.FALSE;
            a(jSONObject4, "topBarStatus", bool);
            a(jSONObject4, "referrerInfo", (Object) null);
            a(jSONObject4, "shareInfo", (Object) null);
            a(jSONObject4, "isSticky", bool);
            JSONObject jSONObject5 = new JSONObject();
            a(jSONObject5, "maxRequestConcurrent", Integer.valueOf(eVar.f13095e));
            a(jSONObject5, "maxUploadConcurrent", Integer.valueOf(eVar.f13096f));
            a(jSONObject5, "maxDownloadConcurrent", Integer.valueOf(eVar.f13097g));
            a(jSONObject5, "maxWebsocketConnect", Integer.valueOf(eVar.f13098h));
            a(jSONObject5, "maxWorkerConcurrent", Integer.valueOf(eVar.f13099i));
            a(jSONObject2, "appLaunchInfo", jSONObject4);
            a(jSONObject2, "jdAppInfo", jSONObject5);
            a(jSONObject2, "isPluginMiniProgram", bool);
            iMantoWebViewJS = this.f13036g;
            z = true;
            if (iMantoWebViewJS == null) {
                com.jingdong.manto.jsengine.a aVar = (com.jingdong.manto.jsengine.a) iMantoWebViewJS.getInterface(com.jingdong.manto.jsengine.a.class);
                a(jSONObject2, "nativeBufferEnabled", Boolean.valueOf(aVar != null && aVar.canUseNativeBuffer()));
            } else {
                a(jSONObject2, "nativeBufferEnabled", bool);
            }
            JSONObject jSONObject6 = new JSONObject();
            a(jSONObject6, "USER_DATA_PATH", "jdfile://usr");
            a(jSONObject2, "env", jSONObject6);
            a(jSONObject2, "appContactInfo", "{}");
            A = A();
            str = FontsUtil.KEY_MULTI_LIGHT;
            if (A && com.jingdong.manto.k.a.b().a() != 0) {
                str = CustomThemeConstance.NAVI_IMAGE_DARK_TAG;
            }
            a(jSONObject2, CustomThemeConstance.TABLE_NAME, str);
            if (!com.jingdong.manto.s.a.f().e() && !com.jingdong.manto.s.b.e().d()) {
                z = false;
            }
            a(jSONObject2, "remoteDebugMode", Boolean.valueOf(z));
            fVar = this.f13035f;
            if (fVar != null && (pkgDetailEntity = fVar.f13016h) != null) {
                a(jSONObject2, "apiWhiteList", pkgDetailEntity.apiWhiteList);
            }
            return jSONObject2;
        }
        jSONObject = null;
        a(jSONObject4, BaseEvent.SCENE, this.f13035f.r.f13091n);
        a(jSONObject4, "path", t.b(this.f13035f.m()));
        a(jSONObject4, SearchIntents.EXTRA_QUERY, jSONObject);
        Boolean bool2 = Boolean.FALSE;
        a(jSONObject4, "topBarStatus", bool2);
        a(jSONObject4, "referrerInfo", (Object) null);
        a(jSONObject4, "shareInfo", (Object) null);
        a(jSONObject4, "isSticky", bool2);
        JSONObject jSONObject52 = new JSONObject();
        a(jSONObject52, "maxRequestConcurrent", Integer.valueOf(eVar.f13095e));
        a(jSONObject52, "maxUploadConcurrent", Integer.valueOf(eVar.f13096f));
        a(jSONObject52, "maxDownloadConcurrent", Integer.valueOf(eVar.f13097g));
        a(jSONObject52, "maxWebsocketConnect", Integer.valueOf(eVar.f13098h));
        a(jSONObject52, "maxWorkerConcurrent", Integer.valueOf(eVar.f13099i));
        a(jSONObject2, "appLaunchInfo", jSONObject4);
        a(jSONObject2, "jdAppInfo", jSONObject52);
        a(jSONObject2, "isPluginMiniProgram", bool2);
        iMantoWebViewJS = this.f13036g;
        z = true;
        if (iMantoWebViewJS == null) {
        }
        JSONObject jSONObject62 = new JSONObject();
        a(jSONObject62, "USER_DATA_PATH", "jdfile://usr");
        a(jSONObject2, "env", jSONObject62);
        a(jSONObject2, "appContactInfo", "{}");
        A = A();
        str = FontsUtil.KEY_MULTI_LIGHT;
        if (A) {
            str = CustomThemeConstance.NAVI_IMAGE_DARK_TAG;
        }
        a(jSONObject2, CustomThemeConstance.TABLE_NAME, str);
        if (!com.jingdong.manto.s.a.f().e()) {
            z = false;
        }
        a(jSONObject2, "remoteDebugMode", Boolean.valueOf(z));
        fVar = this.f13035f;
        if (fVar != null) {
            a(jSONObject2, "apiWhiteList", pkgDetailEntity.apiWhiteList);
        }
        return jSONObject2;
    }

    @Override // com.jingdong.manto.k.a.b
    public void onDeepModeChanged(int i2) {
        a(i2);
    }

    public final Activity p() {
        com.jingdong.manto.f fVar = this.f13035f;
        if (fVar == null) {
            return null;
        }
        return fVar.i();
    }

    public void y() {
        v();
        r();
        x();
        t();
        c("https://service.vapp.jd.com/preload/js-engine");
    }

    public void z() {
        if (this.f13043n) {
            return;
        }
        s();
        r();
        x();
        t();
        u();
        q();
        w();
        o();
        c(String.format("https://service.vapp.jd.com/%s/js-engine", a()));
        this.f13043n = true;
    }
}
