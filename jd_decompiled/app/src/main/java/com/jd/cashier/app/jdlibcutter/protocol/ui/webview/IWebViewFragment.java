package com.jd.cashier.app.jdlibcutter.protocol.ui.webview;

import android.content.Context;
import android.view.KeyEvent;
import androidx.fragment.app.Fragment;

/* loaded from: classes13.dex */
public interface IWebViewFragment {
    void addInterceptor(Fragment fragment, IWebViewInterceptor iWebViewInterceptor);

    void addJavaScript(Fragment fragment, Object obj, String str);

    Fragment getCommonWebFragment(Context context);

    void onDestroy(Fragment fragment);

    boolean onKeyDown(Fragment fragment, int i2, KeyEvent keyEvent);
}
