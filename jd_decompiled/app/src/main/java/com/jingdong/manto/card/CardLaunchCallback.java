package com.jingdong.manto.card;

import com.jingdong.manto.launch.MantoPreLaunchProcess;

/* loaded from: classes15.dex */
public interface CardLaunchCallback {
    void onBeginLaunch();

    void onCreateRuntime();

    void onInitRuntime();

    void onLaunchError(MantoPreLaunchProcess.LaunchError launchError);

    void onLaunchSuccess();

    void onPrepareSuccess(boolean z);

    void onShowSplash();

    void onStart();
}
