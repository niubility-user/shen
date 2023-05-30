package com.jingdong.common.paidTraffic;

import android.app.Activity;
import android.net.Uri;
import android.text.TextUtils;
import com.jd.lib.babel.task.viewkit.VKEventUtil;
import com.jingdong.app.mall.miaosha.MiaoShaPublicConstants;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.utils.CommonBridge;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.oklog.OKLog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class PaidTrafficHelper {
    public static final int TARGET_PAGE_3C = 3;
    public static final int TARGET_PAGE_ACTIVITY = 8;
    public static final int TARGET_PAGE_FLASHBUY = 2;
    public static final int TARGET_PAGE_HOME = 99;
    public static final int TARGET_PAGE_MINIPDETAIL = 0;
    public static final int TARGET_PAGE_NEW_SKU = 7;
    public static final int TARGET_PAGE_NEW_STORE = 6;
    public static final int TARGET_PAGE_RANK = 4;
    public static final int TARGET_PAGE_SECKILL = 1;
    public static final int TARGET_PAGE_SUPERMARKET = 5;
    public static String adBackUrl4Activity;
    public static String category_three;
    public static String category_two;
    public static String skuid;
    public static int targetPage;

    public static void toFlashBuy(Activity activity, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("category", "jump");
            jSONObject.put("des", JumpUtil.VALUE_DES_BRAND_SECKILL);
            jSONObject.put("channel", "productDetail");
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("innerAnchor", str);
            jSONArray.put(jSONObject2);
            jSONObject.put(MiaoShaPublicConstants.MIAO_SHA_INNER_LINK, jSONArray);
            String str2 = "openApp.jdMobile://virtual?params=" + jSONObject.toString();
            Uri parse = Uri.parse(str2);
            if (OKLog.D) {
                OKLog.d("TEST", "TARGET_PAGE_FLASHBUY openUri : " + str2);
            }
            new OpenAppJumpBuilder.Builder(parse).build().jump(activity);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void toH5(Activity activity, String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("category", "jump");
            jSONObject.put("des", "m");
            jSONObject.put("productId", str);
            jSONObject.put("url", str2);
            String str3 = "openApp.jdMobile://virtual?params=" + jSONObject.toString();
            Uri parse = Uri.parse(str3);
            if (OKLog.D) {
                OKLog.d("TEST", "TARGET_PAGE_FLASHBUY openUri : " + str3);
            }
            new OpenAppJumpBuilder.Builder(parse).build().jump(activity);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void toH5ByDeepLink(BaseActivity baseActivity, String str) {
        CommonBridge.goToMWithUrl(baseActivity, str);
    }

    public static void toNewSku(Activity activity, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("category", "jump");
            jSONObject.put("des", JumpUtil.VALUE_NEW_ARRIVALS);
            jSONObject.put("newArrivalsType", "1");
            jSONObject.put("channel", "123");
            jSONObject.put("advSku", str);
            String str2 = "openApp.jdMobile://virtual?params=" + jSONObject.toString();
            Uri parse = Uri.parse(str2);
            if (OKLog.D) {
                OKLog.d("TEST", "TARGET_PAGE_FLASHBUY openUri : " + str2);
            }
            new OpenAppJumpBuilder.Builder(parse).build().jump(activity);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void toRank(Activity activity, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("category", "jump");
            jSONObject.put("des", JumpUtil.VALUE_DES_RANKINGLISTHOME);
            jSONObject.put("fromName", "productdetail_pay");
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("innerAnchor", str);
            String str2 = TextUtils.isEmpty(category_two) ? "" : category_two;
            if (!TextUtils.isEmpty(category_three)) {
                if (TextUtils.isEmpty(str2)) {
                    str2 = category_three;
                } else {
                    str2 = str2 + CartConstant.KEY_YB_INFO_LINK + category_three;
                }
            }
            jSONObject2.put("innerExtId", str2);
            jSONArray.put(jSONObject2);
            jSONObject.put(MiaoShaPublicConstants.MIAO_SHA_INNER_LINK, jSONArray);
            String str3 = "openApp.jdMobile://virtual?params=" + jSONObject.toString();
            Uri parse = Uri.parse(str3);
            if (OKLog.D) {
                OKLog.d("TEST", "TARGET_PAGE_FLASHBUY openUri : " + str3);
            }
            new OpenAppJumpBuilder.Builder(parse).build().jump(activity);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void toSuperMarket(Activity activity, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("category", "jump");
            jSONObject.put("des", JumpUtil.VALUE_DES_JDREACT_COMMON);
            jSONObject.put("ishidden", true);
            jSONObject.put("appname", "JDReactSuperMarketPro");
            jSONObject.put(JDReactConstant.IntentConstant.MODULE_NAME, "JDReactSuperMarketPro");
            jSONObject.put(VKEventUtil.JUMP_NEEDLOGIN, "0");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("transparentenable", true);
            jSONObject2.put(VKEventUtil.JUMP_NEEDLOGIN, "0");
            jSONObject2.put("sourceId", 37);
            jSONObject2.put("productId", str);
            jSONObject.put("param", jSONObject2);
            String str2 = "openApp.jdMobile://virtual?params=" + jSONObject.toString();
            Uri parse = Uri.parse(str2);
            if (OKLog.D) {
                OKLog.d("TEST", "TARGET_PAGE_FLASHBUY openUri : " + str2);
            }
            new OpenAppJumpBuilder.Builder(parse).build().jump(activity);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void unionBackMta(Activity activity, String str) {
        if (activity == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("backpvid", str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        if (OKLog.D) {
            OKLog.d("TEST", "unionBackMta backpvid : " + str);
        }
        JDMtaUtils.sendClickDataWithExt(activity, "KeplerBack_AnotherBackButton", "", "onClick", "", activity.getClass().getSimpleName(), "", "", jSONObject.toString(), null);
    }
}
