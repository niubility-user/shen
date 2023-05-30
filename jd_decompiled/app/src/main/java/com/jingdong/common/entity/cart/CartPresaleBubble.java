package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.XView2.common.XView2Constants;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class CartPresaleBubble {
    public long beforeCountDownTime;
    public HashMap<String, String> bubblesShow;
    public String countDownText;
    public long endTime;
    public int state;
    public String staticText;

    public CartPresaleBubble(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.state = jDJSONObject.optInt(XView2Constants.STATE);
        this.endTime = jDJSONObject.optLong("endTime");
        this.staticText = jDJSONObject.optString("staticText");
        this.countDownText = jDJSONObject.optString("countDownText");
        this.beforeCountDownTime = jDJSONObject.optLong("beforeCountDownTime");
        JDJSONObject optJSONObject = jDJSONObject.optJSONObject("bubblesShow");
        if (optJSONObject != null) {
            this.bubblesShow = new HashMap<>(optJSONObject.size());
            for (String str : optJSONObject.keySet()) {
                this.bubblesShow.put(str, optJSONObject.optString(str));
            }
        }
    }
}
