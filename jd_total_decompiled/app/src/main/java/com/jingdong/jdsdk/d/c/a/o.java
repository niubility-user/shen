package com.jingdong.jdsdk.d.c.a;

import android.content.Context;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.platform.lib.openapi.mta.IJDMtaUtils;
import java.util.HashMap;

/* loaded from: classes14.dex */
public class o implements IJDMtaUtils {
    private static o a;

    private o() {
    }

    public static synchronized o a() {
        o oVar;
        synchronized (o.class) {
            if (a == null) {
                a = new o();
            }
            oVar = a;
        }
        return oVar;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.mta.IJDMtaUtils
    public void onClickWithPageId(Context context, String str, String str2, String str3, String str4) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.mta.IJDMtaUtils
    public void onSavePackOrderPage(String str) {
        JDMtaUtils.onSavePackOrderPage(str);
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.mta.IJDMtaUtils
    public void onSaveProductOrderPage(String str) {
        JDMtaUtils.onSaveProductOrderPage(str);
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.mta.IJDMtaUtils
    public void onSaveProductOrderPageWithSkuTag(String str, String str2) {
        JDMtaUtils.onSaveProductOrderPageWithSkuTag(str, str2);
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.mta.IJDMtaUtils
    public void sendCommonData(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6, String str7) {
        JDMtaUtils.sendCommonData(context, str, str2, str3, obj, str4, str5, str6, str7);
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.mta.IJDMtaUtils
    public void sendCommonDataForPromotionListPage(Context context, String str, String str2, String str3, Object obj, String str4, Class cls, String str5, String str6) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.mta.IJDMtaUtils
    public void sendCommonDataWithExt(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6, String str7, String str8, HashMap<String, String> hashMap) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.mta.IJDMtaUtils
    public void sendPagePv(Context context, Object obj, String str, String str2, String str3) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.mta.IJDMtaUtils
    public void sendCommonData(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6) {
        JDMtaUtils.sendCommonData(context, str, str2, str3, obj, str4, str5, str6);
    }
}
