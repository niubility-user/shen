package com.jingdong.sdk.platform.lib.utils;

import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.sdk.platform.lib.openapi.OpenApiHelper;

/* loaded from: classes10.dex */
public class HostUtils {
    public static String getCartHost() {
        return getHost(HostConstants.CART_HOST);
    }

    private static String getHost(String str) {
        return OpenApiHelper.getIHostConfig() != null ? OpenApiHelper.getIHostConfig().getHost(str) : "";
    }

    public static String getNgwHost() {
        return getHost(HostConstants.NGW_HOST);
    }

    public static String getOrderHost() {
        return getHost(HostConstants.ORDER_HTTPSETTING_HOST);
    }

    public static String getPersonalHost() {
        return getHost(HostConstants.PERSONAL_HOST);
    }

    public static String getWareHost() {
        return getHost(HostConstants.WARE_HOST);
    }
}
