package com.jingdong.app.mall.home.o.a;

import android.text.TextUtils;

/* loaded from: classes4.dex */
public class m {
    public static String a(com.jingdong.app.mall.home.r.e.b bVar) {
        return bVar == null ? "" : bVar.getJsonString("skuCoverImg");
    }

    public static int b(com.jingdong.app.mall.home.r.e.b bVar) {
        int jsonInt;
        if (bVar != null && (jsonInt = bVar.getJsonInt("mainTitleFont")) >= 20) {
            return jsonInt;
        }
        return 30;
    }

    public static boolean c(com.jingdong.app.mall.home.r.e.b bVar) {
        return bVar != null && TextUtils.equals(bVar.getJsonString("rightImgEnlarge"), "1");
    }
}
