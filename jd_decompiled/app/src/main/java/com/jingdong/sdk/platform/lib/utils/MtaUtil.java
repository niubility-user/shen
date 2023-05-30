package com.jingdong.sdk.platform.lib.utils;

import android.content.Context;
import com.jingdong.sdk.platform.lib.openapi.OpenApiHelper;
import java.util.HashMap;

/* loaded from: classes10.dex */
public class MtaUtil {
    private static final String TAG = "MtaUtil";

    public static void onSavePackOrderPage(String str) {
        OpenApiHelper.getIJDMtaUtils().onSavePackOrderPage(str);
    }

    public static void onSaveProductOrderPage(String str) {
        OpenApiHelper.getIJDMtaUtils().onSaveProductOrderPage(str);
    }

    public static void onSaveProductOrderPageWithSkuTag(String str, String str2) {
        OpenApiHelper.getIJDMtaUtils().onSaveProductOrderPageWithSkuTag(str, str2);
    }

    public static void sendCommonData(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6) {
        OpenApiHelper.getIJDMtaUtils().sendCommonData(context, str, str2, str3, obj, str4, str5, str6);
    }

    void onClickWithPageId(Context context, String str, String str2, String str3, String str4) {
        OpenApiHelper.getIJDMtaUtils().onClickWithPageId(context, str, str2, str3, str4);
    }

    void sendCommonDataForPromotionListPage(Context context, String str, String str2, String str3, Object obj, String str4, Class cls, String str5, String str6) {
        OpenApiHelper.getIJDMtaUtils().sendCommonDataForPromotionListPage(context, str, str2, str3, obj, str4, cls, str5, str6);
    }

    void sendCommonDataWithExt(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6, String str7, String str8, HashMap<String, String> hashMap) {
        OpenApiHelper.getIJDMtaUtils().sendCommonDataWithExt(context, str, str2, str3, obj, str4, str5, str6, str7, str8, hashMap);
    }

    void sendPagePv(Context context, Object obj, String str, String str2, String str3) {
        OpenApiHelper.getIJDMtaUtils().sendPagePv(context, obj, str, str2, str3);
    }

    public static void sendCommonData(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6, String str7) {
        OpenApiHelper.getIJDMtaUtils().sendCommonData(context, str, str2, str3, obj, str4, str5, str6, str7);
    }
}
