package com.jd.lib.un.business.widget;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class a {
    private static a p;
    private String a;
    private Application b;

    /* renamed from: c  reason: collision with root package name */
    private String f5864c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f5865e;

    /* renamed from: f  reason: collision with root package name */
    private String f5866f;

    /* renamed from: g  reason: collision with root package name */
    private String f5867g;

    /* renamed from: h  reason: collision with root package name */
    private String f5868h;

    /* renamed from: i  reason: collision with root package name */
    private String f5869i;

    /* renamed from: j  reason: collision with root package name */
    private String f5870j;

    /* renamed from: k  reason: collision with root package name */
    private Integer f5871k;

    /* renamed from: l  reason: collision with root package name */
    private String f5872l;

    /* renamed from: m  reason: collision with root package name */
    private String f5873m;

    /* renamed from: n  reason: collision with root package name */
    private InterfaceC0167a f5874n;
    private b o;

    /* renamed from: com.jd.lib.un.business.widget.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0167a {
        boolean enable();
    }

    private a() {
    }

    public static a g() {
        a aVar;
        a aVar2 = p;
        if (aVar2 != null) {
            return aVar2;
        }
        synchronized (a.class) {
            if (p == null) {
                p = new a();
            }
            aVar = p;
        }
        return aVar;
    }

    public String a() {
        return this.f5867g;
    }

    public String b() {
        return TextUtils.isEmpty(this.f5872l) ? "android" : this.f5872l;
    }

    public String c() {
        return TextUtils.isEmpty(this.f5873m) ? "1.0" : this.f5873m;
    }

    @Nullable
    public Context d() {
        Application application = this.b;
        if (application != null) {
            return application.getApplicationContext();
        }
        return null;
    }

    public String e() {
        return this.f5869i;
    }

    public String f() {
        return this.f5865e;
    }

    public Integer h() {
        Integer num = this.f5871k;
        return Integer.valueOf(num == null ? 100 : num.intValue());
    }

    public String i() {
        return this.f5866f;
    }

    public String j() {
        return this.f5870j;
    }

    public String k() {
        return this.a;
    }

    public String l() {
        return this.f5868h;
    }

    public String m() {
        return this.f5864c;
    }

    public String n() {
        return this.d;
    }

    public void o(Application application) {
        this.b = application;
    }

    public boolean p() {
        b bVar = this.o;
        if (bVar == null || bVar.isElderMode()) {
            return false;
        }
        return this.o.isDarkMode();
    }

    public boolean q() {
        b bVar = this.o;
        if (bVar == null) {
            return false;
        }
        return bVar.isElderMode();
    }

    public boolean r() {
        InterfaceC0167a interfaceC0167a = this.f5874n;
        if (interfaceC0167a != null) {
            return interfaceC0167a.enable();
        }
        return true;
    }

    public a s(b bVar) {
        this.o = bVar;
        return this;
    }

    public void t(InterfaceC0167a interfaceC0167a) {
        this.f5874n = interfaceC0167a;
    }

    public void u(String str) {
        this.a = str;
    }
}
