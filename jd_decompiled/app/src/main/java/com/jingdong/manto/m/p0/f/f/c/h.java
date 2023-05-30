package com.jingdong.manto.m.p0.f.f.c;

import android.media.AudioRecord;

/* loaded from: classes15.dex */
interface h {

    /* loaded from: classes15.dex */
    public static class a implements h {
        private final AudioRecord a;
        private final c b;

        /* renamed from: c  reason: collision with root package name */
        private final int f13554c;

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(c cVar) {
            this.b = cVar;
            int a = new d(cVar).a();
            this.f13554c = a;
            this.a = new AudioRecord(cVar.c(), cVar.e(), cVar.a(), cVar.d(), a);
        }

        @Override // com.jingdong.manto.m.p0.f.f.c.h
        public c c() {
            return this.b;
        }

        @Override // com.jingdong.manto.m.p0.f.f.c.h
        public AudioRecord d() {
            return this.a;
        }

        public int f() {
            return this.f13554c;
        }
    }

    c c();

    AudioRecord d();
}
