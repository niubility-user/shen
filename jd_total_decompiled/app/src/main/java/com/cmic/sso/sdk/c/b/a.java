package com.cmic.sso.sdk.c.b;

import com.jingdong.common.bing.utils.JDBingConst;
import java.net.URLEncoder;

/* loaded from: classes.dex */
public abstract class a extends g {
    protected String a = "";
    protected String b = "";

    /* renamed from: c  reason: collision with root package name */
    protected String f989c = "";
    protected String d = "";

    /* renamed from: e  reason: collision with root package name */
    protected String f990e = "";

    /* renamed from: f  reason: collision with root package name */
    protected String f991f = "";

    /* renamed from: g  reason: collision with root package name */
    protected String f992g = "";

    /* renamed from: h  reason: collision with root package name */
    protected String f993h = "";

    /* renamed from: i  reason: collision with root package name */
    protected String f994i = "";

    /* renamed from: j  reason: collision with root package name */
    protected String f995j = "0";

    /* renamed from: k  reason: collision with root package name */
    protected String f996k = "1.0";

    /* renamed from: l  reason: collision with root package name */
    protected String f997l = "";

    /* renamed from: m  reason: collision with root package name */
    protected String f998m = "";

    /* renamed from: n  reason: collision with root package name */
    protected String f999n = "";
    protected String o = "";
    protected String p = "";
    protected String q = "";
    protected String r = "";
    protected String s = "";
    protected String t = "";
    protected String u = JDBingConst.NOMETHOD_ERROR_CODE;
    protected String v = "";
    protected String w = "";
    protected String x = "";

    public void a(String str) {
        this.v = str;
    }

    public void b(String str) {
        this.a = t(str);
    }

    public void c(String str) {
        this.b = t(str);
    }

    public void d(String str) {
        this.f989c = t(str);
    }

    public void e(String str) {
        this.f990e = t(str);
    }

    public void f(String str) {
        this.f991f = t(str);
    }

    public void g(String str) {
        this.f992g = URLEncoder.encode(t(str));
    }

    public void h(String str) {
        this.f993h = URLEncoder.encode(t(str));
    }

    public void i(String str) {
        this.f994i = URLEncoder.encode(t(str));
    }

    public void j(String str) {
        this.f995j = t(str);
    }

    public void k(String str) {
        this.f996k = t(str);
    }

    public void l(String str) {
        this.f998m = t(str);
    }

    public void m(String str) {
        this.f999n = t(str);
    }

    public void n(String str) {
        this.p = t(str);
    }

    public void o(String str) {
        this.q = t(str);
    }

    public void p(String str) {
        this.r = t(str);
    }

    public void q(String str) {
        this.s = t(str);
    }

    public void r(String str) {
        this.t = t(str);
    }

    public void s(String str) {
        this.w = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String t(String str) {
        return str == null ? "" : str;
    }

    public void u(String str) {
        this.x = str;
    }

    @Override // com.cmic.sso.sdk.c.b.g
    public String a() {
        return this.f989c;
    }
}
