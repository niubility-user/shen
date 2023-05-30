package com.jingdong.common.messagecenter;

import android.content.Context;

/* loaded from: classes5.dex */
public interface JDPushApi {
    void bindPin(String str);

    void checkRegId(String str);

    void clearBadge(Context context);

    void clearPushNotices();

    int getChannelId();

    void recordOpenPushInfo(Context context, int i2, String str, String str2, int i3, String str3, String str4);

    void registeredBusiness(int i2, Class<?> cls);

    void setBadgeNum(Context context, int i2);

    void unBindPin(String str);
}
