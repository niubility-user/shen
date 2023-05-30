package com.jingdong.common.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import com.jd.lib.productdetail.core.entitys.NoStockRecommendHead;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import com.jingdong.common.unification.navigationbar.db.NavigationDbConstants;
import com.jingdong.common.utils.ServerConfigFetcher;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpGroupSetting;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class GlobalInitialization {
    private static final String TAG = "JDGlobalInit";
    static final int TASK_TOKEN_REGISTER_DEVICE = 2;
    public static final int TASK_TOKEN_SERVER_CONFIG = 1;
    private static GlobalInitialization globalInitialization;
    private HttpGroup httpGroup;
    public boolean alreadyDevice = false;
    public boolean alreadyConfig = false;
    int runningTaskFlags = 0;

    public static boolean canDoABTest() {
        return FunctionAccessUtil.checkFunctionReady(Configuration.KEY_LAST_ACCESS_ABTEST, "keyAbTestBackFunctionIdAccessThresholdMS");
    }

    public static boolean canDoDevice() {
        return FunctionAccessUtil.checkFunctionReady(Configuration.KEY_LAST_ACCESS_DEVICE, Configuration.KEY_DEVICE_THRESHOLD);
    }

    static boolean canDoServerConfig() {
        return FunctionAccessUtil.checkFunctionReady(Configuration.KEY_LAST_ACCESS_SERVERCONFIG, Configuration.KEY_SERVERCONFIG_THRESHOLD);
    }

    public static synchronized GlobalInitialization getGlobalInitializationInstance() {
        GlobalInitialization globalInitialization2;
        synchronized (GlobalInitialization.class) {
            if (globalInitialization == null) {
                globalInitialization = new GlobalInitialization();
            }
            globalInitialization2 = globalInitialization;
        }
        return globalInitialization2;
    }

    public static boolean isDeviceFirstCalled() {
        return CommonBase.getJdSharedPreferences().getLong(Configuration.KEY_LAST_ACCESS_DEVICE, -1L) == -1;
    }

    private synchronized void networkInitializationEnd() {
        if (OKLog.D) {
            OKLog.d(TAG, "GlobalInitialization networkInitializationEnd() -->> ");
        }
        if (this.alreadyDevice && this.alreadyConfig) {
            BaseFrameUtil.getInstance().networkInitializationState = 2;
        } else {
            BaseFrameUtil.getInstance().networkInitializationState = 0;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "GlobalInitialization networkInitializationEnd() with " + BaseFrameUtil.getInstance().networkInitializationState);
        }
    }

    public static void regDevice() {
        if (!canDoDevice() || BaseFrameUtil.getInstance().getCurrentMyActivity() == null) {
            return;
        }
        getGlobalInitializationInstance().registerDevice(false);
    }

    public static void requestConfig() {
        if (OKLog.D) {
            OKLog.d(TAG, "------request--Config--");
        }
        final int intFromPreference = ConfigUtil.getIntFromPreference(ConfigUtil.SHARED_DATA_VERSION, 0);
        long longFromPreference = ConfigUtil.getLongFromPreference(ConfigUtil.SHARED_CACHE_TIME, 0L);
        if (longFromPreference == 0 || longFromPreference <= System.currentTimeMillis()) {
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setFunctionId("clientConfig");
            httpSetting.setCacheMode(2);
            httpSetting.setAttempts(2);
            httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.PERSONAL_HOME_HOST));
            httpSetting.putJsonParam(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_DATAVERSION, Integer.valueOf(intFromPreference));
            httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.utils.GlobalInitialization.2
                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    int optInt;
                    String str;
                    String str2;
                    JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                    if (OKLog.D) {
                        OKLog.d(GlobalInitialization.TAG, jSONObject.toString());
                    }
                    if (jSONObject == null || !"0".equals(jSONObject.optString("code")) || (optInt = jSONObject.optInt(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_DATAVERSION)) == intFromPreference) {
                        return;
                    }
                    int optInt2 = jSONObject.optInt("cacheTime");
                    JSONObjectProxy jSONObjectOrNull = jSONObject.getJSONObjectOrNull("clientConfig");
                    String str3 = "";
                    if (jSONObjectOrNull != null) {
                        str3 = jSONObjectOrNull.optString(ABTestUtils.KEY_PARAM_NOT_CONTROL_LIST);
                        str2 = jSONObjectOrNull.optString(ABTestUtils.KEY_PARAM_CONTROL_LIST);
                        str = jSONObjectOrNull.optString(ABTestUtils.KEY_PARAM_MAX_NUM);
                    } else {
                        str = "";
                        str2 = str;
                    }
                    SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
                    edit.putInt(ConfigUtil.SHARED_DATA_VERSION, optInt);
                    edit.putString(ConfigUtil.SHARED_NOT_STACK_ACTIVITY, str3);
                    edit.putLong(ConfigUtil.SHARED_CACHE_TIME, System.currentTimeMillis() + (optInt2 * 1000));
                    edit.putString(ConfigUtil.SHARED_CONTROL_ACTIVITY, str2);
                    edit.putString(ConfigUtil.SHARED_MAX_STACK_NUM, str);
                    edit.commit();
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
                public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                }
            });
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        }
    }

    public HttpGroup getHttpGroup() {
        IMyActivity currentMyActivity;
        if (this.httpGroup == null && (currentMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity()) != null) {
            HttpGroupSetting createNewSettings = HttpGroupUtils.createNewSettings();
            createNewSettings.setType(1000);
            createNewSettings.setMyActivity((Activity) currentMyActivity);
            this.httpGroup = HttpGroup.getHttpGroup(createNewSettings);
        }
        return this.httpGroup;
    }

    public void registerDevice(boolean z) {
        if (BaseFrameUtil.getInstance().getCurrentMyActivity() == null) {
            return;
        }
        if (z) {
            OKLog.d(TAG, "GlobalInitialization registerDevice() skip for CPA ");
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "GlobalInitialization registerDevice() BEING with isFirst= " + z);
        }
        setTaskBeginFlag(2);
        JSONObject jSONObject = null;
        try {
            jSONObject = new JSONObject(StatisticsReportUtil.getDeviceInfoStr());
        } catch (JSONException e2) {
            OKLog.e(TAG, e2);
        }
        HttpGroup.CustomOnAllListener customOnAllListener = new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.common.utils.GlobalInitialization.3
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                try {
                    CommonBase.getJdSharedPreferences().edit().putBoolean("registerDevice", true).commit();
                } catch (Exception e3) {
                    if (OKLog.D) {
                        OKLog.e(GlobalInitialization.TAG, e3);
                    }
                }
                GlobalInitialization.this.alreadyDevice = true;
                FunctionAccessUtil.updateLastAccess(Configuration.KEY_LAST_ACCESS_DEVICE);
                GlobalInitialization.this.setTaskEndFlag(2);
                if (OKLog.D) {
                    OKLog.d(GlobalInitialization.TAG, "GlobalInitialization registerDevice() END-->> ");
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                GlobalInitialization.this.setTaskEndFlag(2);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        };
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setJsonParams(jSONObject);
        httpSetting.setFunctionId(NoStockRecommendHead.DEVICE);
        httpSetting.setHost(Configuration.getNgwHost());
        httpSetting.setListener(customOnAllListener);
        httpSetting.setNeedGlobalInitialization(false);
        httpSetting.setTopPriority(true);
        getHttpGroup().add(httpSetting);
    }

    public void serverConfig(ServerConfigFetcher.ServerConfigListener serverConfigListener) {
        ExceptionReporter.reportDeviceUUIDParamMta();
        ServerConfigFetcher.getFetcher().fetch(serverConfigListener, new ServerConfigFetcher.FetchStateListener() { // from class: com.jingdong.common.utils.GlobalInitialization.1
            @Override // com.jingdong.common.utils.ServerConfigFetcher.FetchStateListener
            public void onEnd() {
                GlobalInitialization.this.alreadyConfig = !ConfigUtil.sServerConfigHashMap.isEmpty();
                GlobalInitialization.this.setTaskEndFlag(1);
            }

            @Override // com.jingdong.common.utils.ServerConfigFetcher.FetchStateListener
            public void onError() {
                GlobalInitialization.this.setTaskEndFlag(1);
            }

            @Override // com.jingdong.common.utils.ServerConfigFetcher.FetchStateListener
            public void onPreRequest() {
                GlobalInitialization.this.setTaskBeginFlag(1);
            }
        });
    }

    public void setTaskBeginFlag(int i2) {
        this.runningTaskFlags = i2 | this.runningTaskFlags;
    }

    void setTaskEndFlag(int i2) {
        int i3 = (i2 ^ (-1)) & this.runningTaskFlags;
        this.runningTaskFlags = i3;
        if (i3 == 0) {
            networkInitializationEnd();
        }
    }
}
