package com.jingdong.common.model.smarttablayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.common.R;
import com.jingdong.common.model.smarttablayout.SmartTabLayout;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import jd.wjlogin_sdk.util.ReplyCode;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class SmartTabStrip extends LinearLayout {
    private static final int AUTO_WIDTH = -1;
    private static final byte DEFAULT_BOTTOM_BORDER_COLOR_ALPHA = 38;
    private static final int DEFAULT_BOTTOM_BORDER_THICKNESS_DIPS = 2;
    private static final byte DEFAULT_DIVIDER_COLOR_ALPHA = 32;
    private static final float DEFAULT_DIVIDER_HEIGHT = 0.5f;
    private static final int DEFAULT_DIVIDER_THICKNESS_DIPS = 1;
    private static final boolean DEFAULT_DRAW_DECORATION_AFTER_TAB = false;
    private static final float DEFAULT_INDICATOR_CORNER_RADIUS = 0.0f;
    private static final int DEFAULT_INDICATOR_GRAVITY = 0;
    private static final boolean DEFAULT_INDICATOR_IN_CENTER = false;
    private static final boolean DEFAULT_INDICATOR_IN_FRONT = false;
    private static final boolean DEFAULT_INDICATOR_WITHOUT_PADDING = false;
    private static final int DEFAULT_SELECTED_INDICATOR_COLOR = -13388315;
    private static final byte DEFAULT_TOP_BORDER_COLOR_ALPHA = 38;
    private static final int DEFAULT_TOP_BORDER_THICKNESS_DIPS = 0;
    private static final int GRAVITY_BOTTOM = 0;
    private static final int GRAVITY_CENTER = 2;
    private static final int GRAVITY_TOP = 1;
    private static final int SELECTED_INDICATOR_THICKNESS_DIPS = 8;
    private static final int TAB_VIEW_PADDING_DIPS = 16;
    private final Paint borderPaint;
    private final int bottomBorderColor;
    private final int bottomBorderThickness;
    private SmartTabLayout.TabColorizer customTabColorizer;
    private final SimpleTabColorizer defaultTabColorizer;
    private final float dividerHeight;
    private final Paint dividerPaint;
    private final int dividerThickness;
    private final boolean drawDecorationAfterTab;
    private int fixWidth;
    private SmartTabIndicationInterpolator indicationInterpolator;
    private final boolean indicatorAlwaysInCenter;
    private final float indicatorCornerRadius;
    private final int indicatorGravity;
    private final boolean indicatorInFront;
    private final Paint indicatorPaint;
    private final RectF indicatorRectF;
    private final int indicatorThickness;
    private final int indicatorWidth;
    private final boolean indicatorWithoutPadding;
    private boolean isFixedWidth;
    private int lastPosition;
    private int selectedPosition;
    private float selectionOffset;
    private int tabDividerHorizontalPadding;
    private final int topBorderColor;
    private final int topBorderThickness;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class SimpleTabColorizer implements SmartTabLayout.TabColorizer {
        private int[] dividerColors;
        private int[] indicatorColors;

        private SimpleTabColorizer() {
        }

        @Override // com.jingdong.common.model.smarttablayout.SmartTabLayout.TabColorizer
        public final int getDividerColor(int i2) {
            int[] iArr = this.dividerColors;
            return iArr[i2 % iArr.length];
        }

        @Override // com.jingdong.common.model.smarttablayout.SmartTabLayout.TabColorizer
        public final int getIndicatorColor(int i2) {
            int[] iArr = this.indicatorColors;
            return iArr[i2 % iArr.length];
        }

        void setDividerColors(int... iArr) {
            this.dividerColors = iArr;
        }

        void setIndicatorColors(int... iArr) {
            this.indicatorColors = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SmartTabStrip(Context context, AttributeSet attributeSet) {
        super(context);
        int i2;
        char c2;
        int[] intArray;
        int[] intArray2;
        this.indicatorRectF = new RectF();
        this.isFixedWidth = false;
        this.fixWidth = 0;
        setWillNotDraw(false);
        float density = BaseInfo.getDensity();
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16842800, typedValue, true);
        int i3 = typedValue.data;
        float f2 = 0.0f * density;
        int colorAlpha = setColorAlpha(i3, ReplyCode.reply0x26);
        int i4 = (int) f2;
        int colorAlpha2 = setColorAlpha(i3, ReplyCode.reply0x26);
        int colorAlpha3 = setColorAlpha(i3, (byte) 32);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.stl_SmartTabLayout);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.stl_SmartTabLayout_stl_indicatorAlwaysInCenter, false);
        boolean z2 = obtainStyledAttributes.getBoolean(R.styleable.stl_SmartTabLayout_stl_indicatorWithoutPadding, false);
        boolean z3 = obtainStyledAttributes.getBoolean(R.styleable.stl_SmartTabLayout_stl_indicatorInFront, false);
        int indicatorInterpolation = AttrEnumPath.getIndicatorInterpolation(obtainStyledAttributes.getString(R.styleable.stl_SmartTabLayout_stl_indicatorInterpolation));
        int indicatorGravity = AttrEnumPath.getIndicatorGravity(obtainStyledAttributes.getString(R.styleable.stl_SmartTabLayout_stl_indicatorGravity));
        int color = obtainStyledAttributes.getColor(R.styleable.stl_SmartTabLayout_stl_indicatorColor, DEFAULT_SELECTED_INDICATOR_COLOR);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.stl_SmartTabLayout_stl_indicatorColors, -1);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.stl_SmartTabLayout_stl_indicatorThickness, (int) (8.0f * density));
        int layoutDimension = obtainStyledAttributes.getLayoutDimension(R.styleable.stl_SmartTabLayout_stl_indicatorWidth, -1);
        float dimension = obtainStyledAttributes.getDimension(R.styleable.stl_SmartTabLayout_stl_indicatorCornerRadius, f2);
        int color2 = obtainStyledAttributes.getColor(R.styleable.stl_SmartTabLayout_stl_overlineColor, colorAlpha);
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.stl_SmartTabLayout_stl_overlineThickness, i4);
        int color3 = obtainStyledAttributes.getColor(R.styleable.stl_SmartTabLayout_stl_underlineColor, colorAlpha2);
        int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.stl_SmartTabLayout_stl_underlineThickness, (int) (2.0f * density));
        int color4 = obtainStyledAttributes.getColor(R.styleable.stl_SmartTabLayout_stl_dividerColor, colorAlpha3);
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.stl_SmartTabLayout_stl_dividerColors, -1);
        int dimensionPixelSize4 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.stl_SmartTabLayout_stl_dividerThickness, (int) (1.0f * density));
        boolean z4 = obtainStyledAttributes.getBoolean(R.styleable.stl_SmartTabLayout_stl_drawDecorationAfterTab, false);
        int dimensionPixelSize5 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.stl_SmartTabLayout_stl_dividerHorizontalPadding, (int) (density * 16.0f));
        obtainStyledAttributes.recycle();
        if (resourceId == -1) {
            i2 = 1;
            c2 = 0;
            intArray = new int[]{color};
        } else {
            i2 = 1;
            c2 = 0;
            intArray = getResources().getIntArray(resourceId);
        }
        if (resourceId2 == -1) {
            intArray2 = new int[i2];
            intArray2[c2] = color4;
        } else {
            intArray2 = getResources().getIntArray(resourceId2);
        }
        SimpleTabColorizer simpleTabColorizer = new SimpleTabColorizer();
        this.defaultTabColorizer = simpleTabColorizer;
        simpleTabColorizer.setIndicatorColors(intArray);
        simpleTabColorizer.setDividerColors(intArray2);
        this.topBorderThickness = dimensionPixelSize2;
        this.topBorderColor = color2;
        this.bottomBorderThickness = dimensionPixelSize3;
        this.bottomBorderColor = color3;
        this.borderPaint = new Paint(1);
        this.indicatorAlwaysInCenter = z;
        this.indicatorWithoutPadding = z2;
        this.indicatorInFront = z3;
        this.indicatorThickness = dimensionPixelSize;
        this.indicatorWidth = layoutDimension;
        this.indicatorPaint = new Paint(1);
        this.indicatorCornerRadius = dimension;
        this.indicatorGravity = indicatorGravity;
        this.tabDividerHorizontalPadding = dimensionPixelSize5;
        this.dividerHeight = 0.5f;
        Paint paint = new Paint(1);
        this.dividerPaint = paint;
        paint.setStrokeWidth(dimensionPixelSize4);
        this.dividerThickness = dimensionPixelSize4;
        this.drawDecorationAfterTab = z4;
        this.indicationInterpolator = SmartTabIndicationInterpolator.of(indicatorInterpolation);
    }

    private static int blendColors(int i2, int i3, float f2) {
        float f3 = 1.0f - f2;
        return Color.rgb((int) ((Color.red(i2) * f2) + (Color.red(i3) * f3)), (int) ((Color.green(i2) * f2) + (Color.green(i3) * f3)), (int) ((Color.blue(i2) * f2) + (Color.blue(i3) * f3)));
    }

    private void drawDecoration(Canvas canvas) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int height = getHeight();
        int width = getWidth();
        int childCount = getChildCount();
        SmartTabLayout.TabColorizer tabColorizer = getTabColorizer();
        boolean isLayoutRtl = Utils.isLayoutRtl(this);
        if (this.indicatorInFront) {
            drawOverline(canvas, 0, width);
            drawUnderline(canvas, 0, width, height);
        }
        if (childCount > 0) {
            View childAt = getChildAt(this.selectedPosition);
            int start = Utils.getStart(childAt, this.indicatorWithoutPadding);
            int end = Utils.getEnd(childAt, this.indicatorWithoutPadding);
            if (isLayoutRtl) {
                start = end;
                end = start;
            }
            int indicatorColor = tabColorizer.getIndicatorColor(this.selectedPosition);
            float f2 = this.indicatorThickness;
            if (this.selectionOffset <= 0.0f || this.selectedPosition >= getChildCount() - 1) {
                i2 = indicatorColor;
                int i7 = start;
                i3 = end;
                i4 = i7;
            } else {
                int indicatorColor2 = tabColorizer.getIndicatorColor(this.selectedPosition + 1);
                if (indicatorColor != indicatorColor2) {
                    indicatorColor = blendColors(indicatorColor2, indicatorColor, this.selectionOffset);
                }
                float leftEdge = this.indicationInterpolator.getLeftEdge(this.selectionOffset);
                float rightEdge = this.indicationInterpolator.getRightEdge(this.selectionOffset);
                float thickness = this.indicationInterpolator.getThickness(this.selectionOffset);
                View childAt2 = getChildAt(this.selectedPosition + 1);
                int start2 = Utils.getStart(childAt2, this.indicatorWithoutPadding);
                int end2 = Utils.getEnd(childAt2, this.indicatorWithoutPadding);
                if (isLayoutRtl) {
                    i5 = (int) ((end2 * rightEdge) + ((1.0f - rightEdge) * start));
                    i6 = (int) ((start2 * leftEdge) + ((1.0f - leftEdge) * end));
                } else {
                    i5 = (int) ((start2 * leftEdge) + ((1.0f - leftEdge) * start));
                    i6 = (int) ((end2 * rightEdge) + ((1.0f - rightEdge) * end));
                }
                f2 *= thickness;
                i3 = i6;
                i4 = i5;
                i2 = indicatorColor;
            }
            drawIndicator(canvas, i4, i3, height, f2, i2);
        }
        if (!this.indicatorInFront) {
            drawOverline(canvas, 0, width);
            drawUnderline(canvas, 0, getWidth(), height);
        }
        drawSeparator(canvas, height, childCount);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00e6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void drawIndicator(Canvas canvas, int i2, int i3, int i4, float f2, int i5) {
        float f3;
        View childAt;
        float f4;
        View childAt2;
        float f5;
        int i6;
        int i7;
        float leftEdge;
        int i8;
        int i9;
        float f6;
        if (this.indicatorThickness <= 0) {
            return;
        }
        boolean isLayoutRtl = Utils.isLayoutRtl(this);
        int i10 = this.indicatorGravity;
        if (i10 == 1) {
            i4 = this.indicatorThickness;
        } else if (i10 != 2) {
            f3 = i4 - (this.indicatorThickness / 2.0f);
            float f7 = f2 / 2.0f;
            float f8 = f3 - f7;
            float f9 = f3 + f7;
            this.indicatorPaint.setColor(i5);
            if (this.isFixedWidth && this.fixWidth <= 0) {
                i7 = this.tabDividerHorizontalPadding;
            } else {
                int i11 = 0;
                childAt = getChildAt(this.selectedPosition);
                if (childAt instanceof TextView) {
                    f4 = 0.0f;
                } else {
                    i11 = ((TextView) childAt).length();
                    if (this.isFixedWidth) {
                        i9 = DPIUtil.dip2px(7.0f) * i11;
                    } else {
                        i9 = this.fixWidth;
                    }
                    f4 = ((i3 - i2) - i9) / 2;
                }
                childAt2 = getChildAt(this.selectedPosition + 1);
                if (childAt2 instanceof TextView) {
                    f5 = 0.0f;
                } else {
                    int length = ((TextView) childAt2).length();
                    if (this.isFixedWidth) {
                        i8 = length * DPIUtil.dip2px(7.0f);
                    } else {
                        i8 = this.fixWidth;
                    }
                    f5 = ((i3 - i2) - i8) / 2;
                }
                if (this.selectionOffset > 0.0f || this.selectedPosition >= getChildCount() - 1) {
                    if (!this.isFixedWidth) {
                        i6 = i11 * DPIUtil.dip2px(7.0f);
                    } else {
                        i6 = this.fixWidth;
                    }
                    i7 = ((i3 - i2) - i6) / 2;
                } else {
                    if (isLayoutRtl) {
                        leftEdge = f4 - ((f5 - f4) * this.indicationInterpolator.getLeftEdge(this.selectionOffset));
                    } else {
                        leftEdge = f4 + ((f5 - f4) * this.indicationInterpolator.getLeftEdge(this.selectionOffset));
                    }
                    i7 = (int) leftEdge;
                }
            }
            int i12 = i2 + i7;
            int i13 = i3 - i7;
            if (this.indicatorWidth != -1) {
                this.indicatorRectF.set(i12, f8, i13, f9);
            } else {
                this.indicatorRectF.set(i12, f8, i13, f9);
            }
            f6 = this.indicatorCornerRadius;
            if (f6 <= 0.0f) {
                canvas.drawRoundRect(this.indicatorRectF, f6, f6, this.indicatorPaint);
                return;
            } else {
                canvas.drawRect(this.indicatorRectF, this.indicatorPaint);
                return;
            }
        }
        f3 = i4 / 2.0f;
        float f72 = f2 / 2.0f;
        float f82 = f3 - f72;
        float f92 = f3 + f72;
        this.indicatorPaint.setColor(i5);
        if (this.isFixedWidth) {
        }
        int i112 = 0;
        childAt = getChildAt(this.selectedPosition);
        if (childAt instanceof TextView) {
        }
        childAt2 = getChildAt(this.selectedPosition + 1);
        if (childAt2 instanceof TextView) {
        }
        if (this.selectionOffset > 0.0f) {
        }
        if (!this.isFixedWidth) {
        }
        i7 = ((i3 - i2) - i6) / 2;
        int i122 = i2 + i7;
        int i132 = i3 - i7;
        if (this.indicatorWidth != -1) {
        }
        f6 = this.indicatorCornerRadius;
        if (f6 <= 0.0f) {
        }
    }

    private void drawOverline(Canvas canvas, int i2, int i3) {
        if (this.topBorderThickness <= 0) {
            return;
        }
        this.borderPaint.setColor(this.topBorderColor);
        canvas.drawRect(i2, 0.0f, i3, this.topBorderThickness, this.borderPaint);
    }

    private void drawSeparator(Canvas canvas, int i2, int i3) {
        if (this.dividerThickness <= 0) {
            return;
        }
        int min = (int) (Math.min(Math.max(0.0f, this.dividerHeight), 1.0f) * i2);
        SmartTabLayout.TabColorizer tabColorizer = getTabColorizer();
        int i4 = (i2 - min) / 2;
        int i5 = min + i4;
        boolean isLayoutRtl = Utils.isLayoutRtl(this);
        for (int i6 = 0; i6 < i3 - 1; i6++) {
            View childAt = getChildAt(i6);
            int end = Utils.getEnd(childAt);
            int marginEnd = Utils.getMarginEnd(childAt);
            int i7 = isLayoutRtl ? end - marginEnd : end + marginEnd;
            this.dividerPaint.setColor(tabColorizer.getDividerColor(i6));
            float f2 = i7;
            canvas.drawLine(f2, i4, f2, i5, this.dividerPaint);
        }
    }

    private void drawUnderline(Canvas canvas, int i2, int i3, int i4) {
        if (this.bottomBorderThickness <= 0) {
            return;
        }
        this.borderPaint.setColor(this.bottomBorderColor);
        canvas.drawRect(i2, i4 - this.bottomBorderThickness, i3, i4, this.borderPaint);
    }

    private static int setColorAlpha(int i2, byte b) {
        return Color.argb((int) b, Color.red(i2), Color.green(i2), Color.blue(i2));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.drawDecorationAfterTab) {
            drawDecoration(canvas);
        }
    }

    SmartTabLayout.TabColorizer getTabColorizer() {
        SmartTabLayout.TabColorizer tabColorizer = this.customTabColorizer;
        return tabColorizer != null ? tabColorizer : this.defaultTabColorizer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isIndicatorAlwaysInCenter() {
        return this.indicatorAlwaysInCenter;
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.drawDecorationAfterTab) {
            return;
        }
        drawDecoration(canvas);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onViewPagerPageChanged(int i2, float f2) {
        this.selectedPosition = i2;
        this.selectionOffset = f2;
        if (f2 == 0.0f && this.lastPosition != i2) {
            this.lastPosition = i2;
        }
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCustomTabColorizer(SmartTabLayout.TabColorizer tabColorizer) {
        this.customTabColorizer = tabColorizer;
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDividerColors(int... iArr) {
        this.customTabColorizer = null;
        this.defaultTabColorizer.setDividerColors(iArr);
        invalidate();
    }

    public void setFixedWidth(boolean z) {
        this.isFixedWidth = z;
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setIndicationInterpolator(SmartTabIndicationInterpolator smartTabIndicationInterpolator) {
        this.indicationInterpolator = smartTabIndicationInterpolator;
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setSelectedIndicatorColors(int... iArr) {
        this.customTabColorizer = null;
        this.defaultTabColorizer.setIndicatorColors(iArr);
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTabDividerHorizontalPadding(int i2) {
        this.tabDividerHorizontalPadding = i2;
        invalidate();
    }

    public void setFixedWidth(int i2) {
        this.fixWidth = i2;
        invalidate();
    }

    public void setTabDividerHorizontalPadding(boolean z) {
        if (z) {
            this.tabDividerHorizontalPadding = 0;
        }
    }
}
