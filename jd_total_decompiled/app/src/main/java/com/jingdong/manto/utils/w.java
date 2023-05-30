package com.jingdong.manto.utils;

import android.app.Activity;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.sdk.api.IShareManager;
import java.util.HashMap;

/* loaded from: classes16.dex */
public class w {
    public static void a(Activity activity, PkgDetailEntity pkgDetailEntity, String str, IShareManager.ShareCallback shareCallback) {
        if (pkgDetailEntity == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("path", str);
        hashMap.put("title", pkgDetailEntity.name);
        hashMap.put("desc", pkgDetailEntity.description);
        hashMap.put("imageUrl", pkgDetailEntity.logo);
        a(activity, pkgDetailEntity.appId, pkgDetailEntity.type, pkgDetailEntity.getShareUrl(), hashMap, shareCallback);
    }

    private static void a(Activity activity, String str, String str2, String str3, HashMap<String, Object> hashMap, IShareManager.ShareCallback shareCallback) {
        String str4 = (String) hashMap.get("imageUrl");
        if (MantoStringUtils.isEmpty(str) || MantoStringUtils.isEmpty(str2) || MantoStringUtils.isEmpty(str4) || MantoStringUtils.isEmpty("vapp")) {
            return;
        }
        hashMap.put("flag", "vapp");
        if (MantoStringUtils.isEmpty(str3)) {
            str3 = com.jingdong.manto.b.g().b("share_h5");
        }
        String str5 = (str3 + "?appId=" + str) + "&type=" + str2;
        String str6 = (String) hashMap.get("path");
        if (!MantoStringUtils.isEmpty(str6)) {
            str5 = str5 + "&path=" + str6;
        }
        hashMap.put("defaultLink", str5);
        IShareManager iShareManager = (IShareManager) com.jingdong.a.n(IShareManager.class);
        if (iShareManager == null) {
            return;
        }
        iShareManager.shareMantoApp(activity, hashMap, shareCallback);
    }
}
