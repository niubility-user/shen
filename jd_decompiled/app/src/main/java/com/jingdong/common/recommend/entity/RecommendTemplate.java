package com.jingdong.common.recommend.entity;

import android.graphics.Color;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class RecommendTemplate extends RecommendMaterialData {
    public String background;
    public String exposureJsonSourceValue;
    public String exposureSourceValue;
    public String firstTitle;
    public ArrayList<String> firstTitleColorList;
    public int[] firstTitleColors;
    public String firstTitleFont;
    public String firstTitleIcon;
    public String img;
    public String imgUrlLocal;
    public String itemPic;
    public String jump;
    public ArrayList<String> lastTitle;
    public String lastTitleBg;
    public String lastTitleFont;
    public String lastTitleIcon;
    public String mainTitle;
    public String mainTitleFont;
    public RecommendVideo recommendVideo;
    public String skuIcon;
    public String sourceValue;
    public String subTitle;
    public String subTitleFont;
    public ArrayList<SubSkuData> subsku;
    public String videoId;
    public String lastTitleType = "1";
    public String isOpenApp = "1";

    public void generateFirstTitleColor() {
        ArrayList<String> arrayList = this.firstTitleColorList;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        this.firstTitleColors = new int[this.firstTitleColorList.size()];
        for (int i2 = 0; i2 < this.firstTitleColorList.size(); i2++) {
            try {
                this.firstTitleColors[i2] = Color.parseColor(this.firstTitleColorList.get(i2));
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                    return;
                }
                return;
            }
        }
    }

    public void generateImageUrl() {
        SubSkuData subSkuData;
        ArrayList<SubSkuData> arrayList = this.subsku;
        if (arrayList == null || arrayList.size() <= 0 || (subSkuData = this.subsku.get(0)) == null) {
            return;
        }
        this.imgUrlLocal = subSkuData.img;
    }

    public RecommendVideo getVideoData() {
        if (this.recommendVideo == null) {
            RecommendVideo recommendVideo = new RecommendVideo();
            this.recommendVideo = recommendVideo;
            recommendVideo.videoId = this.videoId;
            recommendVideo.img = this.img;
        }
        return this.recommendVideo;
    }
}
