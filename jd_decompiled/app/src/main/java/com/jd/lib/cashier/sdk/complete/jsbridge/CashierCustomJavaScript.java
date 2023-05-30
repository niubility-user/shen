package com.jd.lib.cashier.sdk.complete.jsbridge;

import android.webkit.JavascriptInterface;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.parser.IJsonParser;
import com.jd.lib.cashier.sdk.c.a.a;
import com.jd.lib.cashier.sdk.complete.entity.CashierCustomMessage;
import com.jd.lib.cashier.sdk.complete.view.CashierCompleteActivity;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.r;

/* loaded from: classes14.dex */
public class CashierCustomJavaScript {
    public static final String BRIDGE_NAME = "JDAppCashier";
    private static final long SHORT_TIME_VALUE = 1200;
    private static final String TAG = "CashierCustomJavaScript";
    private a mCashierActionDispatcher = new a();
    private CashierCompleteActivity mHostController;
    private long mLastPostMsgTime;

    public CashierCustomJavaScript(CashierCompleteActivity cashierCompleteActivity) {
        this.mHostController = cashierCompleteActivity;
    }

    private boolean isPostMultiMsgInShortTime() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.mLastPostMsgTime <= SHORT_TIME_VALUE) {
            return true;
        }
        this.mLastPostMsgTime = currentTimeMillis;
        return false;
    }

    public void onDestroy() {
        if (this.mHostController != null) {
            this.mHostController = null;
        }
        if (this.mCashierActionDispatcher != null) {
            this.mCashierActionDispatcher = null;
        }
    }

    @JavascriptInterface
    public void postMsgToCashier(String str) {
        r.b(TAG, "postMsgToCashier message = " + str);
        if (g0.a(this.mHostController) && !isPostMultiMsgInShortTime()) {
            try {
                IJsonParser jsonParser = DependInitializer.getJsonParser();
                CashierCustomMessage cashierCustomMessage = jsonParser != null ? (CashierCustomMessage) jsonParser.parseJsonToObject(str, CashierCustomMessage.class) : null;
                r.b(TAG, "postMsgToCashier msgEntity = " + cashierCustomMessage);
                a aVar = this.mCashierActionDispatcher;
                if (aVar == null || cashierCustomMessage == null) {
                    return;
                }
                aVar.a(this.mHostController, cashierCustomMessage);
            } catch (Exception unused) {
                r.b(TAG, "postMsgToCashier error");
            }
        }
    }
}
