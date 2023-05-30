package com.jingdong.manto.jsapi.camera.record.h;

import android.content.Context;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.jingdong.manto.jsapi.camera.record.MantoCameraView;
import com.jingdong.manto.jsapi.camera.record.b;

/* loaded from: classes15.dex */
public class a implements c {
    private Context a;
    private c b;

    /* renamed from: c */
    private MantoCameraView f13179c;
    private c d;

    public a(Context context, MantoCameraView mantoCameraView) {
        this.a = context;
        b bVar = new b(this);
        this.d = bVar;
        this.b = bVar;
        this.f13179c = mantoCameraView;
    }

    @Override // com.jingdong.manto.jsapi.camera.record.h.c
    public void a() {
        this.b.a();
    }

    @Override // com.jingdong.manto.jsapi.camera.record.h.c
    public void a(float f2, float f3, b.d dVar) {
        this.b.a(f2, f3, dVar);
    }

    @Override // com.jingdong.manto.jsapi.camera.record.h.c
    public void a(float f2, int i2) {
        this.b.a(f2, i2);
    }

    @Override // com.jingdong.manto.jsapi.camera.record.h.c
    public void a(Surface surface) {
        this.b.a(surface);
    }

    @Override // com.jingdong.manto.jsapi.camera.record.h.c
    public void a(SurfaceHolder surfaceHolder, float f2) {
        this.b.a(surfaceHolder, f2);
    }

    @Override // com.jingdong.manto.jsapi.camera.record.h.c
    public void a(SurfaceHolder surfaceHolder, float f2, boolean z) {
        this.b.a(surfaceHolder, f2, z);
    }

    @Override // com.jingdong.manto.jsapi.camera.record.h.c
    public void a(String str) {
        this.b.a(str);
    }

    @Override // com.jingdong.manto.jsapi.camera.record.h.c
    public void b() {
        this.b.b();
    }

    public Context c() {
        return this.a;
    }

    public MantoCameraView d() {
        return this.f13179c;
    }

    @Override // com.jingdong.manto.jsapi.camera.record.h.c
    public void stop() {
        this.b.stop();
    }
}
