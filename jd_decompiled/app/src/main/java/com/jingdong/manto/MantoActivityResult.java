package com.jingdong.manto;

import android.app.Activity;
import android.content.Intent;

/* loaded from: classes15.dex */
public interface MantoActivityResult {

    /* loaded from: classes15.dex */
    public interface ResultCallback {
        void onActivityResult(int i2, int i3, Intent intent);
    }

    Activity getActivity();

    ResultCallback getResultCallback();

    void restoreResultCallback();

    void setResultCallback(ResultCallback resultCallback);
}
