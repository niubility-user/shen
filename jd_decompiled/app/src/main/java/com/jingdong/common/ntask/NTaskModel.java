package com.jingdong.common.ntask;

import com.jd.framework.json.JDJSONObject;

/* loaded from: classes5.dex */
public class NTaskModel {
    public String activityId;
    public int awardStyle;
    public int completionFlag;
    public String contentId;
    public int contentType;
    public String hint;
    public String img;
    public int progress;
    public String title;
    public String url;

    public static NTaskModel parse(JDJSONObject jDJSONObject, String str, int i2) {
        NTaskModel nTaskModel = new NTaskModel();
        JDJSONObject jSONObject = jDJSONObject.getJSONObject("data");
        nTaskModel.img = jSONObject.optString("img");
        nTaskModel.awardStyle = jSONObject.optInt("awardStyle");
        int optInt = jSONObject.optInt("completionNum");
        int optInt2 = jSONObject.optInt("needNum");
        nTaskModel.completionFlag = jSONObject.optInt("completionFlag");
        boolean optBoolean = jSONObject.optBoolean("first");
        int i3 = nTaskModel.completionFlag;
        if (i3 == 0) {
            nTaskModel.progress = 100;
            nTaskModel.title = "\u7acb\u5373\u62bd\u5956";
        } else if (i3 == 1) {
            nTaskModel.progress = (int) (((optInt * 1.0f) / optInt2) * 100.0f);
            nTaskModel.title = "\u4efb\u52a1\u4e2d";
            if (optBoolean) {
                nTaskModel.title = "\u5206\u4eab\u6709\u793c";
            }
        } else {
            nTaskModel.progress = 100;
            nTaskModel.title = "\u5df2\u5b8c\u6210";
        }
        nTaskModel.url = jSONObject.optString("contentUrl");
        nTaskModel.hint = jSONObject.optString("bubbleText");
        nTaskModel.contentId = str;
        nTaskModel.contentType = i2;
        nTaskModel.activityId = jSONObject.optString("activityId");
        return nTaskModel;
    }
}
