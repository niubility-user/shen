package com.jingdong.common.jdreactFramework.download;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.utils.ReactModuleAvailabilityUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class ReactUpdateModelHelper {
    public static PluginDownloadInfo findPluginDegradeModel(String str) {
        String moduleBackupZip = ReactModuleAvailabilityUtils.getModuleBackupZip(str, null);
        if (TextUtils.isEmpty(moduleBackupZip)) {
            return null;
        }
        JDJSONObject parseObject = JDJSON.parseObject(moduleBackupZip);
        PluginDownloadInfo pluginDownloadInfo = new PluginDownloadInfo();
        PluginUpdateInfo pluginUpdateInfo = new PluginUpdateInfo();
        pluginUpdateInfo.pluginUpdateName = str;
        pluginUpdateInfo.pluginUpdateVersion = parseObject.optString("versionCode");
        pluginUpdateInfo.realDownloadUrl = parseObject.optString("zipPath");
        pluginUpdateInfo.zipPathSignature = parseObject.optString("zipPathSignature");
        pluginUpdateInfo.isItForceUpdate = DYConstants.DY_TRUE;
        pluginUpdateInfo.downType = 0;
        pluginDownloadInfo.setPluginResult(pluginUpdateInfo);
        return pluginDownloadInfo;
    }

    public static PluginDownloadInfo getPluginDownloadModelByName(String str, JDJSONArray jDJSONArray) throws Exception {
        if (jDJSONArray != null && jDJSONArray.size() > 0) {
            for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
                JDJSONObject optJSONObject = jDJSONArray.optJSONObject(i2);
                if (TextUtils.equals(optJSONObject.optString(JDReactConstant.ModuleCode).trim(), str)) {
                    PluginDownloadInfo pluginDownloadInfo = new PluginDownloadInfo();
                    PluginUpdateInfo pluginUpdateInfo = new PluginUpdateInfo();
                    pluginUpdateInfo.pluginUpdateName = optJSONObject.optString(JDReactConstant.ModuleCode).trim();
                    pluginUpdateInfo.pluginUpdateVersion = optJSONObject.optString("versionCode");
                    pluginUpdateInfo.pluginDownloadUrl = optJSONObject.optString("zipPath");
                    pluginUpdateInfo.zipPathSignature = optJSONObject.optString("zipPathSignature");
                    pluginUpdateInfo.pluginDownloadSplitUrl = optJSONObject.optString("zipSplitPath");
                    pluginUpdateInfo.zipSplitPathSignature = optJSONObject.optString("zipSplitPathSignature");
                    pluginUpdateInfo.pluginCommonVersion = optJSONObject.optString("commonVersion");
                    pluginUpdateInfo.patch = optJSONObject.optJSONArray("patch");
                    if (optJSONObject.containsKey("upgradeLevel")) {
                        int optInt = optJSONObject.optInt("upgradeLevel", -1);
                        pluginUpdateInfo.upgradeLevel = optInt;
                        if (optInt == 1) {
                            pluginUpdateInfo.isItForceUpdate = DYConstants.DY_TRUE;
                        } else if (optInt == 2) {
                            pluginUpdateInfo.isItForceUpdate = DYConstants.DY_TRUE;
                        } else if (optInt == 3) {
                            pluginUpdateInfo.isItForceUpdate = DYConstants.DY_FALSE;
                        } else if (optInt == 4) {
                            pluginUpdateInfo.isItForceUpdate = DYConstants.DY_FALSE;
                        } else {
                            pluginUpdateInfo.isItForceUpdate = optJSONObject.optString("isNeed");
                        }
                    } else {
                        pluginUpdateInfo.isItForceUpdate = optJSONObject.optString("isNeed");
                        pluginUpdateInfo.upgradeLevel = 0;
                    }
                    pluginDownloadInfo.setPluginResult(pluginUpdateInfo);
                    return pluginDownloadInfo;
                }
            }
        }
        return null;
    }

    public static List<PluginDownloadInfo> setPluginDownloadModel(JDJSONArray jDJSONArray) throws Exception {
        ArrayList arrayList = new ArrayList();
        if (jDJSONArray != null && jDJSONArray.size() > 0) {
            for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
                JDJSONObject optJSONObject = jDJSONArray.optJSONObject(i2);
                PluginDownloadInfo pluginDownloadInfo = new PluginDownloadInfo();
                PluginUpdateInfo pluginUpdateInfo = new PluginUpdateInfo();
                pluginUpdateInfo.pluginUpdateName = optJSONObject.optString(JDReactConstant.ModuleCode).trim();
                pluginUpdateInfo.pluginUpdateVersion = optJSONObject.optString("versionCode");
                pluginUpdateInfo.pluginDownloadUrl = optJSONObject.optString("zipPath");
                pluginUpdateInfo.zipPathSignature = optJSONObject.optString("zipPathSignature");
                pluginUpdateInfo.pluginDownloadSplitUrl = optJSONObject.optString("zipSplitPath");
                pluginUpdateInfo.zipSplitPathSignature = optJSONObject.optString("zipSplitPathSignature");
                pluginUpdateInfo.pluginCommonVersion = optJSONObject.optString("commonVersion");
                pluginUpdateInfo.patch = optJSONObject.optJSONArray("patch");
                if (optJSONObject.containsKey("upgradeLevel")) {
                    int optInt = optJSONObject.optInt("upgradeLevel", -1);
                    pluginUpdateInfo.upgradeLevel = optInt;
                    if (optInt == 1) {
                        pluginUpdateInfo.isItForceUpdate = DYConstants.DY_TRUE;
                    } else if (optInt == 2) {
                        pluginUpdateInfo.isItForceUpdate = DYConstants.DY_TRUE;
                    } else if (optInt == 3) {
                        pluginUpdateInfo.isItForceUpdate = DYConstants.DY_FALSE;
                    } else if (optInt == 4) {
                        pluginUpdateInfo.isItForceUpdate = DYConstants.DY_FALSE;
                    } else {
                        pluginUpdateInfo.isItForceUpdate = optJSONObject.optString("isNeed");
                    }
                } else {
                    pluginUpdateInfo.isItForceUpdate = optJSONObject.optString("isNeed");
                    pluginUpdateInfo.upgradeLevel = 0;
                }
                pluginDownloadInfo.setPluginResult(pluginUpdateInfo);
                arrayList.add(i2, pluginDownloadInfo);
            }
        }
        return arrayList;
    }

    public static PluginDownloadInfo getPluginDownloadModelByName(String str, String str2) {
        try {
            return getPluginDownloadModelByName(str, JDJSON.parseArray(str2));
        } catch (Exception unused) {
            return null;
        }
    }

    public static List<PluginDownloadInfo> setPluginDownloadModel(String str) {
        try {
            return setPluginDownloadModel(JDJSON.parseArray(str));
        } catch (Exception unused) {
            return new ArrayList();
        }
    }
}
