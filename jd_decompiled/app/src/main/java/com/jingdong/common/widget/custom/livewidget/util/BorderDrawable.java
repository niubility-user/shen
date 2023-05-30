package com.jingdong.common.widget.custom.livewidget.util;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.Nullable;

/* loaded from: classes12.dex */
public class BorderDrawable extends Drawable {
    public int mBottomBorderWidth;
    private int mColor;
    private int[] mColors;
    private Integer mHeight;
    public float[] mInnerBorderRadii;
    public float mInnerBorderRadius;
    Path mInnerPath;
    public int mLeftBorderWidth;
    private Orientation mOrientation;
    public float[] mOuterBorderRadii;
    public float mOuterBorderRadius;
    Path mOuterPath;
    private Paint mPaint;
    private RectF mRectF;
    public int mRightBorderWidth;
    private Shader mShader;
    public int mTopBorderWidth;
    private Integer mWidth;

    /* renamed from: com.jingdong.common.widget.custom.livewidget.util.BorderDrawable$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$common$widget$custom$livewidget$util$BorderDrawable$Orientation;

        static {
            int[] iArr = new int[Orientation.values().length];
            $SwitchMap$com$jingdong$common$widget$custom$livewidget$util$BorderDrawable$Orientation = iArr;
            try {
                iArr[Orientation.LEFT_RIGHT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jingdong$common$widget$custom$livewidget$util$BorderDrawable$Orientation[Orientation.TOP_BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes12.dex */
    public enum Orientation {
        TOP_BOTTOM,
        LEFT_RIGHT
    }

    public BorderDrawable(int i2) {
        this(i2, i2, i2, i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        float f2;
        float f3;
        int width = getBounds().width();
        int height = getBounds().height();
        Integer num = this.mWidth;
        if (num == null || this.mHeight == null || num.intValue() != width || this.mHeight.intValue() != height) {
            this.mOuterPath.reset();
            this.mInnerPath.reset();
            if (Build.VERSION.SDK_INT >= 21) {
                float[] fArr = this.mOuterBorderRadii;
                if (fArr != null && this.mInnerBorderRadii != null) {
                    this.mOuterPath.addRoundRect(0.0f, 0.0f, width, height, fArr, Path.Direction.CW);
                    this.mInnerPath.addRoundRect(this.mLeftBorderWidth, this.mTopBorderWidth, width - this.mRightBorderWidth, height - this.mBottomBorderWidth, this.mInnerBorderRadii, Path.Direction.CW);
                } else {
                    float f4 = this.mOuterBorderRadius;
                    this.mOuterPath.addRoundRect(0.0f, 0.0f, width, height, f4, f4, Path.Direction.CW);
                    float f5 = this.mInnerBorderRadius;
                    this.mInnerPath.addRoundRect(this.mLeftBorderWidth, this.mTopBorderWidth, width - this.mRightBorderWidth, height - this.mBottomBorderWidth, f5, f5, Path.Direction.CW);
                }
            } else if (this.mOuterBorderRadii != null && this.mInnerBorderRadii != null) {
                this.mOuterPath.addRoundRect(new RectF(0.0f, 0.0f, width, height), this.mOuterBorderRadii, Path.Direction.CW);
                this.mInnerPath.addRoundRect(new RectF(this.mLeftBorderWidth, this.mTopBorderWidth, width - this.mRightBorderWidth, height - this.mBottomBorderWidth), this.mInnerBorderRadii, Path.Direction.CW);
            } else {
                Path path = this.mOuterPath;
                RectF rectF = new RectF(0.0f, 0.0f, width, height);
                float f6 = this.mOuterBorderRadius;
                path.addRoundRect(rectF, f6, f6, Path.Direction.CW);
                Path path2 = this.mInnerPath;
                RectF rectF2 = new RectF(this.mLeftBorderWidth, this.mTopBorderWidth, width - this.mRightBorderWidth, height - this.mBottomBorderWidth);
                float f7 = this.mInnerBorderRadius;
                path2.addRoundRect(rectF2, f7, f7, Path.Direction.CW);
            }
            if (this.mColors != null) {
                if (this.mOrientation == null) {
                    this.mOrientation = Orientation.LEFT_RIGHT;
                }
                int i2 = AnonymousClass1.$SwitchMap$com$jingdong$common$widget$custom$livewidget$util$BorderDrawable$Orientation[this.mOrientation.ordinal()];
                if (i2 == 1) {
                    f2 = width;
                } else if (i2 != 2) {
                    f2 = 0.0f;
                } else {
                    f3 = height;
                    f2 = 0.0f;
                    LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, f2, f3, this.mColors, (float[]) null, Shader.TileMode.REPEAT);
                    this.mShader = linearGradient;
                    this.mPaint.setShader(linearGradient);
                }
                f3 = 0.0f;
                LinearGradient linearGradient2 = new LinearGradient(0.0f, 0.0f, f2, f3, this.mColors, (float[]) null, Shader.TileMode.REPEAT);
                this.mShader = linearGradient2;
                this.mPaint.setShader(linearGradient2);
            }
            this.mRectF = new RectF(0.0f, 0.0f, width, height);
            this.mWidth = Integer.valueOf(width);
            this.mHeight = Integer.valueOf(height);
        }
        int saveLayer = canvas.saveLayer(this.mRectF, null, 31);
        canvas.drawPath(this.mOuterPath, this.mPaint);
        this.mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawPath(this.mInnerPath, this.mPaint);
        this.mPaint.setXfermode(null);
        canvas.restoreToCount(saveLayer);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
    }

    public void setColor(int i2) {
        this.mColor = i2;
        this.mPaint.setColor(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    public void setColors(int[] iArr) {
        this.mColors = iArr;
    }

    public void setCornerRadii(@Nullable float[] fArr) {
        setCornerRadii(fArr, fArr);
    }

    public void setCornerRadius(float f2) {
        setCornerRadius(f2, f2 - this.mLeftBorderWidth);
    }

    public void setOrientation(Orientation orientation) {
        this.mOrientation = orientation;
    }

    public BorderDrawable(int i2, int i3, int i4, int i5) {
        this.mLeftBorderWidth = 0;
        this.mRightBorderWidth = 0;
        this.mTopBorderWidth = 0;
        this.mBottomBorderWidth = 0;
        this.mInnerBorderRadius = 0.0f;
        this.mOuterBorderRadius = 0.0f;
        this.mOuterPath = new Path();
        this.mInnerPath = new Path();
        this.mPaint = new Paint(1);
        this.mLeftBorderWidth = i2;
        this.mRightBorderWidth = i3;
        this.mTopBorderWidth = i4;
        this.mBottomBorderWidth = i5;
    }

    public void setCornerRadii(float[] fArr, @Nullable float[] fArr2) {
        this.mOuterBorderRadii = fArr;
        this.mInnerBorderRadii = fArr2;
    }

    public void setCornerRadius(float f2, float f3) {
        this.mOuterBorderRadius = f2;
        this.mInnerBorderRadius = f3;
    }
}
