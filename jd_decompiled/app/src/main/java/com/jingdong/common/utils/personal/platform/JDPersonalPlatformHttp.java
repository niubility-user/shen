package com.jingdong.common.utils.personal.platform;

import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.utils.personal.JDPersonalStaticConfigUtils;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0013\b\u0016\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006R$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0003\u0010\u0007\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u0006\u00a8\u0006\u000f"}, d2 = {"Lcom/jingdong/common/utils/personal/platform/JDPersonalPlatformHttp;", "", "Lcom/jingdong/jdsdk/network/toolbox/HttpSetting;", "httpSetting", "", "handleLocationInfo", "(Lcom/jingdong/jdsdk/network/toolbox/HttpSetting;)V", "Lcom/jingdong/jdsdk/network/toolbox/HttpSetting;", "getHttpSetting", "()Lcom/jingdong/jdsdk/network/toolbox/HttpSetting;", "setHttpSetting", "", "menuStaticSource", "<init>", "(Ljava/lang/String;)V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class JDPersonalPlatformHttp {
    @Nullable
    private HttpSetting httpSetting;

    public /* synthetic */ JDPersonalPlatformHttp(String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "0" : str);
    }

    private final void handleLocationInfo(HttpSetting httpSetting) {
        if (httpSetting != null) {
            JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
            jDLocationCacheOption.setBusinessId(JDPersonalStaticConfigUtils.LBS_ID);
            JDLocation location = JDLocationCache.getInstance().getLocation(jDLocationCacheOption);
            String str = null;
            if (location != null) {
                if (!(location.getLng() == 0.0d && location.getLat() == 0.0d && location.getProvinceId() == 1 && location.getCityId() == 0 && location.getDistrictId() == 0 && location.getTownId() == 0)) {
                    str = String.valueOf(location.getProvinceId()) + CartConstant.KEY_YB_INFO_LINK + location.getCityId() + CartConstant.KEY_YB_INFO_LINK + location.getDistrictId() + CartConstant.KEY_YB_INFO_LINK + location.getTownId();
                }
            }
            if (str == null) {
                str = "0_0_0_0";
            }
            httpSetting.putJsonParam("locationArea", str);
        }
    }

    @Nullable
    public final HttpSetting getHttpSetting() {
        return this.httpSetting;
    }

    public final void setHttpSetting(@Nullable HttpSetting httpSetting) {
        this.httpSetting = httpSetting;
    }

    public JDPersonalPlatformHttp(@NotNull String str) {
        HttpSetting httpSetting = new HttpSetting();
        this.httpSetting = httpSetting;
        if (httpSetting != null) {
            httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.PERSONAL_HOST));
            httpSetting.setUseFastJsonParser(true);
            httpSetting.setEffect(0);
            httpSetting.setTopPriority(true);
            httpSetting.setEnableGatewayQueue(Intrinsics.areEqual("0", str));
            httpSetting.setFunctionId("personinfoBusiness");
        }
        handleLocationInfo(this.httpSetting);
    }
}
