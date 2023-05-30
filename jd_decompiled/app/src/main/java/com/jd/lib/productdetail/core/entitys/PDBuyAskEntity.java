package com.jd.lib.productdetail.core.entitys;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.corelib.utils.Log;
import java.util.ArrayList;

/* loaded from: classes15.dex */
public class PDBuyAskEntity {
    public static final int CONSULTATION = 0;
    public String content;
    public String creationTime;
    public String replyContent;
    public String replyTime;
    public String userId;

    public PDBuyAskEntity(JDJSONObject jDJSONObject, int i2) {
        if (i2 != 0) {
            return;
        }
        this.content = jDJSONObject.optString("content");
        this.creationTime = jDJSONObject.optString("creationTime");
        this.userId = jDJSONObject.optString("userId");
        this.replyContent = jDJSONObject.optString("replyContent");
        this.replyTime = jDJSONObject.optString("replyTime");
    }

    public static ArrayList<PDBuyAskEntity> toList(JDJSONArray jDJSONArray, int i2) {
        ArrayList<PDBuyAskEntity> arrayList = null;
        try {
            ArrayList<PDBuyAskEntity> arrayList2 = new ArrayList<>();
            for (int i3 = 0; i3 < jDJSONArray.size(); i3++) {
                try {
                    arrayList2.add(new PDBuyAskEntity(jDJSONArray.optJSONObject(i3), i2));
                } catch (Exception e2) {
                    e = e2;
                    arrayList = arrayList2;
                    if (Log.V) {
                        Log.v("Comment", "JSONException-->>", e);
                    }
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (Exception e3) {
            e = e3;
        }
    }
}
