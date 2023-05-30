package com.jingdong.sdk.platform.lib.openapi.mta;

import android.content.Context;
import java.util.HashMap;

/* loaded from: classes10.dex */
public interface IJDMtaUtils {
    void onClickWithPageId(Context context, String str, String str2, String str3, String str4);

    void onSavePackOrderPage(String str);

    void onSaveProductOrderPage(String str);

    void onSaveProductOrderPageWithSkuTag(String str, String str2);

    void sendCommonData(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6);

    void sendCommonData(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6, String str7);

    void sendCommonDataForPromotionListPage(Context context, String str, String str2, String str3, Object obj, String str4, Class cls, String str5, String str6);

    void sendCommonDataWithExt(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6, String str7, String str8, HashMap<String, String> hashMap);

    void sendPagePv(Context context, Object obj, String str, String str2, String str3);
}
