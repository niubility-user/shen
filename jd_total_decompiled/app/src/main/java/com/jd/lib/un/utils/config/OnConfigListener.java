package com.jd.lib.un.utils.config;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;

/* loaded from: classes.dex */
public interface OnConfigListener {
    Application getApplication();

    Configuration getConfiguration(Activity activity);
}
