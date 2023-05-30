package com.jingdong.common.XView2.layer;

import android.view.View;
import com.jingdong.common.XView2.container.XView2Container;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public interface IBaseLayer {
    void changeLayoutCallBack(String str, String str2, int i2, String str3);

    void closeLayer(String str, int i2);

    void closeXView2Anim();

    void configControlBtn();

    void configCurrentLayer(XViewConfigEntity.TargetsEntity targetsEntity, View view);

    void destroyLayer();

    BaseLayerDelegate getBaseLayerDelegate();

    View getContainer();

    String getLayerId();

    void initParamsBeforeCreateLayer(JSONObject jSONObject);

    XView2Container initXViewContainer();

    boolean isFullScreen();

    boolean isJumpClose();

    boolean isLayerEnterImmediatelyPop();

    boolean isLayerEnterPop();

    boolean isRenderSuccess();

    void onLayerDisplayed(String str, String str2);

    void onLoadingLayer(String str, String str2);

    void onPause();

    void onResume();

    void releaseLayer();

    void setCurrentLayer(View view);

    void setTargetInfo(XViewConfigEntity.TargetsEntity targetsEntity);

    void showPrepared();

    void startTimeCountTimer();

    void startXView2popUpAnim();
}
