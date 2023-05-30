package com.jingdong.aura.wrapper.listener;

import android.app.ActivityManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import java.util.List;

/* loaded from: classes.dex */
public interface AuraPrivacyInfoListener {
    String getOsBuild_BRAND();

    String getOsBuild_CPU_ABI();

    String getOsBuild_HARDWARE();

    String getOsBuild_MANUFACTURER();

    String[] getOsBuild_SUPPORTED_32_BIT_ABIS();

    Configuration getOsConfiguration(Resources resources);

    DisplayMetrics getOsDisplayMetrics(Resources resources);

    List<ActivityManager.RunningTaskInfo> getOsRunningTaskInfo();

    boolean getPrivacyState();

    boolean isForeground();
}
