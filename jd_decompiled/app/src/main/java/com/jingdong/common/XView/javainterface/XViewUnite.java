package com.jingdong.common.XView.javainterface;

import android.webkit.JavascriptInterface;
import com.jingdong.common.XView.XView;
import com.jingdong.common.web.javainterface.impl.JDAppUnite;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes5.dex */
public class XViewUnite extends JDAppUnite {
    private final String TAG;

    public XViewUnite(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.TAG = XViewUnite.class.getSimpleName();
    }

    @JavascriptInterface
    public void getOpenDoorVideoPlayState(String str) {
        Log.d(this.TAG, "getOpenDoorVideoPlayState:" + str);
        if (this.webUiBinder.getUi() instanceof XView) {
            ((XView) this.webUiBinder.getUi()).getOpenDoorVideoPlayerState(str);
        }
    }

    @JavascriptInterface
    public void sourceIsReady(String str) {
        Log.d(this.TAG, "sourceIsReady:" + str);
        if (this.webUiBinder.getUi() instanceof XView) {
            ((XView) this.webUiBinder.getUi()).sourceIsReady(str);
        }
    }
}
