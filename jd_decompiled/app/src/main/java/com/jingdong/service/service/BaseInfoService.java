package com.jingdong.service.service;

/* loaded from: classes10.dex */
public interface BaseInfoService {
    String getAndroidVersion();

    float getDensity();

    int getDensityDpi();

    String getDeviceBrand();

    String getDeviceModel();

    String[][] getNetAddresses();

    float getScaledDensity();

    int getScreenHeight();

    int getScreenWidth();

    boolean isNetworkAvailable();

    boolean isWifi();
}
