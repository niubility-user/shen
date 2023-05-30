package com.jingdong.common.entity;

import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class JdNews {
    public static final int JDNEWS = 0;
    public static final int JDNEWS_DETAIL = 1;
    private static final String TAG = "JdNews";
    public String addTime;
    public String content;
    public String endTime;
    public int jdNewsId;
    public String startTime;
    public String title;

    JdNews() {
    }

    public static ArrayList<JdNews> toList(JSONArrayPoxy jSONArrayPoxy, int i2) {
        ArrayList<JdNews> arrayList = null;
        try {
            ArrayList<JdNews> arrayList2 = new ArrayList<>();
            for (int i3 = 0; i3 < jSONArrayPoxy.length(); i3++) {
                try {
                    arrayList2.add(new JdNews(jSONArrayPoxy.getJSONObject(i3), i2));
                } catch (JSONException e2) {
                    e = e2;
                    arrayList = arrayList2;
                    if (OKLog.E) {
                        OKLog.e("Ware", e);
                    }
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (JSONException e3) {
            e = e3;
        }
    }

    public JdNews(JSONObjectProxy jSONObjectProxy, int i2) {
        try {
            if (i2 == 0) {
                this.jdNewsId = jSONObjectProxy.getInt("jdNewsId");
                this.title = jSONObjectProxy.getString("title");
                this.addTime = jSONObjectProxy.getString("addTime");
                this.startTime = jSONObjectProxy.getString("startTime");
                this.endTime = jSONObjectProxy.getString("endTimel");
            } else if (i2 != 1) {
            } else {
                this.addTime = jSONObjectProxy.getString("addTime");
                this.content = jSONObjectProxy.getString("content");
                this.title = jSONObjectProxy.getString("title");
            }
        } catch (JSONException e2) {
            OKLog.e(TAG, e2);
        }
    }
}
