package com.jingdong.app.mall.personel.home;

import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.PersonalInfoManager;
import com.jingdong.common.utils.personal.JDPersonalPlatformConfigUtils;
import com.jingdong.common.utils.personal.PersonInfoBusinessCallback;
import com.jingdong.common.utils.personal.platform.impl.JDPersonalPlatformHttpProxy;

/* loaded from: classes4.dex */
public class a {
    public static void a() {
        try {
            JDPersonalPlatformConfigUtils.getPersonInfoBusinessInfo(new JDPersonalPlatformHttpProxy("1"), (PersonInfoBusinessCallback) null);
        } catch (Exception unused) {
        }
        if (LoginUserBase.hasLogin()) {
            PersonalInfoManager.requestPersonalInfo(HttpGroupUtils.getHttpGroupaAsynPool(), null, 1, 1, false);
        }
    }
}
