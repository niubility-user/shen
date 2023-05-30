package com.jingdong.app.mall.bundle.productdetailcard.entity;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class PdCardData {
    public String code;
    public ArrayList<PdCardFloorInfo> floorEntity;

    public PdCardData(JDJSONObject jDJSONObject) {
        try {
            this.code = jDJSONObject.optString("code");
            JDJSONArray jSONArray = jDJSONObject.getJSONArray("floorInfo");
            int size = jSONArray.size();
            this.floorEntity = new ArrayList<>(size);
            for (int i2 = 0; i2 < size; i2++) {
                JDJSONObject optJSONObject = jSONArray.optJSONObject(i2);
                if (optJSONObject != null) {
                    this.floorEntity.add(new PdCardFloorInfo(optJSONObject));
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
