package com.huawei.hms.hatool;

import java.util.Map;

/* loaded from: classes12.dex */
public class s0 {
    private boolean a;
    private boolean b;

    /* renamed from: c  reason: collision with root package name */
    private String f1404c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f1405e;

    /* renamed from: f  reason: collision with root package name */
    private String f1406f;

    /* renamed from: g  reason: collision with root package name */
    private j0 f1407g;

    /* renamed from: h  reason: collision with root package name */
    private String f1408h;

    /* renamed from: i  reason: collision with root package name */
    private Map<String, String> f1409i;

    /* renamed from: j  reason: collision with root package name */
    private String f1410j;

    /* renamed from: k  reason: collision with root package name */
    private int f1411k;

    /* renamed from: l  reason: collision with root package name */
    private int f1412l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f1413m;

    /* renamed from: n  reason: collision with root package name */
    private boolean f1414n;
    private boolean o;
    private String p;
    private long q;

    public s0() {
        this.f1405e = "";
        this.f1406f = "";
        this.f1407g = new j0();
        this.f1408h = "";
        this.f1410j = "";
        this.f1411k = 10;
        this.f1412l = 7;
        this.f1413m = true;
        this.f1414n = true;
        this.o = false;
        this.q = 0L;
    }

    public s0(s0 s0Var) {
        this.f1405e = "";
        this.f1406f = "";
        this.f1407g = new j0();
        this.f1408h = "";
        this.f1410j = "";
        this.f1411k = 10;
        this.f1412l = 7;
        this.f1413m = true;
        this.f1414n = true;
        this.o = false;
        this.q = 0L;
        this.f1407g = s0Var.f1407g;
        b(s0Var.a);
        a(s0Var.f1404c);
        b(s0Var.d);
        e(s0Var.f1405e);
        g(s0Var.f1406f);
        d(s0Var.f1408h);
        f(s0Var.f1410j);
        c(s0Var.b);
        a(s0Var.f1411k);
        b(s0Var.f1412l);
        d(s0Var.f1413m);
        a(s0Var.f1414n);
        e(s0Var.o);
        a(s0Var.f1409i);
        c(s0Var.p);
        a(s0Var.q);
    }

    public void a(int i2) {
        this.f1411k = i2;
    }

    public void a(long j2) {
        this.q = j2;
    }

    public void a(String str) {
        this.f1404c = str;
    }

    public void a(Map<String, String> map) {
        this.f1409i = map;
    }

    public void a(boolean z) {
        this.f1414n = z;
    }

    public boolean a() {
        return this.f1414n;
    }

    public int b() {
        return this.f1411k;
    }

    public void b(int i2) {
        this.f1412l = i2;
    }

    public void b(String str) {
        this.d = str;
    }

    public void b(boolean z) {
        this.a = z;
    }

    public void c(String str) {
        this.p = str;
    }

    public void c(boolean z) {
        this.b = z;
    }

    public boolean c() {
        return this.a;
    }

    public int d() {
        return this.f1412l;
    }

    public void d(String str) {
        this.f1408h = str;
    }

    public void d(boolean z) {
        this.f1413m = z;
    }

    public void e(String str) {
        this.f1405e = str;
    }

    public void e(boolean z) {
        this.o = z;
    }

    public boolean e() {
        return this.b;
    }

    public String f() {
        return this.f1404c;
    }

    public void f(String str) {
        this.f1410j = str;
    }

    public void g(String str) {
        this.f1406f = str;
    }

    public boolean g() {
        return this.f1413m;
    }

    public String h() {
        return this.d;
    }

    public boolean i() {
        return this.o;
    }

    public j0 j() {
        return this.f1407g;
    }

    public Map<String, String> k() {
        return this.f1409i;
    }

    public long l() {
        return this.q;
    }

    public String m() {
        return this.p;
    }

    public String n() {
        return this.f1408h;
    }

    public String o() {
        return this.f1405e;
    }

    public String p() {
        return this.f1410j;
    }

    public String q() {
        return this.f1406f;
    }
}
