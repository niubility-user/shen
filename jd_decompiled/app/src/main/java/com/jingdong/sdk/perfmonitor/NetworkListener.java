package com.jingdong.sdk.perfmonitor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;

/* loaded from: classes12.dex */
public class NetworkListener implements HttpGroup.OnAllListener {
    private String functionId;
    private HttpGroup.OnAllListener listener;
    private String pageKey;

    public NetworkListener(@Nullable HttpGroup.OnAllListener onAllListener, @NonNull String str, @NonNull String str2) {
        this.listener = onAllListener;
        this.pageKey = str;
        this.functionId = str2;
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
    public void onEnd(HttpResponse httpResponse) {
        HttpGroup.OnAllListener onAllListener = this.listener;
        if (onAllListener != null) {
            onAllListener.onEnd(httpResponse);
        }
        PerfMonitor.getInstance().onResponse(this.pageKey, this.functionId);
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
    public void onError(HttpError httpError) {
        HttpGroup.OnAllListener onAllListener = this.listener;
        if (onAllListener != null) {
            onAllListener.onError(httpError);
        }
        if (httpError != null) {
            PerfMonitor.getInstance().onResponse(this.pageKey, this.functionId, httpError.getErrorCode(), httpError.getMessage());
        }
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
    public void onProgress(int i2, int i3) {
        HttpGroup.OnAllListener onAllListener = this.listener;
        if (onAllListener != null) {
            onAllListener.onProgress(i2, i3);
        }
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
    public void onStart() {
        HttpGroup.OnAllListener onAllListener = this.listener;
        if (onAllListener != null) {
            onAllListener.onStart();
        }
        PerfMonitor.getInstance().onRequest(this.pageKey, this.functionId);
    }
}
