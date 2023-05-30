package com.jingdong.service.service;

import com.jingdong.service.callback.PersonalInfoListener;

/* loaded from: classes10.dex */
public interface UserService {
    void clearLocalOnlineState();

    String getA2();

    String getCookies();

    int getDwAppID();

    String getPin();

    String getUserAppId();

    boolean hasLogin();

    String imgUrl();

    String petName();

    void requestPersonalInfo(PersonalInfoListener personalInfoListener);
}
