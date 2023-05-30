package com.jingdong.service;

import com.airbnb.lottie.L;
import com.jd.libs.jdmbridge.base.proxy.nav.INavAdapter;

/* loaded from: classes10.dex */
public enum PointServiceEnum {
    CUSTOM_CONFIG_POINT("CUSTOM_CONFIG", true),
    BASE_INFO_POINT("BASE_INFO", true),
    USER_POINT("USER", true),
    DEVICE_POINT("DEVICE", true),
    MEDIA_POINT("MEDIA", true),
    DEEPLINK_POINT("DEEPLINK", true),
    NOTIFY_POINT("NOTIFY", true),
    OPENAPP_POINT("OPENAPP", true),
    PERMISSION_POINT("PERMISSION", true),
    REMOTE_IMAGE_POINT("REMOTE_IMAGE", true),
    GATEWAY_POINT("GATEWAY", true),
    LOTTIE_POINT(L.TAG, true),
    REGEX_POINT("REGEX", true),
    SHARE_POINT(INavAdapter.BTN_TYPE_SHARE, true),
    VENDER_POINT("vender", true),
    MTA_POINT("MTA", false),
    LOCATION_POINT("LOCATION", false),
    XVIW_POINT("XVIW", false),
    UI_MODE_POINT("UI_MODE", false),
    RN_POINT("RN", false),
    EXTENSION_POINT("EXTENSION", false),
    BIOMETRIC_POINT("BIOMETRIC", false);
    
    private String name;
    private boolean necessary;

    PointServiceEnum(String str, boolean z) {
        this.name = str;
        this.necessary = z;
    }

    public String getName() {
        return this.name;
    }

    public boolean getNecessary() {
        return this.necessary;
    }

    public void setName(String str) {
        this.name = str;
    }
}
