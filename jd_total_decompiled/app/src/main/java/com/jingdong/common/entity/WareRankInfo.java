package com.jingdong.common.entity;

import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class WareRankInfo implements Serializable {
    public String cid3Name;
    public String currentRank;

    public WareRankInfo(JSONObjectProxy jSONObjectProxy) {
        if (jSONObjectProxy == null) {
            return;
        }
        update(JDJSON.parseObject(jSONObjectProxy.toString()));
    }

    private void update(JDJSONObject jDJSONObject) {
        this.currentRank = jDJSONObject.getString("currentRank");
        this.cid3Name = jDJSONObject.getString("cid3Name");
    }

    public WareRankInfo(JDJSONObject jDJSONObject) {
        update(jDJSONObject);
    }
}
