package com.unionpay;

import android.app.Activity;
import android.os.Build;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.unionpay.tsmservice.data.Constant;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class WebViewJavascriptBridge implements Serializable {
    aa _messageHandler;
    Activity mContext;
    WebView mWebView;
    private boolean mAllowScheme = false;
    Map _messageHandlers = new HashMap();
    Map _responseCallbacks = new HashMap();
    long _uniqueId = 0;

    public WebViewJavascriptBridge(Activity activity, WebView webView, aa aaVar) {
        byte b = 0;
        this.mContext = activity;
        this.mWebView = webView;
        this._messageHandler = aaVar;
        WebSettings settings = this.mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setAllowFileAccess(false);
        settings.setDomStorageEnabled(true);
        try {
            if (Build.VERSION.SDK_INT >= 11) {
                this.mWebView.removeJavascriptInterface("accessibility");
                this.mWebView.removeJavascriptInterface("accessibilityTraversal");
                this.mWebView.removeJavascriptInterface("searchBoxJavaBridge_");
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.mWebView.addJavascriptInterface(this, "_WebViewJavascriptBridge");
        this.mWebView.setWebViewClient(new z(this, b));
        this.mWebView.setWebChromeClient(new y(this, b));
    }

    public void _callbackJs(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("responseId", str);
        hashMap.put(Constant.KEY_RESPONSE_DATA, str2);
        _dispatchMessage(hashMap);
    }

    private void _dispatchMessage(Map map) {
        String jSONObject = new JSONObject(map).toString();
        com.unionpay.utils.j.a("test", "sending:" + jSONObject);
        this.mContext.runOnUiThread(new w(this, String.format("javascript:WebViewJavascriptBridge._handleMessageFromJava('%s');", doubleEscapeString(jSONObject))));
    }

    private void _sendData(String str, ab abVar, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("data", str);
        if (abVar != null) {
            StringBuilder sb = new StringBuilder("java_cb_");
            long j2 = this._uniqueId + 1;
            this._uniqueId = j2;
            sb.append(j2);
            String sb2 = sb.toString();
            this._responseCallbacks.put(sb2, abVar);
            hashMap.put("callbackId", sb2);
        }
        if (str2 != null) {
            hashMap.put("handlerName", str2);
        }
        _dispatchMessage(hashMap);
    }

    public static String convertStreamToString(InputStream inputStream) {
        String str;
        str = "";
        try {
            Scanner useDelimiter = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
            str = useDelimiter.hasNext() ? useDelimiter.next() : "";
            inputStream.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return str;
    }

    private String doubleEscapeString(String str) {
        return str.replace("\\", "\\\\").replace("\"", "\\\"").replace("'", "\\'").replace(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, "\\n").replace("\r", "\\r").replace("\f", "\\f");
    }

    private void loadWebViewJavascriptBridgeJs(WebView webView) {
        webView.loadUrl("javascript:" + convertStreamToString(getClass().getResourceAsStream("res/webviewjavascriptbridge.js")));
    }

    @JavascriptInterface
    public void _handleMessageFromJs(String str, String str2, String str3, String str4, String str5) {
        aa aaVar;
        if (str2 != null) {
            ((ab) this._responseCallbacks.get(str2)).a(str3);
            this._responseCallbacks.remove(str2);
            return;
        }
        x xVar = str4 != null ? new x(this, str4) : null;
        if (str5 != null) {
            aaVar = (aa) this._messageHandlers.get(str5);
            if (aaVar == null) {
                com.unionpay.utils.j.c("test", "WVJB Warning: No handler for " + str5);
                return;
            }
        } else {
            aaVar = this._messageHandler;
        }
        try {
            this.mContext.runOnUiThread(new v(this, aaVar, str, xVar));
        } catch (Exception e2) {
            com.unionpay.utils.j.c("test", "WebViewJavascriptBridge: WARNING: java handler threw. " + e2.getMessage());
        }
    }

    public void callHandler(String str) {
        callHandler(str, null, null);
    }

    public void callHandler(String str, String str2) {
        callHandler(str, str2, null);
    }

    public void callHandler(String str, String str2, ab abVar) {
        _sendData(str2, abVar, str);
    }

    public void registerHandler(String str, aa aaVar) {
        this._messageHandlers.put(str, aaVar);
    }

    public void send(String str) {
        send(str, null);
    }

    public void send(String str, ab abVar) {
        _sendData(str, abVar, null);
    }

    public void setAllowScheme(boolean z) {
        this.mAllowScheme = z;
    }
}
