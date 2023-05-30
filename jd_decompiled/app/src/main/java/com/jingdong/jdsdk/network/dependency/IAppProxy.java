package com.jingdong.jdsdk.network.dependency;

import android.app.Activity;

/* loaded from: classes.dex */
public interface IAppProxy {
    void clearCacheFiles();

    void exitApp();

    Activity getCurrentMyActivity();
}
