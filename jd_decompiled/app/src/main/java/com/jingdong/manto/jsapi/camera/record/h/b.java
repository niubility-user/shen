package com.jingdong.manto.jsapi.camera.record.h;

import android.graphics.Bitmap;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.jingdong.manto.jsapi.camera.record.b;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes15.dex */
public class b implements c {
    private com.jingdong.manto.jsapi.camera.record.h.a a;

    /* loaded from: classes15.dex */
    class a implements b.f {
        a() {
        }

        @Override // com.jingdong.manto.jsapi.camera.record.b.f
        public void a(Bitmap bitmap, boolean z) {
            b.this.a.d().a(bitmap, z);
        }
    }

    /* renamed from: com.jingdong.manto.jsapi.camera.record.h.b$b  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class C0526b implements b.e {
        C0526b() {
        }

        @Override // com.jingdong.manto.jsapi.camera.record.b.e
        public void a(String str) {
            b.this.a.d().a(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(com.jingdong.manto.jsapi.camera.record.h.a aVar) {
        this.a = aVar;
    }

    @Override // com.jingdong.manto.jsapi.camera.record.h.c
    public void a() {
        com.jingdong.manto.jsapi.camera.record.b.e().a(new a());
    }

    @Override // com.jingdong.manto.jsapi.camera.record.h.c
    public void a(float f2, float f3, b.d dVar) {
        if (this.a.d().a(f2, f3)) {
            com.jingdong.manto.jsapi.camera.record.b.e().a(this.a.c(), f2, f3, dVar);
        }
    }

    @Override // com.jingdong.manto.jsapi.camera.record.h.c
    public void a(float f2, int i2) {
        com.jingdong.manto.jsapi.camera.record.b.e().a(f2, i2);
    }

    @Override // com.jingdong.manto.jsapi.camera.record.h.c
    public void a(Surface surface) {
        com.jingdong.manto.jsapi.camera.record.b.e().a(surface);
    }

    @Override // com.jingdong.manto.jsapi.camera.record.h.c
    public void a(SurfaceHolder surfaceHolder, float f2) {
        com.jingdong.manto.jsapi.camera.record.b.e().a(surfaceHolder, f2);
    }

    @Override // com.jingdong.manto.jsapi.camera.record.h.c
    public void a(SurfaceHolder surfaceHolder, float f2, boolean z) {
        com.jingdong.manto.jsapi.camera.record.b.e().a(surfaceHolder, f2, z);
    }

    @Override // com.jingdong.manto.jsapi.camera.record.h.c
    public void a(String str) {
        com.jingdong.manto.jsapi.camera.record.b.e().a(str);
    }

    @Override // com.jingdong.manto.jsapi.camera.record.h.c
    public void b() {
        com.jingdong.manto.jsapi.camera.record.b.e().a(new C0526b());
    }

    @Override // com.jingdong.manto.jsapi.camera.record.h.c
    public void stop() {
        com.jingdong.manto.jsapi.camera.record.b.e().c();
    }
}
