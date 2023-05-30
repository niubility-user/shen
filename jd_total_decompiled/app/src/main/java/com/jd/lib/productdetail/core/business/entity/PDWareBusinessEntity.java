package com.jd.lib.productdetail.core.business.entity;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.floor.FloorBussinessName;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import java.util.ArrayList;
import jpbury.t;

/* loaded from: classes15.dex */
public class PDWareBusinessEntity {
    public ArrayList<BaseTemplateEntity> businessFloorEntities;
    public int code;
    public boolean isUsed = false;
    public String mJdjson;
    public String mJsonString;
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
                    if (this.businessFloorEntities.size() > 0) {
                        this.businessFloorEntities.add(new BusinessFloorEntity(buildDetailWeb()));
                        this.businessFloorEntities.add(new BusinessFloorEntity(buildDetailRecommend()));
                    }
                }
            } catch (Exception e2) {
                if (Log.D) {
                    Log.d(t.f20145j, e2.getMessage());
                }
            }
        }
    }

    public JDJSONObject buildDetailRecommend() {
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("mId", (Object) FloorBussinessName.FB_BUSINESS_DETAIL_LOCK_RECOMMEND_MOCK);
        jDJSONObject.put("bId", (Object) FloorBussinessName.FB_BUSINESS_DETAIL_LOCK_RECOMMEND_MOCK);
        JDJSONObject jDJSONObject2 = new JDJSONObject();
        jDJSONObject2.put("bgc", (Object) "#FFFFFF");
        jDJSONObject2.put("spl", (Object) "grey_strip");
        jDJSONObject.put("cf", (Object) jDJSONObject2);
        jDJSONObject.put("data", (Object) new JDJSONObject());
        return jDJSONObject;
    }

    public JDJSONObject buildDetailWeb() {
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("mId", (Object) FloorBussinessName.FB_BUSINESS_DETAIL_WEB_MOCK);
        jDJSONObject.put("bId", (Object) FloorBussinessName.FB_BUSINESS_DETAIL_WEB_MOCK);
        JDJSONObject jDJSONObject2 = new JDJSONObject();
        jDJSONObject2.put("bgc", (Object) "#FFFFFF");
        jDJSONObject2.put("spl", (Object) "grey_strip");
        jDJSONObject.put("cf", (Object) jDJSONObject2);
        jDJSONObject.put("data", (Object) new JDJSONObject());
        return jDJSONObject;
    }
}
