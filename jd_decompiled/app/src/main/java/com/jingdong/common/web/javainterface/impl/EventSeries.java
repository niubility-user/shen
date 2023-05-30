package com.jingdong.common.web.javainterface.impl;

import android.webkit.JavascriptInterface;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.xrender.XRender;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;

/* loaded from: classes12.dex */
public final class EventSeries extends BaseWebComponent implements IJavaInterface {
    private final String TAG;

    public EventSeries(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.TAG = EventSeries.class.getSimpleName();
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return WebUiConstans.JavaInterfaceNames.ANDROID_PING;
    }

    @JavascriptInterface
    public void setSeries(String str) {
        Log.d(this.TAG, "setSeries:" + str);
        JDMtaUtils.setMtaContent(XRender.getInstance().isPreRender() ? JdSdk.getInstance().getApplication() : this.webUiBinder.getBaseActivity(), str);
    }

    @JavascriptInterface
    public void setSeriesUnion(String str) {
        Log.d(this.TAG, "setSeriesUnion:" + str);
        JDMtaUtils.setMtaContentUnion(str);
    }

    public EventSeries() {
        this.TAG = EventSeries.class.getSimpleName();
    }
}
