package com.jingdong.common.sample.json;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.corelib.utils.Log;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class JshopSearch {
    private static final String TAG = "JshopSearch";
    public ArrayList<JshopSearchItem> shopList;

    public JshopSearch() {
    }

    public JshopSearch(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.shopList = new ArrayList<>();
        JDJSONArray optJSONArray = jDJSONObject.optJSONArray("shopList");
        String optString = jDJSONObject.optString("logid");
        String optString2 = jDJSONObject.optString("mtest");
        if (optJSONArray == null) {
            return;
        }
        int size = optJSONArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            JshopSearchItem jshopSearchItem = new JshopSearchItem(optJSONArray.optJSONObject(i2));
            jshopSearchItem.logid = optString;
            jshopSearchItem.mtest = optString2;
            this.shopList.add(jshopSearchItem);
        }
    }

    public JshopSearch(JDJSONObject jDJSONObject, int i2) {
        if (jDJSONObject == null) {
            return;
        }
        Log.d(TAG, "page = " + i2);
        this.shopList = new ArrayList<>();
        JDJSONArray optJSONArray = jDJSONObject.optJSONArray("shopList");
        if (optJSONArray == null) {
            return;
        }
        int size = optJSONArray.size();
        int i3 = 0;
        while (i3 < size) {
            JshopSearchItem jshopSearchItem = new JshopSearchItem(optJSONArray.optJSONObject(i3));
            if (i2 == 0) {
                jshopSearchItem.isNeedShowPDetail = i3 == 0;
            } else {
                jshopSearchItem.isNeedShowPDetail = false;
            }
            this.shopList.add(jshopSearchItem);
            i3++;
        }
    }
}
