package com.tencent.smtt.sdk.ui.dialog.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.tencent.smtt.sdk.ui.dialog.a;
import java.lang.ref.WeakReference;

/* loaded from: classes9.dex */
public class RoundImageView extends ImageView {
    private Paint a;
    private Xfermode b;

    /* renamed from: c  reason: collision with root package name */
    private Bitmap f17880c;
    private float[] d;

    /* renamed from: e  reason: collision with root package name */
    private RectF f17881e;

    /* renamed from: f  reason: collision with root package name */
    private int f17882f;

    /* renamed from: g  reason: collision with root package name */
    private WeakReference<Bitmap> f17883g;

    /* renamed from: h  reason: collision with root package name */
    private float f17884h;

    /* renamed from: i  reason: collision with root package name */
    private Path f17885i;

    public RoundImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        this.f17882f = Color.parseColor("#eaeaea");
        Paint paint = new Paint();
        this.a = paint;
        paint.setAntiAlias(true);
        this.f17885i = new Path();
        this.d = new float[8];
        this.f17881e = new RectF();
        this.f17884h = a.a(context, 16.46f);
        int i2 = 0;
        while (true) {
            float[] fArr = this.d;
            if (i2 >= fArr.length) {
                return;
            }
            fArr[i2] = this.f17884h;
            i2++;
        }
    }

    private Bitmap a() {
        Bitmap bitmap = null;
        try {
            bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint(1);
            paint.setColor(-16777216);
            RectF rectF = new RectF(0.0f, 0.0f, getWidth(), getHeight());
            float f2 = this.f17884h;
            canvas.drawRoundRect(rectF, f2, f2, paint);
            return bitmap;
        } catch (Throwable th) {
            th.printStackTrace();
            return bitmap;
        }
    }

    private void a(int i2, int i3) {
        this.f17885i.reset();
        this.a.setStrokeWidth(i2);
        this.a.setColor(i3);
        this.a.setStyle(Paint.Style.STROKE);
    }

    private void a(Canvas canvas, int i2, int i3, RectF rectF, float[] fArr) {
        a(i2, i3);
        this.f17885i.addRoundRect(rectF, fArr, Path.Direction.CCW);
        canvas.drawPath(this.f17885i, this.a);
    }

    @Override // android.view.View
    public void invalidate() {
        this.f17883g = null;
        Bitmap bitmap = this.f17880c;
        if (bitmap != null) {
            bitmap.recycle();
            this.f17880c = null;
        }
        super.invalidate();
    }

    @Override // android.widget.ImageView, android.view.View
    @SuppressLint({"DrawAllocation"})
    protected void onDraw(Canvas canvas) {
        WeakReference<Bitmap> weakReference = this.f17883g;
        Bitmap bitmap = weakReference == null ? null : weakReference.get();
        if (bitmap == null || bitmap.isRecycled()) {
            Drawable drawable = getDrawable();
            if (drawable != null) {
                int intrinsicWidth = drawable.getIntrinsicWidth();
                int intrinsicHeight = drawable.getIntrinsicHeight();
                Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas2 = new Canvas(createBitmap);
                float f2 = intrinsicWidth;
                float f3 = intrinsicHeight;
                float max = Math.max((getWidth() * 1.0f) / f2, (getHeight() * 1.0f) / f3);
                drawable.setBounds(0, 0, (int) (f2 * max), (int) (max * f3));
                drawable.draw(canvas2);
                Bitmap bitmap2 = this.f17880c;
                if (bitmap2 == null || bitmap2.isRecycled()) {
                    this.f17880c = a();
                }
                this.a.reset();
                this.a.setFilterBitmap(false);
                this.a.setXfermode(this.b);
                Bitmap bitmap3 = this.f17880c;
                if (bitmap3 != null) {
                    canvas2.drawBitmap(bitmap3, 0.0f, 0.0f, this.a);
                }
                this.a.setXfermode(null);
                canvas.drawBitmap(createBitmap, 0.0f, 0.0f, (Paint) null);
                this.f17883g = new WeakReference<>(createBitmap);
            }
        } else {
            this.a.setXfermode(null);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.a);
        }
        a(canvas, 1, this.f17882f, this.f17881e, this.d);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.f17881e.set(0.5f, 0.5f, i2 - 0.5f, i3 - 0.5f);
    }
}
