package com.jd.libs.jdmbridge.base;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.webkit.ValueCallback;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.libs.jdmbridge.base.base.IProxy;
import com.jd.libs.jdmbridge.base.callback.Native2JsCallback;
import com.jd.libs.jdmbridge.base.proxy.nav.NavBridgeAdapter;
import com.jd.libs.jdmbridge.base.proxy.share.ShareBridgeAdapter;
import com.jd.xbridge.XBridgeManager;
import com.jd.xbridge.base.IBridgeWebView;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes16.dex */
public class JDBridgeManager {
    public static final String SDK_VERSION = "3.0";
    public static final String TAG = "JDBridgeManager";
    private static Map<String, Class<? extends com.jd.xbridge.base.IBridgePlugin>> mFnMap = new HashMap();
    private IBridgeWebView mWebView;
    private Map<String, IProxy> mProxyMap = new HashMap();
    private Handler mMainHandler = new Handler(Looper.getMainLooper());

    public JDBridgeManager(IBridgeWebView iBridgeWebView) {
        init(iBridgeWebView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(String str, final Native2JsCallback native2JsCallback) {
        this.mWebView.evaluateJavascript(str, new ValueCallback() { // from class: com.jd.libs.jdmbridge.base.b
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                JDBridgeManager.e(Native2JsCallback.this, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void d(String str, final Native2JsCallback native2JsCallback) {
        IBridgeWebView iBridgeWebView = this.mWebView;
        if (iBridgeWebView != null) {
            iBridgeWebView.evaluateJavascript(str, new ValueCallback() { // from class: com.jd.libs.jdmbridge.base.d
                @Override // android.webkit.ValueCallback
                public final void onReceiveValue(Object obj) {
                    JDBridgeManager.f(Native2JsCallback.this, (String) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void e(Native2JsCallback native2JsCallback, String str) {
        if (native2JsCallback != null) {
            native2JsCallback.onJsResult(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void f(Native2JsCallback native2JsCallback, String str) {
        if (native2JsCallback != null) {
            native2JsCallback.onJsResult(str);
        }
    }

    public static Class<? extends com.jd.xbridge.base.IBridgePlugin> getPlugin(String str) {
        return mFnMap.get(str);
    }

    private void init(IBridgeWebView iBridgeWebView) {
        this.mWebView = iBridgeWebView;
        iBridgeWebView.addJavascriptInterface(new XWebViewBridge(this), "XWebView");
    }

    public static void registerBridgePlugin(String str, Class<? extends com.jd.xbridge.base.IBridgePlugin> cls) {
        mFnMap.put(str, cls);
        XBridgeManager.INSTANCE.registerPlugin(str, cls);
    }

    public void callback2H5(String str, String str2) {
        evaluateJs(str + ContainerUtils.FIELD_DELIMITER + str + "('" + str2 + "');");
    }

    public void evaluateJs(String str) {
        evaluateJs(str, null);
    }

    public IProxy getProxy(String str) {
        return this.mProxyMap.get(str);
    }

    public IBridgeWebView getWebView() {
        return this.mWebView;
    }

    public void post(Runnable runnable) {
        this.mMainHandler.post(runnable);
    }

    public void registerBridge(IProxy iProxy) {
        if (iProxy == null) {
            return;
        }
        String name = iProxy.getName();
        this.mProxyMap.put(name, iProxy);
        char c2 = '\uffff';
        try {
            int hashCode = name.hashCode();
            if (hashCode != -264591251) {
                if (hashCode == -127648888 && name.equals("MobileNavi")) {
                    c2 = 1;
                }
            } else if (name.equals("shareHelper")) {
                c2 = 0;
            }
            if (c2 == 0) {
                this.mWebView.addJavascriptInterface(new ShareBridgeAdapter(this), "shareHelper");
            } else if (c2 != 1) {
            } else {
                this.mWebView.addJavascriptInterface(new NavBridgeAdapter(this), "MobileNavi");
            }
        } catch (Throwable th) {
            String str = "registerBridge error: " + th.getMessage();
        }
    }

    public void callback2H5(String str, String str2, String str3, Object obj, String str4) {
        String format = String.format("%s && %s('%s')", str, str, StringUtils.stringfyJSonData(str3, obj, str4, str2));
        evaluateJs(format);
        String str5 = "evaluateJs:" + format;
    }

    public void evaluateJs(final String str, final Native2JsCallback native2JsCallback) {
        if (Build.VERSION.SDK_INT < 24 && (this.mWebView.getView().getHandler() == null || this.mWebView.getView().getHandler().getLooper() != Looper.getMainLooper())) {
            this.mMainHandler.post(new Runnable() { // from class: com.jd.libs.jdmbridge.base.c
                @Override // java.lang.Runnable
                public final void run() {
                    JDBridgeManager.this.d(str, native2JsCallback);
                }
            });
        } else {
            this.mWebView.getView().post(new Runnable() { // from class: com.jd.libs.jdmbridge.base.a
                @Override // java.lang.Runnable
                public final void run() {
                    JDBridgeManager.this.b(str, native2JsCallback);
                }
            });
        }
    }
}
