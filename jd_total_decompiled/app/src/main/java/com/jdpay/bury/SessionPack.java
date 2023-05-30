package com.jdpay.bury;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jdpay.bury.proguard.APIKeep;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import jpbury.k0;

@APIKeep
/* loaded from: classes18.dex */
public final class SessionPack {
    public static final String KEY_APP_ID = "appId";
    public static final String KEY_APP_SOURCE = "appSource";
    public static final String KEY_BIZ_NAME = "bizName";
    public static final String KEY_MERCHANT_NO = "merchantNo";
    public static final String KEY_MODE = "mode";
    public static final String KEY_ORDER_ID = "orderId";
    public static final String KEY_PAY_PARAM = "payParam";
    public static final String KEY_PIN_MARK = "pinMark";
    public static final String KEY_PT_KEY = "ptKey";
    public static final String KEY_SESSION_KEY = "sessionKey";
    public static final String KEY_UUID = "uuid";
    private final Map<String, String> a;
    private String b;

    public SessionPack() {
        HashMap hashMap = new HashMap();
        this.a = hashMap;
        this.b = UUID.randomUUID().toString();
        hashMap.put(KEY_MERCHANT_NO, "");
        hashMap.put("orderId", "");
        hashMap.put(KEY_PT_KEY, "");
        hashMap.put(KEY_BIZ_NAME, "");
    }

    public SessionPack(@NonNull String str) {
        this();
        this.a.put(KEY_PIN_MARK, k0.a(str));
    }

    public SessionPack(@NonNull String str, @NonNull String str2) {
        this();
        this.a.put("payParam", str);
        this.a.put("appId", str2);
    }

    public SessionPack(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        this();
        Map<String, String> map;
        String str4;
        if ("H5".equals(str3)) {
            map = this.a;
            str4 = KEY_PT_KEY;
        } else {
            map = this.a;
            str4 = "sessionKey";
        }
        map.put(str4, str);
        this.a.put(KEY_APP_SOURCE, str2);
    }

    public Map<String, String> getSessions() {
        return this.a;
    }

    public String getUUID() {
        return this.b;
    }

    public void pack() {
        if ("H5".equals(this.a.get("mode"))) {
            String str = this.a.get("sessionKey");
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.a.remove("mode");
            this.a.remove("sessionKey");
            this.a.put(KEY_PT_KEY, str);
        }
    }

    public SessionPack setDeviceId(String str) {
        this.a.put("deviceId", str);
        return this;
    }

    public SessionPack with(String str, String str2) {
        this.a.put(str, str2);
        return this;
    }

    public SessionPack with(Map<String, String> map) {
        this.a.putAll(map);
        return this;
    }

    public SessionPack withSession(@NonNull String str, @NonNull String str2) {
        this.a.put("payParam", str);
        this.a.put("appId", str2);
        return this;
    }

    public SessionPack withSession(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        Map<String, String> map;
        String str4;
        if ("H5".equals(str3)) {
            map = this.a;
            str4 = KEY_PT_KEY;
        } else {
            map = this.a;
            str4 = "sessionKey";
        }
        map.put(str4, str);
        this.a.put(KEY_APP_SOURCE, str2);
        return this;
    }
}
