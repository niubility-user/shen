package com.jd.lib.productdetail.core.entitys;

import java.util.List;

/* loaded from: classes15.dex */
public class PdKaEntity {
    public String bizType;
    public int broaden;
    public Event event;
    public String eventName;
    public String floorFlag;
    public List<WareAtom> params;
    public String popupTitle;
    public AuthenticationTip region1;
    public String region2Subtitle;
    public String region2Tick;
    public String region2Title;
    public String specBuryPoint;
    public String title;
    public AuthenticationTop topRegion;

    /* loaded from: classes15.dex */
    public static class AuthenticationInfo {
        public String stepDesc;
        public String stepPicture;
    }

    /* loaded from: classes15.dex */
    public static class AuthenticationTip {
        public List<AuthenticationInfo> content;
        public String title;
    }

    /* loaded from: classes15.dex */
    public static class AuthenticationTop {
        public String showPicture;
    }

    /* loaded from: classes15.dex */
    public static class Event {
        public String buttonSkip;
        public String icon;
        public String link;
        public int popup;
        public String popupButtonText;
        public String popupInfo;
        public String text;
        public int type;
    }

    /* loaded from: classes15.dex */
    public static class WareAtom {
        public String attDictUrl;
        public String attValueDefinition;
        public String name;
        public String shortValue;
        public String value;
    }

    public boolean isNeadShowKaLayer() {
        String str = this.floorFlag;
        return str != null && str.compareTo("electronicLocks") == 0;
    }
}
