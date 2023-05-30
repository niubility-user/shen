package com.jingdong.app.mall.bundle.styleinfoview.entitys.gift;

/* loaded from: classes3.dex */
public class PdGiftResultData {
    public String code;
    public Data data;

    /* loaded from: classes3.dex */
    public static class BaseInfo {
        public String agreementText;
        public String agreementUrl;
        public String imgUrl;
        public String shareButton;
        public String shareText;
        public String shareUrl;
        public String subButton;
    }

    /* loaded from: classes3.dex */
    public static class Data {
        public BaseInfo baseInfo;
        public String closeVideo;
        public String giftId;
        public VideoInfo videoInfo;
    }

    /* loaded from: classes3.dex */
    public static class VideoInfo {
        public String imgUrl;
        public String playUrl;
        public String upLoadUrl;
        public String videoId;
        public String videoStatus;
        public String videoTip;
    }
}
