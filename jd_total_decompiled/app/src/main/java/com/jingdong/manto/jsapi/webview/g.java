package com.jingdong.manto.jsapi.webview;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.webkit.ValueCallback;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import com.jingdong.common.cashiernative.CashierSdkGlobalConfig;
import com.jingdong.manto.BaseWebView;
import com.jingdong.manto.MantoActivityResult;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.R;
import com.jingdong.manto.jsengine.IMantoBaseInterface;
import com.jingdong.manto.pkg.db.entity.DomainBlackListEntity;
import com.jingdong.manto.sdk.api.ILogin;
import com.jingdong.manto.sdk.api.IWebPayIntercept;
import com.jingdong.manto.sdk.api.IWebview;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.manto.utils.n;
import com.jingdong.manto.utils.r;
import com.jingdong.manto.utils.z;
import com.jingdong.manto.widget.MantoProgressBar;
import com.jingdong.sdk.jweb.JDWebView;
import com.jingdong.sdk.jweb.JWebChromeClient;
import com.jingdong.sdk.jweb.JWebViewClient;
import java.io.File;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Map;

/* loaded from: classes15.dex */
public class g extends FrameLayout {
    private MantoProgressBar a;
    private IWebview.IMantoWebViewInterface b;

    /* renamed from: c */
    private Reference<MantoCore> f13211c;
    public int d;

    /* renamed from: e */
    private Context f13212e;

    /* renamed from: f */
    private DomainBlackListEntity f13213f;

    /* renamed from: g */
    private String[] f13214g;

    /* renamed from: h */
    private Reference<InterfaceC0533g> f13215h;

    /* renamed from: i */
    private String f13216i;

    /* renamed from: j */
    private Dialog f13217j;

    /* renamed from: k */
    IWebview.IMantoWebViewCallBack f13218k;

    /* renamed from: l */
    private boolean f13219l;

    /* loaded from: classes15.dex */
    public class a implements IWebview.IMantoWebViewCallBack {
        a() {
            g.this = r1;
        }

        @Override // com.jingdong.manto.sdk.api.IWebview.IMantoWebViewCallBack
        public boolean onLongClick(View view) {
            return g.this.a(view);
        }

        @Override // com.jingdong.manto.sdk.api.IWebview.IMantoWebViewCallBack
        public void onPageFinished(View view, String str) {
            g.this.a(view, str);
        }

        @Override // com.jingdong.manto.sdk.api.IWebview.IMantoWebViewCallBack
        public void onProgressChanged(View view, int i2) {
            g.this.a(view, i2);
        }

        @Override // com.jingdong.manto.sdk.api.IWebview.IMantoWebViewCallBack
        public void onReceivedTitle(View view, String str) {
            g.this.c(str);
        }

        @Override // com.jingdong.manto.sdk.api.IWebview.IMantoWebViewCallBack
        public boolean shouldOverrideUrlLoading(View view, String str) {
            return g.this.b(view, str);
        }
    }

    /* loaded from: classes15.dex */
    public class b implements IWebPayIntercept.IWebPayCallBack {
        b() {
            g.this = r1;
        }

        @Override // com.jingdong.manto.sdk.api.IWebPayIntercept.IWebPayCallBack
        public void onPayBack(String str) {
            if (g.this.f13219l || g.this.b == null) {
                return;
            }
            g.this.b.loadUrl(str);
        }
    }

    /* loaded from: classes15.dex */
    public class c implements DialogInterface.OnClickListener {
        final /* synthetic */ MantoCore a;
        final /* synthetic */ String b;

        c(MantoCore mantoCore, String str) {
            g.this = r1;
            this.a = mantoCore;
            this.b = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            g.this.a(this.a, this.b);
            dialogInterface.dismiss();
        }
    }

    /* loaded from: classes15.dex */
    public class d implements DialogInterface.OnClickListener {
        d(g gVar) {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            dialogInterface.dismiss();
        }
    }

    /* loaded from: classes15.dex */
    public class e implements Runnable {
        final /* synthetic */ MantoCore a;
        final /* synthetic */ String b;

        /* loaded from: classes15.dex */
        class a implements Runnable {
            final /* synthetic */ String a;

            a(String str) {
                e.this = r1;
                this.a = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                g.this.b(this.a);
            }
        }

        e(MantoCore mantoCore, String str) {
            g.this = r1;
            this.a = mantoCore;
            this.b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            String absolutePath = new File(new File(n.f14314c + "mantoImage"), "image_" + System.currentTimeMillis() + ".png").getAbsolutePath();
            try {
                com.jingdong.manto.sdk.b.a(this.a.getBitmap(this.b), 100, Bitmap.CompressFormat.JPEG, absolutePath, true);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            MantoThreadUtils.runOnUIThread(new a(absolutePath));
        }
    }

    /* loaded from: classes15.dex */
    public static class f implements IWebview.IMantoWebViewInterface {
        private BaseWebView a;
        public Reference<MantoCore> b;

        /* loaded from: classes15.dex */
        class a extends JWebViewClient {
            final /* synthetic */ IWebview.IMantoWebViewCallBack a;

            a(f fVar, IWebview.IMantoWebViewCallBack iMantoWebViewCallBack) {
                this.a = iMantoWebViewCallBack;
            }

            @Override // com.jingdong.sdk.jweb.JWebViewClient
            public void onPageFinished(JDWebView jDWebView, String str) {
                this.a.onPageFinished(jDWebView, str);
            }

            @Override // com.jingdong.sdk.jweb.JWebViewClient
            public boolean shouldOverrideUrlLoading(JDWebView jDWebView, String str) {
                return this.a.shouldOverrideUrlLoading(jDWebView, str);
            }
        }

        /* loaded from: classes15.dex */
        class b extends BaseWebChromeClient {
            final /* synthetic */ IWebview.IMantoWebViewCallBack a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            b(f fVar, MantoActivityResult mantoActivityResult, IWebview.IMantoWebViewCallBack iMantoWebViewCallBack) {
                super(mantoActivityResult);
                this.a = iMantoWebViewCallBack;
            }

            @Override // com.jingdong.sdk.jweb.JWebChromeClient
            public void onProgressChanged(JDWebView jDWebView, int i2) {
                this.a.onProgressChanged(jDWebView, i2);
                super.onProgressChanged(jDWebView, i2);
            }

            @Override // com.jingdong.sdk.jweb.JWebChromeClient
            public void onReceivedTitle(JDWebView jDWebView, String str) {
                this.a.onReceivedTitle(jDWebView, str);
            }
        }

        /* loaded from: classes15.dex */
        class c extends BaseWebChromeClient {
            final /* synthetic */ IWebview.IMantoWebViewCallBack a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            c(f fVar, MantoActivityResult mantoActivityResult, IWebview.IMantoWebViewCallBack iMantoWebViewCallBack) {
                super(mantoActivityResult);
                this.a = iMantoWebViewCallBack;
            }

            @Override // com.jingdong.sdk.jweb.JWebChromeClient
            public void onProgressChanged(JDWebView jDWebView, int i2) {
                this.a.onProgressChanged(jDWebView, i2);
                super.onProgressChanged(jDWebView, i2);
            }

            @Override // com.jingdong.sdk.jweb.JWebChromeClient
            public void onReceivedTitle(JDWebView jDWebView, String str) {
                this.a.onReceivedTitle(jDWebView, str);
            }
        }

        /* loaded from: classes15.dex */
        class d implements View.OnLongClickListener {
            final /* synthetic */ IWebview.IMantoWebViewCallBack a;

            d(f fVar, IWebview.IMantoWebViewCallBack iMantoWebViewCallBack) {
                this.a = iMantoWebViewCallBack;
            }

            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                return this.a.onLongClick(view);
            }
        }

        public f(Reference<MantoCore> reference) {
            MantoCore mantoCore;
            Context activity = (reference == null || (mantoCore = reference.get()) == null) ? null : mantoCore.getActivity();
            this.a = new BaseWebView(activity == null ? com.jingdong.a.g() : activity);
            this.b = reference;
        }

        @Override // com.jingdong.manto.jsengine.IMantoWebViewJS
        public void addJavascriptInterface(Object obj, String str) {
            this.a.addJavascriptInterface(obj, str);
        }

        @Override // com.jingdong.manto.sdk.api.IWebview.IMantoWebViewInterface
        public boolean canGoBack() {
            return this.a.canGoBack();
        }

        @Override // com.jingdong.manto.jsengine.IMantoWebViewJS
        public void destroy() {
            this.a.destroy();
        }

        @Override // com.jingdong.manto.jsengine.IMantoWebViewJS
        public void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
            this.a.evaluateJavascript(str, valueCallback);
        }

        @Override // com.jingdong.manto.sdk.api.IWebview.IMantoWebViewInterface
        public JDWebView.HitTestResult getHitTestResult() {
            return this.a.getHitTestResult();
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
            return "webviewContainer";
        }

        @Override // com.jingdong.manto.sdk.api.IWebview.IMantoWebViewInterface
        public String getTitle() {
            return this.a.getTitle();
        }

        @Override // com.jingdong.manto.sdk.api.IWebview.IMantoWebViewInterface
        public String getUrl() {
            return this.a.getUrl();
        }

        @Override // com.jingdong.manto.sdk.api.IWebview.IMantoWebViewInterface
        public View getView() {
            return this.a;
        }

        @Override // com.jingdong.manto.sdk.api.IWebview.IMantoWebViewInterface
        public JWebChromeClient getWebChromeClient() {
            return this.a.getWebChromeClient();
        }

        @Override // com.jingdong.manto.sdk.api.IWebview.IMantoWebViewInterface
        public void goBack() {
            this.a.goBack();
        }

        @Override // com.jingdong.manto.sdk.api.IWebview.IMantoWebViewInterface
        public void init(IWebview.IMantoWebViewCallBack iMantoWebViewCallBack, String str, boolean z) {
            String str2;
            Context g2 = com.jingdong.a.g();
            BaseWebView baseWebView = this.a;
            baseWebView.getSettings().setDomStorageEnabled(true);
            baseWebView.getSettings().setJavaScriptEnabled(true);
            IWebview iWebview = (IWebview) com.jingdong.a.n(IWebview.class);
            String hostUA = iWebview != null ? iWebview.getHostUA() : "";
            baseWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);
            if (TextUtils.isEmpty(hostUA)) {
                str2 = baseWebView.getSettings().getUserAgentString() + str;
            } else {
                str2 = hostUA + ";" + baseWebView.getSettings().getUserAgentString() + str;
            }
            MantoLog.d("webview", "finalUA: " + str2);
            if (z) {
                str2 = str2.replaceAll(CashierSdkGlobalConfig.CASHIER_SDK_SOURCE, "").replaceAll("Jdapp", "");
                MantoLog.w("webview", "finalUA after removing jdapp: " + str2);
            }
            baseWebView.getSettings().setUserAgentString(str2);
            baseWebView.getView().setHorizontalScrollBarEnabled(false);
            baseWebView.getView().setVerticalScrollBarEnabled(false);
            baseWebView.getSettings().setBuiltInZoomControls(true);
            baseWebView.getSettings().setUseWideViewPort(true);
            baseWebView.getSettings().setLoadWithOverviewMode(true);
            baseWebView.getSettings().setGeolocationEnabled(true);
            baseWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            baseWebView.getSettings().setAppCacheMaxSize(10485760L);
            baseWebView.getSettings().setAppCachePath(g2.getDir("webviewcache", 0).getAbsolutePath());
            baseWebView.getSettings().setDatabaseEnabled(true);
            baseWebView.getSettings().setDatabasePath(n.b + "databases/");
            baseWebView.setOnTouchListener();
            baseWebView.getSettings().setAllowFileAccess(false);
            baseWebView.getSettings().setAppCacheMaxSize(1L);
            baseWebView.getSettings().enableMixedContent();
            baseWebView.setWebViewClient(new a(this, iMantoWebViewCallBack));
            Reference<MantoCore> reference = this.b;
            MantoCore mantoCore = reference != null ? reference.get() : null;
            if (mantoCore != null) {
                baseWebView.setWebChromeClient(new b(this, mantoCore.getActivityResultImpl(), iMantoWebViewCallBack));
            } else {
                baseWebView.setWebChromeClient(new c(this, null, iMantoWebViewCallBack));
            }
            baseWebView.setOnLongClickListener(new d(this, iMantoWebViewCallBack));
        }

        @Override // com.jingdong.manto.sdk.api.IWebview.IMantoWebViewInterface
        public boolean isSysWebView() {
            BaseWebView baseWebView = this.a;
            return baseWebView == null || baseWebView.getX5WebViewExtension() == null;
        }

        @Override // com.jingdong.manto.sdk.api.IWebview.IMantoWebViewInterface
        public void loadUrl(String str) {
            this.a.loadUrl(str);
        }
    }

    /* renamed from: com.jingdong.manto.jsapi.webview.g$g */
    /* loaded from: classes15.dex */
    public interface InterfaceC0533g {
        void a();

        void b();
    }

    public g(@NonNull Activity activity, DomainBlackListEntity domainBlackListEntity, String[] strArr, String str, boolean z) {
        super(com.jingdong.a.g());
        this.f13218k = new a();
        this.f13219l = false;
        this.f13212e = com.jingdong.a.g();
        this.f13211c = new SoftReference(null);
        this.f13213f = domainBlackListEntity;
        this.f13214g = strArr;
        this.f13216i = str;
        IWebview iWebview = (IWebview) com.jingdong.a.n(IWebview.class);
        if (iWebview != null) {
            this.b = iWebview.getWebview(this.f13211c);
        }
        if (this.b == null) {
            this.b = new f(this.f13211c);
        }
        this.b.init(this.f13218k, z.a(this.f13212e, ";jdmp;"), z);
        g();
        addView(this.b.getView(), new FrameLayout.LayoutParams(-1, -1));
        MantoProgressBar mantoProgressBar = new MantoProgressBar(this.f13212e);
        this.a = mantoProgressBar;
        mantoProgressBar.setProgressDrawable(getResources().getDrawable(R.drawable.manto_progress_bar));
        addView(this.a, new FrameLayout.LayoutParams(-1, MantoDensityUtils.dip2pixel(this.f13212e, 3)));
        ILogin iLogin = (ILogin) com.jingdong.a.n(ILogin.class);
        if (iLogin != null) {
            iLogin.asyncWebCookies();
        }
    }

    public g(@NonNull MantoCore mantoCore, DomainBlackListEntity domainBlackListEntity, String[] strArr, String str, boolean z) {
        super(com.jingdong.a.g());
        this.f13218k = new a();
        this.f13219l = false;
        this.f13212e = com.jingdong.a.g();
        this.f13211c = new SoftReference(mantoCore);
        this.f13213f = domainBlackListEntity;
        this.f13214g = strArr;
        this.f13216i = str;
        IWebview iWebview = (IWebview) com.jingdong.a.n(IWebview.class);
        if (iWebview != null) {
            this.b = iWebview.getWebview(this.f13211c);
        }
        if (this.b == null) {
            this.b = new f(this.f13211c);
        }
        this.b.init(this.f13218k, z.a(this.f13212e, ";jdmp;"), z);
        a((com.jingdong.manto.e) mantoCore, "JDJSCore");
        g();
        addView(this.b.getView(), new FrameLayout.LayoutParams(-1, -1));
        MantoProgressBar mantoProgressBar = new MantoProgressBar(this.f13212e);
        this.a = mantoProgressBar;
        mantoProgressBar.setProgressDrawable(getResources().getDrawable(R.drawable.manto_progress_bar));
        addView(this.a, new FrameLayout.LayoutParams(-1, MantoDensityUtils.dip2pixel(this.f13212e, 3)));
        ILogin iLogin = (ILogin) com.jingdong.a.n(ILogin.class);
        if (iLogin != null) {
            iLogin.asyncWebCookies();
        }
    }

    public static g a(MantoCore mantoCore) {
        if (mantoCore != null) {
            return ((com.jingdong.manto.e) mantoCore).getMantoWebViewContainer();
        }
        return null;
    }

    public void a(MantoCore mantoCore, String str) {
        if (mantoCore == null) {
            return;
        }
        com.jingdong.manto.b.d().networkIO().execute(new e(mantoCore, str));
    }

    private void a(String str) {
        Activity activity;
        try {
            if (Build.VERSION.SDK_INT < 29) {
                return;
            }
            Reference<MantoCore> reference = this.f13211c;
            MantoCore mantoCore = null;
            if (reference != null) {
                MantoCore mantoCore2 = reference.get();
                mantoCore = mantoCore2;
                activity = mantoCore2 != null ? mantoCore2.getActivity() : null;
            } else {
                activity = null;
            }
            if (activity == null) {
                return;
            }
            Dialog dialog = this.f13217j;
            if (dialog != null && dialog.isShowing()) {
                this.f13217j.dismiss();
                return;
            }
            Dialog a2 = com.jingdong.manto.widget.dialog.a.a(activity, null, activity.getResources().getString(R.string.manto_ask_save_pic), activity.getResources().getString(R.string.manto_confirm), activity.getResources().getString(R.string.manto_cancel), new c(mantoCore, str), new d(this), null, null, null);
            this.f13217j = a2;
            a2.show();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void b(String str) {
        if (new File(str).exists()) {
            String e2 = z.e("png");
            if (r.a(str, e2, true)) {
                z.a(e2, true);
            }
        }
    }

    private void c() {
        String str = "javascript:" + com.jingdong.manto.pkg.b.f.c("JSBridge.js");
        if (MantoStringUtils.isEmpty(str)) {
            MantoLog.e("mantoWebviewContainer", "execInitScript, js null");
        } else {
            this.b.evaluateJavascript(str, null);
        }
    }

    private void d() {
        String a2 = com.jingdong.manto.jsapi.webview.b.a();
        if (this.f13219l || this.b == null) {
            return;
        }
        MantoLog.e("webview", "blackDomain interrupt, jump to url:" + a2);
        this.b.loadUrl(a2);
    }

    private void e() {
        this.b.evaluateJavascript("window.__jdjs_environment = \"miniprogram\";", null);
    }

    public void a() {
        IWebview.IMantoWebViewInterface iMantoWebViewInterface = this.b;
        if (iMantoWebViewInterface != null) {
            iMantoWebViewInterface.destroy();
        }
    }

    public void a(View view, int i2) {
        if (i2 == 100) {
            this.a.b();
        } else {
            f();
        }
    }

    public void a(View view, String str) {
        if (this.f13219l) {
            MantoLog.d("betterWb", "page is finished, not inject.");
            return;
        }
        e();
        c();
        setTitle("" + this.b.getTitle());
    }

    void a(com.jingdong.manto.e eVar, String str) {
        this.d = eVar.registMantoWebviewInterface(this.b, str);
    }

    public boolean a(View view) {
        IWebview.IMantoWebViewInterface iMantoWebViewInterface = this.b;
        if (iMantoWebViewInterface != null) {
            JDWebView.HitTestResult hitTestResult = iMantoWebViewInterface.getHitTestResult();
            int i2 = hitTestResult.mType;
            String str = hitTestResult.mExtra;
            if (i2 == 5 || i2 == 8) {
                a(str);
                return false;
            }
            return false;
        }
        return false;
    }

    public final boolean b() {
        IWebview.IMantoWebViewInterface iMantoWebViewInterface = this.b;
        if (iMantoWebViewInterface != null && (iMantoWebViewInterface.getWebChromeClient() instanceof BaseWebChromeClient) && ((BaseWebChromeClient) this.b.getWebChromeClient()).isFullScreen()) {
            ((BaseWebChromeClient) this.b.getWebChromeClient()).hide();
            return true;
        }
        IWebview.IMantoWebViewInterface iMantoWebViewInterface2 = this.b;
        if (iMantoWebViewInterface2 == null || !iMantoWebViewInterface2.canGoBack()) {
            return false;
        }
        this.b.goBack();
        return true;
    }

    public boolean b(View view, String str) {
        InterfaceC0533g interfaceC0533g;
        MantoLog.d("webview", "shouldOverrideUrlLoading: url:" + str);
        if (str == null) {
            return false;
        }
        if (!TextUtils.isEmpty(this.f13216i) && str.startsWith(this.f13216i)) {
            Reference<InterfaceC0533g> reference = this.f13215h;
            interfaceC0533g = reference != null ? reference.get() : null;
            if (interfaceC0533g != null) {
                MantoLog.d("webview", "shouldOverrideUrlLoading: notify onRedirectUrlInterrupted()");
                interfaceC0533g.b();
            }
            return true;
        } else if (str.startsWith("http:") || str.startsWith("https:")) {
            if (com.jingdong.manto.jsapi.webview.b.a(this.f13213f, this.f13214g, str)) {
                MantoLog.e("webview", "blackDomain interrupt, original url: " + str);
                d();
                return true;
            }
            return false;
        } else {
            if (str.startsWith("weixin://wap/pay")) {
                Reference<InterfaceC0533g> reference2 = this.f13215h;
                interfaceC0533g = reference2 != null ? reference2.get() : null;
                if (interfaceC0533g != null) {
                    MantoLog.d("webview", "shouldOverrideUrlLoading: notify onWxClientInterrupted()");
                    interfaceC0533g.a();
                }
            }
            IWebPayIntercept iWebPayIntercept = (IWebPayIntercept) com.jingdong.a.n(IWebPayIntercept.class);
            if (iWebPayIntercept != null && str.startsWith("weixin://jdapp/pay")) {
                iWebPayIntercept.onNativePay(getContext(), str, new b());
                return true;
            }
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                intent.addFlags(268435456);
                com.jingdong.a.g().startActivity(intent);
                return true;
            } catch (Exception unused) {
                return false;
            }
        }
    }

    public void c(String str) {
        setTitle("" + str);
    }

    public void f() {
        this.a.c();
    }

    void g() {
        Map<String, Object> hostJsInterfaces;
        IWebview iWebview = (IWebview) com.jingdong.a.n(IWebview.class);
        if (iWebview == null || (hostJsInterfaces = iWebview.getHostJsInterfaces()) == null) {
            return;
        }
        for (String str : hostJsInterfaces.keySet()) {
            this.b.addJavascriptInterface(hostJsInterfaces.get(str), str);
        }
    }

    public IWebview.IMantoWebViewInterface getWebView() {
        return this.b;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f13219l = true;
        this.b = null;
        this.f13218k = null;
        this.f13212e = null;
        this.f13217j = null;
    }

    public void setH5PayInterruptCallbackReference(InterfaceC0533g interfaceC0533g) {
        this.f13215h = new SoftReference(interfaceC0533g);
    }

    public void setTitle(String str) {
        Reference<MantoCore> reference = this.f13211c;
        if (reference == null || reference.get() == null) {
            return;
        }
        ((com.jingdong.manto.e) this.f13211c.get()).setTitle(str, this.d);
    }
}
