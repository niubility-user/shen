package com.jingdong.common.XView2.layer.flexcube.view;

import android.animation.Animator;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.airbnb.lottie.LottieAnimationView;
import com.jd.lib.flexcube.iwidget.entity.material.ClickEvent;
import com.jd.lib.flexcube.iwidget.ui.IWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication;
import com.jd.lib.flexcube.widgets.b.b;
import com.jd.lib.flexcube.widgets.c.a;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.container.XView2Container;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.entity.XViewLottieEntity;
import com.jingdong.common.XView2.entity.lottie.XViewLottieDataPath;
import com.jingdong.common.XView2.layer.BaseLayerDelegate;
import com.jingdong.common.XView2.layer.IBaseLayer;
import com.jingdong.common.XView2.layer.ILayerCallBack;
import com.jingdong.common.XView2.layer.flexcube.IXViewWithFlexCube;
import com.jingdong.common.XView2.strategy.downloader.XViewCache;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import java.io.FileInputStream;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class XViewLottieView extends LottieAnimationView implements IWidget<XViewLottieEntity>, IBaseLayer {
    public ILayerCallBack mCallBack;
    private XViewLottieEntity mEntity;
    public AtomicBoolean mHasResume;
    private AtomicBoolean mIsCompleted;
    private String mLayerId;
    private float multiple;

    public XViewLottieView(Context context) {
        super(context);
        this.mIsCompleted = new AtomicBoolean(false);
        this.mHasResume = new AtomicBoolean(false);
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void changeLayoutCallBack(String str, String str2, int i2, String str3) {
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void clear() {
        setVisibility(8);
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void closeLayer(String str, int i2) {
        cancelAnimation();
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void closeXView2Anim() {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void configControlBtn() {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void configCurrentLayer(XViewConfigEntity.TargetsEntity targetsEntity, View view) {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void destroyLayer() {
        destroyDrawingCache();
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public BaseLayerDelegate getBaseLayerDelegate() {
        return null;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public View getContainer() {
        return null;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public String getLayerId() {
        return null;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsHeight() {
        XViewLottieEntity xViewLottieEntity = this.mEntity;
        if (xViewLottieEntity != null) {
            return xViewLottieEntity.getBaseConfig().getH(this.multiple);
        }
        return 0;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsWidth() {
        XViewLottieEntity xViewLottieEntity = this.mEntity;
        if (xViewLottieEntity != null) {
            return xViewLottieEntity.getBaseConfig().getW(this.multiple);
        }
        return 0;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void initParamsBeforeCreateLayer(JSONObject jSONObject) {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public XView2Container initXViewContainer() {
        return null;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public boolean isFullScreen() {
        return false;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public boolean isJumpClose() {
        return false;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public boolean isLayerEnterImmediatelyPop() {
        return false;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public boolean isLayerEnterPop() {
        return false;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public boolean isRenderSuccess() {
        return false;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void onLayerDisplayed(String str, String str2) {
        if (this.mIsCompleted.get()) {
            return;
        }
        playAnimation();
        this.mIsCompleted.set(true);
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void onLoadingLayer(String str, String str2) {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void onPause() {
        pauseAnimation();
        this.mHasResume.set(false);
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void onResume() {
        if (!this.mIsCompleted.get()) {
            playAnimation();
            this.mIsCompleted.set(true);
        } else if (this.mHasResume.get()) {
        } else {
            resumeAnimation();
            this.mHasResume.set(true);
        }
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void releaseLayer() {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void setCurrentLayer(View view) {
    }

    protected void setOnClick(Map<String, String> map, IWidgetCommunication iWidgetCommunication) {
        ClickEvent a = b.a(map, this.mEntity.dataPath.clickEvent);
        if (a != null) {
            a.b bVar = new a.b(getContext(), a);
            bVar.a(iWidgetCommunication.getBabelScope());
            setOnClickListener(bVar.b());
            return;
        }
        setClickable(false);
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void setTargetInfo(XViewConfigEntity.TargetsEntity targetsEntity) {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void showPrepared() {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void startTimeCountTimer() {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void startXView2popUpAnim() {
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00e9  */
    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void updateContent(@NonNull Map<String, String> map, IWidgetCommunication iWidgetCommunication) {
        XViewLottieDataPath xViewLottieDataPath;
        boolean z;
        XViewLottieEntity xViewLottieEntity = this.mEntity;
        if (xViewLottieEntity != null && (xViewLottieDataPath = xViewLottieEntity.dataPath) != null) {
            String d = b.d(map, xViewLottieDataPath.url);
            if (TextUtils.isEmpty(d)) {
                clear();
                return;
            }
            setVisibility(0);
            if (iWidgetCommunication != null && iWidgetCommunication.getBabelScope() != null) {
                IXViewWithFlexCube iXViewWithFlexCube = (IXViewWithFlexCube) iWidgetCommunication.getBabelScope().getService(IXViewWithFlexCube.class);
                this.mLayerId = iXViewWithFlexCube != null ? iXViewWithFlexCube.getLayerId() : "";
                this.mCallBack = iXViewWithFlexCube != null ? iXViewWithFlexCube.getLayerCallBack() : null;
            }
            if (SwitchQueryFetcher.getSwitchBooleanValue(XView2Constants.XVIEW_PREDOWNLOAD, false)) {
                String finalLayerId = XView2Utils.getFinalLayerId(this.mLayerId);
                com.jd.hybrid.downloader.m.b files = XViewCache.getInstance().getFiles(finalLayerId + d);
                if (files != null && !TextUtils.isEmpty(files.getFilePath())) {
                    try {
                        setAnimation(new FileInputStream(files.getFilePath()), null);
                        z = true;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    if (!z) {
                        setAnimationFromUrl(d);
                    }
                    loop(XView2Utils.isConvertBool(this.mEntity.getConfig().loop));
                    if (!"1".equals(this.mEntity.getConfig().autoFitType)) {
                        setScaleType(ImageView.ScaleType.FIT_XY);
                    } else if ("2".equals(this.mEntity.getConfig().autoFitType)) {
                        setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    } else {
                        setScaleType(ImageView.ScaleType.CENTER_CROP);
                    }
                    addAnimatorListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.XView2.layer.flexcube.view.XViewLottieView.1
                        @Override // android.animation.Animator.AnimatorListener
                        public void onAnimationCancel(Animator animator) {
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator) {
                            XViewLottieView xViewLottieView = XViewLottieView.this;
                            if (xViewLottieView.mCallBack == null || !XView2Utils.isConvertBool(xViewLottieView.mEntity.getConfig().autoClose)) {
                                return;
                            }
                            XViewLottieView xViewLottieView2 = XViewLottieView.this;
                            xViewLottieView2.mCallBack.onLayerClosed(xViewLottieView2.mLayerId, 5);
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public void onAnimationRepeat(Animator animator) {
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public void onAnimationStart(Animator animator) {
                            XViewLottieView xViewLottieView = XViewLottieView.this;
                            ILayerCallBack iLayerCallBack = xViewLottieView.mCallBack;
                            if (iLayerCallBack != null) {
                                iLayerCallBack.onLayerDisplayed(xViewLottieView.mLayerId);
                            }
                        }
                    });
                    if (!TextUtils.isEmpty(this.mEntity.dataPath.clickEvent)) {
                        setClickable(false);
                        return;
                    } else {
                        setOnClick(map, iWidgetCommunication);
                        return;
                    }
                }
            }
            z = false;
            if (!z) {
            }
            loop(XView2Utils.isConvertBool(this.mEntity.getConfig().loop));
            if (!"1".equals(this.mEntity.getConfig().autoFitType)) {
            }
            addAnimatorListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.XView2.layer.flexcube.view.XViewLottieView.1
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    XViewLottieView xViewLottieView = XViewLottieView.this;
                    if (xViewLottieView.mCallBack == null || !XView2Utils.isConvertBool(xViewLottieView.mEntity.getConfig().autoClose)) {
                        return;
                    }
                    XViewLottieView xViewLottieView2 = XViewLottieView.this;
                    xViewLottieView2.mCallBack.onLayerClosed(xViewLottieView2.mLayerId, 5);
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    XViewLottieView xViewLottieView = XViewLottieView.this;
                    ILayerCallBack iLayerCallBack = xViewLottieView.mCallBack;
                    if (iLayerCallBack != null) {
                        iLayerCallBack.onLayerDisplayed(xViewLottieView.mLayerId);
                    }
                }
            });
            if (!TextUtils.isEmpty(this.mEntity.dataPath.clickEvent)) {
            }
        } else {
            clear();
        }
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void updateStyle(@NonNull XViewLottieEntity xViewLottieEntity, float f2) {
        this.mEntity = xViewLottieEntity;
        this.multiple = f2;
        setBackgroundColor(xViewLottieEntity != null ? com.jd.lib.flexcube.iwidget.b.a.a(xViewLottieEntity.getConfig().bgColor, 0) : 0);
    }
}
