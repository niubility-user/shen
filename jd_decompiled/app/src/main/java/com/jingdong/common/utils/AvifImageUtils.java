package com.jingdong.common.utils;

import android.text.TextUtils;
import com.jd.mobile.image.utils.AvifDecoderUtil;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;

/* loaded from: classes.dex */
public class AvifImageUtils {
    public static boolean avifBitmapConvertEnable() {
        return TextUtils.equals(JDMobileConfig.getInstance().getConfig("JDImageAvif", "avifConfig", "avifBitmapConvertEnable", "0"), "1");
    }

    public static boolean avifEnable() {
        return configAvifEnable() && configAvifAddGatewayKey() && frescoDecoderSupport();
    }

    public static boolean configAvifAddGatewayKey() {
        return TextUtils.equals(JDMobileConfig.getInstance().getConfig("JDImageAvif", "avifConfig", "avifAddGatewayKey", "0"), "1");
    }

    public static boolean configAvifEnable() {
        return TextUtils.equals(JDMobileConfig.getInstance().getConfig("JDImageAvif", "avifConfig", "avifEnable", "0"), "1");
    }

    public static boolean frescoDecoderSupport() {
        return AvifDecoderUtil.isHasAddAVIFDecoder();
    }
}
