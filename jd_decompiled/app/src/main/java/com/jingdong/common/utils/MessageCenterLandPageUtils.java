package com.jingdong.common.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.miaosha.MiaoShaPublicConstants;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkBabelHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkCartHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkCommuneHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkCouponCenterHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkEvaluateCenterHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkJShopHomeHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkMiaoShaHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkMyCouponHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkOrderCenterHelper;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.deeplinkhelper.imhelper.DDParameterBuilder;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.messagecenter.JDDDMessageRouterUtil;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.auraSetting.AuraConstants;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.jdtoast.ToastUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class MessageCenterLandPageUtils {
    private static boolean isOpenMainFrameActivity;

    public static void commodityLandingPage(BaseActivity baseActivity, String str, String str2, String str3) {
        if (baseActivity != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            Bundle bundle = new Bundle();
            bundle.putString("id", str);
            bundle.putString("msgId", str2);
            if (!TextUtils.isEmpty(str3) && "0".equals(str3)) {
                bundle.putString("fromNotice", str3);
                startProductDetailActivityWithFlag(baseActivity, bundle, 335544320);
                return;
            }
            startProductDetailActivity(baseActivity, bundle, null);
            return;
        }
        ToastUtils.showToastInCenter((Context) baseActivity, (byte) 1, "\u9875\u9762\u5df2\u4e22\u5931:(", 0);
    }

    public static void couponsLandingPage(BaseActivity baseActivity, String str, String str2, String str3) {
        if (baseActivity != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            if ("1".equals(str2)) {
                Bundle bundle = new Bundle();
                bundle.putString("msgId", str);
                if (TextUtils.isEmpty(str3)) {
                    str3 = "";
                }
                bundle.putString(NotificationMessageSummary.COUPONS_ID, str3);
                bundle.putString(AuraConstants.MESSAGE_COUPON_TYPE_FROM_NOTICE, AuraConstants.MESSAGE_COUPON_TYPE_NEW);
                JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_COUPON, baseActivity, bundle);
                return;
            }
            Bundle bundle2 = new Bundle();
            bundle2.putString("msgId", str);
            if (TextUtils.isEmpty(str3)) {
                str3 = "";
            }
            bundle2.putString(NotificationMessageSummary.COUPONS_ID, str3);
            bundle2.putString(AuraConstants.MESSAGE_COUPON_TYPE_FROM_NOTICE, AuraConstants.MESSAGE_COUPON_TYPE_WILL_EXPIRE);
            JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_COUPON, baseActivity, bundle2);
            return;
        }
        ToastUtils.showToastInCenter((Context) baseActivity, (byte) 1, "\u9875\u9762\u5df2\u4e22\u5931:(", 0);
    }

    public static void evaluationLandingPage(BaseActivity baseActivity, String str, String str2, String str3) {
        if (baseActivity != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            Intent intent = new Intent();
            intent.putExtra("orderId", str);
            intent.putExtra("msgId", str2);
            intent.putExtra("com.360buy:navigationDisplayFlag", -1);
            if (!TextUtils.isEmpty(str3) && "0".equals(str3)) {
                intent.putExtra("fromNotice", str3);
                DeepLinkEvaluateCenterHelper.startEvaluateUnite(baseActivity, intent.getExtras(), 335544320, "");
                return;
            }
            DeepLinkEvaluateCenterHelper.startEvaluateUnite(baseActivity, intent.getExtras());
            return;
        }
        ToastUtils.showToastInCenter((Context) baseActivity, (byte) 1, "\u9875\u9762\u5df2\u4e22\u5931:(", 0);
    }

    public static void firstBoxLandPage(BaseActivity baseActivity) {
        if (baseActivity == null) {
            return;
        }
        DeepLinkCommonHelper.startActivityDirect(baseActivity, DeepLinkCommonHelper.HOST_MESSAGE_CENTER_MAIN_ACTIVITY_NEW, null);
    }

    public static void forwardWebActivity(BaseActivity baseActivity, String str, boolean z, boolean z2, String str2, String str3) {
        if (baseActivity != null && !TextUtils.isEmpty(str)) {
            jumpToOpenApp(baseActivity, url2openapp(str), z, z2, str2, str3);
        } else {
            ToastUtils.showToastInCenter((Context) baseActivity, (byte) 1, "\u9875\u9762\u5df2\u4e22\u5931:(", 0);
        }
    }

    public static boolean isDigital(String str) {
        try {
            Integer.valueOf(str).intValue();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isMockValidInHashRange(int i2, int i3) {
        if (i2 >= 0 && i3 >= 0 && (i2 != 0 || i3 != 0)) {
            try {
                String pin = UserUtil.getWJLoginHelper().getPin();
                if (!TextUtils.isEmpty(pin)) {
                    int hash = GrayReleasedHashUtils.hash(pin, 100);
                    if (hash >= i2 && hash <= i3) {
                        return true;
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public static boolean isOpenMainFrameActivity() {
        return isOpenMainFrameActivity;
    }

    public static void jumpAlarmLocationJDMain(BaseActivity baseActivity) {
        if (baseActivity == null) {
            return;
        }
        JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_APPHOME, baseActivity, null);
    }

    public static void jumpAlarmLocationMiaoSha(BaseActivity baseActivity) {
        if (baseActivity == null) {
            return;
        }
        DeepLinkMiaoShaHelper.startMiaoShaList(baseActivity);
    }

    public static void jumpCouponCenter(BaseActivity baseActivity, String str) {
        if (baseActivity != null && !TextUtils.isEmpty(str) && isDigital(str)) {
            DeepLinkCouponCenterHelper.startCouponCenterByTab(baseActivity, "", Integer.parseInt(str));
        } else {
            ToastUtils.showToastInCenter((Context) baseActivity, (byte) 1, "\u9875\u9762\u5df2\u4e22\u5931:(", 0);
        }
    }

    public static void jumpCouponTrade(BaseActivity baseActivity) {
        if (baseActivity == null) {
            return;
        }
        DeepLinkCouponCenterHelper.startMarketHistory(baseActivity);
    }

    public static void jumpDongDong(BaseActivity baseActivity, String str) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(baseActivity, "mix.ActivityShadow"));
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!TextUtils.isEmpty(jSONObject.optString("mix"))) {
                JDDDMessageRouterUtil.openMixChat(baseActivity, str);
            } else if (!TextUtils.isEmpty(jSONObject.optString("venderId"))) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("from", DDParameterBuilder.ACTION_BROADCAST_ENTRY_ASK);
                jSONObject2.put("body", jSONObject);
                jSONObject2.put("pin", LoginUserBase.getUserPin());
                intent.putExtra("action", jSONObject2.toString());
                baseActivity.startActivity(intent);
            } else {
                firstBoxLandPage(baseActivity);
            }
        } catch (Exception unused) {
            firstBoxLandPage(baseActivity);
        }
    }

    public static void jumpScanLogin(Activity activity, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("key", str);
        DeepLinkLoginHelper.startScanLoginActivity(activity, bundle, 67108864);
    }

    public static void jumpToBabelActivity(BaseActivity baseActivity, String str, String str2) {
        int i2 = 0;
        if (baseActivity != null && !TextUtils.isEmpty(str)) {
            Bundle bundle = new Bundle();
            bundle.putString("activityId", str);
            if (!TextUtils.isEmpty(str2) && "0".equals(str2)) {
                bundle.putString("fromNotice", str2);
                i2 = 335544320;
            }
            DeepLinkBabelHelper.startBabelActivity(baseActivity, bundle, i2);
            return;
        }
        ToastUtils.showToastInCenter((Context) baseActivity, (byte) 1, "\u9875\u9762\u5df2\u4e22\u5931:(", 0);
    }

    public static void jumpToCalendarPage(BaseActivity baseActivity) {
        if (baseActivity == null) {
            return;
        }
        JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_JDREMINDER, baseActivity, null);
    }

    public static void jumpToCouponMainActivity(BaseActivity baseActivity) {
        if (baseActivity == null) {
            return;
        }
        DeepLinkMyCouponHelper.startFetchListActivity(baseActivity);
    }

    public static void jumpToFaxianAuthorPage(BaseActivity baseActivity, String str) {
        if (baseActivity != null && !TextUtils.isEmpty(str)) {
            Bundle bundle = new Bundle();
            bundle.putString("authorId", str);
            JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_FAXIAN_AUTHOR, baseActivity, bundle);
            return;
        }
        ToastUtils.showToastInCenter((Context) baseActivity, (byte) 1, "\u9875\u9762\u5df2\u4e22\u5931:(", 0);
    }

    public static void jumpToOpenApp(BaseActivity baseActivity, String str, boolean z, boolean z2, String str2, String str3) {
        if (baseActivity != null && !TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("msgId", str2);
                jSONObject.put("businessType", str3);
                jSONObject.put("url", str);
                Uri parse = Uri.parse(str);
                JSONObject string2JsonObject = JumpUtil.string2JsonObject(parse.getQueryParameter("params"), parse);
                String optString = string2JsonObject.optString("des");
                Log.d("MessageDes", optString);
                if (z2) {
                    if (!optString.equalsIgnoreCase("m") && !optString.equalsIgnoreCase(JumpUtil.VAULE_DES_GET_COUPON)) {
                        if (z) {
                            new OpenAppJumpBuilder.Builder(parse).sourceValue("fromNotice").build().jump(baseActivity);
                        } else {
                            new OpenAppJumpBuilder.Builder(parse).build().jump(baseActivity);
                        }
                    }
                    string2JsonObject.put("des", JumpUtil.VALUE_DES_MESSAGE_M);
                    string2JsonObject.put("msg", jSONObject.toString());
                    String str4 = "openApp.jdMobile://virtual?params=" + string2JsonObject.toString();
                    Log.d("MessagePage", str4);
                    Uri parse2 = Uri.parse(str4);
                    if (z) {
                        new OpenAppJumpBuilder.Builder(parse2).sourceValue("fromNotice").build().jump(baseActivity);
                    } else {
                        new OpenAppJumpBuilder.Builder(parse2).build().jump(baseActivity);
                    }
                } else if (z) {
                    new OpenAppJumpBuilder.Builder(parse).sourceValue("fromNotice").build().jump(baseActivity);
                } else {
                    new OpenAppJumpBuilder.Builder(parse).build().jump(baseActivity);
                }
                return;
            } catch (Exception unused) {
                Uri parse3 = Uri.parse(str);
                if (z) {
                    new OpenAppJumpBuilder.Builder(parse3).sourceValue("fromNotice").build().jump(baseActivity);
                    return;
                } else {
                    new OpenAppJumpBuilder.Builder(parse3).build().jump(baseActivity);
                    return;
                }
            }
        }
        ToastUtils.showToastInCenter((Context) baseActivity, (byte) 1, "\u9875\u9762\u5df2\u4e22\u5931:(", 0);
    }

    public static void jumpToQuestionAndAnswerPage(BaseActivity baseActivity, String str, String str2) {
        if (baseActivity != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            Bundle bundle = new Bundle();
            bundle.putString(DeepLinkCommuneHelper.PRODUCT_ID, str);
            bundle.putString(DeepLinkCommuneHelper.QUESTION_ID, str2);
            DeepLinkCommuneHelper.startCommuneDetail(baseActivity, bundle);
            return;
        }
        ToastUtils.showToastInCenter((Context) baseActivity, (byte) 1, "\u9875\u9762\u5df2\u4e22\u5931:(", 0);
    }

    public static void jumpToSearchActivity(BaseActivity baseActivity, String str) {
        if (baseActivity == null) {
            return;
        }
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("from", "search");
            bundle.putString(JshopConst.JSHOP_SEARCH_KEYWORD, str);
        } else {
            bundle.putString("from", "search");
            bundle.putString(JshopConst.JSHOP_SEARCH_KEYWORD, "\u7279\u4ea7");
        }
        JumpUtil.execJumpByDes(JumpUtil.VAULE_DES_PRODUCT_LIST, baseActivity, bundle);
    }

    public static void logisticsTrackingLandingPage(BaseActivity baseActivity, String str, String str2, String str3) {
        if (baseActivity != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            DeepLinkOrderCenterHelper.startLogisticsForMessage(baseActivity, str, str2, str3, 335544320);
        } else {
            ToastUtils.showToastInCenter((Context) baseActivity, (byte) 1, "\u9875\u9762\u5df2\u4e22\u5931:(", 0);
        }
    }

    public static void messageCenterSecondPage(BaseActivity baseActivity, String str, String str2, String str3) {
    }

    public static void mobileChannelLandPage(BaseActivity baseActivity, String str) {
    }

    public static void orderDetailsLandingPage(BaseActivity baseActivity, String str, String str2, String str3) {
        if (baseActivity != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            DeepLinkOrderCenterHelper.startOrderDetailWithFlags(baseActivity, str, str2, str3, 335544320);
        } else {
            ToastUtils.showToastInCenter((Context) baseActivity, (byte) 1, "\u9875\u9762\u5df2\u4e22\u5931:(", 0);
        }
    }

    public static void orderListLandingPage(BaseActivity baseActivity, String str) {
        if (baseActivity == null) {
            return;
        }
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(str) && "0".equals(str)) {
            DeepLinkOrderCenterHelper.startOrderList(baseActivity, bundle, 335544320);
        } else {
            DeepLinkOrderCenterHelper.startOrderList(baseActivity);
        }
    }

    public static void setIsOpenMainFrameActivity(boolean z) {
        isOpenMainFrameActivity = z;
    }

    public static void setUpgradeLandPage(BaseActivity baseActivity, String str) {
        if (baseActivity != null && !TextUtils.isEmpty(str)) {
            URLParamMap uRLParamMap = new URLParamMap();
            uRLParamMap.put(RemoteMessageConst.TO, str);
            CommonBridge.forwardWebActivityJugeNF(baseActivity, RemoteMessageConst.TO, uRLParamMap, false);
            return;
        }
        ToastUtils.showToastInCenter((Context) baseActivity, (byte) 1, "\u9875\u9762\u5df2\u4e22\u5931:(", 0);
    }

    public static void shopHomePage(BaseActivity baseActivity, String str, String str2, String str3) {
        if (baseActivity != null && !TextUtils.isEmpty(str)) {
            Intent intent = new Intent();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("venderId", str2);
                jSONObject.put("shopId", str);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            if (!TextUtils.isEmpty(str3) && "0".equals(str3)) {
                intent.putExtra("fromNotice", str3);
                intent.addFlags(335544320);
            }
            intent.putExtra(JshopConst.INTENT_BRAND_JSON, jSONObject.toString());
            DeepLinkJShopHomeHelper.gotoJShopHome(baseActivity, intent.getExtras());
            return;
        }
        ToastUtils.showToastInCenter((Context) baseActivity, (byte) 1, "\u9875\u9762\u5df2\u4e22\u5931:(", 0);
    }

    public static void shoppingCartLandingPage(BaseActivity baseActivity, String str, String str2) {
        if (baseActivity == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("locationSkuId", str);
        bundle.putString("locationSkuPrice", str2);
        DeepLinkCartHelper.startCartMainUseFlag(baseActivity, bundle, 335544320);
    }

    public static void spikeLandingPage(BaseActivity baseActivity, String str, String str2, String str3) {
        if (baseActivity == null) {
            return;
        }
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && isDigital(str2)) {
            bundle.putLong("productId", Long.parseLong(str2));
            bundle.putInt(MiaoShaPublicConstants.KEY_GID, Integer.parseInt(str));
            DeepLinkMiaoShaHelper.startMiaoShaList(baseActivity, bundle);
        } else if (!TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            bundle.putInt(MiaoShaPublicConstants.KEY_GID, Integer.parseInt(str));
            DeepLinkMiaoShaHelper.startMiaoShaList(baseActivity, bundle);
        } else if (TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && isDigital(str2)) {
            bundle.putLong("productId", Long.parseLong(str2));
            DeepLinkMiaoShaHelper.startMiaoShaList(baseActivity, bundle);
        } else if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            DeepLinkMiaoShaHelper.startMiaoShaList(baseActivity, bundle);
        } else if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !isDigital(str2)) {
            bundle.putInt(MiaoShaPublicConstants.KEY_GID, Integer.parseInt(str));
            DeepLinkMiaoShaHelper.startMiaoShaList(baseActivity, bundle);
        } else if (TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !isDigital(str2)) {
            DeepLinkMiaoShaHelper.startMiaoShaList(baseActivity, bundle);
        } else {
            baseActivity.startActivityInFrame(null);
        }
    }

    private static void startProductDetailActivity(Context context, Bundle bundle, SourceEntity sourceEntity) {
        if (context == null || bundle == null) {
            return;
        }
        if (sourceEntity != null) {
            bundle.putSerializable("source", sourceEntity);
        }
        if (!TextUtils.isEmpty(bundle.getString("clickUrl"))) {
            bundle.putString("targetUrl", bundle.getString("clickUrl"));
        }
        DeeplinkProductDetailHelper.startProductDetail(context, bundle);
    }

    private static void startProductDetailActivityWithFlag(Context context, Bundle bundle, int i2) {
        if (context == null || bundle == null) {
            return;
        }
        DeeplinkProductDetailHelper.startProductDetailWithFlag(context, bundle, i2);
    }

    public static void urgentPush(BaseActivity baseActivity, String str) {
        baseActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    public static String url2openapp(String str) {
        StringBuilder sb = new StringBuilder("openApp.jdMobile://virtual?params=");
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("category", (Object) "jump");
        jDJSONObject.put("des", (Object) "m");
        jDJSONObject.put("url", (Object) str);
        sb.append(jDJSONObject.toJSONString());
        return sb.toString();
    }

    public static void virtualBusinessLandingPage(BaseActivity baseActivity, String str, String str2) {
        if (baseActivity != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            new JSONObjectProxy();
            int parseInt = Integer.parseInt(str2);
            Bundle bundle = new Bundle();
            bundle.putString("orderId", str);
            bundle.putInt("orderType", parseInt);
            JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_VIRTUAL_BUSINESS, baseActivity, bundle);
            return;
        }
        ToastUtils.showToastInCenter((Context) baseActivity, (byte) 1, "\u9875\u9762\u5df2\u4e22\u5931:(", 0);
    }
}
