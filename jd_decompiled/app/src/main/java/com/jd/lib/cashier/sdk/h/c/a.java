package com.jd.lib.cashier.sdk.h.c;

import android.os.SystemClock;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.RecChannel;
import java.util.List;

/* loaded from: classes14.dex */
public class a extends com.jd.lib.cashier.sdk.core.aac.b {
    public String A;
    public String B;
    public int C;
    public String D;
    public boolean E;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;
    public long J;
    public CashierPayEntity K;
    public List<com.jd.lib.cashier.sdk.d.a.e.a> M;
    public List<com.jd.lib.cashier.sdk.d.a.e.a> N;
    public Payment O;
    public RecChannel P;
    public String R;
    public String S;
    public String T;
    public String V;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f3510c;

    /* renamed from: e  reason: collision with root package name */
    public String f3511e;

    /* renamed from: f  reason: collision with root package name */
    public String f3512f;

    /* renamed from: g  reason: collision with root package name */
    public String f3513g;

    /* renamed from: h  reason: collision with root package name */
    public String f3514h;

    /* renamed from: i  reason: collision with root package name */
    public String f3515i;

    /* renamed from: j  reason: collision with root package name */
    public String f3516j;

    /* renamed from: k  reason: collision with root package name */
    public String f3517k;

    /* renamed from: l  reason: collision with root package name */
    public String f3518l;

    /* renamed from: m  reason: collision with root package name */
    public List<String> f3519m;

    /* renamed from: n  reason: collision with root package name */
    public List<String> f3520n;
    public String o;
    public String p;
    public String q;
    public String r;
    public String s;
    public String t;
    public String u;
    public String v;
    public String w;
    public String x;
    public String y;
    public String z;
    public String d = "02";
    public b Q = new b();
    public String U = "";
    public String W = "";
    public boolean X = false;
    public String Y = "";
    public String Z = "";
    public String a0 = "";
    public String b0 = "";
    public com.jd.lib.cashier.sdk.h.d.a L = new com.jd.lib.cashier.sdk.h.d.a();

    @Override // com.jd.lib.cashier.sdk.core.aac.b
    public void b() {
    }

    public String c() {
        return this.X ? "popUp" : "normal";
    }

    public String d() {
        return e() ? this.f3512f : "";
    }

    public boolean e() {
        return !TextUtils.isEmpty(this.f3512f) && this.f3512f.split(DYConstants.DY_REGEX_COMMA).length >= 2;
    }

    public boolean f() {
        CashierPayEntity cashierPayEntity = this.K;
        return cashierPayEntity != null && TextUtils.equals(cashierPayEntity.orderDetailPopUpSwitch, "1");
    }

    public void g() {
        this.u = "";
        this.R = "";
        this.N = null;
        this.M = null;
        this.K = null;
        this.G = false;
        this.H = false;
        this.O = null;
        this.P = null;
        com.jd.lib.cashier.sdk.h.d.a aVar = this.L;
        if (aVar != null) {
            aVar.b();
        }
        this.J = SystemClock.elapsedRealtime();
        this.Q = new b();
    }
}
