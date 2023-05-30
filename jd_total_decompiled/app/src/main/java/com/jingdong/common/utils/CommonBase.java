package com.jingdong.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.un.utils.UnAndroidUtils;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.app.mall.bundle.jdweather.action.JDWeatherActionKey;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.frame.IMainActivity;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import com.jingdong.common.web.WebConstants;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpGroupSetting;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R2;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class CommonBase implements ICommon {
    public static final String KEY_FAVO_PRICE_LOWER_NOTIFY_TIP = "favo_price_notify_tip";
    public static final String KEY_FAVO_PRODUCT_SORT_RECORD = "favo_product_sort_record";
    private static final String KEY_GUIDE_ACTIVITY = "guide_activity";
    public static final String KEY_GUIDE_COMMENT_EDIT = "guide_coo_comment_edit";
    public static final String KEY_GUIDE_COMMENT_LIST = "guide_coo_comment_list";
    public static final String KEY_GUIDE_SEARCH_FILTER = "guide_search_filter";
    private static final String KEY_SHAKE_SKIN = "shake_switch_skin";
    private static final String TAG = "CommonBase";
    private static boolean marketPriceFlag;
    public static String miaoShaKey;
    private static String pay_appId;

    /* loaded from: classes6.dex */
    public interface BrowserAllUrlListener extends BrowserNewUrlListener {
        void onReady();
    }

    /* loaded from: classes6.dex */
    public interface BrowserCashierNewUrlListener extends BrowserCashierUrlListener {
        void onError(HttpError httpError, Bundle bundle);
    }

    /* loaded from: classes6.dex */
    public interface BrowserCashierUrlListener extends BrowserNewUrlListener {
        void onComplete(String str, Bundle bundle);
    }

    /* loaded from: classes6.dex */
    public interface BrowserNewUrlListener extends BrowserUrlListener {
        void onError(HttpError httpError);
    }

    /* loaded from: classes6.dex */
    public interface BrowserQueueListener extends BrowserAllUrlListener {
        void onCancelQueue();
    }

    /* loaded from: classes.dex */
    public interface BrowserUrlListener {
        void onComplete(String str);
    }

    /* loaded from: classes6.dex */
    public interface GenTokenStatusListener extends BrowserAllUrlListener {
        void gentokenStatus(String str, String str2, String str3);
    }

    /* loaded from: classes6.dex */
    public interface ProgresslListener {
        void onEnd();

        void onError();

        void onStart();
    }

    /* loaded from: classes6.dex */
    public interface SettlementPayBrowserAllUrlListener extends BrowserQueueListener {
        void onPayIdComplete(String str, String str2, String str3, String str4);
    }

    public static boolean CheckNetWork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) JdSdk.getInstance().getApplication().getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo[] networkInfoArr = null;
        try {
            networkInfoArr = connectivityManager.getAllNetworkInfo();
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
        if (networkInfoArr == null) {
            return false;
        }
        for (NetworkInfo networkInfo : networkInfoArr) {
            if (networkInfo.isConnected()) {
                return true;
            }
        }
        return false;
    }

    public static boolean activityIsGuided(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (String str2 : getJdSharedPreferences().getString(KEY_GUIDE_ACTIVITY, "").split(DYConstants.DY_REGEX_VERTICAL_LINE)) {
            if (OKLog.D) {
                OKLog.d(TAG, "activityIsGuided string -->> " + str2);
            }
            if (str.equalsIgnoreCase(str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkSDcard() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static void fixBackBroundRepeat(View view) {
        Drawable background = view.getBackground();
        if (background == null || !(background instanceof BitmapDrawable)) {
            return;
        }
        BitmapDrawable bitmapDrawable = (BitmapDrawable) background;
        bitmapDrawable.mutate();
        bitmapDrawable.setTileModeX(Shader.TileMode.REPEAT);
    }

    public static Boolean getBooleanFromPreference(String str, Boolean bool) {
        return Boolean.valueOf(getJdSharedPreferences().getBoolean(str, bool.booleanValue()));
    }

    public static String getDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static String getExitType() {
        String switchStringValue = SwitchQueryFetcher.getSwitchStringValue("mp_exitType", "2");
        return !TextUtils.isEmpty(switchStringValue) ? switchStringValue : "1";
    }

    public static long getHomeActivityStoppedPeriod() {
        return getJdSharedPreferences().getLong("homeActivityStoppedTime", 0L);
    }

    public static int getIntFromPreference(String str, int i2) {
        return getJdSharedPreferences().getInt(str, i2);
    }

    public static boolean getJdFaxianNewFlag() {
        return getJdSharedPreferences().getBoolean("jdFaxianNewFlag", true);
    }

    public static synchronized SharedPreferences getJdSharedPreferences() {
        SharedPreferences sharedPreferences;
        synchronized (CommonBase.class) {
            sharedPreferences = SharedPreferencesUtil.getSharedPreferences();
        }
        return sharedPreferences;
    }

    public static long getLongFromPreference(String str, long j2) {
        return getJdSharedPreferences().getLong(str, j2);
    }

    public static boolean getMarketPriceFlag() {
        boolean z = ConfigUtil.get(11);
        marketPriceFlag = z;
        return z;
    }

    @Deprecated
    public static String getMiaoShaKey() {
        return "";
    }

    public static long getMiaoShaLeaveTime() {
        return getJdSharedPreferences().getLong("miaosha_leave_time", 0L);
    }

    public static PackageInfo getPackageInfo(Context context, String str) {
        if (context == null) {
            return null;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
            return null;
        }
    }

    public static String getPayAppID() {
        String payUrl = Configuration.getPayUrl();
        return ("http://paybeta.m.jd.com/index.action".equalsIgnoreCase(payUrl) || Configuration.PAY_URL_ADDRESS_NEW_BETA.equalsIgnoreCase(payUrl) || Configuration.PAY_URL_ADDRESS_CARE.equalsIgnoreCase(payUrl)) ? "android_app_beta" : "jd_android_app4";
    }

    public static String getPayAppKey() {
        String payUrl = Configuration.getPayUrl();
        return ("http://paybeta.m.jd.com/index.action".equalsIgnoreCase(payUrl) || Configuration.PAY_URL_ADDRESS_NEW_BETA.equalsIgnoreCase(payUrl) || Configuration.PAY_URL_ADDRESS_CARE.equalsIgnoreCase(payUrl)) ? "6fg7weDfF6gh" : "e53jfgRgd7Hk";
    }

    public static long getReActivationIntervalDays(long j2) {
        String stringFromPreference = ConfigUtil.getStringFromPreference(ConfigUtil.REMIMDER_TIME, null);
        if (TextUtils.isEmpty(stringFromPreference)) {
            return j2;
        }
        try {
            return Long.parseLong(stringFromPreference);
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.d(TAG, " -->> getReActivationIntervalDays" + e2.getMessage());
                return j2;
            }
            return j2;
        }
    }

    public static String getStringFromPreference(String str, String str2) {
        return getJdSharedPreferences().getString(str, str2);
    }

    public static long getTriggerAtTime() {
        return getJdSharedPreferences().getLong(Constants.SHARED_PREFERENCES_REACTIVATION_ALARM_TRIGGER_AT_TIME, -1L);
    }

    public static void handleHomeConnectReadTimeByNetType(HttpSetting httpSetting) {
        if ("wifi".equals(NetUtils.getNetworkType())) {
            httpSetting.setConnectTimeout(R2.dimen.vf_xlarge);
            httpSetting.setReadTimeout(R2.dimen.vf_xlarge);
            return;
        }
        httpSetting.setConnectTimeout(10000);
        httpSetting.setReadTimeout(10000);
    }

    public static boolean isIntentAvailable(Intent intent) {
        List<ResolveInfo> queryIntentActivities = JdSdk.getInstance().getApplication().getPackageManager().queryIntentActivities(intent, 65536);
        return queryIntentActivities != null && queryIntentActivities.size() > 0;
    }

    public static boolean isMyStreetNew() {
        return getJdSharedPreferences().getBoolean("MyJD_MyStreet_new", true);
    }

    public static Intent newBrowserIntent(Uri uri, boolean z) {
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        if (z) {
            intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
        }
        intent.setFlags(268435456);
        return intent;
    }

    public static boolean putBooleanToPreference(String str, Boolean bool) {
        return getJdSharedPreferences().edit().putBoolean(str, bool.booleanValue()).commit();
    }

    public static boolean putIntToPreference(String str, int i2) {
        return getJdSharedPreferences().edit().putInt(str, i2).commit();
    }

    public static boolean putLongToPreference(String str, long j2) {
        return getJdSharedPreferences().edit().putLong(str, j2).commit();
    }

    public static boolean putStringToPreference(String str, String str2) {
        try {
            SharedPreferencesUtil.putString(str, str2);
            return true;
        } catch (Throwable unused) {
            return true;
        }
    }

    public static void putTriggerAtTime(long j2) {
        getJdSharedPreferences().edit().putLong(Constants.SHARED_PREFERENCES_REACTIVATION_ALARM_TRIGGER_AT_TIME, j2).commit();
    }

    public static void queryBrowserUrl(String str, URLParamMap uRLParamMap, BrowserUrlListener browserUrlListener) {
        queryBrowserUrl(str, uRLParamMap, browserUrlListener, false);
    }

    public static void queryBrowserUrlSupportNoLbs(String str, URLParamMap uRLParamMap, BrowserUrlListener browserUrlListener, boolean z) {
        queryBrowserUrlSupportNoLbs(str, uRLParamMap, browserUrlListener);
    }

    public static void querySettlementPayMentChannelsData(String str, String str2, String str3, HttpGroup.OnCommonListener onCommonListener) {
        HttpGroupSetting createNewSettings = HttpGroupUtils.createNewSettings();
        createNewSettings.setType(1000);
        createNewSettings.setMyActivity((Activity) BaseFrameUtil.getInstance().getCurrentMyActivity());
        HttpGroup httpGroup = HttpGroup.getHttpGroup(createNewSettings);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId(str);
        httpSetting.setEffect(0);
        httpSetting.putJsonParam("payId", str2);
        httpSetting.putJsonParam("appId", str3);
        httpSetting.setUrl(Configuration.getSettlementPayUrl());
        httpSetting.setListener(onCommonListener);
        httpGroup.add(httpSetting);
    }

    public static void removeTriggerAtTime() {
        getJdSharedPreferences().edit().remove(Constants.SHARED_PREFERENCES_REACTIVATION_ALARM_TRIGGER_AT_TIME).commit();
    }

    public static void saveCommonParams() {
        if (OKLog.D) {
            OKLog.d(TAG, "getDeviceInfoStr==" + StatisticsReportUtil.getDeviceInfoStr());
        }
        putStringToPreference("commonParams", StatisticsReportUtil.getDeviceInfoStr());
    }

    public static void setHomeActivityStoppedPeriod(long j2) {
        try {
            getJdSharedPreferences().edit().putLong("homeActivityStoppedTime", j2).commit();
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public static void setIsGuided(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        getJdSharedPreferences().edit().putString(KEY_GUIDE_ACTIVITY, getJdSharedPreferences().getString(KEY_GUIDE_ACTIVITY, "") + "|" + str).commit();
    }

    public static void setJdFaxianNewFlag(boolean z) {
        try {
            getJdSharedPreferences().edit().putBoolean("jdFaxianNewFlag", z).commit();
        } catch (Exception unused) {
        }
    }

    public static void setMiaoShaLeaveTime(long j2) {
        try {
            getJdSharedPreferences().edit().putLong("miaosha_leave_time", j2).commit();
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public static void setMyStreetNew(boolean z) {
        try {
            getJdSharedPreferences().edit().putBoolean("MyJD_MyStreet_new", z).commit();
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public static void toBrowser(String str, URLParamMap uRLParamMap) {
        queryBrowserUrl(str, uRLParamMap, new BrowserUrlListener() { // from class: com.jingdong.common.utils.CommonBase.1
            @Override // com.jingdong.common.utils.CommonBase.BrowserUrlListener
            public void onComplete(String str2) {
                CommonBase.toBrowser(Uri.parse(str2));
            }
        });
    }

    public LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(JdSdk.getInstance().getApplication());
    }

    public IMainActivity goToMainFrameActivity(Context context) {
        Intent mainFrameActivityIntent = DexAsyncUtil.getMainFrameActivityIntent(context);
        IMainActivity mainFrameActivity = BaseFrameUtil.getInstance().getMainFrameActivity();
        if (mainFrameActivity == null) {
            if (OKLog.D) {
                OKLog.d(TAG, "Commutils goToMainFrameActivity() -->> not run");
            }
            mainFrameActivityIntent.addFlags(268435456);
            context.startActivity(mainFrameActivityIntent);
        } else {
            if (OKLog.D) {
                OKLog.d(TAG, "Commutils goToMainFrameActivity() -->> run");
            }
            if (mainFrameActivity.isMainStop() || UnAndroidUtils.mateXEasyClient(context)) {
                mainFrameActivityIntent.addFlags(268435456);
                context.startActivity(mainFrameActivityIntent);
            }
        }
        return mainFrameActivity;
    }

    public boolean isCanClick() {
        return NetUtils.isNetworkAvailable();
    }

    public static void queryBrowserUrl(final String str, final URLParamMap uRLParamMap, final BrowserUrlListener browserUrlListener, final boolean z) {
        if (OKLog.D) {
            OKLog.d(TAG, "queryBrowserUrl action-->> " + str);
        }
        HttpGroupSetting createNewSettings = HttpGroupUtils.createNewSettings();
        createNewSettings.setType(1000);
        createNewSettings.setMyActivity((Activity) BaseFrameUtil.getInstance().getCurrentMyActivity());
        HttpGroup httpGroup = HttpGroup.getHttpGroup(createNewSettings);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setEffect(0);
        httpSetting.setNotifyUser(false);
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setFunctionId("genToken");
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.utils.CommonBase.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                String string = fastJsonObject.getString("tokenKey");
                if (string == null) {
                    onError(null);
                    return;
                }
                String string2 = fastJsonObject.getString("url");
                if (string2 == null) {
                    onError(null);
                    return;
                }
                if (OKLog.D) {
                    OKLog.d(CommonBase.TAG, "fun:genToken onEnd() -->> token = " + string);
                    OKLog.d(CommonBase.TAG, "fun:genToken onEnd() -->> url = " + string2);
                }
                BrowserUrlListener browserUrlListener2 = browserUrlListener;
                if (browserUrlListener2 != null && (browserUrlListener2 instanceof GenTokenStatusListener)) {
                    ((GenTokenStatusListener) browserUrlListener).gentokenStatus(fastJsonObject.optString("status"), fastJsonObject.optString("error_msg"), string);
                }
                URLParamMap uRLParamMap2 = uRLParamMap;
                if (uRLParamMap2 != null) {
                    uRLParamMap2.put("tokenKey", string);
                }
                JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
                jDLocationCacheOption.setBusinessId(WebConstants.LBS_ID);
                JDLocation location = JDLocationCache.getInstance().getLocation(jDLocationCacheOption);
                if (location != null) {
                    double lat = location.getLat();
                    double lng = location.getLng();
                    if (lat != 0.0d || lng != 0.0d) {
                        JSONObject jSONObject = new JSONObject();
                        if (lat != 0.0d) {
                            try {
                                jSONObject.put("lat", "" + lat);
                            } catch (JSONException e2) {
                                e2.printStackTrace();
                            }
                        }
                        if (lng != 0.0d) {
                            jSONObject.put(HybridSDK.LNG, "" + lng);
                        }
                        if (location.getProvinceId() != 0) {
                            jSONObject.put(JDWeatherActionKey.PROVINCE_ID, "" + location.getProvinceId());
                        }
                        if (location.getCityId() != 0) {
                            jSONObject.put(JDWeatherActionKey.CITY_ID, "" + location.getCityId());
                        }
                        if (location.getDistrictId() != 0) {
                            jSONObject.put("districtId", "" + location.getDistrictId());
                        }
                        if (location.getProvinceName() != null) {
                            jSONObject.put("provinceName", "" + location.getProvinceName());
                        }
                        if (location.getCityName() != null) {
                            jSONObject.put("cityName", "" + location.getCityName());
                        }
                        if (location.getDistrictName() != null) {
                            jSONObject.put("districtName", "" + location.getDistrictName());
                        }
                        URLParamMap uRLParamMap3 = uRLParamMap;
                        if (uRLParamMap3 != null) {
                            uRLParamMap3.put("lbs", jSONObject.toString());
                        }
                    }
                }
                String mergerUrlAndParams = HttpGroup.mergerUrlAndParams(string2, uRLParamMap);
                if (OKLog.D) {
                    OKLog.d("Temp", "queryBrowserUrl() mergerUrl -->> " + mergerUrlAndParams);
                }
                BrowserUrlListener browserUrlListener3 = browserUrlListener;
                if (browserUrlListener3 != null) {
                    browserUrlListener3.onComplete(mergerUrlAndParams);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                BrowserUrlListener browserUrlListener2;
                if (z && (browserUrlListener2 = browserUrlListener) != null && (browserUrlListener2 instanceof BrowserNewUrlListener)) {
                    ((BrowserNewUrlListener) browserUrlListener2).onError(httpError);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                BrowserUrlListener browserUrlListener2 = browserUrlListener;
                if (browserUrlListener2 != null && (browserUrlListener2 instanceof BrowserAllUrlListener)) {
                    ((BrowserAllUrlListener) browserUrlListener2).onReady();
                }
                httpSettingParams.putJsonParam("action", str);
                URLParamMap uRLParamMap2 = uRLParamMap;
                if (uRLParamMap2 == null || uRLParamMap2.get((Object) RemoteMessageConst.TO) == null) {
                    return;
                }
                httpSettingParams.putJsonParam(RemoteMessageConst.TO, uRLParamMap.get((Object) RemoteMessageConst.TO));
            }
        });
        httpGroup.add(httpSetting);
    }

    public static void queryBrowserUrlSupportNoLbs(final String str, final URLParamMap uRLParamMap, final BrowserUrlListener browserUrlListener) {
        if (OKLog.D) {
            OKLog.d(TAG, "queryBrowserUrl action-->> " + str);
        }
        HttpGroupSetting createNewSettings = HttpGroupUtils.createNewSettings();
        createNewSettings.setType(1000);
        createNewSettings.setMyActivity((Activity) BaseFrameUtil.getInstance().getCurrentMyActivity());
        HttpGroup httpGroup = HttpGroup.getHttpGroup(createNewSettings);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setEffect(0);
        httpSetting.setNotifyUser(false);
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setFunctionId("genToken");
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.utils.CommonBase.3
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                String string = fastJsonObject.getString("tokenKey");
                if (string == null) {
                    onError(null);
                    return;
                }
                String string2 = fastJsonObject.getString("url");
                if (string2 == null) {
                    onError(null);
                    return;
                }
                if (OKLog.D) {
                    OKLog.d(CommonBase.TAG, "fun:genToken onEnd() -->> token = " + string);
                    OKLog.d(CommonBase.TAG, "fun:genToken onEnd() -->> url = " + string2);
                }
                BrowserUrlListener browserUrlListener2 = browserUrlListener;
                if (browserUrlListener2 != null && (browserUrlListener2 instanceof GenTokenStatusListener)) {
                    ((GenTokenStatusListener) browserUrlListener).gentokenStatus(fastJsonObject.optString("status"), fastJsonObject.optString("error_msg"), string);
                }
                URLParamMap uRLParamMap2 = uRLParamMap;
                if (uRLParamMap2 != null) {
                    uRLParamMap2.put("tokenKey", string);
                }
                if (!SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.WEBURLNOLBS, false)) {
                    JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
                    jDLocationCacheOption.setBusinessId(WebConstants.LBS_ID);
                    JDLocation location = JDLocationCache.getInstance().getLocation(jDLocationCacheOption);
                    if (location != null) {
                        double lat = location.getLat();
                        double lng = location.getLng();
                        if (lat != 0.0d || lng != 0.0d) {
                            JSONObject jSONObject = new JSONObject();
                            if (lat != 0.0d) {
                                try {
                                    jSONObject.put("lat", "" + lat);
                                } catch (JSONException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            if (lng != 0.0d) {
                                jSONObject.put(HybridSDK.LNG, "" + lng);
                            }
                            if (location.getProvinceId() != 0) {
                                jSONObject.put(JDWeatherActionKey.PROVINCE_ID, "" + location.getProvinceId());
                            }
                            if (location.getCityId() != 0) {
                                jSONObject.put(JDWeatherActionKey.CITY_ID, "" + location.getCityId());
                            }
                            if (location.getDistrictId() != 0) {
                                jSONObject.put("districtId", "" + location.getDistrictId());
                            }
                            if (location.getProvinceName() != null) {
                                jSONObject.put("provinceName", "" + location.getProvinceName());
                            }
                            if (location.getCityName() != null) {
                                jSONObject.put("cityName", "" + location.getCityName());
                            }
                            if (location.getDistrictName() != null) {
                                jSONObject.put("districtName", "" + location.getDistrictName());
                            }
                            URLParamMap uRLParamMap3 = uRLParamMap;
                            if (uRLParamMap3 != null) {
                                uRLParamMap3.put("lbs", jSONObject.toString());
                            }
                        }
                    }
                }
                String mergerUrlAndParams = HttpGroup.mergerUrlAndParams(string2, uRLParamMap);
                if (OKLog.D) {
                    OKLog.d("Temp", "queryBrowserUrl() mergerUrl -->> " + mergerUrlAndParams);
                }
                BrowserUrlListener browserUrlListener3 = browserUrlListener;
                if (browserUrlListener3 != null) {
                    browserUrlListener3.onComplete(mergerUrlAndParams);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                BrowserUrlListener browserUrlListener2 = browserUrlListener;
                if (browserUrlListener2 instanceof BrowserNewUrlListener) {
                    ((BrowserNewUrlListener) browserUrlListener2).onError(httpError);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                BrowserUrlListener browserUrlListener2 = browserUrlListener;
                if (browserUrlListener2 != null && (browserUrlListener2 instanceof BrowserAllUrlListener)) {
                    ((BrowserAllUrlListener) browserUrlListener2).onReady();
                }
                httpSettingParams.putJsonParam("action", str);
                URLParamMap uRLParamMap2 = uRLParamMap;
                if (uRLParamMap2 == null || uRLParamMap2.get((Object) RemoteMessageConst.TO) == null) {
                    return;
                }
                httpSettingParams.putJsonParam(RemoteMessageConst.TO, uRLParamMap.get((Object) RemoteMessageConst.TO));
            }
        });
        httpGroup.add(httpSetting);
    }

    public static void toBrowser(Uri uri) {
        try {
            ExceptionReporter.reportWebViewCommonError("CommonBaseToBrowser", uri.toString(), "", "");
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, "CommonBaseToBrowser--webview", e2);
            }
        }
        Intent newBrowserIntent = newBrowserIntent(uri, true);
        try {
            IMyActivity currentMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity();
            if (currentMyActivity == null) {
                return;
            }
            if (isIntentAvailable(newBrowserIntent)) {
                currentMyActivity.startActivityNoException(newBrowserIntent);
            } else {
                currentMyActivity.startActivityNoException(newBrowserIntent(uri, false));
            }
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }
}
