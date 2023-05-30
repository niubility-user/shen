package com.jingdong.app.mall.aura;

import android.app.ActivityManager;
import android.content.Context;
import com.jingdong.aura.wrapper.privacy.AbsAuraPrivacyInfo;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.List;

/* loaded from: classes19.dex */
public class o extends AbsAuraPrivacyInfo {
    public o(Context context) {
        super(context);
        Log.i("PrivacyInfo", "PrivacyInfo: ");
    }

    @Override // com.jingdong.aura.wrapper.privacy.AbsAuraPrivacyInfo, com.jingdong.aura.wrapper.listener.AuraPrivacyInfoListener
    public String getOsBuild_BRAND() {
        Log.i("PrivacyInfo", "getOsBuild_BRAND: ");
        return BaseInfo.getDeviceBrand();
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraPrivacyInfoListener
    public String getOsBuild_MANUFACTURER() {
        Log.i("PrivacyInfo", "getOsBuild_MANUFACTURER: ");
        return BaseInfo.getDeviceManufacture();
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraPrivacyInfoListener
    @Deprecated
    public List<ActivityManager.RunningTaskInfo> getOsRunningTaskInfo() {
        Log.i("PrivacyInfo", "getOsRunningTaskInfo: ");
        return null;
    }

    @Override // com.jingdong.aura.wrapper.privacy.AbsAuraPrivacyInfo, com.jingdong.aura.wrapper.listener.AuraPrivacyInfoListener
    public boolean getPrivacyState() {
        boolean isAcceptPrivacy = JDPrivacyHelper.isAcceptPrivacy(this.mContext);
        Log.i("PrivacyInfo", "getPrivacyState: " + isAcceptPrivacy);
        return isAcceptPrivacy;
    }

    @Override // com.jingdong.aura.wrapper.privacy.AbsAuraPrivacyInfo, com.jingdong.aura.wrapper.listener.AuraPrivacyInfoListener
    public boolean isForeground() {
        boolean isAppForeground = BaseInfo.isAppForeground();
        Log.i("PrivacyInfo", "isForeground: " + isAppForeground);
        return isAppForeground;
    }
}
