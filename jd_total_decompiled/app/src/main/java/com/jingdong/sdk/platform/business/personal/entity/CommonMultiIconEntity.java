package com.jingdong.sdk.platform.business.personal.entity;

import com.jd.framework.json.anotation.JSONField;
import java.util.List;

/* loaded from: classes.dex */
public class CommonMultiIconEntity {
    @JSONField(name = "commonInfo")
    public CommonInfo commonInfo;
    @JSONField(name = "bizParts")
    public List<IconElement> iconElementList;

    /* loaded from: classes.dex */
    public static class CommonInfo extends BaseCommonInfoEntity {
        @JSONField(name = "backGround")
        public Background background;
        public int isPage;
        public int itemPerRow;
        @JSONField(name = "maiDian")
        public MtaEntity mtaEntity;
        public String name;
        public int rows;

        /* loaded from: classes10.dex */
        public static class Background {
            public String type;
            public String value;
        }

        /* loaded from: classes10.dex */
        public static class MtaEntity {
            public String eventId;
            public String eventParam;
        }
    }

    /* loaded from: classes10.dex */
    public static class IconElement {
        public String bizId;
        public String contentType;
        public String iconContent;
        public int jumpType;
        public String lottieContent;
        public String mUrl;
        public MaiDian maiDian;
        public int needLogin;
        public int playTimes;
        public String safeImage;
        public SubTitle subTitle;
        public Title title;
        public UpdateNotice updateNotice;
        public long updateTimeStamp;

        /* loaded from: classes10.dex */
        public static class BaseTitle {
            public String color;
            public String fontSize;
            public String value;
        }

        /* loaded from: classes10.dex */
        public static class MaiDian {
            public String eventId;
            public String eventParam;
            public String pageLevel;
        }

        /* loaded from: classes10.dex */
        public static class SubTitle extends BaseTitle {
        }

        /* loaded from: classes10.dex */
        public static class Title extends BaseTitle {
        }

        /* loaded from: classes10.dex */
        public static class UpdateNotice {
            public String noticeType;
        }
    }
}
