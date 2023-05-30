package com.jingdong.common.recommend.entity;

import android.text.TextUtils;
import com.jingdong.common.recommend.LabelInfo;
import com.jingdong.common.recommend.RecommendLiveProduct;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class RecommendVideo extends RecommendMaterialData {
    public String authorName;
    public String authorPic;
    public ArrayList<String> benefitIcons;
    public String brokerInfo;
    public String channelJumpUrl;
    public String description;
    public String exposureJsonSourceValue;
    public String exposureSourceValue;
    public String icon2;
    public String imageurl;
    public String img;
    public String imgCount;
    public String imgCountImg;
    public String imgUrlLocal;
    public int isProduct;
    public String jump;
    public List<LabelInfo> labels;
    public String leftTopIcon;
    public ArrayList<RecommendLiveProduct> liveSkus;
    public int liveStatus;
    public String mainTitle;
    public String nonWareImageScale;
    public String pageView;
    public String playUrl;
    public String pv;
    public String pvIcon;
    public String pvIconRound;
    public String reqsig;
    public String sourceValue;
    public ArrayList<SubSkuData> subsku;
    public String thumbsUpIcon;
    public String thumbsUpNumber;
    public String videoDuration;
    public String videoId;
    public String videoSummaryUrl;
    public String videoTitle;
    public String viewCount;
    public String viewCountImg;
    public String wname;
    public String isOpenApp = "1";
    public String videoLayout = "1";
    public boolean isMeasureParent = false;
    public boolean canPlay = true;
    public boolean enableLoop = true;
    public boolean playGuidAni = false;

    public void generateImageUrl() {
        SubSkuData subSkuData;
        ArrayList<SubSkuData> arrayList = this.subsku;
        if (arrayList == null || arrayList.size() <= 0 || (subSkuData = this.subsku.get(0)) == null) {
            return;
        }
        this.imgUrlLocal = subSkuData.img;
    }

    public String getVideoUrl() {
        return !TextUtils.isEmpty(this.videoSummaryUrl) ? this.videoSummaryUrl : this.playUrl;
    }

    public boolean isShowDot() {
        return "1".equals(this.source);
    }
}
