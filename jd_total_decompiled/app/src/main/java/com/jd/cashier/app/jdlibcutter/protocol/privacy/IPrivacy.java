package com.jd.cashier.app.jdlibcutter.protocol.privacy;

import android.content.Context;
import android.util.DisplayMetrics;

/* loaded from: classes13.dex */
public interface IPrivacy {
    DisplayMetrics getDisplayMetrics();

    String getPackageName();

    boolean isNFCAvailable(Context context);

    boolean isPackageInstalled(Context context, String str);

    boolean isProcessForeground();
}
