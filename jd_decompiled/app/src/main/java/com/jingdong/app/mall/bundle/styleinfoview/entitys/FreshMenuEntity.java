package com.jingdong.app.mall.bundle.styleinfoview.entitys;

import java.util.List;

/* loaded from: classes3.dex */
public class FreshMenuEntity {
    public boolean isDark;
    public List<Item> menu;
    public String title;

    /* loaded from: classes3.dex */
    public class Item {
        public String bannerImg;
        public String bannerText;
        public String img;
        public boolean isSelected = true;
        public String jumpType;
        public String jumpUrl;
        public String likeIcon;
        public String likeIconPopup;
        public String likeText;
        public String pageViewIcon;
        public String pageViewIconPopup;
        public String pageViewText;
        public String popup;
        public PopupParam popupParam;

        /* loaded from: classes3.dex */
        public class PopupParam {
            public String comefrom;
            public String skus;

            public PopupParam() {
            }
        }

        public Item() {
        }
    }
}
