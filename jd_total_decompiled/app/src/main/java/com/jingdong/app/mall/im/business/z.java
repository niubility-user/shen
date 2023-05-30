package com.jingdong.app.mall.im.business;

import com.jingdong.common.login.SafetyManager;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.PersonalInfoManager;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.service.callback.PersonalInfoListener;
import com.jingdong.service.impl.IMUser;

/* loaded from: classes4.dex */
public class z extends IMUser {
    private static final String a = "z";

    /* loaded from: classes4.dex */
    class a implements PersonalInfoManager.PersonalInfoRequestListener {
        final /* synthetic */ PersonalInfoListener a;

        a(z zVar, PersonalInfoListener personalInfoListener) {
            this.a = personalInfoListener;
        }

        @Override // com.jingdong.common.utils.PersonalInfoManager.PersonalInfoRequestListener
        public void onEnd() {
            String str = PersonalInfoManager.getInstance().getUserInfoSns().imgUrl;
            String str2 = PersonalInfoManager.getInstance().getUserInfoSns().petName;
            this.a.onEnd();
        }

        @Override // com.jingdong.common.utils.PersonalInfoManager.PersonalInfoRequestListener
        public void onError() {
            this.a.onError();
        }
    }

    @Override // com.jingdong.service.impl.IMUser, com.jingdong.service.service.UserService
    public void clearLocalOnlineState() {
        UserUtil.getWJLoginHelper().clearLocalOnlineState();
    }

    @Override // com.jingdong.service.impl.IMUser, com.jingdong.service.service.UserService
    public String getA2() {
        String a2 = UserUtil.getWJLoginHelper().getA2();
        OKLog.d("bundleicssdkservice", a + "--- getPin\uff1a" + a2);
        return a2;
    }

    @Override // com.jingdong.service.impl.IMUser, com.jingdong.service.service.UserService
    public String getCookies() {
        String cookies = SafetyManager.getCookies();
        OKLog.d("bundleicssdkservice", a + "---cookies:" + cookies);
        return cookies;
    }

    @Override // com.jingdong.service.impl.IMUser, com.jingdong.service.service.UserService
    public int getDwAppID() {
        return UserUtil.getClientInfo().getDwAppID();
    }

    @Override // com.jingdong.service.impl.IMUser, com.jingdong.service.service.UserService
    public String getPin() {
        String pin = UserUtil.getWJLoginHelper().getPin();
        OKLog.d("bundleicssdkservice", a + "--- getPin\uff1a" + pin);
        return pin;
    }

    @Override // com.jingdong.service.impl.IMUser, com.jingdong.service.service.UserService
    public String getUserAppId() {
        OKLog.d("bundleicssdkservice", a + "---getUserAppId :im.customer");
        return "im.customer";
    }

    @Override // com.jingdong.service.impl.IMUser, com.jingdong.service.service.UserService
    public boolean hasLogin() {
        boolean hasLogin = UserUtil.getWJLoginHelper().hasLogin();
        OKLog.d("bundleicssdkservice", a + "--- hasLogin\uff1a" + hasLogin);
        return hasLogin;
    }

    @Override // com.jingdong.service.impl.IMUser, com.jingdong.service.service.UserService
    public String imgUrl() {
        String str = PersonalInfoManager.getInstance().getUserInfoSns().imgUrl;
        OKLog.d("bundleicssdkservice", a + "---imgUrl:" + str);
        return str;
    }

    @Override // com.jingdong.service.impl.IMUser, com.jingdong.service.service.UserService
    public String petName() {
        String str = PersonalInfoManager.getInstance().getUserInfoSns().petName;
        OKLog.d("bundleicssdkservice", a + "---petName:" + str);
        return str;
    }

    @Override // com.jingdong.service.impl.IMUser, com.jingdong.service.service.UserService
    public void requestPersonalInfo(PersonalInfoListener personalInfoListener) {
        OKLog.d("bundleicssdkservice", a + "---requestPersonalInfo");
        PersonalInfoManager.requestPersonalInfo(HttpGroupUtils.getHttpGroupaAsynPool(), new a(this, personalInfoListener));
    }
}
