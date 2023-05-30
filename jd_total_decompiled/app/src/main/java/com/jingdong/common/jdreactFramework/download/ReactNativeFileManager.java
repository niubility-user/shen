package com.jingdong.common.jdreactFramework.download;

import android.content.Context;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.utils.ReactVersionUtils;

/* loaded from: classes5.dex */
public class ReactNativeFileManager {
    private static final String TAG = "ReactNativeFileManager";
    public static final int TYPE_ALL = 0;
    public static final int TYPE_ONLY_FULL = 1;
    public static final int TYPE_ONLY_SPLIT = 2;

    public static PluginVersion getPluginDir(Context context, String str, boolean z) {
        return getPluginDir(context, str, z, 0);
    }

    public static PluginVersion getPluginDir(Context context, String str, boolean z, int i2) {
        PluginVersion pluginVersionPath;
        PluginVersion pluginVersion = null;
        if (i2 != 2) {
            try {
                pluginVersionPath = ReactVersionUtils.getPluginVersionPath(true, str);
            } catch (Exception e2) {
                JLog.d(TAG, "plugin data: null error is" + e2.getMessage());
                return new PluginVersion();
            }
        } else {
            pluginVersionPath = null;
        }
        if (i2 != 1) {
            PluginVersion pluginVersionPath2 = ReactVersionUtils.getPluginVersionPath(false, str);
            if (z || pluginVersionPath2.splitEnable) {
                pluginVersionPath = ReactVersionUtils.getNewPluginVersion(pluginVersionPath, pluginVersionPath2);
            }
        }
        PluginVersion pluginPreloadDataPath = ReactVersionUtils.getPluginPreloadDataPath(context, str);
        if (pluginPreloadDataPath != null) {
            if (!pluginPreloadDataPath.full) {
                if (i2 == 1) {
                    pluginPreloadDataPath = null;
                }
                if (!z && !pluginPreloadDataPath.splitEnable) {
                    return ReactVersionUtils.getNewPluginVersion(pluginVersion, pluginVersionPath);
                }
            } else if (i2 == 2) {
                return ReactVersionUtils.getNewPluginVersion(pluginVersion, pluginVersionPath);
            }
        }
        pluginVersion = pluginPreloadDataPath;
        return ReactVersionUtils.getNewPluginVersion(pluginVersion, pluginVersionPath);
    }

    public static PluginVersion getPluginDir(Context context, String str) {
        return getPluginDir(context, str, false);
    }
}
