package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

/* loaded from: classes5.dex */
public class DeepLinkMemeHelper {
    private static final String HOST = "meme";

    public static void startContentAuthorActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(64))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(JumpUtil.VALUE_DES_FAXIAN_AUTHOR_NEW).toString(), bundle);
        }
    }

    public static void startSearchActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(64))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST).addPathSegment("search").toString(), bundle);
        }
    }

    public static void startSearchResultActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(64))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST).addPathSegment("searchResult").toString(), bundle);
        }
    }
}
