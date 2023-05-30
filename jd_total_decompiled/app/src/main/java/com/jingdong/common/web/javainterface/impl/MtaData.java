package com.jingdong.common.web.javainterface.impl;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.openlinktime.OpenLinkTimeManager;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.log.Log;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes12.dex */
public final class MtaData {
    private String cpsMtaData;
    private WebView webView;
    private final String TAG = MtaData.class.getSimpleName();
    private String mtaData = null;
    private int sequence = 0;
    private boolean mtaDataInjected = false;
    private Handler mtaHandler = null;
    private Runnable mtaRunnable = null;

    public MtaData(WebView webView) {
        this.webView = null;
        this.webView = webView;
    }

    @JavascriptInterface
    public String getCpsMtaData() {
        String str = this.cpsMtaData;
        if (str == null) {
            str = "";
        }
        this.cpsMtaData = null;
        return str;
    }

    @JavascriptInterface
    public String getMtaData() {
        if (TextUtils.isEmpty(this.mtaData)) {
            ExceptionReporter.reportWebViewCommonError("MtaData_Error", "", "Mta data is null or empty", "");
        }
        String str = this.mtaData;
        return str != null ? str : "";
    }

    public String getName() {
        return WebUiConstans.JavaInterfaceNames.MTADATA;
    }

    public int getSequence() {
        return this.sequence;
    }

    public void injectMtaData() {
        if (this.mtaRunnable == null) {
            this.mtaRunnable = new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.MtaData.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (MtaData.this.webView != null) {
                            WebView webView = MtaData.this.webView;
                            StringBuilder sb = new StringBuilder();
                            sb.append("window.jdWebViewMtaData='");
                            sb.append(MtaData.this.mtaData == null ? "" : MtaData.this.mtaData);
                            sb.append("'");
                            webView.evaluateJavascript(sb.toString(), null);
                            MtaData.this.mtaDataInjected = true;
                            Log.d(MtaData.this.TAG, "Inject MTA data: " + MtaData.this.mtaData);
                            return;
                        }
                        Log.d(MtaData.this.TAG, "webview is null when injecting MTA data.");
                    } catch (Exception e2) {
                        Log.e(MtaData.this.TAG, e2.getMessage(), e2);
                    }
                }
            };
        }
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            this.mtaRunnable.run();
            return;
        }
        if (this.mtaHandler == null) {
            this.mtaHandler = new Handler(Looper.getMainLooper());
        }
        this.mtaHandler.post(this.mtaRunnable);
    }

    public boolean isMtaDataInjected() {
        return this.mtaDataInjected;
    }

    public void onDestroy() {
        this.webView = null;
        this.mtaRunnable = null;
        Handler handler = this.mtaHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public String parseAndGetCpsMtaData(Bundle bundle) {
        if (bundle != null) {
            try {
                int i2 = bundle.getInt(OpenAppJumpController.KEY_OPEN_LINK_PARAMS, 0);
                this.sequence = i2;
                JDJSONObject openJsonParam = OpenLinkTimeManager.getInstance().getOpenJsonParam(i2);
                return openJsonParam != null ? openJsonParam.toJSONString() : "";
            } catch (Throwable unused) {
                return "";
            }
        }
        return "";
    }

    public void setCpsMtaData(String str) {
        this.cpsMtaData = str;
    }

    public void setMtaData(String str) {
        this.mtaData = str;
    }

    public void setMtaDataInjected(boolean z) {
        this.mtaDataInjected = z;
    }
}
