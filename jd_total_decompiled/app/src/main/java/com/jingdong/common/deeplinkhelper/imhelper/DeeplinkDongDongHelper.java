package com.jingdong.common.deeplinkhelper.imhelper;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.deeplinkhelper.DeepLinkSwitch;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

/* loaded from: classes5.dex */
public class DeeplinkDongDongHelper {
    private static final String HOST_DONDDONG_ACTIVITY = "icsactivityshadow";
    private static final String SERVER_CONFIG_USE_JIMI_IM = "useJimiIm";
    private static DeeplinkDongDongHelper mInstance;
    private Boolean isCanUseJimiIm = null;

    private DeeplinkDongDongHelper() {
    }

    public static synchronized DeeplinkDongDongHelper getInstance() {
        DeeplinkDongDongHelper deeplinkDongDongHelper;
        synchronized (DeeplinkDongDongHelper.class) {
            if (mInstance == null) {
                mInstance = new DeeplinkDongDongHelper();
            }
            deeplinkDongDongHelper = mInstance;
        }
        return deeplinkDongDongHelper;
    }

    public boolean isCanUseDongDong() {
        if (this.isCanUseJimiIm == null) {
            this.isCanUseJimiIm = Boolean.valueOf(TextUtils.equals(SharedPreferencesUtil.getSharedPreferences().getString(SERVER_CONFIG_USE_JIMI_IM, "1"), "1"));
        }
        return this.isCanUseJimiIm.booleanValue();
    }

    public void startDongDong(Context context, Bundle bundle) {
        if (isCanUseDongDong() && context != null && DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(53))) {
            JDMtaUtils.sendCommonData(context, "start_dongdong", "aura", "DeeplinkDongDongHelper.startDongDong", (Object) null, bundle.toString(), "com.jd.lib.icssdk.ActivityShadow", "");
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_DONDDONG_ACTIVITY).toString(), bundle);
        }
    }
}
