package com.jingdong.common.entity;

import android.text.TextUtils;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class CommentReply {
    private static final String TAG = "CommentReply";
    private String parentNickName;
    public String plusAddress;
    public String plusAvailable;
    private String replyData;
    private String replyDate;
    private String replyId;
    private String replyNo;
    private String userImgURL;
    private String userLevel;
    private String userNickName;

    public CommentReply() {
    }

    public static ArrayList<CommentReply> toList(JSONArrayPoxy jSONArrayPoxy) {
        ArrayList<CommentReply> arrayList = null;
        try {
            ArrayList<CommentReply> arrayList2 = new ArrayList<>();
            for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
                try {
                    arrayList2.add(new CommentReply(jSONArrayPoxy.getJSONObject(i2)));
                } catch (JSONException e2) {
                    e = e2;
                    arrayList = arrayList2;
                    if (OKLog.E) {
                        OKLog.e("Comment", "JSONException -->> ", e);
                    }
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (JSONException e3) {
            e = e3;
        }
    }

    public String getFloorText() {
        if ("1".equals(this.replyNo)) {
            return "\u6c99\u53d1";
        }
        if ("2".equals(this.replyNo)) {
            return "\u677f\u51f3";
        }
        if ("3".equals(this.replyNo)) {
            return "\u5730\u677f";
        }
        return this.replyNo + "#";
    }

    public String getParentNickName() {
        return this.parentNickName;
    }

    public String getPlusAddress() {
        return this.plusAddress;
    }

    public String getPlusAvailable() {
        return this.plusAvailable;
    }

    public String getReplyData() {
        return this.replyData;
    }

    public String getReplyDate() {
        return this.replyDate;
    }

    public String getReplyId() {
        return this.replyId;
    }

    public String getReplyNo() {
        return this.replyNo;
    }

    public String getUserImgURL() {
        if (!TextUtils.isEmpty(this.userImgURL) && !this.userImgURL.startsWith("http://")) {
            return "http://" + this.userImgURL;
        }
        return this.userImgURL;
    }

    public String getUserLevel() {
        return this.userLevel;
    }

    public String getUserNickName() {
        return this.userNickName;
    }

    public void setParentNickName(String str) {
        this.parentNickName = str;
    }

    public void setPlusAddress(String str) {
        this.plusAddress = str;
    }

    public void setPlusAvailable(String str) {
        this.plusAvailable = str;
    }

    public void setReplyData(String str) {
        this.replyData = str;
    }

    public void setReplyDate(String str) {
        this.replyDate = str;
    }

    public void setReplyId(String str) {
        this.replyId = str;
    }

    public void setReplyNo(String str) {
        this.replyNo = str;
    }

    public void setUserImgURL(String str) {
        this.userImgURL = str;
    }

    public void setUserLevel(String str) {
        this.userLevel = str;
    }

    public void setUserNickName(String str) {
        this.userNickName = str;
    }

    public CommentReply(JSONObjectProxy jSONObjectProxy) {
        try {
            setUserNickName(jSONObjectProxy.getStringOrNull("userNickName"));
            setUserLevel(jSONObjectProxy.getStringOrNull("userLevel"));
            setPlusAvailable(jSONObjectProxy.getStringOrNull("plusAvailable"));
            setPlusAddress(jSONObjectProxy.getStringOrNull("plusAddress"));
            setUserImgURL(jSONObjectProxy.getStringOrNull("userImgURL"));
            setReplyId(jSONObjectProxy.getStringOrNull("replyId"));
            setReplyDate(jSONObjectProxy.getStringOrNull("replyDate"));
            setReplyData(jSONObjectProxy.getStringOrNull("replyData"));
            setReplyNo(jSONObjectProxy.getStringOrNull("replyNo"));
            setParentNickName(jSONObjectProxy.getStringOrNull("parentNickName"));
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }
}
