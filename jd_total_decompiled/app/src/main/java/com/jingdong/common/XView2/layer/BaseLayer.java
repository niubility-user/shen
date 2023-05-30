package com.jingdong.common.XView2.layer;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.flexcube.layout.entity.FlexCubeModel;
import com.jd.lib.flexcube.layout.entity.FloorConfig;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.cleanmvp.ui.BaseFragment;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.R;
import com.jingdong.common.XView.XView;
import com.jingdong.common.XView2.XView2;
import com.jingdong.common.XView2.animation.AnimateListener;
import com.jingdong.common.XView2.animation.AnimateViewWrapper;
import com.jingdong.common.XView2.animation.XViewAnimationManager;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.common.XView2LayerObservableManager;
import com.jingdong.common.XView2.common.XView2OutlineProvider;
import com.jingdong.common.XView2.container.ContentFrameLayout;
import com.jingdong.common.XView2.container.IContainerControl;
import com.jingdong.common.XView2.container.XView2Container;
import com.jingdong.common.XView2.entity.ExtraInfoEntity;
import com.jingdong.common.XView2.entity.LocationEntity;
import com.jingdong.common.XView2.entity.PopSceneEntity;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.layer.flexcube.FlexCubeLayerHelper;
import com.jingdong.common.XView2.layer.timer.ICountdown;
import com.jingdong.common.XView2.layer.timer.ITimerCallBack;
import com.jingdong.common.XView2.layer.timer.MyCountDownTimer;
import com.jingdong.common.XView2.page.PageManager;
import com.jingdong.common.XView2.utils.BaseRunnable;
import com.jingdong.common.XView2.utils.LayerUtil;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.XView2.utils.XViewMtaUtil;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public abstract class BaseLayer<T> implements IBaseLayer {
    public XViewConfigEntity.LayersEntity layersEntity;
    public AnimateViewWrapper mAnimateViewWrapper;
    public BaseLayerDelegate mBaseLayerDelegate;
    public ILayerCallBack mCallBack;
    public Context mContext;
    public View mCurrentLayer;
    protected FlexCubeModel mFlexCubeModel;
    public View mFlexCubeView;
    protected FlexCubeLayerHelper mHelper;
    public LAYERESTATE mLayerState;
    public XViewConfigEntity.TargetsEntity mTargetsEntity;
    protected XView2 mXView;
    public XViewAnimationManager mXViewAnimationManager;
    public XView2Container mXViewContainer;
    public AtomicBoolean mHasResume = new AtomicBoolean(false);
    public Handler mHandler = new Handler(Looper.getMainLooper());
    private AtomicBoolean mIsSuccessRender = new AtomicBoolean(false);
    private int mCloseReason = 0;
    public AtomicBoolean mIsPopStatusMode = new AtomicBoolean(false);
    public AtomicBoolean mCouldPop = new AtomicBoolean(false);
    int finalLayoutWidth = 0;
    int finalLayoutHeight = 0;
    View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.jingdong.common.XView2.layer.BaseLayer.6
        {
            BaseLayer.this = this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            XViewConfigEntity.LayersEntity layersEntity;
            if (XView2Utils.isFastClick()) {
                return;
            }
            BaseLayer baseLayer = BaseLayer.this;
            if (baseLayer.mTargetsEntity == null || (layersEntity = baseLayer.layersEntity) == null) {
                return;
            }
            baseLayer.handleClickCallBack(layersEntity.androidJumpUrl, 2, layersEntity.name);
            if (!TextUtils.isEmpty(BaseLayer.this.layersEntity.androidJumpUrl)) {
                BaseLayer baseLayer2 = BaseLayer.this;
                XView2Utils.clickJump(baseLayer2.mContext, baseLayer2.layersEntity.androidJumpUrl);
                BaseLayer baseLayer3 = BaseLayer.this;
                XViewMtaUtil.jumpClickMta(baseLayer3.mContext, baseLayer3.layersEntity, baseLayer3.mTargetsEntity);
            }
            if (XView2Utils.isConvertBool(BaseLayer.this.layersEntity.jmpCls)) {
                BaseLayer baseLayer4 = BaseLayer.this;
                baseLayer4.closeLayer(baseLayer4.layersEntity.layerId, 1);
            }
        }
    };
    public MyCountDownTimer mCountDownTimer = new MyCountDownTimer(1000);

    /* loaded from: classes5.dex */
    public enum LAYERESTATE {
        RESUME,
        PAUSE,
        DESTROY,
        CLOSE,
        CREATE,
        READY,
        DISPLAYED,
        ERROR
    }

    public BaseLayer(Activity activity, T t, XView2 xView2) {
        XViewConfigEntity.LayersEntity layersEntity = (XViewConfigEntity.LayersEntity) t;
        this.layersEntity = layersEntity;
        this.mContext = activity;
        this.mXView = xView2;
        this.mBaseLayerDelegate = new BaseLayerDelegate(activity, layersEntity, xView2);
    }

    public void checkConfigChanged(int i2, int i3) {
        try {
            Context context = this.mContext;
            if (context instanceof BaseActivity) {
                onScreenChanged(XView2Utils.screenWidthChanged(i2, Math.max(i3, ((BaseActivity) context).getWindowManager().getDefaultDisplay().getHeight())), i2, i3);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:131:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0075  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void newFlexCubeLayout() {
        FlexCubeModel flexCubeModel;
        XViewConfigEntity.LayersEntity layersEntity;
        boolean z;
        XView2Container xView2Container;
        ContentFrameLayout contentFrameLayout;
        FlexCubeLayerHelper flexCubeLayerHelper = this.mHelper;
        if (flexCubeLayerHelper == null || (flexCubeModel = this.mFlexCubeModel) == null || (layersEntity = this.layersEntity) == null || this.mFlexCubeView == null) {
            return;
        }
        int dealSize = flexCubeLayerHelper.dealSize(flexCubeModel, layersEntity);
        FlexCubeModel flexCubeModel2 = this.mFlexCubeModel;
        if (dealSize <= 0) {
            dealSize = DPIUtil.getAppWidth((Activity) this.mContext);
        }
        flexCubeModel2.setFlexCubeWidth(dealSize);
        this.mHelper.refreshFlexCubeView(this.mFlexCubeModel, this.mFlexCubeView);
        XViewConfigEntity.LayoutEntity layoutEntity = this.layersEntity.renderData.layout;
        if (layoutEntity != null && "1".equals(layoutEntity.contentFillMode)) {
            FloorConfig floorConfig = this.mFlexCubeModel.floorConfig;
            if (floorConfig.w > 0 && floorConfig.f4266h > 0) {
                z = true;
                int flexCubeWidth = this.mFlexCubeModel.getFlexCubeWidth();
                int round = Math.round(r2.floorConfig.f4266h * this.mFlexCubeModel.getMultiple());
                if (!z) {
                    this.mFlexCubeView.setLayoutParams(new FrameLayout.LayoutParams(flexCubeWidth, round));
                } else {
                    this.mFlexCubeView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
                }
                xView2Container = this.mXViewContainer;
                if (xView2Container != null || (contentFrameLayout = xView2Container.mContentContainer) == null || contentFrameLayout.getLayoutParams() == null) {
                    return;
                }
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mXViewContainer.mContentContainer.getLayoutParams();
                if (!z) {
                    flexCubeWidth = -2;
                }
                layoutParams.width = flexCubeWidth;
                if (!z) {
                    round = -2;
                }
                layoutParams.height = round;
                layoutParams.gravity = dealAlign(this.layersEntity, layoutParams);
                this.mXViewContainer.mContentContainer.setLayoutParams(layoutParams);
                return;
            }
        }
        z = false;
        int flexCubeWidth2 = this.mFlexCubeModel.getFlexCubeWidth();
        int round2 = Math.round(r2.floorConfig.f4266h * this.mFlexCubeModel.getMultiple());
        if (!z) {
        }
        xView2Container = this.mXViewContainer;
        if (xView2Container != null) {
        }
    }

    private void onScreenChanged(boolean z, int i2, int i3) {
        XView2Container xView2Container;
        if (!z || (xView2Container = this.mXViewContainer) == null) {
            return;
        }
        XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
        FrameLayout.LayoutParams generateLayoutParams = xView2Container.generateLayoutParams(layersEntity.layout, layersEntity.fullScreen);
        if (generateLayoutParams == null) {
            return;
        }
        XView2Container xView2Container2 = this.mXViewContainer;
        if (xView2Container2.mRootContainer instanceof ViewGroup) {
            XViewConfigEntity.LayersEntity layersEntity2 = this.layersEntity;
            if (layersEntity2.renderType == 6 && layersEntity2.layout != null) {
                if (XView2Constants.FRAGMENT.equals(xView2Container2.mContainerParent)) {
                    String str = this.layersEntity.layout.align;
                    if (!"1".equals(str) && !"2".equals(str) && !"3".equals(str)) {
                        if ("right".equals(this.layersEntity.layout.leftOrRight)) {
                            generateLayoutParams.leftMargin = 0;
                            generateLayoutParams.rightMargin = XView2Utils.getSizeBy750((int) this.layersEntity.layout.right);
                            str = "3";
                        } else {
                            generateLayoutParams.leftMargin = XView2Utils.getSizeBy750((int) this.layersEntity.layout.left);
                            generateLayoutParams.rightMargin = 0;
                        }
                    } else {
                        generateLayoutParams.leftMargin = 0;
                        generateLayoutParams.rightMargin = 0;
                    }
                    String str2 = this.layersEntity.layout.verticalAlign;
                    if (!"1".equals(str2) && !"2".equals(str2) && !"3".equals(str2)) {
                        if ("bottom".equals(this.layersEntity.layout.topOrBottom)) {
                            generateLayoutParams.topMargin = 0;
                            XView2Container xView2Container3 = this.mXViewContainer;
                            XViewConfigEntity.LayoutEntity layoutEntity = this.layersEntity.layout;
                            generateLayoutParams.bottomMargin = xView2Container3.calculateBottomMargin(layoutEntity, layoutEntity.bottom);
                            str2 = "3";
                        } else {
                            XView2Container xView2Container4 = this.mXViewContainer;
                            XViewConfigEntity.LayoutEntity layoutEntity2 = this.layersEntity.layout;
                            generateLayoutParams.topMargin = xView2Container4.calculateTopMargin(layoutEntity2, layoutEntity2.top);
                            generateLayoutParams.bottomMargin = 0;
                        }
                    } else {
                        generateLayoutParams.topMargin = 0;
                        generateLayoutParams.bottomMargin = 0;
                    }
                    XView2Container xView2Container5 = this.mXViewContainer;
                    View view = xView2Container5.mRootContainer;
                    if (view instanceof RelativeLayout) {
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(generateLayoutParams.width, generateLayoutParams.height);
                        layoutParams.leftMargin = generateLayoutParams.leftMargin;
                        layoutParams.bottomMargin = generateLayoutParams.bottomMargin;
                        layoutParams.rightMargin = generateLayoutParams.rightMargin;
                        if (this.mXViewContainer.mRootContainer != null && "3".equals(str2)) {
                            int height = this.mXViewContainer.mRootContainer.getHeight() - generateLayoutParams.height;
                            XView2Container xView2Container6 = this.mXViewContainer;
                            XViewConfigEntity.LayoutEntity layoutEntity3 = this.layersEntity.layout;
                            layoutParams.topMargin = height - xView2Container6.calculateBottomMargin(layoutEntity3, layoutEntity3.bottom);
                        } else {
                            layoutParams.topMargin = generateLayoutParams.topMargin;
                        }
                        this.mXViewContainer.setRule(str, str2, layoutParams);
                        this.mXViewContainer.setLayoutParams(layoutParams);
                    } else if (view instanceof FrameLayout) {
                        generateLayoutParams.gravity = XView2Utils.getAlign(str, str2);
                        this.mXViewContainer.setLayoutParams(generateLayoutParams);
                    } else {
                        xView2Container5.setLayoutParams(generateLayoutParams);
                    }
                } else if ("activity".equals(this.mXViewContainer.mContainerParent)) {
                    this.mXViewContainer.setLayoutParams(this.mXViewContainer.addViewToActivityWithParams(generateLayoutParams));
                }
                newFlexCubeLayout();
                return;
            }
            xView2Container2.setLayoutParams(generateLayoutParams);
        }
    }

    private void setBackKeyListener() {
        XViewConfigEntity.LayersEntity layersEntity;
        XViewConfigEntity.LayoutEntity layoutEntity;
        if (!(this.mContext instanceof BaseActivity) || (layersEntity = this.layersEntity) == null) {
            return;
        }
        if ((XView2Utils.isConvertBool(layersEntity.fullScreen) || XView2Utils.isConvertBool(this.layersEntity.jmpCls) || ((layoutEntity = this.layersEntity.layout) != null && XView2Utils.isConvertBool(layoutEntity.fill))) && this.mXView != null) {
            setBackKeyTriggerListener();
        }
    }

    public void XViewScreenAdapter() {
        XViewConfigEntity.LayersEntity layersEntity;
        FlexCubeLayerHelper flexCubeLayerHelper;
        if (SwitchQueryFetcher.getSwitchBooleanValue("XViewScreenAdapter", true) && (layersEntity = this.layersEntity) != null && layersEntity.renderType == 6 && (flexCubeLayerHelper = this.mHelper) != null && flexCubeLayerHelper.mXViewLottieView == null && flexCubeLayerHelper.mXViewVideoView == null && flexCubeLayerHelper.xViewWebView == null) {
            Context context = this.mContext;
            if (context instanceof BaseActivity) {
                ((BaseActivity) context).setConfigurationChangedListener(new BaseActivity.IConfigurationChangedListener() { // from class: com.jingdong.common.XView2.layer.BaseLayer.9
                    {
                        BaseLayer.this = this;
                    }

                    @Override // com.jingdong.common.BaseActivity.IConfigurationChangedListener
                    public void onConfigurationChanged(Configuration configuration) {
                        BaseLayer.this.checkWidthChanged();
                    }
                });
            }
        }
    }

    public void buildTimeCountTimerText() {
        BaseLayerDelegate baseLayerDelegate = this.mBaseLayerDelegate;
        if (baseLayerDelegate != null) {
            baseLayerDelegate.buildTimeCountTimerText(this.mXViewContainer, new ITimerCallBack() { // from class: com.jingdong.common.XView2.layer.BaseLayer.10
                {
                    BaseLayer.this = this;
                }

                @Override // com.jingdong.common.XView2.layer.timer.ITimerCallBack
                public void closeTimer(int i2) {
                    BaseLayer baseLayer = BaseLayer.this;
                    XViewConfigEntity.LayersEntity layersEntity = baseLayer.layersEntity;
                    if (layersEntity != null) {
                        if (i2 == 1) {
                            baseLayer.closeLayer(layersEntity.layerId, 13);
                        }
                        if (i2 == 2) {
                            BaseLayer baseLayer2 = BaseLayer.this;
                            baseLayer2.closeLayer(baseLayer2.layersEntity.layerId, 12);
                        }
                    }
                }
            });
        }
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void changeLayoutCallBack(String str, String str2, int i2, String str3) {
        BaseLayerDelegate baseLayerDelegate = this.mBaseLayerDelegate;
        if (baseLayerDelegate != null) {
            baseLayerDelegate.changeLayoutCallBack(str, str2, i2, str3);
        }
    }

    public void checkWidthChanged() {
        XView2Container xView2Container = this.mXViewContainer;
        if (xView2Container == null || xView2Container.mRootContainer == null) {
            return;
        }
        XView2Utils.postOnUiThread(new BaseRunnable() { // from class: com.jingdong.common.XView2.layer.BaseLayer.1
            {
                BaseLayer.this = this;
            }

            @Override // com.jingdong.common.XView2.utils.BaseRunnable
            protected void safeRun() {
                BaseLayer baseLayer = BaseLayer.this;
                baseLayer.finalLayoutHeight = baseLayer.mXViewContainer.mRootContainer.getHeight();
                BaseLayer baseLayer2 = BaseLayer.this;
                baseLayer2.finalLayoutWidth = baseLayer2.mXViewContainer.mRootContainer.getWidth();
                BaseLayer baseLayer3 = BaseLayer.this;
                baseLayer3.checkConfigChanged(baseLayer3.finalLayoutWidth, baseLayer3.finalLayoutHeight);
            }
        }, 800L);
    }

    public void closeCurrentLayer() {
        this.mLayerState = LAYERESTATE.CLOSE;
        MyCountDownTimer myCountDownTimer = this.mCountDownTimer;
        if (myCountDownTimer != null) {
            myCountDownTimer.cancel();
        }
        this.mIsPopStatusMode.set(false);
        XView2Container xView2Container = this.mXViewContainer;
        if (xView2Container != null) {
            xView2Container.setVisibleGone();
            this.mXViewContainer.removeContainerControlListener();
            if (!XView2Utils.canWebViewLayerPreLoaded(this.layersEntity)) {
                this.mXViewContainer.reset();
                this.mXViewContainer.removeCurrentLayer();
                this.mXViewContainer.resetAllViews();
                XView2LayerObservableManager manager = XView2LayerObservableManager.getManager();
                XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
                manager.notifyRelease(layersEntity.layerId, layersEntity.logicKey);
                this.mXViewContainer = null;
            } else {
                this.mIsSuccessRender.set(false);
            }
        }
        releaseLayer();
        resetLayerEntity();
        removeTriggerListener();
        XView2Constants.mCloseType = null;
    }

    public void closeCurrentXView1Instance() {
        View view = this.mCurrentLayer;
        if (view instanceof XView) {
            ((XView) view).closeXView();
        }
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void closeLayer(String str, int i2) {
        XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
        if (layersEntity == null) {
            return;
        }
        this.mCloseReason = i2;
        if ((!XView2Utils.isConvertBool(layersEntity.fullScreen) && !XView2Utils.isConvertBool(this.layersEntity.jmpCls) && !XView2Utils.isConvertBool(this.layersEntity.layout.fill)) || (!XView2Constants.CLOSE_TYPE.BACK_KEY.equals(XView2Constants.mCloseType) && !XView2Constants.CLOSE_TYPE.JUMP_CLOSE.equals(XView2Constants.mCloseType))) {
            if ("1".equals(this.layersEntity.useCloseAnimation)) {
                doUserCloseAnimation();
                return;
            } else if (!"2".equals(this.layersEntity.useCloseAnimation) && !"3".equals(this.layersEntity.useCloseAnimation)) {
                setLayerClose(str, this.layersEntity.logicKey, i2);
                closeCurrentLayer();
                return;
            } else {
                closeXView2Anim();
                return;
            }
        }
        setLayerClose(str, this.layersEntity.logicKey, i2);
        closeCurrentLayer();
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void closeXView2Anim() {
        XViewConfigEntity.LayersEntity layersEntity;
        if (this.mBaseLayerDelegate == null || (layersEntity = this.layersEntity) == null) {
            return;
        }
        ViewGroup animateTargetView = LayerUtil.getAnimateTargetView(layersEntity, this.mXViewContainer);
        if (animateTargetView == null) {
            closeCurrentLayer();
        } else {
            this.mBaseLayerDelegate.closeXView2Anim(animateTargetView, new AnimateListener() { // from class: com.jingdong.common.XView2.layer.BaseLayer.3
                {
                    BaseLayer.this = this;
                }

                @Override // com.jingdong.common.XView2.animation.AnimateListener
                public void onAnimateEnd() {
                    BaseLayer.this.endCloseAni();
                    BaseLayer baseLayer = BaseLayer.this;
                    XViewConfigEntity.LayersEntity layersEntity2 = baseLayer.layersEntity;
                    baseLayer.setLayerClose(layersEntity2.layerId, layersEntity2.logicKey, baseLayer.mCloseReason);
                    BaseLayer.this.closeCurrentLayer();
                }

                @Override // com.jingdong.common.XView2.animation.AnimateListener
                public void onAnimateError() {
                    BaseLayer baseLayer = BaseLayer.this;
                    XViewConfigEntity.LayersEntity layersEntity2 = baseLayer.layersEntity;
                    baseLayer.setLayerClose(layersEntity2.layerId, layersEntity2.logicKey, baseLayer.mCloseReason);
                    BaseLayer.this.closeCurrentLayer();
                }

                @Override // com.jingdong.common.XView2.animation.AnimateListener
                public void onAnimateStart(long j2) {
                    BaseLayer baseLayer = BaseLayer.this;
                    baseLayer.startCloseAni(baseLayer.layersEntity.useCloseAnimation, j2);
                }
            });
        }
    }

    public void configContainer() {
        XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
        if (layersEntity == null || this.mXViewContainer == null) {
            return;
        }
        String containerBgColor = XView2Utils.getContainerBgColor(layersEntity.backgroundColor);
        if (!TextUtils.isEmpty(containerBgColor)) {
            try {
                this.mXViewContainer.setBackgroundColor(Color.parseColor(containerBgColor));
            } catch (IllegalArgumentException unused) {
                this.mXViewContainer.setBackgroundColor(0);
            }
        } else {
            this.mXViewContainer.setBackgroundColor(0);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            this.mXViewContainer.setOutlineProvider(new XView2OutlineProvider(XView2Utils.getSizeBy750((int) this.layersEntity.layout.radius)));
            this.mXViewContainer.setClipToOutline(true);
            this.mXViewContainer.requestLayout();
        }
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void configControlBtn() {
        int i2;
        int i3;
        XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
        if (layersEntity == null || XView2Utils.isListEmpty(layersEntity.controls)) {
            return;
        }
        Iterator<XViewConfigEntity.ControlEntity> it = this.layersEntity.controls.iterator();
        while (it.hasNext()) {
            final XViewConfigEntity.ControlEntity next = it.next();
            if (XView2Utils.isConvertBool(next.isShow)) {
                final SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this.mContext);
                simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
                simpleDraweeView.setClickable(true);
                XView2Container xView2Container = this.mXViewContainer;
                int i4 = 0;
                if (xView2Container != null) {
                    int i5 = xView2Container.mContentContainerLeft;
                    int i6 = xView2Container.mContentContainerTop;
                    int i7 = xView2Container.mContentContainerHeight;
                    i3 = xView2Container.mContentContainerWidth;
                    i2 = i6;
                    i4 = i7;
                } else {
                    i2 = 0;
                    i3 = 0;
                }
                FrameLayout.LayoutParams layoutParams = null;
                if (next.width > 0 && next.height > 0) {
                    layoutParams = new FrameLayout.LayoutParams(XView2Utils.getSizeBy750((int) next.width), XView2Utils.getSizeBy750((int) next.height));
                    if (XView2Utils.isConvertBool(next.type)) {
                        XViewConfigEntity.LayoutEntity layoutEntity = this.layersEntity.layout;
                        if (layoutEntity != null && XView2Utils.isConvertBool(layoutEntity.contentForceCenter)) {
                            layoutParams.topMargin = (XView2Utils.H_HEIGHT / 2) + (i4 / 2) + XView2Utils.getSizeBy750(64);
                            layoutParams.gravity = 1;
                        } else {
                            layoutParams.leftMargin = XView2Utils.getSizeBy750((int) next.left);
                            layoutParams.topMargin = XView2Utils.getSizeBy750((int) next.top);
                        }
                    } else {
                        XViewConfigEntity.LayoutEntity layoutEntity2 = this.layersEntity.layout;
                        if (layoutEntity2 != null && XView2Utils.isConvertBool(layoutEntity2.contentForceCenter)) {
                            layoutParams.topMargin = XView2Utils.getSizeBy750((int) next.top) - i2;
                            layoutParams.leftMargin = XView2Utils.getSizeBy750((int) next.left) - ((XView2Utils.H_WIDTH - i3) / 2);
                        } else {
                            layoutParams.leftMargin = XView2Utils.getSizeBy750((int) next.left);
                            layoutParams.topMargin = XView2Utils.getSizeBy750((int) next.top);
                        }
                    }
                } else if (XView2Utils.isConvertBool(next.type)) {
                    long sizeBy750 = XView2Utils.getSizeBy750((int) this.layersEntity.layout.width);
                    long sizeBy7502 = XView2Utils.getSizeBy750((int) this.layersEntity.layout.width);
                    double d = sizeBy750;
                    Double.isNaN(d);
                    int min = (int) Math.min(Math.max(d / 4.0d, XView2Utils.getSizeBy750(40)), XView2Utils.getSizeBy750(88));
                    FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(min, min);
                    XViewConfigEntity.LayoutEntity layoutEntity3 = this.layersEntity.layout;
                    if (layoutEntity3 != null && XView2Utils.isConvertBool(layoutEntity3.contentForceCenter)) {
                        layoutParams2.topMargin = (XView2Utils.H_HEIGHT / 2) + (i4 / 2) + XView2Utils.getSizeBy750(64);
                        layoutParams2.gravity = 1;
                    } else {
                        layoutParams2.gravity = 5;
                        Double.isNaN(d);
                        layoutParams2.rightMargin = (int) (d * 0.1d);
                        double d2 = sizeBy7502;
                        Double.isNaN(d2);
                        layoutParams2.topMargin = (int) (d2 * 0.1d);
                    }
                    layoutParams = layoutParams2;
                }
                simpleDraweeView.setLayoutParams(layoutParams);
                if (XView2Utils.isConvertBool(this.layersEntity.userInteractionEnabled)) {
                    simpleDraweeView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.XView2.layer.BaseLayer.12
                        {
                            BaseLayer.this = this;
                        }

                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            if (BaseLayer.this.mCallBack == null || XView2Utils.isFastClick()) {
                                return;
                            }
                            if (XView2Utils.isConvertBool(next.type)) {
                                BaseLayer baseLayer = BaseLayer.this;
                                if (baseLayer.mBaseLayerDelegate != null && baseLayer.mTargetsEntity != null) {
                                    JDJSONObject jDJSONObject = new JDJSONObject();
                                    int i8 = BaseLayer.this.mTargetsEntity.targetType;
                                    if (i8 == 1) {
                                        jDJSONObject.put("name", (Object) next.name);
                                    } else if (i8 == 2) {
                                        jDJSONObject.put("name", (Object) Uri.encode(next.name));
                                    }
                                    BaseLayer baseLayer2 = BaseLayer.this;
                                    BaseLayerDelegate baseLayerDelegate = baseLayer2.mBaseLayerDelegate;
                                    XViewConfigEntity.LayersEntity layersEntity2 = baseLayer2.layersEntity;
                                    baseLayerDelegate.onClickCallBack(layersEntity2.layerId, layersEntity2.logicKey, 1, jDJSONObject.toJSONString());
                                }
                                BaseLayer baseLayer3 = BaseLayer.this;
                                baseLayer3.mCallBack.onLayerClosed(baseLayer3.layersEntity.layerId, 2);
                            } else {
                                if (!TextUtils.isEmpty(next.androidJumpUrl)) {
                                    XView2Utils.clickJump(BaseLayer.this.mContext, next.androidJumpUrl);
                                }
                                BaseLayer baseLayer4 = BaseLayer.this;
                                if (baseLayer4.mBaseLayerDelegate != null && baseLayer4.mTargetsEntity != null) {
                                    JDJSONObject jDJSONObject2 = new JDJSONObject();
                                    int i9 = BaseLayer.this.mTargetsEntity.targetType;
                                    if (i9 == 1) {
                                        jDJSONObject2.put("name", (Object) next.name);
                                        if (!TextUtils.isEmpty(next.androidJumpUrl)) {
                                            jDJSONObject2.put("url", (Object) next.androidJumpUrl);
                                        }
                                    } else if (i9 == 2) {
                                        jDJSONObject2.put("name", (Object) Uri.encode(next.name));
                                        if (!TextUtils.isEmpty(next.androidJumpUrl)) {
                                            jDJSONObject2.put("url", (Object) Uri.encode(next.androidJumpUrl));
                                        }
                                    }
                                    BaseLayer baseLayer5 = BaseLayer.this;
                                    BaseLayerDelegate baseLayerDelegate2 = baseLayer5.mBaseLayerDelegate;
                                    XViewConfigEntity.LayersEntity layersEntity3 = baseLayer5.layersEntity;
                                    baseLayerDelegate2.onClickCallBack(layersEntity3.layerId, layersEntity3.logicKey, 3, jDJSONObject2.toJSONString());
                                }
                                if (!TextUtils.isEmpty(next.androidJumpUrl) && (XView2Utils.isConvertBool(next.closeAfterClk) || XView2Utils.isConvertBool(BaseLayer.this.layersEntity.jmpCls))) {
                                    BaseLayer baseLayer6 = BaseLayer.this;
                                    baseLayer6.mCallBack.onLayerClosed(baseLayer6.layersEntity.layerId, 1);
                                    XView2Constants.mCloseType = XView2Constants.CLOSE_TYPE.JUMP_CLOSE;
                                }
                                if (XView2Utils.isConvertBool(next.closeAfterClk)) {
                                    BaseLayer baseLayer7 = BaseLayer.this;
                                    baseLayer7.mCallBack.onLayerClosed(baseLayer7.layersEntity.layerId, 3);
                                }
                            }
                            BaseLayer baseLayer8 = BaseLayer.this;
                            XViewMtaUtil.controlClickMta(baseLayer8.mContext, baseLayer8.layersEntity, baseLayer8.mTargetsEntity, next);
                        }
                    });
                }
                if (TextUtils.isEmpty(next.img)) {
                    controlError(next, simpleDraweeView);
                } else {
                    XView2Utils.setOptionReferer(XView2Utils.imageNullOptions, this.layersEntity.layerId);
                    JDImageUtils.displayImage(next.img, simpleDraweeView, XView2Utils.imageNullOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.XView2.layer.BaseLayer.13
                        {
                            BaseLayer.this = this;
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingCancelled(String str, View view) {
                            BaseLayer.this.controlError(next, simpleDraweeView);
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                            BaseLayer.this.controlError(next, simpleDraweeView);
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingStarted(String str, View view) {
                        }
                    });
                }
                XView2Container xView2Container2 = this.mXViewContainer;
                if (xView2Container2 != null) {
                    xView2Container2.addContainerView(simpleDraweeView, next);
                }
            }
        }
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void configCurrentLayer(XViewConfigEntity.TargetsEntity targetsEntity, View view) {
        setTargetInfo(targetsEntity);
        setCurrentLayer(view);
        setBaseLayerDelegateTargetsEntity(targetsEntity);
    }

    public void controlError(XViewConfigEntity.ControlEntity controlEntity, SimpleDraweeView simpleDraweeView) {
        if (simpleDraweeView == null || controlEntity == null || !"1".equals(controlEntity.type)) {
            return;
        }
        simpleDraweeView.setImageResource(R.drawable.xview_close);
    }

    public abstract View createLayer(XView2Container xView2Container, T t, ILayerCallBack iLayerCallBack);

    public int dealAlign(XViewConfigEntity.LayersEntity layersEntity, FrameLayout.LayoutParams layoutParams) {
        XViewConfigEntity.RenderDataEntity renderDataEntity;
        XViewConfigEntity.LayoutEntity layoutEntity;
        if (layersEntity == null || (renderDataEntity = layersEntity.renderData) == null || (layoutEntity = renderDataEntity.layout) == null) {
            return 3;
        }
        String str = layoutEntity.contentAlign;
        String str2 = layoutEntity.contentVerticalAlign;
        if (layoutParams != null) {
            if (!"1".equals(str) && !"2".equals(str) && !"3".equals(str) && !XView2Utils.isConvertBool(layersEntity.renderData.layout.contentFillMode)) {
                if ("right".equals(layersEntity.renderData.layout.leftOrRight)) {
                    layoutParams.leftMargin = 0;
                    layoutParams.rightMargin = XView2Utils.getSizeBy750((int) layersEntity.renderData.layout.right);
                    str = "3";
                } else {
                    layoutParams.leftMargin = XView2Utils.getSizeBy750((int) layersEntity.renderData.layout.left);
                    layoutParams.rightMargin = 0;
                }
            } else {
                layoutParams.leftMargin = 0;
                layoutParams.rightMargin = 0;
            }
            if (!"1".equals(str2) && !"2".equals(str2) && !"3".equals(str2) && !XView2Utils.isConvertBool(layersEntity.renderData.layout.contentFillMode)) {
                if ("bottom".equals(layersEntity.renderData.layout.topOrBottom)) {
                    layoutParams.topMargin = 0;
                    layoutParams.bottomMargin = XView2Utils.getSizeBy750((int) layersEntity.renderData.layout.bottom);
                    str2 = "3";
                } else {
                    layoutParams.topMargin = XView2Utils.getSizeBy750((int) layersEntity.renderData.layout.top);
                    layoutParams.bottomMargin = 0;
                }
            } else {
                layoutParams.topMargin = 0;
                layoutParams.bottomMargin = 0;
            }
        }
        return XView2Utils.getAlign(str, str2);
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void destroyLayer() {
        this.mLayerState = LAYERESTATE.DESTROY;
        releaseContainer();
        releaseLayer();
        resetLayerEntity();
        removeBackKeyTriggerListener();
        removeScreenAdapter();
        this.mCallBack = null;
        this.mBaseLayerDelegate = null;
    }

    public void doUserCloseAnimation() {
        XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
        if (layersEntity == null || TextUtils.isEmpty(layersEntity.layerId) || !XView2Utils.isConvertBool(this.layersEntity.useCloseAnimation)) {
            return;
        }
        XView2LayerObservableManager manager = XView2LayerObservableManager.getManager();
        XViewConfigEntity.LayersEntity layersEntity2 = this.layersEntity;
        LocationEntity notifyLocationObserverByType = manager.notifyLocationObserverByType(layersEntity2.layerId, layersEntity2.logicKey, 2);
        if (notifyLocationObserverByType != null && notifyLocationObserverByType.rectF != null) {
            XView2Container xView2Container = this.mXViewContainer;
            if (xView2Container == null || xView2Container.getContentContainer() == null) {
                return;
            }
            ViewGroup animateTargetView = LayerUtil.getAnimateTargetView(this.layersEntity, this.mXViewContainer);
            if (animateTargetView == null) {
                closeCurrentLayer();
                return;
            }
            if (this.mAnimateViewWrapper == null) {
                this.mAnimateViewWrapper = new AnimateViewWrapper(animateTargetView, notifyLocationObserverByType.rectF);
            }
            this.mAnimateViewWrapper.startAnimation((Activity) this.mContext, new AnimateListener() { // from class: com.jingdong.common.XView2.layer.BaseLayer.8
                {
                    BaseLayer.this = this;
                }

                @Override // com.jingdong.common.XView2.animation.AnimateListener
                public void onAnimateEnd() {
                    XView2LayerObservableManager manager2 = XView2LayerObservableManager.getManager();
                    XViewConfigEntity.LayersEntity layersEntity3 = BaseLayer.this.layersEntity;
                    manager2.notifyAnimateEnd(layersEntity3.layerId, layersEntity3.logicKey);
                    BaseLayer.this.endCloseAni();
                    BaseLayer baseLayer = BaseLayer.this;
                    XViewConfigEntity.LayersEntity layersEntity4 = baseLayer.layersEntity;
                    baseLayer.setLayerClose(layersEntity4.layerId, layersEntity4.logicKey, baseLayer.mCloseReason);
                    BaseLayer.this.closeCurrentLayer();
                }

                @Override // com.jingdong.common.XView2.animation.AnimateListener
                public void onAnimateError() {
                    BaseLayer baseLayer = BaseLayer.this;
                    XViewConfigEntity.LayersEntity layersEntity3 = baseLayer.layersEntity;
                    baseLayer.setLayerClose(layersEntity3.layerId, layersEntity3.logicKey, baseLayer.mCloseReason);
                    BaseLayer.this.closeCurrentLayer();
                }

                @Override // com.jingdong.common.XView2.animation.AnimateListener
                public void onAnimateStart(long j2) {
                    BaseLayer baseLayer = BaseLayer.this;
                    baseLayer.startCloseAni(baseLayer.layersEntity.useCloseAnimation, j2);
                }
            });
            return;
        }
        closeCurrentLayer();
    }

    public void endCloseAni() {
        XViewConfigEntity.LayersEntity layersEntity;
        BaseLayerDelegate baseLayerDelegate = this.mBaseLayerDelegate;
        if (baseLayerDelegate == null || (layersEntity = this.layersEntity) == null) {
            return;
        }
        baseLayerDelegate.endCloseAniCallBack(layersEntity.layerId, layersEntity.logicKey);
    }

    public void endPopAni() {
        XViewConfigEntity.LayersEntity layersEntity;
        BaseLayerDelegate baseLayerDelegate = this.mBaseLayerDelegate;
        if (baseLayerDelegate == null || (layersEntity = this.layersEntity) == null) {
            return;
        }
        baseLayerDelegate.endPopAniCallBack(layersEntity.layerId, layersEntity.logicKey);
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public BaseLayerDelegate getBaseLayerDelegate() {
        return this.mBaseLayerDelegate;
    }

    public int getDayTimes() {
        XViewConfigEntity.RuleEntity ruleEntity;
        XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
        if (layersEntity == null || (ruleEntity = layersEntity.rule) == null) {
            return 0;
        }
        return ruleEntity.dayTimes;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public String getLayerId() {
        XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
        return layersEntity != null ? layersEntity.layerId : "";
    }

    public XViewConfigEntity.LayersEntity getLayersEntity() {
        return this.layersEntity;
    }

    public String getLogicKey() {
        XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
        return layersEntity != null ? layersEntity.logicKey : "";
    }

    public int getTotalTimes() {
        XViewConfigEntity.RuleEntity ruleEntity;
        XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
        if (layersEntity == null || (ruleEntity = layersEntity.rule) == null) {
            return 0;
        }
        return ruleEntity.times;
    }

    public void handleClickCallBack(String str, int i2, String str2) {
        XViewLogPrint.JDXLog.e(XView2Constants.TAG, "mBaseLayerDelegate" + this.mBaseLayerDelegate);
        JDJSONObject jDJSONObject = new JDJSONObject();
        XViewConfigEntity.TargetsEntity targetsEntity = this.mTargetsEntity;
        if (targetsEntity != null && this.layersEntity != null) {
            int i3 = targetsEntity.targetType;
            if (i3 == 1) {
                if (!TextUtils.isEmpty(str)) {
                    jDJSONObject.put("url", (Object) str);
                }
                if (!TextUtils.isEmpty(str2)) {
                    jDJSONObject.put("name", (Object) str2);
                }
            } else if (i3 == 2) {
                if (!TextUtils.isEmpty(str)) {
                    jDJSONObject.put("url", (Object) Uri.encode(str));
                }
                if (!TextUtils.isEmpty(str2)) {
                    jDJSONObject.put("name", (Object) Uri.encode(str2));
                }
            }
        }
        BaseLayerDelegate baseLayerDelegate = this.mBaseLayerDelegate;
        if (baseLayerDelegate != null) {
            XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
            baseLayerDelegate.onClickCallBack(layersEntity.layerId, layersEntity.logicKey, i2, jDJSONObject.toJSONString());
        }
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void initParamsBeforeCreateLayer(JSONObject jSONObject) {
        BaseLayerDelegate baseLayerDelegate = this.mBaseLayerDelegate;
        if (baseLayerDelegate != null) {
            baseLayerDelegate.initParamsBeforeCreateLayer(jSONObject);
        }
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public XView2Container initXViewContainer() {
        if (this.mContext == null || this.mXView == null) {
            return null;
        }
        if (this.mXViewContainer == null) {
            this.mXViewContainer = new XView2Container(this.mContext);
        }
        View decorView = ((Activity) this.mContext).getWindow().getDecorView();
        if (decorView == null) {
            return null;
        }
        int width = decorView.getWidth();
        int height = decorView.getHeight();
        if (width == 0) {
            width = com.jingdong.jdsdk.utils.DPIUtil.getWidth();
        }
        if (height == 0) {
            height = com.jingdong.jdsdk.utils.DPIUtil.getHeight();
        }
        XView2Utils.screenWidthChanged(width, height);
        this.mXViewContainer.setBaseLayerDelegate(this.mBaseLayerDelegate);
        this.mXViewContainer.initXView2Container(this.mXView, this.layersEntity);
        this.mXViewContainer.setRootView((ViewGroup) ((Activity) this.mContext).getWindow().getDecorView().findViewById(16908290));
        return this.mXViewContainer;
    }

    public boolean isCanLayerRealPop() {
        return !XView2Utils.canWebViewLayerPreLoaded(this.layersEntity) || this.mIsPopStatusMode.get();
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public boolean isFullScreen() {
        XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
        return layersEntity != null && "1".equals(layersEntity.fullScreen);
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public boolean isJumpClose() {
        XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
        return layersEntity != null && "1".equals(layersEntity.jmpCls);
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public boolean isLayerEnterImmediatelyPop() {
        XViewConfigEntity.RuleEntity ruleEntity;
        PopSceneEntity popSceneEntity;
        XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
        return (layersEntity == null || (ruleEntity = layersEntity.rule) == null || (popSceneEntity = ruleEntity.popScene) == null || !"1".equals(popSceneEntity.onEnterTargetImmediately)) ? false : true;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public boolean isLayerEnterPop() {
        XViewConfigEntity.RuleEntity ruleEntity;
        PopSceneEntity popSceneEntity;
        XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
        return (layersEntity == null || (ruleEntity = layersEntity.rule) == null || (popSceneEntity = ruleEntity.popScene) == null || TextUtils.isEmpty(popSceneEntity.onEnterTarget) || !"1".equals(this.layersEntity.rule.popScene.onEnterTarget)) ? false : true;
    }

    public boolean isPopUpAnimType() {
        XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
        return layersEntity != null && ("1".equals(layersEntity.popUpAnimTy) || "2".equals(this.layersEntity.popUpAnimTy) || "3".equals(this.layersEntity.popUpAnimTy));
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public boolean isRenderSuccess() {
        return this.mIsSuccessRender.get();
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void onLayerDisplayed(String str, String str2) {
        if (this.mIsSuccessRender.get()) {
            return;
        }
        this.mIsSuccessRender.set(true);
        XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
        if (layersEntity != null) {
            layersEntity.renderEndTime = System.currentTimeMillis();
        }
        setLayerShowSuccess(str, str2);
        startXView2popUpAnim();
        configContainer();
        configControlBtn();
        updateTimeRule(str);
        setBackKeyListener();
        startTimeCountTimer();
        buildTimeCountTimerText();
        setFlexCubeViewAnim();
        XViewMtaUtil.sendLayerExpMta(this.mContext, this.layersEntity, this.mTargetsEntity);
        XViewMtaUtil.sendLayerPerformanceMta(this.mContext, this.layersEntity, this.mTargetsEntity);
        XViewScreenAdapter();
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void onLoadingLayer(String str, String str2) {
        startLoadingLayer(str, str2);
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void onPause() {
        this.mLayerState = LAYERESTATE.PAUSE;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void onResume() {
        this.mLayerState = LAYERESTATE.RESUME;
    }

    public void releaseContainer() {
        if (this.mXViewContainer != null) {
            if (XView2Utils.canWebViewLayerPreLoaded(this.layersEntity)) {
                this.mXViewContainer.reset();
                this.mXViewContainer.removeCurrentLayer();
                closeCurrentXView1Instance();
                XView2LayerObservableManager manager = XView2LayerObservableManager.getManager();
                XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
                manager.notifyRelease(layersEntity.layerId, layersEntity.logicKey);
            }
            this.mXViewContainer.resetAllViews();
            this.mXViewContainer = null;
            this.mCurrentLayer = null;
        }
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void releaseLayer() {
        BaseLayerDelegate baseLayerDelegate = this.mBaseLayerDelegate;
        if (baseLayerDelegate != null) {
            baseLayerDelegate.setAnimationRef(this.mAnimateViewWrapper, this.mXViewAnimationManager);
            this.mBaseLayerDelegate.releaseBaseLayer();
            this.mBaseLayerDelegate = null;
        }
    }

    public void removeBackKeyTriggerListener() {
        XView2 xView2 = this.mXView;
        if (xView2 == null) {
            return;
        }
        if (xView2.mIsTabModel.get()) {
            Fragment currentFragment = this.mXView.getCurrentFragment();
            if (currentFragment instanceof BaseFragment) {
                ((BaseFragment) currentFragment).removeBackKeyTriggerListener();
                return;
            }
            return;
        }
        Context context = this.mContext;
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).removeBackKeyTriggerListener();
        }
    }

    public void removeScreenAdapter() {
        Context context = this.mContext;
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).removeConfigurationChangedListener();
        }
    }

    public void removeTriggerListener() {
        XViewConfigEntity.LayersEntity layersEntity;
        if (!(this.mContext instanceof BaseActivity) || (layersEntity = this.layersEntity) == null) {
            return;
        }
        if ((XView2Utils.isConvertBool(layersEntity.fullScreen) || XView2Utils.isConvertBool(this.layersEntity.jmpCls) || XView2Utils.isConvertBool(this.layersEntity.layout.fill)) && this.mXView != null) {
            removeBackKeyTriggerListener();
        }
    }

    public void resetLayerEntity() {
        XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
        if (layersEntity != null) {
            layersEntity.mHasReloadCount = 0;
            layersEntity.mReLoadLayerCallBack = null;
        }
    }

    public void setBackKeyTriggerListener() {
        XView2 xView2 = this.mXView;
        if (xView2 == null) {
            return;
        }
        if (xView2.mIsTabModel.get()) {
            Fragment currentFragment = this.mXView.getCurrentFragment();
            if ((currentFragment instanceof BaseFragment) && XView2Utils.isHitTargetCheck(this.mXView)) {
                ((BaseFragment) currentFragment).setBackKeyTriggerListener(new BaseFragment.IBackKeyTriggerListener() { // from class: com.jingdong.common.XView2.layer.BaseLayer.4
                    {
                        BaseLayer.this = this;
                    }

                    @Override // com.jingdong.cleanmvp.ui.BaseFragment.IBackKeyTriggerListener
                    public void backKeyTrigger() {
                        XView2Constants.mCloseType = XView2Constants.CLOSE_TYPE.BACK_KEY;
                        XViewLogPrint.JDXLog.e(XView2Constants.TAG, "backKey ");
                        BaseLayer baseLayer = BaseLayer.this;
                        baseLayer.closeLayer(baseLayer.layersEntity.layerId, 8);
                    }
                });
                return;
            }
            return;
        }
        Context context = this.mContext;
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).setBackKeyTriggerListener(new BaseActivity.IBackKeyTriggerListener() { // from class: com.jingdong.common.XView2.layer.BaseLayer.5
                {
                    BaseLayer.this = this;
                }

                @Override // com.jingdong.common.BaseActivity.IBackKeyTriggerListener
                public void backKeyTrigger() {
                    XView2Constants.mCloseType = XView2Constants.CLOSE_TYPE.BACK_KEY;
                    BaseLayer baseLayer = BaseLayer.this;
                    baseLayer.closeLayer(baseLayer.layersEntity.layerId, 8);
                }
            });
        }
    }

    public void setBaseLayerDelegateTargetsEntity(XViewConfigEntity.TargetsEntity targetsEntity) {
        BaseLayerDelegate baseLayerDelegate = this.mBaseLayerDelegate;
        if (baseLayerDelegate != null) {
            baseLayerDelegate.setTargetsEntity(targetsEntity);
        }
    }

    public void setCouldPop(boolean z) {
        this.mCouldPop.set(z);
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void setCurrentLayer(View view) {
        XViewConfigEntity.LayersEntity layersEntity;
        XView2Container xView2Container = this.mXViewContainer;
        if (xView2Container != null) {
            this.mCurrentLayer = view;
            xView2Container.setCurrentLayer(view, this);
            if (view != null && (layersEntity = this.layersEntity) != null && XView2Utils.isConvertBool(layersEntity.userInteractionEnabled)) {
                view.setClickable(true);
                this.mXViewContainer.setOnClickListener(this.mOnClickListener);
            }
            this.mXViewContainer.setContainerControlListener(new IContainerControl() { // from class: com.jingdong.common.XView2.layer.BaseLayer.7
                {
                    BaseLayer.this = this;
                }

                @Override // com.jingdong.common.XView2.container.IContainerControl
                public void closeCurrentLayer() {
                    BaseLayer baseLayer = BaseLayer.this;
                    XViewConfigEntity.LayersEntity layersEntity2 = baseLayer.layersEntity;
                    if (layersEntity2 != null) {
                        baseLayer.closeLayer(layersEntity2.layerId, 11);
                        if (BaseLayer.this.layersEntity.renderType == 6) {
                            JDJSONObject jDJSONObject = new JDJSONObject();
                            jDJSONObject.put("name", (Object) "\u80cc\u666f\u70b9\u51fb");
                            jDJSONObject.put("type", (Object) "blank");
                            BaseLayer baseLayer2 = BaseLayer.this;
                            XViewMtaUtil.controlClickMta(baseLayer2.mContext, baseLayer2.layersEntity, baseLayer2.mTargetsEntity, jDJSONObject);
                            return;
                        }
                        XViewConfigEntity.ControlEntity controlEntity = new XViewConfigEntity.ControlEntity();
                        controlEntity.name = "\u80cc\u666f\u70b9\u51fb";
                        controlEntity.type = "blank";
                        BaseLayer baseLayer3 = BaseLayer.this;
                        XViewMtaUtil.controlClickMta(baseLayer3.mContext, baseLayer3.layersEntity, baseLayer3.mTargetsEntity, controlEntity);
                    }
                }
            });
            this.mXViewContainer.addLayerViewWidthParams(view);
        }
        BaseLayerDelegate baseLayerDelegate = this.mBaseLayerDelegate;
        if (baseLayerDelegate != null) {
            baseLayerDelegate.setCurrentLayer(this.mCurrentLayer, this);
        }
    }

    public void setFlexCubeViewAnim() {
        BaseLayerDelegate baseLayerDelegate = this.mBaseLayerDelegate;
        if (baseLayerDelegate != null) {
            baseLayerDelegate.setFlexCubeViewAnim(this.mFlexCubeView, this.mXViewAnimationManager);
        }
    }

    public void setLayerClose(String str, String str2, int i2) {
        BaseLayerDelegate baseLayerDelegate = this.mBaseLayerDelegate;
        if (baseLayerDelegate == null) {
            return;
        }
        baseLayerDelegate.layerCloseCallBack(str, str2, i2);
    }

    public void setLayerShowSuccess(String str, String str2) {
        BaseLayerDelegate baseLayerDelegate = this.mBaseLayerDelegate;
        if (baseLayerDelegate == null) {
            return;
        }
        baseLayerDelegate.layerShowSuccessCallBack(str, str2);
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void setTargetInfo(XViewConfigEntity.TargetsEntity targetsEntity) {
        this.mTargetsEntity = targetsEntity;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void showPrepared() {
    }

    public void startCloseAni(String str, long j2) {
        XViewConfigEntity.LayersEntity layersEntity;
        BaseLayerDelegate baseLayerDelegate = this.mBaseLayerDelegate;
        if (baseLayerDelegate == null || (layersEntity = this.layersEntity) == null) {
            return;
        }
        baseLayerDelegate.startCloseAniCallBack(layersEntity.layerId, layersEntity.logicKey, str, j2);
    }

    public void startLoadingLayer(String str, String str2) {
        BaseLayerDelegate baseLayerDelegate = this.mBaseLayerDelegate;
        if (baseLayerDelegate == null) {
            return;
        }
        baseLayerDelegate.startLoadingLayerCallBack(str, str2);
    }

    public void startPopAni(String str, long j2) {
        XViewConfigEntity.LayersEntity layersEntity;
        BaseLayerDelegate baseLayerDelegate = this.mBaseLayerDelegate;
        if (baseLayerDelegate == null || (layersEntity = this.layersEntity) == null) {
            return;
        }
        baseLayerDelegate.startPopAniCallBack(layersEntity.layerId, layersEntity.logicKey, str, j2);
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void startTimeCountTimer() {
        XViewConfigEntity.RuleEntity ruleEntity;
        MyCountDownTimer myCountDownTimer;
        XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
        if (layersEntity == null || (ruleEntity = layersEntity.rule) == null) {
            return;
        }
        float f2 = layersEntity.extraInfo.showDuration;
        if (f2 <= 0.0f) {
            int i2 = ruleEntity.showDuration;
            f2 = i2 > 0 ? i2 : 0.0f;
        }
        if (f2 <= 0.0f || (myCountDownTimer = this.mCountDownTimer) == null) {
            return;
        }
        myCountDownTimer.cancel();
        this.mCountDownTimer.start(f2 * 1000.0f, new ICountdown() { // from class: com.jingdong.common.XView2.layer.BaseLayer.11
            {
                BaseLayer.this = this;
            }

            @Override // com.jingdong.common.XView2.layer.timer.ICountdown
            public void onFinish() {
                BaseLayer baseLayer = BaseLayer.this;
                ILayerCallBack iLayerCallBack = baseLayer.mCallBack;
                if (iLayerCallBack != null) {
                    iLayerCallBack.onLayerClosed(baseLayer.layersEntity.layerId, 12);
                }
                ExtraInfoEntity extraInfoEntity = BaseLayer.this.layersEntity.extraInfo;
                if (extraInfoEntity == null || TextUtils.isEmpty(extraInfoEntity.countDownLink)) {
                    return;
                }
                BaseLayer baseLayer2 = BaseLayer.this;
                XView2Utils.clickJump(baseLayer2.mContext, baseLayer2.layersEntity.extraInfo.countDownLink);
            }

            @Override // com.jingdong.common.XView2.layer.timer.ICountdown
            public void onStart() {
            }

            @Override // com.jingdong.common.XView2.layer.timer.ICountdown
            public void onTick(long j2) {
            }
        });
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void startXView2popUpAnim() {
        if (this.mBaseLayerDelegate != null) {
            ViewGroup animateTargetView = LayerUtil.getAnimateTargetView(this.layersEntity, this.mXViewContainer);
            if (animateTargetView == null) {
                closeCurrentLayer();
            } else {
                this.mBaseLayerDelegate.startXView2popUpAnim(animateTargetView, new AnimateListener() { // from class: com.jingdong.common.XView2.layer.BaseLayer.2
                    {
                        BaseLayer.this = this;
                    }

                    @Override // com.jingdong.common.XView2.animation.AnimateListener
                    public void onAnimateEnd() {
                        BaseLayer.this.endPopAni();
                    }

                    @Override // com.jingdong.common.XView2.animation.AnimateListener
                    public void onAnimateError() {
                    }

                    @Override // com.jingdong.common.XView2.animation.AnimateListener
                    public void onAnimateStart(long j2) {
                        BaseLayer baseLayer = BaseLayer.this;
                        XViewConfigEntity.LayersEntity layersEntity = baseLayer.layersEntity;
                        if (layersEntity != null) {
                            baseLayer.startPopAni(layersEntity.popUpAnimTy, j2);
                        }
                    }
                });
            }
        }
    }

    public void updateTimeRule(String str) {
        PageManager.getInstance().updatePageInfo(str, getTotalTimes(), getDayTimes());
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public XView2Container getContainer() {
        return this.mXViewContainer;
    }
}
