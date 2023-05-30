package com.jingdong.service.service;

import android.app.Activity;
import android.app.FragmentManager;
import androidx.fragment.app.Fragment;
import com.jingdong.service.callback.WebPageFinishedListener;
import com.jingdong.service.callback.WebPageListener;

/* loaded from: classes10.dex */
public interface XVIewService {
    Fragment getWebFragment(String str);

    void loadUrl(String str);

    void setJavascriptEnable(boolean z);

    @Deprecated
    void setOnPageFinishedListener(WebPageFinishedListener webPageFinishedListener);

    void setOnWebPageListener(WebPageListener webPageListener);

    void showDialog(FragmentManager fragmentManager, Activity activity, String str, String str2, boolean z);

    boolean webViewGoBack();
}
