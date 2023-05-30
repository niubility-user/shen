package com.jd.fireeye.a;

import android.content.Context;

/* loaded from: classes13.dex */
public class b {
    private C0078b a;

    /* renamed from: com.jd.fireeye.a.b$b  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public static class C0078b {
        private Context a;
        private boolean b = false;

        public C0078b a(Context context) {
            this.a = context;
            return this;
        }

        public Context b() {
            return this.a;
        }

        public boolean c() {
            return this.b;
        }

        public C0078b a(boolean z) {
            this.b = z;
            return this;
        }

        public b a() {
            return new b(this);
        }
    }

    public C0078b a() {
        C0078b c0078b = new C0078b();
        this.a = c0078b;
        return c0078b;
    }

    public Context b() {
        return this.a.b();
    }

    public boolean c() {
        return this.a.c();
    }

    public b() {
    }

    private b(C0078b c0078b) {
        if (c0078b == null) {
            return;
        }
        this.a = c0078b;
    }
}
