package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Arrays;

/* loaded from: classes.dex */
public abstract class RoundedDrawable extends Drawable implements Rounded, TransformAwareDrawable {
    private final Drawable mDelegate;
    @Nullable
    @VisibleForTesting
    RectF mInsideBorderBounds;
    @Nullable
    @VisibleForTesting
    float[] mInsideBorderRadii;
    @Nullable
    @VisibleForTesting
    Matrix mInsideBorderTransform;
    @Nullable
    @VisibleForTesting
    Matrix mPrevInsideBorderTransform;
    @Nullable
    private TransformCallback mTransformCallback;
    protected boolean mIsCircle = false;
    protected boolean mRadiiNonZero = false;
    protected float mBorderWidth = 0.0f;
    protected final Path mPath = new Path();
    protected boolean mIsShaderTransformDirty = true;
    protected int mBorderColor = 0;
    protected final Path mBorderPath = new Path();
    private final float[] mCornerRadii = new float[8];
    @VisibleForTesting
    final float[] mBorderRadii = new float[8];
    @VisibleForTesting
    final RectF mRootBounds = new RectF();
    @VisibleForTesting
    final RectF mPrevRootBounds = new RectF();
    @VisibleForTesting
    final RectF mBitmapBounds = new RectF();
    @VisibleForTesting
    final RectF mDrawableBounds = new RectF();
    @VisibleForTesting
    final Matrix mBoundsTransform = new Matrix();
    @VisibleForTesting
    final Matrix mPrevBoundsTransform = new Matrix();
    @VisibleForTesting
    final Matrix mParentTransform = new Matrix();
    @VisibleForTesting
    final Matrix mPrevParentTransform = new Matrix();
    @VisibleForTesting
    final Matrix mInverseParentTransform = new Matrix();
    @VisibleForTesting
    final Matrix mTransform = new Matrix();
    private float mPadding = 0.0f;
    private boolean mScaleDownInsideBorders = false;
    private boolean mPaintFilterBitmap = false;
    private boolean mIsPathDirty = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RoundedDrawable(Drawable drawable) {
        this.mDelegate = drawable;
    }

    @Override // android.graphics.drawable.Drawable
    public void clearColorFilter() {
        this.mDelegate.clearColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("RoundedDrawable#draw");
        }
        this.mDelegate.draw(canvas);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    @Override // android.graphics.drawable.Drawable
    @RequiresApi(api = 19)
    public int getAlpha() {
        return this.mDelegate.getAlpha();
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public int getBorderColor() {
        return this.mBorderColor;
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public float getBorderWidth() {
        return this.mBorderWidth;
    }

    @Override // android.graphics.drawable.Drawable
    @Nullable
    @RequiresApi(api = 21)
    public ColorFilter getColorFilter() {
        return this.mDelegate.getColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mDelegate.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mDelegate.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.mDelegate.getOpacity();
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public float getPadding() {
        return this.mPadding;
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public boolean getPaintFilterBitmap() {
        return this.mPaintFilterBitmap;
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public float[] getRadii() {
        return this.mCornerRadii;
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public boolean getScaleDownInsideBorders() {
        return this.mScaleDownInsideBorders;
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public boolean isCircle() {
        return this.mIsCircle;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        this.mDelegate.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.mDelegate.setAlpha(i2);
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setBorder(int i2, float f2) {
        if (this.mBorderColor == i2 && this.mBorderWidth == f2) {
            return;
        }
        this.mBorderColor = i2;
        this.mBorderWidth = f2;
        this.mIsPathDirty = true;
        invalidateSelf();
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setCircle(boolean z) {
        this.mIsCircle = z;
        this.mIsPathDirty = true;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(int i2, @NonNull PorterDuff.Mode mode) {
        this.mDelegate.setColorFilter(i2, mode);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.mDelegate.setColorFilter(colorFilter);
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setPadding(float f2) {
        if (this.mPadding != f2) {
            this.mPadding = f2;
            this.mIsPathDirty = true;
            invalidateSelf();
        }
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setPaintFilterBitmap(boolean z) {
        if (this.mPaintFilterBitmap != z) {
            this.mPaintFilterBitmap = z;
            invalidateSelf();
        }
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setRadii(float[] fArr) {
        if (fArr == null) {
            Arrays.fill(this.mCornerRadii, 0.0f);
            this.mRadiiNonZero = false;
        } else {
            Preconditions.checkArgument(fArr.length == 8, "radii should have exactly 8 values");
            System.arraycopy(fArr, 0, this.mCornerRadii, 0, 8);
            this.mRadiiNonZero = false;
            for (int i2 = 0; i2 < 8; i2++) {
                this.mRadiiNonZero |= fArr[i2] > 0.0f;
            }
        }
        this.mIsPathDirty = true;
        invalidateSelf();
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setRadius(float f2) {
        int i2 = (f2 > 0.0f ? 1 : (f2 == 0.0f ? 0 : -1));
        Preconditions.checkState(i2 >= 0);
        Arrays.fill(this.mCornerRadii, f2);
        this.mRadiiNonZero = i2 != 0;
        this.mIsPathDirty = true;
        invalidateSelf();
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setScaleDownInsideBorders(boolean z) {
        if (this.mScaleDownInsideBorders != z) {
            this.mScaleDownInsideBorders = z;
            this.mIsPathDirty = true;
            invalidateSelf();
        }
    }

    @Override // com.facebook.drawee.drawable.TransformAwareDrawable
    public void setTransformCallback(@Nullable TransformCallback transformCallback) {
        this.mTransformCallback = transformCallback;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean shouldRound() {
        return this.mIsCircle || this.mRadiiNonZero || this.mBorderWidth > 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updatePath() {
        float[] fArr;
        if (this.mIsPathDirty) {
            this.mBorderPath.reset();
            RectF rectF = this.mRootBounds;
            float f2 = this.mBorderWidth / 2.0f;
            rectF.inset(f2, f2);
            if (this.mIsCircle) {
                this.mBorderPath.addCircle(this.mRootBounds.centerX(), this.mRootBounds.centerY(), Math.min(this.mRootBounds.width(), this.mRootBounds.height()) / 2.0f, Path.Direction.CW);
            } else {
                int i2 = 0;
                while (true) {
                    fArr = this.mBorderRadii;
                    if (i2 >= fArr.length) {
                        break;
                    }
                    fArr[i2] = (this.mCornerRadii[i2] + this.mPadding) - (this.mBorderWidth / 2.0f);
                    i2++;
                }
                this.mBorderPath.addRoundRect(this.mRootBounds, fArr, Path.Direction.CW);
            }
            RectF rectF2 = this.mRootBounds;
            float f3 = (-this.mBorderWidth) / 2.0f;
            rectF2.inset(f3, f3);
            this.mPath.reset();
            float f4 = this.mPadding + (this.mScaleDownInsideBorders ? this.mBorderWidth : 0.0f);
            this.mRootBounds.inset(f4, f4);
            if (this.mIsCircle) {
                this.mPath.addCircle(this.mRootBounds.centerX(), this.mRootBounds.centerY(), Math.min(this.mRootBounds.width(), this.mRootBounds.height()) / 2.0f, Path.Direction.CW);
            } else if (this.mScaleDownInsideBorders) {
                if (this.mInsideBorderRadii == null) {
                    this.mInsideBorderRadii = new float[8];
                }
                for (int i3 = 0; i3 < this.mBorderRadii.length; i3++) {
                    this.mInsideBorderRadii[i3] = this.mCornerRadii[i3] - this.mBorderWidth;
                }
                this.mPath.addRoundRect(this.mRootBounds, this.mInsideBorderRadii, Path.Direction.CW);
            } else {
                this.mPath.addRoundRect(this.mRootBounds, this.mCornerRadii, Path.Direction.CW);
            }
            float f5 = -f4;
            this.mRootBounds.inset(f5, f5);
            this.mPath.setFillType(Path.FillType.WINDING);
            this.mIsPathDirty = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateTransform() {
        Matrix matrix;
        TransformCallback transformCallback = this.mTransformCallback;
        if (transformCallback != null) {
            transformCallback.getTransform(this.mParentTransform);
            this.mTransformCallback.getRootBounds(this.mRootBounds);
        } else {
            this.mParentTransform.reset();
            this.mRootBounds.set(getBounds());
        }
        this.mBitmapBounds.set(0.0f, 0.0f, getIntrinsicWidth(), getIntrinsicHeight());
        this.mDrawableBounds.set(this.mDelegate.getBounds());
        this.mBoundsTransform.setRectToRect(this.mBitmapBounds, this.mDrawableBounds, Matrix.ScaleToFit.FILL);
        if (this.mScaleDownInsideBorders) {
            RectF rectF = this.mInsideBorderBounds;
            if (rectF == null) {
                this.mInsideBorderBounds = new RectF(this.mRootBounds);
            } else {
                rectF.set(this.mRootBounds);
            }
            RectF rectF2 = this.mInsideBorderBounds;
            float f2 = this.mBorderWidth;
            rectF2.inset(f2, f2);
            if (this.mInsideBorderTransform == null) {
                this.mInsideBorderTransform = new Matrix();
            }
            this.mInsideBorderTransform.setRectToRect(this.mRootBounds, this.mInsideBorderBounds, Matrix.ScaleToFit.FILL);
        } else {
            Matrix matrix2 = this.mInsideBorderTransform;
            if (matrix2 != null) {
                matrix2.reset();
            }
        }
        if (!this.mParentTransform.equals(this.mPrevParentTransform) || !this.mBoundsTransform.equals(this.mPrevBoundsTransform) || ((matrix = this.mInsideBorderTransform) != null && !matrix.equals(this.mPrevInsideBorderTransform))) {
            this.mIsShaderTransformDirty = true;
            this.mParentTransform.invert(this.mInverseParentTransform);
            this.mTransform.set(this.mParentTransform);
            if (this.mScaleDownInsideBorders) {
                this.mTransform.postConcat(this.mInsideBorderTransform);
            }
            this.mTransform.preConcat(this.mBoundsTransform);
            this.mPrevParentTransform.set(this.mParentTransform);
            this.mPrevBoundsTransform.set(this.mBoundsTransform);
            if (this.mScaleDownInsideBorders) {
                Matrix matrix3 = this.mPrevInsideBorderTransform;
                if (matrix3 == null) {
                    this.mPrevInsideBorderTransform = new Matrix(this.mInsideBorderTransform);
                } else {
                    matrix3.set(this.mInsideBorderTransform);
                }
            } else {
                Matrix matrix4 = this.mPrevInsideBorderTransform;
                if (matrix4 != null) {
                    matrix4.reset();
                }
            }
        }
        if (this.mRootBounds.equals(this.mPrevRootBounds)) {
            return;
        }
        this.mIsPathDirty = true;
        this.mPrevRootBounds.set(this.mRootBounds);
    }
}
