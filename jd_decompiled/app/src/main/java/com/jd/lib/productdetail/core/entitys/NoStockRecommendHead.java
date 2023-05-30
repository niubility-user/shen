package com.jd.lib.productdetail.core.entitys;

import java.util.List;

/* loaded from: classes15.dex */
public class NoStockRecommendHead {
    public static final String DEVICE = "device";
    public static final String PIN = "pin";
    public String addrIcon;
    public String arrowIcon;
    public List<PdBybtOrder> bybtOrderList;
    public String bybtPopupTips;
    public String entityId;
    public String keyword;
    public String leftIcon;
    public List<PopupTab> popupTab;
    public String rightIcon;
    public String roleType;
    public boolean searchRecommendFlag;
    public String stockState;
    public String tabBottom;
    public String tabTitle;
    public ReCommendWareInfo wareInfo;

    /* loaded from: classes15.dex */
    public static class PdBybtOrder {
        public String imageurl;
        public String numText;
        public String orderTime;
        public String wname;
    }

    /* loaded from: classes15.dex */
    public static class PopupTab {
        public String text;
        public int type;
    }

    /* loaded from: classes15.dex */
    public static class ReCommendWareInfo {
        public String mainPic;
        public String price;
        public String title;
    }
}
