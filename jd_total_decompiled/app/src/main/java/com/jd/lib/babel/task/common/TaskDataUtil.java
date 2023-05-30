package com.jd.lib.babel.task.common;

import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class TaskDataUtil {
    public static final long HOUR = 3600000;
    public static final String RECORDTIMESTAMP = "recordTimestamp";

    /* loaded from: classes14.dex */
    private static class SingletionInternalClassHolder {
        private static final TaskDataUtil instance = new TaskDataUtil();

        private SingletionInternalClassHolder() {
        }
    }

    public static TaskDataUtil getInstance() {
        return SingletionInternalClassHolder.instance;
    }

    public boolean isValid(long j2) {
        return SystemClock.elapsedRealtime() - j2 < 3600000;
    }

    public Map<String, Object> readData(@Nullable String str) {
        return readData(str, false);
    }

    public boolean writeData(@Nullable String str, @Nullable Map<String, Object> map) {
        String userPin = LoginUserBase.getUserPin();
        if (TextUtils.isEmpty(userPin) || TextUtils.isEmpty(str) || map == null || map.isEmpty()) {
            return false;
        }
        String encrypt16 = MD5.encrypt16(userPin);
        map.put(RECORDTIMESTAMP, String.valueOf(SystemClock.elapsedRealtime()));
        JSONObject jSONObject = new JSONObject(map);
        if (TextUtils.isEmpty(jSONObject.toString())) {
            return false;
        }
        SharedPreferencesUtil.putString(encrypt16 + str, jSONObject.toString());
        return true;
    }

    private TaskDataUtil() {
    }

    public Map<String, Object> readData(String str, boolean z) {
        String userPin = LoginUserBase.getUserPin();
        if (TextUtils.isEmpty(userPin)) {
            return new HashMap();
        }
        String str2 = MD5.encrypt16(userPin) + str;
        if (SharedPreferencesUtil.contains(str2)) {
            String string = SharedPreferencesUtil.getString(str2, "");
            if (TextUtils.isEmpty(string)) {
                return new HashMap();
            }
            try {
                Map<String, Object> jsonToMapObject = CommonUtil.jsonToMapObject(string);
                if (jsonToMapObject == null) {
                    return new HashMap();
                }
                if (z) {
                    return jsonToMapObject;
                }
                Object obj = jsonToMapObject.get(RECORDTIMESTAMP);
                if (obj == null) {
                    SharedPreferencesUtil.remove(str2);
                    return new HashMap();
                }
                try {
                    if (isValid(Long.valueOf(Long.parseLong(obj.toString())).longValue())) {
                        return jsonToMapObject;
                    }
                    SharedPreferencesUtil.remove(str2);
                    return new HashMap();
                } catch (Exception unused) {
                    SharedPreferencesUtil.remove(str2);
                }
            } catch (Exception unused2) {
                return new HashMap();
            }
        }
        return new HashMap();
    }
}
