package com.jd.lib.productdetail.core.entitys.warebusiness;

import com.jd.lib.productdetail.core.entitys.GjhsActivityComponent;
import java.util.List;

/* loaded from: classes15.dex */
public class PdSimpleActivityEntity {
    public GjhsActivityComponent activityComponent;
    public List<ActivityProList> activityProList;
    public String backgroundImage;
    public String buttonTxt;
    public String closeIcon;
    public String colorRange;
    public String popUpIcon;
    public String popUpSubTitle;

    /* loaded from: classes15.dex */
    public static class ActivityProList {
        public ActivityComponent activityComponent;
        public String activityName;
        public String activityNameColor;
        public String bigSale;
        public String bizKey;
        public String cornerTag;
        public String desc;
        public String descColor;
        public String iconType;
        public String jumpArrow;
        public int jumpType;
        public String jumpUrl;
        public String layoutType;
        public String mainIcon;
        public boolean mustLogin;
        public String oldIcon;
        public int sortNum;

        /* loaded from: classes15.dex */
        public static class ActivityComponent {
            public String highlightColor;
            public String highlightTxt;
            public String icon;
            public String subTitle;
            public String subTitleColor;
            public String title;
        }
    }
}
