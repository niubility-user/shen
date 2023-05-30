package com.jd.lib.productdetail.core.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jump.OpenAppConstant;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;

/* loaded from: classes15.dex */
public class OpenAppUtils {
    public static final String OPENAPP = "openapp.jdmobile://virtual?params={\"category\":\"jump\",\"des\":\"jdreactcommon\",\"modulename\":\"JDReactMatchesBuy\",\"appname\":\"JDReactMatchesBuy\",\"ishidden\":true,\"param\":{\"matchId\":\"%1$s\",\"skuId\":\"%2$s\",\"area\":\"%3$s\",\"source\":\"%4$s\"}}";
    public static final String SOURCE_FLOOR = "1";
    public static final String SOURCE_FLOOR_NEW = "2";
    public static final String SOURCE_FLOOR_TOPIMAGE = "3";
    public static final String SOURCE_FLOOR_TOPIMAGE_NEW = "4";

    public static void openAppForInner(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        new OpenAppJumpBuilder.Builder(Uri.parse(str)).build().jump(context);
    }

    public static void openMatchBuyFlutter(Context context, String str, String str2, String str3) {
        new OpenAppJumpBuilder.Builder().scheme("openapp.jdmobile").host(OpenAppConstant.HOST_1).category("jump").des("JDFlutterView").map("flutterRouterName", "JDFlutterMatchBuy").map("isShowNativeNavBar", "0").params("{\"area\":\"" + PDUtils.getPhoneBasicInfoObj() + "\",\"source\":\"" + str3 + "\",\"skuId\":\"" + str + "\",\"matchId\":\"" + str2 + "\"}").build().jump(context);
    }

    public static void openMatchBuyFlutterNew(Context context, String str, String str2, String str3) {
        new OpenAppJumpBuilder.Builder().scheme("openapp.jdmobile").host(OpenAppConstant.HOST_1).category("jump").des("JDFlutterView").map(JDReactConstant.IntentConstant.MODULE_NAME, "JDFlutterMatchBuy").map("flutterRouterName", "JDFlutterMatchBuy").map("isFromFlutterVCEnable", "1").map("isShowIOSNavBar", "0").map("isJDFlutter", "1").params("{\"source\":\"" + str3 + "\",\"skuId\":\"" + str + "\",\"matchId\":\"" + str2 + "\"}").build().jump(context);
    }

    public static void openMatchBuyRn(Context context, String str, String str2, String str3) {
        if (context == null) {
            return;
        }
        new OpenAppJumpBuilder.Builder(Uri.parse(String.format(OPENAPP, str, str2, PDUtils.getPhoneBasicInfoObj(), str3))).build().jump(context);
    }

    public static void openWithContext(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }
}
