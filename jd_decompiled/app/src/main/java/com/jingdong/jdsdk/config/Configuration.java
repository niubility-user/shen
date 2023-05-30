package com.jingdong.jdsdk.config;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.BaseApplication;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import com.jingdong.sdk.jdhttpdns.pojo.IpModelOld;
import com.jingdong.sdk.oklog.OKLog;
import com.meituan.android.walle.WalleChannelReader;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* loaded from: classes.dex */
public class Configuration {
    public static final String ALARM_NEED_WAKE_UP = "alarmNeedWakeUp";
    public static final String APPLICATION_SHORTCUT = "applicationShortcut";
    public static final String APPLICATION_UPGRADE = "applicationUpgrade";
    public static final String APP_START_COUNT = "appStartCount";
    public static final String ATTEMPTS = "attempts";
    public static final String ATTEMPTS_TIME = "attemptsTime";
    public static final String BARCODE_SCAN = "barCodeScan";
    public static final String BEFORE_INIT_TIP = "beforeInitTip";
    public static final String BROADCAST_FROM_CUTEVENT = "com.jingdong.app.mall.cutevent";
    public static final String BROADCAST_FROM_WXLOGIN = "com.jingdong.app.mall.wxloginreceiver";
    public static final String CASHIERDESK_BETA_HOST = "paybeta.m.jd.com";
    public static final String CASHIERDESK_BETA_M_HOST = "beta-mpay.m.jd.com";
    public static final String CASHIERDESK_FINISH_BETA_HOST = "beta-finish.m.jd.com";
    public static final String CASHIERDESK_FINISH_HOST = "payfinish.m.jd.com";
    public static final String CASHIERDESK_HOST = "pay.m.jd.com";
    public static final String CASHIERDESK_M_HOST = "mpay.m.jd.com";
    public static final String CASHIERDESK_NEW_BETA_HOST = "beta-cashier.m.jd.com";
    public static final String CASHIERDESK_NEW_HOST = "cashier.m.jd.com";
    public static final String CASHIER_NATIVE_HOST = "cashier_native_host";
    public static final String CBTMODE = "CBTMode";
    public static final String CDN_HOST = "cndHost";
    public static final String CHECK_NEW_VERSION = "checkNewVersion";
    public static final String CLIENT = "client";
    public static final String COMMUNITY_HOST = "go.m.jd.com";
    public static final String CONNECT_TIMEOUT = "connectTimeout";
    public static final String CONNECT_TIMEOUT_FOR_2G = "connectTimeoutFor2G";
    public static final String CONNECT_TIMEOUT_FOR_WIFI = "connectTimeoutForWIFI";
    public static final String COST_HINT = "costHint";
    public static final String CURRENT_APK_COMMENT = "currentApkComment";
    public static final String CURRENT_APK_MD5 = "currentApkMD5";
    public static final String CURRENT_APK_SIZE = "currentApkSize";
    public static final String DEFAULT_FIRST_GET_TOKEN_DELAY = "defaultFirstGetTokenDelay";
    public static final String DEFAULT_PUSH_MESSAGE_ALARM_DELAY = "defaultPushMessageAlarmDelay";
    public static final String DISCUSSUPLOADIMAGE_HEIGHT = "discussUploadImageHeight";
    public static final String DISCUSSUPLOADIMAGE_QUALITY = "discussUploadImageQuality";
    public static final String DISCUSSUPLOADIMAGE_WIDTH = "discussUploadImageWidth";
    public static final String DYNAMIC_SDK_FORCE_LOCAL = "\u515c\u5e95\u6a21\u677f\u6d4b\u8bd5\u914d\u7f6e";
    public static final String DYNAMIC_SDK_HOST = "dynamic_sdk_host";
    public static final int D_MODEL_SPLIT_LEN = 25;
    public static final String HAS_INIT_TIP = "hasInitTip";
    public static final String HOST = "host";
    public static final String HYBRID_HOST = "hybrid_host";
    public static final String INIT_FIRST_POOL_SIZE = "initFirstPoolSize";
    public static final String INIT_SECOND_POOL_SIZE = "initSecondPoolSize";
    public static final String INIT_THIRD_POOL_SIZE = "initThirdPoolSize";
    public static final String IS_COOLMART = "isCoolMart";
    public static final String JDPAY_SDK_HOST = "jdpay_sdk_host";
    public static final String JDPAY_SDK_HOST_CERT = "jdpay_sdk_host_cert";
    public static final String JDPAY_SDK_HOST_CERT_PRE1 = "http://cert-pre1.jd.com/";
    public static final String JDPAY_SDK_HOST_CERT_RELEASE = "https://jdpaycert.jd.com/";
    public static final String JDPAY_SDK_HOST_CERT_TEST = "http://10.222.133.116/";
    public static final String JDPAY_SDK_HOST_MGATE = "jdpay_sdk_host_mgate";
    public static final String JDPAY_SDK_HOST_MGATE_PRE1 = "http://mgate-pre1.jd.com/";
    public static final String JDPAY_SDK_HOST_MGATE_RELEASE = "https://mgate.jd.com/";
    public static final String JDPAY_SDK_HOST_MGATE_TEST = "http://10.222.132.234/";
    public static final String JDPAY_SDK_HOST_PRE1 = "http://sdk-pre1.jd.com/";
    public static final String JDPAY_SDK_HOST_PRE2 = "http://sdk-pre2.jd.com/";
    public static final String JDPAY_SDK_HOST_PRE3 = "http://sdk-pre3.jd.com/";
    public static final String JDPAY_SDK_HOST_RELEASE = "https://jdpaysdk.jd.com/";
    public static final String JDPAY_SDK_HOST_TEST = "http://10.222.133.60/";
    public static final String JLOG_VERSION = "JLogVersion";
    public static final String KEY_ABTEST_THRESHOLD = "keyAbTestBackFunctionIdAccessThresholdMS";
    public static final String KEY_DEVICE_THRESHOLD = "keyDeviceAccessThresholdMS";
    public static final String KEY_LAST_ACCESS_ABTEST = "keyLastAccessAbTestBackFunctionId";
    public static final String KEY_LAST_ACCESS_DEVICE = "keyLastAccessDevice";
    public static final String KEY_LAST_ACCESS_SERVERCONFIG = "keyLastAccessServerConfig";
    public static final String KEY_LAST_ACCESS_START = "keyLastAccessStart";
    public static final String KEY_LAST_ACCESS_VERSION = "keyLastAccessVersion";
    public static final String KEY_LAST_UPDATE = "keyLastUpdate";
    public static final String KEY_SERVERCONFIG_THRESHOLD = "keyServerConfigAccessThresholdMS";
    public static final String KEY_START_THRESHOLD = "keyAbTestBackFunctionIdAccessThresholdMS";
    public static final String KEY_TIME_STAMP = "keyTimestamp";
    public static final String KEY_UPDATE_INSTALL_NUM = "keyLastInstallShowNum";
    public static final String KEY_UPDATE_INTERVAL = "keyUpdateInterval";
    public static final String KEY_UPDATE_LIMIT = "keyLastShowLimit";
    public static final String KEY_UPDATE_NUM = "keyLastShowNum";
    public static final String KEY_UPDATE_WEB = "keyUpdateWeb";
    public static final String KEY_VERSION_THRESHOLD = "keyVersionAccessThresholdMS";
    public static final String LEAVE_TIME_GAP = "leaveTimeGap";
    public static final String LOCAL_FILE_CACHE = "localFileCache";
    public static final String LOCAL_MEMORY_CACHE = "localMemoryCache";
    public static final String LOCATION_TIP = "locationTip";
    public static final String MAX_FIRST_POOL_SIZE = "maxFirstPoolSize";
    public static final String MAX_SECOND_POOL_SIZE = "maxSecondPoolSize";
    public static final String MAX_THIRD_POOL_SIZE = "maxThirdPoolSize";
    public static final String MIAppId = "2882303761517506461";
    public static final String MIAppKey = "5601750626461";
    public static final String MSG_BOOT_COMPLETED = "msgBootCompleted";
    public static final String MSG_CENTER_HOST = "jpns.m.jd.com";
    public static final String MSG_CENTER_HOST_BETA = "jpns.m.jd.care";
    public static final String MSG_CENTER_HTTPS_HOST_BETA = "beta-jpns.m.jd.com";
    public static final String MSG_HOST = "msgHost";
    public static final String MSG_SET_SWITCH = "msgSwitch";
    public static final String MUST_USE_WIFI_MAC = "mustUseWifiMac";
    public static final String MZAppId = "110198";
    public static final String MZAppKey = "d9b0957b105346fd9c521e65b9af8d25";
    public static final String M_HOST = "mHost";
    public static final String NEW_MSG_CENTER_HOST = "msg.m.jd.com";
    public static final String NEW_MSG_CENTER_HOST_BETA = "beta-msg.m.jd.com";
    public static final String NEW_MSG_CENTER_REDPOINT_HOST = "redpoint-msg.m.jd.com";
    public static final String NEW_MSG_CENTER_REDPOINT_HOST_BETA = "beta-redpoint-msg.m.jd.com";
    public static final String OPTIMIZE_COMBINE_API = "optCombineAPI";
    public static final String ORDER_HOST = "orderHost";
    public static final String OTHER_APP = "otherApp";
    public static final String PAI_HOST = "paiHost";
    public static final String PARTNER = "partner";
    public static final String PAY_APP_ID = "jd_android_app4";
    public static final String PAY_APP_ID_BETA = "android_app_beta";
    public static final String PAY_APP_KEY = "e53jfgRgd7Hk";
    public static final String PAY_APP_KEY_BETA = "6fg7weDfF6gh";
    public static final String PAY_URL_ADDRESS = "http://pay.m.jd.com/index.action";
    public static final String PAY_URL_ADDRESS_BETA = "http://paybeta.m.jd.com/index.action";
    public static final String PAY_URL_ADDRESS_CARE = "http://pay.m.jd.care/index.action";
    public static final String PAY_URL_ADDRESS_NEW = "http://cashier.m.jd.com/index.action";
    public static final String PAY_URL_ADDRESS_NEW_BETA = "http://beta-cashier.m.jd.com/index.action";
    public static final String PAY_URL_ADDRESS_TEST = "http://paytest.m.jd.com/index.action";
    public static final String PERSONAL_CONFIG_HOST = "cc.m.jd.com";
    public static final String PHOTOBUY_BETA_HOST = "photobuy.jd.care";
    public static final String PHOTOBUY_HOST = "photobuy.jd.local";
    public static final String PHOTO_SHUT = "photoShut";
    public static final String PLUG_HOST = "plugHost";
    public static final String PRINT_JLOG = "useJLog";
    public static final String PUSH_MSG_MODE = "msgMode";
    public static final String READ_TIMEOUT = "readTimeout";
    public static final String READ_TIMEOUT_FOR_WIFI = "readTimeoutForWIFI";
    public static final String REQUEST_METHOD = "requestMethod";
    public static final String ROUTINE_CHECK_DELAY_TIME = "routineCheckDelayTime";
    public static final String SECKILL_HOST = "seckillsoatest.jd.local";
    public static final String SETTLEMENT_GENAPPPAYID_PAY_URL_ADDRESS_BETA = "http://paybeta.m.jd.com/index.action";
    public static final String SETTLEMENT_PAY_URL_ADDRESS = "http://pay.m.jd.com/pay/indexForPre.action";
    public static final String SETTLEMENT_PAY_URL_ADDRESS_BETA = "http://paybeta.m.jd.com/pay/indexForPre.action";
    public static final String SLEF_BROADCAST_PERMISSION = "com.jingdong.app.mall.permission.self_broadcast";
    public static final String SPARSE_IMG_SWITCH = "sparseImgSwitch";
    public static final String SUB_UNION_ID = "subunionId";
    public static final String TEST_MODE = "testMode";
    public static final String TEST_RELEASE_HOST_MODE = "releaseHostMode";
    public static final String TOKEN_HOST = "tokenHost";
    public static final String UNIFORM_GLOBAL_HOST_BETA = "beta-api.jd.co.th";
    public static final String UNIFORM_GLOBAL_HOST_INTRA_BETA = "beta-intra.jd.co.th";
    public static final String UNIFORM_HOST = "api.m.jd.com";
    public static final String UNIFORM_HOST_BETA = "api.m.jd.care";
    public static final String UNIFORM_HOST_HTTPS_BETA = "beta-api.m.jd.com";
    public static final String UNIFORM_HOST_TEST = "android.m.jd.care/service";
    public static final String UNION_ID = "unionId";
    public static final String UPDATE_DELAY = "updateDelay";
    public static final String VOICE_SEARCH_SHUT = "voiseSearchShut";
    public static final boolean WX_CHECK_SIG = true;
    private static Map<String, String> localProperties;
    private static Properties properties;
    private static Map<String, String> v2Cache;

    public static String getBarcodeHost() {
        return HostConfig.getInstance().getHost(HostConstants.BARCODE_HOST);
    }

    public static Boolean getBooleanProperty(String str) {
        return getBooleanProperty(str, null);
    }

    public static String getCartHost() {
        return HostConfig.getInstance().getHost(HostConstants.CART_HOST);
    }

    public static String getCashierNativeHost() {
        return HostConfig.getInstance().getHost(CASHIER_NATIVE_HOST);
    }

    public static String getCommentHost() {
        return HostConfig.getInstance().getHost(HostConstants.COMMENT_HOST);
    }

    public static String getCommonNewHost() {
        return HostConfig.getInstance().getHost(HostConstants.COMMON_NEW_HOST);
    }

    public static String getExtraProperty(String str, String str2) {
        String str3;
        if (v2Cache == null) {
            v2Cache = new HashMap();
        }
        if (v2Cache.containsKey(str)) {
            return v2Cache.get(str);
        }
        try {
            if (TextUtils.equals(str, PARTNER)) {
                str3 = WalleChannelReader.getChannel(BaseApplication.getInstance(), str2);
            } else {
                str3 = WalleChannelReader.get(BaseApplication.getInstance(), str);
            }
        } catch (Throwable unused) {
            str3 = null;
        }
        if (str3 != null) {
            str2 = str3;
        }
        v2Cache.put(str, str2);
        if (OKLog.D) {
            OKLog.d("Configuration", "getExtraProperty, key=" + str + ",result=" + str2);
        }
        return str2;
    }

    public static String getHybridHost() {
        return HostConfig.getInstance().getHost(HYBRID_HOST);
    }

    public static Integer getIntegerProperty(String str) {
        return getIntegerProperty(str, null);
    }

    public static String getJDTravelHost() {
        return HostConfig.getInstance().getHost(HostConstants.JDTRAVEL_HOST);
    }

    public static String getJdpaySdkCertHost() {
        return HostConfig.getInstance().getHost(JDPAY_SDK_HOST_CERT);
    }

    public static String getJdpaySdkHost() {
        return HostConfig.getInstance().getHost(JDPAY_SDK_HOST);
    }

    public static String getJdpaySdkMgateHost() {
        return HostConfig.getInstance().getHost(JDPAY_SDK_HOST_MGATE);
    }

    public static String getJshopHost() {
        return HostConfig.getInstance().getHost(HostConstants.JSHOP_HOST);
    }

    public static Long getLongProperty(String str) {
        return getLongProperty(str, null);
    }

    public static String getNewMsgCenterHost() {
        return HostConfig.getInstance().getHost(HostConstants.NEW_MSG_CENTER_HOST);
    }

    public static String getNewShareHost() {
        return HostConfig.getInstance().getHost(HostConstants.NEW_SHARE_HOST);
    }

    public static String getNgwHost() {
        return HostConfig.getInstance().getHost(HostConstants.NGW_HOST);
    }

    public static String getOrderCenterHost() {
        return HostConfig.getInstance().getHost(HostConstants.ORDER_CENTER_HOST);
    }

    public static String getPayUrl() {
        return HostConfig.getInstance().getHost("payUrl");
    }

    public static String getPersonalHomeHost() {
        return HostConfig.getInstance().getHost(HostConstants.PERSONAL_HOME_HOST);
    }

    public static String getPersonalHost() {
        return HostConfig.getInstance().getHost(HostConstants.PERSONAL_HOST);
    }

    public static String getPortalHost() {
        return HostConfig.getInstance().getHost(HostConstants.PORTAL_HOST);
    }

    public static Properties getProperties() {
        init();
        return properties;
    }

    public static String getProperty(String str) {
        return getProperty(str, null);
    }

    public static String getSearchHost() {
        return HostConfig.getInstance().getHost(HostConstants.SEARCH_HOST);
    }

    public static String getSettlementGenAppPayIdPayUrl() {
        return HostConfig.getInstance().getHost(HostConstants.SETTLEMENT_GENAPPPAYID_PAY_URL);
    }

    public static String getSettlementPayUrl() {
        return HostConfig.getInstance().getHost(HostConstants.SETTLEMENT_PAY_URL);
    }

    public static String getStoryHost() {
        return HostConfig.getInstance().getHost(HostConstants.STORY_HOST);
    }

    public static String getVirtualHost() {
        return HostConfig.getInstance().getHost(HostConstants.VIRTUAL_HOST);
    }

    public static String getWareHost() {
        return HostConfig.getInstance().getHost(HostConstants.WARE_HOST);
    }

    public static String getWmpHost() {
        return HostConfig.getInstance().getHost(HostConstants.WMP_HOST);
    }

    private static synchronized void init() {
        synchronized (Configuration.class) {
            if (localProperties != null) {
                return;
            }
            HashMap hashMap = new HashMap();
            localProperties = hashMap;
            hashMap.put(ORDER_HOST, "api.m.jd.com");
            localProperties.put(PAI_HOST, "jdimg.m.jd.com");
            localProperties.put("host", "api.m.jd.com");
            localProperties.put(M_HOST, null);
            localProperties.put(PLUG_HOST, "jdmps.m.jd.com");
            localProperties.put(TOKEN_HOST, "api.m.jd.com");
            localProperties.put(MSG_HOST, MSG_CENTER_HOST);
            localProperties.put(CDN_HOST, "cdngw.m.jd.com");
            localProperties.put("connectTimeout", "10000");
            localProperties.put("connectTimeoutFor2G", "20000");
            localProperties.put("connectTimeoutForWIFI", "10000");
            localProperties.put("readTimeout", "15000");
            localProperties.put("readTimeoutForWIFI", "10000");
            localProperties.put("attempts", "3");
            localProperties.put("attemptsTime", "0");
            localProperties.put("requestMethod", IMantoServerRequester.POST);
            localProperties.put(LOCAL_MEMORY_CACHE, DYConstants.DY_FALSE);
            localProperties.put(LOCAL_FILE_CACHE, DYConstants.DY_FALSE);
            localProperties.put(BEFORE_INIT_TIP, DYConstants.DY_FALSE);
            localProperties.put(MSG_BOOT_COMPLETED, DYConstants.DY_TRUE);
            localProperties.put(MSG_SET_SWITCH, DYConstants.DY_TRUE);
            localProperties.put(LOCATION_TIP, DYConstants.DY_FALSE);
            localProperties.put(SPARSE_IMG_SWITCH, DYConstants.DY_TRUE);
            localProperties.put(INIT_FIRST_POOL_SIZE, "3");
            localProperties.put(MAX_FIRST_POOL_SIZE, "3");
            localProperties.put(INIT_SECOND_POOL_SIZE, "0");
            localProperties.put(MAX_SECOND_POOL_SIZE, "0");
            localProperties.put(INIT_THIRD_POOL_SIZE, "1");
            localProperties.put(MAX_THIRD_POOL_SIZE, "1");
            localProperties.put(DISCUSSUPLOADIMAGE_WIDTH, "500");
            localProperties.put(DISCUSSUPLOADIMAGE_HEIGHT, "500");
            localProperties.put(DISCUSSUPLOADIMAGE_QUALITY, IpModelOld.PORT_HTTP);
            localProperties.put(ROUTINE_CHECK_DELAY_TIME, "2000");
            localProperties.put(LEAVE_TIME_GAP, "86400000");
            localProperties.put(DEFAULT_FIRST_GET_TOKEN_DELAY, "180000");
            localProperties.put(DEFAULT_PUSH_MESSAGE_ALARM_DELAY, "600000");
            localProperties.put("testMode", DYConstants.DY_FALSE);
            localProperties.put(PRINT_JLOG, DYConstants.DY_TRUE);
            localProperties.put(JLOG_VERSION, "2");
            localProperties.put(CBTMODE, DYConstants.DY_FALSE);
            localProperties.put(CHECK_NEW_VERSION, DYConstants.DY_TRUE);
            localProperties.put(OTHER_APP, DYConstants.DY_TRUE);
            localProperties.put(PUSH_MSG_MODE, DYConstants.DY_TRUE);
            localProperties.put(UPDATE_DELAY, "0");
            localProperties.put("client", "android");
            localProperties.put(APPLICATION_UPGRADE, DYConstants.DY_TRUE);
            localProperties.put(APPLICATION_SHORTCUT, DYConstants.DY_TRUE);
            localProperties.put(COST_HINT, DYConstants.DY_FALSE);
            localProperties.put(BARCODE_SCAN, DYConstants.DY_TRUE);
            localProperties.put(PHOTO_SHUT, DYConstants.DY_FALSE);
            localProperties.put(VOICE_SEARCH_SHUT, DYConstants.DY_FALSE);
            localProperties.put(IS_COOLMART, DYConstants.DY_FALSE);
            localProperties.put(KEY_DEVICE_THRESHOLD, Long.toString(86400000L));
            localProperties.put(KEY_SERVERCONFIG_THRESHOLD, Long.toString(86400000L));
            localProperties.put(KEY_VERSION_THRESHOLD, Long.toString(86400000L));
            localProperties.put("keyAbTestBackFunctionIdAccessThresholdMS", Long.toString(86400000L));
            localProperties.put("keyAbTestBackFunctionIdAccessThresholdMS", Long.toString(1800000L));
            localProperties.put(MUST_USE_WIFI_MAC, DYConstants.DY_FALSE);
            try {
                properties = new Properties();
                InputStream open = JdSdk.getInstance().getApplicationContext().getAssets().open("config.properties");
                if (open != null) {
                    properties.load(open);
                }
            } catch (Exception unused) {
            }
            initHostConfig();
        }
    }

    private static void initHostConfig() {
        HostConfig.getInstance().putHost(HostConstants.NGW_HOST, new HostConfig.HostModel("api.m.jd.care", "api.m.jd.com", "beta-api.m.jd.com", UNIFORM_HOST_TEST));
        HostConfig.getInstance().putHost(HostConstants.PERSONAL_HOST, new HostConfig.HostModel("api.m.jd.care", "api.m.jd.com", "beta-api.m.jd.com", UNIFORM_HOST_TEST, "mp1.m.jd.care", "mp2.m.jd.care", "mp3.m.jd.care", "mp4.m.jd.care", "beta-api2.m.jd.com"));
        HostConfig.getInstance().putHost(HostConstants.PERSONAL_HOME_HOST, new HostConfig.HostModel("beta-cc.m.jd.com", PERSONAL_CONFIG_HOST, "cc.m.jd.care", UNIFORM_HOST_TEST));
        HostConfig.getInstance().putHost(HostConstants.CART_HOST, new HostConfig.HostModel("api.m.jd.care", "api.m.jd.com", "beta-api.m.jd.com", UNIFORM_HOST_TEST, "mp.m.jd.care", "mp1.m.jd.care", "mp2.m.jd.care", "mp3.m.jd.care", "mp4.m.jd.care", "mp5.m.jd.care", "mp6.m.jd.care", "mp7.m.jd.care", "mp8.m.jd.care", "mp9.m.jd.care", "mp10.m.jd.care", "mp11.m.jd.care", "mp12.m.jd.care", "beta-sq-api.m.jd.com"));
        HostConfig.getInstance().putHost(HostConstants.SEARCH_HOST, new HostConfig.HostModel("api.m.jd.care", "api.m.jd.com", "beta-api.m.jd.com", UNIFORM_HOST_TEST, "mp.m.jd.care", "mp1.m.jd.care", "mp2.m.jd.care", "mp3.m.jd.care", "mp4.m.jd.care", "mp5.m.jd.care", "mp6.m.jd.care", "mp7.m.jd.care", "mp8.m.jd.care", "mp9.m.jd.care", "mp10.m.jd.care", "mp11.m.jd.care", "mp12.m.jd.care"));
        HostConfig.getInstance().putHost(HostConstants.WARE_HOST, new HostConfig.HostModel("api.m.jd.care", "api.m.jd.com", "beta-api.m.jd.com", UNIFORM_HOST_TEST));
        HostConfig.getInstance().putHost(HostConstants.JSHOP_HOST, new HostConfig.HostModel("api.m.jd.care", "api.m.jd.com", "beta-api.m.jd.com", UNIFORM_HOST_TEST));
        HostConfig.getInstance().putHost("payUrl", new HostConfig.HostModel("http://paybeta.m.jd.com/index.action", PAY_URL_ADDRESS, PAY_URL_ADDRESS_NEW_BETA, PAY_URL_ADDRESS_NEW));
        HostConfig.getInstance().putHost(HostConstants.PAY_URL_TEST, new HostConfig.HostModel(PAY_URL_ADDRESS_TEST, PAY_URL_ADDRESS, PAY_URL_ADDRESS_NEW_BETA, PAY_URL_ADDRESS_NEW));
        HostConfig.getInstance().putHost(HostConstants.SETTLEMENT_GENAPPPAYID_PAY_URL, new HostConfig.HostModel("http://paybeta.m.jd.com/index.action", PAY_URL_ADDRESS, PAY_URL_ADDRESS_NEW_BETA, PAY_URL_ADDRESS_NEW));
        HostConfig.getInstance().putHost(HostConstants.SETTLEMENT_PAY_URL, new HostConfig.HostModel(SETTLEMENT_PAY_URL_ADDRESS_BETA, SETTLEMENT_PAY_URL_ADDRESS, PAY_URL_ADDRESS_NEW_BETA, PAY_URL_ADDRESS_NEW));
        HostConfig.getInstance().putHost(HostConstants.JDTRAVEL_HOST, new HostConfig.HostModel("api.m.jd.care", "api.m.jd.com", "beta-api.m.jd.com"));
        HostConfig.getInstance().putHost(HostConstants.VIRTUAL_HOST, new HostConfig.HostModel("api.m.jd.care", "api.m.jd.com", "beta-api.m.jd.com"));
        HostConfig.getInstance().putHost(HostConstants.PORTAL_HOST, new HostConfig.HostModel("api.m.jd.care", "api.m.jd.com", "beta-api.m.jd.com", SECKILL_HOST));
        HostConfig.getInstance().putHost(HostConstants.BARCODE_HOST, new HostConfig.HostModel("api.m.jd.care", "api.m.jd.com", "beta-api.m.jd.com", UNIFORM_HOST_TEST));
        HostConfig.getInstance().putHost(HostConstants.ORDER_HTTPSETTING_HOST, new HostConfig.HostModel("api.m.jd.care", "api.m.jd.com", "beta-api.m.jd.com", UNIFORM_HOST_TEST, "mp.m.jd.care", "mp1.m.jd.care", "mp2.m.jd.care", "mp3.m.jd.care", "mp4.m.jd.care"));
        HostConfig.getInstance().putHost(HostConstants.MSG_CENTER_HOST, new HostConfig.HostModel(MSG_CENTER_HOST_BETA, MSG_CENTER_HOST, MSG_CENTER_HTTPS_HOST_BETA));
        HostConfig.getInstance().putHost(HostConstants.NEW_MSG_CENTER_HOST, new HostConfig.HostModel(NEW_MSG_CENTER_HOST_BETA, NEW_MSG_CENTER_HOST));
        HostConfig.getInstance().putHost(NEW_MSG_CENTER_REDPOINT_HOST, new HostConfig.HostModel(NEW_MSG_CENTER_REDPOINT_HOST_BETA, NEW_MSG_CENTER_REDPOINT_HOST));
        HostConfig.getInstance().putHost(HostConstants.COMMENT_HOST, new HostConfig.HostModel("api.m.jd.care", "api.m.jd.com", "beta-api.m.jd.com", UNIFORM_HOST_TEST));
        HostConfig.getInstance().putHost(HostConstants.COMMENT_IMG_UPLOAD_HOST, new HostConfig.HostModel("go.m.jd.care", COMMUNITY_HOST, "beta-go.m.jd.com", UNIFORM_HOST_TEST));
        HostConfig.getInstance().putHost(HostConstants.PHOTO_BUY_HOST, new HostConfig.HostModel(PHOTOBUY_BETA_HOST, PHOTOBUY_HOST));
        HostConfig.getInstance().putHost(HostConstants.COMMON_NEW_HOST, new HostConfig.HostModel("api.m.jd.care", "api.m.jd.com", "beta-api.m.jd.com", UNIFORM_HOST_TEST));
        HostConfig.getInstance().putHost(HostConstants.MP_SEARCH_HOST, new HostConfig.HostModel("mp.m.jd.care", "api.m.jd.com"));
        HostConfig.getInstance().putHost(CASHIER_NATIVE_HOST, new HostConfig.HostModel("api.m.jd.care", "api.m.jd.com", "beta-api.m.jd.com", UNIFORM_HOST_TEST, "beta-api2.m.jd.com", "beta-api3.m.jd.com", "beta-api4.m.jd.com", "mp.m.jd.care", "mp1.m.jd.care", "mp2.m.jd.care", "mp3.m.jd.care", "mp4.m.jd.care", "mp5.m.jd.care", "mp6.m.jd.care", "mp7.m.jd.care", "mp8.m.jd.care", "mp9.m.jd.care", "mp10.m.jd.care"));
        HostConfig.getInstance().putHost(HostConstants.ORDER_CENTER_HOST, new HostConfig.HostModel("api.m.jd.care", "api.m.jd.com", "beta-api.m.jd.com", UNIFORM_HOST_TEST, "mp.m.jd.care", "mp1.m.jd.care", "mp2.m.jd.care", "iordersoa.m.jd.care", "mp3.m.jd.care", "mp4.m.jd.care", "mp5.m.jd.care", "mp6.m.jd.care", "mp7.m.jd.care", "mp8.m.jd.care", "mp9.m.jd.care", "mp10.m.jd.care"));
        HostConfig.getInstance().putHost(HostConstants.STORY_HOST, new HostConfig.HostModel("beta-go.m.jd.com", COMMUNITY_HOST));
        HostConfig.getInstance().putHost(HostConstants.WMP_HOST, new HostConfig.HostModel("beta-wmp-data.jd.com", "wmp-data.jd.com"));
        HostConfig.getInstance().putHost(HostConstants.NEW_SHARE_HOST, new HostConfig.HostModel("beta-newshare.m.jd.com", "newshare.m.jd.com"));
        HostConfig.getInstance().putHost(JDPAY_SDK_HOST, new HostConfig.HostModel(JDPAY_SDK_HOST_RELEASE, JDPAY_SDK_HOST_PRE1, JDPAY_SDK_HOST_PRE2, JDPAY_SDK_HOST_PRE3, JDPAY_SDK_HOST_TEST));
        HostConfig.getInstance().putHost(JDPAY_SDK_HOST_MGATE, new HostConfig.HostModel(JDPAY_SDK_HOST_MGATE_RELEASE, JDPAY_SDK_HOST_MGATE_PRE1, JDPAY_SDK_HOST_MGATE_TEST));
        HostConfig.getInstance().putHost(JDPAY_SDK_HOST_CERT, new HostConfig.HostModel(JDPAY_SDK_HOST_CERT_RELEASE, JDPAY_SDK_HOST_CERT_PRE1, JDPAY_SDK_HOST_CERT_TEST));
        HostConfig.getInstance().putHost(DYNAMIC_SDK_HOST, new HostConfig.HostModel("beta-api.m.jd.com", "api.m.jd.com"));
        HostConfig.getInstance().putHost(DYNAMIC_SDK_FORCE_LOCAL, new HostConfig.HostModel("02\u6b63\u5e38\u6a21\u5f0f", "01\u5f3a\u5236\u8d70\u515c\u5e95\u6d41\u7a0b"));
        HostConfig.getInstance().putHost(HYBRID_HOST, new HostConfig.HostModel("beta-api.m.jd.com", "api.m.jd.com"));
        if (isBeta()) {
            HostConfig.getInstance().restoreSvaedConfigFromSp();
        }
    }

    public static boolean isBeta() {
        return JdSdk.getInstance().getBuildConfigDebug();
    }

    public static boolean setHostProperty(String str, String str2) {
        init();
        if (OKLog.D) {
            OKLog.d("Configuration", "setHostProperty()-->" + str + str2);
        }
        SharedPreferencesUtil.getSharedPreferences().edit().putString(str, str2).commit();
        properties.setProperty(str, str2);
        return true;
    }

    public static void setTestModeProperty(Boolean bool) {
        init();
        if (bool.booleanValue()) {
            properties.setProperty("testMode", DYConstants.DY_TRUE);
        } else {
            properties.setProperty("testMode", DYConstants.DY_FALSE);
        }
        SharedPreferencesUtil.getSharedPreferences().edit().putBoolean("testMode", bool.booleanValue()).commit();
    }

    public static Boolean getBooleanProperty(String str, Boolean bool) {
        String property = getProperty(str);
        if (property == null) {
            return bool;
        }
        try {
            return Boolean.valueOf(property);
        } catch (Exception unused) {
            return bool;
        }
    }

    public static Integer getIntegerProperty(String str, Integer num) {
        String property = getProperty(str);
        if (property == null) {
            return num;
        }
        try {
            return Integer.valueOf(property);
        } catch (Exception unused) {
            return num;
        }
    }

    public static Long getLongProperty(String str, Long l2) {
        String property = getProperty(str);
        if (property == null) {
            return l2;
        }
        try {
            return Long.valueOf(property);
        } catch (Exception unused) {
            return l2;
        }
    }

    public static String getProperty(String str, String str2) {
        init();
        if (TextUtils.equals(str, PARTNER)) {
            if (OKLog.D) {
                return getExtraProperty(str, JdSdk.getInstance().isGoogleChannel() ? "google-test" : "test");
            }
            return getExtraProperty(str, JdSdk.getInstance().isGoogleChannel() ? "google" : "jingdong");
        }
        if (TextUtils.equals(str, UNION_ID)) {
            str2 = getExtraProperty(str, JdSdk.getInstance().isGoogleChannel() ? "50966" : "50965");
        } else if (TextUtils.equals(str, SUB_UNION_ID)) {
            str2 = getExtraProperty(str, JdSdk.getInstance().isGoogleChannel() ? "google" : "jingdong");
        } else {
            Properties properties2 = properties;
            String property = properties2 != null ? properties2.getProperty(str) : null;
            if (property == null) {
                property = localProperties.get(str);
            }
            if (property != null) {
                str2 = property;
            }
        }
        if (OKLog.D) {
            OKLog.d("Configuration", "getProperty-->" + str2);
        }
        return str2;
    }
}
