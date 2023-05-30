package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.videoplayer.VideoPlayerConstants;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

/* loaded from: classes5.dex */
public class VideoPlayerDeepLinkHelper {
    public static final String HOST_VIDEOPLAYER = "video_player_activity";

    public static void startVideoPlayer(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_VIDEOPLAYER).toString(), bundle);
    }

    public static void startVideoPlayerForLive(Context context, String str, String str2, String str3, int i2, String str4) {
        Bundle bundle = new Bundle();
        bundle.putInt(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_PLAY_TYPE, 1);
        bundle.putString("sku", str);
        bundle.putString(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_COVER_IMG_URL, str2);
        bundle.putString(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_VIDEO_URL, str3);
        bundle.putInt(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_JUMP_FROM, i2);
        bundle.putString(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_RES_ID, str4);
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_VIDEOPLAYER).toString(), bundle);
    }

    public static void startVideoPlayer(Context context, String str, String str2) {
        startVideoPlayer(context, str, str2, null, 0);
    }

    public static void startVideoPlayer(Context context, String str, String str2, String str3) {
        startVideoPlayer(context, str, str2, str3, 0);
    }

    public static void startVideoPlayer(Context context, String str, String str2, String str3, boolean z, ShareInfo shareInfo, String str4) {
        Bundle bundle = new Bundle();
        bundle.putInt(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_PLAY_TYPE, 2);
        bundle.putString("sku", str);
        bundle.putString(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_COVER_IMG_URL, str2);
        bundle.putString(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_VIDEO_URL, str3);
        bundle.putInt(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_SEEK_TO_POINT, 0);
        bundle.putParcelable(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_SHARE_INFO, shareInfo);
        bundle.putBoolean(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_SHARE_ENABLE, z);
        bundle.putString(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_EVENT_PARAMS, str4);
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_VIDEOPLAYER).toString(), bundle);
    }

    public static void startVideoPlayerForLive(Context context, String str, String str2, String str3, int i2, String str4, String str5) {
        Bundle bundle = new Bundle();
        bundle.putInt(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_PLAY_TYPE, 1);
        bundle.putString("sku", str);
        bundle.putString(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_COVER_IMG_URL, str2);
        bundle.putString(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_VIDEO_URL, str3);
        bundle.putInt(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_JUMP_FROM, i2);
        bundle.putString(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_RES_ID, str4);
        bundle.putString(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_VIDEO_ID, str5);
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_VIDEOPLAYER).toString(), bundle);
    }

    public static void startVideoPlayer(Context context, String str, String str2, String str3, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_PLAY_TYPE, 2);
        bundle.putString("sku", str);
        bundle.putString(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_COVER_IMG_URL, str2);
        bundle.putString(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_VIDEO_URL, str3);
        bundle.putInt(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_SEEK_TO_POINT, i2);
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_VIDEOPLAYER).toString(), bundle);
    }

    public static void startVideoPlayer(Context context, String str, String str2, String str3, int i2, int i3) {
        Bundle bundle = new Bundle();
        bundle.putInt(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_PLAY_TYPE, 2);
        bundle.putString("sku", str);
        bundle.putString(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_COVER_IMG_URL, str2);
        bundle.putString(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_VIDEO_URL, str3);
        bundle.putInt(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_SEEK_TO_POINT, i2);
        bundle.putInt(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_JUMP_FROM, i3);
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_VIDEOPLAYER).toString(), bundle);
    }

    public static void startVideoPlayer(Context context, String str, String str2, String str3, int i2, int i3, String str4) {
        Bundle bundle = new Bundle();
        bundle.putInt(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_PLAY_TYPE, 2);
        bundle.putString("sku", str);
        bundle.putString(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_COVER_IMG_URL, str2);
        bundle.putString(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_VIDEO_URL, str3);
        bundle.putInt(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_SEEK_TO_POINT, i2);
        bundle.putInt(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_JUMP_FROM, i3);
        bundle.putString(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_VIDEO_ID, str4);
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_VIDEOPLAYER).toString(), bundle);
    }
}
