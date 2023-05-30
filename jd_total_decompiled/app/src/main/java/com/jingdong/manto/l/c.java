package com.jingdong.manto.l;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.jingdong.manto.utils.MantoLog;
import java.lang.ref.WeakReference;

/* loaded from: classes15.dex */
public class c implements h {
    final String a;
    private final WeakReference<ImageView> b;

    /* renamed from: c  reason: collision with root package name */
    private final k f13231c;
    boolean d = false;

    /* renamed from: e  reason: collision with root package name */
    String f13232e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(ImageView imageView, k kVar, String str) {
        this.b = new WeakReference<>(imageView);
        this.f13231c = kVar;
        this.a = "ImageView#" + imageView.hashCode();
        this.f13232e = str;
    }

    private void d() {
        if (this.b.get() != null) {
            this.f13231c.a(this.b.get().hashCode());
        }
    }

    @Override // com.jingdong.manto.l.h
    public final void a() {
        d();
    }

    @Override // com.jingdong.manto.l.a
    public final String b() {
        return this.a;
    }

    @Override // com.jingdong.manto.l.h
    public void b(Bitmap bitmap) {
        d();
        ImageView imageView = this.b.get();
        if (imageView != null) {
            if (!com.jingdong.manto.sdk.thread.a.b()) {
                MantoLog.e("DefaultLoadListener", "onBitmapLoaded in non-main thread ");
            }
            com.jingdong.manto.jsapi.coverview.a.a(this.f13232e, imageView, bitmap);
        }
        this.d = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ImageView c() {
        return this.b.get();
    }

    @Override // com.jingdong.manto.l.h
    public void onStart() {
    }
}
