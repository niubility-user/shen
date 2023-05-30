package com.jd.manto.d;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.manto.share.ShareProxyActivity;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.manto.sdk.api.IShareManager;
import java.util.HashMap;

/* loaded from: classes17.dex */
public class v implements IShareManager {
    @Override // com.jingdong.manto.sdk.api.IShareManager
    public void shareMantoApp(Activity activity, HashMap<String, Object> hashMap, IShareManager.ShareCallback shareCallback) {
        String str = (String) hashMap.get("url");
        String str2 = (String) hashMap.get("title");
        String str3 = (String) hashMap.get("imageUrl");
        String str4 = (String) hashMap.get("flag");
        String str5 = (String) hashMap.get("desc");
        String str6 = (String) hashMap.get("path");
        String str7 = (String) hashMap.get("channel");
        String str8 = (String) hashMap.get("mpId");
        String str9 = (String) hashMap.get("mpPath");
        String str10 = (String) hashMap.get("defaultLink");
        if (str3 != null && str4 != null) {
            String str11 = TextUtils.isEmpty(str5) ? "" : str5;
            String str12 = TextUtils.isEmpty(str2) ? "" : str2;
            if (TextUtils.isEmpty(str7)) {
                str7 = "Wxfriends,Wxmoments,QQfriends";
            }
            String str13 = str7;
            ShareInfo shareInfo = new ShareInfo(!TextUtils.isEmpty(str) ? str : str10, str12, str11, str3, str4);
            shareInfo.setChannels(str13);
            ShareProxyActivity.x(activity, shareInfo, shareCallback);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("errMessage", "imageUrl is null");
        shareCallback.onShareFailed(bundle);
    }
}
