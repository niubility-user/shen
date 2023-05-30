package com.jd.lib.productdetail.mainimage.holder.gyroscope;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import com.jd.mobile.image.ImageRequestListener;

/* loaded from: classes15.dex */
public class e implements ImageRequestListener<Bitmap> {
    public final /* synthetic */ PdMImageGyroscopeViewHolder a;

    /* loaded from: classes15.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            PdMImageGyroscopeViewHolder pdMImageGyroscopeViewHolder = e.this.a;
            if (pdMImageGyroscopeViewHolder.f4651k) {
                return;
            }
            pdMImageGyroscopeViewHolder.D.setVisibility(8);
            e.this.a.f4652l.setVisibility(0);
            e.this.a.z();
        }
    }

    /* loaded from: classes15.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            PdMImageGyroscopeViewHolder pdMImageGyroscopeViewHolder = e.this.a;
            if (pdMImageGyroscopeViewHolder.f4651k) {
                return;
            }
            pdMImageGyroscopeViewHolder.D.setVisibility(8);
            e.this.a.f4652l.setVisibility(0);
            e.this.a.z();
        }
    }

    public e(PdMImageGyroscopeViewHolder pdMImageGyroscopeViewHolder) {
        this.a = pdMImageGyroscopeViewHolder;
    }

    @Override // com.jd.mobile.image.ImageRequestListener
    public void onCancel() {
        PdMImageGyroscopeViewHolder pdMImageGyroscopeViewHolder = this.a;
        if (pdMImageGyroscopeViewHolder.f4651k) {
            return;
        }
        pdMImageGyroscopeViewHolder.F.post(new b());
    }

    @Override // com.jd.mobile.image.ImageRequestListener
    public void onFailure(Throwable th) {
        PdMImageGyroscopeViewHolder pdMImageGyroscopeViewHolder = this.a;
        if (pdMImageGyroscopeViewHolder.f4651k) {
            return;
        }
        pdMImageGyroscopeViewHolder.F.post(new a());
    }

    @Override // com.jd.mobile.image.ImageRequestListener
    public void onSuccess(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        PdMImageGyroscopeViewHolder pdMImageGyroscopeViewHolder = this.a;
        if (pdMImageGyroscopeViewHolder.f4651k) {
            return;
        }
        if (bitmap2 != null) {
            int width = bitmap2.getWidth();
            if (width > 0) {
                float f2 = ((int) (this.a.E * 1.2f)) / width;
                Matrix matrix = new Matrix();
                matrix.postScale(f2, f2);
                this.a.F.post(new com.jd.lib.productdetail.mainimage.holder.gyroscope.b(this, Bitmap.createBitmap(bitmap2, 0, 0, width, width, matrix, true)));
                return;
            }
            this.a.F.post(new c(this));
            return;
        }
        pdMImageGyroscopeViewHolder.F.post(new d(this));
    }
}
