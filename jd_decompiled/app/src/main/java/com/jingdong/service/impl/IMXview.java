package com.jingdong.service.impl;

import android.app.Activity;
import android.app.FragmentManager;
import androidx.fragment.app.Fragment;
import com.jingdong.service.BaseService;
import com.jingdong.service.callback.WebPageFinishedListener;
import com.jingdong.service.callback.WebPageListener;
import com.jingdong.service.service.XVIewService;

/* loaded from: classes10.dex */
public class IMXview extends BaseService implements XVIewService {
    private static final String TAG = "IMXview";

    public Fragment getWebFragment(String str) {
        return null;
    }

    public void loadUrl(String str) {
    }

    public void setJavascriptEnable(boolean z) {
    }

    @Deprecated
    public void setOnPageFinishedListener(WebPageFinishedListener webPageFinishedListener) {
    }

    public void setOnWebPageListener(WebPageListener webPageListener) {
    }

    public void showDialog(FragmentManager fragmentManager, Activity activity, String str, String str2, boolean z) {
    }

    public boolean webViewGoBack() {
        return false;
    }
}
