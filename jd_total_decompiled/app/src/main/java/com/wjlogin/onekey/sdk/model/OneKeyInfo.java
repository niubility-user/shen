package com.wjlogin.onekey.sdk.model;

/* loaded from: classes.dex */
public class OneKeyInfo {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f18363c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f18364e;

    /* renamed from: f  reason: collision with root package name */
    private String f18365f;

    public String getCmAppId() {
        return this.a;
    }

    public String getCmAppKey() {
        return this.b;
    }

    public String getCtAppId() {
        return this.f18363c;
    }

    public String getCtAppSecret() {
        return this.d;
    }

    public String getCuClientId() {
        return this.f18364e;
    }

    public String getCuClientSecret() {
        return this.f18365f;
    }

    public void setCmAppId(String str) {
        this.a = str;
    }

    public void setCmAppKey(String str) {
        this.b = str;
    }

    public void setCtAppId(String str) {
        this.f18363c = str;
    }

    public void setCtAppSecret(String str) {
        this.d = str;
    }

    public void setCuClientId(String str) {
        this.f18364e = str;
    }

    public void setCuClientSecret(String str) {
        this.f18365f = str;
    }
}
