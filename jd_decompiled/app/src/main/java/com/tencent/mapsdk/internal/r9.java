package com.tencent.mapsdk.internal;

import com.tencent.mapsdk.internal.l9;
import com.tencent.mapsdk.internal.m9;

/* loaded from: classes9.dex */
public abstract class r9<D extends m9> extends k9<D> {

    /* renamed from: c  reason: collision with root package name */
    private static c f17197c = new a();
    private d b;

    /* loaded from: classes9.dex */
    public static class a implements c {
        @Override // com.tencent.mapsdk.internal.r9.c
        public String a(String str) {
            return p9.a(str);
        }
    }

    /* loaded from: classes9.dex */
    public enum b {
        DISK,
        DB
    }

    /* loaded from: classes9.dex */
    public interface c {
        String a(String str);
    }

    /* loaded from: classes9.dex */
    public static abstract class d implements l9.a {
        private b b;

        /* renamed from: c  reason: collision with root package name */
        private int f17199c = p.JCE_MAX_STRING_LENGTH;
        private c d = r9.f17197c;

        public d(b bVar) {
            this.b = bVar;
        }

        @Override // com.tencent.mapsdk.internal.l9.a
        public int a() {
            return this.f17199c;
        }

        public d a(int i2) {
            this.f17199c = i2;
            return this;
        }

        public d a(c cVar) {
            this.d = cVar;
            return this;
        }

        public b b() {
            return this.b;
        }

        public c c() {
            return this.d;
        }

        public String toString() {
            return "Options{mType=" + this.b + ", mCacheSize=" + this.f17199c + ", keyGenerator=" + this.d + '}';
        }
    }

    public r9(d dVar) {
        this.b = dVar;
    }

    public d i() {
        return this.b;
    }
}
