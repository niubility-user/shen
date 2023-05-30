package com.huawei.hms.common.internal;

import android.app.Activity;
import com.huawei.hms.support.api.client.SubAppInfo;
import com.huawei.hms.support.api.entity.auth.Scope;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes12.dex */
public class ClientSettings {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private List<Scope> f1263c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private List<String> f1264e;

    /* renamed from: f  reason: collision with root package name */
    private String f1265f;

    /* renamed from: g  reason: collision with root package name */
    private SubAppInfo f1266g;

    /* renamed from: h  reason: collision with root package name */
    private WeakReference<Activity> f1267h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f1268i;

    /* renamed from: j  reason: collision with root package name */
    private String f1269j;

    /* renamed from: k  reason: collision with root package name */
    private boolean f1270k;

    public ClientSettings(String str, String str2, List<Scope> list, String str3, List<String> list2) {
        this.a = str;
        this.b = str2;
        this.f1263c = list;
        this.d = str3;
        this.f1264e = list2;
    }

    public List<String> getApiName() {
        return this.f1264e;
    }

    public String getAppID() {
        return this.d;
    }

    public String getClientClassName() {
        return this.b;
    }

    public String getClientPackageName() {
        return this.a;
    }

    public Activity getCpActivity() {
        WeakReference<Activity> weakReference = this.f1267h;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public String getCpID() {
        return this.f1265f;
    }

    public String getInnerHmsPkg() {
        return this.f1269j;
    }

    public List<Scope> getScopes() {
        return this.f1263c;
    }

    public SubAppInfo getSubAppID() {
        return this.f1266g;
    }

    public boolean isHasActivity() {
        return this.f1268i;
    }

    public boolean isUseInnerHms() {
        return this.f1270k;
    }

    public void setApiName(List<String> list) {
        this.f1264e = list;
    }

    public void setAppID(String str) {
        this.d = str;
    }

    public void setClientClassName(String str) {
        this.b = str;
    }

    public void setClientPackageName(String str) {
        this.a = str;
    }

    public void setCpActivity(Activity activity) {
        this.f1267h = new WeakReference<>(activity);
        this.f1268i = true;
    }

    public void setCpID(String str) {
        this.f1265f = str;
    }

    public void setInnerHmsPkg(String str) {
        this.f1269j = str;
    }

    public void setScopes(List<Scope> list) {
        this.f1263c = list;
    }

    public void setSubAppId(SubAppInfo subAppInfo) {
        this.f1266g = subAppInfo;
    }

    public void setUseInnerHms(boolean z) {
        this.f1270k = z;
    }

    public ClientSettings(String str, String str2, List<Scope> list, String str3, List<String> list2, SubAppInfo subAppInfo) {
        this(str, str2, list, str3, list2);
        this.f1266g = subAppInfo;
    }
}
