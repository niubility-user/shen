package com.jingdong.common.recommend.entity;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes6.dex */
public class RecommendHomeData {
    private ArrayList<RecommendItem> homeRecommendItems;
    public boolean isHomeCache;

    public RecommendHomeData(boolean z, ArrayList<RecommendItem> arrayList) {
        this.isHomeCache = z;
        this.homeRecommendItems = arrayList;
        if (z) {
            setHomeDataCache();
        }
    }

    private void setHomeDataCache() {
        RecommendDna recommendDna;
        ArrayList<RecommendItem> arrayList = this.homeRecommendItems;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        Iterator<RecommendItem> it = this.homeRecommendItems.iterator();
        while (it.hasNext()) {
            RecommendItem next = it.next();
            int i2 = next.type;
            if (i2 == 1000 && (recommendDna = next.dna) != null) {
                recommendDna.isBackUp = 1;
            }
            if (i2 == 2001 || i2 == 2002) {
                RecommendFestivalData recommendFestivalData = next.recommendFestivalData;
                if (recommendFestivalData != null) {
                    recommendFestivalData.isBackUp = 1;
                }
            }
        }
    }

    public ArrayList<RecommendItem> getHomeRecommendItems() {
        return this.homeRecommendItems;
    }

    public boolean hasData() {
        ArrayList<RecommendItem> arrayList = this.homeRecommendItems;
        return arrayList != null && arrayList.size() > 0;
    }

    public boolean isHomeDataInsert(boolean z) {
        ArrayList<RecommendItem> arrayList;
        return ((z && this.isHomeCache) || (!z && !this.isHomeCache)) && (arrayList = this.homeRecommendItems) != null && arrayList.size() > 0;
    }

    public void setHomeRecommendItems(ArrayList<RecommendItem> arrayList) {
        this.homeRecommendItems = arrayList;
    }
}
