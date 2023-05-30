package com.jd.libs.jdmbridge.base;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.jd.libs.jdmbridge.base.base.BaseBridgeAdapter;
import com.jd.libs.jdmbridge.base.base.IProxy;

/* loaded from: classes16.dex */
public class XWebViewBridge extends BaseBridgeAdapter implements IProxy {
    public XWebViewBridge(JDBridgeManager jDBridgeManager) {
        super(jDBridgeManager);
    }

    public void callback2Js(String str, String str2, String str3, Object obj, String str4) {
        JDBridgeManager jDBridgeManager = this.mBridgeManager;
        if (jDBridgeManager != null) {
            jDBridgeManager.callback2H5(str, str2, str3, obj, str4);
        }
    }

    @JavascriptInterface
    public void callNative(String str, String str2, String str3, final String str4, final String str5) {
        String.format("module:%s, action:%s, params:%s", str, str2, str3);
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            Class<? extends com.jd.xbridge.base.IBridgePlugin> plugin = JDBridgeManager.getPlugin(str);
            if (plugin == null) {
                callback2Js(str4, str5, "-2", null, "plugin not found");
                return;
            }
            try {
                JDBridgeManager jDBridgeManager = this.mBridgeManager;
                if (plugin.newInstance().execute(jDBridgeManager != null ? jDBridgeManager.getWebView() : null, str2, str3, new com.jd.xbridge.base.IBridgeCallback() { // from class: com.jd.libs.jdmbridge.base.XWebViewBridge.1
                    {
                        XWebViewBridge.this = this;
                    }

                    @Override // com.jd.xbridge.base.IBridgeCallback
                    public void onError(String str6) {
                        String str7 = str4;
                        if (str7 != null) {
                            XWebViewBridge.this.callback2Js(str7, str5, "-1", "", str6 == null ? "" : str6);
                        }
                        String.format("onError:%s", str6);
                    }

                    @Override // com.jd.xbridge.base.IBridgeCallback
                    public void onSuccess(Object obj) {
                        String str6 = str4;
                        if (str6 != null) {
                            XWebViewBridge.this.callback2Js(str6, str5, "0", obj, "");
                        }
                        String.format("success:%s", obj);
                    }
                }) || str4 == null) {
                    return;
                }
                callback2Js(str4, str5, "-2", "", "");
                return;
            } catch (Exception e2) {
                if (str4 != null) {
                    callback2Js(str4, str5, "1", "", "Plugin throw exception !");
                }
                e2.printStackTrace();
                String.format("exception:%s", e2.getMessage());
                return;
            }
        }
        callback2Js(str4, str5, "-2", null, "plugin not found");
    }

    @Override // com.jd.libs.jdmbridge.base.base.IProxy
    public String getName() {
        return "XWebView";
    }

    @Override // com.jd.libs.jdmbridge.base.base.BaseBridgeAdapter
    public IProxy getProxy() {
        return this;
    }
}
