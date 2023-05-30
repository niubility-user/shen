package com.jingdong.common.XView2.layer;

import com.jingdong.common.XView2.entity.LocationEntity;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public interface IBaseLayerDelegateCallBack {
    void changeLayoutCallBack(String str, String str2, int i2, String str3);

    void endCloseAniCallBack(String str, String str2);

    void endPopAniCallBack(String str, String str2);

    LocationEntity getPopLocationCallBack(String str, String str2, int i2, int i3);

    void initParamsBeforeCreateLayer(JSONObject jSONObject);

    void layerCloseCallBack(String str, String str2, int i2);

    void layerShowErrorCallBack(String str, int i2);

    void layerShowSuccessCallBack(String str, String str2);

    boolean notifyLayerCanPopStatus(String str, JSONObject jSONObject);

    void onClickCallBack(String str, String str2, int i2, String str3);

    void releaseBaseLayer();

    void startCloseAniCallBack(String str, String str2, String str3, long j2);

    void startLoadingLayerCallBack(String str, String str2);

    void startPopAniCallBack(String str, String str2, String str3, long j2);
}
