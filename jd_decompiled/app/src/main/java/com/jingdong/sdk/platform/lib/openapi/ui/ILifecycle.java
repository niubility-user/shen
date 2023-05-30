package com.jingdong.sdk.platform.lib.openapi.ui;

import android.os.Bundle;

/* loaded from: classes10.dex */
public interface ILifecycle {
    void onCreate(Bundle bundle);

    void onDestroy();

    void onPause();

    void onResume();

    void onStart();

    void onStop();
}
