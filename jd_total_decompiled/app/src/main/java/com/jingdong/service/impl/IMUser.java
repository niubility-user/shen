package com.jingdong.service.impl;

import com.jingdong.service.BaseService;
import com.jingdong.service.callback.PersonalInfoListener;
import com.jingdong.service.service.UserService;

/* loaded from: classes10.dex */
public class IMUser extends BaseService implements UserService {
    private static final String TAG = "IMUser";

    public void clearLocalOnlineState() {
    }

    public String getA2() {
        return "";
    }

    public String getCookies() {
        return null;
    }

    public int getDwAppID() {
        return 0;
    }

    public String getPin() {
        return "";
    }

    public String getUserAppId() {
        return null;
    }

    public boolean hasLogin() {
        return false;
    }

    public String imgUrl() {
        return null;
    }

    public String petName() {
        return null;
    }

    public void requestPersonalInfo(PersonalInfoListener personalInfoListener) {
    }
}
