package com.jd.lib.babel.servicekit.iservice;

import android.app.Activity;
import android.content.Context;

/* loaded from: classes13.dex */
public interface IDpi {
    int getAppHeight(Activity activity);

    int getAppWidth(Activity activity);

    int getHeight(Context context);

    int getWidth(Context context);
}
