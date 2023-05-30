package com.jd.libs.hybrid.base.util;

import android.content.Context;
import androidx.annotation.Keep;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import java.util.HashMap;

/* loaded from: classes16.dex */
public class MtaUtils {
    private static Boolean a;
    private static CustomMtaSender b;

    @Keep
    /* loaded from: classes16.dex */
    public interface CustomMtaSender {
        void sendExposureDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, HashMap<String, String> hashMap);
    }

    private MtaUtils() {
    }

    private static void a() {
        try {
            new JDMtaUtils();
            a = Boolean.TRUE;
        } catch (Throwable unused) {
            if (Log.isDebug()) {
                Log.w("MtaUtils", "Error in importing module 'com.jingdong.jdsdk.mta.JDMtaUtils', cannot use JDMtaUtils to send mta data. You can either set your own by MtaUtils.setMtaSender() method or add dependency for 'JDMtaUtils'");
            }
            a = Boolean.FALSE;
        }
    }

    private static void b(Context context, String str, String str2, String str3, String str4, String str5, String str6, HashMap<String, String> hashMap) {
        if (a == null) {
            a();
        }
        if (a.booleanValue()) {
            try {
                JDMtaUtils.sendExposureDataWithExt(context, str, str2, str3, str4, str5, str6, hashMap);
            } catch (Throwable th) {
                Log.e("MtaUtils", th);
            }
        }
    }

    public static void sendExposureData(Context context, String str, String str2, String str3, String str4, String str5, String str6, HashMap<String, String> hashMap) {
        Log.d("MtaUtils", String.format("exposure data: event_id = %s, json_param = %s", str, str6));
        CustomMtaSender customMtaSender = b;
        if (customMtaSender != null) {
            customMtaSender.sendExposureDataWithExt(context, str, str2, str3, str4, str5, str6, hashMap);
        } else {
            b(context, str, str2, str3, str4, str5, str6, hashMap);
        }
    }

    public static void setMtaSender(CustomMtaSender customMtaSender) {
        b = customMtaSender;
    }
}
