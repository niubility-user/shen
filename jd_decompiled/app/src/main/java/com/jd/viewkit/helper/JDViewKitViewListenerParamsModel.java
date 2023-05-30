package com.jd.viewkit.helper;

import com.jd.viewkit.thirdinterface.model.JDViewKitEventModel;

/* loaded from: classes18.dex */
public class JDViewKitViewListenerParamsModel {
    public static String paramsModelTypeCountdown = "ountdown";
    public static String paramsModelTypeMultiTab = "multiTab";
    public static String paramsModelTypeRefreshFloor = "refreshFloor";
    public static String paramsModelTypeRefreshPage = "refreshPage";
    private String Type;
    private JDViewKitEventModel event;

    public JDViewKitViewListenerParamsModel(String str, JDViewKitEventModel jDViewKitEventModel) {
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
