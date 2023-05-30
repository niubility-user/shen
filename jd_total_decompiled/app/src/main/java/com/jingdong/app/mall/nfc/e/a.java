package com.jingdong.app.mall.nfc.e;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.entity.JumpEntity;

/* loaded from: classes4.dex */
public class a {
    public JumpEntity a;

    public a(JDJSONObject jDJSONObject) {
        JDJSONObject optJSONObject = jDJSONObject.optJSONObject("jump");
        if (optJSONObject != null) {
            this.a = (JumpEntity) optJSONObject.toJavaObject(JumpEntity.class);
        }
    }
}
