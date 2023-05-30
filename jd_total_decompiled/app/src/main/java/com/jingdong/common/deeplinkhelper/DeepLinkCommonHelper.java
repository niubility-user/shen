package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.ILogin;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.CommonMHybridHelper;
import com.jingdong.common.web.JDWebViewBlackUrlDialog;
import com.jingdong.common.web.WebHybridUtils;
import com.jingdong.common.web.WebJumpUtils;
import com.jingdong.common.web.WebOfflineLoaderManager;
import com.jingdong.common.web.WebPreLoadHelper;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.common.web.xrender.XRender;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.deeplink.a;
import com.jingdong.sdk.deeplink.b;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class DeepLinkCommonHelper {
    public static final String BUNDLE_KEY_JD_READ_ACTIVITY_BOOKID = "bookId";
    public static final String HOST_ABOUT_ACTIVITY = "aboutkactivity";
    public static final String HOST_ADDRESSLIST_ACTIVITY = "addresslist";
    public static final String HOST_AIRTICKET_FLIGHT_LIST_ACTIVITY = "flightlistactivity";
    public static final String HOST_AIRTICKET_FLIGHT_ORDER_LIST_ACTIVITY = "flightorderlistactivity";
    public static final String HOST_AIRTICKET_FLIGHT_SEARCH_ACTIVITY = "flightsearchactivity";
    public static final String HOST_AIRTICKET_INT_FLIGHT_LIST_ACTIVITY = "intflightlistactivity";
    public static final String HOST_BABEL_VIDEO_ACTIVITY = "babelvideoactivity";
    public static final String HOST_BINDING_CARD_LIST_ACTIVITY = "binding_card_list";
    public static final String HOST_BUYASKLIST_ACTIVITY = "buyasklistactivity";
    public static final String HOST_CARD_MANAGER_ACTIVITY = "cardmanageractivity";
    public static final String HOST_CATEGORY_ACTIVITY = "categoryactivity";
    public static final String HOST_CATEGORY_PRODUCT_LIST_ACTIVITY = "categorylistpageactivity";
    public static final String HOST_CHANNELPRODUCTLIST_ACTIVITY = "channelproductlistactivity";
    public static final String HOST_CHANNELSEARCH_ACTIVITY = "channelsearchactivity";
    public static final String HOST_CHARGE_CITY_ACTIVITY = "chargecityactivity";
    public static final String HOST_CHAT_BACKGROUND_SETTING_ACTIVITY = "chat_background_setting_activity";
    public static final String HOST_COMMERCIALRULE_ACTIVITY = "commercialruleactivity";
    public static final String HOST_COMPLETEORDER_ACTIVITY = "completeorderactivity";
    public static final String HOST_COMPLETE_ORDER_ACTIVITY = "completeorderactivity";
    public static final String HOST_COMPLETE_ORDER_LIST_ACTIVITY = "completeOrderListActivity";
    public static final String HOST_CONVERGE_LIST = "productlistConverge";
    public static final String HOST_COOIMAGE = "cooimage";
    public static final String HOST_COUPONCENTER_ACTIVITY = "couponcenter";
    public static final String HOST_CROSSCATEGORY_LIST = "productlistCrossCategory";
    public static final String HOST_DOLPHIN_ACTIVITY = "dolphinactivity";
    public static final String HOST_EDITNEWEASYBUYADDRESS_ACTIVITY = "editneweasybuyaddress";
    public static final String HOST_EDITORDERADDRESSLISTACT_ACTIVITY = "editorderaddresslistAct";
    public static final String HOST_EDITORDERADDRESSLISTFLOOR_ACTIVITY = "editorderaddresslistfloor";
    public static final String HOST_EDITORDERADDRESSLIST_ACTIVITY = "editorderaddresslist";
    public static final String HOST_EDITORDERADDRESS_ACTIVITY = "editorderaddress";
    public static final String HOST_ENJOYBUY_ACTIVITY = "enjoybuymainactivity";
    public static final String HOST_ENJOYBUY_BRAND_ACTIVITY = "enjoybuybrandactivity";
    public static final String HOST_EVALUATE_ACTIVITY = "evaluateunite";
    public static final String HOST_EVALUATE_CENTER_MAIN = "evaluatecentermain";
    public static final String HOST_EVALUATE_COMMENT_FLOOR = "commentfloor";
    public static final String HOST_EVALUATE_COMMENT_INPUT = "commentinput";
    public static final String HOST_EVALUATE_DETAIL_ACTIVITY = "evaluatedetail";
    public static final String HOST_EVALUATE_SECOND_REPLY = "commentsecondreply";
    public static final String HOST_EVALUATE_UGC_CONTENT = "shareOrderUgcContent";
    public static final String HOST_FEEDBACK_ACTIVITY = "feedbackactivity";
    public static final String HOST_FLIGHTSEARCH_ACTIVITY = "flightsearchactivity";
    public static final String HOST_FRIEND_LIST = "friendlist";
    public static final String HOST_FRIEND_MANAGER = "friendmanager";
    public static final String HOST_GAMECHARGEDETAIL_ACTIVITY = "gamechargedetailAct";
    public static final String HOST_GAME_CHARGE_COUPON_ACTIVITY = "gamechargecouponactivity";
    public static final String HOST_GIFT_POOL_ACTIVITY = "giftpoolactivity";
    public static final String HOST_GIFT_SHOPPING_ACTIVITY = "giftshoppingactivity";
    public static final String HOST_H5_VIDEO_PLAY_ACTIVITY = "h5videoplayactivity";
    public static final String HOST_HOME_OVERSEA_SELECT_ACTIVITY = "homeoverseaselectactivity";
    public static final String HOST_HOURSEARCH_ACTIVITY = "hoursearchactivity";
    public static final String HOST_HYBIRD_ACTIVITY = "hybirdactivity";
    public static final String HOST_IDCARDUPLOAD_ACTIVITY = "idcarduploadactivity";
    public static final String HOST_IMAGE_ACTIVITY = "imageactivity";
    public static final String HOST_INTELLIGENT_ASSISTANT = "intelligent_assistant";
    public static final String HOST_INTELLIGENT_ASSISTANT_CHAT_TIMBRE = "intelligent_assistant_chat_timbre";
    public static final String HOST_INTELLIGENT_ASSISTANT_LOGIN_GUIDE_PAGE = "intelligent_assistant_login_guide_page";
    public static final String HOST_INTELLIGENT_ASSISTANT_MALL = "intelligent_assistant_mall";
    public static final String HOST_INTELLIGENT_ASSISTANT_NICKNAME_MODIFICATION = "intelligent_assistant_nickname_modification";
    public static final String HOST_INTELLIGENT_ASSISTANT_SETTING = "intelligent_assistant_setting";
    public static final String HOST_INTELLIGENT_ASSISTANT_SKILL_INTRODUCTION = "intelligent_assistant_skill_introduction";
    public static final String HOST_INTELLIGENT_ASSISTANT_VOICE_REPORT_CONTROL = "intelligent_assistant_voice_report_control";
    public static final String HOST_JDGAME_ACTIVITY = "jdgameactivity";
    public static final String HOST_JDMOBILE_CHANNEL_ACTIVITY = "jdmobilechannel";
    public static final String HOST_JD_COMMON_DYNAMIC_ACTIVITY = "dynamiccommonactivity";
    public static final String HOST_JD_READ_ACTIVITY = "jdread";
    public static final String HOST_JD_TASK_CLEAR_ACTIVITY = "jdtaskclearactivity";
    public static final String HOST_JD_TO_MOBIKE = "jdToMobike";
    public static final String HOST_JSHOP_ACTIVITY_ACTIVITY = "jshopActivity";
    public static final String HOST_JSHOP_COUPONS_LIST_ACTIVITY = "jshopcouponslist";
    public static final String HOST_JSHOP_DYNAMIC_ACTIVITY = "jshopdynamic";
    public static final String HOST_JSHOP_DYNAMIC_ACTIVITY_ORIGIN = "jshopdynamic_origin";
    public static final String HOST_JSHOP_DYNAMIC_DETAIL = "jshopdynamicdetail";
    public static final String HOST_JSHOP_DYNAMIC_MORE_PRODUCTS_ACTIVITY = "jshopdynamicmoreproducts";
    public static final String HOST_JSHOP_DYNAMIC_SET_ACTIVITY = "jshopdynamicsetactivity";
    public static final String HOST_JSHOP_DYNA_FRAGMENT_ACTIVITY = "jshopdynafragmentact";
    public static final String HOST_JSHOP_LABEL_ACTIVITY = "jshoplabelactivity";
    public static final String HOST_JSHOP_MEMBER_ACTIVITY = "jshopmemberactivity";
    public static final String HOST_JSHOP_MEMBER_ACTIVITY_ORIGIN = "jshopmemberactivity_origin";
    public static final String HOST_JSHOP_NEW_ACTIVITY = "jshopnew";
    public static final String HOST_JSHOP_NEW_ACTIVITY_ORIGIN = "jshopnew_origin";
    public static final String HOST_JSHOP_NOPAYPIN_ACTIVITY = "jshopnopaypinactivity";
    public static final String HOST_JSHOP_PRAISED_DYNAMIC_ACTIVITY = "jshoppraisedynamicactivity";
    public static final String HOST_JSHOP_PRODUCTLIST_ACTIVITY = "jshopproductlist";
    public static final String HOST_JSHOP_PRODUCTLIST_ACTIVITY_ORIGIN = "jshopproductlist_origin";
    public static final String HOST_JSHOP_PROMOTION_ACTIVITY = "jshoppromotion";
    public static final String HOST_JSHOP_PROMOTION_ACTIVITY_ORIGIN = "jshoppromotion_origin";
    public static final String HOST_JSHOP_RECOMMEND_PRODUCT_LIST = "jshoprecommendproductlist";
    public static final String HOST_JSHOP_SEARCH_ACTIVITY = "jshopsearchactivity";
    public static final String HOST_JSHOP_SEARCH_WORD_ACTIVITY = "jshopsearchword";
    public static final String HOST_JSHOP_SIGNUP_ACTIVITY = "jshopsignup";
    public static final String HOST_JSHOP_TAKE_COUPON_ACTIVITY = "jshoptakecoupon";
    public static final String HOST_JSHOP_TOPICWARE_ACTIVITY = "jshoptopicware";
    public static final String HOST_JSHOP_TOPICWARE_ACTIVITY_ORIGIN = "jshoptopicware_origin";
    public static final String HOST_LOGININ = "loginin";
    public static final String HOST_MAINSHOP = "mainshop";
    public static final String HOST_MAINSHOP_ORIGIN = "mainshop_origin";
    public static final String HOST_MARKET_HISTORY_ACTIVITY = "markethistory";
    public static final String HOST_MESSAGE_CENTER_MAIN_ACTIVITY_NEW = "messagecentermainactivitynew";
    public static final String HOST_MESSAGE_CENTER_SECOND_ACTIVITY = "messagecenteraccountactivity";
    public static final String HOST_MESSAGE_CENTER_SETTING_ACTIVITY = "messagecentersettingactivity";
    public static final String HOST_MESSAGE_CENTER_SHIELD_ACTIVITY = "messagecentershieldsubscriptionnumberactivity";
    public static final String HOST_MIAOSHA_ACTIVITY = "miaoshaactivity";
    public static final String HOST_MIAOSHA_BANNER_ACTIVITY = "miaoshabannerinner";
    public static final String HOST_MIAOSHA_BRANDINNER_ACTIVITY = "miaoshabrandinner";
    public static final String HOST_MIAOSHA_CATEGORY_ACTIVITY = "miaoshacategory";
    public static final String HOST_MIAOSHA_LIANGFAN_INNER_ACTIVITY = "liangfaninner";
    public static final String HOST_MIAOSHA_LIVE_PREDICT_ACTIVITY = "miaoshalivepredict";
    public static final String HOST_MIAOSHA_MYCONCERN_ACTIVITY = "miaoshamyconcern";
    public static final String HOST_MORESETTING_ACTIVITY = "moresettingactivity";
    public static final String HOST_MORE_ENTRANCE_LIST_ACTIVITY = "moreentrancelistactivity";
    public static final String HOST_MORE_GAME_INTERACTION_ACTIVITY = "moregameinteractionactivity";
    public static final String HOST_MOVIE_ACTIVITY = "movieactivity";
    public static final String HOST_MULTISELLER_ACTIVITY = "multiselleractivity";
    public static final String HOST_MYCOUPON_ACTIVITY = "my_coupon_activity";
    public static final String HOST_MY_COUPON_FETCH_LIST_ACTIVITY = "my_coupon_fetch_list_activity";
    public static final String HOST_MY_GIFT_CARD_ACTIVITY = "mygiftcardactivity";
    public static final String HOST_MY_MESSAGE_BOX_ACTIVITY = "mymessagebox";
    public static final String HOST_MY_WALLET_ACTIVITY = "mywalletactivity";
    public static final String HOST_MY_WALLET_ACTIVITY_PLATFORM = "myPlatformWallet";
    public static final String HOST_MY_WALLET_ACTIVITY_PLATFORM_NEW = "platformwalletnew";
    public static final String HOST_NETWORK_DIAGNOSE_ACTIVITY = "networkdiagnoseactivity";
    public static final String HOST_NEWEASYBUYADDRESSLIST_ACTIVITY = "neweasyaddresslist";
    public static final String HOST_NEWFILLORDER_ACTIVITY = "newfillorderactivity";
    public static final String HOST_NEW_CATEGORY_ACTIVITY = "newcategoryactivity";
    public static final String HOST_NEW_FEEDBACK_ACTIVITY = "newfeedbackactivity";
    public static final String HOST_ONEBOXLIST_ACTIVITY = "oneboxlistactivity";
    public static final String HOST_PHONECHARGEFLOWLIST_ACTIVITY = "phonechargeflowlistAct";
    public static final String HOST_PHONECHARGEFLOWORDERDETAIL_ACTIVITY = "phonechargefloworderdetailAct";
    public static final String HOST_PHONECHARGEORDERDETAIL_ACTIVITY = "phonechargeorderdetailAct";
    public static final String HOST_PHONECHARGEORDERLIST_ACTIVITY = "phonechargeorderlistAct";
    public static final String HOST_PHONECHARGE_ACTIVITY = "chargeactivity";
    public static final String HOST_PLATFORM_FEEDBACK_ACTIVITY = "platformfeedbackactivity";
    public static final String HOST_POPUPNEWFILLORDER_ACTIVITY = "popupnewfillorderactivity";
    public static final String HOST_POPUP_ADDRESSLIST_ACTIVITY = "popupaddresslist";
    public static final String HOST_POPUP_EDITORDERADDRESS_ACTIVITY = "popupeditorderaddress";
    public static final String HOST_PRIVACY_POLICY_PAGE = "privacypolicypage";
    public static final String HOST_PRODUCT_LIST = "productlist";
    public static final String HOST_PURCHASE_LIST = "productlistPurchase";
    public static final String HOST_QBGAMEORDERLIST_ACTIVITY = "qbgameorderlistAct";
    public static final String HOST_QB_CHARGE_ACTIVITY = "qbchargeactivity";
    public static final String HOST_RANK_CATE_INTER = "rankCateIntermediateActivity";
    public static final String HOST_SCAN_CODE_LOGIN_ACTIVITY = "scancodeloginactivity";
    public static final String HOST_SEARCHSHOP_LIST_ORIGIN = "jshopsearchlistactivity_origin";
    public static final String HOST_SEARCH_ACTIVITY = "serachactivity";
    public static final String HOST_SELFPICKMAP_ACTIVITY = "selfpickmapactivity";
    public static final String HOST_SET_TEXT_SCALE_MODE_PAGE = "settextscalemodeactivity";
    public static final String HOST_SHARE_ORDER_COMMENT_LIST = "commentlist";
    public static final String HOST_SHARE_ORDER_COMMENT_PHOTO = "commentphoto";
    public static final String HOST_SHARE_ORDER_COMMENT_SELECTION = "commentselection";
    public static final String HOST_SHARE_ORDER_REPORT_DETAIL = "CommentReportDetail";
    public static final String HOST_SHOPPINGCART = "shoppingcart";
    public static final String HOST_SHOW_SHARE_FRIEND_LIST = "showsharefriendlist";
    public static final String HOST_SWITCH_MODE_ACTIVITY = "switchmodeactivity";
    public static final String HOST_USER_MANAGER_ID_CARD_LIST_ACTIVITY = "cardlistactivity";
    public static final String HOST_USER_MANAGER_PLAN_B_ACTIVITY = "usermanagerplanbactivity";
    public static final String HOST_USER_SECOND_ACTIVITY = "usersecondactivity";
    public static final String HOST_VIDEOLIVEROOM_ACTIVITY = "videoliveroom";
    public static final String HOST_VOICESEARCH_ACTIVITY = "voicesearchactivity";
    public static final String HOST_WEBACTIVITY = "webactivity";
    public static final String HOST_WORTHBUYAUTHOR_ACTIVITY = "worthbuyauthor";
    public static final String HOST_YCT_NFC_ACTIVITY = "nfcactivity";
    private static final String MAIN_APP = "main";
    public static final String PATH_MAIN_JD_READ_ACTIVITY = "tryread";
    public static final String VALUE_DES_INNOVATION_LAB = "innovationLab";

    private static boolean isSwitchOpen(String str) {
        if ("main".equals(str)) {
            return true;
        }
        return DeepLinkSwitch.getInstance().isSwitchOpen(str);
    }

    private static void loginAndstartActivity(final IMyActivity iMyActivity, final String str, final Bundle bundle, final int i2, String str2) {
        LoginUserHelper.getInstance().executeLoginRunnable(iMyActivity, new Runnable() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper.1
            @Override // java.lang.Runnable
            public void run() {
                DeepLinkDispatch.startActivityDirect(iMyActivity.getThisActivity(), str, bundle, i2);
            }
        }, str2);
    }

    private static void loginAndstartActivityForResult(final IMyActivity iMyActivity, final String str, final Bundle bundle, final int i2, final int i3, String str2) {
        LoginUserHelper.getInstance().executeLoginRunnable(iMyActivity, new Runnable() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper.2
            @Override // java.lang.Runnable
            public void run() {
                DeepLinkDispatch.startActivityForResult(iMyActivity.getThisActivity(), str, bundle, i2, i3);
            }
        }, str2);
    }

    public static void startActivity(Context context, String str, Bundle bundle, boolean z, int i2, boolean z2, String str2) {
        if (context != null) {
            DeepLinkUri.Builder host = new DeepLinkUri.Builder().scheme("jingdong").host(str);
            a d = b.a().d(host.toString());
            if (d == null) {
                return;
            }
            String b = d.b();
            if (!TextUtils.isEmpty(b) && isSwitchOpen(b)) {
                if (z2) {
                    if (context instanceof IMyActivity) {
                        loginAndstartActivity((IMyActivity) context, host.toString(), bundle, i2, str2);
                        return;
                    }
                    throw new IllegalArgumentException("context must be a subclass of BaseActivity if need login");
                }
                DeepLinkDispatch.startActivityDirect(context, host.toString(), bundle, i2);
                return;
            }
            return;
        }
        throw new NullPointerException("context parameter is null");
    }

    public static void startActivityDirect(Context context, String str, Bundle bundle) {
        startActivity(context, str, bundle, true, 0, false, "");
    }

    public static void startActivityDirectNeedLogin(IMyActivity iMyActivity, String str, Bundle bundle, String str2) {
        startActivity(iMyActivity.getThisActivity(), str, bundle, true, 0, true, str2);
    }

    public static void startActivityForResult(Activity activity, String str, Bundle bundle, int i2) {
        startActivityForResult(activity, str, bundle, i2, 0, false, "");
    }

    public static void startActivityForResultNeedLogin(IMyActivity iMyActivity, String str, Bundle bundle, int i2, String str2) {
        startActivityForResult(iMyActivity.getThisActivity(), str, bundle, i2, 0, true, str2);
    }

    public static void startBindingCardListActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_BINDING_CARD_LIST_ACTIVITY).toString(), bundle);
    }

    public static void startCardManagerActivity(Context context, Bundle bundle, boolean z) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_CARD_MANAGER_ACTIVITY).toString(), bundle);
    }

    public static void startCooImageActivityForResult(Activity activity, Bundle bundle, int i2) {
        DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_COOIMAGE).toString(), bundle, i2);
    }

    public static void startLoginForResult(Activity activity, Bundle bundle, int i2) {
        DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_LOGININ).toString(), bundle, i2);
    }

    public static void startMainFrameClearAllTask(Activity activity) {
        DeepLinkDispatch.startActivityDirect(activity, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_JD_TASK_CLEAR_ACTIVITY).toString(), (Bundle) null, 67108864);
        activity.finish();
    }

    public static void startMoreEntranceListActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_MORE_ENTRANCE_LIST_ACTIVITY).toString(), bundle);
    }

    public static void startMoreGameInteractionActivity(final Context context, final Bundle bundle) {
        try {
            final String builder = new DeepLinkUri.Builder().scheme("jingdong").host(HOST_MORE_GAME_INTERACTION_ACTIVITY).toString();
            DeepLinkLoginHelper.startLoginActivity(context, bundle, new ILogin() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper.3
                @Override // com.jingdong.common.login.ILogin
                public void onSuccess(String str) {
                    if (TextUtils.equals(str, builder)) {
                        if (OKLog.D) {
                            OKLog.d("startMoreGameInteractionActivity", "startMoreGameInteractionActivity onSuccess host: " + builder);
                        }
                        DeepLinkDispatch.startActivityDirect(context, builder, bundle);
                    }
                }
            }, builder);
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.d("startMoreGameInteractionActivity", "startMoreGameInteractionActivity error: " + e2);
            }
        }
    }

    public static void startMyGiftCardActivity(Context context, Bundle bundle) {
    }

    public static void startMyWalletActivity(Context context, Bundle bundle, boolean z) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_MY_WALLET_ACTIVITY).toString(), bundle);
    }

    public static void startNetworkDiagnoseActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_NETWORK_DIAGNOSE_ACTIVITY).toString(), bundle);
    }

    public static void startOrderListActivity(Context context, Bundle bundle, boolean z) {
        DeepLinkOrderCenterHelper.startOrderList(context, bundle);
    }

    public static void startPaperBookActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_JD_READ_ACTIVITY).addPathSegment(PATH_MAIN_JD_READ_ACTIVITY).toString(), bundle);
    }

    @Deprecated
    public static void startProductListActivity(Context context, Bundle bundle, boolean z) {
        if (bundle != null && (!TextUtils.isEmpty(bundle.getString("CouponbatchID")) || !TextUtils.isEmpty(bundle.getString("activityId")) || !TextUtils.isEmpty(bundle.getString("eCardID")))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_CONVERGE_LIST).toString(), bundle);
        } else {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_PRODUCT_LIST).toString(), bundle);
        }
    }

    public static void startShopActivity(Context context, Bundle bundle, boolean z) {
        DeepLinkJShopHomeHelper.gotoJShopHome(context, bundle);
    }

    public static void startShoppingCartActivity(Context context, Bundle bundle, boolean z) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_SHOPPINGCART).toString(), bundle);
    }

    public static void startSwitchModeActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_SWITCH_MODE_ACTIVITY).toString(), bundle);
    }

    public static void startUserManagerActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_USER_MANAGER_PLAN_B_ACTIVITY).toString(), bundle);
    }

    public static void startUserSecondActivity(Context context, Bundle bundle, boolean z) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("usersecondactivity").toString(), bundle);
    }

    public static void startWebActivity(Context context, Bundle bundle, boolean z) {
        String addCustomParams = WebUtils.addCustomParams(null, bundle);
        if (WebJumpUtils.is2Native(context, bundle)) {
            return;
        }
        if (SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.WEB_BLACK_LIST_LOGIC, false)) {
            if (WebJumpUtils.checkUrlInIllegalList(bundle)) {
                ExceptionReporter.reportWebViewCommonError(SwitchQueryFetcher.WEB_BLACK_LIST_LOGIC, "", "JDWebView black list error", "");
                return;
            } else if (WebJumpUtils.checkUrlInBlackList(bundle)) {
                String urlFromBundle = WebJumpUtils.getUrlFromBundle(bundle);
                if (TextUtils.isEmpty(urlFromBundle)) {
                    return;
                }
                new JDWebViewBlackUrlDialog(context, urlFromBundle).show();
                ExceptionReporter.reportWebViewCommonError(SwitchQueryFetcher.WEB_BLACK_LIST_LOGIC, "", "JDWebView black list error", "");
                return;
            }
        }
        boolean sendClickEvent = XRender.getInstance().sendClickEvent(addCustomParams);
        if (WebHybridUtils.hybridEnable && !sendClickEvent) {
            bundle = WebPreLoadHelper.startPreLoadForBundle(WebOfflineLoaderManager.createOfflineLoaderForBundle(bundle, CommonMHybridHelper.sOfflineParamGetter), CommonMHybridHelper.sPreloadParamGetter);
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_WEBACTIVITY).toString(), bundle);
    }

    public static void startActivityForResult(Activity activity, String str, Bundle bundle, int i2, int i3, boolean z, String str2) {
        if (activity != null) {
            DeepLinkUri.Builder host = new DeepLinkUri.Builder().scheme("jingdong").host(str);
            a d = b.a().d(host.toString());
            if (d == null) {
                return;
            }
            String b = d.b();
            if (!TextUtils.isEmpty(b) && isSwitchOpen(b)) {
                if (z) {
                    if (activity instanceof IMyActivity) {
                        loginAndstartActivityForResult((IMyActivity) activity, host.toString(), bundle, i2, i3, str2);
                        return;
                    }
                    throw new IllegalArgumentException("activity must be a subclass of BaseActivity if need login");
                }
                DeepLinkDispatch.startActivityForResult(activity, host.toString(), bundle, i2, i3);
                return;
            }
            return;
        }
        throw new NullPointerException("activity parameter is null");
    }
}
