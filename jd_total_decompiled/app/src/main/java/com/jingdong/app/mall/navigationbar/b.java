package com.jingdong.app.mall.navigationbar;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;

/* loaded from: classes4.dex */
public class b {
    public static void a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        URLParamMap uRLParamMap = new URLParamMap();
        uRLParamMap.put(RemoteMessageConst.TO, str);
        Bundle bundle = new Bundle();
        SerializableContainer serializableContainer = new SerializableContainer();
        serializableContainer.setMap(uRLParamMap);
        bundle.putSerializable("urlParamMap", serializableContainer);
        bundle.putString("urlAction", RemoteMessageConst.TO);
        DeepLinkCommonHelper.startWebActivity(context, bundle, true);
    }

    public static void b(Context context, String str, int i2) {
        if (Log.D) {
            Log.d("Navigation_Bubble", "jump_url=" + str + " jump_type=" + i2);
        }
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        if (i2 == 1) {
            a(context, str);
        } else if (i2 == 2) {
            JDRouter.to(context, str).open();
        } else if (i2 != 3) {
        } else {
            new OpenAppJumpBuilder.Builder(Uri.parse(str)).build().jump(context);
        }
    }
}
