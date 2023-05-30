package com.jingdong.common.recommend.entity;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.List;

/* loaded from: classes6.dex */
public class RecommendHomeCardBean {
    public int itemType;
    public List<SubWareList> subWareList;

    /* loaded from: classes6.dex */
    public static class SubWareList {
        public String bgImg;
        public int channelMaterialId;
        public String channelTitleImg;
        public String channelTitleImgWidth;
        public JDJSONObject content;
        public String expo;
        public String expoJson;
        public String expoLog;
        public String exposureJsonSourceValue;
        public int fontBold;
        public int fontSize;
        public boolean hasExpoView;
        public int id;
        public int imageType;
        public String img;
        public String img2;
        public String isUseDollar;
        public String itemid;
        public String jdFlvPull;
        public JumpEntity jump;
        public JumpEntity jump2;
        public String labelBgImg;
        public String labelText;
        public String labelTextColor;
        public String liveRoomImg;
        public String liveRoomTitle;
        public String maintitleColor;
        public String markedImg;
        public String pageView;
        public String price1;
        public String price2;
        public String priceColor;
        private RecommendVideo recommendVideo;
        public int reversePeriod;
        public String rightImgEnlarge;
        public String screen;
        public String showName;
        public String showNameImg;
        public String showNameImgWidth;
        public String showSeconds;
        public String[] skuList;
        public String skuTagImg;
        public String slogan;
        public String sloganAreaColor;
        public String sloganColor;
        public String sloganIcon;
        public String sloganTagImg;
        public String sourceValue;
        public String streamerIcon;
        public String streamerName;
        public String subtitle;
        public String subtitleColor;
        public String videoUrl;

        public String getAllSkuId() {
            if (this.skuList == null) {
                return "";
            }
            int i2 = 0;
            String str = "";
            while (true) {
                String[] strArr = this.skuList;
                if (i2 >= strArr.length) {
                    return str;
                }
                String str2 = strArr[i2];
                if (i2 == 0) {
                    if (str2 == null) {
                        str2 = "";
                    }
                    str = str2;
                } else {
                    String concat = str.concat(CartConstant.KEY_YB_INFO_LINK);
                    if (str2 == null) {
                        str2 = "";
                    }
                    str = concat.concat(str2);
                }
                i2++;
            }
        }

        public String getSkuId(int i2) {
            String str;
            String[] strArr = this.skuList;
            return (strArr == null || i2 < 0 || strArr.length <= i2 || (str = strArr[i2]) == null) ? "" : str;
        }

        public RecommendVideo getVideoData() {
            if (this.recommendVideo == null) {
                RecommendVideo recommendVideo = new RecommendVideo();
                this.recommendVideo = recommendVideo;
                recommendVideo.playUrl = this.videoUrl;
                recommendVideo.authorPic = this.streamerIcon;
                recommendVideo.imageurl = this.liveRoomImg;
                recommendVideo.mainTitle = this.streamerName;
                recommendVideo.pageView = this.pageView;
                recommendVideo.liveStatus = 1;
                recommendVideo.enableLoop = false;
            }
            return this.recommendVideo;
        }
    }
}
