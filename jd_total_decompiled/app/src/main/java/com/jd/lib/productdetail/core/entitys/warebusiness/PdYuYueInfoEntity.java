package com.jd.lib.productdetail.core.entitys.warebusiness;

import java.util.List;

/* loaded from: classes15.dex */
public class PdYuYueInfoEntity {
    public String arrowUrl;
    public PdYuYueInfoDivider dianDD;
    public PdYuYueInfoMember member;
    public PdYuYueInfoMemberJump memberUrl;
    public List<PdYuYueInfoStepEntity> stepInfos;

    /* loaded from: classes15.dex */
    public static class PdYuYueInfoDivider {
        public String imgUrl;
    }

    /* loaded from: classes15.dex */
    public static class PdYuYueInfoMember {
        public boolean isJDFont;
        public String text;
        public String textColor;
        public String textSize;
    }

    /* loaded from: classes15.dex */
    public static class PdYuYueInfoMemberJump {
        public String imgUrl;
        public String jumpType;
        public String jumpUrl;
    }

    /* loaded from: classes15.dex */
    public static class PdYuYueInfoStepEntity {
        public PdYuYueInfoStepLabel stepLabel;
        public PdYuYueInfoStepNo stepNo;
        public PdYuYueInfoStepTime stepTime;
    }

    /* loaded from: classes15.dex */
    public static class PdYuYueInfoStepLabel {
        public boolean isJDFont;
        public String text;
        public String textColor;
        public String textSize;
    }

    /* loaded from: classes15.dex */
    public static class PdYuYueInfoStepNo {
        public boolean isJDFont;
        public String text;
        public String textColor;
        public String textSize;
    }

    /* loaded from: classes15.dex */
    public static class PdYuYueInfoStepTime {
        public boolean isJDFont;
        public String text;
        public String textColor;
        public String textSize;
    }
}
