package com.jingdong.common.entity;

import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class MiaoSha {
    private static final String TAG = "MiaoSha";
    public Long endRemainTime;
    public Long startRemainTime;
    private List<Product> wareInfoList = new ArrayList();

    public MiaoSha() {
    }

    public static ArrayList<MiaoSha> toList(JSONArrayPoxy jSONArrayPoxy) {
        ArrayList<MiaoSha> arrayList = null;
        if (jSONArrayPoxy == null) {
            return null;
        }
        try {
            ArrayList<MiaoSha> arrayList2 = new ArrayList<>();
            for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
                try {
                    arrayList2.add(new MiaoSha(jSONArrayPoxy.getJSONObject(i2)));
                } catch (JSONException e2) {
                    e = e2;
                    arrayList = arrayList2;
                    OKLog.e(TAG, e);
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (JSONException e3) {
            e = e3;
        }
    }

    public List<Product> getWareInfoList() {
        return this.wareInfoList;
    }

    public void setEndRemainTime(Long l2) {
        this.endRemainTime = Long.valueOf(l2.longValue() * 1000);
    }

    public void setStartRemainTime(Long l2) {
        this.startRemainTime = Long.valueOf(l2.longValue() * 1000);
    }

    public void setWareInfoList(List<Product> list) {
        this.wareInfoList = list;
    }

    public MiaoSha(JSONObjectProxy jSONObjectProxy) {
        setStartRemainTime(Long.valueOf(jSONObjectProxy.optLong("startRemainTime")));
        setEndRemainTime(Long.valueOf(jSONObjectProxy.optLong("endRemainTime")));
        setWareInfoList(Product.toList(jSONObjectProxy.getJSONArrayOrNull("wareInfoList"), 17));
    }
}
