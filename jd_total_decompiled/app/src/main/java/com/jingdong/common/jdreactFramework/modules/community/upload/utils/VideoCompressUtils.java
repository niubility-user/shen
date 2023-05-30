package com.jingdong.common.jdreactFramework.modules.community.upload.utils;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes5.dex */
public class VideoCompressUtils {
    private static final String CONFIG_IMAGE_COMPRESS_KEY = "shareorderPicMaxSize";
    private static final String CONFIG_RATE_KEY = "SOVideoCompress";
    public static final long VIDEO_BYTE_COUNT = 6291456;
    public static final int VIDEO_FRAME = 25;
    public static final String VIDEO_RATE = "2000k";
    private static final String[] VIDEO_RATES = {VIDEO_RATE, "3000k", "4000k"};
    private static final int[] IMAGE_COMPRESS = {85, 75, 65};

    public static int getImageCompFromConfig() {
        String stringFromPreference = ConfigUtil.getStringFromPreference(CONFIG_IMAGE_COMPRESS_KEY);
        if (Log.D) {
            Log.d("eva-config", "compress--image->" + stringFromPreference);
        }
        if (TextUtils.equals("3", stringFromPreference)) {
            return IMAGE_COMPRESS[2];
        }
        if (TextUtils.equals("2", stringFromPreference)) {
            return IMAGE_COMPRESS[1];
        }
        return IMAGE_COMPRESS[0];
    }

    public static String getRate(String str) {
        if (TextUtils.isEmpty(str)) {
            str = JDMobileConfig.getInstance().getConfig("JDProductdetail", CONFIG_RATE_KEY, CONFIG_RATE_KEY);
            Utils.log("eva-config", "compress--video->" + str);
        }
        return getRateFromConfig(str);
    }

    public static String getRateFromConfig(String str) {
        if (TextUtils.equals("3", str)) {
            return VIDEO_RATES[2];
        }
        if (TextUtils.equals("2", str)) {
            return VIDEO_RATES[1];
        }
        return VIDEO_RATES[0];
    }
}
