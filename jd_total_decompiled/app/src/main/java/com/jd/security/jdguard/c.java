package com.jd.security.jdguard;

import android.content.Context;
import com.jd.security.jdguard.core.d;
import com.jd.security.jdguard.d.c.e;
import java.util.HashMap;

/* loaded from: classes.dex */
public class c {
    private Context a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f6879c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f6880e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f6881f;

    /* renamed from: g  reason: collision with root package name */
    private int f6882g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f6883h;

    /* renamed from: i  reason: collision with root package name */
    private InterfaceC0212c f6884i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f6885j;

    /* loaded from: classes.dex */
    public static class b {
        private Context a;
        private String b;

        /* renamed from: c  reason: collision with root package name */
        private String f6886c;
        private String d;

        /* renamed from: e  reason: collision with root package name */
        private int f6887e = 20;

        /* renamed from: f  reason: collision with root package name */
        private boolean f6888f = true;

        /* renamed from: g  reason: collision with root package name */
        private boolean f6889g = false;

        /* renamed from: h  reason: collision with root package name */
        private boolean f6890h = false;

        /* renamed from: i  reason: collision with root package name */
        private boolean f6891i = false;

        /* renamed from: j  reason: collision with root package name */
        private boolean f6892j = false;

        /* renamed from: k  reason: collision with root package name */
        private boolean f6893k = false;

        /* renamed from: l  reason: collision with root package name */
        private InterfaceC0212c f6894l;

        public b m(String str) {
            this.b = str;
            return this;
        }

        public c n() {
            return new c(this);
        }

        public b o(InterfaceC0212c interfaceC0212c) {
            this.f6894l = interfaceC0212c;
            return this;
        }

        public b p(Context context) {
            this.a = context;
            return this;
        }

        public b q(boolean z) {
            this.f6888f = z;
            return this;
        }

        public b r(boolean z) {
            this.f6889g = z;
            return this;
        }

        public b s(int i2) {
            this.f6887e = i2;
            return this;
        }

        public b t(boolean z) {
            this.f6893k = z;
            return this;
        }

        public b u(String str) {
            this.f6886c = str;
            return this;
        }

        public b v(String str) {
            this.d = str;
            return this;
        }
    }

    /* renamed from: com.jd.security.jdguard.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0212c extends e, d {
        String getDfpEid();

        void onSendStreamData(HashMap<String, String> hashMap, String str, String str2, int i2);
    }

    public boolean a() {
        return this.f6880e;
    }

    public boolean b() {
        return this.f6881f;
    }

    public int c() {
        return this.f6882g;
    }

    public String d() {
        return this.b;
    }

    public InterfaceC0212c e() {
        return this.f6884i;
    }

    public Context f() {
        return this.a;
    }

    public String g() {
        return this.f6879c;
    }

    public String h() {
        return this.d;
    }

    public boolean i() {
        return this.f6883h;
    }

    public boolean j() {
        return this.f6885j;
    }

    private c(b bVar) {
        this.a = bVar.a;
        this.b = bVar.b;
        this.f6879c = bVar.f6886c;
        this.d = bVar.d;
        this.f6880e = bVar.f6888f;
        this.f6881f = bVar.f6889g;
        boolean unused = bVar.f6890h;
        this.f6882g = bVar.f6887e;
        this.f6883h = bVar.f6891i;
        this.f6884i = bVar.f6894l;
        boolean unused2 = bVar.f6892j;
        this.f6885j = bVar.f6893k;
    }
}
