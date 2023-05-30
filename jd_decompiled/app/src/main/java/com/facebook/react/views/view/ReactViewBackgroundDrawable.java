package com.facebook.react.views.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.core.view.ViewCompat;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.FloatUtil;
import com.facebook.react.uimanager.Spacing;
import com.facebook.yoga.YogaConstants;
import java.util.Arrays;
import java.util.Locale;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class ReactViewBackgroundDrawable extends Drawable {
    private static final int ALL_BITS_SET = -1;
    private static final int ALL_BITS_UNSET = 0;
    private static final int DEFAULT_BORDER_ALPHA = 255;
    private static final int DEFAULT_BORDER_COLOR = -16777216;
    private static final int DEFAULT_BORDER_RGB = 0;
    @Nullable
    private Spacing mBorderAlpha;
    @Nullable
    private float[] mBorderCornerRadii;
    @Nullable
    private Spacing mBorderRGB;
    @Nullable
    private BorderStyle mBorderStyle;
    @Nullable
    private Spacing mBorderWidth;
    @Nullable
    private Path mCenterDrawPath;
    private final Context mContext;
    @Nullable
    private PointF mInnerBottomLeftCorner;
    @Nullable
    private PointF mInnerBottomRightCorner;
    @Nullable
    private Path mInnerClipPathForBorderRadius;
    @Nullable
    private RectF mInnerClipTempRectForBorderRadius;
    @Nullable
    private PointF mInnerTopLeftCorner;
    @Nullable
    private PointF mInnerTopRightCorner;
    private int mLayoutDirection;
    @Nullable
    private Path mOuterClipPathForBorderRadius;
    @Nullable
    private RectF mOuterClipTempRectForBorderRadius;
    @Nullable
    private PathEffect mPathEffectForBorderStyle;
    @Nullable
    private Path mPathForBorder;
    @Nullable
    private Path mPathForBorderRadiusOutline;
    @Nullable
    private RectF mTempRectForBorderRadiusOutline;
    @Nullable
    private RectF mTempRectForCenterDrawPath;
    private boolean mNeedUpdatePathForBorderRadius = false;
    private float mBorderRadius = Float.NaN;
    private final Paint mPaint = new Paint(1);
    private int mColor = 0;
    private int mAlpha = 255;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.facebook.react.views.view.ReactViewBackgroundDrawable$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$views$view$ReactViewBackgroundDrawable$BorderStyle;

        static {
            int[] iArr = new int[BorderStyle.values().length];
            $SwitchMap$com$facebook$react$views$view$ReactViewBackgroundDrawable$BorderStyle = iArr;
            try {
                iArr[BorderStyle.SOLID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$views$view$ReactViewBackgroundDrawable$BorderStyle[BorderStyle.DASHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$views$view$ReactViewBackgroundDrawable$BorderStyle[BorderStyle.DOTTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes12.dex */
    public enum BorderRadiusLocation {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_RIGHT,
        BOTTOM_LEFT,
        TOP_START,
        TOP_END,
        BOTTOM_START,
        BOTTOM_END
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public enum BorderStyle {
        SOLID,
        DASHED,
        DOTTED;

        @Nullable
        public static PathEffect getPathEffect(BorderStyle borderStyle, float f2) {
            int i2 = AnonymousClass1.$SwitchMap$com$facebook$react$views$view$ReactViewBackgroundDrawable$BorderStyle[borderStyle.ordinal()];
            if (i2 == 2) {
                float f3 = f2 * 3.0f;
                return new DashPathEffect(new float[]{f3, f3, f3, f3}, 0.0f);
            } else if (i2 != 3) {
                return null;
            } else {
                return new DashPathEffect(new float[]{f2, f2, f2, f2}, 0.0f);
            }
        }
    }

    public ReactViewBackgroundDrawable(Context context) {
        this.mContext = context;
    }

    private static int colorFromAlphaAndRGBComponents(float f2, float f3) {
        return ((((int) f2) << 24) & (-16777216)) | (((int) f3) & ViewCompat.MEASURED_SIZE_MASK);
    }

    private void drawQuadrilateral(Canvas canvas, int i2, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        if (i2 == 0) {
            return;
        }
        if (this.mPathForBorder == null) {
            this.mPathForBorder = new Path();
        }
        this.mPaint.setColor(i2);
        this.mPathForBorder.reset();
        this.mPathForBorder.moveTo(f2, f3);
        this.mPathForBorder.lineTo(f4, f5);
        this.mPathForBorder.lineTo(f6, f7);
        this.mPathForBorder.lineTo(f8, f9);
        this.mPathForBorder.lineTo(f2, f3);
        canvas.drawPath(this.mPathForBorder, this.mPaint);
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x013a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void drawRectangularBackgroundWithBorders(android.graphics.Canvas r25) {
        /*
            Method dump skipped, instructions count: 460
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.view.ReactViewBackgroundDrawable.drawRectangularBackgroundWithBorders(android.graphics.Canvas):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void drawRoundedBackgroundWithBorders(android.graphics.Canvas r23) {
        /*
            Method dump skipped, instructions count: 457
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.view.ReactViewBackgroundDrawable.drawRoundedBackgroundWithBorders(android.graphics.Canvas):void");
    }

    private static int fastBorderCompatibleColorOrZero(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        int i10 = (i5 > 0 ? i9 : -1) & (i2 > 0 ? i6 : -1) & (i3 > 0 ? i7 : -1) & (i4 > 0 ? i8 : -1);
        if (i2 <= 0) {
            i6 = 0;
        }
        if (i3 <= 0) {
            i7 = 0;
        }
        int i11 = i6 | i7;
        if (i4 <= 0) {
            i8 = 0;
        }
        int i12 = i11 | i8;
        if (i5 <= 0) {
            i9 = 0;
        }
        if (i10 == (i12 | i9)) {
            return i10;
        }
        return 0;
    }

    private int getBorderColor(int i2) {
        Spacing spacing = this.mBorderRGB;
        float f2 = spacing != null ? spacing.get(i2) : 0.0f;
        Spacing spacing2 = this.mBorderAlpha;
        return colorFromAlphaAndRGBComponents(spacing2 != null ? spacing2.get(i2) : 255.0f, f2);
    }

    private int getBorderWidth(int i2) {
        Spacing spacing = this.mBorderWidth;
        if (spacing == null) {
            return 0;
        }
        float f2 = spacing.get(i2);
        if (YogaConstants.isUndefined(f2)) {
            return -1;
        }
        return Math.round(f2);
    }

    private static void getEllipseIntersectionWithLine(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, PointF pointF) {
        double d9 = (d + d3) / 2.0d;
        double d10 = (d2 + d4) / 2.0d;
        double d11 = d5 - d9;
        double d12 = d6 - d10;
        double abs = Math.abs(d3 - d) / 2.0d;
        double abs2 = Math.abs(d4 - d2) / 2.0d;
        double d13 = ((d8 - d10) - d12) / ((d7 - d9) - d11);
        double d14 = d12 - (d11 * d13);
        double d15 = abs2 * abs2;
        double d16 = abs * abs;
        double d17 = d15 + (d16 * d13 * d13);
        double d18 = abs * 2.0d * abs * d14 * d13;
        double d19 = (-(d16 * ((d14 * d14) - d15))) / d17;
        double d20 = d17 * 2.0d;
        double sqrt = ((-d18) / d20) - Math.sqrt(d19 + Math.pow(d18 / d20, 2.0d));
        double d21 = sqrt + d9;
        double d22 = (d13 * sqrt) + d14 + d10;
        if (Double.isNaN(d21) || Double.isNaN(d22)) {
            return;
        }
        pointF.x = (float) d21;
        pointF.y = (float) d22;
    }

    private boolean isBorderColorDefined(int i2) {
        Spacing spacing = this.mBorderRGB;
        float f2 = spacing != null ? spacing.get(i2) : Float.NaN;
        Spacing spacing2 = this.mBorderAlpha;
        return (YogaConstants.isUndefined(f2) || YogaConstants.isUndefined(spacing2 != null ? spacing2.get(i2) : Float.NaN)) ? false : true;
    }

    private void setBorderAlpha(int i2, float f2) {
        if (this.mBorderAlpha == null) {
            this.mBorderAlpha = new Spacing(255.0f);
        }
        if (FloatUtil.floatsEqual(this.mBorderAlpha.getRaw(i2), f2)) {
            return;
        }
        this.mBorderAlpha.set(i2, f2);
        invalidateSelf();
    }

    private void setBorderRGB(int i2, float f2) {
        if (this.mBorderRGB == null) {
            this.mBorderRGB = new Spacing(0.0f);
        }
        if (FloatUtil.floatsEqual(this.mBorderRGB.getRaw(i2), f2)) {
            return;
        }
        this.mBorderRGB.set(i2, f2);
        invalidateSelf();
    }

    private void updatePath() {
        if (this.mNeedUpdatePathForBorderRadius) {
            this.mNeedUpdatePathForBorderRadius = false;
            if (this.mInnerClipPathForBorderRadius == null) {
                this.mInnerClipPathForBorderRadius = new Path();
            }
            if (this.mOuterClipPathForBorderRadius == null) {
                this.mOuterClipPathForBorderRadius = new Path();
            }
            if (this.mPathForBorderRadiusOutline == null) {
                this.mPathForBorderRadiusOutline = new Path();
            }
            if (this.mCenterDrawPath == null) {
                this.mCenterDrawPath = new Path();
            }
            if (this.mInnerClipTempRectForBorderRadius == null) {
                this.mInnerClipTempRectForBorderRadius = new RectF();
            }
            if (this.mOuterClipTempRectForBorderRadius == null) {
                this.mOuterClipTempRectForBorderRadius = new RectF();
            }
            if (this.mTempRectForBorderRadiusOutline == null) {
                this.mTempRectForBorderRadiusOutline = new RectF();
            }
            if (this.mTempRectForCenterDrawPath == null) {
                this.mTempRectForCenterDrawPath = new RectF();
            }
            this.mInnerClipPathForBorderRadius.reset();
            this.mOuterClipPathForBorderRadius.reset();
            this.mPathForBorderRadiusOutline.reset();
            this.mCenterDrawPath.reset();
            this.mInnerClipTempRectForBorderRadius.set(getBounds());
            this.mOuterClipTempRectForBorderRadius.set(getBounds());
            this.mTempRectForBorderRadiusOutline.set(getBounds());
            this.mTempRectForCenterDrawPath.set(getBounds());
            float fullBorderWidth = getFullBorderWidth();
            if (fullBorderWidth > 0.0f) {
                float f2 = fullBorderWidth * 0.5f;
                this.mTempRectForCenterDrawPath.inset(f2, f2);
            }
            RectF directionAwareBorderInsets = getDirectionAwareBorderInsets();
            RectF rectF = this.mInnerClipTempRectForBorderRadius;
            rectF.top += directionAwareBorderInsets.top;
            rectF.bottom -= directionAwareBorderInsets.bottom;
            rectF.left += directionAwareBorderInsets.left;
            rectF.right -= directionAwareBorderInsets.right;
            float fullBorderRadius = getFullBorderRadius();
            float borderRadiusOrDefaultTo = getBorderRadiusOrDefaultTo(fullBorderRadius, BorderRadiusLocation.TOP_LEFT);
            float borderRadiusOrDefaultTo2 = getBorderRadiusOrDefaultTo(fullBorderRadius, BorderRadiusLocation.TOP_RIGHT);
            float borderRadiusOrDefaultTo3 = getBorderRadiusOrDefaultTo(fullBorderRadius, BorderRadiusLocation.BOTTOM_LEFT);
            float borderRadiusOrDefaultTo4 = getBorderRadiusOrDefaultTo(fullBorderRadius, BorderRadiusLocation.BOTTOM_RIGHT);
            if (Build.VERSION.SDK_INT >= 17) {
                boolean z = getResolvedLayoutDirection() == 1;
                float borderRadius = getBorderRadius(BorderRadiusLocation.TOP_START);
                float borderRadius2 = getBorderRadius(BorderRadiusLocation.TOP_END);
                float borderRadius3 = getBorderRadius(BorderRadiusLocation.BOTTOM_START);
                float borderRadius4 = getBorderRadius(BorderRadiusLocation.BOTTOM_END);
                if (I18nUtil.getInstance().doLeftAndRightSwapInRTL(this.mContext)) {
                    if (!YogaConstants.isUndefined(borderRadius)) {
                        borderRadiusOrDefaultTo = borderRadius;
                    }
                    if (!YogaConstants.isUndefined(borderRadius2)) {
                        borderRadiusOrDefaultTo2 = borderRadius2;
                    }
                    if (!YogaConstants.isUndefined(borderRadius3)) {
                        borderRadiusOrDefaultTo3 = borderRadius3;
                    }
                    if (!YogaConstants.isUndefined(borderRadius4)) {
                        borderRadiusOrDefaultTo4 = borderRadius4;
                    }
                    float f3 = z ? borderRadiusOrDefaultTo2 : borderRadiusOrDefaultTo;
                    if (z) {
                        borderRadiusOrDefaultTo2 = borderRadiusOrDefaultTo;
                    }
                    float f4 = z ? borderRadiusOrDefaultTo4 : borderRadiusOrDefaultTo3;
                    if (z) {
                        borderRadiusOrDefaultTo4 = borderRadiusOrDefaultTo3;
                    }
                    borderRadiusOrDefaultTo3 = f4;
                    borderRadiusOrDefaultTo = f3;
                } else {
                    float f5 = z ? borderRadius2 : borderRadius;
                    if (!z) {
                        borderRadius = borderRadius2;
                    }
                    float f6 = z ? borderRadius4 : borderRadius3;
                    if (!z) {
                        borderRadius3 = borderRadius4;
                    }
                    if (!YogaConstants.isUndefined(f5)) {
                        borderRadiusOrDefaultTo = f5;
                    }
                    if (!YogaConstants.isUndefined(borderRadius)) {
                        borderRadiusOrDefaultTo2 = borderRadius;
                    }
                    if (!YogaConstants.isUndefined(f6)) {
                        borderRadiusOrDefaultTo3 = f6;
                    }
                    if (!YogaConstants.isUndefined(borderRadius3)) {
                        borderRadiusOrDefaultTo4 = borderRadius3;
                    }
                }
            }
            float max = Math.max(borderRadiusOrDefaultTo - directionAwareBorderInsets.left, 0.0f);
            float max2 = Math.max(borderRadiusOrDefaultTo - directionAwareBorderInsets.top, 0.0f);
            float max3 = Math.max(borderRadiusOrDefaultTo2 - directionAwareBorderInsets.right, 0.0f);
            float max4 = Math.max(borderRadiusOrDefaultTo2 - directionAwareBorderInsets.top, 0.0f);
            float max5 = Math.max(borderRadiusOrDefaultTo4 - directionAwareBorderInsets.right, 0.0f);
            float max6 = Math.max(borderRadiusOrDefaultTo4 - directionAwareBorderInsets.bottom, 0.0f);
            float max7 = Math.max(borderRadiusOrDefaultTo3 - directionAwareBorderInsets.left, 0.0f);
            float max8 = Math.max(borderRadiusOrDefaultTo3 - directionAwareBorderInsets.bottom, 0.0f);
            float f7 = borderRadiusOrDefaultTo3;
            float f8 = borderRadiusOrDefaultTo4;
            this.mInnerClipPathForBorderRadius.addRoundRect(this.mInnerClipTempRectForBorderRadius, new float[]{max, max2, max3, max4, max5, max6, max7, max8}, Path.Direction.CW);
            this.mOuterClipPathForBorderRadius.addRoundRect(this.mOuterClipTempRectForBorderRadius, new float[]{borderRadiusOrDefaultTo, borderRadiusOrDefaultTo, borderRadiusOrDefaultTo2, borderRadiusOrDefaultTo2, f8, f8, f7, f7}, Path.Direction.CW);
            Spacing spacing = this.mBorderWidth;
            float f9 = spacing != null ? spacing.get(8) / 2.0f : 0.0f;
            float f10 = borderRadiusOrDefaultTo + f9;
            float f11 = borderRadiusOrDefaultTo2 + f9;
            float f12 = f8 + f9;
            float f13 = f7 + f9;
            this.mPathForBorderRadiusOutline.addRoundRect(this.mTempRectForBorderRadiusOutline, new float[]{f10, f10, f11, f11, f12, f12, f13, f13}, Path.Direction.CW);
            this.mCenterDrawPath.addRoundRect(this.mTempRectForCenterDrawPath, new float[]{max + f9, max2 + f9, max3 + f9, max4 + f9, max5 + f9, max6 + f9, max7 + f9, max8 + f9}, Path.Direction.CW);
            if (this.mInnerTopLeftCorner == null) {
                this.mInnerTopLeftCorner = new PointF();
            }
            PointF pointF = this.mInnerTopLeftCorner;
            RectF rectF2 = this.mInnerClipTempRectForBorderRadius;
            float f14 = rectF2.left;
            pointF.x = f14;
            float f15 = rectF2.top;
            pointF.y = f15;
            RectF rectF3 = this.mOuterClipTempRectForBorderRadius;
            getEllipseIntersectionWithLine(f14, f15, (max * 2.0f) + f14, (max2 * 2.0f) + f15, rectF3.left, rectF3.top, f14, f15, pointF);
            if (this.mInnerBottomLeftCorner == null) {
                this.mInnerBottomLeftCorner = new PointF();
            }
            PointF pointF2 = this.mInnerBottomLeftCorner;
            RectF rectF4 = this.mInnerClipTempRectForBorderRadius;
            float f16 = rectF4.left;
            pointF2.x = f16;
            float f17 = rectF4.bottom;
            pointF2.y = f17;
            RectF rectF5 = this.mOuterClipTempRectForBorderRadius;
            getEllipseIntersectionWithLine(f16, f17 - (max8 * 2.0f), (max7 * 2.0f) + f16, f17, rectF5.left, rectF5.bottom, f16, f17, pointF2);
            if (this.mInnerTopRightCorner == null) {
                this.mInnerTopRightCorner = new PointF();
            }
            PointF pointF3 = this.mInnerTopRightCorner;
            RectF rectF6 = this.mInnerClipTempRectForBorderRadius;
            float f18 = rectF6.right;
            pointF3.x = f18;
            float f19 = rectF6.top;
            pointF3.y = f19;
            RectF rectF7 = this.mOuterClipTempRectForBorderRadius;
            getEllipseIntersectionWithLine(f18 - (max3 * 2.0f), f19, f18, (max4 * 2.0f) + f19, rectF7.right, rectF7.top, f18, f19, pointF3);
            if (this.mInnerBottomRightCorner == null) {
                this.mInnerBottomRightCorner = new PointF();
            }
            PointF pointF4 = this.mInnerBottomRightCorner;
            RectF rectF8 = this.mInnerClipTempRectForBorderRadius;
            float f20 = rectF8.right;
            pointF4.x = f20;
            float f21 = rectF8.bottom;
            pointF4.y = f21;
            RectF rectF9 = this.mOuterClipTempRectForBorderRadius;
            getEllipseIntersectionWithLine(f20 - (max5 * 2.0f), f21 - (max6 * 2.0f), f20, f21, rectF9.right, rectF9.bottom, f20, f21, pointF4);
        }
    }

    private void updatePathEffect() {
        BorderStyle borderStyle = this.mBorderStyle;
        PathEffect pathEffect = borderStyle != null ? BorderStyle.getPathEffect(borderStyle, getFullBorderWidth()) : null;
        this.mPathEffectForBorderStyle = pathEffect;
        this.mPaint.setPathEffect(pathEffect);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        updatePathEffect();
        if (!hasRoundedBorders()) {
            drawRectangularBackgroundWithBorders(canvas);
        } else {
            drawRoundedBackgroundWithBorders(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mAlpha;
    }

    public float getBorderRadius(BorderRadiusLocation borderRadiusLocation) {
        return getBorderRadiusOrDefaultTo(Float.NaN, borderRadiusLocation);
    }

    public float getBorderRadiusOrDefaultTo(float f2, BorderRadiusLocation borderRadiusLocation) {
        float[] fArr = this.mBorderCornerRadii;
        if (fArr == null) {
            return f2;
        }
        float f3 = fArr[borderRadiusLocation.ordinal()];
        return YogaConstants.isUndefined(f3) ? f2 : f3;
    }

    public float getBorderWidthOrDefaultTo(float f2, int i2) {
        Spacing spacing = this.mBorderWidth;
        if (spacing == null) {
            return f2;
        }
        float raw = spacing.getRaw(i2);
        return YogaConstants.isUndefined(raw) ? f2 : raw;
    }

    @VisibleForTesting
    public int getColor() {
        return this.mColor;
    }

    public RectF getDirectionAwareBorderInsets() {
        float borderWidthOrDefaultTo = getBorderWidthOrDefaultTo(0.0f, 8);
        float borderWidthOrDefaultTo2 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 1);
        float borderWidthOrDefaultTo3 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 3);
        float borderWidthOrDefaultTo4 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 0);
        float borderWidthOrDefaultTo5 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 2);
        if (Build.VERSION.SDK_INT >= 17 && this.mBorderWidth != null) {
            boolean z = getResolvedLayoutDirection() == 1;
            float raw = this.mBorderWidth.getRaw(4);
            float raw2 = this.mBorderWidth.getRaw(5);
            if (I18nUtil.getInstance().doLeftAndRightSwapInRTL(this.mContext)) {
                if (!YogaConstants.isUndefined(raw)) {
                    borderWidthOrDefaultTo4 = raw;
                }
                if (!YogaConstants.isUndefined(raw2)) {
                    borderWidthOrDefaultTo5 = raw2;
                }
                float f2 = z ? borderWidthOrDefaultTo5 : borderWidthOrDefaultTo4;
                if (z) {
                    borderWidthOrDefaultTo5 = borderWidthOrDefaultTo4;
                }
                borderWidthOrDefaultTo4 = f2;
            } else {
                float f3 = z ? raw2 : raw;
                if (!z) {
                    raw = raw2;
                }
                if (!YogaConstants.isUndefined(f3)) {
                    borderWidthOrDefaultTo4 = f3;
                }
                if (!YogaConstants.isUndefined(raw)) {
                    borderWidthOrDefaultTo5 = raw;
                }
            }
        }
        return new RectF(borderWidthOrDefaultTo4, borderWidthOrDefaultTo2, borderWidthOrDefaultTo5, borderWidthOrDefaultTo3);
    }

    public float getFullBorderRadius() {
        if (YogaConstants.isUndefined(this.mBorderRadius)) {
            return 0.0f;
        }
        return this.mBorderRadius;
    }

    public float getFullBorderWidth() {
        Spacing spacing = this.mBorderWidth;
        if (spacing == null || YogaConstants.isUndefined(spacing.getRaw(8))) {
            return 0.0f;
        }
        return this.mBorderWidth.getRaw(8);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return ColorUtil.getOpacityFromColor(ColorUtil.multiplyColorAlpha(this.mColor, this.mAlpha));
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        if (Build.VERSION.SDK_INT < 21) {
            super.getOutline(outline);
        } else if ((!YogaConstants.isUndefined(this.mBorderRadius) && this.mBorderRadius > 0.0f) || this.mBorderCornerRadii != null) {
            updatePath();
            outline.setConvexPath(this.mPathForBorderRadiusOutline);
        } else {
            outline.setRect(getBounds());
        }
    }

    public int getResolvedLayoutDirection() {
        return this.mLayoutDirection;
    }

    public boolean hasRoundedBorders() {
        if (YogaConstants.isUndefined(this.mBorderRadius) || this.mBorderRadius <= 0.0f) {
            float[] fArr = this.mBorderCornerRadii;
            if (fArr != null) {
                for (float f2 : fArr) {
                    if (!YogaConstants.isUndefined(f2) && f2 > 0.0f) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mNeedUpdatePathForBorderRadius = true;
    }

    public boolean onResolvedLayoutDirectionChanged(int i2) {
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        if (i2 != this.mAlpha) {
            this.mAlpha = i2;
            invalidateSelf();
        }
    }

    public void setBorderColor(int i2, float f2, float f3) {
        setBorderRGB(i2, f2);
        setBorderAlpha(i2, f3);
    }

    public void setBorderStyle(@Nullable String str) {
        BorderStyle valueOf = str == null ? null : BorderStyle.valueOf(str.toUpperCase(Locale.US));
        if (this.mBorderStyle != valueOf) {
            this.mBorderStyle = valueOf;
            this.mNeedUpdatePathForBorderRadius = true;
            invalidateSelf();
        }
    }

    public void setBorderWidth(int i2, float f2) {
        if (this.mBorderWidth == null) {
            this.mBorderWidth = new Spacing();
        }
        if (FloatUtil.floatsEqual(this.mBorderWidth.getRaw(i2), f2)) {
            return;
        }
        this.mBorderWidth.set(i2, f2);
        if (i2 == 0 || i2 == 1 || i2 == 2 || i2 == 3 || i2 == 4 || i2 == 5 || i2 == 8) {
            this.mNeedUpdatePathForBorderRadius = true;
        }
        invalidateSelf();
    }

    public void setColor(int i2) {
        this.mColor = i2;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void setRadius(float f2) {
        if (FloatUtil.floatsEqual(this.mBorderRadius, f2)) {
            return;
        }
        this.mBorderRadius = f2;
        this.mNeedUpdatePathForBorderRadius = true;
        invalidateSelf();
    }

    public boolean setResolvedLayoutDirection(int i2) {
        if (this.mLayoutDirection != i2) {
            this.mLayoutDirection = i2;
            return onResolvedLayoutDirectionChanged(i2);
        }
        return false;
    }

    public void setRadius(float f2, int i2) {
        if (this.mBorderCornerRadii == null) {
            float[] fArr = new float[8];
            this.mBorderCornerRadii = fArr;
            Arrays.fill(fArr, Float.NaN);
        }
        if (FloatUtil.floatsEqual(this.mBorderCornerRadii[i2], f2)) {
            return;
        }
        this.mBorderCornerRadii[i2] = f2;
        this.mNeedUpdatePathForBorderRadius = true;
        invalidateSelf();
    }
}
