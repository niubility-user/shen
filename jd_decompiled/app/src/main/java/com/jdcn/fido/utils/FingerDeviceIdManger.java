package com.jdcn.fido.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes18.dex */
public class FingerDeviceIdManger {
    private static final String FILE_NAME = "share_deviceId";
    private static AtomicReference<String> deviceId = new AtomicReference<>();

    public static String getOrGenerateDeviceId(Context context) {
        try {
            String str = deviceId.get();
            if (TextUtils.isEmpty(str)) {
                str = getParamString(context, "deviceId", "");
                if (TextUtils.isEmpty(str)) {
                    String uuid = UUID.randomUUID().toString();
                    if (!TextUtils.isEmpty(uuid)) {
                        if (deviceId.compareAndSet(null, uuid)) {
                            setParamString(context, "deviceId", deviceId.get());
                        }
                        str = deviceId.get();
                    }
                } else {
                    deviceId.compareAndSet(null, str);
                }
            }
            if (EnvUtil.getServerType() != 2 || TextUtils.isEmpty(str)) {
                return str;
            }
            return str + "evn_test";
        } catch (Throwable unused) {
            return "";
        }
    }

    private static String getParamString(Context context, String str, String str2) {
        return context.getSharedPreferences(FILE_NAME, 0).getString(str, str2);
    }

    private static void setParamString(Context context, String str, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences(FILE_NAME, 0).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public static String updateDeviceId(Context context) {
        try {
            String uuid = UUID.randomUUID().toString();
            if (TextUtils.isEmpty(uuid)) {
                uuid = "";
            } else {
                deviceId.set(uuid);
                setParamString(context, "deviceId", uuid);
            }
            if (EnvUtil.getServerType() != 2 || TextUtils.isEmpty(uuid)) {
                return uuid;
            }
            return uuid + "evn_test";
        } catch (Throwable unused) {
            return "";
        }
    }
}
