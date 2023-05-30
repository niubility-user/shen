package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.unification.image.editor.ImageConstant;
import com.jingdong.common.unification.image.editor.ImageParam;
import com.jingdong.common.unification.video.VideoConstant;
import com.jingdong.common.unification.video.VideoParam;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class DeepLinkVideoAndImageHelper {
    private static final String TAG = "DeepLinkVideoAndImageHelper";

    public static void startImageEditorActivity(Context context, ImageParam imageParam) {
        DeepLinkUri.Builder host = new DeepLinkUri.Builder().scheme("jingdong").host("image_editor_activity");
        Bundle bundle = new Bundle();
        bundle.putSerializable(ImageConstant.IMAGE_PARAM, imageParam);
        DeepLinkDispatch.startActivityDirect(context, host.toString(), bundle);
    }

    public static void startImageEditorActivityForResult(Activity activity, ImageParam imageParam, int i2) {
        DeepLinkUri.Builder host = new DeepLinkUri.Builder().scheme("jingdong").host("image_editor_activity");
        Bundle bundle = new Bundle();
        bundle.putSerializable(ImageConstant.IMAGE_PARAM, imageParam);
        DeepLinkDispatch.startActivityForResult(activity, host.toString(), bundle, i2);
    }

    public static void startVideoEditorActivity(Context context, VideoParam videoParam) {
        DeepLinkUri.Builder host = new DeepLinkUri.Builder().scheme("jingdong").host("video_editor_activity");
        Bundle bundle = new Bundle();
        bundle.putSerializable(VideoConstant.VIDEO_PARAM, videoParam);
        DeepLinkDispatch.startActivityDirect(context, host.toString(), bundle);
    }

    public static void startVideoEditorActivityForResult(Activity activity, VideoParam videoParam, int i2) {
        DeepLinkUri.Builder host = new DeepLinkUri.Builder().scheme("jingdong").host("video_editor_activity");
        Bundle bundle = new Bundle();
        bundle.putSerializable(VideoConstant.VIDEO_PARAM, videoParam);
        DeepLinkDispatch.startActivityForResult(activity, host.toString(), bundle, i2);
    }

    public static void startVideoRecorderActivity(Context context, VideoParam videoParam) {
        DeepLinkUri.Builder host = new DeepLinkUri.Builder().scheme("jingdong").host("video_recorder_activity");
        Bundle bundle = new Bundle();
        bundle.putSerializable(VideoConstant.VIDEO_PARAM, videoParam);
        DeepLinkDispatch.startActivityDirect(context, host.toString(), bundle);
    }

    public static void startVideoRecorderActivityForResult(Activity activity, VideoParam videoParam, int i2) {
        OKLog.d(TAG, "startVideoRecorderActivityForResult");
        DeepLinkUri.Builder host = new DeepLinkUri.Builder().scheme("jingdong").host("video_recorder_activity");
        Bundle bundle = new Bundle();
        bundle.putSerializable(VideoConstant.VIDEO_PARAM, videoParam);
        DeepLinkDispatch.startActivityForResult(activity, host.toString(), bundle, i2);
    }
}
