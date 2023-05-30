package com.jd.viewkit.helper;

import com.jd.viewkit.thirdinterface.model.JDViewKitEventModel;

/* loaded from: classes18.dex */
public class JDViewKitPageRefreshModel {
    public static String pageRefreshTypeCountdown = "ountdown";
    public static String pageRefreshTypeSelectTab = "selectTab";
    public static String pageRefreshTypeUpdateData = "updateData";
    private String Type;
    private JDViewKitEventModel event;

    public JDViewKitPageRefreshModel(String str, JDViewKitEventModel jDViewKitEventModel) {
        this.Type = str;
        this.event = jDViewKitEventModel;
    }

    public JDViewKitEventModel getEvent() {
        return this.event;
    }

    public String getType() {
        return this.Type;
    }
}
