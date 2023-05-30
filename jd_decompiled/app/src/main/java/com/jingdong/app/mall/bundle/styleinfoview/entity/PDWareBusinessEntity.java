package com.jingdong.app.mall.bundle.styleinfoview.entity;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import java.util.ArrayList;
import jpbury.t;

/* loaded from: classes3.dex */
public class PDWareBusinessEntity {
    public ArrayList<BaseTemplateEntity> businessFloorEntities;
    public int code;
    public boolean isUsed = false;
    public String skuId;

    public PDWareBusinessEntity(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            try {
                JDJSONArray jSONArray = jDJSONObject.getJSONArray("floors");
                if (jSONArray != null) {
                    int size = jSONArray.size();
                    this.businessFloorEntities = new ArrayList<>(size);
                    for (int i2 = 0; i2 < size; i2++) {
                        JDJSONObject optJSONObject = jSONArray.optJSONObject(i2);
                        if (optJSONObject != null) {
                            this.businessFloorEntities.add(new BusinessFloorEntity(optJSONObject));
                        }
                    }
                }
            } catch (Exception e2) {
                if (Log.D) {
                    Log.d(t.f20145j, e2.getMessage());
                }
            }
        }
    }
}
