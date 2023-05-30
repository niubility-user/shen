package com.jingdong.common.widget.shadow;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.lib.un.basewidget.R;
import com.jingdong.common.pool.bitmappool.GlideBitmapPool;
import com.jingdong.common.widget.shadow.strategy.AutoFitShadowStrategy;
import com.jingdong.common.widget.shadow.strategy.PathShadowStrategy;
import com.jingdong.common.widget.shadow.strategy.ShadowStrategy;
import com.jingdong.common.widget.shadow.strategy.ShapeShadowStrategy;

/* loaded from: classes12.dex */
public class ShadowLayout extends FrameLayout {
    private Paint mClipPaint;
    private ShadowStrategy mShadowStrategy;

    public ShadowLayout(@NonNull Context context) {
        this(context, null);
    }

    private void initClipPaint() {
        Paint paint = new Paint(1);
        this.mClipPaint = paint;
        paint.setDither(true);
        this.mClipPaint.setFilterBitmap(true);
    }

    private void initCustomConfig(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ShadowLayout);
        int i2 = obtainStyledAttributes.getInt(R.styleable.ShadowLayout_shadowModel, 0);
        if (i2 == 0) {
            this.mShadowStrategy = new AutoFitShadowStrategy(this, context, attributeSet);
        } else if (i2 == 1) {
            this.mShadowStrategy = new ShapeShadowStrategy(this, context, attributeSet);
        } else if (i2 != 2) {
            this.mShadowStrategy = new AutoFitShadowStrategy(this, context, attributeSet);
        } else {
            this.mShadowStrategy = new PathShadowStrategy(this, context, attributeSet);
        }
        obtainStyledAttributes.recycle();
    }

    private void initDefaultConfig() {
        this.mShadowStrategy = new AutoFitShadowStrategy(this, getContext(), null);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void childDrawableStateChanged(View view) {
        super.childDrawableStateChanged(view);
        this.mShadowStrategy.refreshShadow(view);
        this.mShadowStrategy.invalidateShadow();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        this.mShadowStrategy.onDraw(canvas);
        this.mShadowStrategy.onDrawOver(canvas);
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j2) {
        if (!this.mShadowStrategy.isShadowClipCanvas()) {
            super.drawChild(canvas, view, j2);
            return true;
        }
        try {
            Bitmap bitmap = GlideBitmapPool.getBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(bitmap);
            super.drawChild(canvas2, view, j2);
            this.mShadowStrategy.onClipCanvas(canvas2, view);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.mClipPaint);
            GlideBitmapPool.putBitmap(bitmap);
            return true;
        } catch (Throwable th) {
            super.drawChild(canvas, view, j2);
            th.printStackTrace();
            return true;
        }
    }

    public float getShadowAlpha() {
        return this.mShadowStrategy.getShadowAlpha();
    }

    public int getShadowColor() {
        return this.mShadowStrategy.getShadowColor();
    }

    public int getShadowOffsetDx() {
        return this.mShadowStrategy.getShadowOffsetDx();
    }

    public int getShadowOffsetDy() {
        return this.mShadowStrategy.getShadowOffsetDy();
    }

    public int getShadowRadius() {
        return this.mShadowStrategy.getShadowRadius();
    }

    public boolean isShadowClipCanvas() {
        return this.mShadowStrategy.isShadowClipCanvas();
    }

    public boolean isShadowEnable() {
        return this.mShadowStrategy.isShadowEnable();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mShadowStrategy.onAttachToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mShadowStrategy.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        this.mShadowStrategy.onLayout(z, i2, i3, i4, i5);
        this.mShadowStrategy.invalidateShadow();
    }

    public void setClipPath(Path path) {
        this.mShadowStrategy.setClipPath(path);
        this.mShadowStrategy.invalidateShadow();
    }

    public void setCornerRadii(float[] fArr) {
        this.mShadowStrategy.setCornerRadii(fArr);
        this.mShadowStrategy.invalidateShadow();
    }

    public void setShadowAlpha(float f2) {
        this.mShadowStrategy.setShadowAlpha(f2);
        this.mShadowStrategy.invalidateShadow();
    }

    public void setShadowClipCanvas(boolean z) {
        this.mShadowStrategy.setShadowClipCanvas(z);
        this.mShadowStrategy.invalidateShadow();
    }

    public void setShadowColor(int i2) {
        this.mShadowStrategy.setShadowColor(ColorStateList.valueOf(i2));
    }

    public void setShadowEnable(boolean z) {
        this.mShadowStrategy.setShadowEnable(z);
        this.mShadowStrategy.invalidateShadow();
    }

    public void setShadowOffsetDx(int i2) {
        this.mShadowStrategy.setShadowOffsetDx(i2);
        requestLayout();
    }

    public void setShadowOffsetDy(int i2) {
        this.mShadowStrategy.setShadowOffsetDy(i2);
        requestLayout();
    }

    public void setShadowRadius(int i2) {
        this.mShadowStrategy.setShadowRadius(i2);
        requestLayout();
    }

    public void superDispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    public ShadowLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void setShadowColor(ColorStateList colorStateList) {
        this.mShadowStrategy.setShadowColor(colorStateList);
    }

    public ShadowLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        setWillNotDraw(false);
        initClipPaint();
        if (attributeSet == null) {
            initDefaultConfig();
        } else {
            initCustomConfig(context, attributeSet);
        }
    }

    public void setCornerRadii(float f2) {
        setCornerRadii(new float[]{f2, f2, f2, f2, f2, f2, f2, f2});
    }
}
