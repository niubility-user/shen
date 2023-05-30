package com.jd.lib.cashier.sdk.complete.jsbridge;

import android.content.Context;
import android.webkit.JavascriptInterface;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.mta.IMta;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.d.d.a;

/* loaded from: classes14.dex */
public class PayEventSeriesScript implements a {
    public static String ANDROID_PING = "AndriodPing";
    private static final String TAG = "PayEventSeriesScript";
    private Context mContext;

    public PayEventSeriesScript(Context context) {
        this.mContext = context;
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.mContext != null) {
            this.mContext = null;
        }
    }

    @JavascriptInterface
    public void setSeries(String str) {
        r.f(TAG, "setSeries.json -->>" + str);
        IMta mta = DependInitializer.getMta();
        if (mta != null) {
            mta.setMtaContent(this.mContext, str);
        }
    }

    @JavascriptInterface
    public void setSeriesUnion(String str) {
        r.f(TAG, "setSeriesUnion.json -->>" + str);
        IMta mta = DependInitializer.getMta();
        if (mta != null) {
            mta.setMtaContentUnion(str);
        }
    }
}
