package com.jingdong.manto.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.text.TextUtils;
import android.widget.ImageView;
import com.jd.dynamic.DYConstants;
import com.jingdong.manto.utils.MantoDensityUtils;

@SuppressLint({"AppCompatCustomView"})
/* loaded from: classes16.dex */
public class b extends ImageView {
    private Bitmap a;
    private boolean b;

    /* renamed from: c  reason: collision with root package name */
    private Paint f14341c;
    private String d;

    public b(Context context) {
        super(context);
        this.b = true;
        this.f14341c = new Paint();
    }

    public void a() {
        this.b = true;
    }

    public Bitmap getSourceBitmap() {
        return this.a;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f14341c = null;
        this.a = null;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        Matrix matrix;
        float height;
        int height2;
        float f2;
        super.onDraw(canvas);
        Bitmap bitmap = this.a;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        if (TextUtils.equals("left", this.d)) {
            setImageBitmap(null);
            matrix = new Matrix();
            this.a.getHeight();
            getHeight();
            matrix.postTranslate(0.0f, (-(this.a.getHeight() - getHeight())) / 2.0f);
        } else {
            if (TextUtils.equals("right", this.d)) {
                setImageBitmap(null);
                matrix = new Matrix();
                this.a.getHeight();
                getHeight();
                matrix.postTranslate(0.0f, (-(this.a.getHeight() - getHeight())) / 2.0f);
            } else {
                if (TextUtils.equals(DYConstants.DY_CENTER, this.d)) {
                    setImageBitmap(null);
                    matrix = new Matrix();
                    this.a.getHeight();
                    getHeight();
                    f2 = (-(this.a.getHeight() - getHeight())) / 2.0f;
                } else if (TextUtils.equals("top left", this.d)) {
                    setImageBitmap(null);
                    matrix = new Matrix();
                } else if (TextUtils.equals("top right", this.d)) {
                    setImageBitmap(null);
                    matrix = new Matrix();
                } else if (TextUtils.equals("bottom left", this.d)) {
                    setImageBitmap(null);
                    matrix = new Matrix();
                    this.a.getHeight();
                    getHeight();
                    matrix.postTranslate(0.0f, -(this.a.getHeight() - getHeight()));
                } else if (TextUtils.equals("bottom right", this.d)) {
                    setImageBitmap(null);
                    matrix = new Matrix();
                    this.a.getHeight();
                    getHeight();
                    matrix.postTranslate(0.0f, -(this.a.getHeight() - getHeight()));
                } else if (TextUtils.equals("top", this.d)) {
                    setImageBitmap(null);
                    matrix = new Matrix();
                    this.a.getWidth();
                    getWidth();
                    matrix.postTranslate((-(this.a.getWidth() - getWidth())) / 2.0f, 0.0f);
                } else if (TextUtils.equals("bottom", this.d)) {
                    setImageBitmap(null);
                    matrix = new Matrix();
                    this.a.getHeight();
                    getHeight();
                    f2 = -(this.a.getHeight() - getHeight());
                } else {
                    if (TextUtils.equals("widthFix", this.d)) {
                        setImageBitmap(null);
                        matrix = new Matrix();
                        height = getWidth() * 1.0f;
                        height2 = this.a.getWidth();
                    } else if (!TextUtils.equals("heightFix", this.d)) {
                        return;
                    } else {
                        setImageBitmap(null);
                        matrix = new Matrix();
                        height = getHeight() * 1.0f;
                        height2 = this.a.getHeight();
                    }
                    float f3 = height / height2;
                    matrix.setScale(f3, f3);
                }
                matrix.postTranslate(0.0f, f2);
                this.a.getWidth();
                getWidth();
                matrix.postTranslate((-(this.a.getWidth() - getWidth())) / 2.0f, 0.0f);
            }
            this.a.getWidth();
            getWidth();
            matrix.postTranslate(-(this.a.getWidth() - getWidth()), 0.0f);
        }
        canvas.drawBitmap(this.a, matrix, this.f14341c);
    }

    public void setContentMode(String str) {
        this.d = str;
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        if (!this.b || bitmap == null) {
            return;
        }
        if (!TextUtils.isEmpty(this.d)) {
            float density = MantoDensityUtils.getDensity(com.jingdong.manto.c.a());
            bitmap = com.jingdong.manto.utils.d.a(bitmap, density, density);
        }
        this.a = bitmap;
        this.b = false;
    }
}
