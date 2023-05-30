package com.jingdong.common.jump;

import android.content.Context;
import android.view.ViewGroup;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class JumpHttpListener {
    private Action action;
    private JumpLoadingWidget loadingWidget;

    /* JADX INFO: Access modifiers changed from: package-private */
    public JumpHttpListener(Action action) {
        this.action = action;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onEnd(HttpResponse httpResponse) {
        JumpLoadingWidget jumpLoadingWidget = this.loadingWidget;
        if (jumpLoadingWidget != null) {
            jumpLoadingWidget.dismiss();
        }
        if (httpResponse == null) {
            this.action.onError(null);
        } else {
            this.action.onEnd(httpResponse);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onError(HttpError httpError) {
        JumpLoadingWidget jumpLoadingWidget = this.loadingWidget;
        if (jumpLoadingWidget != null) {
            jumpLoadingWidget.dismiss();
        }
        this.action.onError(httpError);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onProgress(int i2, int i3) {
        this.action.onProgress(i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void showProgressbar(Context context) {
        JumpLoadingWidget jumpLoadingWidget = new JumpLoadingWidget();
        this.loadingWidget = jumpLoadingWidget;
        jumpLoadingWidget.showProgressBar(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void showProgressbar(ViewGroup viewGroup) {
        JumpLoadingWidget jumpLoadingWidget = new JumpLoadingWidget();
        this.loadingWidget = jumpLoadingWidget;
        jumpLoadingWidget.showProgressBar(viewGroup);
    }
}
