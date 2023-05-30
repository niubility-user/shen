package com.jingdong.app.mall.bundle.jdrhsdk.api;

import android.content.Context;
import java.util.HashMap;

/* loaded from: classes2.dex */
public interface JDRiskHandleMtaHelper {
    void sendClickDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, HashMap<String, String> hashMap);

    void sendExposureDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, HashMap<String, String> hashMap);

    void sendPVExtend(Context context, Object obj, String str, String str2, String str3, String str4, String str5, String str6, HashMap<String, String> hashMap, HashMap<String, String> hashMap2);
}
