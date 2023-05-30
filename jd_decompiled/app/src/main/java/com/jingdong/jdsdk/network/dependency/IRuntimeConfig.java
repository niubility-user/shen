package com.jingdong.jdsdk.network.dependency;

/* loaded from: classes.dex */
public interface IRuntimeConfig {
    boolean getBoolean(String str, boolean z);

    String getDataFromMobileConfig(String str, String str2);

    String getDataFromSwitchQuery(String str, String str2);

    @Deprecated
    String getStringFromPreference(String str);

    boolean isUseHttpsDuringX();

    boolean isXTime();
}
