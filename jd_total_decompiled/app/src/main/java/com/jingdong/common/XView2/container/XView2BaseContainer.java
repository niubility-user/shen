package com.jingdong.common.XView2.container;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.XView2.XView2;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.common.XView2LayerObservableManager;
import com.jingdong.common.XView2.entity.LocationEntity;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.layer.BaseLayerDelegate;
import com.jingdong.common.XView2.utils.LayerUtil;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes5.dex */
public abstract class XView2BaseContainer extends FrameLayout implements IContainer {
    private boolean isPackUp;
    private AnimatorSet mAnimatorSet;
    private ValueAnimator mAnimatorX;
    private ValueAnimator mAnimatorY;
    public BaseLayerDelegate mBaseLayerDelegate;
    public View mCloseBtn;
    public String mContainerParent;
    public ContentFrameLayout mContentContainer;
    public int mContentContainerHeight;
    public int mContentContainerLeft;
    public int mContentContainerTop;
    public int mContentContainerWidth;
    protected Context mContext;
    public AtomicBoolean mHasLayoutEd;
    public boolean mIsFullFill;
    public XViewConfigEntity.LayersEntity mLayersEntity;
    public View mRootContainer;
    public ViewGroup mRootView;
    private int mScreenMargin;
    private AtomicBoolean mScrollState;
    public XView2 mXView2;
    private Handler sHandler;

    public XView2BaseContainer(@NonNull Context context) {
        super(context);
        this.isPackUp = false;
        this.mScrollState = new AtomicBoolean(true);
        this.sHandler = new Handler(Looper.getMainLooper());
        this.mScreenMargin = 0;
        this.mContentContainer = null;
        this.mCloseBtn = null;
        this.mXView2 = null;
        this.mIsFullFill = false;
        this.mHasLayoutEd = new AtomicBoolean(false);
        this.mRootContainer = null;
        this.mContainerParent = "";
        this.mContentContainerLeft = 0;
        this.mContentContainerTop = 0;
        this.mContentContainerHeight = 0;
        this.mContentContainerWidth = 0;
        this.mContext = context;
    }

    public void addContainer() {
        if (this.mXView2 == null) {
            return;
        }
        XViewConfigEntity.LayersEntity layersEntity = this.mLayersEntity;
        FrameLayout.LayoutParams generateLayoutParams = generateLayoutParams(layersEntity.layout, layersEntity.fullScreen);
        if (generateLayoutParams == null) {
            return;
        }
        try {
            if (this.mContext != null) {
                if (XView2Utils.isGlobalLayer(this.mLayersEntity) && (this.mContext instanceof Activity)) {
                    setZIndex(Long.MAX_VALUE);
                    addViewToActivity(generateLayoutParams);
                    this.mContainerParent = "activity";
                } else {
                    if (!XView2Utils.isConvertBool(this.mLayersEntity.fullScreen) && !"2".equals(this.mLayersEntity.layout.containerTy) && this.mXView2.getCurrentFragment() != null) {
                        Context context = this.mContext;
                        if ((context instanceof BaseActivity) && ((BaseActivity) context).getSupportFragmentManager() != null && this.mXView2.getCurrentFragment() != null && !"1".equals(this.mLayersEntity.fullScreen) && XView2Utils.isHitTargetCheck(this.mXView2)) {
                            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u5f39\u7a97\u6dfb\u52a0\u5230Fragment\u4e0a");
                            if ("2".equals(this.mLayersEntity.layout.containerTy)) {
                                addViewToActivity(generateLayoutParams);
                                this.mContainerParent = "activity";
                            }
                            if ("1".equals(this.mLayersEntity.layout.containerTy) || TextUtils.isEmpty(this.mLayersEntity.layout.containerTy)) {
                                addViewToFragmentView(generateLayoutParams);
                                this.mContainerParent = XView2Constants.FRAGMENT;
                            }
                        }
                    }
                    addViewToActivity(generateLayoutParams);
                    this.mContainerParent = "activity";
                }
                addContentContainer();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.common.XView2.container.IContainer
    public void addContainerView(View view, XViewConfigEntity.ControlEntity controlEntity) {
        if (controlEntity == null) {
            return;
        }
        if (view != null && view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        if ("1".equals(controlEntity.type)) {
            this.mCloseBtn = view;
            addView(view);
            return;
        }
        ContentFrameLayout contentFrameLayout = this.mContentContainer;
        if (contentFrameLayout != null) {
            contentFrameLayout.addView(view);
        }
    }

    public void addContentContainer() {
        FrameLayout.LayoutParams layoutParams;
        FrameLayout.LayoutParams layoutParams2;
        XViewConfigEntity.LayoutEntity layoutEntity;
        if (this.mLayersEntity == null) {
            return;
        }
        if (this.mContentContainer == null) {
            this.mContentContainer = new ContentFrameLayout(this.mContext);
        }
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-2, -2);
        if (LayerUtil.isConfigMaterialLayout(this.mLayersEntity)) {
            XViewConfigEntity.LayersEntity layersEntity = this.mLayersEntity;
            XViewConfigEntity.LayoutEntity layoutEntity2 = layersEntity.renderData.layout;
            long j2 = layoutEntity2.height;
            long j3 = layoutEntity2.top;
            long j4 = j2 + j3 > 0 ? j2 + j3 : 0L;
            long j5 = layoutEntity2.width;
            long j6 = layoutEntity2.left;
            long j7 = j5 + j6 > 0 ? j5 + j6 : 0L;
            if (!XView2Utils.isListEmpty(layersEntity.controls)) {
                Iterator<XViewConfigEntity.ControlEntity> it = this.mLayersEntity.controls.iterator();
                while (it.hasNext()) {
                    XViewConfigEntity.ControlEntity next = it.next();
                    if (next != null && XView2Utils.isConvertBool(next.isShow) && !"1".equals(next.type)) {
                        long j8 = next.height;
                        long j9 = next.top;
                        if (j8 + j9 > j4) {
                            j4 = j8 + j9;
                        }
                        long j10 = next.width;
                        FrameLayout.LayoutParams layoutParams4 = layoutParams3;
                        Iterator<XViewConfigEntity.ControlEntity> it2 = it;
                        long j11 = next.left;
                        if (j10 + j11 > j7) {
                            j7 = j10 + j11;
                        }
                        if (j9 < j3) {
                            j3 = j9;
                        }
                        if (j11 < j6) {
                            j6 = j11;
                        }
                        layoutParams3 = layoutParams4;
                        it = it2;
                    }
                }
            }
            layoutParams = layoutParams3;
            if (j7 > j6 && j4 > j3) {
                int i2 = (int) (j7 - j6);
                int i3 = (int) (j4 - j3);
                layoutParams2 = new FrameLayout.LayoutParams(XView2Utils.getSizeBy750(i2), XView2Utils.getSizeBy750(i3));
                this.mContentContainerHeight = XView2Utils.getSizeBy750(i3);
                this.mContentContainerWidth = XView2Utils.getSizeBy750(i2);
                int i4 = (int) j6;
                this.mContentContainerLeft = XView2Utils.getSizeBy750(i4);
                this.mContentContainerTop = calculateTopMargin(this.mLayersEntity.layout, j3);
                XViewConfigEntity.LayoutEntity layoutEntity3 = this.mLayersEntity.layout;
                if (layoutEntity3 != null && XView2Utils.isConvertBool(layoutEntity3.contentForceCenter)) {
                    layoutParams2.topMargin = (XView2Utils.H_HEIGHT / 2) - (this.mContentContainerHeight / 2);
                    layoutParams2.leftMargin = (XView2Utils.H_WIDTH / 2) - (this.mContentContainerWidth / 2);
                } else {
                    layoutParams2.topMargin = calculateTopMargin(this.mLayersEntity.layout, j3);
                    layoutParams2.leftMargin = XView2Utils.getSizeBy750(i4);
                }
            } else {
                if (this.mLayersEntity.layout != null) {
                    layoutParams2 = new FrameLayout.LayoutParams(XView2Utils.getSizeBy750((int) this.mLayersEntity.layout.width), XView2Utils.getSizeBy750((int) this.mLayersEntity.layout.height));
                    this.mContentContainerHeight = XView2Utils.getSizeBy750((int) this.mLayersEntity.layout.height);
                    this.mContentContainerWidth = XView2Utils.getSizeBy750((int) this.mLayersEntity.layout.width);
                    this.mContentContainerLeft = XView2Utils.getSizeBy750((int) this.mLayersEntity.layout.left);
                    XViewConfigEntity.LayoutEntity layoutEntity4 = this.mLayersEntity.layout;
                    this.mContentContainerTop = calculateTopMargin(layoutEntity4, layoutEntity4.top);
                    XViewConfigEntity.LayoutEntity layoutEntity5 = this.mLayersEntity.layout;
                    if (layoutEntity5 != null && XView2Utils.isConvertBool(layoutEntity5.contentForceCenter)) {
                        layoutParams2.topMargin = (XView2Utils.H_HEIGHT / 2) - (this.mContentContainerHeight / 2);
                        layoutParams2.leftMargin = (XView2Utils.H_WIDTH / 2) - (this.mContentContainerWidth / 2);
                    }
                }
                layoutParams2 = layoutParams;
            }
        } else {
            layoutParams = layoutParams3;
            if (!XView2Utils.isConvertBool(this.mLayersEntity.fullScreen) && ((layoutEntity = this.mLayersEntity.layout) == null || !XView2Utils.isConvertBool(layoutEntity.fill))) {
                if (this.mLayersEntity.layout != null) {
                    layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
                    this.mContentContainerHeight = XView2Utils.getSizeBy750((int) this.mLayersEntity.layout.height);
                    this.mContentContainerWidth = XView2Utils.getSizeBy750((int) this.mLayersEntity.layout.width);
                    this.mContentContainerLeft = XView2Utils.getSizeBy750((int) this.mLayersEntity.layout.left);
                    XViewConfigEntity.LayoutEntity layoutEntity6 = this.mLayersEntity.layout;
                    this.mContentContainerTop = calculateTopMargin(layoutEntity6, layoutEntity6.top);
                    XViewConfigEntity.LayoutEntity layoutEntity7 = this.mLayersEntity.layout;
                    if (layoutEntity7 != null && XView2Utils.isConvertBool(layoutEntity7.contentForceCenter)) {
                        layoutParams2.topMargin = (XView2Utils.H_HEIGHT / 2) - (this.mContentContainerHeight / 2);
                        layoutParams2.leftMargin = (XView2Utils.H_WIDTH / 2) - (this.mContentContainerWidth / 2);
                    }
                }
                layoutParams2 = layoutParams;
            } else {
                layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
            }
        }
        ContentFrameLayout contentFrameLayout = this.mContentContainer;
        if (contentFrameLayout != null && contentFrameLayout.getParent() != null) {
            ((ViewGroup) this.mContentContainer.getParent()).removeView(this.mContentContainer);
        }
        if (this.mContext != null) {
            addView(this.mContentContainer, layoutParams2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void addLayerViewWidthParams(View view) {
        int i2;
        int i3;
        ContentFrameLayout contentFrameLayout;
        XViewConfigEntity.LayoutEntity layoutEntity;
        if (this.mLayersEntity == null) {
            return;
        }
        int i4 = 0;
        if (this.mContentContainer != null) {
            i4 = this.mContentContainerLeft;
            i2 = this.mContentContainerTop;
            i3 = this.mContentContainerWidth;
        } else {
            i2 = 0;
            i3 = 0;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        XViewConfigEntity.RenderDataEntity renderDataEntity = this.mLayersEntity.renderData;
        if (renderDataEntity != null && (layoutEntity = renderDataEntity.layout) != null) {
            long j2 = layoutEntity.height;
            if (j2 > 0) {
                long j3 = layoutEntity.width;
                if (j3 > 0) {
                    long j4 = layoutEntity.top;
                    long j5 = layoutEntity.left;
                    layoutParams.height = XView2Utils.getSizeBy750((int) j2);
                    layoutParams.width = XView2Utils.getSizeBy750((int) j3);
                    XViewConfigEntity.LayoutEntity layoutEntity2 = this.mLayersEntity.layout;
                    if (layoutEntity2 != null && XView2Utils.isConvertBool(layoutEntity2.contentForceCenter)) {
                        layoutParams.topMargin = XView2Utils.getSizeBy750((int) this.mLayersEntity.renderData.layout.top) - i2;
                        int sizeBy750 = XView2Utils.getSizeBy750((int) this.mLayersEntity.renderData.layout.left);
                        double d = XView2Utils.H_WIDTH;
                        Double.isNaN(d);
                        double d2 = i3;
                        Double.isNaN(d2);
                        layoutParams.leftMargin = sizeBy750 - ((int) ((d * 0.5d) - (d2 * 0.5d)));
                    } else {
                        int i5 = (int) j4;
                        if (XView2Utils.getSizeBy750(i5) >= i2) {
                            layoutParams.topMargin = XView2Utils.getSizeBy750(i5) - i2;
                        }
                        int i6 = (int) j5;
                        if (XView2Utils.getSizeBy750(i6) >= i4) {
                            layoutParams.leftMargin = XView2Utils.getSizeBy750(i6) - i4;
                        }
                    }
                    if (view != null && view.getParent() != null) {
                        ((ViewGroup) view.getParent()).removeView(view);
                    }
                    contentFrameLayout = this.mContentContainer;
                    if (contentFrameLayout == null) {
                        contentFrameLayout.addView(view, layoutParams);
                        return;
                    }
                    return;
                }
            }
        }
        layoutParams = new FrameLayout.LayoutParams(-1, -1);
        if (view != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        contentFrameLayout = this.mContentContainer;
        if (contentFrameLayout == null) {
        }
    }

    @Override // com.jingdong.common.XView2.container.IContainer
    public void addViewToActivity(FrameLayout.LayoutParams layoutParams) {
        View decorView = ((Activity) this.mContext).getWindow().getDecorView();
        this.mRootContainer = decorView;
        if (decorView instanceof ViewGroup) {
            XView2LevelManager.getManager().sortContainerLevel(this.mRootContainer, this, addViewToActivityWithParams(layoutParams));
        }
    }

    public FrameLayout.LayoutParams addViewToActivityWithParams(FrameLayout.LayoutParams layoutParams) {
        XViewConfigEntity.LayoutEntity layoutEntity;
        XViewConfigEntity.LayersEntity layersEntity = this.mLayersEntity;
        if (layersEntity != null && layersEntity.renderType == 6 && (layoutEntity = layersEntity.layout) != null) {
            String str = layoutEntity.align;
            String str2 = "3";
            if (!"1".equals(str) && !"2".equals(str) && !"3".equals(str) && !XView2Utils.isConvertBool(this.mLayersEntity.layout.fill)) {
                if ("right".equals(this.mLayersEntity.layout.leftOrRight)) {
                    layoutParams.leftMargin = 0;
                    layoutParams.rightMargin = XView2Utils.getSizeBy750((int) this.mLayersEntity.layout.right);
                    str = "3";
                } else {
                    layoutParams.leftMargin = XView2Utils.getSizeBy750((int) this.mLayersEntity.layout.left);
                    layoutParams.rightMargin = 0;
                }
            } else {
                layoutParams.leftMargin = 0;
                layoutParams.rightMargin = 0;
            }
            String str3 = this.mLayersEntity.layout.verticalAlign;
            if (!"1".equals(str3) && !"2".equals(str3) && !"3".equals(str3) && !XView2Utils.isConvertBool(this.mLayersEntity.layout.fill)) {
                if ("bottom".equals(this.mLayersEntity.layout.topOrBottom)) {
                    layoutParams.topMargin = 0;
                    XViewConfigEntity.LayoutEntity layoutEntity2 = this.mLayersEntity.layout;
                    layoutParams.bottomMargin = calculateBottomMargin(layoutEntity2, layoutEntity2.bottom);
                    layoutParams.gravity = XView2Utils.getAlign(str, str2);
                } else {
                    XViewConfigEntity.LayoutEntity layoutEntity3 = this.mLayersEntity.layout;
                    layoutParams.topMargin = calculateTopMargin(layoutEntity3, layoutEntity3.top);
                    layoutParams.bottomMargin = 0;
                }
            } else {
                layoutParams.topMargin = 0;
                layoutParams.bottomMargin = 0;
            }
            str2 = str3;
            layoutParams.gravity = XView2Utils.getAlign(str, str2);
        }
        return layoutParams;
    }

    @Override // com.jingdong.common.XView2.container.IContainer
    public void addViewToFragmentView(FrameLayout.LayoutParams layoutParams) {
        XViewConfigEntity.LayoutEntity layoutEntity;
        View view = this.mXView2.getCurrentFragment().getView();
        this.mRootContainer = view;
        if (view instanceof ViewGroup) {
            XViewConfigEntity.LayersEntity layersEntity = this.mLayersEntity;
            if (layersEntity.renderType == 6 && (layoutEntity = layersEntity.layout) != null) {
                String str = layoutEntity.align;
                if (!"1".equals(str) && !"2".equals(str) && !"3".equals(str)) {
                    if ("right".equals(this.mLayersEntity.layout.leftOrRight)) {
                        layoutParams.leftMargin = 0;
                        layoutParams.rightMargin = XView2Utils.getSizeBy750((int) this.mLayersEntity.layout.right);
                        str = "3";
                    } else {
                        layoutParams.leftMargin = XView2Utils.getSizeBy750((int) this.mLayersEntity.layout.left);
                        layoutParams.rightMargin = 0;
                    }
                } else {
                    layoutParams.leftMargin = 0;
                    layoutParams.rightMargin = 0;
                }
                String str2 = this.mLayersEntity.layout.verticalAlign;
                if (!"1".equals(str2) && !"2".equals(str2) && !"3".equals(str2)) {
                    if ("bottom".equals(this.mLayersEntity.layout.topOrBottom)) {
                        layoutParams.topMargin = 0;
                        XViewConfigEntity.LayoutEntity layoutEntity2 = this.mLayersEntity.layout;
                        layoutParams.bottomMargin = calculateBottomMargin(layoutEntity2, layoutEntity2.bottom);
                        str2 = "3";
                    } else {
                        XViewConfigEntity.LayoutEntity layoutEntity3 = this.mLayersEntity.layout;
                        layoutParams.topMargin = calculateTopMargin(layoutEntity3, layoutEntity3.top);
                        layoutParams.bottomMargin = 0;
                    }
                } else {
                    layoutParams.topMargin = 0;
                    layoutParams.bottomMargin = 0;
                }
                View view2 = this.mRootContainer;
                if (view2 instanceof RelativeLayout) {
                    RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(layoutParams.width, layoutParams.height);
                    layoutParams2.leftMargin = layoutParams.leftMargin;
                    layoutParams2.bottomMargin = layoutParams.bottomMargin;
                    layoutParams2.rightMargin = layoutParams.rightMargin;
                    if (this.mRootContainer != null && "3".equals(str2)) {
                        int height = this.mRootContainer.getHeight() - layoutParams.height;
                        XViewConfigEntity.LayoutEntity layoutEntity4 = this.mLayersEntity.layout;
                        layoutParams2.topMargin = height - calculateBottomMargin(layoutEntity4, layoutEntity4.bottom);
                    } else {
                        layoutParams2.topMargin = layoutParams.topMargin;
                    }
                    setRule(str, str2, layoutParams2);
                    XView2LevelManager.getManager().sortContainerLevel(this.mRootContainer, this, layoutParams2);
                    return;
                } else if (view2 instanceof FrameLayout) {
                    layoutParams.gravity = XView2Utils.getAlign(str, str2);
                    XView2LevelManager.getManager().sortContainerLevel(this.mRootContainer, this, layoutParams);
                    return;
                } else {
                    return;
                }
            }
            XView2LevelManager.getManager().sortContainerLevel(this.mRootContainer, this, layoutParams);
        }
    }

    public int calculateBottomMargin(XViewConfigEntity.LayoutEntity layoutEntity, long j2) {
        XView2 xView2;
        float floatValue;
        int sizeBy750;
        int i2;
        if (layoutEntity == null) {
            return XView2Utils.getSizeBy750((int) j2);
        }
        if (layoutEntity.baseTabTop && (xView2 = this.mXView2) != null && xView2.getTargetEntity() != null && !TextUtils.isEmpty(this.mXView2.getTargetEntity().targetLayoutInfo.paddingBottom)) {
            String str = this.mXView2.getTargetEntity().targetLayoutInfo.paddingBottom;
            if (!TextUtils.isEmpty(str)) {
                int length = str.length();
                if (str.contains("px")) {
                    float floatValue2 = Float.valueOf(str.substring(0, length - 2)).floatValue();
                    if (floatValue2 > 0.0f) {
                        sizeBy750 = XView2Utils.getSizeBy750((int) j2);
                        i2 = XView2Utils.getSizeBy750((int) floatValue2);
                        return sizeBy750 + i2;
                    }
                } else {
                    if (str.contains("pt")) {
                        floatValue = Float.valueOf(str.substring(0, length - 2)).floatValue();
                    } else {
                        floatValue = Float.valueOf(str).floatValue();
                    }
                    if (floatValue > 0.0f) {
                        sizeBy750 = XView2Utils.getSizeBy750((int) j2);
                        i2 = (int) floatValue;
                        return sizeBy750 + i2;
                    }
                }
            }
        }
        return XView2Utils.getSizeBy750((int) j2);
    }

    public int calculateTopMargin(XViewConfigEntity.LayoutEntity layoutEntity, long j2) {
        XView2 xView2;
        float floatValue;
        if (layoutEntity == null) {
            return XView2Utils.getSizeBy750((int) j2);
        }
        if (layoutEntity.baseNavBottom && (xView2 = this.mXView2) != null && xView2.getTargetEntity() != null && !TextUtils.isEmpty(this.mXView2.getTargetEntity().targetLayoutInfo.paddingTop)) {
            String str = this.mXView2.getTargetEntity().targetLayoutInfo.paddingTop;
            if (!TextUtils.isEmpty(str)) {
                int length = str.length();
                if (str.contains("px")) {
                    float floatValue2 = Float.valueOf(str.substring(0, length - 2)).floatValue();
                    if (floatValue2 > 0.0f) {
                        return getRealTopMargin((float) j2, XView2Utils.getSizeBy750((int) floatValue2));
                    }
                } else {
                    if (str.contains("pt")) {
                        floatValue = Float.valueOf(str.substring(0, length - 2)).floatValue();
                    } else {
                        floatValue = Float.valueOf(str).floatValue();
                    }
                    if (floatValue > 0.0f) {
                        return getRealTopMargin((float) j2, (int) floatValue);
                    }
                }
            }
            return XView2Utils.getSizeBy750((int) j2);
        }
        return XView2Utils.getSizeBy750((int) j2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void endMove() {
        float f2;
        float width;
        if (this.mRootView == null || "1".equals(this.mLayersEntity.layout.fill) || this.mIsFullFill) {
            return;
        }
        int left = this.mRootView.getLeft() + this.mRootView.getPaddingLeft();
        int right = this.mRootView.getRight() - this.mRootView.getPaddingRight();
        int top = this.mRootView.getTop() + this.mRootView.getPaddingTop();
        int bottom = this.mRootView.getBottom() - this.mRootView.getPaddingBottom();
        int width2 = this.mRootView.getWidth();
        this.isPackUp = false;
        if (getY() + getHeight() > bottom) {
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "1111");
            f2 = bottom - getHeight();
        } else {
            f2 = top;
            if (getY() < f2) {
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "2222");
            } else {
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "3333");
                f2 = getY();
            }
        }
        if (getX() < (((width2 - getWidth()) + left) >> 1)) {
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "4444");
            width = left + this.mScreenMargin;
        } else {
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "5555");
            width = (right - getWidth()) - this.mScreenMargin;
        }
        ValueAnimator valueAnimator = this.mAnimatorX;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.mAnimatorX.setFloatValues(getX(), width);
        } else {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(getX(), width);
            this.mAnimatorX = ofFloat;
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.XView2.container.XView2BaseContainer.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    XView2BaseContainer.this.setX(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                }
            });
            this.mAnimatorX.addListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.XView2.container.XView2BaseContainer.2
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                }
            });
        }
        ValueAnimator valueAnimator2 = this.mAnimatorY;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
            this.mAnimatorY.setFloatValues(getY(), f2);
        } else {
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(getY(), f2);
            this.mAnimatorY = ofFloat2;
            ofFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.XView2.container.XView2BaseContainer.3
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator3) {
                    XView2BaseContainer.this.setY(((Float) valueAnimator3.getAnimatedValue()).floatValue());
                }
            });
        }
        AnimatorSet animatorSet = this.mAnimatorSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        } else {
            AnimatorSet animatorSet2 = new AnimatorSet();
            this.mAnimatorSet = animatorSet2;
            animatorSet2.play(this.mAnimatorX).with(this.mAnimatorY);
            this.mAnimatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        }
        this.mAnimatorSet.start();
    }

    public FrameLayout.LayoutParams generateLayoutParams(XViewConfigEntity.LayoutEntity layoutEntity, String str) {
        if (layoutEntity == null || this.mLayersEntity == null) {
            return null;
        }
        long j2 = layoutEntity.height;
        long j3 = layoutEntity.width;
        long j4 = layoutEntity.left;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        XView2LayerObservableManager manager = XView2LayerObservableManager.getManager();
        XViewConfigEntity.LayersEntity layersEntity = this.mLayersEntity;
        int i2 = (int) j3;
        int i3 = (int) j2;
        LocationEntity notifyPopLocationObserver = manager.notifyPopLocationObserver(layersEntity.layerId, layersEntity.logicKey, XView2Utils.getSizeBy750(i2), XView2Utils.getSizeBy750(i3));
        if (notifyPopLocationObserver != null) {
            layoutParams.height = XView2Utils.getSizeBy750(i3);
            layoutParams.width = XView2Utils.getSizeBy750(i2);
            RectF rectF = notifyPopLocationObserver.rectF;
            if (rectF != null) {
                layoutParams.topMargin = (int) rectF.top;
                layoutParams.leftMargin = (int) rectF.left;
            } else if ("1".equals(notifyPopLocationObserver.LocationStatus)) {
                XView2LayerObservableManager manager2 = XView2LayerObservableManager.getManager();
                XViewConfigEntity.LayersEntity layersEntity2 = this.mLayersEntity;
                manager2.notifyLayerShowError(layersEntity2.layerId, layersEntity2.logicKey);
                return null;
            } else if (!XView2Utils.isConvertBool(str) && !XView2Utils.isConvertBool(layoutEntity.fill) && j2 > 0) {
                if (j3 > 0) {
                    layoutParams.height = XView2Utils.getSizeBy750(i3);
                    layoutParams.width = XView2Utils.getSizeBy750(i2);
                    layoutParams.topMargin = calculateTopMargin(layoutEntity, layoutEntity.top);
                    layoutParams.leftMargin = XView2Utils.getSizeBy750((int) j4);
                } else {
                    layoutParams.height = XView2Utils.getSizeBy750(i3);
                    layoutParams.width = XView2Utils.getSizeBy750(-1);
                    layoutParams.topMargin = calculateTopMargin(layoutEntity, layoutEntity.top);
                }
            }
        } else if (!XView2Utils.isConvertBool(str) && !XView2Utils.isConvertBool(layoutEntity.fill) && j2 > 0) {
            if (j3 > 0) {
                layoutParams.height = XView2Utils.getSizeBy750(i3);
                layoutParams.width = XView2Utils.getSizeBy750(i2);
                layoutParams.topMargin = calculateTopMargin(layoutEntity, layoutEntity.top);
                layoutParams.leftMargin = XView2Utils.getSizeBy750((int) j4);
            } else {
                layoutParams.height = XView2Utils.getSizeBy750(i3);
                layoutParams.width = XView2Utils.getSizeBy750(-1);
                layoutParams.topMargin = calculateTopMargin(layoutEntity, layoutEntity.top);
            }
        }
        return layoutParams;
    }

    public View getCloseBtn() {
        return this.mCloseBtn;
    }

    public int getMutexGroupID() {
        XViewConfigEntity.LayersEntity layersEntity = this.mLayersEntity;
        if (layersEntity != null) {
            return layersEntity.mutexGroupID;
        }
        return 0;
    }

    public int getRealTopMargin(float f2, int i2) {
        Context context = this.mContext;
        if ((context instanceof BaseActivity) && ((BaseActivity) context).isStatusBarTintEnable()) {
            return XView2Utils.getSizeBy750((int) f2) + i2 + UnStatusBarTintUtil.getStatusBarHeight(this.mContext);
        }
        return XView2Utils.getSizeBy750((int) f2) + i2;
    }

    @Override // com.jingdong.common.XView2.container.IContainer
    public long getZIndex() {
        XViewConfigEntity.LayoutEntity layoutEntity;
        XViewConfigEntity.LayersEntity layersEntity = this.mLayersEntity;
        if (layersEntity == null || (layoutEntity = layersEntity.layout) == null) {
            return 0L;
        }
        return layoutEntity.zIndex;
    }

    @Override // com.jingdong.common.XView2.container.IContainer
    public void initXView2Container(XView2 xView2, XViewConfigEntity.LayersEntity layersEntity) {
        if (xView2 == null) {
            return;
        }
        this.mXView2 = xView2;
        this.mLayersEntity = layersEntity;
        if (layersEntity == null || layersEntity.layout == null) {
            return;
        }
        this.mScreenMargin = XView2Utils.getSizeBy750(10);
        setVisibility(4);
        if (getParent() != null) {
            ((ViewGroup) getParent()).removeView(this);
        }
        addContainer();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AnimatorSet animatorSet = this.mAnimatorSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        Handler handler = this.sHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public void onScroll(boolean z) {
        AtomicBoolean atomicBoolean = this.mScrollState;
        if (atomicBoolean == null || this.sHandler == null || atomicBoolean.get() == z) {
            return;
        }
        this.mScrollState.set(z);
        setScrollState();
    }

    public void packUp() {
        ViewGroup viewGroup;
        float width;
        if (this.isPackUp || (viewGroup = this.mRootView) == null) {
            return;
        }
        this.isPackUp = true;
        int left = viewGroup.getLeft() + this.mRootView.getPaddingLeft();
        int right = this.mRootView.getRight() - this.mRootView.getPaddingRight();
        if (getX() == left) {
            width = left - (getWidth() >> 1);
        } else {
            width = right - (getWidth() >> 1);
        }
        ValueAnimator valueAnimator = this.mAnimatorX;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.mAnimatorX.setFloatValues(getX(), width);
        } else {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(getX(), width);
            this.mAnimatorX = ofFloat;
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.XView2.container.XView2BaseContainer.4
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    XView2BaseContainer.this.setX(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                }
            });
        }
        AnimatorSet animatorSet = this.mAnimatorSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        } else {
            AnimatorSet animatorSet2 = new AnimatorSet();
            this.mAnimatorSet = animatorSet2;
            animatorSet2.play(this.mAnimatorX);
            this.mAnimatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        }
        this.mAnimatorSet.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void removeRule(XViewConfigEntity.LayersEntity layersEntity, RelativeLayout.LayoutParams layoutParams) {
        XViewConfigEntity.LayoutEntity layoutEntity;
        if (layersEntity == null || (layoutEntity = layersEntity.layout) == null || layoutParams == null) {
            return;
        }
        if ("2".equals(layoutEntity.align)) {
            layoutParams.addRule(14, 0);
        } else if ("3".equals(layersEntity.layout.align)) {
            layoutParams.addRule(11, 0);
        }
        if ("2".equals(layersEntity.layout.verticalAlign)) {
            layoutParams.addRule(15, 0);
        } else if ("3".equals(layersEntity.layout.verticalAlign)) {
            layoutParams.addRule(12, 0);
        }
    }

    public void setBaseLayerDelegate(BaseLayerDelegate baseLayerDelegate) {
        this.mBaseLayerDelegate = baseLayerDelegate;
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        super.setOnTouchListener(onTouchListener);
    }

    @Override // com.jingdong.common.XView2.container.IContainer
    public void setRootView(ViewGroup viewGroup) {
        this.mRootView = viewGroup;
    }

    public void setRule(String str, String str2, RelativeLayout.LayoutParams layoutParams) {
        if (layoutParams == null) {
            return;
        }
        if ("2".equals(str)) {
            layoutParams.addRule(14);
        } else if ("3".equals(str)) {
            layoutParams.addRule(11);
        }
        if ("2".equals(str2)) {
            layoutParams.addRule(15);
        } else if ("3".equals(str2)) {
            layoutParams.addRule(10);
        }
    }

    public void setScrollState() {
        int width;
        int width2;
        float f2;
        if (this.mScrollState == null || this.mLayersEntity == null) {
            return;
        }
        int left = this.mRootView.getLeft() + this.mRootView.getPaddingLeft();
        int right = this.mRootView.getRight() - this.mRootView.getPaddingRight();
        if (this.mScrollState.get()) {
            float x = getX();
            int i2 = this.mScreenMargin;
            if (x <= left + i2) {
                width2 = left + i2;
                f2 = width2;
            } else {
                right -= getWidth();
                width = this.mScreenMargin;
                f2 = right - width;
            }
        } else {
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u6eda\u52a8\u4e2d");
            if (getX() <= this.mScreenMargin + left) {
                width2 = left - (getWidth() >> 1);
                f2 = width2;
            } else {
                width = getWidth() >> 1;
                f2 = right - width;
            }
        }
        ValueAnimator valueAnimator = this.mAnimatorX;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.mAnimatorX.setFloatValues(getX(), f2);
        } else {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(getX(), f2);
            this.mAnimatorX = ofFloat;
            ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
            this.mAnimatorX.addListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.XView2.container.XView2BaseContainer.5
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                }
            });
            this.mAnimatorX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.XView2.container.XView2BaseContainer.6
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    XView2BaseContainer.this.setX(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                }
            });
        }
        AnimatorSet animatorSet = this.mAnimatorSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        } else {
            AnimatorSet animatorSet2 = new AnimatorSet();
            this.mAnimatorSet = animatorSet2;
            animatorSet2.play(this.mAnimatorX);
            this.mAnimatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        }
        this.mAnimatorSet.start();
    }

    @Override // com.jingdong.common.XView2.container.IContainer
    public void setZIndex(long j2) {
        XViewConfigEntity.LayoutEntity layoutEntity;
        XViewConfigEntity.LayersEntity layersEntity = this.mLayersEntity;
        if (layersEntity == null || (layoutEntity = layersEntity.layout) == null) {
            return;
        }
        layoutEntity.zIndex = j2;
    }

    @Override // android.view.View
    public ViewGroup getRootView() {
        return this.mRootView;
    }
}
