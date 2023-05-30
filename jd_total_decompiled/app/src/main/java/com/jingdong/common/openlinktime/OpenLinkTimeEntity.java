package com.jingdong.common.openlinktime;

import com.jingdong.jdsdk.constant.CartConstant;
import java.util.HashMap;

/* loaded from: classes.dex */
public class OpenLinkTimeEntity {
    public static final String EVENT_BACK = "\u540e\u53f0";
    public static final String TYPE_BABEL = "babel";
    public static final String TYPE_BABEL_NATIVE = "babelNative";
    public static final String TYPE_WEBVIEW = "webview";
    private String businessType;
    private String event;
    private String jumpUrl;
    private String url;
    private String isFinish = "0";
    private String isPrivacy = "0";
    private String isCold = "0";

    public void babel() {
        this.businessType = "babel";
        this.isFinish = "1";
    }

    public void babelNative() {
        this.businessType = TYPE_BABEL_NATIVE;
        this.isFinish = "1";
    }

    public HashMap<String, String> getReportMap() {
        HashMap<String, String> hashMap = new HashMap<>(16);
        hashMap.put("isFinish", this.isFinish);
        hashMap.put("event", this.event);
        hashMap.put("url", this.url);
        hashMap.put(CartConstant.KEY_JUMPURL, this.jumpUrl);
        hashMap.put("businessType", this.businessType);
        hashMap.put("isPrivacy", this.isPrivacy);
        hashMap.put("isCold", this.isCold);
        return hashMap;
    }

    public boolean isBabelType() {
        return "babel".equals(this.businessType);
    }

    public void isCold(boolean z) {
        this.isCold = z ? "1" : "0";
    }

    public void isFinish(boolean z) {
        this.isFinish = z ? "1" : "0";
    }

    public void isPrivacy(boolean z) {
        this.isPrivacy = z ? "1" : "0";
    }

    public boolean isWebViewType() {
        return "webview".equals(this.businessType);
    }

    public void setBusinessType(String str) {
        this.businessType = str;
    }

    public void setEvent(String str) {
        this.event = str;
    }

    public void setJumpUrl(String str) {
        this.jumpUrl = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void webview() {
        this.businessType = "webview";
        this.isFinish = "1";
    }
}
