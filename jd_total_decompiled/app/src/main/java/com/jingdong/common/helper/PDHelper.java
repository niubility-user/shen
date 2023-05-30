package com.jingdong.common.helper;

import android.text.TextUtils;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.PDConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class PDHelper implements PDConstant {
    private static final String TAG = "PDHelper";
    static long timeClick;

    public static boolean canClick() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - timeClick > 1000) {
            timeClick = currentTimeMillis;
            return true;
        }
        return false;
    }

    public static String getPDClassName() {
        return DeeplinkProductDetailHelper.isUsePlugin() ? "com.jd.lib.productdetail.ProductDetailActivity" : "com.jingdong.app.mall.productdetail.ProductDetailActivity";
    }

    public static String getPageId(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return (str.equals("com.jingdong.app.mall.productdetail.ProductDetailActivity") || str.equals("com.jd.lib.productdetail.ProductDetailActivity")) ? RecommendMtaUtils.Productdetail_MainPage : (str.equals("com.jingdong.app.mall.productdetail.ProductDetailInfoActivity") || str.equals("com.jingdong.app.mall.productdetail.ProductDetailInfoPage") || str.equals("com.jd.lib.productdetail.ProductDetailInfoActivity") || str.equals("com.jd.lib.productdetail.ProductDetailInfoPage")) ? "Productdetail_ProductInfo" : (str.equals("com.jingdong.app.mall.productdetail.ProductPacksListActivity") || str.equals("com.jd.lib.productdetail.ProductPacksListActivity")) ? "Productdetail_OnSale" : (str.equals("com.jingdong.app.mall.productdetail.PdGiftActivity") || str.equals("com.jd.lib.productdetail.PdGiftActivity")) ? "Productdetail_GiftPage" : (str.equals("com.jingdong.app.mall.productdetail.PdSecretGiftShareActivity") || str.equals("com.jd.lib.productdetail.PdSecretGiftShareActivity")) ? "Productdetail_GiftVideoPage" : TextUtils.equals(str, "com.jd.lib.productdetailfeeds.PdFeedsActivity") ? RecommendMtaUtils.ProductdetailFeeds_MainPage : str;
    }

    public static void onClick(String str, String str2, String str3, String str4) {
        onClick(str, str2, str3, str4, null, null);
    }

    public static void onExposure(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        try {
            if (Log.D) {
                Log.d(TAG, "eventId=" + str3 + " eventParam=" + str4 + " pageParam=" + str2 + " skuTag=" + str7);
            }
            JDMtaUtils.sendExposureData(JdSdk.getInstance().getApplication().getApplicationContext(), str, getPageId(str), str2, str3, str4, str5, str6, str7);
        } catch (Throwable th) {
            ExceptionReporter.reportExceptionToBugly(th);
        }
    }

    public static void onClick(String str, String str2, String str3, String str4, String str5, String str6) {
        onClick(str, str2, str3, str4, str5, str6, null, null);
    }

    public static void onClick(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        onClick(str, str2, str3, str4, str5, str6, str7, str8, null);
    }

    public static void onClick(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, HashMap<String, String> hashMap) {
        try {
            if (OKLog.D) {
                OKLog.d(TAG, "eventId=" + str + " eventParam=" + str2 + " pageParam=" + str4 + " skuTag=" + str5);
            }
            JDMtaUtils.sendCommonData4ProductDetail(JdSdk.getInstance().getApplication().getApplicationContext(), str, str2, "onClick", getPageId(str3), str3, str4, str5, str6, str7, str8, hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static void onExposure(String str, String str2, String str3, String str4, String str5, String str6) {
        onExposure(str, str2, str3, str4, str5, null, str6);
    }
}
