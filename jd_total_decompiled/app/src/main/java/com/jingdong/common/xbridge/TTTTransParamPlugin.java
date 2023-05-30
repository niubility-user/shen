package com.jingdong.common.xbridge;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import androidx.annotation.Nullable;
import com.jd.xbridge.base.IBridgeCallback;
import com.jd.xbridge.base.IBridgePlugin;
import com.jd.xbridge.base.IBridgeWebView;
import com.jingdong.common.web.ui.JDWebView;

/* loaded from: classes12.dex */
public class TTTTransParamPlugin implements IBridgePlugin {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(JDWebView jDWebView, IBridgeCallback iBridgeCallback) {
        jDWebView.hidePlaceHolder();
        if (iBridgeCallback != null) {
            iBridgeCallback.onSuccess("success !");
        }
    }

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
    public boolean execute(@Nullable IBridgeWebView iBridgeWebView, @Nullable String str, @Nullable String str2, @Nullable final IBridgeCallback iBridgeCallback) {
        if ("stopLoadingAnimating".equals(str)) {
            if (!(iBridgeWebView instanceof View)) {
                if (iBridgeCallback != null) {
                    iBridgeCallback.onError("fail !");
                    return true;
                }
                return true;
            }
            final JDWebView jDWebView = getJDWebView((View) iBridgeWebView);
            if (jDWebView != null) {
                if (!TextUtils.isEmpty(jDWebView.getLoadingPlaceHolder())) {
                    jDWebView.post(new Runnable() { // from class: com.jingdong.common.xbridge.f
                        @Override // java.lang.Runnable
                        public final void run() {
                            TTTTransParamPlugin.a(JDWebView.this, iBridgeCallback);
                        }
                    });
                    return true;
                } else if (iBridgeCallback != null) {
                    iBridgeCallback.onSuccess("success !");
                    return true;
                } else {
                    return true;
                }
            }
            return true;
        }
        return false;
    }
}
