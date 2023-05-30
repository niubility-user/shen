package com.jingdong.common.network;

import android.app.Activity;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class DefaultEffectHttpListener extends EffectHttpListener implements HttpGroup.OnStartListener, HttpGroup.OnEndListener, HttpGroup.OnErrorListener, HttpGroup.OnCancelListener {
    private static final String TAG = "DefaultEffectHttpListener";
    private HttpGroup.OnCancelListener onCancelListener;
    private HttpGroup.OnEndListener onEndListener;
    private HttpGroup.OnErrorListener onErrorListener;
    private HttpGroup.OnStartListener onStartListener;

    public DefaultEffectHttpListener(HttpSetting httpSetting, Activity activity) {
        super(activity, httpSetting != null ? httpSetting.getProgressBarRootLayout() : null);
        if (OKLog.D) {
            OKLog.d("=======>", "DefaultEffectHttpListener construct!");
        }
        if (httpSetting != null) {
            this.onStartListener = httpSetting.getOnStartListener();
            this.onEndListener = httpSetting.getOnEndListener();
            this.onErrorListener = httpSetting.getOnErrorListener();
            this.onCancelListener = httpSetting.getOnCancelListener();
            this.onTouchEvent = httpSetting.isOnTouchEvent();
        }
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnCancelListener
    public void onCancel() {
        HttpGroup.OnCancelListener onCancelListener = this.onCancelListener;
        if (onCancelListener != null) {
            onCancelListener.onCancel();
        }
        missionComplete();
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
    public void onEnd(HttpResponse httpResponse) {
        HttpGroup.OnEndListener onEndListener = this.onEndListener;
        if (onEndListener != null) {
            onEndListener.onEnd(httpResponse);
        }
        missionComplete();
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
    public void onError(HttpError httpError) {
        HttpGroup.OnErrorListener onErrorListener = this.onErrorListener;
        if (onErrorListener != null) {
            onErrorListener.onError(httpError);
        }
        missionComplete();
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
    public void onStart() {
        missionBegins();
        HttpGroup.OnStartListener onStartListener = this.onStartListener;
        if (onStartListener != null) {
            onStartListener.onStart();
        }
    }
}
