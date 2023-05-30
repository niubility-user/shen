package com.jingdong.common.web.util;

import android.text.TextUtils;
import com.jd.libs.hybrid.base.util.HybridUrlUtils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class UrlRedirectHelper {
    private static final String SWITCH_QUERY_CONFIG_KEY = "hybrid_redirect_urls";
    private static final String SWITCH_QUERY_ON_KEY = "is_hybrid_redirect_on";
    private static UrlRedirectHelper sInstance;
    private ConcurrentHashMap<String, String> mUrlMap = new ConcurrentHashMap<>();

    private UrlRedirectHelper() {
        parseConfig(SwitchQueryFetcher.getSwitchStringValue(SWITCH_QUERY_CONFIG_KEY, ""));
    }

    public static UrlRedirectHelper getInstance() {
        if (sInstance == null) {
            sInstance = new UrlRedirectHelper();
        }
        return sInstance;
    }

    public static boolean isSwitchOn() {
        return SwitchQueryFetcher.getSwitchBooleanValue(SWITCH_QUERY_ON_KEY, false);
    }

    private void parseConfig(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str.trim());
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String trim = keys.next().trim();
                this.mUrlMap.put(HybridUrlUtils.excludeQuery(trim), HybridUrlUtils.excludeQuery(jSONObject.optString(trim).trim()));
            }
        } catch (Throwable unused) {
        }
    }

    public String getRedirectUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            String excludeQuery = HybridUrlUtils.excludeQuery(str.trim());
            ConcurrentHashMap<String, String> concurrentHashMap = this.mUrlMap;
            if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
                return str;
            }
            String str2 = this.mUrlMap.get(excludeQuery);
            return !TextUtils.isEmpty(str2) ? str.replaceFirst(excludeQuery, str2) : str;
        } catch (Throwable unused) {
            return str;
        }
    }
}
