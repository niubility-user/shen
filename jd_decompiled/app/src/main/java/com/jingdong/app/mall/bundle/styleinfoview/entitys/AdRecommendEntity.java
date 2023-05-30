package com.jingdong.app.mall.bundle.styleinfoview.entitys;

import java.util.ArrayList;

/* loaded from: classes3.dex */
public class AdRecommendEntity {
    public String titleText;
    public ArrayList<Entity> wareInfoList;

    /* loaded from: classes3.dex */
    public class Entity {
        public String clickUrl;
        public String client_click_url;
        public String client_exposal_url;
        public String exposureSourceValue;
        public String imageurl;
        public boolean isExposure = false;
        public int itemType;
        public String jdPrice;
        public String sid;
        public String sourceValue;
        public String wareId;
        public String wname;

        public Entity() {
        }
    }
}
