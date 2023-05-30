package com.jingdong.common.entity;

import com.jingdong.common.deeplinkhelper.DeepLinkRankHelper;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class MiaoshaCategoryEntity {
    private static final String TAG = "MiaoshaCategoryEntity";
    public int cateId;
    public String categoryName;
    public String selectImg;
    public String sourceValue;
    public String unSelectImg;

    public MiaoshaCategoryEntity() {
    }

    public static List<MiaoshaCategoryEntity> toList(JSONObjectProxy jSONObjectProxy) {
        JSONArrayPoxy jSONArrayOrNull = jSONObjectProxy.getJSONArrayOrNull("categories");
        if (jSONArrayOrNull != null) {
            int length = jSONArrayOrNull.length();
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < length; i2++) {
                try {
                    arrayList.add(new MiaoshaCategoryEntity(jSONArrayOrNull.getJSONObject(i2)));
                } catch (JSONException e2) {
                    OKLog.e(TAG, e2);
                }
            }
            return arrayList;
        }
        return null;
    }

    public MiaoshaCategoryEntity(JSONObjectProxy jSONObjectProxy) {
        this.cateId = jSONObjectProxy.optInt(DeepLinkRankHelper.CATE_ID);
        this.categoryName = jSONObjectProxy.optString("categoryName");
        this.selectImg = jSONObjectProxy.optString("selectImg");
        this.unSelectImg = jSONObjectProxy.optString("unSelectImg");
        this.sourceValue = jSONObjectProxy.optString("sourceValue");
    }
}
