package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.utils.DexAsyncUtil;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

/* loaded from: classes5.dex */
public class DeepLinkHomeHelper {
    public static void startHomeActivity(Context context, Bundle bundle) {
        if (context == null) {
            return;
        }
        Intent mainFrameActivityIntent = DexAsyncUtil.getMainFrameActivityIntent(context);
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt(RemoteMessageConst.TO, 0);
        mainFrameActivityIntent.putExtras(bundle);
        context.startActivity(mainFrameActivityIntent);
    }

    public static void startHomeOverSeaSelectActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_HOME_OVERSEA_SELECT_ACTIVITY).toString(), bundle);
    }
}
