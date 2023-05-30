package com.jd.lib.productdetail.core.entitys;

import java.util.List;

/* loaded from: classes15.dex */
public class PdWhiteBarNewUser {
    public String JumpLayerTipsImg;
    public Step activationStep;
    public String activityRule;
    public String activityRuleIcon;
    public String activityRuleUrl;
    public String buttonJumpUrl;
    public String buttonText;
    public String guideTitleTip;
    public String mainTitle;
    public boolean newWbFlag;
    public Preferential preferential;
    public Right right;
    public String subTitle;
    public String title;
    public String topBg;

    /* loaded from: classes15.dex */
    public static class Preferential {
        public boolean canClick;
        public String detailTitle;
        public List<Detail> details;

        /* loaded from: classes15.dex */
        public static class Detail {
            public String detailTitle;
            public String mainInfo;
            public String subCouponInfo;
            public String subInfo;
        }
    }

    /* loaded from: classes15.dex */
    public static class Right {
        public String detailTitle;
        public List<Detail> details;
        public String rightIcon;
        public List<Content> rights;

        /* loaded from: classes15.dex */
        public static class Content {
            public String rightContent;
        }

        /* loaded from: classes15.dex */
        public static class Detail {
            public String mainInfo;
            public String subInfo;
        }
    }

    /* loaded from: classes15.dex */
    public static class Step {
        public String connectIcon;
        public String stepHeadContent;
        public List<Detail> steps;

        /* loaded from: classes15.dex */
        public static class Detail {
            public String stepContent;
            public String stepNumber;
        }
    }
}
