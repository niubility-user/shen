package com.jingdong.common.jdreactFramework.download;

import com.jd.framework.json.JDJSONArray;

/* loaded from: classes5.dex */
public class PluginUpdateInfo {
    public static final int DOWN_TYPE_FULL = 0;
    public static final int DOWN_TYPE_SPLIT = 1;
    public static final int STATUS_COMPLETED = 1;
    public static final int STATUS_DOWNLOADING = 2;
    public static final int STATUS_NO_DOWNLOAD = 0;
    private static final String TAG = "PluginUpdateInfo";
    public double PluginId;
    public String isItForceUpdate;
    public boolean isPatchDownload;
    public String localSourcePath;
    public JDJSONArray patch;
    public String pluginCommonVersion;
    public String pluginDownloadSplitUrl;
    public String pluginDownloadUrl;
    public boolean pluginIsEncrypted;
    private PluginListenerNew pluginListener;
    public String pluginUpdateName;
    public String pluginUpdateVersion;
    public String pluginUpgradeFlag;
    public String realDownloadUrl;
    public String realPatchMd5;
    public int status;
    public int upgradeLevel;
    public String zipPathSignature;
    public String zipSplitPathSignature;
    public int downType = 0;
    public String patchType = "";

    public PluginListenerNew getPluginListener() {
        return this.pluginListener;
    }

    public boolean isItUseAssets() {
        return false;
    }

    public void setPluginListener(PluginListenerNew pluginListenerNew) {
        this.pluginListener = pluginListenerNew;
    }

    public String toString() {
        return "PluginUpdateInfo{pluginUpdateName='" + this.pluginUpdateName + "', pluginUpdateVersion='" + this.pluginUpdateVersion + "', pluginCommonVersion='" + this.pluginCommonVersion + "', isItForceUpdate='" + this.isItForceUpdate + "', realDownloadUrl='" + this.realDownloadUrl + "', downType=" + this.downType + '}';
    }
}
