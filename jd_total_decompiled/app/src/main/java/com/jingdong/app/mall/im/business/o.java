package com.jingdong.app.mall.im.business;

import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.service.impl.IMLocation;

/* loaded from: classes4.dex */
public class o extends IMLocation {
    private static final String a = "o";

    @Override // com.jingdong.service.impl.IMLocation, com.jingdong.service.service.LocationService
    public String getLocation() {
        StringBuilder sb = new StringBuilder();
        JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
        jDLocationCacheOption.setBusinessId("5a7c17a2b85cf73b92e2336c76803850");
        JDLocation location = JDLocationCache.getInstance().getLocation(jDLocationCacheOption);
        sb.append(location.getProvinceId());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(location.getCityId());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(location.getDistrictId());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(location.getTownId());
        OKLog.d("bundleicssdkservice", a + "---getLocation:" + sb.toString());
        return sb.toString();
    }
}
