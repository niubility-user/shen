package com.jingdong.common.jump;

import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;

/* loaded from: classes5.dex */
public abstract class Action {
    public abstract void onEnd(HttpResponse httpResponse);

    public abstract void onError(HttpError httpError);

    public void onProgress(int i2, int i3) {
    }
}
