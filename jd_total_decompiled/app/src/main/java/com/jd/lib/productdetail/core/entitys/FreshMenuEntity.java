package com.jd.lib.productdetail.core.entitys;

import java.util.List;

/* loaded from: classes15.dex */
public class FreshMenuEntity {
    public boolean isDark;
    public List<Item> menu;
    public String title;

    /* loaded from: classes15.dex */
    public static class Item {
        public String bannerImg;
        public String bannerText;
        public String contentid;
        public String img;
        public String imgNew;
        public String jumpType;
        public String jumpUrl;
        public String likeIcon;
        public String likeIconPopup;
        public String likeText;
        public String matchTopTitle;
        public String pageViewIcon;
        public String pageViewIconPopup;
        public String pageViewText;
        public String popup;
        public PopupParam popupParam;
        public String videoImg;
        public boolean isSelected = true;
        public boolean isComeFromMatchShop = false;

        /* loaded from: classes15.dex */
        public static class PopupParam {
            public String comefrom;
            public String skus;
        }
    }
}
