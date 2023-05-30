package com.jingdong.manto.jsapi.webview;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.R;
import com.jingdong.manto.jsapi.base.MantoCallback;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.pkg.db.entity.DomainBlackListEntity;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.sdk.api.ILogin;
import com.jingdong.manto.sdk.api.IWebview;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoThreadUtils;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class d extends AbstractMantoViewManager {
    private g a;
    private DomainBlackListEntity b;

    /* renamed from: c  reason: collision with root package name */
    private String[] f13207c;
    private boolean d = false;

    /* renamed from: e  reason: collision with root package name */
    MantoLifecycleLisener f13208e = new b();

    /* renamed from: f  reason: collision with root package name */
    private com.jingdong.manto.jsapi.webview.a f13209f;

    /* loaded from: classes15.dex */
    class a implements ILogin.WebCookieCallBack {
        final /* synthetic */ IWebview.IMantoWebViewInterface a;
        final /* synthetic */ String b;

        /* renamed from: com.jingdong.manto.jsapi.webview.d$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class RunnableC0532a implements Runnable {
            final /* synthetic */ String a;

            RunnableC0532a(String str) {
                this.a = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                IWebview.IMantoWebViewInterface iMantoWebViewInterface;
                String str;
                if (a.this.a == null) {
                    return;
                }
                if (this.a.indexOf("#") >= 0 && !TextUtils.isEmpty(a.this.a.getUrl())) {
                    a.this.a.evaluateJavascript(String.format("window.location=\"%s\"", this.a), null);
                    return;
                }
                if (com.jingdong.manto.jsapi.webview.b.a(d.this.b, d.this.f13207c, a.this.b)) {
                    iMantoWebViewInterface = a.this.a;
                    str = com.jingdong.manto.jsapi.webview.b.a();
                } else {
                    iMantoWebViewInterface = a.this.a;
                    str = this.a;
                }
                iMantoWebViewInterface.loadUrl(str);
            }
        }

        /* loaded from: classes15.dex */
        class b implements Runnable {
            b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                a aVar = a.this;
                if (aVar.a == null) {
                    return;
                }
                if (aVar.b.indexOf("#") >= 0 && !TextUtils.isEmpty(a.this.a.getUrl())) {
                    a aVar2 = a.this;
                    aVar2.a.evaluateJavascript(String.format("window.location=\"%s\"", aVar2.b), null);
                } else if (com.jingdong.manto.jsapi.webview.b.a(d.this.b, d.this.f13207c, a.this.b)) {
                    a.this.a.loadUrl(com.jingdong.manto.jsapi.webview.b.a());
                } else {
                    a aVar3 = a.this;
                    aVar3.a.loadUrl(aVar3.b);
                }
            }
        }

        a(IWebview.IMantoWebViewInterface iMantoWebViewInterface, String str) {
            this.a = iMantoWebViewInterface;
            this.b = str;
        }

        @Override // com.jingdong.manto.sdk.api.ILogin.WebCookieCallBack
        public void onFailure() {
            MantoThreadUtils.runOnUIThread(new b());
        }

        @Override // com.jingdong.manto.sdk.api.ILogin.WebCookieCallBack
        public void onSuccess(String str) {
            MantoThreadUtils.runOnUIThread(new RunnableC0532a(str));
        }
    }

    /* loaded from: classes15.dex */
    class b implements MantoLifecycleLisener {
        b() {
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onBackground() {
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onDestroy() {
            if (d.this.f13209f != null) {
                d.this.f13209f.b();
            }
            d.this.f13209f = null;
            if (d.this.a == null) {
                return;
            }
            d.this.a.a();
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onForeground() {
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onPause() {
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onReady() {
            Activity h2 = com.jingdong.manto.b.h();
            if (h2 == null || d.this.a == null || d.this.a.getWebView() == null || !d.this.a.getWebView().isSysWebView()) {
                return;
            }
            MantoLog.d("webview", "sys webview");
            d dVar = d.this;
            dVar.f13209f = new com.jingdong.manto.jsapi.webview.a(h2, dVar.a.getWebView(), d.this.d);
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public boolean onRemove() {
            if (d.this.a != null) {
                return d.this.a.b();
            }
            return false;
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final MantoLifecycleLisener addLifecycleLisener(Bundle bundle) {
        return this.f13208e;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public View getCustomView(MantoCore mantoCore) {
        return null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public View getCustomView(MantoCore mantoCore, Bundle bundle) {
        if (g.a((com.jingdong.manto.e) mantoCore) != null) {
            return null;
        }
        this.b = com.jingdong.manto.b.k().c();
        PkgDetailEntity k2 = com.jingdong.a.k(bundle.getString("appid"), bundle.getString("type"));
        String str = k2 != null ? k2.domains : "";
        MantoLog.d("HTMLWebView", "domainBlackListEntity:" + this.b + ", miniapp white domains:" + str);
        if (!TextUtils.isEmpty(str)) {
            this.f13207c = str.split("@,@");
        }
        this.d = bundle.getBoolean("full_screen", false);
        g gVar = new g(mantoCore, this.b, this.f13207c, "", false);
        this.a = gVar;
        gVar.setId(R.id.manto_pageview_html_webview);
        return this.a;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "HTMLWebView";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public String getViewName() {
        return "HTMLWebView";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public Bundle handleInsertData(MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putInt(AbstractMantoViewManager.VIEW_ID_KEY, jSONObject.optInt("htmlId"));
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public void handleMethod(String str, View view, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public Bundle handleRemoveData(MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putInt(AbstractMantoViewManager.VIEW_ID_KEY, jSONObject.optInt("htmlId"));
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public Bundle handleUpdateData(MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putInt(AbstractMantoViewManager.VIEW_ID_KEY, jSONObject.optInt("htmlId"));
        bundle.putString("src", jSONObject.optString("src"));
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        return null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    protected void injectJsApiMethod(List<JsApiMethod> list) {
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public boolean onViewInsert(Bundle bundle, View view, MantoCore mantoCore) {
        return false;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public boolean onViewRemove(Bundle bundle, View view, MantoCore mantoCore) {
        return false;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public boolean onViewUpdate(MantoCore mantoCore, View view, Bundle bundle) {
        if (view instanceof g) {
            String string = bundle.getString("src", "about:blank");
            IWebview.IMantoWebViewInterface webView = ((g) view).getWebView();
            if (webView == null) {
                return false;
            }
            ILogin iLogin = (ILogin) com.jingdong.a.n(ILogin.class);
            if (iLogin != null && iLogin.needSyncWebCookies()) {
                iLogin.syncWebCookies(string, new a(webView, string));
            } else if (webView == null) {
                return false;
            } else {
                if (string.indexOf("#") < 0 || TextUtils.isEmpty(webView.getUrl())) {
                    if (com.jingdong.manto.jsapi.webview.b.a(this.b, this.f13207c, string)) {
                        string = com.jingdong.manto.jsapi.webview.b.a();
                    }
                    webView.loadUrl(string);
                } else {
                    webView.evaluateJavascript(String.format("window.location=\"%s\"", string), null);
                }
            }
            return true;
        }
        return false;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public boolean onViewUpdate(MantoCore mantoCore, View view, Bundle bundle, MantoCallback mantoCallback) {
        return false;
    }
}
