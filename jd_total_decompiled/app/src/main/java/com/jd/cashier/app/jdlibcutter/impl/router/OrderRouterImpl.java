package com.jd.cashier.app.jdlibcutter.impl.router;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairValue;
import com.jd.cashier.app.jdlibcutter.protocol.router.IOrderRouter;
import com.jingdong.common.deeplinkhelper.DeepLinkOrderCenterHelper;

/* loaded from: classes13.dex */
public class OrderRouterImpl implements IOrderRouter {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.router.IOrderRouter
    public void routerToOrderDetail(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        DeepLinkOrderCenterHelper.startOrderDetail(context, str, PairValue.ORDER_SOURCE_FROM);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.router.IOrderRouter
    public void routerToOrderDetailLayer(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("orderId", str);
        bundle.putString("from", PairValue.ORDER_SOURCE_FROM);
        bundle.putString(PairKey.ORDER_SOURCE, "pay");
        DeepLinkOrderCenterHelper.startOrderDetailCardLayer(context, bundle);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.router.IOrderRouter
    public void routerToOrderList(Context context) {
        if (context != null) {
            DeepLinkOrderCenterHelper.startOrderList(context, PairValue.ORDER_SOURCE_FROM);
        }
    }
}
