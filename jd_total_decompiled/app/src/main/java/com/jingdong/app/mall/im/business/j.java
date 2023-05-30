package com.jingdong.app.mall.im.business;

import android.content.Context;
import android.os.Bundle;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkJShopHomeHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkSwitch;
import com.jingdong.common.login.ILogin;
import com.jingdong.common.utils.JumpMessageActivityUtil;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.service.callback.LoginListener;
import com.jingdong.service.impl.IMDeeplink;

/* loaded from: classes4.dex */
public class j extends IMDeeplink {
    private static final String a = "j";

    /* loaded from: classes4.dex */
    class a implements ILogin {
        final /* synthetic */ LoginListener a;

        a(j jVar, LoginListener loginListener) {
            this.a = loginListener;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            this.a.onSuccess(str);
        }
    }

    @Override // com.jingdong.service.impl.IMDeeplink, com.jingdong.service.service.DeeplinkService
    public void gotoJShopHome(Context context, String str, String str2, String str3) {
        OKLog.d("bundleicssdkservice", a + "---gotoJShopHome shopId\uff1a" + str + " venderId\uff1a " + str2 + " shopName: " + str3);
        try {
            Bundle build = new DeepLinkJShopHomeHelper.JShopBundleBuilder(str, str2, str3).build();
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("moduleId", (Object) "im_dongodng");
            jDJSONObject.put("entrance", (Object) "");
            build.putString("sourceInfo", jDJSONObject.toJSONString());
            DeepLinkJShopHomeHelper.gotoJShopHome(context, build);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.service.impl.IMDeeplink, com.jingdong.service.service.DeeplinkService
    public boolean isSwitchOpen(long j2) {
        boolean isSwitchOpen = DeepLinkSwitch.getInstance().isSwitchOpen(j2);
        OKLog.d("bundleicssdkservice", a + "---isSwitchOpen:" + isSwitchOpen);
        return isSwitchOpen;
    }

    @Override // com.jingdong.service.impl.IMDeeplink, com.jingdong.service.service.DeeplinkService
    public void jumpToMessageCenter(Context context) {
        OKLog.d("bundleicssdkservice", a + "---jumpToMessageCenter");
        JumpMessageActivityUtil.jumpToMessageCenter(context);
    }

    @Override // com.jingdong.service.impl.IMDeeplink, com.jingdong.service.service.DeeplinkService
    public void startLoginActivity(Context context, Bundle bundle) {
        OKLog.d("bundleicssdkservice", a + "---startLoginActivity");
        DeepLinkLoginHelper.startLoginActivity(context, bundle);
    }

    @Override // com.jingdong.service.impl.IMDeeplink, com.jingdong.service.service.DeeplinkService
    public void startWebActivity(Context context, String str) {
        OKLog.d("bundleicssdkservice", a + "---startWebActivity:" + str);
        Bundle bundle = new Bundle();
        URLParamMap uRLParamMap = new URLParamMap();
        uRLParamMap.put(RemoteMessageConst.TO, str);
        SerializableContainer serializableContainer = new SerializableContainer();
        serializableContainer.setMap(uRLParamMap);
        bundle.putSerializable("urlParamMap", serializableContainer);
        DeepLinkCommonHelper.startWebActivity(context, bundle, true);
    }

    @Override // com.jingdong.service.impl.IMDeeplink, com.jingdong.service.service.DeeplinkService
    public void startLoginActivity(Context context, Bundle bundle, LoginListener loginListener, String str) {
        OKLog.d("bundleicssdkservice", a + "---startLoginActivity with callback");
        DeepLinkLoginHelper.startLoginActivity(context, bundle, new a(this, loginListener), str);
    }
}
