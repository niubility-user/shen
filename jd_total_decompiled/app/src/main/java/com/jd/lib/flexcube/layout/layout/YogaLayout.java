package com.jd.lib.flexcube.layout.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaDirection;
import com.facebook.yoga.YogaDisplay;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaFlexDirection;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaMeasureOutput;
import com.facebook.yoga.YogaNode;
import com.facebook.yoga.YogaOverflow;
import com.facebook.yoga.YogaPositionType;
import com.facebook.yoga.YogaWrap;
import com.jd.lib.flexcube.R;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes14.dex */
public class YogaLayout extends ViewGroup {

    /* renamed from: g  reason: collision with root package name */
    private final Map<View, YogaNode> f4420g;

    /* renamed from: h  reason: collision with root package name */
    private final YogaNode f4421h;

    /* loaded from: classes14.dex */
    public static class a implements YogaMeasureFunction {
        private int a(YogaMeasureMode yogaMeasureMode) {
            if (yogaMeasureMode == YogaMeasureMode.AT_MOST) {
                return Integer.MIN_VALUE;
            }
            return yogaMeasureMode == YogaMeasureMode.EXACTLY ? 1073741824 : 0;
        }

        @Override // com.facebook.yoga.YogaMeasureFunction
        public long measure(YogaNode yogaNode, float f2, YogaMeasureMode yogaMeasureMode, float f3, YogaMeasureMode yogaMeasureMode2) {
            View view = (View) yogaNode.getData();
            if (view != null && !(view instanceof YogaLayout)) {
                view.measure(View.MeasureSpec.makeMeasureSpec((int) f2, a(yogaMeasureMode)), View.MeasureSpec.makeMeasureSpec((int) f3, a(yogaMeasureMode2)));
                return YogaMeasureOutput.make(view.getMeasuredWidth(), view.getMeasuredHeight());
            }
            return YogaMeasureOutput.make(0, 0);
        }
    }

    public YogaLayout(Context context) {
        this(context, null, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void b(LayoutParams layoutParams, YogaNode yogaNode, View view) {
        if (Build.VERSION.SDK_INT >= 17 && view.getResources().getConfiguration().getLayoutDirection() == 1) {
            yogaNode.setDirection(YogaDirection.RTL);
        }
        Drawable background = view.getBackground();
        if (background != null) {
            if (background.getPadding(new Rect())) {
                yogaNode.setPadding(YogaEdge.LEFT, r0.left);
                yogaNode.setPadding(YogaEdge.TOP, r0.top);
                yogaNode.setPadding(YogaEdge.RIGHT, r0.right);
                yogaNode.setPadding(YogaEdge.BOTTOM, r0.bottom);
            }
        }
        for (int i2 = 0; i2 < layoutParams.a.size(); i2++) {
            int keyAt = layoutParams.a.keyAt(i2);
            float floatValue = layoutParams.a.valueAt(i2).floatValue();
            if (keyAt == R.styleable.yoga_yg_alignContent) {
                yogaNode.setAlignContent(YogaAlign.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.yoga_yg_alignItems) {
                yogaNode.setAlignItems(YogaAlign.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.yoga_yg_alignSelf) {
                yogaNode.setAlignSelf(YogaAlign.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.yoga_yg_aspectRatio) {
                yogaNode.setAspectRatio(floatValue);
            } else if (keyAt == R.styleable.yoga_yg_borderLeft) {
                yogaNode.setBorder(YogaEdge.LEFT, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_borderTop) {
                yogaNode.setBorder(YogaEdge.TOP, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_borderRight) {
                yogaNode.setBorder(YogaEdge.RIGHT, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_borderBottom) {
                yogaNode.setBorder(YogaEdge.BOTTOM, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_borderStart) {
                yogaNode.setBorder(YogaEdge.START, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_borderEnd) {
                yogaNode.setBorder(YogaEdge.END, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_borderHorizontal) {
                yogaNode.setBorder(YogaEdge.HORIZONTAL, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_borderVertical) {
                yogaNode.setBorder(YogaEdge.VERTICAL, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_borderAll) {
                yogaNode.setBorder(YogaEdge.ALL, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_direction) {
                yogaNode.setDirection(YogaDirection.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.yoga_yg_display) {
                yogaNode.setDisplay(YogaDisplay.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.yoga_yg_flex) {
                yogaNode.setFlex(floatValue);
            } else if (keyAt == R.styleable.yoga_yg_flexBasis) {
                yogaNode.setFlexBasis(floatValue);
            } else if (keyAt == R.styleable.yoga_yg_flexDirection) {
                yogaNode.setFlexDirection(YogaFlexDirection.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.yoga_yg_flexGrow) {
                yogaNode.setFlexGrow(floatValue);
            } else if (keyAt == R.styleable.yoga_yg_flexShrink) {
                yogaNode.setFlexShrink(floatValue);
            } else if (keyAt == R.styleable.yoga_yg_height) {
                yogaNode.setHeight(floatValue);
            } else if (keyAt == R.styleable.yoga_yg_marginLeft) {
                yogaNode.setMargin(YogaEdge.LEFT, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_justifyContent) {
                yogaNode.setJustifyContent(YogaJustify.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.yoga_yg_marginTop) {
                yogaNode.setMargin(YogaEdge.TOP, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_marginRight) {
                yogaNode.setMargin(YogaEdge.RIGHT, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_marginBottom) {
                yogaNode.setMargin(YogaEdge.BOTTOM, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_marginStart) {
                yogaNode.setMargin(YogaEdge.START, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_marginEnd) {
                yogaNode.setMargin(YogaEdge.END, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_marginHorizontal) {
                yogaNode.setMargin(YogaEdge.HORIZONTAL, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_marginVertical) {
                yogaNode.setMargin(YogaEdge.VERTICAL, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_marginAll) {
                yogaNode.setMargin(YogaEdge.ALL, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_maxHeight) {
                yogaNode.setMaxHeight(floatValue);
            } else if (keyAt == R.styleable.yoga_yg_maxWidth) {
                yogaNode.setMaxWidth(floatValue);
            } else if (keyAt == R.styleable.yoga_yg_minHeight) {
                yogaNode.setMinHeight(floatValue);
            } else if (keyAt == R.styleable.yoga_yg_minWidth) {
                yogaNode.setMinWidth(floatValue);
            } else if (keyAt == R.styleable.yoga_yg_overflow) {
                yogaNode.setOverflow(YogaOverflow.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.yoga_yg_paddingLeft) {
                yogaNode.setPadding(YogaEdge.LEFT, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_paddingTop) {
                yogaNode.setPadding(YogaEdge.TOP, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_paddingRight) {
                yogaNode.setPadding(YogaEdge.RIGHT, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_paddingBottom) {
                yogaNode.setPadding(YogaEdge.BOTTOM, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_paddingStart) {
                yogaNode.setPadding(YogaEdge.START, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_paddingEnd) {
                yogaNode.setPadding(YogaEdge.END, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_paddingHorizontal) {
                yogaNode.setPadding(YogaEdge.HORIZONTAL, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_paddingVertical) {
                yogaNode.setPadding(YogaEdge.VERTICAL, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_paddingAll) {
                yogaNode.setPadding(YogaEdge.ALL, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_positionLeft) {
                yogaNode.setPosition(YogaEdge.LEFT, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_positionTop) {
                yogaNode.setPosition(YogaEdge.TOP, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_positionRight) {
                yogaNode.setPosition(YogaEdge.RIGHT, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_positionBottom) {
                yogaNode.setPosition(YogaEdge.BOTTOM, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_positionStart) {
                yogaNode.setPosition(YogaEdge.START, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_positionEnd) {
                yogaNode.setPosition(YogaEdge.END, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_positionHorizontal) {
                yogaNode.setPosition(YogaEdge.HORIZONTAL, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_positionVertical) {
                yogaNode.setPosition(YogaEdge.VERTICAL, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_positionAll) {
                yogaNode.setPosition(YogaEdge.ALL, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_positionType) {
                yogaNode.setPositionType(YogaPositionType.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.yoga_yg_width) {
                yogaNode.setWidth(floatValue);
            } else if (keyAt == R.styleable.yoga_yg_wrap) {
                yogaNode.setWrap(YogaWrap.fromInt(Math.round(floatValue)));
            }
        }
        for (int i3 = 0; i3 < layoutParams.b.size(); i3++) {
            int keyAt2 = layoutParams.b.keyAt(i3);
            String valueAt = layoutParams.b.valueAt(i3);
            if (valueAt.equals("auto")) {
                if (keyAt2 == R.styleable.yoga_yg_marginLeft) {
                    yogaNode.setMarginAuto(YogaEdge.LEFT);
                } else if (keyAt2 == R.styleable.yoga_yg_marginTop) {
                    yogaNode.setMarginAuto(YogaEdge.TOP);
                } else if (keyAt2 == R.styleable.yoga_yg_marginRight) {
                    yogaNode.setMarginAuto(YogaEdge.RIGHT);
                } else if (keyAt2 == R.styleable.yoga_yg_marginBottom) {
                    yogaNode.setMarginAuto(YogaEdge.BOTTOM);
                } else if (keyAt2 == R.styleable.yoga_yg_marginStart) {
                    yogaNode.setMarginAuto(YogaEdge.START);
                } else if (keyAt2 == R.styleable.yoga_yg_marginEnd) {
                    yogaNode.setMarginAuto(YogaEdge.END);
                } else if (keyAt2 == R.styleable.yoga_yg_marginHorizontal) {
                    yogaNode.setMarginAuto(YogaEdge.HORIZONTAL);
                } else if (keyAt2 == R.styleable.yoga_yg_marginVertical) {
                    yogaNode.setMarginAuto(YogaEdge.VERTICAL);
                } else if (keyAt2 == R.styleable.yoga_yg_marginAll) {
                    yogaNode.setMarginAuto(YogaEdge.ALL);
                }
            }
            if (valueAt.endsWith("%")) {
                float parseFloat = Float.parseFloat(valueAt.substring(0, valueAt.length() - 1));
                if (keyAt2 == R.styleable.yoga_yg_flexBasis) {
                    yogaNode.setFlexBasisPercent(parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_height) {
                    yogaNode.setHeightPercent(parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_marginLeft) {
                    yogaNode.setMarginPercent(YogaEdge.LEFT, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_marginTop) {
                    yogaNode.setMarginPercent(YogaEdge.TOP, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_marginRight) {
                    yogaNode.setMarginPercent(YogaEdge.RIGHT, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_marginBottom) {
                    yogaNode.setMarginPercent(YogaEdge.BOTTOM, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_marginStart) {
                    yogaNode.setMarginPercent(YogaEdge.START, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_marginEnd) {
                    yogaNode.setMarginPercent(YogaEdge.END, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_marginHorizontal) {
                    yogaNode.setMarginPercent(YogaEdge.HORIZONTAL, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_marginVertical) {
                    yogaNode.setMarginPercent(YogaEdge.VERTICAL, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_marginAll) {
                    yogaNode.setMarginPercent(YogaEdge.ALL, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_maxHeight) {
                    yogaNode.setMaxHeightPercent(parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_maxWidth) {
                    yogaNode.setMaxWidthPercent(parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_minHeight) {
                    yogaNode.setMinHeightPercent(parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_minWidth) {
                    yogaNode.setMinWidthPercent(parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_paddingLeft) {
                    yogaNode.setPaddingPercent(YogaEdge.LEFT, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_paddingTop) {
                    yogaNode.setPaddingPercent(YogaEdge.TOP, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_paddingRight) {
                    yogaNode.setPaddingPercent(YogaEdge.RIGHT, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_paddingBottom) {
                    yogaNode.setPaddingPercent(YogaEdge.BOTTOM, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_paddingStart) {
                    yogaNode.setPaddingPercent(YogaEdge.START, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_paddingEnd) {
                    yogaNode.setPaddingPercent(YogaEdge.END, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_paddingHorizontal) {
                    yogaNode.setPaddingPercent(YogaEdge.HORIZONTAL, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_paddingVertical) {
                    yogaNode.setPaddingPercent(YogaEdge.VERTICAL, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_paddingAll) {
                    yogaNode.setPaddingPercent(YogaEdge.ALL, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_positionLeft) {
                    yogaNode.setPositionPercent(YogaEdge.LEFT, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_positionTop) {
                    yogaNode.setPositionPercent(YogaEdge.TOP, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_positionRight) {
                    yogaNode.setPositionPercent(YogaEdge.RIGHT, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_positionBottom) {
                    yogaNode.setPositionPercent(YogaEdge.BOTTOM, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_positionStart) {
                    yogaNode.setPositionPercent(YogaEdge.START, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_positionEnd) {
                    yogaNode.setPositionPercent(YogaEdge.END, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_positionHorizontal) {
                    yogaNode.setPositionPercent(YogaEdge.HORIZONTAL, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_positionVertical) {
                    yogaNode.setPositionPercent(YogaEdge.VERTICAL, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_positionAll) {
                    yogaNode.setPositionPercent(YogaEdge.ALL, parseFloat);
                } else if (keyAt2 == R.styleable.yoga_yg_width) {
                    yogaNode.setWidthPercent(parseFloat);
                }
            }
        }
    }

    private void c(YogaNode yogaNode, float f2, float f3) {
        View view = (View) yogaNode.getData();
        if (view != null && view != this) {
            if (view.getVisibility() == 8) {
                return;
            }
            int round = Math.round(yogaNode.getLayoutX() + f2);
            int round2 = Math.round(yogaNode.getLayoutY() + f3);
            view.measure(View.MeasureSpec.makeMeasureSpec(Math.round(yogaNode.getLayoutWidth()), 1073741824), View.MeasureSpec.makeMeasureSpec(Math.round(yogaNode.getLayoutHeight()), 1073741824));
            view.layout(round, round2, view.getMeasuredWidth() + round, view.getMeasuredHeight() + round2);
        }
        int childCount = yogaNode.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (equals(view)) {
                c(yogaNode.getChildAt(i2), f2, f3);
            } else if (!(view instanceof YogaLayout)) {
                c(yogaNode.getChildAt(i2), yogaNode.getLayoutX() + f2, yogaNode.getLayoutY() + f3);
            }
        }
    }

    private void d(int i2, int i3) {
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        if (mode2 == 1073741824) {
            this.f4421h.setHeight(size2);
        }
        if (mode == 1073741824) {
            this.f4421h.setWidth(size);
        }
        if (mode2 == Integer.MIN_VALUE) {
            this.f4421h.setMaxHeight(size2);
        }
        if (mode == Integer.MIN_VALUE) {
            this.f4421h.setMaxWidth(size);
        }
        this.f4421h.calculateLayout(Float.NaN, Float.NaN);
    }

    private void g(View view, boolean z) {
        YogaNode yogaNode = this.f4420g.get(view);
        if (yogaNode == null) {
            return;
        }
        YogaNode owner = yogaNode.getOwner();
        int i2 = 0;
        while (true) {
            if (i2 >= owner.getChildCount()) {
                break;
            } else if (owner.getChildAt(i2).equals(yogaNode)) {
                owner.removeChildAt(i2);
                break;
            } else {
                i2++;
            }
        }
        yogaNode.setData(null);
        this.f4420g.remove(view);
        if (z) {
            this.f4421h.calculateLayout(Float.NaN, Float.NaN);
        }
    }

    public void a(View view, YogaNode yogaNode) {
        this.f4420g.put(view, yogaNode);
        addView(view);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        YogaNode yogaNode;
        this.f4421h.setMeasureFunction(null);
        if (view instanceof VirtualYogaLayout) {
            VirtualYogaLayout virtualYogaLayout = (VirtualYogaLayout) view;
            virtualYogaLayout.c(this);
            YogaNode b = virtualYogaLayout.b();
            YogaNode yogaNode2 = this.f4421h;
            yogaNode2.addChildAt(b, yogaNode2.getChildCount());
            return;
        }
        super.addView(view, i2, layoutParams);
        if (this.f4420g.containsKey(view)) {
            return;
        }
        if (view instanceof YogaLayout) {
            yogaNode = ((YogaLayout) view).e();
        } else {
            if (this.f4420g.containsKey(view)) {
                yogaNode = this.f4420g.get(view);
            } else {
                yogaNode = new YogaNode();
            }
            yogaNode.setData(view);
            yogaNode.setMeasureFunction(new a());
        }
        b((LayoutParams) view.getLayoutParams(), yogaNode, view);
        this.f4420g.put(view, yogaNode);
        YogaNode yogaNode3 = this.f4421h;
        yogaNode3.addChildAt(yogaNode, yogaNode3.getChildCount());
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public YogaNode e() {
        return this.f4421h;
    }

    public YogaNode f(View view) {
        return this.f4420g.get(view);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (!(getParent() instanceof YogaLayout)) {
            d(View.MeasureSpec.makeMeasureSpec(i4 - i2, 1073741824), View.MeasureSpec.makeMeasureSpec(i5 - i3, 1073741824));
        }
        c(this.f4421h, 0.0f, 0.0f);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        if (!(getParent() instanceof YogaLayout)) {
            d(i2, i3);
        }
        setMeasuredDimension(Math.round(this.f4421h.getLayoutWidth()), Math.round(this.f4421h.getLayoutHeight()));
    }

    @Override // android.view.ViewGroup
    public void removeAllViews() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            g(getChildAt(i2), false);
        }
        super.removeAllViews();
    }

    @Override // android.view.ViewGroup
    public void removeAllViewsInLayout() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            g(getChildAt(i2), true);
        }
        super.removeAllViewsInLayout();
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        g(view, false);
        super.removeView(view);
    }

    @Override // android.view.ViewGroup
    public void removeViewAt(int i2) {
        g(getChildAt(i2), false);
        super.removeViewAt(i2);
    }

    @Override // android.view.ViewGroup
    public void removeViewInLayout(View view) {
        g(view, true);
        super.removeViewInLayout(view);
    }

    @Override // android.view.ViewGroup
    public void removeViews(int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            g(getChildAt(i4), false);
        }
        super.removeViews(i2, i3);
    }

    @Override // android.view.ViewGroup
    public void removeViewsInLayout(int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            g(getChildAt(i4), true);
        }
        super.removeViewsInLayout(i2, i3);
    }

    public YogaLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public YogaLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        LayoutParams layoutParams;
        YogaNode yogaNode = new YogaNode();
        this.f4421h = yogaNode;
        this.f4420g = new HashMap();
        yogaNode.setData(this);
        yogaNode.setMeasureFunction(new a());
        if (attributeSet != null) {
            layoutParams = new LayoutParams(context, attributeSet);
        } else {
            layoutParams = (LayoutParams) generateDefaultLayoutParams();
        }
        b(layoutParams, yogaNode, this);
    }

    /* loaded from: classes14.dex */
    public static class LayoutParams extends ViewGroup.LayoutParams {
        SparseArray<Float> a;
        SparseArray<String> b;

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            if (layoutParams instanceof LayoutParams) {
                LayoutParams layoutParams2 = (LayoutParams) layoutParams;
                this.a = layoutParams2.a.clone();
                this.b = layoutParams2.b.clone();
                return;
            }
            this.a = new SparseArray<>();
            this.b = new SparseArray<>();
            if (layoutParams.width >= 0) {
                this.a.put(R.styleable.yoga_yg_width, Float.valueOf(((ViewGroup.LayoutParams) this).width));
            }
            if (layoutParams.height >= 0) {
                this.a.put(R.styleable.yoga_yg_height, Float.valueOf(((ViewGroup.LayoutParams) this).height));
            }
        }

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
            this.a = new SparseArray<>();
            this.b = new SparseArray<>();
            if (i2 >= 0) {
                this.a.put(R.styleable.yoga_yg_width, Float.valueOf(i2));
            }
            if (i3 >= 0) {
                this.a.put(R.styleable.yoga_yg_height, Float.valueOf(i3));
            }
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.a = new SparseArray<>();
            this.b = new SparseArray<>();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.yoga);
            int i2 = ((ViewGroup.LayoutParams) this).width;
            if (i2 >= 0) {
                this.a.put(R.styleable.yoga_yg_width, Float.valueOf(i2));
            }
            int i3 = ((ViewGroup.LayoutParams) this).height;
            if (i3 >= 0) {
                this.a.put(R.styleable.yoga_yg_height, Float.valueOf(i3));
            }
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i4 = 0; i4 < indexCount; i4++) {
                int index = obtainStyledAttributes.getIndex(i4);
                TypedValue typedValue = new TypedValue();
                obtainStyledAttributes.getValue(index, typedValue);
                int i5 = typedValue.type;
                if (i5 == 5) {
                    this.a.put(index, Float.valueOf(obtainStyledAttributes.getDimensionPixelSize(index, 0)));
                } else if (i5 == 3) {
                    this.b.put(index, obtainStyledAttributes.getString(index));
                } else {
                    this.a.put(index, Float.valueOf(obtainStyledAttributes.getFloat(index, 0.0f)));
                }
            }
            obtainStyledAttributes.recycle();
        }
    }
}
