package com.jingdong.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.hybrid.offlineload.temp.CommonResEngine;
import com.jd.phc.e;
import com.jingdong.common.entity.Switch;
import com.jingdong.common.entity.SwitchEntity;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.openlinktime.OpenLinkTimeManager;
import com.jingdong.common.unification.navigationbar.db.NavigationDbConstants;
import com.jingdong.common.web.WebHybridUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.FileGuider;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class SwitchQueryFetcher {
    public static final String ABTEST_SCAN_QUERY = "abTestScanQuery";
    public static final String ARSAO_3D_SWITCH = "ar_3d_animation";
    public static final String ARSAO_618_SWITCH = "ar_618";
    public static final String ARSAO_GONGLUE_URL_SWITCH = "ar_618_gonglue_url";
    public static final String ARSAO_HAOYOUZHULI_SWITCH = "ar_haoyouzhuli";
    public static final String ARSAO_SWITCH = "ar_sao";
    public static final String ARSAO_ZHONGJIANGJILU_SWITCH = "ar_zhongjiangjilu";
    public static final String BABEL_TIME_OUT = "ttttimeout";
    public static final String BABEL_WEB_DEL_PROG = "ttt_webDelProg";
    public static final String CASH_REWARD = "cashReward";
    public static final String CONTROLWEBREDIRECT = "controlWebRedirect";
    public static final String DEBUG_ENABLE = "debugEnable";
    public static final String EXCEPTION_REPORTER = "x_ep";
    public static final String HOME_SHAKE_URL = "homeShakeUrl";
    public static final String HYBRID_COMMON_RESOURCE_INTERNAL = "x_lr";
    public static final String HYBRID_CONFIG = "x_hd";
    public static final String HYBRID_DOWNLOAD_FILE = "x_hdc";
    public static final String HYBRID_REFRESH_TIME = "x_h";
    public static final String JUMPTO_SECKILL_ACTIVITY = "jumpToSeckillActivity";
    public static final String KEY_APP_VERSION = "x_app_version";
    public static final String LAUNCH_START_ENABLE = "appstart_upload_enable";
    public static final String LINK_TRACK_ENABLE = "linkTrackEnable";
    public static final String METHOD_SWITCH = "methodSwitch";
    public static final String MIAOSHA_POPUPWINDOW = "miaoshaPopupWindow";
    public static final String NETWORK_DETECT = "netWorkDetect";
    public static final String OENPAPP_BUILDER_ENCODE = "opBuilderEncode";
    public static final String PAYCODE_SEED_SWITCH = "paycode_seed";
    public static final String PAYCODE_SWITCH = "pay_code_hide";
    public static final String PERFORMANCE_MONITOR = "perfMonitor";
    public static final String PERFORMANCE_REPORTER = "performanceReporter";
    public static final String PHOTOBUY_HISTORY = "photobuy_history_enable";
    public static final String PHOTOBUY_INDEPENDENT_SCANQUERY = "photoBuyIndependentScanQuery";
    public static final String PHOTOBUY_WASTE_SORTING_TIPS = "photobuyWasteSortingTips";
    public static final String PRELOAD_ADDRESS = "preloadAddress";
    public static final String PRELOAD_BABEL = "preloadBabel";
    public static final String PRELOAD_BROWER_HISTORY = "preloadBrowerHistory";
    public static final String PRELOAD_CART = "preloadCart";
    public static final String PRELOAD_COMMUNE = "preloadCommune";
    public static final String PRELOAD_EVALUATECENTER = "preloadEvaluateCenter";
    public static final String PRELOAD_ICSSDK = "preloadIcssdk";
    public static final String PRELOAD_JDLIVELIST = "preloadJdLiveList";
    public static final String PRELOAD_JDPAYSDK = "preloadJdPaySdk";
    public static final String PRELOAD_JSHOP = "preloadJShop";
    public static final String PRELOAD_LOGIN = "preloadLogin";
    public static final String PRELOAD_MESSAGE = "preloadMessage";
    public static final String PRELOAD_MIAOSHA = "preloadMiaosha";
    public static final String PRELOAD_MIAOSHA_TAB = "preloadMiaoshaTab";
    public static final String PRELOAD_MYLIVE = "preloadMyLive";
    public static final String PRELOAD_NEW_COUPON = "preloadNewCoupon";
    public static final String PRELOAD_ORDER_CENTER = "preloadOrderCenter";
    public static final String PRELOAD_PARITY_CATEGORY = "preloadParityCategory";
    public static final String PRELOAD_PARITY_GOODS = "preloadParityGoods";
    public static final String PRELOAD_PERSONAL = "preloadPersonal";
    public static final String PRELOAD_PLUGIN = "preloadPlugin";
    public static final String PRELOAD_PRODUCT_DETAIL = "preloadProductDetail";
    public static final String PRELOAD_SCAN = "preloadScan";
    public static final String PRELOAD_SEARCH = "preloadSearch";
    public static final String PRELOAD_SECKILL_AREALIST = "preloadSeckillAreaList";
    public static final String PRELOAD_SECKILL_GENERIC_FLOOR = "preloadSeckillGenericFloor";
    public static final String PRELOAD_SETTLEMENT = "preloadSettlement";
    public static final String PRELOAD_SHANGOU = "preloadShanGou";
    public static final String PRELOAD_SHARE_ORDER = "preloadShareOrder";
    public static final String PRELOAD_WORTHBUY = "preloadWorthBuy";
    public static final String PerformanceReportForRN = "performanceReportForRN";
    public static final String REQUEST_CACHE_TIME = "requestCacheTime";
    public static final String SAOASAO_SWITCH = "saoasao";
    public static final String SCAN_WASTE_SORTING_TIPS = "scanWasteSortingTips";
    public static final String SCAN_ZOOM = "scanZoom";
    public static final String SCREEN_SHOT_CLOSE_TOAST = "screenShotCloseToast";
    public static final String SCREEN_SHOT_SHARE = "screenShotShare";
    public static final String SECKILL_HOME = "secKillHome";
    public static final String SECKILL_HOME_NAV_BACK = "seckillHomeNavBack";
    private static final String SFG_TIMEINTERVAL = "sfg_t";
    private static final String SPKEY = "switch_query";
    private static final String SWITCH_REFRESH_TIME = "x_s";
    public static final String SWITCH_SHARE_CM_CHANNEL_ID_WHITE = "shareCmChannelIds_str";
    public static final String SWITCH_SHARE_FRIENDS_ENABLE = "sharefriends_enable";
    public static final String SWITCH_SHARE_IMG_PANEL = "shareImgPanel_enable";
    public static final String SWITCH_SHARE_JCOMM_CREATE_SWITCH = "jcomm_create_switch";
    public static final String SWITCH_SHARE_JCOMM_EXCHANGE_SWITCH = "jcomm_exchange_switch";
    public static final String SWITCH_SHARE_JCOMM_PAGE_BLACK = "jcomm_page_black";
    public static final String SWITCH_SHARE_JCOMM_PAGE_WHITE = "jcomm_page_white";
    public static final String SWITCH_SHARE_JCOMM_SPOT_CH = "jcomm_spot_ch";
    public static final String SWITCH_SHARE_JDFAMILY = "share2JDFamily";
    public static final String SWITCH_SHARE_PANEL_IMG_URL = "shareImgPanelUrl_str";
    public static final String SWITCH_SHARE_PICTORIAL_CLOSED = "pictorialExchangeClosed";
    public static final String SWITCH_SHARE_URL_LENGTH = "shareUrlLength_int";
    public static final String SWITCH_TYPE_BOOLEAN = "booleanSwitch";
    public static final String SWITCH_TYPE_ENCODE = "encodedSwitch";
    public static final String SWITCH_TYPE_INT = "intSwitch";
    public static final String SWITCH_TYPE_STRING = "stringSwitch";
    public static final String SWITCH_WEBVIEW_FORCE_SYSTEM = "x_fs";
    public static final String SWITCH_X_X5 = "x_x5";
    public static final String TAG = "SwitchQueryFetcher";
    public static final long TEN_MINUTE = 600000;
    public static final String UAENCRYPT = "ua_encrypt";
    public static final String UAENCRYPTBLACKURL = "UAEncryptBlackUrl";
    public static final String UPDATE_ADDRESS_DISABLED = "disableUpdateAddress";
    public static final String VIDEO_IPV6_SWITCH = "ipv6_video_play";
    public static final String WB_DEL_FOR_64 = "wbDeleteFor64";
    public static final String WB_SYS_NOTIFY_RESIZE = "wbSysResize";
    public static final String WB_WEB_CONTROL_BACK = "wbWebControlBack";
    public static final String WEBURLNOLBS = "WebUrlNoLbs";
    public static final String WEB_ALBUM_SWITCH = "albumSwitch";
    public static final String WEB_BLACK_LIST_LOGIC = "WebViewBlackListLogic";
    public static final String WEB_COOKIE_EXPIRE = "ceTime";
    public static final String WEB_FUNCTION_REPORT = "functionReport";
    public static final String WEB_GENTOKEN_BLACK_LIST = "gentokenBlackList";
    public static final String WEB_GENTOKEN_GRAY = "gentokenGray";
    public static final String WEB_GETCOUPON_ARGS = "getCouponArgs";
    public static final String WEB_HOST_BLACK_LIST = "hostBlackList";
    public static final String WEB_HOST_ILLEGALURL_BLCK_LIST = "IllegalUrlBlackList";
    public static final String WEB_HYBRID_DISABLE = "webHybridClose";
    public static final String WEB_MEDIA_REPORT = "mediaReport";
    public static final String WEB_PASS_GENTOKEN_LIST = "passGentokenList";
    public static final String WEB_PASS_GENTOKEN_LIST1 = "passGentokenList1";
    public static final String WEB_PASS_GENTOKEN_LIST2 = "passGentokenList2";
    public static final String WEB_PRODUCTDETAIL_GRAY = "pingouUrlFilter";
    public static final String WEB_SCHEME_BLACKLIST = "schemeBlackList";
    public static final String XTIME_STAMP = "x_timestamp";
    public static final String XVIEW2_ENABLE = "xviewFirstGray";
    private static Handler handler;
    private static boolean isInited;
    private static volatile SwitchQueryFetcher manager;
    private static File sPreFile;
    public Runnable delayRunnable;
    SharedPreferences.Editor edit;
    private boolean isNewAppVersion;
    private List<FetchListener> listeners;
    public String mCdnHost;
    public String mColdOrNot;
    public String mDataVersion;
    public String mDegrade;
    public String mDiffOff;
    private Map<String, String> mGateWayMap;
    public String mGateWayVersion;
    private AtomicBoolean mIsFetching;
    private long mLastFetchTime;
    private JDJSONObject mLocalJsonObject;
    public String mLocalSwitchCount;
    public String mReserved;
    private int mStatus;
    public String mSwitch;
    public String mSwitchCount;
    private Runnable runnable;
    private SharedPreferences sharedPreferences;
    private long XStartTime = 0;
    private long XEndTime = 0;
    private volatile long XTimeDiff = 0;
    private ConcurrentMap<String, String> entityMap = new ConcurrentHashMap();

    /* loaded from: classes.dex */
    public interface FetchListener {
        void onFetchEnd(boolean z);
    }

    private SwitchQueryFetcher() {
        SharedPreferences jdSharedPreferences = CommonBase.getJdSharedPreferences();
        this.sharedPreferences = jdSharedPreferences;
        this.edit = jdSharedPreferences.edit();
        this.mReserved = "";
        this.mColdOrNot = "1";
        this.mDataVersion = "0";
        this.mCdnHost = "";
        this.mDegrade = "1";
        this.mGateWayVersion = "";
        this.mSwitch = "";
        this.mDiffOff = "3";
        this.isNewAppVersion = false;
        this.mGateWayMap = new ConcurrentHashMap();
        this.mIsFetching = new AtomicBoolean(false);
        this.runnable = new Runnable() { // from class: com.jingdong.common.utils.SwitchQueryFetcher.2
            {
                SwitchQueryFetcher.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                String[] split;
                try {
                    if (SwitchQueryFetcher.this.mGateWayMap != null) {
                        if (OKLog.D) {
                            OKLog.e(SwitchQueryFetcher.TAG, "\u83b7\u53d6\u7f51\u5173\u6570\u636e\u771f\u5b9e" + SwitchQueryFetcher.this.mGateWayMap.toString());
                        }
                        String realSwitchConfigKey = SwitchQueryFetcher.this.getRealSwitchConfigKey();
                        if (TextUtils.isEmpty(realSwitchConfigKey)) {
                            if (SwitchQueryFetcher.handler != null) {
                                SwitchQueryFetcher.handler.removeCallbacks(SwitchQueryFetcher.this.runnable);
                                return;
                            }
                            return;
                        }
                        String str = (String) SwitchQueryFetcher.this.mGateWayMap.get(realSwitchConfigKey);
                        if (TextUtils.isEmpty(str)) {
                            if (SwitchQueryFetcher.handler != null) {
                                SwitchQueryFetcher.handler.removeCallbacks(SwitchQueryFetcher.this.runnable);
                                return;
                            }
                            return;
                        }
                        String optString = new JSONObject(str).optString("switchQuery");
                        if (!TextUtils.isEmpty(optString) && (split = optString.split(CartConstant.KEY_YB_INFO_LINK, 4)) != null && split.length >= 4) {
                            if (!TextUtils.isEmpty(split[0]) && !"0".equals(split[0])) {
                                SwitchQueryFetcher switchQueryFetcher = SwitchQueryFetcher.this;
                                switchQueryFetcher.mSwitch = split[0];
                                switchQueryFetcher.mGateWayVersion = split[1];
                                if (TextUtils.isEmpty(split[2])) {
                                    return;
                                }
                                int parseInt = Integer.parseInt(split[2]);
                                int nextInt = parseInt > 0 ? new Random().nextInt(parseInt) : 0;
                                if (!SwitchQueryFetcher.this.mIsFetching.get() && !TextUtils.isEmpty(SwitchQueryFetcher.this.mGateWayVersion) && !TextUtils.isEmpty(SwitchQueryFetcher.this.mDataVersion) && Long.parseLong(SwitchQueryFetcher.this.mGateWayVersion) > Long.parseLong(SwitchQueryFetcher.this.mDataVersion)) {
                                    SwitchQueryFetcher.handler.removeCallbacks(SwitchQueryFetcher.this.delayRunnable);
                                    SwitchQueryFetcher.handler.postDelayed(SwitchQueryFetcher.this.delayRunnable, nextInt * 1000);
                                    return;
                                }
                                if (SwitchQueryFetcher.handler != null) {
                                    SwitchQueryFetcher.handler.removeCallbacks(SwitchQueryFetcher.this.runnable);
                                }
                            }
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    if (SwitchQueryFetcher.handler != null) {
                        SwitchQueryFetcher.handler.removeCallbacks(SwitchQueryFetcher.this.runnable);
                    }
                }
            }
        };
        this.delayRunnable = new Runnable() { // from class: com.jingdong.common.utils.SwitchQueryFetcher.3
            {
                SwitchQueryFetcher.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                SwitchQueryFetcher.this.fetch("2");
            }
        };
        this.mStatus = 0;
        this.listeners = Collections.synchronizedList(new LinkedList());
        loadLocalData();
        checkAppVersion();
        if (handler == null) {
            HandlerThread handlerThread = new HandlerThread("gateWay_thread");
            handlerThread.start();
            handler = new Handler(handlerThread.getLooper());
        }
    }

    private void checkAppVersion() {
        String string = CommonBase.getJdSharedPreferences().getString(KEY_APP_VERSION, "");
        String versionName = PackageInfoUtil.getVersionName();
        if (!TextUtils.equals(string, versionName)) {
            this.isNewAppVersion = true;
        }
        this.edit.putString(KEY_APP_VERSION, versionName);
        this.edit.apply();
    }

    public JDJSONObject diffData(JDJSONObject jDJSONObject) {
        SwitchEntity switchEntity;
        JDJSONArray jDJSONArray;
        SwitchEntity switchEntity2;
        SwitchEntity switchEntity3;
        ArrayList<Switch> arrayList;
        if (jDJSONObject != null && jDJSONObject.containsKey("diffOff")) {
            this.mDiffOff = jDJSONObject.optString("diffOff");
        }
        if (!isSwitchDiffQuery() || jDJSONObject == null || jDJSONObject.optJSONArray("data") == null) {
            return jDJSONObject;
        }
        JDJSONObject jDJSONObject2 = null;
        try {
            if (this.mLocalJsonObject == null) {
                return null;
            }
            if (OKLog.D) {
                OKLog.e(TAG, "diff before-->>");
            }
            String optString = this.mLocalJsonObject.optString(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_DATAVERSION);
            String optString2 = jDJSONObject.optString(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_DATAVERSION);
            if (!TextUtils.equals(optString, optString2) && !TextUtils.isEmpty(optString2)) {
                if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2) && Long.parseLong(optString2) > Long.parseLong(optString)) {
                    this.mLocalJsonObject.put(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_DATAVERSION, (Object) optString2);
                }
                if (!TextUtils.isEmpty(jDJSONObject.optString("coldOrNot"))) {
                    this.mLocalJsonObject.put("coldOrNot", (Object) jDJSONObject.optString("coldOrNot"));
                }
                if (!TextUtils.isEmpty(jDJSONObject.optString("cdnHost"))) {
                    this.mLocalJsonObject.put("cdnHost", (Object) jDJSONObject.optString("cdnHost"));
                }
                if (!TextUtils.isEmpty(jDJSONObject.optString("degrade"))) {
                    this.mLocalJsonObject.put("degrade", (Object) jDJSONObject.optString("degrade"));
                }
                if (!TextUtils.isEmpty(jDJSONObject.optString("diffOff"))) {
                    this.mLocalJsonObject.put("diffOff", (Object) jDJSONObject.optString("diffOff"));
                }
                String optString3 = jDJSONObject.optString("switchCount");
                this.mSwitchCount = optString3;
                if (!TextUtils.isEmpty(optString3)) {
                    this.mLocalJsonObject.put("switchCount", (Object) this.mSwitchCount);
                }
                JDJSONArray optJSONArray = this.mLocalJsonObject.optJSONArray("data");
                if (optJSONArray == null) {
                    return null;
                }
                ArrayList arrayList2 = new ArrayList();
                int i2 = 0;
                while (i2 < optJSONArray.size()) {
                    JDJSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                    if (optJSONObject != null && (switchEntity = (SwitchEntity) JDJSON.parseObject(optJSONObject.toString(), SwitchEntity.class)) != null) {
                        ArrayList<Switch> arrayList3 = switchEntity.switches;
                        if (arrayList3 != null && arrayList3.size() > 0) {
                            JDJSONArray optJSONArray2 = jDJSONObject.optJSONArray("data");
                            if (optJSONArray2 == null) {
                                return jDJSONObject2;
                            }
                            int i3 = 0;
                            while (i3 < optJSONArray2.size()) {
                                JDJSONObject optJSONObject2 = optJSONArray2.optJSONObject(i3);
                                if (optJSONObject2 != null && (switchEntity3 = (SwitchEntity) JDJSON.parseObject(optJSONObject2.toString(), SwitchEntity.class)) != null && TextUtils.equals(switchEntity.type, switchEntity3.type) && (arrayList = switchEntity3.switches) != null && arrayList.size() > 0) {
                                    Iterator<Switch> it = switchEntity3.switches.iterator();
                                    while (it.hasNext()) {
                                        Switch next = it.next();
                                        if (!TextUtils.isEmpty(next.name)) {
                                            Iterator<Switch> it2 = switchEntity.switches.iterator();
                                            boolean z = false;
                                            while (it2.hasNext()) {
                                                Switch next2 = it2.next();
                                                JDJSONArray jDJSONArray2 = optJSONArray;
                                                if (TextUtils.equals(next2.name, next.name)) {
                                                    int i4 = next.status;
                                                    if (i4 != -1 && i4 != 0) {
                                                        if (i4 == 1) {
                                                            next2.value = next.value;
                                                        }
                                                        z = true;
                                                    }
                                                    it2.remove();
                                                    z = true;
                                                }
                                                optJSONArray = jDJSONArray2;
                                            }
                                            JDJSONArray jDJSONArray3 = optJSONArray;
                                            if (next.status == 1 && !z) {
                                                switchEntity.switches.add(next);
                                            }
                                            optJSONArray = jDJSONArray3;
                                        }
                                    }
                                }
                                i3++;
                                optJSONArray = optJSONArray;
                            }
                            jDJSONArray = optJSONArray;
                        } else {
                            jDJSONArray = optJSONArray;
                            if (TextUtils.equals(switchEntity.type, SWITCH_TYPE_ENCODE) && !TextUtils.isEmpty(switchEntity.data)) {
                                JDJSONArray optJSONArray3 = jDJSONObject.optJSONArray("data");
                                if (optJSONArray3 == null) {
                                    return null;
                                }
                                for (int i5 = 0; i5 < optJSONArray3.size(); i5++) {
                                    JDJSONObject optJSONObject3 = optJSONArray3.optJSONObject(i5);
                                    if (optJSONObject3 != null && (switchEntity2 = (SwitchEntity) JDJSON.parseObject(optJSONObject3.toString(), SwitchEntity.class)) != null && TextUtils.equals(switchEntity2.type, SWITCH_TYPE_ENCODE)) {
                                        switchEntity.data = switchEntity2.data;
                                    }
                                }
                            }
                        }
                        arrayList2.add(switchEntity);
                        i2++;
                        optJSONArray = jDJSONArray;
                        jDJSONObject2 = null;
                    }
                    jDJSONArray = optJSONArray;
                    i2++;
                    optJSONArray = jDJSONArray;
                    jDJSONObject2 = null;
                }
                String jSONString = JDJSON.toJSONString(arrayList2);
                if (!TextUtils.isEmpty(jSONString)) {
                    this.mLocalJsonObject.put("data", (Object) JDJSON.parseArray(jSONString));
                }
                if (OKLog.D) {
                    OKLog.e(TAG, "diff after-->>");
                }
                return this.mLocalJsonObject;
            }
            if (OKLog.D) {
                OKLog.e(TAG, "no diff-->> localDataVersion " + optString + " diffDataVersion: " + optString2);
                return null;
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            SwitchQueryFetcherUtil.reportSwitchQueryHttpExp(SwitchQueryFetcherUtil.SWITCH_QUERY_JSON_INVALID, e2.getMessage(), SwitchQueryFetcherUtil.SWITCH_QUERY_JSON_DIFF);
            return null;
        }
    }

    public static SwitchQueryFetcher getFetcher() {
        if (manager == null) {
            synchronized (SwitchQueryFetcher.class) {
                if (manager == null) {
                    manager = new SwitchQueryFetcher();
                }
            }
        }
        return manager;
    }

    public synchronized String getRealSwitchConfigKey() {
        Map<String, String> map = this.mGateWayMap;
        if (map != null && map.keySet() != null && this.mGateWayMap.keySet().size() != 0) {
            for (String str : this.mGateWayMap.keySet()) {
                if (!TextUtils.isEmpty(str) && "x-switch-config".equalsIgnoreCase(str.toLowerCase())) {
                    return str;
                }
            }
            return "";
        }
        return "";
    }

    public static boolean getSwitchBooleanValue(String str, boolean z) {
        String str2;
        try {
            SwitchQueryFetcher fetcher = getFetcher();
            synchronized (fetcher) {
                str2 = fetcher.entityMap.get(str);
            }
            if (TextUtils.isEmpty(str2)) {
                str2 = CommonBase.getJdSharedPreferences().getString(str, z ? "yes" : "no");
            }
            return "yes".equalsIgnoreCase(str2);
        } catch (Exception unused) {
            return z;
        }
    }

    public static int getSwitchIntValue(String str, int i2) {
        String str2;
        try {
            SwitchQueryFetcher fetcher = getFetcher();
            synchronized (fetcher) {
                str2 = fetcher.entityMap.get(str);
            }
            if (TextUtils.isEmpty(str2)) {
                str2 = CommonBase.getJdSharedPreferences().getString(str, String.valueOf(i2));
            }
            return str2 != null ? Integer.parseInt(str2) : i2;
        } catch (Exception unused) {
            return i2;
        }
    }

    public static String getSwitchStringValue(String str, String str2) {
        String str3;
        try {
            SwitchQueryFetcher fetcher = getFetcher();
            synchronized (fetcher) {
                str3 = fetcher.entityMap.get(str);
            }
            return TextUtils.isEmpty(str3) ? CommonBase.getJdSharedPreferences().getString(str, str2) : str3;
        } catch (Exception unused) {
            return str2;
        }
    }

    public static synchronized boolean isXTime() {
        synchronized (SwitchQueryFetcher.class) {
        }
        return false;
    }

    public boolean loadJsonFromFile(File file) {
        if (file == null) {
            return false;
        }
        try {
            JDJSONObject readGZipFile = SwitchQueryFetcherUtil.readGZipFile(file.getAbsolutePath());
            if (readGZipFile != null && readGZipFile.optJSONArray("data") != null && !readGZipFile.optJSONArray("data").isEmpty()) {
                sPreFile = file;
                parseData(readGZipFile);
                syncData(readGZipFile);
                return true;
            }
        } catch (Exception unused) {
        }
        return false;
    }

    private void loadLocalData() {
        try {
            if (isInited) {
                Log.d(TAG, "first loadLocalData-->> ");
                return;
            }
            isInited = true;
            if (OKLog.D) {
                OKLog.d(TAG, "load local data-->> ");
            }
            String string = CommonBase.getJdSharedPreferences().getString(SPKEY, "");
            if (!TextUtils.isEmpty(string)) {
                JDJSONObject parseObject = JDJSON.parseObject(string);
                this.mLocalJsonObject = parseObject;
                parseData(parseObject);
                this.mDiffOff = "3";
            }
            this.XTimeDiff = CommonBase.getJdSharedPreferences().getLong(XTIME_STAMP, 0L);
        } catch (Exception unused) {
        }
    }

    private synchronized void notifyFetchResult(boolean z) {
        this.mStatus = z ? 1 : -1;
        for (FetchListener fetchListener : this.listeners) {
            if (fetchListener != null) {
                fetchListener.onFetchEnd(z);
            }
        }
    }

    public void onResponse(boolean z) {
        notifyFetchResult(z);
        CommonResEngine.available = !getSwitchBooleanValue(HYBRID_COMMON_RESOURCE_INTERNAL, false);
        if (WebHybridUtils.isXTime()) {
            if (z) {
                String switchStringValue = getSwitchStringValue(HYBRID_CONFIG, "");
                if (TextUtils.isEmpty(switchStringValue)) {
                    HybridSDK.setXTimeData(null, false);
                    return;
                } else {
                    HybridSDK.setXTimeData(switchStringValue, true);
                    return;
                }
            }
            HybridSDK.setXTimeData(null, false);
        }
    }

    public void parseData(JDJSONObject jDJSONObject) {
        SwitchEntity switchEntity;
        Map<String, String> a;
        List<Switch> parseArray;
        if (jDJSONObject == null) {
            return;
        }
        try {
            JDJSONArray optJSONArray = jDJSONObject.optJSONArray("data");
            if (optJSONArray != null && optJSONArray.size() >= 1) {
                String optString = jDJSONObject.optString(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_DATAVERSION);
                if (!TextUtils.isEmpty(optString)) {
                    if (!TextUtils.isEmpty(this.mDataVersion) && Long.parseLong(this.mDataVersion) > Long.parseLong(optString)) {
                        return;
                    }
                    this.mDataVersion = optString;
                }
                if (OKLog.D) {
                    OKLog.e(TAG, "parseData mDataVersion-->> " + this.mDataVersion);
                }
                if (!TextUtils.isEmpty(jDJSONObject.optString("coldOrNot"))) {
                    this.mColdOrNot = jDJSONObject.optString("coldOrNot");
                }
                if (!TextUtils.isEmpty(jDJSONObject.optString("cdnHost"))) {
                    this.mCdnHost = jDJSONObject.optString("cdnHost");
                }
                if (!TextUtils.isEmpty(jDJSONObject.optString("degrade"))) {
                    this.mDegrade = jDJSONObject.optString("degrade");
                }
                if (!TextUtils.isEmpty(jDJSONObject.optString("diffOff"))) {
                    this.mDiffOff = jDJSONObject.optString("diffOff");
                }
                if (!TextUtils.isEmpty(jDJSONObject.optString("switchCount"))) {
                    this.mSwitchCount = jDJSONObject.optString("switchCount");
                }
                synchronized (this) {
                    ConcurrentMap<String, String> concurrentMap = this.entityMap;
                    if (concurrentMap != null && concurrentMap.size() > 0) {
                        this.entityMap = new ConcurrentHashMap(this.entityMap.size());
                    } else {
                        this.entityMap = new ConcurrentHashMap();
                    }
                    for (int i2 = 0; i2 < optJSONArray.size(); i2++) {
                        JDJSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                        if (optJSONObject != null && (switchEntity = (SwitchEntity) JDJSON.parseObject(optJSONObject.toString(), SwitchEntity.class)) != null) {
                            ArrayList<Switch> arrayList = switchEntity.switches;
                            if (arrayList != null && arrayList.size() > 0) {
                                sortSwitch(switchEntity.switches);
                            } else if (TextUtils.equals(switchEntity.type, SWITCH_TYPE_ENCODE) && !TextUtils.isEmpty(switchEntity.data) && (a = e.c(JdSdk.getInstance().getApplicationContext()).a(switchEntity.data)) != null && a.containsKey("data")) {
                                String str = a.get("data");
                                if (!TextUtils.isEmpty(str) && (parseArray = JDJSON.parseArray(str, Switch.class)) != null && !parseArray.isEmpty()) {
                                    sortSwitch(parseArray);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e2) {
            SwitchQueryFetcherUtil.reportSwitchQueryHttpExp(SwitchQueryFetcherUtil.SWITCH_QUERY_JSON_INVALID, e2.getMessage(), SwitchQueryFetcherUtil.SWITCH_QUERY_JSON_PARSE);
        }
    }

    public void syncData(JDJSONObject jDJSONObject) {
        JDJSONArray optJSONArray;
        if (jDJSONObject == null || (optJSONArray = jDJSONObject.optJSONArray("data")) == null || optJSONArray.size() < 1) {
            return;
        }
        this.mLocalJsonObject = jDJSONObject;
        if (!TextUtils.isEmpty(jDJSONObject.optString("switchCount"))) {
            this.mLocalSwitchCount = jDJSONObject.optString("switchCount");
        }
        this.edit.putString(SPKEY, jDJSONObject.toJSONString());
        this.edit.apply();
    }

    public static void updateXTime() {
        String[] split = getSwitchStringValue(SFG_TIMEINTERVAL, "1644919200,1644948000").split(DYConstants.DY_REGEX_COMMA);
        if (split.length == 2) {
            try {
                getFetcher().XStartTime = Long.parseLong(split[0]) * 1000;
                getFetcher().XEndTime = Long.parseLong(split[1]) * 1000;
            } catch (Exception e2) {
                Log.e(TAG, e2.getMessage(), e2);
            }
        }
        if (getFetcher().XStartTime == 0 || getFetcher().XEndTime == 0) {
            getFetcher().XStartTime = 1644919200000L;
            getFetcher().XEndTime = 1644948000000L;
        }
    }

    public synchronized void addFetchListener(FetchListener fetchListener) {
        if (fetchListener == null) {
            return;
        }
        this.listeners.add(fetchListener);
    }

    public void fetch(String str) {
        if (!"3".equals(str) || "1".equals(this.mDegrade)) {
            if (("1".equals(str) || "3".equals(str)) && isInited && this.mLastFetchTime > 0 && SystemClock.elapsedRealtime() - this.mLastFetchTime < getSwitchIntValue(SWITCH_REFRESH_TIME, 600) * 1000) {
                Log.d(TAG, "fetch \u9891\u7e41\u8bf7\u6c42\u964d\u7ea7 \u7531\u5f00\u5173\u63a7\u5236");
                return;
            }
            this.mIsFetching.set(true);
            this.mLastFetchTime = SystemClock.elapsedRealtime();
            loadLocalData();
            if (OKLog.D) {
                OKLog.d(TAG, "switchQuery() BEGIN-->> ");
            }
            HttpGroup.CustomOnAllListener customOnAllListener = new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.common.utils.SwitchQueryFetcher.1
                {
                    SwitchQueryFetcher.this = this;
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    OpenLinkTimeManager.getInstance().addExtraTiming("switchqueryServiceEnd", System.currentTimeMillis());
                    if (httpResponse != null && httpResponse.getFastJsonObject() != null) {
                        JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                        SwitchQueryFetcher.this.loadCDNData(fastJsonObject);
                        JDJSONObject diffData = SwitchQueryFetcher.this.diffData(fastJsonObject);
                        SwitchQueryFetcher.this.parseData(diffData);
                        SwitchQueryFetcher.this.saveXTimeStamp(diffData);
                        SwitchQueryFetcher.updateXTime();
                        SwitchQueryFetcher.this.syncData(diffData);
                        Context applicationContext = JdSdk.getInstance().getApplicationContext();
                        SwitchQueryFetcher switchQueryFetcher = SwitchQueryFetcher.this;
                        SwitchQueryFetcherUtil.sendExpMta(applicationContext, "1", switchQueryFetcher.mDataVersion, switchQueryFetcher.mLocalSwitchCount, switchQueryFetcher.mSwitchCount);
                        if (OKLog.D && diffData != null) {
                            OKLog.d(SwitchQueryFetcher.TAG, "switchQuery() END-->> " + diffData.toJSONString());
                        }
                    }
                    SwitchQueryFetcher.this.mIsFetching.set(false);
                    SwitchQueryFetcher.this.onResponse(true);
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    SwitchQueryFetcher.this.mIsFetching.set(false);
                    SwitchQueryFetcher.this.edit.putString(SwitchQueryFetcher.PAYCODE_SWITCH, "no");
                    Context applicationContext = JdSdk.getInstance().getApplicationContext();
                    SwitchQueryFetcher switchQueryFetcher = SwitchQueryFetcher.this;
                    SwitchQueryFetcherUtil.sendExpMta(applicationContext, "2", switchQueryFetcher.mDataVersion, switchQueryFetcher.mLocalSwitchCount, switchQueryFetcher.mSwitchCount);
                    if (httpError != null && httpError.getException() != null) {
                        SwitchQueryFetcherUtil.reportSwitchQueryHttpExp(SwitchQueryFetcherUtil.SWITCH_QUERY_HTTP_ERROR, httpError.getException().getMessage(), "");
                    }
                    SwitchQueryFetcher.this.onResponse(false);
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                public void onProgress(int i2, int i3) {
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                public void onStart() {
                }
            };
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setFunctionId("switchQuery");
            httpSetting.putJsonParam("coldOrNot", TextUtils.equals(str, "1") ? "1" : "2");
            if (OKLog.D) {
                OKLog.e(TAG, "fetch mDataVersion-->> " + this.mDataVersion);
            }
            httpSetting.putJsonParam(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_DATAVERSION, this.isNewAppVersion ? "0" : this.mDataVersion);
            if (this.isNewAppVersion) {
                this.isNewAppVersion = false;
            }
            httpSetting.setUseFastJsonParser(true);
            httpSetting.setHost(Configuration.getPortalHost());
            httpSetting.setListener(customOnAllListener);
            OpenLinkTimeManager.getInstance().addExtraTiming("switchqueryServiceStart", System.currentTimeMillis());
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        }
    }

    public int getFetchStatus() {
        return this.mStatus;
    }

    public boolean isSwitchDiffQuery() {
        JDJSONObject jDJSONObject;
        if (!isInited || (jDJSONObject = this.mLocalJsonObject) == null || jDJSONObject.optJSONArray("data") == null) {
            return false;
        }
        return (!TextUtils.equals(this.mColdOrNot, "1") && TextUtils.equals(this.mDiffOff, "1")) || TextUtils.equals(this.mDiffOff, "2");
    }

    public void loadCDNData(JDJSONObject jDJSONObject) {
        if ("1".equals(this.mDegrade)) {
            return;
        }
        if (jDJSONObject == null || !jDJSONObject.containsKey("data")) {
            if (OKLog.D) {
                OKLog.d(TAG, "loadCDNData");
            }
            if (TextUtils.isEmpty(this.mCdnHost) || TextUtils.isEmpty(this.mGateWayVersion) || !"1".equals(this.mSwitch)) {
                return;
            }
            loadCDNDataByUrl(this.mCdnHost + this.mGateWayVersion + CartConstant.KEY_YB_INFO_LINK + "android.json");
        }
    }

    public void loadCDNDataByUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        File file = sPreFile;
        if (file != null && file.exists() && System.currentTimeMillis() - sPreFile.lastModified() < 600000) {
            loadJsonFromFile(sPreFile);
            return;
        }
        FileGuider fileGuider = new FileGuider();
        fileGuider.setSpace(1);
        fileGuider.setImmutable(false);
        fileGuider.setFileName(Md5Encrypt.md5(str));
        fileGuider.setMode(1);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(str);
        httpSetting.setSavePath(fileGuider);
        httpSetting.setLocalFileCache(true);
        httpSetting.setOnTouchEvent(true);
        httpSetting.setEffect(0);
        httpSetting.setType(500);
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.utils.SwitchQueryFetcher.4
            {
                SwitchQueryFetcher.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                File saveFile;
                if (httpResponse == null || (saveFile = httpResponse.getSaveFile()) == null) {
                    return;
                }
                boolean loadJsonFromFile = SwitchQueryFetcher.this.loadJsonFromFile(saveFile);
                if (OKLog.D) {
                    OKLog.d(SwitchQueryFetcher.TAG, "success " + loadJsonFromFile);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                if (httpError == null || httpError.getException() == null) {
                    return;
                }
                SwitchQueryFetcherUtil.reportSwitchQueryHttpExp(SwitchQueryFetcherUtil.SWITCH_QUERY_HTTP_RESTRICT, httpError.getException().getMessage(), "");
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public synchronized void removeFetchListener(FetchListener fetchListener) {
        if (fetchListener == null) {
            return;
        }
        this.listeners.remove(fetchListener);
    }

    public void saveXTimeStamp(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        try {
            long optLong = jDJSONObject.optLong("timeStamp", 0L);
            Log.d(TAG, "timeStamp" + optLong);
            if (optLong == 0) {
                getFetcher().XTimeDiff = 0L;
            } else {
                getFetcher().XTimeDiff = (optLong * 1000) - System.currentTimeMillis();
            }
            this.edit.putLong(XTIME_STAMP, getFetcher().XTimeDiff);
            this.edit.apply();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void sortSwitch(List<Switch> list) {
        String versionName = PackageInfoUtil.getVersionName();
        for (Switch r1 : list) {
            if (!TextUtils.isEmpty(r1.name)) {
                String str = r1.appVersionUp;
                String str2 = r1.appVersionDown;
                if ((!TextUtils.isEmpty(str2) && SwitchQueryFetcherUtil.leftLargerOrEqual(str2, versionName)) || (!TextUtils.isEmpty(str) && SwitchQueryFetcherUtil.leftLargerOrEqual(versionName, str))) {
                    if (OKLog.D) {
                        OKLog.e(TAG, "switchObj.name" + r1.name + "\u4e0d\u5728\u7248\u672c\u8303\u56f4\u5185");
                    }
                } else {
                    this.entityMap.put(r1.name, TextUtils.isEmpty(r1.value) ? "" : r1.value);
                }
            }
        }
    }

    public synchronized void transDataFromGateWay(Map<String, String> map) {
        if (map == null) {
            return;
        }
        if (OKLog.D) {
            OKLog.e(TAG, "mDegrade" + this.mDegrade);
        }
        if ("1".equals(this.mDegrade)) {
            return;
        }
        if (this.mIsFetching.get()) {
            return;
        }
        this.mGateWayMap = map;
        Handler handler2 = handler;
        if (handler2 != null) {
            handler2.post(this.runnable);
        }
    }
}
