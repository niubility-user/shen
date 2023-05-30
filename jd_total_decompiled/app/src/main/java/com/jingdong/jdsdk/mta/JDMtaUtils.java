package com.jingdong.jdsdk.mta;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.TrafficStats;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import com.jd.lib.un.utils.UnAndroidUtils;
import com.jingdong.common.ActivityNumController;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.login.DeviceFingerUtil;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.base.BaseArchUtil;
import com.jingdong.common.utils.inter.JDOverseasUtil;
import com.jingdong.common.web.IRouterParams;
import com.jingdong.jdma.JDMAConfig;
import com.jingdong.jdma.JDMaInterface;
import com.jingdong.jdma.common.utils.Constant;
import com.jingdong.jdma.minterface.AppMode;
import com.jingdong.jdma.minterface.BaseEvent;
import com.jingdong.jdma.minterface.ClickInterfaceParam;
import com.jingdong.jdma.minterface.DomainInterface;
import com.jingdong.jdma.minterface.ExposureInterfaceParam;
import com.jingdong.jdma.minterface.HttpDns;
import com.jingdong.jdma.minterface.ISwitchQuery;
import com.jingdong.jdma.minterface.JDMABaseInfo;
import com.jingdong.jdma.minterface.MaInitCommonInfo;
import com.jingdong.jdma.minterface.OnPermissionCheckListener;
import com.jingdong.jdma.minterface.OrderInterfaceParam;
import com.jingdong.jdma.minterface.PropertyInterfaceParam;
import com.jingdong.jdma.minterface.PvInterfaceParam;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.login.LoginUserHelper;
import com.jingdong.jdsdk.mta.param.MtaClickParam;
import com.jingdong.jdsdk.mta.param.MtaExposureParam;
import com.jingdong.jdsdk.mta.param.MtaPvParam;
import com.jingdong.jdsdk.secure.Base64;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.bmode.util.JDBModeUtils;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.jdhttpdns.JDHttpDnsToolkit;
import com.jingdong.sdk.jdhttpdns.pojo.IpModel;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.smtt.sdk.ProxyConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class JDMtaUtils {
    public static final String ABTKEY = "abt";
    public static final String LAT = "lat";
    private static final String LIB_TAG = "Lib_JDMA";
    public static final String LON = "lon";
    private static final String TAG = "JDMtaUtils";
    private static String ipAddress = null;
    private static final List<IJDMADeliver> mJDMADeliverList;
    private static final LinkedHashMap<String, String> mRequestIdMap;
    public static MaInitCommonInfo maInitCommonInfo = null;
    private static final int maxMapSize = 100;
    public static String oldClassName;
    public static String oldPageParam;
    private static JDLocationCacheOption option;
    private static int uid;
    private static String[] page1str = new String[4];
    private static String[] page2str = new String[4];
    private static String[] page3str = new String[4];
    private static String[] page4str = new String[4];
    private static String[] page5str = new String[4];
    private static ArrayList<String> page1lists = new ArrayList<>();
    private static ArrayList<String> page2lists = new ArrayList<>();
    private static ArrayList<String> page3lists = new ArrayList<>();
    private static ArrayList<String> page4lists = new ArrayList<>();
    private static ArrayList<String> page5lists = new ArrayList<>();
    private static long pv_timestamp = System.currentTimeMillis();
    private static String lastExtensionId = "";
    private static String lastEvent_id = "";
    private static String lastEvent_param = "";
    private static String lastPage_Param = "";
    private static MutableLiveData<String> currentPageId = new MutableLiveData<>();
    private static String lastEventId = "";
    private static DomainInterface domainInterface = new DomainInterface(Constant.STATISTIC_GET_STRATEGY_DEFAULT_DOMAIN, "mars.jd.com");

    static {
        JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
        option = jDLocationCacheOption;
        uid = -1;
        ipAddress = "";
        oldClassName = "";
        oldPageParam = "";
        jDLocationCacheOption.setBusinessId("16ec447f15a574ac5f8d59263bc7d69e");
        mRequestIdMap = new LinkedHashMap<String, String>(50, 0.5f, true) { // from class: com.jingdong.jdsdk.mta.JDMtaUtils.1
            @Override // java.util.LinkedHashMap
            protected boolean removeEldestEntry(Map.Entry<String, String> entry) {
                return size() > 100;
            }
        };
        mJDMADeliverList = new ArrayList();
    }

    public static void acceptPrivacyProtocol(boolean z) {
    }

    public static void acceptProtocal(boolean z) {
    }

    public static int addJDMADeliver(IJDMADeliver iJDMADeliver) {
        if (iJDMADeliver == null) {
            return 1;
        }
        List<IJDMADeliver> list = mJDMADeliverList;
        if (list.size() >= 5) {
            return 2;
        }
        if (list.contains(iJDMADeliver)) {
            return 3;
        }
        list.add(iJDMADeliver);
        return 0;
    }

    public static void clearMtaContent() {
        JDMaInterface.clearMtaSource();
    }

    private static void completeCommonParams(BaseEvent baseEvent) {
        if (baseEvent != null) {
            baseEvent.lat = getLatitude("");
            baseEvent.lon = getLongitude("");
            baseEvent.pin = getUserPin();
        }
    }

    private static void deliver(BaseEvent baseEvent) {
        HashMap<String, String> map;
        if (mJDMADeliverList.size() == 0 || baseEvent == null) {
            return;
        }
        if (baseEvent instanceof ClickInterfaceParam) {
            ClickInterfaceParam clickInterfaceParam = (ClickInterfaceParam) baseEvent;
            map = clickInterfaceParam.toMap();
            HashMap<String, String> hashMap = clickInterfaceParam.map;
            if (hashMap != null) {
                for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                    map.put(entry.getKey(), entry.getValue());
                }
            }
        } else if (baseEvent instanceof PvInterfaceParam) {
            PvInterfaceParam pvInterfaceParam = (PvInterfaceParam) baseEvent;
            map = pvInterfaceParam.toMap();
            HashMap<String, String> hashMap2 = pvInterfaceParam.map;
            if (hashMap2 != null) {
                for (Map.Entry<String, String> entry2 : hashMap2.entrySet()) {
                    map.put(entry2.getKey(), entry2.getValue());
                }
            }
        } else if (baseEvent instanceof PropertyInterfaceParam) {
            PropertyInterfaceParam propertyInterfaceParam = (PropertyInterfaceParam) baseEvent;
            map = propertyInterfaceParam.toMap();
            HashMap<String, String> hashMap3 = propertyInterfaceParam.map;
            if (hashMap3 != null) {
                for (Map.Entry<String, String> entry3 : hashMap3.entrySet()) {
                    map.put(entry3.getKey(), entry3.getValue());
                }
            }
        } else if (baseEvent instanceof OrderInterfaceParam) {
            OrderInterfaceParam orderInterfaceParam = (OrderInterfaceParam) baseEvent;
            map = orderInterfaceParam.toMap();
            HashMap<String, String> hashMap4 = orderInterfaceParam.map;
            if (hashMap4 != null) {
                for (Map.Entry<String, String> entry4 : hashMap4.entrySet()) {
                    map.put(entry4.getKey(), entry4.getValue());
                }
            }
        } else if (baseEvent instanceof ExposureInterfaceParam) {
            ExposureInterfaceParam exposureInterfaceParam = (ExposureInterfaceParam) baseEvent;
            map = exposureInterfaceParam.toMap();
            HashMap<String, String> hashMap5 = exposureInterfaceParam.map;
            if (hashMap5 != null) {
                for (Map.Entry<String, String> entry5 : hashMap5.entrySet()) {
                    map.put(entry5.getKey(), entry5.getValue());
                }
            }
        } else {
            map = baseEvent.toMap();
        }
        for (IJDMADeliver iJDMADeliver : mJDMADeliverList) {
            if (iJDMADeliver != null) {
                iJDMADeliver.deliverData(map);
            }
        }
    }

    public static void destroy() {
        JDMaInterface.destroy();
    }

    private static JSONObject generateCurrentSourceObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            String[] strArr = page1str;
            if (strArr != null) {
                jSONObject.put("lv1_page_name", strArr[0]);
                jSONObject.put("lv1_page_param", page1str[1]);
                jSONObject.put("lv1_event_id", page1str[2]);
                jSONObject.put("lv1_event_param", page1str[3]);
            }
            String[] strArr2 = page2str;
            if (strArr2 != null) {
                jSONObject.put("lv2_page_name", strArr2[0]);
                jSONObject.put("lv2_page_param", page2str[1]);
                jSONObject.put("lv2_event_id", page2str[2]);
                jSONObject.put("lv2_event_param", page2str[3]);
            }
            String[] strArr3 = page3str;
            if (strArr3 != null) {
                jSONObject.put("lv3_page_name", strArr3[0]);
                jSONObject.put("lv3_page_param", page3str[1]);
                jSONObject.put("lv3_event_id", page3str[2]);
                jSONObject.put("lv3_event_param", page3str[3]);
            }
            String[] strArr4 = page4str;
            if (strArr4 != null) {
                jSONObject.put("lv4_page_name", strArr4[0]);
                jSONObject.put("lv4_page_param", page4str[1]);
                jSONObject.put("lv4_event_id", page4str[2]);
                jSONObject.put("lv4_event_param", page4str[3]);
            }
            String[] strArr5 = page5str;
            if (strArr5 != null) {
                jSONObject.put("lv5_page_name", strArr5[0]);
                jSONObject.put("lv5_page_param", page5str[1]);
                jSONObject.put("lv5_event_id", page5str[2]);
                jSONObject.put("lv5_event_param", page5str[3]);
            }
            jSONObject.put("pv_sid", "" + JDMaInterface.getOpen_count());
            jSONObject.put("pv_seq", "" + JDMaInterface.getSeq());
            jSONObject.put("sourceType", "" + JDMaInterface.getSourceType());
            jSONObject.put("sourceValue", "" + JDMaInterface.getSourceValue());
            jSONObject.put("cart_ts", "" + System.currentTimeMillis());
            jSONObject.put("cart_sid", "" + JDMaInterface.getOpen_count());
            jSONObject.put("cart_seq", "" + JDMaInterface.getSeq());
            jSONObject.put("cart_jdv", JDMaInterface.getJdv());
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
        if (OKLog.D) {
            OKLog.i(TAG, "getCurrentSource() 1map=source=" + jSONObject.toString());
        }
        return jSONObject;
    }

    public static String getCurrentMicrosecond() {
        return "" + System.currentTimeMillis();
    }

    public static String getCurrentModeTag() {
        return "2".equals(String.valueOf(JDBModeUtils.getCurrentMode())) ? "7" : JDElderModeUtils.isElderMode() ? "1" : JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext()) ? "" : "2";
    }

    public static MutableLiveData<String> getCurrentPageId() {
        return currentPageId;
    }

    @Deprecated
    public static String getEncryptLoginUserName(String str) {
        return str;
    }

    public static String getIPAddressInternal() {
        if (TextUtils.isEmpty(ipAddress)) {
            String[][] netAddresses = BaseInfo.getNetAddresses();
            int length = netAddresses.length;
            int i2 = 0;
            loop0: while (true) {
                if (i2 >= length) {
                    break;
                }
                String[] strArr = netAddresses[i2];
                if (strArr != null) {
                    for (String str : strArr) {
                        if (!TextUtils.isEmpty(str)) {
                            ipAddress = str.toUpperCase();
                            break loop0;
                        }
                    }
                }
                i2++;
            }
        }
        return ipAddress;
    }

    public static String getJda() {
        return JDMaInterface.getJda();
    }

    public static String getJdv() {
        OKLog.w(LIB_TAG, "getJdv()");
        return JDMaInterface.getJdv();
    }

    public static String getLastEventId() {
        return lastEventId;
    }

    private static String getLatitude(String str) {
        JDLocationCacheOption jDLocationCacheOption = option;
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        jDLocationCacheOption.setSceneId(str);
        JDLocation mtaLocation = JDLocationCache.getInstance().getMtaLocation(option);
        if (mtaLocation == null) {
            return "0";
        }
        return "" + mtaLocation.getLat();
    }

    private static String getLongitude(String str) {
        JDLocationCacheOption jDLocationCacheOption = option;
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        jDLocationCacheOption.setSceneId(str);
        JDLocation mtaLocation = JDLocationCache.getInstance().getMtaLocation(option);
        if (mtaLocation == null) {
            return "0";
        }
        return "" + mtaLocation.getLng();
    }

    public static synchronized MaInitCommonInfo getMaInitCommonInfo(Context context) {
        MaInitCommonInfo maInitCommonInfo2;
        synchronized (JDMtaUtils.class) {
            if (maInitCommonInfo == null) {
                MaInitCommonInfo maInitCommonInfo3 = new MaInitCommonInfo();
                maInitCommonInfo = maInitCommonInfo3;
                maInitCommonInfo3.appv = PackageInfoUtil.getVersionName();
                maInitCommonInfo.build = PackageInfoUtil.getVersionCode() + "";
                maInitCommonInfo.channel = Configuration.getProperty(Configuration.PARTNER, "jingdong");
                maInitCommonInfo.guid = StatisticsReportUtil.readDeviceUUID();
                maInitCommonInfo.installationId = StatisticsReportUtil.readInstallationId();
                maInitCommonInfo.setCheckListener(new OnPermissionCheckListener() { // from class: com.jingdong.jdsdk.mta.JDMtaUtils.2
                    @Override // com.jingdong.jdma.minterface.OnPermissionCheckListener
                    public String updateGuid() {
                        return StatisticsReportUtil.readDeviceUUID();
                    }
                });
                MaInitCommonInfo maInitCommonInfo4 = maInitCommonInfo;
                maInitCommonInfo4.app_device = JDMAConfig.ANDROID;
                maInitCommonInfo4.proj_id = "1";
                maInitCommonInfo4.site_id = "JA2015_311210";
                maInitCommonInfo4.domainInterface = domainInterface;
                maInitCommonInfo4.zipFlag = 1;
                maInitCommonInfo4.setHttpDns(new HttpDns() { // from class: com.jingdong.jdsdk.mta.JDMtaUtils.3
                    @Override // com.jingdong.jdma.minterface.HttpDns
                    public String getIpByHost(String str) {
                        IpModel ipModelByHost = JDHttpDnsToolkit.getInstance().getIpModelByHost(str);
                        return ipModelByHost != null ? ipModelByHost.getIp() : "";
                    }

                    @Override // com.jingdong.jdma.minterface.HttpDns
                    public boolean isHttpDnsEnabled() {
                        return false;
                    }
                });
                maInitCommonInfo.setJDMABaseInfo(new JDMABaseInfo() { // from class: com.jingdong.jdsdk.mta.JDMtaUtils.4
                    @Override // com.jingdong.jdma.minterface.JDMABaseInfo
                    public String getAndroidId() {
                        return BaseInfo.getAndroidId();
                    }

                    @Override // com.jingdong.jdma.minterface.JDMABaseInfo
                    public String getDeviceBrand() {
                        return BaseInfo.getDeviceBrand();
                    }

                    @Override // com.jingdong.jdma.minterface.JDMABaseInfo
                    public String getDeviceModel() {
                        return BaseInfo.getDeviceModel();
                    }

                    @Override // com.jingdong.jdma.minterface.JDMABaseInfo
                    public int getOsVersionInt() {
                        return BaseInfo.getAndroidSDKVersion();
                    }

                    @Override // com.jingdong.jdma.minterface.JDMABaseInfo
                    public String getOsVersionName() {
                        return BaseInfo.getAndroidVersion();
                    }

                    @Override // com.jingdong.jdma.minterface.JDMABaseInfo
                    public String getScreenSize() {
                        return BaseInfo.getScreenHeight() + ProxyConfig.MATCH_ALL_SCHEMES + BaseInfo.getScreenWidth();
                    }

                    @Override // com.jingdong.jdma.minterface.JDMABaseInfo
                    public String getSimOperator() {
                        return "";
                    }
                });
                maInitCommonInfo.setISwitchQuery(new ISwitchQuery() { // from class: com.jingdong.jdsdk.mta.JDMtaUtils.5
                    @Override // com.jingdong.jdma.minterface.ISwitchQuery
                    public String getValueByKey(String str, String str2) {
                        SwitchQueryFetcher.getFetcher();
                        return SwitchQueryFetcher.getSwitchStringValue(str, str2);
                    }

                    @Override // com.jingdong.jdma.minterface.ISwitchQuery
                    public boolean isXTime() {
                        SwitchQueryFetcher.getFetcher();
                        return SwitchQueryFetcher.getSwitchBooleanValue("isMaXTime", false);
                    }

                    @Override // com.jingdong.jdma.minterface.ISwitchQuery
                    public int getValueByKey(String str, int i2) {
                        SwitchQueryFetcher.getFetcher();
                        return SwitchQueryFetcher.getSwitchIntValue(str, i2);
                    }

                    @Override // com.jingdong.jdma.minterface.ISwitchQuery
                    public boolean getValueByKey(String str, boolean z) {
                        SwitchQueryFetcher.getFetcher();
                        return SwitchQueryFetcher.getSwitchBooleanValue(str, z);
                    }
                });
            }
            setModeTag(getCurrentModeTag());
            setAreaCode(JDOverseasUtil.getCurrentOverseasArea() + "");
            setMaIsSparse(String.valueOf(JDBModeUtils.getAdviseVersion()));
            setMaBGroup(String.valueOf(JDBModeUtils.getPopulationType()));
            setMaAbTest(JDBModeUtils.getBuriedExpLabel());
            maInitCommonInfo2 = maInitCommonInfo;
        }
        return maInitCommonInfo2;
    }

    public static String getSessionInfo() {
        return JDMaInterface.getSessionInfo(JdSdk.getInstance().getApplicationContext());
    }

    public static String getSessionInfoUnion() {
        return JDMaInterface.getSessionInfo(JdSdk.getInstance().getApplicationContext());
    }

    @JavascriptInterface
    public static void getSessionToH5(IRouterParams iRouterParams) {
        if (iRouterParams == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pv_sid", JDMaInterface.getOpen_count());
            jSONObject.put("pv_seq", JDMaInterface.getSeq());
            jSONObject.put("extension_id", Base64.encodeBytes(lastExtensionId.getBytes()));
            jSONObject.put("h5_time", JDMaInterface.getH5Time());
            jSONObject.put("h5_size", JDMaInterface.getH5Size());
            jSONObject.put("ma_is_sparse", String.valueOf(JDBModeUtils.getAdviseVersion()));
            jSONObject.put("ma_b_group", String.valueOf(JDBModeUtils.getPopulationType()));
            jSONObject.put("ma_ab_test", JDBModeUtils.getBuriedExpLabel());
            jSONObject.put("lat", getLatitude(""));
            jSONObject.put(LON, getLongitude(""));
            jSONObject.put("jdv", JDMaInterface.getJdv());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        OKLog.w(LIB_TAG, "getSessionToH5(), result: " + jSONObject);
        iRouterParams.onCallBack(jSONObject);
    }

    @JavascriptInterface
    public static String getSyncSessionToH5(IRouterParams iRouterParams) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pv_sid", JDMaInterface.getOpen_count());
            jSONObject.put("pv_seq", JDMaInterface.getSeq());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        OKLog.w(LIB_TAG, "getSyncSessionToH5(), result: " + jSONObject);
        return jSONObject.toString();
    }

    public static String getUnpl() {
        return JDMaInterface.getUnpl();
    }

    private static String getUserPin() {
        return !JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext()) ? "" : LoginUserHelper.getInstance().getLoginUser().getLoginUserName();
    }

    private static boolean handle1Page(Context context, String str, String str2, String str3, String str4, String str5) {
        if (page1lists.contains(str)) {
            if (str4 == null) {
                str4 = "";
            }
            set1Page(str4, str5, str, str2);
            return true;
        }
        return false;
    }

    private static boolean handle2Page(Context context, String str, String str2, String str3, String str4, String str5) {
        if (page2lists.contains(str)) {
            if (str4 == null) {
                str4 = "";
            }
            set2Page(str4, str5, str, str2);
            return true;
        }
        return false;
    }

    private static boolean handle3Page(Context context, String str, String str2, String str3, String str4, String str5) {
        if (page3lists.contains(str)) {
            if (str4 == null) {
                str4 = "";
            }
            set3Page(str4, str5, str, str2);
            return true;
        }
        return false;
    }

    private static boolean handle4Page(Context context, String str, String str2, String str3, String str4, String str5) {
        if (page4lists.contains(str)) {
            if (str4 == null) {
                str4 = "";
            }
            set4Page(str4, str5, str, str2);
            return true;
        }
        return false;
    }

    private static boolean handle5Page(Context context, String str, String str2, String str3, String str4, String str5) {
        OKLog.d(TAG, "handle5Page clickId =" + str);
        if (page5lists.contains(str)) {
            if (str4 == null) {
                str4 = "";
            }
            set5Page(str4, str5, str, str2);
            return true;
        }
        return false;
    }

    @Deprecated
    public static boolean hasPhonePermission(String str) {
        return false;
    }

    public static void init(Context context) {
        OKLog.w(LIB_TAG, "init()");
        JDMaInterface.setShowLog(false);
        getMaInitCommonInfo(context);
        setAppMode(JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext()) ? AppMode.NORMAL : AppMode.SIMPLE);
        JDMaInterface.init(context, maInitCommonInfo);
    }

    public static void initPageEventIds(String[] strArr, String[] strArr2, String[] strArr3, String[] strArr4, String[] strArr5) {
        for (String str : strArr) {
            page1lists.add(str);
        }
        for (String str2 : strArr2) {
            page2lists.add(str2);
        }
        for (String str3 : strArr3) {
            page3lists.add(str3);
        }
        for (String str4 : strArr4) {
            page4lists.add(str4);
        }
        for (String str5 : strArr5) {
            page5lists.add(str5);
        }
    }

    public static void onClick(Context context, String str, String str2) {
        try {
            sendCommonData(context, str, "", "onClick", str2, "", "", "");
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static void onClickWithPageId(Context context, String str, String str2, String str3) {
        try {
            sendCommonData(context, str, "", "onClick", str2, "", "", "", str3, "");
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static void onPause() {
        JDMaInterface.onPause();
    }

    public static void onResume(Context context) {
        JDMaInterface.onResume(context);
    }

    public static void onSavePackOrderPage(String str) {
        OKLog.d(TAG, "onSavePackOrderPage : ", new Throwable("onSavePackOrderPage"));
        try {
            JDMtaCacheTable.insertOrUpdate("p_" + str, generateCurrentSourceObject().toString());
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
    }

    public static void onSavePackOrderPageWithSkuTag(String str, String str2) {
        try {
            JSONObject generateCurrentSourceObject = generateCurrentSourceObject();
            generateCurrentSourceObject.put("sku_tag", str2);
            JDMtaCacheTable.insertOrUpdate("p_" + str, generateCurrentSourceObject.toString());
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
    }

    public static void onSaveProductOrderPage(String str) {
        try {
            JDMtaCacheTable.insertOrUpdate("s_" + str, generateCurrentSourceObject().toString());
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
    }

    public static void onSaveProductOrderPageWithSkuTag(String str, String str2) {
        try {
            JSONObject generateCurrentSourceObject = generateCurrentSourceObject();
            generateCurrentSourceObject.put("sku_tag", str2);
            JDMtaCacheTable.insertOrUpdate("s_" + str, generateCurrentSourceObject.toString());
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
    }

    public static void putRequestId(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        mRequestIdMap.put(str, str2);
    }

    public static void removeJDMADeliver(IJDMADeliver iJDMADeliver) {
        mJDMADeliverList.remove(iJDMADeliver);
    }

    public static void removeUserProperty(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JDMaInterface.removeUserProperty(str);
    }

    public static void sendCDNImageData(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        getMaInitCommonInfo(context);
        if (context == null || str == null) {
            return;
        }
        PropertyInterfaceParam propertyInterfaceParam = new PropertyInterfaceParam();
        propertyInterfaceParam.pin = getUserPin();
        propertyInterfaceParam.lon = getLongitude("");
        propertyInterfaceParam.lat = getLatitude("");
        propertyInterfaceParam.page_name = str;
        propertyInterfaceParam.page_param = str2;
        propertyInterfaceParam.pic_ts = str3;
        propertyInterfaceParam.pic_url = str4;
        propertyInterfaceParam.pic_endts = str5;
        propertyInterfaceParam.pic_size = str6;
        propertyInterfaceParam.cdn_ip = str7;
        propertyInterfaceParam.ldns_ip = getIPAddressInternal();
        JDMaInterface.sendPropertyData(context, maInitCommonInfo, propertyInterfaceParam);
        deliver(propertyInterfaceParam);
    }

    public static void sendClickData(MtaClickParam mtaClickParam) {
        if (mtaClickParam == null) {
            return;
        }
        getMaInitCommonInfo(JdSdk.getInstance().getApplicationContext());
        completeCommonParams(mtaClickParam);
        JDMaInterface.sendData(JdSdk.getInstance().getApplicationContext(), maInitCommonInfo, mtaClickParam);
        deliver(mtaClickParam);
    }

    public static void sendClickDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, HashMap<String, String> hashMap) {
        sendClickDataWithExt(context, str, str2, str3, str4, str5, str6, str7, str8, "", "", "", "", hashMap);
    }

    public static void sendCommonData(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2) {
        sendCommonDataWithExt(context, str, str2, str3, str4, str5, str6, str7, i2, (HashMap<String, String>) null);
    }

    public static void sendCommonData4ProductDetail(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, HashMap<String, String> hashMap) {
        getMaInitCommonInfo(context);
        ClickInterfaceParam clickInterfaceParam = new ClickInterfaceParam();
        String str11 = (hashMap == null || !hashMap.containsKey(BaseEvent.LBS_SCENE_ID)) ? "" : hashMap.get(BaseEvent.LBS_SCENE_ID);
        clickInterfaceParam.lat = getLatitude(str11);
        clickInterfaceParam.lon = getLongitude(str11);
        clickInterfaceParam.event_id = str;
        clickInterfaceParam.event_param = str2;
        clickInterfaceParam.next_page_name = str9;
        clickInterfaceParam.page_name = str5;
        clickInterfaceParam.page_param = str6;
        clickInterfaceParam.pin = getUserPin();
        clickInterfaceParam.page_id = str4;
        clickInterfaceParam.shop = str8;
        clickInterfaceParam.sku_tag = str7;
        lastEventId = str;
        if (hashMap != null) {
            clickInterfaceParam.map = hashMap;
        }
        updateLastExtensionID(hashMap);
        updateLastClickParam(str, str2, str6);
        JDMaInterface.sendClickData(context, maInitCommonInfo, clickInterfaceParam);
        deliver(clickInterfaceParam);
        boolean handle1Page = handle1Page(context, str, str2, str3, str5, str6);
        if (!handle1Page) {
            handle1Page = handle2Page(context, str, str2, str3, str5, str6);
        }
        if (!handle1Page) {
            handle1Page = handle3Page(context, str, str2, str3, str5, str6);
        }
        if (!handle1Page) {
            handle1Page = handle4Page(context, str, str2, str3, str5, str6);
        }
        if (handle1Page) {
            return;
        }
        handle5Page(context, str, str2, str3, str5, str6);
    }

    public static void sendCommonDataForPromotionListPage(Context context, String str, String str2, String str3, Object obj, String str4, Class cls, String str5, String str6) {
        sendCommonDataForPromotionListPage(context, str, str2, str3, obj, str4, cls != null ? cls.getName() : "", str5, str6);
    }

    public static void sendCommonDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2, HashMap<String, String> hashMap) {
        getMaInitCommonInfo(context);
        ClickInterfaceParam clickInterfaceParam = new ClickInterfaceParam();
        String str8 = (hashMap == null || !hashMap.containsKey(BaseEvent.LBS_SCENE_ID)) ? "" : hashMap.get(BaseEvent.LBS_SCENE_ID);
        clickInterfaceParam.lat = getLatitude(str8);
        clickInterfaceParam.lon = getLongitude(str8);
        clickInterfaceParam.event_id = str;
        clickInterfaceParam.event_param = str2;
        clickInterfaceParam.map = hashMap;
        updateLastExtensionID(hashMap);
        updateLastClickParam(str, str2, str5);
        if (str6 != null) {
            if (str6.equals("ProductDetailNewActivity")) {
                clickInterfaceParam.next_page_name = "com.jingdong.app.mall.product.ProductDetailNewLastActivity";
            } else {
                clickInterfaceParam.next_page_name = str6;
            }
        }
        clickInterfaceParam.page_name = str4;
        clickInterfaceParam.page_param = str5;
        clickInterfaceParam.pin = getUserPin();
        lastEventId = str;
        JDMaInterface.sendClickData(context, maInitCommonInfo, clickInterfaceParam);
        deliver(clickInterfaceParam);
        if (i2 == 1) {
            set1Page(str4, str5, str, str2);
        } else if (i2 == 2) {
            set2Page(str4, str5, str, str2);
        } else if (i2 == 3) {
            set3Page(str4, str5, str, str2);
        } else if (i2 == 4) {
            set4Page(str4, str5, str, str2);
        } else if (i2 == 5) {
            set5Page(str4, str5, str, str2);
        }
    }

    public static void sendCommonDataWithExtParam(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6, String str7, String... strArr) {
        String str8;
        getMaInitCommonInfo(context);
        if (obj == null) {
            str8 = "";
        } else if (obj instanceof String) {
            str8 = (String) obj;
        } else {
            str8 = obj.getClass().getName();
        }
        ClickInterfaceParam clickInterfaceParam = new ClickInterfaceParam();
        clickInterfaceParam.lat = getLatitude("");
        clickInterfaceParam.lon = getLongitude("");
        clickInterfaceParam.event_id = str;
        clickInterfaceParam.event_param = str2;
        clickInterfaceParam.page_name = str8;
        clickInterfaceParam.page_param = str4;
        clickInterfaceParam.page_id = str7;
        clickInterfaceParam.event_func = str3;
        clickInterfaceParam.pin = getUserPin();
        if (str5 != null) {
            if (str5.endsWith("ProductDetailNewActivity")) {
                str5 = "com.jingdong.app.mall.product.ProductDetailNewLastActivity";
            }
            clickInterfaceParam.next_page_name = str5;
        }
        if (strArr != null && strArr.length > 1) {
            if (clickInterfaceParam.map == null) {
                clickInterfaceParam.map = new HashMap<>();
            }
            for (int i2 = 0; i2 < strArr.length; i2 += 2) {
                String str9 = strArr[i2];
                int i3 = i2 + 1;
                if (i3 >= strArr.length) {
                    break;
                }
                String str10 = strArr[i3];
                if (str9 != null && !str9.isEmpty() && str10 != null && !str10.isEmpty()) {
                    clickInterfaceParam.map.put(str9, str10);
                }
            }
        }
        updateLastExtensionID(clickInterfaceParam.map);
        updateLastClickParam(str, str2, str4);
        lastEventId = str;
        JDMaInterface.sendClickData(context, maInitCommonInfo, clickInterfaceParam);
        deliver(clickInterfaceParam);
        boolean handle1Page = handle1Page(context, str, str2, str3, str8, str4);
        if (!handle1Page) {
            handle1Page = handle2Page(context, str, str2, str3, str8, str4);
        }
        if (!handle1Page) {
            handle1Page = handle3Page(context, str, str2, str3, str8, str4);
        }
        if (!handle1Page) {
            handle1Page = handle4Page(context, str, str2, str3, str8, str4);
        }
        if (handle1Page) {
            return;
        }
        handle5Page(context, str, str2, str3, str8, str4);
    }

    @Deprecated
    private static void sendData(Context context, HashMap<String, String> hashMap, int i2) {
    }

    @JavascriptInterface
    public static void sendDataFromH5(IRouterParams iRouterParams) {
        if (iRouterParams == null || TextUtils.isEmpty(iRouterParams.getRouterParam())) {
            return;
        }
        JDMaInterface.recordLogFromH5(iRouterParams.getRouterParam());
    }

    public static void sendEpData(Context context, Object obj, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        String str8;
        getMaInitCommonInfo(context);
        if (context == null) {
            return;
        }
        if (obj == null) {
            str8 = "";
        } else if (obj instanceof String) {
            str8 = obj.toString();
        } else {
            str8 = obj.getClass().getName();
        }
        ExposureInterfaceParam exposureInterfaceParam = new ExposureInterfaceParam();
        exposureInterfaceParam.lat = getLatitude("");
        exposureInterfaceParam.lon = getLongitude("");
        exposureInterfaceParam.page_name = str8;
        exposureInterfaceParam.page_id = str;
        exposureInterfaceParam.page_param = str2;
        exposureInterfaceParam.pin = getUserPin();
        exposureInterfaceParam.orderId = str6;
        exposureInterfaceParam.shopId = str5;
        exposureInterfaceParam.eventId = str3;
        exposureInterfaceParam.eventParam = str4;
        exposureInterfaceParam.ep_flag = "1";
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pv_sid", "" + JDMaInterface.getOpen_count());
        hashMap.put("pv_seq", "" + JDMaInterface.getSeq());
        hashMap.put("sku_tag", str7);
        exposureInterfaceParam.map = hashMap;
        JDMaInterface.sendExposureData(context, maInitCommonInfo, exposureInterfaceParam);
        deliver(exposureInterfaceParam);
    }

    public static void sendExposureData(Context context, Object obj, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        String str8;
        getMaInitCommonInfo(context);
        if (context == null) {
            return;
        }
        if (obj == null) {
            str8 = "";
        } else if (obj instanceof String) {
            str8 = obj.toString();
        } else {
            str8 = obj.getClass().getName();
        }
        ExposureInterfaceParam exposureInterfaceParam = new ExposureInterfaceParam();
        exposureInterfaceParam.lat = getLatitude("");
        exposureInterfaceParam.lon = getLongitude("");
        exposureInterfaceParam.page_name = str8;
        exposureInterfaceParam.page_id = str;
        exposureInterfaceParam.page_param = str2;
        exposureInterfaceParam.pin = getUserPin();
        exposureInterfaceParam.orderId = str6;
        exposureInterfaceParam.shopId = str5;
        exposureInterfaceParam.eventId = str3;
        exposureInterfaceParam.eventParam = str4;
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pv_sid", "" + JDMaInterface.getOpen_count());
        hashMap.put("pv_seq", "" + JDMaInterface.getSeq());
        hashMap.put("sku_tag", str7);
        exposureInterfaceParam.map = hashMap;
        JDMaInterface.sendExposureData(context, maInitCommonInfo, exposureInterfaceParam);
        deliver(exposureInterfaceParam);
    }

    public static void sendExposureDataExtend(Context context, Object obj, String str, String str2, String str3, String str4, String str5, String str6, String str7, HashMap<String, String> hashMap) {
        String str8;
        getMaInitCommonInfo(context);
        if (context == null) {
            return;
        }
        if (obj == null) {
            str8 = "";
        } else if (obj instanceof String) {
            str8 = obj.toString();
        } else {
            str8 = obj.getClass().getName();
        }
        ExposureInterfaceParam exposureInterfaceParam = new ExposureInterfaceParam();
        exposureInterfaceParam.lat = getLatitude("");
        exposureInterfaceParam.lon = getLongitude("");
        exposureInterfaceParam.page_name = str8;
        exposureInterfaceParam.page_id = str;
        exposureInterfaceParam.page_param = str2;
        exposureInterfaceParam.pin = getUserPin();
        exposureInterfaceParam.orderId = str6;
        exposureInterfaceParam.shopId = str5;
        exposureInterfaceParam.eventId = str3;
        exposureInterfaceParam.eventParam = str4;
        HashMap<String, String> hashMap2 = new HashMap<>();
        hashMap2.put("pv_sid", "" + JDMaInterface.getOpen_count());
        hashMap2.put("pv_seq", "" + JDMaInterface.getSeq());
        hashMap2.put("sku_tag", str7);
        exposureInterfaceParam.map = hashMap2;
        if (hashMap != null) {
            if (hashMap.containsKey(BaseEvent.SCENE)) {
                exposureInterfaceParam.scene = hashMap.get(BaseEvent.SCENE);
            }
            if (hashMap.containsKey("lat")) {
                exposureInterfaceParam.lat = hashMap.get("lat");
            }
            if (hashMap.containsKey(LON)) {
                exposureInterfaceParam.lon = hashMap.get(LON);
            }
        }
        JDMaInterface.sendExposureData(context, maInitCommonInfo, exposureInterfaceParam);
        deliver(exposureInterfaceParam);
    }

    @Deprecated
    public static void sendExposureDataOverLoad(Context context, String str, String str2, String str3, String str4, String str5) {
        sendExposureData(context, str5, str4, str3, str, str2, "", "", "");
    }

    @Deprecated
    public static void sendExposureDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, HashMap<String, String> hashMap) {
        sendExposureDataWithExt(context, str, str2, str3, str4, str5, "", str6, str7, str8, hashMap);
    }

    public static void sendExposureExtend(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        getMaInitCommonInfo(context);
        if (context == null) {
            return;
        }
        ExposureInterfaceParam exposureInterfaceParam = new ExposureInterfaceParam();
        String str10 = (hashMap == null || !hashMap.containsKey(BaseEvent.LBS_SCENE_ID)) ? "" : hashMap.get(BaseEvent.LBS_SCENE_ID);
        exposureInterfaceParam.lat = getLatitude(str10);
        exposureInterfaceParam.lon = getLongitude(str10);
        exposureInterfaceParam.page_name = str4;
        exposureInterfaceParam.page_id = str3;
        exposureInterfaceParam.page_param = str5;
        exposureInterfaceParam.pin = getUserPin();
        exposureInterfaceParam.orderId = str8;
        exposureInterfaceParam.shopId = str7;
        exposureInterfaceParam.eventId = str;
        exposureInterfaceParam.eventParam = str2;
        exposureInterfaceParam.jsonParam = str6;
        if (hashMap == null) {
            hashMap = new HashMap<>();
        }
        hashMap.put("pv_sid", "" + JDMaInterface.getOpen_count());
        hashMap.put("pv_seq", "" + JDMaInterface.getSeq());
        hashMap.put("sku_tag", str9);
        exposureInterfaceParam.map = hashMap;
        if (hashMap2 != null) {
            if (hashMap2.containsKey(BaseEvent.SCENE)) {
                exposureInterfaceParam.scene = hashMap2.get(BaseEvent.SCENE);
            }
            if (hashMap2.containsKey("lat")) {
                exposureInterfaceParam.lat = hashMap2.get("lat");
            }
            if (hashMap2.containsKey(LON)) {
                exposureInterfaceParam.lon = hashMap2.get(LON);
            }
        }
        JDMaInterface.sendExposureData(context, maInitCommonInfo, exposureInterfaceParam);
        deliver(exposureInterfaceParam);
    }

    public static void sendJdvInfo(String str) {
        OKLog.w(LIB_TAG, "sendJdvInfo(), jdv: " + str);
        JDMaInterface.setJdv(str);
    }

    public static void sendOrderDatas(Context context, String str, String str2, String str3, String str4, boolean z, String str5) {
        sendOrderDatasWithExt(context, str, str2, str3, str4, z, str5, null, "");
    }

    public static void sendOrderDatasOnlyForPack(Context context, String str, String str2, String str3, String str4, String str5, boolean z, String str6) {
        sendOrderDatasWithExtOnlyForPack(context, str, str2, str3, str4, str5, z, str6, null);
    }

    public static void sendOrderDatasWithExt(Context context, String str, String str2, String str3, String str4, boolean z, String str5, HashMap<String, String> hashMap, String str6) {
        getMaInitCommonInfo(context);
        OrderInterfaceParam orderInterfaceParam = new OrderInterfaceParam();
        String findSource = JDMtaCacheTable.findSource(str3);
        if (OKLog.D) {
            String str7 = TAG;
            OKLog.i(str7, "sendOrderDatas 1map=productId=" + str3);
            OKLog.i(str7, "sendOrderDatas 1map=sourceJson=" + findSource);
        }
        try {
            if (TextUtils.isEmpty(findSource)) {
                findSource = generateCurrentSourceObject().toString();
            }
            if (!TextUtils.isEmpty(findSource)) {
                JSONObject jSONObject = new JSONObject(findSource);
                String str8 = (hashMap == null || !hashMap.containsKey(BaseEvent.LBS_SCENE_ID)) ? "" : hashMap.get(BaseEvent.LBS_SCENE_ID);
                orderInterfaceParam.lat = getLatitude(str8);
                orderInterfaceParam.lon = getLongitude(str8);
                orderInterfaceParam.pv_sid = "" + JDMaInterface.getOpen_count();
                orderInterfaceParam.pv_seq = "" + JDMaInterface.getSeq();
                orderInterfaceParam.sku_tag = jSONObject.optString("sku_tag");
                orderInterfaceParam.cart_jdv = jSONObject.optString("cart_jdv");
                orderInterfaceParam.cart_sid = jSONObject.optString("cart_sid");
                orderInterfaceParam.cart_seq = jSONObject.optString("cart_seq");
                orderInterfaceParam.cart_ts = jSONObject.optString("cart_ts");
                orderInterfaceParam.lv1_page_name = jSONObject.optString("lv1_page_name");
                orderInterfaceParam.lv1_page_param = jSONObject.optString("lv1_page_param");
                orderInterfaceParam.lv1_event_id = jSONObject.optString("lv1_event_id");
                orderInterfaceParam.lv1_event_param = jSONObject.optString("lv1_event_param");
                orderInterfaceParam.lv2_page_name = jSONObject.optString("lv2_page_name");
                orderInterfaceParam.lv2_page_param = jSONObject.optString("lv2_page_param");
                orderInterfaceParam.lv2_event_id = jSONObject.optString("lv2_event_id");
                orderInterfaceParam.lv2_event_param = jSONObject.optString("lv2_event_param");
                orderInterfaceParam.lv3_page_name = jSONObject.optString("lv3_page_name");
                orderInterfaceParam.lv3_page_param = jSONObject.optString("lv3_page_param");
                orderInterfaceParam.lv3_event_id = jSONObject.optString("lv3_event_id");
                orderInterfaceParam.lv3_event_param = jSONObject.optString("lv3_event_param");
                orderInterfaceParam.lv4_page_name = jSONObject.optString("lv4_page_name");
                orderInterfaceParam.lv4_page_param = jSONObject.optString("lv4_page_param");
                orderInterfaceParam.lv4_event_id = jSONObject.optString("lv4_event_id");
                orderInterfaceParam.lv4_event_param = jSONObject.optString("lv4_event_param");
                orderInterfaceParam.lv5_page_name = jSONObject.optString("lv5_page_name");
                orderInterfaceParam.lv5_page_param = jSONObject.optString("lv5_page_param");
                orderInterfaceParam.lv5_event_id = jSONObject.optString("lv5_event_id");
                orderInterfaceParam.lv5_event_param = jSONObject.optString("lv5_event_param");
            }
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
        orderInterfaceParam.order_total_fee = str2;
        orderInterfaceParam.order_ts = getCurrentMicrosecond();
        orderInterfaceParam.prod_id = str3;
        orderInterfaceParam.quantity = str4;
        orderInterfaceParam.sale_ord_id = str;
        orderInterfaceParam.pin = getUserPin();
        orderInterfaceParam.ord_ext = str5;
        orderInterfaceParam.map = hashMap;
        orderInterfaceParam.ord_type = str6;
        JDMaInterface.sendOrderData(context, maInitCommonInfo, orderInterfaceParam);
        deliver(orderInterfaceParam);
    }

    public static void sendOrderDatasWithExtOnlyForPack(Context context, String str, String str2, String str3, String str4, String str5, boolean z, String str6, HashMap<String, String> hashMap) {
        getMaInitCommonInfo(context);
        OrderInterfaceParam orderInterfaceParam = new OrderInterfaceParam();
        String findSource = JDMtaCacheTable.findSource(str3);
        if (OKLog.D) {
            String str7 = TAG;
            OKLog.i(str7, "sendOrderDatas 1map=packId=" + str3);
            OKLog.i(str7, "sendOrderDatas 1map=productId=" + str4);
            OKLog.i(str7, "sendOrderDatas 1map=sourceJson=" + findSource);
        }
        try {
            if (TextUtils.isEmpty(findSource)) {
                findSource = generateCurrentSourceObject().toString();
            }
            if (!TextUtils.isEmpty(findSource)) {
                JSONObject jSONObject = new JSONObject(findSource);
                String str8 = (hashMap == null || !hashMap.containsKey(BaseEvent.LBS_SCENE_ID)) ? "" : hashMap.get(BaseEvent.LBS_SCENE_ID);
                orderInterfaceParam.lat = getLatitude(str8);
                orderInterfaceParam.lon = getLongitude(str8);
                orderInterfaceParam.pv_sid = "" + JDMaInterface.getOpen_count();
                orderInterfaceParam.pv_seq = "" + JDMaInterface.getSeq();
                orderInterfaceParam.sku_tag = jSONObject.optString("sku_tag");
                orderInterfaceParam.cart_jdv = jSONObject.optString("cart_jdv");
                orderInterfaceParam.cart_sid = jSONObject.optString("cart_sid");
                orderInterfaceParam.cart_seq = jSONObject.optString("cart_seq");
                orderInterfaceParam.cart_ts = jSONObject.optString("cart_ts");
                orderInterfaceParam.lv1_page_name = jSONObject.optString("lv1_page_name");
                orderInterfaceParam.lv1_page_param = jSONObject.optString("lv1_page_param");
                orderInterfaceParam.lv1_event_id = jSONObject.optString("lv1_event_id");
                orderInterfaceParam.lv1_event_param = jSONObject.optString("lv1_event_param");
                orderInterfaceParam.lv2_page_name = jSONObject.optString("lv2_page_name");
                orderInterfaceParam.lv2_page_param = jSONObject.optString("lv2_page_param");
                orderInterfaceParam.lv2_event_id = jSONObject.optString("lv2_event_id");
                orderInterfaceParam.lv2_event_param = jSONObject.optString("lv2_event_param");
                orderInterfaceParam.lv3_page_name = jSONObject.optString("lv3_page_name");
                orderInterfaceParam.lv3_page_param = jSONObject.optString("lv3_page_param");
                orderInterfaceParam.lv3_event_id = jSONObject.optString("lv3_event_id");
                orderInterfaceParam.lv3_event_param = jSONObject.optString("lv3_event_param");
                orderInterfaceParam.lv4_page_name = jSONObject.optString("lv4_page_name");
                orderInterfaceParam.lv4_page_param = jSONObject.optString("lv4_page_param");
                orderInterfaceParam.lv4_event_id = jSONObject.optString("lv4_event_id");
                orderInterfaceParam.lv4_event_param = jSONObject.optString("lv4_event_param");
                orderInterfaceParam.lv5_page_name = jSONObject.optString("lv5_page_name");
                orderInterfaceParam.lv5_page_param = jSONObject.optString("lv5_page_param");
                orderInterfaceParam.lv5_event_id = jSONObject.optString("lv5_event_id");
                orderInterfaceParam.lv5_event_param = jSONObject.optString("lv5_event_param");
            }
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
        orderInterfaceParam.order_total_fee = str2;
        orderInterfaceParam.order_ts = getCurrentMicrosecond();
        orderInterfaceParam.prod_id = str4;
        orderInterfaceParam.quantity = str5;
        orderInterfaceParam.sale_ord_id = str;
        orderInterfaceParam.pin = getUserPin();
        orderInterfaceParam.ord_ext = str6;
        orderInterfaceParam.map = hashMap;
        JDMaInterface.sendOrderData(context, maInitCommonInfo, orderInterfaceParam);
        deliver(orderInterfaceParam);
    }

    public static void sendOrderExtend(Context context, String str, String str2, String str3, String str4, String str5, HashMap<String, String> hashMap, String str6, HashMap<String, String> hashMap2) {
        getMaInitCommonInfo(context);
        OrderInterfaceParam orderInterfaceParam = new OrderInterfaceParam();
        String findSource = JDMtaCacheTable.findSource(str3);
        if (OKLog.D) {
            String str7 = TAG;
            OKLog.i(str7, "sendOrderDatas 1map=productId=" + str3);
            OKLog.i(str7, "sendOrderDatas 1map=sourceJson=" + findSource);
        }
        try {
            if (TextUtils.isEmpty(findSource)) {
                findSource = generateCurrentSourceObject().toString();
            }
            if (!TextUtils.isEmpty(findSource)) {
                JSONObject jSONObject = new JSONObject(findSource);
                String str8 = (hashMap == null || !hashMap.containsKey(BaseEvent.LBS_SCENE_ID)) ? "" : hashMap.get(BaseEvent.LBS_SCENE_ID);
                orderInterfaceParam.lat = getLatitude(str8);
                orderInterfaceParam.lon = getLongitude(str8);
                orderInterfaceParam.pv_sid = "" + JDMaInterface.getOpen_count();
                orderInterfaceParam.pv_seq = "" + JDMaInterface.getSeq();
                orderInterfaceParam.sku_tag = jSONObject.optString("sku_tag");
                orderInterfaceParam.cart_jdv = jSONObject.optString("cart_jdv");
                orderInterfaceParam.cart_sid = jSONObject.optString("cart_sid");
                orderInterfaceParam.cart_seq = jSONObject.optString("cart_seq");
                orderInterfaceParam.cart_ts = jSONObject.optString("cart_ts");
                orderInterfaceParam.lv1_page_name = jSONObject.optString("lv1_page_name");
                orderInterfaceParam.lv1_page_param = jSONObject.optString("lv1_page_param");
                orderInterfaceParam.lv1_event_id = jSONObject.optString("lv1_event_id");
                orderInterfaceParam.lv1_event_param = jSONObject.optString("lv1_event_param");
                orderInterfaceParam.lv2_page_name = jSONObject.optString("lv2_page_name");
                orderInterfaceParam.lv2_page_param = jSONObject.optString("lv2_page_param");
                orderInterfaceParam.lv2_event_id = jSONObject.optString("lv2_event_id");
                orderInterfaceParam.lv2_event_param = jSONObject.optString("lv2_event_param");
                orderInterfaceParam.lv3_page_name = jSONObject.optString("lv3_page_name");
                orderInterfaceParam.lv3_page_param = jSONObject.optString("lv3_page_param");
                orderInterfaceParam.lv3_event_id = jSONObject.optString("lv3_event_id");
                orderInterfaceParam.lv3_event_param = jSONObject.optString("lv3_event_param");
                orderInterfaceParam.lv4_page_name = jSONObject.optString("lv4_page_name");
                orderInterfaceParam.lv4_page_param = jSONObject.optString("lv4_page_param");
                orderInterfaceParam.lv4_event_id = jSONObject.optString("lv4_event_id");
                orderInterfaceParam.lv4_event_param = jSONObject.optString("lv4_event_param");
                orderInterfaceParam.lv5_page_name = jSONObject.optString("lv5_page_name");
                orderInterfaceParam.lv5_page_param = jSONObject.optString("lv5_page_param");
                orderInterfaceParam.lv5_event_id = jSONObject.optString("lv5_event_id");
                orderInterfaceParam.lv5_event_param = jSONObject.optString("lv5_event_param");
            }
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
        orderInterfaceParam.order_total_fee = str2;
        orderInterfaceParam.order_ts = getCurrentMicrosecond();
        orderInterfaceParam.prod_id = str3;
        orderInterfaceParam.quantity = str4;
        orderInterfaceParam.sale_ord_id = str;
        orderInterfaceParam.pin = getUserPin();
        orderInterfaceParam.ord_ext = str5;
        orderInterfaceParam.map = hashMap;
        orderInterfaceParam.ord_type = str6;
        if (hashMap2 != null) {
            if (hashMap2.containsKey(BaseEvent.SCENE)) {
                orderInterfaceParam.scene = hashMap2.get(BaseEvent.SCENE);
            }
            if (hashMap2.containsKey("lat")) {
                orderInterfaceParam.lat = hashMap2.get("lat");
            }
            if (hashMap2.containsKey(LON)) {
                orderInterfaceParam.lon = hashMap2.get(LON);
            }
        }
        JDMaInterface.sendOrderData(context, maInitCommonInfo, orderInterfaceParam);
        deliver(orderInterfaceParam);
    }

    public static void sendPVExtend(Context context, Object obj, String str, String str2, String str3, String str4, String str5, String str6, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        String str7;
        OKLog.w(LIB_TAG, "sendPV, page_param: " + str);
        getMaInitCommonInfo(context);
        if (context == null) {
            return;
        }
        if (obj == null) {
            str7 = "";
        } else if (obj instanceof String) {
            str7 = obj.toString();
        } else {
            str7 = obj.getClass().getName();
        }
        PvInterfaceParam pvInterfaceParam = new PvInterfaceParam();
        String str8 = (hashMap == null || !hashMap.containsKey(BaseEvent.LBS_SCENE_ID)) ? "" : hashMap.get(BaseEvent.LBS_SCENE_ID);
        pvInterfaceParam.lat = getLatitude(str8);
        pvInterfaceParam.lon = getLongitude(str8);
        pvInterfaceParam.page_name = str7;
        pvInterfaceParam.page_param = str;
        pvInterfaceParam.pin = getUserPin();
        pvInterfaceParam.page_id = str2;
        pvInterfaceParam.sku = str4;
        pvInterfaceParam.sku_tag = str5;
        pvInterfaceParam.click_url = str6;
        pvInterfaceParam.lastPage_param = oldPageParam;
        pvInterfaceParam.loadTime = "0";
        pvInterfaceParam.shp = str3;
        pvInterfaceParam.ref_event_id = lastEvent_id;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("event_param", lastEvent_param);
            jSONObject.put("page_param", lastPage_Param);
            pvInterfaceParam.ref_event_param = jSONObject.toString();
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
        if (hashMap == null) {
            hashMap = new HashMap<>();
        }
        try {
            hashMap.put("isfold", UnAndroidUtils.isFoldScreen() ? "1" : "0");
            if (context instanceof Activity) {
                hashMap.put("pageposition", String.valueOf(UnAndroidUtils.getWindowLocSate((Activity) context)));
            } else if (obj instanceof Activity) {
                hashMap.put("pageposition", String.valueOf(UnAndroidUtils.getWindowLocSate((Activity) obj)));
            } else if ((obj instanceof Fragment) && ((Fragment) obj).getActivity() != null) {
                hashMap.put("pageposition", String.valueOf(UnAndroidUtils.getWindowLocSate(((Fragment) obj).getActivity())));
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        hashMap.put("extension_id", lastExtensionId);
        hashMap.put("unionwsws", DeviceFingerUtil.getUnionFingerPrint());
        hashMap.put(ActivityNumController.KEY_ACT_STACK_INFO, ActivityNumController.getActStackStr());
        pvInterfaceParam.lastPage = oldClassName;
        if (!str7.endsWith("WebActivity")) {
            oldClassName = str2;
            oldPageParam = str;
        }
        try {
            if (uid == -1) {
                uid = context.getPackageManager().getApplicationInfo(JdSdk.getInstance().getApplication().getPackageName(), 1).uid;
            }
            long uidRxBytes = TrafficStats.getUidRxBytes(uid);
            long uidTxBytes = TrafficStats.getUidTxBytes(uid);
            hashMap.put("c_r_byte", "" + uidRxBytes);
            hashMap.put("c_t_byte", "" + uidTxBytes);
        } catch (PackageManager.NameNotFoundException e4) {
            OKLog.e(TAG, e4);
        } catch (Throwable th) {
            OKLog.e(TAG, th);
        }
        hashMap.put(BaseArchUtil.MTA_KEY_PAGE_ROLLBACK, BaseArchUtil.getRollbackPVTag(obj));
        pvInterfaceParam.map = hashMap;
        currentPageId.postValue(str2);
        if (hashMap2 != null) {
            if (hashMap2.containsKey(BaseEvent.SCENE)) {
                pvInterfaceParam.scene = hashMap2.get(BaseEvent.SCENE);
            }
            if (hashMap2.containsKey("lat")) {
                pvInterfaceParam.lat = hashMap2.get("lat");
            }
            if (hashMap2.containsKey(LON)) {
                pvInterfaceParam.lon = hashMap2.get(LON);
            }
        }
        JDMaInterface.sendPvData(context, maInitCommonInfo, pvInterfaceParam);
        deliver(pvInterfaceParam);
    }

    public static void sendPagePv(Context context, Object obj, String str, String str2, String str3) {
        sendPagePv(context, obj, str, str2, str3, "", "");
    }

    public static void sendPagePv4Watch(Context context, String str, String str2, String str3, long j2) {
        OKLog.w(LIB_TAG, "sendPagePv4Watch(), page_param: " + str2);
        getMaInitCommonInfo(context);
        if (context == null) {
            return;
        }
        PvInterfaceParam pvInterfaceParam = new PvInterfaceParam();
        pvInterfaceParam.lat = getLatitude("");
        pvInterfaceParam.lon = getLongitude("");
        pvInterfaceParam.page_name = str;
        pvInterfaceParam.page_param = str2;
        pvInterfaceParam.pin = getUserPin();
        pvInterfaceParam.page_id = str3;
        pvInterfaceParam.lastPage = "";
        pvInterfaceParam.lastPage_param = "";
        pvInterfaceParam.loadTime = "0";
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            hashMap.put("isfold", UnAndroidUtils.isFoldScreen() ? "1" : "0");
            if (context instanceof Activity) {
                hashMap.put("pageposition", String.valueOf(UnAndroidUtils.getWindowLocSate((Activity) context)));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            if (uid == -1) {
                uid = context.getPackageManager().getApplicationInfo(JdSdk.getInstance().getApplication().getPackageName(), 1).uid;
            }
            long uidRxBytes = TrafficStats.getUidRxBytes(uid);
            long uidTxBytes = TrafficStats.getUidTxBytes(uid);
            hashMap.put("c_r_byte", "" + uidRxBytes);
            hashMap.put("c_t_byte", "" + uidTxBytes);
            hashMap.put("watch_sid", "" + j2);
        } catch (Throwable th) {
            OKLog.e(TAG, th);
        }
        pvInterfaceParam.map = hashMap;
        currentPageId.postValue(str3);
        JDMaInterface.sendPvData(context, maInitCommonInfo, pvInterfaceParam);
        deliver(pvInterfaceParam);
    }

    public static void sendPropertyData(Context context, String str, String str2, String str3, String str4) {
        getMaInitCommonInfo(context);
        if (context == null || str2 == null) {
            return;
        }
        PropertyInterfaceParam propertyInterfaceParam = new PropertyInterfaceParam();
        propertyInterfaceParam.pin = getUserPin();
        propertyInterfaceParam.page_ts = str;
        propertyInterfaceParam.lon = getLongitude("");
        propertyInterfaceParam.lat = getLatitude("");
        propertyInterfaceParam.page_name = str2;
        propertyInterfaceParam.page_param = str3;
        propertyInterfaceParam.end_ts = str4;
        propertyInterfaceParam.ldns_ip = getIPAddressInternal();
        JDMaInterface.sendPropertyData(context, maInitCommonInfo, propertyInterfaceParam);
        deliver(propertyInterfaceParam);
    }

    public static void sendPropertyExtend(Context context, String str, String str2, String str3, String str4, HashMap<String, String> hashMap) {
        getMaInitCommonInfo(context);
        if (context == null || str2 == null) {
            return;
        }
        PropertyInterfaceParam propertyInterfaceParam = new PropertyInterfaceParam();
        propertyInterfaceParam.pin = getUserPin();
        propertyInterfaceParam.page_ts = str;
        propertyInterfaceParam.lon = getLongitude("");
        propertyInterfaceParam.lat = getLatitude("");
        propertyInterfaceParam.page_name = str2;
        propertyInterfaceParam.page_param = str3;
        propertyInterfaceParam.end_ts = str4;
        propertyInterfaceParam.ldns_ip = getIPAddressInternal();
        if (hashMap != null) {
            if (hashMap.containsKey(BaseEvent.SCENE)) {
                propertyInterfaceParam.scene = hashMap.get(BaseEvent.SCENE);
            }
            if (hashMap.containsKey("lat")) {
                propertyInterfaceParam.lat = hashMap.get("lat");
            }
            if (hashMap.containsKey(LON)) {
                propertyInterfaceParam.lon = hashMap.get(LON);
            }
        }
        JDMaInterface.sendPropertyData(context, maInitCommonInfo, propertyInterfaceParam);
        deliver(propertyInterfaceParam);
    }

    public static void sendPvData(MtaPvParam mtaPvParam) {
        if (mtaPvParam == null) {
            return;
        }
        OKLog.w(LIB_TAG, "sendPvData(), pvParam: " + mtaPvParam);
        getMaInitCommonInfo(JdSdk.getInstance().getApplicationContext());
        completeCommonParams(mtaPvParam);
        currentPageId.postValue(mtaPvParam.pageId);
        JDMaInterface.sendData(JdSdk.getInstance().getApplicationContext(), maInitCommonInfo, mtaPvParam);
        deliver(mtaPvParam);
    }

    @Deprecated
    public static void sendUnplInfo(String str) {
    }

    public static void sendWebviewLoadData(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        getMaInitCommonInfo(context);
        if (context == null || str == null) {
            return;
        }
        PropertyInterfaceParam propertyInterfaceParam = new PropertyInterfaceParam();
        propertyInterfaceParam.pin = getUserPin();
        propertyInterfaceParam.page_ts = getCurrentMicrosecond();
        propertyInterfaceParam.lon = getLongitude("");
        propertyInterfaceParam.lat = getLatitude("");
        propertyInterfaceParam.page_name = str;
        propertyInterfaceParam.page_param = str2;
        propertyInterfaceParam.ldns_ip = getIPAddressInternal();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("mload_type", str3);
        hashMap.put("mload_ts", str5);
        hashMap.put("mload_url", str4);
        hashMap.put("mload_endts", str6);
        hashMap.put("mload_status", str7);
        propertyInterfaceParam.map = hashMap;
        JDMaInterface.sendPropertyData(context, maInitCommonInfo, propertyInterfaceParam);
        deliver(propertyInterfaceParam);
    }

    public static void set1Page(String str, String str2, String str3, String str4) {
        set2Page(null, "", "", "");
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str3)) {
            if (TextUtils.isEmpty(str)) {
                str = "";
            } else if (str.endsWith("ProductDetailNewActivity")) {
                str = "com.jingdong.app.mall.product.ProductDetailNewLastActivity";
            }
            String[] strArr = page1str;
            strArr[0] = str;
            strArr[1] = str2;
            strArr[2] = str3;
            strArr[3] = str4;
            if (OKLog.D) {
                OKLog.i(TAG, "set1Page==...........page1str[0]=" + page1str[0] + ".......page1str[1]=" + page1str[1] + ".......page1str[2]=" + page1str[2] + ".......page1str[3]=" + page1str[3]);
                return;
            }
            return;
        }
        if (OKLog.W) {
            OKLog.w(TAG, "clear 1page data since pageName:" + str + " or clickId:" + str3 + " is empty");
        }
        String[] strArr2 = page1str;
        strArr2[0] = "";
        strArr2[1] = "";
        strArr2[2] = "";
        strArr2[3] = "";
    }

    public static void set2Page(String str, String str2, String str3, String str4) {
        set3Page(null, "", "", "");
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str3)) {
            if (TextUtils.isEmpty(str)) {
                str = "";
            } else if (str.endsWith("ProductDetailNewActivity")) {
                str = "com.jingdong.app.mall.product.ProductDetailNewLastActivity";
            }
            String[] strArr = page2str;
            strArr[0] = str;
            strArr[1] = str2;
            strArr[2] = str3;
            strArr[3] = str4;
            if (OKLog.D) {
                OKLog.i(TAG, "set2Page==...........page2str[0]=" + page2str[0] + ".......page2str[1]=" + page2str[1] + ".......page2str[2]=" + page2str[2] + ".......page2str[3]=" + page2str[3]);
                return;
            }
            return;
        }
        if (OKLog.W) {
            OKLog.w(TAG, "clear 2page data since pageName:" + str + " or clickId:" + str3 + " is empty");
        }
        String[] strArr2 = page2str;
        strArr2[0] = "";
        strArr2[1] = "";
        strArr2[2] = "";
        strArr2[3] = "";
    }

    public static void set3Page(String str, String str2, String str3, String str4) {
        set4Page(null, "", "", "");
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str3)) {
            if (TextUtils.isEmpty(str)) {
                str = "";
            } else if (str.endsWith("ProductDetailNewActivity")) {
                str = "com.jingdong.app.mall.product.ProductDetailNewLastActivity";
            }
            String[] strArr = page3str;
            strArr[0] = str;
            strArr[1] = str2;
            strArr[2] = str3;
            strArr[3] = str4;
            if (OKLog.D) {
                OKLog.i(TAG, "set3Page==...........page3str[0]=" + page3str[0] + ".......page3str[1]=" + page3str[1] + ".......page3str[2]=" + page3str[2] + ".......page3str[3]=" + page3str[3]);
                return;
            }
            return;
        }
        if (OKLog.W) {
            OKLog.w(TAG, "clear 3page data since pageName:" + str + " or clickId:" + str3 + " is empty");
        }
        String[] strArr2 = page3str;
        strArr2[0] = "";
        strArr2[1] = "";
        strArr2[2] = "";
        strArr2[3] = "";
    }

    public static void set4Page(String str, String str2, String str3, String str4) {
        set5Page(null, "", "", "");
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str3)) {
            if (TextUtils.isEmpty(str)) {
                str = "";
            } else if (str.endsWith("ProductDetailNewActivity")) {
                str = "com.jingdong.app.mall.product.ProductDetailNewLastActivity";
            }
            String[] strArr = page4str;
            strArr[0] = str;
            strArr[1] = str2;
            strArr[2] = str3;
            strArr[3] = str4;
            if (OKLog.D) {
                OKLog.i(TAG, "set3Page==...........page3str[0]=" + page3str[0] + ".......page3str[1]=" + page3str[1] + ".......page3str[2]=" + page3str[2] + ".......page3str[3]=" + page3str[3]);
                return;
            }
            return;
        }
        if (OKLog.W) {
            OKLog.w(TAG, "clear 4page data since pageName:" + str + " or clickId:" + str3 + " is empty");
        }
        String[] strArr2 = page4str;
        strArr2[0] = "";
        strArr2[1] = "";
        strArr2[2] = "";
        strArr2[3] = "";
    }

    public static void set5Page(String str, String str2, String str3, String str4) {
        String str5 = TAG;
        OKLog.d(str5, "set5Page pageName =" + str);
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str3)) {
            if (TextUtils.isEmpty(str)) {
                str = "";
            } else if (str.endsWith("ProductDetailNewActivity")) {
                str = "com.jingdong.app.mall.product.ProductDetailNewLastActivity";
            }
            String[] strArr = page5str;
            strArr[0] = str;
            strArr[1] = str2;
            strArr[2] = str3;
            strArr[3] = str4;
            if (OKLog.D) {
                OKLog.d(str5, "set3Page==...........page3str[0]=" + page3str[0] + ".......page3str[1]=" + page3str[1] + ".......page3str[2]=" + page3str[2] + ".......page3str[3]=" + page3str[3]);
                return;
            }
            return;
        }
        if (OKLog.W) {
            OKLog.w(TAG, "clear 5page data since pageName:" + str + " or clickId:" + str3 + " is empty");
        }
        String[] strArr2 = page5str;
        strArr2[0] = "";
        strArr2[1] = "";
        strArr2[2] = "";
        strArr2[3] = "";
    }

    public static void setAppMode(AppMode appMode) {
        JDMaInterface.setAppMode(appMode);
    }

    public static void setAreaCode(String str) {
        JDMaInterface.setAreaCode(str);
    }

    public static void setImeiTag() {
        JDMaInterface.setImeiTag("1");
    }

    public static void setMaAbTest(String str) {
        JDMaInterface.setMaAbTest(str);
    }

    public static void setMaBGroup(String str) {
        JDMaInterface.setMaBGroup(str);
    }

    public static void setMaIsSparse(String str) {
        JDMaInterface.setMaIsSparse(str);
    }

    public static void setModeTag(String str) {
        JDMaInterface.setModeTag(str);
    }

    public static void setMtaContent(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("jdv")) {
                jSONObject.remove("jdv");
            }
            JDMaInterface.setMtaContent4Inside(jSONObject.toString());
            if (jSONObject.has("event_series")) {
                JSONObject optJSONObject = jSONObject.optJSONObject("event_series");
                String optString = optJSONObject.optString("event_level");
                String optString2 = optJSONObject.optString("event_id");
                String optString3 = optJSONObject.optString("event_param");
                String optString4 = optJSONObject.optString("page_name");
                String optString5 = optJSONObject.optString("page_param");
                if ("1".equals(optString)) {
                    set1Page(optString4, optString5, optString2, optString3);
                } else if ("2".equals(optString)) {
                    set2Page(optString4, optString5, optString2, optString3);
                } else if ("3".equals(optString)) {
                    set3Page(optString4, optString5, optString2, optString3);
                } else if ("4".equals(optString)) {
                    set4Page(optString4, optString5, optString2, optString3);
                } else if ("5".equals(optString)) {
                    set5Page(optString4, optString5, optString2, optString3);
                }
            }
            setSessionInfo(context, str);
        } catch (JSONException e2) {
            OKLog.e(TAG, e2);
        }
    }

    public static void setMtaContent4OpenApp(Context context, String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        OKLog.w(LIB_TAG, "setMtaContent4OpenApp(), isFromMInside: " + z + " content: " + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (z && jSONObject.has("jdv")) {
                String str2 = TAG;
                OKLog.d(str2, "setMtaContent4OpenApp:has jdv");
                OKLog.d(str2, "setMtaContent4OpenApp:has overwrite_jdv:" + jSONObject.has("overwrite_jdv"));
                if (!jSONObject.has("overwrite_jdv") || !jSONObject.getBoolean("overwrite_jdv")) {
                    jSONObject.remove("jdv");
                }
            }
            JDMaInterface.setMtaContent4OpenApp(context, jSONObject.toString());
            if (jSONObject.has("event_series")) {
                JSONObject optJSONObject = jSONObject.optJSONObject("event_series");
                String optString = optJSONObject.optString("event_level");
                String optString2 = optJSONObject.optString("event_id");
                String optString3 = optJSONObject.optString("event_param");
                String optString4 = optJSONObject.optString("page_name");
                String optString5 = optJSONObject.optString("page_param");
                if ("1".equals(optString)) {
                    set1Page(optString4, optString5, optString2, optString3);
                } else if ("2".equals(optString)) {
                    set2Page(optString4, optString5, optString2, optString3);
                } else if ("3".equals(optString)) {
                    set3Page(optString4, optString5, optString2, optString3);
                } else if ("4".equals(optString)) {
                    set4Page(optString4, optString5, optString2, optString3);
                } else if ("5".equals(optString)) {
                    set5Page(optString4, optString5, optString2, optString3);
                }
            }
        } catch (JSONException e2) {
            OKLog.e(TAG, e2);
        }
    }

    public static void setMtaContentUnion(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        OKLog.w(LIB_TAG, "setMtaContentUnion(), content: " + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("jdv")) {
                jSONObject.remove("jdv");
            }
            JDMaInterface.setMtaContent4Inside(jSONObject.toString());
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
    }

    public static void setMtaContentUnion4OpenApp(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        OKLog.w(LIB_TAG, "setMtaContentUnion4OpenApp(), content: " + str);
        JDMaInterface.setMtaContent4OpenApp(JdSdk.getInstance().getApplicationContext(), str);
    }

    public static void setOAID() {
        JDMaInterface.setOAID(BaseInfo.getOAID());
    }

    @JavascriptInterface
    public static void setSessionFromH5(IRouterParams iRouterParams) {
        if (iRouterParams == null || TextUtils.isEmpty(iRouterParams.getRouterParam())) {
            return;
        }
        try {
            OKLog.w(LIB_TAG, "setSessionFromH5(), params.getRouterParam(): " + iRouterParams.getRouterParam());
            JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
            if (jSONObject.has("pv_sid") && jSONObject.has("pv_seq")) {
                JDMaInterface.setSessionInfo(JdSdk.getInstance().getApplicationContext(), iRouterParams.getRouterParam());
            }
            if (jSONObject.has("jdv")) {
                JDMaInterface.setJdv(jSONObject.optString("jdv"));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void setSessionInfo(Context context, String str) {
        JDMaInterface.setSessionInfo(context, str);
    }

    public static void setSourceData(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        JDMaInterface.setSourceData(str, str2, JdSdk.getInstance().getApplicationContext());
    }

    public static void setUserProperty(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        JDMaInterface.setUserProperty(str, str2);
    }

    public static void updateJdv(Context context, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            OKLog.w(LIB_TAG, "updateJdv(), content: " + str);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("jdv", str);
            setMtaContent4OpenApp(context, jSONObject.toString(), false);
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
    }

    private static void updateLastClickParam(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        lastEvent_id = str;
        lastEvent_param = str2;
        lastPage_Param = str3;
    }

    private static void updateLastExtensionID(HashMap<String, String> hashMap) {
        if (hashMap == null || !hashMap.containsKey("extension_id")) {
            return;
        }
        String str = hashMap.get("extension_id");
        if (TextUtils.isEmpty(str)) {
            return;
        }
        lastExtensionId = str;
    }

    public static void updateUnpl(String str, String str2, String str3) {
        JDMaInterface.updateUnpl(str, str2, str3);
    }

    public static void sendClickDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, HashMap<String, String> hashMap) {
        getMaInitCommonInfo(context);
        ClickInterfaceParam clickInterfaceParam = new ClickInterfaceParam();
        String str13 = (hashMap == null || !hashMap.containsKey(BaseEvent.LBS_SCENE_ID)) ? "" : hashMap.get(BaseEvent.LBS_SCENE_ID);
        clickInterfaceParam.lat = getLatitude(str13);
        clickInterfaceParam.lon = getLongitude(str13);
        clickInterfaceParam.event_id = str;
        clickInterfaceParam.event_param = str2;
        clickInterfaceParam.event_func = str3;
        clickInterfaceParam.page_id = str4;
        clickInterfaceParam.page_name = str5;
        clickInterfaceParam.page_param = str6;
        if (str7 != null) {
            if (str7.equals("ProductDetailNewActivity")) {
                clickInterfaceParam.next_page_name = "com.jingdong.app.mall.product.ProductDetailNewLastActivity";
            } else {
                clickInterfaceParam.next_page_name = str7;
            }
        }
        clickInterfaceParam.jsonParam = str8;
        clickInterfaceParam.shop = str9;
        clickInterfaceParam.ord = str10;
        clickInterfaceParam.sku = str11;
        clickInterfaceParam.sku_tag = str12;
        clickInterfaceParam.map = hashMap;
        updateLastExtensionID(hashMap);
        updateLastClickParam(str, str2, str6);
        clickInterfaceParam.pin = getUserPin();
        lastEventId = str;
        JDMaInterface.sendClickData(context, maInitCommonInfo, clickInterfaceParam);
        deliver(clickInterfaceParam);
        boolean handle1Page = handle1Page(context, str, str2, str3, str5, str6);
        if (!handle1Page) {
            handle1Page = handle2Page(context, str, str2, str3, str5, str6);
        }
        if (!handle1Page) {
            handle1Page = handle3Page(context, str, str2, str3, str5, str6);
        }
        if (!handle1Page) {
            handle1Page = handle4Page(context, str, str2, str3, str5, str6);
        }
        if (handle1Page) {
            return;
        }
        handle5Page(context, str, str2, str3, str5, str6);
    }

    public static void sendCommonData(Context context, String str, String str2, String str3, Object obj, String str4, Class cls, String str5) {
        sendCommonData(context, str, str2, str3, obj, str4, cls, str5, "");
    }

    public static void sendExposureDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, HashMap<String, String> hashMap) {
        sendExposureDataWithExt(context, str, str2, str3, str4, str5, str6, "", "", "", hashMap);
    }

    public static void sendOrderDatas(Context context, String str, String str2, String str3, String str4, boolean z, String str5, String str6) {
        sendOrderDatasWithExt(context, str, str2, str3, str4, z, str5, null, str6);
    }

    public static void sendPagePv(Context context, Object obj, String str, String str2, String str3, String str4, String str5) {
        sendPagePv(context, obj, str, str2, str3, "", str4, str5);
    }

    public static void removeUserProperty(String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        JDMaInterface.removeUserProperty(strArr);
    }

    public static void sendCommonData(Context context, String str, String str2, String str3, Object obj, String str4, Class cls, String str5, String str6) {
        sendCommonDataWithExt(context, str, str2, str3, obj, str4, cls, str5, str6, (HashMap<String, String>) null);
    }

    public static void sendCommonDataForPromotionListPage(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6, String str7) {
        getMaInitCommonInfo(context);
        String obj2 = obj != null ? obj.toString() : "";
        ClickInterfaceParam clickInterfaceParam = new ClickInterfaceParam();
        clickInterfaceParam.lat = getLatitude("");
        clickInterfaceParam.lon = getLongitude("");
        clickInterfaceParam.event_id = str;
        clickInterfaceParam.event_param = str2;
        if (!TextUtils.isEmpty(str5)) {
            clickInterfaceParam.next_page_name = str5;
        }
        clickInterfaceParam.page_name = obj2;
        clickInterfaceParam.page_param = str4;
        clickInterfaceParam.page_id = str7;
        clickInterfaceParam.event_func = str3;
        clickInterfaceParam.pin = getUserPin();
        lastEventId = str;
        updateLastClickParam(str, str2, str4);
        JDMaInterface.sendClickData(context, maInitCommonInfo, clickInterfaceParam);
        deliver(clickInterfaceParam);
        boolean handle1Page = handle1Page(context, str, str2, str3, obj2, str4);
        if (!handle1Page) {
            handle1Page = handle2Page(context, str, str2, str3, obj2, str4);
        }
        if (!handle1Page) {
            handle1Page = handle3Page(context, str, str2, str3, obj2, str4);
        }
        if (!handle1Page) {
            handle1Page = handle4Page(context, str, str2, str3, obj2, str4);
        }
        if (handle1Page) {
            return;
        }
        handle5Page(context, str, str2, str3, obj2, str4);
    }

    public static void sendExposureDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, HashMap<String, String> hashMap) {
        getMaInitCommonInfo(context);
        if (context == null) {
            return;
        }
        ExposureInterfaceParam exposureInterfaceParam = new ExposureInterfaceParam();
        String str10 = (hashMap == null || !hashMap.containsKey(BaseEvent.LBS_SCENE_ID)) ? "" : hashMap.get(BaseEvent.LBS_SCENE_ID);
        exposureInterfaceParam.lat = getLatitude(str10);
        exposureInterfaceParam.lon = getLongitude(str10);
        exposureInterfaceParam.page_name = str4;
        exposureInterfaceParam.page_id = str3;
        exposureInterfaceParam.page_param = str5;
        exposureInterfaceParam.pin = getUserPin();
        exposureInterfaceParam.orderId = str8;
        exposureInterfaceParam.shopId = str7;
        exposureInterfaceParam.eventId = str;
        exposureInterfaceParam.eventParam = str2;
        exposureInterfaceParam.jsonParam = str6;
        if (hashMap == null) {
            hashMap = new HashMap<>();
        }
        hashMap.put("pv_sid", "" + JDMaInterface.getOpen_count());
        hashMap.put("pv_seq", "" + JDMaInterface.getSeq());
        hashMap.put("sku_tag", str9);
        exposureInterfaceParam.map = hashMap;
        JDMaInterface.sendExposureData(context, maInitCommonInfo, exposureInterfaceParam);
        deliver(exposureInterfaceParam);
    }

    public static void sendPagePv(Context context, Object obj, String str, String str2, String str3, String str4, String str5, String str6) {
        sendPagePv(context, obj, str, str2, str3, str4, str5, str6, null);
    }

    public static void setUserProperty(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        JDMaInterface.setUserProperty(map);
    }

    public static void onClick(Context context, String str, String str2, String str3) {
        try {
            sendCommonData(context, str, str3, "onClick", str2, "", "", "");
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static void onClickWithPageId(Context context, String str, String str2, String str3, String str4) {
        try {
            sendCommonData(context, str, str3, "onClick", str2, "", "", "", str4, "");
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static void sendCommonData(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6, String str7) {
        sendCommonDataWithExt(context, str, str2, str3, obj, str4, str5, str6, str7, (HashMap<String, String>) null);
    }

    public static void sendPagePv(Context context, Object obj, String str, String str2, String str3, String str4, String str5, String str6, HashMap<String, String> hashMap) {
        String str7;
        OKLog.w(LIB_TAG, "sendPagePv, page_param: " + str);
        getMaInitCommonInfo(context);
        if (context == null) {
            return;
        }
        if (obj == null) {
            str7 = "";
        } else if (obj instanceof String) {
            str7 = obj.toString();
        } else {
            str7 = obj.getClass().getName();
        }
        PvInterfaceParam pvInterfaceParam = new PvInterfaceParam();
        String str8 = (hashMap == null || !hashMap.containsKey(BaseEvent.LBS_SCENE_ID)) ? "" : hashMap.get(BaseEvent.LBS_SCENE_ID);
        pvInterfaceParam.lat = getLatitude(str8);
        pvInterfaceParam.lon = getLongitude(str8);
        pvInterfaceParam.page_name = str7;
        pvInterfaceParam.page_param = str;
        pvInterfaceParam.pin = getUserPin();
        pvInterfaceParam.page_id = str2;
        pvInterfaceParam.sku = str4;
        pvInterfaceParam.sku_tag = str5;
        pvInterfaceParam.click_url = str6;
        pvInterfaceParam.lastPage_param = oldPageParam;
        pvInterfaceParam.loadTime = "0";
        pvInterfaceParam.shp = str3;
        pvInterfaceParam.ref_event_id = lastEvent_id;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("event_param", lastEvent_param);
            jSONObject.put("page_param", lastPage_Param);
            pvInterfaceParam.ref_event_param = jSONObject.toString();
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
        if (hashMap == null) {
            hashMap = new HashMap<>();
        }
        try {
            hashMap.put("isfold", UnAndroidUtils.isFoldScreen() ? "1" : "0");
            if (context instanceof Activity) {
                hashMap.put("pageposition", String.valueOf(UnAndroidUtils.getWindowLocSate((Activity) context)));
            } else if (obj instanceof Activity) {
                hashMap.put("pageposition", String.valueOf(UnAndroidUtils.getWindowLocSate((Activity) obj)));
            } else if ((obj instanceof Fragment) && ((Fragment) obj).getActivity() != null) {
                hashMap.put("pageposition", String.valueOf(UnAndroidUtils.getWindowLocSate(((Fragment) obj).getActivity())));
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        hashMap.put("extension_id", lastExtensionId);
        hashMap.put("unionwsws", DeviceFingerUtil.getUnionFingerPrint());
        hashMap.put(ActivityNumController.KEY_ACT_STACK_INFO, ActivityNumController.getActStackStr());
        pvInterfaceParam.lastPage = oldClassName;
        if (!str7.endsWith("WebActivity")) {
            oldClassName = str2;
            oldPageParam = str;
        }
        try {
            if (uid == -1) {
                uid = context.getPackageManager().getApplicationInfo(JdSdk.getInstance().getApplication().getPackageName(), 1).uid;
            }
            long uidRxBytes = TrafficStats.getUidRxBytes(uid);
            long uidTxBytes = TrafficStats.getUidTxBytes(uid);
            hashMap.put("c_r_byte", "" + uidRxBytes);
            hashMap.put("c_t_byte", "" + uidTxBytes);
        } catch (PackageManager.NameNotFoundException e4) {
            OKLog.e(TAG, e4);
        } catch (Throwable th) {
            OKLog.e(TAG, th);
        }
        hashMap.put(BaseArchUtil.MTA_KEY_PAGE_ROLLBACK, BaseArchUtil.getRollbackPVTag(obj));
        pvInterfaceParam.map = hashMap;
        currentPageId.postValue(str2);
        JDMaInterface.sendPvData(context, maInitCommonInfo, pvInterfaceParam);
        deliver(pvInterfaceParam);
    }

    public static void sendCommonData(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6) {
        sendCommonData(context, str, str2, str3, obj, str4, str5, str6, "");
    }

    public static void sendCommonData(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6, String str7, String str8) {
        sendCommonDataWithExt(context, str, str2, str3, obj, str4, str5, str6, str7, str8, null);
    }

    public static void onClick(Context context, String str, String str2, String str3, String str4) {
        try {
            sendCommonData(context, str, str3, "onClick", str2, str4, "", "");
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static void onClickWithPageId(Context context, String str, String str2, String str3, String str4, String str5) {
        try {
            sendCommonData(context, str, str3, "onClick", str2, str4, "", "", str5, "");
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    @Deprecated
    public static void sendCommonData(Context context, HashMap<String, String> hashMap) {
        if (OKLog.D) {
            OKLog.d(TAG, "this \"sendCommonData\" method deprecated, nothing happens!");
        }
    }

    public static void onClick(Context context, String str, String str2, String str3, String str4, String str5) {
        try {
            sendCommonData(context, str, "", "onClick", str2, str3, str4, str5);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static void sendExposureData(MtaExposureParam mtaExposureParam) {
        if (mtaExposureParam == null) {
            return;
        }
        getMaInitCommonInfo(JdSdk.getInstance().getApplicationContext());
        completeCommonParams(mtaExposureParam);
        JDMaInterface.sendData(JdSdk.getInstance().getApplicationContext(), maInitCommonInfo, mtaExposureParam);
        deliver(mtaExposureParam);
    }

    public static void sendEpData(Context context, Object obj, String str, String str2, String str3, String str4, String str5, String str6, String str7, HashMap<String, String> hashMap) {
        String str8;
        getMaInitCommonInfo(context);
        if (context == null) {
            return;
        }
        if (obj == null) {
            str8 = "";
        } else if (obj instanceof String) {
            str8 = obj.toString();
        } else {
            str8 = obj.getClass().getName();
        }
        ExposureInterfaceParam exposureInterfaceParam = new ExposureInterfaceParam();
        exposureInterfaceParam.lat = getLatitude("");
        exposureInterfaceParam.lon = getLongitude("");
        exposureInterfaceParam.page_name = str8;
        exposureInterfaceParam.page_id = str;
        exposureInterfaceParam.page_param = str2;
        exposureInterfaceParam.pin = getUserPin();
        exposureInterfaceParam.orderId = str6;
        exposureInterfaceParam.shopId = str5;
        exposureInterfaceParam.eventId = str3;
        exposureInterfaceParam.eventParam = str4;
        exposureInterfaceParam.ep_flag = "1";
        HashMap<String, String> hashMap2 = new HashMap<>();
        hashMap2.put("pv_sid", "" + JDMaInterface.getOpen_count());
        hashMap2.put("pv_seq", "" + JDMaInterface.getSeq());
        hashMap2.put("sku_tag", str7);
        exposureInterfaceParam.map = hashMap2;
        if (hashMap != null) {
            if (hashMap.containsKey(BaseEvent.SCENE)) {
                exposureInterfaceParam.scene = hashMap.get(BaseEvent.SCENE);
            }
            if (hashMap.containsKey("lat")) {
                exposureInterfaceParam.lat = hashMap.get("lat");
            }
            if (hashMap.containsKey(LON)) {
                exposureInterfaceParam.lon = hashMap.get(LON);
            }
        }
        JDMaInterface.sendExposureData(context, maInitCommonInfo, exposureInterfaceParam);
        deliver(exposureInterfaceParam);
    }

    public static void sendCommonDataWithExt(Context context, String str, String str2, String str3, Object obj, String str4, Class cls, String str5, String str6, HashMap<String, String> hashMap) {
        getMaInitCommonInfo(context);
        String str7 = "";
        String name = obj != null ? obj.getClass().getName() : "";
        ClickInterfaceParam clickInterfaceParam = new ClickInterfaceParam();
        if (hashMap != null && hashMap.containsKey(BaseEvent.LBS_SCENE_ID)) {
            str7 = hashMap.get(BaseEvent.LBS_SCENE_ID);
        }
        clickInterfaceParam.lat = getLatitude(str7);
        clickInterfaceParam.lon = getLongitude(str7);
        clickInterfaceParam.event_id = str;
        clickInterfaceParam.event_param = str2;
        clickInterfaceParam.page_id = str6;
        if (cls != null) {
            if (cls.getSimpleName().equals("ProductDetailNewActivity")) {
                clickInterfaceParam.next_page_name = "com.jingdong.app.mall.product.ProductDetailNewLastActivity";
            } else {
                clickInterfaceParam.next_page_name = cls.getName();
            }
        }
        clickInterfaceParam.page_name = name;
        clickInterfaceParam.page_param = str4;
        clickInterfaceParam.event_func = str3;
        clickInterfaceParam.map = hashMap;
        updateLastExtensionID(hashMap);
        updateLastClickParam(str, str2, str4);
        clickInterfaceParam.pin = getUserPin();
        lastEventId = str;
        JDMaInterface.sendClickData(context, maInitCommonInfo, clickInterfaceParam);
        deliver(clickInterfaceParam);
        boolean handle1Page = handle1Page(context, str, str2, str3, name, str4);
        if (!handle1Page) {
            handle1Page = handle2Page(context, str, str2, str3, name, str4);
        }
        if (!handle1Page) {
            handle1Page = handle3Page(context, str, str2, str3, name, str4);
        }
        if (!handle1Page) {
            handle1Page = handle4Page(context, str, str2, str3, name, str4);
        }
        if (handle1Page) {
            return;
        }
        handle5Page(context, str, str2, str3, name, str4);
    }

    public static void sendCommonData4ProductDetail(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        getMaInitCommonInfo(context);
        ClickInterfaceParam clickInterfaceParam = new ClickInterfaceParam();
        String str11 = (hashMap == null || !hashMap.containsKey(BaseEvent.LBS_SCENE_ID)) ? "" : hashMap.get(BaseEvent.LBS_SCENE_ID);
        clickInterfaceParam.lat = getLatitude(str11);
        clickInterfaceParam.lon = getLongitude(str11);
        clickInterfaceParam.event_id = str;
        clickInterfaceParam.event_param = str2;
        clickInterfaceParam.next_page_name = str9;
        clickInterfaceParam.page_name = str5;
        clickInterfaceParam.page_param = str6;
        clickInterfaceParam.pin = getUserPin();
        clickInterfaceParam.page_id = str4;
        clickInterfaceParam.shop = str8;
        clickInterfaceParam.sku_tag = str7;
        lastEventId = str;
        if (hashMap != null) {
            clickInterfaceParam.map = hashMap;
        }
        updateLastExtensionID(hashMap);
        updateLastClickParam(str, str2, str6);
        if (hashMap2 != null) {
            if (hashMap2.containsKey(BaseEvent.SCENE)) {
                clickInterfaceParam.scene = hashMap2.get(BaseEvent.SCENE);
            }
            if (hashMap2.containsKey("lat")) {
                clickInterfaceParam.lat = hashMap2.get("lat");
            }
            if (hashMap2.containsKey(LON)) {
                clickInterfaceParam.lon = hashMap2.get(LON);
            }
        }
        JDMaInterface.sendClickData(context, maInitCommonInfo, clickInterfaceParam);
        deliver(clickInterfaceParam);
        boolean handle1Page = handle1Page(context, str, str2, str3, str5, str6);
        if (!handle1Page) {
            handle1Page = handle2Page(context, str, str2, str3, str5, str6);
        }
        if (!handle1Page) {
            handle1Page = handle3Page(context, str, str2, str3, str5, str6);
        }
        if (!handle1Page) {
            handle1Page = handle4Page(context, str, str2, str3, str5, str6);
        }
        if (handle1Page) {
            return;
        }
        handle5Page(context, str, str2, str3, str5, str6);
    }

    public static void sendClickDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        getMaInitCommonInfo(context);
        ClickInterfaceParam clickInterfaceParam = new ClickInterfaceParam();
        String str13 = (hashMap == null || !hashMap.containsKey(BaseEvent.LBS_SCENE_ID)) ? "" : hashMap.get(BaseEvent.LBS_SCENE_ID);
        clickInterfaceParam.lat = getLatitude(str13);
        clickInterfaceParam.lon = getLongitude(str13);
        clickInterfaceParam.event_id = str;
        clickInterfaceParam.event_param = str2;
        clickInterfaceParam.event_func = str3;
        clickInterfaceParam.page_id = str4;
        clickInterfaceParam.page_name = str5;
        clickInterfaceParam.page_param = str6;
        if (str7 != null) {
            if (str7.equals("ProductDetailNewActivity")) {
                clickInterfaceParam.next_page_name = "com.jingdong.app.mall.product.ProductDetailNewLastActivity";
            } else {
                clickInterfaceParam.next_page_name = str7;
            }
        }
        clickInterfaceParam.jsonParam = str8;
        clickInterfaceParam.shop = str9;
        clickInterfaceParam.ord = str10;
        clickInterfaceParam.sku = str11;
        clickInterfaceParam.sku_tag = str12;
        clickInterfaceParam.map = hashMap;
        updateLastExtensionID(hashMap);
        updateLastClickParam(str, str2, str6);
        clickInterfaceParam.pin = getUserPin();
        lastEventId = str;
        if (hashMap2 != null) {
            if (hashMap2.containsKey(BaseEvent.SCENE)) {
                clickInterfaceParam.scene = hashMap2.get(BaseEvent.SCENE);
            }
            if (hashMap2.containsKey("lat")) {
                clickInterfaceParam.lat = hashMap2.get("lat");
            }
            if (hashMap2.containsKey(LON)) {
                clickInterfaceParam.lon = hashMap2.get(LON);
            }
        }
        JDMaInterface.sendClickData(context, maInitCommonInfo, clickInterfaceParam);
        deliver(clickInterfaceParam);
        boolean handle1Page = handle1Page(context, str, str2, str3, str5, str6);
        if (!handle1Page) {
            handle1Page = handle2Page(context, str, str2, str3, str5, str6);
        }
        if (!handle1Page) {
            handle1Page = handle3Page(context, str, str2, str3, str5, str6);
        }
        if (!handle1Page) {
            handle1Page = handle4Page(context, str, str2, str3, str5, str6);
        }
        if (handle1Page) {
            return;
        }
        handle5Page(context, str, str2, str3, str5, str6);
    }

    @Deprecated
    public static void sendEpData(Context context, String str, String str2, String str3, String str4, String str5) {
        sendEpData(context, str5, str4, str3, str, str2, "", "", "");
    }

    public static void sendCommonDataWithExt(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6, String str7, HashMap<String, String> hashMap) {
        String str8;
        getMaInitCommonInfo(context);
        String str9 = "";
        if (obj == null) {
            str8 = "";
        } else if (obj instanceof String) {
            str8 = (String) obj;
        } else {
            str8 = obj.getClass().getName();
        }
        ClickInterfaceParam clickInterfaceParam = new ClickInterfaceParam();
        if (hashMap != null && hashMap.containsKey(BaseEvent.LBS_SCENE_ID)) {
            str9 = hashMap.get(BaseEvent.LBS_SCENE_ID);
        }
        clickInterfaceParam.lat = getLatitude(str9);
        clickInterfaceParam.lon = getLongitude(str9);
        clickInterfaceParam.event_id = str;
        clickInterfaceParam.event_param = str2;
        clickInterfaceParam.page_name = str8;
        clickInterfaceParam.page_param = str4;
        clickInterfaceParam.page_id = str7;
        clickInterfaceParam.event_func = str3;
        clickInterfaceParam.map = hashMap;
        updateLastExtensionID(hashMap);
        updateLastClickParam(str, str2, str4);
        clickInterfaceParam.pin = getUserPin();
        if (str5 != null) {
            if (str5.endsWith("ProductDetailNewActivity")) {
                str5 = "com.jingdong.app.mall.product.ProductDetailNewLastActivity";
            }
            clickInterfaceParam.next_page_name = str5;
        }
        lastEventId = str;
        JDMaInterface.sendClickData(context, maInitCommonInfo, clickInterfaceParam);
        deliver(clickInterfaceParam);
        boolean handle1Page = handle1Page(context, str, str2, str3, str8, str4);
        if (!handle1Page) {
            handle1Page = handle2Page(context, str, str2, str3, str8, str4);
        }
        if (!handle1Page) {
            handle1Page = handle3Page(context, str, str2, str3, str8, str4);
        }
        if (!handle1Page) {
            handle1Page = handle4Page(context, str, str2, str3, str8, str4);
        }
        if (handle1Page) {
            return;
        }
        handle5Page(context, str, str2, str3, str8, str4);
    }

    @Deprecated
    public static void sendEpData(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, HashMap<String, String> hashMap) {
        sendEpData(context, str, str2, str3, str4, str5, "", str6, str7, str8, hashMap);
    }

    public static void sendEpData(Context context, String str, String str2, String str3, String str4, String str5, String str6, HashMap<String, String> hashMap) {
        sendEpData(context, str, str2, str3, str4, str5, str6, "", "", "", hashMap);
    }

    public static void sendEpData(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, HashMap<String, String> hashMap) {
        getMaInitCommonInfo(context);
        if (context == null) {
            return;
        }
        ExposureInterfaceParam exposureInterfaceParam = new ExposureInterfaceParam();
        String str10 = (hashMap == null || !hashMap.containsKey(BaseEvent.LBS_SCENE_ID)) ? "" : hashMap.get(BaseEvent.LBS_SCENE_ID);
        exposureInterfaceParam.lat = getLatitude(str10);
        exposureInterfaceParam.lon = getLongitude(str10);
        exposureInterfaceParam.page_name = str4;
        exposureInterfaceParam.page_id = str3;
        exposureInterfaceParam.page_param = str5;
        exposureInterfaceParam.pin = getUserPin();
        exposureInterfaceParam.orderId = str8;
        exposureInterfaceParam.shopId = str7;
        exposureInterfaceParam.eventId = str;
        exposureInterfaceParam.eventParam = str2;
        exposureInterfaceParam.jsonParam = str6;
        exposureInterfaceParam.ep_flag = "1";
        if (hashMap == null) {
            hashMap = new HashMap<>();
        }
        hashMap.put("pv_sid", "" + JDMaInterface.getOpen_count());
        hashMap.put("pv_seq", "" + JDMaInterface.getSeq());
        hashMap.put("sku_tag", str9);
        exposureInterfaceParam.map = hashMap;
        JDMaInterface.sendExposureData(context, maInitCommonInfo, exposureInterfaceParam);
        deliver(exposureInterfaceParam);
    }

    public static void sendEpData(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        getMaInitCommonInfo(context);
        if (context == null) {
            return;
        }
        ExposureInterfaceParam exposureInterfaceParam = new ExposureInterfaceParam();
        String str10 = (hashMap == null || !hashMap.containsKey(BaseEvent.LBS_SCENE_ID)) ? "" : hashMap.get(BaseEvent.LBS_SCENE_ID);
        exposureInterfaceParam.lat = getLatitude(str10);
        exposureInterfaceParam.lon = getLongitude(str10);
        exposureInterfaceParam.page_name = str4;
        exposureInterfaceParam.page_id = str3;
        exposureInterfaceParam.page_param = str5;
        exposureInterfaceParam.pin = getUserPin();
        exposureInterfaceParam.orderId = str8;
        exposureInterfaceParam.shopId = str7;
        exposureInterfaceParam.eventId = str;
        exposureInterfaceParam.eventParam = str2;
        exposureInterfaceParam.jsonParam = str6;
        exposureInterfaceParam.ep_flag = "1";
        if (hashMap == null) {
            hashMap = new HashMap<>();
        }
        hashMap.put("pv_sid", "" + JDMaInterface.getOpen_count());
        hashMap.put("pv_seq", "" + JDMaInterface.getSeq());
        hashMap.put("sku_tag", str9);
        exposureInterfaceParam.map = hashMap;
        if (hashMap2 != null) {
            if (hashMap2.containsKey(BaseEvent.SCENE)) {
                exposureInterfaceParam.scene = hashMap2.get(BaseEvent.SCENE);
            }
            if (hashMap2.containsKey("lat")) {
                exposureInterfaceParam.lat = hashMap2.get("lat");
            }
            if (hashMap2.containsKey(LON)) {
                exposureInterfaceParam.lon = hashMap2.get(LON);
            }
        }
        JDMaInterface.sendExposureData(context, maInitCommonInfo, exposureInterfaceParam);
        deliver(exposureInterfaceParam);
    }

    public static void sendCommonDataWithExt(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6, String str7, String str8, HashMap<String, String> hashMap) {
        String str9;
        String name;
        String str10 = str5;
        getMaInitCommonInfo(context);
        String str11 = "";
        if (obj != null) {
            if (obj instanceof String) {
                name = (String) obj;
            } else {
                name = obj.getClass().getName();
            }
            str9 = name;
        } else {
            str9 = "";
        }
        ClickInterfaceParam clickInterfaceParam = new ClickInterfaceParam();
        if (hashMap != null && hashMap.containsKey(BaseEvent.LBS_SCENE_ID)) {
            str11 = hashMap.get(BaseEvent.LBS_SCENE_ID);
        }
        clickInterfaceParam.lat = getLatitude(str11);
        clickInterfaceParam.lon = getLongitude(str11);
        clickInterfaceParam.event_id = str;
        clickInterfaceParam.event_param = str2;
        if (str10 != null) {
            if (str10.endsWith("ProductDetailNewActivity")) {
                str10 = "com.jingdong.app.mall.product.ProductDetailNewLastActivity";
            }
            clickInterfaceParam.next_page_name = str10;
        }
        clickInterfaceParam.page_name = str9;
        clickInterfaceParam.page_param = str4;
        clickInterfaceParam.pin = getUserPin();
        clickInterfaceParam.page_id = str7;
        clickInterfaceParam.event_func = str3;
        clickInterfaceParam.shop = str8;
        clickInterfaceParam.map = hashMap;
        updateLastExtensionID(hashMap);
        updateLastClickParam(str, str2, str4);
        lastEventId = str;
        JDMaInterface.sendClickData(context, maInitCommonInfo, clickInterfaceParam);
        deliver(clickInterfaceParam);
        boolean handle1Page = handle1Page(context, str, str2, str3, str9, str4);
        if (!handle1Page) {
            handle1Page = handle2Page(context, str, str2, str3, str9, str4);
        }
        if (!handle1Page) {
            handle1Page = handle3Page(context, str, str2, str3, str9, str4);
        }
        if (!handle1Page) {
            handle1Page = handle4Page(context, str, str2, str3, str9, str4);
        }
        if (handle1Page) {
            return;
        }
        handle5Page(context, str, str2, str3, str9, str4);
    }

    public static void sendEpData(MtaExposureParam mtaExposureParam) {
        if (mtaExposureParam == null) {
            return;
        }
        getMaInitCommonInfo(JdSdk.getInstance().getApplicationContext());
        completeCommonParams(mtaExposureParam);
        mtaExposureParam.ep_flag = "1";
        JDMaInterface.sendData(JdSdk.getInstance().getApplicationContext(), maInitCommonInfo, mtaExposureParam);
        deliver(mtaExposureParam);
    }

    public static void sendCommonDataWithExt(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6, String str7, String str8, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        String str9;
        String str10 = str5;
        getMaInitCommonInfo(context);
        String str11 = "";
        if (obj == null) {
            str9 = "";
        } else if (obj instanceof String) {
            str9 = (String) obj;
        } else {
            str9 = obj.getClass().getName();
        }
        ClickInterfaceParam clickInterfaceParam = new ClickInterfaceParam();
        if (hashMap != null && hashMap.containsKey(BaseEvent.LBS_SCENE_ID)) {
            str11 = hashMap.get(BaseEvent.LBS_SCENE_ID);
        }
        clickInterfaceParam.lat = getLatitude(str11);
        clickInterfaceParam.lon = getLongitude(str11);
        clickInterfaceParam.event_id = str;
        clickInterfaceParam.event_param = str2;
        if (str10 != null) {
            if (str10.endsWith("ProductDetailNewActivity")) {
                str10 = "com.jingdong.app.mall.product.ProductDetailNewLastActivity";
            }
            clickInterfaceParam.next_page_name = str10;
        }
        clickInterfaceParam.page_name = str9;
        clickInterfaceParam.page_param = str4;
        clickInterfaceParam.pin = getUserPin();
        clickInterfaceParam.page_id = str7;
        clickInterfaceParam.event_func = str3;
        clickInterfaceParam.shop = str8;
        clickInterfaceParam.map = hashMap;
        updateLastExtensionID(hashMap);
        updateLastClickParam(str, str2, str4);
        lastEventId = str;
        if (hashMap2 != null) {
            if (hashMap2.containsKey(BaseEvent.SCENE)) {
                clickInterfaceParam.scene = hashMap2.get(BaseEvent.SCENE);
            }
            if (hashMap2.containsKey("lat")) {
                clickInterfaceParam.lat = hashMap2.get("lat");
            }
            if (hashMap2.containsKey(LON)) {
                clickInterfaceParam.lon = hashMap2.get(LON);
            }
        }
        JDMaInterface.sendClickData(context, maInitCommonInfo, clickInterfaceParam);
        deliver(clickInterfaceParam);
        boolean handle1Page = handle1Page(context, str, str2, str3, str9, str4);
        if (!handle1Page) {
            handle1Page = handle2Page(context, str, str2, str3, str9, str4);
        }
        if (!handle1Page) {
            handle1Page = handle3Page(context, str, str2, str3, str9, str4);
        }
        if (!handle1Page) {
            handle1Page = handle4Page(context, str, str2, str3, str9, str4);
        }
        if (handle1Page) {
            return;
        }
        handle5Page(context, str, str2, str3, str9, str4);
    }
}
