package com.jingdong.service.service;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.service.callback.LoginListener;

/* loaded from: classes10.dex */
public interface DeeplinkService {
    void gotoJShopHome(Context context, String str, String str2, String str3);

    boolean isSwitchOpen(long j2);

    void jumpToMessageCenter(Context context);

    void startLoginActivity(Context context, Bundle bundle);

    void startLoginActivity(Context context, Bundle bundle, LoginListener loginListener, String str);

    void startWebActivity(Context context, String str);
}
