package com.jingdong.common.jdreactFramework.utils;

import android.content.Context;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.jd.framework.json.JDJSON;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.download.PluginVersion;
import com.jingdong.common.jdreactFramework.download.ReactNativeFileManager;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class ReactVersionUtils {
    private static final String TAG = "ReactVersionUtils";
    private static Map<String, String> mPreloadVersionXmls;
    private static Lock mLock = new ReentrantLock();
    private static PluginVersion lastCommonPlugin = null;

    public static PluginVersion getBlockPluginVersion(String str, String str2) {
        PluginVersion pluginVersion = new PluginVersion();
        try {
            PluginVersion pluginVersion2 = XmlUtils.getPluginVersion(str + File.separator + str2 + ".version");
            if (pluginVersion2 != null) {
                pluginVersion.pluginName = str2;
                pluginVersion.pluginVersion = pluginVersion2.pluginVersion;
                pluginVersion.pluginDir = str;
                pluginVersion.testmodeVersion = pluginVersion2.testmodeVersion;
                pluginVersion.commitId = pluginVersion2.commitId;
            }
        } catch (Exception unused) {
        }
        return pluginVersion;
    }

    public static PluginVersion getCommonPluginVersion() {
        return getCommonPluginVersion(null);
    }

    public static Map<String, String> getDataPluginVersionLists(boolean z, boolean z2) {
        PluginVersion newPluginVersion;
        ArrayMap arrayMap = new ArrayMap();
        Map<String, String> scanDownloadPluginPaths = XmlUtils.scanDownloadPluginPaths();
        if (scanDownloadPluginPaths != null && !scanDownloadPluginPaths.isEmpty()) {
            Iterator<Map.Entry<String, String>> it = scanDownloadPluginPaths.entrySet().iterator();
            while (it.hasNext()) {
                String key = it.next().getKey();
                PluginVersion pluginVersionPath = (z || TextUtils.equals(key, JDReactConstant.COMMON_BUNDLE_NAME)) ? getPluginVersionPath(true, key) : null;
                PluginVersion pluginVersionPath2 = getPluginVersionPath(false, key);
                if (z2) {
                    newPluginVersion = getNewPluginVersion(pluginVersionPath, pluginVersionPath2);
                } else {
                    if (!pluginVersionPath2.splitEnable) {
                        pluginVersionPath2 = null;
                    }
                    newPluginVersion = getNewPluginVersion(pluginVersionPath2, pluginVersionPath);
                }
                String str = newPluginVersion != null ? newPluginVersion.pluginVersion : null;
                if (!TextUtils.isEmpty(str)) {
                    arrayMap.put(key, str);
                }
            }
        }
        return arrayMap;
    }

    public static Map<String, String> getMergedPluginVersionLists() {
        return getMergedPluginVersionLists(getPreloadPluginVersionLists(), getDataPluginVersionLists());
    }

    public static PluginVersion getNewPluginVersion(PluginVersion pluginVersion, PluginVersion pluginVersion2) {
        return pluginVersion == null ? pluginVersion2 : (pluginVersion2 != null && VersionUtils.compareVersion(pluginVersion.pluginVersion, pluginVersion2.pluginVersion) < 0) ? pluginVersion2 : pluginVersion;
    }

    public static PluginVersion getPluginPreloadDataPath(Context context, String str) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(JDReactConstant.ASSERT_DIR);
            String str2 = File.separator;
            sb.append(str2);
            sb.append(str);
            sb.append(str2);
            sb.append(str);
            sb.append(".version");
            String sb2 = sb.toString();
            String str3 = JDReactConstant.ASSERT_DIR + str2 + str;
            PluginVersion pluginVersion = XmlUtils.getPluginVersion(context.getAssets().open(sb2));
            if (pluginVersion != null) {
                pluginVersion.isPreset = true;
                pluginVersion.pluginDir = str3;
                if (!TextUtils.equals(str, JDReactConstant.COMMON_BUNDLE_NAME)) {
                    PluginVersion commonPluginVersion = getCommonPluginVersion(pluginVersion.pluginCommonVersion);
                    if (commonPluginVersion != null) {
                        pluginVersion.pluginCommonDir = commonPluginVersion.pluginDir + JDReactConstant.COMMON_BUNDLE_RELATIVE_PATH;
                        pluginVersion.pluginCommonVersion = commonPluginVersion.pluginVersion;
                        pluginVersion.splitEnable = true;
                        pluginVersion.full = false;
                    } else {
                        pluginVersion.full = true;
                    }
                } else {
                    pluginVersion.full = true;
                    lastCommonPlugin = pluginVersion;
                }
            }
            return pluginVersion;
        } catch (Exception unused) {
            return null;
        }
    }

    private static PluginVersion getPluginVersionForCache(boolean z, String str) {
        PluginVersion pluginVersion = new PluginVersion();
        pluginVersion.full = z;
        pluginVersion.isPreset = false;
        StringBuilder sb = new StringBuilder();
        sb.append(JDReactConstant.ReactDownloadPath.getAbsolutePath());
        String str2 = File.separator;
        sb.append(str2);
        sb.append(ReactSharedPreferenceUtils.getCurPath(z, str));
        String sb2 = sb.toString();
        PluginVersion pluginVersion2 = XmlUtils.getPluginVersion(sb2 + str2 + str + ".version");
        if (pluginVersion2 != null) {
            pluginVersion.pluginName = str;
            pluginVersion.pluginVersion = pluginVersion2.pluginVersion;
            pluginVersion.pluginDir = sb2;
            pluginVersion.testmodeVersion = pluginVersion2.testmodeVersion;
            pluginVersion.commitId = pluginVersion2.commitId;
            pluginVersion.pluginCommonVersion = pluginVersion2.pluginCommonVersion;
        }
        return pluginVersion;
    }

    public static PluginVersion getPluginVersionForPath(String str, String str2) {
        PluginVersion pluginVersion = new PluginVersion();
        try {
            boolean contains = str.contains("/full/");
            pluginVersion.full = contains;
            pluginVersion.isPreset = false;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            String str3 = File.separator;
            sb.append(str3);
            sb.append(str2);
            sb.append(".version");
            PluginVersion pluginVersion2 = XmlUtils.getPluginVersion(sb.toString());
            if (pluginVersion2 != null) {
                pluginVersion.pluginName = str2;
                pluginVersion.pluginVersion = pluginVersion2.pluginVersion;
                pluginVersion.pluginDir = str;
                pluginVersion.testmodeVersion = pluginVersion2.testmodeVersion;
                pluginVersion.commitId = pluginVersion2.commitId;
                pluginVersion.pluginCommonVersion = pluginVersion2.pluginCommonVersion;
            }
            if (!contains) {
                PluginVersion commonPluginVersion = getCommonPluginVersion(pluginVersion.pluginCommonVersion);
                if (commonPluginVersion != null) {
                    pluginVersion.pluginCommonDir = commonPluginVersion.pluginDir + str3 + JDReactConstant.COMMON_BUNDLE_NAME + ".jsbundle";
                    pluginVersion.pluginCommonVersion = commonPluginVersion.pluginVersion;
                    pluginVersion.splitEnable = true;
                } else {
                    pluginVersion.splitEnable = false;
                }
            }
        } catch (Exception unused) {
        }
        return pluginVersion;
    }

    public static ArrayList<PluginVersion> getPluginVersionListsMap(String str) {
        String str2;
        ArrayList<PluginVersion> arrayList = new ArrayList<>();
        Map<String, String> scanPluginPaths = XmlUtils.scanPluginPaths(new File(str));
        if (scanPluginPaths != null && !scanPluginPaths.isEmpty()) {
            for (Map.Entry<String, String> entry : scanPluginPaths.entrySet()) {
                PluginVersion pluginVersion = XmlUtils.getPluginVersion(((Object) entry.getValue()) + File.separator + entry.getKey() + ".version");
                if (pluginVersion != null && (str2 = pluginVersion.pluginVersion) != null && pluginVersion != null && !TextUtils.isEmpty(str2)) {
                    arrayList.add(pluginVersion);
                }
            }
        }
        return arrayList;
    }

    public static PluginVersion getPluginVersionPath(boolean z, String str) {
        PluginVersion pluginVersion;
        try {
            pluginVersion = getPluginVersionForCache(z, str);
            if (!z) {
                try {
                    PluginVersion commonPluginVersion = getCommonPluginVersion(pluginVersion.pluginCommonVersion);
                    if (commonPluginVersion != null) {
                        pluginVersion.pluginCommonDir = commonPluginVersion.pluginDir + File.separator + JDReactConstant.COMMON_BUNDLE_NAME + ".jsbundle";
                        pluginVersion.pluginCommonVersion = commonPluginVersion.pluginVersion;
                        pluginVersion.splitEnable = true;
                    } else {
                        pluginVersion.splitEnable = false;
                    }
                } catch (Exception unused) {
                }
            }
        } catch (Exception unused2) {
            pluginVersion = null;
        }
        return pluginVersion != null ? pluginVersion : new PluginVersion();
    }

    public static Map<String, String> getPreloadPluginVersionLists() {
        Map<String, String> arrayMap;
        PluginVersion pluginVersion;
        String str;
        mLock.lock();
        try {
            Map<String, String> map = mPreloadVersionXmls;
            if (map != null && map.size() != 0) {
                return mPreloadVersionXmls;
            }
            String preloadPackage = ReactSharedPreferenceUtils.getPreloadPackage(JDReactConstant.SHARE_PREFRENCE_NAME);
            if (!TextUtils.isEmpty(preloadPackage) && (arrayMap = (Map) JDJSON.parse(preloadPackage)) != null && arrayMap.size() != 0) {
                mPreloadVersionXmls = arrayMap;
            } else {
                arrayMap = new ArrayMap<>();
                ArrayMap arrayMap2 = (ArrayMap) XmlUtils.scanPreloadVersion();
                if (arrayMap2 != null && !arrayMap2.isEmpty()) {
                    Iterator it = arrayMap2.entrySet().iterator();
                    Context applicationContext = JDReactHelper.newInstance().getApplicationContext();
                    while (it.hasNext()) {
                        String str2 = (String) ((Map.Entry) it.next()).getValue();
                        if (!TextUtils.isEmpty(str2)) {
                            InputStream inputStream = null;
                            PluginVersion pluginVersion2 = null;
                            InputStream inputStream2 = null;
                            try {
                                InputStream open = applicationContext.getAssets().open(str2);
                                try {
                                    pluginVersion2 = XmlUtils.getPluginVersion(open);
                                    pluginVersion2.isPreset = true;
                                    if (open != null) {
                                        try {
                                            open.close();
                                        } catch (IOException unused) {
                                        }
                                    }
                                } catch (IOException unused2) {
                                    PluginVersion pluginVersion3 = pluginVersion2;
                                    inputStream2 = open;
                                    pluginVersion = pluginVersion3;
                                    if (inputStream2 != null) {
                                        try {
                                            inputStream2.close();
                                        } catch (IOException unused3) {
                                        }
                                    }
                                    pluginVersion2 = pluginVersion;
                                    if (pluginVersion2 != null) {
                                        arrayMap.put(pluginVersion2.pluginName, str);
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    inputStream = open;
                                    if (inputStream != null) {
                                        try {
                                            inputStream.close();
                                        } catch (IOException unused4) {
                                        }
                                    }
                                    throw th;
                                }
                            } catch (IOException unused5) {
                                pluginVersion = null;
                            } catch (Throwable th2) {
                                th = th2;
                            }
                            if (pluginVersion2 != null && (str = pluginVersion2.pluginVersion) != null) {
                                arrayMap.put(pluginVersion2.pluginName, str);
                            }
                        }
                    }
                    Map<String, String> map2 = mPreloadVersionXmls;
                    if (map2 != null) {
                        map2.clear();
                    }
                    mPreloadVersionXmls = arrayMap;
                    if (arrayMap != null && arrayMap.size() != 0) {
                        ReactSharedPreferenceUtils.savePreloadPackage(JDReactConstant.SHARE_PREFRENCE_NAME, new JSONObject(mPreloadVersionXmls).toString());
                    }
                }
            }
            return arrayMap;
        } finally {
            mLock.unlock();
        }
    }

    public static String mergedVersion(String str) throws Exception {
        PluginVersion pluginDir = ReactNativeFileManager.getPluginDir(JDReactHelper.newInstance().getApplicationContext(), str);
        if (pluginDir != null) {
            return pluginDir.pluginVersion;
        }
        return null;
    }

    public static int weUsePreloadData(Context context, String str) {
        String str2;
        String str3;
        PluginVersion newPluginVersion = getNewPluginVersion(getPluginVersionPath(true, str), getPluginVersionPath(false, str));
        PluginVersion pluginPreloadDataPath = getPluginPreloadDataPath(context, str);
        if (newPluginVersion != null && (str2 = newPluginVersion.pluginVersion) != null && pluginPreloadDataPath != null && (str3 = pluginPreloadDataPath.pluginVersion) != null) {
            return VersionUtils.compareVersion(str3, str2) >= 0 ? 1 : 0;
        } else if (newPluginVersion == null || newPluginVersion.pluginVersion == null) {
            return (pluginPreloadDataPath == null || pluginPreloadDataPath.pluginVersion == null) ? -1 : 1;
        } else {
            return 0;
        }
    }

    public static PluginVersion getCommonPluginVersion(String str) {
        PluginVersion pluginVersion = lastCommonPlugin;
        if (pluginVersion == null) {
            pluginVersion = getPluginPreloadDataPath(JDReactHelper.newInstance().getApplicationContext(), JDReactConstant.COMMON_BUNDLE_NAME);
        }
        if (TextUtils.isEmpty(str) || pluginVersion == null || VersionUtils.compareVersion(pluginVersion.pluginVersion, str) >= 0) {
            return pluginVersion;
        }
        return null;
    }

    public static Map<String, String> getMergedPluginVersionLists(Map<String, String> map, Map<String, String> map2) {
        ArrayMap arrayMap = new ArrayMap();
        if ((map == null || map.size() == 0) && (map2 == null || map2.size() == 0)) {
            return arrayMap;
        }
        if (map2 == null || map2.size() == 0) {
            return map;
        }
        if (map.size() == 0) {
            return map2;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (map2.containsKey(key)) {
                try {
                    String str = map2.get(key);
                    if (VersionUtils.compareVersion(value, str) >= 0) {
                        arrayMap.put(key, value);
                    } else {
                        arrayMap.put(key, str);
                    }
                } catch (Exception unused) {
                    if (value != null) {
                        arrayMap.put(key, value);
                    }
                }
            } else {
                arrayMap.put(key, value);
            }
        }
        for (Map.Entry<String, String> entry2 : map2.entrySet()) {
            String key2 = entry2.getKey();
            String value2 = entry2.getValue();
            if (!arrayMap.containsKey(key2)) {
                arrayMap.put(key2, value2);
            }
        }
        return arrayMap;
    }

    public static ArrayList<PluginVersion> getPluginVersionListsMap(String str, String str2) {
        ArrayList<PluginVersion> arrayList = new ArrayList<>();
        Map<String, String> scanPluginPaths = XmlUtils.scanPluginPaths(new File(str));
        if (scanPluginPaths != null && !scanPluginPaths.isEmpty()) {
            for (Map.Entry<String, String> entry : scanPluginPaths.entrySet()) {
                StringBuilder sb = new StringBuilder();
                sb.append((Object) entry.getValue());
                String str3 = File.separator;
                sb.append(str3);
                sb.append(str2);
                sb.append(".version");
                PluginVersion pluginVersion = XmlUtils.getPluginVersion(sb.toString());
                if (pluginVersion != null && pluginVersion.pluginVersion != null) {
                    pluginVersion.pluginDir = ((Object) entry.getValue()) + str3;
                    if (pluginVersion != null && !TextUtils.isEmpty(pluginVersion.pluginVersion)) {
                        arrayList.add(pluginVersion);
                    }
                }
            }
        }
        return arrayList;
    }

    public static Map<String, String> getDataPluginVersionLists() {
        ArrayMap arrayMap = new ArrayMap();
        Map<String, String> scanDownloadPluginPaths = XmlUtils.scanDownloadPluginPaths();
        if (scanDownloadPluginPaths != null && !scanDownloadPluginPaths.isEmpty()) {
            Iterator<Map.Entry<String, String>> it = scanDownloadPluginPaths.entrySet().iterator();
            while (it.hasNext()) {
                String key = it.next().getKey();
                PluginVersion newPluginVersion = getNewPluginVersion(getPluginVersionForCache(true, key), getPluginVersionForCache(false, key));
                String str = newPluginVersion == null ? null : newPluginVersion.pluginVersion;
                if (!TextUtils.isEmpty(str)) {
                    arrayMap.put(key, str);
                }
            }
        }
        return arrayMap;
    }

    public static Map<String, String> getPreloadPluginVersionLists(boolean z) {
        ArrayMap arrayMap;
        PluginVersion pluginPreloadDataPath;
        String str;
        long currentTimeMillis = System.currentTimeMillis();
        ArrayMap arrayMap2 = new ArrayMap();
        try {
            arrayMap = (ArrayMap) XmlUtils.scanPreloadVersion();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (arrayMap != null && !arrayMap.isEmpty()) {
            for (Map.Entry entry : arrayMap.entrySet()) {
                if (!TextUtils.isEmpty((String) entry.getValue()) && (pluginPreloadDataPath = getPluginPreloadDataPath(JDReactHelper.newInstance().getApplicationContext(), (String) entry.getKey())) != null && (str = pluginPreloadDataPath.pluginVersion) != null) {
                    if (z) {
                        arrayMap2.put(pluginPreloadDataPath.pluginName, str);
                    } else if (TextUtils.equals(pluginPreloadDataPath.pluginName, JDReactConstant.COMMON_BUNDLE_NAME)) {
                        arrayMap2.put(pluginPreloadDataPath.pluginName, pluginPreloadDataPath.pluginVersion);
                    } else if (pluginPreloadDataPath.splitEnable) {
                        arrayMap2.put(pluginPreloadDataPath.pluginName, pluginPreloadDataPath.pluginVersion);
                    }
                }
            }
            if (JDReactHelper.newInstance().isDebug()) {
                JLog.d(TAG, "handler time:" + (System.currentTimeMillis() - currentTimeMillis));
            }
            return arrayMap2;
        }
        return arrayMap2;
    }
}
