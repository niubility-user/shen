package com.jingdong.common.XView2.container;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.flexcube.iwidget.ui.IWidget;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.layer.BaseLayer;
import com.jingdong.common.XView2.layer.BaseLayerDelegate;
import com.jingdong.common.XView2.layer.flexcube.FlexCubeLayer;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes5.dex */
public class XView2Container extends XView2BaseContainer {
    private FrameLayout.LayoutParams btnParams;
    private boolean isClickable;
    private BaseLayer mBaseLayer;
    private View.OnClickListener mClickListener;
    private View mCurrentLayer;
    private IContainerControl mIContainerControlListener;
    private float mInitBottomLimitY;
    private float mInitLocationY;
    private float mInitTopLimitY;
    private float mInitX;
    private float mInitY;
    boolean mIsInRectArea;
    private Runnable packUpRunnable;
    private boolean startMove;

    public XView2Container(Context context) {
        super(context);
        this.mInitY = 0.0f;
        this.mInitX = 0.0f;
        this.mInitTopLimitY = 0.0f;
        this.mInitBottomLimitY = 0.0f;
        this.mInitLocationY = 0.0f;
        this.startMove = false;
        this.btnParams = null;
        this.isClickable = true;
        this.packUpRunnable = new Runnable() { // from class: com.jingdong.common.XView2.container.XView2Container.1
            {
                XView2Container.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                XView2Container.this.packUp();
            }
        };
        this.mIsInRectArea = false;
    }

    private void dealChild(View view, float f2, float f3) {
        if ((view instanceof ViewGroup) && !(view instanceof IWidget)) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                dealChild(viewGroup.getChildAt(i2), f2, f3);
            }
        } else if (view instanceof IWidget) {
            Rect rect = new Rect();
            view.getGlobalVisibleRect(rect);
            boolean isInRect = XView2Utils.isInRect(f2, f3, rect, null);
            this.mIsInRectArea = isInRect;
            if (isInRect) {
                throw new RuntimeException();
            }
        }
    }

    private void dealTouchArea(View view, float f2, float f3) {
        try {
            dealChild(view, f2, f3);
        } catch (RuntimeException unused) {
        }
        dispatchClickCallBack();
    }

    private void setTranslation(float f2) {
        float f3 = this.mInitLocationY;
        if (f3 < 0.0f || f3 > XView2Utils.H_HEIGHT || this.mInitTopLimitY > 0.0f) {
            return;
        }
        float f4 = this.mInitBottomLimitY;
        if (f4 < this.mInitLocationY || f4 < XView2Utils.getSizeBy750(100)) {
            return;
        }
        float f5 = this.mInitLocationY;
        float f6 = f2 - f5;
        if (f6 > 0.0f || f6 > this.mInitTopLimitY) {
            if (f6 <= 0.0f || f6 < this.mInitBottomLimitY - f5) {
                setTranslationY(f6);
            }
        }
    }

    public void changeLayerLayout(boolean z, JDJSONObject jDJSONObject) {
        FrameLayout.LayoutParams layoutParams;
        View view;
        if (jDJSONObject == null) {
            return;
        }
        int optLong = (int) jDJSONObject.optLong("width");
        int optLong2 = (int) jDJSONObject.optLong("height");
        int optLong3 = (int) jDJSONObject.optLong("top");
        int optLong4 = (int) jDJSONObject.optLong("left");
        ViewGroup.LayoutParams layoutParams2 = getLayoutParams();
        if (this.mBaseLayer instanceof FlexCubeLayer) {
            if (layoutParams2 instanceof FrameLayout.LayoutParams) {
                ((FrameLayout.LayoutParams) layoutParams2).gravity = 51;
            } else if (layoutParams2 instanceof RelativeLayout.LayoutParams) {
                removeRule(this.mLayersEntity, (RelativeLayout.LayoutParams) layoutParams2);
            }
        }
        View view2 = this.mCloseBtn;
        if (view2 != null && this.btnParams == null) {
            this.btnParams = (FrameLayout.LayoutParams) view2.getLayoutParams();
        }
        if (z) {
            if (layoutParams2 != null && (view = this.mRootContainer) != null) {
                this.mIsFullFill = true;
                layoutParams2.width = view.getWidth();
                layoutParams2.height = this.mRootContainer.getHeight();
                if (this.mBaseLayer instanceof FlexCubeLayer) {
                    if (layoutParams2 instanceof ViewGroup.MarginLayoutParams) {
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams2;
                        marginLayoutParams.leftMargin = 0;
                        marginLayoutParams.topMargin = 0;
                    }
                    setTranslationY(0.0f);
                    setTranslationX(0.0f);
                } else {
                    setY(0.0f);
                    setX(0.0f);
                }
                if (this.mCloseBtn != null) {
                    long height = this.mRootContainer.getHeight();
                    Double.isNaN(r10);
                    double min = Math.min(Math.max(r10 / 4.0d, XView2Utils.getSizeBy750(40)), XView2Utils.getSizeBy750(88));
                    FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(optLong, optLong);
                    int i2 = (int) min;
                    layoutParams3.width = i2;
                    layoutParams3.height = i2;
                    layoutParams3.gravity = 5;
                    Double.isNaN(r10);
                    layoutParams3.rightMargin = (int) (r10 * 0.1d);
                    double d = height;
                    Double.isNaN(d);
                    layoutParams3.topMargin = (int) (d * 0.1d);
                    this.mCloseBtn.setLayoutParams(layoutParams3);
                }
            }
        } else if (layoutParams2 != null && jDJSONObject != null) {
            this.mIsFullFill = false;
            if (optLong > 0 && optLong2 > 0) {
                layoutParams2.width = optLong;
                layoutParams2.height = optLong2;
            }
            if (this.mBaseLayer instanceof FlexCubeLayer) {
                if (layoutParams2 instanceof ViewGroup.MarginLayoutParams) {
                    if (optLong4 >= 0) {
                        ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin = optLong4;
                    }
                    if (optLong3 >= 0) {
                        ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin = optLong3;
                    }
                }
                this.mInitLocationY = optLong3;
            } else {
                if (optLong3 > 0) {
                    setY(optLong3);
                }
                if (optLong4 > 0) {
                    setX(optLong4);
                }
            }
            View view3 = this.mCloseBtn;
            if (view3 != null && (layoutParams = this.btnParams) != null) {
                view3.setLayoutParams(layoutParams);
            }
        }
        BaseLayer baseLayer = this.mBaseLayer;
        if ((baseLayer instanceof FlexCubeLayer) && layoutParams2 != null) {
            ((FlexCubeLayer) baseLayer).changeSize(layoutParams2.width, layoutParams2.height);
        }
        setLayoutParams(layoutParams2);
    }

    public void delayPackUp() {
        postDelayed(this.packUpRunnable, 3000L);
    }

    public void dispatchClickCallBack() {
        if (!this.mIsInRectArea) {
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u5728\u70b9\u51fb\u533a\u57df\u5916");
            if (this.mBaseLayerDelegate != null) {
                JDJSONObject jDJSONObject = new JDJSONObject();
                jDJSONObject.put("name", (Object) "xviewbox");
                BaseLayerDelegate baseLayerDelegate = this.mBaseLayerDelegate;
                XViewConfigEntity.LayersEntity layersEntity = this.mLayersEntity;
                baseLayerDelegate.onClickCallBack(layersEntity.layerId, layersEntity.logicKey, 4, jDJSONObject.toJSONString());
            }
        } else {
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u5728\u70b9\u51fb\u533a\u57df\u5185");
        }
        this.mIsInRectArea = false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x0016, code lost:
        if (r2 != 3) goto L180;
     */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r10) {
        /*
            Method dump skipped, instructions count: 423
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.XView2.container.XView2Container.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    public ViewGroup getContentContainer() {
        return this.mContentContainer;
    }

    public View getCurrentLayer() {
        return this.mCurrentLayer;
    }

    @Override // android.view.View
    public boolean isClickable() {
        return this.isClickable;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        XViewConfigEntity.LayersEntity layersEntity;
        if (this.startMove && (layersEntity = this.mLayersEntity) != null && layersEntity.renderType == 6) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        this.mInitBottomLimitY = (XView2Utils.H_HEIGHT - XView2Utils.getSizeBy750(100)) - getHeight();
        if (this.mHasLayoutEd.get()) {
            return;
        }
        this.mInitLocationY = getY();
        this.mInitTopLimitY = XView2Utils.getSizeBy750(144) - getY();
        this.mHasLayoutEd.set(true);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        XViewConfigEntity.LayersEntity layersEntity = this.mLayersEntity;
        if (layersEntity == null || !XView2Utils.isConvertBool(layersEntity.userInteractionEnabled)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    public void removeContainerControlListener() {
        this.mIContainerControlListener = null;
    }

    public void removeCurrentLayer() {
        View view;
        ContentFrameLayout contentFrameLayout = this.mContentContainer;
        if (contentFrameLayout == null || (view = this.mCurrentLayer) == null) {
            return;
        }
        contentFrameLayout.removeView(view);
        removeView(this.mContentContainer);
    }

    public void removeDelay() {
        removeCallbacks(this.packUpRunnable);
    }

    public void reset() {
        if ("3".equals(this.mLayersEntity.dragMode)) {
            setTranslationY(0.0f);
        } else if ("2".equals(this.mLayersEntity.dragMode)) {
            setTranslationX(0.0f);
            setTranslationY(0.0f);
        }
        this.mContentContainerLeft = 0;
        this.mContentContainerTop = 0;
        this.mContentContainerHeight = 0;
        this.mContentContainerWidth = 0;
        this.mIsFullFill = false;
        this.mRootContainer = null;
        this.mContainerParent = "";
    }

    public void resetAllViews() {
        removeAllViews();
        if (getParent() == null || !(getParent() instanceof ViewGroup)) {
            return;
        }
        ((ViewGroup) getParent()).removeView(this);
    }

    @Override // android.view.View
    public void setClickable(boolean z) {
        this.isClickable = z;
    }

    public void setContainerControlListener(IContainerControl iContainerControl) {
        this.mIContainerControlListener = iContainerControl;
    }

    public void setCurrentLayer(View view, BaseLayer baseLayer) {
        this.mCurrentLayer = view;
        this.mBaseLayer = baseLayer;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mClickListener = onClickListener;
    }

    public void setVisibleGone() {
        post(new Runnable() { // from class: com.jingdong.common.XView2.container.XView2Container.2
            {
                XView2Container.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                XView2Container.this.setVisibility(4);
            }
        });
    }
}
