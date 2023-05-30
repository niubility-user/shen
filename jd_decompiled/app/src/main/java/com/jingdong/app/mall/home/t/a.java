package com.jingdong.app.mall.home.t;

import android.graphics.Bitmap;
import com.jd.mobile.image.ImageRequestListener;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.o;

/* loaded from: classes4.dex */
public abstract class a implements ImageRequestListener<Bitmap> {

    /* renamed from: com.jingdong.app.mall.home.t.a$a */
    /* loaded from: classes4.dex */
    public class C0327a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ Bitmap f10882g;

        C0327a(Bitmap bitmap) {
            a.this = r1;
            this.f10882g = bitmap;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            a.this.onBitmapWithUiNull(this.f10882g);
        }
    }

    private void onBitmapWithNull(Bitmap bitmap) {
        f.E0(new C0327a(bitmap));
    }

    public abstract void onBitmapWithUiNull(Bitmap bitmap);

    @Override // com.jd.mobile.image.ImageRequestListener
    public void onCancel() {
        onBitmapWithNull(null);
    }

    @Override // com.jd.mobile.image.ImageRequestListener
    public void onFailure(Throwable th) {
        onBitmapWithNull(null);
    }

    @Override // com.jd.mobile.image.ImageRequestListener
    public void onSuccess(Bitmap bitmap) {
        if (!o.a(bitmap)) {
            onBitmapWithNull(null);
        } else {
            onBitmapWithNull(bitmap);
        }
    }
}
