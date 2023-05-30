package com.jingdong.common.messagepop;

import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.framework.json.JDJSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class JDMessageNoticeModel {
    protected static final int STYLE_0 = 0;
    protected static final int STYLE_1 = 1;
    protected String bizId;
    protected int chooseIndex;
    protected int defaultPushBtn;
    protected boolean isCanShow = false;
    protected String pageId;
    protected int pushContentId;
    protected String pushDarkImage;
    protected String pushImage;
    protected int pushStyleId;
    protected String pushSubTitle;
    protected String pushTitle;

    public void update(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.pushContentId = jDJSONObject.optInt("pushContentId");
        this.pushStyleId = jDJSONObject.optInt("pushStyleId", -1);
        this.defaultPushBtn = jDJSONObject.optInt("defaultPushBtn", 1);
        this.pushTitle = jDJSONObject.optString("pushTitle").replace("\\n", ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        this.pushSubTitle = jDJSONObject.optString("pushSubTitle");
        this.pushImage = jDJSONObject.optString("pushImage");
        this.pushDarkImage = jDJSONObject.optString("pushDarkImage");
        this.pageId = jDJSONObject.optString("pageId");
        this.bizId = jDJSONObject.optString("bizId");
        int i2 = this.pushStyleId;
        if (i2 == 0) {
            this.isCanShow = true;
            this.chooseIndex = 4;
            if (TextUtils.isEmpty(this.pushTitle) || TextUtils.isEmpty(this.pushSubTitle)) {
                this.pushTitle = "\u53ca\u65f6\u83b7\u53d6\u7269\u6d41\u8fdb\u5ea6\u3001\u4fc3\u9500\u4f18\u60e0";
                this.pushSubTitle = "\u5f00\u542f\u6d88\u606f\u901a\u77e5\uff0c\u83b7\u53d6\u7269\u6d41\u8fdb\u5ea6\n\u5546\u54c1\u964d\u4ef7\u3001\u4f18\u60e0\u798f\u5229\u4fe1\u606f";
            }
        } else if (i2 != 1) {
        } else {
            this.isCanShow = true;
            if (TextUtils.isEmpty(this.pushTitle) || TextUtils.isEmpty(this.pushSubTitle) || TextUtils.isEmpty(this.pushImage)) {
                this.pushTitle = "\u5f00\u542f\u6d88\u606f\u901a\u77e5";
                this.pushSubTitle = "\u53ca\u65f6\u83b7\u53d6\u8ba2\u5355\u7269\u6d41\u3001\u5546\u54c1\u964d\u4ef7\u901a\u77e5";
                this.pushImage = "";
            }
            this.chooseIndex = this.defaultPushBtn;
        }
    }
}
