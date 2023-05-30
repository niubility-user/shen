package com.jd.lib.cashier.sdk.h.a.c;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.navigator.CashierNavigator;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.cashier.app.jdlibcutter.protocol.router.IOrderRouter;
import com.jd.lib.cashier.sdk.core.utils.m;
import com.jd.lib.cashier.sdk.core.utils.p;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.h.h.n;
import com.jd.lib.cashier.sdk.pay.bean.convert.PaymentChoseHolder;
import java.util.Map;

/* loaded from: classes14.dex */
public class a extends com.jd.lib.cashier.sdk.core.aac.a {
    private Map<String, Object> d(FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.h.c.a aVar) {
        com.jd.lib.cashier.sdk.b.b.b bVar = new com.jd.lib.cashier.sdk.b.b.b();
        PaymentChoseHolder a = n.a(fragmentActivity);
        if (a != null) {
            bVar.put((com.jd.lib.cashier.sdk.b.b.b) PairKey.COMBINE_TYPE, a.combineType);
            bVar.put((com.jd.lib.cashier.sdk.b.b.b) PairKey.BANK_CODE, a.bankCode);
            bVar.put((com.jd.lib.cashier.sdk.b.b.b) PairKey.BANK_PLAN_RATE, a.bankPlanRate);
            bVar.put((com.jd.lib.cashier.sdk.b.b.b) PairKey.CHANNEL_STATUS, a.channelStatus);
            bVar.put((com.jd.lib.cashier.sdk.b.b.b) "channelId", a.channelId);
            bVar.put((com.jd.lib.cashier.sdk.b.b.b) PairKey.UNIQUE_CHANNEL_ID, a.uniqueChannelId);
            bVar.put((com.jd.lib.cashier.sdk.b.b.b) PairKey.CHANNEL_TYPE, a.channelType);
            bVar.put((com.jd.lib.cashier.sdk.b.b.b) "channelCode", a.channelType);
            bVar.put((com.jd.lib.cashier.sdk.b.b.b) PairKey.IS_NEW_CARD, a.isNewCard ? "1" : "0");
            bVar.put((com.jd.lib.cashier.sdk.b.b.b) PairKey.ACCOUNT_CODE, a.accountCode);
            bVar.put((com.jd.lib.cashier.sdk.b.b.b) PairKey.PRIZE_ID, a.prizeId);
            bVar.put((com.jd.lib.cashier.sdk.b.b.b) PairKey.REQUIRE_UUID, a.requireUUID);
            bVar.put((com.jd.lib.cashier.sdk.b.b.b) "productCode", a.productCode);
            bVar.put((com.jd.lib.cashier.sdk.b.b.b) PairKey.PAY_MARKETING_UUID, a.payMarketingUUID);
            bVar.put((com.jd.lib.cashier.sdk.b.b.b) PairKey.MER_CHANT_FEE_SUB_SIDE_BY, a.merchantFeeSubSideBy);
            bVar.put((com.jd.lib.cashier.sdk.b.b.b) PairKey.PAY_TOKEN, a.payToken);
            bVar.put((com.jd.lib.cashier.sdk.b.b.b) "jdPayChannel", a.jdPayChannel);
            if (!TextUtils.isEmpty(a.changetag)) {
                bVar.put((com.jd.lib.cashier.sdk.b.b.b) PairKey.CHANGETAG, a.changetag);
            }
        }
        bVar.put((com.jd.lib.cashier.sdk.b.b.b) "back_url", aVar.f3514h);
        if (!TextUtils.isEmpty(aVar.B)) {
            bVar.put((com.jd.lib.cashier.sdk.b.b.b) "sdkToken", aVar.B);
        }
        bVar.put((com.jd.lib.cashier.sdk.b.b.b) "appId", aVar.b);
        bVar.put((com.jd.lib.cashier.sdk.b.b.b) "paySign", aVar.f3518l);
        bVar.put((com.jd.lib.cashier.sdk.b.b.b) "orderId", aVar.f3511e);
        bVar.put((com.jd.lib.cashier.sdk.b.b.b) "orderType", aVar.f3515i);
        bVar.put((com.jd.lib.cashier.sdk.b.b.b) "orderPrice", aVar.f3516j);
        bVar.put((com.jd.lib.cashier.sdk.b.b.b) "orderTypeCode", aVar.f3517k);
        bVar.put((com.jd.lib.cashier.sdk.b.b.b) "from", aVar.D);
        if (aVar.K != null) {
            bVar.put((com.jd.lib.cashier.sdk.b.b.b) PairKey.IS_NEW_JD_PAY_API, "1");
            bVar.put((com.jd.lib.cashier.sdk.b.b.b) PairKey.REQUIRE_UUID, aVar.K.requireUUID);
        }
        if (!TextUtils.isEmpty(aVar.d())) {
            bVar.put((com.jd.lib.cashier.sdk.b.b.b) PairKey.GROUP_ORDERS, aVar.d());
        }
        if (!TextUtils.isEmpty(aVar.f3513g)) {
            bVar.put((com.jd.lib.cashier.sdk.b.b.b) PairKey.COMBINED_ORDER_ID, aVar.f3513g);
        }
        return bVar;
    }

    public void c(FragmentActivity fragmentActivity, String str) {
        p.a(fragmentActivity, str);
    }

    public void e(FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.h.c.a aVar) {
        if (fragmentActivity == null || aVar == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("appId", aVar.b);
        bundle.putString(PairKey.APP_KEY, aVar.f3510c);
        bundle.putString("orderId", aVar.f3511e);
        bundle.putString("orderType", aVar.f3515i);
        bundle.putString("payablePrice", aVar.f3516j);
        bundle.putString("orderTypeCode", aVar.f3517k);
        bundle.putString("payId", m.f().i());
        b bVar = new b();
        bVar.setMap(d(fragmentActivity, aVar));
        bundle.putSerializable("map", bVar);
        CashierNavigator.jumpBtCombinationPayPage(fragmentActivity, bundle);
    }

    public void f(FragmentActivity fragmentActivity, Map<String, String> map) {
        p.d(fragmentActivity, map);
    }

    public void g(Context context, com.jd.lib.cashier.sdk.h.c.a aVar) {
        if (aVar != null) {
            Bundle bundle = new Bundle();
            bundle.putString("appId", aVar.b);
            bundle.putString(PairKey.APP_KEY, aVar.f3510c);
            bundle.putString("back_url", aVar.f3514h);
            bundle.putString("orderId", aVar.f3511e);
            bundle.putString("orderType", aVar.f3515i);
            bundle.putString("payablePrice", aVar.f3516j);
            bundle.putString("orderTypeCode", aVar.f3517k);
            bundle.putString("fromActivity", "5");
            bundle.putString("payId", m.f().i());
            if (TextUtils.equals(aVar.U, "1")) {
                CashierNavigator.jumpToFriendPayDialogPage(context, bundle);
            } else {
                CashierNavigator.jumpToFriendPayPage(context, bundle);
            }
        }
    }

    public void h(FragmentActivity fragmentActivity, String str) {
        if (fragmentActivity != null) {
            try {
                if (fragmentActivity.isFinishing()) {
                    return;
                }
                p.h(fragmentActivity, str);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void i(Context context, String str) {
        IOrderRouter orderRouter;
        if (context != null) {
            try {
                if (TextUtils.isEmpty(str) || (orderRouter = DependInitializer.getOrderRouter()) == null) {
                    return;
                }
                orderRouter.routerToOrderDetailLayer(context, str);
            } catch (Exception e2) {
                r.d(a.class.getSimpleName(), e2.getMessage());
            }
        }
    }
}
