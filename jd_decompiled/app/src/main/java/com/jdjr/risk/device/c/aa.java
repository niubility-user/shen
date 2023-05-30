package com.jdjr.risk.device.c;

import android.content.Context;
import android.text.format.Formatter;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes18.dex */
public class aa {
    public static long a() {
        try {
            return BaseInfo.getRomSize();
        } catch (Exception unused) {
            return 0L;
        }
    }

    public static String a(Context context) {
        try {
            return Formatter.formatFileSize(context, a());
        } catch (Throwable unused) {
            return "";
        }
    }

    public static long b() {
        try {
            return BaseInfo.getMemTotalSize();
        } catch (Exception unused) {
            return 0L;
        }
    }

    public static String b(Context context) {
        try {
            return Formatter.formatFileSize(context, BaseInfo.getExternalStorageSize());
        } catch (Throwable unused) {
            return "";
        }
    }
}
