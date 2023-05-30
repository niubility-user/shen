package com.jingdong.common.web.javainterface.impl;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.openlinktime.OpenLinkTimeManager;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.common.web.managers.WebPerformance;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.sdk.log.Log;

/* loaded from: classes12.dex */
public class Performance implements IJavaInterface {
    private static final String TAG = "Performance";
    public static long pageStartLoadTime;
    private JDWebView mJdWebView;
    IWebUiBinder mWebUiBinder;
    private boolean renderReceived = false;
    private boolean timingReceived = false;

    public Performance(IWebUiBinder iWebUiBinder) {
        this.mWebUiBinder = iWebUiBinder;
        this.mJdWebView = iWebUiBinder.getJdWebView();
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return WebUiConstans.JavaInterfaceNames.PERFORMANCE;
    }

    public void onPageStarted() {
        this.renderReceived = false;
        this.timingReceived = false;
    }

    @JavascriptInterface
    public void sendResource(String str, String str2, String str3, String str4, String str5, String str6) {
        JDWebView jDWebView;
        Log.d(TAG, "[sendResource] --> lcp: " + str6 + "  resource: " + str4);
        JDWebView jDWebView2 = this.mJdWebView;
        if (jDWebView2 != null) {
            com.jd.libs.xdog.b.j(jDWebView2.getDogId(), "data", WebPerfManager.LCP, str6);
            com.jd.libs.xdog.b.l(this.mJdWebView.getDogId(), str2);
        }
        if (!TextUtils.isEmpty(str5)) {
            JDJSONArray parseArray = JDJSON.parseArray(str5);
            if (parseArray == null) {
                return;
            }
            for (int i2 = 0; i2 < parseArray.size(); i2++) {
                JDJSONObject jSONObject = parseArray.getJSONObject(i2);
                String valueOf = String.valueOf(jSONObject.getDoubleValue("startTime"));
                if ("first-contentful-paint".equals(jSONObject.getString("name"))) {
                    JDWebView jDWebView3 = this.mJdWebView;
                    if (jDWebView3 != null) {
                        com.jd.libs.xdog.b.j(jDWebView3.getDogId(), "data", WebPerfManager.FCP, valueOf);
                    }
                } else if ("first-paint".equals(jSONObject.getString("name")) && (jDWebView = this.mJdWebView) != null) {
                    com.jd.libs.xdog.b.j(jDWebView.getDogId(), "data", WebPerfManager.FP, valueOf);
                }
            }
        }
        JDWebView jDWebView4 = this.mJdWebView;
        WebPerformance record = (jDWebView4 == null || jDWebView4.getPerformanceHolder() == null) ? null : this.mJdWebView.getPerformanceHolder().getRecord(Long.parseLong(str));
        if (record == null || record.isReported()) {
            return;
        }
        record.appendData("timing", str2);
        if (!TextUtils.isEmpty(str3)) {
            record.appendData(WebPerfManager.MEMORY, str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            record.appendData(WebPerfManager.RESOURCE_TIMING_TMP, str4);
        }
        record.appendData(WebPerfManager.PAINT, str5);
        if (!TextUtils.isEmpty(str6)) {
            record.appendData(WebPerfManager.LCP, str6);
        }
        this.timingReceived = true;
        if (this.renderReceived) {
            if (this.mJdWebView.isPreRender() && this.mJdWebView.getXRenderManager() != null && this.mJdWebView.isPageFinished() && !this.mJdWebView.isAttached()) {
                if (this.mJdWebView.getXRenderManager().isPerformanceReported()) {
                    return;
                }
                this.mJdWebView.getPerformanceHolder().reportRecordBefore(record, true, true);
                return;
            }
            this.mJdWebView.getPerformanceHolder().reportRecordBefore(record, true);
        }
    }

    public void setWebView(JDWebView jDWebView) {
        this.mJdWebView = jDWebView;
    }

    @JavascriptInterface
    public void transferRenderTime(String str) {
        Log.d(TAG, "[transferRenderTime] --> renderTime = " + str);
        JDWebView jDWebView = this.mJdWebView;
        if (jDWebView != null && jDWebView.getPerformanceCallback() != null) {
            this.mJdWebView.getPerformanceCallback().onRender(str);
        }
        JDWebView jDWebView2 = this.mJdWebView;
        WebPerformance webPerformance = null;
        Object extraSetting = jDWebView2 != null ? jDWebView2.getExtraSetting(OpenAppJumpController.KEY_OPEN_LINK) : null;
        if ((extraSetting instanceof Boolean) && ((Boolean) extraSetting).booleanValue()) {
            JDWebView jDWebView3 = this.mJdWebView;
            String finalUrl = jDWebView3 != null ? jDWebView3.getFinalUrl() : null;
            OpenLinkTimeManager openLinkTimeManager = OpenLinkTimeManager.getInstance();
            JDWebView jDWebView4 = this.mJdWebView;
            openLinkTimeManager.webviewRender(finalUrl, jDWebView4 != null ? jDWebView4.getMtaSequence() : 0);
        }
        JDWebView jDWebView5 = this.mJdWebView;
        if (jDWebView5 != null && jDWebView5.getPerformanceHolder() != null) {
            webPerformance = this.mJdWebView.getPerformanceHolder().getCurrentRecord();
        }
        if (webPerformance == null || webPerformance.isReported()) {
            return;
        }
        webPerformance.appendData(WebPerfManager.RENDER, str);
        this.renderReceived = true;
        if (this.timingReceived) {
            this.mJdWebView.getPerformanceHolder().appendExtraToCurrRecord("transferRenderTime", Long.valueOf(System.currentTimeMillis()));
            if (this.mJdWebView.isPreRender() && this.mJdWebView.getXRenderManager() != null && this.mJdWebView.isPageFinished() && !this.mJdWebView.isAttached()) {
                if (this.mJdWebView.getXRenderManager() == null || this.mJdWebView.getXRenderManager().isPerformanceReported()) {
                    return;
                }
                this.mJdWebView.getPerformanceHolder().reportRecordBefore(webPerformance, true, true);
                return;
            }
            this.mJdWebView.getPerformanceHolder().reportRecordBefore(webPerformance, true);
        }
    }

    public Performance() {
    }
}
