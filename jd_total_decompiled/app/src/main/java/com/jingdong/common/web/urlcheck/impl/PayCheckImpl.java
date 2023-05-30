package com.jingdong.common.web.urlcheck.impl;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.javainterface.impl.WebJavaScript;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.urlcheck.ICheckUrl;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.tencent.smtt.sdk.WebView;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class PayCheckImpl extends BaseWebComponent implements ICheckUrl {
    public LocalBroadcastManager localBroadcastManager;
    private WxPayResultBroadCastReceiver mWxPayResultBroadCastReceiver;
    private String payHost;

    public PayCheckImpl(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.payHost = "communication";
    }

    private void sendException(String str, String str2) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("function", str);
            hashMap.put("errCode", "933");
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            hashMap.put("url", str2);
            hashMap.put("occurTime", ExceptionReporter.getCurrentMicrosecond());
            ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplicationContext(), hashMap);
        } catch (Exception unused) {
        }
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public boolean checkReturn() {
        return true;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public boolean checkUrl(WebView webView, String str) {
        String string;
        WebJavaScript webJavaScript;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (this.webUiBinder != null) {
            try {
                Object invoke = Class.forName("com.jingdong.common.utils.CashierGenPayIdDomainCollector").getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
                invoke.getClass().getMethod("preHandHistoryUrl", JDWebView.class, String.class).invoke(invoke, this.webUiBinder.getJdWebView(), str);
            } catch (Throwable unused) {
            }
        }
        if (WebViewHelper.isJdPayMatch(this.webUiBinder.getBaseActivity(), str)) {
            return true;
        }
        Uri parse = Uri.parse(str);
        if (!JumpUtil.isJdScheme(parse.getScheme()) && (webJavaScript = (WebJavaScript) this.webUiBinder.getJavaInterfaceObj(WebUiConstans.JavaInterfaceNames.WEBJAVASCRIPT)) != null) {
            webJavaScript.setPayCompleted(false);
        }
        if (JumpUtil.isJdScheme(parse.getScheme()) && this.payHost.equals(parse.getHost())) {
            String queryParameter = parse.getQueryParameter("params");
            if (!TextUtils.isEmpty(queryParameter)) {
                try {
                    JSONObject jSONObject = new JSONObject(queryParameter);
                    String string2 = jSONObject.getString("type");
                    try {
                        string = jSONObject.getString("payId");
                    } catch (JSONException unused2) {
                        string = jSONObject.getString("tokenKey");
                    }
                    if (!TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string)) {
                        this.webUiBinder.getWebEntity().payID = string;
                        registerWXResultReceiver(string2, string);
                        sendException("PayCheckImpl.checkUrl()", str);
                    }
                } catch (JSONException unused3) {
                }
            }
        }
        return false;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public String getKey() {
        return WebUiConstans.UrlCheckKeys.CHECK_PAY;
    }

    public void registerWXResultReceiver(String str, String str2) {
        if (this.localBroadcastManager == null) {
            this.localBroadcastManager = LocalBroadcastManager.getInstance(BaseApplication.getInstance().getBaseContext());
        }
        if (("10".equals(str) || "13".equals(str)) && !this.webUiBinder.getWebEntity().isRegister) {
            this.webUiBinder.getWebEntity().isRegister = true;
            this.mWxPayResultBroadCastReceiver = new WxPayResultBroadCastReceiver(this.webUiBinder);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.jd.wxPayResult");
            intentFilter.addAction("com.jd.QQPayResult");
            this.localBroadcastManager.registerReceiver(this.mWxPayResultBroadCastReceiver, intentFilter);
        }
    }

    public void sendBroadcastToPhoneCharge() {
        if (this.localBroadcastManager == null) {
            this.localBroadcastManager = LocalBroadcastManager.getInstance(BaseApplication.getInstance().getBaseContext());
        }
        Intent intent = new Intent();
        intent.setAction("pay_success");
        this.localBroadcastManager.sendBroadcast(intent);
    }

    public void unRegisterWXResultReceiver() {
        if (this.mWxPayResultBroadCastReceiver == null || this.localBroadcastManager == null) {
            return;
        }
        this.webUiBinder.getWebEntity().isRegister = false;
        this.localBroadcastManager.unregisterReceiver(this.mWxPayResultBroadCastReceiver);
    }
}
