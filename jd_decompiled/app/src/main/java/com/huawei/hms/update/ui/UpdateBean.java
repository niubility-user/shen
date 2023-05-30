package com.huawei.hms.update.ui;

import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes12.dex */
public class UpdateBean implements Serializable {
    private boolean a;
    private boolean b;

    /* renamed from: c  reason: collision with root package name */
    private String f1502c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private String f1503e;

    /* renamed from: f  reason: collision with root package name */
    private String f1504f;

    /* renamed from: g  reason: collision with root package name */
    private ArrayList f1505g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f1506h = true;

    private static <T> T a(T t) {
        return t;
    }

    public String getClientAppId() {
        return (String) a(this.f1503e);
    }

    public String getClientAppName() {
        return (String) a(this.f1504f);
    }

    public String getClientPackageName() {
        return (String) a(this.f1502c);
    }

    public int getClientVersionCode() {
        return ((Integer) a(Integer.valueOf(this.d))).intValue();
    }

    public boolean getResolutionInstallHMS() {
        return this.b;
    }

    public ArrayList getTypeList() {
        return (ArrayList) a(this.f1505g);
    }

    public boolean isHmsOrApkUpgrade() {
        return ((Boolean) a(Boolean.valueOf(this.a))).booleanValue();
    }

    public boolean isNeedConfirm() {
        return ((Boolean) a(Boolean.valueOf(this.f1506h))).booleanValue();
    }

    public void setClientAppId(String str) {
        this.f1503e = str;
    }

    public void setClientAppName(String str) {
        this.f1504f = str;
    }

    public void setClientPackageName(String str) {
        this.f1502c = str;
    }

    public void setClientVersionCode(int i2) {
        this.d = i2;
    }

    public void setHmsOrApkUpgrade(boolean z) {
        this.a = z;
    }

    public void setNeedConfirm(boolean z) {
        this.f1506h = z;
    }

    public void setResolutionInstallHMS(boolean z) {
        this.b = z;
    }

    public void setTypeList(ArrayList arrayList) {
        this.f1505g = arrayList;
    }
}
