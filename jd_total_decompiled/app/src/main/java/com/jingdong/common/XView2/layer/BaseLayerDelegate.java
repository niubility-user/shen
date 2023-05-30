package com.jingdong.common.XView2.layer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSON;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.iwidget.ui.IKnowWidget;
import com.jd.lib.flexcube.widgets.FlexBoxView;
import com.jingdong.app.mall.mylib.R;
import com.jingdong.common.XView.XView;
import com.jingdong.common.XView2.XView2;
import com.jingdong.common.XView2.animation.AnimateListener;
import com.jingdong.common.XView2.animation.AnimateViewWrapper;
import com.jingdong.common.XView2.animation.FadeAnimation;
import com.jingdong.common.XView2.animation.ScaleAnimation;
import com.jingdong.common.XView2.animation.XViewAnimationManager;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.common.XView2LayerObservableManager;
import com.jingdong.common.XView2.container.XView2Container;
import com.jingdong.common.XView2.entity.LocationEntity;
import com.jingdong.common.XView2.entity.TimerEntity;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.layer.flexcube.FlexCubeLayer;
import com.jingdong.common.XView2.layer.timer.ICountdown;
import com.jingdong.common.XView2.layer.timer.ITimerCallBack;
import com.jingdong.common.XView2.layer.timer.MyCountDownTimer;
import com.jingdong.common.XView2.utils.TimeUtil;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.XView2.utils.XViewMtaUtil;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;
import com.jingdong.common.web.ui.CommonMFragment;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.tencent.smtt.sdk.ValueCallback;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class BaseLayerDelegate implements IBaseLayerDelegateCallBack {
    public AnimateViewWrapper mAnimateViewWrapper;
    public String mAppendUrlParams;
    private BaseLayer mBaseLayer;
    Context mContext;
    MyCountDownTimer mCountDownTimer;
    View mCurrentLayer;
    private FadeAnimation mFadeAnimation;
    XViewConfigEntity.LayersEntity mLayersEntity;
    protected ScaleAnimation mScaleAnimation;
    XViewConfigEntity.TargetsEntity mTargetsEntity;
    public XViewAnimationManager mViewAnimationManager;
    XView2 mXView2;
    private Map<String, String> mAppendUrlParamsMap = new HashMap();
    public Map<String, String> mStateMap = new HashMap();
    public int mStyleId = 0;

    public BaseLayerDelegate(Activity activity, XViewConfigEntity.LayersEntity layersEntity, XView2 xView2) {
        this.mContext = activity;
        this.mLayersEntity = layersEntity;
        this.mXView2 = xView2;
    }

    private AnimateListener getNewAnimateListener(final AnimateListener animateListener) {
        return new AnimateListener() { // from class: com.jingdong.common.XView2.layer.BaseLayerDelegate.3
            {
                BaseLayerDelegate.this = this;
            }

            @Override // com.jingdong.common.XView2.animation.AnimateListener
            public void onAnimateEnd() {
                AnimateListener animateListener2 = animateListener;
                if (animateListener2 != null) {
                    animateListener2.onAnimateEnd();
                }
            }

            @Override // com.jingdong.common.XView2.animation.AnimateListener
            public void onAnimateError() {
                AnimateListener animateListener2 = animateListener;
                if (animateListener2 != null) {
                    animateListener2.onAnimateError();
                }
            }

            @Override // com.jingdong.common.XView2.animation.AnimateListener
            public void onAnimateStart(long j2) {
                AnimateListener animateListener2 = animateListener;
                if (animateListener2 != null) {
                    animateListener2.onAnimateStart(j2);
                }
            }
        };
    }

    public void buildTimeCountTimerText(XView2Container xView2Container, final ITimerCallBack iTimerCallBack) {
        XViewConfigEntity.LayersEntity layersEntity;
        TimerEntity timerEntity;
        if (xView2Container == null || (layersEntity = this.mLayersEntity) == null || (timerEntity = layersEntity.timer) == null || timerEntity.sec <= 0) {
            return;
        }
        FrameLayout frameLayout = new FrameLayout(this.mContext);
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this.mContext);
        simpleDraweeView.setImageResource(R.drawable.xview2_timer_bg);
        simpleDraweeView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.XView2.layer.BaseLayerDelegate.4
            {
                BaseLayerDelegate.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ITimerCallBack iTimerCallBack2 = iTimerCallBack;
                if (iTimerCallBack2 != null) {
                    iTimerCallBack2.closeTimer(1);
                }
            }
        });
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(XView2Utils.getSizeBy750(72), XView2Utils.getSizeBy750(36));
        layoutParams.gravity = 53;
        if (xView2Container.getCurrentLayer() != null) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) xView2Container.getCurrentLayer().getLayoutParams();
            if (layoutParams2 != null) {
                layoutParams.topMargin = layoutParams2.topMargin + XView2Utils.getSizeBy750(5);
                layoutParams.rightMargin = layoutParams2.rightMargin + XView2Utils.getSizeBy750(25);
            }
            xView2Container.addView(frameLayout, layoutParams);
        }
        frameLayout.addView(simpleDraweeView, new FrameLayout.LayoutParams(XView2Utils.getSizeBy750(72), XView2Utils.getSizeBy750(36)));
        final TextView textView = new TextView(this.mContext);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(XView2Utils.getSizeBy750(32), XView2Utils.getSizeBy750(32));
        layoutParams3.topMargin = XView2Utils.getSizeBy750(2);
        layoutParams3.leftMargin = XView2Utils.getSizeBy750(2);
        textView.setTextSize(0, XView2Utils.getSizeBy750(20));
        textView.setTextColor(Color.parseColor(JDDarkUtil.COLOR_FFFFFFF));
        textView.setIncludeFontPadding(false);
        textView.setGravity(17);
        FontsUtil.changeTextFont(textView, 4099);
        frameLayout.addView(textView, layoutParams3);
        if (this.mCountDownTimer == null) {
            this.mCountDownTimer = new MyCountDownTimer(1000L);
        }
        this.mCountDownTimer.cancel();
        this.mCountDownTimer.start(this.mLayersEntity.timer.sec * 1000, new ICountdown() { // from class: com.jingdong.common.XView2.layer.BaseLayerDelegate.5
            {
                BaseLayerDelegate.this = this;
            }

            @Override // com.jingdong.common.XView2.layer.timer.ICountdown
            public void onFinish() {
                ITimerCallBack iTimerCallBack2 = iTimerCallBack;
                if (iTimerCallBack2 != null) {
                    iTimerCallBack2.closeTimer(2);
                }
            }

            @Override // com.jingdong.common.XView2.layer.timer.ICountdown
            public void onStart() {
            }

            @Override // com.jingdong.common.XView2.layer.timer.ICountdown
            public void onTick(long j2) {
                String ms2TimeString = TimeUtil.ms2TimeString(j2);
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "time " + ms2TimeString);
                if (!TextUtils.isEmpty(ms2TimeString)) {
                    textView.setText(ms2TimeString);
                }
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "millisUntilFinished " + j2);
            }
        });
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayerDelegateCallBack
    public void changeLayoutCallBack(String str, String str2, int i2, String str3) {
        JDWebView jdWebView;
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return;
        }
        View view = this.mCurrentLayer;
        if (view instanceof XView) {
            JDWebView jdWebView2 = ((XView) view).getJdWebView();
            StringBuilder sb = new StringBuilder(str);
            if (!TextUtils.isEmpty(str2)) {
                sb.append("','");
                sb.append(str2);
            }
            sb.append("','");
            sb.append(i2);
            sb.append("','");
            sb.append(str3);
            jdWebView2.loadUrl("javascript:nxviewChangeLayoutCallBack('" + sb.toString() + "');");
        }
        BaseLayer baseLayer = this.mBaseLayer;
        if (!(baseLayer instanceof FlexCubeLayer) || (jdWebView = ((FlexCubeLayer) baseLayer).getJdWebView()) == null) {
            return;
        }
        StringBuilder sb2 = new StringBuilder(str);
        if (!TextUtils.isEmpty(str2)) {
            sb2.append("','");
            sb2.append(str2);
        }
        sb2.append("','");
        sb2.append(i2);
        sb2.append("','");
        sb2.append(str3);
        jdWebView.loadUrl("javascript:nxviewChangeLayoutCallBack('" + sb2.toString() + "');");
    }

    public void closeXView2Anim(View view, final AnimateListener animateListener) {
        XViewConfigEntity.LayersEntity layersEntity = this.mLayersEntity;
        if (layersEntity == null) {
            return;
        }
        if ("2".equals(layersEntity.useCloseAnimation)) {
            if (this.mScaleAnimation == null) {
                this.mScaleAnimation = new ScaleAnimation(view, this.mLayersEntity.renderType == 6);
            }
            this.mScaleAnimation.startScaleInAnimation((Activity) this.mContext, new AnimateListener() { // from class: com.jingdong.common.XView2.layer.BaseLayerDelegate.2
                {
                    BaseLayerDelegate.this = this;
                }

                @Override // com.jingdong.common.XView2.animation.AnimateListener
                public void onAnimateEnd() {
                    AnimateListener animateListener2 = animateListener;
                    if (animateListener2 != null) {
                        animateListener2.onAnimateEnd();
                    }
                }

                @Override // com.jingdong.common.XView2.animation.AnimateListener
                public void onAnimateError() {
                    AnimateListener animateListener2 = animateListener;
                    if (animateListener2 != null) {
                        animateListener2.onAnimateError();
                    }
                }

                @Override // com.jingdong.common.XView2.animation.AnimateListener
                public void onAnimateStart(long j2) {
                    AnimateListener animateListener2 = animateListener;
                    if (animateListener2 != null) {
                        animateListener2.onAnimateStart(j2);
                    }
                }
            });
        } else if ("3".equals(this.mLayersEntity.useCloseAnimation)) {
            if (this.mScaleAnimation == null) {
                this.mScaleAnimation = new ScaleAnimation(view, this.mLayersEntity.renderType == 6);
            }
            this.mScaleAnimation.startScaleInAnimationInCenter((Activity) this.mContext, getNewAnimateListener(animateListener));
        }
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayerDelegateCallBack
    public void endCloseAniCallBack(String str, String str2) {
        XView2 xView2;
        XViewConfigEntity.TargetsEntity targetsEntity = this.mTargetsEntity;
        if (targetsEntity == null) {
            return;
        }
        if (targetsEntity.targetType == 1) {
            XView2LayerObservableManager.getManager().notifyEndCloseAni(str, str2);
        }
        if (this.mTargetsEntity.targetType == 2 && (this.mContext instanceof FragmentActivity) && (xView2 = this.mXView2) != null && (xView2.getCurrentFragment() instanceof CommonMFragment)) {
            CommonMFragment commonMFragment = (CommonMFragment) this.mXView2.getCurrentFragment();
            if (commonMFragment.getJdWebView() != null) {
                commonMFragment.getJdWebView().loadUrl("javascript:nxviewEndCloseAni('" + str + "');");
            }
        }
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayerDelegateCallBack
    public void endPopAniCallBack(String str, String str2) {
        XView2 xView2;
        XViewConfigEntity.TargetsEntity targetsEntity = this.mTargetsEntity;
        if (targetsEntity == null) {
            return;
        }
        if (targetsEntity.targetType == 1) {
            XView2LayerObservableManager.getManager().notifyEndPopAni(str, str2);
        }
        if (this.mTargetsEntity.targetType == 2 && (this.mContext instanceof FragmentActivity) && (xView2 = this.mXView2) != null && (xView2.getCurrentFragment() instanceof CommonMFragment)) {
            CommonMFragment commonMFragment = (CommonMFragment) this.mXView2.getCurrentFragment();
            if (commonMFragment.getJdWebView() != null) {
                commonMFragment.getJdWebView().loadUrl("javascript:nxviewEndPopAni('" + str + "');");
            }
        }
    }

    public String getAppendParamsUrl(String str) {
        Map<String, String> map;
        return (TextUtils.isEmpty(str) || (map = this.mAppendUrlParamsMap) == null) ? str : HttpGroup.mergerUrlAndParams(str, map);
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayerDelegateCallBack
    public LocationEntity getPopLocationCallBack(String str, String str2, int i2, int i3) {
        XView2 xView2;
        IWebUiBinder webUiBinder;
        XViewConfigEntity.TargetsEntity targetsEntity = this.mTargetsEntity;
        if (targetsEntity == null) {
            return null;
        }
        if (targetsEntity.targetType == 1) {
            XView2LayerObservableManager.getManager().notifyPopLocationObserver(str, str2, i2, i3);
        }
        if (this.mTargetsEntity.targetType == 2 && (this.mContext instanceof FragmentActivity) && (xView2 = this.mXView2) != null && (xView2.getCurrentFragment() instanceof CommonMFragment)) {
            CommonMFragment commonMFragment = (CommonMFragment) this.mXView2.getCurrentFragment();
            if (commonMFragment.getJdWebView() != null && (webUiBinder = commonMFragment.getWebUiBinder()) != null && webUiBinder.getJdWebView() != null && webUiBinder.getJdWebView().getWebView() != null) {
                webUiBinder.getJdWebView().getWebView().evaluateJavascript("nxviewPopLocationCallBack('" + str + "');", new ValueCallback() { // from class: com.jingdong.common.XView2.layer.BaseLayerDelegate.6
                    {
                        BaseLayerDelegate.this = this;
                    }

                    @Override // com.tencent.smtt.sdk.ValueCallback, android.webkit.ValueCallback
                    public void onReceiveValue(Object obj) {
                        XViewLogPrint.JDXLog.e(XView2Constants.TAG, "onReceiveValue" + obj);
                    }
                });
            }
        }
        return null;
    }

    public Map<String, String> getStateMap() {
        return this.mStateMap;
    }

    public int getStyleId() {
        return this.mStyleId;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayerDelegateCallBack
    public void initParamsBeforeCreateLayer(JSONObject jSONObject) {
        JSONObject optJSONObject;
        if (jSONObject == null) {
            return;
        }
        if (jSONObject.has(XView2Constants.APPENDURLPARAMS) && this.mLayersEntity != null) {
            this.mAppendUrlParamsMap = (Map) JDJSON.parseObject(jSONObject.optJSONObject(XView2Constants.APPENDURLPARAMS).toString(), Map.class);
            XViewConfigEntity.RenderDataEntity renderDataEntity = this.mLayersEntity.renderData;
            if (renderDataEntity != null && !TextUtils.isEmpty(renderDataEntity.url) && TextUtils.isEmpty(this.mAppendUrlParams)) {
                this.mAppendUrlParams = HttpGroup.mergerUrlAndParams(this.mLayersEntity.renderData.url, this.mAppendUrlParamsMap);
            }
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "mAppendUrlParams" + this.mAppendUrlParams);
        }
        if (jSONObject.has(XView2Constants.STATE) && this.mLayersEntity != null && (optJSONObject = jSONObject.optJSONObject(XView2Constants.STATE)) != null) {
            this.mStateMap = (Map) JDJSON.parseObject(optJSONObject.toString(), Map.class);
        }
        if (!jSONObject.has(XView2Constants.STYLEID) || this.mLayersEntity == null) {
            return;
        }
        this.mStyleId = jSONObject.optInt(XView2Constants.STYLEID);
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayerDelegateCallBack
    public void layerCloseCallBack(String str, String str2, int i2) {
        XView2 xView2;
        if (i2 == 0) {
            return;
        }
        XViewMtaUtil.sendLayerCloseMta(this.mContext, this.mLayersEntity, this.mTargetsEntity, i2);
        XViewConfigEntity.TargetsEntity targetsEntity = this.mTargetsEntity;
        if (targetsEntity != null && targetsEntity.targetType == 1) {
            if (i2 == 13 || i2 == 2 || i2 == 8) {
                XView2LayerObservableManager.getManager().notifyClickClose(str, str2);
            }
            if (i2 == 1) {
                XView2LayerObservableManager.getManager().notifyJumpClose(str, str2);
            }
            XView2LayerObservableManager.getManager().notifyLayerClose(str, str2, i2);
        }
        XViewConfigEntity.TargetsEntity targetsEntity2 = this.mTargetsEntity;
        if (targetsEntity2 != null && targetsEntity2.targetType == 2 && (this.mContext instanceof FragmentActivity) && (xView2 = this.mXView2) != null && (xView2.getCurrentFragment() instanceof CommonMFragment)) {
            CommonMFragment commonMFragment = (CommonMFragment) this.mXView2.getCurrentFragment();
            if (commonMFragment.getJdWebView() != null) {
                JDWebView jdWebView = commonMFragment.getJdWebView();
                StringBuilder sb = new StringBuilder(str);
                if (!TextUtils.isEmpty(str2)) {
                    sb.append("','");
                    sb.append(str2);
                }
                sb.append("','");
                sb.append(i2);
                jdWebView.injectJs("javascript:nxviewWillRemoveCallBack('" + sb.toString() + "');");
            }
        }
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayerDelegateCallBack
    public void layerShowErrorCallBack(String str, int i2) {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayerDelegateCallBack
    public void layerShowSuccessCallBack(String str, String str2) {
        XView2 xView2;
        XViewConfigEntity.TargetsEntity targetsEntity = this.mTargetsEntity;
        if (targetsEntity == null) {
            return;
        }
        if (targetsEntity.targetType == 1) {
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u539f\u751flayerShowSuccessCallBack");
            XView2LayerObservableManager.getManager().notifyLayerShowSuccess(str, str2);
        }
        if (this.mTargetsEntity.targetType == 2 && (this.mContext instanceof FragmentActivity) && (xView2 = this.mXView2) != null && (xView2.getCurrentFragment() instanceof CommonMFragment)) {
            CommonMFragment commonMFragment = (CommonMFragment) this.mXView2.getCurrentFragment();
            if (commonMFragment.getJdWebView() != null) {
                commonMFragment.getJdWebView().injectJs("javascript:nxviewWillDisplayCallBack('" + str + "');");
            }
        }
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayerDelegateCallBack
    public boolean notifyLayerCanPopStatus(String str, JSONObject jSONObject) {
        XViewConfigEntity.TargetsEntity targetsEntity = this.mTargetsEntity;
        if (targetsEntity == null) {
            return false;
        }
        if (targetsEntity.targetType == 1) {
            return XView2LayerObservableManager.getManager().notifyLayerCanPopStatus(str, jSONObject);
        }
        return true;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayerDelegateCallBack
    public void onClickCallBack(String str, String str2, int i2, String str3) {
        XView2 xView2;
        XViewConfigEntity.TargetsEntity targetsEntity = this.mTargetsEntity;
        if (targetsEntity == null) {
            return;
        }
        if (targetsEntity.targetType == 1) {
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u539f\u751fonClickCallBack");
            XView2LayerObservableManager.getManager().notifyOnClick(str, str2, i2, str3);
        }
        if (this.mTargetsEntity.targetType == 2 && (this.mContext instanceof FragmentActivity) && (xView2 = this.mXView2) != null && (xView2.getCurrentFragment() instanceof CommonMFragment)) {
            CommonMFragment commonMFragment = (CommonMFragment) this.mXView2.getCurrentFragment();
            if (commonMFragment.getJdWebView() != null) {
                JDWebView jdWebView = commonMFragment.getJdWebView();
                StringBuilder sb = new StringBuilder(str);
                if (!TextUtils.isEmpty(str2)) {
                    sb.append("','");
                    sb.append(str2);
                }
                sb.append("','");
                sb.append(i2);
                sb.append("','");
                sb.append(str3);
                jdWebView.loadUrl("javascript:nxviewOnClick('" + sb.toString() + "');");
            }
        }
    }

    public void releaseAnimation() {
        AnimateViewWrapper animateViewWrapper = this.mAnimateViewWrapper;
        if (animateViewWrapper != null) {
            animateViewWrapper.destroyAnimation();
            this.mAnimateViewWrapper = null;
        }
        XViewAnimationManager xViewAnimationManager = this.mViewAnimationManager;
        if (xViewAnimationManager != null) {
            xViewAnimationManager.destroyAnimation();
            this.mViewAnimationManager = null;
        }
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayerDelegateCallBack
    public void releaseBaseLayer() {
        ScaleAnimation scaleAnimation = this.mScaleAnimation;
        if (scaleAnimation != null) {
            scaleAnimation.destroyAnimation();
            this.mScaleAnimation = null;
        }
        FadeAnimation fadeAnimation = this.mFadeAnimation;
        if (fadeAnimation != null) {
            fadeAnimation.destroyAnimation();
            this.mFadeAnimation = null;
        }
        MyCountDownTimer myCountDownTimer = this.mCountDownTimer;
        if (myCountDownTimer != null) {
            myCountDownTimer.cancel();
            this.mCountDownTimer.destroy();
            this.mCountDownTimer = null;
        }
        releaseAnimation();
        this.mAppendUrlParams = null;
        this.mStateMap = null;
    }

    public void setAnimationRef(AnimateViewWrapper animateViewWrapper, XViewAnimationManager xViewAnimationManager) {
        this.mAnimateViewWrapper = animateViewWrapper;
        this.mViewAnimationManager = xViewAnimationManager;
    }

    public void setCurrentLayer(View view, BaseLayer baseLayer) {
        this.mCurrentLayer = view;
        this.mBaseLayer = baseLayer;
    }

    public void setFlexCubeViewAnim(View view, XViewAnimationManager xViewAnimationManager) {
        if (view == null) {
            return;
        }
        int i2 = 0;
        if ((view instanceof ViewGroup) && !(view instanceof IKnowWidget)) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            viewGroup.setClipChildren(false);
            viewGroup.setClipToPadding(false);
            while (i2 < childCount) {
                setFlexCubeViewAnim(viewGroup.getChildAt(i2), xViewAnimationManager);
                i2++;
            }
        } else if (view instanceof IKnowWidget) {
            BaseWidgetEntity widgetEntity = ((IKnowWidget) view).getWidgetEntity();
            if (widgetEntity != null && !TextUtils.isEmpty(widgetEntity.customConfig)) {
                try {
                    int optInt = new JSONObject(widgetEntity.customConfig).optInt(XView2Constants.APPEAR_ANIM_TYPE);
                    if (xViewAnimationManager != null) {
                        xViewAnimationManager.setXViewAnimation(this.mContext, view, optInt);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            if (view instanceof FlexBoxView) {
                ViewGroup viewGroup2 = (ViewGroup) view;
                int childCount2 = viewGroup2.getChildCount();
                viewGroup2.setClipChildren(false);
                viewGroup2.setClipToPadding(false);
                while (i2 < childCount2) {
                    View childAt = viewGroup2.getChildAt(i2);
                    if (childAt instanceof ViewGroup) {
                        setFlexCubeViewAnim(childAt, xViewAnimationManager);
                    }
                    i2++;
                }
            }
        }
    }

    public void setTargetsEntity(XViewConfigEntity.TargetsEntity targetsEntity) {
        this.mTargetsEntity = targetsEntity;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayerDelegateCallBack
    public void startCloseAniCallBack(String str, String str2, String str3, long j2) {
        XView2 xView2;
        XViewConfigEntity.TargetsEntity targetsEntity = this.mTargetsEntity;
        if (targetsEntity == null) {
            return;
        }
        if (targetsEntity.targetType == 1) {
            XView2LayerObservableManager.getManager().notifyStartCloseAni(str, str2, str3, j2);
        }
        if (this.mTargetsEntity.targetType == 2 && (this.mContext instanceof FragmentActivity) && (xView2 = this.mXView2) != null && (xView2.getCurrentFragment() instanceof CommonMFragment)) {
            CommonMFragment commonMFragment = (CommonMFragment) this.mXView2.getCurrentFragment();
            if (commonMFragment.getJdWebView() != null) {
                JDWebView jdWebView = commonMFragment.getJdWebView();
                StringBuilder sb = new StringBuilder(str);
                if (!TextUtils.isEmpty(str2)) {
                    sb.append("','");
                    sb.append(str2);
                }
                sb.append("','");
                sb.append(str3);
                sb.append("','");
                sb.append(j2);
                jdWebView.loadUrl("javascript:nxviewStartCloseAni('" + sb.toString() + "');");
            }
        }
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayerDelegateCallBack
    public void startLoadingLayerCallBack(String str, String str2) {
        XView2 xView2;
        XViewConfigEntity.TargetsEntity targetsEntity = this.mTargetsEntity;
        if (targetsEntity == null) {
            return;
        }
        if (targetsEntity.targetType == 1) {
            XView2LayerObservableManager.getManager().notifyStartLoadingLayer(str, str2);
        }
        if (this.mTargetsEntity.targetType == 2 && (this.mContext instanceof FragmentActivity) && (xView2 = this.mXView2) != null && (xView2.getCurrentFragment() instanceof CommonMFragment)) {
            CommonMFragment commonMFragment = (CommonMFragment) this.mXView2.getCurrentFragment();
            if (commonMFragment.getJdWebView() != null) {
                commonMFragment.getJdWebView().loadUrl("javascript:nxviewStartLoadingCallBack('" + str + "');");
            }
        }
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayerDelegateCallBack
    public void startPopAniCallBack(String str, String str2, String str3, long j2) {
        XView2 xView2;
        XViewConfigEntity.TargetsEntity targetsEntity = this.mTargetsEntity;
        if (targetsEntity == null) {
            return;
        }
        if (targetsEntity.targetType == 1) {
            XView2LayerObservableManager.getManager().notifyStartPopAni(str, str2, str3, j2);
        }
        if (this.mTargetsEntity.targetType == 2 && (this.mContext instanceof FragmentActivity) && (xView2 = this.mXView2) != null && (xView2.getCurrentFragment() instanceof CommonMFragment)) {
            CommonMFragment commonMFragment = (CommonMFragment) this.mXView2.getCurrentFragment();
            if (commonMFragment.getJdWebView() != null) {
                JDWebView jdWebView = commonMFragment.getJdWebView();
                StringBuilder sb = new StringBuilder();
                sb.append("javascript:nxviewStartPopAni('");
                sb.append(str + "','" + str3 + "','" + j2);
                sb.append("');");
                jdWebView.loadUrl(sb.toString());
            }
        }
    }

    public void startXView2popUpAnim(View view, final AnimateListener animateListener) {
        XViewConfigEntity.LayersEntity layersEntity = this.mLayersEntity;
        if (layersEntity == null) {
            return;
        }
        if ("1".equals(layersEntity.popUpAnimTy)) {
            if (this.mScaleAnimation == null) {
                this.mScaleAnimation = new ScaleAnimation(view, this.mLayersEntity.renderType == 6);
            }
            this.mScaleAnimation.startScaleOutAnimation((Activity) this.mContext, new AnimateListener() { // from class: com.jingdong.common.XView2.layer.BaseLayerDelegate.1
                {
                    BaseLayerDelegate.this = this;
                }

                @Override // com.jingdong.common.XView2.animation.AnimateListener
                public void onAnimateEnd() {
                    AnimateListener animateListener2 = animateListener;
                    if (animateListener2 != null) {
                        animateListener2.onAnimateEnd();
                    }
                }

                @Override // com.jingdong.common.XView2.animation.AnimateListener
                public void onAnimateError() {
                    AnimateListener animateListener2 = animateListener;
                    if (animateListener2 != null) {
                        animateListener2.onAnimateError();
                    }
                }

                @Override // com.jingdong.common.XView2.animation.AnimateListener
                public void onAnimateStart(long j2) {
                    AnimateListener animateListener2 = animateListener;
                    if (animateListener2 != null) {
                        animateListener2.onAnimateStart(j2);
                    }
                }
            });
        } else if (TextUtils.equals("2", this.mLayersEntity.popUpAnimTy)) {
            if (this.mFadeAnimation == null) {
                this.mFadeAnimation = new FadeAnimation(view);
            }
            this.mFadeAnimation.startFadeInAnimation((Activity) this.mContext, getNewAnimateListener(animateListener));
        } else if ("3".equals(this.mLayersEntity.popUpAnimTy)) {
            if (this.mScaleAnimation == null) {
                this.mScaleAnimation = new ScaleAnimation(view, this.mLayersEntity.renderType == 6);
            }
            this.mScaleAnimation.startScaleOutAnimationInCenter((Activity) this.mContext, getNewAnimateListener(animateListener));
        }
    }
}
