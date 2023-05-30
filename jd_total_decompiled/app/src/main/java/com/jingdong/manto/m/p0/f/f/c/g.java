package com.jingdong.manto.m.p0.f.f.c;

import android.media.AudioRecord;
import com.jingdong.manto.m.p0.f.f.c.h;

/* loaded from: classes15.dex */
public interface g extends h {

    /* loaded from: classes15.dex */
    public static class a extends h.a implements g {
        private final int d;

        /* renamed from: e  reason: collision with root package name */
        private volatile boolean f13553e;

        public a(c cVar) {
            super(cVar);
            this.d = f();
        }

        @Override // com.jingdong.manto.m.p0.f.f.c.g
        public void a(boolean z) {
            this.f13553e = z;
        }

        @Override // com.jingdong.manto.m.p0.f.f.c.g
        public boolean a() {
            return this.f13553e;
        }

        @Override // com.jingdong.manto.m.p0.f.f.c.g
        public AudioRecord b() {
            AudioRecord d = d();
            d.startRecording();
            a(true);
            return d;
        }

        @Override // com.jingdong.manto.m.p0.f.f.c.g
        public int e() {
            return this.d;
        }
    }

    void a(boolean z);

    boolean a();

    AudioRecord b();

    int e();
}
