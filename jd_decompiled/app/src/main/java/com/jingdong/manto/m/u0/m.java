package com.jingdong.manto.m.u0;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Region;

/* loaded from: classes15.dex */
public class m extends Canvas {
    public Bitmap a;

    public m(Bitmap bitmap) {
        super(bitmap);
        this.a = bitmap;
    }

    public void a() {
        try {
            Bitmap bitmap = this.a;
            if (bitmap == null || bitmap.isRecycled()) {
                return;
            }
            this.a.recycle();
        } catch (Throwable unused) {
        }
    }

    public final void a(float f2, float f3, float f4, float f5) {
        Bitmap bitmap = this.a;
        RectF rectF = new RectF(0.0f, 0.0f, this.a.getWidth(), this.a.getHeight());
        RectF rectF2 = new RectF(f2, f3, f4, f5);
        Region.Op op = Region.Op.DIFFERENCE;
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.clipRect(rectF);
        canvas.clipRect(rectF2, op);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        if (createBitmap == null || createBitmap.isRecycled()) {
            return;
        }
        this.a.eraseColor(0);
        drawBitmap(createBitmap, 0.0f, 0.0f, (Paint) null);
        createBitmap.recycle();
    }

    @Override // android.graphics.Canvas
    public final void setBitmap(Bitmap bitmap) {
        super.setBitmap(bitmap);
        this.a = bitmap;
    }
}
