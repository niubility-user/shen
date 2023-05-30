package com.jingdong.common.web.entity;

import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class JsInputEntity {
    private static final String TAG = "JsInputEntity";
    public String businessType;
    public String callBackName;
    public String params;

    public boolean addParam(String str, Object obj) {
        try {
            JDJSONObject jDJSONObject = (JDJSONObject) JDJSON.parse(this.params);
            if (jDJSONObject != null) {
                jDJSONObject.put(str, obj);
                this.params = jDJSONObject.toJSONString();
                return true;
            }
            return false;
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.d(TAG, e2);
            }
            return false;
        }
    }

    public Object getParamValue(Object obj) {
        try {
            JDJSONObject jDJSONObject = (JDJSONObject) JDJSON.parse(this.params);
            if (jDJSONObject != null) {
                return jDJSONObject.get(obj);
            }
            return null;
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.d(TAG, e2);
            }
            return null;
        }
    }

    public String toString() {
        return "JsInputEntity{businessType='" + this.businessType + "', callBackName='" + this.callBackName + "', params='" + this.params + "'}";
    }
}
