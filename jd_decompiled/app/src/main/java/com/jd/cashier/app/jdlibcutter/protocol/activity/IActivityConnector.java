package com.jd.cashier.app.jdlibcutter.protocol.activity;

import androidx.fragment.app.FragmentActivity;

/* loaded from: classes13.dex */
public interface IActivityConnector {
    void initStatus();

    void onClearBitmapCache();

    void onCreate(FragmentActivity fragmentActivity);

    void onDestroy(FragmentActivity fragmentActivity);

    void onFinish(FragmentActivity fragmentActivity);

    void onPause(FragmentActivity fragmentActivity);

    void onRestart(FragmentActivity fragmentActivity);

    void onResume(FragmentActivity fragmentActivity);

    void onStart(FragmentActivity fragmentActivity);

    void onStop(FragmentActivity fragmentActivity);

    void setContentView(FragmentActivity fragmentActivity);

    void setStatusBarTintEnable(boolean z);

    void setStatusBarTransparentEnable(boolean z);
}
