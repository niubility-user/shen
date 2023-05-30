package com.jingdong.common.entity;

import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class BuyAsk {
    public static final int CONSULTATION = 0;
    public String content;
    public String creationTime;
    public String replyContent;
    public String replyTime;
    public String userId;

    public BuyAsk(JSONObjectProxy jSONObjectProxy, int i2) {
        if (i2 != 0) {
            return;
        }
        this.content = jSONObjectProxy.getStringOrNull("content");
        this.creationTime = jSONObjectProxy.getStringOrNull("creationTime");
        this.userId = jSONObjectProxy.getStringOrNull("userId");
        this.replyContent = jSONObjectProxy.getStringOrNull("replyContent");
        this.replyTime = jSONObjectProxy.getStringOrNull("replyTime");
    }

    public static ArrayList<BuyAsk> toList(JSONArrayPoxy jSONArrayPoxy, int i2) {
        ArrayList<BuyAsk> arrayList = null;
        try {
            ArrayList<BuyAsk> arrayList2 = new ArrayList<>();
            for (int i3 = 0; i3 < jSONArrayPoxy.length(); i3++) {
                try {
                    arrayList2.add(new BuyAsk(jSONArrayPoxy.getJSONObject(i3), i2));
                } catch (JSONException e2) {
                    e = e2;
                    arrayList = arrayList2;
                    if (OKLog.V) {
                        OKLog.v("Comment", "JSONException -->> ", e);
                    }
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (JSONException e3) {
            e = e3;
        }
    }
}
