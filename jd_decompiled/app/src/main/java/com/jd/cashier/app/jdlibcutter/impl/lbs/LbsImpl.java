package com.jd.cashier.app.jdlibcutter.impl.lbs;

import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.config.IConfig;
import com.jd.cashier.app.jdlibcutter.protocol.lbs.ILbs;
import com.jingdong.common.entity.DesCommonUtils;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes13.dex */
public class LbsImpl implements ILbs {
    private JDLocationCacheOption mLbsCacheOption;

    public LbsImpl() {
        if (this.mLbsCacheOption == null) {
            this.mLbsCacheOption = new JDLocationCacheOption();
        }
        IConfig sdkConfig = DependInitializer.getSdkConfig();
        this.mLbsCacheOption.setBusinessId(sdkConfig != null ? sdkConfig.getLbsBusinessId() : "");
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.lbs.ILbs
    public String getCityId() {
        return String.valueOf(JDLocationCache.getInstance().getLocation(this.mLbsCacheOption).getCityId());
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.lbs.ILbs
    public String getDistrictId() {
        return String.valueOf(JDLocationCache.getInstance().getLocation(this.mLbsCacheOption).getDistrictId());
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.lbs.ILbs
    public String getLatitude() {
        String valueOf = String.valueOf(JDLocationCache.getInstance().getLocation(this.mLbsCacheOption).getLat());
        try {
            return DesCommonUtils.encryptThreeDESECB(valueOf, "ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8");
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
                return valueOf;
            }
            return valueOf;
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.lbs.ILbs
    public String getLongitude() {
        String valueOf = String.valueOf(JDLocationCache.getInstance().getLocation(this.mLbsCacheOption).getLng());
        try {
            return DesCommonUtils.encryptThreeDESECB(valueOf, "ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8");
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
                return valueOf;
            }
            return valueOf;
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.lbs.ILbs
    public String getProvinceId() {
        return String.valueOf(JDLocationCache.getInstance().getLocation(this.mLbsCacheOption).getProvinceId());
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.lbs.ILbs
    public String getTownId() {
        return String.valueOf(JDLocationCache.getInstance().getLocation(this.mLbsCacheOption).getTownId());
    }
}
