package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkNewProductHelper;
import com.jingdong.common.jump.JumpUtil;
import java.util.HashMap;
import java.util.Map;

@Des(des = JumpUtil.VALUE_NEWPRODUCT_CORE)
/* loaded from: classes19.dex */
public class JumpToNewProductCore extends PReqDesJump {
    @Override // com.jingdong.app.mall.basic.deshandler.PReqDesJump
    void s(Context context, Bundle bundle) {
        DeepLinkNewProductHelper.startNewProductCoreActivity(context, bundle);
        c(context);
    }

    @Override // com.jingdong.app.mall.basic.deshandler.PReqDesJump
    String t(Context context, Bundle bundle) {
        return "getCommonInfo";
    }

    @Override // com.jingdong.app.mall.basic.deshandler.PReqDesJump
    Map<String, Object> u(Context context, Bundle bundle) {
        HashMap hashMap = new HashMap();
        hashMap.put("isRN", 0);
        hashMap.put("darkMode", 0);
        hashMap.put("switchSkin", 0);
        hashMap.put("source", 2);
        return hashMap;
    }
}
