package com.jingdong.app.mall.bundle.styleinfoview.entitys;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.corelib.utils.Log;
import java.util.ArrayList;

/* loaded from: classes3.dex */
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
        ArrayList<PDBuyAskEntity> arrayList;
        try {
            arrayList = new ArrayList<>();
            for (int i3 = 0; i3 < jDJSONArray.size(); i3++) {
                try {
                    arrayList.add(new PDBuyAskEntity(jDJSONArray.optJSONObject(i3), i2));
                } catch (Exception e2) {
                    e = e2;
                    if (Log.V) {
                        Log.v("Comment", "JSONException-->>", e);
                    }
                    return arrayList;
                }
            }
        } catch (Exception e3) {
            e = e3;
            arrayList = null;
        }
        return arrayList;
    }
}
