package com.jingdong.app.mall.bundle.jd_component.bubble;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/* loaded from: classes2.dex */
public class BubbleDrawable extends Drawable {
    private Bitmap bubbleBitmap;
    private int bubbleColor;
    private BubbleType bubbleType;
    private float mAngle;
    private float mArrowHeight;
    private ArrowLocation mArrowLocation;
    private float mArrowPosition;
    private ArrowStart mArrowStart;
    private float mArrowWidth;
    private BitmapShader mBitmapShader;
    private Paint mPaint;
    private Path mPath;
    private RectF mRect;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.bundle.jd_component.bubble.BubbleDrawable$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$app$mall$bundle$jd_component$bubble$BubbleDrawable$ArrowLocation;
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$app$mall$bundle$jd_component$bubble$BubbleDrawable$ArrowStart;
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$app$mall$bundle$jd_component$bubble$BubbleDrawable$BubbleType;

        static {
            int[] iArr = new int[ArrowStart.values().length];
            $SwitchMap$com$jingdong$app$mall$bundle$jd_component$bubble$BubbleDrawable$ArrowStart = iArr;
            try {
                iArr[ArrowStart.BOTTOM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$bundle$jd_component$bubble$BubbleDrawable$ArrowStart[ArrowStart.TOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$bundle$jd_component$bubble$BubbleDrawable$ArrowStart[ArrowStart.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$bundle$jd_component$bubble$BubbleDrawable$ArrowStart[ArrowStart.LEFT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$bundle$jd_component$bubble$BubbleDrawable$ArrowStart[ArrowStart.RIGHT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[BubbleType.values().length];
            $SwitchMap$com$jingdong$app$mall$bundle$jd_component$bubble$BubbleDrawable$BubbleType = iArr2;
            try {
                iArr2[BubbleType.COLOR.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$bundle$jd_component$bubble$BubbleDrawable$BubbleType[BubbleType.BITMAP.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr3 = new int[ArrowLocation.values().length];
            $SwitchMap$com$jingdong$app$mall$bundle$jd_component$bubble$BubbleDrawable$ArrowLocation = iArr3;
            try {
                iArr3[ArrowLocation.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$bundle$jd_component$bubble$BubbleDrawable$ArrowLocation[ArrowLocation.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$bundle$jd_component$bubble$BubbleDrawable$ArrowLocation[ArrowLocation.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$bundle$jd_component$bubble$BubbleDrawable$ArrowLocation[ArrowLocation.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public enum ArrowLocation {
        LEFT(0),
        RIGHT(1),
        TOP(2),
        BOTTOM(3);
        
        private int mValue;

        ArrowLocation(int i2) {
            this.mValue = i2;
        }

        public static ArrowLocation getDefault() {
            return LEFT;
        }

        public static ArrowLocation mapIntToValue(int i2) {
            for (ArrowLocation arrowLocation : values()) {
                if (i2 == arrowLocation.getIntValue()) {
                    return arrowLocation;
                }
            }
            return getDefault();
        }

        public int getIntValue() {
            return this.mValue;
        }
    }

    /* loaded from: classes2.dex */
    public enum ArrowStart {
        CENTER(0),
        LEFT(1),
        RIGHT(2),
        TOP(3),
        BOTTOM(4);
        
        private int mValue;

        ArrowStart(int i2) {
            this.mValue = i2;
        }

        public static ArrowStart mapIntToValue(int i2) {
            for (ArrowStart arrowStart : values()) {
                if (i2 == arrowStart.getIntValue()) {
                    return arrowStart;
                }
            }
            return CENTER;
        }

        public int getIntValue() {
            return this.mValue;
        }
    }

    /* loaded from: classes2.dex */
    public enum BubbleType {
        COLOR,
        BITMAP
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        public static float DEFAULT_ANGLE = 20.0f;
        public static float DEFAULT_ARROW_HEIGHT = 25.0f;
        public static float DEFAULT_ARROW_POSITION = 50.0f;
        public static float DEFAULT_ARROW_WITH = 25.0f;
        public static int DEFAULT_BUBBLE_COLOR = -65536;
        private Bitmap bubbleBitmap;
        private RectF mRect;
        private float mArrowWidth = DEFAULT_ARROW_WITH;
        private float mAngle = DEFAULT_ANGLE;
        private float mArrowHeight = DEFAULT_ARROW_HEIGHT;
        private float mArrowPosition = DEFAULT_ARROW_POSITION;
        private int bubbleColor = DEFAULT_BUBBLE_COLOR;
        private BubbleType bubbleType = BubbleType.COLOR;
        private ArrowLocation mArrowLocation = ArrowLocation.LEFT;
        private ArrowStart mArrowStart = ArrowStart.CENTER;

        public Builder angle(float f2) {
            this.mAngle = f2 * 2.0f;
            return this;
        }

        public Builder arrowHeight(float f2) {
            this.mArrowHeight = f2;
            return this;
        }

        public Builder arrowLocation(ArrowLocation arrowLocation) {
            this.mArrowLocation = arrowLocation;
            return this;
        }

        public Builder arrowPosition(float f2) {
            this.mArrowPosition = f2;
            return this;
        }

        public Builder arrowStart(ArrowStart arrowStart) {
            this.mArrowStart = arrowStart;
            return this;
        }

        public Builder arrowWidth(float f2) {
            this.mArrowWidth = f2;
            return this;
        }

        public Builder bubbleBitmap(Bitmap bitmap) {
            this.bubbleBitmap = bitmap;
            bubbleType(BubbleType.BITMAP);
            return this;
        }

        public Builder bubbleColor(int i2) {
            this.bubbleColor = i2;
            bubbleType(BubbleType.COLOR);
            return this;
        }

        public Builder bubbleType(BubbleType bubbleType) {
            this.bubbleType = bubbleType;
            return this;
        }

        public BubbleDrawable build() {
            if (this.mRect != null) {
                return new BubbleDrawable(this, null);
            }
            throw new IllegalArgumentException("BubbleDrawable Rect can not be null");
        }

        public Builder rect(RectF rectF) {
            this.mRect = rectF;
            return this;
        }
    }

    /* synthetic */ BubbleDrawable(Builder builder, AnonymousClass1 anonymousClass1) {
        this(builder);
    }

    private void setUp(Canvas canvas) {
        int i2 = AnonymousClass1.$SwitchMap$com$jingdong$app$mall$bundle$jd_component$bubble$BubbleDrawable$BubbleType[this.bubbleType.ordinal()];
        if (i2 == 1) {
            this.mPaint.setColor(this.bubbleColor);
        } else if (i2 == 2) {
            if (this.bubbleBitmap == null) {
                return;
            }
            if (this.mBitmapShader == null) {
                Bitmap bitmap = this.bubbleBitmap;
                Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                this.mBitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
            }
            this.mPaint.setShader(this.mBitmapShader);
            setUpShaderMatrix();
        }
        setUpPath(this.mArrowLocation, this.mPath);
        canvas.drawPath(this.mPath, this.mPaint);
    }

    private void setUpBottomPath(RectF rectF, Path path) {
        float f2;
        float f3;
        float f4;
        int i2 = AnonymousClass1.$SwitchMap$com$jingdong$app$mall$bundle$jd_component$bubble$BubbleDrawable$ArrowStart[this.mArrowStart.ordinal()];
        if (i2 != 4) {
            if (i2 != 5) {
                f3 = this.mArrowPosition + ((rectF.right - rectF.left) / 2.0f);
                f4 = this.mArrowWidth / 2.0f;
            } else {
                f3 = (this.mArrowPosition + rectF.right) - rectF.left;
                f4 = this.mArrowWidth;
            }
            f2 = f3 - f4;
        } else {
            f2 = this.mArrowPosition;
        }
        path.moveTo(rectF.left + this.mAngle, rectF.top);
        path.lineTo(rectF.width() - this.mAngle, rectF.top);
        float f5 = rectF.right;
        float f6 = this.mAngle;
        float f7 = rectF.top;
        path.arcTo(new RectF(f5 - f6, f7, f5, f6 + f7), 270.0f, 90.0f);
        path.lineTo(rectF.right, (rectF.bottom - this.mArrowHeight) - this.mAngle);
        float f8 = rectF.right;
        float f9 = this.mAngle;
        float f10 = rectF.bottom;
        float f11 = this.mArrowHeight;
        path.arcTo(new RectF(f8 - f9, (f10 - f9) - f11, f8, f10 - f11), 0.0f, 90.0f);
        path.lineTo(rectF.left + this.mArrowWidth + f2, rectF.bottom - this.mArrowHeight);
        path.lineTo(rectF.left + f2 + (this.mArrowWidth / 2.0f), rectF.bottom);
        path.lineTo(rectF.left + f2, rectF.bottom - this.mArrowHeight);
        path.lineTo(rectF.left + Math.min(this.mAngle, f2), rectF.bottom - this.mArrowHeight);
        float f12 = rectF.left;
        float f13 = rectF.bottom;
        float f14 = this.mAngle;
        float f15 = this.mArrowHeight;
        path.arcTo(new RectF(f12, (f13 - f14) - f15, f14 + f12, f13 - f15), 90.0f, 90.0f);
        path.lineTo(rectF.left, rectF.top + this.mAngle);
        float f16 = rectF.left;
        float f17 = rectF.top;
        float f18 = this.mAngle;
        path.arcTo(new RectF(f16, f17, f18 + f16, f18 + f17), 180.0f, 90.0f);
        path.close();
    }

    private void setUpLeftPath(RectF rectF, Path path) {
        float f2;
        float f3;
        float f4;
        int i2 = AnonymousClass1.$SwitchMap$com$jingdong$app$mall$bundle$jd_component$bubble$BubbleDrawable$ArrowStart[this.mArrowStart.ordinal()];
        if (i2 == 1) {
            f2 = (this.mArrowPosition + rectF.bottom) - rectF.top;
            f3 = this.mArrowWidth;
        } else if (i2 != 2) {
            f2 = this.mArrowPosition + ((rectF.bottom - rectF.top) / 2.0f);
            f3 = this.mArrowWidth / 2.0f;
        } else {
            f4 = this.mArrowPosition;
            path.moveTo(this.mArrowWidth + rectF.left + this.mAngle, rectF.top);
            path.lineTo(rectF.width() - this.mAngle, rectF.top);
            float f5 = rectF.right;
            float f6 = this.mAngle;
            float f7 = rectF.top;
            path.arcTo(new RectF(f5 - f6, f7, f5, f6 + f7), 270.0f, 90.0f);
            path.lineTo(rectF.right, rectF.bottom - this.mAngle);
            float f8 = rectF.right;
            float f9 = this.mAngle;
            float f10 = rectF.bottom;
            path.arcTo(new RectF(f8 - f9, f10 - f9, f8, f10), 0.0f, 90.0f);
            path.lineTo(rectF.left + this.mArrowWidth + this.mAngle, rectF.bottom);
            float f11 = rectF.left;
            float f12 = this.mArrowWidth;
            float f13 = rectF.bottom;
            float f14 = this.mAngle;
            path.arcTo(new RectF(f11 + f12, f13 - f14, f14 + f11 + f12, f13), 90.0f, 90.0f);
            path.lineTo(rectF.left + this.mArrowWidth, this.mArrowHeight + f4);
            path.lineTo(rectF.left, (this.mArrowHeight / 2.0f) + f4);
            path.lineTo(rectF.left + this.mArrowWidth, f4);
            path.lineTo(rectF.left + this.mArrowWidth, rectF.top + this.mAngle);
            float f15 = rectF.left;
            float f16 = this.mArrowWidth;
            float f17 = rectF.top;
            float f18 = this.mAngle;
            path.arcTo(new RectF(f15 + f16, f17, f15 + f18 + f16, f18 + f17), 180.0f, 90.0f);
            path.close();
        }
        f4 = f2 - f3;
        path.moveTo(this.mArrowWidth + rectF.left + this.mAngle, rectF.top);
        path.lineTo(rectF.width() - this.mAngle, rectF.top);
        float f52 = rectF.right;
        float f62 = this.mAngle;
        float f72 = rectF.top;
        path.arcTo(new RectF(f52 - f62, f72, f52, f62 + f72), 270.0f, 90.0f);
        path.lineTo(rectF.right, rectF.bottom - this.mAngle);
        float f82 = rectF.right;
        float f92 = this.mAngle;
        float f102 = rectF.bottom;
        path.arcTo(new RectF(f82 - f92, f102 - f92, f82, f102), 0.0f, 90.0f);
        path.lineTo(rectF.left + this.mArrowWidth + this.mAngle, rectF.bottom);
        float f112 = rectF.left;
        float f122 = this.mArrowWidth;
        float f132 = rectF.bottom;
        float f142 = this.mAngle;
        path.arcTo(new RectF(f112 + f122, f132 - f142, f142 + f112 + f122, f132), 90.0f, 90.0f);
        path.lineTo(rectF.left + this.mArrowWidth, this.mArrowHeight + f4);
        path.lineTo(rectF.left, (this.mArrowHeight / 2.0f) + f4);
        path.lineTo(rectF.left + this.mArrowWidth, f4);
        path.lineTo(rectF.left + this.mArrowWidth, rectF.top + this.mAngle);
        float f152 = rectF.left;
        float f162 = this.mArrowWidth;
        float f172 = rectF.top;
        float f182 = this.mAngle;
        path.arcTo(new RectF(f152 + f162, f172, f152 + f182 + f162, f182 + f172), 180.0f, 90.0f);
        path.close();
    }

    private void setUpPath(ArrowLocation arrowLocation, Path path) {
        int i2 = AnonymousClass1.$SwitchMap$com$jingdong$app$mall$bundle$jd_component$bubble$BubbleDrawable$ArrowLocation[arrowLocation.ordinal()];
        if (i2 == 1) {
            setUpLeftPath(this.mRect, path);
        } else if (i2 == 2) {
            setUpRightPath(this.mRect, path);
        } else if (i2 == 3) {
            setUpTopPath(this.mRect, path);
        } else if (i2 != 4) {
        } else {
            setUpBottomPath(this.mRect, path);
        }
    }

    private void setUpRightPath(RectF rectF, Path path) {
        float f2;
        float f3;
        float f4;
        int i2 = AnonymousClass1.$SwitchMap$com$jingdong$app$mall$bundle$jd_component$bubble$BubbleDrawable$ArrowStart[this.mArrowStart.ordinal()];
        if (i2 == 1) {
            f2 = (this.mArrowPosition + rectF.bottom) - rectF.top;
            f3 = this.mArrowWidth;
        } else if (i2 != 2) {
            f2 = this.mArrowPosition + ((rectF.bottom - rectF.top) / 2.0f);
            f3 = this.mArrowWidth / 2.0f;
        } else {
            f4 = this.mArrowPosition;
            path.moveTo(rectF.left + this.mAngle, rectF.top);
            path.lineTo((rectF.width() - this.mAngle) - this.mArrowWidth, rectF.top);
            float f5 = rectF.right;
            float f6 = this.mAngle;
            float f7 = this.mArrowWidth;
            float f8 = rectF.top;
            path.arcTo(new RectF((f5 - f6) - f7, f8, f5 - f7, f6 + f8), 270.0f, 90.0f);
            path.lineTo(rectF.right - this.mArrowWidth, f4);
            path.lineTo(rectF.right, (this.mArrowHeight / 2.0f) + f4);
            path.lineTo(rectF.right - this.mArrowWidth, f4 + this.mArrowHeight);
            path.lineTo(rectF.right - this.mArrowWidth, rectF.bottom - this.mAngle);
            float f9 = rectF.right;
            float f10 = this.mAngle;
            float f11 = this.mArrowWidth;
            float f12 = rectF.bottom;
            path.arcTo(new RectF((f9 - f10) - f11, f12 - f10, f9 - f11, f12), 0.0f, 90.0f);
            path.lineTo(rectF.left + this.mArrowWidth, rectF.bottom);
            float f13 = rectF.left;
            float f14 = rectF.bottom;
            float f15 = this.mAngle;
            path.arcTo(new RectF(f13, f14 - f15, f15 + f13, f14), 90.0f, 90.0f);
            float f16 = rectF.left;
            float f17 = rectF.top;
            float f18 = this.mAngle;
            path.arcTo(new RectF(f16, f17, f18 + f16, f18 + f17), 180.0f, 90.0f);
            path.close();
        }
        f4 = f2 - f3;
        path.moveTo(rectF.left + this.mAngle, rectF.top);
        path.lineTo((rectF.width() - this.mAngle) - this.mArrowWidth, rectF.top);
        float f52 = rectF.right;
        float f62 = this.mAngle;
        float f72 = this.mArrowWidth;
        float f82 = rectF.top;
        path.arcTo(new RectF((f52 - f62) - f72, f82, f52 - f72, f62 + f82), 270.0f, 90.0f);
        path.lineTo(rectF.right - this.mArrowWidth, f4);
        path.lineTo(rectF.right, (this.mArrowHeight / 2.0f) + f4);
        path.lineTo(rectF.right - this.mArrowWidth, f4 + this.mArrowHeight);
        path.lineTo(rectF.right - this.mArrowWidth, rectF.bottom - this.mAngle);
        float f92 = rectF.right;
        float f102 = this.mAngle;
        float f112 = this.mArrowWidth;
        float f122 = rectF.bottom;
        path.arcTo(new RectF((f92 - f102) - f112, f122 - f102, f92 - f112, f122), 0.0f, 90.0f);
        path.lineTo(rectF.left + this.mArrowWidth, rectF.bottom);
        float f132 = rectF.left;
        float f142 = rectF.bottom;
        float f152 = this.mAngle;
        path.arcTo(new RectF(f132, f142 - f152, f152 + f132, f142), 90.0f, 90.0f);
        float f162 = rectF.left;
        float f172 = rectF.top;
        float f182 = this.mAngle;
        path.arcTo(new RectF(f162, f172, f182 + f162, f182 + f172), 180.0f, 90.0f);
        path.close();
    }

    private void setUpShaderMatrix() {
        Matrix matrix = new Matrix();
        matrix.set(null);
        matrix.postScale(getIntrinsicWidth() / this.bubbleBitmap.getWidth(), getIntrinsicHeight() / this.bubbleBitmap.getHeight());
        RectF rectF = this.mRect;
        matrix.postTranslate(rectF.left, rectF.top);
        this.mBitmapShader.setLocalMatrix(matrix);
    }

    private void setUpTopPath(RectF rectF, Path path) {
        float f2;
        float f3;
        float f4;
        int i2 = AnonymousClass1.$SwitchMap$com$jingdong$app$mall$bundle$jd_component$bubble$BubbleDrawable$ArrowStart[this.mArrowStart.ordinal()];
        if (i2 != 4) {
            if (i2 != 5) {
                f3 = this.mArrowPosition + ((rectF.right - rectF.left) / 2.0f);
                f4 = this.mArrowWidth / 2.0f;
            } else {
                f3 = (this.mArrowPosition + rectF.right) - rectF.left;
                f4 = this.mArrowWidth;
            }
            f2 = f3 - f4;
        } else {
            f2 = this.mArrowPosition;
        }
        path.moveTo(rectF.left + Math.min(f2, this.mAngle), rectF.top + this.mArrowHeight);
        path.lineTo(rectF.left + f2, rectF.top + this.mArrowHeight);
        path.lineTo(rectF.left + (this.mArrowWidth / 2.0f) + f2, rectF.top);
        path.lineTo(rectF.left + this.mArrowWidth + f2, rectF.top + this.mArrowHeight);
        path.lineTo(rectF.right - this.mAngle, rectF.top + this.mArrowHeight);
        float f5 = rectF.right;
        float f6 = this.mAngle;
        float f7 = rectF.top;
        float f8 = this.mArrowHeight;
        path.arcTo(new RectF(f5 - f6, f7 + f8, f5, f6 + f7 + f8), 270.0f, 90.0f);
        path.lineTo(rectF.right, rectF.bottom - this.mAngle);
        float f9 = rectF.right;
        float f10 = this.mAngle;
        float f11 = rectF.bottom;
        path.arcTo(new RectF(f9 - f10, f11 - f10, f9, f11), 0.0f, 90.0f);
        path.lineTo(rectF.left + this.mAngle, rectF.bottom);
        float f12 = rectF.left;
        float f13 = rectF.bottom;
        float f14 = this.mAngle;
        path.arcTo(new RectF(f12, f13 - f14, f14 + f12, f13), 90.0f, 90.0f);
        path.lineTo(rectF.left, rectF.top + this.mArrowHeight + this.mAngle);
        float f15 = rectF.left;
        float f16 = rectF.top;
        float f17 = this.mArrowHeight;
        float f18 = this.mAngle;
        path.arcTo(new RectF(f15, f16 + f17, f18 + f15, f18 + f16 + f17), 180.0f, 90.0f);
        path.close();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        setUp(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) this.mRect.height();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return (int) this.mRect.width();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.mPaint.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    private BubbleDrawable(Builder builder) {
        this.mPath = new Path();
        this.mPaint = new Paint(1);
        this.mRect = builder.mRect;
        this.mAngle = builder.mAngle;
        this.mArrowHeight = builder.mArrowHeight;
        this.mArrowWidth = builder.mArrowWidth;
        this.mArrowPosition = builder.mArrowPosition;
        this.bubbleColor = builder.bubbleColor;
        this.bubbleBitmap = builder.bubbleBitmap;
        this.mArrowLocation = builder.mArrowLocation;
        this.bubbleType = builder.bubbleType;
        this.mArrowStart = builder.mArrowStart;
    }
}
