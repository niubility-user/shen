package com.jd.dynamic.yoga.android;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.react.uimanager.ViewProps;
import com.jd.dynamic.R;
import com.jd.dynamic.b.c.a;
import com.jd.dynamic.b.l.b.b;
import com.jd.dynamic.b.l.b.c;
import com.jd.dynamic.lib.utils.t;
import com.jd.dynamic.lib.views.CollectionView;
import com.jd.dynamic.lib.views.RecyclerViewAdapter;
import com.jd.dynamic.yoga.YogaAlign;
import com.jd.dynamic.yoga.YogaConstants;
import com.jd.dynamic.yoga.YogaDirection;
import com.jd.dynamic.yoga.YogaDisplay;
import com.jd.dynamic.yoga.YogaEdge;
import com.jd.dynamic.yoga.YogaFlexDirection;
import com.jd.dynamic.yoga.YogaJustify;
import com.jd.dynamic.yoga.YogaMeasureFunction;
import com.jd.dynamic.yoga.YogaMeasureMode;
import com.jd.dynamic.yoga.YogaMeasureOutput;
import com.jd.dynamic.yoga.YogaNode;
import com.jd.dynamic.yoga.YogaNodeFactory;
import com.jd.dynamic.yoga.YogaOverflow;
import com.jd.dynamic.yoga.YogaPositionType;
import com.jd.dynamic.yoga.YogaWrap;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes13.dex */
public class YogaLayout extends ViewGroup {
    public boolean isRootLayout;
    private boolean isShadowEnable;
    private float lb;
    private float lt;
    private Paint mClipPaint;
    private LayoutParams mLayoutParams;
    private b mShadowStrategy;
    private final YogaNode mYogaNode;
    private final Map<View, YogaNode> mYogaNodes;
    private Paint paint;
    private Path path;
    private float rb;
    private float rt;

    /* loaded from: classes13.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public boolean hasChange;
        SparseArray<Float> numericAttributes;
        SparseArray<String> stringAttributes;

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
            this.numericAttributes = new SparseArray<>();
            this.stringAttributes = new SparseArray<>();
            if (i2 >= 0) {
                this.numericAttributes.put(R.styleable.yoga_yg_width, Float.valueOf(i2));
            }
            if (i3 >= 0) {
                this.numericAttributes.put(R.styleable.yoga_yg_height, Float.valueOf(i3));
            }
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            SparseArray sparseArray;
            float f2;
            Object string;
            this.numericAttributes = new SparseArray<>();
            this.stringAttributes = new SparseArray<>();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.yoga);
            int i2 = ((ViewGroup.MarginLayoutParams) this).width;
            if (i2 >= 0) {
                this.numericAttributes.put(R.styleable.yoga_yg_width, Float.valueOf(i2));
            }
            int i3 = ((ViewGroup.MarginLayoutParams) this).height;
            if (i3 >= 0) {
                this.numericAttributes.put(R.styleable.yoga_yg_height, Float.valueOf(i3));
            }
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i4 = 0; i4 < indexCount; i4++) {
                int index = obtainStyledAttributes.getIndex(i4);
                TypedValue typedValue = new TypedValue();
                obtainStyledAttributes.getValue(index, typedValue);
                int i5 = typedValue.type;
                if (i5 == 5) {
                    sparseArray = this.numericAttributes;
                    f2 = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (i5 == 3) {
                    sparseArray = this.stringAttributes;
                    string = obtainStyledAttributes.getString(index);
                    sparseArray.put(index, string);
                } else {
                    sparseArray = this.numericAttributes;
                    f2 = obtainStyledAttributes.getFloat(index, 0.0f);
                }
                string = Float.valueOf(f2);
                sparseArray.put(index, string);
            }
            obtainStyledAttributes.recycle();
        }

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
                this.numericAttributes.put(R.styleable.yoga_yg_width, Float.valueOf(((ViewGroup.MarginLayoutParams) this).width));
            }
            if (layoutParams.height >= 0) {
                this.numericAttributes.put(R.styleable.yoga_yg_height, Float.valueOf(((ViewGroup.MarginLayoutParams) this).height));
            }
        }

        public float getNumericAttribute(int i2) {
            return this.numericAttributes.get(i2, Float.valueOf(0.0f)).floatValue();
        }

        public String getStringAttribute(int i2) {
            return this.stringAttributes.get(i2, "");
        }

        public void putNumericAttribute(int i2, float f2) {
            this.hasChange = true;
            this.numericAttributes.put(i2, Float.valueOf(f2));
        }

        public void putStringAttribute(int i2, String str) {
            this.hasChange = true;
            this.stringAttributes.put(i2, str);
        }
    }

    /* loaded from: classes13.dex */
    public static class ViewMeasureFunction implements YogaMeasureFunction {
        private int viewMeasureSpecFromYogaMeasureMode(YogaMeasureMode yogaMeasureMode) {
            if (yogaMeasureMode == YogaMeasureMode.AT_MOST) {
                return Integer.MIN_VALUE;
            }
            return yogaMeasureMode == YogaMeasureMode.EXACTLY ? 1073741824 : 0;
        }

        @Override // com.jd.dynamic.yoga.YogaMeasureFunction
        public long measure(YogaNode yogaNode, float f2, YogaMeasureMode yogaMeasureMode, float f3, YogaMeasureMode yogaMeasureMode2) {
            if (yogaNode.getData() instanceof View) {
                View view = (View) yogaNode.getData();
                if (view == null || (view instanceof YogaLayout)) {
                    return YogaMeasureOutput.make(0, 0);
                }
                view.measure(View.MeasureSpec.makeMeasureSpec((int) f2, viewMeasureSpecFromYogaMeasureMode(yogaMeasureMode)), View.MeasureSpec.makeMeasureSpec((int) f3, viewMeasureSpecFromYogaMeasureMode(yogaMeasureMode2)));
                return YogaMeasureOutput.make(view.getMeasuredWidth(), view.getMeasuredHeight());
            }
            return YogaMeasureOutput.make(0, 0);
        }
    }

    public YogaLayout(Context context) {
        this(context, null, 0);
    }

    public YogaLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public YogaLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.paint = new Paint();
        this.isShadowEnable = false;
        YogaNode create = YogaNodeFactory.create();
        this.mYogaNode = create;
        this.mYogaNodes = new HashMap();
        create.setData(this);
        create.setMeasureFunction(new ViewMeasureFunction());
        applyLayoutParams(attributeSet != null ? new LayoutParams(context, attributeSet) : (LayoutParams) generateDefaultLayoutParams(), create, this);
        init();
    }

    public YogaLayout(Context context, LayoutParams layoutParams) {
        super(context, null, 0);
        this.paint = new Paint();
        this.isShadowEnable = false;
        YogaNode create = YogaNodeFactory.create();
        this.mYogaNode = create;
        this.mYogaNodes = new HashMap();
        create.setData(this);
        create.setMeasureFunction(new ViewMeasureFunction());
        this.mLayoutParams = layoutParams;
        if (layoutParams == null) {
            this.mLayoutParams = (LayoutParams) generateDefaultLayoutParams();
        }
        applyLayoutParams(this.mLayoutParams, create, this);
        init();
    }

    public static void applyLayoutParams(LayoutParams layoutParams, YogaNode yogaNode, View view) {
        YogaEdge yogaEdge;
        YogaEdge yogaEdge2;
        YogaEdge yogaEdge3;
        YogaEdge yogaEdge4;
        YogaEdge yogaEdge5;
        YogaEdge yogaEdge6;
        YogaEdge yogaEdge7;
        YogaEdge yogaEdge8;
        if (Build.VERSION.SDK_INT >= 17 && view.getResources().getConfiguration().getLayoutDirection() == 1) {
            yogaNode.setDirection(YogaDirection.RTL);
        }
        Drawable background = view.getBackground();
        if (background != null) {
            if (background.getPadding(new Rect())) {
                yogaNode.setPadding(YogaEdge.LEFT, r2.left);
                yogaNode.setPadding(YogaEdge.TOP, r2.top);
                yogaNode.setPadding(YogaEdge.RIGHT, r2.right);
                yogaNode.setPadding(YogaEdge.BOTTOM, r2.bottom);
            }
        }
        for (int i2 = 0; i2 < layoutParams.numericAttributes.size(); i2++) {
            int keyAt = layoutParams.numericAttributes.keyAt(i2);
            float floatValue = layoutParams.numericAttributes.valueAt(i2).floatValue();
            if (keyAt == R.styleable.yoga_yg_alignContent) {
                yogaNode.setAlignContent(YogaAlign.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.yoga_yg_alignItems) {
                yogaNode.setAlignItems(YogaAlign.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.yoga_yg_alignSelf) {
                yogaNode.setAlignSelf(YogaAlign.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.yoga_yg_aspectRatio) {
                yogaNode.setAspectRatio(floatValue);
            } else {
                if (keyAt == R.styleable.yoga_yg_borderLeft) {
                    yogaEdge7 = YogaEdge.LEFT;
                } else if (keyAt == R.styleable.yoga_yg_borderTop) {
                    yogaEdge7 = YogaEdge.TOP;
                } else if (keyAt == R.styleable.yoga_yg_borderRight) {
                    yogaEdge7 = YogaEdge.RIGHT;
                } else if (keyAt == R.styleable.yoga_yg_borderBottom) {
                    yogaEdge7 = YogaEdge.BOTTOM;
                } else if (keyAt == R.styleable.yoga_yg_borderStart) {
                    yogaEdge7 = YogaEdge.START;
                } else if (keyAt == R.styleable.yoga_yg_borderEnd) {
                    yogaEdge7 = YogaEdge.END;
                } else if (keyAt == R.styleable.yoga_yg_borderHorizontal) {
                    yogaEdge7 = YogaEdge.HORIZONTAL;
                } else if (keyAt == R.styleable.yoga_yg_borderVertical) {
                    yogaEdge7 = YogaEdge.VERTICAL;
                } else if (keyAt == R.styleable.yoga_yg_borderAll) {
                    yogaEdge7 = YogaEdge.ALL;
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
                } else {
                    if (keyAt == R.styleable.yoga_yg_marginLeft) {
                        yogaEdge6 = YogaEdge.LEFT;
                    } else if (keyAt == R.styleable.yoga_yg_justifyContent) {
                        yogaNode.setJustifyContent(YogaJustify.fromInt(Math.round(floatValue)));
                    } else if (keyAt == R.styleable.yoga_yg_marginTop) {
                        yogaEdge6 = YogaEdge.TOP;
                    } else if (keyAt == R.styleable.yoga_yg_marginRight) {
                        yogaEdge6 = YogaEdge.RIGHT;
                    } else if (keyAt == R.styleable.yoga_yg_marginBottom) {
                        yogaEdge6 = YogaEdge.BOTTOM;
                    } else if (keyAt == R.styleable.yoga_yg_marginStart) {
                        yogaEdge6 = YogaEdge.START;
                    } else if (keyAt == R.styleable.yoga_yg_marginEnd) {
                        yogaEdge6 = YogaEdge.END;
                    } else if (keyAt == R.styleable.yoga_yg_marginHorizontal) {
                        yogaEdge6 = YogaEdge.HORIZONTAL;
                    } else if (keyAt == R.styleable.yoga_yg_marginVertical) {
                        yogaEdge6 = YogaEdge.VERTICAL;
                    } else if (keyAt == R.styleable.yoga_yg_marginAll) {
                        yogaEdge6 = YogaEdge.ALL;
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
                    } else {
                        if (keyAt == R.styleable.yoga_yg_positionLeft) {
                            yogaEdge5 = YogaEdge.LEFT;
                        } else if (keyAt == R.styleable.yoga_yg_positionTop) {
                            yogaEdge5 = YogaEdge.TOP;
                        } else if (keyAt == R.styleable.yoga_yg_positionRight) {
                            yogaEdge5 = YogaEdge.RIGHT;
                        } else if (keyAt == R.styleable.yoga_yg_positionBottom) {
                            yogaEdge5 = YogaEdge.BOTTOM;
                        } else if (keyAt == R.styleable.yoga_yg_positionStart) {
                            yogaEdge5 = YogaEdge.START;
                        } else if (keyAt == R.styleable.yoga_yg_positionEnd) {
                            yogaEdge5 = YogaEdge.END;
                        } else if (keyAt == R.styleable.yoga_yg_positionHorizontal) {
                            yogaEdge5 = YogaEdge.HORIZONTAL;
                        } else if (keyAt == R.styleable.yoga_yg_positionVertical) {
                            yogaEdge5 = YogaEdge.VERTICAL;
                        } else if (keyAt == R.styleable.yoga_yg_positionAll) {
                            yogaEdge5 = YogaEdge.ALL;
                        } else if (keyAt == R.styleable.yoga_yg_positionType) {
                            yogaNode.setPositionType(YogaPositionType.fromInt(Math.round(floatValue)));
                        } else if (keyAt == R.styleable.yoga_yg_width) {
                            yogaNode.setWidth(floatValue);
                        } else if (keyAt == R.styleable.yoga_yg_wrap) {
                            yogaNode.setWrap(YogaWrap.fromInt(Math.round(floatValue)));
                        }
                        yogaNode.setPosition(yogaEdge5, floatValue);
                    }
                    yogaNode.setMargin(yogaEdge6, floatValue);
                }
                yogaNode.setBorder(yogaEdge7, floatValue);
            }
            if (view instanceof YogaLayout) {
                if (keyAt == R.styleable.yoga_yg_paddingLeft) {
                    yogaEdge8 = YogaEdge.LEFT;
                } else if (keyAt == R.styleable.yoga_yg_paddingTop) {
                    yogaEdge8 = YogaEdge.TOP;
                } else if (keyAt == R.styleable.yoga_yg_paddingRight) {
                    yogaEdge8 = YogaEdge.RIGHT;
                } else if (keyAt == R.styleable.yoga_yg_paddingBottom) {
                    yogaEdge8 = YogaEdge.BOTTOM;
                } else if (keyAt == R.styleable.yoga_yg_paddingStart) {
                    yogaEdge8 = YogaEdge.START;
                } else if (keyAt == R.styleable.yoga_yg_paddingEnd) {
                    yogaEdge8 = YogaEdge.END;
                } else if (keyAt == R.styleable.yoga_yg_paddingHorizontal) {
                    yogaEdge8 = YogaEdge.HORIZONTAL;
                } else if (keyAt == R.styleable.yoga_yg_paddingVertical) {
                    yogaEdge8 = YogaEdge.VERTICAL;
                } else if (keyAt == R.styleable.yoga_yg_paddingAll) {
                    yogaEdge8 = YogaEdge.ALL;
                }
                yogaNode.setPadding(yogaEdge8, floatValue);
            }
        }
        for (int i3 = 0; i3 < layoutParams.stringAttributes.size(); i3++) {
            int keyAt2 = layoutParams.stringAttributes.keyAt(i3);
            String valueAt = layoutParams.stringAttributes.valueAt(i3);
            if ("auto".equals(valueAt)) {
                if (keyAt2 == R.styleable.yoga_yg_marginLeft) {
                    yogaEdge4 = YogaEdge.LEFT;
                } else if (keyAt2 == R.styleable.yoga_yg_marginTop) {
                    yogaEdge4 = YogaEdge.TOP;
                } else if (keyAt2 == R.styleable.yoga_yg_marginRight) {
                    yogaEdge4 = YogaEdge.RIGHT;
                } else if (keyAt2 == R.styleable.yoga_yg_marginBottom) {
                    yogaEdge4 = YogaEdge.BOTTOM;
                } else if (keyAt2 == R.styleable.yoga_yg_marginStart) {
                    yogaEdge4 = YogaEdge.START;
                } else if (keyAt2 == R.styleable.yoga_yg_marginEnd) {
                    yogaEdge4 = YogaEdge.END;
                } else if (keyAt2 == R.styleable.yoga_yg_marginHorizontal) {
                    yogaEdge4 = YogaEdge.HORIZONTAL;
                } else if (keyAt2 == R.styleable.yoga_yg_marginVertical) {
                    yogaEdge4 = YogaEdge.VERTICAL;
                } else if (keyAt2 == R.styleable.yoga_yg_marginAll) {
                    yogaEdge4 = YogaEdge.ALL;
                }
                yogaNode.setMarginAuto(yogaEdge4);
            }
            if (valueAt.endsWith("%")) {
                float f2 = 0.0f;
                try {
                    f2 = Float.parseFloat(valueAt.substring(0, valueAt.length() - 1));
                } catch (Exception unused) {
                }
                if (keyAt2 == R.styleable.yoga_yg_flexBasis) {
                    yogaNode.setFlexBasisPercent(f2);
                } else if (keyAt2 == R.styleable.yoga_yg_height) {
                    yogaNode.setHeightPercent(f2);
                } else {
                    if (keyAt2 == R.styleable.yoga_yg_marginLeft) {
                        yogaEdge2 = YogaEdge.LEFT;
                    } else if (keyAt2 == R.styleable.yoga_yg_marginTop) {
                        yogaEdge2 = YogaEdge.TOP;
                    } else if (keyAt2 == R.styleable.yoga_yg_marginRight) {
                        yogaEdge2 = YogaEdge.RIGHT;
                    } else if (keyAt2 == R.styleable.yoga_yg_marginBottom) {
                        yogaEdge2 = YogaEdge.BOTTOM;
                    } else if (keyAt2 == R.styleable.yoga_yg_marginStart) {
                        yogaEdge2 = YogaEdge.START;
                    } else if (keyAt2 == R.styleable.yoga_yg_marginEnd) {
                        yogaEdge2 = YogaEdge.END;
                    } else if (keyAt2 == R.styleable.yoga_yg_marginHorizontal) {
                        yogaEdge2 = YogaEdge.HORIZONTAL;
                    } else if (keyAt2 == R.styleable.yoga_yg_marginVertical) {
                        yogaEdge2 = YogaEdge.VERTICAL;
                    } else if (keyAt2 == R.styleable.yoga_yg_marginAll) {
                        yogaEdge2 = YogaEdge.ALL;
                    } else if (keyAt2 == R.styleable.yoga_yg_maxHeight) {
                        yogaNode.setMaxHeightPercent(f2);
                    } else if (keyAt2 == R.styleable.yoga_yg_maxWidth) {
                        yogaNode.setMaxWidthPercent(f2);
                    } else if (keyAt2 == R.styleable.yoga_yg_minHeight) {
                        yogaNode.setMinHeightPercent(f2);
                    } else if (keyAt2 == R.styleable.yoga_yg_minWidth) {
                        yogaNode.setMinWidthPercent(f2);
                    } else {
                        if (keyAt2 == R.styleable.yoga_yg_positionLeft) {
                            yogaEdge = YogaEdge.LEFT;
                        } else if (keyAt2 == R.styleable.yoga_yg_positionTop) {
                            yogaEdge = YogaEdge.TOP;
                        } else if (keyAt2 == R.styleable.yoga_yg_positionRight) {
                            yogaEdge = YogaEdge.RIGHT;
                        } else if (keyAt2 == R.styleable.yoga_yg_positionBottom) {
                            yogaEdge = YogaEdge.BOTTOM;
                        } else if (keyAt2 == R.styleable.yoga_yg_positionStart) {
                            yogaEdge = YogaEdge.START;
                        } else if (keyAt2 == R.styleable.yoga_yg_positionEnd) {
                            yogaEdge = YogaEdge.END;
                        } else if (keyAt2 == R.styleable.yoga_yg_positionHorizontal) {
                            yogaEdge = YogaEdge.HORIZONTAL;
                        } else if (keyAt2 == R.styleable.yoga_yg_positionVertical) {
                            yogaEdge = YogaEdge.VERTICAL;
                        } else if (keyAt2 == R.styleable.yoga_yg_positionAll) {
                            yogaEdge = YogaEdge.ALL;
                        } else if (keyAt2 == R.styleable.yoga_yg_width) {
                            yogaNode.setWidthPercent(f2);
                        }
                        yogaNode.setPositionPercent(yogaEdge, f2);
                    }
                    yogaNode.setMarginPercent(yogaEdge2, f2);
                }
                if (view instanceof YogaLayout) {
                    if (keyAt2 == R.styleable.yoga_yg_paddingLeft) {
                        yogaEdge3 = YogaEdge.LEFT;
                    } else if (keyAt2 == R.styleable.yoga_yg_paddingTop) {
                        yogaEdge3 = YogaEdge.TOP;
                    } else if (keyAt2 == R.styleable.yoga_yg_paddingRight) {
                        yogaEdge3 = YogaEdge.RIGHT;
                    } else if (keyAt2 == R.styleable.yoga_yg_paddingBottom) {
                        yogaEdge3 = YogaEdge.BOTTOM;
                    } else if (keyAt2 == R.styleable.yoga_yg_paddingStart) {
                        yogaEdge3 = YogaEdge.START;
                    } else if (keyAt2 == R.styleable.yoga_yg_paddingEnd) {
                        yogaEdge3 = YogaEdge.END;
                    } else if (keyAt2 == R.styleable.yoga_yg_paddingHorizontal) {
                        yogaEdge3 = YogaEdge.HORIZONTAL;
                    } else if (keyAt2 == R.styleable.yoga_yg_paddingVertical) {
                        yogaEdge3 = YogaEdge.VERTICAL;
                    } else if (keyAt2 == R.styleable.yoga_yg_paddingAll) {
                        yogaEdge3 = YogaEdge.ALL;
                    }
                    yogaNode.setPaddingPercent(yogaEdge3, f2);
                }
            }
        }
    }

    private void applyLayoutRecursive(YogaNode yogaNode, float f2, float f3) {
        if (yogaNode.getData() instanceof View) {
            View view = (View) yogaNode.getData();
            if (view != null && view != this) {
                if (view.getVisibility() == 8) {
                    return;
                }
                int round = Math.round(yogaNode.getLayoutX() + f2);
                int round2 = Math.round(yogaNode.getLayoutY() + f3);
                view.measure(View.MeasureSpec.makeMeasureSpec(Math.round(yogaNode.getLayoutWidth()), 1073741824), View.MeasureSpec.makeMeasureSpec(Math.round(yogaNode.getLayoutHeight()), 1073741824));
                view.layout(round, round2, view.getMeasuredWidth() + round, view.getMeasuredHeight() + round2);
                if (view instanceof CollectionView) {
                    CollectionView collectionView = (CollectionView) view;
                    if (collectionView.getRecyclerView() != null) {
                        RecyclerView recyclerView = collectionView.getRecyclerView();
                        if (recyclerView.getTag(R.id.dynamic_recyclerview_notify_tag) != null && recyclerView.getAdapter() != null) {
                            ((RecyclerViewAdapter) recyclerView.getAdapter()).notifyChange();
                        }
                    }
                }
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
    }

    private void createLayout(int i2, int i3) {
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        this.mYogaNode.setWidth(Float.NaN);
        this.mYogaNode.setHeight(Float.NaN);
        if (mode2 == 1073741824) {
            this.mYogaNode.setHeight(size2);
        }
        if (mode == 1073741824) {
            this.mYogaNode.setWidth(size);
        }
        if (this.mYogaNode.getFlexDirection() != YogaFlexDirection.COLUMN) {
            this.mYogaNode.getFlexDirection();
            YogaFlexDirection yogaFlexDirection = YogaFlexDirection.COLUMN_REVERSE;
        }
        if (mode2 == Integer.MIN_VALUE) {
            this.mYogaNode.setMaxHeight(size2);
        }
        if (mode == Integer.MIN_VALUE) {
            this.mYogaNode.setMaxWidth(size);
        }
        this.mYogaNode.calculateLayout(Float.NaN, Float.NaN);
    }

    private float getNodeValue(float f2) {
        if (YogaConstants.isUndefined(f2)) {
            return 0.0f;
        }
        return f2;
    }

    private void init() {
        this.path = new Path();
        setWillNotDraw(false);
    }

    private void initClipPaint() {
        Paint paint = new Paint(1);
        this.mClipPaint = paint;
        paint.setDither(true);
        this.mClipPaint.setFilterBitmap(true);
    }

    private void initDefaultConfig() {
        this.mShadowStrategy = new c(this, getContext(), null);
    }

    private void invalidateAll() {
        int childCount = this.mYogaNode.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            YogaNode childAt = this.mYogaNode.getChildAt(i2);
            if (childAt.isMeasureDefined()) {
                childAt.dirty();
            }
            if (childAt.getData() instanceof YogaLayout) {
                ((YogaLayout) childAt.getData()).invalidateAll();
            }
        }
    }

    private void newSetVisibility(View view, int i2) {
        int i3;
        YogaNode yogaNode = this.mYogaNodes.get(view);
        try {
            if (i2 != 8) {
                try {
                    i3 = ((Integer) view.getTag(R.id.dynamic_yoga_child_position)).intValue();
                } catch (Exception unused) {
                    i3 = -1;
                }
                if (this.mYogaNode.indexOf(yogaNode) != -1) {
                    return;
                }
                if (i3 == -1) {
                    this.mYogaNode.addChildAt(this.mYogaNodes.get(view), this.mYogaNode.getChildCount());
                } else if (this.mYogaNode.getChildAt(i3).getData() != a.b) {
                } else {
                    this.mYogaNode.addChildAt(this.mYogaNodes.get(view), i3);
                    this.mYogaNode.removeChildAt(i3 + 1);
                }
            } else {
                int indexOf = this.mYogaNode.indexOf(yogaNode);
                if (indexOf == -1) {
                    return;
                }
                YogaNode create = YogaNodeFactory.create();
                create.setHeight(0.0f);
                create.setWidth(0.0f);
                create.setData(a.b);
                this.mYogaNode.addChildAt(create, indexOf);
                this.mYogaNode.removeChildAt(indexOf + 1);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void removeViewFromYogaTree(View view, boolean z) {
        YogaNode yogaNode = this.mYogaNodes.get(view);
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
        this.mYogaNodes.remove(view);
        if (z) {
            this.mYogaNode.calculateLayout(Float.NaN, Float.NaN);
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        YogaNode create;
        this.mYogaNode.setMeasureFunction(null);
        if (view instanceof VirtualYogaLayout) {
            VirtualYogaLayout virtualYogaLayout = (VirtualYogaLayout) view;
            virtualYogaLayout.transferChildren(this);
            YogaNode yogaNode = virtualYogaLayout.getYogaNode();
            YogaNode yogaNode2 = this.mYogaNode;
            yogaNode2.addChildAt(yogaNode, yogaNode2.getChildCount());
            return;
        }
        super.addView(view, i2, layoutParams);
        if (view instanceof YogaLayout) {
            create = ((YogaLayout) view).getYogaNode();
        } else {
            create = this.mYogaNodes.containsKey(view) ? this.mYogaNodes.get(view) : YogaNodeFactory.create();
            create.setData(view);
            create.setMeasureFunction(new ViewMeasureFunction());
        }
        applyLayoutParams((LayoutParams) view.getLayoutParams(), create, view);
        this.mYogaNodes.put(view, create);
        if (view.getVisibility() == 8) {
            create = YogaNodeFactory.create();
            create.setHeight(0.0f);
            create.setWidth(0.0f);
            create.setData(a.b);
        }
        int childCount = this.mYogaNode.getChildCount();
        this.mYogaNode.addChildAt(create, childCount);
        view.setTag(R.id.dynamic_yoga_child_position, Integer.valueOf(childCount));
    }

    public void addView(View view, YogaNode yogaNode) {
        this.mYogaNodes.put(view, yogaNode);
        addView(view);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void childDrawableStateChanged(View view) {
        b bVar;
        super.childDrawableStateChanged(view);
        if (!this.isShadowEnable || (bVar = this.mShadowStrategy) == null) {
            return;
        }
        bVar.a(view);
        this.mShadowStrategy.h();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        b bVar;
        super.dispatchDraw(canvas);
        if (!this.isShadowEnable || (bVar = this.mShadowStrategy) == null) {
            return;
        }
        bVar.a(canvas);
        this.mShadowStrategy.b(canvas);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        if (this.lt == 0.0f && this.rt == 0.0f && this.lb == 0.0f && this.rb == 0.0f) {
            super.draw(canvas);
        } else if (Build.VERSION.SDK_INT > 26) {
            canvas.clipPath(this.path);
            super.draw(canvas);
        } else {
            canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), this.paint, 31);
            canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
            super.draw(canvas);
            this.paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            canvas.drawPath(this.path, this.paint);
            this.paint.setXfermode(null);
            canvas.restore();
        }
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public float getShadowAlpha() {
        b bVar = this.mShadowStrategy;
        if (bVar != null) {
            return bVar.g();
        }
        return 0.0f;
    }

    public int getShadowColor() {
        b bVar = this.mShadowStrategy;
        if (bVar != null) {
            return bVar.f();
        }
        return 0;
    }

    public int getShadowOffsetDx() {
        b bVar = this.mShadowStrategy;
        if (bVar != null) {
            return bVar.d();
        }
        return 0;
    }

    public int getShadowOffsetDy() {
        b bVar = this.mShadowStrategy;
        if (bVar != null) {
            return bVar.e();
        }
        return 0;
    }

    public int getShadowRadius() {
        b bVar = this.mShadowStrategy;
        if (bVar != null) {
            return bVar.c();
        }
        return 0;
    }

    public LayoutParams getYogaLayoutLayoutParams() {
        return this.mLayoutParams;
    }

    public YogaNode getYogaNode() {
        return this.mYogaNode;
    }

    public YogaNode getYogaNodeForView(View view) {
        return this.mYogaNodes.get(view);
    }

    public void initShadow() {
        this.isShadowEnable = true;
        initClipPaint();
        initDefaultConfig();
    }

    public void invalidate(View view) {
        if (!this.mYogaNodes.containsKey(view)) {
            int childCount = this.mYogaNode.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                YogaNode childAt = this.mYogaNode.getChildAt(i2);
                if (childAt.getData() instanceof YogaLayout) {
                    ((YogaLayout) childAt.getData()).invalidate(view);
                }
            }
            invalidate();
            return;
        }
        if (this.mYogaNodes.get(view).isMeasureDefined()) {
            this.mYogaNodes.get(view).dirty();
        }
        if ((view instanceof ScrollView) || (view instanceof HorizontalScrollView)) {
            View childAt2 = ((ViewGroup) view).getChildAt(0);
            if (childAt2 instanceof YogaLayout) {
                ((YogaLayout) childAt2).invalidateAll();
                childAt2.requestLayout();
            }
        }
    }

    public boolean isShadowClipCanvas() {
        b bVar = this.mShadowStrategy;
        if (bVar != null) {
            return bVar.b();
        }
        return false;
    }

    public boolean isShadowEnable() {
        b bVar = this.mShadowStrategy;
        if (bVar != null) {
            return bVar.a();
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        b bVar;
        super.onAttachedToWindow();
        if (!this.isShadowEnable || (bVar = this.mShadowStrategy) == null) {
            return;
        }
        bVar.j();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        b bVar;
        super.onDetachedFromWindow();
        if (!this.isShadowEnable || (bVar = this.mShadowStrategy) == null) {
            return;
        }
        bVar.k();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        b bVar;
        t.e("YogaLayout", Integer.valueOf(getId()), ViewProps.ON_LAYOUT);
        if (!(getParent() instanceof YogaLayout)) {
            createLayout(View.MeasureSpec.makeMeasureSpec(i4 - i2, 1073741824), View.MeasureSpec.makeMeasureSpec(i5 - i3, 1073741824));
        }
        applyLayoutRecursive(this.mYogaNode, 0.0f, 0.0f);
        if (!this.isShadowEnable || (bVar = this.mShadowStrategy) == null) {
            return;
        }
        bVar.d(z, i2, i3, i4, i5);
        this.mShadowStrategy.h();
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        t.e("YogaLayout", Integer.valueOf(getId()), "onMeasure");
        if (!(getParent() instanceof YogaLayout)) {
            createLayout(i2, i3);
        }
        setMeasuredDimension(Math.round(this.mYogaNode.getLayoutWidth()), Math.round(this.mYogaNode.getLayoutHeight()));
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.path.reset();
        Path path = this.path;
        RectF rectF = new RectF(0.0f, 0.0f, getWidth(), getHeight());
        float f2 = this.lt;
        float f3 = this.rt;
        float f4 = this.rb;
        float f5 = this.lb;
        path.addRoundRect(rectF, new float[]{f2, f2, f3, f3, f4, f4, f5, f5}, Path.Direction.CCW);
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

    public void setChildVisibility(View view, int i2) {
        view.setVisibility(i2);
        newSetVisibility(view, i2);
    }

    public void setCorner(int i2, int i3, int i4, int i5) {
        float f2 = i2;
        this.lt = f2;
        float f3 = i3;
        this.rt = f3;
        float f4 = i4;
        this.lb = f4;
        float f5 = i5;
        this.rb = f5;
        this.path.reset();
        this.path.addRoundRect(new RectF(0.0f, 0.0f, getWidth(), getHeight()), new float[]{f2, f2, f3, f3, f5, f5, f4, f4}, Path.Direction.CCW);
        invalidate();
    }

    public void setCornerRadii(float f2) {
        setCornerRadii(new float[]{f2, f2, f2, f2, f2, f2, f2, f2});
    }

    public void setCornerRadii(float[] fArr) {
        b bVar;
        if (!this.isShadowEnable || (bVar = this.mShadowStrategy) == null) {
            return;
        }
        bVar.c(fArr);
        this.mShadowStrategy.h();
    }

    public void setShadowAlpha(float f2) {
        b bVar;
        if (!this.isShadowEnable || (bVar = this.mShadowStrategy) == null) {
            return;
        }
        bVar.a(f2);
        this.mShadowStrategy.h();
    }

    public void setShadowClipCanvas(boolean z) {
        b bVar = this.mShadowStrategy;
        if (bVar != null) {
            bVar.b(z);
            this.mShadowStrategy.h();
        }
    }

    public void setShadowColor(int i2) {
        b bVar;
        if (!this.isShadowEnable || (bVar = this.mShadowStrategy) == null) {
            return;
        }
        bVar.a(ColorStateList.valueOf(i2));
    }

    public void setShadowColor(ColorStateList colorStateList) {
        b bVar = this.mShadowStrategy;
        if (bVar != null) {
            bVar.a(colorStateList);
        }
    }

    public void setShadowEnable(boolean z) {
        b bVar = this.mShadowStrategy;
        if (bVar != null) {
            bVar.a(z);
            this.mShadowStrategy.h();
        }
    }

    public void setShadowOffsetDx(int i2) {
        b bVar;
        if (!this.isShadowEnable || (bVar = this.mShadowStrategy) == null) {
            return;
        }
        bVar.b(i2);
        requestLayout();
    }

    public void setShadowOffsetDy(int i2) {
        b bVar;
        if (!this.isShadowEnable || (bVar = this.mShadowStrategy) == null) {
            return;
        }
        bVar.c(i2);
        requestLayout();
    }

    public void setShadowRadius(int i2) {
        b bVar;
        if (!this.isShadowEnable || (bVar = this.mShadowStrategy) == null) {
            return;
        }
        bVar.a(i2);
        requestLayout();
    }

    public void superDispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }
}
