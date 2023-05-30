package com.jingdong.common.web.ui;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;

/* loaded from: classes12.dex */
public class JDWebViewBuilder {
    boolean autoSendReadyEvent;
    boolean canUseDarkMode;
    Context context;
    boolean enableHybrid;
    HashMap<String, Object> extraSetting;
    boolean hybridStarted;
    String hybridUrl;
    boolean isEncryptUaBlackUrl;
    boolean isNeedShare;
    boolean isPreRender;
    String loadingPlaceHolder;
    String offlineId;
    String preloadId;
    boolean shouldReportInitRecord = true;
    boolean showErrView;

    public JDWebViewBuilder(Context context) {
        this.context = context;
    }

    public JDWebViewBuilder addExtraSetting(String str, Object obj) {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        if (this.extraSetting == null) {
            this.extraSetting = new HashMap<>(1);
        }
        this.extraSetting.put(str, obj);
        return this;
    }

    public JDWebView build() {
        return new JDWebView(this.context, this);
    }

    public JDWebView preRenderBuild() {
        return new JDWebView(this.context, this);
    }

    public JDWebViewBuilder setAutoSendReadyEvent(boolean z) {
        this.autoSendReadyEvent = z;
        return this;
    }

    public JDWebViewBuilder setCanUseDarkMode(boolean z) {
        this.canUseDarkMode = z;
        return this;
    }

    public JDWebViewBuilder setEnableHybrid(boolean z) {
        this.enableHybrid = z;
        return this;
    }

    public JDWebViewBuilder setEncryptUABlackUrl(boolean z) {
        this.isEncryptUaBlackUrl = z;
        return this;
    }

    public JDWebViewBuilder setErrView(boolean z) {
        this.showErrView = z;
        return this;
    }

    public JDWebViewBuilder setHybridStarted(boolean z) {
        this.hybridStarted = z;
        return this;
    }

    public JDWebViewBuilder setHybridUrl(String str) {
        this.hybridUrl = str;
        return this;
    }

    public JDWebViewBuilder setIsNeedShare(boolean z) {
        this.isNeedShare = z;
        return this;
    }

    public JDWebViewBuilder setIsPreRender(boolean z) {
        this.isPreRender = z;
        return this;
    }

    public JDWebViewBuilder setLoadingBg(String str) {
        this.loadingPlaceHolder = str;
        return this;
    }

    public JDWebViewBuilder setOfflineId(String str) {
        this.offlineId = str;
        return this;
    }

    public JDWebViewBuilder setPreloadId(String str) {
        this.preloadId = str;
        return this;
    }

    public JDWebViewBuilder setShouldReportInitRecord(boolean z) {
        this.shouldReportInitRecord = z;
        return this;
    }

    public JDWebView build(JDWebView jDWebView) {
        this.isPreRender = false;
        if (jDWebView != null) {
            this.isPreRender = true;
            jDWebView.setJDWebViewBuilder(this);
            return jDWebView;
        }
        return new JDWebView(this.context, this);
    }
}
