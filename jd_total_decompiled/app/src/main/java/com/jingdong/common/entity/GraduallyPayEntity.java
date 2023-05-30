package com.jingdong.common.entity;

import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class GraduallyPayEntity implements Serializable {
    public String graduallyPay;
    public GraduallyPayPopupEntity graduallyPayPopupEntity;

    public GraduallyPayEntity(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            this.graduallyPay = jDJSONObject.getString("graduallyPay");
            JDJSONObject jSONObject = jDJSONObject.getJSONObject("graduallyPayPopup");
            if (jSONObject != null) {
                this.graduallyPayPopupEntity = (GraduallyPayPopupEntity) JDJSON.parseObject(jSONObject.toString(), GraduallyPayPopupEntity.class);
            }
        }
    }
}
