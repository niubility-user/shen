package com.jingdong.service.service;

import android.app.Activity;
import android.content.Context;
import java.util.List;

/* loaded from: classes10.dex */
public interface NotifyService {
    boolean checkMessagePopShow();

    void dismissNotifyActivity(Activity activity);

    int getAnimationStyle();

    String getAppPackageName();

    Activity getCurrentActivity();

    boolean getDDStationWindowKey();

    boolean getMSGSOUND();

    boolean getMsgShakeSwitch();

    String getNotifyClassName();

    List<String> getPopWindowList();

    boolean isAppForeground(Context context);

    int notifyLargeIcon();

    int notifySmallIcon();

    int soundResId();
}
