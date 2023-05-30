package com.jingdong.common.web.javainterface.impl;

import android.webkit.JavascriptInterface;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.util.WebUnifiedMtaUtil;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes12.dex */
public final class BindGiftcard extends BaseWebComponent implements IJavaInterface {
    private final String TAG;

    public BindGiftcard(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.TAG = BindGiftcard.class.getSimpleName();
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return WebUiConstans.JavaInterfaceNames.BIND_GIFTCARD;
    }

    @JavascriptInterface
    public void onBindFinish(boolean z) {
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "BindGiftcard-onBindFinish");
        if (Log.D) {
            Log.d(this.TAG, " onBindFinish -->> isBindSuccess : " + z);
        }
        this.webUiBinder.finishUi();
    }

    public BindGiftcard() {
        this.TAG = BindGiftcard.class.getSimpleName();
    }
}
