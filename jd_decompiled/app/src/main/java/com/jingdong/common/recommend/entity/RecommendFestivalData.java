package com.jingdong.common.recommend.entity;

import android.graphics.Color;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes6.dex */
public class RecommendFestivalData {
    public int isBackUp = 0;
    public ArrayList<RecommendFestivalItemData> subWareList;
    public String wareBgUrl;
    public String wname;

    public void generateBgColors() {
        ArrayList<String> arrayList;
        ArrayList<RecommendFestivalItemData> arrayList2 = this.subWareList;
        if (arrayList2 == null || arrayList2.size() <= 0) {
            return;
        }
        Iterator<RecommendFestivalItemData> it = this.subWareList.iterator();
        while (it.hasNext()) {
            RecommendFestivalItemData next = it.next();
            if (next != null && (arrayList = next.subTitleBgColor) != null && arrayList.size() > 0) {
                next.subTitleBgColors = new int[next.subTitleBgColor.size()];
                for (int i2 = 0; i2 < next.subTitleBgColor.size(); i2++) {
                    try {
                        next.subTitleBgColors[i2] = Color.parseColor(next.subTitleBgColor.get(i2));
                    } catch (Exception e2) {
                        if (OKLog.D) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public boolean isCacheData() {
        return this.isBackUp == 1;
    }
}
