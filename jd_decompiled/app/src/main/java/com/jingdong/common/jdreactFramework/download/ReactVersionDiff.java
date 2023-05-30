package com.jingdong.common.jdreactFramework.download;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.utils.FileUtil;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.utils.ReactSharedPreferenceUtils;
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

    /* JADX WARN: Code restructure failed: missing block: B:89:0x006b, code lost:
        if (matchPatchData(r3, r2, r4) == false) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0078, code lost:
        if (r4 == null) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x007c, code lost:
        if (r4.full == false) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x007e, code lost:
        r2 = "0";
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0081, code lost:
        r2 = "3";
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0087, code lost:
        if (matchPatchData(r3, r2, r4) != false) goto L101;
     */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0099 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:105:0x009a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.jingdong.common.jdreactFramework.download.PluginDownloadInfo getReactDownloadInfo(com.jingdong.common.jdreactFramework.download.PluginDownloadInfo r9, boolean r10) {
        /*
            r0 = 0
            if (r9 != 0) goto L4
            return r0
        L4:
            com.jingdong.common.jdreactFramework.download.PluginVersion r1 = com.jingdong.common.jdreactFramework.utils.ReactVersionUtils.getCommonPluginVersion(r0)     // Catch: java.lang.Exception -> Lb5
            java.lang.String r2 = ""
            if (r1 == 0) goto Lf
            java.lang.String r1 = r1.pluginVersion     // Catch: java.lang.Exception -> Lb5
            goto L10
        Lf:
            r1 = r2
        L10:
            com.jingdong.common.jdreactFramework.download.PluginUpdateInfo r3 = r9.getPluginResult()     // Catch: java.lang.Exception -> Lb5
            com.jingdong.common.jdreactFramework.JDReactHelper r4 = com.jingdong.common.jdreactFramework.JDReactHelper.newInstance()     // Catch: java.lang.Exception -> Lb5
            android.content.Context r4 = r4.getApplicationContext()     // Catch: java.lang.Exception -> Lb5
            java.lang.String r5 = r3.pluginUpdateName     // Catch: java.lang.Exception -> Lb5
            r6 = 1
            r7 = 0
            com.jingdong.common.jdreactFramework.download.PluginVersion r4 = com.jingdong.common.jdreactFramework.download.ReactNativeFileManager.getPluginDir(r4, r5, r6, r7)     // Catch: java.lang.Exception -> Lb5
            if (r4 == 0) goto L31
            java.lang.String r5 = r3.pluginUpdateVersion     // Catch: java.lang.Exception -> Lb5
            java.lang.String r8 = r4.pluginVersion     // Catch: java.lang.Exception -> Lb5
            int r5 = com.jingdong.common.jdreactFramework.utils.VersionUtils.compareVersion(r5, r8)     // Catch: java.lang.Exception -> Lb5
            if (r5 > 0) goto L31
            return r0
        L31:
            if (r10 == 0) goto L3b
            com.jingdong.common.jdreactFramework.JDReactHelper r10 = com.jingdong.common.jdreactFramework.JDReactHelper.newInstance()     // Catch: java.lang.Exception -> Lb5
            boolean r10 = r10.isDiffUpdate()     // Catch: java.lang.Exception -> Lb5
        L3b:
            java.lang.String r5 = r3.pluginDownloadSplitUrl     // Catch: java.lang.Exception -> Lb5
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> Lb5
            if (r5 != 0) goto L76
            boolean r5 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Exception -> Lb5
            if (r5 != 0) goto L76
            java.lang.String r5 = r3.pluginCommonVersion     // Catch: java.lang.Exception -> Lb5
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> Lb5
            if (r5 != 0) goto L76
            java.lang.String r5 = r3.pluginCommonVersion     // Catch: java.lang.Exception -> Lb5
            int r1 = com.jingdong.common.jdreactFramework.utils.VersionUtils.compareVersion(r5, r1)     // Catch: java.lang.Exception -> Lb5
            if (r1 <= 0) goto L5a
            goto L76
        L5a:
            if (r10 == 0) goto L6d
            if (r4 == 0) goto L67
            boolean r10 = r4.full     // Catch: java.lang.Exception -> Lb5
            if (r10 == 0) goto L65
            java.lang.String r2 = "1"
            goto L67
        L65:
            java.lang.String r2 = "2"
        L67:
            boolean r10 = matchPatchData(r3, r2, r4)     // Catch: java.lang.Exception -> Lb5
            if (r10 != 0) goto L73
        L6d:
            java.lang.String r10 = r3.pluginDownloadSplitUrl     // Catch: java.lang.Exception -> Lb5
            r3.realDownloadUrl = r10     // Catch: java.lang.Exception -> Lb5
            r3.isPatchDownload = r7     // Catch: java.lang.Exception -> Lb5
        L73:
            r3.downType = r6     // Catch: java.lang.Exception -> Lb5
            goto L91
        L76:
            if (r10 == 0) goto L89
            if (r4 == 0) goto L83
            boolean r10 = r4.full     // Catch: java.lang.Exception -> Lb5
            if (r10 == 0) goto L81
            java.lang.String r2 = "0"
            goto L83
        L81:
            java.lang.String r2 = "3"
        L83:
            boolean r10 = matchPatchData(r3, r2, r4)     // Catch: java.lang.Exception -> Lb5
            if (r10 != 0) goto L8f
        L89:
            java.lang.String r10 = r3.pluginDownloadUrl     // Catch: java.lang.Exception -> Lb5
            r3.realDownloadUrl = r10     // Catch: java.lang.Exception -> Lb5
            r3.isPatchDownload = r7     // Catch: java.lang.Exception -> Lb5
        L8f:
            r3.downType = r7     // Catch: java.lang.Exception -> Lb5
        L91:
            java.lang.String r10 = r3.realDownloadUrl     // Catch: java.lang.Exception -> Lb5
            boolean r10 = android.text.TextUtils.isEmpty(r10)     // Catch: java.lang.Exception -> Lb5
            if (r10 == 0) goto L9a
            return r0
        L9a:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r0 = "download list:"
            r10.append(r0)
            java.lang.String r0 = r9.toString()
            r10.append(r0)
            java.lang.String r10 = r10.toString()
            java.lang.String r0 = "ReactVersionDiff"
            com.jingdong.common.jdreactFramework.utils.JLog.d(r0, r10)
            return r9
        Lb5:
            r9 = move-exception
            r9.printStackTrace()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.jdreactFramework.download.ReactVersionDiff.getReactDownloadInfo(com.jingdong.common.jdreactFramework.download.PluginDownloadInfo, boolean):com.jingdong.common.jdreactFramework.download.PluginDownloadInfo");
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
