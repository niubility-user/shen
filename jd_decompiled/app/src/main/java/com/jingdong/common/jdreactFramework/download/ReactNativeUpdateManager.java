package com.jingdong.common.jdreactFramework.download;

import android.content.Context;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.utils.ReactSharedPreferenceUtils;
import com.jingdong.common.jdreactFramework.utils.ReactVersionUtils;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class ReactNativeUpdateManager {
    private static final String TAG = "ReactNativeUpdateManage";
    private static ReactNativeUpdateManager helper;
    private Context rnContext;
    private ConcurrentHashMap<String, PluginDownloadInfo> forceDownloadMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, PluginDownloadInfo> debugdownloadMap = new ConcurrentHashMap<>();

    private PluginListenerNew createWrapperListener(final PluginDownloadInfo pluginDownloadInfo, final PluginListenerNew pluginListenerNew) {
        return new PluginListenerNew() { // from class: com.jingdong.common.jdreactFramework.download.ReactNativeUpdateManager.4
            {
                ReactNativeUpdateManager.this = this;
            }

            @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
            public void onDownloadProgressChanged(int i2) {
                PluginListenerNew pluginListenerNew2 = pluginListenerNew;
                if (pluginListenerNew2 != null) {
                    pluginListenerNew2.onDownloadProgressChanged(i2);
                }
            }

            @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
            public void onFailure(String str, String str2, boolean z, String str3) {
                PluginListenerNew pluginListenerNew2 = pluginListenerNew;
                if (pluginListenerNew2 != null) {
                    pluginListenerNew2.onFailure(str, str2, z, str3);
                }
                PluginDownloadInfo pluginDownloadInfo2 = pluginDownloadInfo;
                if (pluginDownloadInfo2 != null) {
                    pluginDownloadInfo2.unregisiterListener(this);
                }
            }

            @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
            public void onFinish(PluginUpdateInfo pluginUpdateInfo) {
                PluginListenerNew pluginListenerNew2 = pluginListenerNew;
                if (pluginListenerNew2 != null) {
                    pluginListenerNew2.onFinish(pluginUpdateInfo);
                }
                PluginDownloadInfo pluginDownloadInfo2 = pluginDownloadInfo;
                if (pluginDownloadInfo2 != null) {
                    pluginDownloadInfo2.unregisiterListener(this);
                }
                ReactNativeUpdateManager.this.forceDownloadMap.remove(pluginUpdateInfo.pluginUpdateName);
            }
        };
    }

    public static synchronized ReactNativeUpdateManager getInstance() {
        ReactNativeUpdateManager reactNativeUpdateManager;
        synchronized (ReactNativeUpdateManager.class) {
            if (helper == null) {
                helper = new ReactNativeUpdateManager();
            }
            reactNativeUpdateManager = helper;
        }
        return reactNativeUpdateManager;
    }

    private void startToLoad(PluginDownloadInfo pluginDownloadInfo) {
        startToLoad(pluginDownloadInfo, false);
    }

    public void addDebugDownloadTask(PluginDownloadInfo pluginDownloadInfo) {
        final String str = pluginDownloadInfo.getPluginResult().pluginUpdateName;
        PluginListenerNew pluginListenerNew = new PluginListenerNew() { // from class: com.jingdong.common.jdreactFramework.download.ReactNativeUpdateManager.1
            {
                ReactNativeUpdateManager.this = this;
            }

            @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
            public void onDownloadProgressChanged(int i2) {
            }

            @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
            public void onFailure(String str2, String str3, boolean z, String str4) {
                PluginDownloadInfo pluginDownloadInfo2 = (PluginDownloadInfo) ReactNativeUpdateManager.this.debugdownloadMap.get(str);
                if (pluginDownloadInfo2 != null) {
                    pluginDownloadInfo2.unregisiterListener(this);
                }
            }

            @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
            public void onFinish(PluginUpdateInfo pluginUpdateInfo) {
                PluginDownloadInfo pluginDownloadInfo2 = (PluginDownloadInfo) ReactNativeUpdateManager.this.debugdownloadMap.get(str);
                if (pluginDownloadInfo2 != null) {
                    pluginDownloadInfo2.unregisiterListener(this);
                }
                ReactNativeUpdateManager.this.debugdownloadMap.remove(str);
            }
        };
        this.debugdownloadMap.put(pluginDownloadInfo.getPluginResult().pluginUpdateName, pluginDownloadInfo);
        pluginDownloadInfo.regisiterListener(pluginListenerNew);
        pluginDownloadInfo.setHttpSetting(pluginDownloadInfo.createHttpSettingforDebug(pluginDownloadInfo.getPluginResult()));
        startToLoad(pluginDownloadInfo);
    }

    public boolean addDownloadTask(final String str, final PluginListener pluginListener) {
        try {
            for (PluginDownloadInfo pluginDownloadInfo : ReactVersionDiff.getReactDownloadLists()) {
                if (pluginDownloadInfo.getPluginResult().pluginUpdateName.equals(str)) {
                    pluginDownloadInfo.regisiterListener(new PluginListener() { // from class: com.jingdong.common.jdreactFramework.download.ReactNativeUpdateManager.2
                        {
                            ReactNativeUpdateManager.this = this;
                        }

                        @Override // com.jingdong.common.jdreactFramework.download.PluginListener
                        public void onDownloadProgressChanged(int i2) {
                            PluginListener pluginListener2 = pluginListener;
                            if (pluginListener2 != null) {
                                pluginListener2.onDownloadProgressChanged(i2);
                            }
                        }

                        @Override // com.jingdong.common.jdreactFramework.download.PluginListener
                        public void onFailure(String str2) {
                            PluginListener pluginListener2 = pluginListener;
                            if (pluginListener2 != null) {
                                pluginListener2.onFailure(str2);
                            }
                            PluginDownloadInfo pluginDownloadInfo2 = (PluginDownloadInfo) ReactNativeUpdateManager.this.forceDownloadMap.get(str);
                            if (pluginDownloadInfo2 != null) {
                                pluginDownloadInfo2.unregisiterListener(this);
                            }
                        }

                        @Override // com.jingdong.common.jdreactFramework.download.PluginListener
                        public void onFinish(PluginUpdateInfo pluginUpdateInfo) {
                            PluginListener pluginListener2 = pluginListener;
                            if (pluginListener2 != null) {
                                pluginListener2.onFinish(pluginUpdateInfo);
                            }
                            PluginDownloadInfo pluginDownloadInfo2 = (PluginDownloadInfo) ReactNativeUpdateManager.this.forceDownloadMap.get(str);
                            if (pluginDownloadInfo2 != null) {
                                pluginDownloadInfo2.unregisiterListener(this);
                            }
                        }
                    });
                    this.forceDownloadMap.put(pluginDownloadInfo.getPluginResult().pluginUpdateName, pluginDownloadInfo);
                    pluginDownloadInfo.setHttpSetting(pluginDownloadInfo.createHttpSetting(pluginDownloadInfo.getPluginResult()));
                    startToLoad(pluginDownloadInfo);
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public void addForceDownloadTask(PluginDownloadInfo pluginDownloadInfo) {
        addDownloadTask(pluginDownloadInfo, true);
    }

    public boolean addForceDownloadTaskListener(String str, PluginListenerNew pluginListenerNew) {
        JLog.d(TAG, " addForceDownloadTaskListener \uff1a" + str);
        PluginDownloadInfo pluginDownloadInfo = this.forceDownloadMap.get(str);
        if ((pluginDownloadInfo != null && pluginDownloadInfo.getPluginResult().downType == 0) || (pluginDownloadInfo != null && pluginDownloadInfo.getPluginResult().downType == 1 && ReactVersionUtils.getCommonPluginVersion(pluginDownloadInfo.getPluginResult().pluginCommonVersion) != null)) {
            pluginDownloadInfo.unregisiterListener(pluginListenerNew);
            pluginDownloadInfo.regisiterListener(createWrapperListener(pluginDownloadInfo, pluginListenerNew));
            if (JDReactHelper.newInstance().useDownloadQueue()) {
                startToLoad(pluginDownloadInfo, true);
            }
            return true;
        }
        return addForceDownloadTask(str, true, createWrapperListener(pluginDownloadInfo, pluginListenerNew));
    }

    public PluginDownloadInfo getDegradeInfo(String str) {
        return ReactUpdateModelHelper.findPluginDegradeModel(str);
    }

    public PluginDownloadInfo getDownloadInfo(String str) {
        return ReactVersionDiff.getReactDownloadInfoByModuleName(str, true);
    }

    public PluginDownloadInfo getForceDownloadInfo(String str) {
        if (this.forceDownloadMap == null || TextUtils.isEmpty(str) || !this.forceDownloadMap.containsKey(str)) {
            return null;
        }
        return this.forceDownloadMap.get(str);
    }

    public String getServicePluginVersion(String str) {
        try {
            for (PluginDownloadInfo pluginDownloadInfo : ReactUpdateModelHelper.setPluginDownloadModel(ReactSharedPreferenceUtils.getUpdateInfo())) {
                if (pluginDownloadInfo.getPluginResult().pluginUpdateName.equals(str)) {
                    return pluginDownloadInfo.getPluginResult().pluginUpdateVersion;
                }
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public int getUpdateLevel(String str) {
        try {
            for (PluginDownloadInfo pluginDownloadInfo : ReactUpdateModelHelper.setPluginDownloadModel(ReactSharedPreferenceUtils.getUpdateInfo())) {
                if (pluginDownloadInfo.getPluginResult().pluginUpdateName.equals(str)) {
                    return pluginDownloadInfo.getPluginResult().upgradeLevel;
                }
            }
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    public boolean isItForceUpdate(String str) {
        try {
            for (PluginDownloadInfo pluginDownloadInfo : ReactUpdateModelHelper.setPluginDownloadModel(ReactSharedPreferenceUtils.getUpdateInfo())) {
                if (pluginDownloadInfo.getPluginResult().pluginUpdateName.equals(str) && DYConstants.DY_TRUE.equalsIgnoreCase(pluginDownloadInfo.getPluginResult().isItForceUpdate)) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return !TextUtils.isEmpty(str) && this.debugdownloadMap.containsKey(str);
    }

    public void unregistForceDownloadTaskListener(String str, PluginListenerNew pluginListenerNew) {
        PluginDownloadInfo pluginDownloadInfo = this.forceDownloadMap.get(str);
        if (pluginDownloadInfo != null) {
            pluginDownloadInfo.unregisiterListener(pluginListenerNew);
        }
    }

    private void startToLoad(PluginDownloadInfo pluginDownloadInfo, boolean z) {
        if (JDReactHelper.newInstance().useDownloadQueue()) {
            ReactNativeDownloadDispatcher.getInstance().enqueue(pluginDownloadInfo, z);
        } else {
            pluginDownloadInfo.getHttpSetting().startToload();
        }
    }

    public boolean addForceDownloadTask(String str, boolean z, PluginListenerNew pluginListenerNew) {
        return addDownloadTask(str, pluginListenerNew, z, true);
    }

    public void addDownloadTask(PluginDownloadInfo pluginDownloadInfo, boolean z) {
        final String str = pluginDownloadInfo.getPluginResult().pluginUpdateName;
        PluginListenerNew pluginListenerNew = new PluginListenerNew() { // from class: com.jingdong.common.jdreactFramework.download.ReactNativeUpdateManager.3
            {
                ReactNativeUpdateManager.this = this;
            }

            @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
            public void onDownloadProgressChanged(int i2) {
            }

            @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
            public void onFailure(String str2, String str3, boolean z2, String str4) {
                PluginDownloadInfo pluginDownloadInfo2 = (PluginDownloadInfo) ReactNativeUpdateManager.this.forceDownloadMap.get(str);
                if (pluginDownloadInfo2 != null) {
                    pluginDownloadInfo2.unregisiterListener(this);
                }
            }

            @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
            public void onFinish(PluginUpdateInfo pluginUpdateInfo) {
                PluginDownloadInfo pluginDownloadInfo2 = (PluginDownloadInfo) ReactNativeUpdateManager.this.forceDownloadMap.get(str);
                if (pluginDownloadInfo2 != null) {
                    pluginDownloadInfo2.unregisiterListener(this);
                }
                ReactNativeUpdateManager.this.forceDownloadMap.remove(str);
            }
        };
        this.forceDownloadMap.put(str, pluginDownloadInfo);
        pluginDownloadInfo.regisiterListener(pluginListenerNew);
        pluginDownloadInfo.setHttpSetting(pluginDownloadInfo.createHttpSetting(pluginDownloadInfo.getPluginResult(), z));
        startToLoad(pluginDownloadInfo);
    }

    public boolean addDownloadTask(String str, PluginListenerNew pluginListenerNew, boolean z, boolean z2) {
        PluginDownloadInfo pluginDownloadInfo = this.forceDownloadMap.get(str);
        if (pluginDownloadInfo == null) {
            pluginDownloadInfo = getDownloadInfo(str);
            if (pluginDownloadInfo == null) {
                return false;
            }
            this.forceDownloadMap.put(pluginDownloadInfo.getPluginResult().pluginUpdateName, pluginDownloadInfo);
        }
        pluginDownloadInfo.unregisiterListener(pluginListenerNew);
        pluginDownloadInfo.regisiterListener(createWrapperListener(pluginDownloadInfo, pluginListenerNew));
        JDReactHttpSetting createHttpSetting = pluginDownloadInfo.createHttpSetting(pluginDownloadInfo.getPluginResult(), z2);
        createHttpSetting.setTopPriority(z);
        pluginDownloadInfo.setHttpSetting(createHttpSetting);
        startToLoad(pluginDownloadInfo, z);
        return true;
    }
}
