package com.jingdong.sdk.platform.business.personal.entity;

import com.jd.framework.json.anotation.JSONField;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class CommonBannerEntity {
    @JSONField(name = "banners")
    public ArrayList<Banner> banners;
    @JSONField(name = "commonInfo")
    public CommonInfo commonInfo;

    /* loaded from: classes10.dex */
    public static class Banner {
        public String eventId;
        public String eventLevel;
        public String eventParam;
        public int jumpType;
        public String jumpUrl;
        public String lableImage;
        public String lableName;
        public int needLogin;
        public String orderGrade;
        public String pageId;
        public String pageParam;
    }

    /* loaded from: classes10.dex */
    public static class CommonInfo extends BaseCommonInfoEntity {
        public int alignStyle;
        public int height;
        public String lableName;
    }
}
