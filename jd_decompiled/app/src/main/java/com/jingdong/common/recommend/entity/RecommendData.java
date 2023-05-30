package com.jingdong.common.recommend.entity;

import java.util.ArrayList;

/* loaded from: classes6.dex */
public class RecommendData {
    private ArrayList RecommendList;
    private RecommendOtherData recommendOtherData;

    public void addRecommend(ArrayList<?> arrayList) {
        if (this.RecommendList == null) {
            this.RecommendList = new ArrayList();
        }
        this.RecommendList.addAll(arrayList);
    }

    public ArrayList<?> getRecommendList() {
        return this.RecommendList;
    }

    public RecommendOtherData getRecommendOtherData() {
        return this.recommendOtherData;
    }

    public void setRecommendList(ArrayList<?> arrayList) {
        this.RecommendList = arrayList;
    }

    public void setRecommendOtherData(RecommendOtherData recommendOtherData) {
        this.recommendOtherData = recommendOtherData;
    }
}
