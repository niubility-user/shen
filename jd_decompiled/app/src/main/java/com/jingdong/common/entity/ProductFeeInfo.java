package com.jingdong.common.entity;

import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class ProductFeeInfo implements Serializable {
    private static final long serialVersionUID = 2774691732752290275L;
    private ArrayList<FeeInfo> mFeeInfos;

    public ProductFeeInfo(JSONObjectProxy jSONObjectProxy) {
        if (jSONObjectProxy == null) {
            return;
        }
        update(JDJSON.parseObject(jSONObjectProxy.toString()));
    }

    private void update(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            try {
                JDJSONArray jSONArray = jDJSONObject.getJSONArray("treatyList");
                if (jSONArray == null || jSONArray.size() <= 0) {
                    return;
                }
                this.mFeeInfos = new ArrayList<>(jSONArray.size());
                for (int i2 = 0; i2 < jSONArray.size(); i2++) {
                    JDJSONObject jSONObject = jSONArray.getJSONObject(i2);
                    if (jSONObject != null) {
                        this.mFeeInfos.add(new FeeInfo(jSONObject, i2));
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    public ArrayList<FeeInfo> getFeeInfos() {
        return this.mFeeInfos;
    }

    /* loaded from: classes5.dex */
    public class FeeInfo implements Serializable {
        public static final String FEE_TYPE_HEYUE = "0";
        public static final String FEE_TYPE_NORMAL = "1";
        private static final long serialVersionUID = -3927567535238748537L;
        public String ft;
        public int index;
        public String name;
        public String oldType;
        public String skuId;
        public String type;

        public FeeInfo(JSONObjectProxy jSONObjectProxy, int i2) {
            if (jSONObjectProxy == null) {
                return;
            }
            update(JDJSON.parseObject(jSONObjectProxy.toString()), i2);
        }

        private void update(JDJSONObject jDJSONObject, int i2) {
            this.index = i2;
            if (jDJSONObject != null) {
                this.skuId = jDJSONObject.getString("sku");
                this.name = jDJSONObject.getString("name");
                this.type = jDJSONObject.getString("type");
                this.ft = jDJSONObject.getString("ft");
                this.oldType = jDJSONObject.getString("oldType");
            }
        }

        public FeeInfo(JDJSONObject jDJSONObject, int i2) {
            update(jDJSONObject, i2);
        }
    }

    public ProductFeeInfo(JDJSONObject jDJSONObject) {
        update(jDJSONObject);
    }
}
