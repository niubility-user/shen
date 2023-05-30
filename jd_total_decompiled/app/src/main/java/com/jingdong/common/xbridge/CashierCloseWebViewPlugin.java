package com.jingdong.common.xbridge;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.jd.xbridge.base.IBridgeCallback;
import com.jd.xbridge.base.IBridgePlugin;
import com.jd.xbridge.base.IBridgeWebView;
import com.jingdong.common.utils.WebViewHelper;

@Keep
/* loaded from: classes12.dex */
public class CashierCloseWebViewPlugin implements IBridgePlugin {
    private void openAndDestroyView(final String str, final Activity activity) {
        if (activity != null) {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                if (!TextUtils.isEmpty(str)) {
                    WebViewHelper.isJdPayMatch(activity, str);
                }
                activity.finish();
                return;
            }
            activity.runOnUiThread(new Runnable() { // from class: com.jingdong.common.xbridge.CashierCloseWebViewPlugin.1
                @Override // java.lang.Runnable
                public void run() {
                    if (!TextUtils.isEmpty(str)) {
                        WebViewHelper.isJdPayMatch(activity, str);
                    }
                    activity.finish();
                }
            });
        }
    }

    @Override // com.jd.xbridge.base.IBridgePlugin
    public boolean execute(@Nullable IBridgeWebView iBridgeWebView, @Nullable String str, @Nullable String str2, @Nullable IBridgeCallback iBridgeCallback) {
        if ("openCashierAndCloseWebview".equals(str)) {
            Context context = (iBridgeWebView == null || iBridgeWebView.getView() == null) ? null : iBridgeWebView.getView().getContext();
            Activity activity = context instanceof Activity ? (Activity) context : null;
            if (activity != null) {
                openAndDestroyView(str2, activity);
                return true;
            }
            return true;
        }
        return false;
    }
}
