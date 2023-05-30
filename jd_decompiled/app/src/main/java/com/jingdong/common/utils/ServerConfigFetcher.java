package com.jingdong.common.utils;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import com.jd.framework.json.JDJSONObject;
import com.jd.framework.network.toolbox.JDVolley;
import com.jingdong.cleanmvp.engine.HttpGroupUtil;
import com.jingdong.common.appupdate.UpdateSharedPreferenceUtil;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.jdsdk.network.config.RuntimeConfigHelper;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JsonEncryptUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes6.dex */
public class ServerConfigFetcher {
    public static final String TAG = "ServerConfigFetcher";
    private static volatile ServerConfigFetcher manager;
    private ArrayList<ConfigLoadedListener> configListenerList = new ArrayList<>();
    private boolean isFetching = false;

    /* loaded from: classes6.dex */
    public interface ConfigLoadedListener {
        void onLoaded();
    }

    /* loaded from: classes6.dex */
    public interface FetchStateListener {
        void onEnd();

        void onError();

        void onPreRequest();
    }

    /* loaded from: classes6.dex */
    public interface ServerConfigListener {
        void onGetConfigSuccess();
    }

    private ServerConfigFetcher() {
    }

    public static ServerConfigFetcher getFetcher() {
        if (manager == null) {
            synchronized (ServerConfigFetcher.class) {
                if (manager == null) {
                    manager = new ServerConfigFetcher();
                }
            }
        }
        return manager;
    }

    private void saveAutoUpgradeByWifi(boolean z) {
        if (UpdateSharedPreferenceUtil.getBoolean(Constants.UPGRADE_WIFI_SETTED_KEY, false, -1)) {
            return;
        }
        UpdateSharedPreferenceUtil.putBoolean(Constants.UPGRADE_WIFI_AUTO_KEY, z, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveServerConfigResult(JDJSONObject jDJSONObject) {
        long currentTimeMillis = System.currentTimeMillis();
        SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
        edit.remove(RuntimeConfigHelper.HTTPS_CONFIG);
        edit.commit();
        ConcurrentHashMap<String, String> concurrentHashMap = ConfigUtil.sServerConfigHashMap;
        if (!concurrentHashMap.isEmpty()) {
            concurrentHashMap.clear();
        }
        for (Map.Entry<String, Object> entry : jDJSONObject.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                String obj = value.toString();
                edit.putString(key, obj);
                ConfigUtil.sServerConfigHashMap.put(key, obj);
                if (OKLog.D) {
                    OKLog.d(TAG, "===== ServerConfig ====== Key : " + key + ", Value : " + obj);
                }
            }
        }
        if (!ConfigUtil.sServerConfigHashMap.isEmpty()) {
            edit.putLong(ConfigUtil.SERVER_CONFIG_SAVED_TIMESTAMP, currentTimeMillis);
        }
        edit.putBoolean("serverConfig", true);
        try {
            edit.commit();
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public synchronized void addConfigLoadedListener(ConfigLoadedListener configLoadedListener) {
        ArrayList<ConfigLoadedListener> arrayList = this.configListenerList;
        if (arrayList != null && configLoadedListener != null) {
            arrayList.add(configLoadedListener);
        }
    }

    public void fetch(final ServerConfigListener serverConfigListener, final FetchStateListener fetchStateListener) {
        if (this.isFetching) {
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "GlobalInitialization serverConfig() BEGIN-->> ");
        }
        if (fetchStateListener != null) {
            fetchStateListener.onPreRequest();
        }
        HttpGroup.CustomOnAllListener customOnAllListener = new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.common.utils.ServerConfigFetcher.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                ServerConfigListener serverConfigListener2;
                JDJSONObject optJSONObject = httpResponse.getFastJsonObject().optJSONObject("serverConfig");
                if (optJSONObject != null) {
                    ServerConfigFetcher.this.saveServerConfigResult(optJSONObject);
                    synchronized (ServerConfigFetcher.this) {
                        for (int i2 = 0; i2 < ServerConfigFetcher.this.configListenerList.size(); i2++) {
                            ((ConfigLoadedListener) ServerConfigFetcher.this.configListenerList.get(i2)).onLoaded();
                        }
                    }
                    JDVolley.updateAdditionalHeaders(ConfigUtil.getKeySwitchState(ConfigUtil.KEY_NETWORK_KEEPALIVE_SWITCH));
                    if (JsonEncryptUtil.shouldEncryptBody()) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jingdong.common.utils.ServerConfigFetcher.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                JsonEncryptUtil.initPhcEngineSDK(JdSdk.getInstance().getApplicationContext());
                            }
                        });
                    }
                    if (ConfigUtil.get(19) && (serverConfigListener2 = serverConfigListener) != null) {
                        serverConfigListener2.onGetConfigSuccess();
                    }
                }
                FetchStateListener fetchStateListener2 = fetchStateListener;
                if (fetchStateListener2 != null) {
                    fetchStateListener2.onEnd();
                }
                FunctionAccessUtil.updateLastAccess(Configuration.KEY_LAST_ACCESS_SERVERCONFIG);
                if (OKLog.D) {
                    OKLog.d(ServerConfigFetcher.TAG, "GlobalInitialization serverConfig() END-->> ");
                }
                ServerConfigFetcher.this.isFetching = false;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                FetchStateListener fetchStateListener2 = fetchStateListener;
                if (fetchStateListener2 != null) {
                    fetchStateListener2.onError();
                }
                ServerConfigFetcher.this.isFetching = false;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
                ServerConfigFetcher.this.isFetching = true;
            }
        };
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("serverConfig");
        httpSetting.setHost(Configuration.getNgwHost());
        httpSetting.setListener(customOnAllListener);
        httpSetting.setNeedGlobalInitialization(false);
        httpSetting.setTopPriority(true);
        httpSetting.setUseFastJsonParser(true);
        new HttpGroupUtil().getHttpGroupaAsynPool().add(httpSetting);
    }

    public synchronized void removeConfigLoadedListener(ConfigLoadedListener configLoadedListener) {
        ArrayList<ConfigLoadedListener> arrayList = this.configListenerList;
        if (arrayList != null && configLoadedListener != null) {
            arrayList.remove(configLoadedListener);
        }
    }
}
