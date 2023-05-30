package com.jingdong.manto.jsapi.openmodule;

/* loaded from: classes15.dex */
public interface MantoLifecycleLisener {
    void onBackground();

    void onDestroy();

    void onForeground();

    void onPause();

    void onReady();

    boolean onRemove();
}
