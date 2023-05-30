package com.jd.lib.cashier.sdk.a.a.d;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.dynamic.DYConstants;
import com.jd.lib.cashier.sdk.a.a.a.c;
import com.jd.lib.cashier.sdk.btcombinationpay.bean.RequestParamBTSkuCalculateRate;
import com.jd.lib.cashier.sdk.btcombinationpay.bean.RequestParamSkuPlanInfo;
import com.jd.lib.cashier.sdk.d.g.c.c.b;
import java.util.List;
import java.util.Map;

/* loaded from: classes14.dex */
public class a {
    private com.jd.lib.cashier.sdk.a.b.a a;

    public a(com.jd.lib.cashier.sdk.a.b.a aVar) {
        this.a = aVar;
    }

    private b d() {
        b bVar = new b();
        try {
            Map<String, Object> map = this.a.f2796i;
            if (map != null) {
                if (!TextUtils.isEmpty((String) map.get("from")) && !TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get("from"))) {
                    bVar.a = (String) this.a.f2796i.get("from");
                }
                if (!TextUtils.isEmpty((String) this.a.f2796i.get(PairKey.COMBINE_TYPE)) && !TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get(PairKey.COMBINE_TYPE))) {
                    bVar.y = (String) this.a.f2796i.get(PairKey.COMBINE_TYPE);
                }
                if (!TextUtils.isEmpty((String) this.a.f2796i.get(PairKey.IS_NEW_CARD)) && !TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get(PairKey.IS_NEW_CARD))) {
                    bVar.t = TextUtils.equals("1", (String) this.a.f2796i.get(PairKey.IS_NEW_CARD));
                }
                if (!TextUtils.isEmpty((String) this.a.f2796i.get(PairKey.PRIZE_ID)) && !TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get(PairKey.PRIZE_ID))) {
                    bVar.f3277n = (String) this.a.f2796i.get(PairKey.PRIZE_ID);
                }
                if (!TextUtils.isEmpty((String) this.a.f2796i.get(PairKey.BANK_CODE)) && !TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get(PairKey.BANK_CODE))) {
                    bVar.s = (String) this.a.f2796i.get(PairKey.BANK_CODE);
                }
                if (!TextUtils.isEmpty((String) this.a.f2796i.get("channelId")) && !TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get("channelId"))) {
                    bVar.f3269f = (String) this.a.f2796i.get("channelId");
                }
                if (!TextUtils.isEmpty((String) this.a.f2796i.get(PairKey.UNIQUE_CHANNEL_ID)) && !TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get(PairKey.UNIQUE_CHANNEL_ID))) {
                    bVar.f3268e = (String) this.a.f2796i.get(PairKey.UNIQUE_CHANNEL_ID);
                }
                if (!TextUtils.isEmpty((String) this.a.f2796i.get(PairKey.REQUIRE_UUID)) && !TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get(PairKey.REQUIRE_UUID))) {
                    bVar.f3274k = (String) this.a.f2796i.get(PairKey.REQUIRE_UUID);
                }
                if (!TextUtils.isEmpty((String) this.a.f2796i.get(PairKey.ACCOUNT_CODE)) && !TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get(PairKey.ACCOUNT_CODE))) {
                    bVar.r = (String) this.a.f2796i.get(PairKey.ACCOUNT_CODE);
                }
                if (!TextUtils.isEmpty((String) this.a.f2796i.get("productCode")) && !TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get("productCode"))) {
                    bVar.u = (String) this.a.f2796i.get("productCode");
                }
                if (!TextUtils.isEmpty((String) this.a.f2796i.get(PairKey.CHANNEL_TYPE)) && !TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get(PairKey.CHANNEL_TYPE))) {
                    bVar.o = (String) this.a.f2796i.get(PairKey.CHANNEL_TYPE);
                }
                if (!TextUtils.isEmpty((String) this.a.f2796i.get(PairKey.BANK_PLAN_RATE)) && !TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get(PairKey.BANK_PLAN_RATE))) {
                    bVar.p = (String) this.a.f2796i.get(PairKey.BANK_PLAN_RATE);
                }
                if (!TextUtils.isEmpty((String) this.a.f2796i.get(PairKey.CHANNEL_STATUS)) && !TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get(PairKey.CHANNEL_STATUS))) {
                    bVar.f3273j = (String) this.a.f2796i.get(PairKey.CHANNEL_STATUS);
                }
                if (!TextUtils.isEmpty((String) this.a.f2796i.get(PairKey.PAY_MARKETING_UUID)) && !TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get(PairKey.PAY_MARKETING_UUID))) {
                    bVar.f3276m = (String) this.a.f2796i.get(PairKey.PAY_MARKETING_UUID);
                }
                if (!TextUtils.isEmpty((String) this.a.f2796i.get(PairKey.MER_CHANT_FEE_SUB_SIDE_BY)) && !TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get(PairKey.MER_CHANT_FEE_SUB_SIDE_BY))) {
                    bVar.q = (String) this.a.f2796i.get(PairKey.MER_CHANT_FEE_SUB_SIDE_BY);
                }
                if (!TextUtils.isEmpty((String) this.a.f2796i.get(PairKey.PAY_TOKEN)) && !TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get(PairKey.PAY_TOKEN))) {
                    bVar.v = (String) this.a.f2796i.get(PairKey.PAY_TOKEN);
                }
                if (!TextUtils.isEmpty((String) this.a.f2796i.get("jdPayChannel")) && !TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get("jdPayChannel"))) {
                    bVar.w = (String) this.a.f2796i.get("jdPayChannel");
                }
                if (!TextUtils.isEmpty((String) this.a.f2796i.get(PairKey.CHANGETAG)) && !TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get(PairKey.CHANGETAG))) {
                    bVar.x = (String) this.a.f2796i.get(PairKey.CHANGETAG);
                }
                if (!TextUtils.isEmpty(this.a.f2797j)) {
                    bVar.A = this.a.f2797j;
                }
            }
            return bVar;
        } catch (Exception e2) {
            e2.printStackTrace();
            return bVar;
        }
    }

    private void e(b bVar) {
        if (!TextUtils.isEmpty((String) this.a.f2796i.get("channelCode")) && !TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get("channelCode"))) {
            bVar.f3287c = (String) this.a.f2796i.get("channelCode");
        }
        if (!TextUtils.isEmpty((String) this.a.f2796i.get("appId")) && !TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get("appId"))) {
            bVar.appId = (String) this.a.f2796i.get("appId");
        }
        if (!TextUtils.isEmpty((String) this.a.f2796i.get("paySign")) && !TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get("paySign"))) {
            bVar.paySign = (String) this.a.f2796i.get("paySign");
        }
        if (!TextUtils.isEmpty((String) this.a.f2796i.get("orderId")) && !TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get("orderId"))) {
            bVar.orderId = (String) this.a.f2796i.get("orderId");
        }
        if (!TextUtils.isEmpty((String) this.a.f2796i.get("orderType")) && !TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get("orderType"))) {
            bVar.orderType = (String) this.a.f2796i.get("orderType");
        }
        if (!TextUtils.isEmpty((String) this.a.f2796i.get("orderPrice")) && !TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get("orderPrice"))) {
            bVar.orderPrice = (String) this.a.f2796i.get("orderPrice");
        }
        if (!TextUtils.isEmpty((String) this.a.f2796i.get("orderTypeCode")) && !TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get("orderTypeCode"))) {
            bVar.orderTypeCode = (String) this.a.f2796i.get("orderTypeCode");
        }
        if (!TextUtils.isEmpty((String) this.a.f2796i.get(PairKey.GROUP_ORDERS)) && !TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get(PairKey.GROUP_ORDERS))) {
            bVar.groupOrders = (String) this.a.f2796i.get(PairKey.GROUP_ORDERS);
        }
        if (TextUtils.isEmpty((String) this.a.f2796i.get(PairKey.COMBINED_ORDER_ID)) || TextUtils.equals(DYConstants.DY_NULL_STR, (String) this.a.f2796i.get(PairKey.COMBINED_ORDER_ID))) {
            return;
        }
        bVar.combinedOrderId = (String) this.a.f2796i.get(PairKey.COMBINED_ORDER_ID);
    }

    public void a(FragmentActivity fragmentActivity) {
        b d = d();
        d.setActivity(fragmentActivity);
        e(d);
        new com.jd.lib.cashier.sdk.a.a.a.b().e(d);
    }

    public void b(FragmentActivity fragmentActivity) {
        c(fragmentActivity, null, "");
    }

    public void c(FragmentActivity fragmentActivity, List<RequestParamSkuPlanInfo> list, String str) {
        if (fragmentActivity == null || this.a == null) {
            return;
        }
        RequestParamBTSkuCalculateRate requestParamBTSkuCalculateRate = new RequestParamBTSkuCalculateRate();
        requestParamBTSkuCalculateRate.setActivity(fragmentActivity);
        com.jd.lib.cashier.sdk.a.b.a aVar = this.a;
        requestParamBTSkuCalculateRate.appId = aVar.b;
        requestParamBTSkuCalculateRate.orderId = aVar.d;
        requestParamBTSkuCalculateRate.orderType = aVar.f2792e;
        requestParamBTSkuCalculateRate.orderPrice = aVar.f2793f;
        requestParamBTSkuCalculateRate.orderTypeCode = aVar.f2794g;
        requestParamBTSkuCalculateRate.paySign = aVar.f2795h;
        if (list != null && !list.isEmpty()) {
            requestParamBTSkuCalculateRate.selectedPlanList = list;
        }
        if (!TextUtils.isEmpty(str)) {
            requestParamBTSkuCalculateRate.operationFlag = str;
        }
        new c().e(requestParamBTSkuCalculateRate);
    }
}
