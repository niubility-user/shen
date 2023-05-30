package com.jingdong.app.mall.k.j;

import com.jingdong.app.mall.R;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.jdupgrade.BaseInfoProvider;
import com.jingdong.sdk.jdupgrade.JDUpgrade;
import com.jingdong.sdk.jdupgrade.UpgradeConfig;

/* loaded from: classes4.dex */
public class b implements com.jingdong.app.mall.k.j.a {

    /* loaded from: classes4.dex */
    class a implements BaseInfoProvider {
        a(b bVar) {
        }

        @Override // com.jingdong.sdk.jdupgrade.BaseInfoProvider
        public String getAppPackageName() {
            return BaseInfo.getAppPackageName();
        }

        @Override // com.jingdong.sdk.jdupgrade.BaseInfoProvider
        public String getAppPartnerName() {
            return Configuration.getProperty(Configuration.PARTNER, "");
        }

        @Override // com.jingdong.sdk.jdupgrade.BaseInfoProvider
        public String getAppUUID() {
            return StatisticsReportUtil.readDeviceUUID();
        }

        @Override // com.jingdong.sdk.jdupgrade.BaseInfoProvider
        public String getAppUserID() {
            return UserUtil.getWJLoginHelper().getPin();
        }

        @Override // com.jingdong.sdk.jdupgrade.BaseInfoProvider
        public String getAppVersionCode() {
            return String.valueOf(BaseInfo.getAppVersionCode());
        }

        @Override // com.jingdong.sdk.jdupgrade.BaseInfoProvider
        public String getAppVersionName() {
            return BaseInfo.getAppVersionName();
        }

        @Override // com.jingdong.sdk.jdupgrade.BaseInfoProvider
        public String getDeviceBrandName() {
            return BaseInfo.getDeviceBrand();
        }

        @Override // com.jingdong.sdk.jdupgrade.BaseInfoProvider
        public String getDeviceModelName() {
            return BaseInfo.getDeviceModel();
        }

        @Override // com.jingdong.sdk.jdupgrade.BaseInfoProvider
        public String getDeviceSupportedABIs() {
            int length;
            String[] deviceSuppportedABIs = BaseInfo.getDeviceSuppportedABIs();
            if (deviceSuppportedABIs == null || (length = deviceSuppportedABIs.length) <= 0) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < length; i2++) {
                sb.append(deviceSuppportedABIs[i2]);
                if (i2 < length - 1) {
                    sb.append(", ");
                }
            }
            return sb.toString();
        }

        @Override // com.jingdong.sdk.jdupgrade.BaseInfoProvider
        public String getNetWorkType() {
            return BaseInfo.getNetworkType();
        }

        @Override // com.jingdong.sdk.jdupgrade.BaseInfoProvider
        public int getOsVersionCode() {
            return BaseInfo.getAndroidSDKVersion();
        }

        @Override // com.jingdong.sdk.jdupgrade.BaseInfoProvider
        public String getOsVersionName() {
            return BaseInfo.getAndroidVersion();
        }
    }

    @Override // com.jingdong.app.mall.k.j.a
    public boolean init() {
        JDUpgrade.init(JdSdk.getInstance().getApplication(), new UpgradeConfig.Builder(JdSdk.getInstance().getBuildConfigDebug() ? "939bf410447f47b9928b1c281a38206f" : "fba8ae5a5078417d90ae1355af234d4f", JdSdk.getInstance().getBuildConfigDebug() ? "1d3b1398a4b20f026e54a826a290000f" : "93f59362cb30881af1e91d12d948b1ee", R.drawable.jd_buy_icon).build(), new a(this));
        return true;
    }
}
