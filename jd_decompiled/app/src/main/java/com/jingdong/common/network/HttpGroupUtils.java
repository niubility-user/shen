package com.jingdong.common.network;

import android.app.Activity;
import com.jingdong.common.network.HttpErrorAlertController;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpGroupSetting;

/* loaded from: classes.dex */
public class HttpGroupUtils {
    public static HttpGroupSetting createNewSettings() {
        HttpGroupSetting httpGroupSetting = new HttpGroupSetting(new HttpUiHelper());
        httpGroupSetting.setHttpErrorAlertControllerFactory(new HttpErrorAlertController.Factory());
        return httpGroupSetting;
    }

    public static HttpGroup getHttpGroupaAsynPool() {
        return getHttpGroupaAsynPool(1000);
    }

    public static HttpGroup getHttpGroupaAsynPool(int i2) {
        HttpGroupSetting createNewSettings = createNewSettings();
        createNewSettings.setType(i2);
        return getHttpGroupaAsynPool(createNewSettings);
    }

    public static HttpGroup getHttpGroupaAsynPool(int i2, Activity activity) {
        HttpGroupSetting createNewSettings = createNewSettings();
        createNewSettings.setType(i2);
        createNewSettings.setMyActivity(activity);
        return getHttpGroupaAsynPool(createNewSettings);
    }

    public static HttpGroup getHttpGroupaAsynPool(Activity activity) {
        return getHttpGroupaAsynPool(1000, activity);
    }

    public static HttpGroup getHttpGroupaAsynPool(HttpGroupSetting httpGroupSetting) {
        return HttpGroup.getHttpGroup(httpGroupSetting);
    }
}
