package com.jingdong.common.web.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.cps.JDUnionUtils;
import com.jingdong.common.deeplinkhelper.DeepLinkBabelHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkCommuneHelper;
import com.jingdong.common.jdreactFramework.utils.ReactActivityUtilsHelperBase;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.navutils.NavUtils;
import com.jingdong.common.openlinktime.OpenLinkTimeManager;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.common.web.MKeyNames;
import com.jingdong.common.web.entity.WebEntity;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.manto.utils.MantoActivityUtils;
import com.jingdong.sdk.log.Log;

/* loaded from: classes12.dex */
public class M2NativeHelper {
    private static final int DELAYTIME = 500;
    private static final String TAG = "M2NativeHelper";
    private static final String ULINK_PREFIX_URL = "linkst.m.jd.com/ul/ul.action";

    public static boolean check2Babel(Context context, String str, Bundle bundle, boolean z, boolean z2) {
        String str2 = str;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.contains("xrender=")) {
            Log.d(TAG, "\u4e0d\u80fd\u515c\u5e95\u901a\u5929\u5854\uff0c\u56e0\u4e3aurl\u5e26xrender\u53c2\u6570\uff0c\u5224\u65ad\u4e3a\u9884\u6e32\u67d3URL");
            return false;
        }
        boolean switchBooleanValue = SwitchQueryFetcher.getSwitchBooleanValue("wvFixAddTTTBabelTwice", false);
        if (!switchBooleanValue && bundle != null) {
            bundle.putBoolean(WebEntity.ADD_CUSTOM_PARAMS_CHECKED, true);
        }
        String babelActivityId = WebViewHelper.getBabelActivityId(str);
        if (TextUtils.isEmpty(babelActivityId)) {
            return false;
        }
        if (!switchBooleanValue) {
            str2 = WebUtils.addBabelCustomParams(str, bundle);
        } else if (z2) {
            if (bundle == null || !bundle.getBoolean(WebEntity.ADD_CUSTOM_PARAMS_CHECKED, false)) {
                str2 = WebUtils.addBabelCustomParams(str, bundle);
                if (bundle != null) {
                    bundle.putBoolean(WebEntity.ADD_CUSTOM_PARAMS_CHECKED, true);
                }
            }
        } else {
            str2 = WebUtils.addBabelCustomParams(str, null);
        }
        String str3 = str2;
        if (bundle != null) {
            bundle.remove(MBaseKeyNames.KEY_OFFLINE_ID);
            bundle.remove(MBaseKeyNames.KEY_PRELOAD_ID);
            bundle.remove(MBaseKeyNames.KEY_USE_HYBRID);
        }
        Bundle bundleFromUrl = WebViewHelper.getBundleFromUrl(str3);
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean(WebEntity.ADD_CUSTOM_PARAMS_CHECKED, true);
        if (bundleFromUrl != null) {
            bundle2.putAll(bundleFromUrl);
        }
        if (z) {
            if (context instanceof BaseActivity) {
                ((BaseActivity) context).isDisposeableUnableExitAnim = true;
            }
            bundle2.putBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, true);
        }
        if (!z2) {
            try {
                URLParamMap uRLParamMap = new URLParamMap();
                uRLParamMap.put(RemoteMessageConst.TO, str3);
                SerializableContainer serializableContainer = new SerializableContainer();
                serializableContainer.setMap(uRLParamMap);
                bundle2.putSerializable("urlParamMap", serializableContainer);
                if (bundle != null) {
                    String string = bundle.getString("des");
                    if (!TextUtils.isEmpty(string)) {
                        bundle2.putString("des", string);
                    }
                    if (z) {
                        if (!bundle2.containsKey("isFromOpenApp") && bundle.containsKey("isFromOpenApp")) {
                            bundle2.putString("isFromOpenApp", bundle.getString("isFromOpenApp"));
                        }
                        if (bundle.containsKey(OpenAppJumpController.KEY_OPEN_LINK)) {
                            bundle2.putBoolean(OpenAppJumpController.KEY_OPEN_LINK, bundle.getBoolean(OpenAppJumpController.KEY_OPEN_LINK));
                        }
                    }
                }
                bundle2.putString("url", str3);
            } catch (Exception e2) {
                Log.e(TAG, e2.getMessage(), e2);
            }
        } else if (bundle != null) {
            bundle2.putAll(bundle);
        }
        bundle2.putString("activityId", babelActivityId);
        bundle2.putBoolean(MKeyNames.SWITCH_QUERY_NATIVE, true);
        try {
            Object invoke = Class.forName("com.jingdong.common.babelrn.utils.M2BabelHelper").getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
            invoke.getClass().getMethod("getQueryNative", String.class, String.class).invoke(invoke, babelActivityId, str3);
        } catch (Throwable unused) {
        }
        DeepLinkBabelHelper.startBabelActivity(context, bundle2, 0);
        String str4 = TAG;
        WebUnifiedMtaUtil.sendExposureMta(context, str4, "", str3, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "2");
        Log.d(str4, "M2Native \u901a\u5929\u5854");
        return true;
    }

    public static boolean checkM2Native(Context context, String str, Bundle bundle, boolean z, boolean z2) {
        String replaceFirst;
        Log.d("PreCheckNative", "Doing pre check native before opening WebView.");
        if (context instanceof Activity) {
            setNativeChecked(bundle);
            if (!NavUtils.isPageDegradeToH5(context, str) && isNeedCheckToNative(str)) {
                Bundle bundleFromUrl = WebViewHelper.getBundleFromUrl(str);
                boolean z3 = z2 || z;
                String str2 = TAG;
                if (JDUnionUtils.isCpsUnionMatch(context, str, "", z3, bundle, bundleFromUrl, str2)) {
                    WebUnifiedMtaUtil.sendExposureMta(context, str2, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                    Log.d(str2, "M2Native cps\u8054\u76df");
                    if (z) {
                        setClosePageParams(context, null);
                        closePage(context);
                    }
                    return true;
                } else if (bundleFromUrl != null && bundleFromUrl.containsKey("jdreactkey")) {
                    Log.d(str2, "M2NativeHelper [rn logic match] url: " + str);
                    Bundle bundle2 = new Bundle();
                    if (z) {
                        setClosePageParams(context, bundle2);
                    }
                    ReactActivityUtilsHelperBase.startJDReactCommonPage(context, str, bundle2);
                    if (z) {
                        closePage(context);
                    }
                    return true;
                } else {
                    String str3 = "";
                    if (!TextUtils.isEmpty(str) && (str.startsWith("http://linkst.m.jd.com/ul/ul.action") || str.startsWith("https://linkst.m.jd.com/ul/ul.action"))) {
                        if (str.startsWith("https")) {
                            replaceFirst = str.replaceFirst("https://linkst.m.jd.com/ul/ul.action\\?", "");
                        } else {
                            replaceFirst = str.startsWith("http") ? str.replaceFirst("http://linkst.m.jd.com/ul/ul.action\\?", "") : null;
                        }
                        if (replaceFirst != null && replaceFirst.startsWith("?")) {
                            replaceFirst = replaceFirst.substring(1);
                        }
                        try {
                            Uri parse = !TextUtils.isEmpty(replaceFirst) ? Uri.parse(replaceFirst) : null;
                            if (parse != null && JumpUtil.isJdScheme(parse.getScheme())) {
                                Intent data = new Intent().setData(parse);
                                if (bundle != null && z2) {
                                    data.putExtras(bundle);
                                }
                                data.putExtra(MBaseKeyNames.KEY_REFERER, str);
                                OpenAppJumpController.dispatchJumpRequest(context, data);
                                if (z) {
                                    closePage(context);
                                }
                                return true;
                            }
                        } catch (Exception e2) {
                            ExceptionReporter.reportWebViewCommonError("M2NativeHelper_ulink_error", str, e2.getMessage(), ExceptionReporter.getStackStringFromException(e2));
                            Log.e(TAG, e2.getMessage(), e2);
                        }
                    }
                    if (!JumpUtil.VALUE_DES_MESSAGE_M.equals(bundle != null ? bundle.getString("des") : null) && check2Babel(context, str, bundle, z, z2)) {
                        OpenLinkTimeManager.getInstance().enterBabel(bundle.getInt(OpenAppJumpController.KEY_OPEN_LINK_PARAMS, 0));
                        if (z) {
                            closePage(context);
                        }
                        return true;
                    }
                    String skuId = WebViewHelper.getSkuId(str);
                    if (!TextUtils.isEmpty(skuId)) {
                        Bundle bundle3 = new Bundle();
                        if (bundleFromUrl != null) {
                            bundle3.putAll(bundleFromUrl);
                        }
                        bundle3.putString("skuId", skuId);
                        if (z) {
                            setClosePageParams(context, bundle3);
                        }
                        JumpUtil.execJumpByDes("productDetail", context, bundle3);
                        String str4 = TAG;
                        WebUnifiedMtaUtil.sendExposureMta(context, str4, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                        Log.d(str4, "M2Native \u5546\u8be6");
                        if (z) {
                            closePage(context);
                        }
                        return true;
                    }
                    String shopId = WebViewHelper.getShopId(str);
                    if (!TextUtils.isEmpty(shopId)) {
                        Bundle bundle4 = new Bundle();
                        if (bundleFromUrl != null) {
                            bundle4.putAll(bundleFromUrl);
                        }
                        bundle4.putString("shopId", shopId);
                        if (z) {
                            setClosePageParams(context, bundle4);
                        }
                        JumpUtil.execJumpByDes(JumpUtil.VAULE_DES_JSHOP, context, bundle4);
                        String str5 = TAG;
                        WebUnifiedMtaUtil.sendExposureMta(context, str5, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                        Log.d(str5, "M2Native \u5e97\u94fa");
                        if (z) {
                            closePage(context);
                        }
                        return true;
                    }
                    String faxianAuthorId = WebViewHelper.getFaxianAuthorId(str);
                    if (!TextUtils.isEmpty(faxianAuthorId)) {
                        Bundle bundle5 = new Bundle();
                        if (bundleFromUrl != null) {
                            bundle5.putAll(bundleFromUrl);
                        }
                        bundle5.putString("authorId", faxianAuthorId);
                        if (z) {
                            setClosePageParams(context, bundle5);
                        }
                        JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_FAXIAN_AUTHOR, context, bundle5);
                        String str6 = TAG;
                        WebUnifiedMtaUtil.sendExposureMta(context, str6, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                        Log.d(str6, "M2Native \u53d1\u73b0 \u4f5c\u8005\u9875");
                        if (z) {
                            closePage(context);
                        }
                        return true;
                    }
                    String livePlayerRoomInfo = WebViewHelper.getLivePlayerRoomInfo(str);
                    if (!TextUtils.isEmpty(livePlayerRoomInfo)) {
                        Bundle bundle6 = new Bundle();
                        if (bundleFromUrl != null) {
                            bundle6.putAll(bundleFromUrl);
                        }
                        bundle6.putString("id", livePlayerRoomInfo);
                        bundle6.putString("liveUrl", str);
                        if (z) {
                            setClosePageParams(context, bundle6);
                        }
                        JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_FIND_LIVE_PRE, context, bundle6);
                        String str7 = TAG;
                        WebUnifiedMtaUtil.sendExposureMta(context, str7, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                        Log.d(str7, "M2Native \u76f4\u64ad\u95f4");
                        if (z) {
                            closePage(context);
                        }
                        return true;
                    }
                    String[] commentDetailInfo = WebViewHelper.getCommentDetailInfo(str);
                    if (commentDetailInfo != null) {
                        Bundle bundle7 = new Bundle();
                        if (bundleFromUrl != null) {
                            bundle7.putAll(bundleFromUrl);
                        }
                        bundle7.putString("productId", commentDetailInfo[0]);
                        bundle7.putString(DeepLinkCommuneHelper.COMMENT_ID, commentDetailInfo[1]);
                        if (z) {
                            setClosePageParams(context, bundle7);
                        }
                        JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_COMMENT_DETAIL, context, bundle7);
                        String str8 = TAG;
                        WebUnifiedMtaUtil.sendExposureMta(context, str8, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                        Log.d(str8, "M2Native \u8bc4\u8bba\u8be6\u60c5");
                        if (z) {
                            closePage(context);
                        }
                        return true;
                    }
                    String videoBuyInfo = WebViewHelper.getVideoBuyInfo(str);
                    if (!TextUtils.isEmpty(videoBuyInfo)) {
                        Bundle bundle8 = new Bundle();
                        if (bundleFromUrl != null) {
                            bundle8.putAll(bundleFromUrl);
                        }
                        bundle8.putString("id", videoBuyInfo);
                        bundle8.putString("channel", "webview");
                        if (z) {
                            setClosePageParams(context, bundle8);
                        }
                        JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_VIDEO_BUY, context, bundle8);
                        String str9 = TAG;
                        WebUnifiedMtaUtil.sendExposureMta(context, str9, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                        Log.d(str9, "M2Native \u53d1\u73b0\u89c6\u9891\u8d2d");
                        if (z) {
                            closePage(context);
                        }
                        return true;
                    } else if (WebViewHelper.isMyJDMatch(str)) {
                        Bundle bundle9 = new Bundle();
                        if (bundleFromUrl != null) {
                            bundle9.putAll(bundleFromUrl);
                        }
                        if (z) {
                            setClosePageParams(context, bundle9);
                        }
                        JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_MYJD, context, bundle9);
                        String str10 = TAG;
                        WebUnifiedMtaUtil.sendExposureMta(context, str10, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                        Log.d(str10, "M2Native \u6211\u7684\u4eac\u4e1c");
                        if (z) {
                            closePage(context);
                        }
                        return true;
                    } else if (WebViewHelper.isMyCartMatch(str)) {
                        Bundle bundle10 = new Bundle();
                        if (bundleFromUrl != null) {
                            bundle10.putAll(bundleFromUrl);
                        }
                        if (z) {
                            setClosePageParams(context, bundle10);
                        }
                        JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_GO_CART, context, bundle10);
                        String str11 = TAG;
                        WebUnifiedMtaUtil.sendExposureMta(context, str11, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                        Log.d(str11, "M2Native \u8d2d\u7269\u8f66");
                        if (z) {
                            closePage(context);
                        }
                        return true;
                    } else {
                        String searchResultInfo = WebViewHelper.getSearchResultInfo(str);
                        if (!TextUtils.isEmpty(searchResultInfo)) {
                            Bundle bundle11 = new Bundle();
                            if (bundleFromUrl != null) {
                                bundle11.putAll(bundleFromUrl);
                                str3 = bundleFromUrl.getString("keyword");
                            }
                            bundle11.putString(JshopConst.JSHOP_SEARCH_KEYWORD, WebViewHelper.filterParam(str3, searchResultInfo));
                            bundle11.putString("from", "search");
                            if (z) {
                                setClosePageParams(context, bundle11);
                            }
                            JumpUtil.execJumpByDes(JumpUtil.VAULE_DES_PRODUCT_LIST, context, bundle11);
                            String str12 = TAG;
                            WebUnifiedMtaUtil.sendExposureMta(context, str12, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                            Log.d(str12, "M2Native \u641c\u7d22\u7ed3\u679c\u9875");
                            if (z) {
                                closePage(context);
                            }
                            return true;
                        } else if (WebViewHelper.isJdPayMatch((Activity) context, str)) {
                            String str13 = TAG;
                            WebUnifiedMtaUtil.sendExposureMta(context, str13, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                            Log.d(str13, "M2Native jdPay");
                            if (z) {
                                setClosePageParams(context, null);
                                closePage(context);
                            }
                            return true;
                        } else if (WebViewHelper.isJDCouponMatch(str)) {
                            Bundle bundle12 = new Bundle();
                            if (bundleFromUrl != null) {
                                bundle12.putAll(bundleFromUrl);
                            }
                            if (z) {
                                setClosePageParams(context, bundle12);
                            }
                            JumpUtil.execJumpByDes(JumpUtil.VAULE_DES_COUPON_CENTER, context, bundle12);
                            String str14 = TAG;
                            WebUnifiedMtaUtil.sendExposureMta(context, str14, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                            Log.d(str14, "M2Native \u9886\u5238\u4e2d\u5fc3");
                            if (z) {
                                closePage(context);
                            }
                            return true;
                        } else if (WebViewHelper.isEnjoyBuyMatch(str)) {
                            Bundle bundle13 = new Bundle();
                            if (bundleFromUrl != null) {
                                bundle13.putAll(bundleFromUrl);
                            }
                            if (z) {
                                setClosePageParams(context, bundle13);
                            }
                            JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_ENJOYBUY, context, bundle13);
                            WebUnifiedMtaUtil.sendExposureMta(context, TAG, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                            if (z) {
                                closePage(context);
                            }
                            return true;
                        } else if (WebViewHelper.isBuyVideoProtrait(str)) {
                            Bundle bundle14 = new Bundle();
                            if (bundleFromUrl != null) {
                                bundle14.putAll(bundleFromUrl);
                            }
                            if (z) {
                                setClosePageParams(context, bundle14);
                            }
                            JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_VIDEO_IMMERSION, context, bundle14);
                            WebUnifiedMtaUtil.sendExposureMta(context, TAG, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                            if (z) {
                                closePage(context);
                            }
                            return true;
                        } else if (WebViewHelper.isOpenHomeUrl(str)) {
                            Bundle bundle15 = new Bundle();
                            if (bundleFromUrl != null) {
                                bundle15.putAll(bundleFromUrl);
                            }
                            if (z) {
                                setClosePageParams(context, bundle15);
                            }
                            JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_APPHOME, context, bundle15);
                            WebUnifiedMtaUtil.sendExposureMta(context, TAG, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                            if (z) {
                                closePage(context);
                            }
                            return true;
                        } else {
                            String pinGouId = WebViewHelper.getPinGouId(str);
                            if (!TextUtils.isEmpty(pinGouId)) {
                                Bundle bundle16 = new Bundle();
                                if (bundleFromUrl != null) {
                                    bundle16.putAll(bundleFromUrl);
                                }
                                bundle16.putString("skuId", pinGouId);
                                if (z) {
                                    setClosePageParams(context, bundle16);
                                }
                                JumpUtil.execJumpByDes("productDetail", context, bundle16);
                                WebUnifiedMtaUtil.sendExposureMta(context, TAG, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                                if (z) {
                                    closePage(context);
                                }
                                return true;
                            } else if (WebViewHelper.isMiniProgUrl(str)) {
                                Bundle bundle17 = new Bundle();
                                if (bundleFromUrl != null) {
                                    bundle17.putAll(bundleFromUrl);
                                }
                                if (z) {
                                    setClosePageParams(context, bundle17);
                                }
                                MantoActivityUtils.startManoPage(context, str, bundle17);
                                WebUnifiedMtaUtil.sendExposureMta(context, TAG, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                                if (z) {
                                    closePage(context);
                                }
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }

    private static void closePage(final Context context) {
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).post(new Runnable() { // from class: com.jingdong.common.web.util.M2NativeHelper.1
                @Override // java.lang.Runnable
                public void run() {
                    ((BaseActivity) context).finish();
                }
            }, 500);
        }
    }

    private static boolean isNeedCheckToNative(String str) {
        try {
            return true ^ TextUtils.equals("0", Uri.parse(str).getQueryParameter("has_native"));
        } catch (Exception e2) {
            Log.e(TAG, e2.getMessage(), e2);
            return true;
        }
    }

    private static void setClosePageParams(Context context, Bundle bundle) {
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).isDisposeableUnableExitAnim = true;
        }
        if (bundle != null) {
            bundle.putBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, true);
        }
    }

    private static void setNativeChecked(Bundle bundle) {
        if (bundle != null) {
            bundle.putBoolean(WebEntity.PRE_CHECKED_NATIVE, true);
        }
    }

    public static boolean check2Babel(Context context, String str, Bundle bundle, boolean z) {
        if (isNeedCheckToNative(str)) {
            return check2Babel(context, str, bundle, false, z);
        }
        return false;
    }

    public static boolean check2Babel(Context context, String str, Bundle bundle) {
        if (isNeedCheckToNative(str)) {
            return check2Babel(context, str, bundle, false, true);
        }
        return false;
    }
}
