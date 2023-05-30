package com.jingdong.common.XView2.layer.flexcube.view;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import com.airbnb.lottie.LottieAnimationView;
import com.jd.lib.flexcube.iwidget.entity.material.ClickEvent;
import com.jd.lib.flexcube.iwidget.ui.IWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication;
import com.jd.lib.flexcube.widgets.b.b;
import com.jd.lib.flexcube.widgets.c.a;
import com.jingdong.common.XView2.container.XView2Container;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.entity.XViewLottieEntity;
import com.jingdong.common.XView2.layer.BaseLayerDelegate;
import com.jingdong.common.XView2.layer.IBaseLayer;
import com.jingdong.common.XView2.layer.ILayerCallBack;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void updateContent(@androidx.annotation.NonNull java.util.Map<java.lang.String, java.lang.String> r7, com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication r8) {
        /*
            r6 = this;
            com.jingdong.common.XView2.entity.XViewLottieEntity r0 = r6.mEntity
            if (r0 == 0) goto Led
            com.jingdong.common.XView2.entity.lottie.XViewLottieDataPath r0 = r0.dataPath
            if (r0 != 0) goto La
            goto Led
        La:
            java.lang.String r0 = r0.url
            java.lang.String r0 = com.jd.lib.flexcube.widgets.b.b.d(r7, r0)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L1a
            r6.clear()
            return
        L1a:
            r1 = 0
            r6.setVisibility(r1)
            r2 = 0
            if (r8 == 0) goto L48
            com.jd.lib.babel.ifloor.entity.BabelScope r3 = r8.getBabelScope()
            if (r3 == 0) goto L48
            com.jd.lib.babel.ifloor.entity.BabelScope r3 = r8.getBabelScope()
            java.lang.Class<com.jingdong.common.XView2.layer.flexcube.IXViewWithFlexCube> r4 = com.jingdong.common.XView2.layer.flexcube.IXViewWithFlexCube.class
            java.lang.Object r3 = r3.getService(r4)
            com.jingdong.common.XView2.layer.flexcube.IXViewWithFlexCube r3 = (com.jingdong.common.XView2.layer.flexcube.IXViewWithFlexCube) r3
            if (r3 == 0) goto L3a
            java.lang.String r4 = r3.getLayerId()
            goto L3c
        L3a:
            java.lang.String r4 = ""
        L3c:
            r6.mLayerId = r4
            if (r3 == 0) goto L45
            com.jingdong.common.XView2.layer.ILayerCallBack r3 = r3.getLayerCallBack()
            goto L46
        L45:
            r3 = r2
        L46:
            r6.mCallBack = r3
        L48:
            java.lang.String r3 = "xviewPreDownLoad2"
            boolean r3 = com.jingdong.common.utils.SwitchQueryFetcher.getSwitchBooleanValue(r3, r1)
            if (r3 == 0) goto L8b
            java.lang.String r3 = r6.mLayerId
            java.lang.String r3 = com.jingdong.common.XView2.utils.XView2Utils.getFinalLayerId(r3)
            com.jingdong.common.XView2.strategy.downloader.XViewCache r4 = com.jingdong.common.XView2.strategy.downloader.XViewCache.getInstance()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r3)
            r5.append(r0)
            java.lang.String r3 = r5.toString()
            com.jd.hybrid.downloader.m.b r3 = r4.getFiles(r3)
            if (r3 == 0) goto L8b
            java.lang.String r4 = r3.getFilePath()
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto L8b
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Exception -> L87
            java.lang.String r3 = r3.getFilePath()     // Catch: java.lang.Exception -> L87
            r4.<init>(r3)     // Catch: java.lang.Exception -> L87
            r6.setAnimation(r4, r2)     // Catch: java.lang.Exception -> L87
            r2 = 1
            goto L8c
        L87:
            r2 = move-exception
            r2.printStackTrace()
        L8b:
            r2 = 0
        L8c:
            if (r2 != 0) goto L91
            r6.setAnimationFromUrl(r0)
        L91:
            com.jingdong.common.XView2.entity.XViewLottieEntity r0 = r6.mEntity
            com.jingdong.common.XView2.entity.lottie.XViewLottieConfig r0 = r0.getConfig()
            java.lang.String r0 = r0.loop
            boolean r0 = com.jingdong.common.XView2.utils.XView2Utils.isConvertBool(r0)
            r6.loop(r0)
            com.jingdong.common.XView2.entity.XViewLottieEntity r0 = r6.mEntity
            com.jingdong.common.XView2.entity.lottie.XViewLottieConfig r0 = r0.getConfig()
            java.lang.String r0 = r0.autoFitType
            java.lang.String r2 = "1"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto Lb6
            android.widget.ImageView$ScaleType r0 = android.widget.ImageView.ScaleType.FIT_XY
            r6.setScaleType(r0)
            goto Ld1
        Lb6:
            com.jingdong.common.XView2.entity.XViewLottieEntity r0 = r6.mEntity
            com.jingdong.common.XView2.entity.lottie.XViewLottieConfig r0 = r0.getConfig()
            java.lang.String r0 = r0.autoFitType
            java.lang.String r2 = "2"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto Lcc
            android.widget.ImageView$ScaleType r0 = android.widget.ImageView.ScaleType.CENTER_INSIDE
            r6.setScaleType(r0)
            goto Ld1
        Lcc:
            android.widget.ImageView$ScaleType r0 = android.widget.ImageView.ScaleType.CENTER_CROP
            r6.setScaleType(r0)
        Ld1:
            com.jingdong.common.XView2.layer.flexcube.view.XViewLottieView$1 r0 = new com.jingdong.common.XView2.layer.flexcube.view.XViewLottieView$1
            r0.<init>()
            r6.addAnimatorListener(r0)
            com.jingdong.common.XView2.entity.XViewLottieEntity r0 = r6.mEntity
            com.jingdong.common.XView2.entity.lottie.XViewLottieDataPath r0 = r0.dataPath
            java.lang.String r0 = r0.clickEvent
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto Le9
            r6.setClickable(r1)
            return
        Le9:
            r6.setOnClick(r7, r8)
            return
        Led:
            r6.clear()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.XView2.layer.flexcube.view.XViewLottieView.updateContent(java.util.Map, com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication):void");
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void updateStyle(@NonNull XViewLottieEntity xViewLottieEntity, float f2) {
        this.mEntity = xViewLottieEntity;
        this.multiple = f2;
        setBackgroundColor(xViewLottieEntity != null ? com.jd.lib.flexcube.iwidget.b.a.a(xViewLottieEntity.getConfig().bgColor, 0) : 0);
    }
}
