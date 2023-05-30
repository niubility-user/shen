package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkNewArrivalsHelper;
import com.jingdong.common.jump.JumpNetDataProvider;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.web.managers.WebPerfManager;
import java.util.HashMap;

@Des(des = JumpUtil.VALUE_NEW_ARRIVALS)
/* loaded from: classes19.dex */
public class JumpToNewArrivals extends a {
    private void s(Context context, Bundle bundle) {
        DeepLinkNewArrivalsHelper.startNewArrivalsActivity(context, bundle);
        c(context);
    }

    private void t(Bundle bundle) {
        u(bundle);
        v(bundle);
    }

    private void u(Bundle bundle) {
        HashMap hashMap = new HashMap();
        hashMap.put("isRN", 0);
        hashMap.put("darkMode", 0);
        hashMap.put("switchSkin", 0);
        hashMap.put("source", 3);
        hashMap.put(WebPerfManager.PAGE_TYPE, 1);
        if (JumpNetDataProvider.getInstance().request("getCommonInfo", hashMap, new boolean[0])) {
            bundle.putBoolean("hasCommonInfoHttpRequestByJump", true);
        }
    }

    private void v(Bundle bundle) {
        HashMap hashMap = new HashMap();
        hashMap.put("source", 3);
        hashMap.put("pageLocation", 1);
        if (JumpNetDataProvider.getInstance().request("getTabHomeInfo", hashMap, new boolean[0])) {
            bundle.putBoolean("hasHomeInfoHttpRequestByJump", true);
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        String str = "onRenderStart:" + SystemClock.elapsedRealtime();
        if ("1".equals(bundle.getString("newArrivalsType", "0"))) {
            t(bundle);
        }
        s(context, bundle);
    }
}
