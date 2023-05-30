package com.jd.cashier.app.jdlibcutter.impl.router;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jd.cashier.app.jdlibcutter.protocol.router.ICashierRouter;
import com.jingdong.common.deeplinkhelper.DeepLinkCashierHelper;

/* loaded from: classes13.dex */
public class CashierRouterImpl implements ICashierRouter {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.router.ICashierRouter
    public void routerToHybridCashier(Activity activity, String str) {
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.router.ICashierRouter
    public void routerToPluginFinishPage(Context context, Bundle bundle) {
        if (context == null || bundle == null) {
            return;
        }
        DeepLinkCashierHelper.startCashierComplete(context, bundle);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.router.ICashierRouter
    public void routerToPluginFinishPage(Activity activity, Bundle bundle, int i2) {
        if (activity == null || activity.isFinishing() || bundle == null) {
            return;
        }
        DeepLinkCashierHelper.startCashierComplete(activity, bundle, i2);
    }
}
