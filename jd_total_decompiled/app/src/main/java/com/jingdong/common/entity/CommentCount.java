package com.jingdong.common.entity;

import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class CommentCount implements Serializable {
    public static final int CONSTRUCTOR = 0;
    public static final int TYPE_BAD = 3;
    public static final int TYPE_CENTER = 2;
    public static final int TYPE_GOOD = 1;
    private static final long serialVersionUID = 958116025414232636L;
    private String name;
    private Integer scoreCount;
    private int type;

    public CommentCount() {
    }

    public static ArrayList<CommentCount> toList(JSONArrayPoxy jSONArrayPoxy, int i2) {
        ArrayList<CommentCount> arrayList = null;
        if (jSONArrayPoxy != null) {
            for (int i3 = 0; i3 < jSONArrayPoxy.length(); i3++) {
                try {
                    if (arrayList == null) {
                        arrayList = new ArrayList<>();
                    }
                    arrayList.add(new CommentCount(jSONArrayPoxy.getJSONObject(i3), i2));
                } catch (Exception e2) {
                    if (OKLog.V) {
                        OKLog.v("CommentCount", "JSONException -->> ", e2);
                    }
                }
            }
        }
        return arrayList;
    }

    public String getName() {
        String str = this.name;
        return str == null ? "" : str;
    }

    public Integer getScoreCount() {
        Integer num = this.scoreCount;
        return Integer.valueOf(num == null ? 0 : num.intValue());
    }

    public int getType() {
        return this.type;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setScoreCount(Integer num) {
        this.scoreCount = num;
    }

    public void setType(int i2) {
        this.type = i2;
    }

    public CommentCount(JSONObjectProxy jSONObjectProxy, int i2) {
        if (i2 != 0) {
            return;
        }
        if (jSONObjectProxy.getIntOrNull("scoreCount1") != null) {
            setScoreCount(jSONObjectProxy.getIntOrNull("scoreCount1"));
            setType(1);
        } else if (jSONObjectProxy.getIntOrNull("scoreCount2") != null) {
            setScoreCount(jSONObjectProxy.getIntOrNull("scoreCount2"));
            setType(2);
        } else if (jSONObjectProxy.getIntOrNull("scoreCount3") != null) {
            setScoreCount(jSONObjectProxy.getIntOrNull("scoreCount3"));
            setType(3);
        }
        setName(jSONObjectProxy.getStringOrNull("message"));
    }
}
