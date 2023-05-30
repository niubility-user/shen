package com.jd.framework.network.impl;

import com.jd.framework.json.JDJSONObject;
import com.jd.framework.network.JDCacheChecker;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class JDCacheCheckerDefault implements JDCacheChecker {
    @Override // com.jd.framework.network.JDCacheChecker
    public <T> boolean isResponseCache(T t) {
        if (t instanceof JSONObject) {
            try {
                try {
                    return ((JSONObject) t).getInt("code") == 0;
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    return false;
                }
            } catch (Throwable unused) {
                return false;
            }
        }
        try {
            if (t instanceof JDJSONObject) {
                try {
                    return ((JDJSONObject) t).getIntValue("code") == 0;
                } catch (com.alibaba.fastjson.JSONException e3) {
                    e3.printStackTrace();
                    return false;
                }
            }
            return true;
        } catch (Throwable unused2) {
        }
    }
}
