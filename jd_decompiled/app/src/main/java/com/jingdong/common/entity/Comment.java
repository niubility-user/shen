package com.jingdong.common.entity;

import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class Comment {
    public static final int COMMENT = 0;
    public String insertTime;
    private ArrayList<Image> mImageList;
    public String mReplyData;
    public Integer replyCount;
    public Integer score;
    public String userName;

    public Comment() {
    }

    public static ArrayList<Comment> toList(JSONArrayPoxy jSONArrayPoxy, int i2) {
        ArrayList<Comment> arrayList = null;
        try {
            ArrayList<Comment> arrayList2 = new ArrayList<>();
            for (int i3 = 0; i3 < jSONArrayPoxy.length(); i3++) {
                try {
                    arrayList2.add(new Comment(jSONArrayPoxy.getJSONObject(i3), i2));
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

    public ArrayList<Image> getImageList() {
        return this.mImageList;
    }

    public String getInsertTime() {
        String str = this.insertTime;
        return str != null ? str : "\u6682\u65e0\u53d1\u8868\u65f6\u95f4";
    }

    public Integer getReplyCount() {
        Integer num = this.replyCount;
        return Integer.valueOf(num != null ? num.intValue() : 0);
    }

    public Integer getScore() {
        Integer num = this.score;
        return Integer.valueOf(num != null ? num.intValue() : 0);
    }

    public String getUserName() {
        String str = this.userName;
        return str != null ? str : "\u6682\u65e0\u7528\u6237\u540d";
    }

    public Comment(JSONObjectProxy jSONObjectProxy, int i2) {
        if (i2 != 0) {
            return;
        }
        this.score = Integer.valueOf(jSONObjectProxy.optInt("commentScore"));
        this.userName = jSONObjectProxy.optString("userNickName");
        this.insertTime = jSONObjectProxy.optString("commentDate");
        this.replyCount = Integer.valueOf(jSONObjectProxy.optInt("replyCnt"));
        this.mReplyData = jSONObjectProxy.optString("commentData");
        JSONArrayPoxy jSONArrayOrNull = jSONObjectProxy.getJSONArrayOrNull("pictureInfoList");
        if (jSONArrayOrNull == null || jSONArrayOrNull.length() <= 0) {
            return;
        }
        this.mImageList = new ArrayList<>(jSONArrayOrNull.length());
        for (int i3 = 0; i3 < jSONArrayOrNull.length(); i3++) {
            JSONObjectProxy jSONObjectOrNull = jSONArrayOrNull.getJSONObjectOrNull(i3);
            if (jSONObjectOrNull != null) {
                this.mImageList.add(new Image(jSONObjectOrNull.getStringOrNull("picURL"), jSONObjectOrNull.getStringOrNull("largePicURL")));
            }
        }
    }
}
