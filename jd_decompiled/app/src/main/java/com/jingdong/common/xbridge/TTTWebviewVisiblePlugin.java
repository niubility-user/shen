package com.jingdong.common.xbridge;

import android.view.View;
import android.view.ViewParent;
import com.jd.xbridge.base.IBridgeCallback;
import com.jd.xbridge.base.IBridgePlugin;
import com.jd.xbridge.base.IBridgeWebView;
import com.jingdong.common.web.ui.JDWebView;

/* loaded from: classes12.dex */
public class TTTWebviewVisiblePlugin implements IBridgePlugin {
    private JDWebView getJDWebView(View view) {
        ViewParent parent = view.getParent();
        if (parent instanceof View) {
            if (parent instanceof JDWebView) {
                return (JDWebView) parent;
            }
            return getJDWebView((View) parent);
        }
        return null;
    }

    @Override // com.jd.xbridge.base.IBridgePlugin
    public boolean execute(IBridgeWebView iBridgeWebView, String str, String str2, IBridgeCallback iBridgeCallback) {
        if ("getWebviewVisible".equals(str)) {
            iBridgeCallback.onSuccess(getJDWebView((View) iBridgeWebView).getVisibleStatus() ? "1" : "0");
            return true;
        }
        return false;
    }
}
