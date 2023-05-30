package com.jd.lib.productdetail.core.entitys.warebusiness;

import java.util.List;

/* loaded from: classes15.dex */
public class PdSimpleBannerEntity {
    public Background background;
    public BannerLeft bannerLeft;
    public BannerRight bannerRight;
    public int bannerType;
    public String beltTracking;
    public String businessName;

    /* loaded from: classes15.dex */
    public static class Background {
        public String backGroundUrl;
    }

    /* loaded from: classes15.dex */
    public static class BannerLeft {
        public Image image;
        public String popContent;
        public TopText topText;
    }

    /* loaded from: classes15.dex */
    public static class BannerRight {
        public BottomContent bottomContent;
        public Image image;
        public String popContent;
        public TopText topText;

        /* loaded from: classes15.dex */
        public static class BottomContent {
            public RemainTime remainTime;
            public TextContent textContent;
        }
    }

    /* loaded from: classes15.dex */
    public static class Image {
        public int imgHeight;
        public String imgUrl;
        public int jumpType;
        public String jumpUrl;
    }

    /* loaded from: classes15.dex */
    public static class RemainTime {
        public long remainSecond;
        public String textColor;
    }

    /* loaded from: classes15.dex */
    public static class TextContent {
        public String color;
        public String fontName;
        public int textSize;
        public String value;
    }

    /* loaded from: classes15.dex */
    public static class TopText {
        public String color;
        public String fontName;
        public List<Style> style;
        public int textSize;
        public String value;

        /* loaded from: classes15.dex */
        public static class Style {
            public int fontSize;
            public int rangeLen;
            public int rangeLoc;
            public String textColor;
        }
    }
}
