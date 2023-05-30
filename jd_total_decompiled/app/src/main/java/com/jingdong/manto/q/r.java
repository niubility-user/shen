package com.jingdong.manto.q;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.ValueCallback;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.manto.BaseWebView;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.OpenJsApiManager;
import com.jingdong.manto.jsengine.IMantoBaseInterface;
import com.jingdong.manto.jsengine.IMantoWebViewJS;
import com.jingdong.manto.k.a;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.manto.utils.z;
import com.jingdong.sdk.jdhttpdns.config.HttpDnsConfig;
import com.jingdong.sdk.jweb.JDWebView;
import com.jingdong.sdk.jweb.JWebChromeClient;
import com.jingdong.sdk.jweb.JWebResourceRequest;
import com.jingdong.sdk.jweb.JWebResourceResponse;
import com.jingdong.sdk.jweb.JWebSettings;
import com.jingdong.sdk.jweb.JWebType;
import com.jingdong.sdk.jweb.JWebViewCallbackClient;
import com.jingdong.sdk.jweb.JWebViewClient;
import com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class r extends BaseWebView implements IMantoWebViewJS, com.jingdong.manto.jsengine.b, a.b {
    private static final String C = r.class.getSimpleName();
    private JWebViewCallbackClient A;
    private ProxyWebViewClientExtension B;

    /* renamed from: i */
    boolean f14101i;

    /* renamed from: j */
    boolean f14102j;

    /* renamed from: k */
    private boolean f14103k;

    /* renamed from: l */
    private Handler f14104l;

    /* renamed from: m */
    private final LinkedList<Pair<String, ValueCallback<String>>> f14105m;

    /* renamed from: n */
    boolean f14106n;
    private volatile boolean o;
    com.jingdong.manto.f p;
    String q;
    private String r;
    private String s;
    public String t;
    public boolean u;
    private String v;
    public Animator w;
    public h x;
    private JWebViewClient y;
    private JWebChromeClient z;

    /* loaded from: classes16.dex */
    public class a extends JWebViewClient {
        a() {
            r.this = r1;
        }

        private JWebResourceResponse a(String str) {
            JWebResourceResponse c2;
            JWebResourceResponse jWebResourceResponse = null;
            if (str.startsWith(r.this.getFrameBasePath())) {
                if (str.equals(r.this.getFramePath())) {
                    c2 = com.jingdong.manto.pkg.b.f.b("NAPageFrame.html");
                } else {
                    String replaceFirst = str.replaceFirst(r.this.getFrameBasePath(), "");
                    if (!TextUtils.isEmpty(r.this.v)) {
                        r rVar = r.this;
                        jWebResourceResponse = com.jingdong.manto.pkg.b.g.b(rVar.p, rVar.v, replaceFirst);
                    }
                    c2 = jWebResourceResponse == null ? com.jingdong.manto.pkg.b.g.c(r.this.p, replaceFirst) : jWebResourceResponse;
                }
                if (c2 == null) {
                    c2 = new JWebResourceResponse("image/*", "utf-8", 404, "Not Found", new HashMap(), new ByteArrayInputStream(new byte[0]));
                }
            } else {
                if (str.startsWith("jdfile://")) {
                    com.jingdong.manto.t.d g2 = com.jingdong.manto.t.c.g(r.this.p.f13019k, str);
                    if (g2 != null) {
                        try {
                            jWebResourceResponse = new JWebResourceResponse(g2.f14209c, "utf-8", new FileInputStream(g2.b));
                        } catch (FileNotFoundException unused) {
                        }
                    }
                } else if ("1".equals(com.jingdong.manto.utils.m.a("webInterruptHttp", "1")) && com.jingdong.manto.utils.t.d(str)) {
                    return null;
                } else {
                    if (!TextUtils.isEmpty(r.this.v)) {
                        r rVar2 = r.this;
                        jWebResourceResponse = com.jingdong.manto.pkg.b.g.b(rVar2.p, rVar2.v, str);
                    }
                    if (jWebResourceResponse == null) {
                        c2 = com.jingdong.manto.pkg.b.g.c(r.this.p, str);
                    }
                }
                c2 = jWebResourceResponse;
            }
            if (c2 != null && c2.getStatusCode() != 404) {
                c2.setStatusCodeAndReasonPhrase(200, "Ok");
                c2.setResponseHeaders(new HashMap());
            }
            return c2;
        }

        @Override // com.jingdong.sdk.jweb.JWebViewClient
        public void onPageFinished(JDWebView jDWebView, String str) {
            r rVar = r.this;
            if (rVar.f14106n) {
                return;
            }
            rVar.f14106n = true;
            rVar.d();
            r.this.f();
            r.this.j();
            r.this.h();
            r.this.g();
            r rVar2 = r.this;
            String str2 = rVar2.t;
            if (str2 != null) {
                rVar2.f(str2);
            }
        }

        @Override // com.jingdong.sdk.jweb.JWebViewClient
        public void onPageStarted(JDWebView jDWebView, String str, Bitmap bitmap) {
            super.onPageStarted(jDWebView, str, bitmap);
        }

        @Override // com.jingdong.sdk.jweb.JWebViewClient
        public JWebResourceResponse shouldInterceptRequest(JDWebView jDWebView, JWebResourceRequest jWebResourceRequest) {
            Uri url;
            if (jWebResourceRequest == null || (url = jWebResourceRequest.getUrl()) == null) {
                return null;
            }
            String uri = url.toString();
            if (MantoStringUtils.isEmpty(uri)) {
                return null;
            }
            return a(uri);
        }

        @Override // com.jingdong.sdk.jweb.JWebViewClient
        public JWebResourceResponse shouldInterceptRequest(JDWebView jDWebView, String str) {
            return !TextUtils.isEmpty(str) ? a(str) : super.shouldInterceptRequest(jDWebView, str);
        }

        @Override // com.jingdong.sdk.jweb.JWebViewClient
        public boolean shouldOverrideUrlLoading(JDWebView jDWebView, JWebResourceRequest jWebResourceRequest) {
            return true;
        }

        @Override // com.jingdong.sdk.jweb.JWebViewClient
        public boolean shouldOverrideUrlLoading(JDWebView jDWebView, String str) {
            return true;
        }
    }

    /* loaded from: classes16.dex */
    public class b extends JWebChromeClient {
        b(r rVar) {
        }

        @Override // com.jingdong.sdk.jweb.JWebChromeClient
        public void onHideCustomView() {
            super.onHideCustomView();
        }

        @Override // com.jingdong.sdk.jweb.JWebChromeClient
        public void onShowCustomView(View view, JWebChromeClient.CustomViewCallback customViewCallback) {
            super.onShowCustomView(view, customViewCallback);
        }
    }

    /* loaded from: classes16.dex */
    public class c implements JWebViewCallbackClient {
        c() {
            r.this = r1;
        }

        @Override // com.jingdong.sdk.jweb.JWebViewCallbackClient
        public void computeScroll(View view) {
            r.this.computeScroll();
        }

        @Override // com.jingdong.sdk.jweb.JWebViewCallbackClient
        public boolean dispatchTouchEvent(MotionEvent motionEvent, View view) {
            return r.this.dispatchTouchEvent(motionEvent);
        }

        @Override // com.jingdong.sdk.jweb.JWebViewCallbackClient
        public void invalidate() {
            r.this.invalidate();
        }

        @Override // com.jingdong.sdk.jweb.JWebViewCallbackClient
        public boolean onInterceptTouchEvent(MotionEvent motionEvent, View view) {
            return r.this.onInterceptTouchEvent(motionEvent);
        }

        @Override // com.jingdong.sdk.jweb.JWebViewCallbackClient
        public void onOverScrolled(int i2, int i3, boolean z, boolean z2, View view) {
            r.this.onOverScrolled(i2, i3, z, z2);
        }

        @Override // com.jingdong.sdk.jweb.JWebViewCallbackClient
        public void onScrollChanged(int i2, int i3, int i4, int i5, View view) {
            h hVar = r.this.x;
            if (hVar != null) {
                hVar.onScrollChanged(i2, i3, i4, i5, view);
            }
            r.this.onScrollChanged(i2, i3, i4, i5);
        }

        @Override // com.jingdong.sdk.jweb.JWebViewCallbackClient
        public boolean onTouchEvent(MotionEvent motionEvent, View view) {
            return r.this.onTouchEvent(motionEvent);
        }

        @Override // com.jingdong.sdk.jweb.JWebViewCallbackClient
        public boolean overScrollBy(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, View view) {
            return r.this.overScrollBy(i2, i3, i4, i5, i6, i7, i8, i9, z);
        }
    }

    /* loaded from: classes16.dex */
    public class d extends ProxyWebViewClientExtension {
        d() {
            r.this = r1;
        }

        @Override // com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension, com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension
        public void computeScroll(View view) {
            r.this.A.computeScroll(view);
        }

        @Override // com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension, com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension
        public boolean dispatchTouchEvent(MotionEvent motionEvent, View view) {
            return r.this.A.dispatchTouchEvent(motionEvent, view);
        }

        @Override // com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension, com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension
        public boolean onInterceptTouchEvent(MotionEvent motionEvent, View view) {
            return r.this.A.onInterceptTouchEvent(motionEvent, view);
        }

        @Override // com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension, com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension
        public void onOverScrolled(int i2, int i3, boolean z, boolean z2, View view) {
            r.this.A.onOverScrolled(i2, i3, z, z2, view);
        }

        @Override // com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension, com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension
        public void onScrollChanged(int i2, int i3, int i4, int i5, View view) {
            r.this.A.onScrollChanged(i2, i3, i4, i5, view);
        }

        @Override // com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension, com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension
        public boolean onTouchEvent(MotionEvent motionEvent, View view) {
            return r.this.A.onTouchEvent(motionEvent, view);
        }

        @Override // com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension, com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension
        public boolean overScrollBy(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, View view) {
            return r.this.A.overScrollBy(i2, i3, i4, i5, i6, i7, i8, i9, z, view);
        }
    }

    /* loaded from: classes16.dex */
    public class e implements ValueCallback<String> {
        e(r rVar) {
        }

        @Override // android.webkit.ValueCallback
        /* renamed from: a */
        public void onReceiveValue(String str) {
        }
    }

    /* loaded from: classes16.dex */
    public class f implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ ValueCallback b;

        f(String str, ValueCallback valueCallback) {
            r.this = r1;
            this.a = str;
            this.b = valueCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            r.super.evaluateJavascript(this.a, this.b);
        }
    }

    /* loaded from: classes16.dex */
    public class g implements ValueCallback<String> {
        g(r rVar) {
        }

        @Override // android.webkit.ValueCallback
        /* renamed from: a */
        public void onReceiveValue(String str) {
        }
    }

    public r(Context context) {
        super(context);
        this.f14103k = false;
        this.f14105m = new LinkedList<>();
        this.f14106n = false;
        this.o = false;
        this.y = new a();
        this.z = new b(this);
        this.A = new c();
        this.B = new d();
        if (Build.VERSION.SDK_INT <= 17) {
            getView().setLayerType(1, null);
        }
        this.f14104l = new Handler(Looper.getMainLooper());
        JWebSettings settings = getSettings();
        settings.enableMixedContent();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setUserAgentString(z.a(context, settings.getUserAgentString()));
        getView().setHorizontalScrollBarEnabled(false);
        getView().setVerticalScrollBarEnabled(false);
        setWebViewClient(this.y);
        setWebChromeClient(this.z);
        setWebViewCallbackClient(this.A);
        if (getWebType() != JWebType.WV_TYPE_SYS) {
            setWebViewClientExtension(this.B);
        }
        getView().setFocusable(false);
        getView().setFocusableInTouchMode(false);
        setBackgroundColor(-1);
        com.jingdong.manto.k.a.b().a(this);
    }

    private static void a(JSONObject jSONObject, String str, Object obj) {
        try {
            jSONObject.put(str, obj);
        } catch (Exception e2) {
            MantoLog.e(C, e2.getMessage());
        }
    }

    private String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        Context g2 = com.jingdong.a.g();
        int pixel2dip = MantoDensityUtils.pixel2dip(g2.getResources().getDisplayMetrics().widthPixels);
        int pixel2dip2 = MantoDensityUtils.pixel2dip(g2.getResources().getDisplayMetrics().heightPixels);
        int min = Math.min(pixel2dip, pixel2dip2);
        int max = Math.max(pixel2dip2, pixel2dip);
        int min2 = Math.min(pixel2dip, min);
        int min3 = Math.min(pixel2dip2, max);
        String replace = str.replace("var deviceWidth = window.screen.width || 375;", "var deviceWidth = " + min2 + " || window.screen.width || 375;").replace("newDeviceHeight = window.screen.height || 375", "newDeviceHeight = " + min3 + " || window.screen.height || 375");
        int lastIndexOf = replace.lastIndexOf("checkDeviceWidth()");
        if (lastIndexOf > 0) {
            StringBuffer stringBuffer = new StringBuffer(replace);
            stringBuffer.insert(lastIndexOf, "//");
            return stringBuffer.toString();
        }
        return replace;
    }

    private void c() {
        Iterator<Pair<String, ValueCallback<String>>> it = this.f14105m.iterator();
        while (it.hasNext()) {
            Pair<String, ValueCallback<String>> next = it.next();
            b((String) next.first, (ValueCallback) next.second);
        }
        this.f14105m.clear();
    }

    private String d(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        Context g2 = com.jingdong.a.g();
        int pixel2dip = MantoDensityUtils.pixel2dip(g2.getResources().getDisplayMetrics().widthPixels);
        int min = Math.min(pixel2dip, Math.min(pixel2dip, MantoDensityUtils.pixel2dip(g2.getResources().getDisplayMetrics().heightPixels)));
        return str.replaceAll("=window.screen.width", ContainerUtils.KEY_VALUE_DELIMITER + min).replaceAll("document.documentElement.clientWidth", "" + min);
    }

    private static String e(String str) {
        int i2 = 0;
        String str2 = "";
        while (true) {
            int indexOf = str.indexOf("<script>", i2);
            int indexOf2 = str.indexOf("</script>", i2);
            if (indexOf == -1 || indexOf2 == -1 || indexOf2 <= indexOf) {
                break;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(i2 > 0 ? ";" : "");
            sb.append(str.substring(indexOf + 8, indexOf2));
            str2 = sb.toString();
            i2 = indexOf2 + 9;
        }
        return str2;
    }

    public String getFrameBasePath() {
        if (this.s == null) {
            this.s = "https://service.vapp.jd.com/";
        }
        return this.s;
    }

    public String getFramePath() {
        if (this.r == null) {
            this.r = getFrameBasePath() + "page-frame.html";
        }
        return this.r;
    }

    final void a(int i2) {
        com.jingdong.manto.f fVar = this.p;
        if (fVar != null && this.f14106n) {
            String b2 = i2 == 0 ? com.jingdong.manto.pkg.b.g.b(fVar, "page-frame.html") : (i2 != 1 || TextUtils.isEmpty(this.v)) ? null : com.jingdong.manto.pkg.b.g.a(this.p, this.v, "page-frame.html");
            if (MantoStringUtils.isEmpty(b2)) {
                return;
            }
            super.evaluateJavascript(c(e(b2)), null);
        }
    }

    @Override // com.jingdong.sdk.jweb.JDWebView, com.jingdong.sdk.jweb.JWebView, com.jingdong.manto.jsengine.IMantoWebViewJS
    public final void addJavascriptInterface(Object obj, String str) {
        if (obj == null || MantoStringUtils.isEmpty(str)) {
            return;
        }
        super.addJavascriptInterface(obj, str);
    }

    public void b(int i2) {
        com.jingdong.manto.f fVar = this.p;
        if (fVar != null && fVar.k() != null && this.f14106n && this.p.P()) {
            String str = i2 == 0 ? FontsUtil.KEY_MULTI_LIGHT : CustomThemeConstance.NAVI_IMAGE_DARK_TAG;
            super.evaluateJavascript("document.dispatchEvent(new CustomEvent(\"onThemeChange\", {detail:{theme:\"" + str + "\"}}))", null);
            super.evaluateJavascript("__jdConfig.theme=\"" + str + "\"", null);
        }
    }

    public void b(String str, ValueCallback<String> valueCallback) {
        if (this.o) {
            return;
        }
        f fVar = new f(str, valueCallback);
        if (MantoThreadUtils.isMainThread()) {
            fVar.run();
        } else {
            this.f14104l.post(fVar);
        }
    }

    final void d() {
        if (this.f14106n) {
            JSONObject jSONObject = new JSONObject();
            com.jingdong.manto.f fVar = this.p;
            if (fVar != null) {
                a(jSONObject, "appType", Integer.valueOf(fVar.r.d));
                a(jSONObject, IMantoBaseModule.CARD_MODE, Boolean.valueOf(this.p.u()));
                JSONObject jSONObject2 = this.p.t.f13047e;
                Iterator<String> keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    try {
                        jSONObject.putOpt(next, jSONObject2.opt(next));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } else if (this.u) {
                a(jSONObject, HttpDnsConfig.PREDOWNLOAD_PARAMS, Boolean.TRUE);
            }
            a(jSONObject, "webviewType", "x5");
            a(jSONObject, HybridSDK.APP_VERSION, (Object) 1);
            JSONObject jSONObject3 = new JSONObject();
            a(jSONObject3, "width", Float.valueOf(MantoDensityUtils.getDMWidthPixels() / MantoDensityUtils.getDensity(getContext())));
            a(jSONObject3, "pixelRatio", Float.valueOf(MantoDensityUtils.getDensity(getContext())));
            super.evaluateJavascript(String.format("var __jdConfig = %s;\nvar __deviceInfo__ = %s\n", jSONObject.toString(), jSONObject3.toString()), null);
            e();
        }
    }

    @Override // com.jingdong.sdk.jweb.JDWebView, com.jingdong.sdk.jweb.JWebView, com.jingdong.manto.jsengine.IMantoWebViewJS
    public void destroy() {
        this.o = true;
        try {
            super.destroy();
        } catch (Exception unused) {
        }
        Animator animator = this.w;
        if (animator != null) {
            animator.cancel();
            this.w = null;
        }
        com.jingdong.manto.k.a.b().b(this);
    }

    public void e() {
        super.evaluateJavascript("__jdConfig.theme=\"" + (com.jingdong.manto.k.a.b().a() == 0 ? FontsUtil.KEY_MULTI_LIGHT : CustomThemeConstance.NAVI_IMAGE_DARK_TAG) + "\"", null);
    }

    @Override // com.jingdong.manto.BaseWebView, com.jingdong.sdk.jweb.JDWebView, com.jingdong.sdk.jweb.JWebView
    public final void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        if (this.f14102j) {
            b(str, valueCallback);
        } else {
            this.f14105m.add(new Pair<>(str, valueCallback));
        }
    }

    final void f() {
        if (this.f14101i) {
            return;
        }
        this.f14101i = true;
        String str = com.jingdong.manto.pkg.b.f.c("NABridge.js") + com.jingdong.manto.pkg.b.f.c("NAWebview.js");
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        if (OpenJsApiManager.getPApiMap() != null && OpenJsApiManager.getPApiMap().size() > 0) {
            String c2 = com.jingdong.manto.pkg.b.f.c("NAWebviewExt.js");
            if (!TextUtils.isEmpty(c2)) {
                sb.append(c2);
            }
        }
        String sb2 = sb.toString();
        if (MantoStringUtils.isEmpty(sb2)) {
            return;
        }
        super.evaluateJavascript(d(sb2), null);
    }

    public final void f(String str) {
        if (this.f14102j) {
            return;
        }
        h();
        this.f14102j = true;
        setTitle(String.format("%s%s%s", getFrameBasePath(), this.q, "/" + str));
        String b2 = TextUtils.isEmpty(this.v) ? com.jingdong.manto.pkg.b.g.b(this.p, str) : com.jingdong.manto.pkg.b.g.a(this.p, this.v, str);
        if (MantoStringUtils.isEmpty(b2)) {
            com.jingdong.manto.f fVar = this.p;
            if (fVar != null) {
                fVar.f13014f.getFirstPage().h();
                return;
            }
            return;
        }
        int indexOf = b2.indexOf("<style>");
        int indexOf2 = b2.indexOf("</style>");
        String str2 = "";
        String encodeToString = Base64.encodeToString(((indexOf == -1 || indexOf2 == -1 || indexOf2 <= indexOf) ? "" : b2.substring(indexOf + 7, indexOf2)).getBytes(), 2);
        int indexOf3 = b2.indexOf("<page>");
        int indexOf4 = b2.indexOf("</page>");
        if (indexOf3 != -1 && indexOf4 != -1 && indexOf4 > indexOf3) {
            str2 = b2.substring(indexOf3 + 6, indexOf4);
        }
        super.evaluateJavascript(String.format("var style = document.createElement('style');style.innerHTML = atob(\"%s\");document.head.appendChild(style);", encodeToString) + String.format("var page = document.createElement('page');page.innerHTML = atob(\"%s\");document.body.appendChild(page);", Base64.encodeToString(str2.getBytes(), 2)) + String.format("%s;", e(b2)), null);
        c();
    }

    final void g() {
        a(0);
        a(1);
    }

    @Override // com.jingdong.manto.jsengine.IMantoWebViewJS
    public IMantoBaseInterface getInterface(Class cls) {
        if (cls.isInstance(this)) {
            return this;
        }
        return null;
    }

    @Override // com.jingdong.manto.jsengine.IMantoWebViewJS
    public String getName() {
        return "web";
    }

    public String getSubPackageRoot() {
        return this.v;
    }

    final void h() {
        com.jingdong.manto.f fVar;
        if (this.f14103k || (fVar = this.p) == null || !fVar.s.r) {
            return;
        }
        this.f14103k = true;
        super.evaluateJavascript(com.jingdong.manto.pkg.b.f.c("NAPerf.js"), new e(this));
    }

    public void i() {
        boolean e2 = com.jingdong.manto.s.a.f().e();
        boolean d2 = com.jingdong.manto.s.b.e().d();
        if (e2 || d2) {
            super.evaluateJavascript(com.jingdong.manto.pkg.b.f.c("NARemoteDebug.js"), new g(this));
        }
    }

    public void init() {
        loadUrl(getFramePath());
        setTitle(this.u ? String.format("%s%s", getFrameBasePath(), "preload/page-frame.html") : String.format("%s%s%s", getFrameBasePath(), this.q, "/preload/page-frame.html"));
    }

    public final void j() {
        com.jingdong.manto.f fVar = this.p;
        if (fVar != null && TextUtils.equals(fVar.f13018j, "1") && this.p.u()) {
            return;
        }
        i();
        com.jingdong.manto.f fVar2 = this.p;
        if (fVar2 == null || !fVar2.s.f13094c) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(com.jingdong.manto.pkg.b.f.c("NAVConsole.js"));
        if (TextUtils.isEmpty(sb.toString())) {
            return;
        }
        super.evaluateJavascript(sb.toString(), null);
    }

    @Override // com.jingdong.manto.k.a.b
    public void onDeepModeChanged(int i2) {
        b(i2);
    }

    public void setRuntime(com.jingdong.manto.f fVar) {
        this.p = fVar;
        g();
        d();
    }

    public void setSubPackageRoot(String str) {
        this.v = str;
    }

    @Override // com.jingdong.manto.jsengine.b
    public void setTitle(String str) {
        evaluateJavascript("document.title=\"" + str + "\"", null);
    }

    public void setWebFocus(boolean z) {
        View view = getView();
        if (view != null) {
            view.setFocusable(z);
            view.setFocusableInTouchMode(z);
        }
    }
}
