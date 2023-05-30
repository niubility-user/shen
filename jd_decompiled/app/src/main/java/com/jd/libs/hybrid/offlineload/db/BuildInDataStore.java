package com.jd.libs.hybrid.offlineload.db;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.Nullable;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.hybrid.base.HybridBase;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.entity.IInterfaceCheck;
import com.jd.libs.hybrid.base.util.HybridDataStore;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.base.util.PreferenceUtils;
import com.jd.libs.hybrid.offlineload.entity.OfflineModule;
import com.jd.libs.hybrid.offlineload.utils.ModuleHelper;
import com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils;
import com.jd.libs.hybrid.offlineload.utils.OfflineFileHelper;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class BuildInDataStore extends HybridDataStore<OfflineModule> {

    /* renamed from: f */
    private static volatile BuildInDataStore f5951f;

    /* renamed from: e */
    protected String f5952e;

    public BuildInDataStore() {
        super("buildInData");
        this.f5952e = "BuildInDataStore";
        Context appContext = HybridSettings.getAppContext();
        if (c(appContext)) {
            return;
        }
        Pair<Long, String> buildInConfigVerFromAsset = OfflineFileHelper.getBuildInConfigVerFromAsset();
        long j2 = PreferenceUtils.getLong(appContext, "buildInConfigVer", 0L);
        long longValue = ((Long) buildInConfigVerFromAsset.first).longValue();
        if (longValue == 0 || longValue != j2) {
            if (longValue == 0) {
                PreferenceUtils.putLong(appContext, "buildInConfigVer", 0L);
                Log.d(this.f5952e, "[BuildInDataStore] No buildIn config file found, delete old build-in data.");
            } else {
                Log.d(this.f5952e, "[BuildInDataStore] Has new buildInVer, delete old build-in data and read from app again.cachedVer = " + j2 + ", buildInVer = " + longValue);
            }
            deleteAll();
        }
        Map<String, V> map = this.f5899c;
        if ((map == 0 || map.isEmpty()) && !TextUtils.isEmpty((CharSequence) buildInConfigVerFromAsset.second)) {
            Log.d(this.f5952e, "[BuildInDataStore] Read build-in config data from app");
            HashMap hashMap = new HashMap();
            String buildInConfigsFromAsset = OfflineFileHelper.getBuildInConfigsFromAsset((String) buildInConfigVerFromAsset.second);
            if (!TextUtils.isEmpty(buildInConfigsFromAsset)) {
                try {
                    JSONArray jSONArray = new JSONArray(buildInConfigsFromAsset);
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        try {
                            OfflineModule type4Module = ModuleHelper.getType4Module(jSONArray.getJSONObject(i2));
                            if (type4Module != null) {
                                ModuleHelper.resetUrl(type4Module);
                                type4Module.setBuildInConfig(true);
                                hashMap.put(type4Module.getAppid(), type4Module);
                            }
                        } catch (JSONException e2) {
                            Log.e(this.f5952e, e2);
                            OfflineExceptionUtils.reportConfigError("parseObj", "BuildInDataStore", (String) null, e2);
                        }
                    }
                } catch (JSONException e3) {
                    Log.e(this.f5952e, e3);
                    OfflineExceptionUtils.reportConfigError("parseArray", "BuildInDataStore", (String) null, e3);
                }
            }
            Map removeUseless = IInterfaceCheck.Companion.removeUseless(hashMap);
            if (!removeUseless.isEmpty()) {
                String obj = removeUseless.toString();
                if (Log.isDebug()) {
                    Log.e(this.f5952e, "[BuildInDataStore] Ignore illegal configs: " + obj);
                }
                OfflineExceptionUtils.reportConfigError(OfflineExceptionUtils.ERR_MSG_NET, "[Offline]\u53bb\u9664\u65e0\u7528\u5185\u7f6e\u914d\u7f6e", "", obj);
            }
            save(hashMap);
            PreferenceUtils.putLong(appContext, "buildInConfigVer", longValue);
        }
    }

    private boolean c(Context context) {
        if ("1".equals(HybridBase.getInstance().getSetting(HybridSDK.SWITCH_CLEAR_BUILD_IN))) {
            Map<String, V> map = this.f5899c;
            if (map == 0 || map.isEmpty()) {
                return true;
            }
            deleteAll();
            PreferenceUtils.putLong(context, "buildInConfigVer", 0L);
            return true;
        }
        return false;
    }

    public static BuildInDataStore getInstance() {
        if (f5951f == null) {
            synchronized (BuildInDataStore.class) {
                if (f5951f == null) {
                    f5951f = new BuildInDataStore();
                }
            }
        }
        return f5951f;
    }

    @Override // com.jd.libs.hybrid.base.util.HybridDataStore
    /* renamed from: b */
    public OfflineModule a(JSONObject jSONObject) throws JSONException {
        return new OfflineModule().fromJson(jSONObject);
    }

    @Override // com.jd.libs.hybrid.base.util.HybridDataStore
    public boolean contains(String str) {
        if (c(HybridSettings.getAppContext())) {
            return false;
        }
        return super.contains(str);
    }

    @Override // com.jd.libs.hybrid.base.util.HybridDataStore
    @Nullable
    public Map<String, OfflineModule> getAll() {
        if (c(HybridSettings.getAppContext())) {
            return null;
        }
        return super.getAll();
    }

    @Override // com.jd.libs.hybrid.base.util.HybridDataStore
    @Nullable
    public OfflineModule get(String str) {
        if (c(HybridSettings.getAppContext())) {
            return null;
        }
        return (OfflineModule) super.get(str);
    }
}
