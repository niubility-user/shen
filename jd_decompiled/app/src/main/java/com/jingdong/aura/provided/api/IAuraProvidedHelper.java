package com.jingdong.aura.provided.api;

import android.app.Activity;
import android.content.SharedPreferences;
import androidx.fragment.app.Fragment;
import com.jingdong.aura.serviceloder.IServiceProvider;

/* loaded from: classes4.dex */
public interface IAuraProvidedHelper extends IServiceProvider {
    int dip2px(float f2);

    void downloadProvidedBundle(String str, IBundleDownloadListener iBundleDownloadListener);

    SharedPreferences getAuraSharedPreferences();

    long getBundleSize(String str);

    SharedPreferences getSharedPreferences();

    String getUpdateIdFromBundleName(String str);

    boolean isNetworkAvailable();

    boolean isWifi();

    Fragment newFragment(Activity activity, String str);

    void stopDownloadBundle(String str);

    void uploadCrash(String str, int i2, String str2, String str3, Throwable th);
}
