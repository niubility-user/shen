package com.jingdong.app.mall.home.floor.view.b.g;

import android.text.TextUtils;
import com.jingdong.common.entity.JumpEntity;

/* loaded from: classes4.dex */
public class a {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f9761c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f9762e;

    /* renamed from: f  reason: collision with root package name */
    private JumpEntity f9763f;

    /* renamed from: g  reason: collision with root package name */
    private String f9764g;

    /* renamed from: h  reason: collision with root package name */
    private String[] f9765h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f9766i;

    /* renamed from: j  reason: collision with root package name */
    private String f9767j;

    /* renamed from: k  reason: collision with root package name */
    private String f9768k;

    /* renamed from: l  reason: collision with root package name */
    private String f9769l;

    public a(com.jingdong.app.mall.home.r.e.h hVar) {
        this.b = hVar.f10702n;
        this.f9761c = hVar.r;
        this.a = hVar.p;
        this.d = hVar.o;
        this.f9762e = hVar.s;
        this.f9763f = hVar.D;
        this.f9764g = hVar.t;
        this.f9765h = hVar.K;
        this.f9766i = "1".equals(hVar.H + "");
        this.f9767j = hVar.L;
        this.f9768k = hVar.J;
        this.f9769l = hVar.I;
    }

    public String a() {
        return this.f9769l;
    }

    public String b() {
        return this.f9768k;
    }

    public String[] c() {
        return this.f9765h;
    }

    public String d() {
        return this.f9767j;
    }

    public JumpEntity e() {
        return this.f9763f;
    }

    public String f() {
        return this.f9762e;
    }

    public String g() {
        return this.f9764g;
    }

    public String h() {
        return this.d;
    }

    public String i() {
        return this.b;
    }

    public String j() {
        return this.a;
    }

    public String k() {
        return this.f9761c;
    }

    public boolean l() {
        return this.f9766i;
    }

    public boolean m() {
        return !TextUtils.isEmpty(this.f9761c);
    }

    public void n(JumpEntity jumpEntity) {
        this.f9763f = jumpEntity;
    }

    public void o(String str) {
        this.f9761c = str;
    }

    public a() {
    }
}
