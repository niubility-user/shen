package com.jingdong.service.service;

import android.content.Context;

/* loaded from: classes10.dex */
public interface OpenAppService {
    void gotoHomePage(Context context);

    void gotoOrderPage(Context context, String str);

    void gotoProductDetailPage(Context context, String str);

    void gotoProductDetailPage(Context context, String str, String str2);

    void gotoUserInfoPage(Context context);

    void startOpenApp(Context context, String str);
}
