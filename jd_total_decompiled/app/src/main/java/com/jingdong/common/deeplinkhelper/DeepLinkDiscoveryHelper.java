package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

/* loaded from: classes5.dex */
public class DeepLinkDiscoveryHelper {
    public static final String HOST_CONTENT_COMMENTS = "content_comments";
    public static final String HOST_DISCOVERY_ALL_COMMENT = "finder_all_comment";
    public static final String HOST_DISCOVERY_ARTICLE = "faxian_article";
    public static final String HOST_DISCOVERY_COMMENT_DETAIL = "finder_comment_detail";
    public static final String HOST_DISCOVERY_COMMENT_INPUT = "faxian_comment_input";
    public static final String HOST_GOODS_DISCOVERY_4_EVENT = "faxian";

    public static void startContentCommentsActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("content_comments").toString(), bundle);
    }

    public static void startDiscovery4EventActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("faxian").toString(), bundle);
    }

    public static void startDiscoveryAllCommentActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("finder_all_comment").toString(), bundle);
    }

    public static void startDiscoveryArticleActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("faxian_article").toString(), bundle);
    }

    public static void startDiscoveryCommentDetailActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("finder_comment_detail").toString(), bundle);
    }

    public static void startDiscoveryCommentInputActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("faxian_comment_input").toString(), bundle);
    }
}
