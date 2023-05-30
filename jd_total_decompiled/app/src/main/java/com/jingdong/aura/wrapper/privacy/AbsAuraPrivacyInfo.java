package com.jingdong.aura.wrapper.privacy;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import com.jingdong.aura.core.util.l.b;
import com.jingdong.aura.core.util.l.c;
import com.jingdong.aura.wrapper.listener.AuraPrivacyInfoListener;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes4.dex */
public abstract class AbsAuraPrivacyInfo implements AuraPrivacyInfoListener {
    protected static b LOG = c.a("AbsAuraPrivacyInfo");
    protected String brand;
    protected String cpu_abi;
    protected String hardware;
    protected Context mContext;
    protected String[] supported_32_bit_abis;

    public AbsAuraPrivacyInfo(Context context) {
        this.mContext = context;
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraPrivacyInfoListener
    public String getOsBuild_BRAND() {
        LOG.a("getOsBuild_BRAND");
        String str = this.brand;
        if (str == null) {
            str = BaseInfo.getDeviceBrand();
        }
        this.brand = str;
        return str;
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraPrivacyInfoListener
    public String getOsBuild_CPU_ABI() {
        LOG.a("getOsBuild_CPU_ABI");
        String str = this.cpu_abi;
        if (str == null) {
            str = Build.CPU_ABI;
        }
        this.cpu_abi = str;
        return str;
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraPrivacyInfoListener
    public String getOsBuild_HARDWARE() {
        LOG.a("getOsBuild_HARDWARE");
        String str = this.hardware;
        if (str == null) {
            str = Build.HARDWARE;
        }
        this.hardware = str;
        return str;
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraPrivacyInfoListener
    @TargetApi(21)
    public String[] getOsBuild_SUPPORTED_32_BIT_ABIS() {
        LOG.a("getOsBuild_SUPPORTED_32_BIT_ABIS");
        String[] strArr = this.supported_32_bit_abis;
        if (strArr == null) {
            strArr = Build.SUPPORTED_32_BIT_ABIS;
        }
        this.supported_32_bit_abis = strArr;
        return strArr;
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraPrivacyInfoListener
    public Configuration getOsConfiguration(Resources resources) {
        LOG.a("getOsConfiguration");
        return resources.getConfiguration();
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraPrivacyInfoListener
    public DisplayMetrics getOsDisplayMetrics(Resources resources) {
        LOG.a("getOsDisplayMetrics");
        return resources.getDisplayMetrics();
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraPrivacyInfoListener
    public boolean getPrivacyState() {
        LOG.a("getPrivacyState");
        return true;
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraPrivacyInfoListener
    public boolean isForeground() {
        LOG.a("isForeground");
        return true;
    }
}
