package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

/* loaded from: classes5.dex */
public class DeepLinkVangophHelper {
    public static final String HOST_VANGOPH = "vangoph";
    public static final String PATH_COLLECTION_DETAIL = "collectionDetail";
    public static final String PATH_DETAIL = "detail";
    public static final String PATH_MAIN = "main";

    public static void startCollectionDetailActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_VANGOPH).addPathSegment(PATH_COLLECTION_DETAIL).toString(), bundle);
    }

    public static void startDetailActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_VANGOPH).addPathSegment("detail").toString(), bundle);
    }

    public static void startPinterestActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_VANGOPH).addPathSegment("main").toString(), bundle);
    }
}
