package com.jingdong.app.mall.newproduct;

import android.os.Bundle;
import com.jingdong.common.jump.JumpNetDataProvider;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class a {
    public void a(Bundle bundle) {
        HashMap hashMap = new HashMap(5);
        hashMap.put("isRN", 0);
        hashMap.put("darkMode", 0);
        hashMap.put("switchSkin", 0);
        hashMap.put("source", 4);
        if (!JumpNetDataProvider.getInstance().request("getCommonInfo", hashMap, new boolean[0]) || bundle == null) {
            return;
        }
        bundle.putBoolean("hasCommonInfoHttpRequestByJumpC", true);
    }
}
