package com.jingdong.sdk.lib.puppetlayout.ylayout.android;

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
import com.facebook.yoga2.YogaAlign;
import com.facebook.yoga2.YogaDirection;
import com.facebook.yoga2.YogaDisplay;
import com.facebook.yoga2.YogaEdge;
import com.facebook.yoga2.YogaFlexDirection;
import com.facebook.yoga2.YogaJustify;
import com.facebook.yoga2.YogaMeasureFunction;
import com.facebook.yoga2.YogaMeasureMode;
import com.facebook.yoga2.YogaMeasureOutput;
import com.facebook.yoga2.YogaNode;
import com.facebook.yoga2.YogaOverflow;
import com.facebook.yoga2.YogaPositionType;
import com.facebook.yoga2.YogaWrap;
import com.jingdong.sdk.lib.puppetlayout.R;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes8.dex */
public class YogaLayout extends ViewGroup {
    private final YogaNode mYogaNode;
    private final Map<View, YogaNode> mYogaNodes;

    /* loaded from: classes8.dex */
    public static class ViewMeasureFunction implements YogaMeasureFunction {
        private int viewMeasureSpecFromYogaMeasureMode(YogaMeasureMode yogaMeasureMode) {
            if (yogaMeasureMode == YogaMeasureMode.AT_MOST) {
                return Integer.MIN_VALUE;
            }
            return yogaMeasureMode == YogaMeasureMode.EXACTLY ? 1073741824 : 0;
        }

        @Override // com.facebook.yoga2.YogaMeasureFunction
        public long measure(YogaNode yogaNode, float f2, YogaMeasureMode yogaMeasureMode, float f3, YogaMeasureMode yogaMeasureMode2) {
            View view = (View) yogaNode.getData();
            if (view != null && !(view instanceof YogaLayout)) {
                int i2 = Build.VERSION.SDK_INT;
                if (i2 <= 17 && viewMeasureSpecFromYogaMeasureMode(yogaMeasureMode) == 0) {
                    f2 = 0.0f;
                }
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec((int) f2, viewMeasureSpecFromYogaMeasureMode(yogaMeasureMode));
                if (i2 <= 17 && viewMeasureSpecFromYogaMeasureMode(yogaMeasureMode2) == 0) {
                    f3 = 0.0f;
                }
                view.measure(makeMeasureSpec, View.MeasureSpec.makeMeasureSpec((int) f3, viewMeasureSpecFromYogaMeasureMode(yogaMeasureMode2)));
                return YogaMeasureOutput.make(view.getMeasuredWidth(), view.getMeasuredHeight());
            }
            return YogaMeasureOutput.make(0, 0);
        }
    }

    public YogaLayout(Context context) {
        this(context, null, 0);
    }

    public static void applyLayoutParams(LayoutParams layoutParams, YogaNode yogaNode, View view) {
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
        for (int i2 = 0; i2 < layoutParams.numericAttributes.size(); i2++) {
            int keyAt = layoutParams.numericAttributes.keyAt(i2);
            float floatValue = layoutParams.numericAttributes.valueAt(i2).floatValue();
            if (keyAt == R.styleable.ylayout_yg2_alignContent) {
                yogaNode.setAlignContent(YogaAlign.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.ylayout_yg2_alignItems) {
                yogaNode.setAlignItems(YogaAlign.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.ylayout_yg2_alignSelf) {
                yogaNode.setAlignSelf(YogaAlign.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.ylayout_yg2_aspectRatio) {
                yogaNode.setAspectRatio(floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_borderLeft) {
                yogaNode.setBorder(YogaEdge.LEFT, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_borderTop) {
                yogaNode.setBorder(YogaEdge.TOP, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_borderRight) {
                yogaNode.setBorder(YogaEdge.RIGHT, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_borderBottom) {
                yogaNode.setBorder(YogaEdge.BOTTOM, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_borderStart) {
                yogaNode.setBorder(YogaEdge.START, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_borderEnd) {
                yogaNode.setBorder(YogaEdge.END, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_borderHorizontal) {
                yogaNode.setBorder(YogaEdge.HORIZONTAL, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_borderVertical) {
                yogaNode.setBorder(YogaEdge.VERTICAL, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_borderAll) {
                yogaNode.setBorder(YogaEdge.ALL, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_direction) {
                yogaNode.setDirection(YogaDirection.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.ylayout_yg2_display) {
                yogaNode.setDisplay(YogaDisplay.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.ylayout_yg2_flex) {
                yogaNode.setFlex(floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_flexBasis) {
                yogaNode.setFlexBasis(floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_flexDirection) {
                yogaNode.setFlexDirection(YogaFlexDirection.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.ylayout_yg2_flexGrow) {
                yogaNode.setFlexGrow(floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_flexShrink) {
                yogaNode.setFlexShrink(floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_height) {
                yogaNode.setHeight(floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_marginLeft) {
                yogaNode.setMargin(YogaEdge.LEFT, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_justifyContent) {
                yogaNode.setJustifyContent(YogaJustify.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.ylayout_yg2_marginTop) {
                yogaNode.setMargin(YogaEdge.TOP, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_marginRight) {
                yogaNode.setMargin(YogaEdge.RIGHT, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_marginBottom) {
                yogaNode.setMargin(YogaEdge.BOTTOM, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_marginStart) {
                yogaNode.setMargin(YogaEdge.START, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_marginEnd) {
                yogaNode.setMargin(YogaEdge.END, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_marginHorizontal) {
                yogaNode.setMargin(YogaEdge.HORIZONTAL, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_marginVertical) {
                yogaNode.setMargin(YogaEdge.VERTICAL, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_marginAll) {
                yogaNode.setMargin(YogaEdge.ALL, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_maxHeight) {
                yogaNode.setMaxHeight(floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_maxWidth) {
                yogaNode.setMaxWidth(floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_minHeight) {
                yogaNode.setMinHeight(floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_minWidth) {
                yogaNode.setMinWidth(floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_overflow) {
                yogaNode.setOverflow(YogaOverflow.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.ylayout_yg2_paddingLeft) {
                yogaNode.setPadding(YogaEdge.LEFT, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_paddingTop) {
                yogaNode.setPadding(YogaEdge.TOP, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_paddingRight) {
                yogaNode.setPadding(YogaEdge.RIGHT, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_paddingBottom) {
                yogaNode.setPadding(YogaEdge.BOTTOM, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_paddingStart) {
                yogaNode.setPadding(YogaEdge.START, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_paddingEnd) {
                yogaNode.setPadding(YogaEdge.END, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_paddingHorizontal) {
                yogaNode.setPadding(YogaEdge.HORIZONTAL, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_paddingVertical) {
                yogaNode.setPadding(YogaEdge.VERTICAL, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_paddingAll) {
                yogaNode.setPadding(YogaEdge.ALL, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_positionLeft) {
                yogaNode.setPosition(YogaEdge.LEFT, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_positionTop) {
                yogaNode.setPosition(YogaEdge.TOP, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_positionRight) {
                yogaNode.setPosition(YogaEdge.RIGHT, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_positionBottom) {
                yogaNode.setPosition(YogaEdge.BOTTOM, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_positionStart) {
                yogaNode.setPosition(YogaEdge.START, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_positionEnd) {
                yogaNode.setPosition(YogaEdge.END, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_positionHorizontal) {
                yogaNode.setPosition(YogaEdge.HORIZONTAL, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_positionVertical) {
                yogaNode.setPosition(YogaEdge.VERTICAL, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_positionAll) {
                yogaNode.setPosition(YogaEdge.ALL, floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_positionType) {
                yogaNode.setPositionType(YogaPositionType.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.ylayout_yg2_width) {
                yogaNode.setWidth(floatValue);
            } else if (keyAt == R.styleable.ylayout_yg2_wrap) {
                yogaNode.setWrap(YogaWrap.fromInt(Math.round(floatValue)));
            }
        }
        for (int i3 = 0; i3 < layoutParams.stringAttributes.size(); i3++) {
            int keyAt2 = layoutParams.stringAttributes.keyAt(i3);
            String valueAt = layoutParams.stringAttributes.valueAt(i3);
            if (valueAt.equals("auto")) {
                if (keyAt2 == R.styleable.ylayout_yg2_marginLeft) {
                    yogaNode.setMarginAuto(YogaEdge.LEFT);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginTop) {
                    yogaNode.setMarginAuto(YogaEdge.TOP);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginRight) {
                    yogaNode.setMarginAuto(YogaEdge.RIGHT);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginBottom) {
                    yogaNode.setMarginAuto(YogaEdge.BOTTOM);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginStart) {
                    yogaNode.setMarginAuto(YogaEdge.START);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginEnd) {
                    yogaNode.setMarginAuto(YogaEdge.END);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginHorizontal) {
                    yogaNode.setMarginAuto(YogaEdge.HORIZONTAL);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginVertical) {
                    yogaNode.setMarginAuto(YogaEdge.VERTICAL);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginAll) {
                    yogaNode.setMarginAuto(YogaEdge.ALL);
                }
            }
            if (valueAt.endsWith("%")) {
                float parseFloat = Float.parseFloat(valueAt.substring(0, valueAt.length() - 1));
                if (keyAt2 == R.styleable.ylayout_yg2_flexBasis) {
                    yogaNode.setFlexBasisPercent(parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_height) {
                    yogaNode.setHeightPercent(parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginLeft) {
                    yogaNode.setMarginPercent(YogaEdge.LEFT, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginTop) {
                    yogaNode.setMarginPercent(YogaEdge.TOP, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginRight) {
                    yogaNode.setMarginPercent(YogaEdge.RIGHT, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginBottom) {
                    yogaNode.setMarginPercent(YogaEdge.BOTTOM, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginStart) {
                    yogaNode.setMarginPercent(YogaEdge.START, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginEnd) {
                    yogaNode.setMarginPercent(YogaEdge.END, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginHorizontal) {
                    yogaNode.setMarginPercent(YogaEdge.HORIZONTAL, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginVertical) {
                    yogaNode.setMarginPercent(YogaEdge.VERTICAL, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginAll) {
                    yogaNode.setMarginPercent(YogaEdge.ALL, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_maxHeight) {
                    yogaNode.setMaxHeightPercent(parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_maxWidth) {
                    yogaNode.setMaxWidthPercent(parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_minHeight) {
                    yogaNode.setMinHeightPercent(parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_minWidth) {
                    yogaNode.setMinWidthPercent(parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_paddingLeft) {
                    yogaNode.setPaddingPercent(YogaEdge.LEFT, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_paddingTop) {
                    yogaNode.setPaddingPercent(YogaEdge.TOP, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_paddingRight) {
                    yogaNode.setPaddingPercent(YogaEdge.RIGHT, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_paddingBottom) {
                    yogaNode.setPaddingPercent(YogaEdge.BOTTOM, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_paddingStart) {
                    yogaNode.setPaddingPercent(YogaEdge.START, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_paddingEnd) {
                    yogaNode.setPaddingPercent(YogaEdge.END, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_paddingHorizontal) {
                    yogaNode.setPaddingPercent(YogaEdge.HORIZONTAL, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_paddingVertical) {
                    yogaNode.setPaddingPercent(YogaEdge.VERTICAL, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_paddingAll) {
                    yogaNode.setPaddingPercent(YogaEdge.ALL, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_positionLeft) {
                    yogaNode.setPositionPercent(YogaEdge.LEFT, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_positionTop) {
                    yogaNode.setPositionPercent(YogaEdge.TOP, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_positionRight) {
                    yogaNode.setPositionPercent(YogaEdge.RIGHT, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_positionBottom) {
                    yogaNode.setPositionPercent(YogaEdge.BOTTOM, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_positionStart) {
                    yogaNode.setPositionPercent(YogaEdge.START, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_positionEnd) {
                    yogaNode.setPositionPercent(YogaEdge.END, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_positionHorizontal) {
                    yogaNode.setPositionPercent(YogaEdge.HORIZONTAL, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_positionVertical) {
                    yogaNode.setPositionPercent(YogaEdge.VERTICAL, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_positionAll) {
                    yogaNode.setPositionPercent(YogaEdge.ALL, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_width) {
                    yogaNode.setWidthPercent(parseFloat);
                }
            }
        }
    }

    private void applyLayoutRecursive(YogaNode yogaNode, float f2, float f3) {
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
                applyLayoutRecursive(yogaNode.getChildAt(i2), f2, f3);
            } else if (!(view instanceof YogaLayout)) {
                applyLayoutRecursive(yogaNode.getChildAt(i2), yogaNode.getLayoutX() + f2, yogaNode.getLayoutY() + f3);
            }
        }
    }

    private void createLayout(int i2, int i3) {
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        if (mode2 == 1073741824) {
            this.mYogaNode.setHeight(size2);
        }
        if (mode == 1073741824) {
            this.mYogaNode.setWidth(size);
        }
        if (mode2 == Integer.MIN_VALUE) {
            this.mYogaNode.setMaxHeight(size2);
        }
        if (mode == Integer.MIN_VALUE) {
            this.mYogaNode.setMaxWidth(size);
        }
        this.mYogaNode.calculateLayout(1.0E21f, 1.0E21f);
    }

    private void removeViewFromYogaTree(View view, boolean z) {
        YogaNode yogaNode = this.mYogaNodes.get(view);
        if (yogaNode == null) {
            return;
        }
        YogaNode parent = yogaNode.getParent();
        int i2 = 0;
        while (true) {
            if (i2 >= parent.getChildCount()) {
                break;
            } else if (parent.getChildAt(i2).equals(yogaNode)) {
                parent.removeChildAt(i2);
                break;
            } else {
                i2++;
            }
        }
        yogaNode.setData(null);
        this.mYogaNodes.remove(view);
        if (z) {
            this.mYogaNode.calculateLayout(1.0E21f, 1.0E21f);
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        YogaNode yogaNode;
        this.mYogaNode.setMeasureFunction(null);
        if (view instanceof VirtualYogaLayout) {
            VirtualYogaLayout virtualYogaLayout = (VirtualYogaLayout) view;
            virtualYogaLayout.transferChildren(this);
            YogaNode yogaNode2 = virtualYogaLayout.getYogaNode();
            YogaNode yogaNode3 = this.mYogaNode;
            yogaNode3.addChildAt(yogaNode2, yogaNode3.getChildCount());
            return;
        }
        super.addView(view, i2, layoutParams);
        if (this.mYogaNodes.containsKey(view)) {
            return;
        }
        if (view instanceof YogaLayout) {
            yogaNode = ((YogaLayout) view).getYogaNode();
        } else {
            if (this.mYogaNodes.containsKey(view)) {
                yogaNode = this.mYogaNodes.get(view);
            } else {
                yogaNode = new YogaNode();
            }
            yogaNode.setData(view);
            yogaNode.setMeasureFunction(new ViewMeasureFunction());
        }
        applyLayoutParams((LayoutParams) view.getLayoutParams(), yogaNode, view);
        this.mYogaNodes.put(view, yogaNode);
        YogaNode yogaNode4 = this.mYogaNode;
        yogaNode4.addChildAt(yogaNode, yogaNode4.getChildCount());
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public YogaNode getYogaNode() {
        return this.mYogaNode;
    }

    public YogaNode getYogaNodeForView(View view) {
        return this.mYogaNodes.get(view);
    }

    public void invalidate(View view) {
        if (this.mYogaNodes.containsKey(view)) {
            this.mYogaNodes.get(view).dirty();
            return;
        }
        int childCount = this.mYogaNode.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            YogaNode childAt = this.mYogaNode.getChildAt(i2);
            if (childAt.getData() instanceof YogaLayout) {
                ((YogaLayout) childAt.getData()).invalidate(view);
            }
        }
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (!(getParent() instanceof YogaLayout)) {
            createLayout(View.MeasureSpec.makeMeasureSpec(i4 - i2, 1073741824), View.MeasureSpec.makeMeasureSpec(i5 - i3, 1073741824));
        }
        applyLayoutRecursive(this.mYogaNode, 0.0f, 0.0f);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        if (!(getParent() instanceof YogaLayout)) {
            createLayout(i2, i3);
        }
        setMeasuredDimension(Math.round(this.mYogaNode.getLayoutWidth()), Math.round(this.mYogaNode.getLayoutHeight()));
    }

    @Override // android.view.ViewGroup
    public void removeAllViews() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            removeViewFromYogaTree(getChildAt(i2), false);
        }
        super.removeAllViews();
    }

    @Override // android.view.ViewGroup
    public void removeAllViewsInLayout() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            removeViewFromYogaTree(getChildAt(i2), true);
        }
        super.removeAllViewsInLayout();
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        removeViewFromYogaTree(view, false);
        super.removeView(view);
    }

    @Override // android.view.ViewGroup
    public void removeViewAt(int i2) {
        removeViewFromYogaTree(getChildAt(i2), false);
        super.removeViewAt(i2);
    }

    @Override // android.view.ViewGroup
    public void removeViewInLayout(View view) {
        removeViewFromYogaTree(view, true);
        super.removeViewInLayout(view);
    }

    @Override // android.view.ViewGroup
    public void removeViews(int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            removeViewFromYogaTree(getChildAt(i4), false);
        }
        super.removeViews(i2, i3);
    }

    @Override // android.view.ViewGroup
    public void removeViewsInLayout(int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            removeViewFromYogaTree(getChildAt(i4), true);
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
        this.mYogaNode = yogaNode;
        this.mYogaNodes = new HashMap();
        yogaNode.setData(this);
        yogaNode.setMeasureFunction(new ViewMeasureFunction());
        if (attributeSet != null) {
            layoutParams = new LayoutParams(context, attributeSet);
        } else {
            layoutParams = (LayoutParams) generateDefaultLayoutParams();
        }
        applyLayoutParams(layoutParams, yogaNode, this);
    }

    /* loaded from: classes8.dex */
    public static class LayoutParams extends ViewGroup.LayoutParams {
        public SparseArray<Float> numericAttributes;
        public SparseArray<String> stringAttributes;

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            if (layoutParams instanceof LayoutParams) {
                LayoutParams layoutParams2 = (LayoutParams) layoutParams;
                this.numericAttributes = layoutParams2.numericAttributes.clone();
                this.stringAttributes = layoutParams2.stringAttributes.clone();
                return;
            }
            this.numericAttributes = new SparseArray<>();
            this.stringAttributes = new SparseArray<>();
            if (layoutParams.width >= 0) {
                this.numericAttributes.put(R.styleable.ylayout_yg2_width, Float.valueOf(((ViewGroup.LayoutParams) this).width));
            }
            if (layoutParams.height >= 0) {
                this.numericAttributes.put(R.styleable.ylayout_yg2_height, Float.valueOf(((ViewGroup.LayoutParams) this).height));
            }
        }

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
            this.numericAttributes = new SparseArray<>();
            this.stringAttributes = new SparseArray<>();
            if (i2 >= 0) {
                this.numericAttributes.put(R.styleable.ylayout_yg2_width, Float.valueOf(i2));
            }
            if (i3 >= 0) {
                this.numericAttributes.put(R.styleable.ylayout_yg2_height, Float.valueOf(i3));
            }
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.numericAttributes = new SparseArray<>();
            this.stringAttributes = new SparseArray<>();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ylayout);
            int i2 = ((ViewGroup.LayoutParams) this).width;
            if (i2 >= 0) {
                this.numericAttributes.put(R.styleable.ylayout_yg2_width, Float.valueOf(i2));
            }
            int i3 = ((ViewGroup.LayoutParams) this).height;
            if (i3 >= 0) {
                this.numericAttributes.put(R.styleable.ylayout_yg2_height, Float.valueOf(i3));
            }
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i4 = 0; i4 < indexCount; i4++) {
                int index = obtainStyledAttributes.getIndex(i4);
                TypedValue typedValue = new TypedValue();
                obtainStyledAttributes.getValue(index, typedValue);
                int i5 = typedValue.type;
                if (i5 == 5) {
                    this.numericAttributes.put(index, Float.valueOf(obtainStyledAttributes.getDimensionPixelSize(index, 0)));
                } else if (i5 == 3) {
                    this.stringAttributes.put(index, obtainStyledAttributes.getString(index));
                } else {
                    this.numericAttributes.put(index, Float.valueOf(obtainStyledAttributes.getFloat(index, 0.0f)));
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    public void addView(View view, YogaNode yogaNode) {
        this.mYogaNodes.put(view, yogaNode);
        addView(view);
    }
}
