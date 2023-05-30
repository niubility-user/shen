package com.jd.lib.push.utils;

import android.content.Context;
import com.jingdong.common.messagecenter.JDPushApi;
import com.jingdong.jdpush_new.j.l;

/* loaded from: classes16.dex */
public class c implements JDPushApi {
    @Override // com.jingdong.common.messagecenter.JDPushApi
    public void bindPin(String str) {
        PushMessageUtils.bindPin(str);
    }

    @Override // com.jingdong.common.messagecenter.JDPushApi
    public void checkRegId(String str) {
        PushMessageUtils.checkRegId(str);
    }

    @Override // com.jingdong.common.messagecenter.JDPushApi
    public void clearBadge(Context context) {
        PushMessageUtils.clearBadge(context);
    }

    @Override // com.jingdong.common.messagecenter.JDPushApi
    public void clearPushNotices() {
        PushMessageUtils.clearPushNotices();
    }

    @Override // com.jingdong.common.messagecenter.JDPushApi
    public int getChannelId() {
        return l.a();
    }

    @Override // com.jingdong.common.messagecenter.JDPushApi
    public void recordOpenPushInfo(Context context, int i2, String str, String str2, int i3, String str3, String str4) {
        PushMessageUtils.recordOpenPushInfo(context, i2, str, str2, i3, str3, str4);
    }

    @Override // com.jingdong.common.messagecenter.JDPushApi
    public void registeredBusiness(int i2, Class<?> cls) {
        PushMessageUtils.registeredBusiness(i2, cls);
    }

    @Override // com.jingdong.common.messagecenter.JDPushApi
    public void setBadgeNum(Context context, int i2) {
        PushMessageUtils.setBadgeNum(context, i2);
    }

    @Override // com.jingdong.common.messagecenter.JDPushApi
    public void unBindPin(String str) {
        PushMessageUtils.unBindUser(str);
    }
}
