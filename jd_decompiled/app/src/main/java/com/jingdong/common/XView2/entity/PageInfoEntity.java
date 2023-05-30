package com.jingdong.common.XView2.entity;

import java.util.ArrayList;

/* loaded from: classes5.dex */
public class PageInfoEntity {
    public ArrayList<PageBasicInfoEntity> pages = new ArrayList<>();

    /* loaded from: classes5.dex */
    public static class PageBasicInfoEntity {
        public int dayTimes;
        public long lastRealTime;
        public String layerId;
        public int showedTimes;
        public int times;
    }
}
