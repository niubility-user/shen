package com.jingdong.common.guard;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.security.jdguard.a.c;
import com.jd.security.jdguard.f.d;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.performance.PerformanceReporter;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.cashiernative.CashierPayChannelCode;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.pay.JumpUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CacheConstant;
import com.jingdong.jdsdk.network.dependency.IJDGuardPlugin;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.oklog.OKLog;
import java.net.URI;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class JDGuardHelper {
    public static final List<String> DEFAULT_FUNCTION_ID_WHITELIST = Arrays.asList("newReceiveRvcCoupon", "nReceiveCoupon", "smtCoupon_detailList", "smt_rankV2Detail", "jdread_api_cart_sub_post", "jdread_api_search", "jdread_api_ebook_ebookId", "jdread_api_ebook_ebookId_recommend", "jdread_api_ebook_ebookId_promotion", "jdread_api_category_cid_ebook", "jdread_api_order_jdmp_pay_detail_post", "jdread_api_channel_cid", "jdread_api_channel_module_collection_id", "jdread_api_paperbook_anchor", "jdread_api_ebook_catalog_v2_ebookId", "jdread_api_suggest", "jdread_api_vip_detail", "smt_promote_detail_info", "smt_take_lottery", "smt_classify_productlist", "smt_index", "smtRight_skuList", "smt_rankGoods", "search_lbsSearchPopCoupon", "mb2cappCartSoa_getCart", "mb2cappCartSoa_addItems", "health_cmp_listFeedAlwaysBuyMain", "health_cmp_pagePreferredSku", "health_cmp_listServiceCard", "uniformRecommend71", "uniformRecommend52", "similarProdList", "uniformRecommend73", "uniformRecommend78", "uniformRecommend72", "pgx_channel_page_data", "platPayChannel", "platGroupPayChannel", "platMedicalPay", "cyberMoneyPaymentAcc", "getCyberMoneyCardCouponInfo", "platApplePay", "platBaiTiaoCalculateRate", CashierPayChannelCode.UNION_PAY, "platBankCardCouponInfo", "weixin", "platCyberMoneyPay", "platDFPay", JumpUtils.FUNCTION_ID_JDPAYV2, "platQQWalletPay", "applePay", "platUnionPay", "genPayId", "isJdPayPaymentAcc", "platWapWXPay", "platOctopusPay", "platBaitiaoCouponInfo", "getWeixinPayParams", "platWXMiniPay", "qryActivityInfoList", "ninebox_hit", "jdBaiTiaoCalculateRate", "platWXGzhPay", JumpUtils.FUNCTION_ID_GETSUCCESSURL, "getBankCardCouponInfo", "platPayResult", JumpUtils.FUNCTION_ID_UNIONPAYV2, "payIndex", "platWXPay", "platJDPayAcc", JumpUtils.FUNCTION_ID_WEIXINPAY, JumpUtils.FUNCTION_ID_GENAPPPAYID, "platCreditCardCalculateRate", "platJDPayChannelList", "switchPlatSdk", "platBestPay", CashierPayChannelCode.BEST_PAY, "jdCreditCardCalculateRate", "platCyberMoneyCouponInfo", "qqWalletPay", "weiXinDFPay", "platDFPayInsideWX", "octopusPay", "platCyberMoneyCardList", "platBaiTiaoSkuCalculateRate", "platJudgeMaliceOrder", "platPayDollarPay", "platPaypalPay", "platOctopusPayRate", "scanCodePay", "platCloseBottomRecommend", "platCreditPayIndex", "platGlobalVerify", "creditPayIndex", "payDollar", "closeBottomRecommend", "globalVerify", "jdPayCreditCardChannel", "jdPayCreditCardCoupons", "jdPayCreditCardFee", "octopusRatePay", "pgx_interact_interface_invoke", "pgx_load_floor_data", "nyfeedFlowDisplayIndex", "pg_channel_page_data", "feedFlowDisplayIndex", "pg_interact_interface_invoke", "feedFlowCpsMerge", "feedFlowCpsInfo", "multiSuppliers", "dj_freight_freightTogetherOrderPost", "dj_freight_freightTogetherOrderTip", "skusForSpuExpose", "billionGoodsList", "billionGenericFloors", "billionDefaultGoodsList", "dj_marketsettle_getCurrentAccountNewPost", "dj_marketsettle_getSaveMoneyPlan", "dj_freeFreightProduct_queryList", "dj_freeFreightProduct_queryDetails", "dj_deliverydiscount_queryFreightDiscount", "dj_redPacketDiscount_queryRedPacketList", "dj_voucher_getVoucherModeInfoResp", "dj_trade_settle_init", "dj_trade_settle_operate", "dj_trade_submit_submitOrder", "dj_trade_cashier_genPayId", "dj_cart_changeItemNum", "dj_cart_querySingleCart", "dj_cart_queryallcarts", "dj_cart_getCouponList", "dj_cart_queryMultiCartInfo", "dj_homeSearch_searchByTab", "dj_homeSearch_searchSkuResultByTab", "dj_storeIndexSearch_search", "dj_promotionSearch_searchStoreGoods", "dj_storeIndexSearch_searchByCategory", "dj_zone_recommendChannelSkuList", "dj_newFeed_contentList", "dj_rank_rankList", "dj_landingPage_channelRecommendSkus", "dj_zone_recommendSkuList", "dj_zone_getCmsTypeAll", "dj_zone_getNewChannelDetail", "dj_store_actArea", "dj_store_storeDetailV220", "dj_product_detailPost", "dj_store_reachStoreHomepage", "dj_zone_recommendStoreList", "dj_act_getActivityPage", "dj_act_storeActPage", "dj_product_detailV8_27", "dj_spuSaleAttr_spuSaleAttrListPost", "dj_mine_getRecommend", "search_lbsChannelTip", "find_shop_list", "assembleShop", "queryChoiceGoodShop", "ls_listServiceProjectItemBySkuId", "housekeeping_recommendServiceList", "housekeeping_nannyOrderGetSkuPromo", "housekeeping_nannyOrderSubmitOrder", "housekeeping_getSkuPromo", "housekeeping_submitOrder", "housekeeping_vouchersByOrderId", "smtRight_skuList", "smt_rankGoods", "getRankInfoList", "batchGetRanks", "getRankLanding", "channelTabInfo", "hours_feeds_1182", "searchCatelogy", "search_recommendRanking", "search_lbsChannelSearch", "search_pageDirectUrl", "uniformRecommend", "search", "superValueSearch ", "couponSearch", "activitySearch", "clickRecommend", "recommendWares", "searchSimilar", "hourReachTab", "hourReachChannel", "nearbyTab", "lbsChannelSearch", "oneboxSearch", "search_hourlyTab", "hours_thumpsup", "hours_benefit_point_popup", "hours_feeds", "hours_degrade", "hours_feeds_page", "hours_recommend", "hours_home", "distance", "hours_popup", "hours_income_beans", "hours_home_tab_name", "hours_home_Tag", "browseHistory", "cash_getPrdList", "newReceiveRvcCouponWithTask", "receiveNecklaceCoupon", "receiveJrCoupon", "receiveMultiCoupon", "getCouponConfig", "getCcFeedInfo", "personinfoBusiness", "getCcFeedList", "delHistoryOrder", "orderDetailInfo", "ase_mc_submitOrder_rn", "getLegoWareDetailComment", "getCommentListWithCard", "wareBusiness", "currentOrder", "submitOrder", "cart", "getFoldCommentList", "NewLogin", "NewLogin_RegArea", "welcomeHome", "categoryFeeds", "categoryHome", "seckillGenericFloors", CacheConstant.ID_MIAO_SHA, "seckillTabImg", "PhoneLogin_MsgCode", "getClarityCaseOrderList", "searchOrderPage", "getProductBrowseHistoryPage");
    private static final boolean DEFAULT_PUSH_ENABLE = true;
    private static final int DEFAULT_PUSH_PERCENTAGE = 5;
    private static final String EVA_CONFIG_KEY = "eva-upload";
    private static final String JDG_CONFIG_KEY = "jdg-configs";
    public static final String JDG_SWITCHES_KEY = "jdg-switches";
    public static final String KEY_FUNCTION_ID_WHITELIST = "jdguard_function_list";
    public static final String KEY_PLUGIN_ENABLE = "enable_jdguard";
    private static final String PERF_CH_ID = "4";
    private static final String PERF_TYPE_ID = "8";
    private static final int PUSH_TYPE_ERROR = 1;
    private static final int PUSH_TYPE_PERFORMANCE = 0;
    private static final String SPACE_KEY = "Eva-Upload";
    public static final String TAG = "JDGuard";

    public static String getAppKey() {
        return "a4dd8286-1287-401b-b451-689faeb871f5";
    }

    public static Map<String, String> getEvaConfigs() {
        try {
            return JDMobileConfig.getInstance().getConfigs(SPACE_KEY, EVA_CONFIG_KEY);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static int getEventPushPercentage() {
        try {
            String config = JDMobileConfig.getInstance().getConfig(SPACE_KEY, JDG_CONFIG_KEY, "eventPushPercentage");
            if (TextUtils.isEmpty(config)) {
                return 5;
            }
            return Integer.parseInt(config);
        } catch (Throwable unused) {
            return 5;
        }
    }

    public static Map<String, String> getJDGConfigs() {
        try {
            return JDMobileConfig.getInstance().getConfigs(SPACE_KEY, JDG_CONFIG_KEY);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static IJDGuardPlugin getJDGuardPlugin() {
        return new IJDGuardPlugin() { // from class: com.jingdong.common.guard.JDGuardHelper.1
            @Override // com.jingdong.jdsdk.network.dependency.IJDGuardPlugin
            public Map<String, String> genSign(URI uri, byte[] bArr, String str, String str2, boolean z) {
                return c.b(uri, bArr, str, str2, z);
            }

            @Override // com.jingdong.jdsdk.network.dependency.IJDGuardPlugin
            public boolean isEnable() {
                return TextUtils.equals(JDMobileConfig.getInstance().getConfig(JDGuardHelper.SPACE_KEY, JDGuardHelper.JDG_SWITCHES_KEY, JDGuardHelper.KEY_PLUGIN_ENABLE, "1"), "1");
            }

            @Override // com.jingdong.jdsdk.network.dependency.IJDGuardPlugin
            public boolean isInWhiteList(String str) {
                try {
                    String switchStringValue = SwitchQueryFetcher.getSwitchStringValue(JDGuardHelper.KEY_FUNCTION_ID_WHITELIST, "");
                    if (OKLog.D) {
                        OKLog.d(JDGuardHelper.TAG, "\u7ebf\u4e0afunctionId\u767d\u540d\u5355:" + switchStringValue);
                    }
                    if (!TextUtils.isEmpty(switchStringValue)) {
                        List parseArray = JDJSON.parseArray(switchStringValue, String.class);
                        if (parseArray == null || parseArray.isEmpty() || !parseArray.contains(str)) {
                            return false;
                        }
                        if (OKLog.D) {
                            OKLog.d(JDGuardHelper.TAG, "functionId " + str + " \u547d\u4e2d\u7ebf\u4e0a\u767d\u540d\u5355");
                            return true;
                        }
                        return true;
                    }
                    if (OKLog.D) {
                        OKLog.d(JDGuardHelper.TAG, "\u542f\u7528\u672c\u5730\u9ed8\u8ba4\u767d\u540d\u5355");
                    }
                    return JDGuardHelper.DEFAULT_FUNCTION_ID_WHITELIST.contains(str);
                } catch (Throwable unused) {
                }
                return false;
            }
        };
    }

    public static String getPicName() {
        return "a4dd8286-1287-401b-b451-689faeb871f5.jdg.jpg";
    }

    public static String getSecName() {
        return "a4dd8286-1287-401b-b451-689faeb871f5.jdg.xbt";
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0028, code lost:
        if (r0 == 1) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x002b, code lost:
        r5 = "signCost";
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x003e, code lost:
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String keyMapping(java.lang.String r5, java.lang.String r6) {
        /*
            r0 = -1
            r1 = 0
            int r2 = r6.hashCode()     // Catch: java.lang.Throwable -> L3e
            r3 = 3237136(0x316510, float:4.536194E-39)
            r4 = 1
            if (r2 == r3) goto L1d
            r3 = 3530173(0x35ddbd, float:4.946826E-39)
            if (r2 == r3) goto L12
            goto L26
        L12:
            java.lang.String r2 = "sign"
            boolean r6 = r6.equals(r2)     // Catch: java.lang.Throwable -> L3e
            if (r6 == 0) goto L26
            r0 = 1
            goto L26
        L1d:
            java.lang.String r2 = "init"
            boolean r6 = r6.equals(r2)     // Catch: java.lang.Throwable -> L3e
            if (r6 == 0) goto L26
            r0 = 0
        L26:
            if (r0 == 0) goto L30
            if (r0 == r4) goto L2b
            goto L3e
        L2b:
            java.lang.String r5 = "signCost"
        L2e:
            r1 = r5
            goto L3e
        L30:
            java.lang.String r6 = "-1103"
            boolean r5 = r6.equals(r5)     // Catch: java.lang.Throwable -> L3e
            if (r5 == 0) goto L3b
            java.lang.String r5 = "initCost"
            goto L2e
        L3b:
            java.lang.String r5 = "initWorkerCost"
            goto L2e
        L3e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.guard.JDGuardHelper.keyMapping(java.lang.String, java.lang.String):java.lang.String");
    }

    public static boolean needEventPush() {
        try {
            String config = JDMobileConfig.getInstance().getConfig(SPACE_KEY, JDG_CONFIG_KEY, "eventPushEnable");
            if (TextUtils.isEmpty(config)) {
                return true;
            }
            return config.equals("1");
        } catch (Throwable unused) {
            return true;
        }
    }

    private static void onErrorEvent(HashMap<String, String> hashMap, final String str, final String str2) {
        try {
            if (!TextUtils.isEmpty(str) && hashMap != null && !hashMap.isEmpty() && hashMap.containsKey("r")) {
                final String str3 = hashMap.get("r");
                final String jSONString = JDJSON.toJSONString(hashMap);
                if (XView2Constants.XVIEW2_ACTION_INIT.equals(str)) {
                    BaseApplication.getHandler().postDelayed(new Runnable() { // from class: com.jingdong.common.guard.JDGuardHelper.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (OKLog.D) {
                                OKLog.d(JDGuardHelper.TAG, "delayed push error with message -> " + jSONString + ", event -> " + str + ", errorNum -> " + str3);
                            }
                            ExceptionReporter.reportJDGuardExceptionData(jSONString, str, str2, str3);
                        }
                    }, Final.FIVE_SECOND);
                    return;
                }
                if (OKLog.D) {
                    OKLog.d(TAG, "push error with message -> " + jSONString + ", event -> " + str + ", errorNum -> " + str3);
                }
                ExceptionReporter.reportJDGuardExceptionData(jSONString, str, str2, str3);
            }
        } catch (Throwable unused) {
        }
    }

    private static void onPerformanceEvent(HashMap<String, String> hashMap, String str, String str2) {
        if (hashMap != null && !hashMap.isEmpty() && !TextUtils.isEmpty(str) && hashMap.containsKey("t") && hashMap.containsKey("r") && PerformanceReporter.getIsNeedReport(JdSdk.getInstance().getApplicationContext(), "8", "4")) {
            try {
                String keyMapping = keyMapping(hashMap.get("r"), str);
                if (TextUtils.isEmpty(keyMapping)) {
                    return;
                }
                final HashMap hashMap2 = new HashMap();
                hashMap2.put(keyMapping, hashMap.get("t"));
                if (XView2Constants.XVIEW2_ACTION_INIT.equals(str)) {
                    BaseApplication.getHandler().postDelayed(new Runnable() { // from class: com.jingdong.common.guard.JDGuardHelper.3
                        @Override // java.lang.Runnable
                        public void run() {
                            JDGuardHelper.pushPerformance(hashMap2);
                        }
                    }, Final.FIVE_SECOND);
                } else {
                    pushPerformance(hashMap2);
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void pushPerformance(HashMap<String, String> hashMap) {
        if (hashMap != null) {
            try {
                if (hashMap.isEmpty()) {
                    return;
                }
                if (OKLog.D) {
                    OKLog.d(TAG, "push performance with map -> " + hashMap);
                }
                HashMap hashMap2 = new HashMap();
                hashMap2.put("typeId", "8");
                hashMap2.put("chId", "4");
                hashMap2.put("occurTime", new DecimalFormat("0.000000").format(System.currentTimeMillis() / 1000));
                hashMap2.putAll(hashMap);
                PerformanceReporter.reportData(hashMap2);
            } catch (Throwable unused) {
            }
        }
    }

    public static void sendStreamData(HashMap<String, String> hashMap, String str, String str2, int i2) {
        if (hashMap == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        try {
            if (i2 == 0) {
                onPerformanceEvent(hashMap, str, str2);
            } else if (i2 != 1) {
            } else {
                onErrorEvent(hashMap, str, str2);
            }
        } catch (Throwable th) {
            d.e(th);
        }
    }
}
