package com.google.android.material.shape;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import androidx.annotation.ColorInt;
import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.TintAwareDrawable;
import com.google.android.material.internal.Experimental;

@Experimental("The shapes API is currently experimental and subject to change")
/* loaded from: classes12.dex */
public class MaterialShapeDrawable extends Drawable implements TintAwareDrawable {
    private int alpha;
    private final ShapePath[] cornerPaths;
    private final Matrix[] cornerTransforms;
    private final Matrix[] edgeTransforms;
    private float interpolation;
    private final Matrix matrix;
    private final Paint paint;
    private Paint.Style paintStyle;
    private final Path path;
    private final PointF pointF;
    private float scale;
    private final float[] scratch;
    private final float[] scratch2;
    private final Region scratchRegion;
    private int shadowColor;
    private int shadowElevation;
    private boolean shadowEnabled;
    private int shadowRadius;
    private final ShapePath shapePath;
    @Nullable
    private ShapePathModel shapedViewModel;
    private float strokeWidth;
    @Nullable
    private PorterDuffColorFilter tintFilter;
    private ColorStateList tintList;
    private PorterDuff.Mode tintMode;
    private final Region transparentRegion;
    private boolean useTintColorForShadow;

    public MaterialShapeDrawable() {
        this(null);
    }

    private float angleOfCorner(int i2, int i3, int i4) {
        getCoordinatesOfCorner(((i2 - 1) + 4) % 4, i3, i4, this.pointF);
        PointF pointF = this.pointF;
        float f2 = pointF.x;
        float f3 = pointF.y;
        getCoordinatesOfCorner((i2 + 1) % 4, i3, i4, pointF);
        PointF pointF2 = this.pointF;
        float f4 = pointF2.x;
        float f5 = pointF2.y;
        getCoordinatesOfCorner(i2, i3, i4, pointF2);
        PointF pointF3 = this.pointF;
        float f6 = pointF3.x;
        float atan2 = ((float) Math.atan2(f3 - r6, f2 - f6)) - ((float) Math.atan2(f5 - pointF3.y, f4 - f6));
        if (atan2 < 0.0f) {
            double d = atan2;
            Double.isNaN(d);
            return (float) (d + 6.283185307179586d);
        }
        return atan2;
    }

    private float angleOfEdge(int i2, int i3, int i4) {
        getCoordinatesOfCorner(i2, i3, i4, this.pointF);
        PointF pointF = this.pointF;
        float f2 = pointF.x;
        float f3 = pointF.y;
        getCoordinatesOfCorner((i2 + 1) % 4, i3, i4, pointF);
        PointF pointF2 = this.pointF;
        return (float) Math.atan2(pointF2.y - f3, pointF2.x - f2);
    }

    private void appendCornerPath(int i2, Path path) {
        float[] fArr = this.scratch;
        ShapePath[] shapePathArr = this.cornerPaths;
        fArr[0] = shapePathArr[i2].startX;
        fArr[1] = shapePathArr[i2].startY;
        this.cornerTransforms[i2].mapPoints(fArr);
        if (i2 == 0) {
            float[] fArr2 = this.scratch;
            path.moveTo(fArr2[0], fArr2[1]);
        } else {
            float[] fArr3 = this.scratch;
            path.lineTo(fArr3[0], fArr3[1]);
        }
        this.cornerPaths[i2].applyToPath(this.cornerTransforms[i2], path);
    }

    private void appendEdgePath(int i2, Path path) {
        int i3 = (i2 + 1) % 4;
        float[] fArr = this.scratch;
        ShapePath[] shapePathArr = this.cornerPaths;
        fArr[0] = shapePathArr[i2].endX;
        fArr[1] = shapePathArr[i2].endY;
        this.cornerTransforms[i2].mapPoints(fArr);
        float[] fArr2 = this.scratch2;
        ShapePath[] shapePathArr2 = this.cornerPaths;
        fArr2[0] = shapePathArr2[i3].startX;
        fArr2[1] = shapePathArr2[i3].startY;
        this.cornerTransforms[i3].mapPoints(fArr2);
        float f2 = this.scratch[0];
        float[] fArr3 = this.scratch2;
        this.shapePath.reset(0.0f, 0.0f);
        getEdgeTreatmentForIndex(i2).getEdgePath((float) Math.hypot(f2 - fArr3[0], r0[1] - fArr3[1]), this.interpolation, this.shapePath);
        this.shapePath.applyToPath(this.edgeTransforms[i2], path);
    }

    private void getCoordinatesOfCorner(int i2, int i3, int i4, PointF pointF) {
        if (i2 == 1) {
            pointF.set(i3, 0.0f);
        } else if (i2 == 2) {
            pointF.set(i3, i4);
        } else if (i2 != 3) {
            pointF.set(0.0f, 0.0f);
        } else {
            pointF.set(0.0f, i4);
        }
    }

    private CornerTreatment getCornerTreatmentForIndex(int i2) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    return this.shapedViewModel.getTopLeftCorner();
                }
                return this.shapedViewModel.getBottomLeftCorner();
            }
            return this.shapedViewModel.getBottomRightCorner();
        }
        return this.shapedViewModel.getTopRightCorner();
    }

    private EdgeTreatment getEdgeTreatmentForIndex(int i2) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    return this.shapedViewModel.getTopEdge();
                }
                return this.shapedViewModel.getLeftEdge();
            }
            return this.shapedViewModel.getBottomEdge();
        }
        return this.shapedViewModel.getRightEdge();
    }

    private void getPath(int i2, int i3, Path path) {
        getPathForSize(i2, i3, path);
        if (this.scale == 1.0f) {
            return;
        }
        this.matrix.reset();
        Matrix matrix = this.matrix;
        float f2 = this.scale;
        matrix.setScale(f2, f2, i2 / 2, i3 / 2);
        path.transform(this.matrix);
    }

    private static int modulateAlpha(int i2, int i3) {
        return (i2 * (i3 + (i3 >>> 7))) >>> 8;
    }

    private void setCornerPathAndTransform(int i2, int i3, int i4) {
        getCoordinatesOfCorner(i2, i3, i4, this.pointF);
        getCornerTreatmentForIndex(i2).getCornerPath(angleOfCorner(i2, i3, i4), this.interpolation, this.cornerPaths[i2]);
        this.cornerTransforms[i2].reset();
        Matrix matrix = this.cornerTransforms[i2];
        PointF pointF = this.pointF;
        matrix.setTranslate(pointF.x, pointF.y);
        this.cornerTransforms[i2].preRotate((float) Math.toDegrees(angleOfEdge(((i2 - 1) + 4) % 4, i3, i4) + 1.5707964f));
    }

    private void setEdgeTransform(int i2, int i3, int i4) {
        float[] fArr = this.scratch;
        ShapePath[] shapePathArr = this.cornerPaths;
        fArr[0] = shapePathArr[i2].endX;
        fArr[1] = shapePathArr[i2].endY;
        this.cornerTransforms[i2].mapPoints(fArr);
        float angleOfEdge = angleOfEdge(i2, i3, i4);
        this.edgeTransforms[i2].reset();
        Matrix matrix = this.edgeTransforms[i2];
        float[] fArr2 = this.scratch;
        matrix.setTranslate(fArr2[0], fArr2[1]);
        this.edgeTransforms[i2].preRotate((float) Math.toDegrees(angleOfEdge));
    }

    private void updateTintFilter() {
        ColorStateList colorStateList = this.tintList;
        if (colorStateList != null && this.tintMode != null) {
            int colorForState = colorStateList.getColorForState(getState(), 0);
            this.tintFilter = new PorterDuffColorFilter(colorForState, this.tintMode);
            if (this.useTintColorForShadow) {
                this.shadowColor = colorForState;
                return;
            }
            return;
        }
        this.tintFilter = null;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.paint.setColorFilter(this.tintFilter);
        int alpha = this.paint.getAlpha();
        this.paint.setAlpha(modulateAlpha(alpha, this.alpha));
        this.paint.setStrokeWidth(this.strokeWidth);
        this.paint.setStyle(this.paintStyle);
        int i2 = this.shadowElevation;
        if (i2 > 0 && this.shadowEnabled) {
            this.paint.setShadowLayer(this.shadowRadius, 0.0f, i2, this.shadowColor);
        }
        if (this.shapedViewModel != null) {
            getPath(canvas.getWidth(), canvas.getHeight(), this.path);
            canvas.drawPath(this.path, this.paint);
        } else {
            canvas.drawRect(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), this.paint);
        }
        this.paint.setAlpha(alpha);
    }

    public float getInterpolation() {
        return this.interpolation;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public Paint.Style getPaintStyle() {
        return this.paintStyle;
    }

    public void getPathForSize(int i2, int i3, Path path) {
        path.rewind();
        if (this.shapedViewModel == null) {
            return;
        }
        for (int i4 = 0; i4 < 4; i4++) {
            setCornerPathAndTransform(i4, i2, i3);
            setEdgeTransform(i4, i2, i3);
        }
        for (int i5 = 0; i5 < 4; i5++) {
            appendCornerPath(i5, path);
            appendEdgePath(i5, path);
        }
        path.close();
    }

    public float getScale() {
        return this.scale;
    }

    public int getShadowElevation() {
        return this.shadowElevation;
    }

    public int getShadowRadius() {
        return this.shadowRadius;
    }

    @Nullable
    public ShapePathModel getShapedViewModel() {
        return this.shapedViewModel;
    }

    public float getStrokeWidth() {
        return this.strokeWidth;
    }

    public ColorStateList getTintList() {
        return this.tintList;
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        Rect bounds = getBounds();
        this.transparentRegion.set(bounds);
        getPath(bounds.width(), bounds.height(), this.path);
        this.scratchRegion.setPath(this.path, this.transparentRegion);
        this.transparentRegion.op(this.scratchRegion, Region.Op.DIFFERENCE);
        return this.transparentRegion;
    }

    public boolean isPointInTransparentRegion(int i2, int i3) {
        return getTransparentRegion().contains(i2, i3);
    }

    public boolean isShadowEnabled() {
        return this.shadowEnabled;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(@IntRange(from = 0, to = 255) int i2) {
        this.alpha = i2;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void setInterpolation(float f2) {
        this.interpolation = f2;
        invalidateSelf();
    }

    public void setPaintStyle(Paint.Style style) {
        this.paintStyle = style;
        invalidateSelf();
    }

    public void setScale(float f2) {
        this.scale = f2;
        invalidateSelf();
    }

    public void setShadowColor(int i2) {
        this.shadowColor = i2;
        this.useTintColorForShadow = false;
        invalidateSelf();
    }

    public void setShadowElevation(int i2) {
        this.shadowElevation = i2;
        invalidateSelf();
    }

    public void setShadowEnabled(boolean z) {
        this.shadowEnabled = z;
        invalidateSelf();
    }

    public void setShadowRadius(int i2) {
        this.shadowRadius = i2;
        invalidateSelf();
    }

    public void setShapedViewModel(ShapePathModel shapePathModel) {
        this.shapedViewModel = shapePathModel;
        invalidateSelf();
    }

    public void setStrokeWidth(float f2) {
        this.strokeWidth = f2;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTint(@ColorInt int i2) {
        setTintList(ColorStateList.valueOf(i2));
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintList(ColorStateList colorStateList) {
        this.tintList = colorStateList;
        updateTintFilter();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintMode(PorterDuff.Mode mode) {
        this.tintMode = mode;
        updateTintFilter();
        invalidateSelf();
    }

    public void setUseTintColorForShadow(boolean z) {
        this.useTintColorForShadow = z;
        invalidateSelf();
    }

    public MaterialShapeDrawable(@Nullable ShapePathModel shapePathModel) {
        this.paint = new Paint();
        this.cornerTransforms = new Matrix[4];
        this.edgeTransforms = new Matrix[4];
        this.cornerPaths = new ShapePath[4];
        this.matrix = new Matrix();
        this.path = new Path();
        this.pointF = new PointF();
        this.shapePath = new ShapePath();
        this.transparentRegion = new Region();
        this.scratchRegion = new Region();
        this.scratch = new float[2];
        this.scratch2 = new float[2];
        this.shapedViewModel = null;
        this.shadowEnabled = false;
        this.useTintColorForShadow = false;
        this.interpolation = 1.0f;
        this.shadowColor = -16777216;
        this.shadowElevation = 5;
        this.shadowRadius = 10;
        this.alpha = 255;
        this.scale = 1.0f;
        this.strokeWidth = 0.0f;
        this.paintStyle = Paint.Style.FILL_AND_STROKE;
        this.tintMode = PorterDuff.Mode.SRC_IN;
        this.tintList = null;
        this.shapedViewModel = shapePathModel;
        for (int i2 = 0; i2 < 4; i2++) {
            this.cornerTransforms[i2] = new Matrix();
            this.edgeTransforms[i2] = new Matrix();
            this.cornerPaths[i2] = new ShapePath();
        }
    }
}
