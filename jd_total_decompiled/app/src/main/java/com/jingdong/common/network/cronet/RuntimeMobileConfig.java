package com.jingdong.common.network.cronet;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class RuntimeMobileConfig {
    public static final String CONFIG_NAME = "JdCronet";
    public static final String KEY_ENABLE = "enable";
    public static final String KEY_ENABLE_DIALING_TASK = "enableDialingTask";
    public static final String KEY_QUIC_END_POINT = "quicEndPoint";
    public static final String KEY_RUN_TASK_INTERVAL = "runTaskInterval";
    public static final String KEY_RUN_TASK_PARAMS = "runTaskParams";
    public static final String KEY_RUN_TASK_PER_DAY = "runTaskPerDay";
    public static final String SPACE_NAME = "perfStrategy";

    public static String getQuicEndPoint() {
        return JDMobileConfig.getInstance().getConfig("JdCronet", SPACE_NAME, KEY_QUIC_END_POINT, "");
    }

    public static int getRunTaskInterval() {
        return Integer.valueOf(JDMobileConfig.getInstance().getConfig("JdCronet", SPACE_NAME, KEY_RUN_TASK_INTERVAL, "0")).intValue();
    }

    public static List<Integer> getRunTaskParams() {
        try {
            return JDJSON.parseArray(JDMobileConfig.getInstance().getConfig("JdCronet", SPACE_NAME, KEY_RUN_TASK_PARAMS, "[]"), Integer.class);
        } catch (Throwable unused) {
            return new ArrayList();
        }
    }

    public static int getRunTaskPerDay() {
        return Integer.valueOf(JDMobileConfig.getInstance().getConfig("JdCronet", SPACE_NAME, KEY_RUN_TASK_PER_DAY, "0")).intValue();
    }

    public static boolean isEnable() {
        return TextUtils.equals(JDMobileConfig.getInstance().getConfig("JdCronet", SPACE_NAME, "enable", "0"), "1");
    }

    public static boolean isEnableDialingTask() {
        return TextUtils.equals(JDMobileConfig.getInstance().getConfig("JdCronet", SPACE_NAME, KEY_ENABLE_DIALING_TASK, "0"), "1");
    }
}
