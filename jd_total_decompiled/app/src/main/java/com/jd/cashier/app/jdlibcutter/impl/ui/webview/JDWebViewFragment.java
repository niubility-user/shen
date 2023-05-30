package com.jd.cashier.app.jdlibcutter.impl.ui.webview;

import android.content.Context;
import android.text.TextUtils;
import android.view.KeyEvent;
import androidx.fragment.app.Fragment;
import com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebViewFragment;
import com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebViewInterceptor;
import com.jingdong.common.web.ui.CommonMFragment;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.uilistener.impl.WebViewUrlInterceptorImpl;

/* loaded from: classes13.dex */
public class JDWebViewFragment implements IWebViewFragment {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebViewFragment
    public void addInterceptor(Fragment fragment, IWebViewInterceptor iWebViewInterceptor) {
        try {
            if (!(fragment instanceof CommonMFragment) || iWebViewInterceptor == null) {
                return;
            }
            JDWebViewInterceptor jDWebViewInterceptor = new JDWebViewInterceptor(iWebViewInterceptor);
            WebViewUrlInterceptorImpl webViewUrlInterceptorImpl = new WebViewUrlInterceptorImpl(null);
            webViewUrlInterceptorImpl.addUrlShouldOverrideLoading(jDWebViewInterceptor);
            JDWebView jdWebView = ((CommonMFragment) fragment).getJdWebView();
            if (jdWebView != null) {
                jdWebView.setWebViewInterceptUrlListener(webViewUrlInterceptorImpl);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebViewFragment
    public void addJavaScript(Fragment fragment, Object obj, String str) {
        JDWebView jdWebView;
        if (!(fragment instanceof CommonMFragment) || obj == null || TextUtils.isEmpty(str) || (jdWebView = ((CommonMFragment) fragment).getJdWebView()) == null) {
            return;
        }
        jdWebView.addJavascriptInterface(obj, str);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebViewFragment
    public Fragment getCommonWebFragment(Context context) {
        return new CommonMFragment();
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebViewFragment
    public void onDestroy(Fragment fragment) {
        if (fragment instanceof CommonMFragment) {
            ((CommonMFragment) fragment).onDestroy();
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebViewFragment
    public boolean onKeyDown(Fragment fragment, int i2, KeyEvent keyEvent) {
        return (fragment instanceof CommonMFragment) && ((CommonMFragment) fragment).onKeyDown(i2, keyEvent);
    }
}
