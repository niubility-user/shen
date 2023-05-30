package com.jd.security.jdguard.d.d;

import android.content.Context;
import com.jd.security.jdguard.d.a;
import com.jd.security.jdguard.d.c.d;
import com.jd.security.jdguard.d.c.f;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes17.dex */
public class b {
    public f a;
    public Context b;

    /* renamed from: c */
    public d f6923c;
    public ScheduledExecutorService d;

    /* renamed from: e */
    public a.d f6924e;

    /* renamed from: f */
    public String f6925f;

    /* renamed from: com.jd.security.jdguard.d.d.b$b */
    /* loaded from: classes17.dex */
    public static class C0215b {
        private Context a = null;
        private f b = null;

        /* renamed from: c */
        private d f6926c = null;
        private ScheduledExecutorService d;

        /* renamed from: e */
        private a.d f6927e;

        /* renamed from: f */
        private String f6928f;

        public b g() {
            return new b(this);
        }

        public C0215b h(Context context) {
            this.a = context;
            return this;
        }

        public C0215b i(String str) {
            this.f6928f = str;
            return this;
        }

        public C0215b j(f fVar) {
            this.b = fVar;
            return this;
        }

        public C0215b k(d dVar) {
            this.f6926c = dVar;
            return this;
        }

        public C0215b l(ScheduledExecutorService scheduledExecutorService) {
            this.d = scheduledExecutorService;
            return this;
        }

        public C0215b m(a.d dVar) {
            this.f6927e = dVar;
            return this;
        }
    }

    private b(C0215b c0215b) {
        this.b = c0215b.a;
        this.a = c0215b.b;
        this.f6923c = c0215b.f6926c;
        this.d = c0215b.d;
        this.f6924e = c0215b.f6927e;
        this.f6925f = c0215b.f6928f;
    }
}
