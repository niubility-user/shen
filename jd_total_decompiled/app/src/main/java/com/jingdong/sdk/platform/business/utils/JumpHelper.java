package com.jingdong.sdk.platform.business.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;

/* loaded from: classes10.dex */
public class JumpHelper {
    public static final int JUMP_TYPE_HTTP = 1;
    public static final int JUMP_TYPE_LAYER = 3;
    public static final int JUMP_TYPE_OPENAPP = 2;
    public static final int JUMP_TYPE_ROUTER = 4;

    public static void gotoMWithUrl(Context context, String str) {
        gotoMWithUrl(context, null, str);
    }

    public static void jump(Context context, String str, int i2) {
        if (i2 == 1) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            gotoMWithUrl(context, str);
        } else if (i2 != 2) {
            if (i2 != 4) {
                return;
            }
            JDRouter.to(context, str).open();
        } else if (TextUtils.isEmpty(str)) {
        } else {
            new OpenAppJumpBuilder.Builder(Uri.parse(str)).build().jump(context);
        }
    }

    public static void gotoMWithUrl(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        URLParamMap uRLParamMap = new URLParamMap();
        uRLParamMap.put(RemoteMessageConst.TO, str2);
        Bundle bundle = new Bundle();
        SerializableContainer serializableContainer = new SerializableContainer();
        serializableContainer.setMap(uRLParamMap);
        bundle.putSerializable("urlParamMap", serializableContainer);
        bundle.putString("urlAction", RemoteMessageConst.TO);
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("locUrl", str);
        }
        DeepLinkCommonHelper.startWebActivity(context, bundle, true);
    }
}
