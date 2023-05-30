package com.jd.lib.un.utils.config;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

/* loaded from: classes.dex */
public class UnUtilsConfig {
    private static UnUtilsConfig config;
    private OnConfigListener configListener;
    private OnDeviceInfo onDeviceInfo;

    private UnUtilsConfig() {
    }

    public static UnUtilsConfig getInstance() {
        UnUtilsConfig unUtilsConfig;
        UnUtilsConfig unUtilsConfig2 = config;
        if (unUtilsConfig2 != null) {
            return unUtilsConfig2;
        }
        synchronized (UnUtilsConfig.class) {
            if (config == null) {
                config = new UnUtilsConfig();
            }
            unUtilsConfig = config;
        }
        return unUtilsConfig;
    }

    public Application getApplication() {
        OnConfigListener onConfigListener = this.configListener;
        if (onConfigListener != null) {
            return onConfigListener.getApplication();
        }
        return null;
    }

    public Configuration getConfiguration(Context context) {
        OnConfigListener onConfigListener = this.configListener;
        Configuration configuration = (onConfigListener == null || !(context instanceof Activity)) ? null : onConfigListener.getConfiguration((Activity) context);
        return (configuration != null || context == null) ? configuration : context.getResources().getConfiguration();
    }

    public OnDeviceInfo getOnDeviceInfo() {
        return this.onDeviceInfo;
    }

    public void init(OnConfigListener onConfigListener) {
        this.configListener = onConfigListener;
    }

    public void setOnDeviceInfo(OnDeviceInfo onDeviceInfo) {
        this.onDeviceInfo = onDeviceInfo;
    }
}
