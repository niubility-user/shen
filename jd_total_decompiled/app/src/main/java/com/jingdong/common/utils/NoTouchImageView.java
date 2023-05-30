package com.jingdong.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class NoTouchImageView extends SimpleDraweeView {
    private static final int DRAG = 1;
    private static final int NONE = 0;
    private static final String TAG = "TouchImageView";
    private Matrix currentMatrix;
    private float currentScale;
    private Rect globalRect;
    private PointF imageCenterPoint;
    private boolean isInitStandardMatrix;
    private PointF midPoint;
    private int mode;
    private float referenceDistance;
    private PointF referenceImageCenterPoint;
    private Matrix referenceMatrix;
    private PointF referencePoint;
    private int srcHeight;
    private int srcWidth;
    private Matrix standardMatrix;
    private float standardScale;

    public NoTouchImageView(Context context) {
        super(context);
        this.standardMatrix = new Matrix();
        this.referenceMatrix = new Matrix();
        this.currentMatrix = new Matrix();
        this.referenceDistance = 1.0f;
        this.referencePoint = new PointF();
        this.midPoint = new PointF();
        this.mode = 0;
        this.globalRect = new Rect();
        this.referenceImageCenterPoint = new PointF();
        this.imageCenterPoint = new PointF();
        init();
    }

    private void init() {
    }

    private void zoom(float f2) {
        Matrix matrix = this.currentMatrix;
        PointF pointF = this.midPoint;
        matrix.postScale(f2, f2, pointF.x, pointF.y);
        this.currentScale *= f2;
        if (OKLog.D) {
            OKLog.d("Temp", "(referenceImageCenterPoint.x - midPoint.x) * scale -->> " + ((this.referenceImageCenterPoint.x - this.midPoint.x) * f2));
        }
        PointF pointF2 = this.referenceImageCenterPoint;
        float f3 = pointF2.x;
        PointF pointF3 = this.midPoint;
        float f4 = f3 - pointF3.x;
        float f5 = pointF2.y - pointF3.y;
        if (f4 < 0.0f) {
            PointF pointF4 = this.imageCenterPoint;
            double d = f3;
            double sqrt = Math.sqrt(Math.abs(f4) * f2);
            Double.isNaN(d);
            pointF4.x = (float) (d - sqrt);
        } else {
            PointF pointF5 = this.imageCenterPoint;
            double d2 = f3;
            double sqrt2 = Math.sqrt(Math.abs(f4) * f2);
            Double.isNaN(d2);
            pointF5.x = (float) (d2 + sqrt2);
        }
        if (f4 < 0.0f) {
            PointF pointF6 = this.imageCenterPoint;
            double d3 = this.referenceImageCenterPoint.y;
            double sqrt3 = Math.sqrt(Math.abs(f5) * f2);
            Double.isNaN(d3);
            pointF6.y = (float) (d3 - sqrt3);
        } else {
            PointF pointF7 = this.imageCenterPoint;
            double d4 = this.referenceImageCenterPoint.y;
            double sqrt4 = Math.sqrt(Math.abs(f5) * f2);
            Double.isNaN(d4);
            pointF7.y = (float) (d4 + sqrt4);
        }
        setImageMatrix(this.currentMatrix);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (this.isInitStandardMatrix) {
            return;
        }
        this.standardMatrix.set(getImageMatrix());
        this.referenceMatrix.set(getImageMatrix());
        this.currentMatrix.set(getImageMatrix());
        this.isInitStandardMatrix = true;
    }

    @Override // com.facebook.drawee.view.DraweeView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.referencePoint.set(motionEvent.getX(), motionEvent.getY());
            this.referenceMatrix.set(getImageMatrix());
            this.mode = 1;
        } else if (action != 1) {
            if (action == 2 && this.mode == 1) {
                this.currentMatrix.set(this.referenceMatrix);
                this.currentMatrix.postTranslate(motionEvent.getX() - this.referencePoint.x, motionEvent.getY() - this.referencePoint.y);
                setImageMatrix(this.currentMatrix);
            }
        } else {
            this.mode = 0;
        }
        return true;
    }

    @Override // android.widget.ImageView
    protected boolean setFrame(int i2, int i3, int i4, int i5) {
        boolean frame = super.setFrame(i2, i3, i4, i5);
        ImageView.ScaleType scaleType = getScaleType();
        ImageView.ScaleType scaleType2 = ImageView.ScaleType.MATRIX;
        if (scaleType != scaleType2) {
            setScaleType(scaleType2);
        }
        this.srcHeight = getDrawable().getIntrinsicHeight();
        this.srcWidth = getDrawable().getIntrinsicWidth();
        getLocalVisibleRect(this.globalRect);
        PointF pointF = this.midPoint;
        if (pointF.x == 0.0f && pointF.y == 0.0f) {
            pointF.set(this.globalRect.centerX(), this.globalRect.centerY());
        }
        this.standardScale = Math.min(this.globalRect.height() / this.srcHeight, this.globalRect.width() / this.srcWidth);
        this.imageCenterPoint.set(this.globalRect.centerX(), this.globalRect.centerY());
        if (OKLog.D) {
            OKLog.d("Temp", "imageCenterPoint.x``` -->> " + this.imageCenterPoint.x);
        }
        if (OKLog.D) {
            OKLog.d("Temp", "imageCenterPoint.y``` -->> " + this.imageCenterPoint.y);
        }
        return frame;
    }

    @Override // com.facebook.drawee.view.DraweeView, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.currentMatrix.set(getImageMatrix());
    }

    public void zoomIn() {
        zoom(0.8f);
    }

    public void zoomOut() {
        zoom(1.25f);
    }

    public NoTouchImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.standardMatrix = new Matrix();
        this.referenceMatrix = new Matrix();
        this.currentMatrix = new Matrix();
        this.referenceDistance = 1.0f;
        this.referencePoint = new PointF();
        this.midPoint = new PointF();
        this.mode = 0;
        this.globalRect = new Rect();
        this.referenceImageCenterPoint = new PointF();
        this.imageCenterPoint = new PointF();
        init();
    }

    public NoTouchImageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.standardMatrix = new Matrix();
        this.referenceMatrix = new Matrix();
        this.currentMatrix = new Matrix();
        this.referenceDistance = 1.0f;
        this.referencePoint = new PointF();
        this.midPoint = new PointF();
        this.mode = 0;
        this.globalRect = new Rect();
        this.referenceImageCenterPoint = new PointF();
        this.imageCenterPoint = new PointF();
        init();
    }
}
