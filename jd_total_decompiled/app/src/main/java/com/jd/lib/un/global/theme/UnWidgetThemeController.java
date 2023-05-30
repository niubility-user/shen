package com.jd.lib.un.global.theme;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import com.jd.lib.un.global.a;
import com.jingdong.common.DpiUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class UnWidgetThemeController {
    public static final boolean DEFAULT_AUTO_DARK = false;
    private static UnWidgetThemeController controller;
    private int appHeight;
    private List<OnConfigurationChangedListener> configurationChangedListeners;
    private a iOptionConfig;
    private OnThemeConfig onThemeConfig;

    /* loaded from: classes16.dex */
    public interface OnConfigurationChangedListener {
        void onChanged();
    }

    private UnWidgetThemeController() {
    }

    public static UnWidgetThemeController getInstance() {
        UnWidgetThemeController unWidgetThemeController;
        UnWidgetThemeController unWidgetThemeController2 = controller;
        if (unWidgetThemeController2 != null) {
            return unWidgetThemeController2;
        }
        synchronized (UnWidgetThemeController.class) {
            if (controller == null) {
                controller = new UnWidgetThemeController();
            }
            unWidgetThemeController = controller;
        }
        return unWidgetThemeController;
    }

    public synchronized UnWidgetThemeController addOnConfigurationChangedListener(OnConfigurationChangedListener onConfigurationChangedListener) {
        if (onConfigurationChangedListener == null) {
            return this;
        }
        List<OnConfigurationChangedListener> list = this.configurationChangedListeners;
        if (list == null) {
            ArrayList arrayList = new ArrayList();
            this.configurationChangedListeners = arrayList;
            arrayList.add(onConfigurationChangedListener);
            return this;
        } else if (list.contains(onConfigurationChangedListener)) {
            return this;
        } else {
            this.configurationChangedListeners.add(onConfigurationChangedListener);
            return this;
        }
    }

    public int getAppHeight() {
        if (this.appHeight == 0) {
            this.appHeight = DpiUtil.getHeight();
        }
        return this.appHeight;
    }

    public Typeface getTypeface(Context context, int i2) {
        a aVar = this.iOptionConfig;
        if (aVar != null) {
            return aVar.getTypeface(context, i2);
        }
        return null;
    }

    public boolean isDarkMode() {
        OnThemeConfig onThemeConfig;
        if (isElderModel() || (onThemeConfig = this.onThemeConfig) == null) {
            return false;
        }
        return onThemeConfig.isDarkModel();
    }

    public boolean isDialogAutoElder() {
        a aVar = this.iOptionConfig;
        if (aVar != null) {
            return aVar.isDialogAutoElder();
        }
        return true;
    }

    public boolean isElderModel() {
        OnThemeConfig onThemeConfig = this.onThemeConfig;
        if (onThemeConfig == null) {
            return false;
        }
        return onThemeConfig.isElderModel();
    }

    public boolean isVoiceEnable() {
        return false;
    }

    public void jdRouter(String str) {
        a aVar = this.iOptionConfig;
        if (aVar != null) {
            aVar.jdRouter(str);
        }
    }

    public void onClickWithPageId(Context context, String str, String str2, String str3, String str4) {
        a aVar = this.iOptionConfig;
        if (aVar != null) {
            aVar.onClickWithPageId(context, str, str2, str3, str4);
        }
    }

    public void onConfigurationChanged(Activity activity) {
        String str = "appHeight:" + getAppHeight();
        if (getAppHeight() == DpiUtil.getAppHeight(activity)) {
            return;
        }
        setAppHeight(DpiUtil.getAppHeight(activity));
        List<OnConfigurationChangedListener> list = this.configurationChangedListeners;
        if (list != null) {
            Iterator<OnConfigurationChangedListener> it = list.iterator();
            while (it.hasNext()) {
                it.next().onChanged();
            }
        }
    }

    public Bitmap qrCode(String str) {
        a aVar = this.iOptionConfig;
        if (aVar != null) {
            return aVar.qrCode(str);
        }
        return null;
    }

    public synchronized UnWidgetThemeController removeOnConfigurationChangedListener(OnConfigurationChangedListener onConfigurationChangedListener) {
        if (onConfigurationChangedListener != null) {
            List<OnConfigurationChangedListener> list = this.configurationChangedListeners;
            if (list != null && list.contains(onConfigurationChangedListener)) {
                this.configurationChangedListeners.remove(onConfigurationChangedListener);
            }
        }
        return this;
    }

    public void sendExposureDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        a aVar = this.iOptionConfig;
        if (aVar != null) {
            aVar.sendExposureDataWithExt(context, str, str2, str3, str4, str5, str6);
        }
    }

    public void setAppHeight(int i2) {
        if (i2 > 0) {
            this.appHeight = i2;
        }
    }

    public UnWidgetThemeController setIOptionConfig(a aVar) {
        this.iOptionConfig = aVar;
        return this;
    }

    public UnWidgetThemeController setOnThemeConfig(OnThemeConfig onThemeConfig) {
        this.onThemeConfig = onThemeConfig;
        return this;
    }
}
