package com.jingdong.manto.sdk.api;

import android.content.Context;
import com.jingdong.manto.sdk.IMantoSdkBase;
import java.util.ArrayList;

/* loaded from: classes16.dex */
public interface ICustomMenuInterface extends IMantoSdkBase {

    /* loaded from: classes16.dex */
    public static class CustomData {
        public String appId;
        public String apptype;
        public String jsonStr;
        public String name;
    }

    /* loaded from: classes16.dex */
    public static class CustomMenuData {
        public int iconResId;
        public int id;
        public String name;
        public boolean task = false;
        public boolean visible = true;
    }

    boolean disableAbout();

    boolean disableAboutShare();

    boolean disableDebugSwitch();

    boolean disableFeedBack();

    boolean disablePerformanceSwitch();

    boolean disableShare();

    boolean disableShortCut();

    boolean disableToggleFavor();

    ArrayList<CustomMenuData> getCustomMenus(Context context);

    void onMenuClicked(Context context, CustomData customData, int i2, String str);
}
