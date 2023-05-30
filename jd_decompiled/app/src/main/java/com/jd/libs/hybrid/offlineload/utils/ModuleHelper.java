package com.jd.libs.hybrid.offlineload.utils;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.jd.libs.hybrid.base.HybridBase;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.HybridUrlUtils;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.db.OfflineDataStore;
import com.jd.libs.hybrid.offlineload.db.TestOfflineDataStore;
import com.jd.libs.hybrid.offlineload.entity.Module;
import com.jd.libs.hybrid.offlineload.entity.OfflineModule;
import com.jd.libs.hybrid.offlineload.entity.TestModule;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class ModuleHelper {
    public static int BCONFIG_CONTAIN_HTML = 5;
    public static int BCONFIG_DOWNLOAD_DEGRADE = 2;
    public static int BCONFIG_IS_SHARED_PKG = 4;
    public static int BCONFIG_PRELOAD_HTML = 6;
    private static Map<String, Integer> a = null;
    private static int b = -1;

    private ModuleHelper() {
    }

    public static void deleteModule(Module module) {
        if (module != null) {
            if (module instanceof TestModule) {
                TestOfflineDataStore.getInstance().delete((TestModule) module);
            } else {
                OfflineDataStore.getInstance().delete((OfflineModule) module);
            }
            OfflineFileHelper.deleteEntityFile(module);
        }
    }

    public static void deleteModules(Collection<OfflineModule> collection) {
        if (collection == null || collection.isEmpty()) {
            return;
        }
        OfflineDataStore.getInstance().delete(collection);
        Iterator<OfflineModule> it = collection.iterator();
        while (it.hasNext()) {
            OfflineFileHelper.deleteEntityFile(it.next());
        }
    }

    @Nullable
    public static OfflineModule getType4Module(JSONObject jSONObject) throws JSONException {
        if (jSONObject != null && "4".equals(jSONObject.optString("type", ""))) {
            return new OfflineModule().fromJson(jSONObject);
        }
        return null;
    }

    public static boolean hasNewModuleVersion(Module module, Module module2) {
        if (module == null || module2 == null) {
            return false;
        }
        return module.getModuleCode() - module2.getModuleCode() > 0 || (!TextUtils.isEmpty(module.getExtendedVersion()) && !TextUtils.isEmpty(module2.getExtendedVersion()) && !module.getExtendedVersion().equals(module2.getExtendedVersion()));
    }

    public static boolean hasSameLatestVersion(Module module) {
        Map<String, Integer> map;
        Integer num;
        if (module != null && !TextUtils.isEmpty(module.getAppid())) {
            String setting = HybridBase.getInstance().getSetting("latestVersionMap");
            int hashCode = setting != null ? setting.hashCode() : -1;
            if (hashCode != b) {
                try {
                    if (TextUtils.isEmpty(setting)) {
                        map = null;
                    } else {
                        JSONObject jSONObject = new JSONObject(setting);
                        Iterator<String> keys = jSONObject.keys();
                        map = null;
                        while (keys.hasNext()) {
                            try {
                                String next = keys.next();
                                int optInt = jSONObject.optInt(next, -1);
                                if (!TextUtils.isEmpty(next) && optInt > -1) {
                                    if (map == null) {
                                        map = new HashMap(jSONObject.length());
                                    }
                                    map.put(next, Integer.valueOf(optInt));
                                }
                            } catch (JSONException e2) {
                                e = e2;
                                Log.e("ModuleHelper", e);
                                OfflineExceptionUtils.reportMatchError("checkSameLatest", "hasSameLatestVersion", module.getAppid(), (String) null, e);
                                Log.d("ModuleHelper", "Latest version map info changed: " + map);
                                a = map;
                                b = hashCode;
                                if (map != null) {
                                }
                                return false;
                            }
                        }
                    }
                } catch (JSONException e3) {
                    e = e3;
                    map = null;
                }
                Log.d("ModuleHelper", "Latest version map info changed: " + map);
                a = map;
                b = hashCode;
            } else {
                map = a;
            }
            if (map != null || map.isEmpty() || (num = map.get(module.getAppid())) == null || num.intValue() > module.getModuleCode()) {
                return false;
            }
            if (num.intValue() < module.getModuleCode()) {
                OfflineExceptionUtils.reportConfigError("Less module version in latest-vers-map", "hasSameLatestVersion", module.getAppid(), (String) null);
                return false;
            }
            return true;
        }
        return false;
    }

    public static void markVisited(Module module) {
        if (module == null) {
            return;
        }
        module.markVisited();
        module.tryIncreaseLpWhenVisited();
        saveModule(module);
    }

    public static boolean needCalculatePriority() {
        long j2 = HybridSettings.LAST_SET_LP_TIME;
        if (j2 == 0 || !DateUtils.isSameDay(j2, System.currentTimeMillis())) {
            HybridSettings.setLastSetLocalPriorityTime(System.currentTimeMillis());
            Log.d("ModuleHelper", "Need to recalculate entity's priority for today. last time = " + j2);
            return true;
        }
        return false;
    }

    public static void resetUrl(Module module) {
        if (module == null || module.isRegexpMatch()) {
            return;
        }
        String originalUrl = module.getOriginalUrl();
        if (TextUtils.isEmpty(originalUrl)) {
            return;
        }
        try {
            module.setOriginalUrl(HybridUrlUtils.excludeQuery(originalUrl));
        } catch (Exception e2) {
            Log.e("ModuleHelper", e2);
            OfflineExceptionUtils.reportDownloadCodeError("resetUrl", module.getAppid(), null, e2);
        }
    }

    public static void saveModule(Module module) {
        if (module instanceof TestModule) {
            TestOfflineDataStore.getInstance().save((TestModule) module);
        } else {
            OfflineDataStore.getInstance().save((OfflineModule) module);
        }
    }

    public static void saveModules(Collection<OfflineModule> collection) {
        OfflineDataStore.getInstance().save(collection);
    }

    public static void saveModules(Map<String, OfflineModule> map) {
        OfflineDataStore.getInstance().save(map);
    }

    public static void deleteModules(Map<String, OfflineModule> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        OfflineDataStore.getInstance().delete(map);
        Iterator<OfflineModule> it = map.values().iterator();
        while (it.hasNext()) {
            OfflineFileHelper.deleteEntityFile(it.next());
        }
    }
}
