package com.jingdong.service.impl;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.service.BaseService;
import com.jingdong.service.callback.LoginListener;
import com.jingdong.service.service.DeeplinkService;

/* loaded from: classes10.dex */
public class IMDeeplink extends BaseService implements DeeplinkService {
    private static final String TAG = "IMDeeplink";

    public void gotoJShopHome(Context context, String str, String str2, String str3) {
    }

    public boolean isSwitchOpen(long j2) {
        return false;
    }

    public void jumpToMessageCenter(Context context) {
    }

    public void startLoginActivity(Context context, Bundle bundle) {
    }

    public void startLoginActivity(Context context, Bundle bundle, LoginListener loginListener, String str) {
    }

    public void startWebActivity(Context context, String str) {
    }
}
