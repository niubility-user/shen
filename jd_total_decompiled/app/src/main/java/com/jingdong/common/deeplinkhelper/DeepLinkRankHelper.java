package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.navutils.NavCenter;
import com.jingdong.common.navutils.NavUri;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.deeplink.a;
import com.jingdong.sdk.deeplink.b;
import com.jingdong.sdk.oklog.OKLog;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class DeepLinkRankHelper {
    private static final String ABTEST_LIST = "rank_abtest_list";
    public static final String CATE2ID = "cate2Id";
    public static final String CATE_ID = "cateId";
    public static final String CATE_NAME = "rank_cate_name";
    public static final String CLASSIFICATION = "Classification";
    private static final String CONFIGID_RANKHOME = "10167";
    private static final String CONFIGID_RANKHOME_HOT_DATA = "10057";
    private static final String CONFIGID_RANKHOME_INSERT = "10186";
    public static final String FLOOR_NUMBER = "floor_number";
    public static final String HOME = "Home";
    public static final String HOST_RANK_HOME_ACTIVITY = "rankhomeactivity";
    public static final String HOST_RANK_LIST_ACTIVITY = "rankinglistactivity";
    public static final String HOST_RANK_RECOMMEND_INNER_PAGE_ACTIVITY = "rankrecommendinnerpageactivity";
    public static final String PHOTO = "Photo";
    public static final String PRODUCTDETAIL = "Productdetail";
    public static final String RANK_AB_TEST = "rank_ab_test";
    public static final String RANK_ENTRY = "rank_entry";
    public static final String RANK_ID = "rank_id";
    public static final String RANK_MAIN_FLOOR_TYPE_ACTIVITY = "rank4001";
    public static final String RANK_MAIN_FLOOR_TYPE_ADD_CART = "rank4005";
    public static final String RANK_MAIN_FLOOR_TYPE_ADD_CART_TOP = "rank4006";
    public static final String RANK_MAIN_FLOOR_TYPE_DES = "rank3003";
    public static final String RANK_MAIN_FLOOR_TYPE_DISCOUNT = "rank3002";
    public static final String RANK_MAIN_FLOOR_TYPE_GUIDE = "rank3007";
    public static final String RANK_MAIN_FLOOR_TYPE_HOT = "rank3001";
    public static final String RANK_MAIN_FLOOR_TYPE_HOT_SEARCH = "rank3005";
    public static final String RANK_MAIN_FLOOR_TYPE_LAST_DAY = "rank3004";
    public static final String RANK_MAIN_FLOOR_TYPE_RECOMMEND = "rank3015";
    public static final String RANK_MAIN_FLOOR_TYPE_SHOP = "rank3006";
    public static final String RANK_MAIN_ID_BOOK_HOT = "rank3008";
    public static final String RANK_MAIN_ID_BOOK_NEW = "rank3009";
    public static final String RANK_MAIN_ID_EDUCATION_HOT = "rank3012";
    public static final String RANK_MAIN_ID_GAME_HOT = "rank3013";
    public static final String RANK_MAIN_ID_MUSIC_HOT = "rank3010";
    public static final String RANK_MAIN_ID_VIDEO_HOT = "rank3011";
    public static final String RANK_SHOP_GUIDE_ID = "rank_shop_guide_id";
    public static final String RANK_SHOP_GUIDE_TITLE = "rank_shop_guide_title";
    public static final String RECOMMEND_ID = "recommend_id";
    public static final String SEPARATEPAGE = "Separatepage";
    public static final String SKU_ID = "skuId";
    private static final String TAG = "DeepLinkRankHelper";
    private static final String UI_ABTEST_JSON_OLD = "A";
    private static Bundle bundleHome;

    private static boolean checkDeepLinkHost(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        a d = b.a().d(new DeepLinkUri.Builder().scheme("jingdong").host(str).toString());
        return (d == null || TextUtils.isEmpty(d.b())) ? false : true;
    }

    public static boolean initJsonUiStyle(JDJSONArray jDJSONArray, String str) {
        OKLog.d(TAG, "abTestJson:" + jDJSONArray + ",configId:" + str);
        boolean z = true;
        if (jDJSONArray != null) {
            int i2 = 0;
            while (true) {
                if (i2 >= jDJSONArray.size()) {
                    break;
                }
                if (jDJSONArray.get(i2) != null) {
                    String[] split = jDJSONArray.get(i2).toString().split("-");
                    if (split.length >= 3 && split[1].equals(str) && split[2].equals("A")) {
                        z = false;
                        break;
                    }
                }
                i2++;
            }
        }
        OKLog.d(TAG, "styleNewJson:" + z);
        return z;
    }

    private static void sendHttpRequestAbTest(IMyActivity iMyActivity, String str, JSONObject jSONObject, boolean z, HttpGroup.OnCommonListener onCommonListener) {
        String str2 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("sendHttpRequestAbTest()...functionId = ");
        sb.append(str);
        sb.append(", params = ");
        sb.append(jSONObject == null ? DYConstants.DY_NULL_STR : jSONObject.toString());
        OKLog.d(str2, sb.toString());
        if (iMyActivity == null) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(Configuration.getNgwHost());
        httpSetting.setFunctionId(str);
        httpSetting.setJsonParams(jSONObject);
        if (LoginUserBase.hasLogin()) {
            httpSetting.setUseCookies(true);
        } else {
            httpSetting.setUseCookies(false);
        }
        httpSetting.setNotifyUser(false);
        httpSetting.setIncompatibleWithOkHttp(true);
        httpSetting.setConnectTimeout(300);
        httpSetting.setReadTimeout(300);
        httpSetting.setAttempts(1);
        httpSetting.setEffect(z ? 1 : 0);
        httpSetting.setUseCookies(true);
        httpSetting.setListener(onCommonListener);
        iMyActivity.getHttpGroupaAsynPool().add(httpSetting);
    }

    public static void startRankHotListActivity(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        OKLog.d(TAG, "RankingListActivity\u5165\u53e3\u53c2\u6570---------->\nskuId: " + str + "\ncateId: " + str2 + "\ncateList: " + str3 + "\ncateName: " + str4 + "\nfrom: " + str5 + "\nrankId: " + str6);
        Bundle bundle = new Bundle();
        bundle.putString(RANK_ENTRY, str5);
        bundle.putString("skuId", str);
        bundle.putString(CATE_ID, str2);
        bundle.putString(CATE_NAME, str4);
        bundle.putString(CATE2ID, str3);
        if (!RANK_MAIN_FLOOR_TYPE_ADD_CART.equals(str6) && !RANK_MAIN_FLOOR_TYPE_ADD_CART_TOP.equals(str6) && !RANK_MAIN_FLOOR_TYPE_HOT.equals(str6) && !RANK_MAIN_ID_BOOK_NEW.equals(str6) && !RANK_MAIN_ID_BOOK_HOT.equals(str6) && !RANK_MAIN_ID_MUSIC_HOT.equals(str6) && !RANK_MAIN_ID_VIDEO_HOT.equals(str6) && !RANK_MAIN_ID_EDUCATION_HOT.equals(str6) && !RANK_MAIN_ID_GAME_HOT.equals(str6) && !RANK_MAIN_FLOOR_TYPE_DISCOUNT.equals(str6) && !RANK_MAIN_FLOOR_TYPE_DES.equals(str6) && !RANK_MAIN_FLOOR_TYPE_SHOP.equals(str6) && !RANK_MAIN_FLOOR_TYPE_HOT_SEARCH.equals(str6) && !RANK_MAIN_FLOOR_TYPE_RECOMMEND.equals(str6) && !RANK_MAIN_FLOOR_TYPE_LAST_DAY.equals(str6)) {
            bundle.putString(RANK_ID, RANK_MAIN_FLOOR_TYPE_HOT);
        } else {
            bundle.putString(RANK_ID, str6);
        }
        NavCenter.from(context).putBundle(bundle).navTo(new NavUri().scheme("https").host("coupon.m.jd.com").path("/ranklist/app_two/hotSaleRankList.action").build());
    }

    public static void startRankRecommendInnerPageActivity(Context context, String str, String str2) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(15))) {
            Bundle bundle = new Bundle();
            bundle.putString(RANK_ID, RANK_MAIN_FLOOR_TYPE_RECOMMEND);
            bundle.putString(RECOMMEND_ID, str);
            bundle.putString(RANK_ENTRY, str2);
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_RANK_RECOMMEND_INNER_PAGE_ACTIVITY).toString(), bundle);
            return;
        }
        OKLog.d(TAG, "\u6392\u884c\u699c\u5f00\u5173\u5173\u95ed\u4e86");
    }

    public static void startRankTopicActivity(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(15))) {
            Bundle bundle = new Bundle();
            bundle.putString(RANK_ENTRY, str4);
            bundle.putString(RANK_ID, RANK_MAIN_FLOOR_TYPE_GUIDE);
            bundle.putString(RANK_SHOP_GUIDE_TITLE, str);
            bundle.putString(RANK_SHOP_GUIDE_ID, str2);
            bundle.putString(RANK_AB_TEST, str5);
            bundle.putString(FLOOR_NUMBER, str6);
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_RANK_LIST_ACTIVITY).toString(), bundle);
            return;
        }
        OKLog.d(TAG, "\u6392\u884c\u699c\u5f00\u5173\u5173\u95ed\u4e86");
        Bundle bundle2 = new Bundle();
        bundle2.putString("url", "https://coupon.m.jd.com/ranklist/hotSaleRankList.action");
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_WEBACTIVITY, bundle2);
    }

    public static void startToRankCateIntermediate(Context context, Bundle bundle) {
        if (checkDeepLinkHost(DeepLinkCommonHelper.HOST_RANK_CATE_INTER)) {
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_RANK_CATE_INTER, bundle);
        }
    }

    public static void startToRankHome(Context context, String str) {
        startToRankHome(context, null, str);
    }

    public static void startToRankHome(final Context context, Bundle bundle, String str) {
        OKLog.d(TAG, "RankHomeActivity\u5165\u53e3\u53c2\u6570from: " + str);
        bundleHome = bundle;
        if (bundle == null) {
            bundleHome = new Bundle();
        }
        bundleHome.putString(RANK_ENTRY, str);
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(CONFIGID_RANKHOME);
        jSONArray.put(CONFIGID_RANKHOME_INSERT);
        jSONArray.put(CONFIGID_RANKHOME_HOT_DATA);
        try {
            jSONObject.put(CartConstant.KEY_CART_CONFIG_CONFIGIDS, jSONArray);
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
        sendHttpRequestAbTest((IMyActivity) context, "rankAbtest", jSONObject, false, new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkRankHelper.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                try {
                    JSONObjectProxy jSONObject2 = httpResponse.getJSONObject();
                    if ("0".equals(jSONObject2.optString("code"))) {
                        String optString = jSONObject2.optString("result");
                        if (DeepLinkRankHelper.initJsonUiStyle(JDJSON.parseArray(optString), DeepLinkRankHelper.CONFIGID_RANKHOME)) {
                            DeepLinkRankHelper.bundleHome.putString(DeepLinkRankHelper.ABTEST_LIST, optString);
                            NavCenter.from(context).putBundle(DeepLinkRankHelper.bundleHome).navTo(new NavUri().scheme("https").host("coupon.m.jd.com").path("/ranklist/app_three/hotSaleRankList.action").build());
                        } else {
                            NavCenter.from(context).putBundle(DeepLinkRankHelper.bundleHome).navTo(new NavUri().scheme("https").host("coupon.m.jd.com").path("/ranklist/app_one/hotSaleRankList.action").build());
                        }
                    }
                } catch (Exception e3) {
                    OKLog.e(DeepLinkRankHelper.TAG, e3);
                    NavCenter.from(context).putBundle(DeepLinkRankHelper.bundleHome).navTo(new NavUri().scheme("https").host("coupon.m.jd.com").path("/ranklist/app_three/hotSaleRankList.action").build());
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                OKLog.d(DeepLinkRankHelper.TAG, "rankAbtest\u8bf7\u6c42\u51fa\u9519");
                NavCenter.from(context).putBundle(DeepLinkRankHelper.bundleHome).navTo(new NavUri().scheme("https").host("coupon.m.jd.com").path("/ranklist/app_three/hotSaleRankList.action").build());
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        });
    }

    public static void startRankTopicActivity(Context context, Bundle bundle) {
        startRankTopicActivity(context, "", bundle.getString("topicId"), "", bundle.getString("fromName"), "", "");
    }
}
