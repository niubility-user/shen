package com.jingdong.common.controller;

import android.text.TextUtils;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONObjectProxy;

/* loaded from: classes5.dex */
public class FavoriteController {

    /* loaded from: classes5.dex */
    public static class FavotiteEvent {
        public long id;
        public boolean isFavotite;

        public FavotiteEvent(long j2, boolean z) {
            this.id = j2;
            this.isFavotite = z;
        }
    }

    /* loaded from: classes5.dex */
    public interface OnFavotiteDoListener {
        void onEnd(boolean z, boolean z2, long j2, String str);
    }

    public static final void addFavorite(final long j2, HttpGroup httpGroup, final OnFavotiteDoListener onFavotiteDoListener) {
        if (httpGroup == null || j2 == 0) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("addFavorite");
        httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.PERSONAL_HOST));
        httpSetting.putJsonParam("wareId", "" + j2);
        httpSetting.putJsonParam("isNewText", Boolean.TRUE);
        if (!TextUtils.isEmpty(JDMtaUtils.getUnpl())) {
            httpSetting.putJsonParam("unpl", JDMtaUtils.getUnpl());
        }
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.controller.FavoriteController.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                String optString = jSONObject.optString("addFavorite");
                OnFavotiteDoListener.this.onEnd(true, jSONObject.optBoolean("flag"), j2, optString);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                OnFavotiteDoListener.this.onEnd(false, false, j2, "");
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        httpSetting.setNotifyUser(true);
        httpGroup.add(httpSetting);
    }

    public static final void cancelFavorite(final long j2, String str, HttpGroup httpGroup, final OnFavotiteDoListener onFavotiteDoListener) {
        if (httpGroup == null || j2 == 0) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("cancelFavorite");
        httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.PERSONAL_HOST));
        httpSetting.putJsonParam("wareId", "" + j2);
        httpSetting.putJsonParam("bbtf", str);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.controller.FavoriteController.3
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                String optString = jSONObject.optString("message");
                OnFavotiteDoListener.this.onEnd(true, jSONObject.optBoolean("flag"), j2, optString);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                OnFavotiteDoListener.this.onEnd(false, false, j2, "");
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        httpSetting.setNotifyUser(true);
        httpGroup.add(httpSetting);
    }

    public static final void cancelFavorite(final long j2, HttpGroup httpGroup, final OnFavotiteDoListener onFavotiteDoListener) {
        if (httpGroup == null || j2 == 0) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("cancelFavorite");
        httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.PERSONAL_HOST));
        httpSetting.putJsonParam("wareId", "" + j2);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.controller.FavoriteController.4
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                String optString = jSONObject.optString("message");
                OnFavotiteDoListener.this.onEnd(true, jSONObject.optBoolean("flag"), j2, optString);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                OnFavotiteDoListener.this.onEnd(false, false, j2, "");
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        httpSetting.setNotifyUser(true);
        httpGroup.add(httpSetting);
    }

    public static final void addFavorite(final long j2, String str, HttpGroup httpGroup, final OnFavotiteDoListener onFavotiteDoListener) {
        if (httpGroup == null || j2 == 0) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("addFavorite");
        httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.PERSONAL_HOST));
        httpSetting.putJsonParam("wareId", "" + j2);
        httpSetting.putJsonParam("isNewText", Boolean.TRUE);
        httpSetting.putJsonParam("bbtf", str);
        if (!TextUtils.isEmpty(JDMtaUtils.getUnpl())) {
            httpSetting.putJsonParam("unpl", JDMtaUtils.getUnpl());
        }
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.controller.FavoriteController.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                String optString = jSONObject.optString("addFavorite");
                OnFavotiteDoListener.this.onEnd(true, jSONObject.optBoolean("flag"), j2, optString);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                OnFavotiteDoListener.this.onEnd(false, false, j2, "");
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        httpSetting.setNotifyUser(true);
        httpGroup.add(httpSetting);
    }
}
