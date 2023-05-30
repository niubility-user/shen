package com.jd.cashier.app.jdlibcutter.protocol.router;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/* loaded from: classes13.dex */
public interface ICashierRouter {
    void routerToHybridCashier(Activity activity, String str);

    void routerToPluginFinishPage(Activity activity, Bundle bundle, int i2);

    void routerToPluginFinishPage(Context context, Bundle bundle);
}
