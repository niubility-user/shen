package com.jdjr.risk.device.c;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes18.dex */
public class f {
    public static Intent a(Context context) {
        Intent intent = new Intent();
        if (context != null) {
            try {
                return context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            } catch (Exception unused) {
                return intent;
            }
        }
        return intent;
    }

    public static String a(Intent intent) {
        String str;
        String str2 = "";
        try {
            int batteryStatus = BaseInfo.getBatteryStatus();
            int batteryPlugged = BaseInfo.getBatteryPlugged();
            if (batteryStatus == 2) {
                str = batteryPlugged == 2 ? "BATTERY_STATUS_CHARGING:BATTERY_PLUGGED_USB" : "";
                if (batteryPlugged == 1) {
                    str = "BATTERY_STATUS_CHARGING:BATTERY_PLUGGED_AC";
                }
            } else {
                str = "";
            }
            if (batteryStatus == 3) {
                str = "BATTERY_STATUS_DISCHARGING";
            }
            if (batteryStatus == 4) {
                str = "BATTERY_STATUS_NOT_CHARGING";
            }
            if (batteryStatus == 5) {
                str = "BATTERY_STATUS_FULL";
            }
        } catch (Throwable unused) {
        }
        try {
            return "".equals(str) ? "BATTERY_STATUS_UNKNOWN" : str;
        } catch (Throwable unused2) {
            str2 = str;
            return str2;
        }
    }

    public static String b(Intent intent) {
        try {
            int batteryLevel = BaseInfo.getBatteryLevel();
            return ((batteryLevel * 100) / BaseInfo.getBatteryScale()) + "%";
        } catch (Throwable unused) {
            return "";
        }
    }
}
