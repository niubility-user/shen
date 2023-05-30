package com.tencent.smtt.sdk;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.net.http.SslCertificate;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import cn.com.union.fido.common.MIMEType;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.dynamic.DYConstants;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebSettingsExtension;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.tencent.smtt.export.external.extension.proxy.X5ProxyWebViewClientExtension;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.sdk.stat.MttLoader;
import com.tencent.smtt.utils.TbsLog;
import java.io.BufferedWriter;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class WebView extends FrameLayout implements View.OnLongClickListener {
    public static int NIGHT_MODE_ALPHA = 153;
    public static final int NIGHT_MODE_COLOR = -16777216;
    public static final int NORMAL_MODE_ALPHA = 255;
    public static final String SCHEME_GEO = "geo:0,0?q=";
    public static final String SCHEME_MAILTO = "mailto:";
    public static final String SCHEME_TEL = "tel:";

    /* renamed from: i */
    private static Context f17795i = null;

    /* renamed from: l */
    private static Method f17796l = null;
    public static boolean mSysWebviewCreated = false;
    public static boolean mWebViewCreated = false;
    private static String o = null;
    private static Paint u = null;
    private static boolean v = true;
    volatile int a;
    private final String b;

    /* renamed from: c */
    private boolean f17797c;
    private IX5WebViewBase d;

    /* renamed from: e */
    private a f17798e;

    /* renamed from: f */
    private WebSettings f17799f;

    /* renamed from: g */
    private Context f17800g;

    /* renamed from: h */
    private TbsWebViewPerformanceRecorder f17801h;

    /* renamed from: j */
    private volatile boolean f17802j;

    /* renamed from: k */
    private boolean f17803k;

    /* renamed from: m */
    private WebViewClient f17804m;
    public WebViewCallbackClient mWebViewCallbackClient;

    /* renamed from: n */
    private WebChromeClient f17805n;
    private final int p;
    private final int q;
    private final int r;
    private final String s;
    private final String t;
    private Object w;
    private View.OnLongClickListener x;

    /* loaded from: classes9.dex */
    public static class HitTestResult {
        @Deprecated
        public static final int ANCHOR_TYPE = 1;
        public static final int EDIT_TEXT_TYPE = 9;
        public static final int EMAIL_TYPE = 4;
        public static final int GEO_TYPE = 3;
        @Deprecated
        public static final int IMAGE_ANCHOR_TYPE = 6;
        public static final int IMAGE_TYPE = 5;
        public static final int PHONE_TYPE = 2;
        public static final int SRC_ANCHOR_TYPE = 7;
        public static final int SRC_IMAGE_ANCHOR_TYPE = 8;
        public static final int UNKNOWN_TYPE = 0;
        private IX5WebViewBase.HitTestResult a;
        private WebView.HitTestResult b;

        public HitTestResult() {
            this.b = null;
            this.a = null;
            this.b = null;
        }

        public HitTestResult(WebView.HitTestResult hitTestResult) {
            this.b = null;
            this.a = null;
            this.b = hitTestResult;
        }

        public HitTestResult(IX5WebViewBase.HitTestResult hitTestResult) {
            this.b = null;
            this.a = hitTestResult;
            this.b = null;
        }

        public String getExtra() {
            IX5WebViewBase.HitTestResult hitTestResult = this.a;
            if (hitTestResult != null) {
                return hitTestResult.getExtra();
            }
            WebView.HitTestResult hitTestResult2 = this.b;
            return hitTestResult2 != null ? hitTestResult2.getExtra() : "";
        }

        public int getType() {
            IX5WebViewBase.HitTestResult hitTestResult = this.a;
            if (hitTestResult != null) {
                return hitTestResult.getType();
            }
            WebView.HitTestResult hitTestResult2 = this.b;
            if (hitTestResult2 != null) {
                return hitTestResult2.getType();
            }
            return 0;
        }
    }

    @Deprecated
    /* loaded from: classes9.dex */
    public interface PictureListener {
        @Deprecated
        void onNewPicture(WebView webView, Picture picture);
    }

    /* loaded from: classes9.dex */
    public class WebViewTransport {
        private WebView b;

        public WebViewTransport() {
            WebView.this = r1;
        }

        public synchronized WebView getWebView() {
            return this.b;
        }

        public synchronized void setWebView(WebView webView) {
            this.b = webView;
        }
    }

    /* loaded from: classes9.dex */
    public class a extends android.webkit.WebView {
        public a(WebView webView, Context context) {
            this(context, null);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Context context, AttributeSet attributeSet) {
            super(r3.c(context), attributeSet);
            WebView.this = r3;
            WebView.mSysWebviewCreated = true;
            if (QbSdk.getIsSysWebViewForcedByOuter() && TbsShareManager.isThirdPartyApp(context)) {
                return;
            }
            CookieSyncManager.createInstance(r3.f17800g).startSync();
            try {
                Method declaredMethod = Class.forName("android.webkit.WebViewWorker").getDeclaredMethod("getHandler", new Class[0]);
                declaredMethod.setAccessible(true);
                ((Handler) declaredMethod.invoke(null, new Object[0])).getLooper().getThread().setUncaughtExceptionHandler(new g());
            } catch (Exception unused) {
            }
        }

        public void a() {
            super.computeScroll();
        }

        public void a(int i2, int i3, int i4, int i5) {
            super.onScrollChanged(i2, i3, i4, i5);
        }

        @TargetApi(9)
        public void a(int i2, int i3, boolean z, boolean z2) {
            if (Build.VERSION.SDK_INT >= 9) {
                super.onOverScrolled(i2, i3, z, z2);
            }
        }

        @TargetApi(9)
        public boolean a(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z) {
            if (Build.VERSION.SDK_INT >= 9) {
                return super.overScrollBy(i2, i3, i4, i5, i6, i7, i8, i9, z);
            }
            return false;
        }

        public boolean a(MotionEvent motionEvent) {
            return super.onTouchEvent(motionEvent);
        }

        public boolean b(MotionEvent motionEvent) {
            return super.dispatchTouchEvent(motionEvent);
        }

        public boolean c(MotionEvent motionEvent) {
            return super.onInterceptTouchEvent(motionEvent);
        }

        @Override // android.webkit.WebView, android.view.View
        public void computeScroll() {
            WebViewCallbackClient webViewCallbackClient = WebView.this.mWebViewCallbackClient;
            if (webViewCallbackClient != null) {
                webViewCallbackClient.computeScroll(this);
            } else {
                super.computeScroll();
            }
        }

        @Override // android.webkit.WebView, android.view.ViewGroup, android.view.View
        protected void dispatchDraw(Canvas canvas) {
            try {
                super.dispatchDraw(canvas);
                if (WebView.v || WebView.u == null) {
                    return;
                }
                canvas.save();
                canvas.drawPaint(WebView.u);
                canvas.restore();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            WebViewCallbackClient webViewCallbackClient = WebView.this.mWebViewCallbackClient;
            return webViewCallbackClient != null ? webViewCallbackClient.dispatchTouchEvent(motionEvent, this) : super.dispatchTouchEvent(motionEvent);
        }

        @Override // android.webkit.WebView
        public android.webkit.WebSettings getSettings() {
            try {
                return super.getSettings();
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            WebViewCallbackClient webViewCallbackClient = WebView.this.mWebViewCallbackClient;
            if (webViewCallbackClient != null) {
                webViewCallbackClient.invalidate();
            }
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            WebViewCallbackClient webViewCallbackClient = WebView.this.mWebViewCallbackClient;
            return webViewCallbackClient != null ? webViewCallbackClient.onInterceptTouchEvent(motionEvent, this) : super.onInterceptTouchEvent(motionEvent);
        }

        @Override // android.webkit.WebView, android.view.View
        @TargetApi(9)
        public void onOverScrolled(int i2, int i3, boolean z, boolean z2) {
            WebViewCallbackClient webViewCallbackClient = WebView.this.mWebViewCallbackClient;
            if (webViewCallbackClient != null) {
                webViewCallbackClient.onOverScrolled(i2, i3, z, z2, this);
            } else if (Build.VERSION.SDK_INT >= 9) {
                super.onOverScrolled(i2, i3, z, z2);
            }
        }

        @Override // android.webkit.WebView, android.view.View
        protected void onScrollChanged(int i2, int i3, int i4, int i5) {
            WebViewCallbackClient webViewCallbackClient = WebView.this.mWebViewCallbackClient;
            if (webViewCallbackClient != null) {
                webViewCallbackClient.onScrollChanged(i2, i3, i4, i5, this);
                return;
            }
            super.onScrollChanged(i2, i3, i4, i5);
            WebView.this.onScrollChanged(i2, i3, i4, i5);
        }

        @Override // android.webkit.WebView, android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (!hasFocus()) {
                requestFocus();
            }
            WebViewCallbackClient webViewCallbackClient = WebView.this.mWebViewCallbackClient;
            if (webViewCallbackClient != null) {
                return webViewCallbackClient.onTouchEvent(motionEvent, this);
            }
            try {
                return super.onTouchEvent(motionEvent);
            } catch (Throwable th) {
                th.printStackTrace();
                return false;
            }
        }

        @Override // android.view.View
        @TargetApi(9)
        public boolean overScrollBy(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z) {
            WebViewCallbackClient webViewCallbackClient = WebView.this.mWebViewCallbackClient;
            if (webViewCallbackClient != null) {
                return webViewCallbackClient.overScrollBy(i2, i3, i4, i5, i6, i7, i8, i9, z, this);
            }
            if (Build.VERSION.SDK_INT >= 9) {
                return super.overScrollBy(i2, i3, i4, i5, i6, i7, i8, i9, z);
            }
            return false;
        }

        @Override // android.webkit.WebView, android.view.View
        public void setOverScrollMode(int i2) {
            try {
                super.setOverScrollMode(i2);
            } catch (Exception unused) {
            }
        }
    }

    public WebView(Context context) {
        this(context, (AttributeSet) null);
    }

    public WebView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WebView(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, false);
    }

    @TargetApi(11)
    public WebView(Context context, AttributeSet attributeSet, int i2, Map<String, Object> map, boolean z) {
        super(context, attributeSet, i2);
        this.b = "WebView";
        this.f17797c = false;
        this.f17799f = null;
        this.f17800g = null;
        this.f17801h = new TbsWebViewPerformanceRecorder();
        this.a = 0;
        this.f17802j = false;
        this.f17803k = false;
        this.f17804m = null;
        this.f17805n = null;
        this.p = 1;
        this.q = 2;
        this.r = 3;
        this.s = "javascript:document.getElementsByTagName('HEAD').item(0).removeChild(document.getElementById('QQBrowserSDKNightMode'));";
        this.t = "javascript:var style = document.createElement('style');style.type='text/css';style.id='QQBrowserSDKNightMode';style.innerHTML='html,body{background:none !important;background-color: #1d1e2a !important;}html *{background-color: #1d1e2a !important; color:#888888 !important;border-color:#3e4f61 !important;text-shadow:none !important;box-shadow:none !important;}a,a *{border-color:#4c5b99 !important; color:#2d69b3 !important;text-decoration:none !important;}a:visited,a:visited *{color:#a600a6 !important;}a:active,a:active *{color:#5588AA !important;}input,select,textarea,option,button{background-image:none !important;color:#AAAAAA !important;border-color:#4c5b99 !important;}form,div,button,span{background-color:#1d1e2a !important; border-color:#4c5b99 !important;}img{opacity:0.5}';document.getElementsByTagName('HEAD').item(0).appendChild(style);";
        this.w = null;
        this.x = null;
        long currentTimeMillis = System.currentTimeMillis();
        mWebViewCreated = true;
        if (QbSdk.getIsSysWebViewForcedByOuter() && TbsShareManager.isThirdPartyApp(context)) {
            this.f17800g = context;
            this.d = null;
            this.f17797c = false;
            QbSdk.a(context, "failed to createTBSWebview!");
            this.f17798e = new a(context, attributeSet);
            CookieSyncManager.createInstance(this.f17800g).startSync();
            try {
                Method declaredMethod = Class.forName("android.webkit.WebViewWorker").getDeclaredMethod("getHandler", new Class[0]);
                declaredMethod.setAccessible(true);
                ((Handler) declaredMethod.invoke(null, new Object[0])).getLooper().getThread().setUncaughtExceptionHandler(new g());
            } catch (Exception unused) {
            }
            CookieManager.getInstance().a();
            this.f17798e.setFocusableInTouchMode(true);
            addView(this.f17798e, new FrameLayout.LayoutParams(-1, -1));
            TbsLog.i("WebView", "SystemWebView Created Success! #3, SysWebViewForcedByOuter.");
            TbsLog.e("WebView", "sys WebView: IsSysWebViewForcedByOuter = true", true);
        } else {
            if (TbsShareManager.isThirdPartyApp(context)) {
                TbsLog.i("webview", "new WebView, thread is " + Thread.currentThread() + "stack: " + Log.getStackTraceString(new Throwable("new WebView Detect")));
            }
            TbsLog.initIfNeed(context);
            if (context == null) {
                throw new IllegalArgumentException("Invalid context argument");
            }
            b(context);
            this.f17800g = context;
            if (context != null) {
                f17795i = context.getApplicationContext();
            }
            if (!this.f17797c || QbSdk.a) {
                this.d = null;
                if (TbsShareManager.isThirdPartyApp(this.f17800g)) {
                    this.f17798e = new a(context, attributeSet);
                } else {
                    this.f17798e = new a(this, context);
                }
                TbsLog.i("WebView", "SystemWebView Created Success! #2");
                CookieManager.getInstance().a();
                this.f17798e.setFocusableInTouchMode(true);
                addView(this.f17798e, new FrameLayout.LayoutParams(-1, -1));
                setDownloadListener(null);
                TbsLog.writeLogToDisk();
                m.a(context);
            } else {
                IX5WebViewBase a2 = u.a().a(true).a(context);
                this.d = a2;
                if (a2 == null || a2.getView() == null) {
                    TbsLog.e("WebView", "sys WebView: failed to createTBSWebview", true);
                    this.d = null;
                    this.f17797c = false;
                    QbSdk.a(context, "failed to createTBSWebview!");
                    b(context);
                    if (TbsShareManager.isThirdPartyApp(this.f17800g)) {
                        this.f17798e = new a(context, attributeSet);
                    } else {
                        this.f17798e = new a(this, context);
                    }
                    TbsLog.i("WebView", "SystemWebView Created Success! #1");
                    CookieManager.getInstance().a();
                    this.f17798e.setFocusableInTouchMode(true);
                    addView(this.f17798e, new FrameLayout.LayoutParams(-1, -1));
                    try {
                        if (Build.VERSION.SDK_INT >= 11) {
                            removeJavascriptInterface("searchBoxJavaBridge_");
                            removeJavascriptInterface("accessibility");
                            removeJavascriptInterface("accessibilityTraversal");
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    TbsLog.writeLogToDisk();
                    m.a(context);
                } else {
                    TbsLog.i("WebView", "X5 WebView Created Success!!");
                    this.d.getView().setFocusableInTouchMode(true);
                    a(attributeSet);
                    addView(this.d.getView(), new FrameLayout.LayoutParams(-1, -1));
                    this.d.setDownloadListener(new b(this, null, this.f17797c));
                    this.d.getX5WebViewExtension().setWebViewClientExtension(new X5ProxyWebViewClientExtension(u.a().a(true).k()) { // from class: com.tencent.smtt.sdk.WebView.1
                        {
                            WebView.this = this;
                        }

                        @Override // com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension, com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension
                        public void invalidate() {
                        }

                        @Override // com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension, com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension
                        public void onScrollChanged(int i3, int i4, int i5, int i6) {
                            super.onScrollChanged(i3, i4, i5, i6);
                            WebView.this.onScrollChanged(i5, i6, i3, i4);
                        }
                    });
                }
            }
            try {
                if (Build.VERSION.SDK_INT >= 11) {
                    removeJavascriptInterface("searchBoxJavaBridge_");
                    removeJavascriptInterface("accessibility");
                    removeJavascriptInterface("accessibilityTraversal");
                }
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
            if (("com.tencent.mobileqq".equals(this.f17800g.getApplicationInfo().packageName) || "com.tencent.mm".equals(this.f17800g.getApplicationInfo().packageName)) && f.a(true).i() && Build.VERSION.SDK_INT >= 11) {
                setLayerType(1, null);
            }
            if (this.d != null) {
                TbsLog.writeLogToDisk();
            }
        }
        this.f17801h.a(System.currentTimeMillis() - currentTimeMillis);
    }

    @Deprecated
    public WebView(Context context, AttributeSet attributeSet, int i2, boolean z) {
        this(context, attributeSet, i2, null, z);
    }

    @Deprecated
    public WebView(Context context, boolean z) {
        super(context);
        this.b = "WebView";
        this.f17797c = false;
        this.f17799f = null;
        this.f17800g = null;
        this.f17801h = new TbsWebViewPerformanceRecorder();
        this.a = 0;
        this.f17802j = false;
        this.f17803k = false;
        this.f17804m = null;
        this.f17805n = null;
        this.p = 1;
        this.q = 2;
        this.r = 3;
        this.s = "javascript:document.getElementsByTagName('HEAD').item(0).removeChild(document.getElementById('QQBrowserSDKNightMode'));";
        this.t = "javascript:var style = document.createElement('style');style.type='text/css';style.id='QQBrowserSDKNightMode';style.innerHTML='html,body{background:none !important;background-color: #1d1e2a !important;}html *{background-color: #1d1e2a !important; color:#888888 !important;border-color:#3e4f61 !important;text-shadow:none !important;box-shadow:none !important;}a,a *{border-color:#4c5b99 !important; color:#2d69b3 !important;text-decoration:none !important;}a:visited,a:visited *{color:#a600a6 !important;}a:active,a:active *{color:#5588AA !important;}input,select,textarea,option,button{background-image:none !important;color:#AAAAAA !important;border-color:#4c5b99 !important;}form,div,button,span{background-color:#1d1e2a !important; border-color:#4c5b99 !important;}img{opacity:0.5}';document.getElementsByTagName('HEAD').item(0).appendChild(style);";
        this.w = null;
        this.x = null;
    }

    private void a(AttributeSet attributeSet) {
        View view;
        if (attributeSet != null) {
            try {
                int attributeCount = attributeSet.getAttributeCount();
                for (int i2 = 0; i2 < attributeCount; i2++) {
                    if (attributeSet.getAttributeName(i2).equalsIgnoreCase("scrollbars")) {
                        int[] intArray = getResources().getIntArray(16842974);
                        int attributeIntValue = attributeSet.getAttributeIntValue(i2, -1);
                        if (attributeIntValue == intArray[1]) {
                            this.d.getView().setVerticalScrollBarEnabled(false);
                            view = this.d.getView();
                        } else if (attributeIntValue == intArray[2]) {
                            this.d.getView().setVerticalScrollBarEnabled(false);
                        } else if (attributeIntValue == intArray[3]) {
                            view = this.d.getView();
                        }
                        view.setHorizontalScrollBarEnabled(false);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a(boolean z) {
        if (!this.f17802j && this.a != 0) {
            j();
        }
        if (!this.f17797c) {
            try {
                Class<?> cls = Class.forName("android.webkit.WebViewClassic");
                Method method = cls.getMethod("fromWebView", android.webkit.WebView.class);
                method.setAccessible(true);
                Object invoke = method.invoke(null, this.f17798e);
                if (invoke != null) {
                    Field declaredField = cls.getDeclaredField("mListBoxDialog");
                    declaredField.setAccessible(true);
                    Object obj = declaredField.get(invoke);
                    if (obj != null) {
                        Dialog dialog = (Dialog) obj;
                        dialog.setOnCancelListener(null);
                        Class<?> cls2 = Class.forName("android.app.Dialog");
                        Field declaredField2 = cls2.getDeclaredField("CANCEL");
                        declaredField2.setAccessible(true);
                        int intValue = ((Integer) declaredField2.get(dialog)).intValue();
                        Field declaredField3 = cls2.getDeclaredField("mListenersHandler");
                        declaredField3.setAccessible(true);
                        ((Handler) declaredField3.get(dialog)).removeMessages(intValue);
                    }
                }
            } catch (Exception unused) {
            }
            if (z) {
                this.f17798e.destroy();
            }
            try {
                TbsLog.i("sdkreport", "webview.tbsWebviewDestroy mQQMusicCrashFix is " + this.f17803k);
                if (this.f17803k) {
                    return;
                }
                Field declaredField4 = Class.forName("android.webkit.BrowserFrame").getDeclaredField("sConfigCallback");
                declaredField4.setAccessible(true);
                ComponentCallbacks componentCallbacks = (ComponentCallbacks) declaredField4.get(null);
                if (componentCallbacks != null) {
                    declaredField4.set(null, null);
                    Field declaredField5 = Class.forName("android.view.ViewRoot").getDeclaredField("sConfigCallbacks");
                    declaredField5.setAccessible(true);
                    Object obj2 = declaredField5.get(null);
                    if (obj2 != null) {
                        List list = (List) obj2;
                        synchronized (list) {
                            list.remove(componentCallbacks);
                        }
                    }
                }
            } catch (Exception unused2) {
            }
        } else if (z) {
            this.d.destroy();
        }
        TbsLog.i("WebView", "X5 GUID = " + QbSdk.b());
    }

    private boolean a(Context context) {
        try {
            return context.getPackageName().indexOf("com.tencent.mobileqq") >= 0;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    private boolean a(View view) {
        Object a2;
        Context context = this.f17800g;
        if ((context == null || getTbsCoreVersion(context) <= 36200) && (a2 = com.tencent.smtt.utils.j.a(this.w, DYConstants.DY_ON_LONG_CLICK, new Class[]{View.class}, view)) != null) {
            return ((Boolean) a2).booleanValue();
        }
        return false;
    }

    private boolean a(WebChromeClient webChromeClient) {
        if (webChromeClient == null) {
            return false;
        }
        boolean z = false;
        boolean z2 = false;
        for (Class<?> cls = webChromeClient.getClass(); cls != WebChromeClient.class && (!z || !z2); cls = cls.getSuperclass()) {
            if (!z) {
                try {
                    cls.getDeclaredMethod("onShowCustomView", View.class, IX5WebChromeClient.CustomViewCallback.class);
                    z = true;
                } catch (NoSuchMethodException unused) {
                }
            }
            if (!z2) {
                try {
                    cls.getDeclaredMethod("onHideCustomView", new Class[0]);
                    z2 = true;
                } catch (NoSuchMethodException unused2) {
                }
            }
        }
        return z && z2;
    }

    private void b(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        u a2 = u.a();
        a2.a(context);
        this.f17801h.b(System.currentTimeMillis() - currentTimeMillis);
        this.f17797c = a2.b();
    }

    public Context c(Context context) {
        int i2 = Build.VERSION.SDK_INT;
        return (i2 < 21 || i2 > 22) ? context : context.createConfigurationContext(new Configuration());
    }

    public static void c() {
        try {
            new Thread(new Runnable() { // from class: com.tencent.smtt.sdk.WebView.8
                @Override // java.lang.Runnable
                public void run() {
                    if (WebView.f17795i == null) {
                        TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--mAppContext == null");
                        return;
                    }
                    f a2 = f.a(true);
                    if (f.b) {
                        TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--needReboot = true");
                        return;
                    }
                    l a3 = l.a(WebView.f17795i);
                    int c2 = a3.c();
                    TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--installStatus = " + c2);
                    if (c2 == 2) {
                        TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--install setTbsNeedReboot true");
                        a2.a(String.valueOf(a3.b()));
                        a2.b(true);
                        return;
                    }
                    int b = a3.b("copy_status");
                    TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--copyStatus = " + b);
                    if (b == 1) {
                        TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--copy setTbsNeedReboot true");
                        a2.a(String.valueOf(a3.c("copy_core_ver")));
                        a2.b(true);
                    } else if (u.a().b()) {
                    } else {
                        if (c2 == 3 || b == 3) {
                            TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--setTbsNeedReboot true");
                            a2.a(String.valueOf(f.d()));
                            a2.b(true);
                        }
                    }
                }
            }).start();
        } catch (Throwable th) {
            TbsLog.e("webview", "updateRebootStatus excpetion: " + th);
        }
    }

    @Deprecated
    public static void disablePlatformNotifications() {
        if (u.a().b()) {
            return;
        }
        com.tencent.smtt.utils.j.a("android.webkit.WebView", "disablePlatformNotifications");
    }

    @Deprecated
    public static void enablePlatformNotifications() {
        if (u.a().b()) {
            return;
        }
        com.tencent.smtt.utils.j.a("android.webkit.WebView", "enablePlatformNotifications");
    }

    @Deprecated
    public static String findAddress(String str) {
        if (u.a().b()) {
            return null;
        }
        return android.webkit.WebView.findAddress(str);
    }

    private void g() {
        if (!this.f17802j && this.a != 0) {
            j();
        }
        if (this.f17797c) {
            this.d.destroy();
            return;
        }
        this.f17798e.destroy();
        try {
            TbsLog.i("sdkreport", "webview.destroyImplNow mQQMusicCrashFix is " + this.f17803k);
            if (this.f17803k) {
                return;
            }
            Field declaredField = Class.forName("android.webkit.BrowserFrame").getDeclaredField("sConfigCallback");
            declaredField.setAccessible(true);
            ComponentCallbacks componentCallbacks = (ComponentCallbacks) declaredField.get(null);
            if (componentCallbacks != null) {
                declaredField.set(null, null);
                Field declaredField2 = Class.forName("android.view.ViewRoot").getDeclaredField("sConfigCallbacks");
                declaredField2.setAccessible(true);
                Object obj = declaredField2.get(null);
                if (obj != null) {
                    List list = (List) obj;
                    synchronized (list) {
                        list.remove(componentCallbacks);
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    public static String getCrashExtraCacheInfo(Context context) {
        Map<String, Object> map;
        if (context == null) {
            return "";
        }
        String str = "tbs_core_version:" + QbSdk.getTbsVersionForCrash(context) + ";tbs_sdk_version:44286;";
        StringBuilder sb = new StringBuilder();
        sb.append(f.a(true).f());
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append(str);
        if (!TbsShareManager.isThirdPartyApp(context) && (map = QbSdk.o) != null && map.containsKey(TbsCoreSettings.TBS_SETTINGS_WEAPP_ID_KEY) && QbSdk.o.containsKey(TbsCoreSettings.TBS_SETTINGS_WEAPP_NAME_KEY)) {
            String str2 = "weapp_id:" + QbSdk.o.get(TbsCoreSettings.TBS_SETTINGS_WEAPP_ID_KEY) + ";" + TbsCoreSettings.TBS_SETTINGS_WEAPP_NAME_KEY + ":" + QbSdk.o.get(TbsCoreSettings.TBS_SETTINGS_WEAPP_NAME_KEY) + ";";
            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            sb.append(str2);
        }
        return sb.length() > 8192 ? sb.substring(sb.length() - 8192) : sb.toString();
    }

    public static String getCrashExtraMessage(Context context) {
        Map<String, Object> map;
        if (context == null) {
            return "";
        }
        String str = "tbs_core_version:" + QbSdk.getTbsVersionForCrash(context) + ";tbs_sdk_version:44286;";
        StringBuilder sb = new StringBuilder();
        sb.append(f.a(true).e());
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append(str);
        if (!TbsShareManager.isThirdPartyApp(context) && (map = QbSdk.o) != null && map.containsKey(TbsCoreSettings.TBS_SETTINGS_WEAPP_ID_KEY) && QbSdk.o.containsKey(TbsCoreSettings.TBS_SETTINGS_WEAPP_NAME_KEY)) {
            String str2 = "weapp_id:" + QbSdk.o.get(TbsCoreSettings.TBS_SETTINGS_WEAPP_ID_KEY) + ";" + TbsCoreSettings.TBS_SETTINGS_WEAPP_NAME_KEY + ":" + QbSdk.o.get(TbsCoreSettings.TBS_SETTINGS_WEAPP_NAME_KEY) + ";";
            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            sb.append(str2);
        }
        return sb.length() > 8192 ? sb.substring(sb.length() - 8192) : sb.toString();
    }

    public static PackageInfo getCurrentWebViewPackage() {
        if (u.a().b() || Build.VERSION.SDK_INT < 26) {
            return null;
        }
        try {
            return (PackageInfo) com.tencent.smtt.utils.j.a("android.webkit.WebView", "getCurrentWebViewPackage");
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Deprecated
    public static synchronized Object getPluginList() {
        synchronized (WebView.class) {
            if (u.a().b()) {
                return null;
            }
            return com.tencent.smtt.utils.j.a("android.webkit.WebView", "getPluginList");
        }
    }

    public static int getTbsCoreVersion(Context context) {
        return QbSdk.getTbsVersion(context);
    }

    public static boolean getTbsNeedReboot() {
        c();
        return f.a(true).g();
    }

    public static int getTbsSDKVersion(Context context) {
        return 44286;
    }

    private void h() {
        try {
            if ("com.xunmeng.pinduoduo".equals(this.f17800g.getApplicationInfo().packageName)) {
                new Thread("WebviewDestroy") { // from class: com.tencent.smtt.sdk.WebView.2
                    {
                        WebView.this = this;
                    }

                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        WebView.this.a(false);
                    }
                }.start();
                if (this.f17797c) {
                    this.d.destroy();
                } else {
                    this.f17798e.destroy();
                }
            } else {
                a(true);
            }
        } catch (Throwable unused) {
            a(true);
        }
    }

    public static boolean hasCreatedSysWebViewInstance() {
        return mSysWebviewCreated;
    }

    public long i() {
        long j2;
        synchronized (QbSdk.f17726h) {
            if (QbSdk.f17723e) {
                QbSdk.f17725g += System.currentTimeMillis() - QbSdk.f17724f;
                TbsLog.d("sdkreport", "pv report, WebView.getWifiConnectedTime QbSdk.sWifiConnectedTime=" + QbSdk.f17725g);
            }
            j2 = QbSdk.f17725g / 1000;
            QbSdk.f17725g = 0L;
            QbSdk.f17724f = System.currentTimeMillis();
        }
        return j2;
    }

    private void j() {
        Runnable runnable = new Runnable() { // from class: com.tencent.smtt.sdk.WebView.7
            {
                WebView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                Bundle sdkQBStatisticsInfo;
                if (WebView.this.f17802j || WebView.this.a == 0) {
                    return;
                }
                synchronized (WebView.this) {
                    if (!WebView.this.f17802j && WebView.this.a != 0) {
                        WebView.this.f17802j = true;
                        String str = "";
                        String str2 = "";
                        String str3 = "";
                        if (WebView.this.f17797c && (sdkQBStatisticsInfo = WebView.this.d.getX5WebViewExtension().getSdkQBStatisticsInfo()) != null) {
                            str = sdkQBStatisticsInfo.getString("guid");
                            str2 = sdkQBStatisticsInfo.getString("qua2");
                            str3 = sdkQBStatisticsInfo.getString("lc");
                        }
                        com.tencent.smtt.sdk.stat.b.a(WebView.this.f17800g, str, str2, str3, WebView.this.a, WebView.this.f17797c, WebView.this.i(), WebView.this.d.getX5WebViewExtension().isX5CoreSandboxMode());
                        WebView.this.a = 0;
                        WebView.this.f17802j = false;
                    }
                }
            }
        };
        Handler tbsLogHandler = TbsLog.getTbsLogHandler();
        if (tbsLogHandler != null) {
            Message obtain = Message.obtain(tbsLogHandler, 501);
            obtain.obj = runnable;
            obtain.sendToTarget();
            return;
        }
        Thread thread = new Thread(runnable);
        thread.setName("TbsPV");
        thread.start();
    }

    public static void setDataDirectorySuffix(String str) {
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                com.tencent.smtt.utils.j.a(Class.forName("android.webkit.WebView"), "setDataDirectorySuffix", (Class<?>[]) new Class[]{String.class}, str);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        HashMap hashMap = new HashMap();
        hashMap.put("data_directory_suffix", str);
        QbSdk.initTbsSettings(hashMap);
    }

    public static synchronized void setSysDayOrNight(boolean z) {
        int i2;
        Paint paint;
        synchronized (WebView.class) {
            if (z == v) {
                return;
            }
            v = z;
            if (u == null) {
                Paint paint2 = new Paint();
                u = paint2;
                paint2.setColor(-16777216);
            }
            if (z) {
                i2 = 255;
                if (u.getAlpha() != 255) {
                    paint = u;
                    paint.setAlpha(i2);
                }
                return;
            }
            int alpha = u.getAlpha();
            i2 = NIGHT_MODE_ALPHA;
            if (alpha != i2) {
                paint = u;
                paint.setAlpha(i2);
            }
            return;
        }
    }

    public static void setWebContentsDebuggingEnabled(boolean z) {
        u a2 = u.a();
        if (a2 != null && a2.b()) {
            a2.c().a(z);
        } else if (Build.VERSION.SDK_INT >= 19) {
            try {
                Method declaredMethod = Class.forName("android.webkit.WebView").getDeclaredMethod("setWebContentsDebuggingEnabled", Boolean.TYPE);
                f17796l = declaredMethod;
                if (declaredMethod != null) {
                    declaredMethod.setAccessible(true);
                    f17796l.invoke(null, Boolean.valueOf(z));
                }
            } catch (Exception e2) {
                TbsLog.e("QbSdk", "Exception:" + e2.getStackTrace());
                e2.printStackTrace();
            }
        }
    }

    public android.webkit.WebView a() {
        if (this.f17797c) {
            return null;
        }
        return this.f17798e;
    }

    public void a(android.webkit.WebView webView) {
    }

    public void a(IX5WebViewBase iX5WebViewBase) {
        this.d = iX5WebViewBase;
    }

    public void addJavascriptInterface(Object obj, String str) {
        if (this.f17797c) {
            this.d.addJavascriptInterface(obj, str);
        } else {
            this.f17798e.addJavascriptInterface(obj, str);
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view) {
        if (!this.f17797c) {
            this.f17798e.addView(view);
            return;
        }
        View view2 = this.d.getView();
        try {
            Method a2 = com.tencent.smtt.utils.j.a(view2, "addView", View.class);
            a2.setAccessible(true);
            a2.invoke(view2, view);
        } catch (Throwable unused) {
        }
    }

    public IX5WebViewBase b() {
        return this.d;
    }

    public boolean canGoBack() {
        return !this.f17797c ? this.f17798e.canGoBack() : this.d.canGoBack();
    }

    public boolean canGoBackOrForward(int i2) {
        return !this.f17797c ? this.f17798e.canGoBackOrForward(i2) : this.d.canGoBackOrForward(i2);
    }

    public boolean canGoForward() {
        return !this.f17797c ? this.f17798e.canGoForward() : this.d.canGoForward();
    }

    @Deprecated
    public boolean canZoomIn() {
        Object a2;
        if (this.f17797c) {
            return this.d.canZoomIn();
        }
        if (Build.VERSION.SDK_INT < 11 || (a2 = com.tencent.smtt.utils.j.a(this.f17798e, "canZoomIn")) == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    @Deprecated
    public boolean canZoomOut() {
        Object a2;
        if (this.f17797c) {
            return this.d.canZoomOut();
        }
        if (Build.VERSION.SDK_INT < 11 || (a2 = com.tencent.smtt.utils.j.a(this.f17798e, "canZoomOut")) == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    @Deprecated
    public Picture capturePicture() {
        if (this.f17797c) {
            return this.d.capturePicture();
        }
        Object a2 = com.tencent.smtt.utils.j.a(this.f17798e, "capturePicture");
        if (a2 == null) {
            return null;
        }
        return (Picture) a2;
    }

    public void clearCache(boolean z) {
        if (this.f17797c) {
            this.d.clearCache(z);
        } else {
            this.f17798e.clearCache(z);
        }
    }

    public void clearFormData() {
        if (this.f17797c) {
            this.d.clearFormData();
        } else {
            this.f17798e.clearFormData();
        }
    }

    public void clearHistory() {
        if (this.f17797c) {
            this.d.clearHistory();
        } else {
            this.f17798e.clearHistory();
        }
    }

    @TargetApi(3)
    public void clearMatches() {
        if (this.f17797c) {
            this.d.clearMatches();
        } else {
            this.f17798e.clearMatches();
        }
    }

    public void clearSslPreferences() {
        if (this.f17797c) {
            this.d.clearSslPreferences();
        } else {
            this.f17798e.clearSslPreferences();
        }
    }

    @Deprecated
    public void clearView() {
        if (this.f17797c) {
            this.d.clearView();
        } else {
            com.tencent.smtt.utils.j.a(this.f17798e, "clearView");
        }
    }

    @Override // android.view.View
    public int computeHorizontalScrollExtent() {
        try {
            if (this.f17797c) {
                Method a2 = com.tencent.smtt.utils.j.a(this.d.getView(), "computeHorizontalScrollExtent", new Class[0]);
                a2.setAccessible(true);
                return ((Integer) a2.invoke(this.d.getView(), new Object[0])).intValue();
            }
            Method a3 = com.tencent.smtt.utils.j.a(this.f17798e, "computeHorizontalScrollExtent", new Class[0]);
            a3.setAccessible(true);
            return ((Integer) a3.invoke(this.f17798e, new Object[0])).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        try {
            if (this.f17797c) {
                Method a2 = com.tencent.smtt.utils.j.a(this.d.getView(), "computeHorizontalScrollOffset", new Class[0]);
                a2.setAccessible(true);
                return ((Integer) a2.invoke(this.d.getView(), new Object[0])).intValue();
            }
            Method a3 = com.tencent.smtt.utils.j.a(this.f17798e, "computeHorizontalScrollOffset", new Class[0]);
            a3.setAccessible(true);
            return ((Integer) a3.invoke(this.f17798e, new Object[0])).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        try {
            if (this.f17797c) {
                return ((Integer) com.tencent.smtt.utils.j.a(this.d.getView(), "computeHorizontalScrollRange", new Class[0], new Object[0])).intValue();
            }
            Method a2 = com.tencent.smtt.utils.j.a(this.f17798e, "computeHorizontalScrollRange", new Class[0]);
            a2.setAccessible(true);
            return ((Integer) a2.invoke(this.f17798e, new Object[0])).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.f17797c) {
            this.d.computeScroll();
        } else {
            this.f17798e.computeScroll();
        }
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        try {
            if (this.f17797c) {
                Method a2 = com.tencent.smtt.utils.j.a(this.d.getView(), "computeVerticalScrollExtent", new Class[0]);
                a2.setAccessible(true);
                return ((Integer) a2.invoke(this.d.getView(), new Object[0])).intValue();
            }
            Method a3 = com.tencent.smtt.utils.j.a(this.f17798e, "computeVerticalScrollExtent", new Class[0]);
            a3.setAccessible(true);
            return ((Integer) a3.invoke(this.f17798e, new Object[0])).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        try {
            if (this.f17797c) {
                Method a2 = com.tencent.smtt.utils.j.a(this.d.getView(), "computeVerticalScrollOffset", new Class[0]);
                a2.setAccessible(true);
                return ((Integer) a2.invoke(this.d.getView(), new Object[0])).intValue();
            }
            Method a3 = com.tencent.smtt.utils.j.a(this.f17798e, "computeVerticalScrollOffset", new Class[0]);
            a3.setAccessible(true);
            return ((Integer) a3.invoke(this.f17798e, new Object[0])).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        try {
            if (this.f17797c) {
                return ((Integer) com.tencent.smtt.utils.j.a(this.d.getView(), "computeVerticalScrollRange", new Class[0], new Object[0])).intValue();
            }
            Method a2 = com.tencent.smtt.utils.j.a(this.f17798e, "computeVerticalScrollRange", new Class[0]);
            a2.setAccessible(true);
            return ((Integer) a2.invoke(this.f17798e, new Object[0])).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public WebBackForwardList copyBackForwardList() {
        return this.f17797c ? WebBackForwardList.a(this.d.copyBackForwardList()) : WebBackForwardList.a(this.f17798e.copyBackForwardList());
    }

    public Object createPrintDocumentAdapter(String str) {
        if (!this.f17797c) {
            if (Build.VERSION.SDK_INT < 21) {
                return null;
            }
            return com.tencent.smtt.utils.j.a(this.f17798e, "createPrintDocumentAdapter", new Class[]{String.class}, str);
        }
        try {
            return this.d.createPrintDocumentAdapter(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void customDiskCachePathEnabled(boolean z, String str) {
        if (!this.f17797c || getX5WebViewExtension() == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean(ViewProps.ENABLED, z);
        bundle.putString("path", str);
        getX5WebViewExtension().invokeMiscMethod("customDiskCachePathEnabled", bundle);
    }

    public void destroy() {
        int i2;
        boolean z = false;
        this.f17803k = false;
        try {
            if (this.f17800g.getApplicationInfo().packageName.contains("com.tencent.qqmusic") && ((i2 = Build.VERSION.SDK_INT) == 21 || i2 == 22)) {
                this.f17803k = true;
                z = true;
            }
        } catch (Throwable th) {
            TbsLog.i("webview", "stack is " + Log.getStackTraceString(th));
        }
        TbsLog.i("webview", "destroy forceDestoyOld is " + z);
        if (z) {
            h();
        } else {
            g();
        }
    }

    public void documentHasImages(Message message) {
        if (this.f17797c) {
            this.d.documentHasImages(message);
        } else {
            this.f17798e.documentHasImages(message);
        }
    }

    public void dumpViewHierarchyWithProperties(BufferedWriter bufferedWriter, int i2) {
        if (this.f17797c) {
            this.d.dumpViewHierarchyWithProperties(bufferedWriter, i2);
        } else {
            com.tencent.smtt.utils.j.a(this.f17798e, "dumpViewHierarchyWithProperties", new Class[]{BufferedWriter.class, Integer.TYPE}, bufferedWriter, Integer.valueOf(i2));
        }
    }

    public void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        if (this.f17797c) {
            try {
                Method a2 = com.tencent.smtt.utils.j.a(this.d.getView(), "evaluateJavascript", String.class, android.webkit.ValueCallback.class);
                a2.setAccessible(true);
                a2.invoke(this.d.getView(), str, valueCallback);
            } catch (Exception e2) {
                e2.printStackTrace();
                loadUrl(str);
            }
        } else if (Build.VERSION.SDK_INT >= 19) {
            try {
                Method declaredMethod = Class.forName("android.webkit.WebView").getDeclaredMethod("evaluateJavascript", String.class, android.webkit.ValueCallback.class);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(this.f17798e, str, valueCallback);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    @Deprecated
    public int findAll(String str) {
        if (this.f17797c) {
            return this.d.findAll(str);
        }
        Object a2 = com.tencent.smtt.utils.j.a(this.f17798e, "findAll", new Class[]{String.class}, str);
        if (a2 == null) {
            return 0;
        }
        return ((Integer) a2).intValue();
    }

    @TargetApi(16)
    public void findAllAsync(String str) {
        if (this.f17797c) {
            this.d.findAllAsync(str);
        } else if (Build.VERSION.SDK_INT >= 16) {
            com.tencent.smtt.utils.j.a(this.f17798e, "findAllAsync", new Class[]{String.class}, str);
        }
    }

    public View findHierarchyView(String str, int i2) {
        return !this.f17797c ? (View) com.tencent.smtt.utils.j.a(this.f17798e, "findHierarchyView", new Class[]{String.class, Integer.TYPE}, str, Integer.valueOf(i2)) : this.d.findHierarchyView(str, i2);
    }

    @TargetApi(3)
    public void findNext(boolean z) {
        if (this.f17797c) {
            this.d.findNext(z);
        } else {
            this.f17798e.findNext(z);
        }
    }

    public void flingScroll(int i2, int i3) {
        if (this.f17797c) {
            this.d.flingScroll(i2, i3);
        } else {
            this.f17798e.flingScroll(i2, i3);
        }
    }

    @Deprecated
    public void freeMemory() {
        if (this.f17797c) {
            this.d.freeMemory();
        } else {
            com.tencent.smtt.utils.j.a(this.f17798e, "freeMemory");
        }
    }

    public SslCertificate getCertificate() {
        return !this.f17797c ? this.f17798e.getCertificate() : this.d.getCertificate();
    }

    public int getContentHeight() {
        return !this.f17797c ? this.f17798e.getContentHeight() : this.d.getContentHeight();
    }

    public int getContentWidth() {
        if (this.f17797c) {
            return this.d.getContentWidth();
        }
        Object a2 = com.tencent.smtt.utils.j.a(this.f17798e, "getContentWidth");
        if (a2 == null) {
            return 0;
        }
        return ((Integer) a2).intValue();
    }

    public Bitmap getFavicon() {
        return !this.f17797c ? this.f17798e.getFavicon() : this.d.getFavicon();
    }

    public HitTestResult getHitTestResult() {
        return !this.f17797c ? new HitTestResult(this.f17798e.getHitTestResult()) : new HitTestResult(this.d.getHitTestResult());
    }

    public String[] getHttpAuthUsernamePassword(String str, String str2) {
        return !this.f17797c ? this.f17798e.getHttpAuthUsernamePassword(str, str2) : this.d.getHttpAuthUsernamePassword(str, str2);
    }

    public boolean getIsX5Core() {
        return this.f17797c;
    }

    @TargetApi(3)
    public String getOriginalUrl() {
        return !this.f17797c ? this.f17798e.getOriginalUrl() : this.d.getOriginalUrl();
    }

    public TbsWebViewPerformanceRecorder getPerformanceRecorder() {
        return this.f17801h;
    }

    public int getProgress() {
        return !this.f17797c ? this.f17798e.getProgress() : this.d.getProgress();
    }

    public boolean getRendererPriorityWaivedWhenNotVisible() {
        Object a2;
        try {
            if (!this.f17797c && Build.VERSION.SDK_INT >= 26 && (a2 = com.tencent.smtt.utils.j.a(this.f17798e, "getRendererPriorityWaivedWhenNotVisible")) != null) {
                return ((Boolean) a2).booleanValue();
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public int getRendererRequestedPriority() {
        Object a2;
        try {
            if (!this.f17797c && Build.VERSION.SDK_INT >= 26 && (a2 = com.tencent.smtt.utils.j.a(this.f17798e, "getRendererRequestedPriority")) != null) {
                return ((Integer) a2).intValue();
            }
            return 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public float getScale() {
        if (this.f17797c) {
            return this.d.getScale();
        }
        Object a2 = com.tencent.smtt.utils.j.a(this.f17798e, "getScale");
        if (a2 == null) {
            return 0.0f;
        }
        return ((Float) a2).floatValue();
    }

    @Override // android.view.View
    public int getScrollBarDefaultDelayBeforeFade() {
        if (getView() == null) {
            return 0;
        }
        return getView().getScrollBarDefaultDelayBeforeFade();
    }

    @Override // android.view.View
    public int getScrollBarFadeDuration() {
        if (getView() == null) {
            return 0;
        }
        return getView().getScrollBarFadeDuration();
    }

    @Override // android.view.View
    public int getScrollBarSize() {
        if (getView() == null) {
            return 0;
        }
        return getView().getScrollBarSize();
    }

    @Override // android.view.View
    public int getScrollBarStyle() {
        if (getView() == null) {
            return 0;
        }
        return getView().getScrollBarStyle();
    }

    public WebSettings getSettings() {
        WebSettings webSettings = this.f17799f;
        if (webSettings != null) {
            return webSettings;
        }
        WebSettings webSettings2 = this.f17797c ? new WebSettings(this.d.getSettings()) : new WebSettings(this.f17798e.getSettings());
        this.f17799f = webSettings2;
        return webSettings2;
    }

    public IX5WebSettingsExtension getSettingsExtension() {
        if (this.f17797c) {
            return this.d.getX5WebViewExtension().getSettingsExtension();
        }
        return null;
    }

    public int getSysNightModeAlpha() {
        return NIGHT_MODE_ALPHA;
    }

    public String getTitle() {
        return !this.f17797c ? this.f17798e.getTitle() : this.d.getTitle();
    }

    public String getUrl() {
        return !this.f17797c ? this.f17798e.getUrl() : this.d.getUrl();
    }

    public View getView() {
        return !this.f17797c ? this.f17798e : this.d.getView();
    }

    public int getVisibleTitleHeight() {
        if (this.f17797c) {
            return this.d.getVisibleTitleHeight();
        }
        Object a2 = com.tencent.smtt.utils.j.a(this.f17798e, "getVisibleTitleHeight");
        if (a2 == null) {
            return 0;
        }
        return ((Integer) a2).intValue();
    }

    public WebChromeClient getWebChromeClient() {
        return this.f17805n;
    }

    public IX5WebChromeClientExtension getWebChromeClientExtension() {
        if (this.f17797c) {
            return this.d.getX5WebViewExtension().getWebChromeClientExtension();
        }
        return null;
    }

    public int getWebScrollX() {
        return this.f17797c ? this.d.getView().getScrollX() : this.f17798e.getScrollX();
    }

    public int getWebScrollY() {
        return this.f17797c ? this.d.getView().getScrollY() : this.f17798e.getScrollY();
    }

    public WebViewClient getWebViewClient() {
        return this.f17804m;
    }

    public IX5WebViewClientExtension getWebViewClientExtension() {
        if (this.f17797c) {
            return this.d.getX5WebViewExtension().getWebViewClientExtension();
        }
        return null;
    }

    public IX5WebViewBase.HitTestResult getX5HitTestResult() {
        if (this.f17797c) {
            return this.d.getHitTestResult();
        }
        return null;
    }

    public IX5WebViewExtension getX5WebViewExtension() {
        if (this.f17797c) {
            return this.d.getX5WebViewExtension();
        }
        return null;
    }

    @Deprecated
    public View getZoomControls() {
        return !this.f17797c ? (View) com.tencent.smtt.utils.j.a(this.f17798e, "getZoomControls") : this.d.getZoomControls();
    }

    public void goBack() {
        if (this.f17797c) {
            this.d.goBack();
        } else {
            this.f17798e.goBack();
        }
    }

    public void goBackOrForward(int i2) {
        if (this.f17797c) {
            this.d.goBackOrForward(i2);
        } else {
            this.f17798e.goBackOrForward(i2);
        }
    }

    public void goForward() {
        if (this.f17797c) {
            this.d.goForward();
        } else {
            this.f17798e.goForward();
        }
    }

    public void invokeZoomPicker() {
        if (this.f17797c) {
            this.d.invokeZoomPicker();
        } else {
            this.f17798e.invokeZoomPicker();
        }
    }

    public boolean isDayMode() {
        return v;
    }

    public boolean isPrivateBrowsingEnabled() {
        Object a2;
        if (this.f17797c) {
            return this.d.isPrivateBrowsingEnable();
        }
        if (Build.VERSION.SDK_INT < 11 || (a2 = com.tencent.smtt.utils.j.a(this.f17798e, "isPrivateBrowsingEnabled")) == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public void loadData(String str, String str2, String str3) {
        if (this.f17797c) {
            this.d.loadData(str, str2, str3);
        } else {
            this.f17798e.loadData(str, str2, str3);
        }
    }

    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        if (this.f17797c) {
            this.d.loadDataWithBaseURL(str, str2, str3, str4, str5);
        } else {
            this.f17798e.loadDataWithBaseURL(str, str2, str3, str4, str5);
        }
    }

    public void loadUrl(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        if (str == null || showDebugView(str)) {
            return;
        }
        if (this.f17797c) {
            this.d.loadUrl(str);
        } else {
            this.f17798e.loadUrl(str);
        }
        this.f17801h.a(System.currentTimeMillis() - currentTimeMillis, str);
    }

    @TargetApi(8)
    public void loadUrl(String str, Map<String, String> map) {
        long currentTimeMillis = System.currentTimeMillis();
        if (str == null || showDebugView(str)) {
            return;
        }
        if (this.f17797c) {
            this.d.loadUrl(str, map);
        } else if (Build.VERSION.SDK_INT >= 8) {
            this.f17798e.loadUrl(str, map);
        }
        this.f17801h.a(System.currentTimeMillis() - currentTimeMillis, str);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f17802j || this.a == 0) {
            return;
        }
        j();
    }

    public boolean onLongClick(View view) {
        View.OnLongClickListener onLongClickListener = this.x;
        if (onLongClickListener == null || !onLongClickListener.onLongClick(view)) {
            return a(view);
        }
        return true;
    }

    public void onPause() {
        if (this.f17797c) {
            this.d.onPause();
        } else {
            com.tencent.smtt.utils.j.a(this.f17798e, "onPause");
        }
    }

    public void onResume() {
        if (this.f17797c) {
            this.d.onResume();
        } else {
            com.tencent.smtt.utils.j.a(this.f17798e, "onResume");
        }
    }

    @Override // android.view.View
    @TargetApi(11)
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (Build.VERSION.SDK_INT < 21 || !a(this.f17800g) || !isHardwareAccelerated() || i2 <= 0 || i3 <= 0) {
            return;
        }
        getLayerType();
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i2) {
        Context context = this.f17800g;
        if (context == null) {
            super.onVisibilityChanged(view, i2);
            return;
        }
        if (o == null) {
            o = context.getApplicationInfo().packageName;
        }
        String str = o;
        if (str != null && (str.equals("com.tencent.mm") || o.equals("com.tencent.mobileqq"))) {
            super.onVisibilityChanged(view, i2);
            return;
        }
        if (i2 != 0 && !this.f17802j && this.a != 0) {
            j();
        }
        super.onVisibilityChanged(view, i2);
    }

    public boolean overlayHorizontalScrollbar() {
        return !this.f17797c ? this.f17798e.overlayHorizontalScrollbar() : this.d.overlayHorizontalScrollbar();
    }

    public boolean overlayVerticalScrollbar() {
        return this.f17797c ? this.d.overlayVerticalScrollbar() : this.f17798e.overlayVerticalScrollbar();
    }

    public boolean pageDown(boolean z) {
        return !this.f17797c ? this.f17798e.pageDown(z) : this.d.pageDown(z, -1);
    }

    public boolean pageUp(boolean z) {
        return !this.f17797c ? this.f17798e.pageUp(z) : this.d.pageUp(z, -1);
    }

    public void pauseTimers() {
        if (this.f17797c) {
            this.d.pauseTimers();
        } else {
            this.f17798e.pauseTimers();
        }
    }

    @TargetApi(5)
    public void postUrl(String str, byte[] bArr) {
        if (this.f17797c) {
            this.d.postUrl(str, bArr);
        } else {
            this.f17798e.postUrl(str, bArr);
        }
    }

    @Deprecated
    public void refreshPlugins(boolean z) {
        if (this.f17797c) {
            this.d.refreshPlugins(z);
        } else {
            com.tencent.smtt.utils.j.a(this.f17798e, "refreshPlugins", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public void reload() {
        if (this.f17797c) {
            this.d.reload();
        } else {
            this.f17798e.reload();
        }
    }

    @TargetApi(11)
    public void removeJavascriptInterface(String str) {
        if (this.f17797c) {
            this.d.removeJavascriptInterface(str);
        } else if (Build.VERSION.SDK_INT >= 11) {
            com.tencent.smtt.utils.j.a(this.f17798e, "removeJavascriptInterface", new Class[]{String.class}, str);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (!this.f17797c) {
            this.f17798e.removeView(view);
            return;
        }
        View view2 = this.d.getView();
        try {
            Method a2 = com.tencent.smtt.utils.j.a(view2, "removeView", View.class);
            a2.setAccessible(true);
            a2.invoke(view2, view);
        } catch (Throwable unused) {
        }
    }

    @Deprecated
    public JSONObject reportInitPerformance(long j2, int i2, long j3, long j4) {
        return null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        if (!this.f17797c) {
            a aVar = this.f17798e;
            if (view == this) {
                view = aVar;
            }
            return aVar.requestChildRectangleOnScreen(view, rect, z);
        }
        View view2 = this.d.getView();
        if (view2 instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view2;
            if (view == this) {
                view = view2;
            }
            return viewGroup.requestChildRectangleOnScreen(view, rect, z);
        }
        return false;
    }

    public void requestFocusNodeHref(Message message) {
        if (this.f17797c) {
            this.d.requestFocusNodeHref(message);
        } else {
            this.f17798e.requestFocusNodeHref(message);
        }
    }

    public void requestImageRef(Message message) {
        if (this.f17797c) {
            this.d.requestImageRef(message);
        } else {
            this.f17798e.requestImageRef(message);
        }
    }

    @Deprecated
    public boolean restorePicture(Bundle bundle, File file) {
        if (this.f17797c) {
            return this.d.restorePicture(bundle, file);
        }
        Object a2 = com.tencent.smtt.utils.j.a(this.f17798e, "restorePicture", new Class[]{Bundle.class, File.class}, bundle, file);
        if (a2 == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public WebBackForwardList restoreState(Bundle bundle) {
        return !this.f17797c ? WebBackForwardList.a(this.f17798e.restoreState(bundle)) : WebBackForwardList.a(this.d.restoreState(bundle));
    }

    public void resumeTimers() {
        if (this.f17797c) {
            this.d.resumeTimers();
        } else {
            this.f17798e.resumeTimers();
        }
    }

    @Deprecated
    public void savePassword(String str, String str2, String str3) {
        if (this.f17797c) {
            this.d.savePassword(str, str2, str3);
        } else {
            com.tencent.smtt.utils.j.a(this.f17798e, "savePassword", new Class[]{String.class, String.class, String.class}, str, str2, str3);
        }
    }

    @Deprecated
    public boolean savePicture(Bundle bundle, File file) {
        if (this.f17797c) {
            return this.d.savePicture(bundle, file);
        }
        Object a2 = com.tencent.smtt.utils.j.a(this.f17798e, "savePicture", new Class[]{Bundle.class, File.class}, bundle, file);
        if (a2 == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public WebBackForwardList saveState(Bundle bundle) {
        return !this.f17797c ? WebBackForwardList.a(this.f17798e.saveState(bundle)) : WebBackForwardList.a(this.d.saveState(bundle));
    }

    @TargetApi(11)
    public void saveWebArchive(String str) {
        if (this.f17797c) {
            this.d.saveWebArchive(str);
        } else if (Build.VERSION.SDK_INT >= 11) {
            com.tencent.smtt.utils.j.a(this.f17798e, "saveWebArchive", new Class[]{String.class}, str);
        }
    }

    @TargetApi(11)
    public void saveWebArchive(String str, boolean z, ValueCallback<String> valueCallback) {
        if (this.f17797c) {
            this.d.saveWebArchive(str, z, valueCallback);
        } else if (Build.VERSION.SDK_INT >= 11) {
            com.tencent.smtt.utils.j.a(this.f17798e, "saveWebArchive", new Class[]{String.class, Boolean.TYPE, android.webkit.ValueCallback.class}, str, Boolean.valueOf(z), valueCallback);
        }
    }

    public void setARModeEnable(boolean z) {
        try {
            if (this.f17797c) {
                getSettingsExtension().setARModeEnable(z);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i2) {
        if (this.f17797c) {
            this.d.setBackgroundColor(i2);
        } else {
            this.f17798e.setBackgroundColor(i2);
        }
        super.setBackgroundColor(i2);
    }

    @Deprecated
    public void setCertificate(SslCertificate sslCertificate) {
        if (this.f17797c) {
            this.d.setCertificate(sslCertificate);
        } else {
            this.f17798e.setCertificate(sslCertificate);
        }
    }

    public void setDayOrNight(boolean z) {
        try {
            if (this.f17797c) {
                getSettingsExtension().setDayOrNight(z);
            }
            setSysDayOrNight(z);
            getView().postInvalidate();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setDownloadListener(final DownloadListener downloadListener) {
        boolean z = this.f17797c;
        if (z) {
            this.d.setDownloadListener(new b(this, downloadListener, z));
        } else {
            this.f17798e.setDownloadListener(new android.webkit.DownloadListener() { // from class: com.tencent.smtt.sdk.WebView.4
                {
                    WebView.this = this;
                }

                @Override // android.webkit.DownloadListener
                public void onDownloadStart(String str, String str2, String str3, String str4, long j2) {
                    DownloadListener downloadListener2 = downloadListener;
                    if (downloadListener2 != null) {
                        downloadListener2.onDownloadStart(str, str2, str3, str4, j2);
                        return;
                    }
                    ApplicationInfo applicationInfo = WebView.this.f17800g == null ? null : WebView.this.f17800g.getApplicationInfo();
                    if (applicationInfo == null || !"com.tencent.mm".equals(applicationInfo.packageName)) {
                        MttLoader.loadUrl(WebView.this.f17800g, str, null, null);
                    }
                }
            });
        }
    }

    @TargetApi(16)
    public void setFindListener(final IX5WebViewBase.FindListener findListener) {
        if (this.f17797c) {
            this.d.setFindListener(findListener);
        } else if (Build.VERSION.SDK_INT >= 16) {
            this.f17798e.setFindListener(new WebView.FindListener() { // from class: com.tencent.smtt.sdk.WebView.3
                {
                    WebView.this = this;
                }

                @Override // android.webkit.WebView.FindListener
                public void onFindResultReceived(int i2, int i3, boolean z) {
                    findListener.onFindResultReceived(i2, i3, z);
                }
            });
        }
    }

    public void setHorizontalScrollbarOverlay(boolean z) {
        if (this.f17797c) {
            this.d.setHorizontalScrollbarOverlay(z);
        } else {
            this.f17798e.setHorizontalScrollbarOverlay(z);
        }
    }

    public void setHttpAuthUsernamePassword(String str, String str2, String str3, String str4) {
        if (this.f17797c) {
            this.d.setHttpAuthUsernamePassword(str, str2, str3, str4);
        } else {
            this.f17798e.setHttpAuthUsernamePassword(str, str2, str3, str4);
        }
    }

    public void setInitialScale(int i2) {
        if (this.f17797c) {
            this.d.setInitialScale(i2);
        } else {
            this.f17798e.setInitialScale(i2);
        }
    }

    @Deprecated
    public void setMapTrackballToArrowKeys(boolean z) {
        if (this.f17797c) {
            this.d.setMapTrackballToArrowKeys(z);
        } else {
            com.tencent.smtt.utils.j.a(this.f17798e, "setMapTrackballToArrowKeys", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public void setNetworkAvailable(boolean z) {
        if (this.f17797c) {
            this.d.setNetworkAvailable(z);
        } else if (Build.VERSION.SDK_INT >= 3) {
            this.f17798e.setNetworkAvailable(z);
        }
    }

    @Override // android.view.View
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        if (!this.f17797c) {
            this.f17798e.setOnLongClickListener(onLongClickListener);
            return;
        }
        View view = this.d.getView();
        try {
            if (this.w == null) {
                Method a2 = com.tencent.smtt.utils.j.a(view, "getListenerInfo", new Class[0]);
                a2.setAccessible(true);
                Object invoke = a2.invoke(view, null);
                Field declaredField = invoke.getClass().getDeclaredField("mOnLongClickListener");
                declaredField.setAccessible(true);
                this.w = declaredField.get(invoke);
            }
            this.x = onLongClickListener;
            getView().setOnLongClickListener(this);
        } catch (Throwable unused) {
        }
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        getView().setOnTouchListener(onTouchListener);
    }

    @Deprecated
    public void setPictureListener(final PictureListener pictureListener) {
        if (this.f17797c) {
            if (pictureListener == null) {
                this.d.setPictureListener(null);
            } else {
                this.d.setPictureListener(new IX5WebViewBase.PictureListener() { // from class: com.tencent.smtt.sdk.WebView.6
                    {
                        WebView.this = this;
                    }

                    @Override // com.tencent.smtt.export.external.interfaces.IX5WebViewBase.PictureListener
                    public void onNewPicture(IX5WebViewBase iX5WebViewBase, Picture picture, boolean z) {
                        WebView.this.a(iX5WebViewBase);
                        pictureListener.onNewPicture(WebView.this, picture);
                    }

                    @Override // com.tencent.smtt.export.external.interfaces.IX5WebViewBase.PictureListener
                    public void onNewPictureIfHaveContent(IX5WebViewBase iX5WebViewBase, Picture picture) {
                    }
                });
            }
        } else if (pictureListener == null) {
            this.f17798e.setPictureListener(null);
        } else {
            this.f17798e.setPictureListener(new WebView.PictureListener() { // from class: com.tencent.smtt.sdk.WebView.5
                {
                    WebView.this = this;
                }

                @Override // android.webkit.WebView.PictureListener
                public void onNewPicture(android.webkit.WebView webView, Picture picture) {
                    WebView.this.a(webView);
                    pictureListener.onNewPicture(WebView.this, picture);
                }
            });
        }
    }

    public void setRendererPriorityPolicy(int i2, boolean z) {
        try {
            if (this.f17797c || Build.VERSION.SDK_INT < 26) {
                return;
            }
            com.tencent.smtt.utils.j.a(this.f17798e, "setRendererPriorityPolicy", new Class[]{Integer.TYPE, Boolean.TYPE}, Integer.valueOf(i2), Boolean.valueOf(z));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.view.View
    public void setScrollBarStyle(int i2) {
        if (this.f17797c) {
            this.d.getView().setScrollBarStyle(i2);
        } else {
            this.f17798e.setScrollBarStyle(i2);
        }
    }

    public void setSysNightModeAlpha(int i2) {
        NIGHT_MODE_ALPHA = i2;
    }

    public void setVerticalScrollbarOverlay(boolean z) {
        if (this.f17797c) {
            this.d.setVerticalScrollbarOverlay(z);
        } else {
            this.f17798e.setVerticalScrollbarOverlay(z);
        }
    }

    public boolean setVideoFullScreen(Context context, boolean z) {
        if (!context.getApplicationInfo().processName.contains("com.tencent.android.qqdownloader") || this.d == null) {
            return false;
        }
        Bundle bundle = new Bundle();
        if (z) {
            bundle.putInt("DefaultVideoScreen", 2);
        } else {
            bundle.putInt("DefaultVideoScreen", 1);
        }
        this.d.getX5WebViewExtension().invokeMiscMethod("setVideoParams", bundle);
        return true;
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        if (getView() == null) {
            return;
        }
        getView().setVisibility(i2);
    }

    public void setWebChromeClient(WebChromeClient webChromeClient) {
        a aVar;
        android.webkit.WebChromeClient webChromeClient2 = null;
        if (this.f17797c) {
            this.d.setWebChromeClient(webChromeClient != null ? new h(u.a().a(true).i(), this, webChromeClient) : null);
        } else {
            if (webChromeClient == null) {
                aVar = this.f17798e;
            } else if (a(webChromeClient)) {
                aVar = this.f17798e;
                webChromeClient2 = new c(this, webChromeClient);
            } else {
                aVar = this.f17798e;
                webChromeClient2 = new SystemWebChromeClient(this, webChromeClient);
            }
            aVar.setWebChromeClient(webChromeClient2);
        }
        this.f17805n = webChromeClient;
    }

    public void setWebChromeClientExtension(IX5WebChromeClientExtension iX5WebChromeClientExtension) {
        if (this.f17797c) {
            this.d.getX5WebViewExtension().setWebChromeClientExtension(iX5WebChromeClientExtension);
        }
    }

    public void setWebViewCallbackClient(WebViewCallbackClient webViewCallbackClient) {
        this.mWebViewCallbackClient = webViewCallbackClient;
        if (!this.f17797c || getX5WebViewExtension() == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("flag", true);
        getX5WebViewExtension().invokeMiscMethod("setWebViewCallbackClientFlag", bundle);
    }

    public void setWebViewClient(WebViewClient webViewClient) {
        if (this.f17797c) {
            this.d.setWebViewClient(webViewClient != null ? new i(u.a().a(true).j(), this, webViewClient) : null);
        } else {
            this.f17798e.setWebViewClient(webViewClient != null ? new SystemWebViewClient(this, webViewClient) : null);
        }
        this.f17804m = webViewClient;
    }

    public void setWebViewClientExtension(IX5WebViewClientExtension iX5WebViewClientExtension) {
        if (this.f17797c) {
            this.d.getX5WebViewExtension().setWebViewClientExtension(iX5WebViewClientExtension);
        }
    }

    @SuppressLint({"NewApi"})
    public boolean showDebugView(String str) {
        String lowerCase = str.toLowerCase();
        if (lowerCase.startsWith("https://debugtbs.qq.com")) {
            getView().setVisibility(4);
            com.tencent.smtt.utils.d.a(this.f17800g).a(lowerCase, this, this.f17800g, TbsHandlerThread.getInstance().getLooper());
            return true;
        } else if (!lowerCase.startsWith("https://debugx5.qq.com") || this.f17797c) {
            return false;
        } else {
            loadDataWithBaseURL(null, "<!DOCTYPE html><html><body><head><title>\u65e0\u6cd5\u6253\u5f00debugx5</title><meta name=\"viewport\" content=\"width=device-width, user-scalable=no\" /></head><br/><br /><h2>debugx5\u9875\u9762\u4ec5\u5728\u4f7f\u7528\u4e86X5\u5185\u6838\u65f6\u6709\u6548\uff0c\u7531\u4e8e\u5f53\u524d\u6ca1\u6709\u4f7f\u7528X5\u5185\u6838\uff0c\u65e0\u6cd5\u6253\u5f00debugx5\uff01</h2><br />\u5c1d\u8bd5<a href=\"https://debugtbs.qq.com?10000\">\u8fdb\u5165DebugTbs\u5b89\u88c5\u6216\u6253\u5f00X5\u5185\u6838</a></body></html>", MIMEType.MIME_TYPE_HTML, "utf-8", null);
            return true;
        }
    }

    public boolean showFindDialog(String str, boolean z) {
        return false;
    }

    public void stopLoading() {
        if (this.f17797c) {
            this.d.stopLoading();
        } else {
            this.f17798e.stopLoading();
        }
    }

    public void super_computeScroll() {
        if (!this.f17797c) {
            this.f17798e.a();
            return;
        }
        try {
            com.tencent.smtt.utils.j.a(this.d.getView(), "super_computeScroll");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean super_dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.f17797c) {
            try {
                Object a2 = com.tencent.smtt.utils.j.a(this.d.getView(), "super_dispatchTouchEvent", new Class[]{MotionEvent.class}, motionEvent);
                if (a2 == null) {
                    return false;
                }
                return ((Boolean) a2).booleanValue();
            } catch (Throwable unused) {
                return false;
            }
        }
        return this.f17798e.b(motionEvent);
    }

    public boolean super_onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f17797c) {
            try {
                Object a2 = com.tencent.smtt.utils.j.a(this.d.getView(), "super_onInterceptTouchEvent", new Class[]{MotionEvent.class}, motionEvent);
                if (a2 == null) {
                    return false;
                }
                return ((Boolean) a2).booleanValue();
            } catch (Throwable unused) {
                return false;
            }
        }
        return this.f17798e.c(motionEvent);
    }

    public void super_onOverScrolled(int i2, int i3, boolean z, boolean z2) {
        if (!this.f17797c) {
            this.f17798e.a(i2, i3, z, z2);
            return;
        }
        View view = this.d.getView();
        try {
            Class cls = Integer.TYPE;
            Class cls2 = Boolean.TYPE;
            com.tencent.smtt.utils.j.a(view, "super_onOverScrolled", new Class[]{cls, cls, cls2, cls2}, Integer.valueOf(i2), Integer.valueOf(i3), Boolean.valueOf(z), Boolean.valueOf(z2));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void super_onScrollChanged(int i2, int i3, int i4, int i5) {
        if (!this.f17797c) {
            this.f17798e.a(i2, i3, i4, i5);
            return;
        }
        View view = this.d.getView();
        try {
            Class cls = Integer.TYPE;
            com.tencent.smtt.utils.j.a(view, "super_onScrollChanged", new Class[]{cls, cls, cls, cls}, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean super_onTouchEvent(MotionEvent motionEvent) {
        if (this.f17797c) {
            try {
                Object a2 = com.tencent.smtt.utils.j.a(this.d.getView(), "super_onTouchEvent", new Class[]{MotionEvent.class}, motionEvent);
                if (a2 == null) {
                    return false;
                }
                return ((Boolean) a2).booleanValue();
            } catch (Throwable unused) {
                return false;
            }
        }
        return this.f17798e.a(motionEvent);
    }

    public boolean super_overScrollBy(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z) {
        if (this.f17797c) {
            View view = this.d.getView();
            try {
                Class cls = Integer.TYPE;
                Object a2 = com.tencent.smtt.utils.j.a(view, "super_overScrollBy", new Class[]{cls, cls, cls, cls, cls, cls, cls, cls, Boolean.TYPE}, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6), Integer.valueOf(i7), Integer.valueOf(i8), Integer.valueOf(i9), Boolean.valueOf(z));
                if (a2 == null) {
                    return false;
                }
                return ((Boolean) a2).booleanValue();
            } catch (Throwable unused) {
                return false;
            }
        }
        return this.f17798e.a(i2, i3, i4, i5, i6, i7, i8, i9, z);
    }

    public void switchNightMode(boolean z) {
        String str;
        if (z == v) {
            return;
        }
        v = z;
        if (z) {
            TbsLog.e("QB_SDK", "deleteNightMode");
            str = "javascript:document.getElementsByTagName('HEAD').item(0).removeChild(document.getElementById('QQBrowserSDKNightMode'));";
        } else {
            TbsLog.e("QB_SDK", "nightMode");
            str = "javascript:var style = document.createElement('style');style.type='text/css';style.id='QQBrowserSDKNightMode';style.innerHTML='html,body{background:none !important;background-color: #1d1e2a !important;}html *{background-color: #1d1e2a !important; color:#888888 !important;border-color:#3e4f61 !important;text-shadow:none !important;box-shadow:none !important;}a,a *{border-color:#4c5b99 !important; color:#2d69b3 !important;text-decoration:none !important;}a:visited,a:visited *{color:#a600a6 !important;}a:active,a:active *{color:#5588AA !important;}input,select,textarea,option,button{background-image:none !important;color:#AAAAAA !important;border-color:#4c5b99 !important;}form,div,button,span{background-color:#1d1e2a !important; border-color:#4c5b99 !important;}img{opacity:0.5}';document.getElementsByTagName('HEAD').item(0).appendChild(style);";
        }
        loadUrl(str);
    }

    public void switchToNightMode() {
        TbsLog.e("QB_SDK", "switchToNightMode 01");
        if (v) {
            return;
        }
        TbsLog.e("QB_SDK", "switchToNightMode");
        loadUrl("javascript:var style = document.createElement('style');style.type='text/css';style.id='QQBrowserSDKNightMode';style.innerHTML='html,body{background:none !important;background-color: #1d1e2a !important;}html *{background-color: #1d1e2a !important; color:#888888 !important;border-color:#3e4f61 !important;text-shadow:none !important;box-shadow:none !important;}a,a *{border-color:#4c5b99 !important; color:#2d69b3 !important;text-decoration:none !important;}a:visited,a:visited *{color:#a600a6 !important;}a:active,a:active *{color:#5588AA !important;}input,select,textarea,option,button{background-image:none !important;color:#AAAAAA !important;border-color:#4c5b99 !important;}form,div,button,span{background-color:#1d1e2a !important; border-color:#4c5b99 !important;}img{opacity:0.5}';document.getElementsByTagName('HEAD').item(0).appendChild(style);");
    }

    public boolean zoomIn() {
        return !this.f17797c ? this.f17798e.zoomIn() : this.d.zoomIn();
    }

    public boolean zoomOut() {
        return !this.f17797c ? this.f17798e.zoomOut() : this.d.zoomOut();
    }
}
