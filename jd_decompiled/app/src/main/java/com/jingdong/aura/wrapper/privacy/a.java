package com.jingdong.aura.wrapper.privacy;

import android.app.ActivityManager;
import android.content.Context;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.List;

/* loaded from: classes4.dex */
public class a extends AbsAuraPrivacyInfo {
    private String a;

    public a(Context context) {
        super(context);
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraPrivacyInfoListener
    public String getOsBuild_MANUFACTURER() {
        AbsAuraPrivacyInfo.LOG.a("getOsBuild_MANUFACTURER");
        String str = this.a;
        if (str == null) {
            str = BaseInfo.getDeviceManufacture();
        }
        this.a = str;
        return str;
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraPrivacyInfoListener
    public List<ActivityManager.RunningTaskInfo> getOsRunningTaskInfo() {
        AbsAuraPrivacyInfo.LOG.a("getOsRunningTaskInfo");
        return BaseInfo.getRunningTasksWithAOP((ActivityManager) this.mContext.getSystemService("activity"), 1);
    }

    @Override // com.jingdong.aura.wrapper.privacy.AbsAuraPrivacyInfo, com.jingdong.aura.wrapper.listener.AuraPrivacyInfoListener
    public boolean isForeground() {
        return true;
    }
}
