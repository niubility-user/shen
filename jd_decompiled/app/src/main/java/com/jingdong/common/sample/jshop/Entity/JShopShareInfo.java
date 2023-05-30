package com.jingdong.common.sample.jshop.Entity;

import com.jd.framework.json.JDJSONObject;

/* loaded from: classes6.dex */
public class JShopShareInfo {
    public String desc;
    public String image;
    public String title;
    public String url;

    public JShopShareInfo(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.title = jDJSONObject.optString("title");
        this.desc = jDJSONObject.optString("desc");
        this.url = jDJSONObject.optString("url");
        this.image = jDJSONObject.optString("image");
    }
}
