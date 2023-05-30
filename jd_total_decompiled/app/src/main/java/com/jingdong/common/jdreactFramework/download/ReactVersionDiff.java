package com.jingdong.common.jdreactFramework.download;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.utils.FileUtil;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.utils.ReactSharedPreferenceUtils;
import com.jingdong.common.jdreactFramework.utils.ReactVersionUtils;
import com.jingdong.common.jdreactFramework.utils.VersionUtils;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes5.dex */
public class ReactVersionDiff {
    private static final String TAG = "ReactVersionDiff";

    public static List<PluginDownloadInfo> getCacheDownloadLists() {
        LinkedList linkedList = new LinkedList();
        try {
            linkedList.addAll(ReactUpdateModelHelper.setPluginDownloadModel(ReactSharedPreferenceUtils.getUpdateInfo()));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (JDReactHelper.newInstance().isDebug()) {
            JLog.d(TAG, "cache list:" + linkedList.toString());
        }
        return linkedList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:145:0x006b, code lost:
        if (matchPatchData(r3, r2, r4) == false) goto L146;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x0078, code lost:
        if (r4 == null) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x007c, code lost:
        if (r4.full == false) goto L153;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x007e, code lost:
        r2 = "0";
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x0081, code lost:
        r2 = "3";
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x0087, code lost:
        if (matchPatchData(r3, r2, r4) != false) goto L157;
     */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0099 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:161:0x009a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static PluginDownloadInfo getReactDownloadInfo(PluginDownloadInfo pluginDownloadInfo, boolean z) {
        if (pluginDownloadInfo == null) {
            return null;
        }
        try {
            PluginVersion commonPluginVersion = ReactVersionUtils.getCommonPluginVersion(null);
            String str = "";
            String str2 = commonPluginVersion != null ? commonPluginVersion.pluginVersion : "";
            PluginUpdateInfo pluginResult = pluginDownloadInfo.getPluginResult();
            PluginVersion pluginDir = ReactNativeFileManager.getPluginDir(JDReactHelper.newInstance().getApplicationContext(), pluginResult.pluginUpdateName, true, 0);
            if (pluginDir != null && VersionUtils.compareVersion(pluginResult.pluginUpdateVersion, pluginDir.pluginVersion) <= 0) {
                return null;
            }
            if (z) {
                z = JDReactHelper.newInstance().isDiffUpdate();
            }
            if (!TextUtils.isEmpty(pluginResult.pluginDownloadSplitUrl) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(pluginResult.pluginCommonVersion) && VersionUtils.compareVersion(pluginResult.pluginCommonVersion, str2) <= 0) {
                if (z) {
                    if (pluginDir != null) {
                        str = pluginDir.full ? "1" : "2";
                    }
                }
                pluginResult.realDownloadUrl = pluginResult.pluginDownloadSplitUrl;
                pluginResult.isPatchDownload = false;
                pluginResult.downType = 1;
                if (TextUtils.isEmpty(pluginResult.realDownloadUrl)) {
                    JLog.d(TAG, "download list:" + pluginDownloadInfo.toString());
                    return pluginDownloadInfo;
                }
                return null;
            }
            pluginResult.realDownloadUrl = pluginResult.pluginDownloadUrl;
            pluginResult.isPatchDownload = false;
            pluginResult.downType = 0;
            if (TextUtils.isEmpty(pluginResult.realDownloadUrl)) {
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static PluginDownloadInfo getReactDownloadInfoByModuleName(String str, boolean z) {
        return getReactDownloadInfo(ReactUpdateModelHelper.getPluginDownloadModelByName(str, ReactSharedPreferenceUtils.getUpdateInfo()), z);
    }

    public static List<PluginDownloadInfo> getReactDownloadLists() {
        return getReactDownloadLists(null);
    }

    private static boolean matchPatchData(PluginUpdateInfo pluginUpdateInfo, String str, PluginVersion pluginVersion) {
        JDJSONArray jDJSONArray;
        if (pluginVersion != null && !pluginVersion.isPreset && (jDJSONArray = pluginUpdateInfo.patch) != null && !jDJSONArray.isEmpty()) {
            String str2 = pluginVersion.pluginDir + File.separator + pluginVersion.pluginName + ".jsbundle";
            for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
                JDJSONObject optJSONObject = jDJSONArray.optJSONObject(i2);
                String optString = optJSONObject.optString("type");
                if (!TextUtils.isEmpty(optJSONObject.optString("patchUrl")) && TextUtils.equals(optString, str) && validateFileMd5(str2, optJSONObject.optString("sourceMd5"))) {
                    pluginUpdateInfo.realDownloadUrl = optJSONObject.optString("patchUrl");
                    pluginUpdateInfo.realPatchMd5 = optJSONObject.optString("patchMd5");
                    pluginUpdateInfo.localSourcePath = pluginVersion.pluginDir;
                    pluginUpdateInfo.isPatchDownload = true;
                    pluginUpdateInfo.patchType = str;
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean validateFileMd5(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (TextUtils.isEmpty(str2)) {
            return true;
        }
        return TextUtils.equals(FileUtil.getFileMD5(new File(str)), str2);
    }

    public static List<PluginDownloadInfo> getReactDownloadLists(List<PluginDownloadInfo> list) {
        PluginDownloadInfo reactDownloadInfo;
        LinkedList linkedList = new LinkedList();
        if (list == null) {
            try {
                list = ReactUpdateModelHelper.setPluginDownloadModel(ReactSharedPreferenceUtils.getUpdateInfo());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (list == null) {
            return linkedList;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2) != null && list.get(i2).getPluginResult() != null && (reactDownloadInfo = getReactDownloadInfo(list.get(i2), true)) != null) {
                linkedList.add(reactDownloadInfo);
            }
        }
        if (JDReactHelper.newInstance().isDebug()) {
            JLog.d(TAG, "download list:" + linkedList.toString());
        }
        return linkedList;
    }
}
