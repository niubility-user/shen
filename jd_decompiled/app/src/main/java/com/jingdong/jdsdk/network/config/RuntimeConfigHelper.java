package com.jingdong.jdsdk.network.config;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.network.filedown.PrivacyController;
import com.jingdong.jdsdk.network.JDHttpTookit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes14.dex */
public final class RuntimeConfigHelper {
    public static final String ADVERTISE_STAT_DATA = "advertiseStatData";
    public static final String ANTI_BLOCK_SWITCH = "antiBlockSwitch";
    public static final String API_ADVANCED_MODE = "apiAdvancedMode";
    public static final String BR_COMPRESS_SWITCH = "brCompressSwitch";
    public static final String BUSINESS_CODE_NO_RETRY_LIST = "business_code_no_retry_list";
    public static final String DEFAULT_BUSINESS_CODE_NO_RETRY_LIST = "[\"-1\"]";
    public static final String DEFAULT_HTTP_CODE_NO_RETRY_LIST = "[\"403\"]";
    public static final String DOWNLOAD_ADVANCED_MODE = "downloadAdvancedMode";
    public static final String DOWNLOAD_DOMAIN_LIST = "downloadDomainList";
    private static final String DOWNLOAD_HTTPDNS_FLAG = "download_httpdns";
    private static final String DOWNLOAD_SUSPEND_WINDOW = "download_suspend_window";
    private static final String ENABLE_DOWNLOAD_SUSPEND = "enable_download_suspend";
    private static final String ENABLE_JDV = "enable_jdv";
    public static final String ENCRYPT_DOMAIN_LIST = "encryptDomainList";
    public static final String ENCRYPT_FAILED_THRESHOLD = "encryptFailedThreshold";
    public static final String ENCRYPT_TRANSMISSION = "encryptTransmission";
    public static final String HTTPS_CONFIG = "httpsDomains";
    public static final String HTTPS_SWITCH = "ishttps";
    public static final String HTTPS_SWITCH_COLOR = "ishttps_gateway";
    public static final String HTTP_CODE_NO_RETRY_LIST = "http_code_no_retry_list";
    public static final String JSON_CODE_EVENT_LISTENER = "jsonCodeEventListener";
    public static final String KEY_BUILD_IN_IP_DEGRADE = "buildInIpDegrade";
    public static final String KEY_HTTP2_PING_CONFIG = "http2pingConfig";
    public static final String KEY_HTTP2_PING_CONFIG_ENABLE = "enable";
    public static final String KEY_HTTP2_PING_CONFIG_INTERVAL = "interval";
    public static final String KEY_OKHTTP_FLAG = "okhttpFlag";
    public static final String MULTI_CALLBACK = "multiCallback";
    public static final String ONLINE_CONNECT_TIMEOUT = "connect_timeout";
    public static final String ONLINE_READ_TIMEOUT = "read_timeout";
    public static final HashSet<String> sEncryptDomainSet = new HashSet<>();
    private static boolean isForceDisableEncryptFeature = false;

    public static boolean advertiseStatDataEnable() {
        return TextUtils.equals(JDHttpTookit.getEngine().getRuntimeConfigImpl().getDataFromMobileConfig(ADVERTISE_STAT_DATA, "0"), "1");
    }

    public static boolean antiBlockSwitch() {
        return "1".equals(JDHttpTookit.getEngine().getRuntimeConfigImpl().getDataFromMobileConfig(ANTI_BLOCK_SWITCH, "1"));
    }

    public static boolean downloadUseHttpDns() {
        return TextUtils.equals(JDHttpTookit.getEngine().getRuntimeConfigImpl().getDataFromMobileConfig(DOWNLOAD_HTTPDNS_FLAG, "0"), "1");
    }

    public static boolean enableApiAdvancedMode() {
        return TextUtils.equals(JDHttpTookit.getEngine().getRuntimeConfigImpl().getDataFromMobileConfig(API_ADVANCED_MODE, "0"), "1");
    }

    public static boolean enableBrCompress() {
        return "1".equals(JDHttpTookit.getEngine().getRuntimeConfigImpl().getDataFromMobileConfig(BR_COMPRESS_SWITCH, "0"));
    }

    public static boolean enableDownloadAdvancedMode() {
        return TextUtils.equals(JDHttpTookit.getEngine().getRuntimeConfigImpl().getDataFromMobileConfig(DOWNLOAD_ADVANCED_MODE, "1"), "1");
    }

    public static boolean enableDownloadSuspend() {
        return TextUtils.equals(JDHttpTookit.getEngine().getRuntimeConfigImpl().getDataFromMobileConfig(ENABLE_DOWNLOAD_SUSPEND, "1"), "1");
    }

    public static boolean enableEncryptTransmission(String str) {
        if (!isForceDisableEncryptFeature && InternalConfiguration.encryptFailedNum.get() <= getEncryptFailedThreshold()) {
            if (JDHttpTookit.getEngine().isEnableEncryptWhiteList()) {
                String dataFromMobileConfig = JDHttpTookit.getEngine().getRuntimeConfigImpl().getDataFromMobileConfig(ENCRYPT_DOMAIN_LIST, "api.m.jd.com");
                try {
                    if (!TextUtils.isEmpty(dataFromMobileConfig)) {
                        String[] split = dataFromMobileConfig.split(DYConstants.DY_REGEX_COMMA);
                        if (split.length > 0) {
                            for (String str2 : split) {
                                HashSet<String> hashSet = sEncryptDomainSet;
                                if (!hashSet.contains(str2)) {
                                    hashSet.add(str2);
                                }
                            }
                        }
                    }
                } catch (Throwable unused) {
                }
                return TextUtils.equals(JDHttpTookit.getEngine().getRuntimeConfigImpl().getDataFromMobileConfig(ENCRYPT_TRANSMISSION, "1"), "1") && sEncryptDomainSet.contains(str);
            }
            return TextUtils.equals(JDHttpTookit.getEngine().getRuntimeConfigImpl().getDataFromMobileConfig(ENCRYPT_TRANSMISSION, "1"), "1");
        }
        return false;
    }

    public static boolean enableJdv() {
        return TextUtils.equals(JDHttpTookit.getEngine().getRuntimeConfigImpl().getDataFromMobileConfig(ENABLE_JDV, "0"), "1");
    }

    public static boolean enableJsonCodeEventListener() {
        return TextUtils.equals(JDHttpTookit.getEngine().getRuntimeConfigImpl().getDataFromMobileConfig(JSON_CODE_EVENT_LISTENER, "0"), "1");
    }

    public static void forceDisableEncryptFeature(boolean z) {
        isForceDisableEncryptFeature = z;
    }

    public static List<String> getCodeNoRetryList(String str) {
        try {
            return JDJSON.parseArray(JDHttpTookit.getEngine().getRuntimeConfigImpl().getDataFromMobileConfig(str, TextUtils.equals(str, HTTP_CODE_NO_RETRY_LIST) ? DEFAULT_HTTP_CODE_NO_RETRY_LIST : DEFAULT_BUSINESS_CODE_NO_RETRY_LIST), String.class);
        } catch (Throwable unused) {
            ArrayList arrayList = new ArrayList();
            if (TextUtils.equals(str, HTTP_CODE_NO_RETRY_LIST)) {
                arrayList.add("403");
            } else {
                arrayList.add("-1");
            }
            return arrayList;
        }
    }

    public static long getDownloadSuspendWindow() {
        long j2;
        String dataFromMobileConfig = JDHttpTookit.getEngine().getRuntimeConfigImpl().getDataFromMobileConfig(DOWNLOAD_SUSPEND_WINDOW, "");
        if (TextUtils.isEmpty(dataFromMobileConfig)) {
            return PrivacyController.DEFAULT_DOWNLOAD_WINDOW;
        }
        try {
            j2 = (int) (Float.parseFloat(dataFromMobileConfig) * 60.0f * 1000.0f);
        } catch (Throwable unused) {
            j2 = 0;
        }
        return j2 > 0 ? j2 : PrivacyController.DEFAULT_DOWNLOAD_WINDOW;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x001f A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int getEncryptFailedThreshold() {
        /*
            com.jingdong.jdsdk.network.JDHttpTookit$Engine r0 = com.jingdong.jdsdk.network.JDHttpTookit.getEngine()
            com.jingdong.jdsdk.network.dependency.IRuntimeConfig r0 = r0.getRuntimeConfigImpl()
            java.lang.String r1 = "encryptFailedThreshold"
            java.lang.String r2 = "0"
            java.lang.String r0 = r0.getDataFromMobileConfig(r1, r2)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L1b
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.Throwable -> L1b
            goto L1c
        L1b:
            r0 = 0
        L1c:
            if (r0 <= 0) goto L1f
            goto L20
        L1f:
            r0 = 3
        L20:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdsdk.network.config.RuntimeConfigHelper.getEncryptFailedThreshold():int");
    }

    public static int getOnlineConnectTimeout() {
        return Integer.valueOf(JDHttpTookit.getEngine().getRuntimeConfigImpl().getDataFromMobileConfig(ONLINE_CONNECT_TIMEOUT, "0")).intValue();
    }

    public static int getOnlineReadTimeout() {
        return Integer.valueOf(JDHttpTookit.getEngine().getRuntimeConfigImpl().getDataFromMobileConfig(ONLINE_READ_TIMEOUT, "0")).intValue();
    }

    public static boolean isSupportDownloadAdvancedMode(String str) {
        String dataFromMobileConfig = JDHttpTookit.getEngine().getRuntimeConfigImpl().getDataFromMobileConfig(DOWNLOAD_DOMAIN_LIST, "");
        if (TextUtils.isEmpty(dataFromMobileConfig)) {
            return false;
        }
        List parseArray = JDJSON.parseArray(dataFromMobileConfig, String.class);
        if (str == null || str.isEmpty()) {
            return false;
        }
        return parseArray.contains(str);
    }

    public static boolean isUseHttps() {
        String dataFromMobileConfig = JDHttpTookit.getEngine().getRuntimeConfigImpl().getDataFromMobileConfig(HTTPS_SWITCH, "1");
        return TextUtils.isEmpty(dataFromMobileConfig) || TextUtils.equals("1", dataFromMobileConfig);
    }

    public static boolean isUseHttpsForUniformRequest() {
        String dataFromMobileConfig = JDHttpTookit.getEngine().getRuntimeConfigImpl().getDataFromMobileConfig(HTTPS_SWITCH_COLOR, "1");
        return TextUtils.isEmpty(dataFromMobileConfig) || TextUtils.equals("1", dataFromMobileConfig);
    }

    public static boolean isUseOkhttp() {
        return true;
    }

    public static boolean multiCallbackEnable() {
        return TextUtils.equals(JDHttpTookit.getEngine().getRuntimeConfigImpl().getDataFromMobileConfig(MULTI_CALLBACK, "0"), "1");
    }
}
